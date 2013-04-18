package com.mycelium.nbt.model.dao;import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.mongodb.core.MongoOperations;import org.springframework.data.mongodb.core.query.Criteria;import org.springframework.data.mongodb.core.query.Query;import org.springframework.stereotype.Repository;import javax.annotation.PostConstruct;import org.springframework.data.mongodb.core.query.Update;import org.apache.log4j.Logger;import org.springframework.data.mongodb.core.FindAndModifyOptions;import com.mycelium.nbt.model.entities.TaskEntity;@Repositorypublic class TaskDao implements CollectionNames {	@Autowired	private MongoOperations _mongoTemplate;@PostConstruct	void init() {		if (_mongoTemplate.collectionExists(COLLECTION_TASKS)) {			_mongoTemplate.dropCollection(COLLECTION_TASKS);		}		_mongoTemplate.createCollection(COLLECTION_TASKS);		}			public void add(TaskEntity task) {		_mongoTemplate.save(task, COLLECTION_TASKS);	}	public List<TaskEntity> findAll() {		return _mongoTemplate.findAll(TaskEntity.class, COLLECTION_TASKS);	}	public TaskEntity findOne(String id) {		return _mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),				TaskEntity.class, COLLECTION_TASKS);	}	public void updateCRList(String taskId,String crId)	{			Update param=new Update();		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(taskId)),			param.push("_attachedCRs",crId),TaskEntity.class,COLLECTION_TASKS);	}		public void delCR(String idOfTask,String idOfCR) 	{		Update param=new Update();		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(idOfTask)),			param.pull("_attachedCRs",idOfCR),TaskEntity.class,COLLECTION_TASKS);	}}		