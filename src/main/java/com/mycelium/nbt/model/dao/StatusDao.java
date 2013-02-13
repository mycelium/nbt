package com.mycelium.nbt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mycelium.nbt.model.entities.StatusEntity;

public class StatusDao {
	private static final String COLLECTION_STATUSES = "Statuses";
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addStatus(StatusEntity status) {
		_mongoTemplate.save(status, COLLECTION_STATUSES);
	}

}
