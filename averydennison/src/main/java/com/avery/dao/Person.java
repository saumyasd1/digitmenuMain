package com.avery.dao;

import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "person")
public class Person {
 
 /*   long id;
    String firstName;
    String lastName;
    //Set<Address> addresses;
 
    public Person() {
 
    }
 
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
      //  this.addresses = new HashSet<Address>();
    }
 
    @Id
    @GeneratedValue
    @Column(name = "person_id")
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
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
 
    /*@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "person_address", joinColumns = { @JoinColumn(name = "person_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
    public Set<Address> getAddresses() {
        return addresses;
    }
 
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }*/
 
}