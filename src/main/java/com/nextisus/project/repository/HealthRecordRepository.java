package com.nextisus.project.repository;

import com.nextisus.project.domain.HealthRecord;
import com.nextisus.project.exception.healthrecord.HealthRecordNotFoundException;
import com.nextisus.project.exception.user.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
    Optional<HealthRecord> findByHealthRecordId(Long healthRecordId);
    List<HealthRecord> findAllByUser_IdOrderByCreateAtDesc(Long userId);

    default HealthRecord getByHealthRecordId(Long healthRecordId) {
        return findByHealthRecordId(healthRecordId).orElseThrow(HealthRecordNotFoundException::new);
    }
}
