<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style>
div.buttomLine {
	border-bottom: #999 1px dotted;	
}
</style>
		<portlet:actionURL var="saveChartURL">
	        <portlet:param name="action" value="saveChartParameter"/>
	    </portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
		<div class="portlet-wrap">
			<div class="portlet-header">
				<h3><spring:message code="chartPortlet.label.chartDescription"/></h3>
			</div>
			<br>
	    	<div class="portlet-wrap-inner">
			    <div class="width-100per">
			    <form:form id="formChart" method="post" action="${saveChartURL}" commandName="chartParameterDetailContract.chartParameterDetail" onsubmit="return doValidate();">
			        <table>
			            <tr><td>
			                <label class="labelBold">
			                    *<spring:message code="chartPortlet.label.chartPath"/>:
			                </label>
			                 <form:input id="chartPath" path="chartPath" size="30"/>
			            </td></tr>
						<tr><td>&nbsp;</td></tr>
			            <tr>
			            	<td>
			                    <form:checkbox path="isRequireMdmParams"/>
			                    <spring:message code="chartPortlet.label.requireMDM"/>
			                </td>
			            </tr>
			     	</table>
			        <br/>
			        <div>
			        	<label class="labelBold"><spring:message code="chartPortlet.label.setChartParameters"/></label><br>
			        	<a href="javascript:void(0)" onClick="addParameter()"><spring:message code="chartPortlet.label.addChartParameters"/></a>
			        </div>
			        <div class="margin-left-10px">
				        <table id="tblParameterList">
				            <tr height="25px">
				                <td class="labelBold width-200px">
				                    <spring:message code="chartPortlet.label.parameterType"/>&nbsp;
				                </td>
				                <td class="labelBold width-180px">
				                    <spring:message code="chartPortlet.label.parameterName"/>&nbsp;
				                </td>
				                <td class="labelBold width-170px" align="right">
				                    <spring:message code="chartPortlet.label.ParameterValue"/>&nbsp;
				                </td>
				                <td class="width-30px" align="right">&nbsp;</td>
				    		</tr>
			    		<c:set var="parameterNum" value="${fn:length(chartParameterDetailContract.chartParameterDetail.parameterList)}"/>
			        	<c:forEach items="${chartParameterDetailContract.chartParameterDetail.parameterList}" var="chartParameter" varStatus="counter" begin="0">
				            <tr valign="top">
				                <td>
							    	<select id="parameterList[${counter.index}].paramType" name="parameterList[${counter.index}].paramType" onChange="refreshParamValueField(this)" class="width-180px">
							    		<option value="analyticsDate" <c:if test="${chartParameter.paramType eq 'analyticsDate'}">selected</c:if> ><spring:message code="obiee.paramType.analyticsDate"/></option>
							    		<option value="analyticsDateBetween" <c:if test="${chartParameter.paramType eq 'analyticsDateBetween'}">selected</c:if> ><spring:message code="obiee.paramType.analyticsDateBetween"/></option>
							    		<option value="analyticsQuarter" <c:if test="${chartParameter.paramType eq 'analyticsQuarter'}">selected</c:if> ><spring:message code="obiee.paramType.analyticsQuarter"/></option>
							    		<option value="analyticsQuarterBetween" <c:if test="${chartParameter.paramType eq 'analyticsQuarterBetween'}">selected</c:if> ><spring:message code="obiee.paramType.analyticsQuarterBetween"/></option>
							    		<option value="string" <c:if test="${chartParameter.paramType eq 'string'}">selected</c:if> ><spring:message code="obiee.paramType.string"/></option>
							    	</select>
				                </td>
				                <td>
				                    <input type="text" id="parameterList[${counter.index}].paramName" name="parameterList[${counter.index}].paramName" value='${chartParameter.paramName}'>
				                </td>
				                <td align="right">
				                <c:choose>
				                	<c:when test="${chartParameter.paramType eq 'analyticsDate'}">
				                	<select id="parameterList[${counter.index}].paramValue" name="parameterList[${counter.index}].paramValue" class="width-120px">
				                		<c:forEach items="${rolling24Months}" var="month">
				                			<option value="${month}" <c:if test="${chartParameter.paramValue == month}">selected</c:if> >${month}</option>
				                		</c:forEach>
				                	</select>
				                	</c:when>
				                	<c:when test="${chartParameter.paramType eq 'analyticsDateBetween'}">
				                	<table>
				                		<tr>
				                			<td><spring:message code="chartPortlet.label.to"/>:&nbsp;</td>
				                			<td>
							                	<select id="parameterList[${counter.index}].paramValue2" name="parameterList[${counter.index}].paramValue2" class="width-120px">
							                		<c:forEach items="${rolling24Months}" var="month">
							                			<option value="${month}" <c:if test="${chartParameter.paramValue2 == month}">selected</c:if> >${month}</option>
							                		</c:forEach>
							                	</select>
				                			</td>
				                		</tr>
				                		<tr>
				                			<td><spring:message code="chartPortlet.label.from"/>:&nbsp;</td>
				                			<td>
							                	<select id="parameterList[${counter.index}].paramValue" name="parameterList[${counter.index}].paramValue" class="width-120px">
							                		<c:forEach items="${rolling24Months}" var="month">
							                			<option value="${month}" <c:if test="${chartParameter.paramValue == month}">selected</c:if> >${month}</option>
							                		</c:forEach>
							                	</select>
				                			</td>
				                		</tr>
				                	</table>
				                	</c:when>
				                	<c:when test="${chartParameter.paramType eq 'analyticsQuarter'}">
				                	<select id="parameterList[${counter.index}].paramValue" name="parameterList[${counter.index}].paramValue" class="width-120px">
				                		<c:forEach items="${rollingQuarters}" var="quarter">
				                			<option value="${quarter}" <c:if test="${chartParameter.paramValue == quarter}">selected</c:if> >${quarter}</option>
				                		</c:forEach>
				                	</select>
				                	</c:when>
				                	<c:when test="${chartParameter.paramType eq 'analyticsQuarterBetween'}">
				                	<table>
				                		<tr>
				                			<td><spring:message code="chartPortlet.label.to"/>:&nbsp;</td>
				                			<td>
							                	<select id="parameterList[${counter.index}].paramValue2" name="parameterList[${counter.index}].paramValue2" class="width-120px">
							                		<c:forEach items="${rollingQuarters}" var="quarter">
							                			<option value="${quarter}" <c:if test="${chartParameter.paramValue2 == quarter}">selected</c:if> >${quarter}</option>
							                		</c:forEach>
							                	</select>
				                			</td>
				                		</tr>
				                		<tr>
				                			<td><spring:message code="chartPortlet.label.from"/>:&nbsp;</td>
				                			<td>
							                	<select id="parameterList[${counter.index}].paramValue" name="parameterList[${counter.index}].paramValue" class="width-120px">
							                		<c:forEach items="${rollingQuarters}" var="quarter">
							                			<option value="${quarter}" <c:if test="${chartParameter.paramValue == quarter}">selected</c:if> >${quarter}</option>
							                		</c:forEach>
							                	</select>
				                			</td>
				                		</tr>
				                	</table>
				                	</c:when>
				                	<c:when test="${chartParameter.paramType eq 'string'}">
				                	<input type="text" id="parameterList[${counter.index}].paramValue" name="parameterList[${counter.index}].paramValue" value='${chartParameter.paramValue}'>
				                	</c:when>
				                </c:choose>
				                </td>
								<td align="right">
			                    	<img id="parameterList[${counter.index}]Img" class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="deleteParameter(this)" >
			                	</td>
				             </tr>
			             </c:forEach>
			    	</table>
			    </div>
				               
			        <table class="width-550px">
			    		<tr><td><br></td></tr>
			    		<tr>
			    			<td align="right">
			  				 	<button class="button_cancel" type="button" onClick="onCancelClick()"><spring:message code="button.cancel"/></button>&nbsp;
			   					<button class="button" type="submit"><spring:message code="button.submit"/></button>
			    			</td>
			    		</tr>
			    	</table>
			    </form:form>
			 </div>
			 </div>
			 <div class="portlet-footer">
					<div class="portlet-footer-inner"></div>
			 </div>
	    </div>
	</div>

<script type="text/javascript">
	var monthOptions;
	<c:forEach items="${rolling24Months}" var="month">
	monthOptions += '<option value="${month}">${month}</option>';
	</c:forEach>

	var quarterOptions;
	<c:forEach items="${rollingQuarters}" var="quarter">
	quarterOptions += '<option value="${quarter}">${quarter}</option>';
	</c:forEach>

	function onCancelClick() {
		window.location.href = "<portlet:renderURL></portlet:renderURL>";
	};
	function addParameter() {
	    var table = document.getElementById("tblParameterList");
	    var tableRowNum = table.rows.length;
	    var newRow = table.insertRow(tableRowNum);
	    newRow.vAlign = "top";
	    var cellType = newRow.insertCell(0);
	    var cellName = newRow.insertCell(1);
	    var cellDisplayName = newRow.insertCell(2);
	    cellDisplayName.align = "right";
	    var cellDelete = newRow.insertCell(3);
	    cellDelete.align = "right";
	    cellType.innerHTML = '<select id="parameterList[' + (tableRowNum -1) +'].paramType" name="parameterList[' + (tableRowNum -1) +'].paramType" onChange="refreshParamValueField(this)" style="width:180px"><option value=""><spring:message code="obiee.paramType.pleaseSelect"/></option><option value="analyticsDate"><spring:message code="obiee.paramType.analyticsDate"/></option><option value="analyticsDateBetween"><spring:message code="obiee.paramType.analyticsDateBetween"/></option><option value="analyticsQuarter"><spring:message code="obiee.paramType.analyticsQuarter"/></option><option value="analyticsQuarterBetween"><spring:message code="obiee.paramType.analyticsQuarterBetween"/></option><option value="string"><spring:message code="obiee.paramType.string"/></option></select>';
	    cellName.innerHTML = '<input type="text" id="parameterList[' + (tableRowNum -1) +'].paramName" name="parameterList[' + (tableRowNum -1) +'].paramName"/>';
	    cellDisplayName.innerHTML = '<input type="text" id="parameterList[' + (tableRowNum -1) +'].paramValue" name="parameterList[' + (tableRowNum -1) +'].paramValue"/>';
	    cellDelete.innerHTML = '<img id="parameterList[' + (tableRowNum - 1) + ']Img" class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" onClick="deleteParameter(this)" style="cursor:pointer;">';
	};

	function doValidate(){
		var valid=true;
		var areAllFieldsEntered = true;
		var isDateToBeforeFrom = false;
		var isQuarterToBeforeFrom = false;
		clearMessage();
		
		var chartPath = jQuery.trim(document.getElementById('chartPath').value);
		if (chartPath==''){
			valid=false;
			showError("<spring:message code='chartPortlet.label.chartPath.required'/>" ,null, true);
		}

	    var tableRowNum = document.getElementById("tblParameterList").rows.length;
	    var paramAmount = tableRowNum - 1;
	    var from;
	    var to;

	    for (var i = 0; i < paramAmount; i ++) {
		    var paramType = document.getElementById('parameterList[' + i + '].paramType').value;
		    if (areAllFieldsEntered && paramType == '') {
		    	areAllFieldsEntered = false;
		    	showError('<spring:message code="chartPortlet.message.allFieldsRequired"/>', null, true);
		    	continue;
		    }
		    
		    if (areAllFieldsEntered && jQuery.trim(document.getElementById('parameterList[' + i + '].paramName').value) == '') {
		    	areAllFieldsEntered = false;
		    	showError('<spring:message code="chartPortlet.message.allFieldsRequired"/>', null, true);
		    }

			if (paramType == 'analyticsDateBetween') {
			    from = document.getElementById('parameterList[' + i + '].paramValue').value;
			    to = document.getElementById('parameterList[' + i + '].paramValue2').value;

			    if (areAllFieldsEntered && (from == "" || to == "")){
			    	areAllFieldsEntered = false;
			    	showError('<spring:message code="chartPortlet.message.allFieldsRequired"/>', null, true);
			    	continue;
				}
				if (!isDateToBeforeFrom && from != "" && to != "" && compareFromTo(from, to) > 0) {
					isDateToBeforeFrom = true;
					showError('<spring:message code="chartPortlet.message.dateValues.toBeforeFrom"/>', null, true);
				}
		    } else if (paramType == 'analyticsQuarterBetween') {
			    from = document.getElementById('parameterList[' + i + '].paramValue').value;
			    to = document.getElementById('parameterList[' + i + '].paramValue2').value;

			    if (areAllFieldsEntered && (from == "" || to == "")){
			    	areAllFieldsEntered = false;
			    	showError('<spring:message code="chartPortlet.message.allFieldsRequired"/>', null, true);
			    	continue;
				}
				if (!isQuarterToBeforeFrom && from != "" && to != "" && compareFromTo(from, to) > 0) {
					isQuarterToBeforeFrom = true;
					showError('<spring:message code="chartPortlet.message.quarterValues.toBeforeFrom"/>', null, true);
				}
		    } else {
			    if (areAllFieldsEntered && jQuery.trim(document.getElementById('parameterList[' + i + '].paramValue').value) == "") {
			    	areAllFieldsEntered = false;
			    	showError('<spring:message code="chartPortlet.message.allFieldsRequired"/>', null, true);
			    }
		    }
	    }

	    if (!areAllFieldsEntered ||
	    	    isDateToBeforeFrom ||
	    	    isQuarterToBeforeFrom) {
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

	function deleteParameter(object) {
		var paramIndex = parseInt(object.id.replace("parameterList[", "").replace("]Img"), "");
	  	var table = document.getElementById("tblParameterList");
        var tableRowNum = table.rows.length;

        // change id and name of parameters in the clicked row
        for (i = paramIndex; i < tableRowNum - 2; i ++) {
            var parameters = jQuery("*[id^=parameterList[" + (i + 1) + "]");
            for (j = 0; j < parameters.length; j ++) {
                parameters[j].id = parameters[j].id.replace(i + 1, i);
                parameters[j].name = parameters[j].name.replace(i + 1, i);
            }
        }
        // delete the clicked row
        table.deleteRow(paramIndex + 1);
    };

    function refreshParamValueField(object) {
        var paramIndex = parseInt(object.id.replace("parameterList[", "").replace("].paramType"), "");
        var rowIndex = paramIndex + 1;
        var cellParamValueIndex = 2;
        var cellParamValue = document.getElementById("tblParameterList").rows[rowIndex].cells[cellParamValueIndex];
        var cellParamValueHTML;
        var paramType = object.value;
        if (paramType == 'analyticsDate') {
        	cellParamValueHTML = '<select id="parameterList[' + paramIndex + '].paramValue" name="parameterList[' + paramIndex + '].paramValue" style="width:120px"><option value=""><spring:message code="obiee.paramType.pleaseSelect"/></option>';
        	cellParamValueHTML += monthOptions;
        	cellParamValueHTML += '</select>';
    		cellParamValue.innerHTML = cellParamValueHTML;
        } else if (paramType == 'analyticsDateBetween') {
        	cellParamValueHTML = '<table><tr><td><spring:message code="chartPortlet.label.to"/>:&nbsp;</td>';
        	cellParamValueHTML += '<td><select id="parameterList[' + paramIndex + '].paramValue2" name="parameterList[' + paramIndex + '].paramValue2" style="width:120px"><option value=""><spring:message code="obiee.paramType.pleaseSelect"/></option>';
        	cellParamValueHTML += monthOptions;
        	cellParamValueHTML += '</select></td></tr>';
        	cellParamValueHTML += '<tr><td><spring:message code="chartPortlet.label.from"/>:&nbsp;</td>';
        	cellParamValueHTML += '<td><select id="parameterList[' + paramIndex + '].paramValue" name="parameterList[' + paramIndex + '].paramValue" style="width:120px"><option value=""><spring:message code="obiee.paramType.pleaseSelect"/></option>';
        	cellParamValueHTML += monthOptions;
        	cellParamValueHTML += '</select></td></tr></table>';
    		cellParamValue.innerHTML = cellParamValueHTML;
        } else if (paramType == 'analyticsQuarter') {
        	cellParamValueHTML = '<select id="parameterList[' + paramIndex + '].paramValue" name="parameterList[' + paramIndex + '].paramValue" style="width:120px"><option value=""><spring:message code="obiee.paramType.pleaseSelect"/></option>';
        	cellParamValueHTML += quarterOptions;
        	cellParamValueHTML += '</select>';
    		cellParamValue.innerHTML = cellParamValueHTML;
        } else if (paramType == 'analyticsQuarterBetween') {
        	cellParamValueHTML = '<table><tr><td><spring:message code="chartPortlet.label.to"/>:&nbsp;</td>';
        	cellParamValueHTML += '<td><select id="parameterList[' + paramIndex + '].paramValue2" name="parameterList[' + paramIndex + '].paramValue2" style="width:120px"><option value=""><spring:message code="obiee.paramType.pleaseSelect"/></option>';
        	cellParamValueHTML += quarterOptions;
        	cellParamValueHTML += '</select></td></tr>';
        	cellParamValueHTML += '<tr><td><spring:message code="chartPortlet.label.from"/>:&nbsp;</td>';
        	cellParamValueHTML += '<td><select id="parameterList[' + paramIndex + '].paramValue" name="parameterList[' + paramIndex + '].paramValue" style="width:120px"><option value=""><spring:message code="obiee.paramType.pleaseSelect"/></option>';
        	cellParamValueHTML += quarterOptions;
        	cellParamValueHTML += '</select></td></tr></table>';
    		cellParamValue.innerHTML = cellParamValueHTML;
        } else if (paramType == 'string') {
            cellParamValue.innerHTML = '<input type="text" id="parameterList[' + paramIndex + '].paramValue" name="parameterList[' + paramIndex + '].paramValue">';
        }
    };
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Chart";
     addPortlet(portletName);
     pageTitle="Chart";
</script>