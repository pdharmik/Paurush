package util;

import org.apache.commons.logging.LogFactory;

import com.amind.session.Session;
import com.amind.session.StatefulSessionFactory;
import com.amind.session.StatelessSessionFactory;

/**
 * @author vpetruchok
 * @version 1.0, 2012-11-14
 */
public class TestSessionFactories {

    public static StatelessSessionFactory newStatelessSessionFactory() {
        return new StatelessSessionFactory() {

            @Override
            public Session attachSession() {
                try {
                    Session session = super.attachSession();
                    LogFactory.getLog(ServiceUtil.class).info("=== Stateless Session");
                    ServiceUtil.getSiebelLogNumber(session);
                    LogFactory.getLog(ServiceUtil.class).info("===");
                    return session;
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    public static StatefulSessionFactory newStatefulSessionFactory() {
        return new StatefulSessionFactory() {

            @Override
            public Session attachSession() {
                try {
                    Session session = super.attachSession();
                    LogFactory.getLog(ServiceUtil.class).info("=== Stateful Session");
                    ServiceUtil.getSiebelLogNumber(session);
                    LogFactory.getLog(ServiceUtil.class).info("===");
                    return session;
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

}
