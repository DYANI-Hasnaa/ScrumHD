package org.scrum.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;


@Entity
public class message implements Serializable {
	
	@Id
	@GeneratedValue
	private Long idMessage;
	private String contenu;
	private Date dateSend;	
	private Long id_user;
	private Long id_backlog;
	private Long id_productOwner;
	@Enumerated(EnumType.STRING)
	private Role sender;
	private int typeSender;
	
	// type=1 ---> client
	// type=0 ---> productOwner
	
	public message(String contenu, Date dateSend, Long id_user, Long id_productOwner) {
		super();
		this.contenu = contenu;
		this.dateSend = dateSend;
		this.id_user = id_user;
		this.id_productOwner = id_productOwner;
	}
	public message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public message(String contenu, Date date, Long id_user, Long id_backlog, Long id_productOwner, Role sender,
			int typeSender) {
		super();
		this.contenu = contenu;
		this.dateSend = date;
		this.id_user = id_user;
		this.id_backlog = id_backlog;
		this.id_productOwner = id_productOwner;
		this.sender = sender;
		this.typeSender = typeSender;
	}
	public message(String contenu, Date dateSend, Long id_user, Long id_productOwner, Role sender, int typeSender) {
		super();
		this.contenu = contenu;
		this.dateSend = dateSend;
		this.id_user = id_user;
		this.id_productOwner = id_productOwner;
		this.sender = sender;
		this.typeSender = typeSender;
	}
	public message(String contenu, Date dateSend, Long id_user, Long id_productOwner, Role sender) {
		super();
		this.contenu = contenu;
		this.dateSend = dateSend;
		this.id_user = id_user;
		this.id_productOwner = id_productOwner;
		this.sender = sender;
	}
	
	
	
	public int getTypeSender() {
		return typeSender;
	}
	public void setTypeSender(int typeSender) {
		this.typeSender = typeSender;
	}
	public Role getSender() {
		return sender;
	}
	public void setSender(Role sender) {
		this.sender = sender;
	}
	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Date getDateSend() {
		return dateSend;
	}
	public void setDateSend(Date dateSend) {
		this.dateSend = dateSend;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Long getId_productOwner() {
		return id_productOwner;
	}
	public void setId_productOwner(Long id_productOwner) {
		this.id_productOwner = id_productOwner;
	}
	public Long getId_backlog() {
		return id_backlog;
	}
	public void setId_backlog(Long id_backlog) {
		this.id_backlog = id_backlog;
	}
	

}

