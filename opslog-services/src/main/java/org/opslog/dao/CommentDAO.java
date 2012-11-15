package org.opslog.dao;

import java.util.List;

import org.opslog.model.Comment;

public interface CommentDAO {

	void save(Comment comment);
	List<Comment> list(int from, int to);
	Comment getComment(Long commentId);
}
