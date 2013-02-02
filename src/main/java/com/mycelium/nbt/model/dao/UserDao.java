package com.mycelium.nbt.model.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mycelium.nbt.model.entities.UserEntity;

public class UserDao {
	private static final String COLLECTION_USERS = "Users";
	@Autowired
	private MongoOperations _mongoTempalte;

	@PostConstruct
	void init() {
		if (_mongoTempalte.collectionExists(COLLECTION_USERS)) {
			_mongoTempalte.dropCollection(COLLECTION_USERS);
		}
		_mongoTempalte.createCollection(COLLECTION_USERS);
		UserEntity user = new UserEntity("lukan", "lukan@mycelium.com",
				"Anton", "Lukashin", "pass01");
		addUser(user);
		user = new UserEntity("gambit", "pkozlov@mycelium.com",
				"Pavel", "Kozlov", "pass02");
		addUser(user);
		user = new UserEntity("msalamatov", "msalamatov@mycelium.com",
				"Michail", "Salamatov", "pass03");
		addUser(user);
	}

	public void addUser(UserEntity user) {
		_mongoTempalte.save(user, COLLECTION_USERS);
	}

	public UserEntity findOne(String id) {
		return _mongoTempalte.findOne(new Query(Criteria.where("id").is(id)),
				UserEntity.class, COLLECTION_USERS);
	}

	public List<UserEntity> findAll() {
		return _mongoTempalte.findAll(UserEntity.class, COLLECTION_USERS);
	}

}