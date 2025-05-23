package com.thatsmyspot.userservice.infrastructure;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    PENDING("PENDING"),
    SUSPENDED("SUSPENDED"),
    DELETED("DELETED");

    private final String displayName;

    UserStatus(String displayName) {
        this.displayName = displayName;
    }
}
