package org.opslog.dao;

import java.util.Iterator;

import org.opslog.model.BacklogItem;
import org.opslog.model.Project;
import org.opslog.model.Sprint;
import org.opslog.model.Task;
import org.opslog.services.BacklogItemFilter;
import org.opslog.services.SprintFilter;

public interface ProjectDAO {

	void save(Project project);
	void delete(Long projectId);
	Project getProject(Long projectId);
	
	void save(Sprint sprint);
	Sprint getSprint(Long sprintId);
	Iterator<Sprint> findSprints(SprintFilter filter, int from, int to);
	Iterator<Sprint> findSprints(String simpleSearch, int from, int to);

	void save(BacklogItem item);
	BacklogItem getBacklogItem(Long backlogItemId);
	Iterator<BacklogItem> findBacklogItems(BacklogItemFilter filter, int from, int to);
	Iterator<BacklogItem> findBacklogItems(String simpleSearch, int from, int to);
	
	Task getTask(Long taskId);
	void save(Task task);
	Iterator<Task> findTasksBy(String key, Object value);
	
	Iterator<Object> query(String query, String collectionName);
}
