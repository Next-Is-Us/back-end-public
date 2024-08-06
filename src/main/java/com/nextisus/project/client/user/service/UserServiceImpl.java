package com.nextisus.project.client.user.service;

import com.nextisus.project.domain.Link;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.User;
import com.nextisus.project.repository.LinkRepository;
import com.nextisus.project.repository.RoleRepository;
import com.nextisus.project.client.user.dto.SignUpRequestDto;
import com.nextisus.project.client.user.dto.SignUpResponseDto;
import com.nextisus.project.exception.user.UserNicknameDuplicatedException;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.exception.EnumUtils;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto dto) {

        // Link
        Link link = linkRepository.getByLink(dto.getLink());

        // Role
        List<Role> roles = dto.getUserRoles().stream()
                .map(EnumUtils::fromString)
                .map(roleRepository::getByRoleName)
                .collect(Collectors.toList());

        // Nickname
        if (userRepository.findByLinkAndNickname(link, dto.getNickname()).isPresent()) {
            log.info("같은 링크 내 닉네임이 중복임");
            throw new UserNicknameDuplicatedException();
        }

        // 회원가입
        User user = User.toEntity(dto, roles, link);
        User savedUser = userRepository.save(user);

        // accessToken 발급
        String accessToken = jwtTokenProvider.createToken(user.getId().toString(), roles);

        // 응답
        return SignUpResponseDto.from(savedUser, accessToken);
    }
}
