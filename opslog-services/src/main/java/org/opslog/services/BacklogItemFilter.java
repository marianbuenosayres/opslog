package org.opslog.services;


public class BacklogItemFilter {

	private Integer priorityEqual;
	private Integer priorityFrom;
	private Integer priorityTo;
	private String projectNameRegex;
	private String nameRegex;
	private String descriptionRegex;
	private Boolean completed;
	private Double percentCompleteFrom;
	private Double percentCompleteTo;
	private String assignedToUser;

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

	public String getProjectNameRegex() {
		return projectNameRegex;
	}

	public void setProjectNameRegex(String projectNameRegex) {
		this.projectNameRegex = projectNameRegex;
	}

	public String getNameRegex() {
		return nameRegex;
	}

	public void setNameRegex(String nameRegex) {
		this.nameRegex = nameRegex;
	}

	public String getDescriptionRegex() {
		return descriptionRegex;
	}

	public void setDescriptionRegex(String descriptionRegex) {
		this.descriptionRegex = descriptionRegex;
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

	public String getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(String assignedToUser) {
		this.assignedToUser = assignedToUser;
	}
}
