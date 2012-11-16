package org.opslog.dao.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.opslog.dao.ProjectDAO;
import org.opslog.model.BacklogItem;
import org.opslog.model.Project;
import org.opslog.model.Sprint;
import org.opslog.model.Sprint.SprintStatus;
import org.opslog.model.Task;
import org.opslog.services.BacklogItemFilter;
import org.opslog.services.SprintFilter;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ProjectJpaDAO extends HibernateTemplate implements ProjectDAO {
	
	public void save(Project project) {
		super.save(project);
	}

	public void delete(Long projectId) {
		super.delete(getProject(projectId));
	}
	
	public Project getProject(Long projectId) {
		return get(Project.class, projectId);
	}

	public BacklogItem getBacklogItem(Long backlogItemId) {
		return get(BacklogItem.class, backlogItemId);
	}

	public void save(Sprint sprint) {
		super.save(sprint);
	}
	
	public Sprint getSprint(Long sprintId) {
		return get(Sprint.class, sprintId);
	}

	public Iterator<Sprint> findSprints(final SprintFilter filter, final int from, final int to) {
		@SuppressWarnings("unchecked")
		Iterator<Sprint> retval = execute(new HibernateCallback<Iterator<Sprint>>() {
			public Iterator<Sprint> doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select s from org.opslog.model.Sprint s where 1=1";
				List<Entry> entries = new ArrayList<Entry>();
				if (filter != null) {
					if (filter.getDateFrom() != null) {
						hql += " and s.startDate >= :startDate";
						entries.add(new Entry("startDate", filter.getDateFrom()));
					}
					if (filter.getDateTo() != null) {
						hql += " and s.endDate <= :endDate";
						entries.add(new Entry("endDate", filter.getDateTo()));
					}
					if (filter.getDescriptionRegex() != null) {
						hql += " and s.description like :description";
						entries.add(new Entry("description", "%" + filter.getDescriptionRegex() + "%"));
					}
					if (filter.getNameRegex() != null) {
						hql += " and s.name like :name";
						entries.add(new Entry("name", "%" + filter.getNameRegex() + "%"));
					}
					if (filter.getProjectNameRegex() != null) {
						hql += " and s.project.name like :projectName";
						entries.add(new Entry("projectName", "%" + filter.getProjectNameRegex() + "%"));
					}
					if (filter.getSearchForStatus() != null & !filter.getSearchForStatus().isEmpty()) {
						hql += " and s.status in (:statuses)";
						List<SprintStatus> statusFilter = new ArrayList<SprintStatus>(filter.getSearchForStatus().size());
						for (String statusName : filter.getSearchForStatus()) {
							statusFilter.add(SprintStatus.valueOf(statusName));
						}
						entries.add(new Entry("statuses", statusFilter));
					}
				}
				Query query = session.createQuery(hql);
				for (Entry entry : entries) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
				query.setFirstResult(from);
				query.setMaxResults(to - from);
				return query.iterate();
			}
		});
		return retval;
	}
	
	public Iterator<Sprint> findSprints(final String simpleSearch, final int from, final int to) {
		@SuppressWarnings("unchecked")
		Iterator<Sprint> retval = execute(new HibernateCallback<Iterator<Sprint>>() {
			public Iterator<Sprint> doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select s from org.opslog.model.Sprint s where 1=1";
				List<Entry> entries = new ArrayList<Entry>();
				if (simpleSearch != null && !"".equals(simpleSearch)) {
					hql += " and (s.description like :simpleSearch or s.name like :simpleSearch or s.project.name like :simpleSearch)";
					entries.add(new Entry("simpleSearch", "%" + simpleSearch + "%"));
				}
				Query query = session.createQuery(hql);
				for (Entry entry : entries) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
				query.setFirstResult(from);
				query.setMaxResults(to - from);
				return query.iterate();
			}
		});
		return retval;
	}

	public Iterator<BacklogItem> findBacklogItems(final BacklogItemFilter filter, final int from, final int to) {
		@SuppressWarnings("unchecked")
		Iterator<BacklogItem> retval = execute(new HibernateCallback<Iterator<BacklogItem>>() {
			public Iterator<BacklogItem> doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select b from org.opslog.model.BacklogItem b where 1=1";
				List<Entry> entries = new ArrayList<Entry>();
				if (filter != null) {
					if (filter.getAssignedToUser() != null) {
						hql += " and b.assingedTo.id = :userId";
						entries.add(new Entry("userId", filter.getAssignedToUser()));
					}
					if (filter.getCompleted() != null) {
						if (filter.getCompleted()) {
							hql += " and b.percentComplete >= 100.0";
						} else {
							hql += " and b.percentComplete < 100.0";
						}
					}
					if (filter.getDescriptionRegex() != null) {
						hql += " and b.description like :description";
						entries.add(new Entry("description", "%" + filter.getDescriptionRegex() + "%"));
					}
					if (filter.getNameRegex() != null) {
						hql += " and b.name like :name";
						entries.add(new Entry("name", "%" + filter.getNameRegex() + "%"));
					}
					if (filter.getPercentCompleteFrom() != null) {
						hql += " and b.percentCompete >= :percentCompleteFrom";
						entries.add(new Entry("percentCompleteFrom", filter.getPercentCompleteFrom()));
					}
					if (filter.getPercentCompleteTo() != null) {
						hql += " and b.percentCompete <= :percentCompleteTo";
						entries.add(new Entry("percentCompleteTo", filter.getPercentCompleteTo()));
					}
					if (filter.getPriorityEqual() != null) {
						hql += " and b.priority = :priorityEqual";
						entries.add(new Entry("priorityEqual", filter.getPriorityEqual()));
					}
					if (filter.getPriorityFrom() != null) {
						hql += " and b.priority >= :priorityFrom";
						entries.add(new Entry("priorityFrom", filter.getPriorityFrom()));
					}
					if (filter.getPriorityTo() != null) {
						hql += " and b.priority <= :priorityTo";
						entries.add(new Entry("priorityTo", filter.getPriorityTo()));
					}
					if (filter.getProjectNameRegex() != null) {
						hql += " and b.project.name like :projectName";
						entries.add(new Entry("projectName", "%" + filter.getProjectNameRegex() + "%"));
					}
				}
				Query query = session.createQuery(hql);
				for (Entry entry : entries) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
				query.setFirstResult(from);
				query.setMaxResults(to - from);
				return query.iterate();
			}
		});
		return retval;
	}
	
	public Iterator<BacklogItem> findBacklogItems(final String simpleSearch, final int from, final int to) {
		@SuppressWarnings("unchecked")
		Iterator<BacklogItem> retval = execute(new HibernateCallback<Iterator<BacklogItem>>() {
			public Iterator<BacklogItem> doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select b from org.opslog.model.BacklogItem b where 1=1";
				List<Entry> entries = new ArrayList<Entry>();
				if (simpleSearch != null && !"".equals(simpleSearch)) {
					hql += " and (b.assingedTo.id like :ss or b.description like :ss or b.name like :ss or b.project.name like :ss)";
					entries.add(new Entry("ss", simpleSearch));
				}
				Query query = session.createQuery(hql);
				for (Entry entry : entries) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
				query.setFirstResult(from);
				query.setMaxResults(to - from);
				return query.iterate();
			}
		});
		return retval;
	}
	
	public void save(BacklogItem item) {
		save(item);
	}

	public Task getTask(Long taskId) {
		return get(Task.class, taskId);
	}

	public void save(Task task) {
		save(task);
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Task> findTasksBy(String key, Object value) {
		String hql = "select t from org.opslog.model.Task t where t." + key + " = ?";
		return (Iterator<Task>) iterate(hql, value);
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Object> query(String query, String collectionName) {
		return iterate(query);
	}
}
