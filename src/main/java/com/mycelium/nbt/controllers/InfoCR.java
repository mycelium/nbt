package com.mycelium.nbt.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
public class InfoCR implements Serializable{

private String idCR;
private String captionCR;
private String descriptionCR;
private List<String> assignedIssuesCaption;
private List<String> assignedTasksCaption;
private HashMap<String,String> issuesIdAndCaption;
private HashMap<String,String> tasksIdAndCaption;

public String getIdCR()
{
	return idCR;
}

public void setIdCR(String idCR)
{
	this.idCR=idCR;
}
public String getCaptionCR()
{
	return captionCR;
}

public void setCaptionCR(String captionCR)
{
	this.captionCR=captionCR;
}
public String getDescriptionCR()
{
	return descriptionCR;
}

public void setDescriptionCR(String descriptionCR)
{
	this.descriptionCR=descriptionCR;
}

public List<String> getAssignedIssuesCaption()
{
	return assignedIssuesCaption;
}

public void setAssignedIssuesCaption(List<String> assignedIssuesCaption)
{
	this.assignedIssuesCaption=assignedIssuesCaption;
}
public List<String> getAssignedTasksCaption()
{
	return assignedTasksCaption;
}

public void setAssignedTasksCaption(List<String> assignedTasksCaption)
{
	this.assignedTasksCaption=assignedTasksCaption;
}
public HashMap<String,String> getIssuesIdAndCaption()
{
	return issuesIdAndCaption;
}

public void setIssuesIdAndCaption(HashMap<String,String> issuesIdAndCaption)
{
	this.issuesIdAndCaption=issuesIdAndCaption;
}
public HashMap<String,String> getTasksIdAndCaption()
{
	return tasksIdAndCaption;
}

public void setTasksIdAndCaption(HashMap<String,String> tasksIdAndCaption)
{
	this.tasksIdAndCaption=tasksIdAndCaption;
}
}