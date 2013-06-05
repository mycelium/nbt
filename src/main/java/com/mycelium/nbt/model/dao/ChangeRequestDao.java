package com.mycelium.nbt.model.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mycelium.nbt.model.entities.ChangeRequestEntity;

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
		/*List<ChangeRequestEntity> initialCRs = new LinkedList<ChangeRequestEntity>();
		List<UserEntity> users = _userDao.findAll();
		for (int i = 0; i < 5; i++) {
			initialCRs.add(new ChangeRequestEntity("caption_" + i, users.get(
					i % users.size()).getId(), new LinkedList<String>(),
					new LinkedList<String>(), ""));
		}*
		_mongoTemplate.insert(initialCRs, COLLECTION_CRS);*/
	}

	public void addCR(ChangeRequestEntity cr) {
		_mongoTemplate.save(cr, COLLECTION_CRS);
	}

	public ChangeRequestEntity findOne(String id) {
		return _mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),
				ChangeRequestEntity.class, COLLECTION_CRS);
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
	
	
	public List<ChangeRequestEntity> findByIssueIdList(String issueIdList) {
		return _mongoTemplate.find(
				new Query(Criteria.where("_issueIdList").is(issueIdList)),
				ChangeRequestEntity.class, COLLECTION_CRS);
	}

	public void addIssueToCR(String crId,String issueId)
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(crId)),
				param.push("_issueIdList",issueId),ChangeRequestEntity.class,COLLECTION_CRS);
	}

	public void addTaskToCR(String crId,String taskId)
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(crId)),
				param.push("_taskIdList",taskId),ChangeRequestEntity.class,COLLECTION_CRS);
	}

	public void deleteIssueFromCR(String crId,String[] issueId) 
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(crId)),
				param.pullAll("_issueIdList",issueId),ChangeRequestEntity.class,COLLECTION_CRS);
	}

	public void deleteTaskFromCR(String CRId,String[] taskId) 
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(CRId)),
				param.pullAll("_taskIdList",taskId),ChangeRequestEntity.class,COLLECTION_CRS);
	}

	public void updateFile(String idOfCR,String fileName)
	{
		Update param=new Update();
		_mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(idOfCR)),
			param.push("_pathToFile",fileName),ChangeRequestEntity.class,COLLECTION_CRS);
	}

}
