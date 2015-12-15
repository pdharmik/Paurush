package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.amind.session.InvalidSessionException;
import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.amind.session.StatefulSessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.service.api.SiebelCrmServiceException;

public class AmindSiebelCrmService {
public static final Logger logger = LogManager.getLogger(AmindSiebelCrmService.class);
	
	// TODO (pkozlov) I think it will be more correctly to rename this field to statefulSessionFactory 
	static SessionFactory sessionFactory = new StatefulSessionFactory();
	
	// added (20.04.2012)
	private SessionFactory statelessSessionFactory = new StatelessSessionFactory();

	protected void closeSession(String sessionHandle) {
		try {
			Session session = sessionFactory.attachSession(sessionHandle);
			if (session != null) {
				session.release();
			}
		}
		catch (Exception e) {
			logger.error("Close session failed for handle:"+ sessionHandle, e);
			// Squash - assume session is already closed
		}
	}

	protected String openSession() {
		String handle = "";
		try {
			Session session = sessionFactory.attachSession();
			handle = session.detach();
		}
		catch (Exception e) {
			logger.error("Open session failed", e);
			throw new SiebelCrmServiceException(e);
		}
		return handle;
	}
	
	protected Session getSession(String crmSessionToken)
	{
		Session session = null;
		try
		{
/*			TODO: make only one call to attachSession.. sessionfactory 
			 * takes care of null paramteter*/
				session = sessionFactory.attachSession(crmSessionToken);

		}
		catch (InvalidSessionException e) {
			try
			{
				logger.warn("Siebel task not found for passed token");
				session = sessionFactory.attachSession();
			}
			catch(Exception ex)
			{
				throw new SiebelCrmServiceException("get session failed for session" 
						+ crmSessionToken , ex);
			}
			
		}
		return session;
	}
	protected Session getSession(AmindCrmSessionHandle crmSessionToken)
	{
		String sessiontoken = crmSessionToken.getSessionHandle();
		if(sessiontoken == null)
		{
			sessiontoken = "";
		}
		return getSession(sessiontoken);
	}

	protected SessionFactory getStatelessSessionFactory() {
		return statelessSessionFactory;
	}

	protected void setStatelessSessionFactory(SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}
}
