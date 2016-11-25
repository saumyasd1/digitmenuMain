package com.avery;


import com.avery.services.PartnerService;



public class PersonManager {
	public static void main(String[] args) {
		PartnerService person = new PartnerService();
		System.out.println(person.hibernateTest());
	}
}