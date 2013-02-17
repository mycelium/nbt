package com.mycelium.nbt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.TypeEntity;

@Repository
public class TypeDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addType(TypeEntity type) {
		_mongoTemplate.save(type, COLLECTION_TYPES);
	}

}
