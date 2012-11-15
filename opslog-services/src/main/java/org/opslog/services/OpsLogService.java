package org.opslog.services;

import java.util.List;

import org.opslog.model.BacklogItem;
import org.opslog.model.Comment;
import org.opslog.model.FileRef;
import org.opslog.model.Project;
import org.opslog.model.Sprint;
import org.opslog.model.Task;
import org.opslog.model.User;

public interface OpsLogService {

	//projects
	void createProject(Project project);
	void deleteProject(Long projectId);
	void addCommentToProject(Long projectId, Comment comment);
	void attachFileToProject(Long projectId, FileRef file, byte[] content);

	//users
	void createUser(User user);
	void updateUser(User user);
	void removeUser(String userId);
	
	//backlog
	void addBacklogItem(BacklogItem item);
	void updateBacklogItem(BacklogItem item);
	void deleteBacklogItem(Long backlogItemId);
	void addCommentToBacklogItem(Long backlogItemId, Comment comment);
	void attachFileToBacklogItem(Long backlogItemId, FileRef file, byte[] content);
	//backlogs are always ordered by priority
	List<BacklogItem> listBacklogItems(int pageNumber);
	List<BacklogItem> listBacklogItemsBy(BacklogItemFilter filter, int pageNumber);
	List<BacklogItem> listBacklogItemsBy(String simpleSearch, int pageNumber);
	
	//sprints
	void addSprint(Sprint sprint);
	void completeSprint(Long sprintId);
	void addItemToSprint(BacklogItem item, Long sprintId);
	List<Sprint> listSprints(int pageNumber);
	List<Sprint> listSprintsBy(SprintFilter filter, int pageNumber);
	List<Sprint> listSprintsBy(String simpleSearch, int pageNumber);
	void addCommentToSprint(Long sprintId, Comment comment);
	void attachFileToSprint(Long sprintId, FileRef file, byte[] content);
	
	//tasks
	void addTask(Task task, Long backlogItemId);
	void removeTask(Long taskId);
	void updateTask(Task task);
	List<Task> getTasksByBacklogItem(Long backlogItemId);
	List<Task> getTasksBySprint(Long sprintId);
	List<Task> getTasksByUser(String userId);
	void addCommentToTask(Long taskId, Comment comment);
	void attachFileToTask(Long taskId, FileRef file, byte[] content);
	
	//charts
	List<Object> query(String query, String collection);
	void replyToComment(Comment reply, Long commentId);
	List<Comment> getLastComments(int size);
}
