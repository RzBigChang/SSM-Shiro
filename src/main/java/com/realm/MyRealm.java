package com.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.User;
import com.service.UserService;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserService service;
	//权限方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	//登录方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if(token.getPrincipal()==null) {
			return null;
		}
		String userName=token.getPrincipal().toString();
		User user=service.login(userName);
		if(user==null) {
			return null;
		}else {
			SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(
					userName, user.getPassword(), ByteSource.Util.bytes(userName+user.getSaltPassword()), getName());
			return simpleAuthenticationInfo;
		}
		
	}

}
