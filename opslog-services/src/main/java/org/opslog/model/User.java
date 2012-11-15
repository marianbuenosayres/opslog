package org.opslog.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String id;
	private Long currentProjectId;	
	private String passwordMD5;
	private List<String> permissions; //canseeprojects,canseeusers,canseebacklog,
	private List<Team> teams;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswordMD5() {
		return passwordMD5;
	}

	public void setPasswordMD5(String passwordMD5) {
		this.passwordMD5 = passwordMD5;
	}
	
	public List<String> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
	public Long getCurrentProjectId() {
		return currentProjectId;
	}
	
	public void setCurrentProjectId(Long currentProjectId) {
		this.currentProjectId = currentProjectId;
	}
	
	public List<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void addTeam(Team team) {
		if (teams == null) teams = new ArrayList<Team>();
		if (!teams.contains(team)) {
			teams.add(team);
		}
	}

	public void removeTeam(Team team) {
		if (teams != null && teams.contains(team)) {
			teams.remove(team);
		}
	}
}
