package com.mycelium.nbt.model.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.IssueEntity;

@Repository
public class IssueDao implements CollectionNames {

	Logger _logger = Logger.getLogger(IssueDao.class);
	@Autowired
	private MongoOperations _mongoTemplate;

	@PostConstruct
	void init() {
		if (_mongoTemplate.collectionExists(COLLECTION_ISSUES)) {
			_mongoTemplate.dropCollection(COLLECTION_ISSUES);
		}
		_mongoTemplate.createCollection(COLLECTION_ISSUES);
	}

	public void addIssue(IssueEntity issue) {
		_mongoTemplate.save(issue, COLLECTION_ISSUES);
	}

	public IssueEntity findOne(String id) {
		return _mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),
				IssueEntity.class, COLLECTION_ISSUES);
	}


	public List<IssueEntity> findAll() {
		return _mongoTemplate.findAll(IssueEntity.class, COLLECTION_ISSUES);
	}

	public void updateMarker(String issueId,String color)
	{
		_logger.warn(findOne(issueId));
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(issueId)),
				Update.update("_marker", color),IssueEntity.class,COLLECTION_ISSUES);
	}

	public void addCRToIssue(String issueId,String crId)
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(issueId)),
				param.push("_attachedCRs",crId),IssueEntity.class,COLLECTION_ISSUES);
	}


	public void deleteCRFromIssue(String issueId,String crId) 
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(issueId)),
				param.pull("_attachedCRs",crId),IssueEntity.class,COLLECTION_ISSUES);
	}

	public List<IssueEntity> findByPriority(String priorityId) {
		return _mongoTemplate.find(new Query(Criteria.where("priorityIssue")
				.is(priorityId)), IssueEntity.class, COLLECTION_ISSUES);
		// TODO change query for searching by priority caption
	}

	public List<IssueEntity> findByReporter(String reporterId) {
		return _mongoTemplate.find(
				new Query(Criteria.where("reporter").is(reporterId)),
				IssueEntity.class, COLLECTION_ISSUES);
		// TODO change query for searching by reporter email or login
	}

	public List<IssueEntity> findByAttachedCRs(List<String> attachedCRs) {
		return _mongoTemplate.find(
				new Query(Criteria.where("attachedCRs").is(attachedCRs)),
				IssueEntity.class, COLLECTION_ISSUES);
	}

	// Edit combination by all parameters
	public List<IssueEntity> findByCombination() {
		// TODO
		return null;
	}

}