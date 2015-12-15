package util;

import org.junit.Test;

import com.amind.session.Session;


/**
 * @author vpetruchok
 * @version 1.0, 2012-11-14
 */
public class TestSessionFactoriesTest {

    @Test
    public void testNewStatelessSessionFactory() throws Exception {
        Session s = TestSessionFactories.newStatelessSessionFactory().attachSession();
        s.release();
    }
    
    @Test
    public void testNewStatefulSessionFactory() throws Exception {
        TestSessionFactories.newStatefulSessionFactory().attachSession().release();
    }
    
}
