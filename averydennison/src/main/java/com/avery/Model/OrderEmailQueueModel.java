package com.avery.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.avery.dao.OrderEmailQueue;
import com.avery.dao.Partner;
import com.avery.dao.Partner_RBOProductLine;
import com.avery.dao.RBO;
import com.avery.utils.HibernateUtil;

public class OrderEmailQueueModel implements OrderEmailQueueInterface{
	
	//static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
/*public HashMap<String, String> EmailSource(int id ){
		
		String Source_email="";
		String Source_email_body ="";
		String Source_email_subject ="";
		HashMap<String, String> emailinfo = new HashMap<String, String>();
		
		
		try{
			log.info("Enter method emailsource  class OrderEmailQueueModel");
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
		
			List<OrderEmailQueue> list = (List<OrderEmailQueue>) session.get(OrderEmailQueue.class, id);

     		/*cr.add(Restrictions.eq("status", "1"));
     		cr.setMaxResults(1);
     		List<OrderEmailQueue> list = cr.list();* /
	         
	         Iterator<OrderEmailQueue> iterator = list.iterator(); 
	         while (iterator.hasNext()){
	        	 OrderEmailQueue orderEmailQueue =  iterator.next(); 
			    
			      Source_email= orderEmailQueue.getSenderEmailId(); 
			      Source_email_body= orderEmailQueue.getMailBody();
			      Source_email_subject= orderEmailQueue.getSubject();
			  }
	         emailinfo.put("source", Source_email);
	         emailinfo.put("subject", Source_email_subject);
	         emailinfo.put("body", Source_email_body);
	         session.close();
	 	 	
		}catch(HibernateException  ex){
			log.error(ex);
		}catch(Exception  e){
			log.error(e);
		}
		log.info("Exit method emailsource  class OrderEmailQueueModel");
		return emailinfo;   
	      
	}*/
	public HashMap<String, String> EmailSource(int id ){
		
		String Source_email="";
		String Source_email_body ="";
		String Source_email_subject ="";
		HashMap<String, String> emailinfo = new HashMap<String, String>();
		
		
		try{
			//log.info("Enter method emailsource  class OrderEmailQueueModel");
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
		
			Criteria cr = session.createCriteria(OrderEmailQueue.class)
    		    .setProjection(Projections.projectionList()
    		      .add(Projections.property("id"), "id")
    		      .add(Projections.property("mailBody"), "mailBody")
    		      .add(Projections.property("subject"), "subject")
    		      .add(Projections.property("senderEmailId"), "senderEmailId"))
    		    .setResultTransformer(Transformers.aliasToBean(OrderEmailQueue.class));

     		//cr.add(Restrictions.eq("status", "1"));
     		cr.add(Restrictions.eq("id", id));
     		//cr.setMaxResults(1);
     		List<OrderEmailQueue> list = cr.list();
	         
	         Iterator<OrderEmailQueue> iterator = list.iterator(); 
	         while (iterator.hasNext()){
	        	 OrderEmailQueue orderEmailQueue =  iterator.next(); 
			    
			      Source_email= orderEmailQueue.getSenderEmailId(); 
			      Source_email_body= orderEmailQueue.getMailBody();
			      Source_email_subject= orderEmailQueue.getSubject();
			  }
	         emailinfo.put("source", Source_email);
	         emailinfo.put("subject", Source_email_subject);
	         emailinfo.put("body", Source_email_body);
	         session.close();
	 	 	
		}catch(HibernateException  ex){
			//log.error(ex);
		}catch(Exception  e){
			//log.error(e);
		}
		//log.info("Exit method emailsource  class OrderEmailQueueModel");
		return emailinfo;   
	      
	}
	public int getPartnerId(String email, String domain ){
		
		int partnerId = 0;
		//log.info("Enter method getPartnerId  class OrderEmailQueueModel");
		try{
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		
		
		Criteria cr = session.createCriteria(Partner.class)
    		    .setProjection(Projections.projectionList()
    		      .add(Projections.property("ID"), "ID")
    		      .add(Projections.property("name"), "name"))
    		    .setResultTransformer(Transformers.aliasToBean(Partner.class));
			cr.add(Restrictions.eq("active", true));
			cr.add(Restrictions.eq("emailDomain", domain));
     		cr.setMaxResults(1);
     		List<Partner> list = cr.list();
	         
	         Iterator<Partner> iterator = list.iterator(); 
	         while (iterator.hasNext()){
	        	 Partner partner =  iterator.next(); 
			     partnerId = partner.getId(); 
			  }
	         if(partnerId == 0){
	        	 Criteria cr1 = session.createCriteria(Partner.class)
	         		    .setProjection(Projections.projectionList()
	         		      .add(Projections.property("ID"), "ID")
	         		      .add(Projections.property("name"), "name"))
	         		    .setResultTransformer(Transformers.aliasToBean(Partner.class));
	     			cr1.add(Restrictions.eq("active", true));
	     			cr1.add(Restrictions.eq("emailId", email));
	          		cr1.setMaxResults(1);
	          		List<Partner> plist = cr1.list();
	     	         
	     	         Iterator<Partner> piterator = plist.iterator(); 
	     	         while (piterator.hasNext()){
	     	        	 Partner partnerid =  piterator.next(); 
	     			     partnerId = partnerid.getId(); 
	     			  }
	         }
     	
	   session.close();
	 	
		}catch(HibernateException  ex){
			//log.error(ex);
		}catch(Exception  e){
			//log.error(e);
		}
		//log.info("Exit method getPartnerId  class OrderEmailQueueModel");
		return partnerId;
	      
	}
	public HashMap<Integer, String> getPartnerRbo(int partnerId ){
		String rboList="";
		HashMap<Integer, String> partner_rboinfo = new HashMap<Integer, String>();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class)
	    		    .setProjection(Projections.projectionList()
	    		      .add(Projections.property("id"), "id")
	    		      .add(Projections.property("emailSubjectRBOMatch"), "emailSubjectRBOMatch"))
	    		    .setResultTransformer(Transformers.aliasToBean(Partner_RBOProductLine.class));
				cr.add(Restrictions.eq("active", true));
				
				cr.add(Restrictions.eq("varPartner.id", partnerId));
				


				//cr.setMaxResults(1);
	     		List<Partner_RBOProductLine> list = cr.list();
	     		
		        Iterator<Partner_RBOProductLine> iterator = list.iterator(); 
				 while (iterator.hasNext()){
		        	 Partner_RBOProductLine partner_RBOProductLine =  iterator.next(); 
		        	 partner_rboinfo.put(partner_RBOProductLine.getId(), partner_RBOProductLine.getEmailSubjectRBOMatch());
		        }
		        session.close();
			}catch(HibernateException  ex){
				
			//	log.error(ex);
			}catch(Exception  e){
				//log.error(e);
			}
		//log.info("Exit method getPartnerRbo  class OrderEmailQueueModel");
		return partner_rboinfo;
	 	
	}
	public int getPartnerProductLines(int partnerId ){
		       	 //partner.
       	 
		return partnerId;
	}
	public int getPartner_rboProductLineId(int partnerId, int RboID, int productlineId ){
		
	 	return partnerId;
	}
	public int updateSchemaId(int schemaId ){
		
		return 1;
	}

	
	
}