package com.lexmark.result;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.lexmark.domain.Account;
import com.lexmark.domain.Activity;
import com.lexmark.result.api.AccountSecuredResult;

public class ActivityDetailResult implements Serializable, AccountSecuredResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7816893925913375159L;
	private Activity activity;
	public void setActivity(Activity activity) {
		this.activity = activity;
        authorizedAccounts.clear();
        authorizedAccounts.add(activity.getPartnerAccount());
	}
	public Activity getActivity() {
		return activity;
	}

    private List<Account> authorizedAccounts = new LinkedList<Account> ();
    @Override
    public List<Account> getAuthorizedAccounts(){
        return authorizedAccounts;
    }
}
