package org.opslog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sprint {

	public enum SprintStatus {
		PLANNING, IN_PROGRESS, COMPLETED
	}
	
	private Long id;
	private Project project;
	private String name;
	private String description;
	private List<BacklogItem> items;
	private SprintStatus status;
	private Date startDate;
	private Date endDate;
	private List<Comment> comments;
	private List<FileRef> attachments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SprintStatus getStatus() {
		return status;
	}
	
	public void setStatus(SprintStatus status) {
		this.status = status;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BacklogItem> getItems() {
		return items;
	}

	public void setItems(List<BacklogItem> items) {
		this.items = items;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void addItem(BacklogItem item) {
		if (items == null) items = new ArrayList<BacklogItem>();
		if (!items.contains(item)) {
			items.add(item);
		}
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
