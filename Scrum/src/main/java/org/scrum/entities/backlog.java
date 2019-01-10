package org.scrum.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;


@Entity
public class backlog implements Serializable  {

	@Id
	@GeneratedValue
	private Long idBacklog;
	@NonNull
	@Column(unique=true)
	private String projectname;
	@NonNull
	private Date datecreation;
	@NonNull
	private String backlogdescription;
	@NonNull
	private int sprintduration;
	
	
	@OneToMany(mappedBy="backlog", fetch=FetchType.LAZY)
	private Collection<Item> items;
	
	@OneToMany(mappedBy="backlog", fetch=FetchType.LAZY)
	private Collection<sprint> sprints;
	
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "userBacklog", 
        joinColumns = { @JoinColumn(name = "idBacklog", nullable = false, updatable = false) }, 
        inverseJoinColumns = { @JoinColumn(name = "id", nullable = false, updatable = false) }
    )
	private Set<user> user = new HashSet<>();
	
	public backlog(String projectname, Date datecreation, String backlogdescription,
			int sprintduration, Set<user> user) {
		super();
		this.projectname = projectname;
		this.datecreation = datecreation;
		this.backlogdescription = backlogdescription;
		this.sprintduration = sprintduration;
		this.user = user;
	}
	public backlog(String projectname, Date datecreation, String backlogdescription,
			int sprintduration, Collection<Item> items, Collection<sprint> sprints) {
		super();
		this.projectname = projectname;
		this.datecreation = datecreation;
		this.backlogdescription = backlogdescription;
		this.sprintduration = sprintduration;
		this.items = items;
		this.sprints = sprints;
	}
	public Collection<sprint> getSprints() {
		return sprints;
	}
	public void setSprints(Collection<sprint> sprints) {
		this.sprints = sprints;
	}
	public Long getIdBacklog() {
		return  idBacklog;
	}
	public void setIdBacklog(Long id) {
		this. idBacklog = id;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public String getBacklogdescription() {
		return backlogdescription;
	}
	public void setBacklogdescription(String backlogdescription) {
		this.backlogdescription = backlogdescription;
	}
	public backlog(String projectname, Date datecreation, String backlogdescription) {
		super();
		this.projectname = projectname;
		this.datecreation = datecreation;
		this.backlogdescription = backlogdescription;
	}
	
	
	
	public Collection<Item> getItems() {
		return items;
	}
	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	public backlog(String projectname, Date datecreation, String backlogdescription,
			Collection<Item> items) {
		super();
		this.projectname = projectname;
		this.datecreation = datecreation;
		this.backlogdescription = backlogdescription;
		this.items = items;
	}
	public backlog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSprintduration() {
		return sprintduration;
	}
	public void setSprintduration(int sprintduration) {
		this.sprintduration = sprintduration;
	}
	public backlog(String projectname, Date datecreation, String backlogdescription,
			int sprintduration, Collection<Item> items) {
		super();
		this.projectname = projectname;
		this.datecreation = datecreation;
		this.backlogdescription = backlogdescription;
		this.sprintduration = sprintduration;
		this.items = items;
	}
	
	public Set<user> getUser() {
		return user;
	}
	public void setUsers(Set<user> user) {
		this.user = user;
	}
	
}
