package py.edu.uca.edw.java3.auditoria_chat.business;

import java.util.List;

import javax.inject.Inject;

import org.ticpy.tekoporu.template.DelegateCrud;

import py.edu.uca.edw.java3.auditoria_chat.domain.Rol;
import py.edu.uca.edw.java3.auditoria_chat.persistence.RolDAO;


public class RolBC extends DelegateCrud<Rol, Long, RolDAO> {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RolDAO rolDAO;
	
	public List<Rol> findPage(int pageSize, int first, String sortField, boolean sortOrderAsc){
		return rolDAO.findPage(pageSize,first, sortField, sortOrderAsc);
	}
	
	public int count() {
		return rolDAO.count();
	}
	
}
