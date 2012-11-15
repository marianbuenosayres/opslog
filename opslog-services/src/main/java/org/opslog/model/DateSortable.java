package org.opslog.model;

import java.util.Date;

public abstract class DateSortable {

	private Date sendDate;
	
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public Date getSendDate() {
		return sendDate;
	}
}
