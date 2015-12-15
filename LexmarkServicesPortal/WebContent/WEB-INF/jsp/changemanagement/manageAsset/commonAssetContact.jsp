<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
	<portlet:renderURL var="addressListPopUpUrl"
		windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
	</portlet:renderURL>
	<portlet:renderURL var="deviceContactListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="action" value="showContactListPopUp"></portlet:param>
	</portlet:renderURL>
<script type="text/javascript">	
	<%@ include file="../../../../js/commonAddress.js"%>	
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="dialog_contact_device"></div>
<div id="dialogAddressDevice"></div>

<div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
            <c:if test="${pageFlow == 'addone'}">
				<c:set var="deviceContactItem" value="${manageAssetForm.assetDetail.deviceContact}" />
			</c:if>
			<c:if test="${pageFlow == 'Change'}">
				<c:set var="deviceContactItem" value="${manageAssetFormForChange.assetDetail.deviceContact}" />
			</c:if>
			
			<c:if test="${pageFlow == 'fleetMgmtChange'}">
						<c:set var="deviceContactItem" value="${manageAssetFormForChange.assetDetail.deviceContact}" />
						</c:if>
						
						<c:if test="${pageFlow == 'fleetMgmtMove'}">
						<c:set var="deviceContactItem" value="${manageAssetFormForChange.assetDetail.deviceContact}" />
						</c:if>
			
              <h4><spring:message code="requestInfo.heading.contactInfoForDevice"/></h4>
              <p class="info"><spring:message code="requestInfo.manageAsset.info.addDifferentInfo"/> 
              <span id="addContactH">
              <c:choose>
		        <c:when test="${empty deviceContactItem}">
		          &quot;<spring:message code="requestInfo.manageAsset.info.addNewContact"/>&quot;
		        </c:when>
		        <c:otherwise>
		        	&quot;<spring:message code="requestInfo.heading.addAnotherContact"/>&quot;	          
		        </c:otherwise>
		      </c:choose>
		      </span>
		      
		      <span style="display: none;" id="addAnotherContactH">
		       	&quot;<spring:message code="requestInfo.heading.addAnotherContact"/>&quot;
		      </span>
		      <span style="display: none;" id="addNewContactH">
		        &quot;<spring:message code="requestInfo.manageAsset.info.addNewContact"/>&quot;
		      </span>		      
              </p>
              
			  <div>
					<table class="displayGrid rounded shadow" id="deviceTable">
						<thead>
						<tr>
							<th class="w20p"><spring:message code="requestInfo.heading.contactType"/></th>
							<th class="w40p"><spring:message code="requestInfo.manageAsset.info.primaryContact"/></th>
							<th class="w40p"><spring:message code="requestInfo.heading.address"/></th>
							<th width="30"></th>
						</tr>
						</thead>
						
						<tbody>										
						
						<c:forEach items="${deviceContactItem}" var="deviceContactList" varStatus="counterContact" begin="0">
						<c:if test="${(deviceContactList.deviceContactType != null && deviceContactList.deviceContactType != '') && (deviceContactList.contactId != null && deviceContactList.contactId != '') && (deviceContactList.address.addressId != null && deviceContactList.address.addressId !='')}">
						<tr class="<c:if test="${counterContact.index % 2 ne 0}">altRow</c:if>">
							<td class="middle">
								<select name="deviceContactType${counterContact.index+1}" id="deviceContactType${counterContact.index+1}" class="w70p">
								<option value="Select"><spring:message code="requestInfo.option.select"/></option>
								<c:forEach items="${deviceContactTypeList}" var="deviceContactTypeList" varStatus="counterContactType" begin="0">
								<c:choose>
								<c:when test="${deviceContactTypeList.key == deviceContactList.deviceContactType}">
								<option value="${deviceContactTypeList.key}" id="${deviceContactTypeList.key}${counterContact.index+1}" selected>${deviceContactTypeList.value}</option>
								</c:when>
								<c:otherwise>
								<option value="${deviceContactTypeList.key}" id="${deviceContactTypeList.key}${counterContact.index+1}">${deviceContactTypeList.value}</option>
								</c:otherwise>
								</c:choose>
								</c:forEach>
								
								<!-- <option value="Service Contact" id="serviceContactOption${counterContact.index+1}">Service Contact</option>
								<option value="Consumable Contact" id="consumableContactOption${counterContact.index+1}">Consumable Contact</option></select>
							 -->
							 </select>
							 
							</td>
													
							<td class="middle">
								<div>
								<ul class="form">
									<li style="display:none"> 
										<span id="deviceContactId${counterContact.index+1}">${deviceContactList.contactId}</span>
									</li>
								    <li>
								      <label><spring:message code="requestInfo.label.name"/></label>
								      <span id="deviceFName${counterContact.index+1}">${deviceContactList.firstName}</span>
								      <span id="deviceLName${counterContact.index+1}">${deviceContactList.lastName}</span></li>
								    <li>
								      <label><spring:message code="requestInfo.label.phoneNumber"/></label>
								      <span id="devicePhone${counterContact.index+1}">${deviceContactList.workPhone}</span></li>
								    <li style="display:none"> <span id="deviceAltPhone${counterContact.index+1}">${deviceContactList.alternatePhone}</span></li>
								    <li>
								      <label><spring:message code="requestInfo.label.emailAddress"/></label>
								      <span id="deviceEmail${counterContact.index+1}">${deviceContactList.emailAddress}</span></li>
							  	</ul>
							  	</div>
								<div>
									<a href="${deviceContactListPopUpUrl}" title='<spring:message code="requestInfo.link.selectAdifferentContact"/>' class="lightboxDevice floatR" onclick="return popupDeviceContact('${counterContact.index+1}');">
									<spring:message code="requestInfo.link.selectAdifferentContact"/></a>
								</div>
							
							</td>
							<td class="middle">								
								<ul class="roDisplay">
								    <li>
									    <div id="deviceAddressId${counterContact.index+1}" style="display:none">${deviceContactList.address.addressId}</div>
									    <div id="deviceStoreFrontName${counterContact.index+1}">${deviceContactList.address.storeFrontName}</div>
										<div id="deviceAddressLine1${counterContact.index+1}">${deviceContactList.address.addressLine1}</div>
										<div id="deviceAddressofficeNumber${counterContact.index+1}">${deviceContactList.address.officeNumber}</div>
										<div id="deviceAddressLine2${counterContact.index+1}">${deviceContactList.address.addressLine2}</div>
										<div id="city_state_zip_popup_span${counterContact.index+1}">										
											<span id="deviceAddressCity${counterContact.index+1}">${deviceContactList.address.city}</span>	
																				
											<span id="deviceAddresscountyComma${counterContact.index+1}">
												<c:if test="${deviceContactList.address.county != null && deviceContactList.address.county != '' && deviceContactList.address.county != ' '}">, </c:if>
											</span>
											<span id="deviceAddresscounty${counterContact.index+1}">${deviceContactList.address.county}</span>
											
											<span id="deviceAddressStateComma${counterContact.index+1}">
												<c:if test="${deviceContactList.address.state != null && deviceContactList.address.state != '' && deviceContactList.address.state != ' '}">, </c:if>
											</span>
											<span id="deviceAddressState${counterContact.index+1}">${deviceContactList.address.state}</span>
											
											<span id="deviceAddressProvinceComma${counterContact.index+1}">
												<c:if test="${deviceContactList.address.province != null && deviceContactList.address.province != '' && deviceContactList.address.province != ' '}">, </c:if>
											</span>
											<span id="deviceAddressProvince${counterContact.index+1}">${deviceContactList.address.province}</span>
											
											<span id="deviceAddressDistrictComma${counterContact.index+1}">
												<c:if test="${deviceContactList.address.district != null && deviceContactList.address.district != '' && deviceContactList.address.district != ' '}">, </c:if>
											</span>
											<span id="deviceAddressDistrict${counterContact.index+1}">${deviceContactList.address.district}</span>
										</div>
										<div id="deviceAddressPostCode${counterContact.index+1}">${deviceContactList.address.postalCode}</div>
										<div id="deviceAddressCountry${counterContact.index+1}">${deviceContactList.address.country}</div>
									</li>
									<li style="display:none">
										<span id="deviceAddressregion${counterContact.index+1}">${deviceContactList.address.region}</span>
										<span id="deviceAddresscountryISOCode${counterContact.index+1}">${deviceContactList.address.countryISOCode}</span>
										<span id="deviceAddressstateCode${counterContact.index+1}">${deviceContactList.address.stateCode}</span>
									</li>									              	
								    <li>
								      <label for="building"><spring:message code="requestInfo.addressInfo.label.building" /></label>
								      <span><input type="text" id="physicalLocation1${counterContact.index+1}" class="w100" maxlength="100" value="${deviceContactList.address.physicalLocation1}" /></span></li>
								    <li>
								      <label for="floor"><spring:message code="requestInfo.addressInfo.label.floor" /></label>
								      <span><input type="text" id="physicalLocation2${counterContact.index+1}" class="w100" maxlength="100" value="${deviceContactList.address.physicalLocation2}" /></span></li>
								    <li>
								      <label for="office"><spring:message code="requestInfo.addressInfo.label.office" /></label>
								      <span><input type="text" id="physicalLocation3${counterContact.index+1}" class="w100" maxlength="100" value="${deviceContactList.address.physicalLocation3}" /></span></li>
								</ul>
								<div>
								<c:choose>								
								<c:when test="${not empty deviceContactList.address}">								
									<a href="${addressListPopUpUrl}" title='<spring:message code="requestInfo.link.selectADifferentAddress"/>'
									id="deviceAddressLink${counterContact.index+1}" class="lightboxAddrDevice floatR" onclick="return popupDeviceAddress('${counterContact.index+1}');">
									<spring:message code="requestInfo.link.selectADifferentAddress" /></a>									
								</c:when>
								<c:otherwise>
									<a href="${addressListPopUpUrl}" title='<spring:message code="requestInfo.link.selectAnAddress"/>'
									id="deviceAddressLink${counterContact.index+1}" class="lightboxAddrDevice floatR" onclick="return popupDeviceAddress('${counterContact.index+1}');">
									<spring:message code="requestInfo.link.selectAnAddress" /></a>
								</c:otherwise>
								</c:choose>	
								</div>					
							</td>
							<td class="middle">	
								<a href="javascript:deleteDeviceContact('${counterContact.index+1}')">
								<img class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" alt="Remove" title="Remove" class="edit" />
								</a>
							</td>
						</tr>
						</c:if>
						</c:forEach>
						</tbody>
					</table>
            </div>
			
			<div class="buttonContainer border-none" id="buttonContactFirst">
			<c:choose>
        <c:when test="${empty deviceContactItem}">
          <button class="button" type="button" onclick="javascript:addDeviceContact()"><spring:message code="requestInfo.manageAsset.info.addNewContact" /></button>
          </c:when>
          <c:otherwise>
          <button class="button" type="button" onclick="javascript:addDeviceContact()"><spring:message code="requestInfo.heading.addAnotherContact" /></button>
          </c:otherwise>
          </c:choose>
        </div>
        <div class="buttonContainer border-none" style="display: none;" id="buttonContactSecond">
        <button class="button" type="button" onclick="javascript:addDeviceContact()"><spring:message code="requestInfo.heading.addAnotherContact" /></button>
        </div>
        <div class="buttonContainer border-none" style="display: none;" id="buttonContactThird">
        <button class="button" type="button" onclick="javascript:addDeviceContact()"><spring:message code="requestInfo.manageAsset.info.addNewContact" /></button>
        </div>
        </div>
</div>

<span style="display: none;">
	<input type="text" name="deviceContactTypeList" id="deviceContactTypeList" />
	
	<input type="text" name="deviceContactIdList" id="deviceContactIdList" />
    <input type="text" name="deviceFNameList" id="deviceFNameList" />
    <input type="text" name="deviceLNameList" id="deviceLNameList" />
    <input type="text" name="devicePhoneList" id="devicePhoneList" />
    <input type="text" name="deviceAltPhoneList" id="deviceAltPhoneList" />
    <input type="text" name="deviceEmailList" id="deviceEmailList" />
    
    <input type="text" name="deviceAddressIdList" id="deviceAddressIdList" />
    <input type="text" name="deviceStoreFrontNameList" id="deviceStoreFrontNameList" />
    <input type="text" name="deviceAddressLine1List" id="deviceAddressLine1List" />
    <input type="text" name="deviceAddressofficeNumberList" id="deviceAddressofficeNumberList" />
    <input type="text" name="deviceAddressLine2List" id="deviceAddressLine2List" />
    <input type="text" name="deviceAddressCityList" id="deviceAddressCityList" />
    <input type="text" name="deviceAddresscountyList" id="deviceAddresscountyList" />
    <input type="text" name="deviceAddressStateList" id="deviceAddressStateList" />
    <input type="text" name="deviceAddressProvinceList" id="deviceAddressProvinceList" />
    <input type="text" name="deviceAddressDistrictList" id="deviceAddressDistrictList" />
    <input type="text" name="deviceAddressPostCodeList" id="deviceAddressPostCodeList" />
    <input type="text" name="deviceAddressCountryList" id="deviceAddressCountryList" />
    <input type="text" name="deviceAddressregionList" id="deviceAddressregionList" />
    <input type="text" name="deviceAddresscountryISOCodeList" id="deviceAddresscountryISOCodeList" />
    <input type="text" name="deviceAddressstateCodeList" id="deviceAddressstateCodeList" />
    <input type="text" name="physicalLocation1List" id="physicalLocation1List" />
    <input type="text" name="physicalLocation2List" id="physicalLocation2List" />
    <input type="text" name="physicalLocation3List" id="physicalLocation3List" />
</span>
       
<script type="text/javascript">

var deviceContactTypeArray = new Array(); 
var deviceContactTypeArrayValue = new Array(); 
var i=0;   	 
<c:forEach items="${deviceContactTypeList}" var="itemContactType">
deviceContactTypeArray[i]= '${itemContactType.key}';
deviceContactTypeArrayValue[i]= '${itemContactType.value}';
i++;
</c:forEach>

var deviceAddrPopUp="false";
var deviceContactPopUp="false";
//var deviceNoOfRow=1;
var deviceNoOfRow;

jQuery(document).ready(function(){
	deviceNoOfRow=document.getElementById("deviceTable").rows.length;
	//alert('deviceNoOfRow='+deviceNoOfRow);
	//alert('deviceContactTypeArray='+deviceContactTypeArray);
});

function addDeviceContact() {
	jQuery('#buttonContactSecond').show();
	jQuery('#buttonContactFirst').hide();
	jQuery('#buttonContactThird').hide();

	jQuery('#addAnotherContactH').show();
	jQuery('#addContactH').hide();
	jQuery('#addNewContactH').hide();

	//alert('in addDeviceContact');
	var table=document.getElementById("deviceTable");
	//var rowCount = table.rows.length;
	var rowCount = deviceNoOfRow;
	deviceNoOfRow++;
    var row = table.insertRow(rowCount);
	var cell1=row.insertCell(0);
	var cell2=row.insertCell(1);
	var cell3=row.insertCell(2);
	var cell4=row.insertCell(3);
	cell1.className="middle";
	cell2.className="middle";
	cell3.className="middle";
	cell4.className="middle";

	if(rowCount%2==0){
		row.className="altRow";
	}	
	var contactTypeOption="";
	for(var i=0 ; i<deviceContactTypeArray.length;i++){
		contactTypeOption=contactTypeOption+'<option value="'+deviceContactTypeArray[i]+'" id="'+deviceContactTypeArray[i]+rowCount+'">' + deviceContactTypeArrayValue[i]+ '</option>';
	}
	
	cell1.innerHTML='<select name="deviceContactType'+rowCount+'" id="deviceContactType'+rowCount+'" class="w70p"><option value="Select"><spring:message code="requestInfo.option.select"/></option>'+contactTypeOption+'</select>';
		
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
	  	'</div>'+
			'<div><a href="${deviceContactListPopUpUrl}"  title=\'<spring:message code="requestInfo.heading.selectAContact"/>\' id="deviceContactLink'+rowCount+'" class="lightboxDevice floatR" onclick="return popupDeviceContact('+rowCount+');">'+
			'<spring:message code="requestInfo.heading.selectAContact"/></a>'+
		'</div>';
		
	cell3.innerHTML='<ul class="roDisplay">'+
	    '<li>'+
	    '<div id="deviceAddressId'+rowCount+'" style="display:none"></div>'+
	    '<div id="deviceStoreFrontName'+rowCount+'"></div>'+
		'<div id="deviceAddressLine1'+rowCount+'"></div>'+
		'<div id="deviceAddressofficeNumber'+rowCount+'"></div>'+
		'<div id="deviceAddressLine2'+rowCount+'"></div>'+
		'<div id="city_state_zip_popup_span'+rowCount+'">'+
		
		'<span id="deviceAddressCity'+rowCount+'"></span>'+
		'<span id="deviceAddresscountyComma'+rowCount+'"></span>'+
		'<span id="deviceAddresscounty'+rowCount+'"></span>'+
		'<span id="deviceAddressStateComma'+rowCount+'"></span>'+
		'<span id="deviceAddressState'+rowCount+'"></span>'+
		'<span id="deviceAddressProvinceComma'+rowCount+'"></span>'+
		'<span id="deviceAddressProvince'+rowCount+'"></span>'+
		'<span id="deviceAddressDistrictComma'+rowCount+'"></span>'+
		'<span id="deviceAddressDistrict'+rowCount+'"></span>'+
		
		'</div>'+
		'<div id="deviceAddressPostCode'+rowCount+'"></div>'+
		'<div id="deviceAddressCountry'+rowCount+'"></div>'+
		'</li>'+
		'<li style="display:none">'+
		'<span id="deviceAddressregion'+rowCount+'"></span>'+
		'<span id="deviceAddresscountryISOCode'+rowCount+'"></span>'+
		'<span id="deviceAddressstateCode'+rowCount+'"></span>'+
		'</li>'+
		              	
	    '<li>'+
	      '<label for="building"><spring:message code="requestInfo.addressInfo.label.building" /></label>'+
	      '<span><input type="text" id="physicalLocation1'+rowCount+'" class="w100" maxlength="100" /></span></li>'+
	    '<li>'+
	      '<label for="floor"><spring:message code="requestInfo.addressInfo.label.floor" /></label>'+
	      '<span><input type="text" id="physicalLocation2'+rowCount+'" class="w100" maxlength="100" /></span></li>'+
	    '<li>'+
	      '<label for="office"><spring:message code="requestInfo.addressInfo.label.office" /></label>'+
	      '<span><input type="text" id="physicalLocation3'+rowCount+'" class="w100" maxlength="100" /></span></li>'+
		'</ul>'+
		'<div><a href="${addressListPopUpUrl}" title="<spring:message code="requestInfo.link.selectAnAddress"/>"'+
		'id="deviceAddressLink'+rowCount+'" class="lightboxAddrDevice floatR" onclick="return popupDeviceAddress('+rowCount+');"><spring:message code="requestInfo.link.selectAnAddress" /></a>'+
		'</div>';

	cell4.innerHTML='<a href="javascript:deleteDeviceContact('+rowCount+')"><img class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" alt="Remove" title="Remove" class="edit" /></a>';
}
function deleteDeviceContact(rowNum) {
	/*if(rowNum ==1){
	jQuery('#buttonContactSecond').hide();
	jQuery('#buttonContactFirst').hide();
	jQuery('#buttonContactThird').show();
	}*/
	//alert('rowNum='+rowNum);
	var table=document.getElementById("deviceTable");
	//var rowCount = table.rows.length;
	//table.deleteRow(rowNum);
	var deviceRow=table.rows[rowNum];
	table.rows[rowNum].style.display = "none";
	for(i=0;i<4;i++) {
		deviceRow.deleteCell(0);
	}

	var addNewContactButton=true;
	var rowCount = table.rows.length;
	for(i=1;i<rowCount;i++) {
		//alert(i+"no of table row="+table.rows[i].style.display);
		if(table.rows[i].style.display!="none") {
			addNewContactButton=false;
		}
	}
	if(addNewContactButton==true) {
		jQuery('#buttonContactSecond').hide();
		jQuery('#buttonContactFirst').hide();
		jQuery('#buttonContactThird').show();

		jQuery('#addContactH').hide();
		jQuery('#addAnotherContactH').hide();
		jQuery('#addNewContactH').show();
	}
}

<%--for contact section start--%>
function popupDeviceContact(rowNum){
	//alert(rowNum);
	deviceContactPopUp=rowNum;
			showOverlay();
			jQuery('#dialog_contact_device').load(jQuery('.lightboxDevice').attr('href'),function(){
				dialog=jQuery('#contentContact').dialog({
					autoOpen: false,
					title: jQuery('.lightboxDevice').attr('title'),
					modal: true,
					draggable: false,
					resizable: false,
					height: 550,
					width: 800,
					close: function(event,ui){
						//alert('in close here');
						//document.getElementById('loadingNotification').style.display = 'block';
						hideOverlay();
						dialog=null;
						jQuery('#contentContact').remove();
						}
					});
				dialog.dialog('open');
				initializeContactGrid();
				if (window.PIE) {
					//alert('before');
		            document.getElementById("createNewContactPopup").fireEvent("onmove");
		            document.getElementById("cancelNewContactPopup").fireEvent("onmove");
		            //alert('after');
		        }	
				});
			//alert('end popupDeviceContact');
			return false;
}

function addDeviceContactElement(ci, ln, fn, wp, ea, altPhn, rowNum, addressId,addressName,storefrontName,ad1,ad2,city,state,province,country,pocode,
		officeNumber,district,county,countryISO,region,stateCode,phyLoc1,phyLoc2,phyLoc3)
{	
	//alert('inside addDeviceContactElement rowNum='+rowNum);
	/*jQuery("#deviceFName"+rowNum).html(fn);
	jQuery("#deviceLName"+rowNum).html(ln);
	jQuery("#devicePhone"+rowNum).html(wp);
	jQuery("#deviceEmail"+rowNum).html(ea);*/

	jQuery("#deviceContactId"+rowNum).html(ci);
	jQuery("#deviceFName"+rowNum).html(fn);
	jQuery("#deviceLName"+rowNum).html(ln);
	jQuery("#devicePhone"+rowNum).html(wp);
	jQuery("#deviceAltPhone"+rowNum).html(altPhn);
	jQuery("#deviceEmail"+rowNum).html(ea);
	jQuery('#deviceContactLink'+rowNum).html("<spring:message code="requestInfo.link.selectAdifferentContact"/>").attr('title',"<spring:message code="requestInfo.link.selectAdifferentContact"/>");
	deviceAddrPopUp=rowNum;
	addDeviceAddress(addressId,addressName,storefrontName,ad1,ad2,city,state,province,country,pocode,
			officeNumber,district,county,countryISO,region,stateCode,phyLoc1,phyLoc2,phyLoc3,true)

}

<%--for address section start--%>
function popupDeviceAddress(rowNum){
	//alert('in popup address');
	deviceAddrPopUp=rowNum;
	hyperlinkId='deviceAddressLink'+rowNum;//This link id is for displaying inner htmls in address
	linkId='deviceAddressLink';//This link id is for displaying inner htmls in address
	//alert(linkId);
	var addressFlag;
	showOverlay();
	//alert('addressFlag is '+addressFlag);
	var url=jQuery('#'+hyperlinkId).attr('href');
	url+="&addressFlag="+addressFlag;
	jQuery('#dialogAddressDevice').load(url,function(){
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
		dialog.dialog('open');
		initializeAddressGrid();
		jQuery("#createNewAddressButton").hide();
		if (window.PIE) {
            document.getElementById("createNewAddressButton").fireEvent("onmove");
            document.getElementById("cancelAddressButton").fireEvent("onmove");
        }
		});
	return false;
}

//Changes for MPS 2.1
function addRestFieldsOfCleanseDeviceAddr(){
	//jQuery('#installAddressisAddressCleansed').val("true");
	<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
		jQuery("#installAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
	<%}%>
	jQuery('#installAddressofficeNumberLbl').html(jQuery('#installAddressofficeNumber').val());
	//Start Added for MPS 2.1
	jQuery("#installAddresscountyLbl").html(',&nbsp;'+jQuery('#installAddresscounty').val());
	jQuery("#installAddresscountyLbl").show();
	if(jQuery('#installAddresscounty').val() =='' || jQuery('#installAddresscounty').val() == null){
		jQuery("#installAddresscountyLbl").hide();
		}
	//jQuery("#installAddressdistrictLbl").html(jQuery('#installAddressdistrict').val());
	//END
	}

function addDeviceAddress(addressId,addressName,storeFrontName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
		addressCountry,addressPostCode,officeNumber,district,county,countryISO,region,stateCode,physicalLoc1,physicalLoc2,physicalLoc3,contactAddrFlag)

{	
	//alert('insideaddDeviceAddress');
	if(addressProvince == null){
		addressProvince = '';
	}
	//alert('addressId is '+addressId);
	//alert('addressProvince '+addressProvince);
	if(addressId==null){
		addressId = '-1';
	}

	/*var deviceContactType = document.getElementById('deviceContactType'+deviceAddrPopUp);
	var deviceContactTypeValue = deviceContactType.options[deviceContactType.selectedIndex].value;
	alert('deviceContactTypeValue='+deviceContactTypeValue);*/

//	if(deviceContactTypeValue=="Service") {
		
		//alert('addressLine1 from popup='+addressLine1);
		if(addressId != '-1' && addressId !=''){
			jQuery('#deviceAddressLink'+deviceAddrPopUp).html("<spring:message code="requestInfo.link.selectADifferentAddress"/>").attr('title',"<spring:message code="requestInfo.link.selectADifferentAddress"/>");
		}
		jQuery("#deviceAddressId"+deviceAddrPopUp).html(addressId);
		jQuery("#deviceStoreFrontName"+deviceAddrPopUp).html(storeFrontName);
		jQuery("#deviceAddressLine1"+deviceAddrPopUp).html(addressLine1);	
		jQuery("#deviceAddressofficeNumber"+deviceAddrPopUp).html(officeNumber);		
		jQuery("#deviceAddressLine2"+deviceAddrPopUp).html(addressLine2);
			
		jQuery("#deviceAddressCity"+deviceAddrPopUp).html(addressCity);
		
		if(!newContactAddFlag && (county == null || county =="" || county ==" ")){
			jQuery("#deviceAddresscountyComma"+deviceAddrPopUp).hide();
		}else{
			jQuery("#deviceAddresscountyComma"+deviceAddrPopUp).show();
		}
		jQuery("#deviceAddresscounty"+deviceAddrPopUp).html(county);
		if(!newContactAddFlag && (addressState ==  null || addressState=="" || addressState ==" ")){
			jQuery("#deviceAddressStateComma"+deviceAddrPopUp).hide();
		}else{
			jQuery("#deviceAddressStateComma"+deviceAddrPopUp).show();
			}
		
		jQuery("#deviceAddressState"+deviceAddrPopUp).html(addressState);
		if(!newContactAddFlag && (addressProvince ==  null || addressProvince=="" || addressProvince ==" ")){
			jQuery("#deviceAddressProvinceComma"+deviceAddrPopUp).hide();
		}else{
			jQuery("#deviceAddressProvinceComma"+deviceAddrPopUp).show();
		}
		jQuery("#deviceAddressProvince"+deviceAddrPopUp).html(addressProvince);
		if(!newContactAddFlag && (district ==  null || district=="" || district ==" ")){
			jQuery("#deviceAddressDistrictComma"+deviceAddrPopUp).hide();
		}else{
			jQuery("#deviceAddressDistrictComma"+deviceAddrPopUp).show();
		}
		jQuery("#deviceAddressDistrict"+deviceAddrPopUp).html(district);
		
		jQuery("#deviceAddressPostCode"+deviceAddrPopUp).html(addressPostCode);
		jQuery("#deviceAddressCountry"+deviceAddrPopUp).html(addressCountry);

		jQuery("#deviceAddressregion"+deviceAddrPopUp).html(region);
		jQuery("#deviceAddresscountryISOCode"+deviceAddrPopUp).html(countryISO);
		jQuery("#deviceAddressstateCode"+deviceAddrPopUp).html(stateCode);

		if(contactAddrFlag==true) {
			if(physicalLoc1 !=null){
			jQuery("#physicalLocation1"+deviceAddrPopUp).val(physicalLoc1);
			}
			if(physicalLoc2 !=null){
			jQuery("#physicalLocation2"+deviceAddrPopUp).val(physicalLoc2);
			}
			if(physicalLoc3 !=null){
			jQuery("#physicalLocation3"+deviceAddrPopUp).val(physicalLoc3);
			}
		}

		//This is for displaying purpose
		/*jQuery("#deviceStoreFrontName"+deviceAddrPopUp).html(storeFrontName);
		jQuery("#deviceAddressLine1"+deviceAddrPopUp).html(addressLine1);	
		jQuery("#deviceAddressofficeNumber"+deviceAddrPopUp).html(officeNumber);*/
		
		if(county !='' && county !=' ' && county != null){
			jQuery("#deviceAddresscountyComma"+deviceAddrPopUp).html(', ');
		}
		if(stateCode !='' && stateCode !=' ' && stateCode != null){
			jQuery("#deviceAddressStateComma"+deviceAddrPopUp).html(", ");
		}
		
		if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
				jQuery("#deviceAddressProvinceComma"+deviceAddrPopUp).html(', ');
		}
		else{
			jQuery("#deviceAddressProvinceComma"+deviceAddrPopUp).html('');
		}
		// region changed to district fot MPS 2.1
		if(district !='' && district !=' ' && district != null){
			jQuery("#deviceAddressDistrictComma"+deviceAddrPopUp).html(', ');
		}

		/*jQuery("#deviceAddressPostCode"+deviceAddrPopUp).html(addressPostCode);		
		jQuery("#deviceAddressCountry"+deviceAddrPopUp).html(addressCountry);

		if(contactAddrFlag==true) {
			jQuery("#physicalLocation1"+deviceAddrPopUp).html(physicalLoc1);
			jQuery("#physicalLocation2"+deviceAddrPopUp).html(physicalLoc2);
			jQuery("#physicalLocation3"+deviceAddrPopUp).html(physicalLoc3);
		}*/

		
	<%--Ends--%>
	
}
<%--for address section end--%>

var duplicateContactType = false;
var emptyContact = false;
var duplicateContact = false;
var emptyContactType = false;

function bindDeviceContact(){

	//alert('inside bindDeviceContact deviceNoOfRow='+deviceNoOfRow);
	var deviceContactType = new Array();
	
	var deviceContactId = new Array();
	var deviceFName = new Array();
	var deviceLName = new Array();
	var devicePhone = new Array();
	var deviceAltPhone = new Array();
	var deviceEmail = new Array();

	var deviceAddressId = new Array();
	var deviceStoreFrontName = new Array();
	var deviceAddressLine1 = new Array();
	var deviceAddressofficeNumber = new Array();
	var deviceAddressLine2 = new Array();
	var deviceAddressCity = new Array();
	var deviceAddresscounty = new Array();
	var deviceAddressState = new Array();
	var deviceAddressProvince = new Array();
	var deviceAddressDistrict = new Array();
	var deviceAddressPostCode = new Array();
	var deviceAddressCountry = new Array();
	var deviceAddressregion = new Array();
	var deviceAddresscountryISOCode = new Array();
	var deviceAddressstateCode = new Array();
	var physicalLocation1 = new Array();
	var physicalLocation2 = new Array();
	var physicalLocation3 = new Array();

	//alert('deviceNoOfRow before bind='+deviceNoOfRow);
	for(i=1;i<deviceNoOfRow;i++){
		//alert('inside for i='+i);
		if(document.getElementById('deviceFName'+i)!=null) {
			//alert('inside if i='+i);
			var deviceContactTypeValue = document.getElementById('deviceContactType'+i);
			deviceContactType[i-1] = deviceContactTypeValue.options[deviceContactTypeValue.selectedIndex].value;
			//alert('deviceContactType='+deviceContactType[i-1]);
			
			//alert('jQuery("#deviceFName"+i).html()='+jQuery("#deviceFName"+i).html());
			
			deviceContactId[i-1] = (jQuery("#deviceContactId"+i).html()!=""?jQuery("#deviceContactId"+i).html():"invalid");
			deviceFName[i-1] = (jQuery("#deviceFName"+i).html()!=""?jQuery("#deviceFName"+i).html():"invalid");
			deviceLName[i-1] = (jQuery("#deviceLName"+i).html()!=""?jQuery("#deviceLName"+i).html():"invalid");
			devicePhone[i-1] = (jQuery("#devicePhone"+i).html()!=""?jQuery("#devicePhone"+i).html():"invalid");
			deviceAltPhone[i-1] = (jQuery("#deviceAltPhone"+i).html()!=""?jQuery("#deviceAltPhone"+i).html():"invalid");
			deviceEmail[i-1] = (jQuery("#deviceEmail"+i).html()!=""?jQuery("#deviceEmail"+i).html():"invalid");

			deviceAddressId[i-1] = (jQuery("#deviceAddressId"+i).html()!=""?jQuery("#deviceAddressId"+i).html():"invalid");
			deviceStoreFrontName[i-1] = (jQuery("#deviceStoreFrontName"+i).html()!=""?jQuery("#deviceStoreFrontName"+i).html():"invalid");
			deviceAddressLine1[i-1] = (jQuery("#deviceAddressLine1"+i).html()!=""?jQuery("#deviceAddressLine1"+i).html():"invalid");
			deviceAddressofficeNumber[i-1] = (jQuery("#deviceAddressofficeNumber"+i).html()!=""?jQuery("#deviceAddressofficeNumber"+i).html():"invalid");
			deviceAddressLine2[i-1] = (jQuery("#deviceAddressLine2"+i).html()!=""?jQuery("#deviceAddressLine2"+i).html():"invalid");
			deviceAddressCity[i-1] = (jQuery("#deviceAddressCity"+i).html()!=""?jQuery("#deviceAddressCity"+i).html():"invalid");
			deviceAddresscounty[i-1] = (jQuery("#deviceAddresscounty"+i).html()!=""?jQuery("#deviceAddresscounty"+i).html():"invalid");
			deviceAddressState[i-1] = (jQuery("#deviceAddressState"+i).html()!=""?jQuery("#deviceAddressState"+i).html():"invalid");
			deviceAddressProvince[i-1] = (jQuery("#deviceAddressProvince"+i).html()!=""?jQuery("#deviceAddressProvince"+i).html():"invalid");
			deviceAddressDistrict[i-1] = (jQuery("#deviceAddressDistrict"+i).html()!=""?jQuery("#deviceAddressDistrict"+i).html():"invalid");
			deviceAddressPostCode[i-1] = (jQuery("#deviceAddressPostCode"+i).html()!=""?jQuery("#deviceAddressPostCode"+i).html():"invalid");
			deviceAddressCountry[i-1] = (jQuery("#deviceAddressCountry"+i).html()!=""?jQuery("#deviceAddressCountry"+i).html():"invalid");
			deviceAddressregion[i-1] = (jQuery("#deviceAddressregion"+i).html()!=""?jQuery("#deviceAddressregion"+i).html():"invalid");
			deviceAddresscountryISOCode[i-1] = (jQuery("#deviceAddresscountryISOCode"+i).html()!=""?jQuery("#deviceAddresscountryISOCode"+i).html():"invalid");
			deviceAddressstateCode[i-1] = (jQuery("#deviceAddressstateCode"+i).html()!=""?jQuery("#deviceAddressstateCode"+i).html():"invalid");
			
			physicalLocation1[i-1] = (jQuery("#physicalLocation1"+i).val()!=""?jQuery("#physicalLocation1"+i).val():"invalid");
			physicalLocation2[i-1] = (jQuery("#physicalLocation2"+i).val()!=""?jQuery("#physicalLocation2"+i).val():"invalid");
			physicalLocation3[i-1] = (jQuery("#physicalLocation3"+i).val()!=""?jQuery("#physicalLocation3"+i).val():"invalid");		
			
		} else {
			//alert('inside else i='+i);
			deviceContactType[i-1] = "deletedContact";
			
			deviceContactId[i-1] = "deletedContact";
			deviceFName[i-1] = "deletedContact";
			deviceLName[i-1] = "deletedContact";
			devicePhone[i-1] = "deletedContact";
			deviceAltPhone[i-1] = "deletedContact";
			deviceEmail[i-1] = "deletedContact";

			deviceAddressId[i-1] = "deletedContact";
			deviceStoreFrontName[i-1] = "deletedContact";
			deviceAddressLine1[i-1] = "deletedContact";
			deviceAddressofficeNumber[i-1] = "deletedContact";
			deviceAddressLine2[i-1] = "deletedContact";
			deviceAddressCity[i-1] = "deletedContact";
			deviceAddresscounty[i-1] = "deletedContact";
			deviceAddressState[i-1] = "deletedContact";
			deviceAddressProvince[i-1] = "deletedContact";
			deviceAddressDistrict[i-1] = "deletedContact";
			deviceAddressPostCode[i-1] = "deletedContact";
			deviceAddressCountry[i-1] = "deletedContact";
			deviceAddressregion[i-1] = "deletedContact";
			deviceAddresscountryISOCode[i-1] = "deletedContact";
			deviceAddressstateCode[i-1] = "deletedContact";
			physicalLocation1[i-1] = "deletedContact";
			physicalLocation2[i-1] = "deletedContact";
			physicalLocation3[i-1] = "deletedContact";
		}
		//alert('exiting for loop i='+i);
	}		

	duplicateContactType = false;
	emptyContact = false;
	duplicateContact = false;
	emptyContactType = false;
	//alert('deviceContactType='+deviceContactType);
	if(i!=1) { // Data is there in Device Contact

	//alert('there is device contact');
		
	for(i=0;i<deviceContactType.length;i++) {
		if(deviceContactType[i]!="deletedContact") {
			//alert('deviceFName[i]='+deviceFName[i]);
			if(deviceFName[i]=='' || deviceFName[i]==' ' || deviceFName[i]==null || deviceFName[i]=='invalid') {			
				emptyContact = true;
			}
			if(deviceContactType[i]=='Select' || deviceContactType[i]==' ' || deviceContactType[i]==null || deviceContactType[i]=='invalid') {			
				emptyContactType = true;
			}
			for(j=i+1;j<deviceContactType.length;j++) {
				if(deviceContactType[i]!='Select' && deviceContactType[i]==deviceContactType[j]) {
					duplicateContactType = true;
					break;
				}
			}	
		}
		/*if(deviceContactId[i]!="deletedContact" && deviceContactId[i]!="-1" && deviceContactId[i]!="" && deviceContactId[i]!=null && deviceContactId[i]!="invalid") {//contact not deleted and not a new contact
			for(j=i+1;j<deviceContactId.length;j++) {
				if(deviceContactId[i]==deviceContactId[j]) {
					//alert('duplicateContact');
					duplicateContact = true;
					break;
				}
			}	
		}*/ //Need to be uncommented if duplicate contact needs to be blocked
	}

	//alert('duplicateContactType='+duplicateContactType);
	if(!duplicateContactType && !emptyContact && !duplicateContact && !emptyContactType) {
	jQuery('#deviceContactTypeList').val(deviceContactType);

	//alert('deviceFNameList length='+deviceFName.length);
	//alert('deviceLNameList='+deviceLName);
	jQuery('#deviceContactIdList').val(deviceContactId);
	jQuery('#deviceFNameList').val(deviceFName);
	jQuery('#deviceLNameList').val(deviceLName);
	jQuery('#devicePhoneList').val(devicePhone);
	jQuery('#deviceAltPhoneList').val(deviceAltPhone);
	jQuery('#deviceEmailList').val(deviceEmail);
	
	jQuery('#deviceAddressIdList').val(deviceAddressId);
	jQuery('#deviceStoreFrontNameList').val(deviceStoreFrontName);
	jQuery('#deviceAddressLine1List').val(deviceAddressLine1);
	jQuery('#deviceAddressofficeNumberList').val(deviceAddressofficeNumber);
	jQuery('#deviceAddressLine2List').val(deviceAddressLine2);
	jQuery('#deviceAddressCityList').val(deviceAddressCity);
	jQuery('#deviceAddressStateList').val(deviceAddressState);
	jQuery('#deviceAddresscountyList').val(deviceAddresscounty);
	jQuery('#deviceAddressProvinceList').val(deviceAddressProvince);
	jQuery('#deviceAddressDistrictList').val(deviceAddressDistrict);
	jQuery('#deviceAddressPostCodeList').val(deviceAddressPostCode);
	jQuery('#deviceAddressCountryList').val(deviceAddressCountry);
	jQuery('#deviceAddressregionList').val(deviceAddressregion);
	jQuery('#deviceAddresscountryISOCodeList').val(deviceAddresscountryISOCode);
	jQuery('#deviceAddressstateCodeList').val(deviceAddressstateCode);
	jQuery('#physicalLocation1List').val(physicalLocation1);
	jQuery('#physicalLocation2List').val(physicalLocation2);
	jQuery('#physicalLocation3List').val(physicalLocation3);

	//alert('deviceAddressPostCode='+deviceAddressPostCode+' deviceAltPhone='+deviceAltPhone);
	return true;
	} else {
		//alert('duplicateContactType');
		return false;
	}
	} else { // No Data is there in Device Contact
		//alert('no data in device contact');
		jQuery('#deviceContactTypeList').val('NoData');
	}

}

</script>