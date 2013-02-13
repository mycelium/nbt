package com.mycelium.nbt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mycelium.nbt.model.entities.TypeEntity;

public class TypeDao {
	private static final String COLLECTION_TYPES = "Types";
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addType(TypeEntity type) {
		_mongoTemplate.save(type, COLLECTION_TYPES);
	}

}
