function redirectToAssetListPage(redirectToAssetListUrl)
{
	//This request goes to the manageAssetController default mapping, results in assetList.jsp
	//var redirectToAssetListUrl='<portlet:renderURL></portlet:renderURL>';
	//alert(redirectToAssetListUrl);
	window.location.href=redirectToAssetListUrl;
}

function addConsumablesContact(ci, ln, fn, midName, wp, altPhn, ea)
	{
		//alert("Inside addConsumables Contact");
		jQuery("#consumablesContactId").val(ci);
		jQuery("#consumablesFirstName").val(fn);
		jQuery("#consumablesLastName").val(ln);
		jQuery("#consumablesWorkPhone").val(wp);
		jQuery("#consumablesEmailAddress").val(ea);
		//jQuery("#consumablesAlternatePhone").val("TEST ALTERNATE PH");
		jQuery("#consumablesAlternatePhone").val(altPhn);
		//jQuery("#consumablesMiddleName").val("TEST MIDDLE NAME");
		jQuery("#consumablesMiddleName").val(midName);
		
		//just for display purpose  consumables contact of asset   
		jQuery("#consumablesLastNameLabel").html(ln);
		jQuery("#consumablesFirstNameLabel").html(fn);
		//jQuery("#consumablesMiddleNameLabel").html("TEST MIDDLE NAME");
		jQuery("#consumablesMiddleNameLabel").html(midName);
		jQuery("#consumablesWorkPhoneLabel").html(wp);
		jQuery("#consumablesEmailAddressLabel").html(ea);
		//jQuery("#consumablesAlternatePhoneLabel").html("TEST ALTERNATE PH");
		jQuery("#consumablesAlternatePhoneLabel").html(altPhn);
	}
	
	/*function addInstalledAddress(addressId,addressName,addressLine1,addressLine2,addressLine3,
			addressCity,addressState,addressProvince,temp,addressCountry,addressPostCode,phyloc1,
			phyloc2, phyloc3, favorite)*/
//MPS 2.1 Changes
function addInstalledAddress(addressId,addressName,addressLine1,addressLine2,
		addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,phyloc1,
		phyloc2, phyloc3, favorite)
	{	
		if(addressName=="null"){		
		jQuery("#installedAddressName").html("");
		}
		else{		
		jQuery("#installedAddressName").html(addressName);	
		}
	
		jQuery("#installedAddressLine1").html(addressLine1);
		jQuery("#installedAddressLine2").html(addressLine2);
		
		jQuery("#installedCountry").html(addressCountry);
		jQuery("#installedState").html(addressState);
		jQuery("#installedProvince").html(addressProvince);
		jQuery("#installedCity").html(addressCity);
		jQuery("#installedPostalCode").html(addressPostCode);
				
		jQuery("#installAddressName").val(addressName);
		jQuery("#installAddressLine1").val(addressLine1);
		jQuery("#installAddressLine2").val(addressLine2);
		
		jQuery("#installCountry").val(addressCountry);
		jQuery("#installState").val(addressState);
		jQuery("#installProvince").val(addressProvince);
		jQuery("#installCity").val(addressCity);
		jQuery("#installPostalCode").val(addressPostCode);
		jQuery("#installStrFrntNm").val(storefrontName);
		
	}
	
	/*function addConsumablesAddress(addressId,addressName,addressLine1,addressLine2,addressLine3,
			addressCountry,addressState,addressProvince,temp,addressCity,addressPostCode,phyloc1,
			phyloc2, phyloc3, favorite)*/
//MPS 2.1 Changes
function addConsumablesAddress(addressId,addressName,addressLine1,addressLine2,addressCity,
		addressState,addressProvince,addressCountry,addressPostCode,storefrontName,phyloc1,
		phyloc2, phyloc3, favorite)
	{
		
		jQuery("#consumablesContactAddressLine1").val(addressLine1);
		jQuery("#consumablesContactAddressLine2").val(addressLine2);
		//jQuery("#consumablesContactAddressLine3").val(addressLine3);
		jQuery("#consumablesContactAddressCountry").val(addressCountry);
		jQuery("#consumablesContactAddressState").val(addressState);
		jQuery("#consumablesContactAddressProvince").val(addressProvince);
		jQuery("#consumablesContactAddressCity").val(addressCity);
		jQuery("#consumablesContactAddressPostalCode").val(addressPostCode);
		jQuery("#consumablesContactAddressStrFrntNm").val(storefrontName);
		/*jQuery("#consumablesContactAddressBuilding").val(phyloc1);
		jQuery("#consumablesContactAddressFloor").val(phyloc2);
		jQuery("#consumablesContactAddressOffice").val(phyloc3);*/
		
		
		
		//just for display purpose  consumables address      
		jQuery("#consumablesCAddressLine1").html(addressLine1);
		jQuery("#consumablesCAddressLine2").html(addressLine2);
		//jQuery("#consumablesCAddressLine3").html(addressLine3);
		jQuery("#consumablesCCountry").html(addressCountry);
		jQuery("#consumablesCState").html(addressState);
		jQuery("#consumablesCProvince").html(addressProvince);
		jQuery("#consumablesCCity").html(addressCity);
		jQuery("#consumablesCPostalCode").html(addressPostCode);
		/*jQuery("#consumablesCBuilding").html(phyloc1);
		jQuery("#consumablesCFloor").html(phyloc2);
		jQuery("#consumablesCOffice").html(phyloc3);*/
		
		jQuery("#consumablesCBuilding").val(phyloc1);
		jQuery("#consumablesCFloor").val(phyloc2);
		jQuery("#consumablesCOffice").val(phyloc3);
	}
	
	function enableChk()
	{	
		jQuery('input[type="checkbox"]').attr('disabled', false);
	}
	
	function addSiteContactElement(ci, ln, fn, wp, ea)
	{	
		jQuery("#sitePrimaryFName").val(fn);
		jQuery("#sitePrimaryLName").val(ln);
		jQuery("#sitePrimaryPhone").val(wp);
		jQuery("#sitePrimaryEmail").val(ea);
		
		//just for display purpose  primary site contact
		jQuery("#sitePrimaryFNameLbl").html(fn);			
		jQuery("#sitePrimaryLNameLbl").html(ln);
		jQuery("#sitePrimaryPhoneLbl").html(wp);
		jQuery("#sitePrimaryEmailLbl").html(ea);
	}
	
	
	function backToSelect()
	{
		/*showOverlay();
		window.location.href="manageassets";*/
		redirectToList(); 
	}
	function redirectToList(){
		jConfirm("<spring:message code="common.back.alert"/>", "", function(confirmResult) {
			if(confirmResult){
				showOverlay();
				window.location.href="manageassets";
			}else{
				return false;
				}
		});
	}
	
	//added by jyoti
	function isAlphaNumeric(s)  {  
		var patrn=/^[a-zA-Z0-9]+$/;  
		if (!patrn.exec(s))
		{	
			return false;  
		}
		
		return true;  
	} 
	
	function isNumeric(s)  {  
		var patrn=/^[0-9]+$/;  
		if (!patrn.exec(s))
		{	
			return false;  
		}
		
		return true;  
	} 
	//added by jyoti
	function isIPAddress_new(s){  
		/*var patrn=/^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;*/
		
		var patrn =/^(?:(?:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9](?::|$)){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))$/i;
		if (s == "0.0.0.0")
			return false;
		else if(s=="255.255.255.255")
			return false;
		else if (!patrn.exec(s)) 
			return false;  
		return true;  
	}  
	//added by arko
	function physicalLocationCheck(val){
		var physicalLocationPatrn=/^[a-zA-Z0-9]{1,30}$/;
		if(!physicalLocationPatrn.exec(val))
			return false;
			return true;
	}
	//added by jyoti
	function checktime(input)
	{
		var patrn=/^((\d)|(0\d)|(1\d)|(2[0-3]))\:((\d)|([0-5]\d))\:((\d)|([0-5]\d))$/;
		if(!patrn.exec(input))
		return false;
		return true;
	}
	//added by jyoti
	function checkdate(s){
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
		if (!patrn.exec(s)) 
			return false;
		return true;
	}
	//added
	function validate(elementId,input)
	{
		//alert('elementId='+elementId+" input="+input);
		if(elementId=="addtnlDescription")
		{
			if(input.length > 2000)
				return '<spring:message code="validation.description.length.errorMsg"/>';
			
			return true;
		}
		else if(elementId=="serialNumber")
		{
			
			if(!(isAlphaNumeric(input)))
				return '<spring:message code="validation.Asset.serialNo.format.errorMsg"/>';
			
			return true;
		}
		else if(elementId=="ipAddress")
		{
			if(!isIPAddress_new(input))
				return '<spring:message code="validation.Asset.ipadd.format.errorMsg"/>';
				return true;
		}else if(elementId=="deinstallipAddress")
		{
			if(!isIPAddress_new(input))
				return '<spring:message code="validation.Asset.ipadd.format.installDeinstallError"/>';
				return true;
		}
		
		else if(elementId=="lastPageReadDate"||elementId=="lastColorPageReadDate")
		{
			var substring=input.split(" ");
			if(!(checkdate(substring[0])&&checktime(substring[1])))
				return '<spring:message code="validation.date.format.errorMsg"/>';
				return true;
		}
		else if(elementId=="installDate")
		{
			if(!checkdate(input))
				return '<spring:message code="validation.date.format.errorMsg"/>';
				return true;
		}
		//modified
		else if(elementId=="lastPageCount"||elementId=="lastColorPageCount")
		{
			if(!isDigit(input)){
				if(elementId=="lastColorPageCount")
					return '<spring:message code="validation.Asset.lastcolorpgcount.format.errorMsg"/>';
				else if(elementId=="lastPageCount")
					return '<spring:message code="validation.Asset.lastpgcount.format.errorMsg"/>';
			}
				
				return true;
		}
		
		
		else
			return true;
		
	}		
	
	