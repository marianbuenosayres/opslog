package org.opslog.dao;

import java.util.Iterator;

import org.opslog.model.Comment;

public interface CommentDAO {

	void save(Comment comment);
	Iterator<Comment> list(int from, int to);
	Comment getComment(Long commentId);
}
