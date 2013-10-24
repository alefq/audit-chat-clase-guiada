package py.edu.uca.edw.java3.auditoria_chat.event;

import java.util.Date;

public class TestEvent {

	private final String message;
	
	private Date lastAutomaticTimeout; 

	public TestEvent(String message) {
		setLastAutomaticTimeout(new Date());
		this.message = message;
	}

	public String getMessage() {

		return message;
	}

	public Date getLastAutomaticTimeout() {
		return lastAutomaticTimeout;
	}

	public void setLastAutomaticTimeout(Date lastAutomaticTimeout) {
		this.lastAutomaticTimeout = lastAutomaticTimeout;
	}
}
