package com.assignment.ecommerce.model;

import java.util.Set;
import java.util.HashSet;

public enum Role {
    ADMIN(Set.of(Permission.READ, Permission.WRITE, Permission.MANAGE_USERS, Permission.PLACE_ORDER)),
    SUPERVISOR(Set.of(Permission.READ)),
    CUSTOMER(Set.of(Permission.PLACE_ORDER));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }

    public enum Permission {
        READ,
        WRITE,
        MANAGE_USERS,
        PLACE_ORDER
    }
}