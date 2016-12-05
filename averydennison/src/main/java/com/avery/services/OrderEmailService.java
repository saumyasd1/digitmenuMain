package com.avery.services;

import com.avery.Model.OrderEmailQueueModel;

public class OrderEmailService {
public static void service(){
	
	OrderEmailQueueModel.createOrderEmailQueue();
	
}
}
