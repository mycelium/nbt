package com.mycelium.nbt.model.dao;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.ChangeRequestEntity;
import com.mycelium.nbt.model.entities.UserEntity;

@Repository
public class ChangeRequestDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	@Autowired
	private UserDao _userDao;

	@PostConstruct
	void init() {
		if (_mongoTemplate.collectionExists(COLLECTION_CRS)) {
			_mongoTemplate.dropCollection(COLLECTION_CRS);
		}
		_mongoTemplate.createCollection(COLLECTION_CRS);
		List<ChangeRequestEntity> initialCRs = new LinkedList<ChangeRequestEntity>();
		List<UserEntity> users = _userDao.findAll();
		for (int i = 0; i < 5; i++) {
			initialCRs.add(new ChangeRequestEntity("caption_" + i, users.get(
					i % users.size()).getId(), new LinkedList<String>(),
					new LinkedList<String>(), ""));
		}
		_mongoTemplate.insert(initialCRs, COLLECTION_CRS);
	}

	public void addChangeRequest(ChangeRequestEntity cr) {
		_mongoTemplate.save(cr, COLLECTION_CRS);
	}

	public IssueDao findOne(String id) {
		return _mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),
				IssueDao.class, COLLECTION_CRS);
	}

	public List<ChangeRequestEntity> findAll() {
		return _mongoTemplate
				.findAll(ChangeRequestEntity.class, COLLECTION_CRS);
	}

	public List<ChangeRequestEntity> findByParentId(String parentId) {
		return _mongoTemplate.find(
				new Query(Criteria.where("_parentId").is(parentId)),
				ChangeRequestEntity.class, COLLECTION_CRS);
	}

}
