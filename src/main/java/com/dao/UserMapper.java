package com.dao;

import java.util.List;

import com.entity.User;

public interface UserMapper {
	public List<User> findUser();//查看用户列表
	public User getUserById(int id);//根据id查看用户详细信息
	public int addUser(User user);//添加用户信息
	public int updateUser(User user);//修改用户信息
	public int delUser(int id);//删除用户信息
	public User login(String username);//登录方法
}
