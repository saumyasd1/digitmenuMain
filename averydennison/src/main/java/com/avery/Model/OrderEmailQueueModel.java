package com.avery.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.dao.OrderFileAttachment;
import com.avery.dao.OrderEmailQueue;
import com.avery.utils.HibernateUtil;

public class OrderEmailQueueModel {
	public static void createOrderEmailQueue(){
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQue=new OrderEmailQueue("Subject", "Sender", "MailBody");
		
		
		
		OrderFileAttachment orderFileAtt=new OrderFileAttachment();
		orderFileAtt.setFilePath("kjhlkm");
		orderFileAtt.setFileData("kjhlkm4154");
		

		orderFileAtt.setVarOrderEmailQueue(orderEmailQue);
		orderEmailQue.getVarOrderFileAttachment().add(orderFileAtt);
		
		session.persist(orderEmailQue);
		session.getTransaction().commit();
		session.close();

	}
	public static void insertOrderEmailQueue(){
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="INSERT INTO orderemailqueue (acknowledgementDate, assignCSR, assignee, ccMailID, comment, createdByName, createdDate, lastModifiedByName, lastModifiedDate, mailBody, orderMail, processID, readDate, receivedDate, senderEmailID, status, subject, tOMailID)"
				+ " VALUES ('', '', '', '', '', '', '', '', '', '', , '', '', '', '', '', '', '')";
		Query query=session.createQuery(hql);
		int result=query.executeUpdate();
		
	}
	public static void selectOrderEmailQueue(){
		List  list=new ArrayList();
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="";
		Query query=session.createQuery(hql);
		list=query.list();
		System.out.println(list);
		/*Iterator it=list.iterator();
		while(it.hasNext()){
			System.out.println();
		}*/
	}
}
