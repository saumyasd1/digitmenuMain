package com.avery;


import com.avery.services.PersonService;



public class PersonManager {
	public static void main(String[] args) {
		PersonService person = new PersonService();
		System.out.println(person.hibernateTest());
	}
}