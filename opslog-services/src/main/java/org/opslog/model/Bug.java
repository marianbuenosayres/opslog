package org.opslog.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Bug extends BacklogItem {

	public enum BugConclussion {
		RESOLVED, CANT_REPRODUCE, WONT_FIX;
	}
	
	@Enumerated(EnumType.STRING)
	private BugConclussion conclusion;
	private Integer severity;
	@ManyToOne
	private User reportedBy;

	public BugConclussion getConclusion() {
		return conclusion;
	}
	
	public void setConclusion(BugConclussion conclusion) {
		this.conclusion = conclusion;
	}
	
	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public User getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(User reportedBy) {
		this.reportedBy = reportedBy;
	}
}
