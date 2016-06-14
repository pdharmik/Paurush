package com.lexmark.services.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.CreateServiceRequestB2bContract;
import com.lexmark.contract.GlobalCatalogListContract;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.Pagination;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.service.util.SearchContractUtil;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.util.LocaleUtil;



/**
 * @author wipro
 * @version 2.1
 *
 */

public class ContractFactory {
	
	private static Logger LOGGER = LogManager.getLogger(ContractFactory.class);
	
	/**
	 * @return AccountAgreementSoldToContract 
	 */
	public static AccountAgreementSoldToContract getAllSiebelAccountListContract(){
		AccountAgreementSoldToContract contract = new AccountAgreementSoldToContract();
		contract.setAccountName("Kaiser");
		return contract;
	}
	
	/**
	 * @param request 
	 * @return RequestListContract 
	 */
	public static RequestListContract getRequestListContract(PortletRequest request){
		RequestListContract contract = new RequestListContract();
		PunchoutAccount account=(PunchoutAccount)request.getAttribute("punchoutAccount");
	
		contract.setAccountId(account.getAccountId());
		
		contract.setContactId(account.getContactId());
	
		return contract;
	}
	
	/**
	 * @param request 
	 * @return CatalogListContract 
	 */
	public static CatalogListContract getProductTypeContract(PortletRequest request){
		
		CatalogListContract contract = new CatalogListContract();
		PunchoutAccount account=(PunchoutAccount)request.getAttribute("punchoutAccount");
		contract.setSoldToNumber(account.getSoldTo());
		contract.setAgreementId(account.getAgreementId());
		contract.setHardwareFlag(true);
		contract.setContractNumber(account.getContractNumber());
		contract.setEffectiveDate(new Date());
		return contract;
	}
	
	/**
	 * @param request 
	 * @return CatalogListContract 
	 */
	public static CatalogListContract getProductModelContract(PortletRequest request){
		
		CatalogListContract contract = new CatalogListContract();
		PunchoutAccount account=(PunchoutAccount)request.getAttribute("punchoutAccount");
		contract.setSoldToNumber(account.getSoldTo());
		contract.setAgreementId(account.getAgreementId());
		contract.setHardwareFlag(true);
		contract.setContractNumber(account.getContractNumber());
		contract.setEffectiveDate(new Date());
		contract.setProductType(StringUtils.isNotBlank(request.getParameter("prodTyp"))==true?request.getParameter("prodTyp"):"");
		return contract;
	}
	
	/**
	 * @param request 
	 * @return CatalogListContract 
	 */
	public static CatalogListContract getPrinterAccessoriesContract(PortletRequest request,PunchoutAccount punchoutAccount){
		
		CatalogListContract contract = new CatalogListContract();
		
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,new String[0], new String[0]);
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		                                                                  		
		//PunchoutAccount account=(PunchoutAccount)request.getAttribute("punchoutAccount");
		contract.setSoldToNumber(punchoutAccount.getSoldTo());
		contract.setAgreementId(punchoutAccount.getAgreementId());
	    contract.setHardwareFlag(false);
		contract.setContractNumber(punchoutAccount.getContractNumber());
		contract.setEffectiveDate(new Date());
		contract.setProductType(StringUtils.isNotBlank(request.getParameter("prodTyp"))==true?request.getParameter("prodTyp"):"");
		contract.setProductModel(StringUtils.isNotBlank(request.getParameter("prodMod"))==true?request.getParameter("prodMod"):"");
		contract.setPartNumber(StringUtils.isNotBlank(request.getParameter("partNum"))==true?request.getParameter("partNum"):"");
		contract.setCatalogFlag(true);
		return contract;
	}
	
	/**
	 * @param request 
	 * @return CatalogListContract 
	 */
	public static CatalogListContract retirevePrinterTypes(PortletRequest request){
		LOGGER.debug("IN retirevePrinterTypes call type set in request is ------->>>> "+request.getAttribute("callType"));
		CatalogListContract contract=new CatalogListContract();
		String callType = "";
		if(request.getAttribute("callType")!=null){
		callType = request.getAttribute("callType").toString();
		}
		if("showPrinterList".equalsIgnoreCase(callType) || "showDefaultView".equalsIgnoreCase(callType)){
			LOGGER.debug("1 in retireveParinterTypes call type is "+callType);
			contract.setHardwareSuppliesFlag(true);
		}
		else if("retrieveBundleGrid".equalsIgnoreCase(callType)){
			LOGGER.debug("2 in retireveParinterTypes call type is "+callType);
			contract.setHardwareAccessoriesFlag(true);
		}
		contract.setBundleId(StringUtils.isNotBlank(request.getParameter("bundleId"))==true?request.getParameter("bundleId"):"");
		PunchoutAccount account=(PunchoutAccount)request.getAttribute("punchoutAccount");
		String materialNum=request.getParameter("model");
		contract.setPrinterMaterialNum(StringUtils.isNotBlank(materialNum)==true?materialNum:"");
		contract.setAgreementId(account.getAgreementId());
		contract.setSoldToNumber(account.getSoldTo());
		contract.setContractNumber(account.getContractNumber());
		contract.setEffectiveDate(new Date());
        return contract;
	}
	
	/**
	 * @param request 
	 * @return HardwareCatalogContract 
	 */
	public static HardwareCatalogContract retrieveBundleListContract(PortletRequest request,PunchoutAccount punchoutAccount){
		HardwareCatalogContract contract = new HardwareCatalogContract();
		
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,new String[0]
				, new String[0]);
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		
		String partTypeParam = StringUtils.isNotBlank(request.getParameter("partType"))==true?request.getParameter("partType"):"";
		if(request.getParameter("certType")!=null){
			String certifiedProduct=ControllerUtil.getCertifiedProduct(request.getParameter("certType"));
			contract.setLocationType(certifiedProduct);
		}
		boolean isProduct=Boolean.parseBoolean(request.getParameter("isProduct"));
		if(isProduct){
			contract.setProductType(partTypeParam);
		}else{
			String partType = ControllerUtil.getPrinterPartType(partTypeParam);
			contract.setPartType(partType);	
		}

		contract.setContractNumber(punchoutAccount.getContractNumber());
		contract.setAgreementId(punchoutAccount.getAgreementId());
        contract.setSoldToNumber(punchoutAccount.getSoldTo());
        
        contract.setEffectiveDate(new Date());
        contract.setStartRecordNumber(-1);
        contract.setNewQueryIndicator(true);
        return contract;
	}
	
	/**
	 * @param request 
	 * @return RequestContract 
	 */
	public static RequestContract getRequestDetailsContract(PortletRequest request){
		RequestContract contract = new RequestContract();
		contract.setServiceRequestNumber(request.getParameter(PunchoutConstants.SR_NUMBER));
		contract.setVisibilityRole(PunchoutConstants.VISIBILITY_ROLE);
		contract.setMadcServiceRequestFlag(true);
		contract.setCreateChildSR(true);
		return contract;
	}

	/**
	 * @param hardwarePartList 
	 * @param contractNo 
	 * @return PriceContract 
	 */
	public static PriceContract getPriceContract(Set<String> contractLineIds, String contractNo){
		PriceContract priceContract = new PriceContract();
		List<Price> priceInputList = new ArrayList<Price>();	
		for(String contractLineItem:contractLineIds){
			Price price = new Price();
			price.setContractLineItemId(contractLineItem);
			priceInputList.add(price);
		}
		priceContract.setContractNumber(contractNo);
		priceContract.setPriceList(priceInputList);
		LOGGER.debug("End of Price Contract");
		return priceContract;
	}

    /**
     * @return CreateServiceRequestB2bContract 
     */
    public static CreateServiceRequestB2bContract createServiceRequestContract(){
		CreateServiceRequestB2bContract contract=new CreateServiceRequestB2bContract();
		return contract;
	}
    
    public static GlobalCatalogListContract createGlobalSearchListContract(PunchoutAccount punchoutAccount,PortletRequest request){
    	
    	LOGGER.debug("searchNumber is "+request.getParameter("searchNumber"));
		String searchNumber = request.getParameter("searchNumber");		
    	GlobalCatalogListContract globalCatalogListContract = new GlobalCatalogListContract();
    	globalCatalogListContract.setAccSoldToNumber(punchoutAccount.getSoldTo());
		globalCatalogListContract.setAccAgreementId(punchoutAccount.getAgreementId());
		globalCatalogListContract.setAccContractNumber(punchoutAccount.getContractNumber());
		globalCatalogListContract.setAccEffectiveDate(new Date());
		globalCatalogListContract.setAccPartNumber(searchNumber);
		globalCatalogListContract.setBunAgreementId(punchoutAccount.getAgreementId());
		globalCatalogListContract.setBunSoldToNumber(punchoutAccount.getSoldTo());
		globalCatalogListContract.setBunContractNumber(punchoutAccount.getContractNumber());
		globalCatalogListContract.setBunEffectiveDate(new Date());
		globalCatalogListContract.setBunPartNumber(searchNumber);
		globalCatalogListContract.setSupSoldToNumber(punchoutAccount.getSoldTo());
		globalCatalogListContract.setSupAgreementId(punchoutAccount.getAgreementId());
		globalCatalogListContract.setSupContractNumber(punchoutAccount.getContractNumber());
		globalCatalogListContract.setSupEffectiveDate(new Date());
		globalCatalogListContract.setSupPartNumber(searchNumber);
		globalCatalogListContract.setAccHardwareAccessoriesFlag(true);
    	return globalCatalogListContract;
    	
    }
    
	public static  LocalizedSiebelValueContract createLocalizedSiebelValueContract(String lovListName, String lovValue, Locale localeName){
		LocalizedSiebelValueContract contract = new LocalizedSiebelValueContract();
		contract.setLocaleName(LocaleUtil.getSupportLocaleCode(localeName));
		contract.setLovListName(lovListName);
		contract.setLovValue(lovValue);
		
		return contract;
		
	}
}