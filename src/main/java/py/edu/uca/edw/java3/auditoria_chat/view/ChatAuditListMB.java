package py.edu.uca.edw.java3.auditoria_chat.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.ticpy.tekoporu.annotation.NextView;
import org.ticpy.tekoporu.annotation.PreviousView;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractListPageBean;
import org.ticpy.tekoporu.transaction.Transactional;

import py.edu.uca.edw.java3.auditoria_chat.business.ChatAuditBC;
import py.edu.uca.edw.java3.auditoria_chat.domain.ChatAudit;
import py.edu.uca.edw.java3.auditoria_chat.domain.Usuario;

@ViewController
@NextView("/chatAudit_edit.xhtml")
@PreviousView("/chatAudit_list.xhtml")
@SessionScoped
public class ChatAuditListMB extends AbstractListPageBean<ChatAudit, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ChatAuditBC chatAuditBC;

	@Inject
	private Logger logger;

	/*
	 * El campo que se utiliza en la vista (xhtml) para el ingreso de criterios
	 * para el filtrado de la grilla
	 */
	private ChatAudit chatAuditFilter;

	private Double precio;

	/*
	 * Este model se utiliza para poder hacer el paginado en el ejemplo que
	 * realiza la paginación vía Base de Datos
	 */
	private LazyDataModel<ChatAudit> model;
	/* El tamaño que tendrá cada página de las grillas */
	private int pageSize = 4;

	@SuppressWarnings("serial")
	@PostConstruct
	public void loadLazyModel() {

		model = new LazyDataModel<ChatAudit>() {
			@Override
			public List<ChatAudit> load(int first, int pageSize,
					String sortField, boolean sortOrder,
					Map<String, String> filters) {

				if (sortField == null)
					sortField = "chatAuditId"; // default sort field

				List<ChatAudit> bookmark = new ArrayList<ChatAudit>();
				bookmark = chatAuditBC.findPage(pageSize, first, sortField,
						sortOrder);

				return bookmark;
			}
		};

		model.setRowCount(chatAuditBC.count());
		model.setPageSize(pageSize);

	}

	@Override
	protected List<ChatAudit> handleResultList() {

		List<ChatAudit> lista = null;
		/*
		 * Si fue cargado algún filtro se hará la búsqueda por hibernate
		 * filtrando los resultados
		 */
		if (hasSomeFilter()) {
			lista = chatAuditBC.findByHibernateExample(getChatAuditFilter());
		} else {
			/*
			 * Si no se cumplen las condiciones para aplicar filtros, se
			 * recupera todos los valores
			 */
			lista = chatAuditBC.findAll();
		}
		logger.info("size: " + lista.size());
		return lista;
	}

	private boolean hasSomeFilter() {
		/* Aquí verificamos si se cargó algún valor */
		boolean ret = false;
		if (!StringUtils.isBlank(getChatAuditFilter().getNickname())
				|| !StringUtils.isBlank(getChatAuditFilter().getNumeroIp())) {
			logger.info("Filtrando por nickname: "
					+ getChatAuditFilter().getNickname());
			logger.info("Filtrando por ip: "
					+ getChatAuditFilter().getNumeroIp());

			ret = true;
		}
		return ret;
	}

	@Transactional
	public String deleteSelection() {
		/*
		 * Este método es invocado cuándo se quiere eliminar elementos
		 * seleccionados de la lista
		 */
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter
				.hasNext();) {
			/* Se itera sobre todos los elementos seleccionados (la pk) */
			Long id = iter.next();
			/*
			 * El Map tiene el valor "true" si el elemento fue seleccionado en
			 * la grilla
			 */
			delete = getSelection().get(id);
			if (delete) {
				/*
				 * Se procede a eliminar ese elemento mediante el BC
				 * correspondiente
				 */
				chatAuditBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

	public LazyDataModel<ChatAudit> getModel() {
		return model;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String handleFilteredResultList() {
		clear();
		handleResultList();
		return getCurrentView();
	}

	public ChatAudit getChatAuditFilter() {
		if (chatAuditFilter == null) {
			/*
			 * Aquí se debe inicializar todo lo que haga falta para el Entity
			 * que proveerá de los criterios para el fitro.
			 */
			chatAuditFilter = new ChatAudit();
		}
		return chatAuditFilter;
	}

	public void setChatAuditFilter(ChatAudit chatAuditFilter) {
		this.chatAuditFilter = chatAuditFilter;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getIva() {
		/* Ejemplo de llamada a un procedimiento almacenado/función */
		return chatAuditBC.getIva(getPrecio());
	}

	public void resetFilters() {
		setChatAuditFilter(new ChatAudit());
		setPrecio(null);
	}

}