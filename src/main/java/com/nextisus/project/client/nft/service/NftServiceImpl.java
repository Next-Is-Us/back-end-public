package com.nextisus.project.client.nft.service;

import com.nextisus.project.domain.*;
import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.client.healthrecord.service.HealthRecordServiceImpl;
import com.nextisus.project.client.nft.dto.response.NftResponseDto;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.repository.UserRoleRepository;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NftServiceImpl implements NftService {

    private final ConditionRepository conditionRepository;
    private final NftRepository nftRepository;
    private final UserRepository userRepository;
    private final HealthRecordServiceImpl healthRecordServiceImpl;
    private final UserRoleRepository userRoleRepository;

    @Override
    public NftResponseDto getNfts(Long userId, String userRole) {
        if(userRole.equals("ROLE_MOM")){
            return getNftsResult(userId);
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
            return getNftsResult(findUser.getId());
        }
    }

    public NftResponseDto getNftsResult(Long userId) {
        List<Condition> conditions = conditionRepository.findAllByUser_Id(userId);
        List<Nft> nfts = nftRepository.findAllByUser_Id(userId);
        Long[] pieceOfNfts = {0L, 1L, 2L, 3L, 4L, 5L, 6L};

        int conditionSize = conditions.size(); // 유저가 기록한 오늘의 상태 수
        int nftSize = nfts.size(); // 유저가 발급 받은 nft 개수
        int index = conditionSize - (nftSize * 30);
        Long pieceOfNft = null;
        if (index <= 30) {
            pieceOfNft = pieceOfNfts[Math.min(index / 5, 6)];
        }
        NftResponseDto nftResponseDto = NftResponseDto.from(pieceOfNft,nftSize);
        return nftResponseDto;
    }

    @Override
    @Transactional
    public Nft createNft(Long userId) {

        List<Condition> conditions = conditionRepository.getAllById(userId);
        int conditionSize = conditions.size();
        LocalDateTime startTime = conditions.get(0).getCreateAt();
        LocalDateTime endTime = conditions.get(conditionSize - 1).getCreateAt();

        // "yyyy.M.dd"형식으로 포맷
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy.M.dd");
        String startDate1 = formatter1.format(startTime);
        String endDate1 = formatter1.format(endTime);
        String recordPeriod = startDate1 + "~" + endDate1;

        // "yyyyMMdd" 형식으로 포맷
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        String startDate2 = formatter2.format(startTime);
        String endDate2 = formatter2.format(endTime);

        // LocalDate 객체 생성
        LocalDate start = LocalDate.parse(startDate2, formatter2);
        LocalDate end = LocalDate.parse(endDate2, formatter2);

        // 두 날짜 사이의 주 수 계산
        long weeksBetween = ChronoUnit.WEEKS.between(start, end);

        String week = weeksBetween + "주";

        // 유저 찾기
        User user = userRepository.getByUser(userId);

        // 엔티티 생성
        Nft nft = Nft.builder()
                .user(user)
                .recordPeriod(recordPeriod)
                .week(week)
                .build();

        // 연관관계 맺기
        nft.createNft(user);

        // DB에 저장
        Nft save = nftRepository.save(nft);

        //해당 유저가 발급 받은 nft의 수
        Long countNft = nftRepository.countByUser_Id(userId);

        //발급 받은 nft의 갯수가 6의 배수 (6 , 12, 18 ... )
        if(countNft % 6 == 0 && countNft != 0) {
            HealthRecord healthRecord = healthRecordServiceImpl.createHealthRecord(userId,countNft);

            //healthInfoId가 null인 nft 가져오기
            List<Nft> allByHealthRecordIsNull = nftRepository.findAllByHealthRecordIsNullAndUser_Id(userId);
            allByHealthRecordIsNull.forEach(n -> {
                n.setHealthRecord(healthRecord);
            });
        }
        return save;
    }

}
