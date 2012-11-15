package org.opslog.dao.mongo;

import org.opslog.dao.UserDAO;
import org.opslog.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

public class UserMongoDAO extends MongoTemplate implements UserDAO {

	private String collectionName;
	
	public UserMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}
	
	public void save(User user) {
		save(getConverter().convertToMongoType(user), getCollectionName());
	}

	public void delete(String userId) {
		remove(getUser(userId), getCollectionName());
	}

	public User getUser(String userId) {
		return findById(userId, User.class, getCollectionName());
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
