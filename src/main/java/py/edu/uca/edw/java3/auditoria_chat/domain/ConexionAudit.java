package py.edu.uca.edw.java3.auditoria_chat.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the conexion_audit database table.
 * 
 */
@Entity
@Table(name="conexion_audit")
public class ConexionAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONEXION_AUDIT_CONEXIONAUDITID_GENERATOR", sequenceName="CONEXION_AUDIT_CONEXION_AUDIT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONEXION_AUDIT_CONEXIONAUDITID_GENERATOR")
	@Column(name="conexion_audit_id")
	private Long conexionAuditId;

	private Timestamp fecha;

	private String nickname;

	private String tipo;

    public ConexionAudit() {
    }

	public Long getConexionAuditId() {
		return this.conexionAuditId;
	}

	public void setConexionAuditId(Long conexionAuditId) {
		this.conexionAuditId = conexionAuditId;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}