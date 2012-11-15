package org.opslog.services.impl;

import java.util.List;

import org.opslog.dao.CommentDAO;
import org.opslog.dao.FileRefDAO;
import org.opslog.dao.ProjectDAO;
import org.opslog.dao.UserDAO;
import org.opslog.model.BacklogItem;
import org.opslog.model.Comment;
import org.opslog.model.FileRef;
import org.opslog.model.Project;
import org.opslog.model.Sprint;
import org.opslog.model.Task;
import org.opslog.model.User;
import org.opslog.services.BacklogItemFilter;
import org.opslog.services.OpsLogService;
import org.opslog.services.SprintFilter;

public class OpsLogServiceImpl implements OpsLogService {

	private int pageSize = 20;
	private UserDAO userDao;
	private ProjectDAO projectDao;
	private CommentDAO commentDao;
	private FileRefDAO fileRefDao;

	public OpsLogServiceImpl() {
	}
	
	public void createProject(Project project) {
		projectDao.save(project);
	}

	public void deleteProject(Long projectId) {
		projectDao.delete(projectId);
	}

	public void createUser(User user) {
		userDao.save(user);
	}

	public void updateUser(User user) {
		createUser(user);
	}

	public void removeUser(String userId) {
		userDao.delete(userId);
	}

	public void addBacklogItem(BacklogItem item) {
		Long projectId = item.getProject().getId();
		Project project = projectDao.getProject(projectId);
		project.addBacklogItem(item);
		projectDao.save(project);
	}

	public void updateBacklogItem(BacklogItem item) {
		Long projectId = item.getProject().getId();
		Project project = projectDao.getProject(projectId);
		project.removeBacklogItemById(item.getId());
		project.addBacklogItem(item);
		projectDao.save(project);
	}

	public void deleteBacklogItem(Long backlogItemId) {
		BacklogItem item = projectDao.getBacklogItem(backlogItemId);
		Long projectId = item.getProject().getId();
		Project project = projectDao.getProject(projectId);
		project.removeBacklogItemById(item.getId());
		project.removeBacklogItemById(backlogItemId);
		projectDao.save(project);
	}

	public List<BacklogItem> listBacklogItems(int pageNumber) {
		return projectDao.findBacklogItems((String) null, from(pageNumber), to(pageNumber));
	}

	public List<BacklogItem> listBacklogItemsBy(BacklogItemFilter filter, int pageNumber) {
		return projectDao.findBacklogItems(filter, from(pageNumber), to(pageNumber));
	}

	public List<BacklogItem> listBacklogItemsBy(String simpleSearch, int pageNumber) {
		return projectDao.findBacklogItems(simpleSearch, from(pageNumber), to(pageNumber));
	}

	public void addSprint(Sprint sprint) {
		Long projectId = sprint.getProject().getId();
		Project project = projectDao.getProject(projectId);
		project.addSprint(sprint);
		projectDao.save(project);
	}

	public void completeSprint(Long sprintId) {
		Sprint sprint = projectDao.getSprint(sprintId);
		sprint.setStatus(Sprint.SprintStatus.COMPLETED);
		projectDao.save(sprint);
	}

	public void addItemToSprint(BacklogItem item, Long sprintId) {
		Sprint sprint = projectDao.getSprint(sprintId);
		item.setSprint(sprint);
		sprint.addItem(item);
		projectDao.save(sprint);
	}

	public List<Sprint> listSprints(int pageNumber) {
		return projectDao.findSprints((String) null, from(pageNumber), to(pageNumber));
	}

	public List<Sprint> listSprintsBy(SprintFilter filter, int pageNumber) {
		return projectDao.findSprints(filter, from(pageNumber), to(pageNumber));
	}

	public List<Sprint> listSprintsBy(String simpleSearch, int pageNumber) {
		return projectDao.findSprints(simpleSearch, from(pageNumber), to(pageNumber));
	}

	public void addTask(Task task, Long backlogItemId) {
		BacklogItem item = projectDao.getBacklogItem(backlogItemId);
		item.addTask(task);
		task.setParent(item);
		projectDao.save(item);
	}

	public void removeTask(Long taskId) {
		Task task = projectDao.getTask(taskId);
		Long backlogItemId = task.getParent().getId();
		BacklogItem item = projectDao.getBacklogItem(backlogItemId);
		item.removeTask(task);
		projectDao.save(item);
	}

	public void updateTask(Task task) {
		projectDao.save(task);
	}

	public List<Task> getTasksByBacklogItem(Long backlogItemId) {
		return projectDao.findTasksBy("parent.id", backlogItemId);
	}

	public List<Task> getTasksBySprint(Long sprintId) {
		return projectDao.findTasksBy("parent.sprint.id", sprintId);
	}

	public List<Task> getTasksByUser(String userId) {
		return projectDao.findTasksBy("assignedTo.id", userId);
	}

	public void addCommentToTask(Long taskId, Comment comment) {
		Task task = projectDao.getTask(taskId);
		task.addComment(comment);
		projectDao.save(task);
		commentDao.save(comment);
	}

	public void addCommentToSprint(Long sprintId, Comment comment) {
		Sprint sprint = projectDao.getSprint(sprintId);
		sprint.addComment(comment);
		projectDao.save(sprint);
		commentDao.save(comment);
	}

	public void addCommentToBacklogItem(Long backlogItemId, Comment comment) {
		BacklogItem item = projectDao.getBacklogItem(backlogItemId);
		item.addComment(comment);
		projectDao.save(item);
		commentDao.save(comment);
	}

	public void addCommentToProject(Long projectId, Comment comment) {
		Project project = projectDao.getProject(projectId);
		project.addComment(comment);
		projectDao.save(project);
		commentDao.save(comment);
	}
	
	public List<Comment> getLastComments(int size) {
		return commentDao.list(0, size);
	}

	public void replyToComment(Comment reply, Long commentId) {
		Comment parentComment = commentDao.getComment(commentId);
		parentComment.addReply(reply);
		commentDao.save(parentComment);
	}

	public void attachFileToProject(Long projectId, FileRef file, byte[] content) {
		Project project = projectDao.getProject(projectId);
		fileRefDao.save(file, content);
		project.addAttachment(file);
		projectDao.save(project);
	}

	public void attachFileToBacklogItem(Long backlogItemId, FileRef file, byte[] content) {
		BacklogItem item = projectDao.getBacklogItem(backlogItemId);
		fileRefDao.save(file, content);
		item.addAttachment(file);
		projectDao.save(item);
	}

	public void attachFileToSprint(Long sprintId, FileRef file, byte[] content) {
		Sprint sprint = projectDao.getSprint(sprintId);
		fileRefDao.save(file, content);
		sprint.addAttachment(file);
		projectDao.save(sprint);
	}

	public void attachFileToTask(Long taskId, FileRef file, byte[] content) {
		Task task = projectDao.getTask(taskId);
		fileRefDao.save(file, content);
		task.addAttachment(file);
		projectDao.save(task);
	}

	public FileRef getAttachment(Long fileRefId) {
		return fileRefDao.getAttachment(fileRefId);
	}
	
	public byte[] getAttachmentContent(Long fileRefId) {
		return fileRefDao.getContent(fileRefId);
	}
	
	public List<Object> query(String query, String collection) {
		return projectDao.query(query, collection);
	}
	
	public ProjectDAO getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public CommentDAO getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}

	public FileRefDAO getFileRefDao() {
		return fileRefDao;
	}

	public void setFileRefDao(FileRefDAO fileRefDao) {
		this.fileRefDao = fileRefDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public UserDAO getUserDao() {
		return userDao;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	private int to(int pageNumber) {
		return (pageNumber + 1) * getPageSize();
	}

	private int from(int pageNumber) {
		return pageNumber * getPageSize();
	}
}
