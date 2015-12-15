<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>

//var addrPatrn=/^[a-zA-Z0-9\s]{1,30}$/;
var namePatrn=/^[a-zA-Z.\-\s]{1,50}$/;
var phonePatrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
var emailPatrn = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
var addrPatrn=/^^\w[a-zA-Z0-9\s\.\,]{0,50}$/; // Added ',' for defect 8541
var addrName=/^[a-zA-Z0-9\s]{1,100}$/;
var strFrntPatrn=/^[a-zA-Z]{1,30}$/;
var cityPatrn=/^[a-zA-Z0-9\s]{1,50}$/;
//var zipPatrn=/^[0-9]{1,30}$/;
var zipPatrn=/^[a-zA-Z0-9]{1,5}[-]?[a-zA-Z0-9]{1,5}?$/;
var bulidingPatrn=/^[a-zA-Z0-9]{1,50}$/;
var floorPatrn=/^[a-zA-Z0-9]{1,50}$/;
var officePatrn=/^[a-zA-Z0-9]{1,50}$/;
var numberPatrn = /^\s*\d+\s*$/;

var popup="";
//Set error message section as popup
function setPopup(){	
	popup="Popup";
}
//Set error message section as main page
function setNotPopup(){	
	popup="";
}
//Validation for Customer Reference Id
/*function custRefIdValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.custrefid.format.errorMsg"/></strong></li>');	
			return false;		
		}
	}
	//document.getElementById("custRefIdErrorMsg").innerHTML='';
	return true;
}

//Validation for Cost Center
function costCenterValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.costCenter.format.errorMsg"/></strong></li>');
			return false;		
		}
	}
	//document.getElementById("costCenterErrorMsg").innerHTML='';
	return true;
}*/
//added by jyoti
function dateCheck(s){
		//alert("inside  date check");
		//alert("date:-"+s);
		var dateFormatSplitted=dateFormat.split(separator);
		//alert("splitted date format:-"+dateFormatSplitted);
		var patrn;
		if(dateFormatSplitted[0]=="dd"||dateFormatSplitted[0]=="mm")
			{
				if(separator=="/")
					patrn=/^\d{1,2}\/\d{1,2}\/\d{4}$/; 
				else if(separator==".")
					patrn=/^\d{1,2}\.\d{1,2}\.\d{4}$/; 
				else
					patrn=/^\d{1,2}-\d{1,2}-\d{4}$/; 
			}
		else
			{
				patrn=/^\d{4}\/\d{1,2}\/\d{1,2}$/; 
			}
		//alert(patrn);
		//var patrn=/^\d{1,2}\/\d{1,2}\/\d{4}$/; 
		if (!patrn.exec(s)) 
				return false;
		else
			return true;
		
	}
//Validation for Effective Date
function dateValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[0-9]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.date.format.errorMsg"/></strong></li>');
			return false;		
		}
	}
	//document.getElementById("dateErrorMsg").innerHTML='';
	return true;
}

function validateFormat(fieldvalue,fieldname,errorDivId) {
		//alert(isValidName(fieldvalue));
		if ((fieldname=="firstname" || fieldname=="firstNamePopup") && !namePatrn.exec(fieldvalue)){
			//alert('firstname wrong');
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.contact.firstname.format.errorMsg"/></strong></li>');
			return false;				
		}
		else if (fieldname=="middlename" && !namePatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.contact.middlename.format.errorMsg"/></strong></li>');
			return false;
		}
		else if ((fieldname=="lastname" || fieldname=="lastNamePopup") && !namePatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.contact.lastname.format.errorMsg"/></strong></li>');
			return false;
		}
		else if ((fieldname=="workphone" || fieldname=="workPhonePopup") && !phonePatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.contact.workphone.format.errorMsg"/></strong></li>');
			return false;
				
		}
		else if ((fieldname=="alternatephone" || fieldname=="alternatePhonePopup") && !phonePatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.contact.alternatephone.format.errorMsg"/></strong></li>');
			return false;
				
		}
		else if((fieldname=="emailAddr" || fieldname=="emailPopup") && !emailPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.contact.emailaddress.format.errorMsg"/></strong></li>');
			return false;	
		}
		else if(fieldname=="quantity" && !numberPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.quantity.format.errorMsg"/></strong></li>');
			return false;	
		}
		// added by jyoti for effective date validation
		else if(fieldname=="effDtOfChange" &&!dateCheck(fieldvalue))
		{
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.date.format.errorMsg"/></strong></li>');
			return false;
				
		}
		<%-- MPS 2.1 CHANGES Starts--%>
		else if (fieldname=="addrLine1" && !addrPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.address.addrline1.format.errorMsg"/></strong></li>');
			return false;
		}
		else if (fieldname=="addrLine2" && !addrPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.address.addrline2.format.errorMsg"/></strong></li>');
			return false;
		}
		<%-- Changes MPS 2.1 start--%>
		else if (fieldname=="officeNo" && !addrPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.address.officeNo.format.errorMsg"/></strong></li>');
			return false;
		}
		<%-- Changes MPS 2.1 Ends--%>
		else if(fieldname=="city" && !cityPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.address.city.format.errorMsg"/></strong></li>');
			return false;	
		}
		else if(fieldname=="zipCode" && !zipPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.address.zip.format.errorMsg"/></strong></li>');
			return false;	
		}
		else if(fieldname=="building" && !bulidingPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.building.format.errorMsg"/></strong></li>');
			return false;				
		}
		else if(fieldname=="floor" && !floorPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.floor.format.errorMsg"/></strong></li>');
			return false;	
		}
		else if(fieldname=="office" && !officePatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.office.format.errorMsg"/></strong></li>');
			return false;
		}
		else if(fieldname=="storeFrontName" && !strFrntPatrn.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.address.storefrontname.format.errorMsg"/></strong></li>');
			return false;
		}
		else if(fieldname=="addrName" && !addrName.exec(fieldvalue)){
			jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.address.addrName.format.errorMsg"/></strong></li>');
			return false;
		}
		<%-- MPS 2.1 CHANGES Ends--%>
		else {
			return true;
		}
}

//Validation for Name
function nameValidate(fieldvalue,fieldname, pagename) {
	//alert("errorMsg"+popup);
	if (fieldvalue!=null && fieldvalue!="") {
		if(!namePatrn.exec(fieldvalue)){
			if (fieldname=="firstname"){
				jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.firstname.format.errorMsg"/></strong></li>');				
			}
			else if (fieldname=="middlename"){
				jQuery('#errorMsg').append('<li><strong><spring:message code="validation.contact.middlename.format.errorMsg"/></strong></li>');
			}
			else if (fieldname=="lastname"){
				jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.lastname.format.errorMsg"/></strong></li>');
			}
			return false;		
		}
	}
	else {
		if (fieldname=="firstname"){
			jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.firstname.empty"/></strong></li>');
			return false;
		}
		else if (fieldname=="lastname"){
			jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.lastname.empty"/></strong></li>');
			return false;
		}
	}
	/*
	if (fieldname=="firstname"){
		document.getElementById("firstNameErrorMsg"+popup).innerHTML='';	
	}
	else if (fieldname=="middlename"){
		document.getElementById("middleNameErrorMsg").innerHTML='';
	}
	else if (fieldname=="lastname"){
		document.getElementById("lastNameErrorMsg"+popup).innerHTML='';	
	}*/
	return true;
}

//Validation for Phone Number
function phoneValidate(fieldvalue,fieldname, pagename) {
	 
	if(jQuery.trim(fieldvalue).length >40){
		if (fieldname=="workphone"){
			jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.workphone.length.errorMsg"/></strong></li>');
		}
		else if(fieldname=="alternatephone"){
			jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.alternatephone.length.errorMsg"/></strong></li>');
		}
		return false;	
	}
	if (fieldvalue!=null && fieldvalue!="") {
		if(!phonePatrn.exec(fieldvalue)){
			if (fieldname=="workphone"){
				jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.workphone.format.errorMsg"/></strong></li>');
					
			}
			else if (fieldname=="alternatephone"){
				jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.alternatephone.format.errorMsg"/></strong></li>');
					
			}
			return false;		
		}
	}
	else {
		if (fieldname=="workphone"){
			jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.workphone.empty"/></strong></li>');
			
			return false;
		}	
	}
	/*
	if (fieldname=="workphone"){
		document.getElementById("workPhoneErrorMsg"+popup).innerHTML='';	
	}
	else if (fieldname=="alternatephone"){
		document.getElementById("alternatePhoneErrorMsg"+popup).innerHTML='';	
	}*/
	return true;
}

//Validation for Email Address
function emailValidate(fieldvalue,fieldname, pagename) {
	if(fieldvalue.length >100){
		jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.emailaddress.length.errorMsg"/></strong></li>');
		
		return false;	
	}
	if (fieldvalue!=null && fieldvalue!="") {
		if(!emailPatrn.exec(fieldvalue)){
			jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.emailaddress.format.errorMsg"/></strong></li>');
				
			return false;		
		}
		//return true;
	}
	else {
		//if (pagename!="changeContact") {
		jQuery('#errorMsg'+popup).append('<li><strong><spring:message code="validation.contact.emailaddress.empty"/></strong></li>');
			
		return false;
		//}
	}
	/*
	document.getElementById("emailErrorMsg"+popup).innerHTML='';*/
	return true;
}


//Validation for Address
/*
function addressValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9\s]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			if (fieldname=="addrLine1"){
				jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.addrline1.format.errorMsg"/></strong></li>');
					
			}
			else if (fieldname=="addrLine2"){
				jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.addrline2.format.errorMsg"/></strong></li>');
					
			}
			else if (fieldname=="addrLine3"){
				jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.addrline3.format.errorMsg"/></strong></li>');
					
			}
			return false;		
		}
		//return true;
	}
	else {
		if (fieldname=="addrLine1"){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.addrline1.empty"/></strong></li>');
			
			return false;
		}
	}
	
	if (fieldname=="addrLine1"){
		//document.getElementById("addrLine1ErrorMsg").innerHTML='';	
	}
	else if (fieldname=="addrLine2"){
		//document.getElementById("addrLine2ErrorMsg").innerHTML='';	
	}
	else if (fieldname=="addrLine3"){
		//document.getElementById("addrLine3ErrorMsg").innerHTML='';	
	}
	return true;
}

//Validation for City
function cityValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9\s]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.city.format.errorMsg"/></strong></li>');
				
			return false;		
		}
	}
	else {
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.city.empty"/></strong></li>');
		
		return false;
	}
	
	//document.getElementById("cityErrorMsg").innerHTML='';
	return true;
}

//Validation for Country
function countryValidate(fieldvalue,fieldname, pagename) {
	//var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue==null || fieldvalue=="") {
		//alert('country validation');
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.country.empty"/></strong></li>');
			
		return false;
	}
	//document.getElementById("countryErrorMsg").innerHTML='';
	return true;
}

//Validation for State
function stateValidate(fieldvalue,fieldname, pagename) {
	//var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue==null || fieldvalue=="") {
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.state.empty"/></strong></li>');
			
		return false;
	}
	//document.getElementById("stateErrorMsg").innerHTML='';
	return true;
}

//Validation for ZIP
function zipValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.zip.format.errorMsg"/></strong></li>');
				
			return false;		
		}
	}
	else {
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.zip.empty"/></strong></li>');
		
		return false;
	}
	
	//document.getElementById("zipErrorMsg").innerHTML='';
	return true;
}

//Validation for Building
function buildingValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.building.format.errorMsg"/></strong></li>');
				
			return false;		
		}
	}
	//document.getElementById("buildingErrorMsg").innerHTML='';
	return true;
}
//Validation for Floor
function floorValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.floor.format.errorMsg"/></strong></li>');
				
			return false;		
		}
	}	
	//document.getElementById("floorErrorMsg").innerHTML='';
	return true;
}
//Validation for Office
function officeValidate(fieldvalue,fieldname, pagename) {
	var patrn=/^[a-zA-Z0-9]{1,30}$/;  
	if (fieldvalue!=null && fieldvalue!="") {
		if(!patrn.exec(fieldvalue)){
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.office.format.errorMsg"/></strong></li>');
				
			return false;		
		}
	}
	//document.getElementById("officeErrorMsg").innerHTML='';
	return true;
}
*/

// Validation for description field length
function descValidate(descData,errorDivId) {
	//alert('descData.length='+descData.length);
	if(descData.length > 2000) {
		//alert('descData.length > 2000');
		jQuery('#'+errorDivId).append('<li><strong><spring:message code="validation.description.length.errorMsg"/></strong></li>');
		return false;
	}
	else {
		return true;
	}
}
</script>