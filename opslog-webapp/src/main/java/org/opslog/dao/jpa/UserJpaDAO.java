package org.opslog.dao.jpa;

import java.util.Iterator;

import org.opslog.dao.UserDAO;
import org.opslog.model.User;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UserJpaDAO extends HibernateTemplate implements UserDAO {

	public void save(User user) {
		super.save(user);
	}

	public void delete(String userId) {
		super.delete(getUser(userId));
	}

	public User getUser(String userId) {
		return get(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	public Iterator<User> getUsers() {
		return (Iterator<User>) iterate("select u from org.opslog.model.User u");
	}
}
