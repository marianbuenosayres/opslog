package org.opslog.dao.mongo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opslog.dao.ProjectDAO;
import org.opslog.model.BacklogItem;
import org.opslog.model.Project;
import org.opslog.model.Sprint;
import org.opslog.model.Sprint.SprintStatus;
import org.opslog.model.Task;
import org.opslog.services.BacklogItemFilter;
import org.opslog.services.SprintFilter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

public class ProjectMongoDAO extends MongoTemplate implements ProjectDAO {

	private String collectionName;
	
	public ProjectMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	public void save(Project project) {
		save(getConverter().convertToMongoType(project), getCollectionName());
	}

	public void delete(Long projectId) {
		remove(findById(projectId, Project.class), getCollectionName());
	}
	
	public Project getProject(Long projectId) {
		return findById(projectId, Project.class, getCollectionName());
	}

	public BacklogItem getBacklogItem(Long backlogItemId) {
		return findById(backlogItemId, BacklogItem.class, getCollectionName());
	}

	public void save(Sprint sprint) {
		save(getConverter().convertToMongoType(sprint), getCollectionName());
	}
	
	public Sprint getSprint(Long sprintId) {
		return findById(sprintId, Sprint.class, getCollectionName());
	}

	public List<Sprint> findSprints(SprintFilter filter, int from, int to) {
		Query query = new Query();
		if (filter != null) {
			if (filter.getDateFrom() != null) {
				query.addCriteria(Criteria.where("startDate").gte(filter.getDateFrom()));
			}
			if (filter.getDateTo() != null) {
				query.addCriteria(Criteria.where("endDate").lte(filter.getDateTo()));
			}
			if (filter.getDescriptionRegex() != null) {
				query.addCriteria(Criteria.where("description").regex(filter.getDescriptionRegex()));
			}
			if (filter.getNameRegex() != null) {
				query.addCriteria(Criteria.where("name").regex(filter.getNameRegex()));
			}
			if (filter.getProjectNameRegex() != null) {
				query.addCriteria(Criteria.where("project.name").regex(filter.getProjectNameRegex()));
			}
			if (filter.getSearchForStatus() != null & !filter.getSearchForStatus().isEmpty()) {
				List<SprintStatus> statusFilter = new ArrayList<SprintStatus>(filter.getSearchForStatus().size());
				for (String statusName : filter.getSearchForStatus()) {
					statusFilter.add(SprintStatus.valueOf(statusName));
				}
				query.addCriteria(Criteria.where("status").in(statusFilter));
			}
		}
		query.skip(from).limit(to - from);
		return find(query, Sprint.class, getCollectionName());
	}
	
	public List<Sprint> findSprints(String simpleSearch, int from, int to) {
		Query query = new Query();
		if (simpleSearch != null && !"".equals(simpleSearch)) {
			query.withHint(simpleSearch);
		}
		query.skip(from).limit(to - from);
		return find(query, Sprint.class, getCollectionName());
	}

	public List<BacklogItem> findBacklogItems(BacklogItemFilter filter, int from, int to) {
		Query query = new Query();
		if (filter != null) {
			if (filter.getAssignedToUser() != null) {
				query.addCriteria(Criteria.where("assingedTo.id").is(filter.getAssignedToUser()));
			}
			if (filter.getCompleted() != null) {
				if (filter.getCompleted()) {
					query.addCriteria(Criteria.where("percentComplete").gte(100.0));
				} else {
					query.addCriteria(Criteria.where("percentComplete").lt(100.0));
				}
			}
			if (filter.getDescriptionRegex() != null) {
				query.addCriteria(Criteria.where("description").regex(filter.getDescriptionRegex()));
			}
			if (filter.getNameRegex() != null) {
				query.addCriteria(Criteria.where("name").regex(filter.getNameRegex()));
			}
			if (filter.getPercentCompleteFrom() != null) {
				query.addCriteria(Criteria.where("percentComplete").gte(filter.getPercentCompleteFrom()));
			}
			if (filter.getPercentCompleteTo() != null) {
				query.addCriteria(Criteria.where("percentComplete").lte(filter.getPercentCompleteTo()));
			}
			if (filter.getPriorityEqual() != null) {
				query.addCriteria(Criteria.where("priority").is(filter.getPriorityEqual()));
			}
			if (filter.getPriorityFrom() != null) {
				query.addCriteria(Criteria.where("priority").gte(filter.getPriorityFrom()));
			}
			if (filter.getPriorityTo() != null) {
				query.addCriteria(Criteria.where("priority").lte(filter.getPriorityTo()));
			}
			if (filter.getProjectNameRegex() != null) {
				query.addCriteria(Criteria.where("project.name").regex(filter.getProjectNameRegex()));
			}
		}
		query.skip(from).limit(to - from);
		return find(query, BacklogItem.class, getCollectionName());
	}
	
	public List<BacklogItem> findBacklogItems(String simpleSearch, int from, int to) {
		Query query = new Query();
		if (simpleSearch != null && !"".equals(simpleSearch)) {
			query.withHint(simpleSearch);
		}
		query.skip(from).limit(to - from);
		return find(query, BacklogItem.class, getCollectionName());
	}
	
	public void save(BacklogItem item) {
		save(getConverter().convertToMongoType(item), getCollectionName());
	}

	public Task getTask(Long taskId) {
		return findById(taskId, Task.class, getCollectionName());
	}

	public void save(Task task) {
		save(getConverter().convertToMongoType(task), getCollectionName());
	}
	
	public List<Task> findTasksBy(String key, Object value) {
		Query query = Query.query(Criteria.where(key).is(value));
		return find(query, Task.class, getCollectionName());
	}
	
	/**
	 * Query format: "AS <CLASS_NAME>; MAP <MAP_FUNCTION>; REDUCE <REDUCE_FUNCTION>;", Case sensitive
	 */
	public List<Object> query(String query, String collectionName) {
		int asIndexBegin = query.indexOf("AS ") +  "AS ".length();
		int asIndexEnd = query.indexOf(";", asIndexBegin);
		String as = query.substring(asIndexBegin, asIndexEnd);
		int mapIndexBegin = query.indexOf("MAP ", asIndexEnd) + "MAP ".length();
		int mapIndexEnd = query.indexOf(";", mapIndexBegin);
		String mapFunction = query.substring(mapIndexBegin, mapIndexEnd);
		int reduceIndexBegin = query.indexOf("REDUCE ", mapIndexEnd) + "REDUCE ".length();
		int reduceIndexEnd = query.indexOf(";", reduceIndexBegin);
		String reduceFunction = query.substring(reduceIndexBegin, reduceIndexEnd);
		try {
			MapReduceResults<?> items = mapReduce(collectionName, mapFunction, reduceFunction, Class.forName(as));
			return asList(items.iterator());
		} catch (ClassNotFoundException e) {
			//TODO
			e.printStackTrace();
		}
		return null;
	}

	private List<Object> asList(Iterator<?> iter) {
		List<Object> retval = new ArrayList<Object>();
		while (iter.hasNext()) {
			retval.add(iter);
		}
		return retval;
	}
	
	public String getCollectionName() {
		return collectionName;
	}
	
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
