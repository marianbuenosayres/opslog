package org.opslog.dao.mongo;

import org.opslog.dao.UserDAO;
import org.opslog.model.Team;
import org.opslog.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

public class UserMongoDAO extends MongoTemplate implements UserDAO {

	public UserMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}
	
	public void save(User user) {
		save(getConverter().convertToMongoType(user), "users");
	}

	public void delete(String userId) {
		remove(findById(userId, User.class, "users"), "users");
	}

	public User getUser(String userId) {
		return findById(userId, User.class, "users");
	}
	
	public void save(Team team) {
		save(getConverter().convertToMongoType(team), "teams");
	}

	public void delete(Long teamId) {
		remove(findById(teamId, Team.class, "teams"), "teams");
	}
	
	public Team getTeam(Long teamId) {
		return findById(teamId, Team.class, "teams");
	}

}
