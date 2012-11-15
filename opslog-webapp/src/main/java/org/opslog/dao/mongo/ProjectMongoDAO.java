package org.opslog.dao.mongo;

import org.opslog.dao.ProjectDAO;
import org.opslog.model.Project;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

public class ProjectMongoDAO extends MongoTemplate implements ProjectDAO {

	public ProjectMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	public void save(Project project) {
		save(getConverter().convertToMongoType(project), "projects");
	}

	public void delete(Long projectId) {
		remove(findById(projectId, Project.class), "projects");
	}
	
	public Project getProject(Long projectId) {
		return findById(projectId, Project.class, "projects");
	}

}
