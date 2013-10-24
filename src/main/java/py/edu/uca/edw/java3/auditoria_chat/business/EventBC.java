package py.edu.uca.edw.java3.auditoria_chat.business;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.ScheduleExpression;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.ticpy.tekoporu.annotation.Name;
import org.ticpy.tekoporu.stereotype.BusinessController;

import py.edu.uca.edw.java3.auditoria_chat.event.Scheduler;
import py.edu.uca.edw.java3.auditoria_chat.event.TestEvent;

@BusinessController
@Name(value = "eventMB")
public class EventBC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2949645766787859209L;

	@Inject
	private Scheduler scheduler;

	@Inject
	private Logger logger;

	public String setTimer() {

		/* Iniciamos una tarea que correrá cada 5 segundos */
		final ScheduleExpression schedule = new ScheduleExpression().hour("*")
				.minute("*").second("*/5");
		TestEvent evento = new TestEvent("five");
		/* Se disparará un evento con el texto "five" */
		scheduler.scheduleEvent(schedule, evento);
		return null;
	}

	public Date getLastAutomaticTimeout() {
		return scheduler.getLastAutomaticTimeout();
	}

}
