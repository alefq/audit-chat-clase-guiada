package py.edu.uca.edw.java3.auditoria_chat.business;

import org.ticpy.tekoporu.stereotype.BusinessController;
import org.ticpy.tekoporu.template.DelegateCrud;

import py.edu.uca.edw.java3.auditoria_chat.domain.ChatAudit;
import py.edu.uca.edw.java3.auditoria_chat.persistence.ChatAuditDAO;


@BusinessController
public class ChatAuditBC extends DelegateCrud<ChatAudit, Long, ChatAuditDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
