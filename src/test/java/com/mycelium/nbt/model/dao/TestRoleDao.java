package com.mycelium.nbt.model.dao;

import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycelium.nbt.model.entities.RoleEntity;
import com.mycelium.nbt.model.enums.RoleType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class TestRoleDao {
	@Autowired
	private RoleDao _roleDao;
	
	@Test
	public void testFindAll() {
		System.out.println();
		List<RoleEntity> roles = _roleDao.findAll();
		System.out.println("Amount of roles in DB = " + roles.size());
		boolean isDbAndEnumEquals = true;
		for (RoleEntity role : roles) {
			System.out.println(role);
			if (RoleType.getById(role.getId()) == null) {
				System.err.println("Role " + role.getCaption() + " not found!");
				isDbAndEnumEquals = false;
			}
		}
		assertTrue(isDbAndEnumEquals);
	}
	
	@Test
	public void testFindByName() {
		System.out.println();
		RoleEntity role = _roleDao.findByName(RoleType.Admin.getCaption());
		System.out.println("Role info: " + role);
		if(role == null ){
			System.err.println("Role " + RoleType.Admin.getCaption() + " not found at type class!");
		}
		assertTrue(role != null);
	}

}
