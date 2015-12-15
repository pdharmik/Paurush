package com.lexmark.contract.api;

import java.io.Serializable;

import com.lexmark.domain.ServicesUser;

/**
 * Created by IntelliJ IDEA.
 * User: sterrell
 * Date: 11/1/11
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class SecureContract  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ServicesUser servicesUser=null;
    public ServicesUser getServicesUser(){
        return servicesUser;
    }
    public void setServicesUser(ServicesUser user){
        this.servicesUser = user;
    }
}
