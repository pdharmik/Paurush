/**
 * 
 */



function Placements(id){
      this.htmlId=id;
      
};

Placements.prototype.htmlIdassetList='list-placement-template';

Placements.prototype.postPlacement=function(){
    
};


Placements.prototype.onChangeHandler=function(){
	var place=this.getValue();
	if(place==="off"){
		$("#filterDeviceHeader,#DeviceProperties").removeClass("disabledDiv");
		$("#placementDetailsList").hide();
		$("#deviceDetailsList").show();
		adjustLeftNav();
		$('#DeviceProperties').find('input, select').each(function () {
		    $(this).prop('disabled', false);
		});
		$('#DeviceProperties .multiSelectBody').each(function(){			
			$(this).removeAttr('disabled');
		});
		$('#imgLocalizedBeginDateAsset').on("click",bindToBeginDateImgClick);
		$('#imgLocalizedEndDateAsset').on("click",bindToEndDateImgClick);
		$("#customizeDevice").attr("onclick","loadPopupCustomize(this)");
	}
	else if(place==="on"){
		$("#placementDetailsList").show();
		$("#filterDeviceHeader,#DeviceProperties").removeClass("disabledDiv");
		$("#placementDetailsList").css("height","10%");
		$("#placementHeader").show();
		$("#placementContent").hide();
		$("#deviceDetailsList").show();

		adjustLeftNav();
		$('#DeviceProperties').find('input, select').each(function () {
		    $(this).prop('disabled', false);
		});
		$('#DeviceProperties .multiSelectBody').each(function(){				
			$(this).removeAttr('disabled');
		});
		$('#imgLocalizedBeginDateAsset').on("click",bindToBeginDateImgClick);
		$('#imgLocalizedEndDateAsset').on("click",bindToEndDateImgClick);
		$("#customizeDevice").attr("onclick","loadPopupCustomize(this)");
	}
	else if(place==="only"){
		$("#placementDetailsList").show();
		$("#placementDetailsList").css("height","100%");
		$("#placementHeader").show();
		$("#placementContent").show();
		$("#deviceDetailsList").hide();
		adjustLeftNav();
		var placementVal;
		placementVal=jQuery("#placementSelect").val();
		if(placementVal=='only'){
			$('#DeviceProperties').find('input, select, span').each(function () {
			    $(this).prop('disabled', true);
			});
			$('#DeviceProperties .multiSelectBody').each(function(){				
				$(this).attr('disabled', 'disabled');
			});
			$('#imgLocalizedBeginDateAsset,#customizeDevice').attr("onclick","");
			$("#filterDeviceHeader,#DeviceProperties").addClass("disabledDiv");
			$('#imgLocalizedEndDateAsset').off("click");
			$('.cancelIconCalendar').hide();
			clearValuesInput(deviceFields.deviceIdInputIds);
			for(fields in deviceFilterFields){
				$('#'+fields).val(deviceFilterFields[fields]);
			}
			
		}
	}
};

Placements.prototype.addPlacement=function(){
  //Post lbs message for adding a placement
	var error="";
	$('.serviceError').empty();
	if($('#popupPlacementDiv #plc-name').val()==""){
		error+="<li class=\"portlet-msg-error\">"+placement_Msgs.placementError1+"</li>";
	}if($('#popupPlacementDiv #plc-model').val()==""){
		error+="<li class=\"portlet-msg-error\">"+placement_Msgs.placementError2+"</li>";
	}
	if($('#popupPlacementDiv #plc-ip').val()!="" && !ipAddress_validate($('#popupPlacementDiv #plc-ip').val())){
		error+="<li class=\"portlet-msg-error\">"+placement_Msgs.placementError3+"</li>";
	}
	if(error!=""){
		$('#popupPlacementDiv .plc-error ul').append(error);
		return ;
	}else{
		placementPopupClose();
		//post json ... 
		lbs.postMessage({"action": "addNew",
		    "item": "placement"
		    });
	}
};
Placements.prototype.handleAddResponse=function(id){
  //Handle response from lbs after adding placement
	
};


Placements.prototype.decomPlacement=function(id){
	$('#fleetMgmtForm #lbs_placementId').val(id);
	//populate address values
	if(id=="")return;
	decommissionPlacementRequest(id);
};

Placements.prototype.changePlacement=function(id){
	$('#fleetMgmtForm #lbs_placementId').val(id);
	if(id=="")return;
	lbs.postMessage({
		"action": "enableDrag",
	    "item": "placement",
	    "info": {"id": id}
	    });
};

Placements.prototype.getValue=function(){
	  //initialize the list template for displaying purpose
		
		return $("#"+this.htmlId).val(); 
	};
Placements.prototype.showAccountPopup=function(plId){
	//Show Account selection
	$('#fleetMgmtForm #lbs_placementId').val(plId);
	isHardwarePage="true";
	showAccountPopup();//Defined in defaultView.jsp
	return false;
};
Placements.prototype.createHWorder=function(acId,acNm,soldtoList,contractNumber,agreementName,agreementid,showPrice,creditCardFlag,poFlag){
	//Show Account selection
	$('#fleetMgmtForm #lbs_accountId').val(acId);
	$('#fleetMgmtForm #lbs_accountName').val(acNm);
	$('#fleetMgmtForm #lbs_soldtoNumber').val(soldtoList);
	$('#fleetMgmtForm #lbs_contractNumber').val(contractNumber);
	$('#fleetMgmtForm #lbs_agreementName').val(agreementName);
	$('#fleetMgmtForm #lbs_agreementid').val(agreementid);
	$('#fleetMgmtForm #lbs_showPrice').val(showPrice);
	$('#fleetMgmtForm #lbs_creditCardFlag').val(creditCardFlag);
	$('#fleetMgmtForm #lbs_poFlag').val(poFlag);
	$('#fleetMgmtForm #lbs_source').val('map');
	$('#fleetMgmtForm').attr('action','hardwareorder');
	$('#fleetMgmtForm').submit();
};
Placements.prototype.createInstallRequest=function(plId){
	
}


var placementObj=new Placements("placementSelect");

function ipAddress_validate(s){  
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

/*var placementVal;
$('#placementSelect').bind('click', function(){
	placementVal=jQuery("#placementSelect").val();
	if(placementVal=='only'){
		$('#DeviceProperties').find('input, select, span').each(function () {
		    $(this).prop('disabled', true);
		});
		$('#imgLocalizedBeginDateAsset').off("click");
		$('#imgLocalizedEndDateAsset').off("click");
		//$('.cancelIconCalendar').trigger("click");
		clearValuesInput(deviceFields.deviceIdInputIds);
		
	}else{
		$('#DeviceProperties').find('input, select').each(function () {
		    $(this).prop('disabled', false);
		});
		$('#imgLocalizedBeginDateAsset').on("click",bindToBeginDateImgClick);
		$('#imgLocalizedEndDateAsset').on("click",bindToEndDateImgClick);
	}
});*/