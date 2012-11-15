package org.opslog.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends Commentable {

	private Long id;
	private String name;
	private List<ProjectStatus> status;
	private List<Sprint> sprints;
	private List<BacklogItem> backlog;
	private List<Bug> bugs;
	
	public Project() {
	}
	
	public Project(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Sprint> getSprints() {
		return sprints;
	}
	
	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}
	
	public List<BacklogItem> getBacklog() {
		return backlog;
	}
	
	public void setBacklog(List<BacklogItem> backlog) {
		this.backlog = backlog;
	}
	
	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}
	
	public List<Bug> getBugs() {
		return bugs;
	}
	
	public void setStatus(List<ProjectStatus> status) {
		this.status = status;
	}
	
	public List<ProjectStatus> getStatus() {
		return status;
	}

	public void addBacklogItem(BacklogItem item) {
		if (backlog == null) backlog = new ArrayList<BacklogItem>();
		if (!backlog.contains(item)) {
			backlog.add(item);
		}
	}

	public void removeBacklogItemById(Long backlogItemId) {
		if (backlog != null) {
			BacklogItem itemToRemove = null;
			for (BacklogItem item : backlog) {
				if (backlogItemId.equals(item.getId())) {
					itemToRemove = item;
					break;
				}
			}
			backlog.remove(itemToRemove);
		}
	}

	public void addSprint(Sprint sprint) {
		if (sprints == null) sprints = new ArrayList<Sprint>();
		if (!sprints.contains(sprint)) {
			sprints.add(sprint);
		}
	}
}
