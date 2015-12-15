package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;

public class CatalogDetailResult implements Serializable{
		/**
		 * 
		 */
		
		private static final long serialVersionUID = 8022882274067805950L;
		private int totalCount;
		private List<OrderPart> selectedPartsList = new ArrayList<OrderPart>();
		private AccountContact accountContact;
		private GenericAddress serviceAddress;
		private GenericAddress shipToAddress;
		//private Asset asset;
		private String defaultSpecialInstruction;
		
		//Amind Uses
		private Part part;
		
		
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		
		public List<OrderPart> getSelectedPartsList() {
			return selectedPartsList;
		}
		public void setSelectedPartsList(List<OrderPart> selectedPartsList) {
			this.selectedPartsList = selectedPartsList;
		}
		public AccountContact getAccountContact() {
			return accountContact;
		}
		public void setAccountContact(AccountContact accountContact) {
			this.accountContact = accountContact;
		}
		public GenericAddress getServiceAddress() {
			return serviceAddress;
		}
		public void setServiceAddress(GenericAddress serviceAddress) {
			this.serviceAddress = serviceAddress;
		}
		public GenericAddress getShipToAddress() {
			return shipToAddress;
		}
		public void setShipToAddress(GenericAddress shipToAddress) {
			this.shipToAddress = shipToAddress;
		}
		public String getDefaultSpecialInstruction() {
			return defaultSpecialInstruction;
		}
		public void setDefaultSpecialInstruction(String defaultSpecialInstruction) {
			this.defaultSpecialInstruction = defaultSpecialInstruction;
		}

		public Part getPart() {
			return part;
		}

		public void setPart(Part part) {
			this.part = part;
		}
	}

