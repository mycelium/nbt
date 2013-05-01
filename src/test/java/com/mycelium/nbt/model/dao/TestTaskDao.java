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
import com.mycelium.nbt.model.entities.TaskEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class TestTaskDao {
	@Autowired
	private TaskDao _taskDao;
	@Autowired
	private ChangeRequestDao _crDao;

	@Test
	public void testFindAll() {
		System.out.println();
		List<TaskEntity> allTasks = _taskDao.findAll();
		assertTrue(allTasks != null);
		System.out.println("Amount of tasks in DB = " + allTasks.size());
		for (TaskEntity task : allTasks) {
			System.out.println(task);
		}
		assertTrue(allTasks.size() >= 0);
	}
	
	@Test
	public void testAddTask() {
		System.out.println();
		List<TaskEntity> allTaks = _taskDao.findAll();
		int amountOfTasks = allTaks.size();
		System.out.println("Amount of tasks in DB before adding task = " + amountOfTasks);
		_taskDao.addTask(new TaskEntity());
		allTaks = _taskDao.findAll();
		System.out.println("Amount of tasks in DB after adding task = " + allTaks.size());
		assertTrue(amountOfTasks + 1 == allTaks.size());
	}
	
	@Test
	public void testFindOne() {
		System.out.println();
		_taskDao.addTask(new TaskEntity());
		List<TaskEntity> allTasks = _taskDao.findAll();
		System.out.println("Amount of tasks in DB = " + allTasks.size());
		assertTrue(allTasks.size() > 0);
		String taskId = allTasks.get(allTasks.size()-1).getId();
		TaskEntity task = _taskDao.findOne(taskId);
		System.out.println("Task info: " + task);
		assertTrue(task != null);
	}
	
	@Test
	public void testAddDeleteCRFromIssue() {
		System.out.println();
		_taskDao.addTask(new TaskEntity());
		_crDao.addCR(new ChangeRequestEntity());
		List<TaskEntity> allTasks = _taskDao.findAll();
		List<ChangeRequestEntity> allCRs = _crDao.findAll();
		String taskId = allTasks.get(allTasks.size()-1).getId();
		String crId = allCRs.get(allCRs.size()-1).getId();
		
		TaskEntity task = _taskDao.findOne(taskId);
		System.out.println("Task info before adding CR: " + task);
		assertFalse(task.getAttachedCRs().contains(crId));
		_taskDao.addCRToTask(taskId, crId);
		task = _taskDao.findOne(taskId);
		System.out.println("Task info after adding CR: " + task);
		assertTrue(task.getAttachedCRs().contains(crId));
		
		_taskDao.deleteCRFromTask(taskId, crId);
		task = _taskDao.findOne(taskId);
		System.out.println("Task info after deleting CR: " + task);
		assertFalse(task.getAttachedCRs().contains(crId));
	}
}

