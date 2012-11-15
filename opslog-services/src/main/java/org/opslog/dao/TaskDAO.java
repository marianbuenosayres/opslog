package org.opslog.dao;

import java.util.List;

import org.opslog.model.Task;

public interface TaskDAO {

	void save(Task task);
	void delete(Long taskId);
	Task getTask(Long taskId);
	List<Task> findTasksBy(String key, Object value);
}
