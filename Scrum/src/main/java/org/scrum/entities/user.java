package org.scrum.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class user implements Serializable {
	
	
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private String username;
	private Date dateNaissance;
	@Column(unique=true)
	private String email;
	@Size(min=6,max=16)
	private String password;
	private String tel;
	private Integer status;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String adresse;
	private String titre;
	private String company;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Collection<Item> items;
	
	@ManyToMany(mappedBy = "user")
    private Set<backlog> backlog = new HashSet<>();
	

	public user(String email, Role role, Set<org.scrum.entities.backlog> backlog) {
		super();
		this.email = email;
		this.role = role;
		this.backlog = backlog;
	}

	public user(String nom, String prenom, String username, Date dateNaissance, String email,
			@Size(min = 6, max = 16) String password, String tel, Integer status, Role role, @NotNull String adresse,
			@NotNull String titre, @NotNull String company) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.status = status;
		this.role = role;
		this.adresse = adresse;
		this.titre = titre;
		this.company = company;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public user() {
		super();
	}

	public user(String username, String nom, String prenom, Date dateNaissance, String email, String password,
			String tel, Integer status, Role role) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.status = status;
		this.role = role;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Set<backlog> getBacklog() {
		return backlog;
	}

	public void setBacklog(Set<backlog> backlog) {
		this.backlog = backlog;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	
}
