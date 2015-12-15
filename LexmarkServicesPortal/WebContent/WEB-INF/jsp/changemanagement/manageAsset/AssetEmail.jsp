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
<style type="text/css"><%@ include file="/WEB-INF/css/grid/dhtmlxgrid_pgn_bricks.css" %></style>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
	.ie7 .lableWidth{width:10%!important;}
</style>
 <c:choose>
 <c:when test="${typeOfRequest =='Add_Asset'}">
 <%-- <c:if test="${typeOfRequest =='Add_Asset'}"> --%>
 <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
<!--  	<tr>
		<td align="left">
		&nbsp;&nbsp;<a id="topEmail" href="javascript:email();">
				<img  class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code="requestInfo.link.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr>-->
<tr>
		<td>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center" class="emailPrintWraper" id="emailPrintWraper">
 
  <tr>
    <td class="table-td-style10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" class="table-td-style11"><strong><spring:message code="requestInfo.cm.heading.changeRequest"/></strong>  &raquo;   <strong><spring:message code="requestInfo.cm.manageAsset.heading.assets" /></strong></td>
        </tr>
      </table></td>
      </tr>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <tr>
        <td><div id="accNameAgreeName"></div></td>
        </tr>
        <!-- END -->
  
  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.requestConfirmation"/></strong>
	</td>
  </tr>
    <tr>
    <td valign="top" class="table-td-style13"><spring:message code="requestInfo.message.confirmMessage1"/>  
		<span id="reqNumber"> </span>		<spring:message code="requestInfo.message.confirmMessage2"/>
		</td>
  </tr>
  <tr>
    <td valign="top"><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.primaryContactForThisRequest" /></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name" /></strong></td>
                <td valign="top" class="table-td-style16" id="firstLastName1_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="phoneNo1_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress" /></strong></td>
                <td valign="top" class="table-td-style16" id="email1_add"></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.additionalInformationForThisRequest" /></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message
										code="requestInfo.label.customerRefereceId" /></strong></td>
                <td valign="top"  class="table-td-style16" id="customerReferenceId_add"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  class="table-td-style16" id="costCenter_add"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style17" id="addtnlDescription_add"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  class="table-td-style16" id="eddate_add"></td>
              </tr>
            </table></td>
        </tr>        
        <tr id="secondaryContactRow_add" style="display: none;">
          <td  valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name" /></strong></td>
                <td valign="top" class="table-td-style16" id="firstLastName2_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="phoneNo2_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress" /></strong></td>
                <td valign="top" class="table-td-style16" id="email2_add"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr class="hr-style1" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails" /></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.serialNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="serialNumber_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.productName" /></strong></td>
                <td valign="top" class="table-td-style16" id="productLine_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.installDate" /> </strong></td>
                <td valign="top" class="table-td-style16" id="installDate_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.ipAddress" /> </strong></td>
                <td valign="top" class="table-td-style16" id="ipAddress_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.hostName" /> </strong></td>
                <td valign="top" class="table-td-style16" id="hostName_add"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.customerAssetTag" /> </strong></td>
                <td valign="top" class="table-td-style16" id="deviceTag_add"></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.manageAsset.heading.billingDetails" /></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.assetCostCenter" /></strong></td>
                <td valign="top"  class="table-td-style16" id="assetCostCenter_add"></td>
              </tr>
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.customerHierarchy" /></th>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel" /></strong></td>
                <td valign="top"  class="table-td-style16" id="chlNodeValue_add"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <div id="emailPageCountsAdd"></div>
       <table width="100%" border="0" cellspacing="0" cellpadding="5" class="table-style11">
              <tr>
                <td valign="top" width="255px" class="table-td-style16"><strong><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDevice" /></strong></td>
                <td valign="top"  class="table-td-style16" id="installAssetFlag_add"></td>
              </tr>
            
            </table>
             <table width="100%" border="0" cellspacing="0" cellpadding="5" class="table-style11">
              <tr>
                <td valign="top" width="255px" class="table-td-style16"><strong><spring:message code="requestInfo.info.heading.installDeinstallConfirm"/></strong></td>
                <td valign="top"  class="table-td-style16" id="deInstallAssetFlag"></td>
              </tr>
            
            </table>
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15">De-Install Information</th>
              </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="5" id="deinstallInfo">
						<thead>
						<tr>
						<th style="width: 12.5%"><spring:message code="requestInfo.label.printerPart"/></th>
						<th style="width: 12.5%"><spring:message code="serviceRequest.label.productModel"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.brand"/></th>
						<th style="width: 12.5%"><spring:message code="serviceRequest.listHeader.hostName"/></th>
						<th style="width: 12.5%"><spring:message code="serviceRequest.listHeader.hostName"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.assettag"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.ipaddress"/></th>
						<th style="width: 12.5%"><spring:message code="requestInfo.label.comments"/></th>
						
						</tr>
						</thead>
							 <tbody>
							<tr>									
							<td id="deInstallSerialNumber"></td>
							<td id="deInstallProductLine"></td>
							<td id="deInstallBrand"></td>
							<td id="deInstallPartNo"></td>
							<td id="deInstallHostName"></td>
							<td id="deInstallAssetTag"></td>
							<td id="deInstallIpAddress"></td>							
							<td id="deInstallComments"></td>
							</tr>
							 </tbody>
						</table>
      <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15">Install Information</th>
              </tr>
      </table>
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14">
          
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.manageAsset.heading.assetInstalledAddress" /></th>
              </tr>
              <tr>
                <td colspan="2" valign="top"  class="table-td-style16" id="installAddressAdd"></td>
                
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.building" /></strong></td>
                <td valign="top"  class="table-td-style16" id="building_add"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16" ><strong><spring:message code="requestInfo.addressInfo.label.floor" /></strong></td>
                <td valign="top"  class="table-td-style16" id="floor_add"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.office" /></strong></td>
                <td valign="top"  class="table-td-style16" id="office_add"></td>
              </tr>
            </table>
           
            </td>
         
        </tr>
      </table>
      <div id="emailAssetContact"></div>
      <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14">
          
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.notes" /></th>
              </tr>
             
              <tr>
                <td colspan="2" valign="top"  class="attachmentDescription2" id="attachmentDescription"></td>
                
              </tr>
              </table>
              <table>
         		<tr><td>&nbsp;</td></tr>
              </table>
               <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15">&nbsp;</th>
              </tr>
              </table>
    <div id="div_attachmentListPrint">
    </div>
    
     </td>
  </tr>
  
</table>
</td>
		</tr>
<tr><td><br></td></tr>
	<br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<a id="btmEmail" onClick="showEmailPopup();">
				<img src="<html:imagesPath/>transparent.png"  class="ui_icon_sprite email-icon cursor-pointer" title="<spring:message code="requestInfo.link.emailThisPage"/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>
</table>
</td></tr></table>
</c:when>
<c:when test="${typeOfRequest =='Change_Asset'}">
<%-- <c:if test="${typeOfRequest=='Change_Asset'}"> --%>
<!-- 	<tr>
		<td align="left">
		&nbsp;&nbsp;<a id="topEmail" href="javascript:email();">
				<img  class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code="requestInfo.link.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr> -->
	<tr>
	
		<td>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center" class="emailPrintWraper" id="emailPrintWraper">
 
  <tr>
    <td class="table-td-style10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" class="table-td-style11"><strong><spring:message code="requestInfo.cm.heading.changeRequest"/></strong>  &raquo;   <strong><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></strong></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.requestConfirmation"/></strong>
	</td>
  </tr>
  <tr>
    <td valign="top" class="table-td-style13"><spring:message code="requestInfo.message.confirmMessage1"/>  
		<span id="reqNumber"></span>	<spring:message code="requestInfo.message.confirmMessage2"/></td>
  </tr>
  <tr>
    <td valign="top"><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.primaryContactForThisRequest" /></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" class="table-td-style16" id="firstLastName1_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="phoneNo1_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress" /></strong></td>
                <td valign="top" class="table-td-style16" id="email1_change"></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  class="table-td-style16" id="customerReferenceId_change"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  class="table-td-style16" id="costCenter_change"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style17" id="addtnlDescription_change"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  class="table-td-style16" id="eddate_change"></td>
              </tr>
            </table></td>
        </tr>
        <tr id="secondaryContactRow_change" style="display: none;">
          <td  valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" class="table-td-style16" id="firstLastName2_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="phoneNo2_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress" /></strong></td>
                <td valign="top" class="table-td-style16" id="email2_change"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr class="hr-style1" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
         <td width="20%">
       			<div id="changeImage">	</div>
       			<div id="productTLIold_change">	</div>
        </td>
          <td width="40%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails" /></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.serialNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="serialNumber_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.productName" /></strong></td>
                <td valign="top" class="table-td-style16" id="productTLI_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.installDate" /> </strong></td>
                <td valign="top" class="table-td-style16" id="installDate_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.ipAddress" /> </strong></td>
                <td valign="top" class="table-td-style16" id="ipAddress_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.hostName" /> </strong></td>
                <td valign="top" class="table-td-style16" id="hostName_change"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.customerAssetTag" /> </strong></td>
                <td valign="top" class="table-td-style16" id="deviceTag_change"></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.manageAsset.heading.billingDetails" /></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.assetCostCenter" /></strong></td>
                <td valign="top"  class="table-td-style16" id="assetCostCenter_change"></td>
              </tr>
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.customerHierarchy" /></th>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel" /></strong></td>
                <td valign="top"  class="table-td-style16" id="chlNodeValue_change"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <div id="emailPageCountsChange"></div>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
        <th colspan="2" align="left" class="table-td-style15">
        <spring:message code="requestInfo.heading.moveInfo"/>
        </th>
        <td>
        		<tr>
                <td  width="55%" valign="top" class="table-td-style18"><strong><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToPhysicallyMoveThisDevice"/></strong></td>
               <td> <span id="installAssetFlag_change"></span></td>
              </tr>
               <%-- Commented for MPS 2.1 --%>
            <%--  <tr id="moveTypeHide">
                <td  valign="top" class="table-td-style18"><strong><spring:message code="requestInfo.cm.manageAsset.label.moveTypeConfirm"/></strong>
                <span id="installAssetFlag_change_value"></span></td>
              </tr>--%>
        </td>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.currentInstallAdd"/></th>
              </tr>
        		
            
              <tr>
                <td colspan="2" valign="top"  class="table-td-style16" id="installAddress_change">
               </td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16" ><strong><spring:message code="requestInfo.addressInfo.label.building" /></strong></td>
                <td valign="top"  class="table-td-style16" id="building_change"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth" class="table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.floor" /></strong></td>
                <td valign="top"  class="table-td-style16" id="floor_change"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.office" /></strong></td>
                <td valign="top"  class="table-td-style16" id="office_change"></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="5" class="table-style11">
            </table>
            </td>
          <td valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5" id="hideMoveToAddress">
              <tr>
                <th colspan="2" align="left" class="table-td-style15">Move to Address</th>
              </tr>
             <tr>
                <td colspan="2" valign="top"  class="table-td-style16" id="moveAddress_change">
               </td>
              </tr>
				  <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.building" /></strong></td>
                <td valign="top"  class="table-td-style16" id="movebuilding_change"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.floor" /></strong></td>
                <td valign="top"  class="table-td-style16" id="movefloor_change"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.office" /></strong></td>
                <td valign="top"  class="table-td-style16" id="moveoffice_change"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <div id="emailAssetContact"></div>
      <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14">
          
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.notes" />&nbsp;</th>
              </tr>              
              
              <tr>
                <td colspan="2" valign="top"  class="attachmentDescription2" id="attachmentDescription"></td>
                
              </tr>
              </table>
              <table>
         		<tr><td>&nbsp;</td></tr>
              </table>
               <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"></th>
              </tr>
              </table>
    <div id="div_attachmentListPrint">
    </div>
    
     </td>
  </tr>
  
</table>
      
      
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          
        </tr>
      </table></td>
  </tr>
  </table>
<!--   <tr>
		<td align="left">
		<a id="btmEmail" href="javascript:email();">
				<img  class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code="requestInfo.link.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr> -->
	<br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<a id="btmEmail" onclick="showEmailPopup();">
				<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon cursor-pointer" title="<spring:message code="requestInfo.link.emailThisPage"/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>

</td>
		</tr>

</c:when>
<c:when test="${typeOfRequest =='Decomm_Asset'}">
<%-- <c:if test="${typeOfRequest=='Decomm_Asset'}"> --%>
<!-- 	<tr >
		<td align="left">
		&nbsp;&nbsp;<a id="topEmail" href="javascript:email();">
				<img  class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code="requestInfo.link.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr> -->
<tr>
		<td>	
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center" class="emailPrintWraper" id="emailPrintWraper">
 
  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff"><strong><spring:message code="requestInfo.cm.heading.changeRequest"/></strong>  &raquo;  <strong><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></strong></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.requestConfirmation"/></strong>
	</td>
  </tr>
  <tr>
    <td valign="top" class="table-td-style13"><spring:message code="requestInfo.message.confirmMessage1"/>  
		<span id="reqNumber"> </span>		<spring:message code="requestInfo.message.confirmMessage2"/></td>
  </tr>
  <tr>
    <td valign="top"><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.primaryContactForThisRequest" /></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" class="table-td-style16" id="firstLastName1_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="phoneNo1_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress" /></strong></td>
                <td valign="top" class="table-td-style16" id="email1_del"></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  class="table-td-style16" id="customerReferenceId_del"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  class="table-td-style16" id="costCenter_del"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style17" id="addtnlDescription_del"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  class="table-td-style16" id="eddate_del"></td>
              </tr>
            </table></td>
        </tr>
        <tr id="secondaryContactRow_del" style="display: none;">
          <td  valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" class="table-td-style16" id="firstLastName2_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="phoneNo2_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress" /></strong></td>
                <td valign="top" class="table-td-style16" id="email2_del"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td>
       			<div id="delImage">	</div>
       			<div id="productTLIold_del"></div>
        </td>
          <td width="80%" valign="top" style="border-color:#cccccc; border-width:1px; vertical-align:top;border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails" /></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.serialNumber" /></strong></td>
                <td valign="top" class="table-td-style16" id="serialNumber_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.productName" /></strong></td>
                <td valign="top" class="table-td-style16" id="productLine_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.installDate" /> </strong></td>
                <td valign="top" class="table-td-style16" id="installDate_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.ipAddress" /> </strong></td>
                <td valign="top" class="table-td-style16" id="ipAddress_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.hostName" /> </strong></td>
                <td valign="top" class="table-td-style16" id="hostName_del"></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.customerAssetTag" /> </strong></td>
                <td valign="top" class="table-td-style16" id="deviceTag_del"></td>
              </tr>
            </table></td>
          
        </tr>
      </table>
      
      
      <div id="emailPageCountsDel"></div>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="260" valign="top" class="table-td-style18"><strong><spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAsset"/></strong></td>
                <td valign="top" class="table-td-style18" id="decommAssetFlag_del"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0" id="hidePickUp1">
        <tr>
          <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.cm.manageAsset.heading.pickupInformation"/></strong></td>
        </tr>
      </table>
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0" id="hidePickUp2">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.manageAsset.heading.pickupAddress"/></th>
              </tr>
              <tr>
                <tr>
                <td colspan="2" valign="top"  class="table-td-style16" id="installAddress_del">
               </td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16" ><strong><spring:message code="requestInfo.addressInfo.label.building" /></strong></td>
                <td valign="top"  class="table-td-style16" id="building_del"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.floor" /></strong></td>
                <td valign="top"  class="table-td-style16" id="floor_del"></td>
              </tr>
              <tr>
                <td valign="top" width="100" class="lableWidth table-td-style16" ><strong><spring:message code="requestInfo.addressInfo.label.office" /></strong></td>
                <td valign="top"  class="table-td-style16" id="office_del"></td>
              </tr>
              </tr>
            </table></td>
          
        </tr>
      </table>
     
     <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14">
          
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.notes" /></th>
              </tr>
              
              <tr>
                <td colspan="2" valign="top"  class="attachmentDescription2" id="attachmentDescription"></td>
                
              </tr>
              </table>
              <table>
         		<tr><td>&nbsp;</td></tr>
              </table>
               <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15">&nbsp;</th>
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


<br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<a id="btmEmail" onClick="showEmailPopup();">
				<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code="requestInfo.link.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>
 
  

</c:when>
</c:choose>

<script type="text/javascript">
jQuery(document).ready(function(){
	if(window.opener.window.document.getElementById("div_pageCountsPagingArea") !=null){
	jQuery("#div_pageCountsPagingArea").remove();
	}
});


if(window.opener.window.document.getElementById("emailAssetContact") != null){
	window.document.getElementById("emailAssetContact").innerHTML = window.opener.window.document.getElementById("emailAssetContact").innerHTML;
	}

if(window.opener.window.document.getElementById("reqNumber") !=null){
	window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;	
}

// Script for Add_Asset start
var requests="${typeOfRequest}";
	//alert("add asset");
<c:if test='${typeOfRequest =="Add_Asset"}'>
window.document.getElementById("firstLastName1_add").innerHTML = window.opener.window.document.getElementById("firstLastName1_add").innerHTML;
window.document.getElementById("phoneNo1_add").innerHTML = window.opener.window.document.getElementById("phoneNo1_add").innerHTML;
window.document.getElementById("email1_add").innerHTML = window.opener.window.document.getElementById("email1_add").innerHTML;
window.document.getElementById("firstLastName2_add").innerHTML = window.opener.window.document.getElementById("firstLastName2_add").innerHTML;
window.document.getElementById("phoneNo2_add").innerHTML = window.opener.window.document.getElementById("phoneNo2_add").innerHTML;
window.document.getElementById("email2_add").innerHTML = window.opener.window.document.getElementById("email2_add").innerHTML;
window.document.getElementById("customerReferenceId_add").innerHTML = window.opener.window.document.getElementById("customerReferenceId_add").innerHTML;
window.document.getElementById("costCenter_add").innerHTML = window.opener.window.document.getElementById("costCenter_add").innerHTML;
window.document.getElementById("addtnlDescription_add").innerHTML = window.opener.window.document.getElementById("addtnlDescription_add").innerHTML;
window.document.getElementById("eddate_add").innerHTML = window.opener.window.document.getElementById("eddate_add").innerHTML;
window.document.getElementById("serialNumber_add").innerHTML = window.opener.window.document.getElementById("serialNumber_add").innerHTML;
window.document.getElementById("productLine_add").innerHTML = window.opener.window.document.getElementById("productLine_add").innerHTML;
window.document.getElementById("installDate_add").innerHTML = window.opener.window.document.getElementById("installDate_add").innerHTML;
window.document.getElementById("ipAddress_add").innerHTML = window.opener.window.document.getElementById("ipAddress_add").innerHTML;
window.document.getElementById("hostName_add").innerHTML = window.opener.window.document.getElementById("hostName_add").innerHTML;
window.document.getElementById("deviceTag_add").innerHTML = window.opener.window.document.getElementById("deviceTag_add").innerHTML;
window.document.getElementById("assetCostCenter_add").innerHTML = window.opener.window.document.getElementById("assetCostCenter_add").innerHTML;
window.document.getElementById("chlNodeValue_add").innerHTML = window.opener.window.document.getElementById("chlNodeValue_add").innerHTML;

window.document.getElementById("installAddressAdd").innerHTML = window.opener.window.document.getElementById("installAddressAdd").innerHTML;
window.document.getElementById("building_add").innerHTML = window.opener.window.document.getElementById("building_add").innerHTML;
window.document.getElementById("floor_add").innerHTML = window.opener.window.document.getElementById("floor_add").innerHTML;
window.document.getElementById("office_add").innerHTML = window.opener.window.document.getElementById("office_add").innerHTML;
window.document.getElementById("installAssetFlag_add").innerHTML = window.opener.window.document.getElementById("installAssetFlag_add").innerHTML;
window.document.getElementById("attachmentDescription").innerHTML = window.opener.window.document.getElementById("attachmentDescription").innerHTML;
window.document.getElementById("div_attachmentListPrint").innerHTML = window.opener.window.document.getElementById("div_attachmentListPrint").innerHTML;
if(window.opener.window.document.getElementById("emailPageCounts") != null){
window.document.getElementById("emailPageCountsAdd").innerHTML = window.opener.window.document.getElementById("emailPageCounts").innerHTML;
}
if(window.opener.window.document.getElementById("deInstallInfo")){
	jQuery('#deinstallInfo').show();
	window.document.getElementById("deInstallAssetFlag").innerHTML = window.opener.window.document.getElementById("deInstallAssetFlag").innerHTML;
	window.document.getElementById("deInstallSerialNumber").innerHTML = window.opener.window.document.getElementById("deInstallSerialNumber").innerHTML;
	window.document.getElementById("deInstallProductLine").innerHTML = window.opener.window.document.getElementById("deInstallProductLine").innerHTML;
	window.document.getElementById("deInstallAssetTag").innerHTML = window.opener.window.document.getElementById("deInstallAssetTag").innerHTML;
	window.document.getElementById("deInstallIpAddress").innerHTML = window.opener.window.document.getElementById("deInstallIpAddress").innerHTML;
	window.document.getElementById("deInstallBrand").innerHTML = window.opener.window.document.getElementById("deInstallBrand").innerHTML;
	window.document.getElementById("deInstallPartNo").innerHTML = window.opener.window.document.getElementById("deInstallPartNo").innerHTML;
	window.document.getElementById("deInstallHostName").innerHTML = window.opener.window.document.getElementById("deInstallHostName").innerHTML;
	window.document.getElementById("deInstallComments").innerHTML = window.opener.window.document.getElementById("deInstallComments").innerHTML;
}else{
	jQuery('#deinstallInfo').hide();
}

if(window.opener.window.document.getElementById("addiContact").style.display!="none" ) {
	jQuery('#secondaryContactRow_add').show();
} 
else {
	jQuery('#secondaryContactRow_add').remove();
}

</c:if>
// Script for Add_Asset end

// Script for Change_Asset end

<c:if test='${typeOfRequest =="Change_Asset"}'>
window.document.getElementById("firstLastName1_change").innerHTML = window.opener.window.document.getElementById("firstLastName1_change").innerHTML;
window.document.getElementById("phoneNo1_change").innerHTML = window.opener.window.document.getElementById("phoneNo1_change").innerHTML;
window.document.getElementById("email1_change").innerHTML = window.opener.window.document.getElementById("email1_change").innerHTML;
window.document.getElementById("firstLastName2_change").innerHTML = window.opener.window.document.getElementById("firstLastName2_change").innerHTML;
window.document.getElementById("phoneNo2_change").innerHTML = window.opener.window.document.getElementById("phoneNo2_change").innerHTML;
window.document.getElementById("email2_change").innerHTML = window.opener.window.document.getElementById("email2_change").innerHTML;
window.document.getElementById("customerReferenceId_change").innerHTML = window.opener.window.document.getElementById("customerReferenceId_change").innerHTML;
window.document.getElementById("costCenter_change").innerHTML = window.opener.window.document.getElementById("costCenter_change").innerHTML;
window.document.getElementById("addtnlDescription_change").innerHTML = window.opener.window.document.getElementById("addtnlDescription_change").innerHTML;
window.document.getElementById("eddate_change").innerHTML = window.opener.window.document.getElementById("eddate_change").innerHTML;
window.document.getElementById("serialNumber_change").innerHTML = window.opener.window.document.getElementById("serialNumber_change").innerHTML;
window.document.getElementById("productTLI_change").innerHTML = window.opener.window.document.getElementById("productLine_change").innerHTML;
window.document.getElementById("installDate_change").innerHTML = window.opener.window.document.getElementById("installDate_change").innerHTML;
window.document.getElementById("ipAddress_change").innerHTML = window.opener.window.document.getElementById("ipAddress_change").innerHTML;
window.document.getElementById("hostName_change").innerHTML = window.opener.window.document.getElementById("hostName_change").innerHTML;
window.document.getElementById("deviceTag_change").innerHTML = window.opener.window.document.getElementById("deviceTag_change").innerHTML;
window.document.getElementById("assetCostCenter_change").innerHTML = window.opener.window.document.getElementById("assetCostCenter_change").innerHTML;
if(window.opener.window.document.getElementById("chlNodeValue_change")!=null){
window.document.getElementById("chlNodeValue_change").innerHTML = window.opener.window.document.getElementById("chlNodeValue_change").innerHTML;
}
if(window.opener.window.document.getElementById("installAssetFlag_change").innerHTML != null && window.opener.window.document.getElementById("installAssetFlag_change").innerHTML=="Yes"){
//window.document.getElementById("installAssetFlag_change_value").innerHTML = window.opener.window.document.getElementById("installAssetFlag_change_value").innerHTML;
//window.document.getElementById("moveTypeHide").style.display='block';
jQuery('#hideMoveToAddress').show();
}else{
	//window.document.getElementById("moveTypeHide").style.display='none';
	jQuery('#hideMoveToAddress').hide();
}
window.document.getElementById("installAssetFlag_change").innerHTML = window.opener.window.document.getElementById("installAssetFlag_change").innerHTML;
window.document.getElementById("installAddress_change").innerHTML = window.opener.window.document.getElementById("installAddressAdd").innerHTML;
window.document.getElementById("building_change").innerHTML = window.opener.window.document.getElementById("building_add").innerHTML;
window.document.getElementById("floor_change").innerHTML = window.opener.window.document.getElementById("floor_add").innerHTML;
window.document.getElementById("office_change").innerHTML = window.opener.window.document.getElementById("office_add").innerHTML;
window.document.getElementById("moveAddress_change").innerHTML = window.opener.window.document.getElementById("moveToAddress").innerHTML;
window.document.getElementById("movebuilding_change").innerHTML = window.opener.window.document.getElementById("building_move").innerHTML;
window.document.getElementById("movefloor_change").innerHTML = window.opener.window.document.getElementById("floor_move").innerHTML;
window.document.getElementById("moveoffice_change").innerHTML = window.opener.window.document.getElementById("office_move").innerHTML;


if(window.opener.window.document.getElementById("addiContact").style.display!="none") {
	jQuery('#secondaryContactRow_change').show();
} 
else {
	jQuery('#secondaryContactRow_change').remove();
}

</c:if>
// Script for change_Asset end
</script>
<script type="text/javascript">
// Script for Decommission_Asset start
//else if(requests=="Decomm_Asset")
<c:if test='${typeOfRequest =="Decomm_Asset"}'>

window.document.getElementById("firstLastName1_del").innerHTML = window.opener.window.document.getElementById("firstLastName1_del").innerHTML;
window.document.getElementById("phoneNo1_del").innerHTML = window.opener.window.document.getElementById("phoneNo1_del").innerHTML;
window.document.getElementById("email1_del").innerHTML = window.opener.window.document.getElementById("email1_del").innerHTML;
window.document.getElementById("firstLastName2_del").innerHTML = window.opener.window.document.getElementById("firstLastName2_del").innerHTML;
window.document.getElementById("phoneNo2_del").innerHTML = window.opener.window.document.getElementById("phoneNo2_del").innerHTML;
window.document.getElementById("email2_del").innerHTML = window.opener.window.document.getElementById("email2_del").innerHTML;
window.document.getElementById("customerReferenceId_del").innerHTML = window.opener.window.document.getElementById("customerReferenceId_del").innerHTML;
window.document.getElementById("costCenter_del").innerHTML = window.opener.window.document.getElementById("costCenter_del").innerHTML;
window.document.getElementById("addtnlDescription_del").innerHTML = window.opener.window.document.getElementById("addtnlDescription_del").innerHTML;
window.document.getElementById("eddate_del").innerHTML = window.opener.window.document.getElementById("eddate_del").innerHTML;
window.document.getElementById("serialNumber_del").innerHTML = window.opener.window.document.getElementById("serialNumber_del").innerHTML;
window.document.getElementById("productLine_del").innerHTML = window.opener.window.document.getElementById("productLine_del").innerHTML;
window.document.getElementById("installDate_del").innerHTML = window.opener.window.document.getElementById("installDate_del").innerHTML;
window.document.getElementById("ipAddress_del").innerHTML = window.opener.window.document.getElementById("ipAddress_del").innerHTML;
window.document.getElementById("hostName_del").innerHTML = window.opener.window.document.getElementById("hostName_del").innerHTML;
window.document.getElementById("deviceTag_del").innerHTML = window.opener.window.document.getElementById("deviceTag_del").innerHTML;
window.document.getElementById("decommAssetFlag_del").innerHTML = window.opener.window.document.getElementById("decommAssetFlag_del").innerHTML;
var decommAssetFlagDelete=window.opener.window.document.getElementById("decommAssetFlag_del").innerHTML;
if(jQuery.trim(decommAssetFlagDelete)=="no"){
	jQuery("#hidePickUp2").hide();
	jQuery("#hidePickUp1").hide();
}else{
	jQuery("#hidePickUp2").show();
	jQuery("#hidePickUp1").show();
}
if(window.opener.window.document.getElementById("installAddressAdd") !=null){
window.document.getElementById("installAddress_del").innerHTML = window.opener.window.document.getElementById("installAddressAdd").innerHTML;
}
if(window.opener.window.document.getElementById("building_add") !=null){
window.document.getElementById("building_del").innerHTML = window.opener.window.document.getElementById("building_add").innerHTML;
}
if(window.opener.window.document.getElementById("floor_add") !=null){
window.document.getElementById("floor_del").innerHTML = window.opener.window.document.getElementById("floor_add").innerHTML;
}
if(window.opener.window.document.getElementById("office_add") !=null){
window.document.getElementById("office_del").innerHTML = window.opener.window.document.getElementById("office_add").innerHTML;
}


if(window.opener.window.document.getElementById("addiContact").style.display!="none") {
	jQuery('#secondaryContactRow_del').show();
} 
else {
	jQuery('#secondaryContactRow_del').remove();
}

</c:if>
</script>
<script type="text/javascript">

if(requests=="Decomm_Asset"){
	if(window.opener.window.document.getElementById("emailPageCounts") != null){
		window.document.getElementById("emailPageCountsDel").innerHTML = window.opener.window.document.getElementById("emailPageCounts").innerHTML;
		}
	window.document.getElementById("attachmentDescription").innerHTML = window.opener.window.document.getElementById("attachmentDescription").innerHTML;
	window.document.getElementById("div_attachmentListPrint").innerHTML = window.opener.window.document.getElementById("div_attachmentListPrint").innerHTML;
}
</script>
<script type="text/javascript">
if(requests=="Decomm_Asset"){
	window.document.getElementById("delImage").innerHTML = "<img src='" + window.opener.window.document.getElementById('delImage').src + "' width='100' height='100'/>";
	window.document.getElementById("productTLIold_del").innerHTML = window.opener.window.document.getElementById("productTLIold_del").innerHTML;

}
</script>



<script type="text/javascript">
if(requests=="Change_Asset"){
	if(window.opener.window.document.getElementById("emailPageCounts") != null){
		window.document.getElementById("emailPageCountsChange").innerHTML = window.opener.window.document.getElementById("emailPageCounts").innerHTML;
		}
	window.document.getElementById("attachmentDescription").innerHTML = window.opener.window.document.getElementById("attachmentDescription").innerHTML;
	window.document.getElementById("div_attachmentListPrint").innerHTML = window.opener.window.document.getElementById("div_attachmentListPrint").innerHTML;

}
</script>
<script type="text/javascript">
if(requests=="Change_Asset"){
	window.document.getElementById("changeImage").innerHTML = "<img src='" + window.opener.window.document.getElementById('changeImage').src + "' width='100' height='100'/>";
	window.document.getElementById("productTLIold_change").innerHTML = window.opener.window.document.getElementById("productTLIold_change").innerHTML;

}
</script>
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