<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">


var namePatrn=/^[a-zA-Z0-9\s]*$/; //newly added defect #7580, #7576
//var namePatrn=/^[a-zA-Z0-9.\-\s]{1,50}$/;
var phonePatrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
var emailPatrn = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
var addrPatrn=/^^\w[a-zA-Z0-9\s\.\,\-]+\,{0,50}$/;
var addrName=/^[a-zA-Z0-9\s]{1,100}$/;
var strFrntPatrn=/^[a-zA-Z]{1,30}$/;
var cityPatrn=/^[a-zA-Z0-9\s]{1,50}$/;
//var zipPatrn=/^[0-9]{1,30}$/;
var zipPatrn=/^[a-zA-Z0-9\s]{1,5}[-]?[a-zA-Z0-9]{1,5}?$/;
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

//added by jyoti
function dateCheck(s){
		//alert("inside  date check");
		//alert("date:-"+s+"separator::::::::::"+separator);
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
				if(separator=="/")
					patrn=/^\d{4}\/\d{1,2}\/\d{1,2}$/; 
				else if(separator==".")
					patrn=/^\d{4}\.\d{1,2}\.\d{1,2}$/; 
				else
				//patrn=/^\d{1,2}-\d{1,2}-\d{4}$/; 
				patrn=/^\d{4}-\d{1,2}-\d{1,2}$/; 
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
			jQuery("#errorMsg").append("<li><strong><spring:message code="validation.date.format.errorMsg"/></strong></li>");
			return false;		
		}
	}
	//document.getElementById("dateErrorMsg").innerHTML="";
	return true;
}

function validateFormat(fieldvalue,fieldname,errorDivId) {
		//alert(isValidName(fieldvalue));
		if ((fieldname=="firstname" || fieldname=="firstNamePopup") && !namePatrn.exec(fieldvalue)){
			//alert("firstname wrong");
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.contact.firstname.format.errorMsg"/></strong></li>");
			return false;				
		}
		else if (fieldname=="middlename" && !namePatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.contact.middlename.format.errorMsg"/></strong></li>");
			return false;
		}
		else if ((fieldname=="lastname" || fieldname=="lastNamePopup") && !namePatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.contact.lastname.format.errorMsg"/></strong></li>");
			return false;
		}
		else if ((fieldname=="workphone" || fieldname=="workPhonePopup") && !phonePatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.contact.workphone.format.errorMsg"/></strong></li>");
			return false;
				
		}
		else if ((fieldname=="alternatephone" || fieldname=="alternatePhonePopup") && !phonePatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.contact.alternatephone.format.errorMsg"/></strong></li>");
			return false;
				
		}
		else if((fieldname=="emailAddr" || fieldname=="emailPopup") && !emailPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.contact.emailaddress.format.errorMsg"/></strong></li>");
			return false;	
		}
		else if(fieldname=="quantity" && !numberPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.quantity.format.errorMsg"/></strong></li>");
			return false;	
		}
		// added by jyoti for effective date validation
		else if(fieldname=="effDtOfChange" &&!dateCheck(fieldvalue))
		{
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.date.format.errorMsg"/></strong></li>");
			return false;
				
		}
		<%-- MPS 2.1 CHANGES Starts--%>
		else if (fieldname=="addrLine1" && !addrPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.address.addrline1.format.errorMsg"/></strong></li>");
			return false;
		}
		else if (fieldname=="addrLine2" && !addrPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.address.addrline2.format.errorMsg"/></strong></li>");
			return false;
		}
		<%-- Changes MPS 2.1 start--%>
		else if (fieldname=="officeNo" && !addrPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.address.officeNo.format.errorMsg"/></strong></li>");
			return false;
		}
		<%-- Changes MPS 2.1 Ends--%>
		<%-- Changes MPS 2.1 Defect 13692--%>
		
		else if(fieldname=="zipCode" && !zipPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.address.zip.format.errorMsg"/></strong></li>");
			return false;	
		}
		else if(fieldname=="building" && !bulidingPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.building.format.errorMsg"/></strong></li>");
			return false;				
		}
		else if(fieldname=="floor" && !floorPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.floor.format.errorMsg"/></strong></li>");
			return false;	
		}
		else if(fieldname=="office" && !officePatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.office.format.errorMsg"/></strong></li>");
			return false;
		}
		else if(fieldname=="storeFrontName" && !strFrntPatrn.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.address.storefrontname.format.errorMsg"/></strong></li>");
			return false;
		}
		else if(fieldname=="addrName" && !addrName.exec(fieldvalue)){
			jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.address.addrName.format.errorMsg"/></strong></li>");
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
				jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.firstname.format.errorMsg"/></strong></li>");				
			}
			else if (fieldname=="middlename"){
				jQuery("#errorMsg").append("<li><strong><spring:message code="validation.contact.middlename.format.errorMsg"/></strong></li>");
			}
			else if (fieldname=="lastname"){
				jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.lastname.format.errorMsg"/></strong></li>");
			}
			return false;		
		}
	}
	else {
		if (fieldname=="firstname"){
			jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.firstname.empty"/></strong></li>");
			return false;
		}
		else if (fieldname=="lastname"){
			jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.lastname.empty"/></strong></li>");
			return false;
		}
	}
	
	return true;
}

//Validation for Phone Number
function phoneValidate(fieldvalue,fieldname, pagename) {
	 
	if(jQuery.trim(fieldvalue).length >40){
		if (fieldname=="workphone"){
			jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.workphone.length.errorMsg"/></strong></li>");
		}
		else if(fieldname=="alternatephone"){
			jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.alternatephone.length.errorMsg"/></strong></li>");
		}
		return false;	
	}
	if (fieldvalue!=null && fieldvalue!="") {
		if(!phonePatrn.exec(fieldvalue)){
			if (fieldname=="workphone"){
				jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.workphone.format.errorMsg"/></strong></li>");
					
			}
			else if (fieldname=="alternatephone"){
				jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.alternatephone.format.errorMsg"/></strong></li>");
					
			}
			return false;		
		}
	}
	else {
		if (fieldname=="workphone"){
			jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.workphone.empty"/></strong></li>");
			
			return false;
		}	
	}
	
	return true;
}

//Validation for Email Address
function emailValidate(fieldvalue,fieldname, pagename) {
	if(fieldvalue.length >100){
		jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.emailaddress.length.errorMsg"/></strong></li>");
		
		return false;	
	}
	if (fieldvalue!=null && fieldvalue!="") {
		if(!emailPatrn.exec(fieldvalue)){
			jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.emailaddress.format.errorMsg"/></strong></li>");
				
			return false;		
		}
		//return true;
	}
	else {
		//if (pagename!="changeContact") {
		jQuery("#errorMsg"+popup).append("<li><strong><spring:message code="validation.contact.emailaddress.empty"/></strong></li>");
			
		return false;
		//}
	}
	/*
	document.getElementById("emailErrorMsg"+popup).innerHTML="";*/
	return true;
}


function descValidate(descData,errorDivId) {
	//alert("descData.length="+descData.length);
	if(descData.length > 2000) {
		//alert("descData.length > 2000");
		jQuery("#"+errorDivId).append("<li><strong><spring:message code="validation.description.length.errorMsg"/>&nbsp;"+descData.length+"</strong></li>");
		jQuery("#"+errorDivId).append(descData.length);
		return false;
	}
	else {
		return true;
	}
}
</script>