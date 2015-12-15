<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="div-style11">

<table class="margin-top-5px" width='100%'>
	<tr>
		<td colspan='2'>
			<h5><spring:message code="serviceRequest.description.createNewAddress"/>:</h5><br>
			<label id="requiredFieldsErrorMsg2" class="color-red" style="display:none"><spring:message code="serviceRequest.errorMsg.pleaseFillInAllRequiredFields"/></label>
		</td>
	</tr>
	<tr >
		<td colspan='2'>* <spring:message code="serviceRequest.label.AddressLine1"/></td>
	</tr>
	<tr >
		<td colspan='2'><input  size= "77" maxlength="200" id="addressLine1" name="addressLine1"  type="text" value="${addressLine1}" onFocus="removeErrMsg();"></td>
	</tr>
	<tr >
		<td colspan='2'>&nbsp;<spring:message code="serviceRequest.label.AddressLine2"/></td>
	</tr>
	<tr >
		<td colspan='2'><input  size= "77"  maxlength="200" id="addressLine2" name="addressLine2"  type="text" value="${addressLine2}" onFocus="removeErrMsg();"></td>
	</tr>
	<tr >
		<td colspan='2'>&nbsp;<spring:message code="serviceRequest.label.AddressLine3"/></td>
		</tr>
	<tr >
		<td colspan='2'><input  size= "77" maxlength="100" id="addressLine3" name="addressLine3"  type="text" value="${addressLine3}" onFocus="removeErrMsg();"></td>
	</tr>
	<tr >
		<td width="50%">* <spring:message code="serviceRequest.label.Country"/> </td>
		<td width="50%">* <spring:message code="serviceRequest.label.City"/></td>
	</tr>
	<tr >
		<td width="50%"><select id="country" class="width-180px" name="countryDropDownList" onChange="getState();">
		<option  value="" selected="selected">Please Select</option>
		<c:forEach
				items="${createNewAddressForm.countries}"
				var="country">
				<option value="${country.countryCode}">${country.countryName}</option>
		</c:forEach>
			</select>	
		</td>
		<td width="50%"><input id="city" size= "20"  maxlength="50" type="text" value="${city}" onFocus="removeErrMsg();">		
	</tr>
	<tr >
		<td width="50%"><div id="showStateProvince">* <spring:message code="serviceRequest.label.StateOrProvince"/></div></td>
		<td width="50%">* <spring:message code="serviceRequest.label.Zip"/></td>
	</tr>
	<tr >
		<td width="50%"><div id="retrieveStateInfo">${stateData}</div>
			 	
		</td>	
		<td width="50%"><input id="zip" maxlength="30" type="text" value="${zip}" onFocus="removeErrMsg();"></td>
	</tr>
	
	<tr>
	     <td width="50%" align="left">&nbsp;</td>
	     <td width="50%" align="left"></td>
	</tr>
</table>
</div>
<br/>
<div align="right"  class="margin-right-30px" ><button name="btnCancel" class="button_cancel" onClick="Liferay.Popup.close(this);"><spring:message code="button.cancel"/></button> &nbsp;<button name="btnSubmit" class="button" onClick="createNew(this);"><spring:message code="button.save"/></button></div>

<script type="text/javascript">  
	var validationFlag = true;	
	function createNew(buttonElm){
		callOmnitureAction('Service Request', 'Service Request Create New Address');
		validateForm();
		var an = '';
		var city = document.getElementById("city").value;
		var al1 = document.getElementById("addressLine1").value;
		var al2 = document.getElementById("addressLine2").value;
		var al3 = document.getElementById("addressLine3").value;
		var country = document.getElementById("country").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;
		var countryName = document.getElementById("country").options[document.getElementById("country").selectedIndex].text;
		var stateName = document.getElementById("state").options[document.getElementById("state").selectedIndex].text;		
		if(validationFlag){
			Liferay.Popup.close(buttonElm);
			window.parent.window.addServiceAddressElement('-1', an, al1, al2, al3, city, null, null, state, country, zip);	
		}
	}
	
	function validateForm(){
		var city = document.getElementById("city").value;
		var al1 = document.getElementById("addressLine1").value;
		var countryCode = document.getElementById("country").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;	
		if((city==null||city=="")
				||(al1==null||al1=="")||(zip==null||zip=="")||((countryCode==null||countryCode=="")||(state==null||state==""))){			
			document.getElementById("requiredFieldsErrorMsg2").style.display="block";
			validationFlag = false;
		}
	}

	function removeErrMsg(){		
		document.getElementById("requiredFieldsErrorMsg2").style.display="none";	
		validationFlag = true;
	}

	function getState(){
		removeErrMsg();
		var countryCode = document.getElementById("country").value;
		var countryName = document.getElementById("country").options[document.getElementById("country").selectedIndex].text;
		var location="<portlet:resourceURL id="getState"/>&countryCode="+countryCode;
		jQuery("#retrieveStateInfo").load(location,function(response){
			if(response=="<select id=\"state\"><option value=\"nostate\">No State Available</option></select>"){
				document.getElementById('showStateProvince').style.display = 'none';
				document.getElementById('retrieveStateInfo').style.display = 'none';
				return false;
			}
		document.getElementById('showStateProvince').style.display = '';
		document.getElementById('retrieveStateInfo').style.display = '';
	});
		
	}
		
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request New Address";
     addPortlet(portletName);
     pageTitle="Create new address";
</script>