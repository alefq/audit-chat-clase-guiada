package py.edu.uca.edw.java3.auditoria_chat.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.ticpy.tekoporu.annotation.NextView;
import org.ticpy.tekoporu.annotation.PreviousView;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractListPageBean;
import org.ticpy.tekoporu.transaction.Transactional;

import py.edu.uca.edw.java3.auditoria_chat.business.RolBC;
import py.edu.uca.edw.java3.auditoria_chat.domain.Rol;


@ViewController
@NextView("/admin/rol_edit.xhtml")
@PreviousView("/admin/rol_list.xhtml")
public class RolListMB extends AbstractListPageBean<Rol, Long>{

	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Rol> model;	
	public LazyDataModel<Rol> getModel() {
		return model;
	}


	public int getPageSize() {
		return pageSize;
	}

	private int pageSize = 5;

	@Inject
	private RolBC rolBC;
	
	@SuppressWarnings("serial")
	@PostConstruct
	public void loadLazyModel() {
		
		model = new LazyDataModel<Rol>() {
			@Override
			public List<Rol> load(int first, int pageSize, String sortField,
					boolean sortOrder, Map<String, String> filters) {
				
					if(sortField == null) sortField = "rolId"; //default sort field
					
					List<Rol> rol = new ArrayList<Rol>();
					rol = rolBC.findPage(pageSize, first, sortField, sortOrder);
					
					return rol;
			}
		};
		
		model.setRowCount(rolBC.count());
		model.setPageSize(pageSize);
		
	}
	

	@Override
	protected List<Rol> handleResultList() {
		return this.rolBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				rolBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
}
