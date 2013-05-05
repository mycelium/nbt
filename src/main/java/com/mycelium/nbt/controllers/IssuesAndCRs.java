package com.mycelium.nbt.controllers;

import java.io.Serializable;
public class IssuesAndCRs implements Serializable{

private String[] idCRList;
private String[] idIssueList;

public String[] getIdCRList()
{
	return idCRList;
}

public void setIdCRList(String[] cRsId)
{
	this.idCRList=cRsId;
}
public String[] getIdIssueList()
{
	return idIssueList;
}

public void setIdIssueList(String[] idIssue)
{
	this.idIssueList=idIssue;
}
}