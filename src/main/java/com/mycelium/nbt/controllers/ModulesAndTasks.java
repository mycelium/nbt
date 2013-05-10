package com.mycelium.nbt.controllers;
import java.io.Serializable;

public class ModulesAndTasks implements Serializable{


private String[] idModuleList;
private String[] idTaskList;

	public String[] getIdModuleList()
{
	return idModuleList;
}

public void setIdModuleList(String[] idModuleList)
{
	this.idModuleList=idModuleList;
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