<%-- @author:
     wipro CI6 
--%>
<%-- This page will appear in the contact information div in the break-fix page --%>
<%@ page contentType="text/html" isELIgnored="false" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="java.util.List"%>
<style type="text/css"><%@ include file="../../css/jquery/jquery-ui.css" %></style><style type="text/css">
#sameAsRequestor span {
    float: none;
}
</style>
<!--<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script> -->
<jsp:include page="/WEB-INF/jsp/include.jsp"></jsp:include>

<jsp:include page="/WEB-INF/jsp/includeDatePicker.jsp"></jsp:include>

<portlet:renderURL var="secContactListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showContactListPopUp"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="primaryContactListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showContactListPopUp"></portlet:param>
</portlet:renderURL>
<portlet:resourceURL var="contactPopupReviewUrl" id="contactPopupReview"/>
<style>
.clearBoth{float: left; clear: both;}
	ul.form li label{margin-left:-78.5%}
	ul.form li {padding-left: 46% !important;}
</style>
<script type="text/javascript">

/*
 * Add new contact from popup
 */
 function addContact(){
	    document.getElementById("primaryContactDivId").style.display='block';
	    //document.getElementById("primaryContactDivId_Dflt").style.display='none';
	    //alert('in addContact lastname='+jQuery('#lastNamePopup').val());
		var ln = jQuery('#lastNamePopup').val();
		//alert('lastname :'+ln);
		var fn = jQuery('#firstNamePopup').val();
		var wp = jQuery('#workPhonePopup').val();
		var ap = jQuery('#alternatePhonePopup').val();
		var ea = jQuery('#emailPopup').val();
		//alert('ea'+ea);
		//alert("in addContact"+ln+" "+fn+" "+wp+" "+ap+" "+ea);
		try {
				if (validationFlagPopup) {
					addContactElement('', ln, fn, wp, ap, ea, false, true);
						
	
						jQuery('#lastNamePopup').val('');
						jQuery('#firstNamePopup').val('');
						jQuery('#workPhonePopup').val('');
						jQuery('#alternatePhonePopup').val('');
						jQuery('#emailPopup').val('');
						
	
						hideNewContact();			
						closeDialog();
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

		var requestorFirstName = document.getElementById("primaryFirstName").value;

		//alert('requestorFirstName='+requestorFirstName+' firstName='+'${serviceRequestConfirmationForm.loginAccountContact.firstName}');
		//alert(document.getElementById("primaryContactId").value);
		if (requestorFirstName!='${serviceRequestConfirmationForm.loginAccountContact.firstName}')
			{
				//alert('rqstEffectiveDateFlag false');
				document.getElementById('sameAsRequestor').style.display = "block";
				document.getElementById('sameAsRequestorCheckBox').checked = false;
				checkboxReset();
			}
		
		//Show the primary contact
		//alert(document.getElementById("primaryLastNameLabel").innerHTML.value);
		document.getElementById("primaryLastNameLabel").innerHTML=document.getElementById("primaryFirstName").value+" "+document.getElementById("primaryLastName").value;
		
		document.getElementById("primaryWorkPhoneLabel").innerHTML=document.getElementById("primaryWorkPhone").value;
		document.getElementById("primaryEmailAddressLabel").innerHTML=document.getElementById("primaryEmailAddress").value;

		//Hide the secondary contact if not selected
		if (document.getElementById("secondaryContactId").value=="") {
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
		document.getElementById("secondaryEmailAddr").value="";	
	}

		//Show the additional contact
		function showAddiContacts() {
			document.getElementById('buttonContact').style.display = "none";
			document.getElementById('addiContact').style.display = "block";
		}

		//Open the select contact popup
		function popupContact(contactType){
			contactListType=contactType;
			showOverlay();
			jQuery('#dialog').load(jQuery('.lightbox').attr('href'),function(){
				dialog=jQuery('#contentContact').dialog({
					autoOpen: false,
					title: jQuery('.lightbox').attr('title'),
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
				/*Commented*/
				//validateContactPopup();	
				});
			return false;
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
			
			//This is only for primary contact of the requestor
			if(contactListType=='primary')
			{
				
				jQuery("#primaryContactDivId").show(); 
				
				document.getElementById("primaryContactId").value=ci;
				document.getElementById("primaryLastName").value=ln;
				document.getElementById("primaryFirstName").value=fn;
				document.getElementById("primaryWorkPhone").value=wp;
				document.getElementById("primaryAltPhone").value= altPhn;
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

		function useRqstInfoAsPrimaryContact(){ //use login user As Primary Contact	

			var ci = "${serviceRequestConfirmationForm.loginAccountContact.contactId}";
			var ln = "${serviceRequestConfirmationForm.loginAccountContact.lastName}";
			var fn = "${serviceRequestConfirmationForm.loginAccountContact.firstName}";
			var wp = "${serviceRequestConfirmationForm.loginAccountContact.workPhone}";
			//var ap = '${serviceRequestConfirmationForm.loginAccountContact.alternatePhone}';
			var ea = "${serviceRequestConfirmationForm.loginAccountContact.emailAddress}";

			//alert('ci='+ci+' ln='+ln+' fn='+fn+' wp='+wp+' ea='+ea);
			addRqstPrimaryContactElement(ci, ln, fn, wp, ea);
			//disablePrimaryContactInputs();		
		}	
		function addRqstPrimaryContactElement(ci, ln, fn, wp, ea){
			//document.getElementById("primaryContactId").value=ci;
			document.getElementById("primaryLastName").value=ln;
			document.getElementById("primaryFirstName").value=fn;
			document.getElementById("primaryWorkPhone").value=wp;
			//document.getElementById("primaryAltPhone").value=ap;
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

</script>
<div style="padding-top: 5px;" class="contentDataId_Left">
       <div class="portlet-wrap rounded">                	
            <div class="portlet-header">  
            <h3>
             	<a href="${primaryContactListPopUpUrl}" title='<spring:message code="requestInfo.link.selectAdifferentContact"/>' class="lightbox service_add_link" onclick="return popupContact('primary')">
             		<spring:message code="requestInfo.link.selectAdifferentContact"/>
             	</a><spring:message code="requestInfo.heading.primaryContactForThisRequest"/>
            </h3>                	
			</div>
		<!-- Primary Contact Section -->
		<div id="primaryContactDivId">
             <ul class="form wordBreak">
               <li>
                 <label><spring:message code="requestInfo.label.name"/></label>
                   <span id="primaryLastNameLabel"></span>
                 </li>
               <li>
                 <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryWorkPhoneLabel"></span>
                 </li>
               <li>
                 <label><spring:message code="requestInfo.label.emailAddress"/></label>
                 <span id="primaryEmailAddressLabel"></span> 
                 </li>
               <li>
                    <label></label><div id="sameAsRequestor" align="left" style="display:none">
                    	<input type="checkBox" id="sameAsRequestorCheckBox" onclick="useRqstInfoAsPrimaryContact();"/>
						<spring:message code="serviceRequest.label.SameAsRequestor" /><br/>
                    </div>
               </li>
             </ul>
            </div>
            <!-- Primary Contact End -->
            
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
   </div>       
        <!-- Additonal contact button -->
	  <div class="columnInner" style="float:left; clear: both;padding-top:0px;margin-top:-10px;" id="buttonContact">
	  	<a class="lightbox" title='<spring:message code="button.addAnAdditionalContact"/>' onclick="return popupContact('secondary')"><spring:message code="button.addAnAdditionalContact"/></a>
		<%-- 
			<button class="button" class="lightbox" type="button"  title='<spring:message code="button.addAnAdditionalContact"/>'  onclick="return popupContact('secondary')"><spring:message code="button.addAnAdditionalContact"/></button>
		--%>	
	  </div>
 
	  <!-- Secondary Contact section -->
	<div style="padding-top: 5px;" class="contentDataId_Left">	  
	  <div class="portlet-wrap rounded" id="addiContact">
	  <div class="portlet-header">  
	      <h3>
	      	<a href="javascript:hideAddiContacts();" class="service_add_link"><spring:message code="requestInfo.link.remove"/></a>
	      	<spring:message code="requestInfo.heading.secondaryContactForThisRequest"/>
	      </h3>
	  </div>		
            <ul class="form wordBreak">
              <li>
                <label><spring:message code="requestInfo.label.name"/>${asset.assetContact.firstName}</label>
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
	   <!-- Secondary Contact -->
 	   <div id="dialog" style="display: none;"></div>