package org.scrum.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class sprint implements Serializable {
	
	@Id
	@GeneratedValue
	private Long idSprint;
	@NonNull
	@Column(unique=true)
	private String namesprint;
	@NonNull
	private String descriptionsprint;
	
	
	

	@NonNull
	private Date requestedOnsprint;
	private Date validatedOnsprint;
	@NonNull
	private String statussprint;
	
	@ManyToOne
	@JoinColumn(name="idBacklog")
	private backlog backlog;
	
	@OneToMany(mappedBy="sprint", fetch=FetchType.LAZY)
	private Collection<Item> items;

	
	
	public backlog getBacklog() {
		return backlog;
	}



	public void setBacklog(backlog backlog) {
		this.backlog = backlog;
	}



	public sprint(String namesprint, String descriptionsprint, Date requestedOnsprint, String statussprint,
			org.scrum.entities.backlog backlog) {
		super();
		this.namesprint = namesprint;
		this.descriptionsprint = descriptionsprint;
		this.requestedOnsprint = requestedOnsprint;
		this.statussprint = statussprint;
		this.backlog = backlog;
	}



	public sprint(String namesprint, String descriptionsprint, Date requestedOnsprint, String statussprint,
			org.scrum.entities.backlog backlog, Collection<Item> items) {
		super();
		this.namesprint = namesprint;
		this.descriptionsprint = descriptionsprint;
		this.requestedOnsprint = requestedOnsprint;
		this.statussprint = statussprint;
		this.backlog = backlog;
		this.items = items;
	}




	public sprint() {
		super();
		// TODO Auto-generated constructor stub
	}



	public sprint(String namesprint, String descriptionsprint, Date requestedOnsprint, String statussprint,
			Collection<Item> items) {
		super();
		this.namesprint = namesprint;
		this.descriptionsprint = descriptionsprint;
		this.requestedOnsprint = requestedOnsprint;
		this.statussprint = statussprint;
		this.items = items;
	}



	public Long getIdSprint() {
		return idSprint;
	}



	public void setIdSprint(Long idSprint) {
		this.idSprint = idSprint;
	}



	public sprint(String namesprint, String descriptionsprint, Date requestedOnsprint, Date validatedOnsprint,
			String statussprint, Collection<Item> items) {
		super();
		this.namesprint = namesprint;
		this.descriptionsprint = descriptionsprint;
		this.requestedOnsprint = requestedOnsprint;
		this.validatedOnsprint = validatedOnsprint;
		this.statussprint = statussprint;
		this.items = items;
	}



	public String getNamesprint() {
		return namesprint;
	}



	public void setNamesprint(String namesprint) {
		this.namesprint = namesprint;
	}



	public String getDescriptionsprint() {
		return descriptionsprint;
	}



	public void setDescriptionsprint(String descriptionsprint) {
		this.descriptionsprint = descriptionsprint;
	}



	public Date getRequestedOnsprint() {
		return requestedOnsprint;
	}



	public void setRequestedOnsprint(Date requestedOnsprint) {
		this.requestedOnsprint = requestedOnsprint;
	}



	public Date getValidatedOnsprint() {
		return validatedOnsprint;
	}



	public void setValidatedOnsprint(Date validatedOnsprint) {
		this.validatedOnsprint = validatedOnsprint;
	}



	public String getStatussprint() {
		return statussprint;
	}



	public void setStatussprint(String statussprint) {
		this.statussprint = statussprint;
	}



	public Collection<Item> getItems() {
		return items;
	}



	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	
	
}