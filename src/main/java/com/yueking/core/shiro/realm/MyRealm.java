package com.yueking.core.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    /**
     * 身份授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("===========doGetAuthorizationInfo===========");
//      todo 根据用户名 查找数据库信息进行用户授权
//todo userService findUserById...
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principals.getPrimaryPrincipal().toString().equals("admin")) {
            info.addRole("admin");
            info.addStringPermission("sys");
        }else {
            info.addRole("user");
            info.addStringPermission("pub");
        }


        return info;
    }

    /**
     * 身份认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=========doGetAuthenticationInfo========"+token);
//        获取登录信息
        String username = (String) token.getPrincipal();
        String password = new String ((char[])token.getCredentials());

//        todo 从数据库中 查找用户是否存在 并读取 数据库存的 passwordMD5
//        todo userService findUserById getUserPassword
        if (!username.equals("yueking") && !username.equals("admin")) {
            System.out.println("======unknownAccount:"+username);
            throw new UnknownAccountException();
        }

        String passwordMD5="202cb962ac59075b964b07152d234b70";
//        if (!password.equals("123")) {
//            System.out.println("======password error!");
//            throw new IncorrectCredentialsException();
//        }else {
//            System.out.println("======password oky");
//        }
        return new SimpleAuthenticationInfo(username,passwordMD5,getName());
    }
}
