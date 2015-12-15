package com.lexmark.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import com.lexmark.constants.LexmarkConstants;

public class JDBCUtil {
//	public static Connection getIDMConnection() throws Exception {
//		String driverClass = getPropertyValue("connection.driver_class");
//		String url = getPropertyValue("idmda.connection.url");
//		String user = getPropertyValue("idmda.connection.user");
//		String password = getPropertyValue("idmda.connection.password");
//		
//		Connection conn = null;
//		
//		Class.forName(driverClass);
//		conn = DriverManager.getConnection(url, user, password);
//		return conn;
//	}
//	
//	public static Connection getServciesUserConnection() throws Exception {
//		String driverClass = getPropertyValue("connection.driver_class");
//		String url = getPropertyValue("servicesuser.connection.url");
//		String user = getPropertyValue("servicesuser.connection.user");
//		String password = getPropertyValue("servicesuser.connection.password");
//		
//		Connection conn = null;
//		
//		Class.forName(driverClass);
//		conn = DriverManager.getConnection(url, user, password);
//		return conn;
//	}
//	
//	private static String getPropertyValue(String key) throws SQLException, ClassNotFoundException {
//		String value = PropertiesMessageUtil.getPropertyMessage(LexmarkConstants.JDBC_PROPERTIES_BUNDLE_NAME, key, Locale.getDefault());
//		return value;
//	}
}
