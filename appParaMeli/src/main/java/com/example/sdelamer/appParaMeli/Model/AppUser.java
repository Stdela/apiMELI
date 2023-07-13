package com.example.sdelamer.appParaMeli.Model;

import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@SQLDelete(sql = "UPDATE app_user SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Entity
public class AppUser {

// Por ahora va a estar básica, van a ser usuarios cargados por bd
	@Id
	private Long id;
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "deleted_at")
	private Date deletedAt;
	@Column(name = "is_deleted")
	private boolean isDeleted = false;
//	TODO :  podemos crear una propia anotación para customValidator
	@NonNull
	@Column(name= "email")
	private String email;
	@NonNull
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "last_update")
	private Date lastUpdate;
	@NonNull
	private String password;
//	private Long role;

	public AppUser() {

	}

	public AppUser(Long id, Date creationDate, Date deletedAt, boolean isDeleted, String email, String name,
			String lastName, Date lastUpdate, String password) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.deletedAt = deletedAt;
		this.isDeleted = isDeleted;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.lastUpdate = lastUpdate;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
