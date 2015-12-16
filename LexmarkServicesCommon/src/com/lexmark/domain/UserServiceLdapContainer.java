package com.lexmark.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.Remote;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class UserServiceLdapContainer implements Remote, Serializable, DataSource, Context{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8796906702968479424L;
	public Logger getParentLogger()  throws SQLFeatureNotSupportedException{
		return null;
		
	}

	public Connection getConnection() throws SQLException {
        try {
            Properties props = new Properties();
            InputStream propstream = getClass().getClassLoader().getResourceAsStream("userservice.properties");
            if(propstream==null) {
                propstream = new FileInputStream("userservice.properties");
            }
            props.load(propstream);
            propstream.close();
            return getConnection(props.getProperty("jdbc.username"),props.getProperty("jdbc.password"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection(String username, String password) throws SQLException {
        try {
            Properties props = new Properties();
            InputStream propstream = getClass().getClassLoader().getResourceAsStream("userservice.properties");
            if(propstream==null) {
                propstream = new FileInputStream("userservice.properties");
            }
            props.load(propstream);
            propstream.close();
            Class.forName(props.getProperty("jdbc.driverClass"));
            return DriverManager.getConnection(props.getProperty("jdbc.url"),username,password);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object lookup(Name name) throws NamingException {
        return this;
    }

    public Object lookup(String name) throws NamingException {
        return this;
    }

    public void bind(Name name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void bind(String name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rebind(Name name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rebind(String name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void unbind(Name name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void unbind(String name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rename(Name oldName, Name newName) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rename(String oldName, String newName) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroySubcontext(Name name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroySubcontext(String name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Context createSubcontext(Name name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Context createSubcontext(String name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object lookupLink(Name name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object lookupLink(String name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NameParser getNameParser(Name name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NameParser getNameParser(String name) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Name composeName(Name name, Name prefix) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String composeName(String name, String prefix) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object removeFromEnvironment(String propName) throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Hashtable<?, ?> getEnvironment() throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNameInNamespace() throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
