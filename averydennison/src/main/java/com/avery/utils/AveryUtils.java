package com.avery.utils;

import com.adeptia.indigo.logging.Logger;
import com.avery.config.ConfigService;
import com.avery.services.OrderEmailQueueServices;

public class AveryUtils {

	static Logger log = Logger
			.getLogger(OrderEmailQueueServices.class.getName());
	
	 public static String getReviseCancelOrderStatus(String configurationFilePath, String orderQueueID)
	  {
	    OrderEmailQueueServices orderEmailService = new OrderEmailQueueServices();
	    ConfigService configService = new ConfigService();
	    ConfigService.setConfigFilePath(configurationFilePath);
	    int orderfilequeueId = Integer.parseInt(orderQueueID);
	    boolean reviseCancelOrderStatus=false;
	    
		try {
			 reviseCancelOrderStatus = orderEmailService.getemaildetail(orderfilequeueId, log);
		} catch (Exception e) {
			log.info("error inrevise cancel order.");
		}
		
	    return String.valueOf(reviseCancelOrderStatus);
	  }
}
