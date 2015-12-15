<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
<script type="text/javascript">
jQuery(document).ready( function() {
	var summaryType = '${manageContactForm.pageName}';
	//alert('summaryType='+summaryType);
	if (summaryType=='changeContact') {
		
		if ('${manageContactForm.accountContact.workPhone}'=='' || '${manageContactForm.accountContact.workPhone}'==null)
			jQuery('#workPhone').html("${oldContactChange['workPhone']}");
		else 
			jQuery('#workPhone').html('${manageContactForm.accountContact.workPhone}');

		if ('${manageContactForm.accountContact.alternatePhone}'=='' || '${manageContactForm.accountContact.alternatePhone}'==null)
			jQuery('#alternatePhone').html("${oldContactChange['alternatePhone']}");
		else 
			jQuery('#alternatePhone').html('${manageContactForm.accountContact.alternatePhone}');

		if ('${manageContactForm.accountContact.emailAddress}'=='' || '${manageContactForm.accountContact.emailAddress}'==null)
			jQuery('#emailAddress').html("${oldContactChange['emailId']}");
		else 
			jQuery('#emailAddress').html('${manageContactForm.accountContact.emailAddress}');
		
	}
});
</script>
<table width="100%">
<!-- 	<tr id="topEmail">
		<td align="left">
		&nbsp;&nbsp;<a id="btmEmail" href="javascript:email();">
				<img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" height="23px" width="23px" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr> -->

<tr>
<td>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center" style="border-width:1px; border-color:#3A7FA8; border-style:solid; background-color:#ffffff" id="emailPrintWraper">
 
  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff"><strong><spring:message code="requestInfo.cm.heading.changeRequest"/></strong> &raquo;    <strong><spring:message code="requestInfo.cm.manageContact.heading.contacts"/></strong></td>
        </tr>
      </table></td>
      </tr>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <tr>
        <td><div id="accNameAgreeName"></div></td>
        </tr>
        <!-- END -->
        
  </tr>
  <tr>
    <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.requestConfirmation"/>
	<span id="reqNumber"></span></strong></td>
  </tr>
  <tr>
    <td valign="top" style="padding:10px;font-family:Arial, Helvetica, sans-serif; font-size:11px" id='srInfoContact'></td>
  </tr>
  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${manageContactForm.serviceRequest.primaryContact.firstName}
                    ${manageContactForm.serviceRequest.primaryContact.lastName}</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="phoneNumber1">${manageContactForm.serviceRequest.primaryContact.workPhone}</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddress1">${manageContactForm.serviceRequest.primaryContact.emailAddress}</td>
              </tr>
          </table></td>
          <td rowspan="2" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="customerReferenceid">${manageContactForm.serviceRequest.customerReferenceId}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="costCenter">${manageContactForm.serviceRequest.costCenter}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="description">${manageContactForm.serviceRequest.addtnlDescription}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="reqEffectiveDate">
                <util:dateFormat value="${manageContactForm.serviceRequest.requestedEffectiveDate}">
				</util:dateFormat></td>
              </tr>
            </table></td>
        </tr>
        <c:if test='${manageContactForm.serviceRequest.secondaryContact.lastName ne ""}'>
        <tr>
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/><strong></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="firstLastName2">${manageContactForm.serviceRequest.secondaryContact.firstName }
					${manageContactForm.serviceRequest.secondaryContact.lastName }</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="phoneNumber2">${manageContactForm.serviceRequest.secondaryContact.workPhone }</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddress2">${manageContactForm.serviceRequest.secondaryContact.emailAddress }</td>
              </tr>
          </table></td>
        </tr>
       </c:if>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      
      <!-- REQUEST INFORMATION GOES BELOW THIS -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              
              <c:choose>
		  		<c:when test="${manageContactForm.pageName eq 'addContact'}">
	              <tr id="newContact">
	                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.newContact"/></th>
	              </tr>
              	</c:when>
              	<c:when test="${manageContactForm.pageName eq 'changeContact'}">
	               <tr id="changeContact">
	                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.cm.manageContact.heading.changedContact"/></th>
	              </tr>
	            </c:when>
              	<c:when test="${manageContactForm.pageName eq 'removeContact'}">
	               <tr id="removeContact">
	                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.cm.manageContact.heading.selectedContact"/></th>
	              </tr>
	            </c:when>
              </c:choose>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.contactInfo.label.firstName"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="firstName">${manageContactForm.accountContact.firstName}</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.contactInfo.label.middleName"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="middleName">${manageContactForm.accountContact.middleName}</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.contactInfo.label.lastName"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="lastName">${manageContactForm.accountContact.lastName}</td>
              </tr>
              <c:choose>
		  		<c:when test="${manageContactForm.pageName eq 'changeContact'}">
	              <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.contactInfo.label.workPhone"/></strong></td>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="workPhone"></td>
	              </tr>
	              <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.contactInfo.label.alternatePhone"/></strong></td>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="alternatePhone"></td>
	              </tr>
	              <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddress"></td>
	              </tr>
            	 </c:when>                      
                 <c:otherwise>
	              <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.contactInfo.label.workPhone"/></strong></td>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="workPhone">${manageContactForm.accountContact.workPhone}</td>
	              </tr>
	              <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.contactInfo.label.alternatePhone"/></strong></td>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="alternatePhone">${manageContactForm.accountContact.alternatePhone}</td>
	              </tr>
	              <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddress">${manageContactForm.accountContact.emailAddress}</td>
	              </tr>
                 </c:otherwise>
               </c:choose>
            </table></td>
        </tr>
       
      </table>
      <c:choose>
		<c:when test='${manageContactForm.pageName eq "changeContact"}'>	
      		<table width="100%" border="0" cellspacing="0" cellpadding="5">
      		<tr>
	          	<td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
	          
	              <tr>
	                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
	                <spring:message code="requestInfo.heading.contactAddress" /></th>
	              </tr>
	              <tr>
	                <td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
	                <div id="addressLine1_add">${manageContactForm.accountContact.address.addressLine1}</div>
					<div id="addressLine2_add">${manageContactForm.accountContact.address.addressLine2}</div>
					<span id="city_add">${manageContactForm.accountContact.address.city}</span>,
					<span id="state_add">${manageContactForm.accountContact.address.state} </span>
					<span id="province_add">${manageContactForm.accountContact.address.province}</span>  
					<span id="postalCode_add">${manageContactForm.accountContact.address.postalCode}</span>
					<div id="country_add">${manageContactForm.accountContact.address.country}</div></td>
	              </tr>
	              <tr>
	                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.building" /></strong></td>
	                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="building_add">${manageContactForm.accountContact.address.physicalLocation1}</td>
	              </tr>
	              <tr>
	                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.floor" /></strong></td>
	                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="floor_add">${manageContactForm.accountContact.address.physicalLocation2}</td>
	              </tr>
	              <tr>
	                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.office" /></strong></td>
	                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="office_add">${manageContactForm.accountContact.address.physicalLocation3}</td>
	              </tr>
	              </table>
	            </td>
              </tr>
            </table>
            </c:when>
		  
		  <c:when test='${manageContactForm.pageName eq "addContact"}'>	
            
            <table width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
          
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
               <spring:message code="requestInfo.cm.manageContact.heading.newContactAddress"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.addressLine1"/> </strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.addressLine1}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.addressLine2" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.addressLine2}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.city" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.city}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.country" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.country}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.stateProvince" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.state}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.postalCode" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.postalCode}</td>
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.building" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${manageContactForm.accountContact.address.physicalLocation1}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.floor" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.physicalLocation2}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.office" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" >${manageContactForm.accountContact.address.physicalLocation3}</td>
              </tr>
              </table>
              </td>
              </tr>
            </table>
      
      </c:when>
		  </c:choose>
		   <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.notes" /></th>
              </tr>
              <tr>
             <td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> &nbsp;</td>
             </tr>
              <tr>
                <td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="attachmentDescription"></td>
                
              </tr>
              </table>
               <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachments" /></th>
              </tr>
              </table>
    <div id="div_attachmentListPrint">
    </div>
    
     </td>
  </tr>
  
</table>
      </td>
  </tr>

</table>
</td>
</tr>		
	<tr><td><br></td></tr>
<!-- 	<tr>
		<td align="left">
		<a id="btmEmail" href="javascript:email();">
				<img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png"  height="23px" width="23px" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr> -->
	<br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right" >
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
				<img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>
</table>
<script type="text/javascript">

window.document.getElementById("srInfoContact").innerHTML = window.opener.window.document.getElementById("srInfoContact").innerHTML;
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;
/* Added for CI7 BRD14-02-12 */
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
</script>
<script type="text/javascript">
window.document.getElementById("attachmentDescription").innerHTML = window.opener.window.document.getElementById("attachmentDescription").innerHTML;
window.document.getElementById("div_attachmentListPrint").innerHTML = window.opener.window.document.getElementById("div_attachmentListPrint").innerHTML;

/* END */
		

		
		function doEmail(){
    		//window.document.getElementById("topEmail").style.display='none';
    		window.document.getElementById("btmEmail").style.display='none';
    		
    		
    	};

    	<%--  Commented for AUI popup
    	function email(){
    		
    		//doEmail();
    		new Liferay.Popup({
    		title: "<spring:message code='requestInfo.title.emailConfirm'/>",
    	  	position: ['center','top'],
    	  	modal: true,
    	  	width: 550, 
    	  	height: 'auto',
    	  	xy: [100, 100],
    	  	onClose: function() {showSelect();},
    	  	url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>"
    	  	});
    	
    		};  --%>
</script>
<%-- AUI popup added --%>
<%-- <script>
var emailPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 650,
	height: 350,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
function showEmailPopup(){
	doEmail();
	emailPopUpWindow.show();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script> --%>

<script type="text/javascript">
emailFunction();
var emailPopUpWindow;
function emailFunction(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	position: [-80,20],
	resizable: false,
	width: 650,
	height: 350,
	xy: [-80, 20],
	destroyOnClose: true,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
}
function showEmailPopup(){
	jQuery(window).scrollTop(0);
	emailPopUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script>
