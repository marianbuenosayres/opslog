package org.opslog.services.impl;

import java.util.List;

import org.opslog.dao.BacklogDAO;
import org.opslog.dao.CommentDAO;
import org.opslog.dao.FileRefDAO;
import org.opslog.dao.ProjectDAO;
import org.opslog.dao.SprintDAO;
import org.opslog.dao.TaskDAO;
import org.opslog.dao.UserDAO;
import org.opslog.model.BacklogItem;
import org.opslog.model.Comment;
import org.opslog.model.FileRef;
import org.opslog.model.Project;
import org.opslog.model.Sprint;
import org.opslog.model.Task;
import org.opslog.model.Team;
import org.opslog.model.User;
import org.opslog.services.BacklogItemFilter;
import org.opslog.services.OpsLogService;
import org.opslog.services.SprintFilter;

public class OpsLogServiceImpl implements OpsLogService {

	private int pageSize = 20;
	private UserDAO userDao;
	private ProjectDAO projectDao;
	private SprintDAO sprintDao;
	private BacklogDAO backlogDao;
	private TaskDAO taskDao;
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
		List<Team> teams = user.getTeams();
		if (teams != null) {
			for (Team team : teams) {
				userDao.save(team);
			}
		}
		userDao.save(user);
	}

	public void updateUser(User user) {
		createUser(user);
	}

	public void createTeam(Team team) {
		List<User> users = team.getUsers();
		if (users != null) {
			for (User user : users) {
				userDao.save(user);
			}
		}
		userDao.save(team);
	}

	public void addUserToTeam(String userId, Long teamId) {
		User user = userDao.getUser(userId);
		Team team = userDao.getTeam(teamId);
		team.addUser(user);
		user.addTeam(team);
		userDao.save(team);
		userDao.save(user);
	}

	public void removeUserFromTeam(String userId, Long teamId) {
		User user = userDao.getUser(userId);
		Team team = userDao.getTeam(teamId);
		team.removeUser(user);
		user.removeTeam(team);
		userDao.save(user);
		userDao.save(team);
	}

	public void removeUser(String userId) {
		User user = userDao.getUser(userId);
		List<Team> teams = user.getTeams();
		if (teams != null) {
			for (Team team : teams) {
				team.removeUser(user);
				userDao.save(team);
			}
		}
		userDao.delete(userId);
	}

	public void removeTeam(Long teamId) {
		Team team = userDao.getTeam(teamId);
		List<User> users = team.getUsers();
		if (users != null) {
			for (User user : users) {
				user.removeTeam(team);
				userDao.save(user);
			}
		}
		userDao.delete(teamId);
	}

	public void addBacklogItem(BacklogItem item) {
		backlogDao.save(item);
	}

	public void updateBacklogItem(BacklogItem item) {
		backlogDao.save(item);
	}

	public void deleteBacklogItem(Long backlogItemId) {
		backlogDao.delete(backlogItemId);
	}

	public List<BacklogItem> listBacklogItems(int pageNumber) {
		return backlogDao.findBacklogItems(null, from(pageNumber), to(pageNumber));
	}

	public List<BacklogItem> listBacklogItemsBy(BacklogItemFilter filter, int pageNumber) {
		return backlogDao.findBacklogItems(filter.toString(), from(pageNumber), to(pageNumber));
	}

	public List<BacklogItem> listBacklogItemsBy(String simpleSearch, int pageNumber) {
		return backlogDao.findBacklogItems(simpleSearch, from(pageNumber), to(pageNumber));
	}

	public void addSprint(Sprint sprint) {
		sprintDao.save(sprint);
	}

	public void completeSprint(Long sprintId) {
		Sprint sprint = sprintDao.getSprint(sprintId);
		sprint.setStatus(Sprint.SprintStatus.COMPLETED);
		sprintDao.save(sprint);
	}

	public void addItemToSprint(BacklogItem item, Long sprintId) {
		Sprint sprint = sprintDao.getSprint(sprintId);
		item.setSprint(sprint);
		sprint.addItem(item);
		backlogDao.save(item);
		sprintDao.save(sprint);
	}

	public List<Sprint> listSprints(int pageNumber) {
		return sprintDao.findSprints(null, from(pageNumber), to(pageNumber));
	}

	public List<Sprint> listSprintsBy(SprintFilter filter, int pageNumber) {
		return sprintDao.findSprints(filter.toString(), from(pageNumber), to(pageNumber));
	}

	public List<Sprint> listSprintsBy(String simpleSearch, int pageNumber) {
		return sprintDao.findSprints(simpleSearch, from(pageNumber), to(pageNumber));
	}

	public void addTask(Task task, Long backlogItemId) {
		BacklogItem item = backlogDao.getItem(backlogItemId);
		item.addTask(task);
		task.setParent(item);
		backlogDao.save(item);
		taskDao.save(task);
	}

	public void removeTask(Long taskId) {
		Task task = taskDao.getTask(taskId);
		Long backlogItemId = task.getParent().getId();
		BacklogItem backlogItem = backlogDao.getItem(backlogItemId);
		backlogItem.removeTask(task);
		backlogDao.save(backlogItem);
		taskDao.delete(taskId);
	}

	public void updateTask(Task task) {
		taskDao.save(task);
	}

	public List<Task> getTasksByBacklogItem(Long backlogItemId) {
		return taskDao.findTasksBy("parent.id", backlogItemId);
	}

	public List<Task> getTasksBySprint(Long sprintId) {
		return taskDao.findTasksBy("parent.sprint.id", sprintId);
	}

	public List<Task> getTasksByUser(String userId) {
		return taskDao.findTasksBy("assignedTo.id", userId);
	}

	public void addCommentToTask(Long taskId, Comment comment) {
		Task task = taskDao.getTask(taskId);
		task.addComment(comment);
		taskDao.save(task);
		commentDao.save(comment);
	}

	public void addCommentToSprint(Long sprintId, Comment comment) {
		Sprint sprint = sprintDao.getSprint(sprintId);
		sprint.addComment(comment);
		sprintDao.save(sprint);
		commentDao.save(comment);
	}

	public void addCommentToBacklogItem(Long backlogItemId, Comment comment) {
		BacklogItem item = backlogDao.getItem(backlogItemId);
		item.addComment(comment);
		backlogDao.save(item);
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
		BacklogItem item = backlogDao.getItem(backlogItemId);
		fileRefDao.save(file, content);
		item.addAttachment(file);
		backlogDao.save(item);
	}

	public void attachFileToSprint(Long sprintId, FileRef file, byte[] content) {
		Sprint sprint = sprintDao.getSprint(sprintId);
		fileRefDao.save(file, content);
		sprint.addAttachment(file);
		sprintDao.save(sprint);
	}

	public void attachFileToTask(Long taskId, FileRef file, byte[] content) {
		Task task = taskDao.getTask(taskId);
		fileRefDao.save(file, content);
		task.addAttachment(file);
		taskDao.save(task);
	}

	public FileRef getAttachment(Long fileRefId) {
		return fileRefDao.getAttachment(fileRefId);
	}
	
	public byte[] getAttachmentContent(Long fileRefId) {
		return fileRefDao.getContent(fileRefId);
	}
	
	public List<Object> query(String query, String collection) {
		return backlogDao.query(query, collection);
	}
	
	public ProjectDAO getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public SprintDAO getSprintDao() {
		return sprintDao;
	}

	public void setSprintDao(SprintDAO sprintDao) {
		this.sprintDao = sprintDao;
	}

	public BacklogDAO getBacklogDao() {
		return backlogDao;
	}

	public void setBacklogDao(BacklogDAO backlogDao) {
		this.backlogDao = backlogDao;
	}

	public TaskDAO getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDAO taskDao) {
		this.taskDao = taskDao;
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
