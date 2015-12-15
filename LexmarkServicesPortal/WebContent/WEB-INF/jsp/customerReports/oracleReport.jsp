<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
span.required {
	color: #FF0000;
}
.displayGrid{width:100%;line-height:20px;}
</style>

<portlet:actionURL var="saveReportOracle">
    <portlet:param name="action" value="saveReportOracle"></portlet:param>
</portlet:actionURL>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
<div class="journal-content-article">
      <h1>Reports</h1>
</div>


	<div class="content">
		<%@ include file="/WEB-INF/jsp/customerReports/leftPanel.jsp"%>
		<div class="right-column">
			<div class="portlet-wrap">
				<div class="portlet-header rounded shadow">
				<!-- added for employee report -->
				<c:if test="${not isEmployee}">
				<table class="displayGrid">
              	<thead>
                	<tr><th>${definitionDisplayForm.definitionName}</th></tr>
                </thead>
                </table>
            	</c:if>
            	<c:if test="${isEmployee}">
				<table class="displayGrid width-100per">
             	 <thead>
                <tr><th>${definitionDisplayForm.definitionName} - ${accountName}</th></tr>
                </thead>
              	</table>
				</c:if>
				<!-- end of addition for employee report -->
				</div>
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="customerReports.scheduleOneReport.label.parametersDesc"/>
				<br>
				<br>
				<div class="portlet-wrap-inner">
					<form:form id="oracleReportForm" commandName="reportOracleForm" method="post" action="${saveReportOracle}" onsubmit="return doValidate();">
					<input type="hidden" id="docDefinitionId" name="docDefinitionId" value="${docDefinitionId}"/>
	 				<input type="hidden" id="roleCategoryId" name="roleCategoryId" value="${roleCategoryId}"/>
					<input type="hidden" id="editReport" name="editReport" value="${editReport}"/>
					<!-- 			Changes for 14.9 Potential CR 14722 -->
							<input id='roleCategoryIdArrayPlus1' type='hidden' name='roleCategoryIdArrayPlus'/>
							<input id='roleCategoryIdArrayMinus1' type='hidden' name='roleCategoryIdArrayMinus'/>
						<table id="tblParameters" width="500px" class="vertical-align-top">
							<c:forEach items="${reportOracleForm.parameters}" var="parameter" varStatus="counter" begin="0">
							<tr valign="top">
								<td width="20px" align="right">
									<c:if test="${parameter.isParameterRequired}">
									<span class="required">*</span>
									</c:if>&nbsp;
									<form:hidden path="parameters[${counter.index}].reportParameterId"/>
									<form:hidden path="parameters[${counter.index}].contactId"/>
									<form:hidden path="parameters[${counter.index}].isParameterRequired"/>
									<form:hidden path="parameters[${counter.index}].parameterType"/>
								</td>
								<td align="left" width="250px">
									<label id="parameters${counter.index}.displayName">${parameter.displayName}</label>:&nbsp;
								</td>
							<c:choose>
							<c:when test="${parameter.parameterType == 'analyticsDate'}">
								<td align="right">
									<form:select path="parameters[${counter.index}].parameterValue" class="width-180px">
										<form:option value="" label=""/>
										<form:options items="${rolling24Months}"/>
									</form:select>
								</td>
							</c:when>
	
							<c:when test="${parameter.parameterType == 'analyticsDateBetween'}">
								<td align="right">									
									<spring:message code="chartPortlet.label.from"/>:&nbsp;
									<form:select path="parameters[${counter.index}].parameterValue" class="width-180px">
										<form:option value="" label=""/>
										<form:options items="${rolling24Months}"/>
									</form:select><br>
									<spring:message code="chartPortlet.label.to"/>:&nbsp;
									<form:select path="parameters[${counter.index}].parameterValue2" class="width-180px">
										<form:option value="" label=""/>
										<form:options items="${rolling24Months}"/>
									</form:select><br>
								</td>
							</c:when>
							
							<c:when test="${parameter.parameterType == 'analyticsQuarter'}">
								<td align="right">
									<form:select path="parameters[${counter.index}].parameterValue" class="width-180px">
										<form:option value="" label=""/>
										<form:options items="${rollingQuarters}"/>
									</form:select>
								</td>
							</c:when>
							
							<c:when test="${parameter.parameterType == 'analyticsQuarterBetween'}">
								<td align="right">
									<spring:message code="chartPortlet.label.to"/>:&nbsp;
									<form:select path="parameters[${counter.index}].parameterValue2" class="width-180px">
										<form:option value="" label=""/>
										<form:options items="${rollingQuarters}"/>
									</form:select><br>
									<spring:message code="chartPortlet.label.from"/>:&nbsp;
									<form:select path="parameters[${counter.index}].parameterValue" class="width-180px">
										<form:option value="" label=""/>
										<form:options items="${rollingQuarters}"/>
									</form:select>
								</td>
							</c:when>
							
							<c:when test="${parameter.parameterType == 'LIST'}">
								<td align="right">
									<form:select path="parameters[${counter.index}].parameterValue" class="width-180px">
										<form:option value="" label=""/>
										<form:options items="${parameter.listOptions}" itemValue="value" itemLabel="label"/>
									</form:select>
								</td>
							</c:when>
							
							<c:when test="${parameter.parameterType == 'STRING'}">
								<td align="right">
									<form:input path="parameters[${counter.index}].parameterValue" class="width-165px"/>
									<form:hidden path="parameters[${counter.index}].maxSize"/>
								</td>
							</c:when>
							</c:choose>
							</tr>
							<tr height="5px"><td>&nbsp;</td></tr>
							</c:forEach>
							<tr>
								<td colspan="3" align="right">
									<a href="javascript:onCancelPageReport();" class="button_cancel anchor-button2"><spring:message code="button.cancel"/></a>&nbsp;
									<a href="javascript:void(0);" onclick="doSubmit();" class="button anchor-button1"><spring:message code="button.submit"/></a>
								</td>
							</tr>
						</table>
					</form:form>
				</div>
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div>
				</div>			
			</div>
		</div>
	</div>
</div>	
<script type="text/javascript">
function doValidate(){
	var valid = true;
	var areAllRequiredParamValued = true;
	var areAllBetweenDatesSelected = true;
	var areAllBetweenQuartersSelected = true;
	var isDateToBeforeFrom = false;
	var isQuarterToBeforeFrom = false;
	var isTooLong = false;
	clearMessage();

	var paramAmount = (document.getElementById("tblParameters").rows.length - 1) /2;
    var from;
    var to;

    for (var i = 0; i < paramAmount; i ++) {
        var paramRequired = document.getElementById('parameters' + i + '.isParameterRequired').value;
	    var paramType = document.getElementById('parameters' + i + '.parameterType').value;

		if (paramType == 'analyticsDateBetween') {
		    from = document.getElementById('parameters' + i + '.parameterValue').value;
		    to = document.getElementById('parameters' + i + '.parameterValue2').value;

		    if (paramRequired == 'true') {
			    if (areAllRequiredParamValued && (from == "" || to == "")) {
			    	areAllRequiredParamValued = false;
			    	showError('<spring:message code="customerReports.obiee.paramMustValued"/>', null, true);
			    	continue;
			    } else if (!isDateToBeforeFrom && from != "" && to != "" && compareFromTo(from, to) > 0) {
					isDateToBeforeFrom = true;
					showError('<spring:message code="chartPortlet.message.dateValues.toBeforeFrom"/>', null, true);
					continue;
				}
		    } else {
				if (areAllBetweenDatesSelected && from != "" && to == "" ||
						areAllBetweenDatesSelected && from == "" && to != "") {
					areAllBetweenDatesSelected = false;
					showError('<spring:message code="customerReports.obiee.date.oneSpecifiedOtherNot"/>', null, true);
				} else if (!isDateToBeforeFrom && from != "" && to != "" && compareFromTo(from, to) > 0) {
					isDateToBeforeFrom = true;
					showError('<spring:message code="chartPortlet.message.dateValues.toBeforeFrom"/>', null, true);
				}
		    }
	    } else if (paramType == 'analyticsQuarterBetween') {
		    from = document.getElementById('parameters' + i + '.parameterValue').value;
		    to = document.getElementById('parameters' + i + '.parameterValue2').value;

		    if (paramRequired == 'true') {
			    if (areAllRequiredParamValued && (from == "" || to == "")) {
			    	areAllRequiredParamValued = false;
			    	showError('<spring:message code="customerReports.obiee.paramMustValued"/>', null, true);
			    	continue;
			    } else if (!isQuarterToBeforeFrom && from != "" && to != "" && compareFromTo(from, to) > 0) {
					isQuarterToBeforeFrom = true;
					showError('<spring:message code="chartPortlet.message.quarterValues.toBeforeFrom"/>', null, true);
					continue;
				}
		    } else {
				if (areAllBetweenQuartersSelected && from != "" && to == "" ||
						areAllBetweenQuartersSelected && from == "" && to != "") {
					areAllBetweenDatesSelected = false;
					showError('<spring:message code="customerReports.obiee.quarter.oneSpecifiedOtherNot"/>', null, true);
				} else if (!isQuarterToBeforeFrom && from != "" && to != "" && compareFromTo(from, to) > 0) {
					isQuarterToBeforeFrom = true;
					showError('<spring:message code="chartPortlet.message.quarterValues.toBeforeFrom"/>', null, true);
				}
		    }
	    } else {
		    if (paramRequired == 'true' &&areAllRequiredParamValued && jQuery.trim(document.getElementById('parameters' + i + '.parameterValue').value) == "") {
		    	areAllRequiredParamValued = false;
		    	showError('<spring:message code="customerReports.obiee.paramMustValued"/>', null, true);
		    }

		    if (paramType == "STRING" && document.getElementById('parameters' + i + '.maxSize').value != "-1" &&
		    		document.getElementById('parameters' + i + '.parameterValue').value.length > parseInt(document.getElementById('parameters' + i + '.maxSize').value)) {
			    isTooLong = true;
			    showError('<spring:message code="message.field.lengthLargerThan" arguments="' +
			    		document.getElementById('parameters' + i + '.displayName').innerHTML +
			    		',' + document.getElementById('parameters' + i + '.maxSize').value + '"/>', null, true);
		    }
	    }
    }

    if (!areAllRequiredParamValued ||
    	    !areAllBetweenDatesSelected ||
    	    !areAllBetweenQuartersSelected||
    	    isDateToBeforeFrom ||
    	    isQuarterToBeforeFrom ||
    	    isTooLong) {
	    valid = false;
    }
    
	return valid;
};

function compareFromTo(from, to) {
	if (from == to) {
		return 0;
	} else if (from > to) {
		return 1;
	} else {
		return -1;
	}
};

function doSubmit(){
	if (doValidate()) {
		var roleCategoryIdArrayPlusList = ${roleCategoryIdArrayPlusList};
		var roleCategoryIdArrayMinusList = ${roleCategoryIdArrayMinusList};
		document.getElementById('roleCategoryIdArrayPlus1').value = roleCategoryIdArrayPlusList;
		document.getElementById('roleCategoryIdArrayMinus1').value = roleCategoryIdArrayMinusList;
		jQuery("#oracleReportForm").submit();
	}
};

function onCancelPageReport() {
	var editReportParam="${editReport}";
	if(editReportParam=="editReport"){
		var roleCategoryIdArrayPlusList = ${roleCategoryIdArrayPlusList};
		var roleCategoryIdArrayMinusList = ${roleCategoryIdArrayMinusList};
		document.getElementById('roleCategoryIdArrayPlus1').value = roleCategoryIdArrayPlusList;
		document.getElementById('roleCategoryIdArrayMinus1').value = roleCategoryIdArrayMinusList;
	var location="<portlet:renderURL><portlet:param name='action' value='showReports'/></portlet:renderURL>&docDefinitionId="+${docDefinitionId}+"&roleCategoryIdArrayPlus="+${roleCategoryIdArrayPlusList}+"&roleCategoryIdArrayMinus="+${roleCategoryIdArrayMinusList};
	//alert('if');
	}else{
	var location="<portlet:renderURL></portlet:renderURL>";
	//alert('else');
	}
	//alert(editReportParam);
	//alert(location);
	window.location.href = location;
};

</script>		
<script type="text/javascript">
//---- Ominture script 
     portletName = "Oracle Anaytics Report";
     addPortlet(portletName);
     pageTitle="Oracle Anaytics Report";
</script>