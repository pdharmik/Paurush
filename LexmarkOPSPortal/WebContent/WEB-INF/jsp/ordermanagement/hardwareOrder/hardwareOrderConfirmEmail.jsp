<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
	.w100mod{width:100px!important;}
	.w200mod{width:200px!important;}
	.w250mod{width:250px!important;}
</style>
<body>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center" style="border-width:1px; border-color:#3A7FA8; border-style:solid; background-color:#ffffff" id="emailPrintWraper">
  <tr>
    <td style="background-color:#dadada"><img src="<html:imagesPath/>/logoLexEmail.png" width="598" height="65" /></td>
  </tr>
  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff">
          <strong><spring:message code="requestInfo.heading.hardwareRequest"/></strong> &raquo; <strong><spring:message code="requestInfo.heading.requestHardware"/></strong></td>
        </tr>
      </table></td>
  </tr>
    <%--Changes BRD 12 CI 7--%>
  <tr><td><span id="accNameAgreeName"> </span></td></tr>
  <%--Ends  Changes BRD 12 CI 7--%>
  <tr>
    <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px">
    <strong><spring:message code="requestInfo.orderSupplies.orderDetails.heading.orderConfirmation"/> <span id="reqNumber"> </span></strong></td>
  </tr>
  <tr>
    <td valign="top" style="padding:10px;font-family:Arial, Helvetica, sans-serif; font-size:11px" id="message"></td>
  </tr>
  <tr>
    <td valign="top"><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span id="primaryCont_name"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="primaryCont_phone"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="primaryCont_email"> </span></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
                <spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="addtnlInfo_customerRefId"> </span></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="addtnlInfo_costCenter"> </span></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="addtnlInfo_description"> </span></td>
              </tr>
            </table></td>
        </tr>
        <tr id="secondaryContactTR">
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="secondaryCont_name"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="secondaryCont_phone"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="secondaryCont_email"> </span></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.orderSupplies.orderDetails.heading.ItemsInyourOrder"/></strong></td>
        </tr>
      </table>
       <table width="100%" border="0" cellspacing="5" cellpadding="0" id="forEmailpreconfHeader">
        <tr>
          <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.preConfhardware"/></strong></td>
        </tr>
      </table>
     <div id="forEmailpreconf"></div>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" id="forEmailaccessoriesHeader">
        <tr>
          <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.accessories"/></strong></td>
        </tr>
      </table>
     <div id="forEmailaccessories"></div>
     
     
     <c:if test="${sessionScope.hardwareFinalFlags['finalTaxCalcFlag'] == 'true' && sessionScope.hardwareFinalFlags['finalShowPriceFlag'] == 'true'}">
     
     <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc;float:right">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
             <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsPriceSubTotal"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span>${hardwareDetailPageForm.subTotal}</span></td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsTaxes"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span>${hardwareDetailPageForm.tax}</span></td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsTotalAmountPayable"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span>${hardwareDetailPageForm.totalAmt}</span></td>
              </tr>
            </table></td>
			</table>
			<div class="wFull" style="">
			<div style="color: #000000;margin:0px 10px;display: inline-block;float: left;font-weight: bold;line-height: 1.8em;width:auto;"><spring:message code="requestInfo.label.hardwareReviewNote"/></div>
			<div style="float:left;width:89%;"><i><spring:message code="requestInfo.message.hardwareTax"/></i></div>
			<div style="clear:both;"></div>
			</div>
     
     </c:if>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5" id="shipToAddrTable">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.shipToAddress"/></th>
              </tr>
              <tr>
                <td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
                <span id="shipToAddrSpan">
                <div>${hardwareDetailPageForm.shipToAddress.storeFrontName}</div>
				<util:addressOutput value="${hardwareDetailPageForm.shipToAddress}"></util:addressOutput>
                </span>                	
				</td>				
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.building"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span>${hardwareDetailPageForm.shipToAddress.physicalLocation1}</span></td>
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.floor"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span>${hardwareDetailPageForm.shipToAddress.physicalLocation2}</span></td>
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.office"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span>${hardwareDetailPageForm.shipToAddress.physicalLocation3}</span></td>
              </tr>
            </table></td>
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.orderInstructions"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.specialInstructions"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span id="spclInstruction"></span></td>
              </tr>
              
              
              
            </table></td>
        </tr>
      </table>
      <table>
      <tr><td>&nbsp;</td></tr>
      </table>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
       <tr>
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.paymentDetails"/></th>
              </tr>
       		<c:if test="${not empty hardwareDetailPageForm.poNumber && hardwareDetailPageForm.poNumber ne null}">
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><strong><spring:message code="requestInfo.label.poNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><span >${hardwareDetailPageForm.poNumber}</span></td>
              </tr>
              </c:if>
              <c:if test="${not empty hardwareDetailPageForm.creditCardEncryptedNo && hardwareDetailPageForm.creditCardEncryptedNo ne null}">
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><strong><spring:message code="requestInfo.label.creditCardNo"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><span >${hardwareDetailPageForm.creditCardEncryptedNo}</span></td>
              </tr>
              </c:if>
            </table></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.coveredService"/></strong></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="180px" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><strong><spring:message code="requestInfo.label.coveredService"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><span ><c:choose><c:when test="${hardwareDetailPageForm.installationOnlyFlag eq 'true'}"><spring:message code="requestInfo.option.yes"/></c:when><c:otherwise><spring:message code="requestInfo.option.no"/></c:otherwise></c:choose></span></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.notesAndAttachments"/></strong></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.notes"/></th>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="notes"></span></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <c:if test="${not empty hardwareDetailPageForm.attachmentList}">
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
         <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachments"/></th>
              </tr>
              <tr>
              <td>
              <span id="attachment"></span>
              </td>
              </tr>
            </table></td>
            <tr>
              	<td><div id="attachmentFailedEmail" class ="error"  style="display: none;"><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></div></td>
              </tr>
        </tr>
          		
           
      </table></c:if></td>
  </tr>
  <!-- 
  <tr>
    <td style="background-color:#ebebe4; font-family:Arial, Helvetica, sans-serif; font-size:11px; padding:10px; border-top-color:#aaaaaa; border-top-style:dotted; border-top-width:1px">Footer    
      information goes here</td>
  </tr> -->
</table>
<br>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr id="topEmail">
		<td width="55%"></td>
		<td align="right" width="25%">
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
                <img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
                <span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
            </a>&nbsp;
		</td>
		<td align="right">
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code="button.cancel"/></button>
		</td>
	</tr>
	
</table>
</body>
<script type="text/javascript">
<%--  Changes BRD 12 CI 7--%>
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
<%--Ends  Changes BRD 12 CI 7--%>
if(window.opener.window.document.getElementById("reqNumber") !=null){
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;
}
window.document.getElementById("primaryCont_name").innerHTML = window.opener.window.document.getElementById("primaryCont_name").innerHTML;
window.document.getElementById("primaryCont_phone").innerHTML = window.opener.window.document.getElementById("primaryCont_phone").innerHTML;
window.document.getElementById("primaryCont_email").innerHTML = window.opener.window.document.getElementById("primaryCont_email").innerHTML;
if(window.opener.window.document.getElementById("addiContact").style.display!="none"){
window.document.getElementById("secondaryCont_name").innerHTML = window.opener.window.document.getElementById("secondaryCont_name").innerHTML;
window.document.getElementById("secondaryCont_phone").innerHTML = window.opener.window.document.getElementById("secondaryCont_phone").innerHTML;
window.document.getElementById("secondaryCont_email").innerHTML = window.opener.window.document.getElementById("secondaryCont_email").innerHTML;
}else{
	jQuery('#secondaryContactTR').remove();
}


window.document.getElementById("addtnlInfo_customerRefId").innerHTML = window.opener.window.document.getElementById("addtnlInfo_customerRefId").innerHTML;
window.document.getElementById("addtnlInfo_costCenter").innerHTML = window.opener.window.document.getElementById("addtnlInfo_costCenter").innerHTML;
window.document.getElementById("addtnlInfo_description").innerHTML = window.opener.window.document.getElementById("addtnlInfo_description").innerHTML;

window.document.getElementById("spclInstruction").innerHTML = window.opener.window.document.getElementById("spclInstruction").innerHTML;

if(window.opener.window.document.getElementById("preConf") != null){
	jQuery('#forEmailpreconfHeader').show();
window.document.getElementById("forEmailpreconf").innerHTML = window.opener.window.document.getElementById("preConf").innerHTML;
}else{
	jQuery('#forEmailpreconfHeader').hide();
}
//window.document.getElementById("forEmailsupplies").innerHTML = window.opener.window.document.getElementById("supplies").innerHTML;
if(window.opener.window.document.getElementById("accessories") != null){
	jQuery('#forEmailaccessoriesHeader').show();
window.document.getElementById("forEmailaccessories").innerHTML = window.opener.window.document.getElementById("accessories").innerHTML;
}else{
	jQuery('#forEmailaccessoriesHeader').hide();
}


//window.document.getElementById("shipToAddrSpan").innerHTML = window.opener.window.document.getElementById("shipToAddrSpan").innerHTML;
//window.document.getElementById("shipToAddr_building").innerHTML = window.opener.window.document.getElementById("shipToAddr_building").innerHTML;
//window.document.getElementById("shipToAddr_floor").innerHTML = window.opener.window.document.getElementById("shipToAddr_floor").innerHTML;
//window.document.getElementById("shipToAddrOffice").innerHTML = window.opener.window.document.getElementById("shipToAddrOffice").innerHTML;
window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes").innerHTML;

if(window.document.getElementById("partTableHolderTd")!=null && window.opener.window.document.getElementById("partTableDiv")!=null){
	window.document.getElementById("partTableHolderTd").innerHTML = window.opener.window.document.getElementById("partTableDiv").innerHTML;
	window.document.getElementById("partTableHolderTd").innerHTML = window.opener.window.document.getElementById("attachmentDetailsDiv").innerHTML;
}
if(window.opener.window.document.getElementById("div_attachmentListPrint") != null){
window.document.getElementById("attachment").innerHTML = window.opener.window.document.getElementById("div_attachmentListPrint").innerHTML;
}

if(window.opener.window.document.getElementById("attachmentHidden").innerHTML=='attachfailed'){
	jQuery('#attachmentFailedEmail').show();
}
else{
	jQuery('#attachmentFailedEmail').hide();
}
function doEmail(){
		
		
		//alert('typeofflow='+${typeOfFlow});
		
	};

	<%-- commented for adding Aui popup
	
function email(){
	if(window.opener.window.document.getElementById("attachmentHidden").innerHTML!='attachfailed'){
		jQuery('#attachmentFailedEmail').remove();
	}
		//alert('typeofflow='+'${typeOfFlow}'');
		doEmail();
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
<%-- 
<script>
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
	if(window.opener.window.document.getElementById("attachmentHidden").innerHTML!='attachfailed'){
		jQuery('#attachmentFailedEmail').remove();
	}
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
		if(window.opener.window.document.getElementById("attachmentHidden").innerHTML!='attachfailed'){
		jQuery('#attachmentFailedEmail').remove();
	}
	jQuery(window).scrollTop(0);
	emailPopUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script>