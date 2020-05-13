package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.entity.User;
import com.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	private RandomNumberGenerator randomNumberGenerator=new SecureRandomNumberGenerator();
//	@SuppressWarnings("rawtypes")
//	@Autowired
//	private RedisTemplate redisTemplate;
	//查看用户信息
	@RequestMapping("/findUser")
	public String findUser(ModelMap model) {
//		List<User> list=redisTemplate.opsForList().range("user1", 0, -1);
//		if(list.size()>0) {
//			list=redisTemplate.opsForList().range("user1", 0, -1);
//		}else {
	List<User>	 list=userService.findUser();
		//}
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
	public void print(Object msg,HttpServletResponse rep) throws IOException {
		String info=JSON.toJSONString(msg);
		PrintWriter out=rep.getWriter();
		out.print(info);
	}
}
