package com.mycelium.nbt.model.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.UserEntity;
import com.mycelium.nbt.model.enums.RoleType;

@Repository
public class UserDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	@Autowired
	private RoleDao _roleDao;

	@PostConstruct
	void init() {
		if (_mongoTemplate.collectionExists(COLLECTION_USERS)) {
			_mongoTemplate.dropCollection(COLLECTION_USERS);
		}
		_mongoTemplate.createCollection(COLLECTION_USERS);
		UserEntity user = new UserEntity("lukan", RoleType.Admin.getId(),
				"lukan@mycelium.com", "Anton", "Lukashin", "pass01");
		addUser(user);
		user = new UserEntity("gambit", RoleType.Developer.getId(),
				"pkozlov@mycelium.com", "Pavel", "Kozlov", "pass02");
		addUser(user);
		user = new UserEntity("msalamatov", RoleType.Manager.getId(),
				"msalamatov@mycelium.com", "Michail", "Salamatov", "pass03");
		addUser(user);
		user = new UserEntity("yura", RoleType.Manager.getId(),
				"yura@mycelium.com", "yura", "yuriev", "1234");
		addUser(user);
		user = new UserEntity("admin", RoleType.Admin.getId(),
				"admin@mycelium.com", "admin", "adminiev", "1234");
		addUser(user);
	}

	public void addUser(UserEntity user) {
		_mongoTemplate.save(user, COLLECTION_USERS);
	}

	public UserEntity findOne(String id) {
		return _mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),
				UserEntity.class, COLLECTION_USERS);
	}

	public UserEntity findByLogin(String login) {
		return _mongoTemplate.findOne(
				new Query(Criteria.where("_login").is(login)),
				UserEntity.class, COLLECTION_USERS);
	}

	public List<UserEntity> findAll() {
		return _mongoTemplate.findAll(UserEntity.class, COLLECTION_USERS);
	}

	public UserEntity deleteUser(String id) {
		return _mongoTemplate.findAndRemove(
				new Query(Criteria.where("_id").is(id)), UserEntity.class,
				COLLECTION_USERS);
	}

}