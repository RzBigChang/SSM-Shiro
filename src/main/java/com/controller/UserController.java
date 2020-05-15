package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.entity.User;
import com.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	private RandomNumberGenerator randomNumberGenerator=new SecureRandomNumberGenerator();
//	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	//查看用户信息
	@RequestMapping("/findUser")
	public String findUser(ModelMap model) {
	List<User>	 list=userService.findUser();
		model.addAttribute("list", list);
		return "findUser";
	}
	//跳转页面
	@RequestMapping("ADD")
	public String ADD() {
		return "AddUser";
	}
	//添加用户
	@RequestMapping("Add")
	public void Add(User user,HttpServletResponse sp) throws IOException {
		//创建盐值
		String salt=randomNumberGenerator.nextBytes().toHex();
		//创建新密码
		String newPassword=new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(user.getUsername()+salt), 2).toHex();
		System.out.println(newPassword);
		System.out.println(salt);
		
		user.setPassword(newPassword);
		user.setSaltPassword(salt);
		int result=userService.addUser(user);
		if(result==1) {
			String json=JSON.toJSONString(result);
			PrintWriter out=sp.getWriter();
			out.print(json);
		}else {
			print(result, sp);
		}
	}
	//查询用户回显数据
	@RequiresRoles("1")
	@RequiresPermissions("1")
	@RequestMapping("RequerId")
	public String getUserById(Map<String,Object>map,int id) {
		try {
			User user=new User();
			user=userService.getUserById(id);
			map.put("user",user);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "账号密码错误";
		}catch (AuthorizationException e) {
			e.printStackTrace();
			return "redirect:MyJsp";
		}
		
		return "Update";
	}
	//修改用户信息
	@RequestMapping("Update")
	public void Update(User user,HttpServletResponse pse) throws IOException {
		int result=userService.updateUser(user);
		if(result==1) {
			String json=JSON.toJSONString(result);
			PrintWriter out=pse.getWriter();
			out.print(json);
		}else {
			print(result, pse);
		}
	}
	//删除用户
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public void Delete(int id,HttpServletResponse res) throws IOException {
		int result=userService.delUser(id);
		if(result==1) {
			String json=JSON.toJSONString(result);
			PrintWriter out=res.getWriter();
			out.print(json);
		}else {
			print(result,res);
		}
	}
	//跳转登录方法
	@RequestMapping("logins")
	public String logins() {
		return "login";
	}
	//登录方法
	
	@RequestMapping("login")
	public String login(User user,Map<String,Object>map,HttpServletRequest request,HttpServletResponse resp) {
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(
				user.getUsername(),
				user.getPassword()
				);
		try {
			subject.login(usernamePasswordToken);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "账号密码错误";
		}catch (AuthorizationException e) {
			return "没有权限";
		}
		return "redirect:/findUser";
		
	}
	//跳转到没有权限页面
	@RequestMapping("MyJsp")
	public String MyJsp() {
		return "MyJsp";
	}
	
	public void print(Object msg,HttpServletResponse rep) throws IOException {
		String info=JSON.toJSONString(msg);
		PrintWriter out=rep.getWriter();
		out.print(info);
	}
}
