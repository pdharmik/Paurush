<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css"><%@ include file="../../../css/combo/dhtmlxcombo.css" %></style> 
<script type="text/javascript"><%@ include file="../../../../js/grid/dhtmlxcommon.js"%></script>
<script type="text/javascript"><%@ include file="../../../../js/combo/dhtmlxcombo.js"%></script>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
	.pad{
		height: 8px;	
	}
	.nestedTable{
		width:100%;
	}
	table tr td.labelTD {
	    width: 250px;
	}
</style>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap"><div class="content">
<table width="800" border="0" class="sectionWrapper">
  <tr><td>
  	<div class="orangeSectionTitles padding-bottom-0px"><spring:message code="documentAdminPage.documentNamePage.title" /></div> 
  	<span><spring:message code="documentAdminPage.documentNamePage.description" /></span>
  </td></tr>
  <tr><td>
  		<portlet:actionURL var="saveURL">
			<portlet:param name="action" value="saveDocumentDefinition"/>
		</portlet:actionURL>
		<form action="${saveURL}" method="post" id="form" onSubmit="return doValidate();"> 
		<input type="hidden" name="displayMdmId" id="displayMdmId" value="${displayMdmId}"/>
			<table class="nestedTable">
				<tr><td>
					<html:statusBanner id="documentName"/>
		        </td></tr>
				<tr> 
			        <td class="labelTD">* <spring:message code="documentAdminPage.documentNamePage.label.documentName" /></td>
			        <td class="labelTD" rowspan="4"  valign="top">
			        	<input type="checkbox" <c:if test="${not empty customerAccount}">checked</c:if>
			        	name="limitAcc" 
			        	onclick="showHidelimitAcc()" id="customerSelectorController">
			        	<spring:message code="documentAdminPage.documentNamePage.text.limitation" />
			        	<br>
			        	<div id="customerSelector">
			        		<spring:message code="documentAdminPage.documentNamePage.label.limitation" />
			        		<div id="divEmployeeAccountSelection" class="div-style40"></div>	
			        	</div>
			        </td>
		        </tr>
			  	<tr> 
			        <td class="labelTD">
			        	<input type="hidden" name="definitionId" value="${definition.id}">
			        	<div class="inputMedWrapper">
			        	<input name="definitionName" id="definitionName" class="inputMed" value="${definition.name}" maxlength="100">
			        	</div>
			        </td>
		        </tr>
    		  	<tr class="pad"><td></td></tr>
		        <tr> 
			        <td class="labelTD">* <spring:message code="documentAdminPage.documentNamePage.label.category" /></td>
		        </tr>	
		        <tr> 
			        <td class="labelTD">
			        	<select name="categoryId" id="categoryId">
			        		<option value=""></option>
				        	<c:forEach items="${categories}" var="category">
				        		<option value="${category.categoryId}" 
				        			<c:if test="${category.categoryId == definition.roleCategory.categoryId}">selected</c:if>>
				        			${category.name}
				        		</option>
				        	</c:forEach>	
			        	</select>
			        </td>
		        </tr>
	  			<tr class="pad"><td></td></tr>
	        	<tr> 
			        <td class="labelTD"><spring:message code="serviceRequestLocaleListPage.details.label.supportedLanguage" /></td>
			        <td class="labelTD"><spring:message code="documentAdminPage.documentNamePage.label.localization.displayValue" /></td>
			        <td class="labelTD"><spring:message code="label.action" /></td>
		        </tr>
		        <c:forEach items="${locales}" var="locale">
			        <tr> 
				        <td width="200px;">
				        	<input type="hidden" name="localeIds" value="${locale.supportedLocale.supportedLocaleId}"> 
				        	${locale.supportedLocale.supportedLocaleName}
						</td>
				        <td width="350px"><div class="inputLgWrapper"><input type="text" name="displayValues" class="inputLg" 
				        id="displayValues_${locale.supportedLocale.supportedLocaleId}"
				        value="${locale.name}" maxlength="100"></div></td>
				        <td align="left">
				        	<script type="text/javascript">
				        		function clearContent(id){
				        			document.getElementById(id).value='';
				        		}
				        	</script>
				        	<a href="javascript:clearContent('displayValues_${locale.supportedLocale.supportedLocaleId}');">
				        		<img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png">
				        	</a>
				        </td>
			        </tr>
		        </c:forEach>
		        <tr class="pad"><td></td><td></td></tr>
		        <tr> 
			        <td colspan="2" class="text-align-right">
			        <div class="buttonContainer">
			        	<button onclick="javascript:back();" id="Cancel_Library_Administration" class="button_cancel color-606060"><spring:message code="button.cancel" /></button>
						<button onclick="javascript:document.getElementById('form').submit();" id="select_Library_Administration" class="button color-white"><spring:message code="button.submit" /></button>
					</div>
					</td>
		        </tr>
			</table>
	  	</form>
  </td></tr>
</table>
</div></div>
<portlet:resourceURL id='acccountSelection' var="comboURL">
	<portlet:param name="default" value="${legalName}"></portlet:param>
</portlet:resourceURL>
<script type="text/javascript">
	jQuery(document).ready( function() {
		if (window.PIE) {
		    document.getElementById("Cancel_Library_Administration").fireEvent("onmove");
		    document.getElementById("select_Library_Administration").fireEvent("onmove");
		}
	});
	
	function showHidelimitAcc(){
	    var ele = document.getElementById("customerSelectorController");
	    var checked = ele.checked;
	    var div = document.getElementById("customerSelector");
	    div.style.display = checked ? "block":"none";
	}
	function doValidate() {
	    var valid = true;
	    clearMessage();
	    var ele =  document.getElementById("definitionName");
	    if(ele.value.trim()==""){
	        showError("<spring:message code='documentAdminPage.documentNamePage.validation.nameEmpty'/>","documentName",true);
	        valid =  false;
	    }
	
	    var select = document.getElementById("categoryId");
	    if(select.value.trim()==""){
	        showError('<spring:message code="documentAdminPage.documentNamePage.validation.categoryEmpty" />',"documentName",true);
	        valid = false;
	    }
	    return valid;
	    return true;
	}
	function back(){
	    location.href="<portlet:renderURL></portlet:renderURL>";
	}
	window.dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
	var comboEmployeeAccountSelection = new dhtmlXCombo("divEmployeeAccountSelection", "legalName", 200);
	comboEmployeeAccountSelection.enableFilteringMode(true, "${comboURL}", true, true);
	comboEmployeeAccountSelection.skin = "dhx_skyblue";
	comboEmployeeAccountSelection.DOMelem_hidden_input.value = "${legalName}";
	comboEmployeeAccountSelection.DOMelem_input.value = "${legalName}"
	showHidelimitAcc();
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Document Admin";
     addPortlet(portletName);
     pageTitle="Document name";
</script>