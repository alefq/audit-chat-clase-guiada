package py.edu.uca.edw.java3.auditoria_chat.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.ticpy.tekoporu.stereotype.PersistenceController;
import org.ticpy.tekoporu.template.JPACrud;

import py.edu.uca.edw.java3.auditoria_chat.domain.ChatAudit;

@PersistenceController
public class ChatAuditDAO extends JPACrud<ChatAudit, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	@SuppressWarnings("unused")
	private Logger logger;

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<ChatAudit> findPage(int pageSize, int first, String sortField,
			boolean sortOrderAsc) {
		/* Este método es dependiente del proveedor JPA: Hibernate */
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(ChatAudit.class);

		/* Se agrega el crieterio para el ordenamiento */
		Order order = Order.asc(sortField);
		if (!sortOrderAsc)
			order = Order.desc(sortField);
		criteria.addOrder(order);

		/* Agregamos la cantidad de resultados y el offset para la consulta */
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		/* Finalmente se hace la consulta por medio de Hibernate */
		return criteria.list();
	}

	public int count() {
		Query q = entityManager.createQuery("select count(*) from ChatAudit c");
		return ((Long) q.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<ChatAudit> findByHibernateExample(ChatAudit chatAudit) {
		List<ChatAudit> list = null;
		Session session = (Session) getEntityManager().getDelegate();
		Example example = null;
		example = Example.create(chatAudit).ignoreCase()
				.enableLike(MatchMode.ANYWHERE).excludeZeroes();
		Criteria crit = session.createCriteria(chatAudit.getClass());
		crit.add(example);
		list = crit.list();
		return list;
	}

}
