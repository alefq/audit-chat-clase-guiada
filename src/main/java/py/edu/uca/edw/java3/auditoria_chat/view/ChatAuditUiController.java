package py.edu.uca.edw.java3.auditoria_chat.view;

import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractPageBean;

import py.edu.uca.edw.java3.auditoria_chat.business.UsuarioBC;
import py.edu.uca.edw.java3.auditoria_chat.domain.Usuario;

@ViewController
public class ChatAuditUiController extends AbstractPageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1379079707680349176L;
	@Inject
	private UsuarioBC usuarioBC;

	public Boolean hasRole(String rol) {
		Boolean ret = false;
		Usuario user = new Usuario();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && facesContext.getExternalContext() != null) {
			HttpServletRequest request = (HttpServletRequest) facesContext
					.getExternalContext().getRequest();
			/* Se obtiene el usuario autenticado */
			Principal principalUser = request.getUserPrincipal();
			user.setUsername(principalUser.getName());
			/* Consulto si está en la lista de roles del usuario */
			ret = usuarioBC.esAdministrador(user, rol);
		}
		return ret;

	}
}
