package py.edu.uca.edw.java3.auditoria_chat.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column
	private Long usuarioId;
	
	@Column
	private String activo;
	
	@Column
	private String apellido;
	
	@Column
	private String email;
	
	@Column
	private String nombre;
	
	@Column
	private String pwd;
	
	@Column
	private String telefono;
	
	@Column
	private String username;
	
	@ManyToOne
	@JoinColumn(name="rolId")
	private Rol rol;
	
	
	
	public Usuario() {
		super();
		this.rol = new Rol();
	}
	
	public Usuario(String activo,String apellido,String email,String nombre, 
			String pwd,Rol rol,String telefono,String username){
		
		this.activo=activo;
		this.apellido=apellido;
		this.email=email;
		this.nombre=nombre;
		this.pwd=pwd;
		this.rol=rol;
		this.telefono=telefono;
		this.username=username;
		
	}
	
	
	public Usuario(Long usuarioId,String username,String nombre, 
			String apellido,String telefono,String email){
		
		this.usuarioId=usuarioId;
		this.username=username;
		this.nombre=nombre;
		this.apellido=apellido;
		this.telefono=telefono;
		this.email=email;
		
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}