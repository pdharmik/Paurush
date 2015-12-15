package com.lexmark.services.portlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.BillerDirectContract;
import com.lexmark.domain.BillerDirect;
import com.lexmark.domain.BillerDirectForm;
import com.lexmark.domain.SapPortlet;
import com.lexmark.domain.SapPortletURL;
import com.lexmark.result.BillerDirectResult;
import com.lexmark.service.api.BillerDirectService;

@Controller
@RequestMapping("VIEW")
public class BillerDirectController extends BaseController {
	
	private static Logger LOGGER = LogManager.getLogger(BillerDirectController.class);
	/*private static List<String> sno;
	private static List<String> funcName;
	private static List<String> funcUrl;
	private static List<String> languageSupport;*/
	@Autowired
	private BillerDirectService billerDirectService;
	@RequestMapping
	public String getBillerDirectLandingPage(Model model, RenderRequest request, RenderResponse response) throws Exception {
		LOGGER.debug("--------------- ENTERED LANDING PAGE ---------------");
		String firstURL = "";
		String prefLang = getPreferedLanguage(request.getPortletSession());
		BillerDirectResult billerDirectResult = billerDirectService.retrieveBillerDirectURLDisplay();
		List<SapPortlet> billerDirectURLList = billerDirectResult.getSapPortletList();
		LOGGER.info("Get the result back");
		List<BillerDirect> horizontalURLList = new ArrayList<BillerDirect>();
		if(billerDirectURLList != null && billerDirectURLList.size()>0){
			//get the horizontal bar list
			LOGGER.info("Inside if bloak");
			for(int i=0;i<billerDirectURLList.size();i++){
				if("HORIZONTAL".equalsIgnoreCase(billerDirectURLList.get(i).getHeaderType())){
					LOGGER.info("inside if block");
					BillerDirect billerDirect = new BillerDirect();
					boolean horizontalURLAdded = false;
					if(billerDirectURLList.get(i).getSapPortletURLs() == null || billerDirectURLList.get(i).getSapPortletURLs().size() ==0){
						billerDirect.setFuncName(billerDirectURLList.get(i).getGridFunctionalityName());
						billerDirect.setFuncUrl(billerDirectURLList.get(i).getGridFunctionalityURL());
						LOGGER.info("Setting name as "+billerDirectURLList.get(i).getGridFunctionalityName()+
								" setting url as "+billerDirectURLList.get(i).getGridFunctionalityURL());
						horizontalURLAdded = true;
					}else{
						for (SapPortletURL billerDirectURL : billerDirectURLList.get(i).getSapPortletURLs()){
							if(prefLang.equalsIgnoreCase(billerDirectURL.getLanguage())){//Here use user specific language
								LOGGER.info("User specific language is "+prefLang);
								billerDirect.setFuncName(billerDirectURL.getFunctionalityName());
								billerDirect.setFuncUrl(billerDirectURL.getFunctionalityURL());
								LOGGER.info("Setting name as "+billerDirectURL.getFunctionalityName()+" setting url as "+billerDirectURL.getFunctionalityURL());
								horizontalURLAdded = true;
								break;
							}
						}//write a condition here if the url is still not added
						if(!horizontalURLAdded){
							billerDirect.setFuncName(billerDirectURLList.get(i).getGridFunctionalityName());
							billerDirect.setFuncUrl(billerDirectURLList.get(i).getGridFunctionalityURL());
							LOGGER.info("Main Setting name as "+billerDirectURLList.get(i).getGridFunctionalityName()+
									" setting url as "+billerDirectURLList.get(i).getGridFunctionalityURL());
							horizontalURLAdded = true;
						}
					}
					LOGGER.info("HORIZONTal URL "+i);
					horizontalURLList.add(billerDirect);
				}
			}
			model.addAttribute("urlListSize", horizontalURLList.size());
			LOGGER.info("size of the list value is "+horizontalURLList.size());
			LOGGER.info("End of if block");
		}
		if(billerDirectURLList != null && billerDirectURLList.size()>0){
			if(horizontalURLList!=null){
				firstURL = horizontalURLList.get(0).getFuncUrl();
				LOGGER.info("First URL is coming as "+firstURL);
			}
		}
		model.addAttribute("firstURL", firstURL);
		/*model.addAttribute("tab1",horizontalURLList.get(0).getFuncName());
		model.addAttribute("tab2",horizontalURLList.get(1).getFuncName());*/
		model.addAttribute("horizontalURLList", horizontalURLList);
		LOGGER.debug("--------------- EXIT LANDING PAGE ---------------");
		return "billerDirect/billerDirectLandingPage";
	}
	
	
	
	
	/**
	 * This method is used to render the edit sap portlet landing page 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */

	@RequestMapping(params = "action=showEditPortletPage")
	public String showEditPortletPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showEditPortletPage -----------------------");
		BillerDirectForm billerDirectForm = new BillerDirectForm();
		model.addAttribute("billerDirectForm", billerDirectForm);
		BillerDirectResult billerDirectResult = billerDirectService.retrieveSupportedLocaleList();
		Map<String, String> languageMap = billerDirectResult.getLanguageMap();
		PortletSession session = request.getPortletSession();
		session.removeAttribute("languageMap");
		session.setAttribute("languageMap", languageMap);
		model.addAttribute("languageMap", languageMap);
		return "billerDirect/billerDirectEditPage";
	}
	
	/**
	 * ResourceMapping for the retrieve url list
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResourceMapping("retriveSapList")
	public void retriveSapList( Model model, ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("-----------------------[IN]retriveSapList----------------------");
		BillerDirectResult billerDirectResult = billerDirectService.retrieveBillerDirectURLDisplay();
		PortletSession session = request.getPortletSession();
		List<SapPortlet> billerDirectList = billerDirectResult.getSapPortletList();
		LOGGER.info("Biller durect list sixe is "+billerDirectList.size());
		if(billerDirectList==null){
			billerDirectList = new ArrayList<SapPortlet>();
		}
		String content = getXmlOutputGenerator(request.getLocale()).sapURLListViewXml(session, billerDirectList, request.getContextPath());
		PrintWriter out =  response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		LOGGER.debug("-----------------------[OUT]retriveSapList----------------------");
	}
	
	
	
	@ActionMapping(params= "action=updateBillerDirectURL")
	public void submitBillerDirectURL(ActionRequest request,ActionResponse response,Model model,@ModelAttribute("billerDirectForm") BillerDirectForm billerDirectForm) throws Exception{
		LOGGER.info("------------------------ [IN] submitBillerDirectURL ---------------------------");
		List<String> sno=new ArrayList<String>();
		List<String> funcName=new ArrayList<String>();
		List<String> urlId=new ArrayList<String>();
		List<String> funcUrl=new ArrayList<String>();
		List<String> languageSupport=new ArrayList<String>();
		
		
		sno = putDataInList(billerDirectForm.getSno());
		urlId = putDataInList(billerDirectForm.getFuncURLId());
		funcName = putDataInList(billerDirectForm.getFuncName());
		funcUrl = putDataInList(billerDirectForm.getFuncUrl());
		languageSupport = putDataInList(billerDirectForm.getLanguageSupport());
		
		
		
		
		for(int i=0;i<sno.size();i++){
			LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ sno values are "+sno.get(i));
		}
		for(int i=0;i<funcName.size();i++){
			LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ funcName values are "+funcName.get(i));
		}
		for(int i=0;i<funcUrl.size();i++){
			LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ funcUrl values are "+funcUrl.get(i));
		}
		for(int i=0;i<languageSupport.size();i++){
			LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ languageSupport values are "+languageSupport.get(i));
		}
		LOGGER.info("Size of main grid "+sno.size());
		LOGGER.info("Size of main grid other one "+billerDirectForm.getBillerDirectURLList().size());
		/*if (billerDirectForm.getBillerDirectURLList()!=null){
			for(int j=0;j<billerDirectForm.getBillerDirectURLList().size();j++){
				for (int i = 1;i <billerDirectForm.getBillerDirectURLList().get(j).getBillerDirectList().size();i++){
					LOGGER.info("this is for "+j+" no row of main grid");
					LOGGER.info("printing value1 "+billerDirectForm.getBillerDirectURLList().get(j).getBillerDirectList().get(i).getFuncName());
					LOGGER.info("printing value2 "+billerDirectForm.getBillerDirectURLList().get(j).getBillerDirectList().get(i).getFuncUrl());
					LOGGER.info("printing value3 "+billerDirectForm.getBillerDirectURLList().get(j).getBillerDirectList().get(i).getLanguage());
				}
			}
		}*/
		//Lets create the contract to save the data in DB
		List<SapPortlet> sapPortletList = new ArrayList<SapPortlet>();
		int urlListValidRow = ((billerDirectForm.getBillerDirectURLList()!=null)? billerDirectForm.getBillerDirectURLList().size():-1);
		LOGGER.info("Need to loop till following row id "+urlListValidRow);
		LOGGER.info("Now need "+sno.size()+"number loop");
		for(int i=0;i<sno.size();i++){
			LOGGER.info("Loop start. Lets see how many loops");
			SapPortlet sapPortlet = new SapPortlet();
			String languageSupport1 = "F";
			//sapPortlet.setId(Integer.parseInt(sno.get(i)));
			sapPortlet.setHeaderType("HORIZONTAL");
			if(!("empty".equalsIgnoreCase(urlId.get(i)))){
				sapPortlet.setId(new Integer(urlId.get(i).toString()));
			}
			sapPortlet.setGridFunctionalityName(funcName.get(i));
			sapPortlet.setGridFunctionalityURL(funcUrl.get(i));
			LOGGER.info("@@@@@@@@@@@@@ checking if I am getting urlId here "+billerDirectForm.getFuncURLId());
			List <SapPortletURL> sapPortletURLs = new ArrayList<SapPortletURL>();
			if(i<urlListValidRow){
				if ( billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().size()>0){
					LOGGER.info("In the first iteration and data available in the sub row");
					LOGGER.info("The value "+billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().size());
					for (int j = 0;j <billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().size();j++){
						LOGGER.info("this is for "+j+" no row of sub grid and "+i+" no row of main grid");
						
						LOGGER.info("id value is "+billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getFuncId());
						languageSupport1 = "T";
						String functionalityName = billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getFuncName();
						String functionalityURL = billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getFuncUrl();
						String language = billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getLanguage();
						String funcId = billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getFuncId();
						if((functionalityName == null && functionalityURL==null)||("".equals(functionalityName) && "".equals(functionalityURL))) continue;
						SapPortletURL sapPortletURL = new SapPortletURL();
						sapPortletURL.setFunctionalityName(functionalityName);
						sapPortletURL.setFunctionalityURL(functionalityURL);
						sapPortletURL.setLanguage(language);
						LOGGER.info("Checking id is null or not");
						LOGGER.info("Id value is "+funcId);
						if(funcId!=null && !("".equalsIgnoreCase(funcId))){
							LOGGER.info("Id is not null here");
							sapPortletURL.setId(new Integer(funcId.toString()));
						}
						LOGGER.info("printing value1 "+functionalityName);
						LOGGER.info("printing value2 "+functionalityURL);
						LOGGER.info("printing value3 "+language);
						sapPortletURLs.add(sapPortletURL);
					}
				}else{
					LOGGER.info("In the first iteration and data NOT available in the sub row");
					/*SapPortletURL sapPortletURL = new SapPortletURL();
					sapPortletURL.setFunctionalityName(funcName.get(i));
					sapPortletURL.setFunctionalityURL(funcUrl.get(i));
					sapPortletURL.setLanguage("en");
					sapPortletURLs.add(sapPortletURL);*/
				}
				/*for (int j = 0;j <billerDirectForm.getBillerDirectURLList().get(j).getBillerDirectList().size();j++){
					if ( billerDirectForm.getBillerDirectURLList().get(j)!=null){
						LOGGER.info("this is for "+j+" no row of sub grid and "+i+" no row of main grid");
						String functionalityName = billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getFuncName();
						String functionalityURL = billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getFuncUrl();
						String language = billerDirectForm.getBillerDirectURLList().get(i).getBillerDirectList().get(j).getLanguage();
						SapPortletURL sapPortletURL = new SapPortletURL();
						sapPortletURL.setFunctionalityName(functionalityName);
						sapPortletURL.setFunctionalityURL(functionalityURL);
						sapPortletURL.setLanguage(language);
						LOGGER.info("printing value1 "+functionalityName);
						LOGGER.info("printing value2 "+functionalityURL);
						LOGGER.info("printing value3 "+language);
						sapPortletURLs.add(sapPortletURL);
					}
				}*/
			}else{
				LOGGER.info("No value in the sub row");
				/*SapPortletURL sapPortletURL = new SapPortletURL();
				sapPortletURL.setFunctionalityName(funcName.get(i));
				sapPortletURL.setFunctionalityURL(funcUrl.get(i));
				sapPortletURL.setLanguage("en");
				sapPortletURLs.add(sapPortletURL);*/
			}
			sapPortlet.setLanguageSupport(languageSupport1);
			sapPortlet.setSapPortletURLs(sapPortletURLs);
			sapPortletList.add(sapPortlet);
		}
		BillerDirectContract contract = new BillerDirectContract();
		contract.setSapPortletList(sapPortletList);
		boolean status = billerDirectService.saveBillerDirectUrl(contract);
		//Update done. Lets delete the urls.
		
		if(billerDirectForm.getDeletedURL()!=null){
			if(!("".equalsIgnoreCase(billerDirectForm.getDeletedURL()))){
				contract.setDeleteURL(billerDirectForm.getDeletedURL());
				/*List<String> deleteURL=new ArrayList<String>();
				deleteURL = putDataInList(billerDirectForm.getDeletedURL());
				for(int k=0;k<deleteURL.size();k++){
					LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ URL idS to be deleted "+deleteURL.get(k));
					boolean deleteSuccess = billerDirectService.deleteBillerDirectUrl(contract);
				}*/
				boolean deleteSuccess = billerDirectService.deleteBillerDirectUrl(contract);
				LOGGER.info("Status message received for delete service is "+deleteSuccess);
			}
		}
		//Delete sub row urls
		if(billerDirectForm.getDeleteSubRowURLId()!=null){
			if(!("".equalsIgnoreCase(billerDirectForm.getDeleteSubRowURLId()))){
				contract.setDeleteSubRowURLId(billerDirectForm.getDeleteSubRowURLId());
				LOGGER.info("Need to delete following sub row id "+billerDirectForm.getDeleteSubRowURLId());
				/*List<String> deleteURL=new ArrayList<String>();
				deleteURL = putDataInList(billerDirectForm.getDeletedURL());
				for(int k=0;k<deleteURL.size();k++){
					LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ URL idS to be deleted "+deleteURL.get(k));
					boolean deleteSuccess = billerDirectService.deleteBillerDirectUrl(contract);
				}*/
				boolean deleteSuccess = billerDirectService.deleteBillerDirectSubRowUrl(contract);
				LOGGER.info("Status message received for delete service is "+deleteSuccess);
			}
		}
		LOGGER.info("Status message received from service is "+status);
		LOGGER.info("Before calling renderParameter");
		response.setRenderParameter("action", "");//Sending the response to the default resource mapping
		LOGGER.info("--------------[EXIT] submitBillerDirectURL ---------------------");
	}
	private List<String> putDataInList(String data)
	{
		
		List<String> list = null;
		list = new ArrayList<String>();
		StringTokenizer st=new StringTokenizer(data, ",");
		while(st.hasMoreTokens())
			{
				list.add(st.nextToken());
			}
		return list;
	}
	
	private String getPreferedLanguage(PortletSession portletSession){
		String prefLang = "";
		LOGGER.info("Entered the function");
		Map<String, String> ldapUserData = (Map<String, String>) portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE);
		if(ldapUserData==null){
			LOGGER.info("ldapUserData is null");
		}
		String userLang = ldapUserData.get(LexmarkConstants.LANGUAGE);
		LOGGER.info("userLang is  "+userLang);
		LOGGER.info("Loop start");
		if (userLang.startsWith("zh")) prefLang = userLang;
		else if (userLang.startsWith("pt")) prefLang = userLang;
		else {
			prefLang = userLang.substring(0, userLang.indexOf('_'));
		}
		LOGGER.info("prefereed language is "+prefLang);
		return prefLang;
	}
}
