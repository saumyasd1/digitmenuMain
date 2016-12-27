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

import com.avery.dao.ErrorLog;
import com.avery.dao.OrderEmailQueue;
import com.avery.dao.OrderFileAttachment;
import com.avery.dao.OrderFileQueue;
import com.avery.dao.Partner;
import com.avery.dao.Partner_RBOProductLine;
import com.avery.dao.RBO;
import com.avery.utils.HibernateUtil;
import com.mysql.jdbc.Blob;

public class OrderEmailQueueModel implements OrderEmailQueueInterface{
	
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());

	public HashMap<String, String> EmailSource(int id ){
		
		String Source_email="";
		String Source_email_body ="";
		String Source_email_subject ="";
		HashMap<String, String> emailinfo = new HashMap<String, String>();
		try{
			//log.info("Enter method emailsource  class OrderEmailQueueModel");
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(OrderEmailQueue.class)
    		    .setProjection(Projections.projectionList()
    		      .add(Projections.property("id"), "id")
    		      .add(Projections.property("mailBody"), "mailBody")
    		      .add(Projections.property("subject"), "subject")
    		      .add(Projections.property("senderEmailId"), "senderEmailId"))
    		    .setResultTransformer(Transformers.aliasToBean(OrderEmailQueue.class));

     		cr.add(Restrictions.eq("id", id));
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
	         session.getTransaction().commit();
	         session.close();
	 	 	
		}catch(HibernateException  ex){
			String error=ex.toString();
			this.updateError("service hibernate error",error);
			log.error(ex);
		}catch(Exception  e){
			String error=e.toString();
			this.updateError("service hibernate error",error);
			log.error(e);
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
		session.beginTransaction();
		
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
			     partnerId = partner.getID(); 
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
	     			     partnerId = partnerid.getID(); 
	     			  }
	         }
	         session.getTransaction().commit();
	   session.close();
	 	
		}catch(HibernateException  ex){
			String error=ex.toString();
			this.updateError("service hibernate error",error);
			log.error(ex);
		}catch(Exception  e){
			String error=e.toString();
			this.updateError("service hibernate error",error);
			log.error(e);
		}
		//log.info("Exit method getPartnerId  class OrderEmailQueueModel");
		return partnerId;
	      
	}
	
	public ArrayList<Object> getPartnerRbo_productlines(int partnerId ){
		 ArrayList<Object> rboproduclines= new ArrayList();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class)
	    		    .setProjection(Projections.projectionList()
	    		      .add(Projections.property("id"), "id")
	    		      .add(Projections.property("fileProductlineMatch"), "fileProductlineMatch")
	    		      .add(Projections.property("fileRBOMatch"), "fileRBOMatch")
	    		      .add(Projections.property("emailSubjectProductLineMatch"), "emailSubjectProductLineMatch")
	    		      //.add(Projections.property("attachmentFileRBOMatch"), "attachmentFileRBOMatch")
	    		     // .add(Projections.property("attachmentProductlineMatch"), "attachmentProductlineMatch")
	    		     // .add(Projections.property("emailSubjectRBOMatchRequired"), "emailSubjectRBOMatchRequired")
	    		     // .add(Projections.property("emailSubjectRBOMatchLocation"), "emailSubjectRBOMatchLocation")
	    		      .add(Projections.property("emailSubjectRBOMatch"), "emailSubjectRBOMatch"))
	    		    .setResultTransformer(Transformers.aliasToBean(Partner_RBOProductLine.class));
				cr.add(Restrictions.eq("active", true));
				cr.add(Restrictions.eq("varPartner.id", partnerId));
				
	     		List<Partner_RBOProductLine> list = cr.list();
	     		rboproduclines=(ArrayList<Object>) cr.list();
	     		session.getTransaction().commit();
		        session.close();
			}catch(HibernateException  ex){
				String error=ex.toString();
				this.updateError("service hibernate error",error);
				log.error(ex);
			}catch(Exception  e){
				String error=e.toString();
				this.updateError("service hibernate error",error);
				log.error(e);
			}
		//log.info("Exit method getPartnerRbo  class OrderEmailQueueModel");
		return rboproduclines;
	 	
	}
	public ArrayList<Object> getPartner_productline(int productlineId ){
		 ArrayList<Object> rboproduclines= new ArrayList();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class)
	    		    .setProjection(Projections.projectionList()
	    		      .add(Projections.property("id"), "id")
	    		      
	    		      .add(Projections.property("fileRBOMatch"), "fileRBOMatch")
	    		      .add(Projections.property("attachmentFileMatchLocation"), "attachmentFileMatchLocation")
	    		      .add(Projections.property("attachmentFileRBOMatch"), "attachmentFileRBOMatch")
	    		      .add(Projections.property("attachmentProductlineMatch"), "attachmentProductlineMatch")
	    		    //  .add(Projections.property("attachmentFileProductlineMatchLocation"), "attachmentFileProductlineMatchLocation")
	    		      //.add(Projections.property("attachmentFileProductlineMatchRequired"), "attachmentFileProductlineMatchRequired")
	    		      .add(Projections.property("fileProductlineMatch"), "fileProductlineMatch"))
	    		     // .add(Projections.property("fileProductLineMatchLocation"), "fileProductLineMatchLocation"))
	    		     // .add(Projections.property("fileProductLineMatchRequired"), "fileProductLineMatchRequired"))
	    		    .setResultTransformer(Transformers.aliasToBean(Partner_RBOProductLine.class));
				cr.add(Restrictions.eq("active", true));
				cr.add(Restrictions.eq("id", productlineId));
				
				//cr.add(Restrictions.eq("varPartner.id", partnerId));
				
	     		List<Partner_RBOProductLine> list = cr.list();
	     		rboproduclines=(ArrayList<Object>) cr.list();
	     		session.getTransaction().commit();
		        session.close();
			}catch(HibernateException  ex){
				String error=ex.toString();
				this.updateError("service hibernate error",error);
				log.error(ex);
			}catch(Exception  e){
				String error=e.toString();
				this.updateError("service hibernate error",error);
				log.error(e);
			}
		
		//log.info("Exit method getPartnerRbo  class OrderEmailQueueModel");
		return rboproduclines;
	 	
	}
	public ArrayList<Object> GetEmailAttachments(int orderEmailId){
		ArrayList<Object> EmailAttachments= new ArrayList();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(OrderFileAttachment.class)
	    		    .setProjection(Projections.projectionList()
	    		      .add(Projections.property("id"), "id")
	    		      .add(Projections.property("fileExtension"), "fileExtension")
	    		      .add(Projections.property("fileName"), "fileName")
	    		      .add(Projections.property("filePath"), "filePath"))
	    		    .setResultTransformer(Transformers.aliasToBean(OrderFileAttachment.class));
				//cr.add(Restrictions.eq("active", true));
				cr.add(Restrictions.eq("varOrderEmailQueue.id", orderEmailId));
				
	     		List<OrderFileAttachment> list = cr.list();
	     		EmailAttachments=(ArrayList<Object>) cr.list();
	     		session.getTransaction().commit();
		        session.close();
		}catch(HibernateException  ex){
			String error=ex.toString();
			this.updateError("service hibernate error",error);
				log.error(ex);
			}catch(Exception  e){
				String error=e.toString();
				this.updateError("service hibernate error",error);
				log.error(e);
			}
		return EmailAttachments;
	}
	public int updateOrderEmail(int orderEmailId, String orderEmailStatus ){
		
		int result = 0;
		try{
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			OrderEmailQueue orderEmail=(OrderEmailQueue)session.load(OrderEmailQueue.class, orderEmailId);
			orderEmail.setStatus(orderEmailStatus);
			session.persist(orderEmail);
			result=1;
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			String error=e.toString();
			this.updateError("service hibernate error",error);
			log.error(e);
		}
		return result;
	}
	
	public int updateOrderEmailAttachment(int attachmentId, int productlineId, String Status){
		int result = 0;
		//System.out.println("attachmentId "+ attachmentId);
		//System.out.println("productlineId "+ productlineId);
		//System.out.println("Status "+ Status);
		try{
			
			
			//Partner_RBOProductLine pline=new Partner_RBOProductLine();
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail=(OrderFileAttachment)session.load(OrderFileAttachment.class, attachmentId);
			
			if(productlineId!=0){
				Partner_RBOProductLine pline=(Partner_RBOProductLine)session.load(Partner_RBOProductLine.class, productlineId);
				pline.setId(productlineId);
				orderEmail.setVarProductLine(pline);
			}
			
			orderEmail.setStatus(Status);
			orderEmail.setId(attachmentId);
			
			session.update(orderEmail);
			result=1;
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			//System.out.println("1212121212");
			String error=e.toString();
			this.updateError("service hibernate error",error);
			log.error(e);
		}
		//System.out.println("result "+ result);
		return result;
	}
	
public int updateError(String ErrorCategory,String description ){
		
		int result = 0;
		try{
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			ErrorLog errorLog=new ErrorLog();
			errorLog.setErrorCategory(ErrorCategory);
			errorLog.setErrorContent(description);
			session.persist(errorLog);
			
			result=1;
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			String error=e.toString();
			
			//this.updateError("error method models",error);
			log.error(e);
		}
		return result;
	}
	
	
}
