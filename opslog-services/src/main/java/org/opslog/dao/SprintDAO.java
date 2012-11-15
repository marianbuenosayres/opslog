package org.opslog.dao;

import java.util.List;

import org.opslog.model.Sprint;

public interface SprintDAO {

	void save(Sprint sprint);
	Sprint getSprint(Long sprintId);
	List<Sprint> findSprints(String filter, int from, int to);

}
