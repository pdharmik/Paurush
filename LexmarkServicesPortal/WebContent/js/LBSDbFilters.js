/**
 * 
 */

//var addressInputIds=["_ctry","_state","_prvnc","_site","_bldng","_flr","_ofceNm"];


function Address(){
	//If any field gets added here add it in lbsService addressMappingMapsRequest() function
	this.aId="";this.aLine1="";this.aLine2="";
	this.cty="";this.state="";this.prvnc="";
	this.poCde="";this.ctry="";
	/*this.conty="";this.stCode="";
	this.stFulNm="";this.dstrct="";this.regin="";this.ltude="";
	this.lngtde="";this.svErMsg="";this.ctryISOCd="";this.isAdrsCleansed="";
	this.bldng="";this.flr="";this.ofceNm="";this.site="";this.strFrntNm="";*/
	
};
Address.prototype={
		setAddressId:function(addressId){this.aId=addressId;},
		getAddressId:function(){return this.aId;},
		
		setAddressLine1:function(addressLine1){this.aLine1=addressLine1;},
		getAddressLine1:function(){return this.aLine1;},
		
		setAddressLine2:function(addressLine2){this.aLine2=addressLine2;},
		getAddressLine2:function(){return this.aLine2;},
		
		/*setStoreFrontName:function(storeFrontName){this.strFrntNm=storeFrontName;},
		getStoreFrontName:function(){return this.strFrntNm;},*/
		
		setCity:function(city){this.cty=city;},
		getCity:function(){return this.cty;},
		
		setState:function(state){this.state=state;},
		getState:function(){return this.state;},
		
		setProvice:function(province){this.prvnc=province;},
		getProvice:function(){return this.prvnc;},
		
		setPostalCode:function(postalCode){this.poCde=postalCode;},
		getPostalCode:function(){return this.poCde;},
		
		setCountry:function(country){this.ctry=country;},
		getCountry:function(){return this.ctry;},
		
		/*setCounty:function(county){this.conty=county;},
		getCounty:function(){return this.conty;},
		
		setStateCode:function(stateCode){this.stCode=stateCode;},
		getStateCode:function(){return this.stCode;},
		
		setStateFullName:function(stateFullName){this.stFulNm=stateFullName;},
		getStateFullName:function(){return this.stFulNm;},
		
		setDistrict:function(district){this.dstrct=district;},
		getDistrict:function(){return this.dstrct;},
		
		setRegion:function(region){this.regin=region;},
		getRegion:function(){return this.regin;},
		
		setLatitude:function(latitude){this.ltude=latitude;},
		getLatitude:function(){return this.ltude;},
		
		setLongitude:function(longitude){this.lngtde=longitude;},
		getLongitude:function(){return this.lngtde;},
		
		setSavedErrorMessage:function(savedErrorMsg){this.svErMsg=savedErrorMsg;},
		getSavedErrorMessage:function(){return this.svErMsg;},
		
		setCountryIsoCode:function(countryIsoCode){this.ctryISOCd=countryIsoCode;},
		getCountryIsoCode:function(){return this.ctryISOCd;},
		
		setIsAddressCleansed:function(isAddrCleansed){this.isAdrsCleansed=isAddrCleansed;},
		getIsAddressCleansed:function(){return this.isAdrsCleansed;},
		
		setBuilding:function(building){this.bldng=building;},
		getBuilding:function(){return this.bldng;},
		
		setFloor:function(floor){this.flr=floor;},
		getFloor:function(){return this.flr;},
		
		setOfficeNumber:function(officeNumber){this.ofceNm=officeNumber;},
		getOfficeNumber:function(){	return this.ofceNm;},
		
		setSite:function(site){this.site=site;},
		getSite:function(){	return this.site;}*/
		
		
					
};




//var deviceIdInputIds=["_srNm","_model","_ipAddr","_type","_assetEtlmnt"];

//var requestIdInputIds=["_reqNo","_reqType","_reqStat","_strtDt","_endDt","_ordrStat","_actvStat"];



function PreferenceFilter(){
	this.address=null;
	this.chl=new chl();
	this.device=null;
	this.srRequest=null;
	this.account=new AccountInformation();
	this.isChlOrAddress;
}
PreferenceFilter.prototype={
		setAddress:function(address){this.address=address;},
		getAddress:function(){return this.address;},
		setChl:function(chl){this.chl=chl;},
		getChl:function(){return this.chl;},
		setDevice:function(device){this.device=device;},
		getDevice:function(){return this.device;},
		setRequest:function(request){this.srRequest=request;},
		getRequest:function(){return this.srRequest;},
		setAccountInfo:function(account){this.account=account;},
		getAccountInfo:function(){return this.account;},
		setCHLOrAddress:function(v){this.isChlOrAddress=v;},
		getCHLOrAddress:function(){return this.isChlOrAddress;},
};







function setPreferenceToDB(url,isPref){
	/* is preference is the flag on the basis of which we will identify whether to load the prefernce or 
	 * the last settings saved in dB while user clicked on apply filter*/
		
	showOverlay();
	jQuery.ajax({
		url:url,			
		type:'POST',
		data: {"address": prefernceFiltr.getCHLOrAddress()=="address"?JSON.stringify(prefernceFiltr.getAddress(),checkValue):JSON.stringify(prefernceFiltr.getChl(),checkValue),
				"device":JSON.stringify(prefernceFiltr.getDevice(),checkValue),
				"srRequest":JSON.stringify(prefernceFiltr.getRequest(),checkValue),
				"account":JSON.stringify(prefernceFiltr.getAccountInfo(),checkValue),
				"isPreference":isPref
			},
		success: function(results){
				hideOverlay();
			}
		
		});


}

function getFilterSettings(url){
	showOverlay();
	clearValuesFilter();
	jQuery.getJSON(url,function(jsonObj){
		hideOverlay();
		parseSettings(jsonObj);
		setValuesToObjects(true);checkAndSetCancelForDate();showMapBtnClicked();hideOverlay();
	});
}

function parseSettings(jsonObj){
	
	prefernceFiltr=new PreferenceFilter();
	if(jsonObj==null){
		return;
	}
	//Below check is for identifying whether its chl or Address
	var isChl=false;
	
	for(var i=0;i<chlInformation.dbFieldNames.length;i++){
		if(chlInformation.dbFieldNames[i] in jsonObj.address){
			isChl=true;
		}else{
			isChl=false;
		}
	}
	//alert('is chl='+isChl);
	if(isChl){
		prefernceFiltr.setChl(jsonObj.address);
	}else{
		prefernceFiltr.setAddress(jsonObj.address);
	}
	prefernceFiltr.setRequest(jsonObj.srRequest);
	prefernceFiltr.setDevice(jsonObj.device);
	prefernceFiltr.setAccountInfo(jsonObj.account);
	//This is for setting account information
	if("acId" in jsonObj.account){initAccountInfor(jsonObj.account.acId,jsonObj.account.accNm);}
	
	//var reqObj=prefernceFiltr.getDevice();
	
	/*for(vari in prefernceFiltr.getDevice()){
		alert("value= "+prefernceFiltr.getDevice()[vari]);
	}*/
	
	if(isChl){
		setValuesToInput(chlInformation.htmlInputIds,chlInformation.dbFieldNames,prefernceFiltr.getChl());
		showCHLFilter();//This method is declared in mapsFilters.jsp
		setCHLValue(prefernceFiltr.getChl()["cNm"]);//This method is declared in mapsFilters.jsp
	}else{
		setValuesToInput(addressFields.htmlInputIds,addressFields.dbFieldNames,prefernceFiltr.getAddress());
		addressPopup=prefernceFiltr.getAddress();//addressPopup is declared in defaultview.jsp This is set so that next tym apply filter will get the value from popup
		showAddress(prefernceFiltr.getAddress());//this method is defined in defaultView.jsp
		//Here we need to trigger change for Country manually
		if("aId" in addressPopup){
			//Address selected from popup so coutry sate city site builing floor call will be different
			loadCoutrySateCitySiteBuilding(addressPopup["aId"]);
		}else{
			loadAllDropDowns(addressPopup);
		}
		
	}
	
	
	setValuesToInput(deviceFields.deviceIdInputIds,deviceFields.dbFieldNames,prefernceFiltr.getDevice());
	setValuesToInput(srFields.srInputIds,srFields.dbFieldNames,prefernceFiltr.getRequest());
	
	setMultiValue();//This method is declared in mapsFilters.jsp
}

function setValuesToInput(arrayInputId,arrayDbFieldName,object){
	
	for(field in object){
		var i=arrayDbFieldName.indexOf(field);
		if(i!=-1){
			$('#'+arrayInputId[i]).val(object[field]);			
		}
		
	}
};
function clearValuesInput(inputIds){
	for(var i=0;i<inputIds.length;i++){
		jQuery('#'+inputIds[i]).val('');
	}
}


function PreferenceFromDB(){}
PreferenceFromDB.prototype={
	setPreferenceObject:function(s){
		this["prefObj"]=JSON.parse(s);
	},
	setIspreferenc:function(){
		return this["prefObj"].isPref="Y";
	},
	getIspreferenc:function(){
		return this["prefObj"].isPref;
		
	},
	getPreferenceObject:function(s){
		//Code changes for OPS since multiple preferences will be returned.
		for(var i=0;i<this.prefObj.length;i++){
			if(this.prefObj[i]!=null && this.prefObj[i].isDefault=="Y"){
				return this.prefObj[i];		
			}
		}
		return null;
		
	},
	getPreferenceObjectById:function(s){
		//Code changes for OPS since multiple preferences will be returned.
		var i=this.getIndex(s);
		if(i!=null)
				return this.prefObj[i];
		else
			return null;
		
	},
	getIndex:function(s){
		for(var i=0;i<this.prefObj.length;i++){
			
			if(this.prefObj[i]!=null && this.prefObj[i].id==s){
				return i;		
			}
		}
		return null;
		
	},
	updatePreferenceById:function(i,s){
		var iN=this.getIndex(i);
		if(iN!=null){
			this.prefObj[iN]=JSON.parse(s);
			return true;
		}else{
			this.prefObj.push(JSON.parse(s));
			return false;
		}
			
		
	},
	removePreferenceById:function(i){
		var iN=this.getIndex(i);
		this.prefObj[iN]=null;			
		
	},
	generatePrefHtml:function(){
		var html="";
		for(var i=0;i<this.prefObj.length;i++){
			if(this.prefObj[i]!=null)
				html+=generateTemplatePref(this.prefObj[i]);
		}
		return html;
	},
	updateFieldSettings:function(idex,s){
		var id=this.getIndex(idex);
		
		if(id!=null){
			this.prefObj[id]["fieldDisp"]=s;
		}
		
	}
	
};

var preferenceObject=new PreferenceFromDB();


