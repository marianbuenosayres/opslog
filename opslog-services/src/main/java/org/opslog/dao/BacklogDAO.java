package org.opslog.dao;

import java.util.List;

import org.opslog.model.BacklogItem;

public interface BacklogDAO {

	void save(BacklogItem item);
	void delete(Long backlogItemId);
	List<BacklogItem> findBacklogItems(String filter, int from, int to);
	BacklogItem getItem(Long backlogItemId);
	List<Object> query(String query, String collection);
}
