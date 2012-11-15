package org.opslog.dao.mongo;

import java.util.List;

import org.opslog.dao.SprintDAO;
import org.opslog.model.Sprint;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

public class SprintMongoDAO extends MongoTemplate implements SprintDAO {

	public SprintMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	public void save(Sprint sprint) {
		save(getConverter().convertToMongoType(sprint), "sprints");
	}
	
	public Sprint getSprint(Long sprintId) {
		return findById(sprintId, Sprint.class, "sprints");
	}

	public List<Sprint> findSprints(String filter, int from, int to) {
		Query query = new Query();
		query.skip(from).limit(to - from);
		if (filter != null) {
			query.withHint(filter);
		}
		return find(query, Sprint.class, "sprints");
	}
}
