package org.opslog.services;

import org.opslog.model.User;

public class BacklogItemFilter {

	private Integer priorityEqual;
	private Integer priorityFrom;
	private Integer priorityTo;
	private String projectNameLike;
	private String nameLike;
	private String descriptionLike;
	private Boolean completed;
	private Double percentCompleteFrom;
	private Double percentCompleteTo;
	private User assignedToUser;

	public Integer getPriorityEqual() {
		return priorityEqual;
	}

	public void setPriorityEqual(Integer priorityEqual) {
		this.priorityEqual = priorityEqual;
	}

	public Integer getPriorityFrom() {
		return priorityFrom;
	}

	public void setPriorityFrom(Integer priorityFrom) {
		this.priorityFrom = priorityFrom;
	}

	public Integer getPriorityTo() {
		return priorityTo;
	}

	public void setPriorityTo(Integer priorityTo) {
		this.priorityTo = priorityTo;
	}

	public String getProjectNameLike() {
		return projectNameLike;
	}

	public void setProjectNameLike(String projectNameLike) {
		this.projectNameLike = projectNameLike;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getDescriptionLike() {
		return descriptionLike;
	}

	public void setDescriptionLike(String descriptionLike) {
		this.descriptionLike = descriptionLike;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Double getPercentCompleteFrom() {
		return percentCompleteFrom;
	}

	public void setPercentCompleteFrom(Double percentCompleteFrom) {
		this.percentCompleteFrom = percentCompleteFrom;
	}

	public Double getPercentCompleteTo() {
		return percentCompleteTo;
	}

	public void setPercentCompleteTo(Double percentCompleteTo) {
		this.percentCompleteTo = percentCompleteTo;
	}

	public User getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(User assignedToUser) {
		this.assignedToUser = assignedToUser;
	}
}
