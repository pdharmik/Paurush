package com.lexmark.services.portlet;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.PortletSession;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.SRAdministrationDetailContract;
import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.contract.SRAdministrationSaveContract;
import com.lexmark.contract.SRLocalizationContract;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.result.SRAdministrationDetailResult;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.result.SRAdministrationSaveResult;
import com.lexmark.result.SRLocalizationResult;
import com.lexmark.result.SupportedLocaleListResult;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.services.form.SRLocaleDetailForm;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.ServiceStatusUtil;
@Controller
@RequestMapping("VIEW")
public class ServiceRequestAdminLocaleController extends BaseController {
	private static final String ERROR_PREFIX = "exception.srlocale.";
	private static final String MESSAGE_PREFIX = "message.srlocale.";
	private static final String YES = "YES";
	private static final String NO= "NO";
	private static final String NA="N/A";
	Logger logger = LogManager.getLogger(ServiceRequestAdminLocaleController.class);
	@Autowired
	ServiceRequestLocale serviceRequestLocaleService;

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showSRAdministrationListPage(Model model, RenderRequest request, RenderResponse response)
			throws Exception {
		logger.debug("inside the default model page--->>>");
		
		/*
		 * modified
		 * BRD 13-10-11
		 * different service call based on the ajax query parameter
		 * Fetching the locale settings from the portalDB based on the search string 
		 */
		
		SRAdministrationListContract contract = ContractFactory.getSRAdministrationListContract();
		logger.debug("The contract is as below---->>>");
		ObjectDebugUtil.printObjectContent(contract, logger);
		//Retrieving all the option value & store that in a list
		SRAdministrationListResult optionResult ;
		List distinctOptionList = new ArrayList();
		Map<String,String> finalOptionTypeLovMap = new HashMap<String, String>();
		optionResult = serviceRequestLocaleService.retrieveSRAdministrationList(contract);
		List<SiebelLocalization> siebelLocalizationRecords = optionResult.getSiebelLocalizations();
		List<String> nonDupOptionTypeList = new ArrayList<String>();
		Iterator<SiebelLocalization> dupList = siebelLocalizationRecords.iterator();
		/*for(int i=0;i<siebelLocalizationRecords.size();i++)
		{
			siebelLocalizationRecords.get(i).getOptionType());
		}*/
		while(dupList.hasNext())
		{
			String nonDupOptionValue = dupList.next().getOptionType();
			if(nonDupOptionTypeList.contains(nonDupOptionValue))
			{
				dupList.remove();
			}
			else
			{
				nonDupOptionTypeList.add(nonDupOptionValue);
			}
		}
		logger.debug("The non duplicate count is-->>> "+nonDupOptionTypeList.size());
		
		for(int i=0;i<nonDupOptionTypeList.size();i++)
		{
			finalOptionTypeLovMap.put(String.valueOf(i), getOptionDisplayDropDownType(nonDupOptionTypeList.get(i)));
			logger.debug("The key & values are-->>> Key="+String.valueOf(i)+" Value= "+nonDupOptionTypeList.get(i));
		}
		PortletSession session = request.getPortletSession();
		session.setAttribute("finalOptionTypeLovMap", finalOptionTypeLovMap ,PortletSession.APPLICATION_SCOPE);
		model.addAttribute("finalOptionTypeLovMap", finalOptionTypeLovMap);
		return "serviceRequest/serviceRequestLocaleListPage";
	}

	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping
	public void retrieveSRAdministrationList(ResourceRequest request, ResourceResponse response) {
		boolean succeed = false;
		try {
			logger.debug("inside");
			SRAdministrationListContract contract = ContractFactory.getSRAdministrationListContract();
			logger.debug("The contract is as below---->>>");
			ObjectDebugUtil.printObjectContent(contract, logger);
			
			/*
			 * modified
			 * BRD 13-10-11
			 * different service call based on the ajax query parameter
			 * Fetching the locale settings from the portalDB based on the search string 
			 */
			//logger.debug("ERROR");
			
			String filterCriterias = request.getParameter("filterCriterias");
			logger.debug("Filter Criterias-->>"+filterCriterias);
			if(null != filterCriterias){
			
			/*The below code is for find the match exists in the database than
			*that of the dispalyed one
			**/
				
			int indexStart = 0;
			int indexEnd = 0;
			String optionKey = "";
			String receivedValue ="";
			//logger.debug("ERROR1-->>"+filterCriterias);
			
			if(filterCriterias.contains("optionType"))
			{
				logger.debug("contains optionType");
				indexStart = filterCriterias.indexOf("optionType:'%");
				indexStart = indexStart+"optionType:'%".length();
				indexEnd = indexStart+1;
				if(indexEnd-indexStart>=1){
					while(filterCriterias.charAt(indexEnd)!='%'){
						indexEnd++;	
					}		
					optionKey = filterCriterias.substring(indexStart, indexEnd).toUpperCase();			
		   }
				else
				{
					optionKey = filterCriterias.substring(indexStart, indexEnd).toUpperCase();
				}	
				logger.debug(filterCriterias.substring(indexStart, indexEnd));
				PortletSession session = request.getPortletSession();
				Map<String,String> optionType=(Map<String,String>)session.getAttribute("finalOptionTypeLovMap",PortletSession.APPLICATION_SCOPE);
				logger.debug("The option type is--->>>"+optionType.get(optionKey));
				String databaseOptionValue = getOptionDisplayType(optionType.get(optionKey));		
				filterCriterias = filterCriterias.replace(optionKey, databaseOptionValue);
				logger.debug("optionType-->>Filter Criterion"+filterCriterias);
		  }
			
			/* constructing the search string if showEntitlement in the in the text box is provided */
			
			if(filterCriterias.contains("showEntitlementFlag"))
			{
				indexStart= filterCriterias.indexOf("showEntitlementFlag:'%");
				indexStart=indexStart+"showEntitlementFlag:'%".length();
				
				indexEnd = indexStart+1;
				if(indexEnd-indexStart>=1){
				while(filterCriterias.charAt(indexEnd)!='%'){
					indexEnd++;	
				}
				receivedValue = filterCriterias.substring(indexStart, indexEnd).toUpperCase();
				}
				else
				{
					receivedValue = filterCriterias.substring(indexStart, indexEnd).toUpperCase();
				}
			logger.debug("+++++"+indexStart+"--"+indexEnd+"--"+filterCriterias.substring(indexStart, indexEnd)+"----Received Value--"+receivedValue);
			//String receivedValue = filterCriterias.substring(indexStart, indexEnd).toUpperCase();
			if(receivedValue.startsWith("Y"))
			{
				boolean match= findMatch(receivedValue,YES);
				logger.debug(match);				
				logger.debug(filterCriterias.substring(0, indexStart)+"1"+filterCriterias.substring(indexEnd,filterCriterias.length()));
				if(match)
				{
					filterCriterias=filterCriterias.substring(0, indexStart)+"1"+filterCriterias.substring(indexEnd,filterCriterias.length());
				}
				else
					{
					filterCriterias=filterCriterias.substring(0, indexStart)+receivedValue+filterCriterias.substring(indexEnd,filterCriterias.length());	
					}
			}
			
			if(receivedValue.startsWith("N") && !receivedValue.contains("N/A") && !receivedValue.contains(NO))
			{
				boolean match= findMatch(receivedValue,NO);
				logger.debug("Match2 is-->>"+match);
				logger.debug(filterCriterias.substring(0, indexStart)+"0"+filterCriterias.substring(indexEnd,filterCriterias.length()));
				if(match){
					logger.debug("Lexmark---->>>"+filterCriterias.substring(0, indexStart)+"0_null"+filterCriterias.substring(indexEnd,filterCriterias.length()));
				filterCriterias=filterCriterias.substring(0, indexStart)+"0_null"+filterCriterias.substring(indexEnd,filterCriterias.length());
				}
				else
				{
					filterCriterias=filterCriterias.substring(0, indexStart)+receivedValue+filterCriterias.substring(indexEnd,filterCriterias.length());
				}
			}
			
			if(receivedValue.startsWith("N/") || receivedValue.startsWith("N/A")){
				boolean match= findMatch(receivedValue,NA);
				logger.debug("Match3 is-->>"+match);
				if(match)
					{
					filterCriterias=filterCriterias.substring(0, indexStart)+"null"+filterCriterias.substring(indexEnd,filterCriterias.length());
					}
				else
					{
					filterCriterias=filterCriterias.substring(0, indexStart)+receivedValue+filterCriterias.substring(indexEnd,filterCriterias.length());
					}
			}
			
			if(receivedValue.startsWith("NO")){
				boolean match= findMatch(receivedValue,NO);
				logger.debug("Match4 is-->>"+match);
				if(match)
					{
					filterCriterias=filterCriterias.substring(0, indexStart)+"0"+filterCriterias.substring(indexEnd,filterCriterias.length());
					}
				else
					{
					filterCriterias=filterCriterias.substring(0, indexStart)+receivedValue+filterCriterias.substring(indexEnd,filterCriterias.length());
					}
			}
			
			
			}
			
			/* constructing the search string if partner type in the dropdown is selected */
			
			if(filterCriterias.contains("DirectpartnerType")||filterCriterias.contains("IndirectpartnerType"))
			{
				int indexStartDirect = 0;
				int indexEndDirect = 0;
				int indexStartInDirect = 0;
				int indexEndInDirect = 0;
				boolean executed = true;
				String receivedInDirectValue="" ;
				String receivedDirectValue="" ;
				if(filterCriterias.contains("N/A"))
				{
					filterCriterias = filterCriterias.replaceAll("N/A", "null");
					logger.debug("Contains N/A "+filterCriterias);
				}
				if(filterCriterias.contains("DirectpartnerType"))
				{
					logger.debug(filterCriterias.length());
					indexStartDirect = filterCriterias.lastIndexOf("DirectpartnerType:'%");
					logger.debug("Length of DirectpartnerType:'% -->>"+"DirectpartnerType:'%".length());
					indexStartDirect=indexStartDirect+"DirectpartnerType:'%".length();
					logger.debug("indexStartDirect-->>"+indexStartDirect);
					for(indexEndDirect=indexStartDirect+1;indexStartDirect<filterCriterias.length();indexEndDirect++){
						logger.debug("loop");
						logger.debug("---"+indexEndDirect+"----"+indexStartDirect);
					if(!Character.isLetter(filterCriterias.charAt(indexEndDirect)) && (!Character.isWhitespace((filterCriterias.charAt(indexEndDirect)))))
					break;
					}
					logger.debug("++++"+indexStartDirect+"--"+indexEndDirect+"--"+filterCriterias.substring(indexStartDirect, indexEndDirect));

					receivedDirectValue = filterCriterias.substring(indexStartDirect, indexEndDirect).toUpperCase();					
		       }
				if(filterCriterias.contains("IndirectpartnerType"))
				{
						indexStartInDirect = filterCriterias.lastIndexOf("IndirectpartnerType:'%");
						logger.debug("indexStartInDirect-->>"+indexStartInDirect);
						logger.debug("length of IndirectpartnerType:'%-->>"+"IndirectpartnerType:'%".length());
						indexStartInDirect=indexStartInDirect+"IndirectpartnerType:'%".length();
						for(indexEndInDirect=indexStartInDirect+1;indexStartDirect<filterCriterias.length();indexEndInDirect++){							
						if(!Character.isLetter(filterCriterias.charAt(indexEndInDirect)) && (!Character.isWhitespace((filterCriterias.charAt(indexEndInDirect)))))
						break;
						}
						logger.debug("++++"+indexStartInDirect+"--"+indexEndInDirect+"--"+filterCriterias.substring(indexStartInDirect, indexEndInDirect));
						receivedInDirectValue = filterCriterias.substring(indexStartInDirect, indexEndInDirect).toUpperCase();	
					
				}
				
			/*	if(receivedDirectValue == null)
					receivedDirectValue="";
				
				if(receivedInDirectValue == null)
					receivedDirectValue="";*/
				
				String finalPartnerType = partnerTypeDecider(receivedDirectValue,receivedInDirectValue);
				
				//filterCriterias = filterCriterias.substring(0, indexStartDirect)+finalPartnerType+filterCriterias.substring(indexEndDirect,filterCriterias.length()).replaceAll("IndirectpartnerType:'%YES%'","");
				logger.debug("Final Partner Type is-->>"+finalPartnerType);
				if(filterCriterias.contains("IndirectpartnerType") && filterCriterias.contains("DirectpartnerType")){
					logger.debug("Inside IF");
					filterCriterias=filterCriterias.substring(0, indexStartDirect)+finalPartnerType+"|"+receivedDirectValue+"|"+receivedInDirectValue+"|"+filterCriterias.substring(indexEndDirect,filterCriterias.length()).replaceAll(">>IndirectpartnerType:'%YES%'","").replaceAll(">>IndirectpartnerType:'%NO%'", "")
							.replaceAll(">>IndirectpartnerType:'%null%'", "");
				}
				else
				{
					if(filterCriterias.contains("IndirectpartnerType")){
					logger.debug("INNER IF");
					filterCriterias=filterCriterias.substring(0, indexStartInDirect)+finalPartnerType+"|"+receivedDirectValue+"|"+receivedInDirectValue+"|"+filterCriterias.substring(indexEndInDirect,filterCriterias.length()).replaceAll("IndirectpartnerType","DirectpartnerType");
					filterCriterias=filterCriterias.replaceAll("IndirectpartnerType", "DirectpartnerType");
					}
					else
					{
						logger.debug("Inside else");
						filterCriterias=filterCriterias.substring(0, indexStartDirect)+finalPartnerType+"|"+receivedDirectValue+"|"+receivedInDirectValue+"|"+filterCriterias.substring(indexEndDirect,filterCriterias.length()).replaceAll("IndirectpartnerType","DirectpartnerType");	
					}
				}
				
				if(filterCriterias.contains("NULL"))
				{
					logger.debug("inside null");
					filterCriterias = filterCriterias.replace(":'%NULL%'", " is null").replaceAll("IndirectpartnerType", "DirectpartnerType");	
				}
				filterCriterias = filterCriterias.replace("DirectpartnerType", "partnerType");											
			}
			
			if(filterCriterias.contains("statusOrder"))
			{
				
				int indexStartstatusOrder= filterCriterias.indexOf("statusOrder:'%");
				indexStartstatusOrder=indexStartstatusOrder+"statusOrder:'%".length();
				
				int indexEndstatusOrder = indexStartstatusOrder+1;
				if(indexEnd-indexStart>=1){
				while(filterCriterias.charAt(indexEndstatusOrder)!='%'){
					indexEndstatusOrder++;	
				}
				filterCriterias = filterCriterias.replace((filterCriterias.substring(indexStartstatusOrder, indexEndstatusOrder)),(filterCriterias.substring(indexStartstatusOrder, indexEndstatusOrder)));
				}
				else
				{
					filterCriterias = filterCriterias.replace((filterCriterias.substring(indexStartstatusOrder, indexEndstatusOrder)),(filterCriterias.substring(indexStartstatusOrder, indexEndstatusOrder)));
				}
				logger.debug("--->>>filter Criterion is--->> "+filterCriterias);
			}
			}
			//ends here
			//logger.debug("ERROR2");
			logger.debug("filterCriterias "+filterCriterias);
			SRAdministrationListResult result ;
			if(filterCriterias == "" || null == filterCriterias)
			{	
			logger.debug("Inside IF");	
			result = serviceRequestLocaleService.retrieveSRAdministrationList(contract);
			}
			else
			{
			logger.debug("Inside else"+filterCriterias);			
			result = serviceRequestLocaleService.retrieveSRAdministrationListFilter(contract,filterCriterias);
			logger.debug("No Result "+result.getTotalCount());
			}
			if (result.isSucceed()) {
				logger.debug("inside IF In the controller");
				List<SiebelLocalization> siebelLocalizations = result.getSiebelLocalizations();			
				//logger.debug("siebelLocalizations-->>"+siebelLocalizations);
				logger.debug("siebelLocalizations size()-->>"+siebelLocalizations.size());
				String xml = getXmlOutputGenerator(request.getLocale()).siebelLocalizations2XML(siebelLocalizations,
						request, response);
				logger.debug(xml);
				logger.debug("After the XML");
				response.setContentType("text/xml");
				OutputStream portletOutputStream = response.getPortletOutputStream();
				portletOutputStream.write(xml.getBytes());
				succeed = true;
			}
		} catch (Exception e) {
			succeed = false;
		}
		if (!succeed) {
			ServiceStatusUtil.responseResult(response, ERROR_PREFIX + "list", request.getLocale());
		}
	}
	
	//added
	
	/**
	 * @param receivedDirectValue 
	 * @param receivedInDirectValue 
	 * @return String 
	 */
	public String partnerTypeDecider(String receivedDirectValue,String receivedInDirectValue)
	{
		{
			logger.debug("Inside Partner Type Decider"+receivedDirectValue+">>"+receivedInDirectValue);
			String partnerTypeResult= "";
			if(receivedDirectValue.equals("YES") && receivedInDirectValue .equals("YES")){
				logger.debug("Loop1");
				partnerTypeResult= "DIRECT_BOTH_INDIRECT";
			}
			if(receivedDirectValue.equals("YES") && receivedInDirectValue.equals("NO")){
				logger.debug("Loop2");
				partnerTypeResult= "DIRECT";
			}
		if(receivedDirectValue.equals("YES") && receivedInDirectValue.equals("")){
			logger.debug("Loop3");
			partnerTypeResult= "BOTH_DIRECT";
			}
		if(receivedDirectValue.equals("NO") && receivedInDirectValue.equals("NO")){
			logger.debug("Loop4");
			partnerTypeResult= "INDIRECT";
			}
		if(receivedDirectValue.equals("") && receivedInDirectValue.equals("YES")){
			logger.debug("Loop5");
			partnerTypeResult= "BOTH_INDIRECT";
			}
		if(receivedDirectValue.equals("NO") && receivedInDirectValue == ""){
			logger.debug("Loop6");
			partnerTypeResult= "INDIRECT";
			}
		if(receivedDirectValue.equals("NO") && receivedInDirectValue.equals("YES")){
			logger.debug("Loop7");
			partnerTypeResult= "INDIRECT";
			}
		if(receivedDirectValue.equals("NULL") && receivedInDirectValue.equals("NULL")){
			logger.debug("Loop8");
			partnerTypeResult= "NULL";
			}
		if(receivedDirectValue.equals("NULL") && receivedInDirectValue.equals("")){
			logger.debug("Loop9");
			partnerTypeResult= "NULL";
			}
		if(receivedDirectValue.equals("") && receivedInDirectValue.equals("NULL")){
			logger.debug("Loop10");
			partnerTypeResult= "NULL";
			}
		if(receivedDirectValue.equals("YES") && receivedInDirectValue.equals("NULL")){
			logger.debug("Loop11");
			partnerTypeResult= "DIRECT";
			}
		if(receivedDirectValue.equals("NULL") && receivedInDirectValue.equals("NO")){
			logger.debug("Loop12");
			partnerTypeResult= "INDIRECT";
			}
		if(receivedDirectValue.equals("NULL") && receivedInDirectValue.equals("YES")){
			logger.debug("Loop13");
			partnerTypeResult= "INDIRECT";
			}
		if(receivedDirectValue.equals("NO") && receivedInDirectValue.equals("NULL")){
			logger.debug("Loop14");
			partnerTypeResult= "INDIRECT";
			}
		if(receivedDirectValue.equals("") && receivedInDirectValue.equals("NO")){
			logger.debug("Loop14");
			partnerTypeResult= "DIRECT";
			}
		
		return partnerTypeResult;
		}
	}
	
	/**
	 * @param receivedVal 
	 * @param pattern 
	 * @return boolean 
	 */
	public boolean findMatch(String receivedVal,String pattern){
		logger.debug("Inside match");
		boolean matchFlag = false;
		logger.debug("---"+receivedVal+"-----"+pattern);
		for(int charCount=0;charCount<receivedVal.length();charCount++)			
		{
			
			if(receivedVal.charAt(charCount) == pattern.charAt(charCount))
			{
				logger.debug("--"+receivedVal.charAt(charCount)+"---"+pattern.charAt(charCount));
				logger.debug("Inside IF");
				matchFlag = true;
			}
			else
			{
				logger.debug("--"+receivedVal.charAt(charCount)+"---"+pattern.charAt(charCount));
				logger.debug("Inside Else");
				matchFlag = false;
				break;
			}
			
		}
		
		return matchFlag;
		
	}
	
	/**
	 * @param optionType 
	 * @return String 
	 */
	private String getOptionDisplayType(String optionType) {
		for(SiebelLocalization.SiebelLocalizationOptionEnum e:SiebelLocalization.SiebelLocalizationOptionEnum.values()) {
			if(e.getDisplayName().equals(optionType)) {
				logger.debug("The portalDB option value is "+e.getDisplayName());
				return e.getValue();
			}
		}
		return "";
	}
	
	/**
	 * @param optionType 
	 * @return String 
	 */
	private String getOptionDisplayDropDownType(String optionType) {
		
		for(SiebelLocalization.SiebelLocalizationOptionEnum e:SiebelLocalization.SiebelLocalizationOptionEnum.values()) {
			if(e.getValue().equals(optionType)) {
				
				return e.getDisplayName();
			}
		}
		return "";
	}
	//ends here

	/**
	 * @param id 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=details")
	public void showSRAdministrationDetailsAction(@RequestParam(value = "id", required = false) Integer id,
			Model model, ActionRequest request, ActionResponse response) throws Exception {
		SiebelLocalization siebelLocalization = null;
		if(id == null) {
			siebelLocalization = new SiebelLocalization();
		} else {
			SRAdministrationDetailContract contract = ContractFactory.getSRAdministrationDetailContract();
			contract.setSiebelLocalizationId(id);
			SRAdministrationDetailResult detailResult = serviceRequestLocaleService
				.retrieveSRAdministrationDetail(contract);
			siebelLocalization = detailResult.getSiebelLocalization();
		}
		SupportedLocaleListResult supportedLocaleListResul = serviceRequestLocaleService
				.retrieveSupportedLocaleList();
		List<SupportedLocale> supportedLocales = supportedLocaleListResul.getSupportedLocales();


		SRLocaleDetailForm form = SRLocaleDetailForm.assembleFrom(siebelLocalization, supportedLocales);

		model.addAttribute("form", form);
		response.setRenderParameter("action", "details");
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=details")
	public String showSRAdministrationDetailsPage(Model model, RenderRequest request, RenderResponse response)
			throws Exception {
		return "serviceRequest/serviceRequestLocaleDetailsPage";
	}

	/**
	 * @param form 
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@RequestMapping(params = "action=save")
	public void saveLocale(SRLocaleDetailForm form, Model model, ActionRequest request, ActionResponse response) {
		boolean succeed = false;
		List<String> errors = new ArrayList<String>();
		Locale locale = request.getLocale();
		try {
			SiebelLocalization siebelLocalization = null;
			if (form != null) {
				siebelLocalization = form.deassemble(errors);
				boolean validate = errors.isEmpty();
				if (validate) {
					SRAdministrationSaveContract contract = ContractFactory.getSRAdministrationSaveContract();
					contract.setSiebelLocalization(siebelLocalization);
					SRAdministrationSaveResult result = serviceRequestLocaleService
							.saveSRAdministrationDetail(contract);
					if (result.isSucceed()) {
						succeed = true;
					}
				}
			}
		} catch (Exception e) {
			succeed = false;
		}
		logger.debug("-->" + succeed);
		errors.add((succeed ? MESSAGE_PREFIX : ERROR_PREFIX) + "save");

		for (String status : errors) {
			ServiceStatusUtil.checkServiceStatus(model, status, locale, true);
		}
		if (succeed) {
			response.setRenderParameter("action", "");
		} else {
			model.addAttribute("form", form);
			response.setRenderParameter("action", "details");
		}
	}

	/**
	 * @param id 
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("delete")
	public void deleteLocale(@RequestParam Integer id, Model model, ResourceRequest request, ResourceResponse response) {
		boolean succeed = false;
		try {
			logger.debug("in delete method. id=" + id);
			SRLocalizationContract contract = ContractFactory.getSRLocalizationContract();
			contract.setSiebelLocalizationId(id);
			SRLocalizationResult result = serviceRequestLocaleService.deleteSRLocalization(contract);
			succeed = result.isSucceed();
		} catch (Exception e) {
			logger.error(e);
			succeed = false;
		}
		ServiceStatusUtil.responseResult(response, (succeed ? MESSAGE_PREFIX : ERROR_PREFIX) + "delete", request.getLocale(),id+"");
	}

}
