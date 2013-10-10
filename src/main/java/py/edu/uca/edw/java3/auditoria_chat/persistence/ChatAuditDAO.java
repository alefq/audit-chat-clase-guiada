package py.edu.uca.edw.java3.auditoria_chat.persistence;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.ticpy.tekoporu.stereotype.PersistenceController;
import org.ticpy.tekoporu.template.JPACrud;

import py.edu.uca.edw.java3.auditoria_chat.domain.ChatAudit;


@PersistenceController
public class ChatAuditDAO extends JPACrud<ChatAudit, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@SuppressWarnings("unused")
	private Logger logger;

}
