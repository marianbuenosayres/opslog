package org.opslog.services;

import java.util.Date;
import java.util.List;

public class SprintFilter {

	private String projectNameRegex;
	private String nameRegex;
	private String descriptionRegex;
	private List<String> searchForStatus;
	private Date dateFrom;
	private Date dateTo;

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

	public List<String> getSearchForStatus() {
		return searchForStatus;
	}

	public void setSearchForStatus(List<String> searchForStatus) {
		this.searchForStatus = searchForStatus;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
}
