package org.opslog.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {

	private Long id;
	private String name;
	private List<User> users;

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
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Map<String, Object> asMap() {
		Map<String, Object> retval = new HashMap<String, Object>();
		retval.put("id", id);
		retval.put("name", name);
		List<Object> userNames = null;
		if (users != null) {
			userNames = new ArrayList<Object>(users.size());
			for (User user : users) {
				userNames.add(user.getId());
			}
		}
		retval.put("users", userNames);
		return retval;
	}

	public void addUser(User user) {
		if (users == null) users = new ArrayList<User>();
		if (!users.contains(user)) {
			users.add(user);
		}
	}

	public void removeUser(User user) {
		if (users != null && users.contains(user)) {
			users.remove(user);
		}
	}
}
