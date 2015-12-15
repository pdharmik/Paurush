<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%--ends --%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>

<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript" src="<html:rootPath/>js/expand.js"></script>
<script type="text/javascript">
<%@ include file="/js/commonAsset.js"%>
</script> 
<style type="text/css">
#pageCountsTable th {
    background-color: #e6e6f0;
    padding: 10px;
}
.rowspace td{
padding: 10px 7px 9px 0;
}
</style>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include> 
<jsp:include page="../../common/validationMPS.jsp" />
<jsp:include page="../../common/mapViewPopup.jsp"></jsp:include>
<portlet:renderURL var="addAssetConfirmationUrl">
	<portlet:param name="action" value="addAssetConfirmation"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="showCHLTreePopUp"
	windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>
<portlet:resourceURL var="displayAttachmentURL" id="displayAttachment">
	
</portlet:resourceURL>

<portlet:actionURL var="addAttachmentsCreate">
	<portlet:param name="action" value="addAttachmentsCreate"/>
</portlet:actionURL>
<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachment"/>
</portlet:actionURL> 

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="dialogAddress" style="display: none;"></div>
<div id="dialog_contact" style="display: none;"></div>
<div id="dialogChlTree" style="display: none;">&nbsp;</div>
 
   <div class="journal-content-article">
  			<h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
  			<h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
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
        <!-- MAIN CONTENT BEGIN -->
        <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.addNewAsset"/> 
			<span><spring:message code="requestInfo.label.fieldsMarked"/><span class="req">*</span>

				 <spring:message code="requestInfo.label.areRequired"/>
			</span>
		</h3>
			<%-- This section shows the front end validation errors --%>
 
				<div class="serviceError" id="errorDiv" style="display: none;">
				</div>
			<%-- End of front end validations --%>
			<%-- This section shows the server side validation errors --%>
		<spring:hasBindErrors name="manageAssetForm">
			<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
					<li><strong><spring:message code="${error.code}" /></strong></li>
				</c:forEach>
			</div>
		</spring:hasBindErrors>
		<spring:hasBindErrors name="fileUploadForm">
				<div class="error" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
			<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
			<div id="jsValidationErrors" class="error" style="display: none;"></div>	
	<div id="validationErrors" class="error" style="display: none;"></div>
		<%--End of server side validation errors --%>
			<%--Form Bean is manageAssetForm instance of ManageAssetForm - --%>
		<form:form commandName="manageAssetForm" method="post" id="addAssetForm">
        <%-- Below section is for the prev sr no and update flag binding--%>
				<form:hidden path="prevSrNo" id="prevSrNo"/>


			<%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
				Additional Information of the Change Mgmt Request --%>
			<jsp:include page="/WEB-INF/jsp/common/commonContactInfo.jsp" />
			  <hr class="separator" />
        <p class="info"><spring:message code="requestInfo.cm.manageAsset.heading.assetInfoHeader"/></p>
        <span class="margin-left-1per">[<span class="req">*</span> <spring:message code="requestInfo.assetInfo.note"/>]</span>
        <div class="portletBlock">
          <div class="columnsTwo">
           <div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.cm.manageAsset.heading.assetIdentifiers"/></h4>
						<ul class="form wordBreak">
							<li>
								<label for="serialNumber"><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label> 
								<span>
									<form:input id="serialNumber" onchange="getProductModel('normalFlow');" path="assetDetail.serialNumber"/>
									<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.serialNo"/>" >
								</span> 
								
							</li>
							<li><label for="productName"><spring:message code="requestInfo.assetInfo.label.productName"/></label> 
							<span id="showProductModel" class="showProductModel"><select id="productType" onchange="showEnterProduct();"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span>
							<span id="productModelLoading" class="treeLoading productModelLoading" ><img src="<html:imagesPath/>loading-icon.gif"/></span>	
							<span class="span-style2"><img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.Product"/>" ></span>
 							</li><!--Changes for CI7 BRD 14-06-07 -->
							<li id="showEnterProductLI" style="display: none;">
							<label><spring:message code="requestInfo.heading.enterProductModel"/></label> 
							<span><input type="text" id="enterProduct"></input></span>
							</li>
							<li><label for="installDate"><spring:message code="requestInfo.assetInfo.label.installDate"/></label> 
							<%--<c:choose>
							<c:when test="${fleetManagementFlag eq 'true'}">--%>
							<span><input id="installDate" value="<util:dateFormat  value="${manageAssetForm.assetDetail.installDate}" timezoneOffset="${timezoneOffset}">
                						</util:dateFormat>" class="w100" maxlength="10" onchange="shwDateCommon('installDate','installDateDelete');" onblur="shwDateCommon('installDate','installDateDelete');"/>
							
							
								<%-- Commented out because of defect# 1729 
								<img src="<html:imagesPath/>ic_cal.png" title="Select a Date" width="23" height="23"
								onclick="showCal('installDate', convertDateToString(new Date().addDays(null)), null, false);" /> --%>
								
								 <img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.date"/>" width="23" height="23"
								onclick="showCal('installDate', null, null, false);" />
								
								<img id="installDateDelete" class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" onClick="removeDateCommon('installDate', 'installDateDelete');"/>
																
								</span>
							<%-- 	</c:when>
								<c:when test="${fleetManagementFlag ne 'true'}"> --%>
								<span style="display:none;"><%--<input id="installdatehidden"  value="${manageAssetForm.assetDetail.installDate}" /> --%>
                						<form:input id="installdatehidden"
						path="assetDetail.installDate" />
                						
                						</span>
                						<%-- </c:when>
                						</c:choose>--%></li>
							<li><label for="ipadd"><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label><span> <form:input id="ipAddress" path="assetDetail.ipAddress"  maxlength="40"/> <img
								class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.ipAdd"/>"></span></li>
							<li><label for="hostName"><spring:message code="requestInfo.assetInfo.label.hostName"/></label><span> <form:input	id="hostName" path="assetDetail.hostName" maxlength="50" /> <img
								class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.hostName"/>"></span></li>
							<li><label for="custTag"><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
							<span><form:input id="customerAssetTag" path="assetDetail.deviceTag" maxlength="50"/>
							<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.custTag"/>" /></span></li>
						</ul>
					</div>
			  
			  
            </div>
			
			<div class="columnsTwo">
           <div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.cm.manageAsset.heading.billingDetails"/></h4>
						<ul class="form wordBreak">
							<li><label for="assetCost"><spring:message code="requestInfo.assetInfo.label.assetCostCenter"/></label> <span><form:input
									id="assetCostCenter" path="assetDetail.assetCostCenter" maxlength="200" /> <img
								class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.costCenter"/>" ></span></li>
						</ul>
					</div>
			  		  
            </div>
			
			<div class="columnsTwo" id="defaultHiererchy">
					<div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.cm.manageAsset.heading.customerHierercyLevel"/></h4>
						<ul class="form wordBreak">
							<li><label class="link"><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/></label> <a id="chlTreeLink" href="${showCHLTreePopUp}"
								onclick="showOverlay();return popUpChlTree();" title="<spring:message code="tree.label.customerHierarchy"/>">
								<spring:message code="requestInfo.cm.manageAsset.link.selectHeirerchyLevel"/></a></li>
						</ul>
					</div>
				</div>

				<%--CHL Information After Selecting from the Hierarchy--%>
				<div class="columnsTwo" id="changeHiererchy" style="display: none;">
					<div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.cm.manageAsset.heading.customerHierercyLevel"/></h4>
						<ul class="form wordBreak">
							<li><label class="link"><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/></label> <span id="chlNodeValueLabel">${manageAssetForm.assetDetail.chlNodeValue}</span>
								<span class="spaceClear"></span> <a id="chlTreeLink"
								href="${showCHLTreePopUp}" onclick="showOverlay();return popUpChlTree();"
								title="Customer Hierarchy"><spring:message code="requestInfo.cm.manageAsset.link.changeHeirerchyLevel"/></a></li>
						</ul>

						<span style="display: none"> <form:input id="chlNodeId"
								path="assetDetail.chlNodeId" /> <form:input id="chlNodeValue"
								path="assetDetail.chlNodeValue" />
						</span>

					</div>
				</div>
			
			
			
          </div>
		  
		  <jsp:include page="/WEB-INF/jsp/common/commonPageCount.jsp" />
         
		  
     
		 <div class="infoBox columnInner rounded shadow">
		 <div class="columnsTwo">
		 <ul class="form installCheck">
							<li>
								 <label><%-- 
								 changed against defect# 1730
								 <spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAsset"/> --%>  
								 <spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDevice"/>
								 <span class="req">*</span> 
								 </label> 
								 <span class="radio radio_confirm">
								 <form:radiobutton name="installAsset" path="installAssetFlag" onclick="showDeinstall('installYes');" value="yes" id="installAssetYes"/>
								 <label>
								 <spring:message code="requestInfo.option.yes"/> </label> 
								 <form:radiobutton name="installAsset" path="installAssetFlag" onclick="showDeinstall('installNo');" value="no" id="installAssetNo"/>
								 <label>
								 <spring:message code="requestInfo.option.no"/></label>
								 </span>
							</li>

						</ul>
					</div>
				 </div>
     <jsp:include page="/WEB-INF/jsp/changemanagement/manageAsset/installDeinstall.jsp" />
		
		<jsp:include page="/WEB-INF/jsp/common/commonInstallInfo.jsp" />
		
		<jsp:include page="/WEB-INF/jsp/changemanagement/manageAsset/commonAssetContact.jsp" />
			
         
        <!-- Add Attachments BLOCK - Start -->
        
		<input type="hidden" id="fileCount" />
		<span style="display: none;">
		<input type="text" name="pageCountTypeID" id="pageCountTypeID" />
        <input type="text" name="pageCountsDateID" id="pageCountsDateID" />
        <input type="text" name="pageCountValueID" id="pageCountValueID" />
        <input type="text" name="attachmentDescriptionID" id="attachmentDescriptionID" />
        <form:input id="productModel" path="assetDetail.productLine" />
        <form:input id="deInstallProductModel" path="assetDetail.deinstModel" />
        </span>
        
		
		</form:form>
		<p class="inlineTitle"><spring:message code="requestInfo.label.attachFiles"/></p>
		
        <jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" />
       
        </div>
        <!-- Add Attachments BLOCK - End --> 
       <div class="buttonContainer">
       <c:if	test="${fleetManagementFlag == true }">
					<button class="button_cancel" onclick="onCancelClick();"
						type="button"><spring:message code="button.back"/></button>
						</c:if>
						<c:if	test="${fleetManagementFlag != true }">
						<button class="button_cancel" onclick="javascript:backToSelect();"
						type="button"><spring:message code="button.back"/></button>
						</c:if>
						<c:if	test="${fleetManagementFlag == true }">
					<button class="button_cancel" type="button"
						onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
						</c:if>
						<c:if	test="${fleetManagementFlag != true }">
						<button class="button_cancel" type="button"
						onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWASSET%>','<%=LexmarkSPOmnitureConstants.ADDASSETCANCEL%>');"><spring:message code="button.cancel"/></button>
						
						</c:if>
					<button class="button" type="button" id="btnContinue"><spring:message code="button.continue"/></button>
				</div>
      </div>
      <!-- MAIN CONTENT END --> 
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
						//var lat=document.getElementById("installAddresslatitude").value;
					
					//var lon=document.getElementById("installAddresslongitude").value;
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
  var pageType = "addAsset";
  var xCo; 
  var yCo;
  jQuery(document).ready(function(){
	  if(jQuery('#installAssetYes').is(':checked')){
		  showDeinstall("installYes");
		  if(jQuery('#deInstallAssetYes').is(':checked')){
			  showDeinstall("deInstallYes");			  
			}else if(jQuery('#deInstallAssetNo').is(':checked')){
				showDeinstall("deInstallno");
			}
		}else if(jQuery('#installAssetNo').is(':checked')){
			showDeinstall("installNo");
		}
	   		
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

	  if(jQuery('#installDate').val()==''){
	  	jQuery('#installDateDelete').hide();
	  }
	  
	 jQuery('#attachmentDescription').val('${manageAssetForm.assetDetail.notes}');
	 <c:if test="${manageAssetForm.assetDetail.deinstSerialNumber != null && manageAssetForm.assetDetail.deinstSerialNumber !=''}">
	  getProductModel("deInstallBackFlow");
	 </c:if>
	  <c:if test="${manageAssetForm.assetDetail.serialNumber != null && manageAssetForm.assetDetail.serialNumber !=''}">
	  getProductModel("backFlow");
	  if(jQuery('#attachmentDescription').val() != '' && jQuery('#attachmentDescription').val() != null){
			jQuery('#showAttachment').show();
		}
	  </c:if>
	  var pageCountTypeListArray= new Array(); 
	  var pageCountsDateListArray= new Array(); 
	  var pageCountValueListArray= new Array(); 
	  <c:if test="${manageAssetForm.assetDetail.pageCounts != null && manageAssetForm.assetDetail.pageCounts !=''}">
      var i=0;   	 
	  <c:forEach items="${manageAssetForm.assetDetail.pageCounts}" var="item">
	  pageCountTypeListArray[i]= '${item.name}';
	  pageCountsDateListArray[i]= '${item.date}';
	  pageCountValueListArray[i]= '${item.count}';
	  i++;
	  </c:forEach>
		for(j=0;j<pageCountTypeListArray.length;j++){
			//if(j>4){		  
			//	  pageCountsGrid.enableAutoHeight(false);
			//  }
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
		//var secondRow="<div class=\"w160 floatL\"><input type=\"text\" value=\""+ pageCountsDateListArray[j] +"\" readonly=\"true\" style=\"float:left;\" id=\"rwid_"+ j +"\" class=\"w150\" /><img id=\"img\" class=\"cal_date\" src=\"" + "/LexmarkServicesPortal/images/ic_cal.png" + "\" onClick=\"showCal('rwid_"+ j +"' , 'Date', 'Date', true);\" onFocus=\"this.className='';\"></div>"
		var secondRow="<div class=\"w160 floatL\"><input type=\"text\" value=\""+ pageCountsDateListArray[j] +"\" readonly=\"true\" style=\"float:left;\" id=\"rwid_"+ j +"\" class=\"w150\" onchange=\"shwDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" onblur=\"shwDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" /><img id=\"img\" class=\"cal_date ui_icon_sprite calendar-icon\" src=\"" + "<html:imagesPath/>transparent.png" + "\" onClick=\"showCal('rwid_"+ j +"' , 'Date', 'Date', true);\" onFocus=\"this.className='';\"><img id=\"pageCountsDateDelete"+ j +"\" class=\"ui_icon_sprite ui-icon-closethick\" src=\"<html:imagesPath/>transparent.png\" onClick=\"removeDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" /></div>";
		var thirdRow="<input type=\"text\" value=\""+ pageCountValueListArray[j] +"\" id='newCount_"+j+"' class=\"w150\"/>";
		var forthRow="<a href=\"javascript:deleteGridRow("+j+");\"><img class=\"ui_icon_sprite trash-icon\" style=\"float:none;\" src=\'"+"/LexmarkServicesPortal/images/transparent.png"+"'  width=\"15\" height=\"15\"/>";
		//pageCountsGrid.addRow(pageCountsGrid.uid(), [firstRow,secondRow,thirdRow,forthRow],pageCountsGrid.getRowsNum());
		jQuery('#pageCountsTable tbody').append("<tr id=\"rowCount_"+j+"\""+" class=\"rowspace\">"+
							"<td>" +firstRow +"</td>"+"<td>"+secondRow +"</td>"+
							"<td>"+thirdRow +"</td>"+"<td>"+forthRow +"</td>"+"</tr>");
		addAlternateClass();
		if(jQuery("#rwid_"+ j).val()=='') {
			jQuery("#pageCountsDateDelete"+ j).hide();
			}
		}
		id = jQuery('#pageCountsTable tbody').children('tr').length;
		
	  </c:if>
	  jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>');
	  	
	  var addAssetConfirmUrl="${addAssetConfirmationUrl}"+"&timeZoneOffset=" + timezoneOffset;
	  
	  jQuery("#installStoreFrontNameLbl").html(document.getElementById("installStoreFrontName").value);
		document.getElementById("installAddressLine1Lbl").innerHTML=document.getElementById("installAddressLine1").value;
		jQuery("#installAddressofficeNumberLbl").html(document.getElementById("installAddressofficeNumber").value);
		if(document.getElementById("installAddressLine2").value !="" && document.getElementById("installAddressLine2").value !=null){
			jQuery("#installAddressLine2Lbl").html(document.getElementById("installAddressLine2").value + ',');
			jQuery("#installAddressLine2Lbl").show();
			}
		if(document.getElementById("installAddressCity").value!='' && document.getElementById("installAddressCity").value != null){
			jQuery("#installAddressCityLbl").html(document.getElementById("installAddressCity").value);
			}
		if(document.getElementById("installAddresscounty").value!='' && document.getElementById("installAddresscounty").value != null){
			jQuery("#installAddresscountyLbl").html(',&nbsp;'+document.getElementById("installAddresscounty").value);
			jQuery("#installAddresscountyLbl").show();
		}
		if(document.getElementById("installAddressstateCode").value!='' && document.getElementById("installAddressstateCode").value != null){
			jQuery("#installAddressStateLbl").html(',&nbsp;'+document.getElementById("installAddressstateCode").value);
		}
		if((document.getElementById("installAddressProvince").value!='' && document.getElementById("installAddressProvince").value!=' ') && document.getElementById("installAddressProvince").value != null){
			jQuery("#installAddressProvinceLbl").html(',&nbsp;'+document.getElementById("installAddressProvince").value);
			jQuery("#installAddressProvinceLbl").show();
		}
		// region changed to district for MPS 2.1
		if((document.getElementById("installAddressdistrict").value!=' ' && document.getElementById("installAddressdistrict").value!='') && document.getElementById("installAddressdistrict").value != null){
			jQuery("#installAddressRegionLB").html(',&nbsp;'+document.getElementById("installAddressdistrict").value);
			jQuery("#installAddressRegionLB").show();
		}
		jQuery("#installAddressPostCodeLbl").html(document.getElementById("installAddressPostCode").value);
		jQuery("#installAddressCountryLbl").html(document.getElementById("installAddressCountry").value);
		if((document.getElementById("installAddressProvince").value=='' || document.getElementById("installAddressProvince").value==' ') || document.getElementById("installAddressProvince").value == null){
			jQuery("#installAddressProvinceLbl").hide();
		}
		if(document.getElementById("installAddresscounty").value=='' || document.getElementById("installAddresscounty").value == null){
			jQuery("#installAddresscountyLbl").hide();
		}
		// region changed to district for MPS 2.1
		if((document.getElementById("installAddressdistrict").value=='' || document.getElementById("installAddressdistrict").value==' ') || document.getElementById("installAddressdistrict").value == null){
			jQuery("#installAddressRegionLB").hide();
		}
		if(document.getElementById("installAddressLine2").value=='' || document.getElementById("installAddressLine2").value == null){
			jQuery("#installAddressLine2Lbl").hide();
		}
		//Added for MPS 2.1
		jQuery('#installAddressofficeNumberLbl').html(jQuery('#installAddressofficeNumber').val());
		//Ends
		
		//seting contacts
		//jQuery('#siteNameLabel').html(jQuery('#siteFirstName').val() + jQuery('#siteLastName').val());
		//jQuery('#siteWorkPhoneLabel').html(jQuery('#siteWorkPhone').val());
		//jQuery('#siteEmailAddressLabel').html(jQuery('#siteEmailAddress').val());
		jQuery(':input').bind('mousedown focus',function(){
			jQuery(this).removeClass('errorColor');
			});
		jQuery("#btnContinue").click(function(){
			callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWASSET%>','<%=LexmarkSPOmnitureConstants.ADDASSETCONTINUE%>');
			jQuery("#errorDiv").html('');
			var k=0;
			var pageCountType = new Array();
			var pageCountsDate = new Array();
			var pageCountValue = new Array();
			for(i=0;i<=id;i++){
				
				var idGeneratePT="select_id_"+i;
				var idGenerateD="rwid_"+i;
				var idGenerateV="newCount_"+i;
				if(jQuery("#"+idGeneratePT).val() != '') {
				if(document.getElementById(idGeneratePT) != null && document.getElementById(idGenerateD)!=null &&  document.getElementById(idGenerateV)!=null){
				pageCountType[k]=jQuery("#"+idGeneratePT).val();
				pageCountsDate[k]=jQuery("#"+idGenerateD).val();
				pageCountValue[k]=jQuery("#"+idGenerateV).val();
				k++;
				}
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
			}*/
			
			
			
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
			
			if(jQuery("#installDate").val()!=''&&jQuery("#installDate").val()!=null){
				jQuery("#installdatehidden").val(jQuery("#installDate").val());	
			}
			//checking for any blank fields installDate
			
			if(checkForFields()==false){
				
				return false;
			}
			if(decomissionFieldCheck()==false){				
				return false;
			}
			
			if(pageCountValidate()==false) {
					//jQuery('#errorDiv').append('<li><strong><spring:message code="validation.asset.add.properPageCount"/></strong></li>');
				
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				return false;
			}
			if( dublicatePCCheck(pageCountType) == false){
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				return false;
			}
			if(bindDeviceContact()==false) {
				//alert('duplicateContactType='+duplicateContactType+' emptyContact='+emptyContact);
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
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				return false;
			}
			
			var elementIdsToValidate=["serialNumber","ipAddress","deinstallipAddress","hostName","customerAssetTag"];
			  
			  	  	validationflag=false;
					for(i=0;i<elementIdsToValidate.length;i++){
						if((jQuery('#'+elementIdsToValidate[i]).val()).length>0){
						
							var patRes=validate(elementIdsToValidate[i],jQuery('#'+elementIdsToValidate[i]).val());
							
							if (patRes!=true)
							{
								validationflag=true;
								jQuery('#'+elementIdsToValidate[i]).addClass('errorColor');
								jQuery("#errorDiv").append('<li class="portlet-msg-error"><strong>'+patRes+'</strong></li>');	
								jQuery('#errorDiv').show();
								jQuery(document).scrollTop(0);
								return false;
							}
						}
					}
					
					<%--Changed for LBS --%>
					var lbsaddressflagins=jQuery("#installLBSAddressFlag").val();
					var installLevelOfDetails = $.trim(jQuery("#installLevelOfDetails").val()).toLowerCase();
					
					if(lbsaddressflagins=="true" && installLevelOfDetails.match("floor level|grid level|mix - see floor")){
												
						if(jQuery('#physicalLocation1h').val() == ''||jQuery('#physicalLocation1h').val() == null ){
							validationflag=true;
							jQuery("#errorDiv").show();
							jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.install.building'/></strong></li>");
							jQuery(document).scrollTop(0);
							return false;
						}
						if(jQuery('#physicalLocation2h').val() == ''||jQuery('#physicalLocation2h').val() == null){
							//alert("inside floor"+jQuery('#flr1').val());
							validationflag=true;
							jQuery("#errorDiv").show();
							jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.label.install.floor'/></strong></li>");
							jQuery(document).scrollTop(0);
							return false;
						}
					}
					
				
				for(var j=0; j<=pageCountsDate.length; j++){	
					if(pageCountsDate != null && pageCountsDate !=""){
					if(pageCountsDate[j] != null){				
					var currentDate = new Date(formatDateToDefault(pageCountsDate[j])).toUTCString();
					if(new Date(currentDate)>new Date(new Date().toUTCString())){					
						jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.currentDateExceed'/> " 
								+(j+1)+ "</strong></li>");
						jQuery('#errorDiv').show();
						jQuery(document).scrollTop(0);
						return false;
					}
						}
					}
				}
					if(false){
						jQuery("#errorDiv").show();
						jQuery(document).scrollTop(0);
							return false;
					}
					else{
								
						jQuery('#pageCountTypeID').val(pageCountType);
						jQuery('#pageCountsDateID').val(pageCountsDate);
						jQuery('#pageCountValueID').val(pageCountValue);
						//alert(jQuery('#attachmentDescription').val());
						jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
						if(jQuery('#productType :selected').val()=="other"){
							jQuery('#productModel').val(jQuery('#enterProduct').val());
							}else{
								jQuery('#productModel').val(jQuery('#productType :selected').val());
								}
						
						if(jQuery('#deinstallProductType :selected').val()=="other"){
							jQuery('#deInstallProductModel').val(jQuery('#enterDeinstallProduct').val());
							}else{
								jQuery('#deInstallProductModel').val(jQuery('#deinstallProductType :selected').val());
								}
						if(jQuery('#deInstallAssetNo').is(':checked')){
							jQuery('#deInstallSerialNumber').val("");
							jQuery('#deInstallProductModel').val("");
							jQuery('#deinstallAssetTag').val("");
							jQuery('#deinstallipAddress').val("");
							jQuery('#deinstallbrand').val("");
							jQuery('#deinstallpartNo').val("");
							jQuery('#deinstallhostName').val("");
							jQuery('#deinstallcomment').val("");
					}
													
						jQuery("#installFloorLevelOfDetails").val($("#flr option:selected").attr("lod"));
						jQuery("#addAssetForm").attr("action", addAssetConfirmUrl);
						jQuery("#addAssetForm").submit();
						
					}	
					
		});
		
		if("${manageAssetForm.assetDetail.chlNodeValue}"!="")
		{	
			showHiererchy();
		}
		else hideHiererchy();
  });
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
					if(jQuery("#chlNodeValue").val()!=""){
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
	function showHiererchy() {
		
		jQuery('#defaultHiererchy').hide();
		jQuery('#changeHiererchy').show();
	}
	function hideHiererchy() {
		
		jQuery('#defaultHiererchy').show();
		jQuery('#changeHiererchy').hide();
	}
	function checkForFields(){
		
		 var flagnotBlank=false;
		 jQuery('#custReferenceId,#costCenter,#addtnlDescription,#effDtOfChange,#serialNumber,#productName,#installDate,#ipAddress,#hostName,#customerAssetTag,#assetCostCenter,#lastPageCount,#lastPageReadDate,#lastColorPageCount,#lastColorPageReadDate').each(
				 function(){
					 if(jQuery(this).val()!=""){
						 flagnotBlank=true;
					 	}
					 }
				 );
		 jQuery('#consumablesFirstNameLabel,#installedAddressName,#chlNodeValueLabel').each(
			 function(){
				 	if(jQuery(this).html()!=""){
					 	flagnotBlank=true;
				 	}
				 }
			 );
var emptyFlag=false;
var checkFlag=false;
		 if(flagnotBlank==false){
			 emptyFlag=true;
			 }
		 
		 if(!jQuery('#installAssetYes').is(':checked') && !jQuery('#installAssetNo').is(':checked')){
			 checkFlag=true;
			 }
		 
			if(emptyFlag && checkFlag){
				jQuery('#errorDiv').append("<li class=\"portlet-msg-error\"><strong><spring:message code='validation.asset.add.nodata'/></strong></li>");
				jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.Asset.installAssetFlag.format.errorMsg'/>" 
						+ "</strong></li>");
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				return false;
			}else if(emptyFlag && !checkFlag){
				jQuery('#errorDiv').append("<li class=\"portlet-msg-error\"><strong><spring:message code='validation.asset.add.nodata'/></strong></li>");
				if((jQuery('#installAssetYes').is(':checked')) && (jQuery('#installAddressLine1').val() == '' && jQuery('#installAddressCity').val() == '' && jQuery('#installAddressState').val() == '' && jQuery('#installAddressCountry').val() == '' && jQuery('#physicalLocation1').val()=='' && jQuery('#physicalLocation2').val()=='' && jQuery('#physicalLocation3').val()=='')){
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.installAddressEmpty'/>" 
					+ "</strong></li>");
					}
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				return false;
			}else if(!emptyFlag && checkFlag){
				jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.Asset.installAssetFlag.format.errorMsg'/>" 
						+ "</strong></li>");
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				return false;
			}
		 
		 if((jQuery('#installAssetYes').is(':checked')))
			{
			if(jQuery('#installAddressLine1').val() == '' && jQuery('#installAddressCity').val() == '' && jQuery('#installAddressState').val() == '' && jQuery('#installAddressCountry').val() == '' && jQuery('#physicalLocation1').val()=='' && jQuery('#physicalLocation2').val()=='' && jQuery('#physicalLocation3').val()==''){
				jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='requestInfo.validation.installAddressEmpty'/>" 
				+ "</strong></li>");
				jQuery('#errorDiv').show();
				jQuery(document).scrollTop(0);
				return false;
				}else if(!jQuery('#deInstallAssetNo').is(':checked') && !jQuery('#deInstallAssetYes').is(':checked')){
							jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.deInstallAssetFlag.format.errorMsg'/>"+ "</strong></li>");
							jQuery('#errorDiv').show();
							jQuery(document).scrollTop(0);
							return false;
					}else{
						return true;
					}
		}
		 
		 	
	}
	 function getProductModel(flowType){
		 var srNumber;
		 if (flowType=="normalFlow" || flowType=="backFlow"){
			 jQuery('#enterProduct').val('');
			 jQuery('#productModelLoading').show();
			 jQuery('#showEnterProductLI').hide();
			 srNumber=jQuery('#serialNumber').val();
			 productTypeCall(flowType,srNumber);
		 }
		 if (flowType=="normalDeinstallFlow" || flowType=="deInstallBackFlow"){
			 jQuery('#enterDeinstallProduct').val('');
			 jQuery('#deinstallProductModelLoading').show();
			 jQuery('#enterDeinstallProduct').hide();
			 srNumber=jQuery('#deInstallSerialNumber').val();
			 productTypeCall(flowType,srNumber);
		 }		 
			
	 }
	 
	 function productTypeCall(flowType,srNumber){
		 var paymentTypeURL="<portlet:resourceURL id="getProductTypes"/>";
			jQuery.ajax({
			url:paymentTypeURL,
			data:{
				serialNumber:srNumber,
				flowType:flowType,
				 },
			type:'POST',
			dataType: 'html',
			success: function(results){
				try{
					if(results !=null){
						if (flowType=="normalFlow" || flowType=="backFlow"){
							jQuery('#productModelLoading').hide();
						jQuery('#showProductModel').html(results);
						if(jQuery('#productType :selected').val()=="other"){
							jQuery('#showEnterProductLI').show();
							if(flowType=="backFlow"){
							 <c:if test="${manageAssetForm.assetDetail.productLine != null && manageAssetForm.assetDetail.productLine !=''}">
							 jQuery('#enterProduct').val('${manageAssetForm.assetDetail.productLine}');
							 </c:if>
							}
						}else{							
							jQuery('#showEnterProductLI').hide();
							if(flowType=="backFlow"||window.location.href.indexOf('/fleet-management')!= -1){
							<c:if test="${manageAssetForm.assetDetail.productLine != null && manageAssetForm.assetDetail.productLine !=''}">
							jQuery('#productType').val('${manageAssetForm.assetDetail.productLine}');							
							</c:if>
							}
							}
						}
					 if (flowType=="normalDeinstallFlow" || flowType=="deInstallBackFlow"){	
						 jQuery('#deinstallProductModelLoading').hide();
						 jQuery('#showDeinstallProductModel').html(results);
							if(jQuery('#deinstallProductType :selected').val()=="other"){
								jQuery('#enterDeinstallProduct').show();
								jQuery('#showDeinstallProductModel').hide();								
								if(flowType=="deInstallBackFlow"){
									alert("${manageAssetForm.assetDetail.deinstModel}");
								 <c:if test="${manageAssetForm.assetDetail.deinstModel != null && manageAssetForm.assetDetail.deinstModel !=''}">
								 jQuery('#enterDeinstallProduct').val('${manageAssetForm.assetDetail.deinstModel}');
								 </c:if>
								}
							}else{
								jQuery('#enterDeinstallProduct').hide();
								jQuery('#showDeinstallProductModel').show();
								if(flowType=="deInstallBackFlow"||window.location.href.indexOf('/fleet-management')!= -1){
								<c:if test="${manageAssetForm.assetDetail.deinstModel != null && manageAssetForm.assetDetail.deinstModel !=''}">
								jQuery('#deinstallProductType').val('${manageAssetForm.assetDetail.deinstModel}');
								
								</c:if>
								}
								}
					}	
				}
				}catch(e){
					//alert("in else");
					}
				
			}
		});
	 }
	 function showEnterProduct(){
		 if(jQuery('#productType :selected').val()=="other"){
			 jQuery('#showEnterProductLI').show();
		}else{
			jQuery('#showEnterProductLI').hide();
			}

		 }
	 function backToSelect() {
			callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWASSET%>','<%=LexmarkSPOmnitureConstants.ADDASSETBACK%>');
			jConfirm("<spring:message code='common.back.alert'/>", "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					window.location.href="manageassets";
				}else{
					return false;
					}
			});
		}

/* Added for CI7 BRD14-02-12 */
 ajaxSuccessFunction=function updateRequest(){
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);					
}
 /* END */
  </script>
