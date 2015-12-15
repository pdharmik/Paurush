package com.lexmark.service.impl.real.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Basic Hibernate helper class, handles SessionFactory, Session and Transaction.
 * <p>
 * Uses a static initializer for the initial SessionFactory creation
 * and holds Session and Transactions in thread local variables. All
 * exceptions are wrapped in an unchecked InfrastructureException.
 *
 * @author Alex zhou
 */
@SuppressWarnings("unchecked")
public class HibernateUtil3 {
	private static Logger LOGGER = LogManager.getLogger(HibernateUtil3.class);

	//private static Log log = LogFactory.getLog(HibernateUtil3.class);
	private static Logger log = LogManager.getLogger(HibernateUtil3.class);

	private static Configuration configuration3;
	private static SessionFactory sessionFactory3 = null;
	private static final ThreadLocal threadSession3 = new ThreadLocal();
	private static final ThreadLocal threadTransaction3 = new ThreadLocal();
	private static final ThreadLocal threadInterceptor3 = new ThreadLocal();

	// Create the initial SessionFactory from the default configuration files
	/*static {
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure().buildSessionFactory();
			// We could also let Hibernate bind it to JNDI:
			// configuration.configure().buildSessionFactory()
		} catch (Throwable ex) {
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			//log.error("Building SessionFactory failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}*/

	/**
	 * Returns the SessionFactory used for this static class.
	 *
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		/* Instead of a static variable, use JNDI:
		SessionFactory sessions = null;
		try {
			Context ctx = new InitialContext();
			String jndiName = "java:hibernate/HibernateFactory";
			sessions = (SessionFactory)ctx.lookup(jndiName);
		} catch (NamingException ex) {
			throw new InfrastructureException(ex);
		}
		return sessions;
		*/
		if(sessionFactory3 == null) {
			try {
				configuration3 = new Configuration();
				sessionFactory3 = configuration3.configure("hibernate3.cfg.xml").buildSessionFactory();
				LOGGER.info("Created session factory for 3 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				// We could also let Hibernate bind it to JNDI:
				// configuration.configure().buildSessionFactory()
			} catch (Throwable ex) {
				// We have to catch Throwable, otherwise we will miss
				// NoClassDefFoundError and other subclasses of Error
				//log.error("Building SessionFactory failed.", ex);
				throw new ExceptionInInitializerError(ex);
			}			
		}
		return sessionFactory3;
	}

	/**
	 * Returns the original Hibernate configuration.
	 *
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		return configuration3;
	}

	/**
	 * Rebuild the SessionFactory with the static Configuration.
	 *
	 */
	 public static void rebuildSessionFactory()
		throws InfrastructureException {
		synchronized(sessionFactory3) {
			try {
				sessionFactory3 = getConfiguration().buildSessionFactory();
			} catch (Exception ex) {
				throw new InfrastructureException(ex);
			}
		}
	 }

	/**
	 * Rebuild the SessionFactory with the given Hibernate Configuration.
	 *
	 * @param cfg
	 */
	 public static void rebuildSessionFactory(Configuration cfg)
		throws InfrastructureException {
		synchronized(sessionFactory3) {
			try {
				sessionFactory3 = cfg.buildSessionFactory();
				configuration3 = cfg;
			} catch (Exception ex) {
				throw new InfrastructureException(ex);
			}
		}
	 }

	/**
	 * Retrieves the current Session local to the thread.
	 * <p/>
	 * If no Session is open, opens a new Session for the running thread.
	 *
	 * @return Session
	 */
	public static Session getSession()
		throws InfrastructureException {
		Session s = (Session) threadSession3.get();
		try {
			log.debug(s);
			//TODO: Bruce, please confirm whether the piece of code "||!s.isOpen()" is correct.
			if (s == null||!s.isOpen()) {
				log.debug("Opening new Session for this thread.");
				if (getInterceptor() != null) {
					log.debug("Using interceptor: " + getInterceptor().getClass());
					s = getSessionFactory().openSession(getInterceptor());
				} else {
					s = getSessionFactory().openSession();
				}
				threadSession3.set(s);
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return s;
	}

	/**
	 * Closes the Session local to the thread.
	 */
	public static void closeSession()
		throws InfrastructureException {
		try {
			Session s = (Session) threadSession3.get();
			threadSession3.set(null);
			if (s != null && s.isOpen()) {
				log.debug("Closing Session of this thread.");
				s.close();
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Start a new database transaction.
	 */
	public static void beginTransaction()
		throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction3.get();
		try {
			if (tx == null) {
				log.debug("Starting new database transaction in this thread.");
				tx = getSession().beginTransaction();
				threadTransaction3.set(tx);
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Commit the database transaction.
	 */
	public static void commitTransaction()
		throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction3.get();
		try {
			if ( tx != null && !tx.wasCommitted()
							&& !tx.wasRolledBack() ) {
				log.debug("Committing database transaction of this thread.");
				tx.commit();
			}
			threadTransaction3.set(null);
		} catch (HibernateException ex) {
			rollbackTransaction();
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Commit the database transaction.
	 */
	public static void rollbackTransaction()
		throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction3.get();
		try {
			threadTransaction3.set(null);
			if ( tx != null && !tx.wasCommitted() && !tx.wasRolledBack() ) {
				log.debug("Tyring to rollback database transaction of this thread.");
				tx.rollback();
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			closeSession();
		}
	}

	/**
	 * Reconnects a Hibernate Session to the current Thread.
	 *
	 * @param session The Hibernate Session to be reconnected.
	 */
	public static void reconnect(Session session)
		throws InfrastructureException {
		try {
			session.reconnect();
			threadSession3.set(session);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Disconnect and return Session from current Thread.
	 *
	 * @return Session the disconnected Session
	 */
	public static Session disconnectSession()
		throws InfrastructureException {

		Session session = getSession();
		try {
			threadSession3.set(null);
			if (session.isConnected() && session.isOpen())
				session.disconnect();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return session;
	}

	/**
	 * Register a Hibernate interceptor with the current thread.
	 * <p>
	 * Every Session opened is opened with this interceptor after
	 * registration. Has no effect if the current Session of the
	 * thread is already open, effective on next close()/getSession().
	 */
	public static void registerInterceptor(Interceptor interceptor) {
		threadInterceptor3.set(interceptor);
	}

	private static Interceptor getInterceptor() {
		Interceptor interceptor =
			(Interceptor) threadInterceptor3.get();
		return interceptor;
	}

}

