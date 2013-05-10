package com.mycelium.nbt.model.dao;import java.util.List;import javax.annotation.PostConstruct;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.mongodb.core.MongoOperations;import org.springframework.data.mongodb.core.query.Criteria;import org.springframework.data.mongodb.core.query.Query;import org.springframework.data.mongodb.core.query.Update;import org.springframework.stereotype.Repository;import com.mycelium.nbt.model.entities.TaskEntity;@Repositorypublic class TaskDao implements CollectionNames {	@Autowired	private MongoOperations _mongoTemplate;	@PostConstruct	void init() {		if (_mongoTemplate.collectionExists(COLLECTION_TASKS)) {			_mongoTemplate.dropCollection(COLLECTION_TASKS);		}		_mongoTemplate.createCollection(COLLECTION_TASKS);	}	public void addTask(TaskEntity task) {		_mongoTemplate.save(task, COLLECTION_TASKS);	}	public List<TaskEntity> findAll() {		return _mongoTemplate.findAll(TaskEntity.class, COLLECTION_TASKS);	}	public TaskEntity findOne(String taskId) {		return _mongoTemplate.findOne(new Query(Criteria.where("_id")				.is(taskId)), TaskEntity.class, COLLECTION_TASKS);	}	public void addCRToTask(String taskId, String crId) {		Update param = new Update();		_mongoTemplate.findAndModify(				new Query(Criteria.where("_id").is(taskId)),				param.push("_attachedCRs", crId), TaskEntity.class,				COLLECTION_TASKS);	}	public void deleteCRFromTask(String taskId, String crId) {		Update param = new Update();		_mongoTemplate.findAndModify(				new Query(Criteria.where("_id").is(taskId)),				param.pull("_attachedCRs", crId), TaskEntity.class,				COLLECTION_TASKS);	}	public void addModuleToTask(String taskId, String moduleId) {		Update param = new Update();		_mongoTemplate.findAndModify(				new Query(Criteria.where("_id").is(taskId)),				param.push("_attachedModules", moduleId), TaskEntity.class,				COLLECTION_TASKS);	}	public void deleteModuleFromTask(String taskId, String moduleId) {		Update param = new Update();		_mongoTemplate.findAndModify(				new Query(Criteria.where("_id").is(taskId)),				param.pull("_attachedModules", moduleId), TaskEntity.class,				COLLECTION_TASKS);	}}