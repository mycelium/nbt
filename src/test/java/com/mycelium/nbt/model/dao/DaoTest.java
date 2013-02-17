package com.mycelium.nbt.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycelium.nbt.model.entities.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class DaoTest {
//	@Resource(name = "userDao")
	@Autowired
	private UserDao _userDao;

	@Test
	public void testUserDao() {
		List<UserEntity> allUsers = _userDao.findAll();
		System.out.println("users in DB = " + allUsers.size());
		for (UserEntity user : allUsers) {
			System.out.println(user);
		}
		assert allUsers.size()>1;
	}
}
