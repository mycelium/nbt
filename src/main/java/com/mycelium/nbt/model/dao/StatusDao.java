package com.mycelium.nbt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.StatusEntity;

@Repository
public class StatusDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addStatus(StatusEntity status) {
		_mongoTemplate.save(status, COLLECTION_STATUSES);
	}

}
