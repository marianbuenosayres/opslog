package org.opslog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {

	private @Id Long id;
	private @Column(length = 100) String name;
	private List<Comment> comments;
	private List<FileRef> attachments;
	
	public Project() {
	}
	
	public Project(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		if (comments == null) comments = new ArrayList<Comment>();
		if (!comments.contains(comment)) {
			comments.add(comment);
		}
	}

	public List<FileRef> getAttachments() {
		return attachments;
	}
	
	public void setAttachments(List<FileRef> attachments) {
		this.attachments = attachments;
	}
	
	public void addAttachment(FileRef file) {
		if (attachments == null) attachments = new ArrayList<FileRef>();
		if (!attachments.contains(file)) {
			attachments.add(file);
		}
	}
}
