<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css"><%@ include file="../../css/combo/dhtmlxcombo.css" %></style> 
<script type="text/javascript"><%@ include file="../../js/combo/dhtmlxcombo.js"%></script>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%--Added by sankha for LEX:AIR00071291 start --%>

<style>

.ie .button, .ie .all_button, .ie .star_button, .ie .button_cancel, .ie #loginCancelBtn{
	position: relative;
	behavior: url(/LGS_Partner_Portal_Rebranded-theme/css/PIE.htc);
}
</style>
<%--Added by sankha for LEX:AIR00071291 end --%>

<portlet:actionURL var="actionUrl">
	<portlet:param name="action" value="submitEmployeeAccountSelection"/>		
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<form id="employeeAccountSelectionForm" name="employeeAccountSelectionForm" commandName="employeeAccountSelectionForm" method="post" action="${actionUrl}">
<input id="siebelId" type="hidden" value="${siebelIdAvailable}"/>
<input id="showFseSelector" name="showFseSelector" type="hidden" value="${showFseSelector}"/>
<div class="main-wrap">
<div class="content">
	<div>
	<c:if test="${employeeProblem==false}">
		<c:if test="${showFseSelector==false && siebelIdAvailable==false}">
			<div id="selectionNotificationMsg"><br><spring:message code='employeeAccountSelection.label.message'/></div>
			<div id="selectionNotification" class="gridLoading">
				<br><spring:message code='label.loadingNotification'/>&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
			</div>
		</c:if>
		<div id="socketTimeoutException">
			<spring:message code="exception.employee.response.timout.body" />
		</div>
		<div id="exception">
			<spring:message code="exception.employee.normal.exception.body" />
		</div>
		<div id="accountSelection">
		<table align="center" width="800">
				<tr>
				<td colspan="3" align="center">
					<c:if test="${showFseSelector==true}">
						<h3><spring:message code="message.account.not.associated" /></h3>										
					</c:if>
					<c:if test="${showFseSelector==false}">
						<h3><spring:message code="employeeAccountSelection.label.enterCustomerName" /></h3>					
					</c:if>
				</td>
				</tr>
				<c:if test="${not empty selectedAccount}">
				<tr>
					<td align="center" colspan="3">
						<h4>
							<spring:message code="employeeAccountSelection.label.selectedAccount" />
							&nbsp;&nbsp;&nbsp;${selectedAccount}
						</h4>
					</td>
				</tr>
				</c:if>
				<tr>
					<td width="180"></td>
					<td align="left">
						<c:if test="${showFseSelector==true}">
							<select id="selectFseAccount" name="accountNumber" class="selectFseAccount" style= "width: 420px ! important;" >
								<option value=""></option>
			 	  				<c:forEach items="${fSEAccountListResult.accountList}" var="accounts">
			 	  					<option value="${accounts.accountId}">${accounts.accountName} (${accounts.accountId})</option>
								</c:forEach>
							</select>
							<input id="legalName" name="legalName" type="hidden"/>
																	
						</c:if>
						<c:if test="${showFseSelector==false}">
							<div id="divEmployeeAccountSelection" class="divEmployeeAccountSelection" ></div>		
						</c:if>
					</td>
					<td align="left" width="180">
						 <a href="javascript:submitEmployeeForm();" class="button" id="btnSubmit"><spring:message code="button.submit"/></a>
					</td>
				</tr>
				<tr>
					<td align= "center" colspan="3">
						<div id="callMethodParam"></div>
					</td>
				</tr>
		</table>
	
		</div>
	</c:if>
	<c:if test="${employeeProblem==true}">
		<h1><spring:message code='employeeAccountSelection.employeeSetupProblem.message'/></h1>
	</c:if>
	</div>
	</div>
	</div>
</form>

<script type="text/javascript">
	
	var siebelIdAvailable = ${siebelIdAvailable};
	var employeeProblem = ${employeeProblem};
	var showFseSelector = ${showFseSelector};
	
	document.getElementById('socketTimeoutException').style.visibility = 'hidden';
	document.getElementById('exception').style.visibility = 'hidden';
	if (employeeProblem==false && showFseSelector==false && siebelIdAvailable==false){
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
	window.dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
	if (employeeProblem==false && showFseSelector==false){
		var comboEmployeeAccountSelection = new dhtmlXCombo("divEmployeeAccountSelection", "legalName", 420);
		comboEmployeeAccountSelection.enableFilteringMode(true, "<portlet:resourceURL></portlet:resourceURL>&requireLdapUpdate=notRequire", true);
	}
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
		if (showFseSelector==false){
			comboEmployeeAccountSelection.confirmValue();
			var legalName = comboEmployeeAccountSelection.getSelectedValue(); 
			if (!legalName || legalName == "") {
				showError('<spring:message code="message.account.not.associated" />');
			}else{
				document.employeeAccountSelectionForm.submit();
			}
		}else{
			var accountNumber = jQuery('#selectFseAccount').val();
			if (!accountNumber || accountNumber == "") {
				showError('<spring:message code="message.account.not.associated" />');
			}else{
				jQuery('#legalName').val(jQuery('#selectFseAccount :selected').text());
				document.employeeAccountSelectionForm.submit();
			}
		}
		/* Added by sankha for LEX:AIR00071291 start */
		if (window.PIE) {
            document.getElementById("btnSubmit").fireEvent("onmove");
        }
		/* Added by sankha for LEX:AIR00071291 end */
	}

	window.document.onkeydown = function(event){
		if(event==null){
			event=window.event;
		}
		if(event.keyCode==13){
			submitEmployeeForm();	
		}
	};
    addPortlet('Employee Account Selection');
 	</script>