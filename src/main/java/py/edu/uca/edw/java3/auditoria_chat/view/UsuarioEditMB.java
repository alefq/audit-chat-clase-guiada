package py.edu.uca.edw.java3.auditoria_chat.view;

import java.util.List;

import javax.inject.Inject;

import org.ticpy.tekoporu.annotation.PreviousView;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractEditPageBean;
import org.ticpy.tekoporu.transaction.Transactional;

import py.edu.uca.edw.java3.auditoria_chat.business.RolBC;
import py.edu.uca.edw.java3.auditoria_chat.business.UsuarioBC;
import py.edu.uca.edw.java3.auditoria_chat.domain.Rol;
import py.edu.uca.edw.java3.auditoria_chat.domain.Usuario;
import py.edu.uca.edw.java3.auditoria_chat.util.HashUtils;


@ViewController
@PreviousView("/admin/usuario_list.xhtml")
public class UsuarioEditMB extends AbstractEditPageBean<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject 
	private RolBC rolBC;

	protected List<Rol> getTiposRoles(){
		return rolBC.findAll();
	}

	@Override
	@Transactional
	public String delete() {
		this.usuarioBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		Usuario usuario = getBean();
		usuario.setPwd(HashUtils.md5(usuario.getPwd()));
		this.usuarioBC.insert(usuario);
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.usuarioBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.usuarioBC.load(getId()));
	}
	
}
