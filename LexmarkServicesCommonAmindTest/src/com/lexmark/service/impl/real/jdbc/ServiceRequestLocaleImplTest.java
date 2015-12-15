package com.lexmark.service.impl.real.jdbc;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import oracle.jdbc.pool.OracleDataSource;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-12-31
 */
public class ServiceRequestLocaleImplTest {
    
    ServiceRequestLocaleImpl service;
    OracleDataSource ods = null;
    
    @Before
    public void setUp() throws Exception {
        service = new ServiceRequestLocaleImpl();
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=tlxkrc2db7-vip.lex.lexmark.com)(PORT=1630))(ADDRESS=(PROTOCOL=TCP)(HOST=tlxkrc2db8-vip.lex.lexmark.com)(PORT=1630))(ADDRESS=(PROTOCOL=TCP)(HOST=tlxkrc2db9-vip.lex.lexmark.com)(PORT=1630))(LOAD_BALANCE=yes))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=eprtten1_taf.lex.lexmark.com)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=180)(DELAY=5))))");
        ods.setUser("LGSPORTAL");
        ods.setPassword("lex#1234");
        
        // TODO(Viktor) 
//        Hashtable env = new Hashtable();
//        env.put("java.naming.factory.initial", "");
//        env.put("java.naming.provider.url", "");
//        env.put("java.naming.factory.state", "");
//        env.put("java.naming.factory.url.pkgs", "");
//        Context ctx = new InitialContext(env);
//        ctx.bind("java:comp/env/jdbc/PortalDB", ods);
    }
    
    @Test
    public void testRetrieveSRAdministrationList() throws Exception {
        SRAdministrationListContract c = new SRAdministrationListContract();
        long t0 = System.currentTimeMillis();
        try {
            SRAdministrationListResult r = service.retrieveSRAdministrationList(c); // circa 6 min
            MiscTest.print(r.getSiebelLocalizations());
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
    @Test
    public void queryOracle() throws Exception {
        System.out.println("isOracleDataSource = " + ods.isOracleDataSource);
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
           conn = ods.getConnection();
           stmt = conn.createStatement();
           rs = stmt.executeQuery("SELECT 1 FROM dual");
           rs.next();
           System.out.println("result = " + rs.getString(1));
        } finally {
            close(rs, null, stmt, conn);
        }
    } 
    
    static void close(Object... objects) {
        for (Object obj : objects) {
            try {
                System.out.printf("Closing %s ...", obj);
                if (obj == null) {
                    System.out.printf("Skiped\n");
                    continue;
                } else if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                } else if (obj instanceof ResultSet) {
                    ((ResultSet) obj).close();
                } else if (obj instanceof Statement) {
                    ((Statement) obj).close();
                } else if (obj instanceof Connection) {
                    ((Connection) obj).close();
                } else {
                    throw new AssertionError();
                }
                System.out.printf("Ok\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
   }
}
