package com.yueking.core.shiro.service.impl;

import com.yueking.core.shiro.dao.RoleDao;
import com.yueking.core.shiro.dao.UserDao;
import com.yueking.core.shiro.entity.Permission;
import com.yueking.core.shiro.entity.Role;
import com.yueking.core.shiro.entity.User;
import com.yueking.core.shiro.service.UserService;
import com.yueking.core.shiro.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PasswordHelper passwordHelper;

    @Override
    public User createUser(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.save(user);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        Optional<User> optional = userDao.findById(userId);
        if (optional != null && optional.get() != null) {
            User user = optional.get();
            user.setPassword(newPassword);
            passwordHelper.encryptPassword(user);
            userDao.saveAndFlush(user);
        }
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        List<Role> roleListNew = new ArrayList<>();

        for (Long roleId : roleIds) {
            Optional<Role> optionalRole = roleDao.findById(roleId);
            if (optionalRole != null) {
                Role role = optionalRole.get();
                if (role != null) {
                    roleListNew.add(role);
                }
            }
        }

        Optional<User> userOptional = userDao.findById(userId);
        if (userOptional != null) {
            User user = userOptional.get();
            if (user != null) {
                Set<Role> roles = user.getRoles();
                roles.addAll(roleListNew);

                user.setRoles(roles);

                userDao.saveAndFlush(user);
            }
        }

    }

    @Override
    public void unCorrelationRoles(Long userId, Long... roleIds) {
        List<Role> roleListNew = new ArrayList<>();

        for (Long roleId : roleIds) {
            Optional<Role> optionalRole = roleDao.findById(roleId);
            if (optionalRole != null && optionalRole.get() != null) {
                Role role = optionalRole.get();
                if (role != null) {
                    roleListNew.add(role);
                }
            }
        }

        Optional<User> userOptional = userDao.findById(userId);
        if (userOptional != null) {
            User user = userOptional.get();
            if (user != null) {
                Set<Role> roles = user.getRoles();
                roles.removeAll(roleListNew);

                user.setRoles(roles);

                userDao.saveAndFlush(user);
            }
        }

    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> getRolesByUser(User user) {
        Set<String> roleNameList = new HashSet<>();
        Set<Role> rolesList = user.getRoles();
        for (Role role : rolesList) {
            roleNameList.add(role.getRole());
        }
        return roleNameList;
    }

    @Override
    public Set<String> getPermissionsByUser(User user) {
        List<Permission> permissionList = new ArrayList<>();
        Set<String> permissionNameList =  new HashSet<>();

        if (user != null) {
            Set<Role> userRoles = user.getRoles();
            for (Role userRole : userRoles) {
                permissionList.addAll(userRole.getPermissions());
            }

            for (Permission permission : permissionList) {
                permissionNameList.add(permission.getPermission());
            }
            return permissionNameList;
        } else {
            return null;
        }
    }

/*
    @Deprecated
    @Override
    public Set<String> findRoles(String username) {
        Set<String> roleNameList = new HashSet<>();
        User user = userDao.findByUsername(username);
        Set<Role> rolesList = user.getRoles();
        for (Role role : rolesList) {
            roleNameList.add(role.getRole());
        }
        return roleNameList;
    }

    @Deprecated
    @Override
    public Set<String> findPermissions(String username) {
        List<Permission> permissionList = new ArrayList<>();
        Set<String> permissionNameList =  new HashSet<>();

        User user = userDao.findByUsername(username);
        if (user != null) {
            Set<Role> userRoles = user.getRoles();

            for (Role userRole : userRoles) {
                permissionList.addAll(userRole.getPermissions());
            }

            for (Permission permission : permissionList) {
                permissionNameList.add(permission.getPermission());
            }
           return permissionNameList;
        } else {
            return null;
        }

    }*/
}
