package org.opslog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProjectStatus {

	public enum Description {
		SUCCESS, FAILED_TESTS, FAILED_COMPILATION, DISABLED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date moment;
	@Enumerated(EnumType.STRING)
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
