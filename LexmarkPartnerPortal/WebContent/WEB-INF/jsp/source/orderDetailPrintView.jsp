<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css">
<%@include file="/css/mps.css"%>
</style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />



<div id="content-wrapper-inner">
<div class="journal-content-article">
	<h1>
<!-- 	Spring message code added for CI Defect #8173 -->
		<span id="changeRequest"><spring:message code="requestInfo.heading.serviceOrder.serviceOrders"/> </span>
	</h1>

</div>
<div class="main-wrap">
      <div class="content forPage">
<div>
	<a onClick="javascript:doPrint();" id="topPrintLink"> <img
		src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px"
		 title="Print this page" /> <span
		class="cursor-pointer"><spring:message code="requestInfo.link.printThisPage"/></span>
	</a>
</div>

	<h3 class="pageTitle"><spring:message code="requestInfo.heading.serviceOrder.serviceOrderDetail"/></h3>
	<!-- ORDER INFORMATION BLOCK - Start -->
	<div class="portletBlock infoBox rounded shadow">
		<div class="columnsTwo w70p">
			<div class="columnInner">
				<h4><spring:message code="requestInfo.heading.serviceOrder.orderInformation"/></h4>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid dataGrid_top">
					<tr>
						<td width="220" class="label"><spring:message
								code="requestInfo.label.serviceOrder.requestNumber" /></td>
						<td id="requestNumber"></td>
					</tr>
					<tr>
						<td width="150" class="label"><spring:message
								code="requestInfo.label.serviceOrder.orderNumber" /></td>
						<td id="orderNumber"></td>
					</tr>
					<!-- Added for Jan release -->
					<tr>
						<td width="150" class="label"><spring:message
								code="requestInfo.label.serviceOrder.serviceType" /></td>
						<td id="serviceType"></td>
					</tr>
					<!-- End Add -->
					<tr>
						<td class="label"><spring:message
								code="requestInfo.label.serviceOrder.serviceProviderOrderRefNo" /></td>
						<td id="serviceProviderOrderRefNo"></td>
					</tr>
					<tr>
						<td class="label"><spring:message
								code="requestInfo.label.serviceOrder.openedDateAndTime" /></td>
						<td id="openedDateAndTime"></td>
					</tr>
					<tr>
						<td class="label"><spring:message
								code="requestInfo.label.serviceOrder.requestStatus" /></td>
						<td id="requestStatus"></td>
					</tr>
					<!-- Added for Jan release -->
					<tr>
						<td class="label"><spring:message
								code="requestInfo.label.serviceOrder.serialNumber" /></td>
						<td id="serialNumber"></td>
					</tr>
					<!-- End Add -->
				</table>
			</div>
		</div>
		<div class="columnsTwo w30p">
			<div class="columnInner">
				<h4>&nbsp;</h4>
				<button class="button_cancel" id="toDisable">
					<span id="buttonType"></span>
				</button>
			</div>
		</div>
	</div>


<div id="fulfillmentInformation"></div>


<div class="portletBlock infoBox rounded shadow">
	
		
			<h4>
				<spring:message
					code="requestInfo.heading.serviceOrder.processedParts" />
			</h4>
			<div class="grid-controls">
				<div class="expand-min" id="gridDisable">
					<ul>
						<li class="first"><img
							src="<html:imagesPath/>transparent.png" class="ui-icon minimize-icon" alt="Minimize All"
							title="Minimize All" /><spring:message
					code="requestInfo.label.minimizeAll" /></li>
						<li><img class="ui-icon expand-icon"src="<html:imagesPath/>transparent.png"
							alt="Expand All" title="Expand All" /><spring:message
					code="requestInfo.label.expandAll" /></li>
					</ul>
				</div>
				<!-- Expand-min End -->
			</div>
			<div id="processedParts"></div>
		
	
</div>

<div id="deliveryInformation"></div>

<div class="portletBlock infoBox rounded shadow">
	<div class="columnsOne">
		<div class="columnInner">
			<h4>
				<spring:message
					code="requestInfo.heading.serviceOrder.emailNotifications" />
			</h4>

		</div>
		<div id="emailNote"></div>
	</div>
</div>

<div>
	<a id="btmPrint" onClick="javascript:doPrint();"> <img
		src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px"
		title="Print this page" /> <span
		class="cursor-pointer"><spring:message
					code="requestInfo.link.printThisPage" /></span>
	</a>
</div>

</div>
</div>
</div>
<script type="text/javascript">
 jQuery(document).ready(function(){
	if(window.opener.window.document.getElementById("buttonType")!=null){
		window.document.getElementById("buttonType").innerHTML = window.opener.window.document.getElementById("buttonType").innerHTML;
		jQuery('#toDisable').attr('disabled',true);
		jQuery('#toDisable').attr('style','opacity:.5');
	}
	else
		jQuery('#toDisable').remove();
	if(window.opener.window.document.getElementById("requestNumber")!=null)
		window.document.getElementById("requestNumber").innerHTML = window.opener.window.document.getElementById("requestNumber").innerHTML;
 	if(window.opener.window.document.getElementById("orderNumber")!=null)
		window.document.getElementById("orderNumber").innerHTML = window.opener.window.document.getElementById("orderNumber").innerHTML;
//  	Added for Jan Release
 	if(window.opener.window.document.getElementById("serviceType")!=null)
		window.document.getElementById("serviceType").innerHTML = window.opener.window.document.getElementById("serviceType").innerHTML;
 	if(window.opener.window.document.getElementById("serialNumber")!=null)
		window.document.getElementById("serialNumber").innerHTML = window.opener.window.document.getElementById("serialNumber").innerHTML;
//  	End Add

	if(window.opener.window.document.getElementById("serviceProviderOrderRefNo")!=null)
		window.document.getElementById("serviceProviderOrderRefNo").innerHTML = window.opener.window.document.getElementById("serviceProviderOrderRefNo").innerHTML;
	if(window.opener.window.document.getElementById("openedDateAndTime")!=null)
		window.document.getElementById("openedDateAndTime").innerHTML = window.opener.window.document.getElementById("openedDateAndTime").innerHTML;
	if(window.opener.window.document.getElementById("requestStatus")!=null)
		window.document.getElementById("requestStatus").innerHTML = window.opener.window.document.getElementById("requestStatus").innerHTML;
	if(window.opener.window.document.getElementById("fulfillmentInformation")!=null)
		window.document.getElementById("fulfillmentInformation").innerHTML = window.opener.window.document.getElementById("fulfillmentInformation").innerHTML;
	if(window.opener.window.document.getElementById("processedParts")!=null)
		window.document.getElementById("processedParts").innerHTML = window.opener.window.document.getElementById("processedParts").innerHTML;
	if(window.opener.window.document.getElementById("deliveryInformation")!=null)
		window.document.getElementById("deliveryInformation").innerHTML = window.opener.window.document.getElementById("deliveryInformation").innerHTML;
	if(window.opener.window.document.getElementById("emailNote")!=null)
		window.document.getElementById("emailNote").innerHTML = window.opener.window.document.getElementById("emailNote").innerHTML;
	//alert('jjj');
	
	jQuery('#gridDisable').attr('style','opacity:.5');
	jQuery('#processedParts a').each(function(){
        //alert('here');
        /*var text=jQuery(this).text();
        jQuery(this).remove();
        jQuery(this).html(text);*/
        
        //alert('html'+jQuery(this).html());
        //jQuery(this).remove();
        jQuery(this).parent().html(jQuery(this).text());
        /*jQuery(this).removeAttr('onClick');
        jQuery(this).removeAttr('href');
        jQuery(this).attr('style','cursor: default;text-decoration: none !important;color: #333333;');*/
        
	});
 });
 	
function doPrint(){
    		window.document.getElementById("topPrintLink").style.display='none';
    		window.document.getElementById("btmPrint").style.display='none';
    		window.print();
    		window.close();
    	};
</script>
