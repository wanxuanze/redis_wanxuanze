package com.bawei.test;

import java.text.SimpleDateFormat;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawei.cms.bean.User;
import com.bawei.utils.StringUtils;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class UserTest {

	@Resource
	private RedisTemplate redisTemplate;
	
	//随机性别
	public static String getSex() {
		return new Random().nextBoolean()?"男":"女";
	}
	//随机电话号码
	public static String getPhone() {
		
		String phone="";
		for(int i=0;i<9;i++) {
			phone += new Random().nextInt(10);
		}
		return "13"+phone;
	}
	
	@Test
	public void userTest() {
		
		long beginTime = System.currentTimeMillis();
		
		//string 类型
		ValueOperations ops = redisTemplate.opsForValue();
		
		for(int i=1;i<=100000;i++) {
			User user = new User();
			//序号
			user.setId(i);
			//名字
			user.setName(StringUtils.getRandomChar(3));
			//性别
			user.setSex(getSex());
			//手机
			user.setPhone(getPhone());
			//邮箱
			user.setEmail(StringUtils.getEmail());
			//生日
			user.setBirthday( StringUtils.randomDate("1949-01-01 00:00:00", "1999-01-01 00:00:00"));
			
			//存入redis中 以字符串string方式
			ops.set(i+"", user);
			
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("序列化方式:json  耗时"+(endTime-beginTime));
	}
	
	
	@Test
	public void testUserByHash() {
		
		long beginTime = System.currentTimeMillis();
		
		//hash类型
		BoundHashOperations boundHashOps = redisTemplate.boundHashOps("hash_user");

		for(int i=1;i<=100000;i++) {
			User user = new User();
			//序号
			user.setId(i);
			//名字
			user.setName(StringUtils.getRandomChar(3));
			//性别
			user.setSex(getSex());
			//手机
			user.setPhone(getPhone());
			//邮箱
			user.setEmail(StringUtils.getEmail());
			//生日
			user.setBirthday( StringUtils.randomDate("1949-01-01 00:00:00", "1999-01-01 00:00:00"));
			
			//存入redis中 以hash方式
			boundHashOps.put(i+"", user.toString());
			
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("序列化方式:hash  耗时"+(endTime-beginTime));
		
	}
}
