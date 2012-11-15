package org.opslog.dao;

import org.opslog.model.Project;

public interface ProjectDAO {

	void save(Project project);
	void delete(Long projectId);
	Project getProject(Long projectId);
}
