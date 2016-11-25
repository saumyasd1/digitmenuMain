package com.avery.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "user")
public class User {
	
	int id;
    String firstName;
    String lastName;
    String email;
    String gender;
    String jobTitle;
    String password;
    String test;
    
	int role;
    int status;
    
    public User() {
    	 
    }
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        
    }
    @Id 
    @GeneratedValue 
    @Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="firstName" , length=100)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="lastName" , length=100)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="email" , length=100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="gender" , length=100)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="jobTitle" , length=100)
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	@Column(name="password" , length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="role")
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
    
    
}
