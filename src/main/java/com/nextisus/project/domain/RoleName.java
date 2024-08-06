package com.nextisus.project.domain;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {
    ROLE_ADMIN, ROLE_MOM, ROLE_SON, ROLE_DAUGHTER, ROLE_DOCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
