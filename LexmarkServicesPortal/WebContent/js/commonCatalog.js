function redirectToAssetListPage(redirectToCatalogListUrl)
{
	//This request goes to the manageCatalogController default mapping, results in CatalogList.jsp
	//var redirectToCatalogListUrl='<portlet:renderURL></portlet:renderURL>';
	//alert(redirectToCatalogListUrl);
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
		
		//just for display purpose  consumables contact of catalog   
		jQuery("#consumablesLastNameLabel").html(ln);
		jQuery("#consumablesFirstNameLabel").html(fn);
		//jQuery("#consumablesMiddleNameLabel").html("TEST MIDDLE NAME");
		jQuery("#consumablesMiddleNameLabel").html(midName);
		jQuery("#consumablesWorkPhoneLabel").html(wp);
		jQuery("#consumablesEmailAddressLabel").html(ea);
		//jQuery("#consumablesAlternatePhoneLabel").html("TEST ALTERNATE PH");
		jQuery("#consumablesAlternatePhoneLabel").html(altPhn);
	}
	
	function addInstalledAddress(addressId,addressName,addressLine1,addressLine2,addressLine3,
			addressCity,addressState,addressProvince,temp,addressCountry,addressPostCode,phyloc1,
			phyloc2, phyloc3, favorite)
	{
		
		jQuery("#installedAddressLine1").html(addressLine1);
		jQuery("#installedAddressLine2").html(addressLine2);
		jQuery("#installedAddressLine3").html(addressLine3);
		jQuery("#installedCountry").html(addressCountry);
		jQuery("#installedState").html(addressState);
		jQuery("#installedProvince").html(addressProvince);
		jQuery("#installedCity").html(addressCity);
		jQuery("#installedPostalCode").html(addressPostCode);
		/*jQuery("#installedBuilding").html(phyloc1);
		jQuery("#installedFloor").html(phyloc2);
		jQuery("#installedOffice").html(phyloc3);*/
		
		jQuery("#installAddressLine1").val(addressLine1);
		jQuery("#installAddressLine2").val(addressLine2);
		jQuery("#installAddressLine3").val(addressLine3);
		jQuery("#installCountry").val(addressCountry);
		jQuery("#installState").val(addressState);
		jQuery("#installProvince").val(addressProvince);
		jQuery("#installCity").val(addressCity);
		jQuery("#installPostalCode").val(addressPostCode);
		/*jQuery("#installBuilding").val(phyloc1);
		jQuery("#installFloor").val(phyloc2);
		jQuery("#installOffice").val(phyloc3);*/
		
	}
	
	function addConsumablesAddress(addressId,addressName,addressLine1,addressLine2,addressLine3,
			addressCountry,addressState,addressProvince,temp,addressCity,addressPostCode,phyloc1,
			phyloc2, phyloc3, favorite)
	{
		
		jQuery("#consumablesContactAddressLine1").val(addressLine1);
		jQuery("#consumablesContactAddressLine2").val(addressLine2);
		jQuery("#consumablesContactAddressLine3").val(addressLine3);
		jQuery("#consumablesContactAddressCountry").val(addressCountry);
		jQuery("#consumablesContactAddressState").val(addressState);
		jQuery("#consumablesContactAddressProvince").val(addressProvince);
		jQuery("#consumablesContactAddressCity").val(addressCity);
		jQuery("#consumablesContactAddressPostalCode").val(addressPostCode);
		jQuery("#consumablesContactAddressBuilding").val(phyloc1);
		jQuery("#consumablesContactAddressFloor").val(phyloc2);
		jQuery("#consumablesContactAddressOffice").val(phyloc3);
		
		
		//just for display purpose  consumables address      
		jQuery("#consumablesCAddressLine1").html(addressLine1);
		jQuery("#consumablesCAddressLine2").html(addressLine2);
		jQuery("#consumablesCAddressLine3").html(addressLine3);
		jQuery("#consumablesCCountry").html(addressCountry);
		jQuery("#consumablesCState").html(addressState);
		jQuery("#consumablesCProvince").html(addressProvince);
		jQuery("#consumablesCCity").html(addressCity);
		jQuery("#consumablesCPostalCode").html(addressPostCode);
		jQuery("#consumablesCBuilding").html(phyloc1);
		jQuery("#consumablesCFloor").html(phyloc2);
		jQuery("#consumablesCOffice").html(phyloc3);
		
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
		showOverlay();
		window.location.href="managecatalogs";
	}
	
	//added by jyoti
	function isAlphaNumeric(s)  {  
		var patrn=/^[a-zA-Z0-9]{1,30}$/;  
		if (!patrn.exec(s)) 
			return false;  
		return true;  
	} 
	//added by jyoti
	function isIPAddress_new(s){  
		var patrn=/^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;//change by jyoti
		if (s == "0.0.0.0")
			return false;
		else if(s=="255.255.255.255")
			return false;
		else if (!patrn.exec(s)) 
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
					
		var patrn=/^\d{1,2}\/\d{1,2}\/\d{4}$/; 
		if (!patrn.exec(s)) 
			return false;
		return true;
	}
	//added by jyoti
	function validate(elementId,input)
	{
		if(elementId=="serialNumber")
		{
			if(!(isDigit(input)&&input.length==7))
				return "Serial Number is not valid";
			return true;
		}
		else if(elementId=="ipAddress")
		{
			if(!isIPAddress_new(input))
				return "IP Address is not vald";
				return true;
		}
		else if(elementId=="assetCCatalognter"||elementId=="productName"||elementId=="refId"||elementId=="hostName"||elementId=="lastColorPageCount"||elementId=="customerAssetTag")
		{
			if(!isAlphaNumeric(input))
			{
				if (elementId=="productName")
					return " Product Name is not valid";
				else if(elementId=="hostName")	
					return " Host Name is not valid";
				else if(elementId=="customerAssetTag")
					return " Customer Asset Tag is not valid";
				else if(elementId=="assetCostCenter")
					return "Asset cost Center is not valid";
			}
				return true;
		}
		else if(elementId=="lastPageReadDate"||elementId=="lastColorPageReadDate")
		{
			var substring=input.split(" ");
			if(!(checkdate(substring[0])&&checktime(substring[1])))
				return "Date Time is not valid";
				return true;
		}
		else if(elementId=="installDate")
		{
			if(!checkdate(input))
				return "Install Date is not valid";
				return true;
		}
		else if(elementId=="lastPageCount")
		{
			if(!isDigit(input))
				return "LTPC is not valid";
				return true;
		}
		else
			return true;
		
		
	}
	//added by jyoti
	function checkNull(elementId,input){
			if(!input.length > 0)
			{
				if(elementId=="serialNumber")
					return "serial Number is Empty";
				else if(elementId=="ipAddress")	
					return "IP Address is Empty";
				else if(elementId=="installDate")	
					return "Install Date is Empty";
				else if(elementId=="lastPageCount")		
					return "LTPC is Empty";
				else if(elementId=="lastPageReadDate"||elementId=="lastColorPageReadDate")	
					return "Date Time is Empty";
				else if(elementId=="hostName")
					return "Host Name is Empty";
				else if(elementId=="productName")
					return "Product name is empty";
				else if(elementId=="customerAssetTag")
					return "Customer Asset tag is empty";
				else if(elementId=="assetCostCenter")
					return "Asset cost Center is empty";
			}
			else 
				return "ok";
			
		}
	