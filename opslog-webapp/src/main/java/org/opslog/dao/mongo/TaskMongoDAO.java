package org.opslog.dao.mongo;

import java.util.List;

import org.opslog.dao.TaskDAO;
import org.opslog.model.Task;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

public class TaskMongoDAO extends MongoTemplate implements TaskDAO {

	public TaskMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	public void save(Task task) {
		save(getConverter().convertToMongoType(task), "tasks");
	}

	public void delete(Long taskId) {
		remove(getTask(taskId), "tasks");
	}
	
	public Task getTask(Long taskId) {
		return findById(taskId, Task.class, "tasks");
	}
	
	public List<Task> findTasksBy(String key, Object value) {
		Query query = new Query(Criteria.where(key).is(value));
		return find(query, Task.class);
	}
}
