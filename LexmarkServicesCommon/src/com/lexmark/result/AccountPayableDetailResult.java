package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.AccountPayableDetail;

public class AccountPayableDetailResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private AccountPayableDetail accountPayableDetail;
    

    public AccountPayableDetailResult() {
    }

    public AccountPayableDetail getAccountPayableDetail() {
        return accountPayableDetail;
    }

    public void setAccountPayableDetail(AccountPayableDetail accountPayableDetail) {
        this.accountPayableDetail = accountPayableDetail;
    }

}
