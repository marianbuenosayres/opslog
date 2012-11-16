package org.opslog.dao.jpa;

public class Entry {

	private final String key;
	private final Object value;
	
	public Entry(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}
}
