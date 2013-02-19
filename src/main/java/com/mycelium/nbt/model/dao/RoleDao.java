package com.mycelium.nbt.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.RoleEntity;
import com.mycelium.nbt.model.enums.RoleEnum;

@Repository
public class RoleDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	private Map<String, RoleEntity> roleAsMap = new HashMap<String, RoleEntity>();

	@PostConstruct
	void init() {
		if (!_mongoTemplate.collectionExists(COLLECTION_ROLES)) {
			_mongoTemplate.createCollection(COLLECTION_ROLES);
			RoleEntity role;
			for (RoleEnum _roleEnum : RoleEnum.values()) {
				role = new RoleEntity(_roleEnum.toString());
				addRole(role);
			}
		}
	}

	private void addRole(RoleEntity role) {
		roleAsMap.put(role.get_caption(), role);
		_mongoTemplate.save(role, COLLECTION_ROLES);
	}

	public List<RoleEntity> findAll() {
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		for (RoleEnum _roleEnum : RoleEnum.values()) {
			roles.add(roleAsMap.get(_roleEnum.toString()));
		}
		return roles;
	}
	
	public RoleEntity findByName(String caption){
		return roleAsMap.get(caption);
	}
}
