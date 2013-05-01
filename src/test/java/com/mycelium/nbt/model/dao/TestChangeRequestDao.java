package com.mycelium.nbt.model.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycelium.nbt.model.entities.ChangeRequestEntity;
import com.mycelium.nbt.model.entities.IssueEntity;
import com.mycelium.nbt.model.entities.TaskEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class TestChangeRequestDao {
	@Autowired
	private ChangeRequestDao _crDao;
	@Autowired
	private TaskDao _taskDao;
	@Autowired
	private IssueDao _issueDao;

	@Test
	public void testFindAll() {
		System.out.println();
		List<ChangeRequestEntity> allCRs = _crDao.findAll();
		assertTrue(allCRs != null);
		System.out.println("Amount of CRs in DB = " + allCRs.size());
		for (ChangeRequestEntity task : allCRs) {
			System.out.println(task);
		}
		assertTrue(allCRs.size() >= 0);
	}

	@Test
	public void testAddTask() {
		System.out.println();
		List<ChangeRequestEntity> allCRs = _crDao.findAll();
		int amountOfCRs = allCRs.size();
		System.out.println("Amount of CRs in DB before adding CR = "
				+ amountOfCRs);
		_crDao.addCR(new ChangeRequestEntity());
		allCRs = _crDao.findAll();
		System.out.println("Amount of CRs in DB after adding CR = "
				+ allCRs.size());
		assertTrue(amountOfCRs + 1 == allCRs.size());
	}

	@Test
	public void testFindOne() {
		System.out.println();
		_crDao.addCR(new ChangeRequestEntity());
		List<ChangeRequestEntity> allCRs = _crDao.findAll();
		System.out.println("Amount of CRs in DB = " + allCRs.size());
		assertTrue(allCRs.size() > 0);
		String crId = allCRs.get(allCRs.size() - 1).getId();
		ChangeRequestEntity cr = _crDao.findOne(crId);
		System.out.println("ChangeRequest info: " + cr);
		assertTrue(cr != null);
	}

	@Test
	public void testFindByParent() {
		System.out.println();
		String parentId = String.valueOf(System.currentTimeMillis());
		List<ChangeRequestEntity> findCRs = _crDao.findByParentId(parentId);
		assertTrue(findCRs.size() == 0);
		_crDao.addCR(new ChangeRequestEntity(null, null, parentId, null, null,
				null, null, null, null, null, null));

		findCRs = _crDao.findByParentId(parentId);
		System.out.println(findCRs.size());
		for (ChangeRequestEntity findCR : findCRs) {
			System.out.println("Found ChangeRequest info: " + findCR);
		}
		assertTrue(findCRs.size() == 1);
	}

	@Test
	public void testAddDeleteIssueFromCR() {
		System.out.println();
		_crDao.addCR(new ChangeRequestEntity());
		_issueDao.addIssue(new IssueEntity());
		List<ChangeRequestEntity> allCRs = _crDao.findAll();
		List<IssueEntity> allIssues = _issueDao.findAll();
		String crId = allCRs.get(allCRs.size() - 1).getId();
		String issueId = allIssues.get(allIssues.size() - 1).getId();

		ChangeRequestEntity cr = _crDao.findOne(crId);
		System.out.println("CR info before adding issue: " + cr);
		assertFalse(cr.getIssueIdList().contains(issueId));
		_crDao.addIssueToCR(crId, issueId);
		cr = _crDao.findOne(crId);
		System.out.println("CR info after adding issue: " + cr);
		assertTrue(cr.getIssueIdList().contains(issueId));

		_crDao.deleteIssueFromCR(crId, new String[] { issueId });
		cr = _crDao.findOne(crId);
		System.out.println("CR info after deleting issue: " + cr);
		assertFalse(cr.getIssueIdList().contains(issueId));
	}

	@Test
	public void testAddDeleteTaskFromCR() {
		System.out.println();
		_crDao.addCR(new ChangeRequestEntity());
		_taskDao.addTask(new TaskEntity());
		List<ChangeRequestEntity> allCRs = _crDao.findAll();
		List<TaskEntity> allTasks = _taskDao.findAll();
		String crId = allCRs.get(allCRs.size() - 1).getId();
		String taskId = allTasks.get(allTasks.size() - 1).getId();

		ChangeRequestEntity cr = _crDao.findOne(crId);
		System.out.println("CR info before adding task: " + cr);
		assertFalse(cr.getTaskIdList().contains(taskId));
		_crDao.addTaskToCR(crId, taskId);
		cr = _crDao.findOne(crId);
		System.out.println("CR info after adding task: " + cr);
		assertTrue(cr.getTaskIdList().contains(taskId));

		_crDao.deleteTaskFromCR(crId, new String[] { taskId });
		cr = _crDao.findOne(crId);
		System.out.println("CR info after deleting task: " + cr);
		assertFalse(cr.getTaskIdList().contains(taskId));
	}
}
