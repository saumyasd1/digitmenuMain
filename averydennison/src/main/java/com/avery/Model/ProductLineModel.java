package com.avery.Model;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.adeptia.indigo.logging.Logger;
import com.avery.dao.Partner_RBOProductLine;
import com.avery.services.OrderEmailQueueServices;
import com.avery.utils.HibernateUtil;

public class ProductLineModel {

	public static Logger log = OrderEmailQueueServices.log;

	public ArrayList<Object> getPartnerRbo_productlines(String email)
			throws Exception {
		ArrayList<Object> rboproduclines = new ArrayList<Object>();
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
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session
					.createCriteria(Partner_RBOProductLine.class)
					.setProjection(
							Projections
									.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("attachmentFileMatchLocation"),
											"attachmentFileMatchLocation")
											.add(Projections.property("attachmentFileMatchRequired"), "attachmentFileMatchRequired")
											.add(Projections.property("attachmentFileOrderMatch"), "attachmentFileOrderMatch")
											.add(Projections.property("attachmentFileOrderMatchLocation"), "attachmentFileOrderMatchLocation")
											.add(Projections.property("attachmentRequired"), "attachmentRequired")
											.add(Projections.property("attachmentFileNameExtension_1"), "attachmentFileNameExtension_1")
											.add(Projections.property("emailBodyPartnerMatch"), "emailBodyPartnerMatch")
											.add(Projections.property("emailBodyPartnerRequired"), "emailBodyPartnerRequired")
											.add(Projections.property("emailBodyProductLineMatch"), "emailBodyProductLineMatch")
											.add(Projections.property("emailBodyProductlineMatchRequired"), "emailBodyProductlineMatchRequired")
											.add(Projections.property("emailBodyRBOMatch"), "emailBodyRBOMatch")
											.add(Projections.property("emailBodyRBOMatchRequired"), "emailBodyRBOMatchRequired")
											.add(Projections.property("emailSubjectPartnerMatch"), "emailSubjectPartnerMatch")
											.add(Projections.property("emailSubjectPartnerRequired"), "emailSubjectPartnerRequired")
											.add(Projections.property("emailSubjectProductLineMatch"), "emailSubjectProductLineMatch")
											.add(Projections.property("emailSubjectProductlineMatchRequired"), "emailSubjectProductlineMatchRequired")
											.add(Projections.property("emailSubjectRBOMatch"), "emailSubjectRBOMatch")
											.add(Projections.property("emailSubjectRBOMatchRequired"), "emailSubjectRBOMatchRequired")
											.add(Projections.property("fileOrderMatch"), "fileOrderMatch")
											.add(Projections.property("fileOrderMatchLocation"), "fileOrderMatchLocation")
											.add(Projections.property("fileOrderMatchRequired"), "fileOrderMatchRequired")
											.add(Projections.property("fileOrderPartnerMatch"), "fileOrderPartnerMatch")
											.add(Projections.property("fileOrderPartnerRequired"), "fileOrderPartnerRequired")
											.add(Projections.property("fileProductlineMatch"), "fileProductlineMatch")
											.add(Projections.property("fileProductLineMatchRequired"), "fileProductLineMatchRequired")
											.add(Projections.property("fileRBOMatch"), "fileRBOMatch")
											.add(Projections.property("fileRBOMatchRequired"), "fileRBOMatchRequired")
											.add(Projections.property("OrderInEmailBodyMatch"), "OrderInEmailBodyMatch")
											.add(Projections.property("OrderInEmailSubjectMatch"), "OrderInEmailSubjectMatch")
											.add(Projections.property("orderInMailBody"), "orderInMailBody")
											.add(Projections.property("orderFileNamePattern"),"orderFileNamePattern")
											.add(Projections.property("active"),"active")
											.add(Projections.property("email"),"email")
											.add(Projections.property("orderFileNameExtension"), "orderFileNameExtension"))
					.setResultTransformer(
							Transformers.aliasToBean(Partner_RBOProductLine.class));
			cr.add(Restrictions.eq("active", true));
			cr.add(Restrictions.like("email", "%" + email + "%"));
			cr.setCacheable(false);
			rboproduclines = (ArrayList<Object>) cr.list();
			session.getTransaction().commit();
			if (rboproduclines.size() == 0) {
				log.debug("no result found for email match. \"" + email
						+ "\".");
				session.beginTransaction();
				Criteria cr1 = session
						.createCriteria(Partner_RBOProductLine.class)
						.setProjection(
								Projections
										.projectionList()
										.add(Projections.property("id"), "id")
										.add(Projections.property("attachmentFileMatchLocation"),
												"attachmentFileMatchLocation")
												.add(Projections.property("attachmentFileMatchRequired"), "attachmentFileMatchRequired")
												.add(Projections.property("attachmentFileOrderMatch"), "attachmentFileOrderMatch")
												.add(Projections.property("attachmentFileOrderMatchLocation"), "attachmentFileOrderMatchLocation")
												.add(Projections.property("attachmentRequired"), "attachmentRequired")
												.add(Projections.property("attachmentFileNameExtension_1"), "attachmentFileNameExtension_1")
												.add(Projections.property("emailBodyPartnerMatch"), "emailBodyPartnerMatch")
												.add(Projections.property("emailBodyPartnerRequired"), "emailBodyPartnerRequired")
												.add(Projections.property("emailBodyProductLineMatch"), "emailBodyProductLineMatch")
												.add(Projections.property("emailBodyProductlineMatchRequired"), "emailBodyProductlineMatchRequired")
												.add(Projections.property("emailBodyRBOMatch"), "emailBodyRBOMatch")
												.add(Projections.property("emailBodyRBOMatchRequired"), "emailBodyRBOMatchRequired")
												.add(Projections.property("emailSubjectPartnerMatch"), "emailSubjectPartnerMatch")
												.add(Projections.property("emailSubjectPartnerRequired"), "emailSubjectPartnerRequired")
												.add(Projections.property("emailSubjectProductLineMatch"), "emailSubjectProductLineMatch")
												.add(Projections.property("emailSubjectProductlineMatchRequired"), "emailSubjectProductlineMatchRequired")
												.add(Projections.property("emailSubjectRBOMatch"), "emailSubjectRBOMatch")
												.add(Projections.property("emailSubjectRBOMatchRequired"), "emailSubjectRBOMatchRequired")
												.add(Projections.property("fileOrderMatch"), "fileOrderMatch")
												.add(Projections.property("fileOrderMatchLocation"), "fileOrderMatchLocation")
												.add(Projections.property("fileOrderMatchRequired"), "fileOrderMatchRequired")
												.add(Projections.property("fileOrderPartnerMatch"), "fileOrderPartnerMatch")
												.add(Projections.property("fileOrderPartnerRequired"), "fileOrderPartnerRequired")
												.add(Projections.property("fileProductlineMatch"), "fileProductlineMatch")
												.add(Projections.property("fileProductLineMatchRequired"), "fileProductLineMatchRequired")
												.add(Projections.property("fileRBOMatch"), "fileRBOMatch")
												.add(Projections.property("fileRBOMatchRequired"), "fileRBOMatchRequired")
												.add(Projections.property("OrderInEmailBodyMatch"), "OrderInEmailBodyMatch")
												.add(Projections.property("OrderInEmailSubjectMatch"), "OrderInEmailSubjectMatch")
												.add(Projections.property("orderInMailBody"), "orderInMailBody")
												.add(Projections.property("active"),"active")
												.add(Projections.property("email"),"email")
												.add(Projections.property("orderFileNamePattern"),"orderFileNamePattern")
												.add(Projections.property("orderFileNameExtension"), "orderFileNameExtension"))
						.setResultTransformer(
								Transformers.aliasToBean(Partner_RBOProductLine.class));
				cr1.add(Restrictions.eq("active", true));
				cr1.add(Restrictions.like("email", "%" + domain + "%"));
				cr1.setCacheable(false);
				rboproduclines = (ArrayList<Object>) cr1.list();
				session.getTransaction().commit();
			}
			else{
				log.debug("Result found for complete  email match. \"" + email
						+ "\".");
			}
			
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				//session.disconnect();
				session.flush();
				session.clear(); // Clearing the session object
				session.close();
			}
		}
		log.debug("return productlines.");
		return rboproduclines;

	}
}
