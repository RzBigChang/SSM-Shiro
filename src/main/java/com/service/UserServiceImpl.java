package com.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.entity.User;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
//	@SuppressWarnings("rawtypes")
//	@Autowired
//	private RedisTemplate redisTemplate;
	@Override
	public List<User> findUser() {
		List<User> list=userMapper.findUser();
//		if(list!=null) {
//			//加入缓存
//			while (redisTemplate.opsForList().size("user1")>0) {
//					redisTemplate.opsForList().leftPop("user1");
//					}
//			redisTemplate.opsForList().rightPushAll("user1", list);
//			redisTemplate.expire("user1", 1, TimeUnit.DAYS);
//		}
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
		return result;
	}

	@Override
	public int updateUser(User user) {
		int result=userMapper.updateUser(user);
		return result;
	}

	@Override
	public int delUser(int id) {
		int result=userMapper.delUser(id);
		return result;
	}

}
