package org.opslog.dao.mongo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opslog.dao.BacklogDAO;
import org.opslog.model.BacklogItem;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

public class BacklogMongoDAO extends MongoTemplate implements BacklogDAO {

	public BacklogMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	public void save(BacklogItem item) {
		save(getConverter().convertToMongoType(item), "backlog");
	}

	public void delete(Long backlogItemId) {
		remove(findById(backlogItemId, BacklogItem.class, "backlog"), "backlog");
	}
	
	public List<BacklogItem> findBacklogItems(String filter, int from, int to) {
		Query query = new Query();
		query.skip(from).limit(to - from);
		if (filter != null) {
			query.withHint(filter);
		}
		return find(query, BacklogItem.class, "backlog");
	}
	
	public BacklogItem getItem(Long backlogItemId) {
		return findById(backlogItemId, BacklogItem.class, "backlog");
	}

	/**
	 * Query format: "AS <CLASS_NAME>; MAP <MAP_FUNCTION>; REDUCE <REDUCE_FUNCTION>;", Case sensitive
	 */
	public List<Object> query(String query, String collection) {
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
			MapReduceResults<?> items = mapReduce(collection, mapFunction, reduceFunction, Class.forName(as));
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
}