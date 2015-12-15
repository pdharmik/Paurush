package com.lexmark.result;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.lexmark.domain.Account;
import com.lexmark.domain.Activity;
import com.lexmark.result.api.AccountSecuredResult;

public class ClaimDetailResult implements Serializable, AccountSecuredResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1877421328409128080L;
	private Activity activity;
	private List<Account> authorizedAccounts = new LinkedList<Account>();
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
		authorizedAccounts.clear();
        authorizedAccounts.add(activity.getPartnerAccount());
	}
	@Override
	public List<Account> getAuthorizedAccounts() {
		return authorizedAccounts;
	}
}
