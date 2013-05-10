package com.mycelium.nbt.model.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.ModuleEntity;



@Repository
public class ModuleDao implements CollectionNames {
	@Autowired
	private MongoOperations _mongoTemplate;

	@PostConstruct
	void init() {
		if (_mongoTemplate.collectionExists(COLLECTION_MODULE)) {
			_mongoTemplate.dropCollection(COLLECTION_MODULE);
		}
		_mongoTemplate.createCollection(COLLECTION_MODULE);
	}

	public void addModule(ModuleEntity module) {
		_mongoTemplate.save(module, COLLECTION_MODULE);
	}

	public List<ModuleEntity> findAll() {
		return _mongoTemplate.findAll(ModuleEntity.class, COLLECTION_MODULE);
	}
	public ModuleEntity findOne(String moduleId) {
		return _mongoTemplate.findOne(new Query(Criteria.where("_id").is(moduleId)),
				ModuleEntity.class, COLLECTION_MODULE);
	}
	public void addTaskToModule(String moduleId, String taskId)
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(moduleId)),
				param.push("_attachedTasks",taskId),ModuleEntity.class,COLLECTION_MODULE);
	}

	public void deleteModuleFromTask(String taskId,String[] moduleId) 
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(taskId)),
				param.pull("_attachedTasks",taskId),ModuleEntity.class,COLLECTION_MODULE);
	}
}

