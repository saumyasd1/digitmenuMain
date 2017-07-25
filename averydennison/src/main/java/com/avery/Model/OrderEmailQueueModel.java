package com.avery.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.adeptia.indigo.logging.Logger;

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
import com.avery.services.OrderEmailQueueServices;
import com.avery.utils.HibernateUtil;

public class OrderEmailQueueModel implements
		OrderEmailQueueInterface {

	public static Logger log = OrderEmailQueueServices.log;

	public HashMap<String, String> emailSource(int id) throws Exception {

		String Source_email = "";
		String subjectRbo = "";
		String subjectProductline = "";
		String subjectPartner = "";
		String BodyProductline = "";
		String Source_email_subject = "";
		HashMap<String, String> emailinfo = new HashMap<String, String>();
		Session session = null;
		try {
			log.debug("get email information \"" + id + "\".");
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria cr = session
					.createCriteria(OrderEmailQueue.class)
					.setProjection(
							Projections
									.projectionList()
									.add(Projections.property("id"), "id")
									// .add(Projections.property("mailBody"),
									// "mailBody")
									.add(Projections.property("subject"),
											"subject")
									.add(Projections
											.property("emailSubjectProductLineMatch"),
											"emailSubjectProductLineMatch")
									.add(Projections
											.property("emailSubjectRBOMatch"),
											"emailSubjectRBOMatch")
									.add(Projections
											.property("emailBodyProductLineMatch"),
											"emailBodyProductLineMatch")
									.add(Projections
											.property("emailSubjectPartnerMatch"),
											"emailSubjectPartnerMatch")
									.add(Projections.property("senderEmailId"),
											"senderEmailId"))
					.setResultTransformer(
							Transformers.aliasToBean(OrderEmailQueue.class));

			cr.add(Restrictions.eq("id", id));
			List<OrderEmailQueue> list = cr.list();

			Iterator<OrderEmailQueue> iterator = list.iterator();
			while (iterator.hasNext()) {
				OrderEmailQueue orderEmailQueue = iterator.next();

				Source_email = orderEmailQueue.getSenderEmailId();
				subjectRbo = orderEmailQueue.getEmailSubjectRBOMatch();
				subjectProductline = orderEmailQueue
						.getEmailSubjectProductLineMatch();
				
				subjectPartner = orderEmailQueue.getEmailSubjectPartnerMatch();
				BodyProductline = orderEmailQueue
						.getEmailBodyProductLineMatch();
				Source_email_subject = orderEmailQueue.getSubject();
			}
			emailinfo.put("source", Source_email);
			emailinfo.put("subject", Source_email_subject);
			emailinfo.put("subjectRbo", subjectRbo);
			emailinfo.put("subjectProductline", subjectProductline);
			emailinfo.put("subjectPartner", subjectPartner);
			emailinfo.put("BodyProductline", BodyProductline);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return email info as subject/body.");
		return emailinfo;

	}

	public int getPartnerId(String email, String domain) {

		int partnerId = 0;
		Session session = null;
		log.debug("get partner id on the basis of email or domain.");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session
					.createCriteria(Partner.class)
					.setProjection(
							Projections
									.projectionList()
									.add(Projections.property("ID"), "ID")
									.add(Projections.property("partnerName"),
											"partnerName"))
					.setResultTransformer(
							Transformers.aliasToBean(Partner.class));
			cr.add(Restrictions.eq("active", true));
			cr.add(Restrictions.eq("emailDomain", domain));
			cr.setMaxResults(1);
			List<Partner> list = cr.list();
			Iterator<Partner> iterator = list.iterator();
			while (iterator.hasNext()) {
				Partner partner = iterator.next();
				if (partner != null) {
					partnerId = (int) partner.getID();
				}
			}
			if (partnerId == 0) {
				Criteria cr1 = session
						.createCriteria(Partner.class)
						.setProjection(
								Projections
										.projectionList()
										.add(Projections.property("ID"), "ID")
										.add(Projections
												.property("partnerName"),
												"partnerName"))
						.setResultTransformer(
								Transformers.aliasToBean(Partner.class));
				cr1.add(Restrictions.eq("active", true));
				cr1.add(Restrictions.eq("emailId", email));
				cr1.setMaxResults(1);
				List<Partner> plist = cr1.list();

				Iterator<Partner> piterator = plist.iterator();
				while (piterator.hasNext()) {
					Partner partnerid = piterator.next();
					if (partnerid != null) {
						partnerId = (int) partnerid.getID();
					}
				}
			}
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return partner id found \"" + partnerId + "\".");
		return partnerId;

	}

	public ArrayList<Object> getPartnerRbo_productlines(String email)
			throws Exception {
		ArrayList<Object> rboproduclines = new ArrayList();
		String emailId = "";
		String domain = "";
		Session session = null;
		log.debug("fetch multiple productlines info from email \"" + email
				+ "\".");
		try {
			if (email.contains("@")) {
				String[] p_email = email.split("@");
				emailId = p_email[0];
				domain = p_email[1];
			}
			domain = "*@" + domain;
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class);
			cr.add(Restrictions.eq("active", true));
			cr.add(Restrictions.like("email", "%" + email + "%"));
			rboproduclines = (ArrayList<Object>) cr.list();
			// rboproduclines=(ArrayList<Object>) cr.list();
			if (rboproduclines.size() == 0) {
				Criteria cr1 = session
						.createCriteria(Partner_RBOProductLine.class);
				cr1.add(Restrictions.eq("active", true));
				cr1.add(Restrictions.like("email", "%" + domain + "%"));
				//List<Partner_RBOProductLine> p_list = cr1.list();
				rboproduclines = (ArrayList<Object>) cr1.list();
			}
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return productlines.");
		return rboproduclines;

	}

	public ArrayList<Object> getPartner_productline(int productlineId)
			throws Exception {
		ArrayList<Object> rboproduclines = new ArrayList<Object>();
		Session session = null;
		log.debug("fetch productline info from productlineId \""
				+ productlineId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Partner_RBOProductLine.class);
			cr.add(Restrictions.eq("active", true));
			cr.add(Restrictions.eq("id", productlineId));
			List<Partner_RBOProductLine> list = cr.list();
			rboproduclines = (ArrayList<Object>) cr.list();
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return productline info.");
		return rboproduclines;
	}

	public Partner_RBOProductLine getProductlineInfo(int productlineId)
			throws Exception {
		ArrayList<Partner_RBOProductLine> rboproduclines = new ArrayList<Partner_RBOProductLine>();
		Session session = null;
		Partner_RBOProductLine schemaInfo = null;
		log.debug("fetch productline info from productlineId \""
				+ productlineId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			schemaInfo = (Partner_RBOProductLine) session.load(
					Partner_RBOProductLine.class, productlineId);

			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return productline info.");
		return schemaInfo;
	}

	public ArrayList<Object> getEmailAttachments(int orderEmailId)
			throws Exception {
		ArrayList<Object> EmailAttachments = new ArrayList();
		Session session = null;
		log.debug("fetch attachments for order email id \"" + orderEmailId
				+ "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session
					.createCriteria(OrderFileAttachment.class)
					.setProjection(
							Projections
									.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("fileExtension"),
											"fileExtension")
									.add(Projections.property("fileName"),
											"fileName")
									.add(Projections.property("status"),
											"status")
									.add(Projections.property("comment"),
											"comment")
									.add(Projections
											.property("fileContentMatch"),
											"fileContentMatch")
									.add(Projections
											.property("fileContentType"),
											"fileContentType")
									.add(Projections.property("varProductLine"),
											"varProductLine")
									.add(Projections.property("filePath"),
											"filePath"))
					.setResultTransformer(
							Transformers.aliasToBean(OrderFileAttachment.class));
			cr.add(Restrictions.eq("varOrderEmailQueue.id", orderEmailId));
			List<OrderFileAttachment> list = cr.list();
			EmailAttachments = (ArrayList<Object>) cr.list();
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return email attachments.");
		return EmailAttachments;
	}

	public ArrayList<Object> getEmailAttachments(int orderEmailId,
			String mailbody) throws Exception {
		ArrayList<Object> EmailAttachments = new ArrayList();
		Session session = null;
		log.debug("fetch attachments for order email id \"" + orderEmailId
				+ "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session
					.createCriteria(OrderFileAttachment.class)
					.setProjection(
							Projections
									.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("fileExtension"),
											"fileExtension")
									.add(Projections.property("fileName"),
											"fileName")
									.add(Projections.property("status"),
											"status")
									.add(Projections.property("comment"),
											"comment")
									.add(Projections
											.property("fileContentMatch"),
											"fileContentMatch")
									.add(Projections
											.property("fileContentType"),
											"fileContentType")
									.add(Projections.property("varProductLine"),
											"varProductLine")
									.add(Projections.property("filePath"),
											"filePath"))
					.setResultTransformer(
							Transformers.aliasToBean(OrderFileAttachment.class));
			cr.add(Restrictions.like("fileName", "%" + mailbody + "%"));
			cr.add(Restrictions.eq("varOrderEmailQueue.id", orderEmailId));

			// cr.add(Restrictions.eq("OrderFileAttachment.id", orderEmailId));
			List<OrderFileAttachment> list = cr.list();
			EmailAttachments = (ArrayList<Object>) cr.list();
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return email attachments.");
		return EmailAttachments;
	}

	public int updateOrderEmail(int orderEmailId, String orderEmailStatus,
			String subject_rboMatch, String subject_productlineMatch,
			String subjectPartnerMatch, String body_productlineMatch,
			String comment) throws Exception {

		int result = 0;
		Session session = null;
		log.debug("update order email on the basis of id \"" + orderEmailId
				+ "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderEmailQueue orderEmail = (OrderEmailQueue) session.load(
					OrderEmailQueue.class, orderEmailId);
			if (orderEmailStatus.length() != 0) {
				orderEmail.setStatus(orderEmailStatus);
			}
			if (body_productlineMatch.length() != 0) {
				body_productlineMatch = body_productlineMatch.trim();
				if (body_productlineMatch.endsWith(",")) {
					body_productlineMatch = body_productlineMatch.substring(0,
							body_productlineMatch.lastIndexOf(","));
				}
				if (body_productlineMatch.startsWith(",")) {
					body_productlineMatch = body_productlineMatch
							.substring(body_productlineMatch.indexOf(",") + 1);
				}
				orderEmail.setEmailBodyProductLineMatch(body_productlineMatch);
			}
			if (subjectPartnerMatch.length() != 0) {
				subjectPartnerMatch = subjectPartnerMatch.trim();
				if (subjectPartnerMatch.endsWith(",")) {
					subjectPartnerMatch = subjectPartnerMatch.substring(0,
							subjectPartnerMatch.lastIndexOf(","));
				}
				if (subjectPartnerMatch.startsWith(",")) {
					subjectPartnerMatch = subjectPartnerMatch
							.substring(subjectPartnerMatch.indexOf(",") + 1);
				}
				orderEmail.setEmailSubjectPartnerMatch(subjectPartnerMatch);
			}
			if (subject_productlineMatch.length() != 0) {
				subject_productlineMatch = subject_productlineMatch.trim();
				if (subject_productlineMatch.endsWith(",")) {
					subject_productlineMatch = subject_productlineMatch
							.substring(0,
									subject_productlineMatch.lastIndexOf(","));
				}
				if (subject_productlineMatch.startsWith(",")) {
					subject_productlineMatch = subject_productlineMatch
							.substring(subject_productlineMatch.indexOf(",") + 1);
				}
				orderEmail
						.setEmailSubjectProductLineMatch(subject_productlineMatch);
			}
			if (subject_rboMatch.length() != 0) {
				subject_rboMatch = subject_rboMatch.trim();
				if (subject_rboMatch.endsWith(",")) {
					subject_rboMatch = subject_rboMatch.substring(0,
							subject_rboMatch.lastIndexOf(","));
				}
				if (subject_rboMatch.startsWith(",")) {
					subject_rboMatch = subject_rboMatch
							.substring(subject_rboMatch.indexOf(",") + 1);
				}

				orderEmail.setEmailSubjectRBOMatch(subject_rboMatch);
			}
			if (comment.length() != 0) {
				orderEmail.setComment(comment);
			}
			session.persist(orderEmail);
			result = 1;
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("OrderEmailQueue update successfull.");
		return result;
	}

	public int updateOrderEmailAttachment(int attachmentId, int productlineId,
			String Status, String rboMatch, String productlineMatch,
			String comment, String contentMatch) throws Exception {
		int result = 0;
		Session session = null;
		log.debug("update OrderFileAttachment on the basis of id \""
				+ attachmentId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail = (OrderFileAttachment) session
					.load(OrderFileAttachment.class, attachmentId);

			if (productlineId != 0) {
				Partner_RBOProductLine pline = (Partner_RBOProductLine) session
						.load(Partner_RBOProductLine.class, productlineId);
				pline.setId(productlineId);
				orderEmail.setVarProductLine(pline);
			}

			orderEmail.setStatus(Status);
			if (!productlineMatch.isEmpty() && productlineMatch != "") {
				productlineMatch = productlineMatch.trim();
				if (productlineMatch.endsWith(",")) {
					productlineMatch = productlineMatch.substring(0,
							productlineMatch.lastIndexOf(","));
				}
				if (productlineMatch.startsWith(",")) {
					productlineMatch = productlineMatch
							.substring(productlineMatch.indexOf(",") + 1);
				}
				orderEmail.setProductLineMatch(productlineMatch);
			}
			if (!rboMatch.isEmpty() && rboMatch != "") {
				rboMatch = rboMatch.trim();
				if (rboMatch.endsWith(",")) {
					rboMatch = rboMatch.substring(0, rboMatch.lastIndexOf(","));
				}
				if (rboMatch.startsWith(",")) {
					rboMatch = rboMatch.substring(rboMatch.indexOf(",") + 1);
				}
				orderEmail.setRboMatch(rboMatch);
			}
			orderEmail.setId(attachmentId);
			comment = comment.trim();
			if (comment.endsWith(",")) {
				comment = comment.substring(0, comment.lastIndexOf(","));
			}
			if (comment.startsWith(",")) {
				comment = comment.substring(comment.indexOf(",") + 1);
			}
			orderEmail.setComment(comment);
			if (!contentMatch.isEmpty() || contentMatch != "") {
				contentMatch = contentMatch.trim();
				if (contentMatch.endsWith(",")) {
					contentMatch = contentMatch.substring(0,
							contentMatch.lastIndexOf(","));
				}
				if (contentMatch.startsWith(",")) {
					contentMatch = contentMatch.substring(contentMatch
							.indexOf(",") + 1);
				}
				orderEmail.setFileContentMatch(contentMatch);
			}
			session.update(orderEmail);
			result = 1;

			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("OrderFileAttachment update successfull.");
		return result;
	}
	
	
	public int updateOrderEmailAttachment(int attachmentId, int productlineId,
			String Status, String rboMatch, String productlineMatch,
			String comment, String contentMatch, String partnerMatch,
			String fileType) throws Exception {
		int result = 0;
		Session session = null;
		log.debug("update OrderFileAttachment on the basis of id \""
				+ attachmentId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail = (OrderFileAttachment) session
					.load(OrderFileAttachment.class, attachmentId);

			if (productlineId != 0) {
				Partner_RBOProductLine pline = (Partner_RBOProductLine) session
						.load(Partner_RBOProductLine.class, productlineId);
				pline.setId(productlineId);
				orderEmail.setVarProductLine(pline);
			}
			if (!Status.isEmpty() && Status != "") {
				orderEmail.setStatus(Status);
			}
			if (!productlineMatch.isEmpty() && productlineMatch != "") {
				productlineMatch = productlineMatch.trim();
				if (productlineMatch.endsWith(",")) {
					productlineMatch = productlineMatch.substring(0,
							productlineMatch.lastIndexOf(","));
				}
				if (productlineMatch.startsWith(",")) {
					productlineMatch = productlineMatch.substring(productlineMatch
							.indexOf(",") + 1);
				}
	
				orderEmail.setProductLineMatch(productlineMatch);
			}
			if (!rboMatch.isEmpty() && rboMatch != "") {
				if (rboMatch.endsWith(",")) {
					rboMatch = rboMatch.substring(0, rboMatch.lastIndexOf(","));
				}
				if (rboMatch.startsWith(",")) {
					rboMatch = rboMatch.substring(rboMatch.indexOf(",") + 1);
				}
				orderEmail.setRboMatch(rboMatch);
			}
			if (!partnerMatch.isEmpty() && partnerMatch != "") {
				if (partnerMatch.endsWith(",")) {
					partnerMatch = partnerMatch.substring(0,
							partnerMatch.lastIndexOf(","));
				}
				if (partnerMatch.startsWith(",")) {
					partnerMatch = partnerMatch
							.substring(partnerMatch.indexOf(",") + 1);
				}
				orderEmail.setPartnerMatch(partnerMatch);
			}
			
			orderEmail.setId(attachmentId);
			if (!comment.isEmpty() && comment != "") {
				comment = comment.trim();
				if (comment.endsWith(",")) {
					comment = comment.substring(0, comment.lastIndexOf(","));
				}
				if (comment.startsWith(",")) {
					comment = comment.substring(comment.indexOf(",") + 1);
				}
				orderEmail.setComment(comment);
			}
			if (!contentMatch.isEmpty() || contentMatch != "") {
				contentMatch = contentMatch.trim();
				if (contentMatch.endsWith(",")) {
					contentMatch = contentMatch.substring(0,
							contentMatch.lastIndexOf(","));
				}
				if (contentMatch.startsWith(",")) {
					contentMatch = contentMatch.substring(contentMatch
							.indexOf(",") + 1);
				}

				orderEmail.setFileContentMatch(contentMatch);
			}
			if (!fileType.isEmpty() || fileType != "") {
				fileType = fileType.trim();
				orderEmail.setFileContentType(fileType);
			}
			session.persist(orderEmail);

			// session.update(orderEmail);
			result = 1;

			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("OrderFileAttachment update successfull.");
		return result;
	}


	/*public int updateOrderEmailAttachment(int attachmentId, int productlineId,
			String Status, String rboMatch, String productlineMatch,
			String comment, String contentMatch, String partnerMatch,
			String fileType) throws Exception {
		int result = 0;
		Session session = null;
		log.debug("update OrderFileAttachment on the basis of id \""
				+ attachmentId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail = (OrderFileAttachment) session
					.load(OrderFileAttachment.class, attachmentId);

			if (productlineId != 0) {
				Partner_RBOProductLine pline = (Partner_RBOProductLine) session
						.load(Partner_RBOProductLine.class, productlineId);
				pline.setId(productlineId);
				orderEmail.setVarProductLine(pline);
			}
			if (!Status.isEmpty() && Status != "") {
				orderEmail.setStatus(Status);
			}
			productlineMatch = productlineMatch.trim();
			if (productlineMatch.endsWith(",")) {
				productlineMatch = productlineMatch.substring(0,
						productlineMatch.lastIndexOf(","));
			}
			if (productlineMatch.startsWith(",")) {
				productlineMatch = productlineMatch.substring(productlineMatch
						.indexOf(",") + 1);
			}

			orderEmail.setProductLineMatch(productlineMatch);
			if (rboMatch.endsWith(",")) {
				rboMatch = rboMatch.substring(0, rboMatch.lastIndexOf(","));
			}
			if (rboMatch.startsWith(",")) {
				rboMatch = rboMatch.substring(rboMatch.indexOf(",") + 1);
			}
			orderEmail.setRboMatch(rboMatch);
			if (partnerMatch.endsWith(",")) {
				partnerMatch = partnerMatch.substring(0,
						partnerMatch.lastIndexOf(","));
			}
			if (partnerMatch.startsWith(",")) {
				partnerMatch = partnerMatch
						.substring(partnerMatch.indexOf(",") + 1);
			}
			orderEmail.setPartnerMatch(partnerMatch);
			orderEmail.setId(attachmentId);
			comment = comment.trim();
			if (comment.endsWith(",")) {
				comment = comment.substring(0, comment.lastIndexOf(","));
			}
			if (comment.startsWith(",")) {
				comment = comment.substring(comment.indexOf(",") + 1);
			}
			orderEmail.setComment(comment);
			if (!contentMatch.isEmpty() || contentMatch != "") {
				contentMatch = contentMatch.trim();
				if (contentMatch.endsWith(",")) {
					contentMatch = contentMatch.substring(0,
							contentMatch.lastIndexOf(","));
				}
				if (contentMatch.startsWith(",")) {
					contentMatch = contentMatch.substring(contentMatch
							.indexOf(",") + 1);
				}

				orderEmail.setFileContentMatch(contentMatch);
			}
			if (!fileType.isEmpty() || fileType != "") {
				fileType = fileType.trim();
				orderEmail.setFileContentType(fileType);
			}
			session.persist(orderEmail);

			// session.update(orderEmail);
			result = 1;

			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("OrderFileAttachment update successfull.");
		return result;
	}*/

	public boolean updateAllAttachment(int email_id, int productlineId,
			String Status, String comment) throws Exception {
		Session session = null;
		log.debug("update OrderFileAttachment on the basis of id \"" + email_id
				+ "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(OrderFileAttachment.class);
			cr.add(Restrictions.eq("varOrderEmailQueue.id", email_id));
			List<OrderFileAttachment> list = cr.list();
			Iterator<OrderFileAttachment> iterator = list.iterator();
			while (iterator.hasNext()) {
				OrderFileAttachment orderFileAttachment = iterator.next();
				orderFileAttachment.getId();
				if (productlineId != 0) {
					Partner_RBOProductLine pline = (Partner_RBOProductLine) session
							.load(Partner_RBOProductLine.class, productlineId);
					pline.setId(productlineId);
					orderFileAttachment.setVarProductLine(pline);
				}
				orderFileAttachment.setStatus(Status);
				comment = comment.trim();
				if (comment.endsWith(",")) {
					comment = comment.substring(0, comment.lastIndexOf(","));
				}
				if (comment.startsWith(",")) {
					comment = comment.substring(comment.indexOf(",") + 1);
				}
				orderFileAttachment.setComment(comment);
				session.persist(orderFileAttachment);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("OrderFileAttachment update successfull.");
		return true;
	}

	public int updateError(String ErrorCategory, String description)
			throws Exception {

		int result = 0;
		Session session = null;
		log.debug("update error.");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			ErrorLog errorLog = new ErrorLog();
			errorLog.setErrorCategory(ErrorCategory);
			errorLog.setErrorContent(description);
			session.persist(errorLog);
			result = 1;
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("error update successfull.");
		return result;
	}

	public boolean updateAttachmenttype(int att_id, String contentType)
			throws Exception {
		Session session = null;
		log.debug("update Attachmenttype on the basis of id \"" + att_id
				+ "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail = (OrderFileAttachment) session
					.load(OrderFileAttachment.class, att_id);
			orderEmail.setId(att_id);
			orderEmail.setFileContentType(contentType);
			session.update(orderEmail);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("OrderFileAttachment update successfull.");
		return true;
	}

	/**
	 * get attachment id andorderemailqueue id for order file queue author
	 * Dipanshu Ahuja
	 * **/
	public int GeteAttachmentId(int fileQueueId) throws Exception {
		int attachment_id = 0;
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment();
		Session session = null;
		log.debug("fetch OrderFileAttachment id on the basis of file queue id \""
				+ fileQueueId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileQueue orderfilequeue = (OrderFileQueue) session.load(
					OrderFileQueue.class, fileQueueId);
			orderFileAttachment = orderfilequeue.getVarOrderFileAttachment();
			if (orderFileAttachment != null) {
				attachment_id = (int) orderFileAttachment.getId();
			}
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("attachment id fetch successfull \"" + attachment_id + "\".");
		return attachment_id;
	}

	public HashMap<String, Integer> GetOrderEmailQueueId(int att_id)
			throws Exception {
		int orderEmailQueueid = 0;
		Session session = null;
		log.debug("fetch order email queue  on the basis of attachment id \""
				+ att_id + "\".");
		HashMap<String, Integer> emailatt_info = new HashMap<String, Integer>();
		OrderEmailQueue orderEmailQueue = new OrderEmailQueue();
		Partner_RBOProductLine partner_RBOProductLine = new Partner_RBOProductLine();
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderFileAttachment = (OrderFileAttachment) session
					.load(OrderFileAttachment.class, att_id);
			orderEmailQueue = orderFileAttachment.getVarOrderEmailQueue();
			if (orderEmailQueue != null) {
				orderEmailQueueid = orderEmailQueue.getId();
			}
			partner_RBOProductLine = orderFileAttachment.getVarProductLine();
			int schema_id = 0;
			if (partner_RBOProductLine != null) {
				schema_id = partner_RBOProductLine.getId();
			}
			emailatt_info.put("emailQueue_id", orderEmailQueueid);
			emailatt_info.put("schema_id", schema_id);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("fetch order email successfull.");
		return emailatt_info;
	}

	public ArrayList<Object> GetEmailAttachmentDetail(int orderEmailId)
			throws Exception {
		Session session = null;
		log.debug("fetch OrderFileAttachment on the basis of order email id \""
				+ orderEmailId + "\".");
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment();
		ArrayList<Object> EmailAttachments = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session
					.createCriteria(OrderFileAttachment.class)
					.setProjection(
							Projections
									.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("fileExtension"),
											"fileExtension")
									.add(Projections.property("fileName"),
											"fileName")
									.add(Projections
											.property("fileContentMatch"),
											"fileContentMatch")
									.add(Projections
											.property("fileContentType"),
											"fileContentType")
									.add(Projections.property("varProductLine"),
											"varProductLine")
									.add(Projections.property("filePath"),
											"filePath"))
					.setResultTransformer(
							Transformers.aliasToBean(OrderFileAttachment.class));
			cr.add(Restrictions.eq("varOrderEmailQueue.id", orderEmailId));

			OrderFileAttachment ofa = (OrderFileAttachment) cr.list().get(0);
			EmailAttachments.add(ofa);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("fetch OrderFileAttachment successfull.");
		return EmailAttachments;
	}

	public int updateOrderEmailAttachmentContent(int attachmentId,
			int productlineId, String Status, String rboMatch,
			String productlineMatch, String comment, String fileType,
			String fileContentMatch) throws Exception {
		int result = 0;
		Session session = null;
		log.debug("update  orderEmail on the basis of attachment id \""
				+ attachmentId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileAttachment orderEmail = (OrderFileAttachment) session
					.load(OrderFileAttachment.class, attachmentId);
			if (productlineId != 0) {
				Partner_RBOProductLine pline = (Partner_RBOProductLine) session
						.load(Partner_RBOProductLine.class, productlineId);
				pline.setId(productlineId);
				orderEmail.setVarProductLine(pline);
			}
			if (!fileContentMatch.isEmpty() || fileContentMatch != ""
					|| fileContentMatch != null) {
				orderEmail.setFileContentMatch(fileContentMatch.trim());
			}
			if (fileType != "") {
				orderEmail.setFileContentType(fileType);
			}
			if (Status != "") {
				orderEmail.setStatus(Status);
			}

			if (!productlineMatch.isEmpty() && productlineMatch != "") {
				orderEmail.setProductLineMatch(productlineMatch);
			}
			if (!rboMatch.isEmpty() && rboMatch != "") {
				orderEmail.setRboMatch(rboMatch);
			}

			orderEmail.setId(attachmentId);
			if (comment != "") {
				comment = comment.trim();
				if (comment.endsWith(",")) {
					comment = comment.substring(0, comment.lastIndexOf(","));
				}
				if (comment.startsWith(",")) {
					comment = comment.substring(comment.indexOf(",") + 1);
				}
				orderEmail.setComment(comment);
			}
			session.update(orderEmail);
			result = 1;
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("orderEmail update successfull.");
		return result;
	}

	public boolean updateOrderFileQueueComment(int orderFileQueueId,
			String comment) throws Exception {
		Session session = null;
		log.debug("update updateOrderFileQueueComment on the basis of orderFileQueueId \""
				+ orderFileQueueId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			OrderFileQueue orderFileQueue = (OrderFileQueue) session.load(
					OrderFileQueue.class, orderFileQueueId);
			orderFileQueue.setId(orderFileQueueId);
			orderFileQueue.setComment(comment);
			session.update(orderFileQueue);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("Order File Queue Comment update successfull.");
		return true;
	}

	public ArrayList<Object> GetEmailBody(int orderEmailId) throws Exception {
		ArrayList<Object> EmailAttachments = new ArrayList();
		Session session = null;
		log.debug("Get emailbody on the basis of order email id \""
				+ orderEmailId + "\".");
		try {
			SessionFactory sessionFactory = HibernateUtil
					.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session
					.createCriteria(OrderFileAttachment.class)
					.setProjection(
							Projections
									.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("fileName"),
											"fileName")
									.add(Projections.property("filePath"),
											"filePath"))
					.setResultTransformer(
							Transformers.aliasToBean(OrderFileAttachment.class));
			cr.add(Restrictions.like("fileName", "%" + "CompleteEmail" + "%"));
			cr.add(Restrictions.eq("varOrderEmailQueue.id", orderEmailId));
			List<OrderFileAttachment> list = cr.list();
			EmailAttachments = (ArrayList<Object>) cr.list();
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
		}
		log.debug("return email attachments.");
		return EmailAttachments;
	}
}
