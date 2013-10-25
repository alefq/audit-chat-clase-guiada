package py.edu.uca.edw.java3.auditoria_chat.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.ticpy.tekoporu.template.JPACrud;

import py.edu.uca.edw.java3.auditoria_chat.domain.Rol;
import py.edu.uca.edw.java3.auditoria_chat.domain.Usuario;

public class RolDAO extends JPACrud<Rol, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	@SuppressWarnings("unused")
	private Logger logger;

	@Inject
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Rol> findPage(int pageSize, int first, String sortField,
			boolean sortOrderAsc) {

		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Rol.class);

		// add order by
		Order order = Order.asc(sortField);
		if (!sortOrderAsc)
			order = Order.desc(sortField);
		criteria.addOrder(order);

		// add limit, offset
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		return criteria.list();

	}

	public int count() {
		Query q = em.createQuery("select count(*) from Rol r");
		return ((Long) q.getSingleResult()).intValue();

	}

	public List<Rol> listRolesByUser(Usuario userFromLogin) {
		/*
		 * El modelo de datos permite sólo un rol por usuario. En caso de que se
		 * tenga un modelo que permita múltiples roles. Para ver una aplicación
		 * con ejemplo de múltiples roles ver:
		 * https://github.com/alefq/asistente-eventos
		 */
		List<Rol> roles = new ArrayList<Rol>();
		Query q = em
				.createQuery("select r from Rol r, Usuario u join u.rol where u.rol.rolId = r.rolId and u.username = :username");
		q.setParameter("username", userFromLogin.getUsername());
		roles = q.getResultList();
		return roles;
	}
}
