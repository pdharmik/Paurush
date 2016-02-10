<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%-- <%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>   <!-- added by for CI5 -->--%>
<style type="text/css"><%@ include file="../../css/combo/dhtmlxcombo.css" %></style> 
<script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxcommon.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/combo/dhtmlxcombo.js"%></script>
<script src="/html/themes/classic/javascript/tree/jquery-1.4.js" type="text/javascript"></script>
<script src="/html/themes/classic/javascript/tree/jquery.treeview.js" type="text/javascript"></script>
<portlet:resourceURL var="customerTreeDetails" id="getCustomerTreeDetails"></portlet:resourceURL>
<portlet:resourceURL var="customerMdmAccountDetails" id="getCustomerMdmAccountDetails"></portlet:resourceURL>
<portlet:resourceURL var="customerL4FromL3AccountDetails" id="getCustomerL4AccountDetailsFromL3"></portlet:resourceURL>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<style type="text/css"><%@ include file="/WEB-INF/css/jquery/jquery-ui.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<style type="text/css">
	.ie7 .button, .ie7 .button_cancel { 
	 	margin-left: 5px;
	 	padding-left: .8em !important;
	 	behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc);
		position: static;		
	 }
	 div.main-wrap {
overflow: auto !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<%--Div for CI BRD 13-10-27 START--%>
		<div id="saveReportPopUp" class="min-width-250px" style="display:none">
    		<div class="columnInner" id="loadingDialog"><div class="columnsTwo w50" style="display: none;"></div>
    		<div class="columnsOne w70p">
      			<p><spring:message code="reportsScheculer.saveConfirmation.popup.Label1"/></p>
				<p><spring:message code="reportsScheculer.saveConfirmation.popup.Label2"/></p>
      		</div>
      		<div class="buttonContainer popupBtnCont">
      		<button id="popupYes" class="button" onclick="javascript:doSubmit('yes');dialog.dialog('close');"><spring:message code="button.yes"/></button>
      		<button id="popupNo" class="button" onclick="doSubmit('no');dialog.dialog('close');"><spring:message code="button.no"/></button>
      		</div>
      		</div>
		</div>
<%--Div for CI BRD 13-10-27 ENDS--%>
<div class="main-wrap">
	<div class="content">
	    <div id="rptAdminReportName"   class="vertical-align-top">
	        <span class="orangeSectionTitles"><spring:message code="customerReportsAdmin.label.reportAdminReportName"/></span><br/>
	        <spring:message code="customerReportsAdmin.label.reportAdminReportDescription"/><br/><br/>
	    </div>
	    <div class="height-5px"></div>
	    <div>
	    <portlet:actionURL var="saveReportDefinitionURL">
	        <portlet:param name="action" value="saveReportDefinition"/>
	    </portlet:actionURL>
	    <form:form id="formReportDefinitionDetail" commandName="reportDefinitionDetailForm.reportDefinition" method="post" action="${saveReportDefinitionURL}">
	        <input type="hidden" id="companyMdmIdLevel" name="companyMdmIdLevel" value=''/>
	        <input type="hidden" id="prevCompanyMdmIdLevel" name="prevCompanyMdmIdLevel" value=''/>
	        <input type="hidden" id="newReportFlag" value="${newReportFlag}"/>
	        <input type="hidden" id="prevCompMdmIdLevel" value="${prevCompMdmIdLevel}"/>
			<%-- BRD 14-02-14 start --%>
	        <input type="hidden" id="showLimitCustomer" value="${showLimitCustomer}"/>	        
	        <input type="hidden" id="companyLevelAssociation" name="companyLevelAssociation" value="${companyLevelAssociation}"/>
	        <%-- BRD 14-02-14 end --%>
			<form:hidden path="id"/>
	        <form:hidden path="displayMdmId"/>
	        <table width="80%" border=0>
	            <tr valign="top">
	                <td class="boldField" width="300">
	                    *<spring:message code="customerReportsAdmin.label.reportName"/>:<br/>
	                </td>
	                <td class="boldField table-td-style19">
	                    *<spring:message code="customerReportsAdmin.label.reportDescription"/>:
	                </td>
	            </tr>
	            <tr valign="top">
	                <td class="boldField" width="300">
	                	<div class="inputMedWrapper">
	                    <form:input path="name" class="inputMed" maxlength="100"/>
	                    </div>
	                </td>
	                <td >    
	                    <form:textarea path="description" cols="80" rows="3" maxlength="500"/>
	                </td>
	            </tr>
	        </table>
	        <br>
	        <table border=0>
	            <tr valign="top">
	                <td class="width-200px">
	                    <!-- <input id="ckbLimitAccess" type="checkbox" name="limitAccessFlag" onclick="toggleAccountSelection()" <c:if test="${!empty reportDefinitionDetailForm.legalName}">CHECKED</c:if>> -->	<!-- commented by nelson for CI5 multiple customer reports -->
	                    <input id="ckbLimitAccess" type="checkbox" name="limitAccessFlag" onclick="toggleAccountSelection()" >	<!-- added by nelson for CI5 multiple customer reports -->
	                    <spring:message code="customerReportsAdmin.label.limitAccess"/>:<br/>
	                    </br>
	                    <input id="ckbLimitExclude" type="checkbox" name="limitExcludeFlag" onclick="toggleAccountSelectionForExclude()" >	<!-- BRD 14-07-03 -->
	                    <spring:message code="customerReportsAdmin.label.excludeAccess"/>:<br/>
	                    <!-- commented for CI5 multiple report customers
	                    <div id="employeeAccountSelectionDIV">
	                    	<spring:message code="customerReportsAdmin.label.selectCustomerName"/><br/>
	                    	<div id="divEmployeeAccountSelection" style="width:200px; height:30px;"></div>
	                    </div>	
	                    -->
	                </td>
	                <td class="labelBold table-td-style20" >
	                    *<spring:message code="customerReportsAdmin.label.reportType"/>: <br/>
	                    <form:select path="reportType" onchange="proceedReportTypeChangeDecider();">
	                        <form:option value="BO">Business Objects</form:option>
	                        <form:option value="MU">Manual Upload</form:option>
	                        <form:option value="OA">Oracle Analytics</form:option>
	                    </form:select>
	                </td>
	                <td class="labelBold">
	                  	<c:choose>
		                <c:when test="${reportDefinitionDetailForm.reportDefinition.reportType == 'MU'}">
	                    <div id="divReportSourceId" style="display: none">
	                    	*<spring:message code="customerReportsAdmin.label.path"/>: <br/>
	                    	<div class="inputLgWrapper">
	                    	<form:input path="reportSourceId" disabled="true" class="inputLg" maxlength="100"/>
	                    	</div>
	                    </div>
	                	</c:when>
	                	<c:otherwise>
	                    <div id="divReportSourceId">
	                        *<spring:message code="customerReportsAdmin.label.path"/>:<br/>
	                        <div class="inputMedWrapper">
	                        <form:input path="reportSourceId" class="inputMed"/>
	                        </div>
	                    </div>
	                	</c:otherwise>
	                	</c:choose>
	                </td>
	            </tr>
	          </table>
	          <%-- BRD 14-02-14 start --%>
	          <br>
	          <div id="valignHt" class='height-auto width-auto'>
	          <table width="100%">
	          <tr><td class="padding-06px" style="display:none;" id="typeQbr"><b><spring:message code="customerReportsAdmin.reportConfiguration.type"/></b><font size="2" color="red">*</font></td><td colspan="3" id="accIdError"><div id="accountIderror" style="display:none;">&nbsp;&nbsp;<font color="red" size="1"><spring:message code="customerReportsAdmin.entered.incurrentmdmid"/></font></div></td></tr>
	        	<tr><td width="25%" class="padding-06px" nowrap>
                 <select name="searchCriteria" id="searchCriteria" onchange="javascript:changetext()" class='searchCriteria'>
                            <option value="compname" selected><spring:message code="customerReportsAdmin.companyName"/> </option>
                            <option value="AccountId"><spring:message code="customerReportsAdmin.mdmaccountId"/></option>
                 </select></td>  
                  <td id="mdmDiv" style="display: none;" width="25%" nowrap><input type="text" maxlength="50" size="30" name="mdmSearch" id="mdmSearch">
               	</td>              
                 <td id="muDiv" style="display: none;" width="25%" nowrap><input type="text" maxlength="50" size="30" name="compName" id="compName" autocomplete="off" onkeyup="javascript:ajaxCall(this)">
               	</td>
                 <td width="40%" align="left" nowrap><div id="sbutton" style="display:none;"><button type="button" class="button" value="<spring:message code="customerReportsAdmin.mdmId.search"/>" name="Search" onclick="simpleSearch()"><spring:message code="customerReportsAdmin.mdmId.search"/></button></div></td>
                  <td width="10%">&nbsp;&nbsp;</td>
                  
                </tr>
                 <tr><td></td><td colspan="3"><div id="cID" style="display:none;"><font color="gray" size="1">&nbsp;&nbsp;<spring:message code="customerReportsAdmin.selectexactmdmId"/></font></div><div id="cname" style="display:none;"><font color="gray" size="1">&nbsp;&nbsp;<spring:message code="customerReportsAdmin.populateCompany"/></font></div></td>
                 </tr>
                 <%-- 
	        	<div id="muDiv" style="display:none">
	        	
	        	<table border=0>
	           			<tr valign="top">
	           			<spring:message code="customerReportsAdmin.label.selectCustomerName"/><br/>								
								<input type="text" maxlength="50" size="30" name="compName" id="compName" autocomplete="off" onkeyup="javascript:ajaxCall(this)">
	           			<td>
	           			</td>
	           			</tr>
	           	</table>	        	
	        	</div>--%>
	          </table>
	        	<%-- BRD 14-02-14 end --%>
	            <!-- added for CI5 for multiple customer report -->
	           <div id="employeeAccountSelectionDIV" style="display:none!important;">
	           		<table border=0 >
	           			<tr valign="top">
							<td>
								<spring:message code="customerReportsAdmin.label.selectCustomerName"/><br/>
	                    		<div id="divEmployeeAccountSelection" class="divEmployeeAccountSelection"></div>
							</td>
							 
							<td class="table-td-style21">
							
								<a href="javascript:includeExcludeDecider()" class="button"><span><spring:message code="customerReportsAdmin.label.add"/></span></a>
							
							</td>
							
						</tr>
	           		</table>
	           		
					<table id="tblCustomerHeadr" width="100%" border=0>
						<tr height="25px">						
							<td class="labelBold width-90per"><spring:message code="customerReportsAdmin.label.customerName" /></td>
							<td class="labelBold width-10per"><spring:message code="customerReportsAdmin.label.action" /></td>						
						</tr>
					</table>
					
				</div>
				<%-- BRD 14-02-14 start --%>
				<div id="divCustomerList" class="div-customer-list" >
					<table id="tblCustomerList" width="100%" border=0>					
						<c:set var="customerListSize" value="${fn:length(reportDefinitionDetailForm.legalNameList)}" />
						<c:forEach items="${reportDefinitionDetailForm.legalNameList}" var="reportCustomer" varStatus="countr" begin="0">
						<tr height="22px">
							<td class="width-93per">
								<input type="hidden" id="customerList[${countr.index}]" name="customerList[${countr.index}]" value='${reportCustomer}' />
								
								<label id="customerList[${countr.index}].cust">${reportCustomer}</label>
							</td>
							<td class="width-7per">
								<img class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="deleteCustomer(${countr.index})" >
							</td>
						</tr>
						</c:forEach>
					</table>
					<br/>
				</div>
				
				<div id="divCustomerListExclude" class="div-customer-list">
					<table id="tblCustomerListExclude" width="100%" border=0>				
						<c:set var="customerListSize" value="${fn:length(reportDefinitionDetailForm.legalNameListExclude)}" />
						<c:forEach items="${reportDefinitionDetailForm.legalNameListExclude}" var="reportCustomerExclude" varStatus="countrExclude" begin="0">
						<tr height="22px">
							<td class="width-93per">
								<input type="hidden" id="customerListExclude[${countrExclude.index}]" name="customerListExclude[${countrExclude.index}]" value='${reportCustomerExclude}' />				
								<label id="customerListExclude[${countrExclude.index}].cust">${reportCustomerExclude}</label>
							</td>
							<td class="width-7per">
								<img class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="deleteCustomerExclude(${countrExclude.index})" >
							</td>
						</tr>
						</c:forEach>
					</table>
					<br/>
				</div>
				<%-- BRD 14-02-14 end --%>
				<%-- BRD 14-02-14 start --%>
				<br>
				<table id="showTblCustomerAssoc" style="display: none;" width="100%" border=0>
						<tr height="25px">					
							<td class="labelBold width-96per"><spring:message code="customerReportsAdmin.label.customerName" /></td>													
						</tr>
				</table>
				<div id="divCust" class="div-customer-list">
					<table id="tblCust" width="100%" border=0>
						<tr height="22px">
							<td class="width-93per">
								<label id="custLabel">${companyLevelAssociation}</label>
							</td>							
						</tr>						
					</table>
					<br/>
				</div>
				<table width="100%">
            		<tr>
                		<td nowrap>                		
                			<div id="treeDisplay" style="display:none">
                			
                			</div>                		
                		</td>
            		</tr>     
            	</table>
            	<br><br>
            	<table width="100%">
            		<tr>
                		<td nowrap>                		
                			<div id="accountAssociation" style="display:none">
                			</div>                		
                		</td>                		
            		</tr>     
            	</table>            	
            	<br><br>
            	<%-- BRD 14-02-14 end --%>
            	
				
				
				<br/>
			<!-- end of addition for CI5 for multiple customer report -->
			  <table border=0>		<!-- added for Ci5 multiple customers report -->
	            <tr valign='top'>
	                <td class="labelBold width-200px">
	                    *<spring:message code="customerReportsAdmin.label.reportCategory"/>:<br/>
	                    <form:select path="roleCategory.categoryId">
	                    <c:forEach items="${reportDefinitionDetailForm.categoryList}" var="reportCategory" varStatus="counter" begin="0">
	                        <form:option value="${reportCategory.categoryId}">${reportCategory.name}</form:option>
	                    </c:forEach>
	                    </form:select>
	                </td>
	                <td class="labelBold table-td-style20" >
	                *<spring:message code="customerReportsAdmin.label.viewType"/>:<br/>
					<form:select path="viewType">
						<form:option value="ALL"><spring:message code="customerReportsAdmin.label.accountType.allCustomer"/></form:option>
						<form:option value="DFM"><spring:message code="customerReportsAdmin.label.accountType.DFM"/></form:option>
						<form:option value="CSS"><spring:message code="customerReportsAdmin.label.accountType.CSS"/></form:option>
						<form:option value="ALL_PARTNER"><spring:message code="customerReportsAdmin.label.accountType.allPartner"/></form:option>
						<form:option value="DIRECT_PARTNER"><spring:message code="customerReportsAdmin.label.accountType.directPartner"/></form:option>
						<form:option value="INDIRECT_PARTNER"><spring:message code="customerReportsAdmin.label.accountType.indirectPartner"/></form:option>
					</form:select>
	                </td>
	                <td class="padding-left-5px">
	                    <form:checkbox path="isSendMDMParameter"/><span id="msgAutoPass">
	                    <c:choose>
	                    	<c:when test="${reportDefinitionDetailForm.reportDefinition.reportType == 'OA'}">
	                    	<spring:message code="customerReportsAdmin.label.oracleAnalytics.passAccountID"/>
	                    	</c:when>
	                    	<c:otherwise>
	                    	<spring:message code="customerReportsAdmin.label.requireMDM"/>
							</c:otherwise>
	                    </c:choose></span>
	                </td>
	            </tr>
	        </table>
	        <br/>
	        <table border=0 width="80%">
	            <tr>
	                <td class="labelBold" width="30%">
	                    <spring:message code="customerReportsAdmin.label.supportedLanguage"/>
	                </td>
	                <td class="labelBold" width="30%">
	                    <spring:message code="customerReportsAdmin.label.detailsEntered"/>
	                </td>
	                <td class="labelBold" colspan='2' >
	                    <spring:message code="customerReportsAdmin.label.actions"/>
	                </td>
	            </tr>
	            <c:forEach items="${reportDefinitionDetailForm.reportDefinition.localeList}" var="reportDefinitionLocale" varStatus="counter" begin="0">
	            <tr  height="25px" >
	                <td>
	                    <label id="localeList${counter.index}.language">${reportDefinitionLocale.supportedLocale.supportedLocaleName}</label>
	                    <!-- Below Hidden field Locale ID added for CI BRD 13-10-27 -->
 	                    <label id="localeList${counter.index}.localeId" style="display: none;">${reportDefinitionLocale.supportedLocale.supportedLocaleId}</label>
	                    <form:hidden path="localeList[${counter.index}].definitionLocaleId"/>
	                    <form:hidden path="localeList[${counter.index}].reportDefinitionId"/>
	                    <form:hidden path="localeList[${counter.index}].supportedLocale.supportedLocaleId"/>
	                    <form:hidden path="localeList[${counter.index}].name"/>
	                    <form:hidden path="localeList[${counter.index}].description"/>
	                </td>
	         <c:choose>
	            <c:when test="${empty reportDefinitionLocale.name}">
	                <td><label id="localeList${counter.index}.localeExist"><spring:message code="customerReportsAdmin.label.no"/></label></td>
	                <td width="2%">
	                    <a id="localeList${counter.index}.link" href="javascript:void(0)" onClick="showLocale(${counter.index}, false)"><spring:message code="customerReportsAdmin.label.add"/></a>
	                </td>
	                <td width="38%">
	                   &nbsp;&nbsp;<img id="localeList${counter.index}.deleteImg" class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="clearLocale(${counter.index})" style="display: none; visibility: hidden"  >
	                </td>
	            </c:when>
	            <c:otherwise>
	                <td ><label id="localeList${counter.index}.localeExist"><spring:message code="customerReportsAdmin.label.yes"/></label></td>
	                <td width="2%">
	                    <a id="localeList${counter.index}.link" href="javascript:void(0)" onClick="showLocale(${counter.index}, false)"><spring:message code="customerReportsAdmin.label.edit"/></a>&nbsp;
	                </td>
	                <td width="38%">    
	                    &nbsp;&nbsp;<img id="localeList${counter.index}.deleteImg" class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="clearLocale(${counter.index})" >
	                </td>
	            </c:otherwise>
	        </c:choose>
	            </tr>
	            </c:forEach>
	        </table>
	        <br/>
	        <div id="selectedLocale" class="selected-locale">
	            <input type="hidden" id="localeIndex" value="${localeIndex}"/>
	            <spring:message code="customerReportsAdmin.label.localizedSettings" />:&nbsp;<label id="selectedLocaleLanguage"></label>
	            <label id="selectedLocaleId" style="display: none;"></label><br/>
	            *<spring:message code="customerReportsAdmin.label.reportName"/>:<br/>
	            <div class="inputMedWrapper">
	            <input type="text" class="inputMed" id="selectedLocaleReportName" maxlength="100"/><br/>
	            </div>
	            *<spring:message code="customerReportsAdmin.label.reportDescription"/>:<br/>
	            <div class="inputMedWrapper">
	            <input type="text" class="inputMed" id="selectedLocaleReportDescription" maxlength="250"/>
	            </div>
	        </div>
	        <style>
	         .add_parameters{float:left; align: right;width:100%;clear:both;}
	         .ie7 .add_parameters{overflow-x: auto;overflow-y:hidden;padding-bottom:20px}
	        </style>
	        <div id="divReportParameters" class="add_parameters">
		        <spring:message code="customerReportsAdmin.label.setReportParameters"/>
		        <br/>
		        <a href="javascript:void(0)" onClick="addParameter()"><spring:message code="customerReportsAdmin.label.addReportParameter"/></a>    
		        <table id="tblParameterList" width="80%" border=0>
		            <tr height="25px">
		                <td class="labelBold" width="15%">
		                    <spring:message code="customerReportsAdmin.label.parameterName"/>&nbsp;
		                </td>
		                <td class="labelBold" width="15%">
		                    <spring:message code="customerReportsAdmin.label.displayName"/>&nbsp;
		                </td>
		                <td class="labelBold" colspan="2" width="5%">
		                    <spring:message code="customerReportsAdmin.label.order"/>&nbsp;
		                </td>
		                <td class="labelBold" width="10%">
		                    <spring:message code="customerReportsAdmin.label.dataType"/>&nbsp;
		                </td>
		                <td class="labelBold" width="10%">
		                    <spring:message code="customerReportsAdmin.label.max"/>&nbsp;
		                </td>
		                <td class="labelBold" width="15%">
		                    <spring:message code="customerReportsAdmin.label.listValues"/>&nbsp;
		                </td>
		                <td class="labelBold" width="5%">
		                    <spring:message code="customerReportsAdmin.label.reqd"/>&nbsp;
		                </td>
	                    <td class="labelBold" width="10%">
	                        <spring:message code="customerReportsAdmin.label.action"/>
	                    </td>
		            </tr>
		        <c:set var="parameterNum" value="${fn:length(reportDefinitionDetailForm.reportDefinition.parameterList)}"/>
		        <c:forEach items="${reportDefinitionDetailForm.reportDefinition.parameterList}" var="reportParameter" varStatus="counter" begin="0">
		            <tr height="25px">
		                <td>
		                    <input type="hidden" id="parameterList[${counter.index}].reportParameterId" name="parameterList[${counter.index}].reportParameterId" value='${reportParameter.reportParameterId}'>
	                        <input type="hidden" id="parameterList[${counter.index}].reportDefinitionId" name="parameterList[${counter.index}].reportDefinitionId" value='${reportParameter.reportDefinitionId}'>
	                        <input type="text" id="parameterList[${counter.index}].name" name="parameterList[${counter.index}].name" value='${reportParameter.name}' maxlength="100">
		                </td>
		                <td>
 		               <!-- Added for CI BRD 13-10-27 --STARTS -->
 		               <!-- Changes done for CI BRD 13-10-27 -->
<%--  		               <c:set var="reportDefinitionLocale" value="${reportDefinitionDetailForm.reportDefinition.localeList}"/> --%>
		               <c:if  test="${localeIndex=='0'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayName}' maxlength="100">
		                 </c:if>
 		               <c:if  test="${localeIndex=='1'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameSpanish}' maxlength="100">
		                 </c:if>
<%-- 		                 <c:if  test="${reportDefinitionLocale.supportedLocale.supportedLocaleId=='3'}"> --%>
							<c:if  test="${localeIndex=='2'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameGerman}' maxlength="100">
		                    </c:if>
							<c:if  test="${localeIndex=='3'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameFrench}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='4'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameChina}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='5'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameChinaTw}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='6'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNamePortugalBrazil}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='7'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNamePortugal}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='8'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameItaly}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='9'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameKorea}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='10'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameRussia}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='11'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameJapan}' maxlength="100">
		                 	</c:if>
							<c:if  test="${localeIndex=='12'}">
		                    <input type="text" id="parameterList[${counter.index}].disName" name="parameterList[${counter.index}].disName" value='${reportParameter.displayNameTurkey}' maxlength="100">
		                 	</c:if>
		                 	
		                 	
		                 	
		                 <!-- Added for CI BRD 13-10-27 --ENDS -->
		                </td>
		                <td>
		                    <c:choose>
		                       <c:when test="${counter.index > 0}">
		                           <img src="<html:imagesPath/>arrow-up.png" onClick="swapParameterOrder(${counter.index}, 'UP')">
		                       </c:when>
		                       <c:otherwise>
		                           <img src="<html:imagesPath/>arrow-up-grayed.png">
		                       </c:otherwise>
		                    </c:choose>
		                </td>
		                <td>
	                        <c:choose>
	                           <c:when test="${counter.index + 1 < parameterNum}">
	                               <img src="<html:imagesPath/>arrow-down.png" onClick="swapParameterOrder(${counter.index}, 'DOWN')">
	                           </c:when>
	                           <c:otherwise>
	                               <img src="<html:imagesPath/>arrow-down-grayed.png">
	                           </c:otherwise>
	                        </c:choose>
		                </td>
		                <td>
		                	<select id="parameterList[${counter.index}].dataType" name="parameterList[${counter.index}].dataType" onChange="enableParameterInput(this.value,this.id);">
		                   	<c:choose>
								<c:when test="${reportDefinitionDetailForm.reportDefinition.reportType == 'BO'}">
								<option value="BOOLEAN" <c:if test="${reportParameter.dataType == 'BOOLEAN'}">SELECTED</c:if>><spring:message code="boxi.paramType.boolean"/></option>
							    <option value="DATE" <c:if test="${reportParameter.dataType == 'DATE'}">SELECTED</c:if>><spring:message code="boxi.paramType.date"/></option>
							    <option value="LIST" <c:if test="${reportParameter.dataType == 'LIST'}">SELECTED</c:if>><spring:message code="obiee.paramType.list"/></option>
							    <option value="STRING" <c:if test="${reportParameter.dataType == 'STRING'}">SELECTED</c:if>><spring:message code="obiee.paramType.string"/></option>
							    <option value="NUMERIC" <c:if test="${reportParameter.dataType == 'NUMERIC'}">SELECTED</c:if>><spring:message code="boxi.paramType.numeric"/></option>
								</c:when>
		                   		<c:when test="${reportDefinitionDetailForm.reportDefinition.reportType == 'OA'}">
							    <option value="analyticsDate" <c:if test="${reportParameter.dataType == 'analyticsDate'}">SELECTED</c:if>><spring:message code="obiee.paramType.analyticsDate"/></option>
							    <option value="analyticsDateBetween" <c:if test="${reportParameter.dataType == 'analyticsDateBetween'}">SELECTED</c:if>><spring:message code="obiee.paramType.analyticsDateBetween"/></option>
							    <option value="analyticsQuarter" <c:if test="${reportParameter.dataType == 'analyticsQuarter'}">SELECTED</c:if>><spring:message code="obiee.paramType.analyticsQuarter"/></option>
							    <option value="analyticsQuarterBetween" <c:if test="${reportParameter.dataType == 'analyticsQuarterBetween'}">SELECTED</c:if>><spring:message code="obiee.paramType.analyticsQuarterBetween"/></option>
							    <option value="LIST" <c:if test="${reportParameter.dataType == 'LIST'}">SELECTED</c:if>><spring:message code="obiee.paramType.list"/></option>
							    <option value="STRING" <c:if test="${reportParameter.dataType == 'STRING'}">SELECTED</c:if>><spring:message code="obiee.paramType.string"/></option>
	                        	</c:when>
		                   	</c:choose>
		                   	</select>
		                </td>
		                <td>
		                    <input type="text" id="parameterList[${counter.index}].maxSize" name="parameterList[${counter.index}].maxSize" size="5" value="${reportParameter.maxSize}" <c:if test="${reportParameter.dataType != 'STRING' }">DISABLED</c:if> maxlength="5"/>
		                </td>
		                <td>
		                    <input type="text" id="parameterList[${counter.index}].listValues" name="parameterList[${counter.index}].listValues"  value="${reportParameter.listValues}" <c:if test="${reportParameter.dataType != 'LIST' }">DISABLED</c:if> maxlength="255"/>
		                </td>
		                <td>
		                    <input type="checkbox" id="parameterList[${counter.index}].isRequired" name="parameterList[${counter.index}].isRequired"
		                       <c:if test="${reportParameter.isRequired}">CHECKED</c:if>/>
		                </td>
		                <td>
		                    <img class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="deleteParameter(${counter.index})" >
		                </td>
		            </tr>
		        </c:forEach>
		    
		        </table>
	        </div>
	    	</div>
	    	<table width="80%" style="*position:relative">
	    		<tr><td><br></td></tr>
	    		<tr>
	    			<td width="100%" align="right" >
	    			<div class="float-right">
	  				 	<a href="javascript:onCancelClick();" class="button_cancel"><span><spring:message code="button.cancel"/></span></a>&nbsp;
 	  				 	<!--Submit Changed to Save for CI BRD13-10-27 -->
						 <a href="javascript:confirmSave();" class="button"><span><spring:message code="button.save"/></span></a>
	    			</div>
	    			</td>
	    		</tr>
	    	</table>
		</div>
	</form:form>
	<select id="slctBODataType" style="visibility: hidden;" onChange="enableParameterInput(this.value,this.id);">
	    <option value="BOOLEAN"><spring:message code="boxi.paramType.boolean"/></option>
	    <option value="DATE"><spring:message code="boxi.paramType.date"/></option>
	    <option value="LIST"><spring:message code="obiee.paramType.list"/></option>
	    <option value="STRING"><spring:message code="obiee.paramType.string"/></option>
	    <option value="NUMERIC"><spring:message code="boxi.paramType.numeric"/></option>
	</select>
	<select id="slctOADataType" style="visibility: hidden;" onChange="enableParameterInput(this.value,this.id);">
	    <option value="analyticsDate"><spring:message code="obiee.paramType.analyticsDate"/></option>
	    <option value="analyticsDateBetween"><spring:message code="obiee.paramType.analyticsDateBetween"/></option>
	    <option value="analyticsQuarter"><spring:message code="obiee.paramType.analyticsQuarter"/></option>
	    <option value="analyticsQuarterBetween"><spring:message code="obiee.paramType.analyticsQuarterBetween"/></option>
	    <option value="LIST"><spring:message code="obiee.paramType.list"/></option>
	    <option value="STRING"><spring:message code="obiee.paramType.string"/></option>
	</select>
	<select id="slctMUDataType" style="visibility: hidden;">
	    
	</select>
</div>
<input id="compName1" type="hidden" />
<input id="formName" type="hidden" />
<portlet:resourceURL id='accountSelection' var="comboURL">
    <portlet:param name="default" value="${reportDefinitionDetailForm.legalName}"></portlet:param>
</portlet:resourceURL>

<script type="text/javascript">
	//Below ready function kept for BRD 13-10-27 Testing
// jQuery(document).ready(function() {
// 	alert('In ready');
// 	loadParameterList();
// 	});
	//jQuery.noConflict();
	var previousReportTypeIndex = document.getElementById('reportDefinition.reportType').selectedIndex;
	//Arrays created for CI BRD 13-10-27 --Defect 10111--STARTS
	var reportsParamListArray_en= new Array(); 
	var reportsParamListArray_sp= new Array(); 
	var reportsParamListArray_gr= new Array(); 
	var reportsParamListArray_fr= new Array(); 
	var reportsParamListArray_ch= new Array(); 
	var reportsParamListArray_ch_tw= new Array(); 
	var reportsParamListArray_pt_br= new Array(); 
	var reportsParamListArray_pt_pt= new Array(); 
	var reportsParamListArray_it= new Array(); 
	var reportsParamListArray_ko= new Array(); 
	var reportsParamListArray_ru= new Array(); 
	var reportsParamListArray_ja= new Array(); 
	var reportsParamListArray_tr= new Array(); 
	//Changes done for 14-02-14
	jQuery(document).ready(function() {
		var checkInclude = ${checkInclude};
		var checkExclude = ${checkExclude};
		if(checkInclude)
			{
			document.getElementById("ckbLimitAccess").checked = true;
			jQuery('#ckbLimitAccess').trigger('click');
		//	toggleAccountSelection();
			}
		if(checkExclude)
		{
			//alert("IF ccc");
			document.getElementById("ckbLimitExclude").checked = true;
			jQuery('#ckbLimitExclude').trigger('click');
			toggleAccountSelectionForExclude();
			//document.getElementById("employeeAccountSelectionDIV").style.display = '';
		//document.getElementById("ckbLimitExclude").checked = true;
		//document.getElementById("tblCustomerListExclude").style.display = "block";
		}
		
		if(document.getElementById("showLimitCustomer").value == "true" && checkInclude){
			//alert("show Customer true")
			var reportType = document.getElementById("reportDefinition.reportType").value;
			//alert("report type :"+reportType);
			if(reportType=="MU")
				{
				 	//alert("report type is MU");
				 	document.getElementById("ckbLimitAccess").checked = true;
				 	document.getElementById("divCustomerList").style.display = 'none';
					document.getElementById("muDiv").style.display = "block";			       
		        	document.getElementById("typeQbr").style.display = "block";
		        	document.getElementById("searchCriteria").style.display = "block";		        	
		        	document.getElementById("cname").style.display = "block";
		        	document.getElementById("divCust").style.display = "block";
		        	//document.getElementById("customerList[0].cust").value = document.getElementById("companyLevelAssociation").value; 
		        	document.getElementById("showTblCustomerAssoc").style.display = "block";
		        	//document.getElementById("custLabel").value = document.getElementById("companyLevelAssociation").value;
				}
			else{
					//alert("report type is BO / OA");
					//document.getElementById("employeeAccountSelectionDIV").style.display = "block";
	    			//document.getElementById("divCustomerList").style.display = "block";
	    			var vsbltSetting = (document.getElementById("ckbLimitAccess").checked) ? true : false;
					if(vsbltSetting) {
	    				document.getElementById("employeeAccountSelectionDIV").style.display = '';
	    				document.getElementById("divCustomerList").style.display = 'block';
					}else{
						document.getElementById("employeeAccountSelectionDIV").style.display = "none";
		    			document.getElementById("divCustomerList").style.display = "none";
					}
	    			document.getElementById("divCust").style.display = "none";
	    			document.getElementById("showTblCustomerAssoc").style.display = "none";
			}			
		}else{
			//alert("show customer false")
			document.getElementById("ckbLimitAccess").checked = false;
		}
		var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_en[n]= '${params.displayName}';
		reportsParamListArray_sp[n]= '${params.displayNameSpanish}';
		reportsParamListArray_gr[n]= '${params.displayNameGerman}';
		reportsParamListArray_fr[n]= '${params.displayNameFrench}';
		reportsParamListArray_ch[n]= '${params.displayNameChina}';
		reportsParamListArray_ch_tw[n]= '${params.displayNameChinaTw}';
		reportsParamListArray_pt_br[n]= '${params.displayNamePortugalBrazil}';
		reportsParamListArray_pt_pt[n]= '${params.displayNamePortugal}';
		reportsParamListArray_it[n]= '${params.displayNameItaly}';
		reportsParamListArray_ko[n]= '${params.displayNameKorea}';
		reportsParamListArray_ru[n]= '${params.displayNameRussia}';
		reportsParamListArray_ja[n]= '${params.displayNameJapan}';
		reportsParamListArray_tr[n]= '${params.displayNameTurkey}';
		  n++;
		  </c:forEach>
	});
	//jQuery.noConflict();
	var previousReportTypeIndex = document.getElementById('reportDefinition.reportType').selectedIndex; 
    showLocale(0, true);
//    added for CI5
	window.dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
	var comboEmployeeAccountSelection = new dhtmlXCombo("divEmployeeAccountSelection", "legalName", 200);
	var selectedLegalName;
	var prev_level='';
    var company_lvl='';
    var prev_association='';
    var company_association='';
    var prev_lv4 = "";
    var prev_lv3 = "";
//	  end of addition by nelson for CI5
	loadAccountSelection();
	function showLocale(index, firstLoadFlag) {
		//Changes made for CI Defect 10111 BRD27 --STARTS
		if (!firstLoadFlag) {
			saveLocale();
		}
		var reportType = document.getElementById("reportDefinition.reportType").value;
		if(reportType=="MU")
			{
				document.getElementById("divReportParameters").style.display = "none";
			}
		else
			{
			document.getElementById("divReportParameters").style.display = "block";
			}
		table = document.getElementById("tblParameterList");
	    var tableRowNum = table.rows.length;
	    var i=0;
	    document.getElementById('localeIndex').value = index;
	  //Below logics are to populate the respective arrays for display Names
	  // this if block is breaking the order for display name and its respective parameter name
	   /*  if(index=="0")
    	{
    	var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_en[n]= '${params.displayName}';
		n++;
		</c:forEach>
    	} */
	    if(index=="1")
	    	{
	    	var n=0;
			<c:forEach items="${reportParams}" var="params">
			reportsParamListArray_sp[n]= '${params.displayNameSpanish}';
			n++;
			</c:forEach>
	    	}
	    if(index=="2")
    	{
    	var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_gr[n]= '${params.displayNameGerman}';
		n++;
		</c:forEach>
    	}
	    if(index=="3")
    	{
    	var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_fr[n]= '${params.displayNameFrench}';
		n++;
		</c:forEach>
    	}
	    if(index=="4")
    	{
    	var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_ch[n]= '${params.displayNameChina}';
		n++;
		</c:forEach>
    	}
	    if(index=="5")
    	{
    	var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_ch_tw[n]= '${params.displayNameChinaTw}';
		n++;
		</c:forEach>
    	}
	    if(index=="6")
		{
		var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_pt_br[n]= '${params.displayNamePortugalBrazil}';
		n++;
		</c:forEach>
		}
	    if(index=="7")
		{
		var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_pt_pt[n]= '${params.displayNamePortugal}';
		n++;
		</c:forEach>
		}
	    if(index=="8")
		{
		var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_it[n]= '${params.displayNameItaly}';
		n++;
		</c:forEach>
		}
	    if(index=="9")
    	{
    	var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_ko[n]= '${params.displayNameKorea}';
		n++;
		</c:forEach>
    	}
	    if(index=="10")
		{
		var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_ru[n]= '${params.displayNameRussia}';
		n++;
		</c:forEach>
		}
	    if(index=="11")
		{
		var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_ja[n]= '${params.displayNameJapan}';
		n++;
		</c:forEach>
		}
	    if(index=="12")
		{
		var n=0;
		<c:forEach items="${reportParams}" var="params">
		reportsParamListArray_tr[n]= '${params.displayNameTurkey}';
		n++;
		</c:forEach>
		}
	  //Below logics are to fill text boxes for display Names based on the locale selected
	    if(index=="0")
		{
		for(var i=0;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_en[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
		if(index=="1")
			{
			for(var i=0;i < tableRowNum-1;i++)
				{
					  if(!reportsParamListArray_sp[i]=="")
					  {
					   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_sp[i];
					  }
				    else
					  {
				    	
					  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
					  }
			    }
			}
		if(index=="2")
		{
		for(var i=0;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_gr[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_gr[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
		if(index=="3")
		{
		for(var i=0;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_fr[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_fr[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
		if(index=="4")
		{
		for(var i=0;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_ch[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_ch[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }	
		    }
		}
		if(index=="5")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_ch_tw[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_ch_tw[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
				
		if(index=="6")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_pt_br[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_pt_br[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
		if(index=="7")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_pt_pt[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_pt_pt[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }	
		    }
		}
		if(index=="8")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_it[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_it[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
		if(index=="9")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_ko[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_ko[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
			}
		}
		if(index=="10")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_ru[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_ru[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
		if(index=="11")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_ja[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_ja[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }
		    }
		}
		if(index=="12")
		{
		for(i;i < tableRowNum-1;i++)
			{
				  if(!reportsParamListArray_tr[i]=="")
				  {
				   document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_tr[i];
				  }
			    else
				  {
				  document.getElementById('parameterList[' + i +'].disName').value=reportsParamListArray_en[i];
				  }	
		    }
		}
				//Changes made for CI Defect 10111 BRD27 --ENDS	 
		
		document.getElementById('localeIndex').value = index;
		document.getElementById('selectedLocaleLanguage').innerHTML = document.getElementById('localeList' + index + '.language').innerHTML;
		document.getElementById('selectedLocaleId').innerHTML = document.getElementById('localeList' + index + '.localeId').innerHTML; //Added for CI 13.10 BRD13-10-27
		document.getElementById('selectedLocaleReportName').value = document.getElementById('reportDefinition.localeList' + index + '.name').value;
		document.getElementById('selectedLocaleReportDescription').value = document.getElementById('reportDefinition.localeList' + index + '.description').value;
	};

	function loadAccountSelection() {
//		commented for CI5 for multiple customer report
	    /*window.dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
	    var comboEmployeeAccountSelection = new dhtmlXCombo("divEmployeeAccountSelection", "legalName", 200);*/
	    comboEmployeeAccountSelection.enableFilteringMode(true, "${comboURL}", true);
	    comboEmployeeAccountSelection.skin = "dhx_skyblue";
	    
//		commented for CI5 for multiple customer report
	    /*comboEmployeeAccountSelection.DOMelem_hidden_input.value = "${reportDefinitionDetailForm.legalName}";
	    comboEmployeeAccountSelection.DOMelem_input.value = "${reportDefinitionDetailForm.legalName}";*/

	    //toggleAccountSelection();
	};

	//Changes done for CI BRD 13-10-27 --STARTS
	function doValidate() {
		saveLocale();
		//clearMessage();
	    var isValid = true;
	    var selectedLocale = document.getElementById('selectedLocaleId').innerHTML;
		// validate required fields: Report Name, Report Description, Path when report type is Business Object, or Oracle Analytics
		if (document.getElementById('reportDefinition.name').value == "") {
			showError("<spring:message code='customerReportsAdmin.label.reportNameRequired'/>",null, true);
			isValid = false;
		}

		if(isInvalidChar(document.getElementById('reportDefinition.name').value)){
        	showError("<spring:message code='customerReportsAdmin.label.reportNameInvalid'/>",null, true);
        	isValid = false;
        }
        if (document.getElementById('reportDefinition.description').value == "") {
            showError("<spring:message code='customerReportsAdmin.label.reportDescriptionRequired'/>",null, true);
            isValid = false;
        }
        var description = document.getElementById('reportDefinition.description').value; 
        if (description.length > 500) {
            showError("<spring:message code='customerReportsAdmin.label.reportDescriptionTooLong'/>",null, true);
            isValid = false;
        }
        if (document.getElementById('reportDefinition.reportType').value != "MU" && document.getElementById('reportDefinition.reportSourceId').value == "") {
            showError("<spring:message code='customerReportsAdmin.label.pathRequired'/>",null, true);
            isValid = false;
        }
		var totalRowNum = document.getElementById("tblParameterList").rows.length;
		var parameterNum = totalRowNum - 1;
		// parameter's name must be unique
		var parameterListString = "";
		var duplicateFlag = false;
		var parameterName = "";
		for (var i = 0; i < parameterNum; i ++) {
			parameterName = document.getElementById("parameterList[" + i +"].name").value;
			
			if (parameterName != "") {
				duplicateFlag = parameterListString.indexOf(parameterName) > -1 ;
				if (duplicateFlag) {
					showError("<spring:message code='customerReportsAdmin.label.parameterNameUnique'/>",null, true);
					return false;
				}
				parameterListString = parameterListString + parameterName;
				// parameter display name is required
					if (document.getElementById("parameterList[" + i +"].disName").value == "") {
						showError("<spring:message code='customerReportsAdmin.label.parameterDisplayNameRequired'/>",null, true);
						isValid = false;
					}
				
				var dataType = document.getElementById("parameterList[" + i +"].dataType").value;
				var repPositiveNumber = new RegExp("^[0-9]*[1-9][0-9]*$");
			    // list values is required when data type is List, and the list values must be inputed in value1=displayName1,value2=displayName2
				if (dataType == "LIST") {
					var listValues = document.getElementById("parameterList[" + i +"].listValues").value;
					if (listValues == "") {
						showError("<spring:message code='customerReportsAdmin.label.listValuesRequiredForList'/>",null, true);
						return false;
					}
					// validate List Values format
					var listValues = document.getElementById("parameterList[" + i +"].listValues").value.split(",");
				    if (listValues.length < 1) {
				        showError("<spring:message code='customerReportsAdmin.label.listValuesIncorrectFormatted'/>",null, true);
				        return false;
				    }
				    for (var index = 0; index < listValues.length; index ++) {
				        valueDisplayName = listValues[index].split("=");
				        if (valueDisplayName.length != 2) {
				            showError("<spring:message code='customerReportsAdmin.label.listValuesIncorrectFormatted'/>",null, true);
				            return false;
				        }
				        for (index2 in valueDisplayName) {
				            if (index2 > 1 || valueDisplayName[index2].length < 1) {
				                showError("<spring:message code='customerReportsAdmin.label.listValuesIncorrectFormatted'/>",null, true);
				                return false;
				            }
				        }
				    }
				} else if (dataType == 'STRING') {
					var maxSize = jQuery.trim(document.getElementById("parameterList[" + i +"].maxSize").value);
					document.getElementById("parameterList[" + i +"].maxSize").value = maxSize;
					if (maxSize != '' && !repPositiveNumber.test(maxSize)) {
						showError("<spring:message code='customerReportsAdmin.label.maxMustBeaNumber'/>",null, true);
                        return false;
					}
				}
			}
		}
//		added for CI5 multiple customers report
		var isListEmpty = false;
		var isNotChecked = false;
		var totalCustomers;
		if(document.getElementById("ckbLimitExclude").checked){
		totalCustomers = document.getElementById('tblCustomerListExclude').rows.length;
		}
		else
		{
		totalCustomers = document.getElementById('tblCustomerList').rows.length;
		}
		if(totalCustomers == 0) {
			//alert("Customer--0");
			document.getElementById('ckbLimitAccess').checked = false;
			isListEmpty = true;
		}
//		end of addition for CI5 multiple customers report		
		// 
		// empty legalName if limit access is unchecked
		if (!document.getElementById("ckbLimitAccess").checked && !document.getElementById("ckbLimitExclude").checked) {
			//alert("HA hA1")
			jQuery("input[name=legalName]").val("");
			isNotChecked = true;  //		added for CI5 multiple customers report
		} 
		
//		added for CI5 multiple customers report
		if(!isListEmpty && !isNotChecked) {
			//alert("HA hA");
			jQuery("input[name=legalName]").val("customerListExclude");
		}
//		end of addition for CI5 multiple customers report	

		return isValid;
	}; //End of doValidate
	
	//Changes done for CI BRD 13-10-27 --ENDS
		
	function isInvalidChar(o)
	{
		var invalidFlag = false;
		for(var i = 0; i <o.length; i++){
			invalidFlag = o.charCodeAt(i)>126;
				if(invalidFlag)
		  			break;
	  	}
	 	return invalidFlag;
	}
		
	function onCancelClick() {
		window.location.href = "<portlet:renderURL></portlet:renderURL>";
	};
	
	//Changes done for CI BRD 13-10-27 --STARTS
	function addParameter() {
	    var table = document.getElementById("tblParameterList");
	    var tableRowNum = table.rows.length;
	    var newRow = table.insertRow(tableRowNum);
	    var cellName = newRow.insertCell(0);
	    var cellDisplayName = newRow.insertCell(1);
	    var cellOrderUp = newRow.insertCell(2);
        var cellOrderDown = newRow.insertCell(3);
	    var cellDataType = newRow.insertCell(4);
	    var cellMax = newRow.insertCell(5);
	    var cellListValues = newRow.insertCell(6);
	    var cellReqd = newRow.insertCell(7);
	    var cellDelete = newRow.insertCell(8);
	    var selectedLocale=document.getElementById('selectedLocaleId').innerHTML;
	    cellName.innerHTML = '<input type="hidden" id="parameterList[' + (tableRowNum -1) +'].reportParameterId" name="parameterList[' + (tableRowNum -1) +'].reportParameterId"/>' +
	        '<input type="hidden" id="parameterList[' + (tableRowNum -1) +'].reportDefinitionId" name="parameterList[' + (tableRowNum -1) +'].reportDefinitionId"/>' +
	        '<input type="text" id="parameterList[' + (tableRowNum -1) +'].name" name="parameterList[' + (tableRowNum -1) +'].name"/>';

	    	cellDisplayName.innerHTML = '<input type="text" id="parameterList[' + (tableRowNum -1) +'].disName" name="parameterList[' + (tableRowNum -1) +'].disName"/>';
	    
	    if (tableRowNum == 1) {
	    	cellOrderUp.innerHTML = '<img src="<html:imagesPath/>arrow-up-grayed.png">';
	    } else {
	    	cellOrderUp.innerHTML = '<img src="<html:imagesPath/>arrow-up.png" onClick="swapParameterOrder(' + (tableRowNum - 1) + ", 'UP'" + ')">';
	        table.rows[tableRowNum -1].cells[3].innerHTML = '<img src="<html:imagesPath/>arrow-down.png" onClick="swapParameterOrder(' + [tableRowNum - 2]  + ", 'DOWN'" + ')">';
	    }
	    cellOrderDown.innerHTML = '<img src="<html:imagesPath/>arrow-down-grayed.png">';

	    
	    // Clone slct**DataType, change id and apend it to cellDataType
	    var reportType = document.getElementById("reportDefinition.reportType").value;
	    var dataTypeTemplate = document.getElementById("slct" + reportType + "DataType");
	    dataTypeClone = dataTypeTemplate.cloneNode(true);
	    dataTypeClone.id = "parameterList[" + (tableRowNum -1) + "].dataType";
	    dataTypeClone.name = "parameterList[" + (tableRowNum -1) + "].dataType";
	    dataTypeClone.style.visibility = "visible";
	    cellDataType.appendChild(dataTypeClone);
	    
	    cellMax.innerHTML = '<input type="text" id="parameterList[' + (tableRowNum -1) +'].maxSize" disabled name="parameterList[' + (tableRowNum -1) +'].maxSize"  size="5" />';
	    cellListValues.innerHTML = '<input type="text" id="parameterList[' + (tableRowNum -1) +'].listValues" disabled name="parameterList[' + (tableRowNum -1) +'].listValues" />';
	    cellReqd.innerHTML = '<input type="checkbox" id="parameterList[' + (tableRowNum -1) +'].isRequired" name="parameterList[' + (tableRowNum -1) +'].isRequired"/>';
	    cellDelete.innerHTML = '<img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" onClick="deleteParameter(' + (tableRowNum - 1) + ')" style="cursor:pointer;">';
	};

	function deleteParameter(rowIndex) {
		var table = document.getElementById("tblParameterList");
        var tableRowNum = table.rows.length;
        var selectedLocale=document.getElementById('selectedLocaleId').innerHTML;
        // move all rows below current row up
        	var parameterFieldsId = new Array('reportParameterId','name','disName','dataType','maxSize','listValues');

        for (i = rowIndex; i < tableRowNum - 2; i ++) {
            for (fieldIndex = 0; fieldIndex < parameterFieldsId.length; fieldIndex ++) {
                document.getElementById('parameterList[' + i +'].' + parameterFieldsId[fieldIndex]).value = 
                    document.getElementById('parameterList[' + (i + 1) +'].' + parameterFieldsId[fieldIndex]).value;
                if(parameterFieldsId[fieldIndex] == "listValues"){
                	if(document.getElementById('parameterList[' + i +'].dataType').value == "LIST"){
                		document.getElementById('parameterList[' + i +'].' + parameterFieldsId[fieldIndex]).disabled = false;
                	}
                	else{
                		document.getElementById('parameterList[' + i +'].' + parameterFieldsId[fieldIndex]).disabled = true;
                	}
                }
            }
            document.getElementById('parameterList[' + i + '].isRequired').checked = 
                document.getElementById('parameterList[' + (i + 1) + '].isRequired').checked;
        }
        // delete the last row
        table.deleteRow(tableRowNum -1);
        if (tableRowNum - 1 > 1) {
            table.rows[tableRowNum -2].cells[3].innerHTML = '<img src="<html:imagesPath/>arrow-down-grayed.png">';
        }
	};
	//Changes done for CI BRD 13-10-27 --ENDS
	
	function proceedReportTypeChangeDecider()
	{
		if(document.getElementById("ckbLimitExclude").checked)
			{
			//alert("Hello--->>>");
			proceedReportTypeChangeExclude();
			}
		else
			{
			proceedReportTypeChange();
			}
	}
	
	function proceedReportTypeChangeExclude() {
	    var parameterTable = document.getElementById('tblParameterList');
	    if (parameterTable.rows.length > 1) {
		    if (!confirm('<spring:message code="customerReportsAdmin.message.changeReportTypeWarning"/>')) {
				document.getElementById('reportDefinition.reportType').selectedIndex = previousReportTypeIndex;
			    return;
		    } else {
		        deleteAllParams(parameterTable);
		    }
	    }
    	previousReportTypeIndex = document.getElementById('reportDefinition.reportType').selectedIndex;
    	var vsbltSetting = (document.getElementById("ckbLimitAccess").checked) ? true : false;
	    var reportType = document.getElementById("reportDefinition.reportType").value;
	  //BRD 14-02-14 start
	    /* if (reportType == 'MU') {
		    	document.getElementById("divReportSourceId").style.display = "none";
		        document.getElementById("reportDefinition.reportSourceId").disabled = true;
		        document.getElementById("employeeAccountSelectionDIV").style.display = "none";
		        if(vsbltSetting) {
		        	document.getElementById("muDiv").style.display = "block";
		        	if(document.getElementById("showLimitCustomer").value == "true"){
		        		document.getElementById("divCust").style.display = "block";
		        		document.getElementById("showTblCustomerAssoc").style.display = "block";
		        	}
		        	//start
		        	//document.getElementById("accountIderror").style.display = "block";
		        	document.getElementById("typeQbr").style.display = "block";
		        	document.getElementById("searchCriteria").style.display = "block";
		        	//document.getElementById("sbutton").style.display = "block";
		        	//document.getElementById("cID").style.display = "block";
		        	document.getElementById("cname").style.display = "block";
		        	//end
		        	document.getElementById("treeDisplay").style.display = "block";
		        	document.getElementById("accountAssociation").style.display = "block";
		        	
		        	document.getElementById("compName").value="";
		        }else{
		        	document.getElementById("muDiv").style.display = "none";
		        	document.getElementById("divCust").style.display = "none";
		        	document.getElementById("showTblCustomerAssoc").style.display = "none";
		        	//start
		        	document.getElementById("accountIderror").style.display = "none";
		        	document.getElementById("typeQbr").style.display = "none";
		        	document.getElementById("searchCriteria").style.display = "none";
		        	document.getElementById("sbutton").style.display = "none";
		        	document.getElementById("cID").style.display = "none";
		        	document.getElementById("cname").style.display = "none";
		        	document.getElementById("mdmDiv").style.display = "none";
		        	//end
		        	document.getElementById("treeDisplay").style.display = "none";
		        	document.getElementById("accountAssociation").style.display = "none";
		        	
		        }		        
		        document.getElementById("divCustomerList").style.display = "none";
				document.getElementById("divReportParameters").style.display = "none"; //Done for BRD13-10-27 CI 13.10
		    } */ if (reportType == 'BO' || reportType == 'OA' || reportType == 'MU') {
		    	//alert("Manual upload--->>>>");
		    	document.getElementById("divReportSourceId").style.display = "block";
		    	document.getElementById("reportDefinition.reportSourceId").disabled = false;
		    	//if(vsbltSetting) {
		    		document.getElementById("employeeAccountSelectionDIV").style.display = "block";
		    		document.getElementById("divCustomerListExclude").style.display = "block";
		    	/* }else{
		    		document.getElementById("employeeAccountSelectionDIV").style.display = "none";
		    		document.getElementById("divCustomerListExclude").style.display = "none";
		    	} */
		    	document.getElementById("muDiv").style.display = "none";
		    	document.getElementById("divCust").style.display = "none";
		    	document.getElementById("showTblCustomerAssoc").style.display = "none";
		    	//start
	        	document.getElementById("accountIderror").style.display = "none";
	        	document.getElementById("typeQbr").style.display = "none";
	        	document.getElementById("searchCriteria").style.display = "none";
	        	document.getElementById("sbutton").style.display = "none";
	        	document.getElementById("cID").style.display = "none";
	        	document.getElementById("cname").style.display = "none";
	        	document.getElementById("mdmDiv").style.display = "none";
	        	//end
		    	document.getElementById("treeDisplay").style.display = "none";	
		    	document.getElementById("accountAssociation").style.display = "none";
		    	
				document.getElementById("divReportParameters").style.display = "block"; //Done for BRD13-10-27 CI 13.10
		    }
	  //BRD 14-02-14 end
	    changeAutoPass(reportType);
	};
	
	function proceedReportTypeChange() {
	    var parameterTable = document.getElementById('tblParameterList');
	    if (parameterTable.rows.length > 1) {
		    if (!confirm('<spring:message code="customerReportsAdmin.message.changeReportTypeWarning"/>')) {
				document.getElementById('reportDefinition.reportType').selectedIndex = previousReportTypeIndex;
			    return;
		    } else {
		        deleteAllParams(parameterTable);
		    }
	    }
    	previousReportTypeIndex = document.getElementById('reportDefinition.reportType').selectedIndex;
    	var vsbltSetting = (document.getElementById("ckbLimitAccess").checked) ? true : false;
	    var reportType = document.getElementById("reportDefinition.reportType").value;
	  //BRD 14-02-14 start
	    if (reportType == 'MU') {
		    	document.getElementById("divReportSourceId").style.display = "none";
		        document.getElementById("reportDefinition.reportSourceId").disabled = true;
		        document.getElementById("employeeAccountSelectionDIV").style.display = "none";
		        if(vsbltSetting) {
		        	document.getElementById("muDiv").style.display = "block";
		        	if(document.getElementById("showLimitCustomer").value == "true"){
		        		document.getElementById("divCust").style.display = "block";
		        		document.getElementById("showTblCustomerAssoc").style.display = "block";
		        	}
		        	//start
		        	//document.getElementById("accountIderror").style.display = "block";
		        	document.getElementById("typeQbr").style.display = "block";
		        	document.getElementById("searchCriteria").style.display = "block";
		        	//document.getElementById("sbutton").style.display = "block";
		        	//document.getElementById("cID").style.display = "block";
		        	document.getElementById("cname").style.display = "block";
		        	//end
		        	document.getElementById("treeDisplay").style.display = "block";
		        	document.getElementById("accountAssociation").style.display = "block";
		        	
		        	document.getElementById("compName").value="";
		        }else{
		        	document.getElementById("muDiv").style.display = "none";
		        	document.getElementById("divCust").style.display = "none";
		        	document.getElementById("showTblCustomerAssoc").style.display = "none";
		        	//start
		        	document.getElementById("accountIderror").style.display = "none";
		        	document.getElementById("typeQbr").style.display = "none";
		        	document.getElementById("searchCriteria").style.display = "none";
		        	document.getElementById("sbutton").style.display = "none";
		        	document.getElementById("cID").style.display = "none";
		        	document.getElementById("cname").style.display = "none";
		        	document.getElementById("mdmDiv").style.display = "none";
		        	//end
		        	document.getElementById("treeDisplay").style.display = "none";
		        	document.getElementById("accountAssociation").style.display = "none";
		        	
		        }		        
		        document.getElementById("divCustomerList").style.display = "none";
				document.getElementById("divReportParameters").style.display = "none"; //Done for BRD13-10-27 CI 13.10
		    } else if (reportType == 'BO' || reportType == 'OA') {
		    	document.getElementById("divReportSourceId").style.display = "block";
		    	document.getElementById("reportDefinition.reportSourceId").disabled = false;
		    	if(vsbltSetting) {
		    		document.getElementById("employeeAccountSelectionDIV").style.display = "block";
		    		document.getElementById("divCustomerList").style.display = "block";
		    	}else{
		    		document.getElementById("employeeAccountSelectionDIV").style.display = "none";
		    		document.getElementById("divCustomerList").style.display = "none";
		    	}
		    	document.getElementById("muDiv").style.display = "none";
		    	document.getElementById("divCust").style.display = "none";
		    	document.getElementById("showTblCustomerAssoc").style.display = "none";
		    	//start
	        	document.getElementById("accountIderror").style.display = "none";
	        	document.getElementById("typeQbr").style.display = "none";
	        	document.getElementById("searchCriteria").style.display = "none";
	        	document.getElementById("sbutton").style.display = "none";
	        	document.getElementById("cID").style.display = "none";
	        	document.getElementById("cname").style.display = "none";
	        	document.getElementById("mdmDiv").style.display = "none";
	        	//end
		    	document.getElementById("treeDisplay").style.display = "none";	
		    	document.getElementById("accountAssociation").style.display = "none";
		    	
				document.getElementById("divReportParameters").style.display = "block"; //Done for BRD13-10-27 CI 13.10
		    }
	  //BRD 14-02-14 end
	    changeAutoPass(reportType);
	};

	function changeAutoPass(reportType) {
	    var msgAutoPassHTML;
	    if (reportType == 'MU' || reportType == 'BO') {
	    	msgAutoPassHTML = '<spring:message code="customerReportsAdmin.label.requireMDM"/>';
	    } else if (reportType == 'OA') {
	    	msgAutoPassHTML = '<spring:message code="customerReportsAdmin.label.oracleAnalytics.passAccountID"/>';
	    }
	    
	    document.getElementById('msgAutoPass').innerHTML = msgAutoPassHTML;
	};

	// delete all parameters
	function deleteAllParams(parameterTable) {
		var paramAmount = parameterTable.rows.length;
        for (var i = paramAmount - 1; i > 0; i --) {
	        parameterTable.deleteRow(i);
        }
	};

	function toggleAccountSelection() {
//		commented for CI5 for CI5 multiple customers report
		/* var vsbltSetting = (document.getElementById("ckbLimitAccess").checked) ? "visible" : "hidden";
	    document.getElementById("employeeAccountSelectionDIV").style.visibility = vsbltSetting; */
//	    added for CI5 multiple customers report
	    var vsbltSetting = (document.getElementById("ckbLimitAccess").checked) ? true : false;
	    if(vsbltSetting)
    	{
		    var ckbLimitExclude = (document.getElementById("ckbLimitExclude").checked) ? true : false;
		    if(ckbLimitExclude){
		    	 document.getElementById("ckbLimitExclude").checked = false;
		    	jQuery('#ckbLimitExclude').trigger('click');	    	
		    }
		    document.getElementById("divCustomerListExclude").style.display = "none";
    	}
	    var reportType = document.getElementById("reportDefinition.reportType").value;
		if(vsbltSetting) {			
			document.getElementById("employeeAccountSelectionDIV").style.display = 'block';
			document.getElementById("divCustomerList").style.display = 'block';
			document.getElementById("muDiv").style.display = "none"; //BRD 14-02-14
			document.getElementById("divCust").style.display = "none";
			document.getElementById("showTblCustomerAssoc").style.display = "none";
        	document.getElementById("typeQbr").style.display = "none";
        	document.getElementById("searchCriteria").style.display = "none";
        	document.getElementById("cname").style.display = "none";
        	//end

			if (reportType == 'MU') {
				var checkInclude = ${checkInclude};
				if(!checkInclude){
					//alert("Include");
				document.getElementById("showTblCustomerAssoc").innerHTML = "";
				document.getElementById("divCust").innerHTML = "";
				}
		    	document.getElementById("divReportSourceId").style.display = "none";
		        document.getElementById("reportDefinition.reportSourceId").disabled = true;
		        document.getElementById("employeeAccountSelectionDIV").style.display = "none";
		        document.getElementById("muDiv").style.display = "block";
		        if(document.getElementById("showLimitCustomer").value == "true"){
		        	document.getElementById("divCust").style.display = "block";
		        	document.getElementById("showTblCustomerAssoc").style.display = "block";
		        }
		        //start
	        	//document.getElementById("accountIderror").style.display = "block";
	        	document.getElementById("typeQbr").style.display = "block";
	        	document.getElementById("searchCriteria").style.display = "block";
	        	//document.getElementById("sbutton").style.display = "block";
	        	//document.getElementById("cID").style.display = "block";
	        	document.getElementById("cname").style.display = "block";
	        	//end
		        if(document.getElementById("compName").value=""){
		        document.getElementById("treeDisplay").style.display = "none";
		        document.getElementById("accountAssociation").style.display = "block";
		        
		        }
		        else{
		        document.getElementById("treeDisplay").style.display = "block";
		        document.getElementById("accountAssociation").style.display = "none";
		        
		        }
		        document.getElementById("divCustomerList").style.display = "none";
				document.getElementById("divReportParameters").style.display = "none"; //Done for BRD13-10-27 CI 13.10
				document.getElementById("compName").value="";
		    } else if (reportType == 'BO' || reportType == 'OA') {
		    	
		    	document.getElementById("divReportSourceId").style.display = "block";
		    	document.getElementById("reportDefinition.reportSourceId").disabled = false;
		    	document.getElementById("employeeAccountSelectionDIV").style.display = "block";
		    	document.getElementById("muDiv").style.display = "none";
		    	document.getElementById("divCust").style.display = "none";
		    	document.getElementById("showTblCustomerAssoc").style.display = "none";
		    	//start
	        	document.getElementById("accountIderror").style.display = "none";
	        	document.getElementById("typeQbr").style.display = "none";
	        	document.getElementById("searchCriteria").style.display = "none";
	        	document.getElementById("sbutton").style.display = "none";
	        	document.getElementById("cID").style.display = "none";
	        	document.getElementById("cname").style.display = "none";
	        	document.getElementById("mdmDiv").style.display = "none";
	        	//end
		    	document.getElementById("treeDisplay").style.display = "none";
		    	document.getElementById("accountAssociation").style.display = "none";
		    	
		    	document.getElementById("divCustomerList").style.display = "block";
				document.getElementById("divReportParameters").style.display = "block"; //Done for BRD13-10-27 CI 13.10
		    }
		}
		else {
			document.getElementById("employeeAccountSelectionDIV").style.display = "none";
			document.getElementById("divCustomerList").style.display = "none";
			document.getElementById("muDiv").style.display = "none"; //BRD 14-02-14
			document.getElementById("divCust").style.display = "none";
			document.getElementById("showTblCustomerAssoc").style.display = "none";
			//start
        	document.getElementById("accountIderror").style.display = "none";
        	document.getElementById("typeQbr").style.display = "none";
        	document.getElementById("searchCriteria").style.display = "none";
        	document.getElementById("sbutton").style.display = "none";
        	document.getElementById("cID").style.display = "none";
        	document.getElementById("cname").style.display = "none";
        	document.getElementById("mdmDiv").style.display = "none";
        	//end
			document.getElementById("treeDisplay").style.display = "none";
			document.getElementById("accountAssociation").style.display = "none";
			
		}
		
		  //BRD 14-02-14 start
		    
//	    end of addition for CI5 multiple customers report
	};
	
	//Added as part of BRD #14-07-03
	
	function toggleAccountSelectionForExclude() {
		//alert("Hello exclude");
	    var vsbltSettingExclude = (document.getElementById("ckbLimitExclude").checked) ? true : false;	   
	    if(vsbltSettingExclude){
		    	var ckbLimitAccess = (document.getElementById("ckbLimitAccess").checked) ? true : false;
		 	    if(ckbLimitAccess){
		 	    	document.getElementById("ckbLimitAccess").checked = false;
		 	    	jQuery('#ckbLimitAccess').trigger('click');
		 	    }	    	
		    	document.getElementById("divCustomerList").style.display = "none";
	    	}
	    var reportType = document.getElementById("reportDefinition.reportType").value;
		if(vsbltSettingExclude) {
		//	alert("vsbltSettingExclude--->>>"+vsbltSettingExclude);
			document.getElementById("employeeAccountSelectionDIV").style.display = '';
			document.getElementById("divCustomerListExclude").style.display = '';
			document.getElementById("muDiv").style.display = "none"; //BRD 14-02-14
			document.getElementById("divCust").style.display = "none";
			document.getElementById("showTblCustomerAssoc").style.display = "none";
			//start
        	//document.getElementById("accountIderror").style.display = "block";
        	document.getElementById("typeQbr").style.display = "none";
        	document.getElementById("searchCriteria").style.display = "none";
        	//document.getElementById("sbutton").style.display = "block";
        	//document.getElementById("cID").style.display = "block";
        	document.getElementById("cname").style.display = "none";
        	//end

			if (reportType == 'BO' || reportType == 'OA' || reportType == 'MU') {
		    	//alert("Hi");
		    	document.getElementById("divReportSourceId").style.display = "block";
		    	document.getElementById("reportDefinition.reportSourceId").disabled = false;
		    	document.getElementById("employeeAccountSelectionDIV").style.display = "block";
		    	document.getElementById("muDiv").style.display = "none";
		    	document.getElementById("divCust").style.display = "none";
		    	document.getElementById("showTblCustomerAssoc").style.display = "none";
		    	//start
	        	document.getElementById("accountIderror").style.display = "none";
	        	document.getElementById("typeQbr").style.display = "none";
	        	document.getElementById("searchCriteria").style.display = "none";
	        	document.getElementById("sbutton").style.display = "none";
	        	document.getElementById("cID").style.display = "none";
	        	document.getElementById("cname").style.display = "none";
	        	document.getElementById("mdmDiv").style.display = "none";
	        	//end
		    	document.getElementById("treeDisplay").style.display = "none";
		    	document.getElementById("accountAssociation").style.display = "none";
		    	
		    	document.getElementById("divCustomerListExclude").style.display = "block";
				document.getElementById("divReportParameters").style.display = "block"; //Done for BRD13-10-27 CI 13.10
		    }
		}
		else {
			document.getElementById("employeeAccountSelectionDIV").style.display = "none";
			document.getElementById("divCustomerListExclude").style.display = "none";
			document.getElementById("muDiv").style.display = "none"; //BRD 14-02-14
			document.getElementById("divCust").style.display = "none";
			document.getElementById("showTblCustomerAssoc").style.display = "none";
			//start
        	document.getElementById("accountIderror").style.display = "none";
        	document.getElementById("typeQbr").style.display = "none";
        	document.getElementById("searchCriteria").style.display = "none";
        	document.getElementById("sbutton").style.display = "none";
        	document.getElementById("cID").style.display = "none";
        	document.getElementById("cname").style.display = "none";
        	document.getElementById("mdmDiv").style.display = "none";
        	//end
			document.getElementById("treeDisplay").style.display = "none";
			document.getElementById("accountAssociation").style.display = "none";
			
		}
		
		  //BRD 14-02-14 start
		    
//	    end of addition for CI5 multiple customers report
	};

	function swapParameterOrder(rowIndex, direction) {
		var addend;
		if (direction == "UP") {
			addend = -1;
		} else if (direction == "DOWN") {
			addend = 1;
		} else {
			showError("Invalid parameter",null, true);
			return;
		}
		var parameterFieldsId = new Array('reportParameterId','name','disName','dataType','maxSize','listValues');
		for (var fieldIndex = 0; fieldIndex < parameterFieldsId.length; fieldIndex ++) {
	        var temp = document.getElementById('parameterList[' + rowIndex + '].' + parameterFieldsId[fieldIndex]).value;
	        document.getElementById('parameterList[' + rowIndex +'].' + parameterFieldsId[fieldIndex]).value = 
		        document.getElementById('parameterList[' + (rowIndex + addend) +'].' + parameterFieldsId[fieldIndex]).value;
	        document.getElementById('parameterList[' + (rowIndex + addend) +'].' + parameterFieldsId[fieldIndex]).value = temp;
	        // changes to make the list value text box disable and enable 
	        if(parameterFieldsId[fieldIndex] == 'listValues'){
	        	
	        	if(document.getElementById('parameterList[' + rowIndex + '].dataType').value == "LIST"){
		        	document.getElementById('parameterList[' + rowIndex +'].' + parameterFieldsId[fieldIndex]).disabled = false;
		        }
		        else{
		        	document.getElementById('parameterList[' + rowIndex +'].' + parameterFieldsId[fieldIndex]).disabled = true;
		        }
	        	
	        	if(document.getElementById('parameterList[' + (rowIndex + addend) + '].dataType').value == "LIST"){
		        	document.getElementById('parameterList[' + (rowIndex + addend) +'].' + parameterFieldsId[fieldIndex]).disabled = false;
		        }
		        else{
		        	document.getElementById('parameterList[' + (rowIndex + addend) +'].' + parameterFieldsId[fieldIndex]).disabled = true;
		        }
	        }
	        
		}
		var tempIsRequired = document.getElementById('parameterList[' + rowIndex + '].isRequired').checked;
		document.getElementById('parameterList[' + rowIndex + '].isRequired').checked = 
			document.getElementById('parameterList[' + (rowIndex + addend) + '].isRequired').checked;
		document.getElementById('parameterList[' + (rowIndex + addend) + '].isRequired').checked = tempIsRequired;
	};
	
	function clearLocale(index) {
		document.getElementById('localeList' + index + '.link').innerHTML = '<spring:message code="customerReportsAdmin.label.add"/>';
		document.getElementById('localeList' + index + '.localeExist').innerHTML = '<spring:message code="customerReportsAdmin.label.no"/>';
		document.getElementById('reportDefinition.localeList' + index + '.name').value = "";
        document.getElementById('reportDefinition.localeList' + index + '.description').value = "";
        document.getElementById('localeList' + index + '.deleteImg').style.display = "none";
        document.getElementById('localeList' + index + '.deleteImg').style.visibility = "hidden";
        showLocale(index, true);
	};

	function saveLocale() {
		var index = document.getElementById('localeIndex').value;
		var reportName = document.getElementById('selectedLocaleReportName').value;
		var reportDescription = document.getElementById('selectedLocaleReportDescription').value;
		document.getElementById('reportDefinition.localeList' + index + '.name').value = reportName;
		document.getElementById('reportDefinition.localeList' + index + '.description').value = reportDescription;
		if (reportName.length == 0 && reportDescription == 0) {
		    document.getElementById('localeList' + index + '.link').innerHTML = '<spring:message code="customerReportsAdmin.label.add"/>';
		    document.getElementById('localeList' + index + '.localeExist').innerHTML = '<spring:message code="customerReportsAdmin.label.no"/>';
            document.getElementById('localeList' + index + '.deleteImg').style.display = "none";
            document.getElementById('localeList' + index + '.deleteImg').style.visibility = "hidden";
		} else {
			document.getElementById('localeList' + index + '.link').innerHTML = '<spring:message code="customerReportsAdmin.label.edit"/>';
			document.getElementById('localeList' + index + '.localeExist').innerHTML = '<spring:message code="customerReportsAdmin.label.yes"/>';
			document.getElementById('localeList' + index + '.deleteImg').style.display = "";
			document.getElementById('localeList' + index + '.deleteImg').style.visibility = "visible";
		}
	};
	
	function enableParameterInput(value,id){
		var tmpCellList = id.replace("dataType","listValues");
		var tmpCellMax = id.replace("dataType","maxSize");
		document.getElementById(tmpCellList).disabled = true;	
		document.getElementById(tmpCellMax).disabled = true;
		
		if(value=="LIST"){
			document.getElementById(tmpCellList).disabled = false;	
			document.getElementById(tmpCellMax).disabled = true;	
		}
		if(value=="STRING"){
			document.getElementById(tmpCellList).disabled = true;	
			document.getElementById(tmpCellMax).disabled = false;	
		}
	};
	
	//Changes done for CI BRD 13-10-27 --STARTS
	var submitType;
	function doSubmit(submitType) {
		var isRestrict="T";
		if(document.getElementById("ckbLimitExclude").checked)
			{
			isRestrict="F";
			}
		//Changes done in URL for CI BRD 13-10-27
		var saveReportDefinitionURL="${saveReportDefinitionURL}"+"&submitType=" + submitType+
		"&newReportFlag="+document.getElementById("newReportFlag").value+
		"&localeIndex="+document.getElementById("localeIndex").value+
		"&isRestrict="+isRestrict+
		"&prevCompanyMdmIdLevel="+document.getElementById('companyMdmIdLevel').value;
		if(document.getElementById("newReportFlag").value != "true"){
			//alert("not a new report");
			if(document.getElementById('companyMdmIdLevel').value==""){
				//alert("no value");
				document.getElementById('companyMdmIdLevel').value = document.getElementById('prevCompMdmIdLevel').value;
				//alert(document.getElementById('companyMdmIdLevel').value);
			}	
		}
		if(document.getElementById("searchCriteria").style.display == "none"){
			//alert("flg false");
			document.getElementById('companyMdmIdLevel').value = "";
			document.getElementById('prevCompMdmIdLevel').value = "";
		}
		
		jQuery("#formReportDefinitionDetail").attr("action", saveReportDefinitionURL);
		jQuery("#formReportDefinitionDetail").submit();
	};
	//Changes done for CI BRD 13-10-27 --ENDS
//  added for CI5 for multiple customer reports
	function addCustomer() {
		if(selectedLegalName == '') {
			// do nothing
		} else {
			addCustomerToTable();
			comboEmployeeAccountSelection.setComboValue('');
		}
	};
	
	function deleteCustomer(customerIndex) {
		//if(document.getElementById("ckbLimitAccess").checked)
	   // {
		var tableCust = document.getElementById('tblCustomerList');
		var tableCustRowNum = tableCust.rows.length;
		for (var i = customerIndex; i < tableCustRowNum - 1; i ++) {
			document.getElementById('customerList[' + i +']').value = document.getElementById('customerList[' + (i + 1) +']').value;
			document.getElementById('customerList[' + i +'].cust').innerHTML = document.getElementById('customerList[' + (i + 1) +'].cust').innerHTML;
		}
		
		tableCust.deleteRow(tableCustRowNum - 1);
	    //}
		/* else
		{
		var tableCust = document.getElementById('tblCustomerList');
		var tableCustRowNum = tableCust.rows.length;
		for (var i = customerIndex; i < tableCustRowNum - 1; i ++) {
			document.getElementById('customerList[' + i +']').value = document.getElementById('customerList[' + (i + 1) +']').value;
			document.getElementById('customerList[' + i +'].cust').innerHTML = document.getElementById('customerList[' + (i + 1) +'].cust').innerHTML;
			}
			
			tableCust.deleteRow(tableCustRowNum - 1);	
		} */	
	};
	
	//Added as part of BRD #14-07-03
	
	function addCustomerExclude() {
		if(selectedLegalName == '') {
			// do nothing
		} else {
			addCustomerToTableExclude();
			comboEmployeeAccountSelection.setComboValue('');
		}
	};
	
	//Added as part of BRD #14-07-03
	
	function deleteCustomerExclude(customerIndex) {
		var tableCust = document.getElementById('tblCustomerListExclude');
		var tableCustRowNum = tableCust.rows.length;
		for (var i = customerIndex; i < tableCustRowNum - 1; i ++) {
			document.getElementById('customerListExclude[' + i +']').value = document.getElementById('customerListExclude[' + (i + 1) +']').value;
			document.getElementById('customerListExclude[' + i +'].cust').innerHTML = document.getElementById('customerListExclude[' + (i + 1) +'].cust').innerHTML;
		}
		
		tableCust.deleteRow(tableCustRowNum - 1);
	};
	
	function addCustomerToTable() {
		var tableCust = document.getElementById('tblCustomerList');	
		var tableCustRowNum = tableCust.rows.length;
	    var newCustRow = tableCust.insertRow(tableCustRowNum);
	    newCustRow.style.height='22px';
	    var cellCust = newCustRow.insertCell(0);
		cellCust.style.width='93%';
	    var cellDeleteCust = newCustRow.insertCell(1);
		cellDeleteCust.style.width='7%';
	    
	    cellCust.innerHTML = '<input type="hidden" id="customerList[' + (tableCustRowNum) + ']" name="customerList[' + (tableCustRowNum) + ']" value="' + selectedLegalName + '" /><label id="customerList[' + (tableCustRowNum) + '].cust">' + selectedLegalName + '</label>';
	    cellDeleteCust.innerHTML = '<img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" onClick="deleteCustomer(' + (tableCustRowNum) + ')" style="cursor:pointer;">';
	};
	
	//Added as part of BRD #14-07-03
	
	function addCustomerToTableExclude() {
		var tableCust = document.getElementById('tblCustomerListExclude');	
		var tableCustRowNum = tableCust.rows.length;
	    var newCustRow = tableCust.insertRow(tableCustRowNum);
	    newCustRow.style.height='22px';
	    var cellCust = newCustRow.insertCell(0);
		cellCust.style.width='93%';
	    var cellDeleteCust = newCustRow.insertCell(1);
		cellDeleteCust.style.width='7%';
	    
	    cellCust.innerHTML = '<input type="hidden" id="customerListExclude[' + (tableCustRowNum) + ']" name="customerListExclude[' + (tableCustRowNum) + ']" value="' + selectedLegalName + '" /><label id="customerListExclude[' + (tableCustRowNum) + '].cust">' + selectedLegalName + '</label>';
	    cellDeleteCust.innerHTML = '<img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" onClick="deleteCustomerExclude(' + (tableCustRowNum) + ')" style="cursor:pointer;">';
	};
	
	//Added as part of BRD #14-07-03
	
	function includeExcludeDecider()
	{
		if(document.getElementById("ckbLimitAccess").checked)
			{
			//alert("if---");
			checkDuplicateCustomer();
			}
		if(document.getElementById("ckbLimitExclude").checked)
			{
			checkDuplicateCustomerExclude();
			}
		
	}
	
	function checkDuplicateCustomer() {
		//alert("inside duplicate---->>>");
		comboEmployeeAccountSelection.confirmValue();
		selectedLegalName = comboEmployeeAccountSelection.getActualValue();
		var tableCust = document.getElementById('tblCustomerList');	
		var tableCustRowNum = tableCust.rows.length;
		var isNew = true;
		for(i=0;i<tableCustRowNum;i++) {
			isNew = false;
			if(selectedLegalName == document.getElementById('customerList[' + i +']').value) {
				alert('<spring:message code="customerReportsAdmin.alert.duplicateCustomerName"/>');
				break;
			}
			else {
				isNew = true;
			}
		}
		if(isNew) {
			addCustomer();
		}
	};
	
	//Added as part of BRD #14-07-03
	
	function checkDuplicateCustomerExclude() {
		//alert("inside exclude----->>>>");
		comboEmployeeAccountSelection.confirmValue();
		selectedLegalName = comboEmployeeAccountSelection.getActualValue();
		var tableCust = document.getElementById('tblCustomerListExclude');	
		var tableCustRowNum = tableCust.rows.length;
		var isNew = true;
		for(i=0;i<tableCustRowNum;i++) {
			isNew = false;
			if(selectedLegalName == document.getElementById('customerListExclude[' + i +']').value) {
				alert('<spring:message code="customerReportsAdmin.alert.duplicateCustomerName"/>');
				break;
			}
			else {
				isNew = true;
			}
		}
		if(isNew) {
			addCustomerExclude();
		}
	};
	//BRD 14-02-14 start
	var previouscall="";
	//var jQuery=null;
	//jQuery = jQuery.noConflict(true);
	function ajaxCall(val){
		
		var onloadingImage=   "<img src='<html:imagesPath/>loading-icon.gif'  height='20' width='20' alt='loading'/>";
		
        var formname = "reportDefinition";
        var search;
       
        search =trim(val.value);
       
        search =val.value;
        document.getElementById('compName1').value = search;
        
        document.getElementById('formName').value = formname;
        
        var searchname = document.getElementById("compName1").value;
        var check;
        
        if( /[^a-zA-Z0-9]/.test( searchname ) ) {
           check=false;
        }
		if(searchname==""){
			document.getElementById('treeDisplay').style.display="none";
		}else{
			document.getElementById('treeDisplay').style.display="block";
		}
        
            if(search!=previouscall){
                previouscall =search;
                
                    jQuery("#treeDisplay").empty();
                    jQuery("#treeDisplay").append(onloadingImage);
        
                    jQuery.ajax(
                    {
                        type: "GET",
                        url: '${customerTreeDetails}',
                        data: "compName=" + search+"&formName="+formname,
                        success: function(message)
                        {
                            jQuery("#treeDisplay").empty();
                            if (message.length > 0)
                            {
                              // message = "Do you mean: " + message;
                                jQuery("#treeDisplay").append(message);
                                jQuery("#tree").treeview({
                                    collapsed: true,
                                    animated: "fast",
                                    control:"#sidetreecontrol",
                                    persist: "location"
                                });

                            }
                        }
                    });
        }
    }
	function trim(stringToTrim) {
        return stringToTrim.replace(/^\s+|\s+$/g,"");
    }

    function checkedCompanyLevel(legalId,domesticId,globalId,noOfZerosDomestic,noOfZerosGlobal){
        
    	//alert("noOfZerosDomestic:"+noOfZerosDomestic+" : "+"noOfZerosGlobal:"+noOfZerosGlobal);
    	var temp3 = legalId;
    	var temp2 = domesticId;
    	var temp1 = globalId;
    	if(legalId==""){
    		document.getElementById("accountAssociation").style.display = "none";
    		
    	}
    	if(noOfZerosDomestic>0){
  	 		if(noOfZerosDomestic=="1"){
  	 			domesticId="0"+domesticId;
  	 		}else if(noOfZerosDomestic=="2"){
  	 			domesticId="00"+domesticId;
  	 		}else if(noOfZerosDomestic=="3"){
  	 			domesticId="000"+domesticId;
  	 		}else if(noOfZerosDomestic=="4"){
  	 			domesticId="0000"+domesticId;
  	 		}else{
  	 			domesticId="0"+domesticId;
  	 		}  		  		
  	  	}
  		
  	 	if(noOfZerosGlobal>0){
  	 		if(noOfZerosGlobal=="1"){
  	 			globalId="0"+globalId;
  	 		}else if(noOfZerosGlobal=="2"){
  	 			globalId="00"+globalId;
  	 		}else if(noOfZerosGlobal=="3"){
  	 			globalId="000"+globalId;
  	 		}else if(noOfZerosGlobal=="4"){
  	 			globalId="0000"+globalId;
  	 		}else{
  	 			globalId="0"+globalId;
  	 		}  		  		
  	  	}
  	 	var temp_level1="";
  	 	var temp_level2="";
  	 	var temp_level3="";
  	 	if(temp3 == ""){
  	 		if(temp2 == ""){
  	 			temp_level1 = globalId+"/"+"Global";
  	 		}
  	 		else{
  	 			temp_level1 = globalId+"/"+"Global";
  	 			temp_level2 = domesticId+"/"+"Domestic";
  	 		}
  	 	}
  	 	else{
  	 		temp_level1 = globalId+"/"+"Global";
	 		temp_level2 = domesticId+"/"+"Domestic";
  	 		temp_level3 = legalId+"/"+"Legal";
  	 	}
  	 	//alert("prev_level before "+prev_level);
		prev_level = temp_level3+","+temp_level2+","+temp_level1+",";
		//alert("prev_level after "+prev_level);
		company_lvl=prev_level;
		//alert("temp_level"+temp_level);
		//alert(prev_level);
		//alert(company_lvl);
		/*
		if(typeof(prev_level) == "undefined"){
			//alert("In if");
			//company_lvl=temp_level;
			//prev_level=temp_level;
			company_lvl=temp_level3+","+temp_level2+","+temp_level1;
			//alert("##company_lvl"+company_lvl);
			//alert("##prev_level"+prev_level);
		}
		else{			
			//alert("In else");
			company_lvl=prev_level+temp_level3+","+temp_level2+","+temp_level1;
			prev_level=company_lvl;
			//alert("**company_lvl"+company_lvl);
			//alert("**prev_level"+prev_level);
		}
		*/
        //var company_lvl = mdmid+"/"+level;
        //alert("mdmid"+mdmid);
        //alert("level"+level);
        //alert("company_lvl"+company_lvl);
        //prev_level = company_lvl;
    	document.getElementById('companyMdmIdLevel').value = company_lvl;
    	document.getElementById('prevCompanyMdmIdLevel').value = document.getElementById('companyMdmIdLevel').value;
    	//alert("New value is ::"+document.getElementById('prevCompanyMdmIdLevel').value);
    	//alert("VALUE : "+document.getElementById('companyMdmIdLevel').value);
    	//alert("value is :: "+document.getElementById('companyMdmIdLevel').value);
    }	
  //BRD 14-02-14 end
  
  function SelectcompanyType(accountId,legalId,domesticId,globalId,noOfZerosDomestic,noOfZerosGlobal)
    {
	  	//alert("noOfZerosDomestic:"+noOfZerosDomestic+" : "+"noOfZerosGlobal:"+noOfZerosGlobal);
  		if(noOfZerosDomestic>0){
	 		if(noOfZerosDomestic=="1"){
	 			domesticId="0"+domesticId;
	 		}else if(noOfZerosDomestic=="2"){
	 			domesticId="00"+domesticId;
	 		}else if(noOfZerosDomestic=="3"){
	 			domesticId="000"+domesticId;
	 		}else if(noOfZerosDomestic=="4"){
	 			domesticId="0000"+domesticId;
	 		}else{
	 			domesticId="0"+domesticId;
	 		}  		  		
	  	}
		
	 	if(noOfZerosGlobal>0){
	 		if(noOfZerosGlobal=="1"){
	 			globalId="0"+globalId;
	 		}else if(noOfZerosGlobal=="2"){
	 			globalId="00"+globalId;
	 		}else if(noOfZerosGlobal=="3"){
	 			globalId="000"+globalId;
	 		}else if(noOfZerosGlobal=="4"){
	 			globalId="0000"+globalId;
	 		}else{
	 			globalId="0"+globalId;
	 		}  		  		
	  	}
	  var temp_level4 = accountId+"/"+"Account";
	  var temp_level3 = legalId+"/"+"Legal";
	  var temp_level2 = domesticId+"/"+"Domestic";
	  var temp_level1 = globalId+"/"+"Global";	
	  if(prev_lv3 == temp_level3){
	  	prev_association = prev_lv4+","+temp_level4+","+temp_level3+","+temp_level2+","+temp_level1+",";
	  }else{
		prev_association = temp_level4+","+temp_level3+","+temp_level2+","+temp_level1+",";
	  }
	  prev_lv4 = prev_lv4 + "," + temp_level4;	  
	  prev_lv3 = temp_level3;
	  //alert("prev_association is :: "+prev_association);
	  document.getElementById('companyMdmIdLevel').value = prev_association;
	  document.getElementById('prevCompanyMdmIdLevel').value = document.getElementById('companyMdmIdLevel').value;
	  //alert("@@ VALUE : "+document.getElementById('companyMdmIdLevel').value);
    }
  
  function RemovecompanyTypeAssociation(){
	  var prev_association='';	  
	  var prev_level='';
  }
  
  function RemoveTreeAssociation(){
	  var prev_association='';	  
	  var prev_level='';
	  document.getElementById("accountAssociation").style.display = "none";
	  
  }
    
//	end of addition for CI5 for multiple customer reports
	//Added for CI BRD 13-10-27--STARTS
	function showSaveReportPopup()
	{
		var table = document.getElementById("tblParameterList");
	    var tableRowNum = table.rows.length;
	    var newRow = table.insertRow(tableRowNum);
	    var cellDisplayNameHidden;
	    var currentLocale = document.getElementById('localeIndex').value;
	    var i=0;
	    for(var j=0; j<tableRowNum-1;j++)
	    	{
	    	  cellDisplayNameHidden = newRow.insertCell(j);
	    	if(currentLocale=="0")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayName_hidden" name="parameterList[' + (j) +'].displayName_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="1")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameSpanish_hidden" name="parameterList[' + (j) +'].displayNameSpanish_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="2")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameGerman_hidden" name="parameterList[' + (j) +'].displayNameGerman_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="3")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameFrench_hidden" name="parameterList[' + (j) +'].displayNameFrench_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="4")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameChina_hidden" name="parameterList[' + (j) +'].displayNameChina_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="5")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameChinaTw_hidden" name="parameterList[' + (j) +'].displayNameChinaTw_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="6")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNamePortugalBrazil_hidden" name="parameterList[' + (j) +'].displayNamePortugalBrazil_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="7")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNamePortugal_hidden" name="parameterList[' + (j) +'].displayNamePortugal_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="8")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameItaly_hidden" name="parameterList[' + (j) +'].displayNameItaly_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="9")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameKorea_hidden" name="parameterList[' + (j) +'].displayNameKorea_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="10")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameRussia_hidden" name="parameterList[' + (j) +'].displayNameRussia_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="11")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameJapan_hidden" name="parameterList[' + (j) +'].displayNameJapan_hidden" style="display:none"/>';
	    	}
		    if(currentLocale=="12")
	    	{
	    	cellDisplayNameHidden.innerHTML = '<input type="text" id="parameterList[' + (j) +'].displayNameTurkey_hidden" name="parameterList[' + (j) +'].displayNameTurkey_hidden" style="display:none"/>';
	    	}
	    	}
	    
	    
	    for(var k=0;k<tableRowNum-1;k++)
	    	{
		    	if(document.getElementById('localeIndex').value=="0")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayName_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="1")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameSpanish_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="2")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameGerman_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="3")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameFrench_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="4")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameChina_hidden').value
		    	=document.getElementById('parameterList[' + (i) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="5")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameChinaTw_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="6")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNamePortugalBrazil_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="7")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNamePortugal_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="8")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameItaly_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="9")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameKorea_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="10")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameRussia_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="11")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameJapan_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
			    if(document.getElementById('localeIndex').value=="12")
		    	{
		    	document.getElementById('parameterList[' + (k) +'].displayNameTurkey_hidden').value
		    	=document.getElementById('parameterList[' + (k) +'].disName').value;
		    	}
	    	}
	    
	    
		dialog = jQuery("#saveReportPopUp").dialog({
			autoOpen: false,
			open: function() {jQuery(".ui-dialog-titlebar-close").hide();},
			modal: true,
			closeOnEscape: false,
			title: 'Report Confirmation',
			resizable: false,
			position: "center",
			width: 450,
			height: 180
			
			});
		
		dialog.dialog("open");
		
		if (window.PIE) {
	        document.getElementById("popupYes").fireEvent("onmove");
	        document.getElementById("popupNo").fireEvent("onmove");
	    }
	    
		return false;
	}
	
	function confirmSave()
	{
		if (doValidate()) {
			showSaveReportPopup();
			document.getElementById("saveReportPopUp").style.display = 'block';
		}
		
	}
	
	function changetext()
    {
        var search=document.getElementById("searchCriteria").options[document.getElementById("searchCriteria").selectedIndex].value;
        if(search=="AccountId")
        {
            document.getElementById("cID").style.display="block";
            document.getElementById("sbutton").style.display="block";
            document.getElementById("cname").style.display="none";
            document.getElementById("muDiv").style.display="none";
            document.getElementById("divCust").style.display = "none";
            document.getElementById("showTblCustomerAssoc").style.display = "none";
            document.getElementById("mdmDiv").style.display="block";
        }else{
            document.getElementById("cID").style.display="none";
            document.getElementById("sbutton").style.display="none";
            document.getElementById("cname").style.display="block";
            document.getElementById("muDiv").style.display="block";            
            document.getElementById("mdmDiv").style.display="none";
        }
        /*
        var selectbox=document.getElementById("accountdetail");
        for(i=selectbox.options.length-1;i>=0;i--)
        {
            selectbox.remove(i);
        }

        for(i=0;i<document.EditAndCreateUser.length;i++)
        {
            if(document.EditAndCreateUser.elements[i].type=="checkbox" && (document.EditAndCreateUser.elements[i].name=="chk" || document.EditAndCreateUser.elements[i].name=="chk_L")&& document.EditAndCreateUser.elements[i].checked==true)
            {
                document.EditAndCreateUser.elements[i].checked=false;
            }
        }
        document.getElementById('association').style.display="none";
        */
        document.getElementById('treeDisplay').style.display="none";
        document.getElementById("accountIderror").style.display="none";
        document.getElementById('compName').value="";
        document.getElementById('accountAssociation').style.display="none";
        

    }
	function simpleSearch(){
        var search =document.getElementById("mdmSearch").value;
        /*search =trim(search);*/
        if(search.length > 0){
            //CompanySelectView(search,"AccountId")
        	//window.location.href="${customerMdmAccountDetails}";
        	jQuery.ajax(
                    {
                        type: "GET",
                        url: '${customerMdmAccountDetails}',
                        data: "search=" + search,
                        success: function(result)
                       	{
                            document.getElementById("accountAssociation").style.display = "block";
                            
                            document.getElementById("accountIderror").style.display="none";
                        	jQuery("#accountAssociation").html(result);
                            
                        }
                    });   	
        	
        }else{
            alert("Please enter MDM Account ID");            
            return false;
        }
    }
	
	function l4Search(l3value){
		//alert("here "+l3value);
		//alert(l3value.length);
        //var search =l3value;
        /*search =trim(search);*/
        //alert(search);
        
            //CompanySelectView(search,"AccountId")
        	//window.location.href="${customerMdmAccountDetails}";
        	jQuery.ajax(
                    {
                        type: "GET",
                        url: '${customerL4FromL3AccountDetails}',
                        data: "search=" + l3value,
                        success: function(result)
                       	{
                          	document.getElementById("accountAssociation").style.display = "block";
                          	
                        	jQuery("#accountAssociation").html(result);
                            
                        }
                    });   	
        	
        
    }
	
// 	function retrieveReportDefinitionID(){
		//put the result into parent page
// 		var url = '<portlet:resourceURL id="saveReportDefinitionURL"/>';
// 		url +="&accountId=" + accountId;
// 		doAjax(url, callbackGetShiptoAddress, null, null);
// 	}
	//Added for CI BRD 13-10-27--ENDS
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Report definition";
     addPortlet(portletName);
     pageTitle="Report definition";
</script>