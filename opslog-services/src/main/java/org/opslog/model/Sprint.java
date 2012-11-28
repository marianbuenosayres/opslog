package org.opslog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sprint extends Commentable {

	public enum SprintStatus {
		PLANNING, IN_PROGRESS, COMPLETED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Project project;
	@Column(length = 100)
	private String name;
	@Column(length = 1024)
	private String description;
	@OneToMany(cascade = CascadeType.ALL)
	private List<BacklogItem> items;
	@OneToOne
	private SprintStatus status;
	private Date startDate;
	private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SprintStatus getStatus() {
		return status;
	}
	
	public void setStatus(SprintStatus status) {
		this.status = status;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BacklogItem> getItems() {
		return items;
	}

	public void setItems(List<BacklogItem> items) {
		this.items = items;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void addItem(BacklogItem item) {
		if (items == null) items = new ArrayList<BacklogItem>();
		if (!items.contains(item)) {
			items.add(item);
		}
	}
}
