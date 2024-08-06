package com.nextisus.project.repository;

import com.nextisus.project.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUser_IdAndRole_Id(Long userId, Long roleId);
    UserRole findByUser_Id(Long userId);
}
