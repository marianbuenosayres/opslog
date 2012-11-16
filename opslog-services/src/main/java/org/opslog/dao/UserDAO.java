package org.opslog.dao;

import java.util.Iterator;

import org.opslog.model.User;

public interface UserDAO {

	Iterator<User> getUsers();
	void save(User user);
	void delete(String userId);
	User getUser(String userId);
}
