package com.vhaven.vhavenapp.entity;

import lombok.Getter;

import java.util.Set;

@Getter
public enum ROLE {

    ADMIN(Set.of(Permissions.PRODUCT_ADD,Permissions.PRODUCT_VIEW,Permissions.PRODUCT_EDIT,Permissions.PRODUCT_DELETE)),
    STAFF(Set.of(Permissions.PRODUCT_ADD,Permissions.PRODUCT_VIEW,Permissions.PRODUCT_EDIT)),
    USER(Set.of(Permissions.PRODUCT_VIEW));

    private final Set<Permissions> permissions;
    ROLE(Set<Permissions> permissions) {
        this.permissions = permissions;
    }


}
