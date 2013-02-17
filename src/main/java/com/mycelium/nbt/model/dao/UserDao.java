package com.mycelium.nbt.model.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.UserEntity;

@Repository
public class UserDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	@PostConstruct
	void init() {
		if (_mongoTemplate.collectionExists(COLLECTION_USERS)) {
			_mongoTemplate.dropCollection(COLLECTION_USERS);
		}
		_mongoTemplate.createCollection(COLLECTION_USERS);
		UserEntity user = new UserEntity("lukan", "admin",
				"lukan@mycelium.com", "Anton", "Lukashin", "pass01");
		addUser(user);
		user = new UserEntity("gambit", "admin", "pkozlov@mycelium.com",
				"Pavel", "Kozlov", "pass02");
		addUser(user);
		user = new UserEntity("msalamatov", "admin", "msalamatov@mycelium.com",
				"Michail", "Salamatov", "pass03");
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

	public UserEntity delete(String id) {
		return _mongoTemplate.findAndRemove(
				new Query(Criteria.where("_id").is(id)), UserEntity.class,
				COLLECTION_USERS);
	}

}
