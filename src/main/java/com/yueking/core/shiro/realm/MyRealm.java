package com.yueking.core.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("===========doGetAuthorizationInfo===========");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=========doGetAuthenticationInfo========"+token);
//        获取登录信息
        String username = (String) token.getPrincipal();
        String password = new String ((char[])token.getCredentials());
        String passwordMD5="202cb962ac59075b964b07152d234b70";

        if (!username.equals("yueking")) {
            System.out.println("======unknownAccount:"+username);
            throw new UnknownAccountException();
        }
//        if (!password.equals("123")) {
//            System.out.println("======password error!");
//            throw new IncorrectCredentialsException();
//        }else {
//            System.out.println("======password oky");
//        }
        return new SimpleAuthenticationInfo(username,passwordMD5,getName());
    }
}
