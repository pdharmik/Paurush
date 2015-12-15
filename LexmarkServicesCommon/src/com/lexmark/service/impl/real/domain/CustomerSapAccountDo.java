package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * @author imdzeluri 
 * 
 * Mapping file:   do-customersapaccountdo-mapping.xml
 */

public class CustomerSapAccountDo extends BaseEntity {
	private static final long serialVersionUID = -4144862330738741179L;
	
	private String soldTo;
	private String billTo;
	private ArrayList<CustomerSapAccountBusinessAddressDo> customerSapAccountBusinessAddress;
	private ArrayList<CompanyCodeDo> companyCodeDo;
	private String salesOrg;
	private String accountName;
	
	public String getSoldTo() {
		return soldTo;
	}

	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public ArrayList<CustomerSapAccountBusinessAddressDo> getCustomerSapAccountBusinessAddress() {
		return customerSapAccountBusinessAddress;
	}

	public void setCustomerSapAccountBusinessAddress(
			ArrayList<CustomerSapAccountBusinessAddressDo> customerSapAccountBusinessAddress) {
		this.customerSapAccountBusinessAddress = customerSapAccountBusinessAddress;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public ArrayList<CompanyCodeDo> getCompanyCodeDo() {
		return companyCodeDo;
	}

	public void setCompanyCodeDo(ArrayList<CompanyCodeDo> companyCodeDo) {
		this.companyCodeDo = companyCodeDo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
}
