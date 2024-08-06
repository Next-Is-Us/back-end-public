package com.nextisus.project.util.init;

import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.RoleName;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRole;
import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.repository.HealthRecordRepository;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.RoleRepository;
import com.nextisus.project.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ConditionRepository conditionRepository;
    private final NftRepository nftRepository;
    private final HealthRecordRepository healthRecordRepository;

    @PostConstruct
    public void init() {
        createRole();
        User adminUser = createAdminAccount();
        createDoctorAccounts(5);
//        createConditions(adminUser);
//        createNftsAndHealthRecord(adminUser);
    }

    // 역할 생성
    private void createRole() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(1L, RoleName.ROLE_ADMIN));
            roleRepository.save(new Role(2L, RoleName.ROLE_MOM));
            roleRepository.save(new Role(3L, RoleName.ROLE_SON));
            roleRepository.save(new Role(4L, RoleName.ROLE_DAUGHTER));
            roleRepository.save(new Role(5L, RoleName.ROLE_DOCTOR));
        }
    }

    // 관리자 계정 생성
    private User createAdminAccount() {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.getByRoleName(RoleName.ROLE_ADMIN);

            // User 생성
            User adminUser = User.builder()
                    .nickname("관리자")
                    .isNotificationEnabled(false)
                    .build();

            // UserRole 생성
            UserRole adminUserRole = UserRole.builder()
                    .user(adminUser)
                    .role(adminRole)
                    .build();

            // UserRole 설정
            adminUser.addUserRole(adminUserRole);

            // User 저장
            return userRepository.save(adminUser);
        }
        return userRepository.findAll().get(0); // 이미 존재하는 사용자가 있는 경우 첫 번째 사용자 반환
    }

    // 의사 계정 여러 개 생성
    private void createDoctorAccounts(int numberOfDoctors) {
        Role doctorRole = roleRepository.getByRoleName(RoleName.ROLE_DOCTOR);

        for (int i = 1; i <= numberOfDoctors; i++) {
            String nickname = "의사" + i;

            // 중복된 닉네임 체크
            if (userRepository.existsByNickname(nickname)) {
                continue; // 중복된 닉네임이 있으면 다음으로 넘어감
            }

            // User 생성
            User doctorUser = User.builder()
                    .nickname(nickname)
                    .isNotificationEnabled(true)
                    .build();

            // UserRole 생성
            UserRole doctorUserRole = UserRole.builder()
                    .user(doctorUser)
                    .role(doctorRole)
                    .build();

            // UserRole 설정
            doctorUser.addUserRole(doctorUserRole);

            // User 저장
            userRepository.save(doctorUser);
        }
    }

    /*// 초기 Condition 생성
    private void createConditions(User user) {
        List<Condition> conditions = new ArrayList<>();
        for (int i = 1; i <= 180; i++) {
            Condition condition = Condition.builder()
                    .sleepTime("8 hours")
                    .isBlushing(false)
                    .isHeadache(false)
                    .isStomachache(false)
                    .isConstipated(false)
                    .isMusclePainful(false)
                    .isSkinTroubled(false)
                    .isNumbness(false)
                    .isChilled(false)
                    .isDepressed(false)
                    .record("Record " + i)
                    .year(2023L)
                    .month(7L)
                    .day((long) (i % 30 + 1))
                    .user(user)
                    .build();
            conditions.add(condition);
        }
        conditionRepository.saveAll(conditions);
    }

    // 초기 NFT 및 HealthRecord 생성
    private void createNftsAndHealthRecord(User user) {
        List<Condition> conditions = conditionRepository.findAllByUser_Id(user.getId());
        for (int i = 0; i < 6; i++) {
            Nft nft = Nft.builder()
                    .user(user)
                    .recordPeriod("2023.07.01~2023.07.30")
                    .week("4 weeks")
                    .build();
            nftRepository.save(nft);
            for (int j = i * 30; j < (i + 1) * 30; j++) {
                conditions.get(j).setNft(nft);
            }
        }
        HealthRecord healthRecord = HealthRecord.builder()
                .user(user)
                .recordPeriod("2023.07.01~2023.07.30")
                .week("4 weeks")
                .nftCount(6L)
                .build();
        healthRecordRepository.save(healthRecord);
        List<Nft> nfts = nftRepository.findAllByUser_Id(user.getId());
        for (Nft nft : nfts) {
            nft.setHealthRecord(healthRecord);
        }
    }*/
}