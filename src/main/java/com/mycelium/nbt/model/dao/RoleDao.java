package com.mycelium.nbt.model.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mycelium.nbt.model.entities.RoleEntity;

public class RoleDao {
	private static final String COLLECTION_ROLES = "Roles";
	@Autowired
	private MongoOperations _mongoTemplate;

	@PostConstruct
	void init() {
		if (_mongoTemplate.collectionExists(COLLECTION_ROLES)) {
			_mongoTemplate.dropCollection(COLLECTION_ROLES);
		}
		_mongoTemplate.createCollection(COLLECTION_ROLES);
		RoleEntity role = new RoleEntity("admin");
		addRole(role);
		role = new RoleEntity("user");
		addRole(role);
	}

	public void addRole(RoleEntity role) {
		_mongoTemplate.save(role, COLLECTION_ROLES);
	}

}
