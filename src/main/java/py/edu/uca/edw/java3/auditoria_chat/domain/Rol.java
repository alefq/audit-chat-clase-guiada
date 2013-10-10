package py.edu.uca.edw.java3.auditoria_chat.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue
	@Column
	private Long rolId;
	
	@Column(name="descripcion")
	private String descripcion;

	public Rol () {
		super();
	}
	
	public Rol (String descripcion) {
		this.descripcion=descripcion;
	}
	
	
	@OneToMany(mappedBy="rol")
	@Column(name="rolId")
	private Set<Usuario> usuarios;
	
	
	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}