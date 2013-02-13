package com.mycelium.nbt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mycelium.nbt.model.entities.PriorityEntity;

public class PriorityDao {
	private static final String COLLECTION_PRIORITIES = "Priorities";
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addPriority(PriorityEntity priority) {
		_mongoTemplate.save(priority, COLLECTION_PRIORITIES);
	}

}
