<%-- Added for CI BRD13-10-02--STARTS --%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<jsp:include page="../../common/mapViewPopup.jsp"></jsp:include>
<%-- Added for CI BRD13-10-02--ENDS --%>
<%-- This subtab.jsp is for the Request History/Change Request tab at the top --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/> 
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%-- <jsp:include page="../../common/subTab.jsp"/> --%>

<%-- <%@ include file="/WEB-INF/jsp/include.jsp"%> --%>
<jsp:include page="../../common/validationMPS.jsp"></jsp:include>

<%-- Added for CI BRD13-10-02 --%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%-- Below jsp added for CI BRD 13-10-02--%>
<%@ include file="/WEB-INF/jsp/common/reqDetailsPopUp.jsp" %>

<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<script type="text/javascript">
<%@ include file="/js/commonAsset.js" %>//Content of js needs to be dynamically included in jsp	
</script>

<%-- Added for CI BRD13-10-02 --%>
<script type="text/javascript" src='<html:rootPath/>js/expand.js'></script>
<style type="text/css">
#pageCountsTable th {
    background-color: #e6e6f0;
    padding: 10px;
}
.rowspace td{
padding: 10px 7px 9px 0;
}
</style>
<%--Added for CI--%>
<%-- Below div opens up as popup with request detail--%>
	<div id="dialogChangeDetails" style="display: none;"></div>
	<div id="dialogSupplyDetails" style="display: none;"></div>

<%-- Below URL sends the request to controller to show the review page --%>
<portlet:renderURL var="decommissionAssetConfirmationUrl">
<portlet:param name="action" value="decommissionAssetConfirmation"></portlet:param>
</portlet:renderURL>

<%-- Below URL sends the request to main asset list page --%>
<portlet:renderURL var="redirectToAssetListPage"></portlet:renderURL>

<portlet:resourceURL var="displayAttachmentURL" id="displayAttachment">
	
</portlet:resourceURL>

<portlet:actionURL var="addAttachmentsCreate">
	<portlet:param name="action" value="addAttachmentsCreate"/>
</portlet:actionURL>
<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachment"/>
</portlet:actionURL> 

<!-- <body onload="javascript:hideSiteContact();"/> -->
<%-- Below div opens up as popup, with contact & address respectively --%>
<div id="dialog_contact"></div>
<div id="dialogAddress"></div>

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
        	
<%--Form Bean is manageAssetFormForDecommission instance of ManageAssetForm - --%>
<form:form method="post" modelAttribute="manageAssetFormForDecommission" id="decommAssetForm">

<%-- Below section is for the prev sr no and update flag binding--%>
<form:hidden path="prevSrNo" id="prevSrNo"/>


<div class="main-wrap">
      <div class="content">
      <!-- MAIN CONTENT BEGIN -->
      <%-- This section shows the server side validation errors --%>
          <spring:hasBindErrors name="manageAssetFormForDecommission">
          	<div class="serviceError">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li class="portlet-msg-error"><strong><spring:message code="${error.code}"/></strong></li>
            	</c:forEach>
     		</div>	
	     </spring:hasBindErrors>
	     <spring:hasBindErrors name="fileUploadForm">
				<div class="serviceError" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li class="portlet-msg-error"><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
	     <%--End of server side validation errors --%>
	        <div class="serviceError" id="errorDiv" style="display: none;"></div> 
	        <div id="jsValidationErrors" class="error" style="display: none;"></div>	
	        <div id="validationErrors" class="error" style="display: none;"></div>
	        <%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
	        
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.decommissionAnAsset"/></h3>
          
          <%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
		Additional Information of the Change Mgmt Request End--%>
		<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
		
         <hr class="separator" />
        <div class="portletBlock infoBox rounded shadow">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
				<tr>
					 <td class="pModel"><ul>
						  <li class="center">
						  <img src="${manageAssetFormForDecommission.assetDetail.productImageURL}" 
						  width="100" height="100" id="MyPicture" onError="image_error();"/></li>
						  <li class="pModelName"><%-- ${manageAssetFormForDecommission.assetDetail.productTLI} --%>
						  ${manageAssetFormForDecommission.assetDetail.descriptionLocalLang}
						  <br/>
						  <a id="differentAssetlink" href="javascript:redirectToAssetListPage('<%=redirectToAssetListPage%>');">
						  <spring:message code="requestInfo.link.chooseAdifferentAsset"/></a></li>
                </ul></td>
				<td class="pDetail"><!-- Inner table: 4 row2 & 2 colums -->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="h4"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails"/></td>
                     <td class="h4">&nbsp;</td>
                  </tr>
                  <tr>
                    <td class="infoDsiplay"><ul class="form">
				<li><label ><spring:message code="requestInfo.assetInfo.label.serialNumber"/> </label>
					<span>${manageAssetFormForDecommission.assetDetail.serialNumber}</span></li>
				<li><label><spring:message code="requestInfo.assetInfo.label.productName"/> </label>
				<%-- <span>${manageAssetFormForDecommission.assetDetail.productLine}</span> --%>
				<span>${manageAssetFormForDecommission.assetDetail.productTLI}</span>
				</li>
				<li><label><spring:message code="requestInfo.assetInfo.label.installDate"/> </label>
				<span>
				<util:dateFormat value="${manageAssetFormForDecommission.assetDetail.installDate}">
                </util:dateFormat>
				<%-- ${manageAssetFormForDecommission.assetDetail.installDate} --%>
				</span></li>
				<li><label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
				<%--Start: Added for Defect 3924-Jan Release--%>
				<span><a href="javascript:gotoControlPanel('${manageAssetFormForDecommission.assetDetail.controlPanelURL}')">${manageAssetFormForDecommission.assetDetail.ipAddress}</a></span>
				<%--End: Added for Defect 3924-Jan Release--%>
                </li>
                
				<li><label><spring:message code="requestInfo.assetInfo.label.hostName"/> </label>
                    <span>${manageAssetFormForDecommission.assetDetail.hostName}
                </span></li>
                <li><label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/> </label>
                    <span>${manageAssetFormForDecommission.assetDetail.deviceTag}</span>
                </li>
			</ul></td>
                  </tr>
                </table></td>
				</tr>
			</table>
		  <%--Selected asset information start --%>
			</div>
</div>
<%-- Selected Asset Information End --%>

<%--Request History for Asset (CI) STARTS--%>
 <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand" onclick="show_load();"> <!--show load called for asynshronous-CI7 -->
              <spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></h4>
              <div  class="collapse">
<!--                <div class="grid-controls">  -->
<!--                       Expand-min Start -->
                       <div class="expand-min">
                      
                       <a href="#grid" id='headerMenuButton' style="display: none;"><spring:message code="requestInfo.link.customizeColumns"/></a>  <!--DONT REMOVE THIS CI BRD13-10-02--%>
                       <!--Commented for CI BRD13-10-02--STARTS -->
<!--                         <ul> -->
<%--                           <li class="first"><img src="<html:imagesPath/>wrench.png"  style="cursor:pointer" alt="Customize Grid" title="Customize Grid" /> --%>
<%--                             <a href="#grid" id='headerMenuButton'><spring:message code="requestInfo.link.customizeColumns"/></a></li>  --%>
<%--                           <li><img src="<html:imagesPath/>reset.png" alt="Reset Grid" title="Reset Grid" /> --%>
<%--                           <a href="javascript:doReset();"><spring:message code="requestInfo.link.reset"/></a></li> --%>
<!--                         </ul> -->
						<!--Commented for CI BRD13-10-02--ENDS -->
                       </div>
<!--                       NO CONTENT HERE PLS  -->
<!--                     </div> -->
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
<jsp:include page="/WEB-INF/jsp/common/commonPageCount.jsp" />
<div class="portletBlock">
	<div class="columnsOne">
	<div class="infoBox columnInner rounded shadow">
			<ul class="form installCheck">
			<li>
                <label for="installAsset"><spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAsset"/><span class="req">*</span>
                </label>
				<span class="radio radio_confirm">
				
				<form:radiobutton id="installAssetYes" path="decommAssetFlag" onclick="clearValidatnErr()"/>	
				<label><spring:message code="requestInfo.option.yes"/></label>
				<form:radiobutton id="installAssetNo" path="decommAssetFlag" onclick="clearValidatnErr()"/>
				<label><spring:message code="requestInfo.option.no"/></label>
				<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" alt="<spring:message code="requestInfo.alt.enterAnyAdditionalInformationForPickUp"/>" />
            </span>
			</li>		
			</ul>
	</div>
	</div>
</div>
  <jsp:include page="/WEB-INF/jsp/common/commonProjectPage.jsp" />
<%-- Pickup Information Start --%>
<div id="showPickUpInfo" style="display: none;">

			<jsp:include page="/WEB-INF/jsp/common/commonInstallInfo.jsp" />
		   
		</div>
		
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

<div class="buttonContainer">
<c:if	test="${fleetManagementFlag == true }">
	<button class="button_cancel" onclick="onCancelClick();" type="button"><spring:message	code="button.back" /></button>
</c:if> <c:if test="${fleetManagementFlag != true }">
	<button class="button_cancel"
		onclick="javascript:backToSelect();callOmnitureAction('<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSET%>','<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETBACK%>');"	type="button"><spring:message code="button.back" /></button>
</c:if> <c:if test="${fleetManagementFlag == true }">
	<button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message	code="button.cancel" /></button>
</c:if> <c:if test="${fleetManagementFlag != true }">
	<button class="button_cancel" type="button"
		onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSET%>','<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETCANCEL%>');"><spring:message	code="button.cancel" /></button>
</c:if>
<button class="button" type="button" id="btnContinue"><spring:message	code="button.continue" /></button>
</div>
</div>
        <!-- MAIN CONTENT END --> 
      
    </div>
</div>
  <!-- <div id="footer">Footer</div> -->		  
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include> <%--Added for CI BRD13-10-02--%>
<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForDecommission.backToMap}"/>
</form>
<script type="text/javascript">
<c:if test="${fleetManagementFlag == true }">
jQuery("#differentAssetlink").hide();
</c:if>
function onCancelClick() {
	//showOverlay();
	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					
	var lat="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLatitude}";
					
		var lon="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLongitude}";
	
	
									var defaultArea={											
								                lat: lat,
								                lng: lon,							                		           
												

											
											};
					
					$('#backJson').val(JSON.stringify(defaultArea));
					$('#backFormMap').submit();
				}else{
					return false;
					}
			});
};
var pageType = "decommissionAsset";
var portlet_name="<%=LexmarkSPOmnitureConstants. DECOMMISSIONASSET %>";
var action_primary="<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETSELECTADIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETADDADDITIONALCONTACT%>";

var dialog;
var linkId;

//Added for CI BRD13-10-02--START
//Bean name changed for CI Defect #8217
var assetRowId1="${manageAssetFormForDecommission.assetDetail.assetId}"; 
var accountId='${manageAssetFormForDecommission.assetDetail.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";
//Added for CI BRD13-10-02--END

/*******************Method for opening the popup for all kinds of address***************/
function popupAddress(hyperlinkId){
	
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	showOverlay();
	jQuery('#dialogAddress').load(jQuery('#'+hyperlinkId).attr('href'),function(){
		
		dialog=jQuery('#content_addr').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			height: 500,
			width: 950,
			close: function(event,ui){
				hideOverlay();
				dialog=null;
				jQuery('#content_addr').remove();
				}
			});
		jQuery(document).scrollTop(0);
		dialog.dialog('open');
		initializeAddressGrid();
		if (window.PIE) {
            document.getElementById("createNewAddressButton").fireEvent("onmove");
            document.getElementById("cancelAddressButton").fireEvent("onmove");
        }
		
		});
	return false;
}
/********************End*******************/


/*******************Method for hiding the site contact div at page load***************/
/* function hideSiteContact() {
	jQuery("#showPickUpInfo").hide();	
} */
/********************End*******************/

/********************Method for closing all kinds of popups*******************/
function closeDialog()
{
		dialog.dialog('close');
		dialog=null;
		jQuery('#dialog').html("");
		
		//if(secondaryContactReq)	showAddiContacts();
		//if(addressChangeReq) showAddress();	
}

/********************End*******************/

//MPS 2.1 Changes

	
/********************End*******************/	

/********************Clicking radio button will show the pickup information area*******************/
	jQuery('input[type="radio"]').click(function()
		{
			if(jQuery(this).attr('id')=="installAssetNo")
			{
				jQuery("#installAssetNo").val("no");
				jQuery("#showPickUpInfo").hide();
				jQuery("#projectBlock").hide();
				$("#projectDetailsCheckBox").val(false);
			}
			else 
			{
				jQuery("#showPickUpInfo").show();
				jQuery("#installAssetYes").val("yes");
				jQuery("#projectBlock").show();
				
			}
	
		});	

	/********************End*******************/


	/********************Method redirecting to asset list page on clicking of the link "Choose a different asset" *******************/
	function redirectToAssetListPage(redirectToAssetListUrl)
	{
		window.location.href=redirectToAssetListUrl;
	}

	/********************End*******************/

	/*********************************************************************************************************
	Below conditions are for back button placed on review page, showing the data user selected/entered earlier
	*/
	var pageCountTypeListArray= new Array(); 
	  var pageCountsDateListArray= new Array(); 
	  var pageCountValueListArray= new Array(); 
	jQuery(document).ready(function() {
		var currentURL = window.location.href;
		jQuery('#pageCountValue').val('${pageCountsOriginalval}');
		jQuery('#pageCountDateValid').val('${pageCountsOriginaldate}');
		 jQuery('#attachmentDescription').val('${manageAssetFormForDecommission.assetDetail.notes}');
		 if(jQuery('#attachmentDescription').val() != '' && jQuery('#attachmentDescription').val() != null){
				jQuery('#showAttachment').show();
			}
//			Change Account Link Hide/Show for CI-7 Defect #12274
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
		
		  <c:if test="${manageAssetFormForDecommission.assetDetail.pageCounts != null && manageAssetFormForDecommission.assetDetail.pageCounts !=''}">
	     var i=0;   	 
		  <c:forEach items="${manageAssetFormForDecommission.assetDetail.pageCounts}" var="item">
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
			jQuery('#pageCountsTable tbody').append("<tr id=\"rowCount_"+j+"\""+"class=\"rowspace\" >"+
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

		
		
			jQuery('input[type="radio"]').attr('checked', false);
		
		var radioFlag='${manageAssetFormForDecommission.decommAssetFlag}';
		
		if(radioFlag=='no')
		{
			jQuery("#showPickUpInfo").hide();
			jQuery("#projectBlock").hide();
			$("#projectDetailsCheckBox").val(false);
			jQuery('#installAssetNo').attr('checked', true);
			jQuery("#installAssetNo").val("no");
		}
		else if(radioFlag=='yes') 
			{
			jQuery("#showPickUpInfo").show();
			jQuery("#projectBlock").show();
			
			jQuery('#installAssetYes').attr('checked', true);
			 jQuery("#installAssetYes").val("yes");
			
			}
	});

	var decommAssetConfirmUrl="${decommissionAssetConfirmationUrl}"
	// added
	jQuery("#btnContinue").click(function(){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSET%>','<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETCONTINUE%>');
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
		
		
		if(jQuery("#physicalLocation1").val()!=''&&jQuery("#physicalLocation1").val()!=null){
			jQuery("#bldng1").val(jQuery("#physicalLocation1").val());	
		}
		if(jQuery("#physicalLocation2").val()!=''&&jQuery("#physicalLocation2").val()!=null){
			jQuery("#flr1").val(jQuery("#physicalLocation2").val());	
		}
		if(jQuery("#physicalLocation3").val()!=''&&jQuery("#physicalLocation3").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#physicalLocation3").val());	
		}
		
		if(jQuery("#office").val()!=''&&jQuery("#office").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#office").val());	
		}
		
	jQuery("#errorDiv").html('');
	jQuery("#errorDiv").hide();
	 var elementIdsToValidate=["addtnlDescription","effDtOfChange"];
	  
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
			
			if(!(jQuery('#installAssetYes').is(':checked')) && !(jQuery('#installAssetNo').is(':checked')))
			{	
				validationflag=true;
				jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.Asset.decomissionFlag.format.errorMsg'/>" 
				+ "</strong></li>");
				
			}
			if(jQuery('#pageCountValue').val() != null && jQuery('#pageCountValue').val() != "" ){
				pageCountValueListArray=jQuery('#pageCountValue').val().split(",");
			}
			if(jQuery('#pageCountDateValid').val() != null && jQuery('#pageCountDateValid').val() != "" ){
				pageCountsDateListArray=jQuery('#pageCountDateValid').val().split(",");
			}
			
			//lbs
			if(jQuery('#installAssetYes').is(':checked')){
			
			var lbsaddressflagins=jQuery("#installLBSAddressFlag").val();
			var installLevelOfDetails = $.trim(jQuery("#installLevelOfDetails").val()).toLowerCase();
			
			if(lbsaddressflagins=="true" && installLevelOfDetails.match("floor level|grid level|mix - see floor")){
				if(jQuery('#physicalLocation2h').val() == ''|| jQuery('#physicalLocation2h').val()== null){
					
					validationflag=true;
					jQuery("#errorDiv").append("<li  class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.pickup.floor'/></strong></li>");
				}
				if(jQuery('#physicalLocation1h').val() == ''|| jQuery('#physicalLocation1h').val()== null){
					
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.pickup.building'/></strong></li>");
				}
				
				
			}
			}
			<%--Added for Project Flag--%>
			
			if(jQuery('#projectDetailsCheckBox').is(':checked')&&jQuery("#projectDetailsCheckBox").val()=="true"){
				if(jQuery('#projectName').val() == ''|| jQuery('#projectName').val()== null){
					
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>Please enter Project Name</strong></li>");
				}
				if(jQuery('#projectPhase').val() == ''|| jQuery('#projectPhase').val()== null){
					
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>Please enter Project Phase</strong></li>");
				}
			}
			for(var j=0; j<pageCountsDate.length; j++){	
				if(pageCountsDate != null && pageCountsDate !=""){
					if(pageCountsDate[j] != null && pageCountsDate[j] != ""){
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
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.newPageCountValue'/> " 
							+(j+1)+ "</strong></li>");
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
			if(pageCountValidate()==false) {
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				validationflag=true;
			}
			if( dublicatePCCheck(pageCountType) == false){
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				validationflag=true;
			}
			if(validationflag==true){
				jQuery("#errorDiv").show();
				jQuery(document).scrollTop(0);
					return false;
			}
			
			jQuery('#pageCountTypeID').val(pageCountType);
			jQuery('#pageCountsDateID').val(pageCountsDate);
			jQuery('#pageCountValueID').val(pageCountValue);
			jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
			jQuery('#pageCountValue').val(pageCountValueListArray);
			jQuery('#pageCountDateValid').val(pageCountsDateListArray);
			jQuery("#installFloorLevelOfDetails").val($("#flr option:selected").attr("lod"));
			jQuery("#decommAssetForm").attr("action", decommAssetConfirmUrl);
			jQuery("#decommAssetForm").submit();
							
});
	function clearValidatnErr()
	{
		if(jQuery("#errorDiv").html()!=""){
		
		jQuery("#errorDiv").html('');
		jQuery("#errorDiv").hide();
		}
	}
	
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
			
		//  Script for Request History Grid CI (BRD13-10-02) STARTS 

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
