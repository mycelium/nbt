package com.mycelium.nbt.controllers;
import java.util.List;
import java.io.Serializable;
public class MarkIssues implements Serializable{

private String[] idList;
private String marker;

public String[] getIdList()
{
	return idList;
}

public void setIdList(String[] idList)
{
	this.idList=idList;
}

public String getMarker()
{
	return marker;
}

public void setMarker(String marker)
{
	this.marker=marker;
}

}