package com.mycelium.nbt.controllers;

import java.io.Serializable;
public class TasksAndCRs implements Serializable{

private String[] idCRList;
private String[] idTaskList;

public String[] getIdCRList()
{
	return idCRList;
}

public void setIdCRList(String[] cRsId)
{
	this.idCRList=cRsId;
}
public String[] getIdTaskList()
{
	return idTaskList;
}

public void setIdTaskList(String[] idTaskList)
{
	this.idTaskList=idTaskList;
}
}