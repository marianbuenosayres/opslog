package org.opslog.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Task {

	private Long id;
	private BacklogItem parent;
	private String name;
	private String description;
	private BigDecimal estimate;
	private User assignedTo;
	private List<Comment> comments;
	private List<FileRef> attachments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BacklogItem getParent() {
		return parent;
	}
	
	public void setParent(BacklogItem parent) {
		this.parent = parent;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getEstimate() {
		return estimate;
	}

	public void setEstimate(BigDecimal estimate) {
		this.estimate = estimate;
	}
	
	public User getAssignedTo() {
		return assignedTo;
	}
	
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
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
