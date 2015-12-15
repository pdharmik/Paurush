package com.lexmark.service.impl.mock;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.*;
import com.lexmark.domain.Account;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.*;
import com.lexmark.result.*;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.result.PaymentTypeListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

import static com.lexmark.constants.LexmarkConstants.*;

public class GlobalServiceImpl implements GlobalService {
	private static Logger logger = LogManager.getLogger(GlobalServiceImpl.class);
	private static String[] legalNames = { "Coca Cola", "Community Bank", "Comcast", "Costco", "Chubb", "Canon",
			"Chevron Corporation", "Cadbury Schweppes", "COFCO", "Coles Myer", "BMW", "Best Buy", "Bank of America", "BTFC" };
	private static String[] mdmLevels = { MDM_LEVEL_GLOBAL, MDM_LEVEL_DOMESTIC, MDM_LEVEL_LEGAL, MDM_LEVEL_ACCOUNT,
			MDM_LEVEL_SIEBEL };

	public CrmSessionHandle initCrmSessionHandle(CrmSessionHandle handle) throws SiebelCrmServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public DunsNumberResult retrieveDunsNumber(DunsNumberContract contract) {
		DunsNumberResult result = new DunsNumberResult();
		result.setDunsNumber(contract.getMdmId());
		String mdmLevel = contract.getMdmLevel();
		if(mdmLevel == null || 
				!mdmLevel.equals(LexmarkConstants.MDM_LEVEL_GLOBAL) || 
				!mdmLevel.equals(MDM_LEVEL_DOMESTIC)) {
			throw new IllegalArgumentException("mdmLeve must be global or domestic");
		}
		return result;
	}

	public GlobalLegalEntityResult retrieveGlobalLegalEntity(GlobalLegalEntityContract contract) {
		GlobalLegalEntityResult result = new GlobalLegalEntityResult();
		GlobalAccount account = new GlobalAccount();
		account.setMdmId(contract.getMdmId());
		account.setMdmLevel(contract.getMdmLevel());
		String legalName = "";
		for (int i=0; i< mdmLevels.length;i++){
			if( mdmLevels[i].equals(contract.getMdmLevel())){
				legalName = legalNames[i];
			}
		}
		account.setLegalName(legalName);
		result.setAccount(account);
		return result;
	}

	public GlobalLegalEntityResult retrieveGlobalLegalEntityByLegalName(GlobalLegalEntityByLegalNameContract contract) {
		GlobalLegalEntityResult result = new GlobalLegalEntityResult();
		GlobalAccount account = new GlobalAccount();
		account.setLegalName(contract.getLeagalName());
		for (int i = 0; i < legalNames.length; i++) {
			if(legalNames[i].equals(contract.getLeagalName())){
				String mdmLevel = mdmLevels[i%mdmLevels.length];
				account.setMdmId(mdmLevel.toUpperCase());
				account.setMdmLevel(mdmLevel);
			}
		}
		result.setAccount(account);
		return result;
	}

	public List<GlobalAccount> retrieveGlobalLegalEntityList() {
		List<GlobalAccount> result = new ArrayList<GlobalAccount>();
		for (int i = 0; i < legalNames.length; i++) {
			GlobalAccount globalAccount = new GlobalAccount();
			globalAccount.setMdmId("T7484" + i);
			if (i % 2 == 0) {
				globalAccount.setMdmLevel(GLOBAL);
			} else {
				globalAccount.setMdmLevel(LEGAL);
			}
			globalAccount.setLegalName(legalNames[i]);
			globalAccount.setDunsNumber("1343346" + i);
			result.add(globalAccount);
		}

		return result;
	}

	public LegalNameResult retrieveLegalName(LegalNameContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListOfValuesResult retrieveProvinceList(ListOfValuesContract contract) throws Exception {
		logger.debug("[IN] retrieveProvinceList");
		ListOfValuesResult result;
		result = new ListOfValuesResult();
		ArrayList<ListOfValues> list = new ArrayList<ListOfValues>();
		ListOfValues l = new ListOfValues();
		l.setId("1");
		l.setType("province");
		l.setValue("Zhejiang");
		list.add(l);
		ListOfValues l2 = new ListOfValues();
		l2.setId("2");
		l2.setType("province");
		l2.setValue("Taiwan");
		list.add(l2);
		ListOfValues l3 = new ListOfValues();
		l3.setId("3");
		l3.setType("province");
		l3.setValue("Tibet");
		list.add(l3);
		result.setLovList(list);
		return result;
	}

	public ListOfValuesResult retrieveStateList(ListOfValuesContract contract) throws Exception {
		ListOfValuesResult result;
		result = new ListOfValuesResult();
		ArrayList<ListOfValues> list = new ArrayList<ListOfValues>();
		ListOfValues l = new ListOfValues();
		l.setId("1");
		l.setType("state");
		l.setValue("Colorado");
		list.add(l);
		ListOfValues l2 = new ListOfValues();
		l2.setId("2");
		l2.setType("state");
		l2.setValue("Illinois");
		list.add(l2);
		ListOfValues l3 = new ListOfValues();
		l3.setId("3");
		l3.setType("state");
		l3.setValue("Alabama");
		list.add(l3);
		result.setLovList(list);
		return result;
	}

	public void releaseSessionHandle(CrmSessionHandle handle) {
		// TODO Auto-generated method stub

	}


	private Boolean getRandomFlag() {
		java.util.Random random = new java.util.Random();
		return random.nextBoolean();
	}
	
	@Override
	public FSEAccountListResult retrieveFSEAccountList(
			FSEAccountListContract contract) throws Exception {
		FSEAccountListResult result = new FSEAccountListResult();
		Account account;
		int accountIndex = 0;
		for (com.lexmark.result.LdapUserDataResult userData : PartnerDomainMockDataGenerator.getLdapUserDataResultList()) {
			accountIndex ++;
			if (contract.getSiebelEmployeeId().equals(userData.getContactId())) {
				break;
			}
		}
		
		if (accountIndex == 2) {
			account = PartnerDomainMockDataGenerator.createPartnerAccountTwo();
		} else if (accountIndex == 3) {
			account = PartnerDomainMockDataGenerator.createPartnerAccountThree();
		} else if (accountIndex == 4) {
			account = PartnerDomainMockDataGenerator.createPartnerAccountFour();
		} else {
			account = PartnerDomainMockDataGenerator.createPartnerAccountOne();
		}
		result.getAccountList().add(account);
		return result;
	}

	@Override
	public PartnerAccountListResult retrievePartnerAccountList(PartnerAccountListContract contract) throws Exception {
		PartnerAccountListResult result = new PartnerAccountListResult();
		Account account;
		int accountIndex = 0;
		for (com.lexmark.result.LdapUserDataResult userData : PartnerDomainMockDataGenerator.getLdapUserDataResultList()) {
			accountIndex ++;
			if (contract.getMdmId().equals(userData.getMdmId())) {
				break;
			}
		}
		
		if (accountIndex == 2) {
			account = PartnerDomainMockDataGenerator.createPartnerAccountTwo();
		} else if (accountIndex == 3) {
			account = PartnerDomainMockDataGenerator.createPartnerAccountThree();
		} else if (accountIndex == 4) {
			account = PartnerDomainMockDataGenerator.createPartnerAccountFour();
		} else {
			account = PartnerDomainMockDataGenerator.createPartnerAccountOne();
		}
		result.getAccountList().add(account);
		
		return result;
	}

	@Override
	public SiebelLOVListResult retrieveSiebelLOVList(
			SiebelLOVListContract contract) throws Exception {
		SiebelLOVListResult  result = new SiebelLOVListResult();
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		String lovName = contract.getLovName();
		if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue())) {
			for (PartStatusEnum currentEnum : PartStatusEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue())) {
			for (CarrierEnum currentEnum : CarrierEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_PAYMENT_STATUS.getValue())) {
			for (PaymentStatusEnum currentEnum : PaymentStatusEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue())) {
			for (ServiceRequestTypeEnum currentEnum : ServiceRequestTypeEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue())) {
			for (RequestStatusEnum currentEnum : RequestStatusEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue())) {
			for (RequestSubStatusEnum currentEnum : RequestSubStatusEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue())) {
			for (ActualFailureCodeEnum currentEnum : ActualFailureCodeEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue())) {
			for (ResolutionCodeEnum currentEnum : ResolutionCodeEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE.getValue())) {
			for (AdditionalPaymentTypeEnum currentEnum : AdditionalPaymentTypeEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1.getValue())) {
			for (ErrorCodeOneEnum currentEnum : ErrorCodeOneEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_2.getValue())) {
			String errorCode1 = contract.getErrorCode1();
			if (errorCode1 != null && errorCode1 !="") {
				for (ErrorCodeTwoEnum currentEnum : ErrorCodeTwoEnum.values()) {
					ListOfValues lov = new ListOfValues();
					if (currentEnum.getValue().indexOf(errorCode1) != -1) {
						lov.setType(lovName);
						lov.setValue(currentEnum.getValue());
						lovList.add(lov);
					}
				}
			}
			
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION.getValue())) {
			for (DeviceConditionEnum currentEnum : DeviceConditionEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_TRAVEL_UNIT_OF_MEASURE.getValue())) {
			for (TravelUnitOfMeasureEnum currentEnum : TravelUnitOfMeasureEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.PARTNER_ORDER_LINE_ITEM_STATUS.getValue())) {
			for (LineStatusEnum currentEnum : LineStatusEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue())) {
			for (ServiceTypeEnum currentEnum : ServiceTypeEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		} else if (lovName.equals(SiebelLocalizationOptionEnum.SERVICE_STATUS.getValue())) {
			for (ServiceStatusEnum currentEnum : ServiceStatusEnum.values()) {
				ListOfValues lov = new ListOfValues();
				lov.setType(lovName);
				lov.setValue(currentEnum.getValue());
				lovList.add(lov);
			}
		}
		result.setLovList(lovList);
		return result;
	}

	@Override
	public PartnerAgreementListResult retrievePartnerAgreementList(PartnerAgreementListContract contract)
			throws Exception {
		PartnerAgreementListResult result = new PartnerAgreementListResult();
		result.setPartnerAgreementList(PartnerDomainMockDataGenerator.getPartnerAgreements());
		return result;
	}

	@Override
	public SiebelAccountIdResult retrieveSiebelAccountId(
			SiebelAccountIdContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public SiebelAccountListResult retrieveSiebelAgreementList(SiebelAccountListContract contract) throws Exception {
        // TODO implement
        return null;
    }

	@Override
	public AccountFlagResult retrieveEntitelmentFlags(
			SiebelAccountListContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SiebelAccountListResult retrieveCatalogAgreementList(SiebelAccountListContract contract) throws Exception{
		return null;
	}

	@Override
	public AddressListResult retrieveBillToAddressList(
			AddressListContract contract) throws Exception {
		List<GenericAddress> billToList = new ArrayList<GenericAddress>();
		for(int i=0;i>6;i++){
			GenericAddress billTo = DomainMockDataGenerator.getGenericAddress(i);
			billToList.add(billTo);
		}
		AddressListResult result = new AddressListResult();
		result.setAddressList(billToList);
		for(GenericAddress billTo:result.getAddressList()){
			logger.debug("Bill To Address Sold To "+billTo.getSoldToNumber());
			logger.debug("Bill To Address Line 1 "+billTo.getAddressLine1());
		}
		return result;
	}
	
	public AddressListResult retrieveBillToAddressListMock(
			AddressListContract contract) throws Exception {
		logger.debug("Enter RetrieveBillToAddress List");
		List<GenericAddress> billToList = DomainMockDataGenerator.getGenericAddressList();
		/*for(int i=1;i>6;i++){
			GenericAddress billTo = DomainMockDataGenerator.getGenericAddress(i);
			billToList.add(billTo);
		}*/
		AddressListResult result = new AddressListResult();
		result.setAddressList(billToList);
		for(GenericAddress billTo:result.getAddressList()){
			logger.debug("Bill To Address Sold To "+billTo.getSoldToNumber());
			logger.debug("Bill To Address Line 1 "+billTo.getAddressLine1());
		}
		return result;
	}

	@Override
	public PaymentTypeListResult retrievePaymentTypeList(
			PaymentListContract contract) throws Exception {
		PaymentTypeListResult result = new PaymentTypeListResult();
		ArrayList<String> paymentTypeList = new ArrayList<String>();
    	paymentTypeList.add("Ship and Bill");
    	//paymentTypeList.add("Pay Later");
    	paymentTypeList.add("Usage Based Billing");
    	result.setPaymentType(paymentTypeList);
		return result;
	}

	@Override
	public AgreementSoldToNumberResult retrieveSoldToList(
			AgreementSoldToNumberContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiebelLOVListResult retrieveNonCacheableSiebelLOVList(
			SiebelLOVListContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}