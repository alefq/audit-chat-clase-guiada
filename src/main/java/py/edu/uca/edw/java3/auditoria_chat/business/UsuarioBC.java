package py.edu.uca.edw.java3.auditoria_chat.business;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.ticpy.tekoporu.annotation.Startup;
import org.ticpy.tekoporu.template.DelegateCrud;
import org.ticpy.tekoporu.transaction.Transactional;

import py.edu.uca.edw.java3.auditoria_chat.domain.Rol;
import py.edu.uca.edw.java3.auditoria_chat.domain.Usuario;
import py.edu.uca.edw.java3.auditoria_chat.persistence.RolDAO;
import py.edu.uca.edw.java3.auditoria_chat.persistence.UsuarioDAO;
import py.edu.uca.edw.java3.auditoria_chat.util.HashUtils;

public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private RolBC rolBC;

	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {

			// insert default roles
			if (rolBC.findAll().isEmpty()) {
				rolBC.insert(new Rol("admin"));
				rolBC.insert(new Rol("user"));
			}

			List<Rol> list = rolBC.findAll();
			Rol rol1 = list.get(0);
			Rol rol2 = list.get(1);

			insert(new Usuario("S", "Administrator", "admin@admin.com",
					"Administrator", HashUtils.md5("admin"), rol1, "5441345",
					"admin"));
			insert(new Usuario("S", "User", "user@user.com", "User",
					HashUtils.md5("user"), rol2, "787454", "user"));
			System.out.println(usuarioDAO.findAll().get(0).getUsername() + " "
					+ usuarioDAO.findAll().get(0).getRol().getRolId() + " "
					+ usuarioDAO.findAll().get(0).getRol().getDescripcion());
		}

	}

	public List<Usuario> listarUsuarios() {
		return usuarioDAO.find();
	}

	public List<Usuario> findPage(int pageSize, int first, String sortField,
			boolean sortOrderAsc) {
		return usuarioDAO.findPage(pageSize, first, sortField, sortOrderAsc);
	}

	public int count() {
		return usuarioDAO.count();
	}

	public Boolean esAdministrador(Usuario user, String rol2) {
		List<Rol> roles = rolBC.listRolesByUser(user);
		Boolean ret = false;
		if (StringUtils.isNotBlank(rol2)) {
			for (Rol rol : roles) {
				if (rol2.equals(rol.getDescripcion())) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}

}
