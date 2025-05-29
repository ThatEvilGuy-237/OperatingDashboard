package com.evil.backend.security;

import com.evil.backend.user.entity.PrivilegeType;

public class AuthorityUtil {
    public static String authority(PrivilegeType type) {
        return type.name();
    }
}
