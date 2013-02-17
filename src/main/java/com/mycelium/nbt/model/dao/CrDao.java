package com.mycelium.nbt.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.CrEntity;
@Repository
public class CrDao  implements CollectionNames{
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addCRs(CrEntity cr) {
		_mongoTemplate.save(cr, COLLECTION_CRS);
	}

	public IssueDao findOne(String id) {
		return _mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				IssueDao.class, COLLECTION_CRS);
	}

	public List<IssueDao> findAll() {
		return _mongoTemplate.findAll(IssueDao.class, COLLECTION_CRS);
	}
	
	public List<IssueDao> findByParentId(String parentId) {
		return _mongoTemplate.find(new Query(Criteria.where("parentId").is(parentId)),
				IssueDao.class, COLLECTION_CRS);
	}

	
}
