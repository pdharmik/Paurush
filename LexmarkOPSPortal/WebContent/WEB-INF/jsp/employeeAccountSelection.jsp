<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css"><%@ include file="../css/combo/dhtmlxcombo.css" %></style> 
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxcommon.js"></script>
<script type="text/javascript"><%@ include file="../../js/combo/dhtmlxcombo.js"%></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo_whp.js"></script>

<style>
div.portlet-topper {
	background: url(/LexmarkOPSPortal/images/bg_off_customize.png) repeat-x;
	height: 30px;
	margin: 0 1px;
	padding: 0 !important;
	line-height: normal !important;
}

div.portlet-topper-inner {
	background-image:none;
	height: 30px;
	margin: 0;
	padding: 0;
}

span.portlet-title {
	font-size: 12px !important;
	display: inline;
	line-height: 32px !important;
	width: auto !important;
	margin: 0;
	padding: 0 0 0 15px;
	color: #fff;
	font-weight: bold;
}
</style>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<portlet:actionURL var="actionUrl">
	<portlet:param name="action" value="submitEmployeeAccountSelection"/>		
</portlet:actionURL>
<form id="employeeAccountSelectionForm" commandName="employeeAccountSelectionForm" method="post" action="${actionUrl}">
<input id="siebelId" type="hidden" value="${siebelIdAvailable}"/>
<div class="main-wrap">
<div class="content">
<div>

	<c:if test="${siebelIdAvailable==false}">
		<div id="selectionNotificationMsg"><br><spring:message code='employeeAccountSelection.label.message'/></div>
		<div id="selectionNotification" class="gridLoading">
			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
		</div>
	</c:if>
	<div id="socketTimeoutException">
		<spring:message code="exception.employee.response.timout.body" />
	</div>
	<div id="exception">
		<spring:message code="exception.employee.normal.exception.body" />
	</div>
	<div id="accountSelection">
		<style>
			#divEmployeeAccountSelection {
				display:inline-block;
				float:left;
				width:420px;
				}
			#btnSubmit .button {
				display:inline-block;
				float:right;
				top:0px !important;
				left:0px !important;
				margin-left:0px;
				position:relative;
				}
		</style>
	<table align="center" width="800">
		 <!-- added by rituparna -->
			<tr>
			<td width="180"></td>
			<td align="left">
				<h3><spring:message code="employeeAccountSelection.label.enterCustomerName" /></h3>
			</td>
			<td width="180"></td>
			</tr>
			<c:if test="${not empty selectedAccount}">
			<tr>
				<td align="center" colspan="3">
					<h4 style="background: none;color: black;border-bottom: none;">
						<spring:message code="employeeAccountSelection.label.selectedAccount" />
						&nbsp;&nbsp;&nbsp;${selectedAccount}
					</h4>
				</td>
			</tr>
			</c:if>
			<!-- commented by rituparna -->
			<!-- tr -->
				<!-- td align="center" width="300" -->
				<!--  	<h4><spring:message code="employeeAccountSelection.label.enterCustomerName" /></h4> -->
				<!-- 
				</td>
				<td>
				-->
			<tr>
			<!-- changed by rituparna -->
				<td width="180"></td>
				<td align="left" width="530px">
					<div id="divEmployeeAccountSelection" style="width:420px; height:30px;"></div>
					 <button id="btnSubmit" class="button" type="submit" onClick="return submitEmployeeForm()" style="float:right; display:inline-block; position:relative; top:0px !important; left:0px !important;"><spring:message code="button.submit"/></button>
				</td>

			</tr>
			<tr>
				<td align= "center" colspan="3">
					<div id="callMethodParam"></div>
				</td>
			</tr>
	</table>

	</div>
	</div>
	</div>
	</div>
</form>

<script type="text/javascript">
	
	var siebelIdAvailable = ${siebelIdAvailable};
	document.getElementById('socketTimeoutException').style.visibility = 'hidden';
	document.getElementById('exception').style.visibility = 'hidden';
	if (siebelIdAvailable==false){
	var url = "<portlet:resourceURL></portlet:resourceURL>&requireLdapUpdate=require";
	document.getElementById('accountSelection').style.visibility = 'hidden';
	jQuery("#callMethodParam").load(url,function(response){
	document.getElementById('selectionNotificationMsg').style.display = 'none';
	document.getElementById('selectionNotification').style.display = 'none';
	if(response == 'socketTimeoutException'){
		document.getElementById('accountSelection').style.visibility = 'hidden';
		document.getElementById('socketTimeoutException').style.visibility = 'visible';
	}else if(response == 'exception'){
		document.getElementById('accountSelection').style.visibility = 'hidden';
		document.getElementById('exception').style.visibility = 'visible';
	}else{
		document.getElementById('accountSelection').style.visibility = 'visible';
		
	}
	});}
</script>
<script type="text/javascript">
	window.dhx_globalImgPath = "<html:imagesPath/>/comboImgs/";
	var comboEmployeeAccountSelection = new dhtmlXCombo("divEmployeeAccountSelection", "legalName", 420);
	comboEmployeeAccountSelection.enableFilteringMode(true, "<portlet:resourceURL></portlet:resourceURL>&requireLdapUpdate=notRequire", true);
	//comboEmployeeAccountSelection.skin = "dhx_skyblue";
	
	function hideErrorMessage(id) {
		document.getElementById(id).style.display = "none";
		document.getElementById(id).style.visibility = "hidden";
	};

	function showErrorMessage(id) {
		document.getElementById(id).style.display = "";
		document.getElementById(id).style.visibility = "visible";
	};
	
	function submitEmployeeForm(){	
		callOmnitureAction('Employee Account Selection', 'Employee Account Selection Submit');
		comboEmployeeAccountSelection.confirmValue();
		var legalName = comboEmployeeAccountSelection.getSelectedValue(); 
		if (!legalName || legalName == "") {
			showError('<spring:message code="message.account.not.associated" />');
			return false;
		}
		return true;	
	}

	window.document.onkeydown = function(event){
		if(event==null){
			event=window.event;
		}
		if(event.keyCode==13){
		    document.getElementById("btnSubmit").click();
			return false;
		}
	};
    addPortlet('Employee Account Selection');
 	</script>