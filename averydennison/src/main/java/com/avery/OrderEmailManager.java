package com.avery;

/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;*/

//import com.avery.Model.OrderEmailQueueInterface;
//import com.avery.Model.OrderEmailQueueModel;
import com.adeptia.indigo.logging.Logger;
import com.avery.config.ConfigService;
//import com.avery.dao.OrderFileAttachment;
//import com.avery.dao.Partner_RBOProductLine;
import com.avery.services.OrderEmailQueueServices;
import com.avery.utils.DateUtility;
//import com.avery.DateManager;

class OrderEmailManager {
	static Logger log = Logger
			.getLogger(OrderEmailQueueServices.class.getName());
	public static void main(String[] args) throws Exception {
		try{
			
			OrderEmailQueueServices orderEmailService = new OrderEmailQueueServices();
			String configFilePath = "D:\\hibernate.cfg.xml";
			ConfigService configService = new ConfigService();
			configService.setConfigFilePath(configFilePath);
			String directoryLocation = "C:\\AveryDennisonFiles\\"; 
			//DateUtility.SetDbTimeZone();
			orderEmailService.orderEmailSourceservice(2149, log);
			System.out.println("hello");
			//System.out.println(orderEmailService.getemaildetail(1191));
			//System.out.println(orderEmailService.EmailBody(1213));
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception("Error while Identifying email."
						 + e.getMessage());
					  
			}
		
	}
	
}
