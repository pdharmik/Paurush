package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.amind.session.StatefulSessionFactory;
import com.amind.session.StatelessSessionFactory;

/**
 *
 * @author vpetruchok
 * @version 1.0, 2012-03-29
 */
public class AmindServiceTest {

    protected static final Log logger = LogFactory.getLog(AmindServiceTest.class);

    protected static AmindGlobalService amindGlobalService;
    protected static AmindCrmSessionHandle crmSessionHandle;
    protected static StatefulSessionFactory statefulSessionFactory;
    protected static StatelessSessionFactory statelessSessionFactory;
    private static List<Session> statefulSessions = Collections.synchronizedList(new ArrayList<Session>());
    

    @BeforeClass
    public static void setUp_AmindServerTest_static() throws InterruptedException {
        logger.info("in setUp()");
        statefulSessionFactory =  TestSessionFactories.newStatefulSessionFactory();
        statelessSessionFactory = TestSessionFactories.newStatelessSessionFactory();

        amindGlobalService = new AmindGlobalService();
        amindGlobalService.setStatefulSessionFactory(statefulSessionFactory);
        amindGlobalService.setStatelessSessionFactory(statelessSessionFactory);

        crmSessionHandle = (AmindCrmSessionHandle) amindGlobalService.initCrmSessionHandle(null);
    }

    @AfterClass
    public static void tearDown_AmindServerTest_static() {
        logger.info("in tearDown()");
        try {
        	if(crmSessionHandle==null) crmSessionHandle = new AmindCrmSessionHandle();
            crmSessionHandle.release();
        } catch (Exception ex) {
            logger.error("crmSessionHandle.release()", ex);
        }
        try {
        	if(statelessSessionFactory==null) statelessSessionFactory = new StatelessSessionFactory();
            statelessSessionFactory.releaseAllStatelessSessions();
        } catch (Exception ex) {
            logger.error("statelessSessionFactory.releaseAllStatelessSessions()", ex);
        }
        
        for (Session statefulSession: statefulSessions) {
            try  {
                statefulSessionFactory.releaseSession(statefulSession);
            } catch (Exception ex) {
                logger.error("statelessSessionFactory.releleasSessions(...)", ex);
            }
        }
    }
    
    /**
     * Return stateful session registering it in statefulSessions list.
     * <br /> After the test sessions in statefulSessions list are released in {@link #tearDown_AmindServerTest_static()} method.
     * 
     * @return stateful session
     */
    public static Session statefulSession() {
        Session s = statefulSessionFactory.attachSession();
        statefulSessions.add(s);
        return s;
    }

    public static String str(Object obj) {
        return ToStringBuilder.reflectionToString(obj);
    }
    
    public static String str(Object[] obj) {
        return ToStringBuilder.reflectionToString(obj);
    }
}
