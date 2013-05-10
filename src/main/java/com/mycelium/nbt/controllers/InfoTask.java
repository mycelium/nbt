package com.mycelium.nbt.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class InfoTask implements Serializable {

	private String idTask;
	private String captionTask;
	private String descriptionTask;
	private List<String> assignedModulesCaption;
	private HashMap<String, String> modulesIdAndCaption;

	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public String getCaptionTask() {
		return captionTask;
	}

	public void setCaptionTask(String captionTask) {
		this.captionTask = captionTask;
	}

	public String getDescriptionTask() {
		return descriptionTask;
	}

	public void setDescriptionTask(String descriptionTask) {
		this.descriptionTask = descriptionTask;
	}

	public List<String> getAssignedModulesCaption() {
		return assignedModulesCaption;
	}

	public void setAssignedModulesCaption(List<String> assignedModulesCaption) {
		this.assignedModulesCaption = assignedModulesCaption;
	}

	public HashMap<String, String> getModulesIdAndCaption() {
		return modulesIdAndCaption;
	}

	public void setModulesIdAndCaption(
			HashMap<String, String> modulesIdAndCaption) {
		this.modulesIdAndCaption = modulesIdAndCaption;
	}

}
