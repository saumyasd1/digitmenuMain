package com.avery.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
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
import com.avery.utils.HibernateUtil;
//import com.mysql.jdbc.Blob;

public class OrderEmailQueueModel implements OrderEmailQueueInterface{
	
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());

	public HashMap<String, String> EmailSource(int id ) throws Exception{
		
		String Source_email="";
		String subjectRbo ="";
		String subjectProductline ="";
		String BodyRbo ="";
		String BodyProductline ="";
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
	    		      //.add(Projections.property("mailBody"), "mailBody")
	    		      .add(Projections.property("subject"), "subject")
	    		      .add(Projections.property("emailSubjectProductLineMatch"), "emailSubjectProductLineMatch")
	    		      .add(Projections.property("emailSubjectRBOMatch"), "emailSubjectRBOMatch")
	    		      .add(Projections.property("emailBodyProductLineMatch"), "emailBodyProductLineMatch")
	    		      .add(Projections.property("emailBodyRBOMatch"), "emailBodyRBOMatch")
	    		      .add(Projections.property("senderEmailId"), "senderEmailId"))
	    		    .setResultTransformer(Transformers.aliasToBean(OrderEmailQueue.class));
	
	     		cr.add(Restrictions.eq("id", id));
	     		List<OrderEmailQueue> list = cr.list();
		         
		         Iterator<OrderEmailQueue> iterator = list.iterator(); 
		         while (iterator.hasNext()){
		        	 OrderEmailQueue orderEmailQueue =  iterator.next(); 
				    
				      Source_email= orderEmailQueue.getSenderEmailId(); 
				      subjectRbo= orderEmailQueue.getEmailSubjectRBOMatch();
				      subjectProductline= orderEmailQueue.getEmailSubjectProductLineMatch();
				      BodyRbo= orderEmailQueue.getEmailBodyRBOMatch();
				      BodyProductline= orderEmailQueue.getEmailBodyProductLineMatch();
				      Source_email_subject= orderEmailQueue.getSubject();
				  }
		         emailinfo.put("source", Source_email);
		         emailinfo.put("subject", Source_email_subject);
		         emailinfo.put("subjectRbo", subjectRbo);
		         emailinfo.put("subjectProductline", subjectProductline);
		         emailinfo.put("BodyRbo", BodyRbo);
		         emailinfo.put("BodyProductline", BodyProductline);
		         session.getTransaction().commit();
		         session.close();
	 	 	
		}catch(HibernateException  ex){
			throw  ex;
		}catch(Exception e){
			throw e;
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
		session.beginTransaction();
		 
		Criteria cr = session.createCriteria(Partner.class)
    		    .setProjection(Projections.projectionList()
    		      .add(Projections.property("ID"), "ID")
    		      .add(Projections.property("partnerName"), "partnerName"))
    		    .setResultTransformer(Transformers.aliasToBean(Partner.class));
			cr.add(Restrictions.eq("active", true));
			cr.add(Restrictions.eq("emailDomain", domain));
     		cr.setMaxResults(1);
     		
     		List<Partner> list = cr.list();
     		//System.out.println("p list size "+list.size()); 
	         Iterator<Partner> iterator = list.iterator(); 
	         while (iterator.hasNext()){
	        	 Partner partner =  iterator.next(); 
	        	 //casted for long
	        	 if(partner!=null){
	        		 partnerId = (int) partner.getID();
	 			}
			    
			  }
	          
	         if(partnerId == 0){
	        	 Criteria cr1 = session.createCriteria(Partner.class)
	         		    .setProjection(Projections.projectionList()
	         		    .add(Projections.property("ID"), "ID")
	         		    .add(Projections.property("partnerName"), "partnerName"))
	         		    .setResultTransformer(Transformers.aliasToBean(Partner.class));
	     			cr1.add(Restrictions.eq("active", true));
	     			cr1.add(Restrictions.eq("emailId", email));
	          		cr1.setMaxResults(1);
	          		List<Partner> plist = cr1.list();
	     	         
	     	         Iterator<Partner> piterator = plist.iterator(); 
	     	         while (piterator.hasNext()){
	     	        	 Partner partnerid =  piterator.next(); 
	     	        	 //casted for long
	     	        	if(partnerid != null){
	     	        		partnerId = (int) partnerid.getID(); 
	     	        	}
	     			  }
	         }
	        
	         session.getTransaction().commit();
	         session.close();
	 	
		}catch(HibernateException  ex){
			throw  ex;
			//log.error(ex);
		}catch(Exception  e){
			throw  e;
			//log.error(e);
		}
		//log.info("Exit method getPartnerId  class OrderEmailQueueModel");
		return partnerId;
	      
	}
	public ArrayList<Object> getPartnerRbo_productlines(String email ) throws Exception{
		 ArrayList<Object> rboproduclines= new ArrayList();
		
			String emailId = "";
			String domain = "";
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			if (email.contains("@")) {
				String[] p_email = email.split("@");
				emailId = p_email[0];
				domain = p_email[1];
				
			}
		
			domain="*@"+domain;
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class);
	    		  
			cr.add(Restrictions.eq("active", true));
			//cr.add(Restrictions.
			cr.add(Restrictions.like("email","%"+email+"%"));
			List<Partner_RBOProductLine> list = cr.list();
    		rboproduclines=(ArrayList<Object>) cr.list();
    		//System.out.println("size     "+rboproduclines.size());
    		if(rboproduclines.size()==0){
    			//System.out.println("size11     "+rboproduclines.size());
    			Criteria cr1 = session.createCriteria(Partner_RBOProductLine.class);
	    		  
    			cr1.add(Restrictions.eq("active", true));
    			cr1.add(Restrictions.like("email","%"+domain+"%"));
    			List<Partner_RBOProductLine> p_list = cr1.list();
        		rboproduclines=(ArrayList<Object>) cr1.list();
    			/*Criteria cr1 = session.createCriteria(Partner_RBOProductLine.class);
	    		  
	   			cr1.add(Restrictions.eq("active", true));
	   			cr1.add(Restrictions.like("email",emailId+"%"));
	   			List<Partner_RBOProductLine> p_list = cr1.list();
        		rboproduclines=(ArrayList<Object>) cr1.list();*/
    		}
	     		
	     		session.getTransaction().commit();
		        session.close();
			}catch(HibernateException  ex){
				//ex.printStackTrace();
				throw  ex;
				
			}catch(Exception  e){
				throw  e;
				//log.error(e);
			}
		//log.info("Exit method getPartnerRbo  class OrderEmailQueueModel");
		return rboproduclines;
	 	
	}
	/*public ArrayList<Object> getPartnerRbo_productlines(int partnerId ){
		 ArrayList<Object> rboproduclines= new ArrayList();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class);
	    		   // .setProjection(Projections.projectionList());
	    		      /*.add(Projections.property("id"), "id")
	    		      .add(Projections.property("fileProductlineMatch"), "fileProductlineMatch")
	    		      .add(Projections.property("fileRBOMatch"), "fileRBOMatch")
	    		      .add(Projections.property("emailSubjectProductLineMatch"), "emailSubjectProductLineMatch")
	    		      .add(Projections.property("attachmentFileProductlineMatchLocation"), "attachmentFileProductlineMatchLocation")
	    		      .add(Projections.property("attachmentFileProductlineMatchRequired"), "attachmentFileProductlineMatchRequired")
	    		      .add(Projections.property("attachmentFileRBOMatch"), "attachmentFileRBOMatch")
	    		      .add(Projections.property("attachmentProductlineMatch"), "attachmentProductlineMatch")
	    		      .add(Projections.property("emailSubjectRBOMatchRequired"), "emailSubjectRBOMatchRequired")
	    		      .add(Projections.property("emailSubjectRBOMatchLocation"), "emailSubjectRBOMatchLocation")
	    		      .add(Projections.property("emailSubjectRBOMatch"), "emailSubjectRBOMatch"))* /
	    		   // .setResultTransformer(Transformers.aliasToBean(Partner_RBOProductLine.class));
				cr.add(Restrictions.eq("active", true));
				cr.add(Restrictions.eq("varPartner.id", partnerId));
				
	     		List<Partner_RBOProductLine> list = cr.list();
	     		rboproduclines=(ArrayList<Object>) cr.list();
	     		session.getTransaction().commit();
		        session.close();
			}catch(HibernateException  ex){
				ex.printStackTrace();
				String error=ex.toString();
				this.updateError("service hibernate error9",error);
				//log.error(ex);
			}catch(Exception  e){
				e.printStackTrace();
				String error=e.toString();
				this.updateError("service hibernate error00",error);
				//log.error(e);
			}
		//log.info("Exit method getPartnerRbo  class OrderEmailQueueModel");
		return rboproduclines;
	 	
	}*/
	public ArrayList<Object> getPartner_productline(int productlineId )throws Exception{
		 ArrayList<Object> rboproduclines= new ArrayList<Object>();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class);
	    		    /*.setProjection(Projections.projectionList()
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
	    		    .setResultTransformer(Transformers.aliasToBean(Partner_RBOProductLine.class));*/
				cr.add(Restrictions.eq("active", true));
				cr.add(Restrictions.eq("id", productlineId));
				
				//cr.add(Restrictions.eq("varPartner.id", partnerId));
				
	     		List<Partner_RBOProductLine> list = cr.list();
	     		rboproduclines=(ArrayList<Object>) cr.list();
	     		session.getTransaction().commit();
		        session.close();
			}catch(HibernateException  ex){
				throw  ex;
			}catch(Exception  e){
				throw  e;
			}
		
		//log.info("Exit method getPartnerRbo  class OrderEmailQueueModel");
		return rboproduclines;
	 	
	}
	public ArrayList<Object> GetEmailAttachments(int orderEmailId)throws Exception{
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
	    		       .add(Projections.property("status"), "status")
	    		        .add(Projections.property("comment"), "comment")
	    		      .add(Projections.property("fileContentMatch"), "fileContentMatch")
	    		      .add(Projections.property("fileContentType"), "fileContentType")
	    		      .add(Projections.property("varProductLine"), "varProductLine")
	    		      .add(Projections.property("filePath"), "filePath"))
	    		    .setResultTransformer(Transformers.aliasToBean(OrderFileAttachment.class));
				//cr.add(Restrictions.eq("active", true));
				cr.add(Restrictions.eq("varOrderEmailQueue.id", orderEmailId));
				
	     		List<OrderFileAttachment> list = cr.list();
	     		EmailAttachments=(ArrayList<Object>) cr.list();
	     		session.getTransaction().commit();
		        session.close();
		}catch(HibernateException  ex){
			throw  ex;
			}catch(Exception  e){
				throw  e;
				//log.error(e);
			}
		return EmailAttachments;
	}
	public int updateOrderEmail(int orderEmailId, String orderEmailStatus, String subject_rboMatch, String subject_productlineMatch, String body_rboMatch, String body_productlineMatch, String comment )throws Exception{
		
		int result = 0;
		try{
			//System.out.println("orderEmailId "+orderEmailId);
			//System.out.println("orderEmailStatus "+orderEmailStatus);
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			OrderEmailQueue orderEmail=(OrderEmailQueue)session.load(OrderEmailQueue.class, orderEmailId);
			if(orderEmailStatus.length()!=0){
				orderEmail.setStatus(orderEmailStatus);
			}
			if(body_productlineMatch.length()!=0){
				orderEmail.setEmailBodyProductLineMatch(body_productlineMatch);
				//orderEmail.set
			}
			if(body_rboMatch.length()!=0){
				orderEmail.setEmailBodyRBOMatch(body_rboMatch);
			}
			if(subject_productlineMatch.length()!=0){
			orderEmail.setEmailSubjectProductLineMatch(subject_productlineMatch);
			}
			if(subject_rboMatch.length()!=0){
				orderEmail.setEmailSubjectRBOMatch(subject_rboMatch);
			}
			if(comment.length()!=0){
				orderEmail.setComment(comment);
			}
			session.persist(orderEmail);
			//System.out.println("orderEmailId "+orderEmailId);
			//System.out.println("orderEmailStatus "+orderEmailStatus);
			result=1;
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			throw  e;
			//log.error(e);
		}
		return result;
	}
	
	
	public int updateOrderEmailAttachment(int attachmentId, int productlineId, String Status, String rboMatch, String productlineMatch, String comment, String contentMatch)throws Exception{
		int result = 0;
		try{
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
			orderEmail.setProductLineMatch(productlineMatch);
			orderEmail.setRboMatch(rboMatch);
			orderEmail.setId(attachmentId);
			orderEmail.setComment(comment);
			orderEmail.setFileContentMatch(contentMatch);
			
			session.update(orderEmail);
			result=1;
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			throw  e;
		}
		return result;
	}
	public boolean updateAllAttachment(int email_id, int productlineId, String Status, String comment)throws Exception{
		int result = 0;
		try{
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria cr = session.createCriteria(OrderFileAttachment.class);
			cr.add(Restrictions.eq("varOrderEmailQueue.id", email_id));
			
			List<OrderFileAttachment> list = cr.list();
	         
	        Iterator<OrderFileAttachment> iterator = list.iterator(); 
	        while (iterator.hasNext()){
	        	OrderFileAttachment orderFileAttachment =  iterator.next(); 
			    orderFileAttachment.getId(); 
			    if(productlineId!=0){
					Partner_RBOProductLine pline=(Partner_RBOProductLine)session.load(Partner_RBOProductLine.class, productlineId);
					pline.setId(productlineId);
					orderFileAttachment.setVarProductLine(pline);
				}
			     orderFileAttachment.setStatus(Status); 
			     orderFileAttachment.setComment(comment);
			     session.persist(orderFileAttachment);
			}
	        result=1;
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			throw  e;
		}
		return true;
	}
	
public int updateError(String ErrorCategory,String description )throws Exception{
		
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
			throw  e;
		}
		return result;
	}
	public boolean updateAttachmenttype(int att_id, String contentType)throws Exception{
		int result=0;
		try{
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail=(OrderFileAttachment)session.load(OrderFileAttachment.class, att_id);
			
			orderEmail.setId(att_id);
			orderEmail.setFileContentType(contentType);
			session.update(orderEmail);
			 result=1;
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			throw  e;
		}
		return true;
	}
	/**
	 * get attachment id andorderemailqueue id for order file queue
	 * author Dipanshu Ahuja
	 * **/
	public int GeteAttachmentId(int fileQueueId)throws Exception{
		int attachment_id=0;
		OrderFileAttachment orderFileAttachment=new OrderFileAttachment();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			//System.out.print("here1");
			OrderFileQueue orderfilequeue=(OrderFileQueue)session.load(OrderFileQueue.class, fileQueueId);
			orderFileAttachment = orderfilequeue.getVarOrderFileAttachment();
			//casted for long
			//System.out.println("here"+orderFileAttachment.getId());
			if(orderFileAttachment!= null){
				attachment_id = (int) orderFileAttachment.getId();
			}
			session.getTransaction().commit();
	        session.close();
		}catch(HibernateException  ex){
			ex.printStackTrace();
			//throw  ex;
		}catch(Exception  e){
			e.printStackTrace();
			//throw  e;		
		}
		return attachment_id;
	}
	public HashMap<String, Integer> GetOrderEmailQueueId(int att_id)throws Exception{
		int orderEmailQueueid=0;
		HashMap<String, Integer> emailatt_info = new HashMap<String, Integer>();
		OrderEmailQueue orderEmailQueue=new OrderEmailQueue();
		Partner_RBOProductLine partner_RBOProductLine=new Partner_RBOProductLine();
		//log.info("Enter method getPartnerRbo  class OrderEmailQueueModel");
		try{
			
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderFileAttachment=(OrderFileAttachment)session.load(OrderFileAttachment.class, att_id);
			
			orderEmailQueue = orderFileAttachment.getVarOrderEmailQueue();
			///casted for long data types
			if( orderEmailQueue != null){
				orderEmailQueueid =  orderEmailQueue.getId();
			}
			partner_RBOProductLine=orderFileAttachment.getVarProductLine();
			int schema_id = 0;
			if( partner_RBOProductLine!=null){
				schema_id =  partner_RBOProductLine.getId();
			}
			//int schema_id = (int) partner_RBOProductLine.getId();
			
			emailatt_info.put("emailQueue_id", orderEmailQueueid);
			emailatt_info.put("schema_id", schema_id);
			
			session.getTransaction().commit();
	        session.close();
		}catch(HibernateException  ex){
			throw  ex;
		}catch(Exception  e){
			throw  e;		
		}
		return emailatt_info;
	}
	public ArrayList<Object> GetEmailAttachmentDetail(int orderEmailId)throws Exception{
		
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment();
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
	    		      .add(Projections.property("fileContentMatch"), "fileContentMatch")
	    		      .add(Projections.property("fileContentType"), "fileContentType")
	    		      .add(Projections.property("varProductLine"), "varProductLine")
	    		      .add(Projections.property("filePath"), "filePath"))
	    		    .setResultTransformer(Transformers.aliasToBean(OrderFileAttachment.class));
				cr.add(Restrictions.eq("varOrderEmailQueue.id", orderEmailId));
				
	     		OrderFileAttachment ofa  = (OrderFileAttachment) cr.list().get(0);
	     		EmailAttachments.add(ofa);
	     		session.getTransaction().commit();
		        session.close();
		}catch(HibernateException  ex){
			throw ex;
		}catch(Exception  e){
			throw e;
		}
		return EmailAttachments;
	}
	public int updateOrderEmailAttachmentContent(int attachmentId, int productlineId, String Status, String rboMatch, String productlineMatch, String comment,String fileType)throws Exception{
		int result = 0;
		try{
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail=(OrderFileAttachment)session.load(OrderFileAttachment.class, attachmentId);
			if(productlineId!=0){
				Partner_RBOProductLine pline=(Partner_RBOProductLine)session.load(Partner_RBOProductLine.class, productlineId);
				pline.setId(productlineId);
				orderEmail.setVarProductLine(pline);
			}
			orderEmail.setFileContentType(fileType);
			orderEmail.setStatus(Status);
			orderEmail.setProductLineMatch(productlineMatch);
			orderEmail.setRboMatch(rboMatch);
			orderEmail.setId(attachmentId);
			orderEmail.setComment(comment);
			session.update(orderEmail);
			result=1;
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			throw  e;
		}
		return result;
	}
	
	public boolean updateOrderFileQueueComment(int orderFileQueueId, String comment)throws Exception{
		try{
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			OrderFileQueue orderFileQueue=(OrderFileQueue)session.load(OrderFileQueue.class, orderFileQueueId);
			
			orderFileQueue.setId(orderFileQueueId);
			orderFileQueue.setComment(comment);
			session.update(orderFileQueue);
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			throw  e;
		}
		return true;
	}
}
