package com.nextisus.project.repository;

import com.nextisus.project.domain.InfoPost;
import com.nextisus.project.exception.infopost.InfoPostNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPostRepository extends JpaRepository<InfoPost, Long> {

    default InfoPost getByInfoPostId(Long id) {
        return findById(id).orElseThrow(InfoPostNotFoundException::new);
    }

    Page<InfoPost> findAllByOrderByCreateAtDesc(Pageable pageable);
}
