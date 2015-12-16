/**
 * 
 */
urlParams.prototype.updateHtmlIds=function(pC){
	for(var i=0;i<this.htmlInputIds.length;i++){
		this.htmlInputIds[i]=(pC+this.htmlInputIds[i]);
	}	
};
PreferenceFilter.prototype.setPrefName=function(n){
	this.prefName=n;
};
PreferenceFilter.prototype.getPrefName=function(){
	return this.prefName;
};
PreferenceFilter.prototype.setIsDefault=function(n){
	this.isDefault=n;
};
PreferenceFilter.prototype.getIsDefault=function(n){
	return this.isDefault;
};
PreferenceFilter.prototype.getId=function(){
	return this.id;
};
PreferenceFilter.prototype.setId=function(n){
	return this.id=n;
};
/*allhtmlTextIds.prototype.updateWithContext=function(pC,htmlTextIds){
	for(field in htmlTextIds){
		this[pC+field]=htmlTextIds[field];
		htmlTextIds[field]=null;
	};
};*/
customizeIndex.prototype.updateWithContext=function(pC,customizeIndex){
	for(field in customizeIndex){
		
		if(field!='filterLocation'){
			this[pC+field]=customizeIndex[field];
			customizeIndex[field]=null;
		}		
	};
};

function CommonFilters(){
	var parentContext="",sUrl,cUrl,siUrl,bUrl,fUrl,znUrl,customizeUrl,allLocUrl;
	var that=this;
	var urlParamsObjPref=new urlParams();
	
	
	var allhtmlTextIdsPref=new allhtmlTextIds();
	var customizeIndexSettingPref=new customizeIndex();
	
	
	this.setParentContext=function(f){
		parentContext=f;
		
	};
	this.getParentContext=function(){
		return parentContext;
	};
	this._initUrls=function(state,city,site,bldng,flr,zn,allUrl,customizeURL){
		sUrl=state;cUrl=city;siUrl=site,bUrl=bldng;fUrl=flr;znUrl=zn;allLocUrl=allUrl;customizeUrl=customizeURL;
		//allhtmlTextIdsPref.updateWithContext(parentContext,allhtmlTextIdsPref);
		customizeIndexSettingPref.updateWithContext(parentContext,customizeIndexSettingPref);
		urlParamsObjPref.updateHtmlIds(parentContext);
	};
	this._initBindChange=function(){
		this.__bindCountry();
		this.__bindState();
		this.__bindCity();
		this.__bindSite();
		this.__bindBuilding();
		this.__bindFocusEvent();
		this.bindCalendarProps();
		
	};
	this.__createMultiSelects=function(){
		new MultiSelect({
			"elementId":parentContext+'productType',
			"elementWidth":width,
			"dataList":productTypeArray
		});
		new MultiSelect({
			"elementId":parentContext+'productSeries',
			"elementWidth":width,
			"dataList":productSeriesArray
		});
		new MultiSelect({
			"elementId":parentContext+'requestType',
			"elementWidth":width,
			"dataList":requestTypeArray
		});
		new MultiSelect({
			"elementId":parentContext+'requestStatus',
			"elementWidth":width,
			"dataList":requestStatusArray
		});
		new MultiSelect({
			"elementId":parentContext+'areaDrop',
			"elementWidth":width,
			"dataList":areaArray
		});
		new MultiSelect({
			"elementId":parentContext+'subAreaDrop',
			"elementWidth":width,
			"dataList":subAreaArray
		});
		new MultiSelect({
			"elementId":parentContext+'assetLifeCycle',
			"elementWidth":width,
			"dataList":assetLifeCycleArray
		});
		new MultiSelect({
			"elementId":parentContext+'devicePhase',
			"elementWidth":width,
			"dataList":devicePhaseArray
		});
		new MultiSelect({
			"elementId":parentContext+'hardwareStatus',
			"elementWidth":width,
			"dataList":hardwareStatusArray
		});
		new MultiSelect({
			"elementId":parentContext+'srOPSStatus',
			"elementWidth":width,
			"dataList":srOPSStatusArray
		});
		new MultiSelect({
			"elementId":parentContext+'srSubStatus',
			"elementWidth":width,
			"dataList":srSubStatusArray
		});
		new MultiSelect({
			"elementId":parentContext+'srSource',
			"elementWidth":width,
			"dataList":srSourceArray
		});
			
		
		
		
		
	};
	
	this.__bindCountry=function(){	
		$('#'+parentContext+'selectCountry').change(function(){
			//alert('country change');
			urlParamsObjPref.clearHtmlAfter($(this).attr('id'));
			if($(this).val()==""){
				return;
			}
			urlParamsObjPref.setDropParamsToObj();
			
			$(this).attr('disabled','disabled');
			that.getState(null,null);
		});
	};
	
	this.getState=function(p,c){
		//function getState(p,c){
			var url=appendURLPrams(sUrl,urlParamsObjPref);	
			var prefPctx=parentContext;
			$.getJSON(url,function(response){
				//alert(parentContext);
				if(prefPctx!=parentContext)return;
				$('#'+parentContext+'selectCountry').removeAttr('disabled','');
				$('#'+parentContext+'selectState').append(response.state);
				if(response.city!=""){$('#'+parentContext+'selectCity').append(response.city);}
				$('#'+parentContext+'selectState').removeAttr('disabled','');
				if(p!=null){
					$('#'+parentContext+'selectState').val(p);
					
				}if(c!=null){
					$('#'+parentContext+'selectCity').val(c);
					
				}
					
			});
		//}

	};
	
	this.__bindState=function(){
		$('#'+parentContext+'selectState').change(function(){
			urlParamsObjPref.clearHtmlAfter($(this).attr('id'));	
			if($(this).val()==""){
				return;
			}
			urlParamsObjPref.setDropParamsToObj();
			$(this).attr('disabled','disabled');
			that.getCity(null);
			
		});
	};
	
	this.getCity=function (p){
		
		var url=appendURLPrams(cUrl,urlParamsObjPref);
		var prefPctx=parentContext;
		
		$.get(url,function(response){
			if(prefPctx!=parentContext)return;
			$('#'+parentContext+'selectState').removeAttr('disabled','');
			if(!isResponseBlank(response)){$('#'+parentContext+'selectCity').append(response);}			
			if(p!=null){
				$('#'+parentContext+'selectCity').val(p);
			}
		});
	};

	this.__bindCity=function(){
		$('#'+parentContext+'selectCity').change(function(){
			
			urlParamsObjPref.clearHtmlAfter($(this).attr('id'));	
			if($(this).val()==""){
				return;
			}
			urlParamsObjPref.setDropParamsToObj();
			
			$(this).attr('disabled','disabled');
			that.getSiteBuildingZone(null,null,null);
		});
	};
	this.getSiteBuildingZone=function (site,building,zone){
		var url=appendURLPrams(siUrl+"&frCal=true",urlParamsObjPref);
		var prefPctx=parentContext;
		
		$.getJSON(url,function(response){
			if(prefPctx!=parentContext)return;
			$('#'+parentContext+'selectCity').removeAttr('disabled','');
			$('#'+parentContext+'bldng').removeAttr('disabled','');
			$('#'+parentContext+'site').removeAttr('disabled','');
			$('#'+parentContext+'zone').removeAttr('disabled','');
			if(!isResponseBlank(response.site)){
				$('#'+parentContext+'site').append(response.site);			
			}
			if(site!=null){$('#'+parentContext+'site').val(site);}
			if(!isResponseBlank(response.building)){
				$('#'+parentContext+'bldng').append(response.building);			
			}
			if(building!=null){$('#'+parentContext+'bldng').val(building);}
			if(!isResponseBlank(response.zone)){
				$('#'+parentContext+'zone').append(response.zone);			
			}
			if(zone!=null){$('#'+parentContext+'zone').val(zone);}
		});
	};
	
	this.__bindSite=function(){
		$('#'+parentContext+'site').change(function(){
			urlParamsObjPref.setDefaultHTMlAt(parentContext+'bldng');	
			if($(this).val()==""){
				return;
			}
			urlParamsObjPref.setDropParamsToObj();
			
			$(this).attr('disabled','disabled');
			that.getBuilding();
		});
	};
	
	this.getBuilding=function (p){
		var url=appendURLPrams(bUrl,urlParamsObjPref);
		var prefPctx=parentContext;
		
		$.get(url,function(response){
			if(prefPctx!=parentContext)return;
			$('#'+parentContext+'site').removeAttr('disabled','');
			if(!isResponseBlank(response)){$('#'+parentContext+'bldng').append(response);}			
			if(p!=null){
				$('#'+parentContext+'bldng').val(p);
			}
		});
	};
	
	this.__bindBuilding=function(){
		$('#'+parentContext+'bldng').change(function(){
			urlParamsObjPref.setDefaultHTMlAt(parentContext+'flr');
			if($(this).val()==""){
				return;
			}
			urlParamsObjPref.setDropParamsToObj();	
			
			$(this).attr('disabled','disabled');
			that.getFloor(null);
		});
	};
	this.__bindFocusEvent=function(){
		for(fields in allhtmlTextIdsPref){
			
			$('#'+parentContext+fields).focus(function(){
				var id=$(this).attr('id');
				
				var fId=id.split(parentContext)[1];				
				if($(this).val()==allhtmlTextIdsPref[fId]){
					$(this).val('');
				}
			});
			$('#'+parentContext+fields).blur(function(){
				var id=$(this).attr('id');
				var fId=id.split(parentContext)[1];				
				if($(this).val()==''){
					$(this).val(allhtmlTextIdsPref[fId]);
				}
			});
		};
	};
	this.getFloor=function (p){
		urlParamsObjPref["extra"]="blank";
		var url=appendURLPrams(fUrl,urlParamsObjPref);
		var prefPctx=parentContext;
		$.get(url,function(response){
			if(prefPctx!=parentContext)return;
			$('#'+parentContext+'bldng').removeAttr('disabled','');
			$('#'+parentContext+'flr').removeAttr('disabled','');
			if(!isResponseBlank(response)){$('#'+parentContext+'flr').append(response);}
			
			if(p!=null){
				$('#'+parentContext+'flr').val(p);
			}
		});	
	};
	
	this.__loadCustomizeSetting=function (indexes){
		customizeIndexSettingPref=new customizeIndex();
		customizeIndexSettingPref.updateWithContext(parentContext,customizeIndexSettingPref);
		
		if(indexes==""){
		
			for(fields in customizeIndexSettingPref){
			if(customizeIndexSettingPref[fields]!=null){
				
				if(customizeIndexSettingPref[fields][1]==true){
					$('#editPreferencesPopup #'+fields).show();
					
				}else{
						$('#editPreferencesPopup #'+fields).hide();
					
					}
				}
			}
			return;
		}
		var array=indexes.split(",");
		for(field in customizeIndexSettingPref){
			if(customizeIndexSettingPref[field]!=null){
				if(array.indexOf(customizeIndexSettingPref[field][0])==-1){
					customizeIndexSettingPref[field][1]=false;	
					$('#editPreferencesPopup #'+field).hide();
				}else{
					customizeIndexSettingPref[field][1]=true;
					$('#editPreferencesPopup #'+field).show();
				}
			}
			
		}
		
		
		
	};

	
	this.__updateBreadCrumpCity=function(){
		breadCrumpObject.updateCity($('#selectCity option:selected').text(),$(this).val());//Defined in defaultview.jsp
		breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
		
	};
	this.__updateBreadCrumpState=function(){
		breadCrumpObject.updateState($('#selectState option:selected').text(),$(this).val());//Defined in defaultview.jsp
		breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
		
	};
	this.__updateBreadCrumpCountry=function(){
		breadCrumpObject.resetRestFields(1);//Defined in defaultview.jsp
		breadCrumpObject.updateCountry($(parentContext+' #selectCountry option:selected').text(),$(this).val());//Defined in defaultview.jsp
		breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
	};
	
	this.__createObjectsForDB=function(){
		var deviceInfoDB = new DeviceInformation();		
		deviceInfoDB.setEditPrefPopVals(parentContext,allhtmlTextIdsPref);
		
		var srInfoDB=new SrRequestInformation();
		srInfoDB.setEditPrefPopVals(parentContext,allhtmlTextIdsPref);
		
		var isCHlOrAddress=$('#'+parentContext+'customerHierarchy').css('display')=='block'?"chl":"address";
		var addressDB=new AddressInformation();
		
		if(isCHlOrAddress=="address"){
			if(parentContext==""){
				//this will be blank for the first time loading of the page and user clicks on set preference.
				addressDB.setEditPrefPopVals(addressPopup,parentContext,allhtmlTextIdsPref);	
			}else{
				addressDB.setEditPrefPopVals(addressPrefPop,parentContext,allhtmlTextIdsPref);
			}
				
		}
		
		var chlDB=new chl();
		if(isCHlOrAddress=="chl"){
			//To be Implemented
			chlDB.setEditPrefPopVals(parentContext,allhtmlTextIdsPref);
		}
		var prefernceFiltr=new PreferenceFilter();
		//Setting the preference filter to save to DB information
		prefernceFiltr.setAddress(addressDB);
		prefernceFiltr.setDevice(deviceInfoDB);
		prefernceFiltr.setRequest(srInfoDB);
		//prefernceFiltr.setAccountInfo(accountInfoDB);
		prefernceFiltr.setChl(chlDB);
		prefernceFiltr.setCHLOrAddress(isCHlOrAddress);
		
		prefernceFiltr.setId($('#editPreferencesPopup #prefId').val());
		prefernceFiltr.setPrefName($('#editPreferencesPopup #name').val());
		prefernceFiltr.setIsDefault($('#editPreferencesPopup #default').attr('checked')=='checked'?'Y':'N');
		return prefernceFiltr;
		//End Setting the preference filter to save to DB information
		
		//alert("final JSON "+JSON.stringify(siebelJSON));
	
	};
	
	this.__savePreference=function(url,prefernceFiltr){
		//alert(JSON.stringify(prefernceFiltr));
				
		showOverlayPopup();
		jQuery.ajax({
			url:url,
			dataType:"json",
			type:'POST',
			data: {"id":prefernceFiltr.getId(),
				"address": prefernceFiltr.getCHLOrAddress()=="address"?JSON.stringify(prefernceFiltr.getAddress(),checkValue):JSON.stringify(prefernceFiltr.getChl(),checkValue),
					"device":JSON.stringify(prefernceFiltr.getDevice(),checkValue),
					"srRequest":JSON.stringify(prefernceFiltr.getRequest(),checkValue),
					"prefName":prefernceFiltr.getPrefName(),
					"isDefaultPref":prefernceFiltr.getIsDefault()
				},
			success: function(results){
				//the result wud contain preference id for future ref				
				
				prefernceFiltr.id=results.id;
				if(!preferenceObject.updatePreferenceById(results.id,JSON.stringify(prefernceFiltr,checkValue))){
					//This means updated is true then no need to create append html
					that.appendHtmlPref(generateTemplatePref(results));
				}else{
					$('#pref_'+results.id+' a').text(results.prefName);
				}
				hideOverlayPopup();
				closeDialogPrefPop();
				}
			
			});

	};
	this.appendHtmlPref=function(html){
		$('#FiltersMenu #savedFiltersPrefPop').after(html);
	};
	this.__getPreference=function(url,successFunc){
		
			showOverlay();
			
			jQuery.getJSON(url,function(jsonObj){
				hideOverlay();
				that.__displayInfoInPopup(that.__parseSettings(jsonObj));
				successFunc();
				
			});
		
	};
	this.__parseSettings=function(jsonObj){
		var prefernceFiltr=new PreferenceFilter();
		//Below check is for identifying whether its chl or Address
		prefernceFiltr.setPrefName(jsonObj.prefName);
		prefernceFiltr.setIsDefault(jsonObj.isDefault);
		prefernceFiltr.setId(jsonObj.id);
		for(var i=0;i<chlInformation.dbFieldNames.length;i++){
			if(chlInformation.dbFieldNames[i] in jsonObj.address){
				prefernceFiltr.setCHLOrAddress("chl");
			}else{
				prefernceFiltr.setCHLOrAddress("address");
			}
		}
		
		if(prefernceFiltr.getCHLOrAddress()=="chl"){
			prefernceFiltr.setChl(jsonObj.address);
		}else{
			prefernceFiltr.setAddress(jsonObj.address);
		}
		prefernceFiltr.setRequest(jsonObj.srRequest);
		prefernceFiltr.setDevice(jsonObj.device);
		
		return prefernceFiltr;
		//this.__displayInfoInPopup();
		
	};
	this.__displayInfoInPopup=function(prefernceFiltr){
	
		if(prefernceFiltr.getCHLOrAddress()=="chl"){
			that.__setValuesToInput(chlInformation.htmlInputIds,chlInformation.dbFieldNames,prefernceFiltr.getChl());
			that.__showCHLFilter();
			that.__setCHLValue(prefernceFiltr.getChl()["cNm"]);//This method is declared in mapsFilters.jsp
		}else{
			that.__setValuesToInput(addressFields.htmlInputIds,addressFields.dbFieldNames,prefernceFiltr.getAddress());
			addressPrefPop=prefernceFiltr.getAddress();
			that.__showAddress(prefernceFiltr.getAddress());
			//Here we need to trigger change for Country manually
			if("aId" in addressPrefPop){
				//Address selected from popup so coutry sate city site builing floor call will be different
				that.__loadCoutrySateCitySiteBuilding(addressPrefPop["aId"]);
			}else{
				that.__loadAllDropDowns(addressPrefPop);
			}
			
		}
		//alert(prefernceFiltr.getPrefName());
		$('#editPreferencesPopup #prefId').val(prefernceFiltr.getId());
		$('#editPreferencesPopup #name').val(prefernceFiltr.getPrefName());
		//alert('def= '+prefernceFiltr.getIsDefault());
		$('#editPreferencesPopup #default').attr('checked',(prefernceFiltr.getIsDefault()=='Y'?true:false));
		
		
		that.__setValuesToInput(deviceFields.deviceIdInputIds,deviceFields.dbFieldNames,prefernceFiltr.getDevice());
		that.__setValuesToInput(srFields.srInputIds,srFields.dbFieldNames,prefernceFiltr.getRequest());
		
		that.__setMultiValue();
	};
	this.__showCHLFilter=function(){
		$("#editPreferencesPopup #filterLocation").css('display','none');
		$('#'+parentContext+"customerHierarchy").css('display','block');
		
		//that.__closePopupCustomize();
		
		//Below code is for saving the customer hierarchy display as customize popup
		//__customizeIndexSetting['filterLocation'][1]=false;//This is locatted in LBSService.js
		//that.__saveCustomizeSetting('customerHierarchy',true);
	};
	
	this.__saveCustomizeSetting=function(id,isChecked){	
		
		customizeIndexSettingPref[id][1]=(isChecked==true?true:false);
		if(id==(parentContext+'customerHierarchy')){
			customizeIndexSettingPref['filterLocation'][1]=false;
		}else{
			customizeIndexSettingPref[parentContext+'customerHierarchy'][1]=false;
		}
			var stringArr="";
			for(field in customizeIndexSettingPref){
				if(customizeIndexSettingPref[field]!=null){
					if(customizeIndexSettingPref[field][1]==true){
						stringArr+=customizeIndexSettingPref[field][0];
						stringArr+=",";
					}
				}
								 
			}
			//alert('final array =' + stringArr);
			$.ajax({
				url:customizeUrl+"&t="+new Date().getTime(),
				type:'POST',
				data:{indexes:stringArr,
					  id:$('#editPreferencesPopup #prefId').val()},
				success: function(response){
					preferenceObject.updateFieldSettings($('#editPreferencesPopup #prefId').val(),stringArr);
					},
			  failure: function(results){}
			});
		
	};
	this.__setCHLValue=function (v){
		$('#'+parentContext+'chlNodeValueLabel').html(v);
	};
	
	this.__setValuesToInput=function (arrayInputId,arrayDbFieldName,object){
		
		for(field in object){
			var i=arrayDbFieldName.indexOf(field);
			if(i!=-1){
				$('#'+parentContext+arrayInputId[i]).val(object[field]);			
			}
			
		}
	};
	this.__showAddress=function(obj){		
		$('#'+parentContext+'address_display_li').html(generateAddressDisplayLBSHTML(obj));
		if("aId" in obj){$('#'+parentContext+'addRessLink').text("Change Address");}		
	};
	
	this.__loadCoutrySateCitySiteBuilding=function (addressId){
		urlParamsObjPref.clearHtmlAfter(parentContext+'selectCountry');
		urlParamsObjPref.setDefaultHTMlAt(parentContext+'selectCountry');
		
		urlParamsObjPref=new urlParams();
		urlParamsObjPref.updateHtmlIds(parentContext);
		
		var url=appendURLPrams(allLocUrl+"&frCal=true&aid="+addressId,urlParamsObjPref);//"${getAllLocationURL}&aid="+addressId+"&frCal=true";
		
		urlParamsObjPref.disableAll();
		
		var prefPctx=parentContext;
		$.getJSON(url,function(response){
			urlParamsObjPref.enableAll();
			if(prefPctx!=parentContext)return;
			if(!isResponseBlank(response.country)){$('#'+parentContext+'selectCountry').append(response.country);}
			if(!isResponseBlank(response.state)){$('#'+parentContext+'selectState').append(response.state);}
			if(!isResponseBlank(response.city)){$('#'+parentContext+'selectCity').append(response.city);}
			if(!isResponseBlank(response.site)){$('#'+parentContext+'site').append(response.site);}
			if(!isResponseBlank(response.building)){$('#'+parentContext+'bldng').append(response.building);}
			if(!isResponseBlank(response.zone)){$('#'+parentContext+'zone').append(response.zone);}
			
			//if(!isResponseBlank(response.floor)){$('#flr').append(response.floor);}
			
			//This condition will be checked if filter values are from DB and when selected from address Popup
			if("ctry" in addressPrefPop){
				$('#'+parentContext+'selectCountry').val(addressPrefPop["ctry"]);
			}else{
				$('#'+parentContext+'selectCountry').val('');
			}
			if("state" in addressPrefPop){
				if(addressPrefPop["state"].indexOf("^")!=-1){
					$('#'+parentContext+'selectState').val(addressPrefPop["state"]);
				}else{
					var tokens=["s","p","c","d"];
					for(var i=0;i<tokens.length;i++){
						if($('#'+parentContext+'selectState').val()==""){
							$('#'+parentContext+'selectState').val(addressPrefPop["state"]+"^"+tokens[i]);		
						}else{
							break;
						}
					}
				}				
				
				
				
			}if("cty" in addressPrefPop){
				$('#'+parentContext+'selectCity').val(addressPrefPop["cty"]);
			}if("sNm" in addressPrefPop){
				$('#'+parentContext+'site').val(addressPrefPop["sNm"]);
			}
			if("bNm" in addressPrefPop){
				$('#'+parentContext+'bldng').val(addressPrefPop["bNm"]);			
			}
			
			if("zn" in addressPrefPop){
				$('#'+parentContext+'zone').val(addressPrefPop["zn"]);			
			}
			
			if("fNm" in addressPrefPop){
				// This is for get floor since its a separate call 
				urlParamsObjPref.setDropParamsToObj();
				getFloor(("fNm" in addressPrefPop)==true?addressPrefPop["fNm"]:null);	
				//Ends floor call here 
				
			}
			
			//this.__updateBradCrumpAll();
			
			
		});
		
	};
	this.__loadAllDropDowns=function(addressPrefObj){
		
		urlParamsObjPref=new urlParams();
		urlParamsObjPref.updateHtmlIds(parentContext);
		
		urlParamsObjPref.clearHtmlAfter(parentContext+'selectCountry');
		urlParamsObjPref.setDropParamsToObj();
		urlParamsObjPref.disableAll();
		
		if("ctry" in addressPrefObj){
			urlParamsObjPref["ctry"]=addressPrefObj["ctry"];
			$('#'+parentContext+'selectCountry').val(addressPrefObj["ctry"]);		
		}
		$('#'+parentContext+'selectCountry').removeAttr('disabled','');
		if("ctry" in addressPrefObj){		
			that.getState(("state" in addressPrefObj)==true?addressPrefObj["state"]:null,("cty" in addressPrefObj)==true?addressPrefObj["cty"]:null);		
		}else{
			$('#'+parentContext+'selectState').removeAttr('disabled');
		}
		if("state" in addressPrefObj){
			urlParamsObjPref["state"]=addressPrefObj["state"];
			that.getCity(("cty" in addressPrefObj)==true?addressPrefObj["cty"]:null);
		}else{
			$('#'+parentContext+'selectCity').removeAttr('disabled');
		}
		if("cty" in addressPrefObj){
			urlParamsObjPref["cty"]=addressPrefObj["cty"];
			that.getSiteBuildingZone(("sNm" in addressPrefObj)==true?addressPrefObj["sNm"]:null,("bNm" in addressPrefObj)==true?addressPrefObj["bNm"]:null,("zn" in addressPrefObj)==true?addressPrefObj["zn"]:null);
		}else{
			$('#'+parentContext+'selectCity').removeAttr('disabled','');
			$('#'+parentContext+'site').removeAttr('disabled','');
			$('#'+parentContext+'bldng').removeAttr('disabled','');
			$('#'+parentContext+'zone').removeAttr('disabled','');
		}
		if("bNm" in addressPrefObj){
			urlParamsObjPref["bldng"]=addressPrefObj["bNm"];
			that.getFloor(("fNm" in addressPrefObj)==true?addressPrefObj["fNm"]:null);		
		}else{
			$('#'+parentContext+'flr').removeAttr('disabled');
		}
		

	};
	this.__setMultiValue=function (){
		$('#editPreferencesPopup .options').each(function()
				{
			
					var arraySelect=$(this).val();
					
					if(arraySelect==null)
					{
						arraySelect=[""];
					}
					var arraySlText=[];
					$(this).find("option").each(function(){
						if($.inArray($(this).val(),arraySelect)!=-1){
							arraySlText.push($(this).text());	
						}
					});
					var id=$(this).attr('id').split("-");
					if(arraySelect.length<3){
						$('#'+id[0]+'-placeholderContent').html(arraySlText.join(","));
					}
					else{
						$('#'+id[0]+'-placeholderContent').html(arraySlText.length+str1);
					}
				});
	};

	
	this.__updateBradCrumpAll=function(){
		breadCrumpObject.updateCountry(addressPopup["ctry"],addressPopup["ctry"]);
		breadCrumpObject.updateState(addressPopup["state"],addressPopup["state"]);
		breadCrumpObject.updateCity(addressPopup["cty"],addressPopup["cty"]);//Defined in defaultview.jsp
		breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
	};
	
	this.__loadMapWithSavedFilter=function(){
		
		if(isChl){
			prefernceFiltr.setChl(jsonObj.address);
		}else{
			prefernceFiltr.setAddress(jsonObj.address);
		}
		prefernceFiltr.setRequest(jsonObj.srRequest);
		
		
		
		var deviceInfoSiebel=new DeviceInformation();
		deviceInfoSiebel.setSiebelDataFromDBObj(prefernceFiltr.getDevice());
		
		var srInfoSiebel=new SrRequestInformation();
		srInfoSiebel.setSiebelDataFromDBObj(prefernceFiltr.getRequest());
		
		
		
		var isCHlOrAddress=$(parentContext+' #filterLocation').css('display')=='block'?"address":"chl";
		//Populate ADdress Information
		var addressSiebel=new AddressInformation();
		
		
		if(isCHlOrAddress=="address"){
			addressSiebel.setSiebelDataFromDBObj(prefernceFiltr.getAddress());			
		}
		var chlSiebel=new chl();
		
		if(isCHlOrAddress=="chl"){
			//need to discuss for encryption
			//encryptionObj.setEncryptionFlag(true);//Set this as newly encryption requireds
			chlSiebel.setSiebelDataFromDBObj(prefernceFiltr.getChl());
			
		}
		
		/*
		if("${fleetMgmtForm.l5HeirarchyParentChain}" != ""){
			var l5HierarchyList="${fleetMgmtForm.l5HeirarchyParentChain}";
			chlSiebel.setL5UserInformation(l5HierarchyList);
			chlDB.setDBL5UserInformation(l5HierarchyList);
		}*/
		
		
		var filtersObj=new Filters();
		
		filtersObj.setRestFields(deviceInfoSiebel,addressSiebel,chlSiebel,srInfoSiebel,requestTypeObj);
		
		siebelJSON=new SiebelJSON();//this should be main page object.
		siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
		siebelJSON.setPostMessageURL(location.href);
		siebelJSON.setFilters(filtersObj);
		siebelJSON.setMview();
		siebelJSON.setQueryId(emailAddress);
		//alert(JSON.stringify(siebelJSON,checkValue));
		//Setting the preference filter to save to DB information
		showMapBtnClicked();
		
		//alert("final JSON "+JSON.stringify(siebelJSON));s
	};
	this.bindCalendarProps=function(){
		jQuery('#'+parentContext+'startDateFilter').val(allhtmlTextIdsPref["startDateFilter"]);//localizeDate(convertDateToString(new Date().addDays(-15))));
		jQuery('#'+parentContext+'endDateFilter').val(allhtmlTextIdsPref["endDateFilter"]);//localizeDate(todayStr));
		$("#"+parentContext+"dateRangeFilterContainer").find("input").mouseup(function(){
			var beginDate=null,endDate=null;
			if(this.id==(parentContext+"startDateFilter")){
				beginDate=convertDateToString(new Date().addDays(-90));
				if($("#"+parentContext+"endDateFilter").val()==allhtmlTextIdsObj[parentContext+"endDateFilter"]){
					endDate=todayStr;
				}else{
					endDate=formatDateToDefault($("#"+parentContext+"endDateFilter").val());
				}
			}else if(this.id==(parentContext+"endDateFilter")){
				endDate=todayStr;
				if($("#"+parentContext+"startDateFilter").val()==allhtmlTextIdsObj[parentContext+"startDateFilter"]){
					beginDate=convertDateToString(new Date().addDays(-90));
				}else{
					beginDate=formatDateToDefault($("#"+parentContext+"startDateFilter").val());
				}
			}

			show_cal(this.id, beginDate, endDate);bindCloseCal();
		});
		$("#"+parentContext+"dateRangeFilterContainer").find("input ~ img").mouseup(function(){
			jQuery(this).prev().mouseup();
		});
		$('#'+parentContext+'imgLocalizedBeginDateAsset').click(function(){
			show_cal(parentContext+'installDateFrom', null, $('#'+parentContext+'installDateTo').val()==""?null:formatDateToDefault($("#"+parentContext+"installDateTo").val()));bindCloseCal();
		});
		$('#'+parentContext+'imgLocalizedEndDateAsset').click(function(){
			show_cal(parentContext+'installDateTo', $('#'+parentContext+'installDateFrom').val()==""?null:formatDateToDefault($("#"+parentContext+"installDateFrom").val()), todayStr);bindCloseCal();
		});
	};
	
	this.__resetFilters=function(){
				
		clearValPop();
		//Reset country list
		$('#'+parentContext+'selectCountry').html(countryHTML);
		
		//Reset Account Info
		
		//Remove the cancel icons of calendar
		$('.cancelIconCalendar').remove();
		//alert(parentContext);
		$('#'+parentContext+'address_display_li').html('');
		$('#'+parentContext+'chlNodeValueLabel').html('');
		
		//Set default values to input fields
		for(fields in allhtmlTextIdsPref){
			if(allhtmlTextIdsPref[fields]!=null){
				$('#'+parentContext+fields).val(allhtmlTextIdsPref[fields]);
			}
			
		}
		
	};
	this.clearValuesInput=function(inputIds){
		for(var i=0;i<inputIds.length;i++){
			jQuery('#'+parentContext+inputIds[i]).val('');
		}

	};
}










