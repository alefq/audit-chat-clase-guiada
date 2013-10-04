package com.alefq.java3.auditoria_chat.domain;

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
 * The persistent class for the chat_audit database table.
 * 
 */
@Entity
@Table(name="chat_audit")
public class ChatAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CHAT_AUDIT_CHATAUDITID_GENERATOR", sequenceName="CHAT_AUDIT_CHAT_AUDIT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHAT_AUDIT_CHATAUDITID_GENERATOR")
	@Column(name="chat_audit_id")
	private Long chatAuditId;

	private Timestamp fecha;

	private String nickname;

	@Column(name="numero_ip")
	private String numeroIp;

	private String texto;

    public ChatAudit() {
    }

	public Long getChatAuditId() {
		return this.chatAuditId;
	}

	public void setChatAuditId(Long chatAuditId) {
		this.chatAuditId = chatAuditId;
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

	public String getNumeroIp() {
		return this.numeroIp;
	}

	public void setNumeroIp(String numeroIp) {
		this.numeroIp = numeroIp;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}