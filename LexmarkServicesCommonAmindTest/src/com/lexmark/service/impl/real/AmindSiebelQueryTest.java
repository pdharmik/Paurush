package com.lexmark.service.impl.real;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;

/**
 * @see com.lexmark.service.impl.real.AmindServiceTest
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-09
 */
public class AmindSiebelQueryTest {

    protected static final Log logger = LogFactory.getLog(AmindSiebelQueryTest.class);
//    protected static StatefulSessionFactory statefulSessionFactory;
    protected static StatelessSessionFactory statelessSessionFactory;
    protected static Session session;
    protected static IDataManager dataManager;

    @BeforeClass
    public static void setUp_AmindSiebelQueryTest_static() throws InterruptedException {
        logger.info("in setUp()");
        statelessSessionFactory =  TestSessionFactories.newStatelessSessionFactory();
        session = statelessSessionFactory.attachSession();
        dataManager = session.getDataManager();
    }

    @AfterClass
    public static void tearDown_AmindSiebelQueryTest_static() {
        logger.info("in tearDown()");
        try {
            session.release();
        } catch (Exception ex) {
            logger.error("session.release()", ex);
        }
        try {
            statelessSessionFactory.releaseAllStatelessSessions();
        } catch (Exception ex) {
            logger.error("statelessSessionFactory.releaseAllStatelessSessions()", ex);
        }
    }
    
    private static List<?> siebelQuery(IDataManager dataManager, Class<?> class1, int numRows, int startRowIndex) {
        QueryObject query = new QueryObject(class1, ActionEvent.QUERY);
        query.setExecutionMode(ExecutionMode.BiDirectional);
        query.setNumRows(numRows);
        query.setStartRowIndex(startRowIndex);
        query.addComponentSearchSpec(class1, "");
        return dataManager.query(query);
    }

    private static List<?> sampleSiebelQuery(IDataManager dataManager, Class<?> doClass, int numRows, int startRowIndex) {
        System.out.printf("\nentering sampleSiebelQuery(...)\n");
        System.out.printf("\tinputparam doClass=%s\n", doClass.getSimpleName());
        System.out.printf("\tinputparam numRows=%s\n", numRows);
        System.out.printf("\tinputparam startRowIndex=%s\n", startRowIndex);
        List<?> result = siebelQuery(dataManager,  doClass, numRows, startRowIndex);
        if (result == null) {
            System.out.printf("result=%s\n", result);
        } else {
            System.out.printf("result.size()=%s:\n", result.size());
            int i = 0;
            for (Object obj : result) {
                System.out.printf("\t[%s] %s\n", ++i, ToStringBuilder.reflectionToString(obj));
            }
        }

        return result;
    }
    
    public static List<?> siebelQuery(Class<?> class1, int numRows, int startRowIndex) {
        return siebelQuery(dataManager, class1, numRows, startRowIndex);
    }

    public static List<?> sampleSiebelQuery(Class<?> doClass, int numRows, int startRowIndex) {
        return sampleSiebelQuery(dataManager, doClass, numRows, startRowIndex);
    }
    

    public static String str(Object obj) {
        return ToStringBuilder.reflectionToString(obj);
    }
}
