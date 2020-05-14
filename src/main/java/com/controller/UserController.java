package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
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
	@RequestMapping("RequerId")
	public String getUserById(Map<String,Object>map,int id) {
		User user=new User();
		user=userService.getUserById(id);
		map.put("user",user);
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
	public void print(Object msg,HttpServletResponse rep) throws IOException {
		String info=JSON.toJSONString(msg);
		PrintWriter out=rep.getWriter();
		out.print(info);
	}
}
