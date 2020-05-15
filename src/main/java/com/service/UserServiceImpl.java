package com.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.entity.User;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
//	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public List<User> findUser() {
		List<User> list=userMapper.findUser();
		if(list!=null) {
			//加入缓存List
			redisTemplate.opsForList().rightPushAll("List",list);
		}
		return list;
	}

	@Override
	public User getUserById(int id) {
		User user=userMapper.getUserById(id);
		return user;
	}

	@Override
	public int addUser(User user) {
		int result=userMapper.addUser(user);
		if(result==1) {
			redisTemplate.delete("list");
		}
		return result;
	}

	@Override
	public int updateUser(User user) {
		int result=userMapper.updateUser(user);
		if(result==1) {
			redisTemplate.delete("list");
		}
		return result;
	}

	@Override
	public int delUser(int id) {
		int result=userMapper.delUser(id);
		if(result==1) {
			redisTemplate.delete("list");
		}
		return result;
	}
	//登录方法录
	@Override
	public User login(String username) {
		User user=userMapper.login(username);
		return user;
	}

}
