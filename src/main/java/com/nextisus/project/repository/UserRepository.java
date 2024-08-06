package com.nextisus.project.repository;

import com.nextisus.project.domain.Link;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRole;
import java.util.List;
import java.util.Optional;

import com.nextisus.project.exception.user.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByLinkAndNickname(Link link, String nickname);
    Optional<User> findByNickname(String nickname);
    List<User> findByLink(Link link);
    Optional<User> findByIdAndLink(Long id, Link link);

    boolean existsByNickname(String nickname);
//    List<User> findAllByLinkAndUserRoles(Link link, List<UserRole> userRoles);
    @Query("SELECT u FROM User u JOIN u.userRoles ur WHERE ur.role IN :roles AND u.link = :link")
    List<User> findAllByLinkAndRoles(@Param("link") Link link, @Param("roles") List<Role> roles);
    default User getByUser(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

    default User getByNickname(String nickname) {
        return findByNickname(nickname).orElseThrow(UserNotFoundException::new);
    }

    default User getByLinkAndNickname(Link link, String nickname) {
        return findByLinkAndNickname(link, nickname).orElseThrow(UserNotFoundException::new);
    }
    Long countByLink_Id(Long linkId);
}
