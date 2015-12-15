<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page contentType="text/html" isELIgnored="false" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="java.util.List"%>
<!--<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script> -->
<jsp:include page="/WEB-INF/jsp/includeDatePicker.jsp"></jsp:include>
<style type="text/css"><%@ include file="../../css/jquery/jquery-ui.css" %></style>
<style type="text/css">
#sameAsRequestor span {
    float: none;
}
</style>
 
<portlet:renderURL var="secContactListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showContactListPopUp"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="primaryContactListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showContactListPopUp"></portlet:param>
</portlet:renderURL>
<portlet:resourceURL var="contactPopupReviewUrl" id="contactPopupReview"/>

<script type="text/javascript">

/*
 * Add new contact from popup
 */
 var newContactAddFlag=false;
function addContact(){
	//alert('addcontact start validationFlagPopup='+validationFlagPopup);
	//alert('in addContact lastname='+jQuery('#lastNamePopup').val());
	var ln = jQuery('#lastNamePopup').val();
	var fn = jQuery('#firstNamePopup').val();
	var wp = jQuery('#workPhonePopup').val();
	var ap = jQuery('#alternatePhonePopup').val();
	var ea = jQuery('#emailPopup').val();
	//alert("in addContact"+ln+" "+fn+" "+wp+" "+ap+" "+ea);
	try {
	//alert('validationFlagPopup in addcontact '+validationFlagPopup);
	if (validationFlagPopup) {	
	//alert('${contactPopupReviewUrl}');
	jQuery.ajax({
		url:'${contactPopupReviewUrl}',
		type:'POST',
		data: {
				firstName : fn,
				lastName: ln,
				workPhone: wp,
				alternatePhone: ap,
				emailId: ea	
		},
		success: function(results){
			//alert("ajax success ok");

			//If error in add new contact validation, show errors from server
			//if (results!=null && results!='') {
			//alert("results "+results);		
			//jQuery("#errorMsgPopup").empty();
			//jQuery("#errorMsgPopup").append(results);			
			//jQuery("#errorMsgPopup").show();				
			//}

			//If no error in add new contact, add the contact to main page
			
			//alert("no server error");
			if(isConsContPop != null){
				if(isConsContPop=="consumablesContact")
				{
						//This is done to maintain the method signature consistency
						var ad1="";var ad2="";var city="";var state="";
						var province="";var country="";var pocode="";var phyLoc1="";
						var phyLoc2="";	var phyLoc3=""; var midName="";				
					
						//window.parent.window.addConsumablesContactElement('-1', ln, fn, wp, ea);
						window.parent.window.addConsumablesContactElement('-1', ln, fn, wp, ea, ad1,ad2,city,state,
								province,country,pocode,phyLoc1,phyLoc2,phyLoc3,midName,ap);
						isConsContPop=null;
				}
			}
			/****This is added for decom asset create new contact from popup****/
			else if(siteContactPopUp!=null)
			{
				if(siteContactPopUp=="siteContactPrimary")
				{
						
						window.parent.window.addSiteContactElement('-1', ln, fn, wp, ea, ap);
						siteContactPopUp=null;
				}
			}
			else if(deviceContactPopUp!=null && deviceContactPopUp!="false")
			{
			 newContactAddFlag=true;
						
					window.parent.window.addDeviceContactElement('-1', ln, fn, wp, ea, ap, deviceContactPopUp);
					deviceContactPopUp="false";
			}
			/*********End of the section**********/
			else {
				addContactElement('', ln, fn, wp, ap, ea, false, true);
			}

			jQuery('#lastNamePopup').val('');
			jQuery('#firstNamePopup').val('');
			jQuery('#workPhonePopup').val('');
			jQuery('#alternatePhonePopup').val('');
			jQuery('#emailPopup').val('');

			hideNewContact();			
			closeDialog();
			
			}
		});
	}
	}
	catch (e) {
		//alert(e);
	}
}

var contactListType="";
var dialog;
var linkId;

	jQuery(document).ready( function() {

		if(jQuery('#effDtOfChange').val()==''){
		  	jQuery('#effDtOfChangeDelete').hide();
		}
		
		var url=document.URL;		
		var newUrl=url.substring(0,url.indexOf("?"));
		if(newUrl==''){
			newUrl = url;
		}
		newUrl=newUrl.substring(newUrl.lastIndexOf("/")+1);
		newUrl=$.trim(newUrl);
		//alert("trimmed url is "+ newUrl);
		
		
		if(newUrl=="consumableorder" || newUrl=="catalogorder" || newUrl=="customer-requests" || newUrl =="hardwareorder")
		{
			document.getElementById('effctiveDateshow').style.display = "none";
			if(document.getElementById("primaryFirstName").value==''){
				useMyInfoAsPrimaryContact();
			}
		}

		var requestorFirstName = document.getElementById("primaryFirstName").value;
		
		if (requestorFirstName!="${requestorContact.firstName}")
			{
				//alert('rqstEffectiveDateFlag false');
				document.getElementById('sameAsRequestor').style.display = "block";
				document.getElementById('sameAsRequestorCheckBox').checked = false;
				checkboxReset();
			}		
		
		document.getElementById("primaryLastNameLabel").innerHTML=document.getElementById("primaryFirstName").value+" "+document.getElementById("primaryLastName").value;
		//document.getElementById("primaryFirstNameLabel").innerHTML=document.getElementById("primaryFirstName").value;
		document.getElementById("primaryWorkPhoneLabel").innerHTML=document.getElementById("primaryWorkPhone").value;
		document.getElementById("primaryEmailAddressLabel").innerHTML=document.getElementById("primaryEmailAddress").value;

		if (document.getElementById("secondaryFirstName").value=="" && document.getElementById("secondaryLastName").value=="") {
			hideAddiContacts();
		}		
		//Show the secondary contact if selected
		else {
			document.getElementById("secondarylastNameLabel").innerHTML=document.getElementById("secondaryFirstName").value+" "+document.getElementById("secondaryLastName").value;
			//document.getElementById("secondaryfirstNameLabel").innerHTML=document.getElementById("secondaryFirstName").value;
			document.getElementById("secondaryworkPhoneLabel").innerHTML=document.getElementById("secondaryWorkPhone").value;
			document.getElementById("secondaryemailAddressLabel").innerHTML=document.getElementById("secondaryEmailAddr").value;
			showAddiContacts();
		}
		
	});

	//Hide the additional contacts
	function hideAddiContacts() {
		//alert('in hideAddiContacts');
		document.getElementById('buttonContact').style.display = "block";
		document.getElementById('addiContact').style.display = "none";

		document.getElementById("secondaryContactId").value="";
		document.getElementById("secondaryFirstName").value="";
		document.getElementById("secondaryLastName").value="";
		document.getElementById("secondaryWorkPhone").value="";
		document.getElementById("secondaryAltPhone").value="";
		document.getElementById("secondaryEmailAddr").value="";	

		//alert('window PIE');
		if (window.PIE) {
			//alert('window PIE inside if');
            document.getElementById("addiContactButton").fireEvent("onmove");
            /*jQuery(".button").trigger("onmove");*/
        }
	}
	function hideMapsContacts()
	{
		document.getElementById('mapsRequestContact').style.display = "none";

		document.getElementById("mapsRequestContactId").value="";
		document.getElementById("mapsRequestFirstName").value="";
		document.getElementById("mapsRequestLastName").value="";
		document.getElementById("mapsRequestWorkPhone").value="";
		document.getElementById("mapsRequestAltPhone").value="";
		document.getElementById("mapsRequestEmailAddr").value="";	

	}

		//Show the additional contact
		function showAddiContacts() {
			document.getElementById('buttonContact').style.display = "none";
			document.getElementById('addiContact').style.display = "block";
		}
		 function showMapsContacts()
		 {
		
			document.getElementById('mapsRequestContact').style.display = "block"; 
		 }

		//Open the select contact popup
	var portlet_name="";
     var action_primary="";
     var action_secondary="";
     var action_mapsRequest="";
     
		function popupContact(contactType){
			switch(contactType){
			case "primary":
				 callOmnitureAction(portlet_name,action_primary); 
				
				break;
			case "secondary":
				callOmnitureAction(portlet_name,action_secondary); 
				break;
			case "mapsRequest":
				callOmnitureAction(portlet_name,action_mapsRequest); 
				break;
			}


			contactListType=contactType;
			showOverlay();
			jQuery('#dialog').load(jQuery('.lightbox').attr('href'),function(){
				dialog=jQuery('#contentContact').dialog({
					autoOpen: false,
					title: contactListType=="mapsRequest"?jQuery('#mapContactHeading').attr('title'):jQuery('.lightbox').attr('title'),
					modal: true,
					draggable: false,
					resizable: false,
					height: 550,
					width: 800,
					close: function(event,ui){
						closeMapContacts();
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
			return false;
		}		
		function closeMapContacts()
		{
			if(contactListType=='mapsRequest'){
				if(jQuery("#mapsRequestContact").css('display')=="none"){

					jQuery("#moveContactSelect").attr("checked",false).trigger('click');
				}
			}
		}
		//Close the select contact popup
		function closeDialog()
		{
			//alert("in closedialogue contactListType="+contactListType);
			dialog.dialog('close');	
			dialog=null;
		}
		
		function addContactElement(ci, ln, fn, wp, altPhn, ea, updateContactFlag, newContactFlag) {
			
			if(contactListType=='secondary')
			{
				//This is a secondary contact req
			
				document.getElementById("secondaryContactId").value=ci;
				document.getElementById("secondaryFirstName").value=fn;
				document.getElementById("secondaryLastName").value=ln;
				document.getElementById("secondaryWorkPhone").value=wp;
				document.getElementById("secondaryAltPhone").value=altPhn;
				document.getElementById("secondaryEmailAddr").value=ea;
				document.getElementById("secondaryUpdateContactFlag").value=updateContactFlag;
				document.getElementById("secondaryNewContactFlag").value=newContactFlag;
				
				//just for display purpose  secondary contact
				document.getElementById("secondarylastNameLabel").innerHTML=fn+" "+ln;
				//document.getElementById("secondaryfirstNameLabel").innerHTML=fn;
				document.getElementById("secondaryworkPhoneLabel").innerHTML=wp;
				document.getElementById("secondaryemailAddressLabel").innerHTML=ea;
				showAddiContacts();
			}
			if(contactListType=='mapsRequest')
			{
			
				document.getElementById("mapsRequestContactId").value=ci;
				document.getElementById("mapsRequestFirstName").value=fn;
				document.getElementById("mapsRequestLastName").value=ln;
				document.getElementById("mapsRequestWorkPhone").value=wp;
				document.getElementById("mapsRequestAltPhone").value=altPhn;
				document.getElementById("mapsRequestEmailAddr").value=ea;
				document.getElementById("mapsRequestUpdateContactFlag").value=updateContactFlag;
				document.getElementById("mapsRequestNewContactFlag").value=newContactFlag;
				
				//just for display purpose  secondary contact
				document.getElementById("mapsRequestLastNameLabel").innerHTML=fn+" "+ln;
				//document.getElementById("secondaryfirstNameLabel").innerHTML=fn;
				document.getElementById("mapsRequestWorkPhoneLabel").innerHTML=wp;
				document.getElementById("mapsRequestEmailAddressLabel").innerHTML=ea;
				showMapsContacts();
			}
			
			//This is only for primary contact of the requestor
			if(contactListType=='primary')
			{
				document.getElementById("primaryContactId").value=ci;
				document.getElementById("primaryLastName").value=ln;
				document.getElementById("primaryFirstName").value=fn;
				document.getElementById("primaryWorkPhone").value=wp;
				document.getElementById("primaryAltPhone").value=altPhn;
				document.getElementById("primaryEmailAddress").value=ea;
				document.getElementById("primaryUpdateContactFlag").value=updateContactFlag;
				document.getElementById("primaryNewContactFlag").value=newContactFlag;
				
				//just for display purpose  primary contact      
				document.getElementById("primaryLastNameLabel").innerHTML=fn+" "+ln;
				//document.getElementById("primaryFirstNameLabel").innerHTML=fn;
				document.getElementById("primaryWorkPhoneLabel").innerHTML=wp;
				document.getElementById("primaryEmailAddressLabel").innerHTML=ea;
				
				document.getElementById('sameAsRequestor').style.display = "block";
				document.getElementById('sameAsRequestorCheckBox').checked = false;
				checkboxReset();
			}
		}

		function useMyInfoAsPrimaryContact(){ //use login user As Primary Contact	

			//alert('first name= ${requestorContact.firstName}');	
			var ci = '${requestorContact.contactId}';
			var ln = "${requestorContact.lastName}";
			var fn = "${requestorContact.firstName}";
			var wp = '${requestorContact.workPhone}';
			var ap = '${requestorContact.alternatePhone}';
			var ea = '${requestorContact.emailAddress}';

			//alert('ci='+ci+' ln='+ln+' fn='+fn+' wp='+wp+' ea='+ea);
			addPrimaryContactElement(ci, ln, fn, wp, ap, ea);
			//disablePrimaryContactInputs();		
		}	
		function addPrimaryContactElement(ci, ln, fn, wp, ap, ea){
			document.getElementById("primaryContactId").value=ci;
			document.getElementById("primaryLastName").value=ln;
			document.getElementById("primaryFirstName").value=fn;
			document.getElementById("primaryWorkPhone").value=wp;
			document.getElementById("primaryAltPhone").value=ap;
			document.getElementById("primaryEmailAddress").value=ea;
			
			document.getElementById("primaryUpdateContactFlag").value=false;
			document.getElementById("primaryNewContactFlag").value=false;	
			
			//just for display purpose  primary contact      
			document.getElementById("primaryLastNameLabel").innerHTML=fn+" "+ln;
			//document.getElementById("primaryFirstNameLabel").innerHTML=fn;
			document.getElementById("primaryWorkPhoneLabel").innerHTML=wp;
			document.getElementById("primaryEmailAddressLabel").innerHTML=ea;	
			document.getElementById('sameAsRequestor').style.display = "none";		
		}

		function removeDateCommon(id, id1){	
			//alert('in removeDateCommon');	
			jQuery('#' + id).val('');
			jQuery('#' +id1).hide();		
		}
		function shwDateCommon(id, id1){	
			//alert('in shwDateCommon');	
			if(jQuery('#' +id).val() != ''){
					jQuery('#' +id1).show();
				}	
		}
			
</script>

<div class="portletBlock">
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">                	

                <h4><a href="${primaryContactListPopUpUrl}" title='<spring:message code="requestInfo.link.selectAdifferentContact"/>' class="lightbox" onclick="return popupContact('primary')">
               <spring:message code="requestInfo.link.selectAdifferentContact"/></a><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>                	

				<!-- Primary Contact Section -->
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>

                    <span id="primaryLastNameLabel"></span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="primaryWorkPhoneLabel"></span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="primaryEmailAddressLabel"></span> </li>
                   <li>
                    <label></label><div id="sameAsRequestor" align="left" style="display:none">
                    	<input type="checkBox" id="sameAsRequestorCheckBox" onclick="useMyInfoAsPrimaryContact();"/>
						<spring:message code="serviceRequest.label.SameAsRequestor" /><br/>
                    </div></li>                  
                </ul>
                
                <!-- Hidden fields to bind the primary contact data with form -->
                <span style="display: none">
	                	<form:input id="primaryContactId" path="serviceRequest.primaryContact.contactId" /> 
						<form:input id="primaryFirstName" path="serviceRequest.primaryContact.firstName" /> 
						<form:input id="primaryLastName" path="serviceRequest.primaryContact.lastName" /> 
						<form:input id="primaryWorkPhone" path="serviceRequest.primaryContact.workPhone" />
						<form:input id="primaryAltPhone" path="serviceRequest.primaryContact.alternatePhone" /> 
						<form:input id="primaryEmailAddress" path="serviceRequest.primaryContact.emailAddress" />
						<form:input id="primaryUpdateContactFlag" path="serviceRequest.primaryContact.updateContactFlag" />
						<form:input id="primaryNewContactFlag" path="serviceRequest.primaryContact.newContactFlag" />
				</span>
					
              </div>
              
              <!-- Additonal contact button -->
			  <div class="addiLink" id="buttonContact">
			  		<a class="lightbox floatR" id="addiContactButton" title='<spring:message code="button.addAnAdditionalContact"/>' onclick="return popupContact('secondary')"><spring:message code="button.addAnAdditionalContact"/></a>
<%-- 				<button class="button lightbox" id="addiContactButton" type="button"  title='<spring:message code="button.addAnAdditionalContact"/>'  onclick="return popupContact('secondary')"><spring:message code="button.addAnAdditionalContact"/></button> --%>
			  </div>
			  
			  <!-- Secondary Contact section -->
			  <div class="infoBox columnInner rounded shadow" id="addiContact" style="display:none;">

                <h4><a href="javascript:hideAddiContacts();"><spring:message code="requestInfo.link.remove"/></a><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>

                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>

                    <span id="secondarylastNameLabel"></span>                    
                    </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="secondaryworkPhoneLabel"></span>
                     </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="secondaryemailAddressLabel"></span>
                     </li>
                </ul>
                <!-- Hidden fields to bind the secondary contact data with form -->
                <span style="display: none">
						<form:input id="secondaryContactId" path="serviceRequest.secondaryContact.contactId" /> 
						<form:input id="secondaryFirstName" path="serviceRequest.secondaryContact.firstName" /> 
						<form:input id="secondaryLastName" path="serviceRequest.secondaryContact.lastName" /> 
						<form:input id="secondaryWorkPhone" path="serviceRequest.secondaryContact.workPhone" /> 
						<form:input id="secondaryAltPhone" path="serviceRequest.secondaryContact.alternatePhone" />
						<form:input id="secondaryEmailAddr" path="serviceRequest.secondaryContact.emailAddress" />
						<form:input id="secondaryUpdateContactFlag" path="serviceRequest.secondaryContact.updateContactFlag" />
						<form:input id="secondaryNewContactFlag" path="serviceRequest.secondaryContact.newContactFlag" />
				</span>
              </div>
			  
            </div>
			
			<!-- Show additonal request information -->
			<div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
                <ul class="form">
                  <li>
                    <label for="custReferenceId"><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span><form:input id="custReferenceId" path="serviceRequest.customerReferenceId" maxlength="64"/>
					<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.tooltip.customerRefereceId"/>' ></span>
				  </li>
                  <li>
                    <label for="costCenter"><spring:message code="requestInfo.label.costCentre"/></label>
                    <span><form:input id="costCenter" path="serviceRequest.costCenter" maxlength="200" />
					<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.tooltip.costCentre"/>' ></span>
				  </li>
                  <li>
                    <label for="addtnlDescription"><spring:message code="requestInfo.label.description"/></label>
                    <span><form:textarea id="addtnlDescription" rows="3" path="serviceRequest.addtnlDescription" maxlength="255" />
                    <img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.tooltip.description"/>' ></span> 
				  </li>
				  
				  <%--<c:if test="${rqstEffectiveDateFlag eq 'showDate'}">--%>
				  <li id="effctiveDateshow"><label for="effDtOfChange"><spring:message code="requestInfo.label.dateOfChange"/></label>
				  <span><form:input id="effDtOfChange" size="10" readonly="true" path="serviceRequest.requestedEffectiveDate" class="w100" maxlength="7" value="" onchange="shwDateCommon('effDtOfChange','effDtOfChangeDelete');" onblur="shwDateCommon('effDtOfChange','effDtOfChangeDelete');"/>
					<img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onclick="showCal('effDtOfChange',convertDateToString(new Date().addDays(null)), null, false);" />
					<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.tooltip.dateOfChange"/>'>
					<img id="effDtOfChangeDelete" class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" onClick="javascript:removeDateCommon('effDtOfChange', 'effDtOfChangeDelete');"/></span>
				  </li>
				  <%-- </c:if>--%>
                </ul>
			  </div>
            </div>
</div>    
<div id="dialog" style="display: none;"></div>            