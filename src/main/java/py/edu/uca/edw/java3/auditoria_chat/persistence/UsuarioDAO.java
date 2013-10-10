package py.edu.uca.edw.java3.auditoria_chat.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.ticpy.tekoporu.template.JPACrud;

import py.edu.uca.edw.java3.auditoria_chat.domain.Usuario;


public class UsuarioDAO extends JPACrud<Usuario, Long> {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	@SuppressWarnings("unused")
	private Logger logger;
	
	@SuppressWarnings("unchecked")
	public List<Usuario> find() {
		
		Query q=  em.createQuery("select new Usuario(u.usuarioId, u.username,u.nombre, u.apellido, u.telefono, u.email )" +
				" from org.ticpy.ruvig.domain.Usuario u");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findPage(int pageSize, int first, String sortField, boolean sortOrderAsc) {
		String order = "asc";
		if (!sortOrderAsc) order="desc";
		
		Query q = em.createQuery("select new Usuario(u.usuarioId, u.username,u.nombre, u.apellido, u.telefono, u.email )" +
				" from Usuario u order by "+sortField +" "+order);
		q.setFirstResult(first);
		q.setMaxResults(pageSize);
		
		return (List<Usuario>) q.getResultList();

	}
	
	public int count() {
		Query q = em.createQuery("select count(*) from Usuario u");
		return ((Long) q.getSingleResult()).intValue();
		
	}
	
}
