package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;

import com.amind.common.util.StringUtils;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.CompanyTreeBean;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.hook.RequestCreateFlags;
import com.lexmark.services.portlet.fleetmanagement.FleetManagementController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.TreeGenerator;
import com.liferay.portal.util.PortalUtil;
/**
 * @author wipro 
 *
 */
@Controller
@RequestMapping("VIEW")
public class OPSLevelSelection extends BaseController{
	private static Logger LOGGER = LogManager.getLogger(OPSLevelSelection.class);
	private String queryGlobal="select distinct COMPANY_MDM_GLOBAL_ULT_NO,COMPANY_MDM_GLOBAL_ULT_Name from idmda.company_detail " +
			"where upper(COMPANY_MDM_GLOBAL_ULT_NAME) like :searchString and rownum <250";
	private String legalNull="select distinct COMPANY_MDM_ID,COMPANY_LEGAL_NAME from idmda.company_detail " +
			"where UPPER(COMPANY_LEGAL_NAME) LIKE :searchString AND COMPANY_MDM_GLOBAL_ULT_NO IS NULL AND  " +
			"COMPANY_MDM_DOMESTIC_ULT_NO IS NULL AND COMPANY_MDM_GLOBAL_ULT_NAME IS NULL AND COMPANY_MDM_DOMESTIC_ULT_NAME IS NULL ";
	
	private String domesticQuery="select distinct COMPANY_MDM_DOMESTIC_ULT_No,COMPANY_MDM_DOMESTIC_ULT_Name " +
			"from idmda.company_detail where upper(COMPANY_MDM_GLOBAL_ULT_No) = :id and rownum <250";
	
	private String legalQuery="select distinct COMPANY_MDM_ID,COMPANY_LEGAL_NAME from idmda.company_detail where " +
			"upper(COMPANY_MDM_GLOBAL_ULT_No) = :global and COMPANY_MDM_DOMESTIC_ULT_No=:domestic and rownum <250";
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 */
	@RequestMapping
	public String displayTree(RenderRequest request, RenderResponse response,Model model){
		
		PortletSession session=request.getPortletSession();
		if(request.getParameter("selected")!=null && "true".equalsIgnoreCase(request.getParameter("selected"))){
			model.addAttribute("selected", true);
		}
		model.addAttribute("companyName", ((ServicesUser)PortalSessionUtil.getServiceUser(session)).getCompanyName());
		
		return "opsLevelSelection/levelSelection";
	}
	
	/**
	 * @param searchString 
	 * @param id 
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("levelList")
	public void generateGlobal(@RequestParam(value="sString",required=false) String searchString,
			@RequestParam(required=false,value="id") String id,ResourceRequest request,ResourceResponse response) throws IOException{
		
		LOGGER.debug("in resurce mapping sstring=" + searchString+" id="+id);
		
		if(StringUtils.isNotBlank(id) && id.indexOf("^g")!=-1){
			//Call for Domestic Query L2
			LOGGER.debug("~~~~~~~~ Calling for Domestic ~~~~`");
			String gId=id.substring(0,id.indexOf("^g"));			
			generateTreefromList(request,response,getDomestic(gId),id);
		}else if(StringUtils.isNotBlank(id) && id.indexOf("^d")!=-1){
			//Call for Legal QueryL3
			LOGGER.debug("~~~~~~~~ Calling for LEGAL ~~~~`");
			String globalId=id.substring(0,id.indexOf("^pG"));
			String domesticId=id.substring(id.indexOf("^pG")+3,id.indexOf("^d"));			
			generateTreefromList(request,response,getLegal(globalId,domesticId),id);
		}else if((StringUtils.isNotBlank(id) && id.indexOf("^l")!=-1)){
			//Call for  QueryL4
			LOGGER.debug("~~~~~~~~ Calling for L4 ~~~~`");
			String legalId=id.substring(0,id.indexOf("^l"));
		
				generateTreefromList(request,response,getL4(legalId,"Legal"),id);
		
			
		}else if(StringUtils.isNotBlank(id) && id.indexOf("^5l")!=-1){
			LOGGER.debug("~~~~~~~~ Calling for L5 ~~~~`");
			String L4Id=id;//.substring(0,id.indexOf("^l5"));
			generateTreefromList(request,response,getL5(L4Id),id);
			
		}else if(StringUtils.isNotBlank(searchString) && searchString.indexOf("^l")!=-1){
			//This is for MDMD ACcount ID calll
			LOGGER.debug("in mdmd account id call");
			String legalId=searchString.substring(0,searchString.indexOf("^l"));
			generateTreefromList(request,response,getL4(legalId,""),"0");	
			
		}else if (StringUtils.isNotBlank(id) && id.indexOf("^chl")!=-1){
			LOGGER.debug("in chl call");
			generateTreefromList(request,response,getChlList(id),id);
			
		}else if(id==null){
			LOGGER.debug("~~~~~~~~ Calling for GLOBAL ~~~~`");
			Query query = HibernateUtil.getSession().createSQLQuery(queryGlobal);
			LOGGER.debug("1 + st"+searchString);
			Query queryLegal= HibernateUtil.getSession().createSQLQuery(legalNull);
			LOGGER.debug("2+ st"+searchString);
			
			LOGGER.debug("in resurce mapping + st"+searchString);
			
	    	String searchstring="%" + searchString.toUpperCase() + "%";
	    	query.setParameter("searchString", searchstring);
	    	queryLegal.setParameter("searchString", searchstring);
	    	
	    	LOGGER.debug(query.toString());
	    	LOGGER.debug(queryLegal.toString());
	    	LOGGER.debug("QueryExecuted");
	    	List list = query.list();
	    	List listLegal = queryLegal.list();
	    	List<CHLNode> chlNodeList=new ArrayList<CHLNode>();
	    	Map<String,String> globalMap=new HashMap<String,String>();
	    	
	    	if(list != null && list.size() > 0) {
	    		LOGGER.debug("List is not null");
				for(int i = 0; i < list.size(); i++){
						
				        Object[] row = (Object[]) list.get(i);
				        LOGGER.debug("Record No:-- "+i+"\n");
				        LOGGER.debug("gbultNo-- "+(String) row[0]+"  gname--- "+(String) row[1]+"\n");
				       
				        String globalNo = (String) row[0];
				        String globalName = (String) row[1];
				        globalMap.put(globalNo, globalName);		        
				        
	        	}
				
	    	
	    	
	    	}
	    	for(String key:globalMap.keySet()){
				CHLNode ctbean = new CHLNode();
				ctbean.setCHLNodeId(key+"^g");
		        ctbean.setChlNodeName(key +"-"+globalMap.get(key));
		        ctbean.setHasChild(true);
		        chlNodeList.add(ctbean);
			}
	    	Set<String> keys=globalMap.keySet();			
			List<String> listKeys=new ArrayList<String>();
			listKeys.addAll(keys);
	    	if(listLegal != null && listLegal.size() > 0) {
	    		LOGGER.debug("List is not null");
				for(int i = 0; i < listLegal.size(); i++){
						
				        Object[] row = (Object[]) listLegal.get(i);
				        LOGGER.debug("Record No:-- "+i+"\n");
				        LOGGER.debug("legalNo-- "+(String) row[0]+"  legalName--- "+(String) row[1]+"\n");
				       
				        String legalNo = (String) row[0];
				        String legalName = (String) row[1];
				        globalMap.put(legalNo, legalName);	
				       
				        		        
				        
	        	}
				
	    	
	    	}
	    	
	    	for(String key:globalMap.keySet()){
	    		if(!listKeys.contains(key)){
	    			CHLNode ctbean = new CHLNode();
					ctbean.setCHLNodeId(key+"^l");
			        ctbean.setChlNodeName(key +"-"+globalMap.get(key));
			        ctbean.setHasChild(true);
			        chlNodeList.add(ctbean);
	    		}
				
			}
	    	
	    	
	    	String nodeId=id;
	    	if(nodeId == null){
				
				nodeId = LexmarkSPConstants.ROOT_NODE_ID;
				
			}
	    	generateTreefromList(request,response,chlNodeList,nodeId);
			
		}
		
		
		
		
		
    	
    	
	}
	/**
	 * @param L4Id 
	 * @return List<CHLNode> 
	 */
	private List<CHLNode> getL5(String L4Id){
		
		
		
		boolean firstCall=false;
		
		String fL4Id=L4Id.substring(0,L4Id.indexOf("^5l"));
		if(L4Id.indexOf("^5l")!=-1){
			
			firstCall=true;
		}
		
		
		List<CHLNode> listNodeFinal=null;
		//Temporary hardcoded
		/*contract.setMdmId("69316");
		contract.setMdmLevel("Legal");*/
		
		if(firstCall){
			
			LOGGER.debug("in first calll");
			SiebelAccountListContract firstContract = new SiebelAccountListContract();
			firstContract.setMdmId(fL4Id);
			firstContract.setMdmLevel("Account");
			List<CHLNode> listNode=new ArrayList<CHLNode>();
			
			ObjectDebugUtil.printObjectContent(firstContract, LOGGER);
			
			List<Account>  accountList=serviceRequestService.retrieveSiebelAccountList(firstContract).getAccountList();
			LOGGER.debug("accountList ="+accountList);
			for(Account a:accountList){
				CHLNode n=new CHLNode();
				n.setCHLNodeId(a.getAccountId()+"^chl");
				n.setChlNodeName(a.getAccountName());
				n.setHasChild(true);
				listNode.add(n);
			}
			listNodeFinal=listNode;
		}
		
		
				
		return 	listNodeFinal;
	}
	
	/**
	 * @param chlid 
	 * @return List<CHLNode> 
	 */
	private List<CHLNode> getChlList(String chlid){
		
			//This is for inter tree call
			LOGGER.debug("in chl calll");
			LocationReportingHierarchyContract contract = ContractFactory.getCustomerHierarchyChildNodeContract();
			
			if(chlid.indexOf("^chl")!=-1){
				String chl=chlid.substring(0, chlid.indexOf("^chl"));
				contract.setChlNodeId(chl);
			}
			
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			AssetReportingHierarchyResult result=new AssetReportingHierarchyResult();
			try {
				result = deviceService.retrieveAssetReportingHierarchy(contract);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.debug("Exception"+e.getMessage());
			}
			LOGGER.debug("after result ");
			if(result.getChlNodeList()!=null){
				//Edit for further calls to this.
				List<CHLNode> listNode=result.getChlNodeList();
				for(CHLNode n:listNode){
					n.setCHLNodeId(n.getCHLNodeId()+"^chl");
				}
			}
			
			return result.getChlNodeList();
		
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param chlNodeList 
	 * @param id 
	 */
	private void generateTreefromList(ResourceRequest request, 
			ResourceResponse response,List<CHLNode> chlNodeList,String id){
		String chlNodeTreeXML = new TreeGenerator(request.getLocale()).generateOPSHierarchyXML(chlNodeList,id);
		PrintWriter out;
		try {
			out = response.getWriter();
			response.setContentType("text/xml");
			out.print(chlNodeTreeXML);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Exception"+e.getMessage());
		}
		
    	
	}
	
	/**
	 * @param globalId 
	 * @param domesticId 
	 * @return List<CHLNode> 
	 */
	private List<CHLNode> getLegal(String globalId, String domesticId){
		Query query = HibernateUtil.getSession().createSQLQuery(legalQuery);
		query.setParameter("global", globalId);
		query.setParameter("domestic", domesticId);
		List list = query.list();
		List<CHLNode> chlNodeList=new ArrayList<CHLNode>();
		if(list != null && list.size() > 0) {
    		LOGGER.debug("List is not null");
			for(int i = 0; i < list.size(); i++){
					
			        Object[] row = (Object[]) list.get(i);
			        LOGGER.debug("Record No:-- "+i+"\n");
			        LOGGER.debug("LegalNo-- "+(String) row[0]+"  Legalname--- "+(String) row[1]+"\n");
			        CHLNode node=new CHLNode();
			        String legalNo = (String) row[0];
			        String legalName = (String) row[1];
			        node.setCHLNodeId(legalNo+"^l");
			        node.setChlNodeName(legalNo+"-"+legalName);
			        node.setHasChild(true);
			        chlNodeList.add(node);
        	}
			
    	
    	
    	}
		return chlNodeList;
	}
	
	/** 
	 * @param companyMdmId 
	 * @param mdmLevel 
	 * @return List<CHLNode> 
	 */
	private List<CHLNode> getL4(String companyMdmId,String mdmLevel){
		LOGGER.debug("companyMdmId ="+companyMdmId);
		
         String L4QueryLegal = "SELECT COMPANY_DA_ID,COMPANY_LEGAL_NAME,COMPANY_TYPE,COMPANY_ROLE,COMPANY_MDM_ID FROM IDMDA.COMPANY_DETAIL WHERE COMPANY_STATUS ='A' AND COMPANY_MDM_ID =:companyId";
         String L4QueryMDMAccId = "SELECT COMPANY_DA_ID,COMPANY_LEGAL_NAME,COMPANY_TYPE,COMPANY_ROLE,COMPANY_MDM_ID FROM IDMDA.COMPANY_DETAIL WHERE COMPANY_STATUS ='A' AND COMPANY_DA_ID =:companyId";
         String finalQuery;
        if("Legal".equalsIgnoreCase(mdmLevel)){
        	finalQuery=L4QueryLegal;
        }else{
        	finalQuery=L4QueryMDMAccId;
        }
         
         Query query = HibernateUtil.getSession().createSQLQuery(finalQuery);
         query.setParameter("companyId", companyMdmId);
 		
 		List list = query.list();
 		List<CHLNode> chlNodeList=new ArrayList<CHLNode>();
 		if(list != null && list.size() > 0) {
     		LOGGER.debug("List is not null");
 			for(int i = 0; i < list.size(); i++){ 					
 			        Object[] row = (Object[]) list.get(i);
 			        LOGGER.debug("Record No:-- "+i+"\n");
 			        LOGGER.debug("L4No-- "+(String) row[0]+"  L4name--- "+(String) row[1]+"\n");
 			        CHLNode node=new CHLNode();
 			        String L4No = (String) row[0];
 			        String L4Name = (String) row[1] ;
 			        StringBuffer dispName=new StringBuffer((String) row[1]);
 			        dispName.append("(").append(L4No).append(")").append(" - ").append((String) row[2]).append(" - ").append((String) row[3]);
 			        //L4No="72140";//hardcoded!!
 			        node.setCHLNodeId(L4No+"^5l");
 			        node.setChlNodeName(dispName.toString());
 			        node.setHasChild(true);
 			        chlNodeList.add(node);
         	}
 			
     	
     	
     	}else{
     		LOGGER.debug("list is null");
     	}
 		
 		return chlNodeList;
         
         
       /*  companyDetails += "<option value='" + cBean.getCompanyID() + "'>" + cBean.getCompanyName() + "(" + cBean.getCompanyID() + ")" + " - " + cBean.getCompanyType() + " - " + cBean.getCompanyRole() + "</option>";
         
		
		 levelName = splitcompanyMdmIdLevel[0];//Returns MDM ID LEVEL.
         companyMdmId = splitcompanyMdmIdLevel[1];//Returns MDM ID.
*/	}
	
	/**
	 * @param id 
	 * @return List<CHLNode> 
	 */
	private List<CHLNode> getDomestic(String id){
		Query query = HibernateUtil.getSession().createSQLQuery(domesticQuery);
		query.setParameter("id", id);
		List list = query.list();
		List<CHLNode> chlNodeList=new ArrayList<CHLNode>();
		if(list != null && list.size() > 0) {
    		LOGGER.debug("List is not null");
			for(int i = 0; i < list.size(); i++){
					
			        Object[] row = (Object[]) list.get(i);
			        LOGGER.debug("Record No:-- "+i+"\n");
			        LOGGER.debug("DomesticNo-- "+(String) row[0]+"  Domesticname--- "+(String) row[1]+"\n");
			        CHLNode node=new CHLNode();
			        String domesticNo = (String) row[0];
			        String domesticName = (String) row[1];
			        node.setCHLNodeId(id+"^pG"+domesticNo+"^d");
			        node.setChlNodeName(domesticNo+"-"+domesticName);
			        node.setHasChild(true);
			        chlNodeList.add(node);
        	}
			
    	
    	
    	}
		return chlNodeList;
		
		
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=submitForm")
	public void submitForm(ActionRequest request,ActionResponse response) throws Exception{
		
		PortletSession session = request.getPortletSession();
		
		ServicesUser servicesUser = PortalSessionUtil.getServiceUser(session);
		if(servicesUser == null) {
			servicesUser = new ServicesUser();
		}
		LOGGER.debug("mdm id ="+request.getParameter("mdmId"));
		LOGGER.debug("mdm Level ="+request.getParameter("mdmLevel"));
		LOGGER.debug(" chl Id ="+request.getParameter("chlId"));
		
		servicesUser.setMdmId(request.getParameter("mdmId"));
		servicesUser.setMdmLevel(request.getParameter("mdmLevel"));
		servicesUser.setChlNodeId(request.getParameter("chlId"));
		servicesUser.setCompanyName(request.getParameter("accntName"));
		
		request.getPortletSession().setAttribute("employeeMdmId", servicesUser.getMdmId(), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmLevel", servicesUser.getMdmLevel(), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeAccountName", servicesUser.getCompanyName() + " (" + servicesUser.getMdmId() + ")", PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeReportMdmId", servicesUser.getMdmId(), PortletSession.APPLICATION_SCOPE);
		session.setAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER,false,PortletSession.APPLICATION_SCOPE);
		
		PortalSessionUtil.setServicesUser(session, servicesUser);
		PortalSessionUtil.setOPSAccountSelected(session);
		
		SiebelAccountListContract siebelAccountListContract = new SiebelAccountListContract();
		
		
		siebelAccountListContract.setMdmId(PortalSessionUtil.getMdmId(session));
		siebelAccountListContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(siebelAccountListContract,LOGGER);
		LOGGER.info("end printing lex loggger");
		SiebelAccountListResult siebelAccountListResult = serviceRequestService.retrieveSiebelAccountList(siebelAccountListContract);
		List<Account> accountListCustomer = siebelAccountListResult.getAccountList();
	
		
		
		Map<String,String> requestAccessMap=RequestCreateFlags.retrieveReqHistoryAccess4Portal(PortalUtil.getHttpServletRequest(request),
				PortalSessionUtil.getUserSegment(request.getPortletSession()), accountListCustomer, LexmarkUserUtil.getUserRoleNameList(request));
		for(String key:requestAccessMap.keySet()){
			requestAccessMap.put(key, "true");
		}
		request.getPortletSession().setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap, PortletSession.APPLICATION_SCOPE);
		
		response.setRenderParameter("selected", "true");
		
	}
}
