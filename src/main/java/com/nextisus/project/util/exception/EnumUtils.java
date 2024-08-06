package com.nextisus.project.util.exception;

import com.nextisus.project.domain.RoleName;
import com.nextisus.project.domain.UserRole;
import com.nextisus.project.exception.role.RoleNotFoundException;

public class EnumUtils {
    public static RoleName fromString(String roleName) {
        try {
            return RoleName.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException();
        }
    }

    public static RoleName fromUserRole(UserRole userRole) {
        try {
            return RoleName.valueOf(userRole.getRole().getRoleName().name());
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException();
        }
    }
}