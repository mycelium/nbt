package com.mycelium.nbt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.PriorityEntity;

@Repository
public class PriorityDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addPriority(PriorityEntity priority) {
		_mongoTemplate.save(priority, COLLECTION_PRIORITIES);
	}

}
