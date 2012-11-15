package org.opslog.model;

import java.util.List;

public class Comment {

	private Long id;
	private User createdBy;
	private String comment;
	private List<Comment> replies;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}
	
	public void addReply(Comment reply) {
		this.replies.add(reply);
	}
}
