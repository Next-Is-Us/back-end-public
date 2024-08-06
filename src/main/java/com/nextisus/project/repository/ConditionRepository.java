package com.nextisus.project.repository;

import com.nextisus.project.domain.Nft;
import com.nextisus.project.exception.condition.HealthRecordNotFoundException;
import com.nextisus.project.exception.condition.UserConditionNotFoundException;
import com.nextisus.project.domain.Condition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConditionRepository  extends JpaRepository<Condition, Long> {
    List<Condition> findByYear(Long year);
    List<Condition> findAllByUser_Id(Long userId);
    List<Condition> findAllByHealthRecord_HealthRecordId(Long healthRecordId);

    default List<Condition> getAllById(Long userId) {
        List<Condition> conditions = findAllByUser_Id(userId);
        if(conditions.isEmpty()) {
            throw new UserConditionNotFoundException();
        }
        return conditions;
    }

    default List<Condition> getAllByHealthRecord(Long healthRecordId) {
        List<Condition> conditions = findAllByHealthRecord_HealthRecordId(healthRecordId);
        if(conditions.isEmpty()) {
            throw new HealthRecordNotFoundException();
        }
        return conditions;
    }
    List<Condition> findAllByNftIsNullAndUser_Id(Long userId);
    List<Condition> findAllByNft_NftId(Long nftId);
    Long countByUser_Id(Long userId);

    @Query("SELECT MIN(c.createAt) FROM Condition c WHERE c.nft.user.id = :userId")
    LocalDateTime findOldestCreateAtByUserId(@Param("userId") Long userId);

    @Query("SELECT MAX(c.createAt) FROM Condition c WHERE c.nft.user.id= :userId")
    LocalDateTime findLatestCreateAtByUserId(@Param("userId") Long userId);

    List<Condition> findAllByHealthRecordIsNullAndUser_Id(Long userId);

    Page<Condition> findAllByHealthRecord_HealthRecordId(Long healthRecordId, Pageable pageable);

    Boolean existsByYearAndMonthAndDayAndUser_Id(Long year, Long month, Long day, Long userId);

    List<Condition> findAllByYearAndMonthAndDayAndUser_Id(Long year, Long month, Long day, Long userId);
}


