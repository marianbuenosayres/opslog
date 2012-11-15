package org.opslog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class BacklogItem {

	private Long id;
	private Integer priority;
	private Project project;
	private Sprint sprint;
	private String name;
	private String description;
	private Integer roughEstimate;
	private Long estimate; //in milliseconds 
	private Long actualDuration;
	private Double percentComplete;
	private User assignedTo;
	
	private List<Task> tasks;
	private List<Comment> comments;
	private List<FileRef> attachments;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setActualDuration(Long actualDuration) {
		this.actualDuration = actualDuration;
	}

	public void setPercentComplete(Double percentComplete) {
		this.percentComplete = percentComplete;
	}

	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

	public Sprint getSprint() {
		return sprint;
	}
	
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	
	public long getActualDuration() {
		return actualDuration;
	}

	public void setActualDuration(long actualDuration) {
		this.actualDuration = actualDuration;
	}

	public double getPercentComplete() {
		return percentComplete;
	}

	public void setPercentComplete(double percentComplete) {
		this.percentComplete = percentComplete;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getEstimate() {
		return estimate;
	}
	
	public void setEstimate(Long estimate) {
		this.estimate = estimate;
	}
	
	public Integer getRoughEstimate() {
		return roughEstimate;
	}
	
	public void setRoughEstimate(Integer roughEstimate) {
		this.roughEstimate = roughEstimate;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addTask(Task task) {
		if (tasks == null) tasks = new ArrayList<Task>();
		if (!tasks.contains(task)) {
			tasks.add(task);
		}
	}

	public void removeTask(Task task) {
		if (tasks != null && tasks.contains(task)) {
			tasks.remove(task);
		}
	}

	public void addComment(Comment comment) {
		if (comments == null) comments = new ArrayList<Comment>();
		if (!comments.contains(comment)) {
			comments.add(comment);
		}
	}

	public List<FileRef> getAttachments() {
		return attachments;
	}
	
	public void setAttachments(List<FileRef> attachments) {
		this.attachments = attachments;
	}
	
	public void addAttachment(FileRef file) {
		if (attachments == null) attachments = new ArrayList<FileRef>();
		if (!attachments.contains(file)) {
			attachments.add(file);
		}
	}
}
