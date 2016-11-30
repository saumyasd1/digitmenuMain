package com.avery;


import com.avery.services.PartnerService;

public class PartnerManager {
public static void main(String[] args) {
	PartnerService ps=new PartnerService();
	System.out.println(ps.hibernateTest());
}
}
