package org.opslog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project extends Commentable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	@Column(length = 100)
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ProjectStatus> status;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Sprint> sprints;
	@OneToMany(cascade = CascadeType.ALL)
	private List<BacklogItem> backlog;
	@OneToMany(cascade = CascadeType.ALL)
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
