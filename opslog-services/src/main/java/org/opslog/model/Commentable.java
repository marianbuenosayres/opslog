package org.opslog.model;

import java.util.ArrayList;
import java.util.List;

public class Commentable {

	private List<Comment> comments;
	private List<FileRef> attachments;

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
