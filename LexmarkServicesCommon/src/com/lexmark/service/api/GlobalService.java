package com.lexmark.service.api;

import java.util.List;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AgreementSoldToNumberContract;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.GlobalLegalEntityByLegalNameContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.contract.LegalNameContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.SiebelAccountIdContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.result.AccountFlagResult;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AgreementSoldToNumberResult;
import com.lexmark.result.DunsNumberResult;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.result.LegalNameResult;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.result.PartnerAgreementListResult;
import com.lexmark.result.PaymentTypeListResult;
import com.lexmark.result.SiebelAccountIdResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.SiebelLOVListResult;

/**
 * @author Mike Martus
 */
public interface GlobalService {
	
	/**
	 * Method to initialize the crm session handle prior to passing it to other service methods.
	 * This method should be called at the start or processing of each web request.
	 * @param handle handle to be initialized.  Pass null to create a new handle.
	 * @return the initialized handle
	 * @throws SiebelCrmServiceException when handle cannot be initialized, e.g. if there is a configuration error.
	 */
	public CrmSessionHandle initCrmSessionHandle(CrmSessionHandle handle) throws SiebelCrmServiceException;
	
	/** 
	 * Release the session to free up resources.  Must be called when the web session is destroyed 
	 * to prevent a memory leak.
	 * @param handle
	 */
	public void releaseSessionHandle(CrmSessionHandle handle);
	
	public DunsNumberResult retrieveDunsNumber(DunsNumberContract contract);
	public List<GlobalAccount> retrieveGlobalLegalEntityList();
	public GlobalLegalEntityResult retrieveGlobalLegalEntity(GlobalLegalEntityContract contract);
	public LegalNameResult retrieveLegalName(LegalNameContract contract);
	public GlobalLegalEntityResult retrieveGlobalLegalEntityByLegalName(GlobalLegalEntityByLegalNameContract contract);
	
	@Cacheable(cacheName="retrieveSiebelLOVList", keyGeneratorName="myKeyGenerator")
	public SiebelLOVListResult retrieveSiebelLOVList(SiebelLOVListContract contract) throws Exception;
	
	public SiebelLOVListResult retrieveNonCacheableSiebelLOVList(SiebelLOVListContract contract) throws Exception;
	
	public PartnerAccountListResult retrievePartnerAccountList(PartnerAccountListContract contract)  throws Exception;
	public FSEAccountListResult retrieveFSEAccountList(FSEAccountListContract contract) throws Exception;
	public PartnerAgreementListResult retrievePartnerAgreementList(PartnerAgreementListContract contract) throws Exception;
	public SiebelAccountIdResult retrieveSiebelAccountId(SiebelAccountIdContract contract) throws Exception;
	
	public SiebelAccountListResult retrieveSiebelAgreementList(SiebelAccountListContract contract) throws Exception;
	
	public AccountFlagResult retrieveEntitelmentFlags(SiebelAccountListContract contract) throws Exception;
	public SiebelAccountListResult retrieveCatalogAgreementList(SiebelAccountListContract contract) throws Exception;

	public PaymentTypeListResult retrievePaymentTypeList(PaymentListContract contract) throws Exception;
	
	public AddressListResult retrieveBillToAddressList(AddressListContract contract) throws Exception;
	
	public AgreementSoldToNumberResult retrieveSoldToList(AgreementSoldToNumberContract contract) throws Exception;

}
