package com.nextisus.project.mom.condition.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.nextisus.project.all.infopost.service.AllInfoPostServiceImpl;
import com.nextisus.project.domain.*;
import com.nextisus.project.exception.healthrecord.MomNotFoundExecption;
import com.nextisus.project.mom.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.client.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.client.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.repository.*;
import com.nextisus.project.client.nft.service.NftServiceImpl;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.constructor.ConstructorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final NftServiceImpl nftServiceImpl;
    private final NftRepository nftRepository;
    private final AllInfoPostServiceImpl allInfoPostServiceImpl;
    private final LinkRepository linkRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    //오늘의 상태 기록
    @Override
    @Transactional
    public SuccessResponse<?> createCondition(CreateConditionRequestDto request, Long userId) {

        // 오늘 날짜
        LocalDateTime today = LocalDateTime.now();

        // 날짜 형식 포맷
        DateTimeFormatter yearformatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthformatter = DateTimeFormatter.ofPattern("M");
        DateTimeFormatter dayformatter = DateTimeFormatter.ofPattern("dd");
        String year = today.format(yearformatter);
        String month = today.format(monthformatter);
        String day = today.format(dayformatter);

        //수면 시간 포맷
        String sleepTime = request.getSleepTime() + "시간";

        // 사용자 조회
        User user = userRepository.getByUser(userId);

        // 새로운 상태 엔티티 생성
        Condition condition = Condition.builder()
                .sleepTime(sleepTime)
                .isBlushing(request.getIsBlushing())
                .isHeadache(request.getIsHeadache())
                .isStomachache(request.getIsStomachache())
                .isConstipated(request.getIsConstipated())
                .isMusclePainful(request.getIsMusclePainful())
                .isSkinTroubled(request.getIsSkinTroubled())
                .isNumbness(request.getIsNumbness())
                .isChilled(request.getIsChilled())
                .isDepressed(request.getIsDepressed())
                .record(request.getRecord())
                .year(Long.parseLong(year))
                .month(Long.parseLong(month))
                .day(Long.parseLong(day))
                .user(user)
                .build();

        // 연관관계 설정
        condition.createdCondition(user);

        // DB에 저장
        Condition save = conditionRepository.save(condition);

        //해당 유저가 기록한 상태 기록의 갯수
        Long countCondition = conditionRepository.countByUser_Id(userId);

        // 방금 기록한 상태의 상태id가 30의 배수면 (30번째, 60번째, 90번째 ... )
        if( countCondition % 30 == 0 && countCondition != 0 ) {
            //nft 생성
                Nft nft = nftServiceImpl.createNft(userId);
                //nftId가 null인 condition 가져옴
                List<Condition> allByNftIsNull = conditionRepository.findAllByNftIsNullAndUser_Id(userId);
                allByNftIsNull.forEach(c -> {
                    c.setNft(nft); //영속성 컨텍스트 어쩌구..
                });
        }
        // 성공 응답 생성
        return SuccessResponse.of(condition);
    }

    // 날짜별 상태 기록 여부 조회
    @Override
    public ConditionListResponseDtoByDate getConditionByDate(Long year, Long month, Long day, Long userId, String userRole) {
        if(userRole.equals("ROLE_MOM")){
            return getConditionByDateResult(year,month,day,userId);
        }
        else {
            //자녀 유저
            Optional<User> user = userRepository.findById(userId);
            //엄마와 자녀유저를 담은 리스트
            List<User> byLink = userRepository.findByLink(user.get().getLink());
            User findUser = null;
            for(User u : byLink){
                UserRole findMom = userRoleRepository.findByUser_IdAndRole_Id(u.getId(), 2L);
                if(findMom != null){
                    Optional<User> mom = userRepository.findById(findMom.getId());
                    findUser = mom.get();
                    break;
                }
            }
            return getConditionByDateResult(year,month,day,findUser.getId());
        }
    }

    public ConditionListResponseDtoByDate getConditionByDateResult(Long year, Long month, Long day, Long userId) {

        User user = userRepository.getByUser(userId);
        Long countLink = userRepository.countByLink_Id(user.getLink().getId());
        Optional<Link> link = linkRepository.findById(user.getLink().getId());
        Boolean isInvited = false;
        UserRole userRole = userRoleRepository.findByUser_Id(userId);
        Optional<Role> role = roleRepository.findById(userRole.getRole().getId());
        if(countLink > 1) {
            isInvited = true;
        }
        String recordDate;
        Boolean isRecording;
        Boolean existCondition = conditionRepository.existsByYearAndMonthAndDayAndUser_Id(year, month, day, userId);
        if(existCondition) {
            recordDate = year+"."+month+"."+day;
            isRecording = true;
        }else{
            recordDate = null;
            isRecording = false;
        }
        //엔티티 생성
        ConditionListResponseDtoByDate response = new ConditionListResponseDtoByDate(
                recordDate,
                isRecording,
                user.getNickname(),
                isInvited,
                link.get().getLink(),
                role.get().getRoleName().toString()
        );
        return response;
    }

    //해당 날짜에 기록한 내용 상세 조회
    @Override
    public ConditionListResponseDto getDetailConditionByDate(Long year, Long month, Long day, String userRole, Long userId) {
        if(userRole.equals("ROLE_MOM")){
            return getDetailConditionByDateResult(year,month,day, userId);
        }
        else {
            //자녀 유저
            Optional<User> user = userRepository.findById(userId);
            //엄마와 자녀유저를 담은 리스트
            List<User> byLink = userRepository.findByLink(user.get().getLink());
            User findUser = null;
            for(User u : byLink){
                UserRole findMom = userRoleRepository.findByUser_IdAndRole_Id(u.getId(), 2L);
                if(findMom != null){
                    Optional<User> mom = userRepository.findById(findMom.getId());
                    findUser = mom.get();
                    break;
                }
            }
            return getDetailConditionByDateResult(year,month,day, findUser.getId());
        }

    }

    public ConditionListResponseDto getDetailConditionByDateResult(Long year, Long month, Long day, Long userId) {

        //요청한 유저가 가지고 있는 기록
        ConditionListResponseDto response = null;
        List<Condition> findConditions = conditionRepository.findAllByYearAndMonthAndDayAndUser_Id(year, month, day, userId);
        if(findConditions.size() > 0) {
            String date = year + "년 " + month + "월 " + day + "일";
            response = ConditionListResponseDto.from(findConditions.get(0),date);
        }
        return response;
    }
}
