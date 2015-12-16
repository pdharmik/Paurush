<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%-- This subtab.jsp is for the Request History/Change Request tab at the top --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>

<%-- Below URL hits the controller to submit the service request --%>
<portlet:actionURL var="addAssetActUrl">
<portlet:param name="action" value="addAsset"></portlet:param>
</portlet:actionURL>

<!--<portlet:renderURL var="redirectToAddAsset">
<portlet:param name="action" value="redirectToAddAsset"></portlet:param>
</portlet:renderURL>
-->
<portlet:resourceURL var="displayAttachment" id="displayAttachment">	
</portlet:resourceURL>
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
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
  
  <div id="content-wrapper">
    <div class="journal-content-article">
  <h1><spring:message code="requestInfo.cm.heading.changeRequest"/> </h1><h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
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
        	
<form:form action="<%=addAssetActUrl %>" method="post" modelAttribute="manageAssetForm" id="addAssetForm">

<form:hidden path="submitToken" />
<span style="display: none;">
		<input type="text" name="timezoneOffset" id="timezoneOffset"/>
		</span>	
    <div class="main-wrap">
      <div class="content"> 
         <!-- MAIN CONTENT BEGIN -->
           <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.addNewAsset"/> &ndash;  <spring:message code="requestInfo.heading.review"/></h3>
       
       <%--Error message to be shown in error div while trying to save SR --%>    
      <div class="info banner err" style="display: none;" id="errorDiv"> 
			<strong><spring:message code="exception.sr.save"/></strong>
      </div>
      <%--End --%>
		  <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/> </p>
        <div class="portletBlock">
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox">
              <%-- Below section shows the primary contact of the requestor --%>
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
				<div class="borderBottomLightGray"></div>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="NameLabel">${manageAssetForm.serviceRequest.primaryContact.firstName}  ${manageAssetForm.serviceRequest.primaryContact.lastName} </span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="workPhoneLabel">${manageAssetForm.serviceRequest.primaryContact.workPhone}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="emailAddressLabel">${manageAssetForm.serviceRequest.primaryContact.emailAddress}</span> </li>
                </ul>
                
                <%-- End --%>
				
              </div>
 

			  <div class="infoBox columnInner rounded shadow" id="addiContact">
			  <%--This is the secondary contact div--%>
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="NameLabel2">${manageAssetForm.serviceRequest.secondaryContact.firstName }  ${manageAssetForm.serviceRequest.secondaryContact.lastName }</span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                <span id="workPhoneLabel2">${manageAssetForm.serviceRequest.secondaryContact.workPhone }</span>  
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                <span id="emailAddressLabel2">${manageAssetForm.serviceRequest.secondaryContact.emailAddress }</span></li>
                </ul>
                <%-- End --%>
              </div>     
            </div>
            
            <%--This section is for the additional information for the cm request --%>
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>				
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span>${manageAssetForm.serviceRequest.customerReferenceId}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                    <span id="costCenter">${manageAssetForm.serviceRequest.costCenter}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span id="description" class="span-description">${manageAssetForm.serviceRequest.addtnlDescription}</span>                   
				  </li>
				  <li>
                    <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                     <span><util:dateFormat value="${manageAssetForm.serviceRequest.requestedEffectiveDate}">
                     </util:dateFormat></span><%--
                    ${manageAssetForm.serviceRequest.requestedEffectiveDate}
                    <util:dateFormat value="${manageAssetForm.serviceRequest.requestedEffectiveDate}" timezoneOffset="${timezoneOffset}">
                     </util:dateFormat>
				  --%></li>
                </ul>
			  </div>
            </div>
          </div>
           <hr class="separator" />
        <p class="info"><spring:message code="requestInfo.cm.manageAsset.heading.newAssetInformation"/></p>
        <div class="portletBlock">
          <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
			  <h4><spring:message code="requestInfo.cm.manageAsset.heading.assetIdentifiers"/></h4>
			  <ul class="form wordBreak">
                <li>
                	<label><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label>
                	<span>${manageAssetForm.assetDetail.serialNumber}</span>
                </li>
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.productName"/></label>
                    <span>${manageAssetForm.assetDetail.productLine}</span>    
                </li>
                <li>
                  	<label><spring:message code="requestInfo.assetInfo.label.installDate"/></label>                	
                  	<span><util:dateFormat value="${manageAssetForm.assetDetail.installDate}">
                  	</util:dateFormat></span><%--
                  	${manageAssetForm.assetDetail.installDate}
                  	<util:dateFormat value="${manageAssetForm.assetDetail.installDate}" timezoneOffset="${timezoneOffset}">
                  	</util:dateFormat>
                --%></li>
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
                    <%--Start: Added for Defect 3924-Jan Release--%>
						<span><a href="javascript:gotoControlPanel('${manageAssetForm.assetDetail.controlPanelURL}')">${manageAssetForm.assetDetail.ipAddress}</a></span>
					<%--End: Added for Defect 3924-Jan Release--%>
                    <%--<span>${manageAssetForm.assetDetail.ipAddress}</span>--%>
                </li>
                      
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
                    <span>${manageAssetForm.assetDetail.hostName}</span>
                </li>
                
                <li>
                    <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
                    <%--
                    ${manageAssetForm.assetDetail.customerAssetTag}
                --%>
                <span>${manageAssetForm.assetDetail.deviceTag}</span>
                </li>	
              </ul>
			</div>
		</div>
			
			<div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
			  <h4><spring:message code="requestInfo.cm.manageAsset.heading.billingDetails"/></h4>
					<ul class="form wordBreak">
						<li>
						<label for="assetCost"><spring:message code="requestInfo.assetInfo.label.assetCostCenter"/></label>
						<span id="assetCostCenterId">${manageAssetForm.assetDetail.assetCostCenter}</span>
						</li>
						</ul>
			  </div>
			  </div>
			<div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
			  <h4><spring:message code="requestInfo.cm.manageAsset.heading.customerHierercyLevel"/></h4>
					<ul class="form wordBreak">
					<li>
                    <label><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/></label>
					<span>${manageAssetForm.assetDetail.chlNodeValue}</span>
                    
                  </li>
				</ul>
			  </div>
			  </div>
			
			
			
          </div>
		  <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div  class="collapse" id="statusExpand">
                <div id="div_pageCounts">
					<div id="tab_pageCounts" style="display:block;">
						<div id="pageCounts_container" class="pageCounts_container"></div>
	    				<div class="pagination"><span id="div_pageCountsPagingArea"></span>
	    				<span id="div_pageCountsinfoArea"></span></div>
					</div>
				</div>
              </div>
            </div>
          </div>
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
					  <ul class="form installCheck">
					  <li>
                        <label for="installAsset"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDevice"/></label>
						<span class="radio_confirm">${manageAssetForm.installAssetFlag}</span>
                      </li>
					</ul>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/changemanagement/manageAsset/installDeinstallConfirm.jsp" />
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.installInfo"/></h4>
          <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
			 
					<h4><spring:message code="requestInfo.cm.manageAsset.heading.assetInstalledAddress"/></h4>
					
					<ul class="roDisplay">
				<%--	<c:choose>
				<c:when test="${fleetManageDropFlag ne 'true'}">
	                	<li>
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      <c:when test="${fleetManageDropFlag eq 'true'}"> --%>
                      <c:choose>
                     <c:when test="${manageAssetForm.assetDetail.installAddress.lbsAddressFlag eq null }">
                      <li>
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      <c:when test="${manageAssetForm.assetDetail.installAddress.lbsAddressFlag eq 'true' }">
                      
                      <li>
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					<li><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span>${manageAssetForm.assetDetail.installAddress.zoneName}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      
                      
                      <c:if test="${manageAssetForm.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'grid level'
                                                ||  (manageAssetForm.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'mix - see floor' 
                                                     &&  manageAssetForm.assetDetail.installAddress.floorLevelOfDetails.toLowerCase() eq 'grid level') }">
        
                      	<li id="gridLiAdd>
							<div id="installedXYLblDiv">
							<label id="installedXYLbl">Grid X/Y : </label><label id="installedCoords"></label>
							</div>
						</li>
						</c:if>
					 
                      </c:when>
                      <c:when test="${manageAssetForm.assetDetail.installAddress.lbsAddressFlag ne 'true' }">
                      <li>
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      </c:choose>
                      <%--</c:when>
                      </c:choose> --%>
                      
					 
					</ul>
					
					</div>
					</div>
        </div>
		
		<jsp:include page="/WEB-INF/jsp/changemanagement/manageAsset/commonAssetContactReview.jsp" />
		           
        <!-- Add Attachments BLOCK - Start -->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine attachmentDescription">${attachmentForm.attachmentDescription}</p>
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
				</div>
				</c:if>
        <!-- Add Attachments BLOCK - End --> 
        <div class="buttonContainer">
          <button class="button_cancel" type="button" onclick="javascript:goBack();"><spring:message code="button.back"/></button>
           
            <span id="fleet_cancel" style="display:none;">
            <button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
           </span>
            
            
            <span id="cancel" style="display:none;">
            <button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWADDASSETCANCEL%>');"><spring:message code="button.cancel"/></button>
            </span>
            
            <button class="button" type="submit" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWADDASSETSUBMIT%>');"><spring:message code="button.submit"/></button>
         

        <!-- MAIN CONTENT END --> 
		</div>
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>
  </form:form>
  </div>
  <form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetForm.backToMap}"/>
</form>
  <script type="text/javascript">
  function onCancelClick() {
		//showOverlay();
		
		jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
					if(confirmResult){
						showOverlay();
						//window.location.href = "<portlet:renderURL></portlet:renderURL>";
						
						var lat="${manageAssetForm.assetDetail.installAddress.lbsLatitude}";
						
						var lon="${manageAssetForm.assetDetail.installAddress.lbsLongitude}";
						if("${manageAssetForm.backToMap}"== ""){
							var defaultArea={											
						                lat: lat,
						                lng: lon
									};

							$('#backJson').val(JSON.stringify(defaultArea));
							}
										$('#backFormMap').submit();
						
					}else{
						return false;
						}
				});
	};
  var offsetMinute = new Date().getTimezoneOffset();
  var timezoneOffset = (offsetMinute/60);
  jQuery('#timezoneOffset').val(timezoneOffset);
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
  var checkBoxVal1="${manageAssetForm.copyFromConsContact}";

  var checkBoxVal2="${manageAssetForm.copyFromConsAddress}";


  jQuery(document).ready(function() {
  
  var xCordInstall='${manageAssetForm.assetDetail.installAddress.coordinatesXPreDebriefRFV}';
  var yCordInstall='${manageAssetForm.assetDetail.installAddress.coordinatesYPreDebriefRFV}';
  
  

  coordinates(xCordInstall,yCordInstall,"installed");
  
		var currentURL = window.location.href;
//		Change Account Link Hide/Show for CI-7 Defect #12274
		if(currentURL.indexOf('/partner-portal') == -1)
		  {	
			  jQuery('#changeaccount').show();
		  }
		else
			{
			jQuery('#changeaccount').hide();
			}

		if(currentURL.indexOf('/fleet-management') == -1){
			jQuery('#fleet_cancel').hide();
			jQuery('#cancel').show();
		}else{
			jQuery('#fleet_cancel').show();
			jQuery('#cancel').hide();
		}
	/*var deviceContactObject =  "${manageAssetForm.assetDetail.deviceContact}";
	//alert('deviceContactType='+deviceContactType);	 

	var deviceContactObjectList = new Array();
	deviceContactObjectList = deviceContactObject.split(",");
	alert('deviceContactObjectList='+deviceContactObjectList);
	var deviceContactObjectListSize = deviceContactObjectList.length;
	alert('deviceContactObjectListSize='+deviceContactObjectListSize);
	for(i=0;i<deviceContactObjectListSize;i++) {
		addDeviceContact();
	}*/
	  
  	//Hide the additional contact, if not selected in previous page
  	if ("${manageAssetForm.serviceRequest.secondaryContact.firstName}"=="" || "${manageAssetForm.serviceRequest.secondaryContact.firstName}"==null) 
  	{
  		  jQuery("#addiContact").hide();	
  	}
  	
  	if ("${manageAssetForm.assetDetail.consumableAddress.addressLine1}"=="" || "${manageAssetForm.assetDetail.consumableAddress.addressLine1}"==null) 
  	{
  		//document.getElementById('consumablesAddr').style.display = "none";
  		  jQuery("#consumablesAddr").hide();	
  	}

  	if (("${manageAssetForm.assetDetail.pickupAddress.addressId}"=="" || "${manageAssetForm.assetDetail.pickupAddress.addressId}"==null)
  		&& ("${manageAssetForm.primarySiteContact.firstName}"=="" || "${manageAssetForm.primarySiteContact.firstName}"==null))
  	{
  		  jQuery("#ServiceAddrRow").hide();	
  	}
  	if (("${manageAssetForm.assetDetail.pickupAddress.addressId}"=="" || "${manageAssetForm.assetDetail.pickupAddress.addressId}"==null)
  		&& ("${manageAssetForm.primarySiteContact.firstName}"=="" || "${manageAssetForm.primarySiteContact.firstName}"==null)) 
  	{
  		  jQuery("#ConsumableAddrRow").hide();	
  	}
  	
  	/*********This is for the error div to show***********/
  	var isError="${errorOccurred}";
  	if(isError)
  		{
  		jQuery("#errorDiv").show();
  		
  		}
  	
  			if(checkBoxVal1=="true" || checkBoxVal2=="true")
  			{	
  				jQuery("#checkBx").attr('checked', true);
  			}
  				
  		//}
  		else 
  			{
  			jQuery("#checkBx").attr('checked', false);
  			/*****If chk box unchecked, dont show it*******/
  			jQuery("#sameAsInstallAddr").hide();
  			}

  		textScroll(document.getElementById('description'));
  		textScroll(document.getElementById('costCenter'));
  		textScroll(document.getElementById('assetCostCenterId'));
  		
  });
  window.history.forward();
  function backDisable() { 
  	window.history.forward(); 
  }
  /*Method gotoControlPanel added for Defect 3924- Jan Release*/
  function gotoControlPanel(url) {
	  controlPanelPopUpWindow.show();
	  controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
	  controlPanelPopUpWindow.io.start();
  		//new Liferay.Popup({
  	    //    title: "",
  	    //    position: [400,150], 
  	    //    modal: true,
  	    //    width: 400, 
  	    //    height: 150,
  	    //    xy: [100, 100],
  	    //    onClose: function() {showSelect();},
  	    <%--    url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
  	            "<portlet:param name='action' value='gotoControlPanel' />"+
  	            "</portlet:renderURL>&controlPanelURL=" + url
  	        }); --%>
  	};
  	function goBack()
  	{
  		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWADDASSETBACK%>');
  		var url='<portlet:renderURL><portlet:param name="action" value="goToAddAsset"></portlet:param></portlet:renderURL>';
  		jQuery("#addAssetForm").attr("action", url);
  		jQuery("#addAssetForm").submit();
  	}
  	function openAttachment(fileName){
		//alert("displayAttachment12:: "+ '${displayAttachment}');
		window.location="${displayAttachment}&fileName="+fileName;
		
	}

  	
  	/* added for Device Contact Info populate */
  	var deviceNoOfRow=1;
  	
  	function addDeviceContact() {
	//alert('in addDeviceContact');
	var table=document.getElementById("deviceTable");
	//var rowCount = table.rows.length;
	var rowCount = deviceNoOfRow;
	deviceNoOfRow++;
    var row = table.insertRow(rowCount);
	var cell1=row.insertCell(0);
	var cell2=row.insertCell(1);
	var cell3=row.insertCell(2);

	cell1.className="middle";
	cell2.className="middle";
	cell3.className="middle";

	if(rowCount%2==0){
		row.className="altRow";
	}
	
	cell1.innerHTML='<div id="deviceContactType'+rowCount+'"></div>';
	cell2.innerHTML='<div>'+
		'<ul class="form">'+
		'<li style="display:none"> <span id="deviceContactId'+rowCount+'"></span></li>'+
	    '<li>'+
	      '<label><spring:message code="requestInfo.label.name"/></label>'+
	      '<span id="deviceFName'+rowCount+'"></span><span id="deviceLName'+rowCount+'"></span></li>'+
	    '<li>'+
	      '<label><spring:message code="requestInfo.label.phoneNumber"/></label>'+
	      '<span id="devicePhone'+rowCount+'"></span></li>'+
	    '<li style="display:none"> <span id="deviceAltPhone'+rowCount+'"></span></li>'+
	    '<li>'+
	      '<label><spring:message code="requestInfo.label.emailAddress"/></label>'+
	      '<span id="deviceEmail'+rowCount+'"></span></li>'+
	  	'</ul>'+
	  	'</div>';
		
	cell3.innerHTML='<ul class="roDisplay">'+
	    '<li>'+
	    '<div id="deviceStoreFrontNameLbl'+rowCount+'"></div>'+
		'<div id="deviceAddressLine1Lbl'+rowCount+'"></div>'+
		'<div id="deviceAddressofficeNumberLbl'+rowCount+'"></div>'+
		'<div id="deviceAddressLine2Lbl'+rowCount+'"></div>'+
		'<div id="city_state_zip_popup_span'+rowCount+'">'+
		'<div style="display: inline;" id="deviceAddressCityLbl'+rowCount+'"></div>'+
		'<div style="display: inline;" id="deviceAddresscountyLbl'+rowCount+'"></div>'+
		'<div style="display: inline;" id="deviceAddressStateLbl'+rowCount+'"></div>'+
		'<div style="display: inline;" id="deviceAddressProvinceLbl'+rowCount+'"></div>'+
		'<div style="display: inline;" id="deviceAddressRegionLB'+rowCount+'"></div>'+
		'</div>'+
		'<div id="deviceAddressPostCodeLbl'+rowCount+'"></div>'+
		'<div id="deviceAddressCountryLbl'+rowCount+'"></div>'+
		'</li>'+	    
		'</ul>';

	//alert('rowCount='+rowCount);
	if(rowCount==1) {
		jQuery("#deviceContactType"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[0].contactType}");
		
		jQuery("#deviceContactId"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[0].contactId}");
		jQuery("#deviceFName"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[0].firstName}");
		jQuery("#deviceLName"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[0].lastName}");
		jQuery("#devicePhone"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[0].workPhone}");
		jQuery("#deviceAltPhone"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[0].alternatePhone}");
		jQuery("#deviceEmail"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[0].emailAddress}");
	} else if(rowCount==2) {
		jQuery("#deviceContactType"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[1].contactType}");
		
		jQuery("#deviceContactId"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[1].contactId}");
		jQuery("#deviceFName"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[1].firstName}");
		jQuery("#deviceLName"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[1].lastName}");
		jQuery("#devicePhone"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[1].workPhone}");
		jQuery("#deviceAltPhone"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[1].alternatePhone}");
		jQuery("#deviceEmail"+rowCount).html("${manageAssetForm.assetDetail.deviceContact[1].emailAddress}");
	}
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
</body>
