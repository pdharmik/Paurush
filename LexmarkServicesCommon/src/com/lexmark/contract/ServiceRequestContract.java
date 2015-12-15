package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;

public class ServiceRequestContract extends ContractBase implements Serializable {
    private static final long serialVersionUID = -8292421665580711731L;
    private String requestNumber;
    private Locale locale;
    private String visibilityRole;

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getVisibilityRole() {
        return visibilityRole;
    }

    public void setVisibilityRole(String visibilityRole) {
        this.visibilityRole = visibilityRole;
    }
}
