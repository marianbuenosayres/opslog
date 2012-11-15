package org.opslog.model;

import java.util.Date;

public class ProjectStatus {

	public enum Description {
		SUCCESS, FAILED_TESTS, FAILED_COMPILATION, DISABLED
	}
	
	private Long id;
	private Date moment;
	private Description status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Description getStatus() {
		return status;
	}

	public void setStatus(Description status) {
		this.status = status;
	}
}
