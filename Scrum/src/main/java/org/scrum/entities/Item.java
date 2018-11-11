package org.scrum.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Item implements Serializable{

	@Id
	@GeneratedValue
	private Long idItem;
	@NonNull
	@Column(unique=true)
	private String name;
	@NonNull
	private int importance;
	@NonNull
	private int days;
	@NonNull
	private String note;
	@NonNull
	private Date requestedOn;
	private Date validatedOn;
	@NonNull
	private String status;
	
	@ManyToOne
	@JoinColumn(name="idSprint")
	private sprint sprint;
	

	@ManyToOne
	@JoinColumn(name="idBacklog")
	private backlog backlog;
	



	public sprint getSprint() {
		return sprint;
	}


	public void setSprint(sprint sprint) {
		this.sprint = sprint;
	}


	public backlog getBacklog() {
		return backlog;
	}


	public void setBacklog(backlog backlog) {
		this.backlog = backlog;
	}


	public Item() {
		super();
	}


	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(Date requestedOn) {
		this.requestedOn = requestedOn;
	}

	public Date getValidatedOn() {
		return validatedOn;
	}

	public void setValidatedOn(Date validatedOn) {
		this.validatedOn = validatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Item(String name, int importance, int days, String note, Date requestedOn, Date validatedOn, String status,
			org.scrum.entities.backlog backlog) {
		super();
		this.name = name;
		this.importance = importance;
		this.days = days;
		this.note = note;
		this.requestedOn = requestedOn;
		this.validatedOn = validatedOn;
		this.status = status;
		this.backlog = backlog;
	}


	public Item(String name, int importance, int days, String note, Date requestedOn, String status,
			org.scrum.entities.backlog backlog) {
		super();
		this.name = name;
		this.importance = importance;
		this.days = days;
		this.note = note;
		this.requestedOn = requestedOn;
		this.status = status;
		this.backlog = backlog;
	}


	public Item(String name, int importance, int days, String note, Date requestedOn, String status,
			org.scrum.entities.sprint sprint, org.scrum.entities.backlog backlog) {
		super();
		this.name = name;
		this.importance = importance;
		this.days = days;
		this.note = note;
		this.requestedOn = requestedOn;
		this.status = status;
		this.sprint = sprint;
		this.backlog = backlog;
	}
	
	

	

	
}