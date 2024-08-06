package com.nextisus.project.repository;

import com.nextisus.project.domain.RoomPost;
import com.nextisus.project.exception.roompost.RoomPostNotFoundException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPostRepository extends JpaRepository<RoomPost, Long> {

    Optional<RoomPost> findById(Long id);
    Page<RoomPost> findAllByRoomIdOrderByCreateAtDesc(Long roomId, Pageable pageable);

    default RoomPost getById(Long id) {
        return findById(id).orElseThrow(RoomPostNotFoundException::new);
    }
}
