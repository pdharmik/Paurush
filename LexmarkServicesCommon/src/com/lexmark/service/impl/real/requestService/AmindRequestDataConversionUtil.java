package com.lexmark.service.impl.real.requestService;

import static com.lexmark.util.LangUtil.isNotEmpty;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Agreement;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Part;
import com.lexmark.service.impl.real.domain.AccountAgreementDo;
import com.lexmark.service.impl.real.domain.AccountBasedDo;
import com.lexmark.service.impl.real.domain.AccountByVendorIdDo;
import com.lexmark.service.impl.real.domain.AccountDo;
import com.lexmark.service.impl.real.domain.AccountEntitlementDo;
import com.lexmark.service.impl.real.domain.AccountServiceAgreementDo;
import com.lexmark.service.impl.real.domain.AgreementFlagsDo;
import com.lexmark.service.impl.real.domain.AgreementRelatedAccountsDO;
import com.lexmark.service.impl.real.domain.CatalogEntitlementAccountDo;
import com.lexmark.service.impl.real.domain.ServiceRequest;
import com.lexmark.service.impl.real.domain.ServiceRequestActivity;
import com.lexmark.service.impl.real.domain.SuppliesSAPCatalogDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailActivityDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailCustomerOrderItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import com.lowagie.text.pdf.events.IndexEvents.Entry;

public class AmindRequestDataConversionUtil {
	private static final Logger logger = LogManager.getLogger(AmindRequestDataConversionUtil.class);

	public static List<com.lexmark.domain.ServiceRequest> convertServiceRequestHistoryList(
			List<ServiceRequest> requestDoList) {
		List<com.lexmark.domain.ServiceRequest> serviceHistoryList = new ArrayList<com.lexmark.domain.ServiceRequest>();

		if (requestDoList == null) {
			return serviceHistoryList;
		}

		for (ServiceRequest requestDo : requestDoList) {
			com.lexmark.domain.ServiceRequest requestHistory = new com.lexmark.domain.ServiceRequest();

			requestHistory.setId(requestDo.getId());
			requestHistory.setServiceRequestStatusDate(requestDo.getServiceRequestStatusDate());
			requestHistory.setServiceRequestStatus(requestDo.getStatus());
			requestHistory.setServiceRequestNumber(requestDo.getServiceRequestNumber());
			requestHistory.setServiceRequestDate(requestDo.getServiceRequestDate());
		
			com.lexmark.domain.ListOfValues reuqestType = new com.lexmark.domain.ListOfValues();
			reuqestType.setValue(requestDo.getRequestType());
			requestHistory.setServiceRequestType(reuqestType);
		
			com.lexmark.domain.ListOfValues lovarea = new com.lexmark.domain.ListOfValues();
			lovarea.setValue(requestDo.getArea());
			requestHistory.setArea(lovarea);
			
			requestHistory.setAsset(populateServiceAssetFromRequestDo(requestDo)); //defect-885, 2012-07-24 vpetruchok   

			serviceHistoryList.add(requestHistory);
		}

		return serviceHistoryList;
	}
	


	public static List<com.lexmark.domain.ServiceRequest> convertServiceRequestDoToRequestList(
			List<? extends ServiceRequest> siebelServiceRequestList) {

		List<com.lexmark.domain.ServiceRequest> portalSRList = new ArrayList<com.lexmark.domain.ServiceRequest>();

		if (isNotEmpty(siebelServiceRequestList)) {
			for (ServiceRequest serviceRequestDO : siebelServiceRequestList) {

				com.lexmark.domain.ServiceRequest portalSR = new com.lexmark.domain.ServiceRequest();
				portalSR.setAsset(populateServiceAssetFromRequestDo(serviceRequestDO));

				portalSR.setId(serviceRequestDO.getId());
				portalSR.setServiceRequestStatusDate(serviceRequestDO.getServiceRequestStatusDate());
				portalSR.setServiceRequestStatus(serviceRequestDO.getStatus());
				portalSR.setServiceRequestNumber(serviceRequestDO.getServiceRequestNumber());
				portalSR.setServiceRequestDate(serviceRequestDO.getServiceRequestDate());
				portalSR.setProblemDescription(serviceRequestDO.getProblemDescription());
				portalSR.setCustomerReferenceNumber(serviceRequestDO.getCustRefNumber());

				if (serviceRequestDO.getServiceRequestETA() == null
						|| "".equals(serviceRequestDO.getServiceRequestETA())) {
					portalSR.setServiceRequestETA(serviceRequestDO.getServiceRequestSLA());
				} else {
					portalSR.setServiceRequestETA(serviceRequestDO.getServiceRequestETA());
				}

				portalSR.setServiceAddress(populateAddressFromRequestDo(serviceRequestDO));
				portalSR.setPrimaryContact(populatePrimaryContactFromRequestDo(serviceRequestDO));
				portalSR.setResolutionCode(serviceRequestDO.getResolutionCode());	// added by nelson for CI5
				portalSRList.add(portalSR);
			}
		}

		return portalSRList;
	}

	public static List<Account> convertAccountDoListToAccount(List<? extends AccountBasedDo> accountsDo) {
		if (accountsDo == null) {
			return new ArrayList<Account>();
		}

		List<Account> accounts = new ArrayList<Account>(accountsDo.size());
		for (AccountBasedDo accountBasedDo : accountsDo) {
			
			Account account = new Account();
			boolean lbsDisplayCheck = false;
			account.setAccountId(accountBasedDo.getId());
			account.setAccountName(accountBasedDo.getName());
			
			if (accountBasedDo instanceof AccountDo) {
				AccountDo accountDo = (AccountDo) accountBasedDo;
				if(isNotEmpty(accountDo.getAlliancePartner()) && "Y".equalsIgnoreCase(accountDo.getAlliancePartner()))
				{
					account.setAlliancePartner(true);
				}else{
					account.setAlliancePartner(false);
				}
				account.setCreateServiceRequest(accountDo.getCreateServiceRequest());
				account.setManualMeterRead(accountDo.getManualMeterRead());
				account.setUserConsumables(accountDo.getUsesConsumables());
				account.setAccountMgmtRequest(accountDo.getChangeManagment());			
				account.setHardwareRequestFlag(accountDo.getHardwareFlag());
				account.setLbsFlag(accountDo.getLbsFlag());
				account.setB2bFlag(accountDo.getB2bFlag());
				account.setDeviceStatus(accountDo.getDeviceStatus());
				account.setLbsUtilization(accountDo.getLbsUtilization());
				
				if(isNotEmpty(accountDo.getLbsDisplayWeb())){
					if(accountDo.getLbsDisplayWeb().equalsIgnoreCase("Show")){
						lbsDisplayCheck = true;
					}
				}
				
				account.setLbsDisplayWeb(lbsDisplayCheck);
				
				if(isNotEmpty(accountDo.getAddressFlag()) && "Y".equalsIgnoreCase(accountDo.getAddressFlag()))
				{
					account.setAddressFlag(false);
				}else{
					account.setAddressFlag(true);
				}
			
				String accountType = accountDo.getFunctionalClassification();
				if ("DFM".equalsIgnoreCase(accountType) || "CSS".equalsIgnoreCase(accountType)) {
					account.setAccountType(accountType);
				}
				
				account.setAddress(getAddressFromAccountDo(accountDo));
				account.setAgreementId(accountDo.getAgreementId());
				account.setAgreementName(accountDo.getAgreementName());
				account.setSpecialHandlingInstruction(accountDo.getSpecialHandlingInstruction());
				account.setUpsCode(accountDo.getUpsCode());
				account.setInvoiceFlag(accountDo.isInvoiceFlag());
				account.setMassUploadFlag(accountDo.isMassUploadFlag());
				account.setShipToDefault(accountDo.getShipToDefault());  			// CRM-TSpores201409160941. Doc "CI CRs and Release Plan".
				
			} else if (accountBasedDo instanceof AccountAgreementDo) {
				AccountAgreementDo agreementDo = (AccountAgreementDo) accountBasedDo;
				account.setAccountOrganization(agreementDo.getAccountOrganization());
				account.setAccountSite(agreementDo.getLocation());
	
			}
			
			if (accountBasedDo instanceof AccountByVendorIdDo) {
			    AccountByVendorIdDo accDo = (AccountByVendorIdDo) accountBasedDo;
			    account.setAccountId(accDo.getId());
			    account.setAccountName(accDo.getName());
			    account.setCountryCode(accDo.getCountry());
			    account.setVendorAccountId(accDo.getVendorAccountId());
			    account.setAddressFlag(!accDo.isAddressFlag());
			    account.setCreateOrderFlag(accDo.isConsumableFlag());
			}
			if (accountBasedDo instanceof AgreementFlagsDo){
				AgreementFlagsDo accountDo = (AgreementFlagsDo) accountBasedDo;
				account.setAssetExpediteFlag(assetExpediteFlag(accountDo));
				account.setCatalogEntitlementFlag(catalogEntitlementFlag(accountDo));
				account.setAssetEntitlementFlag(assetEntitlementFlag(accountDo));
		        account.setCatalogExpediteFlag(catalogExpediteFlag(accountDo));
		        List<Agreement> aggList = new ArrayList<Agreement>();
		        for (AccountServiceAgreementDo agreementDo : LangUtil.notNull(accountDo.getAccountServiceAgreements())) {
		        	for (AccountEntitlementDo agreementEntitlementDo : LangUtil.notNull(agreementDo.getAccountEntitlements())) {
						if ("Consumables Mgmt Services"
								.equalsIgnoreCase(agreementEntitlementDo
										.getEntitlementType())) {
							account.setQuantityServices(agreementEntitlementDo
									.getMpsQuantity());
						} else if ("Consumables Mgmt Supply"
								.equalsIgnoreCase(agreementEntitlementDo
										.getEntitlementType())) {
							account.setQuantitySupplies(agreementEntitlementDo
									.getMpsQuantity());
						}
		        	}
		        }
			}
			accounts.add(account);
		}

		return accounts;
	}
	
	public static List<Account> convertCatalogAccountToAccountList(
			List<? extends AccountBasedDo> accountsDo, String contractNumber, String soldToType) {
		if (accountsDo == null) {
			return new ArrayList<Account>();
		}
		List<Account> accounts = new ArrayList<Account>(accountsDo.size());
		for (AccountBasedDo accountBasedDo : accountsDo) {

			if (accountBasedDo instanceof CatalogEntitlementAccountDo) {
				CatalogEntitlementAccountDo entitlementDo = (CatalogEntitlementAccountDo) accountBasedDo;
				if (entitlementDo != null) {
					if (LangUtil.isNotEmpty(entitlementDo.getSuppliesCatalogDo())) {
						for (Iterator<SuppliesSAPCatalogDo> iter = entitlementDo.getSuppliesCatalogDo().iterator(); iter.hasNext();) {
							SuppliesSAPCatalogDo catalogDo = iter.next();
							if (soldToType != null) {
								if (!soldToType.equalsIgnoreCase(catalogDo						// changes as per email "INC0168189 :Duplicate account"
										.getSoldToType())
										|| (LangUtil.isNotBlank(contractNumber) && !contractNumber
												.equalsIgnoreCase(catalogDo
														.getContractNumber()))
										|| "Inactive"
												.equalsIgnoreCase(catalogDo
														.getContractSAPSatus())) {
									iter.remove();
								}
							} else {
								if ("AB".equalsIgnoreCase(catalogDo
										.getSoldToType())
										|| "SB".equalsIgnoreCase(catalogDo
												.getSoldToType())
										|| (LangUtil.isNotBlank(contractNumber) && !contractNumber
												.equalsIgnoreCase(catalogDo
														.getContractNumber()))
										|| "Inactive"
												.equalsIgnoreCase(catalogDo
														.getContractSAPSatus())) {
									iter.remove();
								}
							}
						}
					}
					
					if (entitlementDo.getAgreementRelatedAccounts() != null) {
						for (AgreementRelatedAccountsDO accountDo : entitlementDo.getAgreementRelatedAccounts()) {
							Account account = new Account();
							account.setCatalogEntitlementFlag(true);
							account.setAccountId(accountDo.getMdmLevel5AccountId());
							account.setAccountName(accountDo.getAccountName());
							account.setAgreementId(entitlementDo.getAgreementId());
							account.setAgreementName(entitlementDo.getAgreementName());
							account.setCatalogExpediteFlag(catalogExpediteFlag(entitlementDo));
							account.setAccountSplitterFlag(entitlementDo.isAccountSplitterFlag());
							account.setShowPriceFlag(entitlementDo.getShowPrice());
							account.setOrganizationName(entitlementDo.getOrganizationName());
							account.setEntitlementType(entitlementDo.getEntitlementType());
							account.setMpsQuantity(entitlementDo.getMpsQuantity());
							GenericAddress address = new GenericAddress();
							address.setCountry(accountDo.getAccountCountry());
							account.setAddress(address);

							AmindServiceUtil.populatePaymentMethod(account, entitlementDo.getPaymentMethod());
							
							if (entitlementDo.getSuppliesCatalogDo() != null) {
								duplicateAccounts(entitlementDo, accounts, accountDo);
							}

							if (!accountListContainsAccount(accounts,account)) {
								accounts.add(account);
							}
						}
					}
					
//					if (entitlementDo.getSuppliesCatalogDo() != null) {
//						duplicateAccounts(entitlementDo, accounts);
//					}
//					else {
//						Account childAccount = new Account();
//						childAccount.setCatalogEntitlementFlag(true);
//						childAccount.setAccountId(entitlementDo.getAccountId());
//						childAccount.setAccountName(entitlementDo.getAccountName());
//						childAccount.setAgreementId(entitlementDo.getAgreementId());
//						childAccount.setAgreementName(entitlementDo.getAgreementName());
//						childAccount.setCatalogExpediteFlag(catalogExpediteFlag(entitlementDo));
//						childAccount.setAccountSplitterFlag(entitlementDo.isAccountSplitterFlag());
//						childAccount.setShowPriceFlag(entitlementDo.getShowPrice());
//						childAccount.setOrganizationName(entitlementDo.getOrganizationName());
//						GenericAddress childAddress = new GenericAddress();
//						childAddress.setCountry(entitlementDo.getAccountCountry());
//						childAccount.setAddress(childAddress);
//
//						AmindServiceUtil.populatePaymentMethod(childAccount,
//								entitlementDo.getPaymentMethod());
//
//						if (!accountListContainsAccount(accounts, childAccount)) {
//							accounts.add(childAccount);
//						}
//					}
				}
			}

		}
		// }
		return accounts;
	}

	static boolean catalogEntitlementFlag(AgreementFlagsDo accountDo) {
        for (AccountServiceAgreementDo agreementDo : LangUtil.notNull(accountDo.getAccountServiceAgreements())) {
        	if ("Current".equalsIgnoreCase(agreementDo.getAgreementStatus())
        	        && "YES".equalsIgnoreCase(agreementDo.getCatalogEntitlementFlag())) {
                return true;
            }
        }
        return false;
    }
	
	static boolean catalogExpediteFlag(CatalogEntitlementAccountDo accountDo) {
		
		if (accountDo.getCatalogExpediteFlag() != null && "Y".equalsIgnoreCase(accountDo.getCatalogExpediteFlag())) {
			return true;
		}
		return false;
	}

    
    private static boolean assetEntitlementFlag(AgreementFlagsDo accountDo) {
        for (AccountServiceAgreementDo agreementDo : LangUtil.notNull(accountDo.getAccountServiceAgreements())) {
        	if ("Current".equalsIgnoreCase(agreementDo.getAgreementStatus())
        	       && "YES".equalsIgnoreCase(agreementDo.getAssetEntitlementFlag())) {
                return true;
            }
        }
        return false;
    }

    private static boolean assetExpediteFlag(AgreementFlagsDo accountDo) {
        for (AccountServiceAgreementDo agreementDo : LangUtil.notNull(accountDo.getAccountServiceAgreements())) {
        	if ("Current".equalsIgnoreCase(agreementDo.getAgreementStatus())
                    && "YES".equalsIgnoreCase(agreementDo.getExpediteOrderCount())) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean catalogExpediteFlag(AgreementFlagsDo accountDo) {
        for (AccountServiceAgreementDo agreementDo : LangUtil.notNull(accountDo.getAccountServiceAgreements())) {
            if ("Current".equalsIgnoreCase(agreementDo.getAgreementStatus())
                   && "YES".equalsIgnoreCase(agreementDo.getCatalogExpediteOrderCount())) {
                return true;
            }
        }
        return false;
    }

	public static List<com.lexmark.domain.ServiceRequestActivity> populateEmailActivities(com.lexmark.domain.ServiceRequest serviceRequest,List<? extends ServiceRequestActivity> actions)
	{
		List<com.lexmark.domain.ServiceRequestActivity> emailActivities = new ArrayList<com.lexmark.domain.ServiceRequestActivity>();
		if (actions != null) {
			for (ServiceRequestActivity action : actions) {
				com.lexmark.domain.ServiceRequestActivity activity = new com.lexmark.domain.ServiceRequestActivity();

				activity.setActivityId(action.getActivityId());
				activity.setActivityDate(action.getCreatedDate());
				activity.setComment(action.getComment());

				if (action.isServiceActivityFlag()) {
					serviceRequest.setServiceActivityStatus(getActivityStatus(action.getStatus()));
				} else {
					if (action.getType().equalsIgnoreCase("Email - Outbound")) {
						activity = populateEmailActivity(action, activity);
						if (activity != null) {
							emailActivities.add(activity);
						}
					}
				}
			}
		}
	
		return emailActivities;
	}
	
	public static List<com.lexmark.domain.ServiceRequestActivity> populateServiceWebUpdateActivities(SupplyRequestDetailDo requestDetailDo, boolean madcServiceRequestFlag) {
		List<com.lexmark.domain.ServiceRequestActivity> serviceWebUpdateActivities = new ArrayList<com.lexmark.domain.ServiceRequestActivity>();
		List<SupplyRequestDetailActivityDo> actions = requestDetailDo.getActions();
		
		if (actions != null) {
			for (ServiceRequestActivity action : actions) {
				com.lexmark.domain.ServiceRequestActivity activity = new com.lexmark.domain.ServiceRequestActivity();
				activity.setActivityDate(action.getCreatedDate());
				activity.setActivityStatus(action.getStatus());
				activity.setComment(action.getComment());
				activity.setActivitySubStatus(action.getStatusDetail());
				
				if (action.isServiceActivityFlag()) {
					activity.setServiceRequestDateETA(action.getServiceRequestETA());
				}
					
				//OPS
				activity.setActivityType(action.getType());
				activity.setActivityId(action.getActivityId());
				activity.setSeverity(action.getSeverity());
				activity.setAssetDetails(populateAssetDetails(requestDetailDo, action));

				if (madcServiceRequestFlag) {
					activity.setActivitySerialNumber(action.getActivitySerialNumberMADC());
					if (!"Email - Outbound".equalsIgnoreCase(action.getType())) {
						activity.setRecipientEmail(action.getEmailAddress());
						activity.setDeviceType(action.getDeviceTypeMADC());
						
						if (isNotEmpty(action.getOverrideDesc())) {
							activity.setActivityDescription(action.getOverrideDesc());
						} else {
							activity.setActivityDescription(action.getDescription());
						}
						serviceWebUpdateActivities.add(activity);
					}
				} else {
					activity.setActivitySerialNumber(action.getActivitySerialNumber());
					if (!"Email - Outbound".equalsIgnoreCase(action.getType())) {
						activity.setRecipientEmail(action.getEmailAddress());
						activity.setDeviceType(action.getDeviceTypeNonMADC());
						
						if (isNotEmpty(action.getOverrideDesc())) {
							activity.setActivityDescription(action.getOverrideDesc());
						} else {
							activity.setActivityDescription(action.getDescription());
						}
						serviceWebUpdateActivities.add(activity);
					}
				}
			}
		}
		return serviceWebUpdateActivities;
	}

	public static List<com.lexmark.domain.ServiceRequestActivity> populateActivityWebUpdateActivities(SupplyRequestDetailDo requestDetailDo)
	{
		List<com.lexmark.domain.ServiceRequestActivity> activityWebUpdateActivities = new ArrayList<com.lexmark.domain.ServiceRequestActivity>();
		List<SupplyRequestDetailActivityDo> actions = requestDetailDo.getActions();
		
		if (actions  != null) {
			for (ServiceRequestActivity action : actions) {
				if(checkEligibility(action.getType(),action.getStatus()))        			// if returned false then details should not reach portal
				 {
					com.lexmark.domain.ServiceRequestActivity activity = new com.lexmark.domain.ServiceRequestActivity();
					activity.setActivityDate(action.getCreatedDate());
					activity.setComment(action.getComment());
					activity.setActivityStatus(action.getStatus());
					activity.setActivitySerialNumber(action.getActivitySerialNumber());
					activity.setActivitySubStatus(action.getStatusDetail());
					
					//OPS
					activity.setActivityType(action.getType());
					activity.setActivityId(action.getActivityId());
					activity.setSeverity(action.getSeverity());
					
					activity.setAssetDetails(populateAssetDetails(requestDetailDo, action));
					
					if (!"Email - Outbound".equalsIgnoreCase(action.getType())) {
						activity.setRecipientEmail(action.getEmailAddress());
						if (isNotEmpty(action.getOverrideDesc())) {
							activity.setActivityDescription(action.getOverrideDesc());
						} else {
							activity.setActivityDescription(action.getDescription());
						}

						activityWebUpdateActivities.add(activity);
					}
				}
			}
		}

		return activityWebUpdateActivities;
	}
	
	private static boolean checkEligibility(String type, String status)    // if returned false then details should not reach portal 
	{
		if("SR Web Update".equalsIgnoreCase(type)
		|| "Activity Web Update".equalsIgnoreCase(type)
		|| "MADC Supporting Task".equalsIgnoreCase(type)
		|| "Pending".equalsIgnoreCase(status)
		|| "Action Required".equalsIgnoreCase(status)
		|| "Ready for Assignment".equalsIgnoreCase(status)
		|| "Assignment Failed".equalsIgnoreCase(status)
		|| "Assignment Failed - SB".equalsIgnoreCase(status))
		{
			return false;
		}
	return true;
	}

	private static List<Asset> populateAssetDetails(SupplyRequestDetailDo requestDetailDo, ServiceRequestActivity action) {
		List<Asset> assetDetails = new ArrayList<Asset>();
		Asset assetDetail = new Asset();
		
		if (requestDetailDo != null) {
			assetDetail.setDescription(requestDetailDo.getAdditionalDetails());
			assetDetail.setDeviceTag(requestDetailDo.getDeviceTag());
			assetDetail.setInstallDate(requestDetailDo.getInstallDate());
			assetDetail.setIpAddress(requestDetailDo.getIpAddress());
			assetDetail.setHostName(requestDetailDo.getHostName());
			assetDetail.setSerialNumber(action.getSerialNumber());
			assetDetail.setDeviceType(action.getDeviceType());
			assetDetail.setActivityNumber(action.getActivityId());
			assetDetail.setStatusDetail(action.getStatusDetail());
			
			if (LangUtil.isNotEmpty(requestDetailDo.getServiceLocation())) {
				if (LangUtil.isNotBlank(requestDetailDo.getOrderSource()) && "Web".equalsIgnoreCase(requestDetailDo.getOrderSource())) {
					populatePhysicalLocationContiansPrefixForAssetDetails(requestDetailDo, assetDetail);
				} else {
					populatePhysicalLocationWOPrefixForAssetDetails(requestDetailDo, assetDetail);
				}
			}

			assetDetail.setPartList(populatePartList(requestDetailDo.getCustomerOrderLineItems()));
			assetDetails.add(assetDetail);
		}
		return assetDetails;
	}


	private static void populatePhysicalLocationWOPrefixForAssetDetails(SupplyRequestDetailDo requestDetailDo, Asset assetDetail) {
		String[] locations = requestDetailDo.getServiceLocation().split(",");
		for(int i = 0 ; i < locations.length ; i++){
			 switch(i){
			 case 0: 
				 assetDetail.setBuilding(locations[i]);
        		 break;
        	 case 1:
        		 assetDetail.setFloor(locations[i]);
        		 break;
        	 case 2:
        		 assetDetail.setOffice(locations[i]);
        		 break;
			 }
		}
	}


	private static void populatePhysicalLocationContiansPrefixForAssetDetails(SupplyRequestDetailDo requestDetailDo, Asset assetDetail) {
    	String[] locations = requestDetailDo.getServiceLocation().split(",");
    	LocationEnum locationEnum = null;
    	for(int i = 0 ; i < locations.length ; i++){
	    	List<String> physicalLocation = Arrays.asList(locations[i].split(" "));
	    	if(LangUtil.isNotEmpty(physicalLocation)) {
	    		try{
	    			locationEnum = LocationEnum.valueOf(physicalLocation.get(0));
	    		}catch (IllegalArgumentException exception){
	    			//squash
	    		}
	    	}
	    	if(locationEnum != null) {
	    		switch(locationEnum)
	    		{
	    		case Building: 
	    			locations[i] = locations[i].replaceFirst(LocationEnum.Building.toString(), "");
	    			assetDetail.setBuilding(locations[i].trim());
	    			break;
	    		case Floor:
	    			locations[i] = locations[i].replaceFirst(LocationEnum.Floor.toString(), "");
	    			assetDetail.setFloor(locations[i].trim());
	    			break;
	    		case Office:
	    			locations[i] = locations[i].replaceFirst(LocationEnum.Office.toString(), "");
	    			assetDetail.setOffice(locations[i].trim());
	    			break;
	    		}
	    	}
    	}
		
	}



	private static List<Part> populatePartList(ArrayList<SupplyRequestDetailCustomerOrderItemDo> customerOrderLineItems) {
		List<Part> parts = new ArrayList<Part>();
		Part part = new Part();
		
		for (SupplyRequestDetailCustomerOrderItemDo partList : LangUtil.notNull(customerOrderLineItems)) {
			part.setPartNumber(partList.getPartNumber());
			part.setDescription(partList.getPartDescriptionExplicit());
			part.setOrderQuantity(partList.getQuantity());
		}
		
		parts.add(part);
		
		return parts;
	}



	private static com.lexmark.domain.ServiceRequestActivity populateEmailActivity(
			ServiceRequestActivity action, com.lexmark.domain.ServiceRequestActivity activity) {

		String emailAddress = action.getEmailAddress();
		String contactEmailAddress = action.getContactEmail();

		if (isEmpty(contactEmailAddress)) {
			return null;
		}
		if (!emailAddress.contains(contactEmailAddress)) {
			return null;
		}

		activity.setRecipientEmail(action.getEmailAddress());
		activity.setActivityStatus(action.getStatus());

		String description = action.getDescription();
		if (isNotEmpty(description)) {
			activity.setActivityDescription(description);
		} else {
			activity.setActivityDescription(action.getOverrideDesc());
		}

		return activity;
	}

	public static String getActivityStatus(String status) {
		
		if (status == null) {
			return status;
		}

		if ((status.equalsIgnoreCase("Sending Loan Machine"))
				|| (status.equalsIgnoreCase("Not Acknowledged")) || (status.equalsIgnoreCase("Scheduled"))
				|| (status.equalsIgnoreCase("Accepted")) || (status.equalsIgnoreCase("En Route"))
				|| (status.equalsIgnoreCase("Assigned")) || (status.equalsIgnoreCase("Debrief Received"))
				|| (status.equalsIgnoreCase("Dispatch Failed"))
				|| (status.equalsIgnoreCase("Dispatched to SP")) || (status.equalsIgnoreCase("Interrupted"))
				|| (status.equalsIgnoreCase("Invalid Debrief"))
				|| (status.equalsIgnoreCase("Intervention Scheduled"))
				|| (status.equalsIgnoreCase("Partner Acknowledgment"))
				|| (status.equalsIgnoreCase("Valid Debrief"))) {

			return "Assigned";
		}

		if (status.equalsIgnoreCase("Completed")) {
			return "Complete";
		}

		if ((status.equalsIgnoreCase("Pending Service Action"))
				|| (status.equalsIgnoreCase("Ready for Assignment"))
				|| (status.equalsIgnoreCase("Ready for Dispatch"))
				|| (status.equalsIgnoreCase("Assignment Failed"))) {

			return "In Process";
		}

		if ((status.equalsIgnoreCase("Cancelled"))
				|| (status.equalsIgnoreCase("Cancelled Parts without visit"))
				|| (status.equalsIgnoreCase("Cancelled Service"))
				|| (status.equalsIgnoreCase("Cancelled Visit With Parts"))
				|| (status.equalsIgnoreCase("Cancelled Visit Without Parts"))) {

			return "Cancelled";
		}

		return status;
	}

	private static Asset populateServiceAssetFromRequestDo(ServiceRequest serviceRequestDO) {
		Asset serviceAsset = new Asset();
		serviceAsset.setAssetId(serviceRequestDO.getAssetId());
		serviceAsset.setProductLine(serviceRequestDO.getProductLine());
		serviceAsset.setSerialNumber(serviceRequestDO.getSerialNumber());
		serviceAsset.setModelNumber(serviceRequestDO.getModelNumber());

		return serviceAsset;
	}

	private static GenericAddress populateAddressFromRequestDo(ServiceRequest serviceRequestDO) {
		GenericAddress address = new GenericAddress();
		address.setCity(serviceRequestDO.getCity());
		address.setState(serviceRequestDO.getState());
		address.setAddressLine1(serviceRequestDO.getStreet1());
		address.setAddressLine2(serviceRequestDO.getStreet2());
		address.setAddressLine3(serviceRequestDO.getStreet3());
		address.setPostalCode(serviceRequestDO.getPostalCode());
		address.setCountry(serviceRequestDO.getCountry());
		address.setProvince(serviceRequestDO.getProvince());
		address.setAddressName(serviceRequestDO.getAddressName());
		address.setStoreFrontName(serviceRequestDO.getStoreFrontName());

		return address;
	}

	private static AccountContact populatePrimaryContactFromRequestDo(ServiceRequest serviceRequestDO) {
		AccountContact primaryContact = new AccountContact();
		primaryContact.setContactId(serviceRequestDO.getPrimaryId());
		primaryContact.setEmailAddress(serviceRequestDO.getPrimaryContactEmail());
		primaryContact.setFirstName(serviceRequestDO.getPrimaryContactFirstName());
		primaryContact.setLastName(serviceRequestDO.getPrimaryContactLastName());
		primaryContact.setWorkPhone(serviceRequestDO.getPrimaryContactWorkPhone());

		return primaryContact;
	}

	private static GenericAddress getAddressFromAccountDo(AccountDo accountDo) {
		GenericAddress address = new GenericAddress();
		address.setAddressLine1(accountDo.getAddress1());
		address.setAddressLine2(accountDo.getAddress2());
		address.setAddressLine3(accountDo.getAddress3());
		address.setCity(accountDo.getCity());
		address.setState(accountDo.getState());
		address.setCountry(accountDo.getCountry());
		address.setPostalCode(accountDo.getPostalCode());
		address.setProvince(accountDo.getProvince());

		return address;
	} 
	
	private static boolean accountListContainsAccount(List<Account> list, Account account){
		
		if (list.size() == 0) {
			return accountFieldsEmpty(account);
		}

		for (Account existingAccount : list) {
			if (accountEquals(existingAccount, account)) {
				return true;
			}
		}
		return false;
	}

	private static boolean accountEquals(Account existingAccount, Account account) { 
		return accountFieldsEmpty(account) || (existingAccount.getAccountId().equalsIgnoreCase(
						account.getAccountId()) && existingAccount.getAgreementId()
						.equalsIgnoreCase(account.getAgreementId()));
	}
	
	private static boolean accountFieldsEmpty(Account account) {
		return account == null
				|| account.getAccountId() == null
				|| account.getAccountId().isEmpty()
				|| account.getAgreementId() == null
				|| account.getAgreementId().isEmpty();
	}
	
	
//	private static void duplicateAccounts(CatalogEntitlementAccountDo entitlementDo, List<Account> accounts) {
//		
//		for (SuppliesSAPCatalogDo suppliesCatalog : entitlementDo.getSuppliesCatalogDo()) {
//			Account childAccount = new Account();
//			childAccount.setCatalogEntitlementFlag(true);
//			childAccount.setAccountId(entitlementDo.getAccountId());
//			childAccount.setAccountName(entitlementDo.getAccountName());
//			childAccount.setAgreementId(entitlementDo.getAgreementId());
//			childAccount.setAgreementName(entitlementDo.getAgreementName());
//			childAccount.setCatalogExpediteFlag(catalogExpediteFlag(entitlementDo));
//			childAccount.setAccountSplitterFlag(entitlementDo.isAccountSplitterFlag());
//			childAccount.setShowPriceFlag(entitlementDo.getShowPrice());
//			childAccount.setOrganizationName(entitlementDo.getOrganizationName());
//			GenericAddress childAddress = new GenericAddress();
//			childAddress.setCountry(entitlementDo.getAccountCountry());
//			childAccount.setAddress(childAddress);
//	
//			AmindServiceUtil.populatePaymentMethod(childAccount,entitlementDo.getPaymentMethod());
//	
//			ArrayList<String> soldToNumbers = new ArrayList<String>();
//			//if(isContractNumber) {
//				for (SuppliesSAPCatalogDo suppliesCatalogChild : entitlementDo.getSuppliesCatalogDo()) {
//					soldToNumbers.add(suppliesCatalogChild.getSoldToNumber());
//				//}
//			}
//			childAccount.setSoldToNumbers(soldToNumbers);
//			
//			childAccount.setContractNumber(suppliesCatalog.getContractNumber());
//			childAccount.setContractLine(suppliesCatalog.getContractLine());
//
//			if (!contractRepeated(accounts, childAccount)) {
//				accounts.add(childAccount);
//			}
//		}
//		
//	}
	
	
	private static void duplicateAccounts(CatalogEntitlementAccountDo entitlementDo, List<Account> accounts,AgreementRelatedAccountsDO accountDo) {
		
		for (SuppliesSAPCatalogDo suppliesCatalog : entitlementDo.getSuppliesCatalogDo()) {
			Account account = new Account();
			account.setCatalogEntitlementFlag(true);
			account.setAccountId(accountDo.getMdmLevel5AccountId());
			account.setAccountName(accountDo.getAccountName());
			account.setAgreementId(entitlementDo.getAgreementId());
			account.setAgreementName(entitlementDo.getAgreementName());
			account.setCatalogExpediteFlag(catalogExpediteFlag(entitlementDo));
			account.setAccountSplitterFlag(entitlementDo.isAccountSplitterFlag());
			account.setShowPriceFlag(entitlementDo.getShowPrice());
			account.setOrganizationName(entitlementDo.getOrganizationName());
			GenericAddress address = new GenericAddress();
			address.setCountry(accountDo.getAccountCountry());
			account.setAddress(address);
			account.setEntitlementType(entitlementDo.getEntitlementType());
			account.setMpsQuantity(entitlementDo.getMpsQuantity());
			AmindServiceUtil.populatePaymentMethod(account,entitlementDo.getPaymentMethod());

//			//Check for nulls
			ArrayList<String> soldToNumbers = new ArrayList<String>();
			for (SuppliesSAPCatalogDo suppliesCatalogChild : entitlementDo.getSuppliesCatalogDo()) {
				if (suppliesCatalogChild.getContractNumber().equalsIgnoreCase(suppliesCatalog.getContractNumber())) {
					soldToNumbers.add(suppliesCatalogChild.getSoldToNumber());
				}
			}
			account.setSoldToNumbers(soldToNumbers);
			account.setContractNumber(suppliesCatalog.getContractNumber());
			account.setContractLine(suppliesCatalog.getContractLine());
			
			if (!contractRepeated(accounts, account)) {
				accounts.add(account);
			}
		}

	}



	private static boolean contractRepeated(List<Account> duplicateAccounts,
			Account childAccount) {
		
		if (duplicateAccounts.size() == 0) {
			return accountFieldsEmptyWithContractNumber(childAccount);
		}

		for (Account existingAccount : duplicateAccounts) {
			if (accountEqualsWithContractNumber(existingAccount, childAccount)) {
				return true;
			}
		}
		return false;
	}



	private static boolean accountEqualsWithContractNumber(
			Account existingAccount, Account childAccount) {
		return accountFieldsEmptyWithContractNumber(childAccount)
				|| (existingAccount.getAccountId().equalsIgnoreCase(
						childAccount.getAccountId())
						&& existingAccount.getAgreementId().equalsIgnoreCase(
								childAccount.getAgreementId()) && existingAccount
						.getContractNumber().equalsIgnoreCase(
								childAccount.getContractNumber()));
	}

	private static boolean accountFieldsEmptyWithContractNumber(
			Account childAccount) {
		return childAccount == null || childAccount.getAccountId() == null
				|| childAccount.getAccountId().isEmpty()
				|| childAccount.getAgreementId() == null
				|| childAccount.getAgreementId().isEmpty()
				|| childAccount.getContractNumber() == null
				|| childAccount.getContractNumber().isEmpty();
	}
}
