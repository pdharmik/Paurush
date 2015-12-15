package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author Vano
 * mapping file: "do-suppliescatalogconsumableparttypedo-mapping.xml"
 */

public class SuppliesCatalogConsumablePartTypeDo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -2533020803352240292L;
 		
		private String billingModel;
		private String billingModelMVF;
		private String sapSoldTo;
		private String soldToMVF;
		private String contractNumber;
		private String contractLine;
		
		public String getBillingModel() {
			return billingModel;
		}
		public void setBillingModel(String billingModel) {
			this.billingModel = billingModel;
		}
		public String getBillingModelMVF() {
			return billingModelMVF;
		}
		public void setBillingModelMVF(String billingModelMVF) {
			this.billingModelMVF = billingModelMVF;
		}
		public String getSapSoldTo() {
			return sapSoldTo;
		}
		public void setSapSoldTo(String sapSoldTo) {
			this.sapSoldTo = sapSoldTo;
		}
		public String getSoldToMVF() {
			return soldToMVF;
		}
		public void setSoldToMVF(String soldToMVF) {
			this.soldToMVF = soldToMVF;
		}
		public String getContractNumber() {
			return contractNumber;
		}
		public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
		}
		public String getContractLine() {
			return contractLine;
		}
		public void setContractLine(String contractLine) {
			this.contractLine = contractLine;
		}
		
}
