package com.yueking.core.shiro.service;


import com.yueking.core.shiro.entity.Permission;

public interface PermissionService {
    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
