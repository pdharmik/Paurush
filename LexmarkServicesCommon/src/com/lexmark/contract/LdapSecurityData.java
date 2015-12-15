package com.lexmark.contract;

import java.io.Serializable;

public class LdapSecurityData implements Serializable {

    private static final long serialVersionUID = 1L;
    public String userName;
    public String password;
    public String ldapUrl;
    public String jdbcJndiUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLdapUrl() {
        return ldapUrl;
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public String getJdbcJndiUrl() {
        return jdbcJndiUrl;
    }

    public void setJdbcJndiUrl(String jdbcJndiUrl) {
        this.jdbcJndiUrl = jdbcJndiUrl;
    }
}
