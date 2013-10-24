package py.edu.uca.edw.java3.auditoria_chat.business;

import java.util.List;

import org.ticpy.tekoporu.stereotype.BusinessController;
import org.ticpy.tekoporu.template.DelegateCrud;

import py.edu.uca.edw.java3.auditoria_chat.domain.Bookmark;
import py.edu.uca.edw.java3.auditoria_chat.domain.ChatAudit;
import py.edu.uca.edw.java3.auditoria_chat.persistence.ChatAuditDAO;

@BusinessController
public class ChatAuditBC extends DelegateCrud<ChatAudit, Long, ChatAuditDAO> {

	private static final long serialVersionUID = 1L;

	public List<ChatAudit> findPage(int pageSize, int first, String sortField,
			boolean sortOrderAsc) {
		return getDelegate().findPage(pageSize, first, sortField, sortOrderAsc);
	}

	public int count() {
		return getDelegate().count();
	}

	public List<ChatAudit> findByHibernateExample(ChatAudit chatAudit) {
		return getDelegate().findByHibernateExample(chatAudit);
	}

	public Double getIva(Double precio) {
		Double ret = new Double(0);
		if (precio != null)
			ret = getDelegate().calcularIva(precio);
		return ret;
	}

}
