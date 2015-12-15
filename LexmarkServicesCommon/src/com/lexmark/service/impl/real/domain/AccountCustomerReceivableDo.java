package com.lexmark.service.impl.real.domain;

/**
 * @author imdzeluri     Mapping file: do-accountcustomerreceivabledo-mapping.xml  
 */
import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

public class AccountCustomerReceivableDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1737091093231356434L;
	
	private ArrayList<CustomerSapAccountDo> customerSapAccount;
	private String accountName;

	public ArrayList<CustomerSapAccountDo> getCustomerSapAccount() {
		return customerSapAccount;
	}

	public void setCustomerSapAccount(
			ArrayList<CustomerSapAccountDo> customerSapAccount) {
		this.customerSapAccount = customerSapAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
