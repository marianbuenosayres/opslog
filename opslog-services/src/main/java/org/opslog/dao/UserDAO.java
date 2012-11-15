package org.opslog.dao;

import org.opslog.model.Team;
import org.opslog.model.User;

public interface UserDAO {

	void save(User user);
	void delete(String userId);
	User getUser(String userId);

	void save(Team team);
	void delete(Long teamId);
	Team getTeam(Long teamId);
	
}
