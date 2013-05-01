package com.mycelium.nbt.model.dao;

import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycelium.nbt.model.entities.UserEntity;
import com.mycelium.nbt.model.enums.RoleType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class TestUserDao {
	@Autowired
	private UserDao _userDao;

	@Test
	public void testFindAll() {
		System.out.println();
		List<UserEntity> allUsers = _userDao.findAll();
		System.out.println("Amount of users in DB = " + allUsers.size());
		for (UserEntity user : allUsers) {
			System.out.println(user);
		}
		assertTrue(allUsers.size() > 1);
	}
	
	@Test
	public void testFindOne() {
		System.out.println();
		List<UserEntity> allUsers = _userDao.findAll();
		assertTrue(allUsers.size() > 1);
		UserEntity user = _userDao.findOne(allUsers.get(0).getId());
		System.out.println("User info: " + user);
		assertTrue(user != null);
	}
	
	@Test
	public void testFindByLogin() {
		System.out.println();
		List<UserEntity> allUsers = _userDao.findAll();
		assert allUsers.size() > 1;
		UserEntity user = _userDao.findByLogin(allUsers.get(0).getLogin());
		System.out.println("User info: " + user);
		assertTrue(user != null);
	}
	
	@Test
	public void testAddUser() {
		System.out.println();
		UserEntity user = new UserEntity("test", RoleType.ROLE_USER.getId(),
				"test@test.com", "Test", "Unit", "pass01");
		_userDao.addUser(user);
		UserEntity findUser = _userDao.findOne(user.getId());
		System.out.println("Found user info: " + findUser);
		assertTrue(findUser != null);
	}
	
	@Test
	public void testDeleteUser() {
		System.out.println();
		UserEntity user = new UserEntity("testDelete", RoleType.ROLE_USER.getId(),
				"test@test.com", "Test", "Unit", "pass01");
		_userDao.addUser(user);
		UserEntity deleteUser = _userDao.deleteUser(user.getId());
		System.out.println("User info before delete: " + deleteUser);
		UserEntity findUser = _userDao.findOne(user.getId());
		System.out.println("User info after delete: " + findUser);
		assertTrue(deleteUser != null);
		assertTrue(findUser == null);
	}

}
