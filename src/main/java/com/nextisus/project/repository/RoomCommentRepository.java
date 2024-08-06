package com.nextisus.project.repository;

import com.nextisus.project.domain.RoomComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomCommentRepository extends JpaRepository<RoomComment, Long> {
    Page<RoomComment> findAllByRoomPost_IdOrderByCreateAtDesc(Long roomPostId, Pageable pageable);
    Long countByRoomPost_Id(Long roomPostId);
}
