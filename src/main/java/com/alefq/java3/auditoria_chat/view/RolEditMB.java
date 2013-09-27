package com.alefq.java3.auditoria_chat.view;

import javax.inject.Inject;

import com.alefq.java3.auditoria_chat.business.RolBC;
import com.alefq.java3.auditoria_chat.domain.Rol;

import org.ticpy.tekoporu.annotation.PreviousView;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractEditPageBean;
import org.ticpy.tekoporu.transaction.Transactional;

@ViewController
@PreviousView("/admin/rol_list.xhtml")
public class RolEditMB extends AbstractEditPageBean<Rol, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private RolBC rolBC;
	

	@Override
	@Transactional
	public String delete() {
		this.rolBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.rolBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.rolBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.rolBC.load(getId()));
	}

}

