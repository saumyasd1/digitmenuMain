package com.avery.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;


public class OrderEmailService {
	
	//static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	
	public void OrderEmailSourceservice(int id){
		String email="";
		String subject="";
		String body="";
		String emailId="";
		String Domain ="";
		
		try{
			
			//log.info("Enter method OrderEmailSourceservice  class OrderEmailService");
			OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
			HashMap emailinfo = orderEmailQueue.EmailSource(id);
			
			 Iterator it = emailinfo.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        
			        if(pair.getKey()=="source"){
			        	email = (String) pair.getValue();
			        }
			        if(pair.getKey()=="subject"){
			        	subject = (String) pair.getValue();
			        }
			        if(pair.getKey()=="body"){
			        	body = (String) pair.getValue();
			        }
			    }
			if(email.contains("@")){
			
				if(email.contains("<")){
					String[] part = email.split("<");
					String[] s_email = part[1].split(">");
					email= s_email[0];
				}
				String[] p_email = email.split("@");
				emailId= p_email[0];
				Domain =p_email[1];
				int partnerId = orderEmailQueue.getPartnerId(emailId, Domain);
				if(partnerId != 0){
					
					HashMap partner_rboinfo = orderEmailQueue.getPartnerRbo(partnerId);
					Iterator rbo = partner_rboinfo.entrySet().iterator();
					
				    while (rbo.hasNext()) {
				    	
				        Map.Entry pair = (Map.Entry)rbo.next();
				        String rbolist = (String) pair.getValue();
				         if(rbolist.contains("|")){
				        	   String[] rbos = rbolist.split("\\|");
							   
							   for (String prbo : rbos) {
								   
								   if(subject.toLowerCase().contains(prbo.toLowerCase())){
									   System.out.println("Partner rbo found is:" + prbo);
									   System.out.println("Partner rbo productline id is:" + pair.getKey());
								   }
							   }
						}
				    }
					
					
				}
				//log.info("Exit method OrderEmailSourceservice  class OrderEmailService");
				System.out.print("Partner id is:" + partnerId);
				
			}else{
				//////////////////////log error for invalid source email
			}
		}catch(HibernateException  ex){
			//log.error(ex);
		}catch(Exception  e){
			//log.error(e);
		}
		
	}
}
