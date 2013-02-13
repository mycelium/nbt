package com.mycelium.nbt.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mycelium.nbt.model.entities.IssueEntity;

public class IssueDao {
	private static final String COLLECTION_ISSUES = "Issues";
	@Autowired
	private MongoOperations _mongoTemplate;

	public void addIssue(IssueEntity issue) {
		_mongoTemplate.save(issue, COLLECTION_ISSUES);
	}

	public IssueDao findOne(String id) {
		return _mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				IssueDao.class, COLLECTION_ISSUES);
	}

	public List<IssueDao> findAll() {
		return _mongoTemplate.findAll(IssueDao.class, COLLECTION_ISSUES);
	}
	
	public List<IssueDao> findByPriority(String priorityId) {
		return _mongoTemplate.find(new Query(Criteria.where("priorityIssue").is(priorityId)),
				IssueDao.class, COLLECTION_ISSUES);
		//TODO change query for searching by priority caption
	}
	
	public List<IssueDao> findByReporter(String reporterId) {
		return _mongoTemplate.find(new Query(Criteria.where("reporter").is(reporterId)),
				IssueDao.class, COLLECTION_ISSUES);
		//TODO change query for searching by reporter email or login
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
