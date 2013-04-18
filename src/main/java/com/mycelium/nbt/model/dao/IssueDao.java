package com.mycelium.nbt.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import org.springframework.data.mongodb.core.query.Update;
import org.apache.log4j.Logger;
import com.mycelium.nbt.model.entities.IssueEntity;
import org.springframework.data.mongodb.core.FindAndModifyOptions;

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
	
	public void updateMarker(String id,String color)
	{
	_logger.warn(findOne(id));
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(id)),
		Update.update("_marker", color),IssueEntity.class,COLLECTION_ISSUES);
	}
	
	public void addCR(String id,String idOfCR)
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(id)),
			param.push("_attachedCRs",idOfCR),IssueEntity.class,COLLECTION_ISSUES);
	}
	
		
	public void delCR(String idOfIssue,String idOfCR) 
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(idOfIssue)),
			param.pull("_attachedCRs",idOfCR),IssueEntity.class,COLLECTION_ISSUES);
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

	public List<IssueEntity> findByAssignee(String assignee) {
		// TODO
		return null;
	}

	// Edit combination by all parameters
	public List<IssueEntity> findByCombination() {
		// TODO
		return null;
	}

}
