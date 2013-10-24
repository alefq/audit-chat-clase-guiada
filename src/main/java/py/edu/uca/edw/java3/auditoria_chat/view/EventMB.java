package py.edu.uca.edw.java3.auditoria_chat.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.ticpy.tekoporu.annotation.Name;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractListPageBean;

import py.edu.uca.edw.java3.auditoria_chat.business.EventBC;
import py.edu.uca.edw.java3.auditoria_chat.domain.ChatAudit;

@ViewController
@Name(value = "eventMB")
public class EventMB extends AbstractListPageBean<ChatAudit, Long> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5835191527760414088L;

	@Inject
	private EventBC eventBC;


	public String setTimer() {
		eventBC.setTimer();
		return null;
	}

	public Date getLastAutomaticTimeout() {
		return eventBC.getLastAutomaticTimeout(); 
	}

	

	@Override
	protected List<ChatAudit> handleResultList() {
		// TODO Auto-generated method stub
		return null;
	}

}
