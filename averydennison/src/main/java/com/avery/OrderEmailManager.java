package com.avery;

import com.avery.services.OrderEmailService;

 class OrderEmailManager {
	public static void main(String[] args) {
		
		OrderEmailService orderEmailService = new OrderEmailService();
		orderEmailService.OrderEmailSourceservice(1);
	}
}
