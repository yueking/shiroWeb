package com.yueking.core.shiro.service;

import com.yueking.core.shiro.dao.PermissionDao;
import com.yueking.core.shiro.dao.RoleDao;
import com.yueking.core.shiro.dao.UserDao;
import com.yueking.core.shiro.entity.Permission;
import com.yueking.core.shiro.entity.Role;
import com.yueking.core.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @BeforeMethod
    public void setUp() {
    }

    @Autowired
    PermissionDao permissionDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Test
    public void testPermissionAdd() {
        for (int i = 1; i < 4; i++) {
            Permission entity = new Permission();
            entity.setPermission("permission" + i);
            entity.setDescription("desc");
            entity.setAvailable(true);

            permissionDao.save(entity);
        }

    }

    @Test(dependsOnMethods = "testPermissionAdd")
    public void testRoleAdd() {
        List<Permission> list = permissionDao.findAll();

        for (int i = 1; i < 3; i++) {
            Role entity = new Role();
            entity.setRole("role"+i);
            entity.setName("role"+i);
            entity.setDescription("desc");
            entity.setAvailable(true);

            entity.setPermissions(new HashSet<>(list));

            roleDao.save(entity);
        }

    }

    @Test(dependsOnMethods = {"testPermissionAdd","testRoleAdd"})
    public void testUserAdd() {
        List<Role> list = roleDao.findAll();
        for (int i = 1; i < 3; i++) {
            User entity = new User();
            entity.setUsername("user"+i);
            entity.setPassword("user"+i);
            entity.setSalt("salt");

            entity.setRoles(new HashSet<>(list));

            userDao.save(entity);
        }
    }

    @Test
    public void testDeleteUserAll() {
        List<User> list = userDao.findAll();
        for (User usersEntity : list) {
            userDao.delete(usersEntity);
        }
    }

    @Test
    public void testUserDelete() {
        userDao.deleteById(7l);
    }

    @Test
    public void testUserUpdateRolesAdd() {
        List<Role> list = roleDao.findAll();
        Optional<User> result = userDao.findById(7l);
        User user = result.get();

        user.setRoles(new HashSet<>(list));
        System.out.println("user:" + user);
        userDao.saveAndFlush(user);
    }

    @Test
    public void testUserUpdateRolesDelete() {
        Optional<User> result = userDao.findById(7l);
        User user = result.get();

        user.setRoles(new HashSet<>());
        System.out.println("user:" + user);
        userDao.saveAndFlush(user);
    }
    //============
    @Test
    public void testUserFindById() {
        Optional<User> result = userDao.findById(7l);
        User user = result.get();
        System.out.println("user:"+user);
    }


    @Test
    public void testRoleUpdatePermission() {
        List<Permission> list = permissionDao.findAll();
        Optional<Role> result = roleDao.findById(67l);
        Role roles = result.get();
        System.out.println("roles:" + roles);

        roles.setPermissions(new HashSet<>(list));

        roleDao.saveAndFlush(roles);
    }



    @Test
    public void testRoleDelete(){
        roleDao.deleteById(5l);
    }

}