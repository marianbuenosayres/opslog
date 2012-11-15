package org.opslog.dao;

import org.opslog.model.FileRef;

public interface FileRefDAO {

	void save(FileRef file, byte[] content);
	FileRef getAttachment(Long fileRefId);
	byte[] getContent(Long fileRefId);
}
