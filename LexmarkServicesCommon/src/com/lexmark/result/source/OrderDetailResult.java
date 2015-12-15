package com.lexmark.result.source;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.lexmark.domain.Account;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Order;
import com.lexmark.result.api.AccountSecuredResult;

public class OrderDetailResult implements Serializable, AccountSecuredResult {
    /**
	 * 
	 */
    private static final long serialVersionUID = -7816893925913375159L;

    private Order order;
    private Activity activity;
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        authorizedAccounts.clear();
        authorizedAccounts.add(activity.getPartnerAccount());
    }

    public Activity getActivity() {
        return activity;
    }

    private List<Account> authorizedAccounts = new LinkedList<Account>();

    @Override
    public List<Account> getAuthorizedAccounts() {
        return authorizedAccounts;
    }
}
