package com.lexmark.reportScheduler.data;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.util.report.PropertiesUtil;
import com.mchange.v2.c3p0.DataSources;

public class ConnectionPoolTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testConnectionPool() {
		try
	    {
//			dbDriverName = oracle.jdbc.driver.OracleDriver
//			dbConnStr = jdbc:oracle:thin:@10.2.11.163:1521:lexmark
//			dbUserName = lexmark
//			dbPassword = lexmark

		// Acquire the DataSource... this is the only c3p0 specific code here
		Class.forName(PropertiesUtil.DBDriverName);
		DataSource unpooled = DataSources.unpooledDataSource(PropertiesUtil.DBConnStr,
				PropertiesUtil.DBUsername,
				PropertiesUtil.DBPassword);
		Properties pro = PropertiesUtil.getConfigProperties();
		DataSource pooled = DataSources.pooledDataSource( unpooled, pro );



		// get hold of a Connection an do stuff, in the usual way
		Connection con  = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		try
		    {
			con = pooled.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT REPORT_DEFINITION_ID FROM REPORT_DEFINITION");
			while (rs.next())
			    
		    }
		finally
		    {
			//i try to be neurotic about ResourceManagement,
			//explicitly closing each resource
			//but if you are in the habit of only closing
			//parent resources (e.g. the Connection) and
			//letting them close their children, all
			//c3p0 DataSources will properly deal.
			attemptClose(rs);
			attemptClose(stmt);
			attemptClose(con);
		    }
	    }
	catch (Exception e)
	    { e.printStackTrace(); }
    
	}
	
	static void attemptClose(ResultSet o)
    {
	try
	    { if (o != null) o.close();}
	catch (Exception e)
	    { e.printStackTrace();}
    }

    static void attemptClose(Statement o)
    {
	try
	    { if (o != null) o.close();}
	catch (Exception e)
	    { e.printStackTrace();}
    }

    static void attemptClose(Connection o)
    {
	try
	    { if (o != null) o.close();}
	catch (Exception e)
	    { e.printStackTrace();}
    }

}
