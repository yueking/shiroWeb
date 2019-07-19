package com.yueking.core.shiro.service.impl;

import com.yueking.core.shiro.dao.PermissionDao;
import com.yueking.core.shiro.dao.RoleDao;
import com.yueking.core.shiro.entity.Permission;
import com.yueking.core.shiro.entity.Role;
import com.yueking.core.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    @Override
    public Role createRole(Role role) {
        return roleDao.saveAndFlush(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteById(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        List permissionListNew= new ArrayList();
        //1.获取权限列表
        for (Long permissionId : permissionIds) {
            Optional<Permission> optional = permissionDao.findById(permissionId);
            if (optional != null&&optional.get() != null) {
                permissionListNew.add(optional.get());
            }
        }


        Optional<Role> rOptional = roleDao.findById(roleId);
        //2.获取 role 并为 rols permissionSet 增加新权限
        if (rOptional != null && rOptional.get() != null) {
            Role role = rOptional.get();
            Set<Permission> permissions = role.getPermissions();
            permissions.addAll(permissionListNew);

            role.setPermissions(permissions);

            roleDao.saveAndFlush(role);
        }


    }

    @Override
    public void unCorrelationPermissions(Long roleId, Long... permissionIds) {

        List permissionListNew= new ArrayList();
        //1.获取权限列表
        for (Long permissionId : permissionIds) {
            Optional<Permission> optional = permissionDao.findById(permissionId);
            if (optional != null&&optional.get() != null) {
                permissionListNew.add(optional.get());
            }
        }


        Optional<Role> rOptional = roleDao.findById(roleId);
        //2.获取 role 并为 rols permissionSet 删除新权限
        if (rOptional != null && rOptional.get() != null) {
            Role role = rOptional.get();
            Set<Permission> permissions = role.getPermissions();
            permissions.removeAll(permissionListNew);
            role.setPermissions(permissions);

            roleDao.saveAndFlush(role);
        }

    }
}
