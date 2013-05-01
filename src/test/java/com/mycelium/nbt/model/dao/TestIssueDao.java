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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class TestIssueDao {
	@Autowired
	private IssueDao _issueDao;
	@Autowired
	private ChangeRequestDao _crDao;

	@Test
	public void testFindAll() {
		System.out.println();
		List<IssueEntity> allIssues = _issueDao.findAll();
		assertTrue(allIssues != null);
		System.out.println("Amount of issues in DB = " + allIssues.size());
		for (IssueEntity issue : allIssues) {
			System.out.println(issue);
		}
		assertTrue(allIssues.size() >= 0);
	}
	
	@Test
	public void testAddIssue() {
		System.out.println();
		List<IssueEntity> allIssues = _issueDao.findAll();
		int amountOfIssues = allIssues.size();
		System.out.println("Amount of issues in DB before adding issue = " + amountOfIssues);
		_issueDao.addIssue(new IssueEntity());
		allIssues = _issueDao.findAll();
		System.out.println("Amount of issues in DB after adding issue = " + allIssues.size());
		assertTrue(amountOfIssues + 1 == allIssues.size());
	}
	
	@Test
	public void testFindOne() {
		System.out.println();
		_issueDao.addIssue(new IssueEntity());
		List<IssueEntity> allIssues = _issueDao.findAll();
		System.out.println("Amount of issues in DB = " + allIssues.size());
		assertTrue(allIssues.size() > 0);
		String issueId = allIssues.get(allIssues.size()-1).getId();
		IssueEntity issue = _issueDao.findOne(issueId);
		System.out.println("Issue info: " + issue);
		assertTrue(issue != null);
	}
	
	@Test
	public void testUpdateMarker() {
		System.out.println();
		String color = "green";
		_issueDao.addIssue(new IssueEntity());
		List<IssueEntity> allIssues = _issueDao.findAll();
		String issueId = allIssues.get(allIssues.size()-1).getId();
		_issueDao.updateMarker(issueId, color);
		IssueEntity issue = _issueDao.findOne(issueId);
		System.out.println("Issue info: " + issue);
		assertTrue(issue.getMarker().equals(color));
	}
	
	@Test
	public void testAddDeleteCRFromIssue() {
		System.out.println();
		_issueDao.addIssue(new IssueEntity());
		_crDao.addCR(new ChangeRequestEntity());
		List<IssueEntity> allIssues = _issueDao.findAll();
		List<ChangeRequestEntity> allCRs = _crDao.findAll();
		String issueId = allIssues.get(allIssues.size()-1).getId();
		String crId = allCRs.get(allCRs.size()-1).getId();
		
		IssueEntity issue = _issueDao.findOne(issueId);
		System.out.println("Issue info before adding CR: " + issue);
		assertFalse(issue.getAttachedCRs().contains(crId));
		_issueDao.addCRToIssue(issueId, crId);
		issue = _issueDao.findOne(issueId);
		System.out.println("Issue info after adding CR: " + issue);
		assertTrue(issue.getAttachedCRs().contains(crId));
		
		_issueDao.deleteCRFromIssue(issueId, crId);
		issue = _issueDao.findOne(issueId);
		System.out.println("Issue info after deleting CR: " + issue);
		assertFalse(issue.getAttachedCRs().contains(crId));
	}
}
