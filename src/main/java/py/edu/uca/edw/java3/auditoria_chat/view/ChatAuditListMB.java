package py.edu.uca.edw.java3.auditoria_chat.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.ticpy.tekoporu.annotation.NextView;
import org.ticpy.tekoporu.annotation.PreviousView;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractListPageBean;
import org.ticpy.tekoporu.transaction.Transactional;

import py.edu.uca.edw.java3.auditoria_chat.business.ChatAuditBC;
import py.edu.uca.edw.java3.auditoria_chat.domain.ChatAudit;


@ViewController
@NextView("/chatAudit_edit.xhtml")
@PreviousView("/chatAudit_list.xhtml")
public class ChatAuditListMB extends AbstractListPageBean<ChatAudit, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ChatAuditBC chatAuditBC;
	
	@Override
	protected List<ChatAudit> handleResultList() {
		return this.chatAuditBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				chatAuditBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}