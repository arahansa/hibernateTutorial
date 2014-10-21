package util;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistry;

/**
 * @author Deepak Kumar * Web: http://www.roseindia.net 
 *  Update by arahansa@naver.com
 */
public class HibernateTestUtil {
	private static SessionFactory sessionFactory;
	private static String configFile = "hibernate.cfg.xml";
	
	
	public static SessionFactory getSessionFactory(Class<?> clazz) {
		if(sessionFactory==null){
			try {
				configFile = "/" + clazz.getPackage().getName().replace('.', '/') + "/" + configFile;
				Configuration cfg = new Configuration().configure(configFile);
				StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
				sb.applySettings(cfg.getProperties());
				StandardServiceRegistry standardServiceRegistry = sb.build();
				sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
			} catch (Throwable th) {
				System.err.println("Enitial SessionFactory creation failed" + th);
				throw new ExceptionInInitializerError(th);
			}
		}
		
		return sessionFactory;
	}

	public void shutdown() {
		sessionFactory.close();
	}
}
