
package com.lexmark.service.impl.real.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.util.report.PropertiesUtil;
import com.mchange.v2.c3p0.DataSources;


/**
* <b>General purpose database Connection object</b>
<br><b>Example:</b><br>
<pre>
			DBConnection dbconn= new DBConnection();
			dbconn.open();
			dbconn.openQueryRS("select RTRIM(Name) as Name from Users");

			String strName;

			while(dbconn.rs.next()) {
				strName = dbconn.rs.getString("Name");
				System.writeln(strName);
			}
			dbconn.closeQueryRS();
			dbconn.close();
</pre>
*/
public class DBConnection  {
	private static Logger logger = LogManager.getLogger(DBConnection.class);

	Connection con =null;
	Statement stmt = null;
	PreparedStatement preparedStatement = null;
	CallableStatement callableStatement = null;

	/** result set from the most recent openQueryRS call
    */
    public ResultSet rs =null;
  	public boolean connectionReady=false;
  	private static DataSource datasource=null;

	/**
	* <b>Opens default Database Connection to the BrandBuilders DB </b>
	*/
  	
  	
	public void open(boolean authCommit)
	{
		connectionReady=false;
		try {
			con = getDataSource().getConnection();
			con.setAutoCommit(authCommit);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		catch (NullPointerException e){
			throw new RuntimeException(e);
		}
		

		if (con!=null) connectionReady=true;

	}
	
	public void open() {
		open(true);
	}
	
	public void getConnection(Connection conn) {
		connectionReady=false;
			con = conn;
		if (con!=null) connectionReady=true;
	}

	/**
	* <b>Closes current database connection</b>
	*/
	public void close()
	{
		try {
			if(con !=null) con.close();
		}
		catch (SQLException e) {
			logger.error("SQLException caught (closing db conn): " + e.getMessage());
		}
		connectionReady=false;
	}
	
	public final void startTransaction() {
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public final void commit() {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public final void rollback() {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public boolean next()
	{
		try {
			return rs.next();
		} catch (SQLException e) {
			logger.error(e.toString()+": Failed to get next record in ResultSet.");
		}
		return false;
	}

	public String getString(String param)
	{
		try {
			return rs.getString(param);
		} catch (SQLException e) {
			logger.error(e.toString()+": Failed to get parameter from result set.");
		}
		return null;
	}


	/** <b>creates a new statement,Performs the given guery, closes the statement</b>
         * @param strSQL sql query string
         */
	public void executeUpdate(String strSQL)
	{
    	try {
    		stmt=con.createStatement();
    	} catch (SQLException e) {
    		logger.error("SQLException caught: (create statement )" + e.getMessage());
    	}

    	try {
    		stmt.executeUpdate(strSQL);
    		if (stmt!=null) stmt.close();
    	} catch (SQLException e) {
    		logger.error("SQLException caught: (executing update )"+strSQL + e.getMessage());
    	}
	}


	/** <b>Creates a new statement and result set for a given SQL query</b>
         * @param strSQL sql query string
         * @return result set from the succesful query
         */

	public ResultSet openQueryRS(String strSQL)
	{

		try {
			if (con!=null) stmt=con.createStatement();
			else {
				logger.error("Cant create statemnt -null connection to DB (openQueryRS)");
			}

		} catch (SQLException e) {
			logger.error("SQLException caught: (opening query rs-create statement "+strSQL+"))" + e.getMessage());
			return null;
		}

		try {

			if (stmt!=null) rs = stmt.executeQuery(strSQL);
			else {
				logger.error("Cant execute query or open result set as the statement is null ");
			}

		} catch (SQLException e) {
			logger.error("SQLException caught: (opening query rs-executing query))" + e.getMessage());
			return null;
		}

		return rs;
	}

	/**
	* <b>Disposes of the current result set and statement.</b>
	*/
	public void closeQueryRS()
	{
		try {
			if (rs!=null) rs.close();
			if (stmt!=null) stmt.close();
		} catch (SQLException e) {
			logger.error("SQLException caught: (closing query rs) " + e.getMessage());
		}

	}

	public PreparedStatement prepareStatement(String commandText) throws SQLException {
		return con.prepareStatement(commandText);
	}
	
	/**
	 *   Nee  open the conn, then run this method, after that we need close prepareStatement and rs.
	 * @param commandText
	 * @param parameters
	 * @throws SQLException
	 */
	public void queryPrepareStatement(String commandText, DBParameter[] parameters)  throws SQLException
	{
    	try {
    		preparedStatement=con.prepareStatement(commandText);
    	} catch (SQLException e) {
    		logger.error("SQLException caught: (create prepare statement )" + e.getMessage());
    	}
    	
    	try {
    			for (int i = 0; i < parameters.length; ++i) {
    				DBParameter parameter = parameters[i];
    				parameter.setValue(preparedStatement, i+1);
    			}
    		
    		  rs = preparedStatement.executeQuery();
    	} catch (SQLException e) {
    		logger.error("SQLException caught: (executing update )"+ e.getMessage());
    	}
	}
	
	/**
	* <b>Disposes of the current result set and statement.</b>
	*/
	public void closeQueryPrepareStatement()
	{
		try {
			if (rs!=null) rs.close();
			if (preparedStatement!=null) preparedStatement.close();
		} catch (SQLException e) {
			logger.error("SQLException caught: (closing query rs) " + e.getMessage());
		}
	}
	
	public void executePrepareStatement(String commandText, DBParameter[] parameters)  throws SQLException
	{
    	try {
    		preparedStatement=con.prepareStatement(commandText);
    	} catch (SQLException e) {
    		logger.error("SQLException caught: (create prepare statement )" + e.getMessage());
    	}
    	
    	try {
    			for (int i = 0; i < parameters.length; ++i) {
    				DBParameter parameter = parameters[i];
    				parameter.setValue(preparedStatement, i+1);
    			}
    		
    		preparedStatement.executeUpdate();
    		if (preparedStatement!=null) preparedStatement.close();
    	} catch (SQLException e) {
    		logger.error("SQLException caught: (executing update )"+ e.getMessage());
    	}
	}
	
	public DataSource getDataSource() {
		if(datasource == null) {
			try {
				Class.forName(PropertiesUtil.DBDriverName);
				DataSource unpooled = DataSources.unpooledDataSource(PropertiesUtil.DBConnStr,
						PropertiesUtil.DBUsername,
						PropertiesUtil.DBPassword);
				datasource = DataSources.pooledDataSource( unpooled, PropertiesUtil.getConfigProperties() );
			} catch(ClassNotFoundException e) {
				logger.error("Couldn't load database driver: " + e.getMessage());
				throw new RuntimeException(e);
			}
			catch(SQLException e) {
				logger.error(PropertiesUtil.DBConnStr+" "+PropertiesUtil.DBUsername+" "+PropertiesUtil.DBPassword+"SQLException caught (opening db con): " + e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return datasource;
	}
}

