<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%-- This subtab.jsp is for the Request History/Change Request tab at the top --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<portlet:resourceURL var="displayAttachment" id="displayAttachment">	
</portlet:resourceURL>
<portlet:actionURL var="changeAssetUrl">
<portlet:param name="action" value="changeAsset"></portlet:param>
</portlet:actionURL>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<script type="text/javascript">
jQuery(document).ready( function() {
	var currentURL = window.location.href;
//	Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	
	else
		{
		jQuery('#changeaccount').hide();
		}

//Hide the additional contact, if not selected in previous page
	if ("${manageAssetFormForChange.serviceRequest.secondaryContact.firstName}"=="" || "${manageAssetFormForChange.serviceRequest.secondaryContact.firstName}"==null) 
	{
		//document.getElementById('addiContact').style.display = "none";
		  jQuery("#addiContact").hide();	
	}
	var cha="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag}";
	//alert("cha"+cha);
	if("${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag}" =="true"){
		//alert("zone show");
		jQuery('#zone_move').show();
	}
	else{
		
		if(currentURL.indexOf('/fleet-management') == -1){
			//alert("zone hide");
			jQuery('#zone_move').hide();
			
			
			}
		else{
			//alert("zone show");
			jQuery('#zone_move').show();
			
		}
	}
	
	if(currentURL.indexOf('/fleet-management') == -1){
		var flowPage="${pageFlow}";
		if(flowPage == 'Change')
			{
			jQuery('#gridLiChange').show();
			}
			
		jQuery('#fleet_cancel').hide();
		jQuery('#cancel').show();
	}else{
		jQuery('#gridLiChange').hide();
		jQuery('#fleet_cancel').show();
		jQuery('#cancel').hide();
	}
	
	/* if ("${manageAssetFormForChange.assetDetail.consumableAddress.addressLine1}"=="" || "${manageAssetFormForChange.assetDetail.consumableAddress.addressLine1}"==null)
	{
		alert("if");
		jQuery("#consumablesAddrNew").hide();
	}else if("${manageAssetFormForChange.assetDetail.installAddress.addressLine1}"=="" || "${manageAssetFormForChange.assetDetail.installAddress.addressLine1}"==null) 
	{
		//document.getElementById('consumablesAddr').style.display = "none";
		alert("else");
		  jQuery("#consumablesAddrOld").hide();	
	} */

	textScroll(document.getElementById('description'));
	textScroll(document.getElementById('costCenter'));
	textScroll(document.getElementById('assetCostCenterId'));
});
</script>
<%-- Below URL hits the controller to submit the service request --%>

<%--Added for CI Defect #7853 --%>
<style>.move_type_confirmation{
    color: #000000;
    float: left;
    font-weight: bold;
    line-height: 1.8em;
    vertical-align: top;
    width:auto;
    
}
.move_type1{padding-left:0px!important;}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.xhdr {
       position: static!important;
}
.objbox {
       position: static!important;
}
</style>
<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
<div id="content-wrapper">
<div class="journal-content-article">
  <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1><h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
</div>
<!-- Added for CI7 BRD14-02-12 -->
<div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        	<!-- END -->
        	
<form:form action="<%=changeAssetUrl %>" method="post" modelAttribute="manageAssetFormForChange" id="changeAssetForm">
		<span style="display: none;">
		<input type="text" name="pageCountValue" id="pageCountValue" />
        <input type="text" name="pageCountDateValid" id="pageCountDateValid" />
        </span>
<form:hidden path="submitToken" />
<span style="display: none;">
		<input type="text" name="timezoneOffset" id="timezoneOffset"/>
		</span>	
<div id="dialog"></div>
<div class="main-wrap">
      <div class="content">
        
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.changeAssetInformation"/>
          	&ndash; 
          	<spring:message code="requestInfo.heading.review"/></h3>
          <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
		  <%--Error message to be shown in error div while trying to save SR --%>    
      <div class="info banner err" style="display: none;" id="errorDiv"> 
		<strong><spring:message code="exception.sr.save"/></strong>
      </div>
      <%--End --%>
          
		     <div class="portletBlock">
			  
			<div class="columnsTwo">
			
			
			
              <div class="columnInner rounded infoBox">
			<%-- Below section shows the primary contact of the requestor --%>
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
					<div class="borderBottomLightGray"></div>
			   <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    
                    <span id="NameLabel">${manageAssetFormForChange.serviceRequest.primaryContact.firstName}  ${manageAssetFormForChange.serviceRequest.primaryContact.lastName}</span>
                    
                  </li>
                  
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    
                    <span id="workPhoneLabel">${manageAssetFormForChange.serviceRequest.primaryContact.workPhone}</span></li>
                    
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    
                    <span id="emailAddressLabel">${manageAssetFormForChange.serviceRequest.primaryContact.emailAddress}</span> </li>
                    
                </ul>
               
                <%-- End --%>
              </div>
			  <div class="infoBox columnInner rounded shadow" id="addiContact">
			  <%--This is the secondary contact div--%>
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>		
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    
					<span id="NameLabel2">${manageAssetFormForChange.serviceRequest.secondaryContact.firstName }  ${manageAssetFormForChange.serviceRequest.secondaryContact.lastName }</span>
					
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                
	                <span id="workPhoneLabel2">${manageAssetFormForChange.serviceRequest.secondaryContact.workPhone }</span>
	                  
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                
	                <span id="emailAddressLabel2">${manageAssetFormForChange.serviceRequest.secondaryContact.emailAddress }</span></li>
	                
                </ul>
                
              </div>
              <%-- End --%>
            </div>
            
            <%--This section is for the additional information for the change asset request --%>
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox addiInfo">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>	
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span>${manageAssetFormForChange.serviceRequest.customerReferenceId}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                    <span id="costCenter">${manageAssetFormForChange.serviceRequest.costCenter}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span id="description" style="word-wrap: break-word;word-break: normal;width:250px">${manageAssetFormForChange.serviceRequest.addtnlDescription}</span>                   
				  </li>
				  <li>
                    <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                    <%-- <fmt:formatDate value="${manageAssetFormForChange.serviceRequest.requestedEffectiveDate}" pattern="MM/dd/yyyy"/>
                   ${manageAssetFormForChange.serviceRequest.requestedEffectiveDate}
                   <util:dateFormat value="${manageAssetFormForChange.serviceRequest.requestedEffectiveDate}" timezoneOffset="${timezoneOffset}">
					</util:dateFormat>--%>
					<util:dateFormat value="${manageAssetFormForChange.serviceRequest.requestedEffectiveDate}">
					</util:dateFormat>
				  </li>
                </ul>
			  </div>
            </div>
          </div>
          <%-- End --%>
	 <hr class="separator" />
	<%--Selected asset information start --%>
		  <p class="info"><spring:message code="requestInfo.cm.manageAsset.heading.enterTheNewInformation"/></p>
		  
		  <div class="portletBlock infoBox rounded shadow">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
				<tr>
					<td class="pModel">
					<ul>
						  <li class="center"><img src='${manageAssetFormForChange.assetDetail.productImageURL}' 
						  width="100" height="100" id="MyPicture" onError="image_error();"></li>
						  <%-- <li class="pModelName">${manageAssetFormForChange.assetDetail.productTLI}</li> --%>
						  <li class="pModelName">${manageAssetFormForChange.assetDetail.descriptionLocalLang}</li>
					</ul>
					</td>
					<td class="pDetail">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="h4"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails"/></td>
								<td class="h4">&nbsp;</td>
							</tr>
							<tr>
                    <td class="infoDsiplay">
                    <ul class="form wordBreak">
                        <li>
                	<label><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label>
                	<span>${manageAssetFormForChange.assetDetail.serialNumber}</span>
                </li>
                        <li>
                    <label><spring:message code="requestInfo.assetInfo.label.productName"/></label>
                    <%-- <span>${manageAssetFormForChange.assetDetail.productLine}</span> --%>    
                    <span>${manageAssetFormForChange.assetDetail.productTLI}</span>
                </li>
                          <li>
                  	<label><spring:message code="requestInfo.assetInfo.label.installDate"/></label>                  	
                  	<%--<fmt:formatDate value="${manageAssetFormForChange.assetDetail.installDate}" pattern="MM/dd/yyyy"/>
                  	 ${manageAssetFormForChange.assetDetail.installDate}
                  	 <util:dateFormat value="${manageAssetFormForChange.assetDetail.installDate}" timezoneOffset="${timezoneOffset}">
					</util:dateFormat> --%>
					<util:dateFormat value="${manageAssetFormForChange.assetDetail.installDate}">
					</util:dateFormat>
                </li>
                      </ul></td>
                    <td>&nbsp;</td>
                  </tr>
				  <tr>
                    <td class="h4 inlineTitle"><spring:message code="requestInfo.cm.manageAsset.heading.currentIdentifiers"/></td>
                    <td class="h4 inlineTitle separatorV"><spring:message code="requestInfo.cm.manageAsset.heading.newIdentifiers"/></td>
                  </tr>
				  <tr>
                    <td class="infoDsiplay">
                    <ul class="form wordBreak">
                                        <li>
										<%--Selected asset information current identifiers start --%>
                    <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
                    <%--Start: Added for Defect 3924-Jan Release--%>
                    <span>
                    <a href="javascript:gotoControlPanel('${manageAssetFormForChange.assetDetail.controlPanelURL}')">${manageAssetFormForChange.assetDetail.ipAddress}</a>
                    </span>
                    <%--End: Added for Defect 3924-Jan Release--%>
                </li>
                      
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
                    <span>${manageAssetFormForChange.assetDetail.hostName}</span>
                </li>
                
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
                    <span>${manageAssetFormForChange.assetDetail.deviceTag}</span>
                </li>
				<%--Selected asset information current identifiers end --%>
				<%--Selected asset information new identifier start --%>
                      </ul></td>
                    <td class="separatorV"><ul class="form wordBreak">
                        <li>
                    <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
                    <span>${manageAssetFormForChange.newAssetInfo.ipAddress}</span>
                </li>
                      
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
                    <span>${manageAssetFormForChange.newAssetInfo.hostName}</span>
                </li>
                
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
                    <span>${manageAssetFormForChange.newAssetInfo.deviceTag}</span>
                </li>
                      </ul></td>
                  </tr>
						</table>
					</td>
				</tr>
			</table>
			
	<%-- End --%>  
		  
	</div>
	<%--Selected asset information new identifier end --%>
	
		
 
 <div class="portletBlock">
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
              <%--Selected asset information current billing details start --%>
			   <h4><spring:message code="requestInfo.cm.manageAsset.heading.currentBillingDetails"/></h4>
			<ul class="form wordBreak">
                      <li>
                        <label><spring:message code="requestInfo.assetInfo.label.assetCostCenter"/> </label>
                        <span>${manageAssetFormForChange.assetDetail.assetCostCenter}</span>
                        </li>
					  </ul>
			<%--Selected asset information current billing details end --%>
					  
		
			
			<%--Selected asset information chl level start --%>
			   <h4 class="inlineTitle"><spring:message code="requestInfo.cm.manageAsset.heading.currentCustomerHeirarchyLevel"/></h4>
			  <ul class="form wordBreak">
                      <li>
                        <label ><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/> </label>
							${manageAssetFormForChange.assetDetail.chlNodeValue}
							<!-- <span>Cincinnati-OH</span> --> <!-- This is done for the time being because chl value is not available -->
                      </li>
					  </ul>
			  </div>
			  </div>
			  <%--Selected asset information chl level end --%>
			  
			  
			  <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
              <%--Selected asset information new billing details start --%>
              
			   <h4><spring:message code="requestInfo.cm.manageAsset.heading.newBillingDetails"/></h4>
			<ul class="form wordBreak">
                       <li>
                        <label for="assetCost"><spring:message code="requestInfo.assetInfo.label.assetCostCenter"/> </label>
                       <span id="assetCostCenterId">${manageAssetFormForChange.newAssetInfo.assetCostCenter}</span>
                      </li>
					  </ul>
			<%--Selected asset information new billing details end --%>
					  
			
			<%--Selected asset information new chl level start --%>
			   <h4 class="inlineTitle"><spring:message code="requestInfo.cm.manageAsset.heading.newCustomerHeirerchyLevel"/></h4>
			  <ul class="form wordBreak">
                    <li>
                    <label ><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/></label>
					<span>${manageAssetFormForChange.newAssetInfo.chlNodeValue}</span>
                    
                  </li>
					  </ul>
			  </div>
			  </div>
			  </div>
			  <%--Selected asset information new chl level end --%>
			
		
			  <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div  class="collapse" id="statusExpand">
                <div id="div_pageCounts">
					<div id="tab_pageCounts" style="display:block;">
						<div id="pageCounts_container" style="background-color: white;width:100%;"></div>
	    				<div class="pagination"><span id="div_pageCountsPagingArea"></span>
	    				<span id="div_pageCountsinfoArea"></span></div>
					</div>
				</div>
              </div>
            </div>
          </div> 
          
          
           <!-- Project fields -->
            <c:if test="${manageAssetFormForChange.serviceRequest.project eq 'true'}">
            <div class="infoBox columnInner rounded shadow">
            <h4><spring:message code="ops.heading.project"/></h4>
            
            <ul class="roDisplay">
                  <c:if test="${manageAssetFormForChange.serviceRequest.projectName ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectName"/></label>
                    <span id="projectName">${manageAssetFormForChange.serviceRequest.projectName} </span></li>
                  </c:if>
                  <c:if test="${manageAssetFormForChange.serviceRequest.projectPhase ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectPhase"/></label>
                    <span id="projectPhase">${manageAssetFormForChange.serviceRequest.projectPhase}</span></li>
                   </c:if>
                   </ul>
            
            </div>
			</c:if>
          
          
		<div class="infoBox columnInner rounded shadow">
		<h4><spring:message code="requestInfo.heading.moveInfo"/></h4>
		<div class="portletBlock" style="margin-left:5px;">
			<ul class="installCheck" style="list-style-type:none;" style="width: 90%">
					 <%--Changes Done for CI Defect #7853--START --%>
					  <li class="" style="list-style-type:none;">
						<span for="installAsset" style="font-weight:bold;"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToPhysicallyMoveThisDevice"/></span>
						<span class="radio_confirm">${manageAssetFormForChange.installAssetFlag}</span>
						    </li>
						    <%-- Commented for MPS 2.1 --%>
						   <%--  <li class="move_type1" style="list-style-type:none;">
						<div id="move_type" class="move_type_confirmation" style="width:auto;float:left;"><spring:message code="requestInfo.cm.manageAsset.label.moveTypeConfirm"/></div>
						<div id="" class="" style="width:auto;float:left;margin-left:40px;">${manageAssetFormForChange.moveType}</div> 
                      	<div style="clear:both"></div>
                      </li>  --%>      
                      <%--Changes Done for CI Defect #7853--START --%>
                   </ul>  
        </div>
		<div class="columnsTwo">
			<div class="infoBox columnInner rounded shadow">
				<h4><spring:message code="requestInfo.heading.currentInstallAdd"/></h4>
				<%--Changed for LBS --%>
				
                      <c:choose>
                      <c:when test="${manageAssetFormForChange.assetDetail.installAddress.lbsAddressFlag eq 'null' }">
                     	<ul class="roDisplay">
	                	<li>
						<div id="installStoreFrontNameLbl">${manageAssetFormForChange.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForChange.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </ul>
                      </c:when>
                      <c:when test="${manageAssetFormForChange.assetDetail.installAddress.lbsAddressFlag eq 'true' }">
                      <div id="installaddress" >
                      <ul class="roDisplay">
                      <li>
						<div id="installStoreFrontNameLbl">${manageAssetFormForChange.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForChange.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					<li><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span>${manageAssetFormForChange.assetDetail.installAddress.zoneName}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      
                      	<c:if test="${manageAssetFormForChange.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'grid level'
                                                   ||  (manageAssetFormForChange.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'mix - see floor' 
                                                     &&  manageAssetFormForChange.assetDetail.installAddress.floorLevelOfDetails.toLowerCase() eq 'grid level')   }">
                      	<li id="gridLiChange" style="display:none">
							<div id="installedXYLblDiv">
							<label id="installedXYLbl">Grid X/Y : </label><label id="installedCoords"></label>
							</div>
						</li>
						</c:if>
                      
					
                      </ul>
                      </div>
                      </c:when>
                      <c:when test="${manageAssetFormForChange.assetDetail.installAddress.lbsAddressFlag ne 'true' }">
                     	<ul class="roDisplay">
	                	<li>
						<div id="installStoreFrontNameLbl">${manageAssetFormForChange.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForChange.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </ul>
                      </c:when>
                      </c:choose>
                     				
			</div>
</div>

			<!-- Move to Address -->
			<div class="columnsTwo" id="hideMoveToAddress">
			<div class="infoBox columnInner rounded shadow">
				<h4><spring:message code="requestInfo.manageAsset.heading.moveToAddr"/></h4>
				<c:choose>
				<%--Changed for LBS --%>
				<c:when test="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag eq 'null' }">     
				<ul class="roDisplay">
					<li>
					<div id="moveToStoreFrontNameLblf">${manageAssetFormForChange.assetDetail.moveToAddress.storeFrontName}</div>
					<util:addressOutput value="${manageAssetFormForChange.assetDetail.moveToAddress}"></util:addressOutput>
					</li>
					<li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation2}</span>
                      </li>
					 
					<li><span id="zone_move"><label><spring:message code='lbs.label.zone'/>: </label>
                       <span >${manageAssetFormForChange.assetDetail.moveToAddress.zoneName}</span></span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation3}</span>
                      </li>
				</ul>
				</c:when>
                      <c:when test="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag eq 'true' }">
                      <ul class="roDisplay">
					<li>
					<div id="moveToStoreFrontNameLblt">${manageAssetFormForChange.assetDetail.moveToAddress.storeFrontName}</div>
					<util:addressOutput value="${manageAssetFormForChange.assetDetail.moveToAddress}"></util:addressOutput>
					</li>
					<li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation2}</span>
                      </li>
					 
					<li><label><spring:message code='lbs.label.zone'/>: </label>
                       <span id="zone_move">${manageAssetFormForChange.assetDetail.moveToAddress.zoneName}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation3} </span>
                      </li>
                     
                      
				</ul>
                      </c:when>
                   
                   <c:when test="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag ne 'true' }">     
				<ul class="roDisplay">
					<li>
					<div id="moveToStoreFrontNameLblf">${manageAssetFormForChange.assetDetail.moveToAddress.storeFrontName}</div>
					<util:addressOutput value="${manageAssetFormForChange.assetDetail.moveToAddress}"></util:addressOutput>
					</li>
					<li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation2}</span>
                      </li>
					 
					<li><span id="zone_move"><label><spring:message code='lbs.label.zone'/>: </label>
                       <span >${manageAssetFormForChange.assetDetail.moveToAddress.zoneName}</span></span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation3}</span>
                      </li>
				</ul>
				</c:when>
                   
                   
				</c:choose>
				<ul class="roDisplay">
                   <c:if test="${manageAssetFormForChange.assetDetail.moveToAddress.levelOfDetails.toLowerCase() eq 'grid level'
                                           ||  ( manageAssetFormForChange.assetDetail.moveToAddress.levelOfDetails.toLowerCase() eq 'mix - see floor' 
                                                     &&  manageAssetFormForChange.assetDetail.moveToAddress.floorLevelOfDetails.toLowerCase() eq 'grid level') }">
                      	
                      	<li id="gridLiMoveTo">
							<div id="moveToXYLblDiv">
								<label id="moveToXYLbl">Grid X/Y : </label><label id="moveToCoords"></label>
							</div>
						</li>
						
						</c:if>
						</ul>
			</div>		
		</div>
			  
		</div>
		<div class="portletBlock">
		<jsp:include page="/WEB-INF/jsp/changemanagement/manageAsset/commonAssetContactReview.jsp" />
		<p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine" style="word-wrap: break-word;word-break: normal; width:500px">${attachmentForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
        <div class="portletBlock infoBox">
         <c:if test="${not empty attachmentForm.attachmentList}">
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
						<table>
						<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${attachmentForm.attachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
										<td class="w70p"><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.attachmentName}');">${attachmentListDetail.displayAttachmentName}</a></td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
					</c:if>
				</div>
        <div class="buttonContainer">
        <%--Changed for LBS --%>
          <button class="button_cancel" type="button" onclick="javascript:goBack();"><spring:message code="button.back"/></button>
         <%-- <c:if test="${fleetManagementFlag eq true }"> --%>
          <span id="fleet_cancel" style="display:none;"><button class="button_cancel ie7_fix_margin" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button></span>
         <%-- </c:if>
          <c:if test="${fleetManagementFlag != true }"> --%>
          <span id="cancel" style="display:none;"><button class="button_cancel ie7_fix_margin" type="button" onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWCHANGEASSETCANCEL%>');"><spring:message code="button.cancel"/></button></span>
         <%-- </c:if> --%>
          <button class="button" type="submit" id="btnContinue" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWCHANGEASSETBACK%>');"><spring:message code="button.submit"/></button>
          </div>
          <input type="hidden" id="fleetManagementFlag" name="fleetManagementFlag" value="${fleetManagementFlag}" />  
          <input type="hidden" id="pageFlow" name="pageFlow" value="${pageFlow}" /> 

        <!-- MAIN CONTENT END --> 
		</div>
	</div>
	</div>
  <!-- <div id="footer">Footer</div> -->   

   </form:form>
   </div>
</body>
<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForChange.backToMap}"/>
</form>
<script type="text/javascript">
function onCancelClick() {
	//showOverlay();
	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					//window.location.href = "<portlet:renderURL></portlet:renderURL>";
					
					//var lat=document.getElementById("installAddresslatitude").value;
					var lat="${manageAssetFormForChange.assetDetail.installAddress.lbsLatitude}";
					
		var lon="${manageAssetFormForChange.assetDetail.installAddress.lbsLongitude}";
				//	var lon=document.getElementById("installAddresslongitude").value;
					if("${manageAssetFormForChange.backToMap}" == ""){
									var defaultArea={											
								                lat: lat,
								                lng: lon,							                		           
								               

											
											};
									
					$('#backJson').val(JSON.stringify(defaultArea));
					}
					$('#backFormMap').submit();
					
				}else{
					return false;
					}
			});
};
pageCountsGrid = new dhtmlXGridObject('pageCounts_container');
pageCountsGrid.setImagePath("<html:imagesPath/>gridImgs/");
pageCountsGrid.setHeader("<spring:message code='requestInfo.heading.commonPageCounts'/>");
pageCountsGrid.setColAlign("left,left,left");
pageCountsGrid.setColTypes("ro,ro,ro,ro");
pageCountsGrid.setInitWidths("160,200,180,0");
pageCountsGrid.enableAutoWidth(true);
pageCountsGrid.enableMultiline(true);
pageCountsGrid.enableAutoHeight(true);
pageCountsGrid.init(); 
pageCountsGrid.prftInit();
pageCountsGrid.enablePaging(true, 5, 10, "div_pageCountsPagingArea", true);
pageCountsGrid.setPagingSkin("bricks");
pageCountsGrid.setSkin("light");
//alert('${requestForm.srStausXML}');
pageCountsGrid.loadXMLString('${pageCountsString}'); 
/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

/*function redirectToHistory()
{
	window.location.href="service-requests";
}*/

/***********This is for the back button**************/
function goBack()
{
	var flowPage="${pageFlow}";
	var fleetManagegmtFlag = "${fleetManagementFlag}";
	//alert("flowPage"+flowPage);
	
	//alert("fleetManagegmtFlag"+fleetManagegmtFlag);

	callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWCHANGEASSETBACK%>');
	var url='<portlet:renderURL><portlet:param name="action" value="goToChangeAsset"></portlet:param></portlet:renderURL>&fleetManagementFlag='+fleetManagegmtFlag+'&pageFlow='+flowPage;
	jQuery("#changeAssetForm").attr("action", url);
	
	jQuery("#changeAssetForm").submit();
}
/******************end****************************/

jQuery(document).ready(function() {
	
	var xCordInstall='${manageAssetFormForChange.assetDetail.installAddress.coordinatesXPreDebriefRFV}';
	var yCordInstall='${manageAssetFormForChange.assetDetail.installAddress.coordinatesYPreDebriefRFV}';
	
	var xCordMove='${manageAssetFormForChange.assetDetail.moveToAddress.coordinatesXPreDebriefRFV}';
	var yCordMove='${manageAssetFormForChange.assetDetail.moveToAddress.coordinatesYPreDebriefRFV}';
			
	
	coordinates(xCordInstall,yCordInstall,"installed");
	coordinates(xCordMove,yCordMove,"moveTo");
	
	var offsetMinute = new Date().getTimezoneOffset();
	  var timezoneOffset = (offsetMinute/60);
	  jQuery('#timezoneOffset').val(timezoneOffset);
	jQuery('#pageCountValue').val('${pageCountsOriginalval}');
	jQuery('#pageCountDateValid').val('${pageCountsOriginaldate}');
	var checkBoxVal2="${manageAssetFormForChange.copyFromConsAddress}";
	var installFlag ="${manageAssetFormForChange.installAssetFlag}"; //Added for CI Defect #7853
	//alert(checkBoxVal1);
	//alert(installFlag);
	//Added for CI Defect #7853--STARTS
	if(installFlag=='No'||installFlag==''||installFlag==null)
		{
		var flowPage="${pageFlow}";
		//jQuery("#move_type").hide();
		if(flowPage=="fleetMgmtMove"){
			jQuery('#hideMoveToAddress').show();
		}
		else{
		jQuery('#hideMoveToAddress').hide();
		}
		}
	else if(installFlag=='Yes')
		{
		//jQuery("#move_type").show();
		jQuery('#hideMoveToAddress').show();
		}
	//Added for CI Defect #7853--ENDS
	/*********************************************************************************************************
	If the consumables address is same as installed address, then the check box would remain checked on page load
	*/
	/* if('${manageAssetFormForChange.copyFromConsAddress}!="false"')
		{	
			jQuery("#check").attr('checked', true);
		} */
		
		/* if(checkBoxVal2.length>0)
		{
			alert("first if"); */
			//alert(checkBoxVal2);
			if(checkBoxVal2=="true")
			{
				//alert("if");
				jQuery("#checkBx").attr('checked', true);
			}
				
		//}
		else 
			{
			//alert("else");
			jQuery("#checkBx").attr('checked', false);
			/*****If chk box unchecked, dont show it*******/
			jQuery("#sameAsInstallAddr").hide();
			}
		var isError="${errorOccurred}";
		var validationflag=false;
		if(isError)
			{
			jQuery("#errorDiv").show();
			validationflag=true;
			}
		jQuery("#btnContinue").click(function(){
			if(validationflag==true){
				jQuery("#errorDiv").show();
				jQuery(document).scrollTop(0);
					return false;
			}
		/*else{
					
			var flowPage="${pageFlow}";
			//alert("flowPage"+flowPage);
			var fleetManagegmtFlag = "${fleetManagementFlag}";
			//alert("fleetManagegmtFlag"+fleetManagegmtFlag);
			
			//var changeAssetActionUrl='<portlet:actionURL><portlet:param name="action" value="changeAsset"></portlet:param></portlet:actionURL>&fleetManagementFlag='+fleetManagegmtFlag+'&pageFlow='+flowPage;
						
			//jQuery("#changeAssetForm").attr("action", changeAssetActionUrl);
			jQuery("#fleetManagementFlag").val()=fleetManagegmtFlag;
			jQuery("#pageFlow").val()=flowPage;
			jQuery("#changeAssetForm").submit();
			event.preventDefault();
		}*/
		});
});
image_error= function () {
	
	  document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
	  
	};
	/*Method gotoControlPanel added for Defect 3924- Jan Release*/
	function gotoControlPanel(url) {
		controlPanelPopUpWindow.show();
		controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
		controlPanelPopUpWindow.io.start();
		<%--
			new Liferay.Popup({
		        title: "",
		        position: [400,150], 
		        modal: true,
		        width: 400, 
		        height: 150,
		        xy: [100, 100],
		        onClose: function() {showSelect();},
		        url:"<portlet:renderURL 
		             windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
		            "<portlet:param name='action' value='gotoControlPanel' />" +
		            "</portlet:renderURL>&controlPanelURL=" + url
		        }); --%>
	};
	function openAttachment(fileName){
		//alert("displayAttachment12:: "+ '${displayAttachment}');
		window.location="${displayAttachment}&fileName="+fileName;
		
	}

/* Added for CI7 BRD14-02-12 */
	ajaxSuccessFunction=function updateRequest(){
 		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);					
 }
	/* END */
</script>   
<script>
var controlPanelPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	controlPanelPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 400,
	height: 195,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
function coordinates(xCo,yCo,flag){
	var xCoordinate="";
	var yCoordinate="";
	var seperator="/";
	if(!(xCo && yCo))
	{
			seperator="";
	}
	if(xCo){xCoordinate=xCo;}
	if(yCo){yCoordinate=yCo;}
	if(flag=="moveTo"){
		$('#moveToCoords').html(xCoordinate+seperator+yCoordinate);
		$('#moveToAddresscoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#moveToAddresscoordinatesYPreDebriefRFV').val(yCoordinate);
	}
	else if(flag=="installed"){
		$('#installedCoords').html(xCoordinate+seperator+yCoordinate);
		$('#installcoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#installcoordinatesYPreDebriefRFV').val(yCoordinate);
	}	
}

</script>