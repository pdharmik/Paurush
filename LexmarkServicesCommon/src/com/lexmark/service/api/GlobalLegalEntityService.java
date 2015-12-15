package com.lexmark.service.api;

import com.lexmark.domain.GlobalAccount;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sterrell
 * Date: 10/26/11
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GlobalLegalEntityService {
    public List<GlobalAccount> getGlobalAccounts();
}
