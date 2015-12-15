<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@page import="java.util.Map"%>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<jsp:include page="/WEB-INF/jsp/common/validationMPS.jsp" />

<!-- Added for CI7 BRD14-02-12 -->
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<!-- END -->

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%--CI BRD 13-10-08 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<script type="text/javascript"><%@ include file="/js/commonAddress.js"%></script>

<portlet:actionURL var='attachDocumentAction'><portlet:param name='action' value='attachDocument' /></portlet:actionURL>
<portlet:actionURL var='removeDocumentAction'><portlet:param name='action' value='removeDocument' /></portlet:actionURL>
<%--<portlet:actionURL var='confirmRequestAction'><portlet:param name='action' value='confirmRequest' /></portlet:actionURL>--%>
<portlet:actionURL var='manageReturnsReviewActionUrl'><portlet:param name='action' value='showManageReturnsReview' /></portlet:actionURL>

<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>

<script type="text/javascript">
 portlet_name="<%=LexmarkSPOmnitureConstants. RETURNSUPPLIES %>";
 action_primary="<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESSELECTDIFFERENTCONTACT %>";
 action_secondary="<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESADDADDITIONALCONTACT %>";
var dialog;
//var fileNameVar = null;
var contactListType="";
var errorList = "";


function uploadFile() {
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESATTACH%>');
	
	var fileCount = document.getElementById("fileCount").value;
	
	var validFlag = 1;

	var fileNameLocal = document.getElementById("txtFilePath").value;
	if(fileNameLocal == ""){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		//showError('<spring:message code="changemanagement.manageCHLOthers.validation.attachmentEmpty"/>', "jsValidationErrors", false);
		//window.scrollTo(0,0);
		errorList ="<li><strong><spring:message code='requestInfo.label.validation.attachmentEmpty'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	if(validFlag==1){
		
		var link="<%=attachDocumentAction %>";
		document.getElementById('fileUploadFormId').target = 'upload_target';
		document.getElementById('fileUploadFormId').action = link;
		jQuery('#fileUploadFormId').submit();

	}	
}

function removeFile(fileName) {
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESDELETEFILE%>');
	//alert("fileName::"+fileName);
	var link="<%=removeDocumentAction %>";
	//alert(link);
	document.getElementById('fileUploadFormId').target = 'upload_target';
	document.getElementById('fileUploadFormId').action = link + "&fileName=" + fileName;
	//alert("hello "+document.getElementById('fileUploadFormId').action);
	jQuery('#fileUploadFormId').submit();
}

function submitRequest(){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESCONTINUE%>');
	//alert(document.getElementById("requestedEffectiveDate").value);
	errorList = "";
	validateFields();
	
	if(errorList != ""){
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		window.parent.document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		
	}else{
		var link="<%=manageReturnsReviewActionUrl %>";
		//alert(link);	
		document.getElementById('manageReturnsFormId').action = link +"&forward=Others"+"&timeZoneOffset=" + timezoneOffset;
		
		document.getElementById('manageReturnsFormId').submit();
		showOverlay();
	}
	
}

function setPathToTextBox()
{
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESBROWSE%>');
	filename=jQuery('#file').val();
	if(filename.match(/fakepath/)) {
		filename = filename.replace(/C:\\fakepath\\/i, '');
	}
	jQuery('#txtFilePath').val(filename);
	//fileNameVar = filename;
}

function openTemplate(type,fileName){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESFILEDOWNLOAD%>');
	if(type=='Attachment'){
		window.location="${chlTemplateURL}&fileType=Attachment&fileName="+fileName;
	}
	
}

jQuery(document).ready(function() {
	var currentURL = window.location.href;
	var change_account=currentURL.indexOf('/partner-portal');
	if(currentURL.indexOf('/partner-portal') != -1)
	{	
		jQuery('#topnavigation li a').each(function(){
			  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
			  jQuery(this).parent().addClass('selected');
			  });
	}
// 		Change Account Link Hide/Show for CI-7 Defect #12274
		if(currentURL.indexOf('/partner-portal') == -1)
		  {	
			  jQuery('#changeaccount').show();
		  }
		else
			{
			jQuery('#changeaccount').hide();
			}
	//Added for CI BRD13-10-08 --STARTS
	jQuery("#pickupStoreFrontNameLbl").html(document.getElementById("pickupStoreFrontName").value);
	document.getElementById("pickupAddressLine1Lbl").innerHTML=document.getElementById("pickupAddressLine1").value;
	jQuery("#pickupAddressofficeNumberLbl").html(document.getElementById("pickupAddressofficeNumber").value);
	if(document.getElementById("pickupAddressLine2").value !="" && document.getElementById("pickupAddressLine2").value !=null){
		jQuery("#pickupAddressLine2Lbl").html(document.getElementById("pickupAddressLine2").value + ',');
		jQuery("#pickupAddressLine2Lbl").show();
		}
	if(document.getElementById("pickupAddressCity").value!='' && document.getElementById("pickupAddressCity").value != null){
		jQuery("#pickupAddressCityLbl").html(document.getElementById("pickupAddressCity").value);
		}
	if(document.getElementById("pickupAddresscounty").value!='' && document.getElementById("pickupAddresscounty").value != null){
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+document.getElementById("pickupAddresscounty").value);
		jQuery("#pickupAddresscountyLbl").show();
	}
	if(document.getElementById("pickupAddressstateCode").value!='' && document.getElementById("pickupAddressstateCode").value != null){
		jQuery("#pickupAddressStateLbl").html(',&nbsp;'+document.getElementById("pickupAddressstateCode").value);
	}
	if((document.getElementById("pickupAddressProvince").value!='' && document.getElementById("pickupAddressProvince").value!=' ') && document.getElementById("pickupAddressProvince").value != null){
		jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+document.getElementById("pickupAddressProvince").value);
		jQuery("#pickupAddressProvinceLbl").show();
	}
	if((document.getElementById("pickupAddressdistrict").value!=' ' && document.getElementById("pickupAddressdistrict").value!='') && document.getElementById("pickupAddressdistrict").value != null){
		jQuery("#pickupAddressRegionLB").html(',&nbsp;'+document.getElementById("pickupAddressdistrict").value);
		jQuery("#pickupAddressRegionLB").show();
	}
	jQuery("#pickupAddressPostCodeLbl").html(document.getElementById("pickupAddressPostCode").value);
	jQuery("#pickupAddressCountryLbl").html(document.getElementById("pickupAddressCountry").value);	
	if((document.getElementById("pickupAddressProvince").value=='' || document.getElementById("pickupAddressProvince").value==' ') || document.getElementById("pickupAddressProvince").value == null){
		jQuery("#pickupAddressProvinceLbl").hide();
	}
	if(document.getElementById("pickupAddresscounty").value=='' || document.getElementById("pickupAddresscounty").value == null){
		jQuery("#pickupAddresscountyLbl").hide();
	}
	if((document.getElementById("pickupAddressdistrict").value=='' || document.getElementById("pickupAddressdistrict").value==' ') || document.getElementById("pickupAddressdistrict").value == null){
		jQuery("#pickupAddressRegionLB").hide();
	}
	if(document.getElementById("pickupAddressLine2").value=='' || document.getElementById("pickupAddressLine2").value == null){
		jQuery("#pickupAddressLine2Lbl").hide();
	}
	
	jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
	//Added for CI BRD13-10-08 --ENDS
	c=1;
	var responseText = "";
	var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	window.parent.document.getElementById("fileCount").value = fileCount;
	
	<c:if test="${fileUploadForm.fileMap != null}">
		jQuery('#fileListTable',window.parent.document).empty();
		responseText = '<thead><tr><th class="w15" id="fileData"></th><th><spring:message code="attachment.message.FileName"/></th><th class="w120 right"><spring:message code="changemanagement.common.label.size"/></th></tr></thead>';
		    responseText = responseText + '<tbody>';
	 		<c:forEach items="${fileUploadForm.fileMap}" var="entry">
	 			if(c%2==0){
	 				responseText = responseText + '<tr class="altRow">';
	 			}
	 			else{
	 				responseText = responseText + '<tr>';
	 			}
	 			c++;
	 			responseText = responseText + '<td class="w15"><img class="ui_icon_sprite ui-icon-closethick" id="img_help1"  src="<html:imagesPath/>transparent.png" width="18" height="18" style="cursor:help;" title="Delete" alt="Remove" onclick=\'removeFile("${entry.key}");\' /></td>';
	 			responseText = responseText + '<td>'+ ' <a href="javascript:void(0);" onclick="openTemplate(\'Attachment\',\'${entry.key}\');"><c:out value="${entry.value.displayFileName}" /></a>' + '</td>';
	 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
	 			
	 			responseText = responseText + '</tr>';
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable',window.parent.document).append(responseText);
		
	</c:if>
	<c:if test="${fileUploadForm.fileMap == null}">
		jQuery('#fileListTable',window.parent.document).empty();
	</c:if>
	
	jQuery('#txtFilePath').val('');
	
	<c:if test="${source != null}"> 
		
		jQuery('#txtFilePath',window.parent.document).val('');
		
		jQuery('#validationErrors',window.parent.document).empty();
		window.parent.document.getElementById("validationErrors").style.display = 'none';
		window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
		if(document.getElementById("errors")!= null){
			//jQuery('#error_banner_jsValidationErrors',window.parent.document).empty();
			
			var divText = document.getElementById("errors").innerHTML;
			jQuery('#validationErrors',window.parent.document).append(divText);
			window.parent.document.getElementById("validationErrors").style.display = 'block';
			window.parent.scrollTo(0,0);
		}
		
		jQuery('#exceptionsDiv',window.parent.document).empty();
		window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
		if(document.getElementById("dispalyExceptions")!= null){
			//jQuery('#error_banner_jsValidationErrors',window.parent.document).empty();
			
			var divText = document.getElementById("dispalyExceptions").innerHTML;
			jQuery('#exceptionsDiv',window.parent.document).append(divText);
			window.parent.document.getElementById("exceptionsDiv").style.display = 'block';
			window.parent.scrollTo(0,0);
		}
		
		jQuery("#processingHint",window.parent.document).css({
			display:"none"
		});
		jQuery("#overlay",window.parent.document).css({
			display:"none" 
		});
	
	
	</c:if>

	
	//added for address
	/*
	* Add the selected address from contact list page, to hidden fields.
	* These fields will bind with the form.
	*/
	if (jQuery('#addressLine1').val() == "") {
		//alert("forward flow");
		jQuery('#addressId').val("${oldContactChange['addressId']}");
		jQuery('#addressLine1').val("${oldContactChange['addressLine1']}");
		jQuery('#addressLine2').val("${oldContactChange['addressLine2']}");
		//jQuery('#addressLine3').val("${oldContactChange['addressLine3']}");
		jQuery('#city').val("${oldContactChange['city']}");
		jQuery('#country').val("${oldContactChange['country']}");
		jQuery('#stateProv').val("${oldContactChange['stateProv']}");
		jQuery('#zipPostal').val("${oldContactChange['zipPostal']}");
		jQuery('#building').val("${oldContactChange['building']}");
		jQuery('#floor').val("${oldContactChange['floor']}");
		jQuery('#office').val("${oldContactChange['office']}");
	}

	setValuestoLabel();

	if (window.PIE) {
		//alert('window PIE inside if');
        document.getElementById("addiContactButton").fireEvent("onmove");
        /*jQuery(".button").trigger("onmove");*/
    }

});
// added
function imagePos(){
	
	document.getElementById("img_help4").style.display = 'none';
	document.getElementById("img_help1").style.display = 'inline-block';
}


/*
 * Show the selected address to page
 */
function setValuestoLabel() {
	jQuery('#addressLine1Label').html(jQuery('#addressLine1').val());
	jQuery('#addressLine2Label').html(jQuery('#addressLine2').val());
	//jQuery('#addressLine3Label').html(jQuery('#addressLine3').val());
	jQuery('#cityLabel').html(jQuery('#city').val());
	jQuery('#countryLabel').html(jQuery('#country').val());
	jQuery('#stateProvLabel').html(jQuery('#stateProv').val());
	jQuery('#zipPostalLabel').html(jQuery('#zipPostal').val());
	
}

/*
 * Open the select address popup
 */
function popupAddress(hyperlinkId){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESSELECTADDRESS%>');
	//alert('in popup address');
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	//alert(linkId);
	showOverlay();
	jQuery('#dialogAddress').load(jQuery('#'+hyperlinkId).attr('href'),function(){
	//alert(jQuery('#'+hyperlinkId).attr('href'));	
		dialog=jQuery('#content_addr').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
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

		if (window.PIE) {
	        document.getElementById("btnSelect_addr").fireEvent("onmove");
	    }
	    
		initializeAddressGrid();		
		
		});
	return false;
}

/*
 * Select the address from popup and set to from data
 */
// function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2,
// 		city,addressState,addressProvince,temp,addressCountry,addressPostCode,storeFrontName,building,floor,office,favourite)
// {
	////setValuestoForm(addressLine1,addressLine2,addressLine3,city,addressCountry,addressState,addressPostCode,building,floor,office);
// 	jQuery('#addressId').val(addressId);
// 	jQuery('#addressLine1').val(addressLine1);
// 	jQuery('#addressLine2').val(addressLine2);
	////jQuery('#addressLine3').val(addressLine3);
// 	jQuery('#city').val(city);
// 	jQuery('#country').val(addressCountry);
// 	jQuery('#stateProv').val(addressState);
// 	jQuery('#zipPostal').val(addressPostCode);
	//Changes for MPS 2.1
// 	if(addressId==null || addressId=="")
// 		jQuery('#serviceAddrisAddrCleansed').val("false");
// 	else
// 		jQuery('#serviceAddrisAddrCleansed').val("true");
	
	//ENDS Changes for MPS 2.1
// 	/*jQuery('#building').val(building);
// 	jQuery('#floor').val(floor);
	
// 	if(office.trim() !="false" && office.trim()!="true"){
// 		jQuery('#office').val(office);
// 	}*/
	
	
// 	setValuestoLabel();
	
// 	if(errorList != ""){
// 		errorList = "";
// 		validateFields();
// 		if(errorList != ""){
// 			jQuery('#jsValidationErrors',window.parent.document).empty();
// 			document.getElementById("jsValidationErrors").innerHTML = errorList;
// 			window.parent.document.getElementById("jsValidationErrors").style.display = 'block';
// 			window.parent.scrollTo(0,0);
// 		}else{
// 			jQuery('#validationErrors',window.parent.document).empty();
// 			window.parent.document.getElementById("validationErrors").style.display = 'none';
// 			window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
// 		}		
		
// 	}else{
// 		jQuery('#validationErrors',window.parent.document).empty();
// 		window.parent.document.getElementById("validationErrors").style.display = 'none';
// 		window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
// 	}
	
// 	closeDialog();
// }

<%--Changes for CI BRD 13-10-08 STARTS--%>
<%--Changes for Phase 2.1--%>
function addPickupAddress(addressId,storeFrontName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
		addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode)
<%--Ends--%>
{	
	if(addressProvince == null){
		addressProvince = '';
	}
	if(addressId==null){
		addressId = '-1';
	}
	jQuery("#pickupAddressId").val(addressId);
	jQuery("#pickupStoreFrontName").val(storefrontName);
	jQuery("#pickupAddressLine1").val(addressLine1);		
	jQuery("#pickupAddressLine2").val(addressLine2);
			
	jQuery("#pickupAddressCity").val(addressCity);
	jQuery("#pickupAddressState").val(addressState);
	jQuery("#pickupAddressProvince").val(addressProvince);
	jQuery("#pickupAddressPostCode").val(addressPostCode);
	jQuery("#pickupAddressCountry").val(addressCountry);
	<%--Changes for Phase 2.1--%>
	jQuery("#pickupAddressofficeNumber").val(officeNumber);
	jQuery("#pickupAddressdistrict").val(district);
	jQuery("#pickupAddresscounty").val(county);
	//added
	jQuery("#pickupAddressregion").val(region);
	jQuery("#pickupAddresscountryISOCode").val(countryISO);
	jQuery("#pickupAddressstateCode").val(stateCode);
	<%--Ends--%>
	//changes for MPS 2.1
	//This is for display purpose pickup address
	jQuery("#pickupStoreFrontNameLbl").html(storefrontName);
	jQuery("#pickupAddressLine1Lbl").html(addressLine1);
	jQuery("#pickupAddressofficeNumberLbl").html(officeNumber);		
	if(addressLine2 !='' && addressLine2 !=' ' && addressLine2 != null){
		jQuery("#pickupAddressLine2Lbl").html(addressLine2+",");
		jQuery("#pickupAddressLine2Lbl").show();
		}

	if(addressCity !='' && addressCity !=' ' && addressCity != null){
		jQuery("#pickupAddressCityLbl").html(addressCity);
		}
	
	if(county !='' && county !=' ' && county != null){
		jQuery("#pickupAddresscountyLbl").html(', '+county);
		jQuery("#pickupAddresscountyLbl").show();
		}
	if(stateCode !='' && stateCode !=' ' && stateCode != null){
		jQuery("#pickupAddressStateLbl").html(", "+stateCode);
		jQuery("#pickupAddressStateLbl").show();
		}
	if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
		jQuery("#pickupAddressProvinceLbl").html(', '+addressProvince);
		jQuery("#pickupAddressProvinceLbl").show();
		}
	// region changed to district fot MPS 2.1
	if(district !=' ' && district !=' ' && district != null){
		jQuery("#pickupAddressRegionLB").html(', '+district);
		jQuery("#pickupAddressRegionLB").show();
		}
	jQuery("#pickupAddressPostCodeLbl").html(addressPostCode);		
	jQuery("#pickupAddressCountryLbl").html(addressCountry);
	if(addressProvince =='' || addressProvince ==' ' || addressProvince == null){
		jQuery("#pickupAddressProvinceLbl").hide();
		}
	if(county =='' || county ==' ' || county == null){
		jQuery("#pickupAddresscountyLbl").hide();
		}
	// region changed to district for MPS 2.1
	if(district ==' '||district =='' || district == null){
		jQuery("#pickupAddressRegionLB").hide();
		}
	if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
		jQuery("#pickupAddressLine2Lbl").hide();
		}
	if(stateCode =='' || stateCode ==' ' || stateCode == null){
		jQuery("#pickupAddressStateLbl").hide();
		}
	removeErrorMessage();
	
	//changes for MPS 2.1 end
}
//Added for CI Defect # 12469
function removeErrorMessage(){
	document.getElementById('jsValidationErrors').style.display = "none";
	document.getElementById('jsValidationErrors').innerHTML = "";
	
}
	function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2, 
			addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode)
	
	{
		
		if(addressId==null || addressId=="")
			jQuery('#pickupAddressisAddressCleansed').val("false");
		else
			jQuery('#pickupAddressisAddressCleansed').val("true");
		
		
		addressChangeReq=true;
		if(linkId=="diffAddressLink")
		{
			<%--Changes for Phase 2.1--%>
			//call to addConsumablesAddress in the commonAsset.js
			addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
					addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode);
			<%--Ends--%>
		}
		closeDialog();
	}
	function addRestFieldsOfCleanseAddress(){
		jQuery('#pickupAddressisAddressCleansed').val("true");
		<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
			jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
		<%}%>
		jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
		//Start Added for MPS 2.1
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+jQuery('#pickupAddresscounty').val());
		jQuery("#pickupAddresscountyLbl").show();
		if(jQuery('#pickupAddresscounty').val() =='' || jQuery('#pickupAddresscounty').val() == null){
			jQuery("#pickupAddresscountyLbl").hide();
			}
		//jQuery("#pickupAddressdistrictLbl").html(jQuery('#pickupAddressdistrict').val());
		//END
		}
	<%--Changes for CI BRD 13-10-08 ENDS--%>
function validateFields(){
	var returnsSubArea = document.getElementById('returnsSubArea')[document.getElementById('returnsSubArea').selectedIndex].innerHTML;
	document.getElementById("returnsSubAreaValue").value=returnsSubArea;
	
	window.parent.document.getElementById("validationErrors").style.display = 'none';
	window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
	//var errorList = "";

	if(document.getElementById('returnsSubArea').value==0 ){
		errorList = "<li><strong><spring:message code='requestInfo.label.validation.subAreaMandatory'/></strong></li>";
		jQuery('#returnsSubArea').addClass('errorColor');
	}
	if(document.getElementById('notes').value == ''){
		//alert("Please fill in the notes field.");
		errorList = errorList + "<li><strong><spring:message code='requestInfo.label.validation.notesMandatory'/></strong></li>";
		jQuery('#notes').addClass('errorColor');
	}
	
	var note = document.getElementById('notes').value;
	
	if(note.length >2000){

		errorList = errorList + "<li><strong><spring:message code='validation.notes.notesMaxLength'/></strong></li>";
		
		jQuery('#notes').addClass('errorColor');
	}

	var addtnlDescription = document.getElementById('addtnlDescription').value;
	if(addtnlDescription.length >2000){
		
		errorList = errorList + "<li><strong><spring:message code='validation.desc.descriptionMaxLength'/></strong></li>";
		
		jQuery('#addtnlDescription').addClass('errorColor');
	}
	

	var building = document.getElementById('building').value;
	if(building.length >100){

		errorList = errorList + "<li><strong><spring:message code='validation.building.buildingMaxLength'/></strong></li>";
		
		jQuery('#building').addClass('errorColor');
	}
	var floor = document.getElementById('floor').value;
	if(floor.length >100){

		errorList = errorList + "<li><strong><spring:message code='validation.floor.floorMaxLength'/></strong></li>";
		
		jQuery('#floor').addClass('errorColor');
	}
	var office = document.getElementById('office').value;
	if(office.length >100){

		errorList = errorList + "<li><strong><spring:message code='validation.office.officeMaxLength'/></strong></li>";
		
		jQuery('#office').addClass('errorColor');
	}


	if(jQuery('#pickupAddressLine1').val() == "" && jQuery('#pickupAddressLine2Lbl').val() == "" && jQuery('#pickupAddressCityLbl').val() == "" && jQuery('#pickupAddressCountryLbl').val() == ""){
		errorList = errorList + "<li><strong><spring:message code='validation.addressItem.addressNotEmpty'/></strong></li>";

		}
}

/*
 * Close the address popup
 */
function closeDialog()
{
	dialog.dialog('close');
	dialog=null;
	jQuery('#dialogAddress').html("");		
}


</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Return Supplies</title>
</head>
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
      <h2 class="step"><spring:message code="ordermanagement.returns.heading.returnSupplies"/></h2> </div>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <!-- Condition Added for CI7 Defect # 12274 -->
      	<c:if test='${change_account!="-1"}'>
    		<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
      	</c:if>
        	</strong>
        	</h3>
        	</div>
        	<!-- END -->
        	
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
         <h3 class="pageTitle"><spring:message code="ordermanagement.returns.heading.returnSupplies"/><span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">*</span><spring:message code="requestInfo.label.areRequired"/></span></h3>
             
            <!-- JS Validation Errors displayed in the below html:statusBanner -->
           <div id="jsValidationErrors" class="error wFull" style="display: none;"></div>
            <!-- Validation Errors displayed in the below div -->
            <div class="wFull">
            <spring:hasBindErrors name="fileUploadForm">
				<div class="error" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
			</div>
          	<div id="validationErrors" class="error" style="display: none;"></div><!--Div used for file upload validation errors  -->
          	
          	<!-- Exceptions displayed in the below div -->
          	 <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong>Exception - ${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
          	<div id="exceptionsDiv" class="error" style="display: none;"></div>	<!--Div used for file upload exception  -->
          	
         <form:form id="manageReturnsFormId" commandName="manageReturnsForm" modelAttribute="manageReturnsForm" method="post" action="${manageReturnsReviewActionUrl}" enctype="multipart/form-data" >
		  <%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
			Additional Information of the Change Mgmt Request End--%>
			<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
	       
	        <hr class="separator" />
	        <div class="portletBlock infoBox">
	          <div class="columnsTwo">
			<!-- Changed for CI Defect # 11926 -->
	            <div class="infoBox columnInner rounded shadow">
	            <h4><span class="req">*</span><spring:message code="requestInfo.heading.details"/></h4>
	            <span style="display: none">
					<form:input path="returnsSubAreaValue" /> 
				</span>
	              <ul class="form">
	              <li>
	              	 <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/><span class="req">*</span></label>
	                    <span>
		                    <form:select path="returnsSubArea" id="returnsSubArea" multiple="false">
		                   		 <form:option value="0" >-<spring:message code="requestInfo.option.select"/>-</form:option>
		                    	 <c:forEach items="${returnsSubAreaMap}" var="entry">
		                     		<form:option value="${entry.key}">${entry.value}</form:option>
		                     	 </c:forEach>
		                    </form:select>
	                     </span>
						 <!--  Help Icon Title changed for 14.8 and 14.9 Translations -->
	                      <img id="img_help4"  src="<html:imagesPath/>transparent.png" class="helpIcon ui_icon_sprite info-icon" title="<spring:message code="requestInfo.tooltip.manageReturnsDetail.subArea"/>" />
	                 </li>
	                <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notes"/><span class="req">*</span></label>
	                  <span>
	                  	<form:textarea path="notes" id="notes" rows="4"/>
	                  </span>
	                  <em class="note w200"><spring:message code="ordermanagement.returns.label.partQuantity"/></em>
	                 
	                </li>
	              </ul>
	            </div>
	          </div>
	          <div class="columnsTwo">
	            <div class="infoBox columnInner rounded shadow">
	             <h4><a href="${addressListPopUpUrl}" title="<spring:message code="requestInfo.title.selectAddress"/> " id="diffAddressLink" onclick="return popupAddress(id);">
					<spring:message code="requestInfo.title.selectAddress"/></a> 
					<spring:message code="requestInfo.heading.returnAddress"/> <span class="req">*</span></h4>			
	              <ul class="roDisplay">
	              <!-- Changes done for CI BRD 13-10-08 STARTS-->
									<li> <div id="pickupStoreFrontNameLbl"></div> 
									<div id="pickupAddressLine1Lbl"></div>
									<div id="pickupAddressofficeNumberLbl"></div>
									<div id="pickupAddressLine2Lbl"></div>
									<div id="city_state_zip_popup_span">
									<div style="display:inline;" id="pickupAddressCityLbl"></div>
									<div  style="display:inline;" id="pickupAddresscountyLbl"></div>
									<div style="display:inline;" id="pickupAddressStateLbl"></div>
									<div style="display:inline;" id="pickupAddressProvinceLbl"></div>
									<div style="display:inline;" id="pickupAddressRegionLB"></div>
									</div>
									<div id="pickupAddressPostCodeLbl"></div>
									<div id="pickupAddressCountryLbl"></div></li>
				<!-- Changes done for CI BRD 13-10-08 ENDS-->
	              </ul>
	              <ul class="form division">
	              <!-- Path for Building,floor and office changed for CI BRD 13-10-08 ENDS-->
	                <li>
	                  <label for="bldg"><spring:message code="requestInfo.addressInfo.label.building"/> </label>
	                  <span><form:input id="building" path="returnAddress.physicalLocation1" class="w80" /></span>
	                </li>
	                <li>
	                  <label for="flr"><spring:message code="requestInfo.addressInfo.label.floor"/></label>
	                  <span><form:input id="floor" path="returnAddress.physicalLocation2" class="w80" /></span>
	                </li>
	                <li>
	                  <label for="ofc"><spring:message code="requestInfo.addressInfo.label.office"/></label>
	                  <span><form:input id="office" path="returnAddress.physicalLocation3" class="w80" /></span>
	                </li>
	              </ul>
	            </div>
	          </div>
	        </div>
	        
	      <!-- Hidden fields to bind with form -->
		  <span style="display: none">
						         	<form:input id="pickupAddressId" path="returnAddress.addressId" />
						         	<form:input id="pickupStoreFrontName" path="returnAddress.storeFrontName" />
							     	<form:input id="pickupAddressLine1" path="returnAddress.addressLine1" /> 
							        <form:input id="pickupAddressLine2" path="returnAddress.addressLine2" />
							        <form:input id="pickupAddressCity" path="returnAddress.city" />
							        <form:input id="pickupAddressState" path="returnAddress.state" />
							        <form:input id="pickupAddressProvince" path="returnAddress.province" />
							        <form:input id="pickupAddressPostCode" path="returnAddress.postalCode" />
							        <form:input id="pickupAddressCountry" path="returnAddress.country" />
 							        <%--<form:input path="listOfFileTypes" id="listOfFileTypes"/> --%>
									<!-- Below Fields for Cleansing address Don't change the
									input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
									 -->
									
									<form:input id="pickupAddresscounty" path="returnAddress.county" />
							        <form:input id="pickupAddressofficeNumber" path="returnAddress.officeNumber" />
							        <form:input id="pickupAddressstateCode" path="returnAddress.stateCode" />
							        <form:input id="pickupAddressstateFullName" path="returnAddress.stateFullName" />
							        <form:input id="pickupAddressdistrict" path="returnAddress.district" />
							        <form:input id="pickupAddressregion" path="returnAddress.region" />
							        <form:input id="pickupAddresslatitude" path="returnAddress.latitude" />
							        <form:input id="pickupAddresslongitude" path="returnAddress.longitude" />
							        <form:input id="pickupAddresscountryISOCode" path="returnAddress.countryISOCode" />
							        <form:input id="pickupAddresssavedErrorMessage" path="returnAddress.savedErrorMessage" />
							        <form:input id="pickupAddressisAddressCleansed" path="returnAddress.isAddressCleansed"/>
							        
									<!-- Ends Cleansing address -->
									</span>
		  <div id="dialog"></div>
  		  <div id="dialogAddress" style="display: none;"></div>
        </form:form>
        
        
        <p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
        <div class="columnInner">
        <form:form id="fileUploadFormId" commandName="fileUploadForm" method="post" action="${attachDocumentAction}" enctype="multipart/form-data" >
        	 <span style="display: none">
				<form:input path="fileCount" /> 
			</span>
			
          	<input type="text" size="70" name="txtFilePath" id="txtFilePath" readonly="readonly">
          	<form:input type="file" size="1" id="file" class="requestsUploader" path="fileData" onchange="setPathToTextBox();"  />
			<button class="button_cancel" type="button" id="btnBrowse" style="cursor:pointer;"><spring:message code="button.browse"/></button>&nbsp;&nbsp;					
			<button class="button_cancel" type="button" id="btnUpload" style="cursor:pointer;" onclick="uploadFile();"><spring:message code="button.attach"/></button>
			<p class="note">
				<spring:message code="requestInfo.label.attachmentInstruction1"/>
				<spring:message code="requestInfo.label.attachmentInstruction2"/>
			</p>
			<iframe id="upload_target" name="upload_target" style="width:0px;height:0px;border:0px solid #fff;"></iframe>
			<div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">
				<table id="fileListTable"></table>
			</div>
		 	<div id="test"></div>
			<div class="lineClear"></div>
        </form:form>
        </div>
        <div class="buttonContainer">
          <button class="button_cancel" onclick="javascript:redirectToHistory(); callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESCANCEL%>');"><spring:message code="button.cancel"/></button>
          <button class="button" id="doReviewBtn" onclick="submitRequest();"><spring:message code="button.continue"/></button>
        </div>
        <input type="hidden" id="fileCount" />
      </div>
      <!-- MAIN CONTENT END --> 
    </div>
  </div>
  
  <script type="text/javascript">

  document.getElementById('effctiveDateshow').style.display = "none";
  jQuery(':input').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
		});
  
  
/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	var currentURL = window.location.href;
	//If block added for CI-7 Defect 12274
	if(currentURL.indexOf('/partner-portal') != -1)
	{	
		var partnerURLWithoutParams;
		var partnerIndexOfQuestionMark = currentURL.indexOf("?");
		if (partnerIndexOfQuestionMark > 0) {
			partnerURLWithoutParams = currentURL.substring(0, partnerIndexOfQuestionMark);
		} else {
			partnerURLWithoutParams = currentURL;
		}
		partnerBaseURL = partnerURLWithoutParams.substring(0, partnerURLWithoutParams.lastIndexOf('/partner-portal') + '/partner-portal'.length) + "/returnsupplies";
		showOverlay();
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showReturnsPage'/></portlet:actionURL>&friendlyURL="+partnerBaseURL;
		return;
	}
	else
		{
		showOverlay();
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showReturnsPage'/></portlet:actionURL>&friendlyURL="+populateURL("returnsupplies",[],[]);
		}
							
}
  /* END */
  </script>
