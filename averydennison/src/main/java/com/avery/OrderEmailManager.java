package com.avery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.config.ConfigService;
import com.avery.dao.OrderFileAttachment;
import com.avery.dao.Partner_RBOProductLine;
import com.avery.services.OrderEmailQueueServices;

class OrderEmailManager {
	public static void main(String[] args) {
		/*OrderEmailQueueServices orderEmailService = new OrderEmailQueueServices();
		String configFilePath = "D:\\hibernate.cfg.xml";
		ConfigService configService = new ConfigService();
		configService.setConfigFilePath(configFilePath);
		String directoryLocation = "C:\\AveryDennisonFiles\\";
		orderEmailService.OrderEmailSourceservice(directoryLocation, 99);
		//call method to update order email queue status
		// params are order email queue id and status as integer
		orderEmailService.updateOrderEmailStatus(99,8);
		//orderEmailService.GetOrderEmailQueueStatus( 119);
		/*OrderEmailQueueServices orderEmailService = new OrderEmailQueueServices();
		String configFilePath = "D:\\hibernate.cfg.xml";
		ConfigService configService = new ConfigService();
		configService.setConfigFilePath(configFilePath);
		String directoryLocation = "C:\\AveryDennisonFiles\\";
		System.out.println(orderEmailService.getemaildetail(123));*/
		
		
	}
	
}
