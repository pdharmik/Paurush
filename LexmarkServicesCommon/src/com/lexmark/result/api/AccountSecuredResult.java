package com.lexmark.result.api;

import com.lexmark.domain.Account;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sterrell
 * Date: 11/1/11
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountSecuredResult {
    public List<Account> getAuthorizedAccounts();
}
