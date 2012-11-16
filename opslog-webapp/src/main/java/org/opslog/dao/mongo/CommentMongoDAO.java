package org.opslog.dao.mongo;

import java.util.Iterator;

import org.opslog.dao.CommentDAO;
import org.opslog.model.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

public class CommentMongoDAO extends MongoTemplate implements CommentDAO {

	private String collectionName;

	public CommentMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	public void save(Comment comment) {
		save(getConverter().convertToMongoType(comment), getCollectionName());
	}

	public Iterator<Comment> list(int from, int to) {
		Query query = new Query();
		query.skip(from).limit(to);
		return find(query, Comment.class, getCollectionName()).iterator();
	}
	
	public Comment getComment(Long commentId) {
		return findById(commentId, Comment.class, getCollectionName());
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
