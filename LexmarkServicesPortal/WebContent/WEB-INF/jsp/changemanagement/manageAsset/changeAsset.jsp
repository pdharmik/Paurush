<%-- This subtab.jsp is for the Request History/Change Request tab at the top --%>
<%-- Added for CI BRD13-10-02--STARTS --%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%-- Added for CI BRD13-10-02--ENDS --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%-- Added for CI BRD13-10-02 --%>
<%--@ include file="/WEB-INF/jsp/include.jsp"--%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<%--Changed for CI Defect #7853 --%>
<style>.move_type_confirmation{
    color: #000000;
    float: left;
    font-weight: bold;
    line-height: 1.8em;
    vertical-align: top;
    width:auto;
    
}
.move_type1{padding-left:0px!important;}<%--Changed for CI Defect #7853 --%>
</style>
<style type="text/css">
#pageCountsTable th {
    background-color: #e6e6f0;
    padding: 10px;
}
.rowspace td{
padding: 10px 7px 9px 0;
}
</style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style>#selectMoveType{}</style> <%--Changed for CI7 Defect 7853 --%>
<![endif]-->
<jsp:include page="../../common/validationMPS.jsp"></jsp:include>
<%-- Below jsp added for CI BRD 13-10-02--%>
<jsp:include page="../../common/reqDetailsPopUp.jsp"></jsp:include>

<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<script type="text/javascript">
<%@ include file="/js/commonAsset.js"%>
</script> 
<%-- Added for CI BRD13-10-02 --%>
<script type="text/javascript" src="<html:rootPath/>js/expand.js"></script>
<jsp:include page="../../common/mapViewPopup.jsp"></jsp:include>

<%-- Below URL opens up the address list grid in popup --%>
<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>

<%-- Below URL opens up the contact list grid in popup --%>
<portlet:renderURL var="showContactListPopUp" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showContactListPopUp"></portlet:param>
</portlet:renderURL>

<%-- Below URL sends the request to controller to show the review page --%>
<portlet:renderURL var="changeAssetConfirmationUrl">
<portlet:param name="action" value="changeAssetConfirmation"></portlet:param>
</portlet:renderURL>

<%-- Below URL opens up the chl tree in popup --%>
<portlet:renderURL var="showCHLTreePopUp" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>

<%-- Below URL sends the request to main asset list page --%>
<portlet:renderURL var="redirectToAssetListPage"></portlet:renderURL>

<%--Added for CI--%>
<%-- Below div opens up as popup with request detail--%>
	<div id="dialogChangeDetails" style="display: none;"></div>
	<div id="dialogSupplyDetails" style="display: none;"></div>

<body onload=""/>
<%-- Below div opens up as popup, with contact, address and chl contents respectively --%>
<div id="dialog_contact"></div>
<div id="dialogAddress"></div>
<div id="dialogChlTree" style="display: none;"></div>

<div id="content-wrapper">
<div class="journal-content-article">
	<c:choose>
		<c:when test="${moveAssetFlag ne 'true'}">
			<h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1><h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
		</c:when>
		<c:when test="${moveAssetFlag eq 'true'}">
			<h1><spring:message code="requestInfo.cm.heading.moveAssetRequest"/></h1><h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
		</c:when>
	</c:choose>
	
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
        	

<div class="main-wrap">
      <div class="content"> 
      	<%--Form Bean is manageAssetFormForChange instance of ManageAssetForm - --%>
		<form:form method="post" commandName="manageAssetFormForChange" id="changeAssetForm">
		<%-- Below section is for the prev sr no and update flag binding--%>
		<form:hidden path="prevSrNo" id="prevSrNo"/>
          <!-- MAIN CONTENT BEGIN -->
          <%-- This section shows the front end validation errors --%>
			<div class="serviceError" id="errorDiv" style="display: none;">
			
			</div>
		  <%-- End of front end validations --%>

          <%-- This section shows the server side validation errors --%>
          <spring:hasBindErrors name="manageAssetFormForChange">
          	<div class="error" >
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="${error.code}"/></strong></li>
            	</c:forEach>
     		</div>	
     		
	     </spring:hasBindErrors>
	     
	     <div id="jsValidationErrors" class="error" style="display: none;"></div>	
	        <div id="validationErrors" class="error" style="display: none;"></div>
	        <%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
     			     
	     <%--End of server side validation errors --%>	
	     <c:choose>
			<c:when test="${moveAssetFlag ne 'true'}">
				<h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.changeAssetInformation"/></h3>
			</c:when>
			<c:when test="${moveAssetFlag eq 'true'}">
				<h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.moveAssetInformation"/></h3>
			</c:when>
		</c:choose>	
	   
	     
	     <%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
		Additional Information of the Change Mgmt Request End--%>
		<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
		
		<%--Request History for Asset (CI) STARTS--%>
		 <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand" onclick="show_load();"> <!--show load called for asynshronous-CI7 -->
              <spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></h4>
              <div  class="collapse">

                       <div class="expand-min">
                      
                       <a href="#grid" id='headerMenuButton' style="display: none;"><spring:message code="requestInfo.link.customizeColumns"/></a> 
                       </div>

                <div id="gridContainerDiv_all_request_types"  class="gridbox gridbox_light" style="display: none;"></div>
                
				    		<div id="loadingNotification_request_type" class='gridLoading'>
	        					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    					</div>
				    		<div class="pagination" id="paginationId">
				    		<span id="pagingArea_request_type"></span><span id="infoArea_req_status"></span> 
				    		</div>
					
              </div>
            </div>
          </div>
        </div>
        <%--Request History for Asset (CI) ENDS--%>
		<%--Selected asset information current identifiers start --%>
		<hr class="separator" />
		<p class="info"><spring:message code="requestInfo.cm.manageAsset.heading.enterTheNewInformation"/></p>
          <div class="portletBlock infoBox rounded shadow"> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
				<tr>
					<td class="pModel">
						<ul>
							<li class="center"><img src="${manageAssetFormForChange.assetDetail.productImageURL}"
							width="100" height="100" id="MyPicture" onError="image_error();"/></li>
							<%-- <li class="pModelName">${manageAssetFormForChange.assetDetail.productTLI}</li> --%>
							<li class="pModelName">${manageAssetFormForChange.assetDetail.descriptionLocalLang}</li>
							<li id="differentAssetlink"><a href="javascript:redirectToAssetListPage('<%=redirectToAssetListPage%>');">
			<spring:message code="requestInfo.link.chooseAdifferentAsset"/></a></li>
						</ul>
					</td>
					<td class="pDetail">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="h4"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails"/></td>
								<td class="h4">&nbsp;</td>
							<tr>
							<tr>
								<td class="infoDsiplay">
									<ul class="form">
										<li><label ><spring:message code="requestInfo.assetInfo.label.serialNumber"/> </label>
											<span>${manageAssetFormForChange.assetDetail.serialNumber}</span>
										</li>
										<li><label><spring:message code="requestInfo.assetInfo.label.productName"/> </label>
											<%-- <span>${manageAssetFormForChange.assetDetail.productLine}</span> --%>
											<span>${manageAssetFormForChange.assetDetail.productTLI}</span>
										</li>
										<li><label><spring:message code="requestInfo.assetInfo.label.installDate"/> </label>
										<span><util:dateFormat value="${manageAssetFormForChange.assetDetail.installDate}" timezoneOffset="${timezoneOffset}">
                						</util:dateFormat></span>
										
										</li>
									</ul>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="h4 inlineTitle"><spring:message code="requestInfo.cm.manageAsset.heading.currentIdentifiers"/></td>
								<td class="h4 inlineTitle separatorV"><spring:message code="requestInfo.cm.manageAsset.heading.newIdentifiers"/></td>
							</tr>
							<tr>
								<td class="infoDsiplay">
									<ul class="form">
										<li><label><spring:message code="requestInfo.assetInfo.label.ipAddress"/> </label>
											<%-- <span>${manageAssetFormForChange.assetDetail.ipAddress}</span> --%>
											<%--Start: Added for Defect 3924-Jan Release--%>
											<span><a href="javascript:gotoControlPanel('${manageAssetFormForChange.assetDetail.controlPanelURL}')">${manageAssetFormForChange.assetDetail.ipAddress}</a></span>
											<%--End: Added for Defect 3924-Jan Release--%>
										</li>
										<li><label><spring:message code="requestInfo.assetInfo.label.hostName"/> </label>
											<span>${manageAssetFormForChange.assetDetail.hostName}</span>
										</li>
										<li><label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/> </label>
										<span>${manageAssetFormForChange.assetDetail.deviceTag}</span>
										</li>
									<ul>
								</td>
								<td class="separatorV">
									<ul class="form">
										<li>
											<label for="ipadd"><spring:message code="requestInfo.assetInfo.label.ipAddress"/> </label>
											<span><form:input id="ipAddress" path="newAssetInfo.ipAddress" maxlength="40"/>
											<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.ipAddress"/>" ></span>
										</li>
										<li>
											<label for="hostName"><spring:message code="requestInfo.assetInfo.label.hostName"/> </label>
											<span><form:input id="hostName" path="newAssetInfo.hostName" maxlength="50"/>
											<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.hostName"/>" ></span>
										</li>
										<li>
											<label for="custTag"><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/> </label>
												
											<span><form:input id="customerAssetTag" path="newAssetInfo.deviceTag" maxlength="50"/>
											<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.customerAssetTag"/>" ></span> 
										</li>
									</ul>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>
			<div class="portletBlock">
				<div class="columnsTwo">
					 <div class="infoBox columnInner rounded shadow">
						 <h4><spring:message code="requestInfo.cm.manageAsset.heading.currentBillingDetails"/></h4>
						 <ul class="form">		  
							<li>
								<label ><spring:message code="requestInfo.assetInfo.label.assetCostCenter"/> </label>	  
								<span>${manageAssetFormForChange.assetDetail.assetCostCenter}</span>
							</li>
						</ul>
						<div class="bottomPush"></div>
						<h4 class="inlineTitle"><spring:message code="requestInfo.cm.manageAsset.heading.currentCustomerHeirarchyLevel"/></h4>
						<ul class="form">
							  <li>
								<label ><spring:message code="requestInfo.cm.manageAsset.heading.currentCustomerHeirarchyLevel"/> </label>
								<span id="chlNodeIdLabel"></span>
								
							  <span>${manageAssetFormForChange.assetDetail.chlNodeValue}</span>
							  </li>
                      
						</ul>
					 </div>
<%--Selected asset information current identifiers end --%>
				</div>
				<div class="columnsTwo">
					<div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.cm.manageAsset.heading.newBillingDetails"/></h4>
						<ul class="form">
				<li>
                    <label for="assetCost"><spring:message code="requestInfo.assetInfo.label.assetCostCenter"/> </label>
                    <span><form:input id="assetCostCenter" path="newAssetInfo.assetCostCenter" maxlength="200"/>
                    <img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" alt="<spring:message code="requestInfo.alt.enterAssetCostCenter"/>" ></span>
                </li>
			</ul>
			<div class="bottomPush"></div>
			<h4 class="inlineTitle"><spring:message code="requestInfo.cm.manageAsset.heading.newCustomerHeirerchyLevel"/></h4>
			  <ul class="form">
                    <li>
                    <label class="link"><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/></label>                    
                    <span id="chlNodeLinkLbl"><a class="link"id="chlTreeLink" href="<%=showCHLTreePopUp%>" 
                    onclick="showOverlay();return popUpChlTree();" title="<spring:message code="tree.label.customerHierarchy"/>"><spring:message code="requestInfo.cm.manageAsset.link.selectHeirerchyLevel"/>
                    </a></span>
                   <span id="chlNodeValueLabel"></span>
                    <span style="display:none" id="chlNodeValueChangeLbl"><span><a class="link"id="chlTreeLink" href="<%=showCHLTreePopUp%>" 
					onclick="showOverlay();return popUpChlTree();" title="Customer Hierarchy"><spring:message code="requestInfo.cm.manageAsset.link.changeHeirerchyLevel"/></a></span></span>                    
                  </li>
			  </ul>
			  
			  	<span style="display: none">
	            <form:input id="chlNodeId" path="newAssetInfo.chlNodeId" /> 
				<form:input id="chlNodeValue" path="newAssetInfo.chlNodeValue" /> 
				</span>
					</div>
				</div>
			</div>
			
		<div>
		  <jsp:include page="/WEB-INF/jsp/common/commonPageCount.jsp" />
		
		</div>
		  
		<jsp:include page="/WEB-INF/jsp/common/commonInstallInfo.jsp" />
		<jsp:include page="/WEB-INF/jsp/changemanagement/manageAsset/commonAssetContact.jsp" />
		  
		  <!-- Add Attachments BLOCK - Start -->
        <span style="display: none;">
		<input type="text" name="pageCountTypeID" id="pageCountTypeID" />
        <input type="text" name="pageCountsDateID" id="pageCountsDateID" />
        <input type="text" name="pageCountValueID" id="pageCountValueID" />
        <input type="text" name="attachmentDescriptionID" id="attachmentDescriptionID" />
        <input type="text" name="pageCountValue" id="pageCountValue" value="${pageCountsOriginalval}" />
        <input type="text" name="pageCountDateValid" id="pageCountDateValid" value="${pageCountsOriginaldate}" />
        </span>
		</form:form>
		
        <jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" />
        <input type="hidden" id="fileCount" />
        <!-- Add Attachments BLOCK - End -->
		  
</div>
<%--Changed for LBS --%>
<div class="buttonContainer">
			<c:if test="${fleetManagementFlag == true }">
			<button class="button_cancel" onclick="onCancelClick();" type="button"><spring:message code="button.back"/></button>
			</c:if>
			<c:if test="${fleetManagementFlag != true }">
			<button class="button_cancel" onclick="javascript:backToSelect();" type="button"><spring:message code="button.back"/></button>
			</c:if>
			<c:if test="${fleetManagementFlag == true }">
			<button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
			</c:if>
			<c:if test="${fleetManagementFlag != true }">
			<button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSET%>','<%=LexmarkSPOmnitureConstants.CHANGEASSETCANCEL%>');"><spring:message code="button.cancel"/></button>
			</c:if>
			<button class="button" type="button" id="btnContinue"><spring:message code="button.continue"/></button>
				
</div>
</div>
</div>
<!-- <div id="footer">Footer</div> -->
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>
<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForChange.backToMap}"/>
</form>
<script type="text/javascript">
<c:if test="${fleetManagementFlag == true }">
jQuery("#differentAssetlink").hide();
</c:if>
<%--Changed for LBS --%>
function onCancelClick() {
	//showOverlay();
	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					//var lat=document.getElementById("installAddresslatitude").value;
					var lat="${manageAssetFormForChange.assetDetail.installAddress.lbsLatitude}";
					
		var lon="${manageAssetFormForChange.assetDetail.installAddress.lbsLongitude}";
		
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

var pageType = "changeAsset";
var portlet_name="<%=LexmarkSPOmnitureConstants.CHANGEASSET %>";
var action_primary="<%=LexmarkSPOmnitureConstants.CHANGEASSETSELECTADIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants.CHANGEASSETADDADDITIONALCONTACT%>";
var flowPage="${pageFlow}";
var primaryContactReq=true;
var secondaryContactReq=false;
var dialog;
var linkId;
var addressChangeReq=false;	
var addrName,addrLine1, addrLine2, addrLine3, addrCountry, addrPostCode, addrState, addrProvince, addrCity
,addrBuilding,addrFloor,addrOffice,physicalLoc1,physicalLoc2,physicalLoc3, addrStrFrntNm;
var copyInstAddr=false;
//var inputVal=0;
/************This is for front-end validation*********/
var nullid=new Array();
var notValidId=new Array();
var notValidId_tab=true;
var isValidationError=false;
var i=0;
var j=0;
var k;
var currentId;
var previousId;
var xCo; 
var yCo;
//Added for CI BRD13-10-02--START
var assetRowId1="${manageAssetFormForChange.assetDetail.assetId}";
var accountId='${manageAssetFormForChange.assetDetail.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";
//Added for CI BRD13-10-02--END

var pageCountTypeListArray= new Array(); 
	  var pageCountsDateListArray= new Array(); 
	  var pageCountValueListArray= new Array();  

jQuery(document).ready(function() {
	var currentURL = window.location.href;
	
	jQuery('#pageCountValue').val('${pageCountsOriginalval}');
	jQuery('#pageCountDateValid').val('${pageCountsOriginaldate}');
	var installFlag ="${manageAssetFormForChange.installAssetFlag}";
//	Change Account Link Hide/Show for CI-7 Defect #12274
var isError="${exceptn}";
			if(isError)
				{
				jQuery("#errorDiv").show();
				jQuery("#errorDiv").append('<li class="portlet-msg-error"><strong><spring:message code="exception.unableToRetrieveDeviceDetail"/></strong></li>');
				}
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	if(installFlag=='No')
	{
		if(flowPage == "fleetMgmtMove"){
			jQuery('#hideMoveToAddress').show();
			jQuery('#moveToAddressLink').show();
		}else{
		jQuery('#hideMoveToAddress').hide();
		}
	}
	else if(installFlag=='Yes')
	{
		jQuery('#hideMoveToAddress').show();
	}
	 jQuery('#attachmentDescription').val('${manageAssetFormForChange.assetDetail.notes}');
	 if(jQuery('#attachmentDescription').val() != '' && jQuery('#attachmentDescription').val() != null){
			jQuery('#showAttachment').show();
		}
	 
	  <c:if test="${manageAssetFormForChange.assetDetail.pageCounts != null && manageAssetFormForChange.assetDetail.pageCounts !=''}">
	     var i=0;   	 
	  <c:forEach items="${manageAssetFormForChange.assetDetail.pageCounts}" var="item">
	  pageCountTypeListArray[i]= '${item.name}';
	  pageCountsDateListArray[i]= '${item.date}';
	  pageCountValueListArray[i]= '${item.count}';
	  i++;
	  </c:forEach>
	
		for(j=0;j<pageCountTypeListArray.length;j++){
		var defaultRow='<select id="select_id_'+j +'"><option value=\"\"></options>';
		var options="";
			for(var i=0 ; i<pageCountsArray.length;i++){
				if(pageCountTypeListArray[j].toUpperCase()==pageCountsArrayKey[i].toUpperCase()){
					options=options+'<option selected value="'+pageCountsArrayKey[i]+'">' + pageCountsArray[i]+ '</options>';
				}else{
					options=options+'<option value="'+pageCountsArrayKey[i]+'">' + pageCountsArray[i]+ '</options>';
					}
			
			}
		var firstRow=defaultRow+options+"</select>";
		var secondRow="<div class=\"w160 floatL\"><input type=\"text\" value=\""+ pageCountsDateListArray[j] +"\" readonly=\"true\" style=\"float:left;\" id=\"rwid_"+ j +"\" class=\"w150\" onchange=\"shwDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" onblur=\"shwDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" /><img id=\"img\" class=\"cal_date ui_icon_sprite calendar-icon\" src=\"" + "/LexmarkServicesPortal/images/transparent.png" + "\" onClick=\"showCal('rwid_"+ j +"' , 'Date', 'Date', true);\" onFocus=\"this.className='';\"><img id=\"pageCountsDateDelete"+ j +"\" class=\"ui_icon_sprite ui-icon-closethick\" src=\"<html:imagesPath/>transparent.png\" onClick=\"removeDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" /></div>";
		var thirdRow="<input type=\"text\" value=\""+ pageCountValueListArray[j] +"\" id='newCount_"+j+"' class=\"w150\"/>";
		var forthRow="<a href=\"javascript:deleteGridRow();\"><img class=\"ui_icon_sprite trash-icon\" style=\"float:none;\" src=\'"+"/LexmarkServicesPortal/images/transparent.png"+"'  width=\"15\" height=\"15\"/>";
		//pageCountsGrid.addRow(pageCountsGrid.uid(), [firstRow,secondRow,thirdRow,forthRow],pageCountsGrid.getRowsNum());
		jQuery('#pageCountsTable tbody').append("<tr id=\"rowCount_"+j+"\""+" class=\"rowspace\" >"+
							"<td>" +firstRow +"</td>"+"<td>"+secondRow +"</td>"+
							"<td colspan=\"2\">"+thirdRow +"</td>"+"</tr>");
		addAlternateClass();
		if(jQuery("#rwid_"+ j).val()=='') {
			jQuery("#pageCountsDateDelete"+ j).hide();
			}
		}
		id = jQuery('#pageCountsTable tbody').children('tr').length;
	  </c:if>
	  
	jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>');	
	if (jQuery('#installAssetYes').is(':checked'))  //Added defect #7853
		{
		//document.getElementById('move_type').style.display = "block";
		}
	var offsetMinuteServiceRequest = new Date().getTimezoneOffset();
	 timezoneOffsetServiceRequest = (offsetMinuteServiceRequest/60)*(-1);
/***************This is for front-end validation*******************/

//alert("flowPage"+flowPage);
var fleetManagegmtFlag = "${fleetManagementFlag}";
//alert("fleetManagegmtFlag"+fleetManagegmtFlag);
var changeAssetConfirmUrl="${changeAssetConfirmationUrl}"+"&timeZoneOffset=" + timezoneOffset+"&pageFlow="+flowPage+"&fleetManagementFlag="+fleetManagegmtFlag;

	xCo= jQuery("#errorDiv").offset().left;
	YCo= jQuery("#errorDiv").offset().top;
	
	//validation for continue button
	jQuery(':input').bind('mousedown focus',function(){
			jQuery(this).removeClass('errorColor');
			});
	
	jQuery("#btnContinue").click(function(){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSET%>','<%=LexmarkSPOmnitureConstants.REVIEWCHANGEASSETSUBMIT%>');
		var k=0;
		var pageCountType = new Array();
		var pageCountsDate = new Array();
		var pageCountValue = new Array();
		for(i=0;i<=id;i++){
			
			var idGeneratePT="select_id_"+i;
			var idGenerateD="rwid_"+i;
			var idGenerateV="newCount_"+i;
			if(document.getElementById(idGeneratePT) != null && document.getElementById(idGenerateD)!=null &&  document.getElementById(idGenerateV)!=null){
			pageCountType[k]=jQuery("#"+idGeneratePT).val();
			pageCountsDate[k]=jQuery("#"+idGenerateD).val();
			pageCountValue[k]=jQuery("#"+idGenerateV).val();
			k++;
			}
		}
		<%--Changed for LBS --%>
		/*if(jQuery("#physicalLocation1").val()!=''&&jQuery("#physicalLocation1").val()!=null){
			jQuery("#physicalLocation1h").val(jQuery("#physicalLocation1").val());	
		}
		if(jQuery("#physicalLocation2").val()!=''&&jQuery("#physicalLocation2").val()!=null){
			jQuery("#physicalLocation2h").val(jQuery("#physicalLocation2").val());	
		}
		if(jQuery("#physicalLocation3").val()!=''&&jQuery("#physicalLocation3").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#physicalLocation3").val());	
		}
		if(jQuery("#zone").val()!=''&&jQuery("#zone").val()!=null){
			jQuery("#zoneh").val(jQuery("#zone").val());	
		}
		if(jQuery("#bldng").val()!=''&&jQuery("#bldng").val()!=null){
			jQuery("#physicalLocation1h").val(jQuery("#bldng").val());	
		}
		if(jQuery("#flr").val()!=''&&jQuery("#flr").val()!=null){
			jQuery("#physicalLocation2h").val(jQuery("#flr").val());	
		}
		if(jQuery("#office").val()!=''&&jQuery("#office").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#office").val());	
		}
		*/
		
		if(jQuery("#physicalLocation1").val()!=''&&jQuery("#physicalLocation1").val()!=null){
			jQuery("#bldng1").val(jQuery("#physicalLocation1").val());	
		}
		if(jQuery("#physicalLocation2").val()!=''&&jQuery("#physicalLocation2").val()!=null){
			jQuery("#flr1").val(jQuery("#physicalLocation2").val());	
		}
		if(jQuery("#physicalLocation3").val()!=''&&jQuery("#physicalLocation3").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#physicalLocation3").val());	
		}
		/*if(jQuery("#zone").val()!=''&&jQuery("#zone").val()!=null){
			jQuery("#zone1").val(jQuery("#zone").val());	
		}
		if(jQuery("#bldng").val()!=''&&jQuery("#bldng").val()!=null){
			jQuery("#bldng1").val(jQuery("#bldng").val());	
		}
		if(jQuery("#flr").val()!=''&&jQuery("#flr").val()!=null){
			jQuery("#flr1").val(jQuery("#flr").val());	
		}*/
		if(jQuery("#office").val()!=''&&jQuery("#office").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#office").val());	
		}
		//alert("move"+jQuery("#moveTophysicalLocation1").val());
		
		if(jQuery("#moveTophysicalLocation1").val()!=''&&jQuery("#moveTophysicalLocation1").val()!=null){
			
		
			jQuery("#bldngm1").val(jQuery("#moveTophysicalLocation1").val());	
		}
		if(jQuery("#moveTophysicalLocation2").val()!=''&&jQuery("#moveTophysicalLocation2").val()!=null){
			jQuery("#flrm1").val(jQuery("#moveTophysicalLocation2").val());	
		}
		if(jQuery("#moveTophysicalLocation3").val()!=''&&jQuery("#moveTophysicalLocation3").val()!=null){
			jQuery("#moveTophysicalLocation3h").val(jQuery("#moveTophysicalLocation3").val());	
		}
		/*if(jQuery("#zonem").val()!=''&&jQuery("#zonem").val()!=null){
			jQuery("#zonemh").val(jQuery("#zonem").val());	
		}*/
		if(jQuery("#zonemove").val()!=''&&jQuery("#zonemove").val()!=null){
			jQuery("#zonem1").val(jQuery("#zonemove").val());	
		}
		/*if(jQuery("#bldngm").val()!=''&&jQuery("#bldngm").val()!=null){
			jQuery("#moveTophysicalLocation1h").val(jQuery("#bldngm").val());	
		}
		if(jQuery("#flrm").val()!=''&&jQuery("#flrm").val()!=null){
			jQuery("#moveTophysicalLocation2h").val(jQuery("#flrm").val());	
		}*/
		if(jQuery("#moveTophysicalLocation3m").val()!=''&&jQuery("#moveTophysicalLocation3m").val()!=null){
			jQuery("#moveTophysicalLocation3h").val(jQuery("#moveTophysicalLocation3m").val());	
		}
		
		var moveTypeSelection=jQuery('#selectMoveType option:selected').val(); //Added for Move Type Validation--CI BRD 13-10-03
		jQuery("#errorDiv").html('');
		/*if(jQuery('#installAssetNo').is(':checked'))  //Added defect #7853
			{
			//jQuery("#move_type").hide();
			}*/
		 var elementIdsToValidate=["addtnlDescription","ipAddress","hostName","customerAssetTag","assetCostCenter","effDtOfChange"];
		  
		  	  	validationflag=false;
				for(i=0;i<elementIdsToValidate.length;i++){
					if((jQuery('#'+elementIdsToValidate[i]).val()).length>0){
					
						var patRes=validate(elementIdsToValidate[i],jQuery('#'+elementIdsToValidate[i]).val());
						
						if (patRes!=true)
						{
							validationflag=true;
							jQuery('#'+elementIdsToValidate[i]).addClass('errorColor');
							jQuery("#errorDiv").append('<li class="portlet-msg-error"><strong>'+patRes+'</strong></li>');	
						}
					}
				}
			
			
			var lbsaddressflagins=jQuery("#installLBSAddressFlag").val();
			var lbsaddressflagmov=jQuery("#moveToAddressLBSAddressFlag").val();
			var levelOfDetails = $.trim(jQuery("#moveToAddressLevelOfDetails").val()).toLowerCase();
			var installLevelOfDetails = $.trim(jQuery("#installLevelOfDetails").val()).toLowerCase();
			
			if(lbsaddressflagins=="true" && installLevelOfDetails.match("floor level|grid level|mix - see floor")){
			
				if(jQuery('#physicalLocation2h').val() == ''||jQuery('#physicalLocation2h').val() == null){
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.install.floor'/></strong></li>");
				}
				if(jQuery('#physicalLocation1h').val() == ''||jQuery('#physicalLocation1h').val() == null ){
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.install.building'/></strong></li>");
				}
			}
			if(lbsaddressflagmov=="true" && levelOfDetails.match("floor level|grid level|mix - see floor")){//alert("lbsaddressflagmov");
				if(jQuery('#moveTophysicalLocation2h').val() == ''||jQuery('#moveTophysicalLocation2h').val() ==null){
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.move.floor'/></strong></li>");
				}
				if(jQuery('#moveTophysicalLocation1h').val() == ''||jQuery('#moveTophysicalLocation1h').val() ==null ){
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.move.building'/></strong></li>");
				}
			}
			
			var fleetMgmtFlag = "${fleetManagementFlag}";
			//alert("fleetMgmtFlag:: "+fleetMgmtFlag);
			if(fleetMgmtFlag!= null && fleetMgmtFlag!= 'true'){
				if(!(jQuery('#installAssetYes').is(':checked')) && !(jQuery('#installAssetNo').is(':checked')))
				{	
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.Asset.moveAssetFlag.format.errorMsg'/>" 
					+ "</strong></li>");
				}
			}else if(fleetMgmtFlag!= null && fleetMgmtFlag== 'true'){
				
				var pageFlow = "${pageFlow}";
				if(pageFlow == "fleetMgmtMove"){
					//jQuery('#installAssetYes').attr("checked", "checked");
					if(!(jQuery('#installAssetYes').is(':checked')) && !(jQuery('#installAssetNo').is(':checked')))
				{	
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.Asset.moveAssetFlag.format.errorMsg'/>" 
					+ "</strong></li>");
				}
					
				}else if(pageFlow == "fleetMgmtChange"){
					jQuery('#installAssetNo').attr("checked", "checked");
				
					
				}/*else{
					jQuery('#installAssetNo').attr("checked", "checked");
				}*/
				//alert("Chaeck box value:: "+jQuery('#installAssetYes').attr("checked"));
			
			}			
			
				 //Added for Move Type Validation--CI BRD 13-10-03
				if((jQuery('#installAssetYes').is(':checked')))
					{
					
					if(jQuery('#moveToAddressLine1').val() == '' && jQuery('#moveToAddressCity').val() == '' && jQuery('#moveToAddressState').val() == '' && jQuery('#moveToAddressCountry').val() == ''){
						validationflag=true;
						jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.moveToAddressEmpty'/>" 
						+ "</strong></li>");
						}
					if(fleetMgmtFlag!= null && fleetMgmtFlag== 'true'){
						var pageFlow = "${pageFlow}";
						if(pageFlow == "fleetMgmtMove" && (lbsaddressflagmov=="true" && 
								levelOfDetails.match("floor level|grid level|mix - see floor"))){
										
								if(jQuery('#bldngm1').val()==''||jQuery('#bldngm1').val()==null||jQuery('#bldngm1').val()=="Select Building"){
								validationflag=true;
								jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.move.building'/></strong></li>");
							}	if(jQuery('#flrm1').val() == ''||jQuery('#flrm1').val()==null||jQuery('#flrm1').val()== "Select Floor"){
								validationflag=true;
								jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.move.floor'/></strong></li>");
							}
								
						}
					}	
					
 				}
				 
				if((jQuery('#installAssetNo').is(':checked')))
				{
					if(fleetMgmtFlag!= null && fleetMgmtFlag== 'true'){
						var pageFlow = "${pageFlow}";
						/*if(pageFlow == "fleetMgmtMove"&&lbsaddressflagmov!="true"){
										
								if(jQuery('#bldngm1').val()==''||jQuery('#bldngm1').val()==null||jQuery('#bldngm1').val()=="Select Building"){
								validationflag=true;
								jQuery("#errorDiv").append("<li><strong><spring:message code='lbs.label.move.building'/></strong></li>");
							}	if(jQuery('#flrm1').val() == ''||jQuery('#flrm1').val()==null||jQuery('#flrm1').val()== "Select Floor"){
								validationflag=true;
								jQuery("#errorDiv").append("<li><strong><spring:message code='lbs.label.move.floor'/></strong></li>");
							}
								
						}*/
					}	
				}
				if(pageCountValidate()==false) {
					//jQuery('#errorDiv').append('<li><strong>Please enter proper data in page counts</strong></li>');
				
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				validationflag=true;
			}
				
				
				if(jQuery('#pageCountValue').val() != null && jQuery('#pageCountValue').val() != "" ){
					pageCountValueListArray=jQuery('#pageCountValue').val().split(",");
				}
				if(jQuery('#pageCountDateValid').val() != null && jQuery('#pageCountDateValid').val() != "" ){
					pageCountsDateListArray=jQuery('#pageCountDateValid').val().split(",");
				}
				
				for(var j=0; j<pageCountsDate.length; j++){
					if(pageCountsDate != null && pageCountsDate !=""){
						if(pageCountsDate[j] !=null && pageCountsDate[j] != ""){
						var currentDate=new Date(formatDateToDefault(pageCountsDate[j])).toUTCString();	
						if(new Date(currentDate)>new Date(new Date().toUTCString())){					
							jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.currentDateExceed'/> " 
									+(j+1)+ "</strong></li>");
							jQuery('#errorDiv').show();
							jQuery(document).scrollTop(0);
							validationflag=true;
						}
						}
					}
					
					if(pageCountValueListArray[j] != null && pageCountValueListArray[j] != ""){
					if(parseInt(pageCountValueListArray[j])>parseInt(pageCountValue[j])){						
						jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.newPageCountValue'/> "+(j+1)+ "</strong></li>");
						jQuery('#errorDiv').show();
						jQuery(document).scrollTop(0);
						validationflag=true;
				}
					if((pageCountValue[j]-pageCountValueListArray[j])>500000){
						jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.newPageCountValue1'/> " 
								+(j+1)+ "</strong></li>");
						jQuery('#errorDiv').show();
						jQuery(document).scrollTop(0);
						validationflag=true;
				}
					}
				}
				
				
				
				
				if( dublicatePCCheck(pageCountType) == false){
					jQuery('#errorDiv').show();
					jQuery(document).scrollTop(0);
					validationflag=true;
				}
				if(bindDeviceContact()==false) {
					if(duplicateContactType){
						jQuery('#errorDiv').append("<li class=\"portlet-msg-error\"><strong><spring:message code='validation.asset.add.dublicateContactInfo'/></strong></li>");
					}
					if(duplicateContact){
						jQuery('#errorDiv').append("<li class=\"portlet-msg-error\"><strong><spring:message code='contact.popup.duplicateData'/></strong></li>");
					}
					if(emptyContact){
						jQuery('#errorDiv').append("<li class=\"portlet-msg-error\"><strong><spring:message code='validation.asset.add.noDataInContactInfo'/></strong></li>");
					}	
					if(emptyContactType){
						jQuery('#errorDiv').append("<li class=\"portlet-msg-error\"><strong><spring:message code='validation.asset.add.contactType'/></strong></li>");
					}				
					validationflag=true;
				}
				
				if(validationflag==true){
					jQuery("#errorDiv").show();
					jQuery(document).scrollTop(0);
						return false;
				}
				else{
					//bindDeviceContact();
				
					jQuery('#pageCountValue').val(pageCountValueListArray);
					jQuery('#pageCountDateValid').val(pageCountsDateListArray);
					jQuery('#pageCountTypeID').val(pageCountType);
					jQuery('#pageCountsDateID').val(pageCountsDate);
					jQuery('#pageCountValueID').val(pageCountValue);
					jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
					
					$("#moveToFloorLevelOfDetails").val($("#flrm option:selected").attr("lod"));
					$("#installFloorLevelOfDetails").val($("#flr option:selected").attr("lod"));
					
					//jQuery("#move_type").show(); //commented defect #7853
					jQuery("#changeAssetForm").attr("action", changeAssetConfirmUrl);
					jQuery("#changeAssetForm").submit();
				}

				
	});
		
	//This is done temporarily
	if(jQuery("#chlNodeValue").val()!="")
	{
		jQuery("#chlNodeValueLabel").html(jQuery("#chlNodeValue").val());
		jQuery('#chlNodeValueChangeLbl').show();
		jQuery('#chlTreeLink').hide();
		
	}
	else 
		{
		jQuery("#chlNodeId").val("${manageAssetFormForChange.assetDetail.chlNodeId}");//Hard coded because chl node id is not available */
		}
	
	if('${manageAssetFormForChange.copyFromConsAddress}'=="true")
	{
		jQuery('#installAddressDataCopyFromConAddr').attr('checked', true);
	};
	
});


//Script for Request History Grid CI (BRD13-10-02) START
var counter=0;
//Added for CI BRD13-10-02--START
function show_load(){
		counter++;
		if(!(counter%2)==0)
			{
		initialiseGrid();
		jQuery("#gridContainerDiv_all_request_types").css('height','25px');
		jQuery("#gridContainerDiv_all_request_types").show();
			}
	}
//Added for CI BRD13-10-02--END


// following variables are declared in dynamicGridInitialize
pagingArea="pagingArea_request_type";infoArea="infoArea_req_status";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification_request_type";backFilterValues="";
gridCreationId="gridContainerDiv_all_request_types";
pageSize=5,pagesIngrp=10;
JSON_Param["<%=gridConfigurationValues[0]%>"]="<spring:message code='suppliesmanagement.details.srhistorygrid'/>";
JSON_Param["<%=gridConfigurationValues[1]%>"]=",,,,";
JSON_Param["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left";
JSON_Param["<%=gridConfigurationValues[3]%>"]="130,140,120,120,100";
JSON_Param["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro";
JSON_Param["<%=gridConfigurationValues[5]%>"]="str,str,str,str,str";
JSON_Param["<%=gridConfigurationValues[6]%>"]="0,1,2,3,4"; //Changed for CI Defect #8217
JSON_Param["<%=gridConfigurationValues[7]%>"]="0,des";
JSON_Param["<%=gridConfigurationValues[8]%>"]=""; //Changed for CI Defect #8217
JSON_Param["<%=JSON_COMBO_FILTER%>1"]="";
JSON_Param["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id='retrieveHistoryList'/>";
JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";

/*
 *Added for MPS phase 2 
 *
 */

reloadGrid=function(){
	clearMessage();
	refreshGridSettingOnPage(requestListGrid);
	xmlURLQueryParams.setLoadXMLUrl();
	requestListGrid.clearAndLoad(xmlURL);
	
};


initURLParams = function(){
	xmlURLQueryParams={
			urlParameters:["direction","orderBy","timezoneOffset","accountRowId","srHistoryType","assetRowId"],
			setParameters : function(){
									this["direction"]=requestListGrid.a_direction;
									this["orderBy"]=requestListGrid.getSortColByOffset();
									this["timezoneOffset"]=timezoneOffset;
									this["accountRowId"]=accountId;
									this["srHistoryType"]="ALL_REQUESTS";
									this["assetRowId"]=assetRowId1;
									
									},
			setLoadXMLUrl : function(){
											xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
											this.setParameters();
											for(i=0;i<this.urlParameters.length;i++){
												if(this[this.urlParameters[i]]!=null)
													xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
													
											}	
									}
			};
	
		
		
		
	};

function onSRNmbrClick(serviceRequestNumber, requestType){
	var serviceRqstType=requestType;
	if(serviceRqstType=='Consumables Management')
		serviceRqstType='Consumables_Management';
    if(serviceRqstType=='Fleet Management')
    	serviceRqstType='Fleet_Management';
	
	goToDetailPage(serviceRequestNumber,serviceRqstType);
}



//Script for Request History Grid CI (BRD13-10-02) ENDS 


/********************Method for copying install address data into consumables address*******************/
	jQuery('input[type="checkbox"]').click(function()
		{	
			var thisCheck=jQuery(this);	
			
			if(jQuery(this).attr('id')=="installAddressDataCopyFromConAddr")
			{
			if (thisCheck.is (':checked'))
				{
				
				copyValues();
				//MPS 2.1 Changes
				addConsumablesAddress(null, null, addrLine1, addrLine2, addrCity,
						addrState, addrProvince, addrCountry, addrPostCode, addrStrFrntNm, addrBuilding,addrFloor,addrOffice, null);
				jQuery("#currConsAddrLink").show();				
				jQuery("#installAddressDataCopyFromConAddr").attr('checked', true);				
				thisCheck.val('true');				
				jQuery("#installAddrDataCpyFrmConAddr").val("true");
				copyInstAddr=true;
				return;
				}
			thisCheck.val('false');
			jQuery("#installAddrDataCpyFrmConAddr").val("false");
		}	
	});

	
	/********************Method for consumables contact*******************/
	function addConsumablesContactElement(ci,ln,fn,wp,ea,ad1,ad2,city,state,province,country,
			pocode,phyLoc1,phyLoc2,phyLoc3,midName,altPhn) {
			
			//This is only for consumables contact of the asset
			if(linkId=="consumablesContact")
			{
				jQuery("#currConsContLink").show();
				addConsumablesContact(ci, ln, fn, midName, wp, altPhn, ea);
			}
		}
	

	/********************Method for closing all kinds of popups*******************/	
	function closeDialog()
	{
			dialog.dialog('close');
			dialog=null;
			jQuery('#dialog').html("");
	}


	/********************Method for showing of hierarchy tree *******************/	
	function showHiererchy() {
		jQuery('#chlTreeLink').hide();
		jQuery('#chlNodeValueChangeLbl').show();
		
	}
	


/*******************Method for opening the popup for all kinds of contact***************/		

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
        url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
            "<portlet:param name='action' value='gotoControlPanel' />" +
            "</portlet:renderURL>&controlPanelURL=" + url
        }); --%>
};


/*******************Method for opening the popup for hierarchy***************/
function popUpChlTree()
{
	
	jQuery('#dialogChlTree').load(jQuery('#chlTreeLink').attr('href'),function(){
		
		var heightDoc=jQuery(window).height()<630?630:jQuery(window).height();		
		var divht=heightDoc-150;		
		jQuery('#chl_node_tree_container').css('width','100%');
		jQuery('#chl_node_tree_container').css('height',divht+'px');	
		dialog=jQuery('#content_chlTree').dialog({
		autoOpen: false,
		title: jQuery('#chlTreeLink').attr('title'),
		modal: true,
		draggable: false,
		resizable: false,		
		width: 400,
		height: heightDoc-80,
		position: 'top',
		close: function(event,ui){
				dialog=null;
				jQuery('#content_chlTree').remove();
				//This section is to hide the select hierarchy level link				
				if(jQuery("#chlNodeValue").val()!="" && jQuery('#cValue').val()!=""){
					showHiererchy();
				} 
			}
	});
	jQuery(document).scrollTop(0);
	dialog.dialog('open');
	hideOverlay();

	if (window.PIE) {
		 	document.getElementById("button_cancel_chl").fireEvent("onmove");
		   	document.getElementById("button_chl").fireEvent("onmove");
		}
	
	initialiseCHLTree();		
});
	
return false;
};
	       

	

/********Method to be called for reverting to current installed address**************/	

	<%--/********************End*******************/ --%>

	<%--/********************Method redirecting to asset list page on clicking of the link "Choose a different asset" *******************/--%>
	function redirectToAssetListPage(redirectToAssetListUrl)
	{
		//This request goes to the manageAssetController default mapping, results in assetList.jsp		
		showOverlay();
		window.location.href=redirectToAssetListUrl;
	}
	<%--/********************End*******************/--%>

		image_error= function () {
			
	  document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
	  
	};
	
	function clearValidatnErr()
	{
		if(jQuery("#errorDiv").html()!=""){
		
		jQuery("#errorDiv").html('');
		jQuery("#errorDiv").hide();
		}
	}
	
	function showHideMoveType(id)
	{
		var yes='installAssetYes';
		var no='installAssetNo';
		if(id==yes)
			{
			jQuery('#hideMoveToAddress').show();
			// document.getElementById('move_type').style.display = "block";
			}
		else if(id==no)
			{
			jQuery('#hideMoveToAddress').hide();
			// document.getElementById('move_type').style.display = "none";
			 jQuery('#selectMoveType option:selected').val(""); // Done for Move Type Validation CI BRD13-10-03
			 jQuery("#selectMoveType").val(' '); //Done for CI Defect #7853
			}
	}
	
	function showHideFleetMoveType(id){
		var yes='installAssetYes';
		var no='installAssetNo';
		if(id==yes)
		{
		jQuery('#moveToAddressLink').hide();
		// document.getElementById('move_type').style.display = "block";
		}
	else if(id==no)
		{
		jQuery('#moveToAddressLink').show();
		// document.getElementById('move_type').style.display = "none";
		 //jQuery('#selectMoveType option:selected').val(""); // Done for Move Type Validation CI BRD13-10-03
		 //jQuery("#selectMoveType").val(' '); //Done for CI Defect #7853
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


</script>