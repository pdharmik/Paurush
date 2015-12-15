package com.lexmark.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DBUtil {
	private static Logger logger = LogManager.getLogger(DBUtil.class);
	
	public static Connection getProductImageConnection() throws SQLException{
		return getConnection("jdbc/ProductImageDB");
	}
	public static Connection getPortalDBConnection() throws SQLException{
		return getConnection("jdbc/PortalDB");
	}
	public static Connection getIDMConnection() throws SQLException{
		return getConnection("jdbc/IDMDB");
	}

	public static Connection getConnection(String dataSourceName) throws SQLException {
		Connection connection = null;
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");

			// Look up our data source
			DataSource ds = (DataSource)envCtx.lookup(dataSourceName);
			connection = ds.getConnection();

		}	catch (SQLException e){
			logger.error("SQL exception getting database connection");
			throw new SQLException(e.getMessage());
		}catch (Exception e){
			logger.error("Exception getting database connection");
			throw new SQLException(e.getMessage());
		}
		
		return connection;
	}
}
