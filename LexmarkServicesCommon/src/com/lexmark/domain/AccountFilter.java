package com.lexmark.domain;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class AccountFilter implements Serializable{
	
	private static final long serialVersionUID = -1567984527729002332L;

	//Annotations For Account
	
		@SerializedName("gbDnNm")
		private String globalDUNSAccountNumber;
		
		@SerializedName("doDnNm")
		private String domesticDUNSAccountNumber;
		
		@SerializedName("lgacNm")
		private String legalAccountNumber;
		
		@SerializedName("mdAcNm")
		private String mDMAccountNumber;
		
		@SerializedName("acId")
		private String accountId;

		public String getGlobalDUNSAccountNumber() {
			return globalDUNSAccountNumber;
		}

		public void setGlobalDUNSAccountNumber(String globalDUNSAccountNumber) {
			this.globalDUNSAccountNumber = globalDUNSAccountNumber;
		}

		public String getDomesticDUNSAccountNumber() {
			return domesticDUNSAccountNumber;
		}

		public void setDomesticDUNSAccountNumber(String domesticDUNSAccountNumber) {
			this.domesticDUNSAccountNumber = domesticDUNSAccountNumber;
		}

		public String getLegalAccountNumber() {
			return legalAccountNumber;
		}

		public void setLegalAccountNumber(String legalAccountNumber) {
			this.legalAccountNumber = legalAccountNumber;
		}

		public String getmDMAccountNumber() {
			return mDMAccountNumber;
		}

		public void setmDMAccountNumber(String mDMAccountNumber) {
			this.mDMAccountNumber = mDMAccountNumber;
		}

		public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

		
}
