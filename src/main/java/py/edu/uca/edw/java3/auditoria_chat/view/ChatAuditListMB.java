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

	private ChatAudit chatAuditFilter;

	private LazyDataModel<ChatAudit> model;
	private int pageSize = 5; // default page size

	@SuppressWarnings("serial")
	@PostConstruct
	public void loadLazyModel() {

		model = new LazyDataModel<ChatAudit>() {
			@Override
			public List<ChatAudit> load(int first, int pageSize,
					String sortField, boolean sortOrder,
					Map<String, String> filters) {

				if (sortField == null)
					sortField = "id"; // default sort field

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
		if (hasSomeFilter()) {
			lista = chatAuditBC.findByHibernateExample(getChatAuditFilter());
		} else {
			lista = chatAuditBC.findAll();
		}
		logger.info("size: " + lista.size());
		return lista;
	}

	private boolean hasSomeFilter() {
		/* Aquí verificamos si se cargó algún valor */
		boolean ret = false;
		if (!StringUtils.isBlank(getChatAuditFilter().getNickname())) {
			logger.info(getChatAuditFilter().getNickname());
			ret = true;
		}
		return ret;
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter
				.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
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

}