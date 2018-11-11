package org.scrum.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class user implements Serializable {
	
	
	@Id
	@GeneratedValue
	private Long id;
	@NonNull
	private String nom;
	@NonNull
	private String prenom;
	@NonNull
	@Column(unique=true)
	private String username;
	@NonNull
	private Date dateNaissance;
	@NonNull
	private String email;
	@NonNull
	@Size(min=6,max=16)
	private String password;
	@NonNull
	private String tel;
	private Integer status;
	@Enumerated(EnumType.STRING)
	private Role role;
	@NotNull
	private String adresse;
	@NotNull
	private String titre;
	@NotNull
	private String company;
	
	
	
	
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
	
}
