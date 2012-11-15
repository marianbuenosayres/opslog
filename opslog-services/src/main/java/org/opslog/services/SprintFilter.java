package org.opslog.services;

import java.util.Date;
import java.util.List;

public class SprintFilter {

	private String projectNameLike;
	private String teamNameLike;
	private String nameLike;
	private String descriptionLike;
	private List<String> searchForStatus;
	private Date dateFrom;
	private Date dateTo;

	public String getProjectNameLike() {
		return projectNameLike;
	}

	public void setProjectNameLike(String projectNameLike) {
		this.projectNameLike = projectNameLike;
	}

	public String getTeamNameLike() {
		return teamNameLike;
	}

	public void setTeamNameLike(String teamNameLike) {
		this.teamNameLike = teamNameLike;
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
	
	@Override
	public String toString() {
		// FIXME Make this turn into a filter
		return super.toString();
	}
}
