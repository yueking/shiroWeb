package com.yueking.core.shiro.service.impl;

import com.yueking.core.shiro.dao.PermissionDao;
import com.yueking.core.shiro.entity.Permission;
import com.yueking.core.shiro.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.save(permission);
    }


    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deleteById(permissionId);
    }
}
