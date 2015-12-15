<%-- Changes for MPS 2.1 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Ends Changes for MPS 2.1 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<style type="text/css"><%@ include file="../../css/jquery/jquery-ui.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%@page import="java.util.HashMap"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.ASSET_ENTITLEMENT_FLAG"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.ISPARTNERPORTAL"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.ACNTCURRDETAILS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.SHOW_ASSET_ACCEPTANCE"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.ONLY_ASSET_ACCEPTANCE"%>


<%@page import="com.liferay.portal.util.PortalUtil"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<jsp:include page="/WEB-INF/jsp/include.jsp"/>

<portlet:resourceURL var="assetRequestHistoryURLvar" id="assetRequestHistory"></portlet:resourceURL>
<%

HashMap<?,?> hMap= (HashMap<?,?>)request.getSession().getAttribute("userAccessMapForSr");


String createServiceReq = "";
String createSuppliesReq = "";
String createChangeMgmtReq = "";
String catalogEntitlement = "";
String assetEntitlement = "";
String showAssetAcceptance="";
String onlyAssetAcceptance="";
String createHardwareReq = ""; //Added for Hardware Request in MPS2.1
String showSuppliesReq="";
String createMapRequest = "";

if(hMap.size()>0){
	createServiceReq = (String)hMap.get("CREATE SERVICE REQUEST");
	createSuppliesReq = (String)hMap.get("CREATE SUPPLIES REQUEST");
	createChangeMgmtReq = (String)hMap.get("CREATE CHANGE MGMT REQUEST");
	catalogEntitlement = (String)hMap.get(CATALOG_ENTITLEMENT_FLAG);
	assetEntitlement = (String)hMap.get(ASSET_ENTITLEMENT_FLAG);
	showAssetAcceptance = (String)hMap.get(SHOW_ASSET_ACCEPTANCE);
	createHardwareReq = (String)hMap.get("CREATE HARDWARE REQUEST"); //Added for Hardware Request in MPS2.1
	onlyAssetAcceptance=(String)hMap.get(ONLY_ASSET_ACCEPTANCE);
	showSuppliesReq=(String)hMap.get("SHOW SUPPLIES REQUEST");
	//out.println("createSuppliesReq="+createSuppliesReq+"\n createHardwareReq= "+createHardwareReq);
	createMapRequest=(String)hMap.get("CREATE MAP REQUEST");
}

//This portion is for parnter portal customer requests
	String currURL=PortalUtil.getCurrentCompleteURL(request);
	boolean isPartnerRequest=currURL.indexOf(ISPARTNERPORTAL)!=-1;
	if(isPartnerRequest){
		//HashMap<?,?> accountSelectedDetails= (HashMap<?,?>)request.getSession().getAttribute(ACNTCURRDETAILS); // commented to resolve the session issue
		HashMap<?,?> accountSelectedDetails= (HashMap<?,?>)request.getSession().getAttribute("userAccessMapForSr");
		catalogEntitlement = (String)accountSelectedDetails.get(CATALOG_ENTITLEMENT_FLAG);
		assetEntitlement = (String)accountSelectedDetails.get(ASSET_ENTITLEMENT_FLAG);
	}
	
	boolean isFleetRequest=currURL.indexOf("fleet-management")!=-1;
	boolean isGridViewRequest=currURL.indexOf("grid-view")!=-1;
	
	
%>
<div id="createContentPopup" class="main-wrap">
<div class="content">
<%-- Changes for MPS 2.1 --%>
<c:if test='${includeGrid eq "true"}'>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
</c:if>
<%-- ENDS  Changes for MPS 2.1 --%>
<div class="portletBodyWrap modal">
	<div>
		
       <%  
       if(createSuppliesReq!=null && createSuppliesReq.equalsIgnoreCase("True") || (createHardwareReq!=null && createHardwareReq.equalsIgnoreCase("True")))
      	{
    	   
       %>
  <div class="columnsThree hovfx height-auto" id="consumablesLinksDiv">
    <div class="columnInner"><img class="ui_icon_sprite widgets req_supplies-icon" src="/LexmarkServicesPortal/images/transparent.png" alt="Supplies Request" ></img>
      <p class="brief"><spring:message code="requestInfo.popup.message.briefDescForUserOR"/></p>
      
      <ul>
     
        
      
         <% 
         
        
         
         if(catalogEntitlement!=null 
        		 && catalogEntitlement.equalsIgnoreCase("true")
        		 && assetEntitlement!= null
        		 && assetEntitlement.equalsIgnoreCase("false")
        		 && createSuppliesReq!=null
        		 && createSuppliesReq.equalsIgnoreCase("true")
        		 ){
        	 
        		 %>
         
         
         <li id="consumableOrderLi">	<a href="catalogorder" id="catalogorder"><spring:message code="requestInfo.popup.label.orderSupplies"/></a> </li> 
         
         <%}else if(catalogEntitlement!=null 
        		 && catalogEntitlement.equalsIgnoreCase("false")
        		 && assetEntitlement!= null
        		 && assetEntitlement.equalsIgnoreCase("true")
        		 && createSuppliesReq!=null
        		 && createSuppliesReq.equalsIgnoreCase("true")
        		 ){ %>
        <li id="consumableOrderLi">  <a href="consumableorder" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTORDERSUPPLIES%>');"id="consumableOrderLink"><spring:message code="requestInfo.popup.label.orderSupplies"/></a> </li>
       	<%}else if(catalogEntitlement!=null 
       		 && catalogEntitlement.equalsIgnoreCase("true")
    		 && assetEntitlement!= null
    		 && assetEntitlement.equalsIgnoreCase("true")
    		 && createSuppliesReq!=null
    		 && createSuppliesReq.equalsIgnoreCase("true")){%>
    	
        
        <li id="consumableOrderLi">  <a href="consumableorder"  onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTORDERSUPPLIES%>');" id="consumableOrderLink"><spring:message code="requestInfo.popup.label.orderSupplies"/></a> </li>
        
        <%}%> 
        
      
        
        
        <% if(createHardwareReq!=null && createHardwareReq.equalsIgnoreCase("True")){ %>
        <li id="hardwareorderLi">	<a href="hardwareorder" id="hardwareorder"><spring:message code="requestInfo.popup.hardwareReq"/></a> </li>
        <% } %>
        
        <%  
       if(createSuppliesReq!=null && createSuppliesReq.equalsIgnoreCase("True"))
      	{
    	   
       %>
       
        <li><a href="returnSupplies" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTRETURNSUPPLIES%>');javascript:checkPageType(event);" id="returnSupplies"><spring:message code="requestInfo.popup.label.returnSupplies"/></a></li>
		<%	
	 	}
      	%>    
      	     
      </ul>
    </div>
  </div>
        <%	
	 	}
      	%>   

       <%
   		if(createChangeMgmtReq!=null && createChangeMgmtReq.equalsIgnoreCase("True"))
    	{
   			
      %>
  <div class="columnsThree hovfx height-auto" id="changeManagementLinks">
    <div class="columnInner"> <img class="ui_icon_sprite widgets req_change-icon" src="/LexmarkServicesPortal/images/transparent.png" alt="Change Management Requests" />
      <p class="brief"><spring:message code="requestInfo.popup.message.briefDescForUserCR"/></p>
      <ul>
      <%if(onlyAssetAcceptance!=null && onlyAssetAcceptance.equalsIgnoreCase("true")){ %>
      		<li><a href="assetacceptance" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTASSETACCEPTANCE%>');" id = "acceptanceRequest"><spring:message code="requestInfo.popup.label.acceptanceRequest"/></a>
	 	 	</li>
      <%}else{ %>
	  		<li><a href="manageassets" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTASSET%>');" ><spring:message code="requestInfo.popup.label.Asset"/></a>
	  		</li>
		  <%
		  	/*
		  	* Changes for MPS 2.1 Access for Asset Acceptance
		  	*/
		  	if(showAssetAcceptance!=null && showAssetAcceptance.equalsIgnoreCase("true")){
		  %>
		  <!-- Added for Asset Acceptance -->
		<li><a href="assetacceptance" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTASSETACCEPTANCE%>');" id = "acceptanceRequest"><spring:message code="requestInfo.popup.label.acceptanceRequest"/></a>
		  </li>
		  <!-- END -->
		  <% }
		  %>
		  
		  <li><a href="manageaddresses" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTADDRESS%>');"><spring:message code="requestInfo.popup.label.Address"/></a>
		  </li>
		  <li><a href="managecontacts" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTCONTACT%>');"><spring:message code="requestInfo.popup.label.Contact"/></a>
	      </li>
		  <li><a href="managechlothers?forward=CHL" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTCUSTOMERHIERARCHY%>');"><spring:message code="requestInfo.popup.label.customerHierarchy"/></a>
		  </li>
	 <%} %> 
     <%if(createMapRequest!=null && createMapRequest.equalsIgnoreCase("true")){ %>
     <%if(isFleetRequest){ %>
      		<li><a href="mapsRequest?fleet=true" id="mapsRequestLink"<%-- onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTCUSTOMERHIERARCHY%>');" --%>><spring:message code="requestInfo.cm.heading.mapsRequest"/></a>
		    </li>
      <%}
      else{%>
      <li><a href="mapsRequest?fleet=false" id="mapsRequestLink" <%-- onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTCUSTOMERHIERARCHY%>');" --%>><spring:message code="requestInfo.cm.heading.mapsRequest"/></a>
		    </li>
		    <%}
     }%>
      </ul>
    </div>
  </div>
	  <%	
	 }
      %>      
  <!-- Moved Inquiry to Request Service Section -->
      <%
       if(createServiceReq!=null && createServiceReq.equalsIgnoreCase("True") && createChangeMgmtReq!=null && createChangeMgmtReq.equalsIgnoreCase("False"))
      {
    	 
    	%> 
    	<div class="columnsThree hovfx height-auto" id="breakFixLinks">
    <div class="columnInner"> <img class="ui_icon_sprite widgets req_service-icon" src="/LexmarkServicesPortal/images/transparent.png" alt="Service Request" />
      <p class="brief"><spring:message code="requestInfo.popup.message.briefDescForUserSR"/></p>
      <ul>
       <li id="breakFixLi"><a href="break-fix" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTREQUESTSERVICE%>');" id="breakFixLink" ><spring:message code="requestInfo.popup.label.requestService"/></a></li>
       </ul>
    </div>
    
  </div>
		  <%}/* removed in MPS 2.1 
		    howAssetAcceptance==null
		    from below condtion*/
		    
       else if(createServiceReq!=null && createServiceReq.equalsIgnoreCase("False") && createChangeMgmtReq!=null && createChangeMgmtReq.equalsIgnoreCase("True")   || (showAssetAcceptance!=null && !showAssetAcceptance.equalsIgnoreCase("true"))){
			 
		 
			 %>	
			 <div class="columnsThree hovfx height-auto" id="breakFixLinks">
    <div class="columnInner"> <img class="ui_icon_sprite widgets req_service-icon" src="/LexmarkServicesPortal/images/transparent.png" alt="Service Request"  />
      <p class="brief"><spring:message code="requestInfo.popup.message.briefDescForUserSR"/></p>
      <ul>
		 <li><a href="managechlothers?forward=Others" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTINQUIRY%>');" id="others"><spring:message code="requestInfo.popup.label.Others"/></a></li>
		 </ul>
    </div>
    
  </div>
		 <%
		 }else if(createServiceReq!=null && createServiceReq.equalsIgnoreCase("True") && createChangeMgmtReq!=null && createChangeMgmtReq.equalsIgnoreCase("True")){ 
   			
      %> 
      <div class="columnsThree hovfx height-auto" id="breakFixLinks" >
    <div class="columnInner"> <img class="ui_icon_sprite widgets req_service-icon" src="/LexmarkServicesPortal/images/transparent.png" alt="Service Request"  />
      <p class="brief"><spring:message code="requestInfo.popup.message.briefDescForUserSR"/></p>
      <ul> 
      <li id="breakFixLi"><a href="break-fix" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTREQUESTSERVICE%>');" id="breakFixLink"><spring:message code="requestInfo.popup.label.requestService"/></a></li>
      <li><a href="managechlothers?forward=Others" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTPOPUP%>','<%=LexmarkSPOmnitureConstants.CREATENEWREQUESTINQUIRY%>');" id="others"><spring:message code="requestInfo.popup.label.Others"/></a></li>
	  </ul>
    </div>
    
  </div>
  <%} %> 
	  <!-- END -->
     
      </div>
  <div class="clear"></div>
  <div class="greenBanner"><!-- img src="/LexmarkServicesPortal/images/gogreen.jpg" width="129" height="75" alt="Go Green" /-->
    <div>
      <h5><spring:message code="requestInfo.popup.label.suppliesAndTheEnvironment"/></h5>
      <spring:message code="requestInfo.popup.label.manageSuppliesWithTheEnvironmentInMind"/><br>
      <a href="#" id="recycleItemLink" class="cursor-pointer"  ><spring:message code="requestInfo.popup.label.recycleItems"/> &raquo;</a> </div>
  </div>
  

</div>

</div>
</div>
<div id="dialog-form" style="display:none" title='<spring:message code="header.recentOrderHistory"/>'>		
</div>
<script type="text/javascript">
  
  
  
// added
var assetId="${assetId}";
var accountId1 = "${accountId1}"

function showRecentAssetHistoryPopup(){
	
	//alert('showRecentAssetHistoryPopup');
	showOverlayPopup();
	var currentURL = window.location.href;
//	alert("currentURL :"+currentURL);
	//alert("assetId in recassethispopup :"+assetId);
	//var url="consumableorder?assetId="+assetId;
	var url="<portlet:resourceURL id='assetRequestHistory'></portlet:resourceURL>&assetId="+assetId+"&t="+new Date().getTime();
	//alert(url);
	jQuery('#dialog-form').load(url,function(data){
		//alert("hello"+jQuery('#dialog-form').html());			
		
		jQuery(document).scrollTop('0');
		
		initGridPopup(assetId,serialNumber,assetTag,ipAddress,modelNo,deviceTag);
	});

	
	return false;
}

function checkPageType(event){
	
	if(typeof pageType != 'undefined' && pageType=="deviceFinder"){		
		jQuery('#accnName').val(accountName);
		jQuery('#accnId').val(accountId1);
		jQuery("#redirectToReturnSupplies").submit();
		event.preventDefault();
		
	}
}

//end

jQuery(document).ready(function(){
	//alert('in ready');

	var currentURL = window.location.href;
	if(currentURL.indexOf('/partner-portal') != -1)
	{			
		jQuery('#changeManagementLinks').hide();
		jQuery('#breakFixLinks').hide();
		$('#hardwareorderLi').remove();<%--Chagnes for defect 17670 Hide from partner portal --%>
	}
	
	var chkConsElgFlag='${chkConsElgFlag}';
	var createServiceRequestFlag='${createServiceRequestFlag}';	
	

	

	
	/****This section is added for requests coming from dm to consumables order***/
	//var assetId="${assetId}";
	if(currentURL.indexOf('/device-finder') != -1)
	{			
	/* Added for defect no.10036 */
		jQuery("#hardwareorderLi").hide();
		/* END */
	}
	if(chkConsElgFlag == "false" ||chkConsElgFlag == "null" )
	{	
		jQuery("#consumableOrderLi").hide();
		
	}
	
	if(chkConsElgFlag=="true"){

		if(jQuery('#consumablesLinksDiv li:first a').attr('href')=='catalogorder')
			jQuery("#consumableOrderLi").hide();
		
		if(jQuery('#consumablesLinksDiv li:first a').attr('id')=='consumableOrderLink'){
			
			jQuery('#consumablesLinksDiv li:first a').attr('href','#');
			jQuery('#consumablesLinksDiv li:first a').bind('click',showRecentAssetHistoryPopup);
		}
		
		
	}
	<%--
	commented as it is not required
	if(assetId!="")
	{
		jQuery("#consumableOrderLink").attr('href','consumableorder?assetId='+assetId);	
	}--%>
	/***End of the section**********/
	
	if(createServiceRequestFlag == "false")
	{	
		jQuery("#breakFixLi").hide();		
	}
	else if(createServiceRequestFlag == "true" && assetId!="")
	{	
		jQuery("#breakFixLink").attr('href','break-fix?assetId='+assetId);	
	}
	
	var mdmlevel="${mdmLevel}";
	if(mdmlevel.toUpperCase != "ACCOUNT" && currentURL.indexOf('/partner-portal') == -1)
	{
		showAccountSelection();
	}
	var errorOccurred="${exceptn}";
	
	if(errorOccurred!="")
	{
		jQuery("#errorDiv_popup").html("Error occurred in opening popup");
		jQuery("#errorDiv_popup").show();
	}

	jQuery('#recycleItemLink').click(function(event){

		var url = "http://www1.lexmark.com/en_US/about-us/collecting-recycling-program/recycling-program-other-countries.shtml";
		var windowName = "RecycleItem" ;
		
		var windowWidth = 800;
		var windowHeight = 800;
		var windowLeft = parseInt((screen.availWidth/2) - (windowWidth/2));
		var windowTop = parseInt((screen.availHeight/2) - (windowHeight/2));
		var windowSize = "width=" + windowWidth + ",height=" + windowHeight + "left=" + windowLeft + ",top=" + windowTop + "screenX=" + windowLeft + ",screenY=" + windowTop;
        
		window.open(url, windowName, windowSize);
		 
        event.preventDefault();
	});
		
});
</script>