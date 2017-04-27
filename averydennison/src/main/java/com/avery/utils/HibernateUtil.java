package com.avery.utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.avery.Model.OrderEmailQueueModel;
import com.avery.config.ConfigService;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	File file = new File(ConfigService.getConfigFilePath());
			SessionFactory sessionFactory = new Configuration().configure(file).buildSessionFactory();
            /*Configuration configuration = new Configuration();
            configuration.configure(file);
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();
            return configuration.buildSessionFactory(serviceRegistry);*/
        	return sessionFactory;
        } catch (Throwable ex) {
           log.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
