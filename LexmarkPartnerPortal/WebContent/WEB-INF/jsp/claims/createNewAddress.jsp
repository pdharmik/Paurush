<%@ include file="/WEB-INF/jsp/include.jsp"%>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="div-style11" >

<table class="margin-top-5px" width='100%'>
	<tr>
		<td colspan='2'>
			<h5><spring:message code="claim.description.createNewAddress"/>:</h5><br>
			<label id="requiredFieldsErrorMsg2" class="color-red" style="display:none"><spring:message code="claim.errorMsg.pleaseFillInAllRequiredFields"/></label>
		</td>
	</tr>
	<tr >
		<td colspan='2'><span class="required">* </span><spring:message code="claim.label.addressLine1"/></td>
	</tr>
	<tr >
		<td colspan='2'><input  size= "77" id="addressLine1" name="addressLine1"  type="text" value="${addressLine1}" onFocus="removeErrMsg();" onblur="validateLength(0,200,this);"></td>
	</tr>
	<tr >
		<td colspan='2'>&nbsp;<spring:message code="claim.label.addressLine2"/></td>
	</tr>
	<tr >
		<td colspan='2'><input  size= "77" id="addressLine2" name="addressLine2"  type="text" value="${addressLine2}" onFocus="removeErrMsg();" onblur="validateLength(0,100,this);"></td>
	</tr>
	<tr >
		<td colspan='2'>&nbsp;<spring:message code="claim.label.addressLine3"/></td>
		</tr>
	<tr >
		<td colspan='2'><input  size= "77" id="addressLine3" name="addressLine3"  type="text" value="${addressLine3}" onFocus="removeErrMsg();" onblur="validateLength(0,100,this);"></td>
	</tr>
	<tr >
		<td width="50%"><span class="required">* </span><spring:message code="claim.label.country"/> </td>
		<td width="50%"><span class="required">* </span><spring:message code="claim.label.city"/></td>
	</tr>
	<tr >
		<td width="50%"><select id="country" class="width-180px" name="countryDropDownList" onChange="getState();">
		<option  value="" selected="selected"><spring:message code="claim.label.pleaseSelect"/></option>
		<c:forEach
				items="${createNewAddressForm.countries}"
				var="country">
				<option value="${country.countryCode}">${country.countryName}</option>
		</c:forEach>
			</select>	
		</td>
		<td width="50%"><input id="city" size= "20" type="text" value="${city}" onFocus="removeErrMsg();" onblur="validateLength(0,50,this);">	</td>	
	</tr>
	<tr >
		<td width="50%">
			<div id="showStateProvince"><span class="required">* </span><spring:message code="claim.label.stateOrProvince"/></div>
			<label id="stateSearchLoading" style="display: none;">
				<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
			</label>
		</td>
		<td width="50%"><span class="required">* </span><spring:message code="claim.label.zip"/></td>
	</tr>
	<tr >
		<td width="50%"><div id="retrieveStateInfo">${stateData}</div>
			 	
		</td>	
		<td width="50%"><input id="zip" type="text" value="${zip}" onFocus="removeErrMsg();" onblur="validateLength(0,30,this);"></td>
	</tr>
	
	<tr>
	     <td width="50%" align="left">&nbsp;</td>
	     <td width="50%" align="left"></td>
	</tr>
</table>
</div>
<br/>
<div align="right"  class="margin-right-30px" ><a href="###" onclick="createNew(this);hideOverlayIE6();Liferay.Popup.close(this);" class="button" name="btnSubmit"><spring:message code="button.save"/></a>&nbsp;<a href="###" class="button" onclick="hideOverlayIE6();Liferay.Popup.close(this);"><spring:message code="button.cancel"/></a></div>

<script type="text/javascript">  
	var validationFlag = true;	
	function createNew(buttonElm){
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
			hideOverlayIE6();
			Liferay.Popup.close(buttonElm);
			window.parent.window.addPartnerAddressElement('-1', an, al1, al2, al3, city, null, null, state, country, zip);	
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
		document.getElementById('showStateProvince').style.display = 'none';
		document.getElementById('retrieveStateInfo').style.display = 'none';
		document.getElementById("stateSearchLoading").style.display = "block";
		var countryCode = document.getElementById("country").value;
		var countryName = document.getElementById("country").options[document.getElementById("country").selectedIndex].text;
		var location="<portlet:resourceURL id="getState"/>&countryCode="+countryCode;
		jQuery("#retrieveStateInfo").load(location,function(response){
			document.getElementById("stateSearchLoading").style.display = "none";
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