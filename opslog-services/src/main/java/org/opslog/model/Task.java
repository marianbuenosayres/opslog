package org.opslog.model;

import java.math.BigDecimal;

public class Task extends Commentable {

	private Long id;
	private BacklogItem parent;
	private String name;
	private String description;
	private BigDecimal estimate;
	private User assignedTo;

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
}
