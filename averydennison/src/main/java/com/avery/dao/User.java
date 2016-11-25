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
    String job_title;
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
	 @Column
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	 @Column
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 @Column
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 @Column
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	 @Column
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	 @Column
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 @Column
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	 @Column
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 @Column(name = "test1")
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
    
    
}
