package org.opslog.dao.mongo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.opslog.dao.FileRefDAO;
import org.opslog.model.FileRef;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

public class FileRefMongoDAO extends MongoTemplate implements FileRefDAO {
	
	private String filePrefix;
	
	public FileRefMongoDAO(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}

	public void save(FileRef file, byte[] content) {
		save(getConverter().convertToMongoType(file), "attachments");
		saveFile(file.getId(), content);
	}

	public FileRef getAttachment(Long fileRefId) {
		return findById(fileRefId, FileRef.class, "attachments");
	}
	
	public byte[] getContent(Long fileRefId) {
		DataInputStream in = null;
		try {
			File file = new File(getFileName(fileRefId));
			in = new DataInputStream(new FileInputStream(file));
			byte[] retval = new byte[(int) file.length()];
			in.readFully(retval);
			return retval;
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) { //TODO
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private void saveFile(Long fileId, byte[] content) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(getFileName(fileId)));
			out.write(content);
			out.flush();
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) { //TODO
					e.printStackTrace();
				}
			}
		}
	}

	private String getFileName(Long fileId) {
		return filePrefix + String.valueOf(fileId) + ".tmp";
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}
	
	public String getFilePrefix() {
		return filePrefix;
	}
}
