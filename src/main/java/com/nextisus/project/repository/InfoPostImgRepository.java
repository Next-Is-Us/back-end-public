package com.nextisus.project.repository;

import com.nextisus.project.domain.InfoPostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoPostImgRepository extends JpaRepository<InfoPostImg,Long> {
    List<InfoPostImg> findAllByInfoPost_Id(Long infoPostId);
}
