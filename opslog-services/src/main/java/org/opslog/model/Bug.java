package org.opslog.model;

public class Bug extends BacklogItem {

	public enum BugConclussion {
		RESOLVED, CANT_REPRODUCE, WONT_FIX;
	}
	
	private BugConclussion conclusion;
	private Integer severity;
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
