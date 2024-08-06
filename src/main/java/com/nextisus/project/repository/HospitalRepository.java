package com.nextisus.project.repository;

import com.nextisus.project.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    //검색
    List<Hospital> findAllByHospitalNameContaining(String keyword);
}
