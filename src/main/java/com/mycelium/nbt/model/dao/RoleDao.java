package com.mycelium.nbt.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.RoleEntity;
import com.mycelium.nbt.model.enums.RoleType;

@Repository
public class RoleDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	@PostConstruct
	void init() {
		if (_mongoTemplate.collectionExists(COLLECTION_ROLES)) {
			_mongoTemplate.dropCollection(COLLECTION_ROLES);
		}
		_mongoTemplate.createCollection(COLLECTION_ROLES);

		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		for (RoleType roleType : RoleType.values()) {
			roles.add(new RoleEntity(roleType.name()));
		}
		_mongoTemplate.insert(roles, COLLECTION_ROLES);
		for (RoleEntity roleEntity : findAll()) {
			RoleType.valueOf(roleEntity.getCaption()).setCaption(roleEntity.getCaption())
					.setId(roleEntity.getId());
		}
	}

	public List<RoleEntity> findAll() {
		return _mongoTemplate.findAll(RoleEntity.class, COLLECTION_ROLES);
	}

	public RoleEntity findByName(String caption) {
		return _mongoTemplate.findOne(
				new Query(Criteria.where("_caption").is(caption)),
				RoleEntity.class, COLLECTION_ROLES);
	}
}