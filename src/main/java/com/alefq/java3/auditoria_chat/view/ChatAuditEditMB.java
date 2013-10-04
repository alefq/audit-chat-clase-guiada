package com.alefq.java3.auditoria_chat.view;

import javax.inject.Inject;

import org.ticpy.tekoporu.annotation.PreviousView;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractEditPageBean;
import org.ticpy.tekoporu.transaction.Transactional;

import com.alefq.java3.auditoria_chat.business.ChatAuditBC;
import com.alefq.java3.auditoria_chat.domain.ChatAudit;

@ViewController
@PreviousView("/chatAudit_list.xhtml")
public class ChatAuditEditMB extends AbstractEditPageBean<ChatAudit, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ChatAuditBC chatAuditBC;
	
	@Override
	@Transactional
	public String delete() {
		this.chatAuditBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.chatAuditBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.chatAuditBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.chatAuditBC.load(getId()));
	}

}