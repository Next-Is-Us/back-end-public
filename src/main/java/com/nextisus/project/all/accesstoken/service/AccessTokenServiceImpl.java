package com.nextisus.project.all.accesstoken.service;

import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenResponseDto;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.User;
import com.nextisus.project.repository.LinkRepository;
import com.nextisus.project.repository.RoleRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.exception.EnumUtils;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LinkRepository linkRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CreateAccessTokenResponseDto createAccessToken(CreateAccessTokenRequestDto dto) {

        User user = null;

        // 계정 찾기
        if (dto.getLink() == null) { // dto 의 link 가 null 이면 admin/doctor -> nickname 으로만 찾아도 됨
            user = userRepository.getByNickname(dto.getNickname());
        }
        else { // 그렇지 않은 경우, mom/daughter/son -> link 와 nickname 으로 찾아야 함
            user = userRepository.getByLinkAndNickname(linkRepository.getByLink(dto.getLink()), dto.getNickname());
        }

        // Role
        List<Role> roles = user.getUserRoles().stream()
                .map(EnumUtils::fromUserRole)
                .map(roleRepository::getByRoleName)
                .collect(Collectors.toList());

        // accessToken 발급
        String accessToken = jwtTokenProvider.createToken(user.getId().toString(), roles);

        // 응답
        return CreateAccessTokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
