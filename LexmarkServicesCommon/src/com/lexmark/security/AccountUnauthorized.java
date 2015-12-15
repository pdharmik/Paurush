package com.lexmark.security;

import com.lexmark.domain.ServicesUser;
import com.lexmark.result.api.AccountSecuredResult;

/**
 * Created by IntelliJ IDEA.
 * User: sterrell
 * Date: 11/1/11
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountUnauthorized extends Exception {
    String message = null;
    public AccountUnauthorized(ServicesUser user, AccountSecuredResult result) {
        StringBuffer message = new StringBuffer();
        message.append("User ").append(user.getEmailAddress()).append(" is not authorized for any account in ").append(result.getAuthorizedAccounts());
        this.message = message.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
