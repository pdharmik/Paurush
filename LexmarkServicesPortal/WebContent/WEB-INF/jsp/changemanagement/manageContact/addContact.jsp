<%-- common subtab jsp to select history or create new request --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%>

<jsp:include page="../../common/validationMPS.jsp" />
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript" src="<html:rootPath/>js/addressPopup.js"></script> 
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<style type="text/css"><%@ include file="/WEB-INF/css/jquery/jquery-ui.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%--Changes for MPS 2.1 --%>
<portlet:resourceURL var="newAddressValidateURL" id="newAddressValidate"></portlet:resourceURL>
 <portlet:actionURL var="AddressActionURL">
<portlet:param name="action" value="submitAddressWithSR"></portlet:param>
</portlet:actionURL>
<%-- ENDS MPS 2.1--%>
<portlet:renderURL var="manageContactReviewUrl">
<portlet:param name="action" value="manageContactReview"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="countryListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="countryDropDownPopulate"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="stateListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="stateDropDownPopulate"></portlet:param>
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<script type="text/javascript">


var portlet_name="<%=LexmarkSPOmnitureConstants.ADDANEWCONTACT %>";
<%--Changes for MPS 2.1 --%>
var action_primary="<%=LexmarkSPOmnitureConstants.ADDCONTACTSELECTADIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants.ADDCONTACTADDADDITIONALCONTACT%>";

var claddressLine1,claddressLine2,clofficeNumber,clcity,clcountry,clstate,clzipPostal,clcounty,clstateCode,clstateFullName,cldistrict,clregion,cllatitude,cllongitude,clcountryISOCode;
<%-- ENDS MPS 2.1--%>    
var validationFlag;
var xCo; 
var yCo;
var stateFlag = false;
<%--Changes for MPS 2.1 --%>
var goForCleanseAddrFlg = false;
<%-- ENDS MPS 2.1--%>
jQuery(document).ready( function() {
	var currentURL = window.location.href;
//	Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	jQuery('#inputBoxes input').click(function(){
			jQuery("#cleansedAddress").hide();
			goForCleanseAddrFlg = true;
		});
<%--Changes for MPS 2.1 --%>
	var success="${success}";
	if(success){
		showDialog();
		}
<%-- ENDS MPS 2.1--%>
	jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>');	

	jQuery('#errorMsg1').empty();
<%--Changes for MPS 2.1 --%>
	loadCountryList();
<%-- ENDS MPS 2.1--%>
	jQuery(':input').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
		});

	//Show errros if exception has occured
	var isException = '${exception}';
	if (isException) {
<%--Changes for MPS 2.1 --%>
		jQuery('#errorMsg1').append('<li><strong><spring:message code="exception.cm.common.webServiceCall"/></strong></li>');
<%-- ENDS MPS 2.1--%>
		jQuery('#errorMsg1').show();
	}
	
	//loadCountryList();	

});


/*
 * 
 Added for MPS 2.1
 */
 var dialog;
 function showDialog(){
	 showOverlay();
	 dialog=jQuery('#requestConfirmDiv').dialog({
			autoOpen: false,
			title: "<spring:message code='requestInfo.message.title'/>",
			modal: true,
			height: 460,
			width: 400,
			position: 'center',
			open:function(){
				jQuery('#requestConfirmDiv').show();
			},
			close: function(event,ui){											
				 dialog.dialog('destroy');
				 window.location.href="managecontacts";
				}
			});
		
		dialog.dialog('open');
	 }




/*
 * Navigate user to contact list page
 */
function backToSelect() {
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWCONTACT%>','<%=LexmarkSPOmnitureConstants.ADDCONTACTBACK%>');
	jConfirm('<spring:message code="common.back.alert"/>', "", function(confirmResult) {
		if(confirmResult){
			showOverlay();
			window.location.href="managecontacts";
		}else{
			return false;
			}
	});
}

function hideCleansedaddress() {
	document.getElementById('cleansedAddress').style.display = "none";
	}
/*
 * Load the country list
 */
function loadCountryList(){
	jQuery.ajax({
		url:'${countryListPopulateURL}',
		success: function(countryListResult){
					jQuery('#country').append(countryListResult);
					//Below code is for Back button to populate country and state list
					if(jQuery('#countryFormVal').val()!="")
					{
						jQuery('#country').val(jQuery('#countryFormVal').val());
						loadStateList();
					}	
					//alert(jQuery('#country option:selected').val());		
			}
	});
}

/*
 * Load the state list as per the country selection
 */
function loadStateList(){
	//alert('loading statelist');
	if(jQuery('#country').val()=="BR"){
		<%--Changes for defect 7994 MPS 2--%>
		//jQuery('#officeMandatory').html("*");
		<%--ends Changes for defect 7994  MPS 2--%>
		jQuery('#officeNoLI').show();		
	}
	else{
		<%--Changes for defect 7994 mps 2--%>
		jQuery('#officeMandatory').html("");
		<%--ends Changes for defect 7994 --%>
		jQuery('#officeNo').val('');
		jQuery('#officeNoLI').hide();
		
	}
	jQuery('#state').empty();
	jQuery.ajax({
		url:'${stateListPopulateURL}',
		type:'POST',
		data: {
				countryCode:jQuery('#country').val()
		      }, 
		beforeSend: function(){
				jQuery('#loadingImage').show();
			},
		success: function(stateListResult){
					//To check if any state is there for the selected country	
					var countryCode=jQuery('#country').val();				
					if(stateListResult==(countryCode+"<option></option>"))
					{
						stateFlag = false;
						jQuery('#state').attr('disabled','disabled');
					}
					else {
						stateFlag = true;
						jQuery('#state').removeAttr('disabled');
					}
					//alert('stateListResult='+stateListResult+' stateFlag='+stateFlag);
					if(stateListResult.substring(0,2)==countryCode){
						jQuery('#state').empty();
						jQuery("#state").append(stateListResult.substring(2));
					}
					jQuery('#loadingImage').hide();	
					//Below code is for Back button to populate country n state list
					if(jQuery('#stateFormVal').val()!="")
						jQuery('#state').val(jQuery('#stateFormVal').val());
					
							
			}
	});
}

 

function validate_popup() {
	
	//alert("here123" + claddressLine1);
	if(document.getElementById('check_popup').checked){
		//alert('checked');
		goForCleanseAddrFlg = true;
		setValues(claddressLine1,claddressLine2,clofficeNumber,clcity,clcountry,clstate,clzipPostal,clcounty,clstateCode,clstateFullName,cldistrict,clregion,cllatitude,cllongitude,clcountryISOCode,goForCleanseAddrFlg);
		//disableInputs();
	}
	else{
		//alert('unchecked');
		goForCleanseAddrFlg = false;
		setValues(jQuery("#addrLine1").val(), jQuery("#addrLine2").val(),jQuery("#officeNo").val(),jQuery("#city").val(),jQuery("#country").val(),jQuery("#state").val(),jQuery("#zipCode").val(),"","","","","","","","",goForCleanseAddrFlg);
		//enableInputs();
	}
	
}
function setValues(claddressLine1,claddressLine2,clofficeNumber,clcity,clcountry,clstate,clzipPostal,clcounty,clstateCode,clstateFullName,cldistrict,clregion,cllatitude,cllongitude,clcountryISOCode,iscleanseFlag){
	jQuery('#addressLine1Cleansed').val(claddressLine1);
	jQuery('#addressLine2Cleansed').val(claddressLine2);
	jQuery('#officeNumberCleansed').val(clofficeNumber);
	jQuery('#cityCleansed').val(clcity);
	jQuery('#countryFormVal').val(clcountry);
	jQuery('#stateFormVal').val(clstate);
	jQuery('#zipPostalCleansed').val(clzipPostal);
	jQuery('#pickupAddresscounty').val(clcounty);
	jQuery('#pickupAddressdistrict').val(cldistrict);
	jQuery('#pickupAddressregion').val(clregion);
	jQuery('#pickupAddresslatitude').val(cllatitude);
	jQuery('#pickupAddresslongitude').val(cllongitude);
	jQuery('#pickupAddressstateCode').val(clstateCode);
	jQuery('#pickupAddresscountryISOCode').val(clcountryISOCode);
	jQuery('#pickupAddressstateFullName').val(clstateFullName);
	jQuery('#pickupAddressisAddressCleansed').val(iscleanseFlag);
	
	
}

/*
 * Validate the form data
 */
function validateBlank() {
	jQuery('#submitButtn').blur();
	
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWCONTACT%>','<%=LexmarkSPOmnitureConstants.ADDCONTACTCONTINUE%>');
	
	
	 jQuery("#prevSrNo").val('${manageContactForm.prevSRNumber}');	
	//alert("prev sr no "+jQuery('#prevSrNo').val());
	jQuery("#pageName").val("addContact");
	jQuery('#countryFormVal').val(jQuery('#country option:selected').text());
	jQuery('#stateFormVal').val(jQuery('#state option:selected').text());
	//Clear the contents of error div
	jQuery('#errorMsg1').html('');
	<%-- Changes for mps 2.1 starts--%>
	jQuery('#errorMsg1').hide();  
	<%-- Changes for mps 2.1 ends--%>
	validationFlag = true;
	//Set error message section which is not for popup
	 /* setNotPopup();
	document.getElementById('errorMsg1').innerHTML = "";
	document.getElementById('errorMsg1').style.display = "none"; */ 
	//alert("jQuery('#firstname').val()="+jQuery('#firstname').val());
	if(jQuery('#firstname').val()==null || jQuery.trim(jQuery('#firstname').val())=='' ) {
		//alert('firstname');
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.contact.firstname.empty"/></strong></li>');
		//alert('firstname end');
		validationFlag = false;
		jQuery('#firstname').addClass('errorColor');
	}
	if(jQuery('#lastname').val()==null || jQuery.trim(jQuery('#lastname').val())=='' ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.contact.lastname.empty"/></strong></li>');
		validationFlag = false;
		//alert('1');
		jQuery('#lastname').addClass('errorColor');
	}
	if(jQuery('#workphone').val()==null || jQuery('#workphone').val()=='' ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.contact.workphone.empty"/></strong></li>');
		validationFlag = false;
		//alert('2');
		jQuery('#workphone').addClass('errorColor');
	}
	if(jQuery('#emailAddr').val()==null || jQuery('#emailAddr').val()=='' ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.contact.emailaddress.empty"/></strong></li>');
		validationFlag = false;
		//alert('3');
		jQuery('#emailAddr').addClass('errorColor');
	} 
	/* if(jQuery('#addrLine1').val()==null || jQuery('#addrLine1').val()=='' ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.addrline1.empty"/></strong></li>');
		validationFlag = false;
		jQuery('#addrLine1').addClass('errorColor');
	}
	if(jQuery('#city').val()==null || jQuery('#city').val()=='' ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.city.empty"/></strong></li>');
		validationFlag = false;
		//alert('3a');
		jQuery('#city').addClass('errorColor');
	} */
	
	
	if($.trim(jQuery('#addrLine1').val())=="")
	{
		validationFlag=false;
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.addrline1.empty"/></strong></li>');
		jQuery('#addrLine1').addClass('errorColor');
	}
	<%-- CHANGES FOR MPS 2.1 starts--%>
	/*if(jQuery('#country').val()=="BR"){
		if(jQuery('#officeNo').val()==null || jQuery('#officeNo').val()=='' ) {
			jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.contact.officeNo.empty"/></strong></li>');
			validationFlag = false;
			//alert('2');
			jQuery('#officeNo').addClass('errorColor');
		}
	}*/
	<%-- CHANGES FOR MPS 2.1 ends--%>
	if($.trim(jQuery('#city').val())=="")
	{
		validationFlag=false;
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.city.empty"/></strong></li>');
		jQuery('#city').addClass('errorColor');
	}
	
	//alert('country='+document.getElementById("country").value);
	/* if(jQuery('#country option:selected').val()==null || jQuery('#country option:selected').val()=='' ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.country.empty"/></strong></li>');
		validationFlag = false;
		//alert('3b');
		jQuery('#country').addClass('errorColor');
	}
	//alert('state='+jQuery('#state option:selected').val());
	if((jQuery('#state option:selected').val()==null || jQuery('#state option:selected').val()=='' || jQuery('#state option:selected').val()=='undefined') && stateFlag==true ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.state.empty"/></strong></li>');
		validationFlag = false;
		//alert('4');
		jQuery('#state').addClass('errorColor');
	}
	//alert('after state');
	if(jQuery('#zipCode').val()==null || jQuery('#zipCode').val()=='' ) {
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.zip.empty"/></strong></li>');
		validationFlag = false;
		jQuery('#zipCode').addClass('errorColor');
		
	} */
	if(jQuery('#country option:selected').text()=="")
	{
		validationFlag=false;
		jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.country.empty"/></strong></li>');
		jQuery('#country').addClass('errorColor');
	}
	if(!jQuery('#state').is(':hidden')){
		if(jQuery('#state option:selected').text()=="")
		{
			if( jQuery('#state option').length > 1)
			{
				validationFlag=false;
				jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.state.empty"/></strong></li>');
				jQuery('#state').addClass('errorColor');	
			}
			if(jQuery('#country option:selected').text()=="" && jQuery('#state option:selected').text()=="")
			{
				validationFlag=false;
				jQuery('#errorMsg1').append('<li><strong><spring:message code="validation.address.state.empty"/></strong></li>');
				jQuery('#state').addClass('errorColor');
			}
			//Change done for CI Defect #9522
			if($.trim(jQuery('#zipCode').val())!="" || jQuery('#state option:selected').text()!="")
			{
				validationFlag=true;
				jQuery('#state').removeClass('errorColor');
				jQuery('#zipCode').removeClass('errorColor');
			}
			
		}
	}
	<%-- CHANGES FOR MPS 2.1 starts--%>
	/*ADded for pattern check*/
	 if( jQuery('#firstname').val()!="" && (!validateFormat(jQuery('#firstname').val(),"firstname","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#firstname').addClass('errorColor');
	}
	if( jQuery('#middlename').val()!="" && (!validateFormat(jQuery('#middlename').val(),"middlename","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#middlename').addClass('errorColor');
	}
	if( jQuery('#lastname').val()!="" && (!validateFormat(jQuery('#lastname').val(),"lastname","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#lastname').addClass('errorColor');
	}
	if( jQuery('#workphone').val()!="" && (!validateFormat(jQuery('#workphone').val(),"workphone","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#workphone').addClass('errorColor');
	}
	if( jQuery('#alternatephone').val()!="" && (!validateFormat(jQuery('#alternatephone').val(),"alternatephone","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#alternatephone').addClass('errorColor');
	}

	if( jQuery('#emailAddr').val()!="" && (!validateFormat(jQuery('#emailAddr').val(),"emailAddr","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#emailAddr').addClass('errorColor');
	} 
	<%-- Changes for MPS 2.1 Starts	
	if(jQuery('#addrLine1').val()!="" && (!validateFormat(jQuery('#addrLine1').val(),"addrLine1","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#addrLine1').addClass('errorColor');
	}
	
	if(jQuery('#addrLine2').val()!="" && (!validateFormat(jQuery('#addrLine2').val(),"addrLine2","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#addrLine2').addClass('errorColor');
	}--%>
	<%-- Changes for MPS 2.1--%>
	
	/*if(jQuery('#country').val()=="BR"){
		if(jQuery('#officeNo').val()!="" && (!validateFormat(jQuery('#officeNo').val(),"officeNo","errorMsg1"))){
			
			validationFlag=false;
			jQuery('#addrLine3').addClass('errorColor');
		}
	}*/
	<%--ENDS  Changes for MPS 2.1--%>
	if( jQuery('#city').val()!="" && (!validateFormat(jQuery('#city').val(),"city","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#city').addClass('errorColor');
	}
	if( jQuery('#zipCode').val()!="" && (!validateFormat(jQuery('#zipCode').val(),"zipCode","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#zipCode').addClass('errorColor');
	}
	if( jQuery('#building').val()!="" && (!validateFormat(jQuery('#building').val(),"building","errorMsg1"))){
		
		validationFlag=false;
		jQuery('#building').addClass('errorColor');
	}
	if( jQuery('#floor').val()!="" && (!validateFormat(jQuery('#floor').val(),"floor","errorMsg1"))){
	
		validationFlag=false;
		jQuery('#floor').addClass('errorColor');
	}
	if( jQuery('#office').val()!="" && (!validateFormat(jQuery('#office').val(),"office","errorMsg1"))){
	
		validationFlag=false;
		jQuery('#office').addClass('errorColor');
	}
	<%--Changes for MPS 2.1 ends--%>
	

	//Submit the page if there is no error
	//alert('addcontact jsp validationFlag='+validationFlag);
	if(validationFlag==false)
	{
		jQuery('#errorMsg1').show();
		jQuery(document).scrollTop(0);
	}
	//brmandal -- start
	else{
			if(jQuery("#cleansedAddress").is(':visible') == false)
			{
				//alert('showing cleansed addres');
				if(jQuery('#ignoreSaveAddrChk').attr('checked')){
					goForCleanseAddrFlg = false;
					setValues(jQuery("#addrLine1").val(), jQuery("#addrLine2").val(),jQuery("#officeNo").val(),jQuery("#city").val(),jQuery("#country").val(),jQuery("#state").val(),jQuery("#zipCode").val(),"","","","","","","","",goForCleanseAddrFlg);
					var addAddressUrl="${AddressActionURL}";
					jQuery("#newContactForm").attr("action", addAddressUrl);		
					jQuery('#newContactForm').submit();
				}else{
					showCleansedaddress();
					}
				
			}else{
				if(document.getElementById('check_popup').checked)
				{
						//submit with recommended address
					 var addContactReviewUrl="${AddressActionURL}&addContact=true&timeZoneOffset=" + timezoneOffset;
					jQuery("#newContactForm").attr("action", addContactReviewUrl);		
					jQuery('#newContactForm').submit(); 
						
				}else{
						//alert('dsklcvklds');
						//alert('check box not checked');
						/*Setting input values to form*/
						validate_popup();
						<%--jQuery('#addressLine1Cleansed').val(jQuery('#addrLine1').val());
						jQuery('#addressLine2Cleansed').val(jQuery('#addrLine2').val());
						jQuery('#officeNumberCleansed').val(jQuery('#officeNo').val());
						jQuery('#cityCleansed').val(jQuery('#city').val());
						jQuery('#zipPostalCleansed').val(jQuery('#zipCode').val());
						jQuery('#countryFormVal').val(jQuery('#country').val());
						jQuery('#stateFormVal').val(jQuery('#state').val());--%>
												
						
						
						var addAddressUrl="${AddressActionURL}";
							jQuery("#newContactForm").attr("action", addAddressUrl);		
							jQuery('#newContactForm').submit();
				}
			}
		}
		//brmandal -- end
	
}

function showCleansedaddress() {
	
	jQuery.ajax({
			url:'${newAddressValidateURL}',
			beforeSend: function()
			{
				showOverlay();
			},
			type:'POST',
			dataType: 'JSON',
			data: {
					storeFrontName: "",
					addressLine1: jQuery('#addrLine1').val(),
					addressLine2: jQuery('#addrLine2').val(),
					officeNumber: jQuery('#officeNo').val(),
					city: 		  jQuery('#city').val(),
					country:	  jQuery('#country option:selected').val(),
					state:		  jQuery('#state option:selected').val(), 	
					postalCode:    jQuery('#zipCode').val()
					
				},
			success: function(results){
					//alert('success');
					hideOverlay();
					var obj2;
					try{
						 obj2=results;
					}catch(e){
						//alert('exception occured');
						}
					
					if(obj2 !=null){						
						var error=obj2.error;
						//var st = obj2.storeFrontName;
						
						if(error=="none"){
							
							jQuery('#cleanseAddressOutput').html(generateAddressDisplayHTML(obj2));
							populateAddressValues(obj2.addressLine1, obj2.addressLine2, obj2.officeNumber,
									obj2.city,obj2.country,obj2.state,obj2.postalCode,obj2.county,obj2.stateCode,obj2.stateFullName,
									obj2.district,obj2.region,obj2.latitude,obj2.longitude,obj2.countryISOCode);
							<%--
							//set the values to span
							//modified for MPS 2.1
							jQuery('#addrLine1_popup_span').html(obj2.addressLine1);
							jQuery('#houseNumber_popup_span').html(obj2.officeNumber);
							if(obj2.addressLine2 != '' && obj2.addressLine2 != null){
								jQuery('#addrLine2_popup_span').html(obj2.addressLine2 + ', ');
								jQuery('#addrLine2_popup_span').show();
								}
							if(obj2.city != '' && obj2.city!= null ){
								jQuery('#cityPopup').html(obj2.city);
							}
							if(obj2.county != '' && obj2.county != null ){
								jQuery('#countyPopup').html(',&nbsp;'+obj2.county);
								jQuery('#countyPopup').show();
							}
							if(obj2.state != '' && obj2.state!= null ){
								jQuery('#statePopup').html(',&nbsp;'+obj2.state);
								jQuery('#statePopup').show();
							}if(obj2.province != '' && obj2.province!= null ){
								jQuery('#provincePopup').html(',&nbsp;'+obj2.province);
								jQuery('#provincePopup').show();

								// region changed to district for MPS 2.1
							}if(obj2.district != ' ' && obj2.district != null ){
								jQuery('#regionPopup').html(',&nbsp;'+obj2.district);
								jQuery('#regionPopup').show();
							}
							jQuery('#zip_popup_span').html(obj2.postalCode);
							jQuery('#country_popup_span').html(obj2.country);
							if(obj2.province == '' || obj2.province == null ){
								jQuery('#provincePopup').hide();
							}
							if(obj2.county == '' || obj2.county == null ){
								jQuery('#countyPopup').hide();
							}
							// region changed to district for MPS 2.1
							if(obj2.district == '' || obj2.district == null ){
								jQuery('#regionPopup').hide();
							}
							if(obj2.addressLine2 == '' || obj2.addressLine2 == null ){
								jQuery('#addrLine2_popup_span').hide();
							}
							if(obj2.state == '' || obj2.state == null ){
								jQuery('#statePopup').hide();
							} --%>
							//modified for MPS 2.1 end
							//display the cleansed address
							jQuery('#cleansedAddress').show();
							//if error div is already show hide it
							jQuery('#errorMsg_popup').hide();
							
							
							//jQuery('#content_addr').data('height.dialog','1000px');
							
							
							jQuery("#ignoreSaveAddress").hide();
							}
						else if(error=="cleanseError")
							{
							jQuery('#errorMsg_popup').html(obj2.cleansedError);
							jQuery('#errorMsg_popup').show();
							jQuery("#ignoreSaveAddress").show();
							}
						else{
								jQuery('#errorMsg_popup').html('');
								jQuery.each(obj2, function(name, value) {
									if(value!="yes")
									jQuery('#errorMsg_popup').append('<li><strong>'+value+'</strong></li>');
									
								});
								jQuery('#errorMsg_popup').show();
								jQuery("#ignoreSaveAddress").show();
								//jQuery('#content_addr').data('height.dialog','800px');
																
							}
					}	else{
						//show the error div with error details
						
						//alert('in else');
						//alert(results);
						jQuery('#errorMsg_popup').html('');
						jQuery('#errorMsg_popup').append(results);
						jQuery('#errorMsg_popup').show();
						//to be changed according to the error div height
						//jQuery('#content_addr').data('height.dialog','800px');
						}
				},
			failure: function(results){
						jQuery('#errorMsg_popup').html('<li><strong>Unable to cleanse contact</strong></li>');
						jQuery('#errorMsg_popup').show();
					}
		});
	
	
	
}


function populateAddressValues(addressLine1, addressLine2,officeNumber,city,country,state,zipPostal,county,stateCode,stateFullName,district,region,latitude,longitude,countryISOCode)
{	

	claddressLine1 = addressLine1;
	claddressLine2 = addressLine2;
	clofficeNumber = officeNumber;
	clcity = city;
	clcountry = country;
	clstate = state;
	clzipPostal = zipPostal;	
	clcounty = county;
	clstateCode = stateCode;
	clstateFullName = stateFullName;
	cldistrict = district;
	clregion = region;
	cllatitude = latitude;
	cllongitude = longitude;
	clcountryISOCode = countryISOCode;
	
	
	
}
function enableInputs(){
	
	jQuery('#addrLine1').removeAttr('disabled');
	jQuery('#addrLine2').removeAttr('disabled');
	jQuery('#officeNo').removeAttr('disabled');
	jQuery('#city').removeAttr('disabled');
	jQuery('#country').removeAttr('disabled');
	jQuery('#state').removeAttr('disabled'); 						
	jQuery('#zipCode').removeAttr('disabled');
	jQuery('#building').romoveAttr('disabled');
	jQuery('#floor').removeAttr('disabled');
	jQuery('#office').removeAttr('disabled');
	}
function disableInputs(){
	
	jQuery('#addrLine1').attr('disabled','disabled');
	jQuery('#addrLine2').attr('disabled','disabled');
	jQuery('#officeNo').attr('disabled','disabled');
	jQuery('#city').attr('disabled','disabled');
	jQuery('#country').attr('disabled','disabled');
	jQuery('#state').attr('disabled','disabled'); 						
	jQuery('#zipCode').attr('disabled','disabled');
	jQuery('#building').attr('disabled','disabled');
	jQuery('#floor').attr('disabled','disabled');
	jQuery('#office').attr('disabled','disabled');
}

function ignoreAndSave(){		
	addServiceAddressElement(null,null,jQuery('#addrLine1').val(),jQuery('#addrLine2').val(),jQuery('#officeNo').val(),
	jQuery('#cityPopup').val(),jQuery('#state_popup').val(),null,null,jQuery('#country_popup').val(),jQuery('#zipCode').val(),
	jQuery('#storeName').val(),null, null, null,null);
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);					
}
/* END */


</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="requestConfirmDiv" style="display: none;">
      		
		 <div class="div-style32">
		 	<img src="<html:imagesPath/>green-check.png" alt="green check" width="50" height="50">
		 </div>
		 <div>
		 	<spring:message code="requestInfo.contact.saved"/>
		 </div>
		 <div class="buttonContainer div-style33" >
          <button class="button" onClick="dialog.dialog('close')"><spring:message code="button.ok"/></button>
        </div>
</div>
<div id="content-wrapper-inner">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1> 
     <h2 class="step"><spring:message code="requestInfo.cm.manageContact.heading.contacts"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong></h3></div>
        	<!-- END -->
        	
    <div class="main-wrap">
      <div class="content">
          <!-- MAIN CONTENT BEGIN -->
          <%--Changes MPS 2.1 --%>
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageContact.heading.addNewContact"/>
          <span><spring:message code="requestInfo.label.fieldsMarked"/> 
          <span class="req">*</span>
          <spring:message code="requestInfo.label.areRequired"/></span>					
		  <span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">**</span>
		  <spring:message code="requestInfo.label.provideAtleastOne"/>
		  </span> 
          </h3>
          <%--Ends Changes MPS 2.1 --%>
          <!-- This block is to show server side errors  -->
          <spring:hasBindErrors name="manageContactForm">
          	<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="${error.code}"/></strong></li>
            	</c:forEach>
     		</div>	
	      </spring:hasBindErrors>          
          
          <form:form id = "newContactForm" commandName="manageContactForm" method="post" >
          
          <%-- Below section is for the prev sr no and update flag binding--%>
		  <form:hidden path="prevSRNumber" id="prevSrNo"/>
		  
          
          
          <!-- This block is to show client side errors  -->
          <div class="error" id="errorMsg1" style="display:none"></div>

		  <!-- Include the common contact info jsp -->
		  <%-- <jsp:include page="/WEB-INF/jsp/common/commonContactInfo.jsp"/>  --%>                 
          
          <!-- <hr class="separator" /> -->
		  <p class="info"><spring:message code="requestInfo.cm.manageContact.heading.pleaseEnterTheFollowingInformationToAddaNewContact"/></p>
          
		  <div class="portletBlock infoBox rounded shadow" id="inputBoxes">	
		  	<!-- User needs to enter contact details here -->		
			<div class="columnsTwo">
				<div class="columnInner">
					<h4><spring:message code="requestInfo.heading.newContact"/></h4>					
                    <ul class="form">
					  <li>
                        <label for="firstname"><spring:message code="requestInfo.contactInfo.label.firstName"/> <span class="req">*</span> </label>
                        <span><form:input id="firstname" path="accountContact.firstName" maxlength="50" /></span>
                      </li>
					  
					  <li>
                        <label for="middlename"><spring:message code="requestInfo.contactInfo.label.middleName"/></label>
                        <span><form:input id="middlename" path="accountContact.middleName" maxlength="50" /></span>
                      </li>
					  <li>
                        <label for="lastname"><spring:message code="requestInfo.contactInfo.label.lastName"/> <span class="req">*</span> </label>
                        <span><form:input id="lastname" path="accountContact.lastName" maxlength="50" /></span>
                      </li>
                      <li>
                        <label for="workphone"><spring:message code="requestInfo.contactInfo.label.workPhone"/> <span class="req">*</span> </label>
                        <span><form:input id="workphone" path="accountContact.workPhone" maxlength="40" /></span>
                      </li>
					  <li>
                        <label for="alternatephone"><spring:message code="requestInfo.contactInfo.label.alternatePhone"/>  </label>
                        <span><form:input id="alternatephone" path="accountContact.alternatePhone" maxlength="40" /></span>
                      </li>
					  <li>
                        <label for="emailAddr"><spring:message code="requestInfo.contactInfo.label.emailAddress"/><span class="req">*</span> </label>
                        <span><form:input id="emailAddr" path="accountContact.emailAddress" maxlength="100" /></span>
                      </li>
					</ul>
				</div>
			</div>
			
			<!-- User needs to enter contact address details here -->
			<div class="columnsTwo splitter">
				<div class="columnInner">
					<h4><spring:message code="requestInfo.cm.manageContact.heading.newContactAddress"/></h4>
                    <ul class="form">					  
                      <li>
                        <label for="addrLine1"><spring:message code="requestInfo.addressInfo.label.addressLine1"/> <span class="req">*</span> </label>
                        <span><input id="addrLine1"  maxlength="70" class="input200"/></span>
                      </li>
					  <li>
                        <label for="addrLine2"><spring:message code="requestInfo.addressInfo.label.addressLine2"/> </label>
                        <span><input id="addrLine2"  maxlength="35" class="input200"/></span>
                      </li>
                      <li id="officeNoLI" style="display: none;">
                        <label for="officeNo"><spring:message code="requestInfo.addressInfo.label.officeNumber"/> <%--Changes for defect 7994 mps 2--%><span class="req" id="officeMandatory"></span><%--ENDS Changes for defect 7994 mps 2--%> </label>
                        <span><input id="officeNo"  maxlength="70" class="input200"  /></span>
                      </li>
					  <li>
                        <label for="city"><spring:message code="requestInfo.addressInfo.label.city"/> <span class="req">*</span> </label>
                        <span><input id="city"  maxlength="50" class="input200"/></span>
                      </li>
					  <li>
                        <label for="country"><spring:message code="requestInfo.addressInfo.label.country"/> <span class="req">*</span></label>
                        <span><select name="country" id="country" onchange="loadStateList();">
						</select></span>
                      </li>
					  <li id="list6">
					  <%--Changes mps 2.1 --%>
                        <label for="state"><spring:message code="requestInfo.addressInfo.label.region"/> <span class="req">**</span></label>
                        
                        <span><select name="state" id="state" disabled="disabled">
						</select></span>
						<div id="stateorzipmesg" class="note" ><span class="margin-top--8px">(<spring:message code="requestInfo.addressInfo.label.regionDetails"/>)</span></div>
                      </li>
					  <%--Ends changes mps 2.1 --%>
					  <li>
                        <label for="zipCode"><spring:message code="requestInfo.addressInfo.label.postalCode"/> <span class="req">**</span> </label>
                        <span><input id="zipCode"  maxlength="30" class="input200"/></span>
                      </li>
					  <li>
                        <label for="building"><spring:message code="requestInfo.addressInfo.label.building"/></label>
                        <span><form:input id="building" path="accountContact.address.physicalLocation1" maxlength="100" /></span>
                      </li>
					  <li>
                        <label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                        <span><form:input id="floor" path="accountContact.address.physicalLocation2" maxlength="100" /></span>
                      </li>
					  <li>
                        <label for="office"><spring:message code="requestInfo.addressInfo.label.office"/> </label>
                        <span><form:input id="office" path="accountContact.address.physicalLocation3" maxlength="100" /></span>
                      </li>
					</ul>
				</div>
			</div>			
		  </div>
		  
		  <!-- Hidden fields to bind the address data with form -->
		  <div style="display:none">
		  <%-- These fields are for cleanse address already provided by User--%>
		    <form:input  id="addressLine1Cleansed" path="accountContact.address.addressLine1"/>
			<form:input  id="addressLine2Cleansed" path="accountContact.address.addressLine2"/> 
			<form:input  id="officeNumberCleansed" path="accountContact.address.officeNumber"/>
			<form:input  id="cityCleansed" path="accountContact.address.city"/> 		  
			<form:input  id="zipPostalCleansed" path="accountContact.address.postalCode"/>    
		   <%-- ENDS --%>
		   
		  	<form:input id="countryFormVal" path="accountContact.address.country" />
		  	<form:input id="stateFormVal" path="accountContact.address.state" />
		  	<form:input id="pickupAddresscounty" path="accountContact.address.county" />
			<form:input id="pickupAddressstateCode" path="accountContact.address.stateCode" />
			<form:input id="pickupAddressstateFullName" path="accountContact.address.stateFullName" />
			<form:input id="pickupAddressdistrict" path="accountContact.address.district" />
			<form:input id="pickupAddressregion" path="accountContact.address.region" />
			<form:input id="pickupAddresslatitude" path="accountContact.address.latitude" />
			<form:input id="pickupAddresslongitude" path="accountContact.address.longitude" />
			<form:input id="pickupAddresscountryISOCode" path="accountContact.address.countryISOCode" />
			<form:input id="pickupAddressisAddressCleansed" path="accountContact.address.isAddressCleansed" value="false"/>
		 
		  
		  
		  
			<form:input id="pageName" path="pageName" value="addContact"/>
			<form:input id="pickupAddresssavedErrorMessage" path="accountContact.address.savedErrorMessage" />
			
		  </div>
		  
		  
    
    <%--brmandal start --%>
    <div class="error" id="errorMsg_popup" style="display:none"></div>
    <%-- Addded for MPS 2.1 cleanse error --%>
   <div id="ignoreSaveAddress" style="display: none;" align="left">
							<input type="checkbox" id="ignoreSaveAddrChk"/>
							<label><spring:message code="addressCleansing.error.ignore.warning">
							</spring:message></label>
		  </div>
    <%-- Ends  for MPS 2.1 cleanse error --%>
   <div id="cleansedAddress" style="display:none;">
					<hr class="separator" />
						<p class="info banner"><spring:message code="requestInfo.popup.cleansedAddresses"/></p>
						<div class="portletBlock infoBox">
						<div class="columnsOne">
						<div class="columnInner">
						<ul class="roDisplay">
						<li id="cleanseAddressOutput"></li>
						<%-- 
									<li><!-- <div id="storefront_popup_span"></div> -->
									<div id="addrLine1_popup_span"></div>
									<div id="houseNumber_popup_span"></div>
									<div id="addrLine2_popup_span"></div>
									<span id="addrLine3_popup_span"></span><br />  
									<div id="city_state_zip_popup_span">
									<div style="display:inline;" id="cityPopup"></div>
									<div style="display:inline;" id="countyPopup"></div>
									<div style="display:inline;" id="statePopup"></div>
									<div style="display:inline;" id="provincePopup"></div>
									<div style="display:inline;" id="regionPopup"></div>
									</div>
									<div id="zip_popup_span"></div>
									<div id="country_popup_span"></div></li>
									<!-- <div id="building_popup_span"></div></li>
									<div id="floor_popup_span"></div></li>
									<div id="office_popup_span"></div></li> -->
							--%>		

								</ul>
								<p class="check">
								<input type="checkbox" id="check_popup"	onclick="javascript:validate_popup();" /> 
								<label for="modAddress">
								<%-- <spring:message code="requestInfo.popup.cleansedAddresses"/> --%>
								<spring:message code="requestInfo.popup.usecleansedAddresses"/>
								</label>
								</p>
								
							</div>
						</div>
					</div>
						
		</div>
		  
		  <!-- This block is for all the buttons at end of page -->
		  <div class="buttonContainer">
			<div  id="button1"><button class="button_cancel" type="button" onclick="javascript:backToSelect();"><spring:message code="button.back"/></button></div>
            <%-- <button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWCONTACT%>','<%=LexmarkSPOmnitureConstants.ADDCONTACTCANCEL%>');"><spring:message code="button.cancel"/></button> --%>
            <div  id="button2"><button id="submitButtn" class="button" type="button" onclick="javascript:validateBlank();"><spring:message code="button.submit"/></button></div>
            
            <div class="clear-both"></div>
          </div>
          
          </form:form>
		  
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
</div>

  <div id="dialog"></div>
