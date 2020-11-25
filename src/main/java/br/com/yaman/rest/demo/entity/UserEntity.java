package br.com.yaman.rest.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(schema = "portal",name = "users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="uuid")
	private String UUID;
	
	@Column(name="name")
	private String name;
	@Column(name="lastname")
	private String lastname;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="timestamp")
	private Date timestamp;

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String uUID, String name, String lastname, String email, String password, Date timestamp) {
		super();
		UUID = uUID;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.timestamp = timestamp;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	
}

