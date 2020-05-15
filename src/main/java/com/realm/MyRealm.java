package com.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.User;
import com.entity.UserAndAuthority;
import com.entity.UserAndRole;
import com.service.UserService;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserService service;
	//权限方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户名
		String name=principals.getPrimaryPrincipal().toString();
		//根据用户名查询
		User user=service.login(name);
		//根据当前登录的用户id查询角色
		List<UserAndRole>roleList=service.RoleQuery(user.getId());
		//根据当前登录的用户查询权限
		List<UserAndAuthority>authorityList=service.AuthorityQuery(user.getId());
		//添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		for (UserAndRole userandrole :roleList) {
			simpleAuthorizationInfo.addRole(String.valueOf(userandrole.getRoleId()));
		}
		for (UserAndAuthority userAndAuthority : authorityList) {
			simpleAuthorizationInfo.addStringPermission(String.valueOf(userAndAuthority.getAuthorityId()));
		}
		return simpleAuthorizationInfo;
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
