package com.mycelium.nbt.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class IssueDao {
	private static final String COLLECTION_ISSUES = "Issues";
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addIssue(IssueDao issue) {
		_mongoTemplate.save(issue, COLLECTION_ISSUES);
	}

	public IssueDao findOne(String id) {
		return _mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				IssueDao.class, COLLECTION_ISSUES);
	}

	public List<IssueDao> findAll() {
		return _mongoTemplate.findAll(IssueDao.class, COLLECTION_ISSUES);
	}
	
	public List<IssueDao> findByPriority(String priority) {
		return _mongoTemplate.find(new Query(Criteria.where("priorityIssue").is(priority)),
				IssueDao.class, COLLECTION_ISSUES);
	}
	
	public List<IssueDao> findByReporter(String reporter) {
		return _mongoTemplate.find(new Query(Criteria.where("reporter").is(reporter)),
				IssueDao.class, COLLECTION_ISSUES);
	}
	
	public List<IssueDao> findByAssignee(String assignee) {
		//TODO
		return null;
	}
	
	// Edit combination by all parameters
	public List<IssueDao> findByCombination() {
		//TODO
		return null;
	}
	

}
