/** Changes for LBS 1.5 - Level of Details checking in Change Asset and Move Asset pages **/
function showBuildingFloorZone(assetAction)
{
	//alert(assetAction);
	var lbsAddressFlag = $("#"+assetAction+"LBSAddressFlag").val();
	var addressId;
	var addressSection;
	
	if(assetAction.toLowerCase() === "movetoaddress")
	{
		addressId = $("#moveToAddressId").val();
		addressSection = "selectMoveToAddress";
	}
	else
	{
		addressId = $("#"+assetAction+"AddressId").val();
		addressSection = "selectLocalAddress";
	}
	
	//$("#moveToAddressLevelOfDetails").val("floor");
	var pageFlowLcase = pageFlow.toLowerCase();
	
	if(pageFlowLcase === "change" || assetAction == "install")
	{
	
		if((lbsAddressFlag != null && lbsAddressFlag.toLowerCase() == "true")
			|| (addressId != null && $.trim(addressId).length > 0))
		{
			
			showBuildingFloorDropDOwns(addressId, assetAction, addressSection);
		}
		else
		{
			hideAssetBuildingFloorSection(assetAction);
			showBuildingFloorText(assetAction, addressSection);
			jQuery("#"+assetAction+"LBSAddressFlag").val("false");
			showAssetBlngFlrText(assetAction);
		}
	}
	if(pageFlowLcase === "fleetmgmtmove" && assetAction.toLowerCase() == "movetoaddress")
	{
		if(lbsAddressFlag != null && lbsAddressFlag.toLowerCase() == "true")
		{
			
			showBuildingFloorDropDOwns(addressId, assetAction, addressSection);
		}
		else if(lbsAddressFlag != null && lbsAddressFlag.toLowerCase() == "false")
		{
			
			hideAssetBuildingFloorSection(assetAction);
			showBuildingFloorText(assetAction, addressSection);
			jQuery("#"+assetAction+"LBSAddressFlag").val("false");
			showAssetBlngFlrText(assetAction);
		}
		else if(addressId != null && $.trim(addressId).length > 0)
		{
			
			showBuildingFloorDropDOwns(addressId, assetAction, addressSection);
		}
		else
		{
			
			hideAssetBuildingFloorSection(assetAction);
			showBuildingFloorText(assetAction, addressSection);
			jQuery("#"+assetAction+"LBSAddressFlag").val("false");
			showAssetBlngFlrText(assetAction);
		}
	}
}

function showBuildingFloorDropDOwns(addressId, assetAction, addressSection)
{
	var addressLevelOfDetails = $("#"+assetAction+"LevelOfDetails").val();
	jQuery("#"+addressSection+"1").hide();
	jQuery("#"+addressSection).hide();
	$("."+assetAction+"Section").find("#gridLi").hide();
	
	//alert(addressLevelOfDetails);
	hideAssetBuildingFloorSection(assetAction);
	
	if(addressLevelOfDetails.toLowerCase().indexOf("street") > -1)
	{
		showBuildingFloorText(assetAction, addressSection);
		showAssetBlngFlrText(assetAction);
	}
	else
	{
		if(assetAction === "install" && pageFlow.toLowerCase() === "fleetmgmtchange")
		{
			jQuery("#"+addressSection+"1").hide();
			jQuery("#"+addressSection).hide();
		}
		else
		{
			jQuery("#"+addressSection+"1").hide();
			jQuery("#"+addressSection).show();
		}
		
		if(addressLevelOfDetails.toLowerCase() === "floor level" 
			|| addressLevelOfDetails.toLowerCase() === "grid level")
		{
			showAssetBlngFlrDD(assetAction);
			if(assetAction.toLowerCase() === "install")
			{
				
				loadCoutrySateCitySiteBuilding(addressId, assetAction);
			}
			else
			{
				loadCoutrySateCitySiteBuildingForLOD(addressId);
			}
			
			if(addressLevelOfDetails.toLowerCase() === "grid level")
			{
				$("."+assetAction+"Section").find("#gridLi").show();
				showGrid(assetAction);
			}
		}
		else if(addressLevelOfDetails.toLowerCase() === "mix - see floor")
		{
			showAssetBlngFlrDD(assetAction);
			var flrDropDownName = "flr";
			if(assetAction.toLowerCase() === "install")
			{
				loadCoutrySateCitySiteBuilding(addressId, assetAction);
			}
			else
			{
				loadCoutrySateCitySiteBuildingForLOD(addressId);
				flrDropDownName = flrDropDownName + "m";
			}
			if(jQuery("#"+flrDropDownName+" option:selected").attr("lod") != undefined && 
			   jQuery("#"+flrDropDownName+" option:selected").attr("lod").toLowerCase().indexOf("grid") > -1)
			{
				$("."+assetAction+"Section").find("#gridLi").show();
				showGrid(assetAction);
			}
		}
		else
		{
			showBuildingFloorText(assetAction, addressSection);
			showAssetBlngFlrText(assetAction);
		}
	}
}

function hideAssetBuildingFloorSection(assetAction)
{
	
	
	if(((pageFlow.toLowerCase() !== "fleetmgmtmove" && assetAction == "install")
			|| (pageFlow.toLowerCase() === "fleetmgmtmove" && assetAction.toLowerCase() == "movetoaddress"))
			)
	{
		
		$(".assetGridLi").hide();
		
			$(".buildingDD").hide();
			$(".floorDD").hide();
			$(".zoneDD").hide();
			
			$(".buildingTT").hide();
			$(".floorTT").hide();
			$(".zoneTT").hide();
			
			$(".bldngMandat").hide();
			$(".flrMandat").hide();
			$(".zoneMandat").hide();
		
	}
}

function showGrid(assetAction)
{
	if(((pageFlow.toLowerCase() !== "fleetmgmtmove" && assetAction == "install")
			|| (pageFlow.toLowerCase() === "fleetmgmtmove" && assetAction.toLowerCase() == "movetoaddress"))
			)
	{
		
		$(".assetGridLi").show();
		
	}
}

function hideGrid(assetAction)
{
	if(((pageFlow.toLowerCase() !== "fleetmgmtmove" && assetAction == "install")
			|| (pageFlow.toLowerCase() === "fleetmgmtmove" && assetAction.toLowerCase() == "movetoaddress"))
			&& assetArr.length > 0)
	{
		for(var ind=0; ind <assetArr.length; ind++)
		{
			$("#"+assetArr[ind]+"_gridLi").hide();
		}
	}
}

function showAssetBlngFlrText(assetAction)
{
	if(((pageFlow.toLowerCase() !== "fleetmgmtmove" && assetAction == "install")
		|| (pageFlow.toLowerCase() === "fleetmgmtmove" && assetAction.toLowerCase() == "movetoaddress"))
		&& isMultiSelect != undefined && assetArr.length > 0)
	{
		/*for(var ind=0; ind <assetArr.length; ind++)
		{*/
			$(".buildingDD").hide();
			$(".floorDD").hide();
			$(".zoneDD").hide();
			
			$(".buildingTT").show();
			$(".floorTT").show();
			$(".zoneTT").show();
			
			$(".bldngMandat").hide();
			$(".flrMandat").hide();
			//$(".zoneMandat").hide();
		//}
	}
}

function showAssetBlngFlrDD(assetAction)
{
	//alert("inside showAssetBlngFlrDD");
	if(((pageFlow.toLowerCase() !== "fleetmgmtmove" && assetAction == "install")
		|| (pageFlow.toLowerCase() === "fleetmgmtmove" && assetAction.toLowerCase() == "movetoaddress"))
		&& assetArr.length > 0)
	{
		/*for(var ind=0; ind <assetArr.length; ind++)
		{*/
			$(".buildingDD").show();
			$(".floorDD").show();
			$(".zoneDD").show();
			
			$(".buildingTT").hide();
			$(".floorTT").hide();
			$(".zoneTT").hide();
			
			if(pageFlow.toLowerCase() != "decommission")
			{
				$(".bldngMandat").show();
				$(".flrMandat").show();
				//$(".zoneMandat").show();
			}
		//}
	}
}

function showBuildingFloorText(assetAction, addressSection)
{
	if(assetAction === "install" && pageFlow.toLowerCase() === "fleetmgmtchange")
	{
		jQuery("#"+addressSection+"1").hide();
		jQuery("#"+addressSection).hide();
	}
	else
	{
		jQuery("#"+addressSection+"1").show();
		jQuery("#"+addressSection).hide();
	}
	
	if(assetAction === "install")
	{
		jQuery("#physicalLocation1").val(jQuery("#bldng1").val());
		jQuery("#physicalLocation2").val(jQuery("#flr1").val());
		
		if(pageFlow.toLowerCase() != "fleetmgmtmove")
		{
			setAssetInstallBuildingFloorText();
		}
	}
	else
	{
		jQuery("#moveTophysicalLocation1").val(jQuery("#bldngm1").val());
		jQuery("#moveTophysicalLocation2").val(jQuery("#flrm1").val());
		setAssetMoveToBuildingFloorText();
	}
}

function setAssetInstallBuildingFloorText()
{
	if(assetArr.length > 0)
	{
			$(".buildingTT").val(jQuery("#bldng1").val());
			$(".floorTT").val(jQuery("#flr1").val());
			$(".zoneTT").val(jQuery("#zone1").val());
			
			/*$(".assetBuildingName").val(jQuery("#bldng1").val());
			$(".assetFloorName").val(jQuery("#flr1").val());
			$(".assetZoneName").val(jQuery("#zone1").val());*/
			
			$(".assetBuildingId").val("");
			$(".assetFloorId").val("");
			$(".assetZoneId").val("");
			
			if('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true")
			{
				for(var ind=0; ind <assetArr.length; ind++)
				{
					$("#"+assetArr[ind]+"_buildingTT").val(jQuery("#"+assetArr[ind]+"_physicalLocation1").val());
					$("#"+assetArr[ind]+"_floorTT").val(jQuery("#"+assetArr[ind]+"_physicalLocation2").val());
					$("#"+assetArr[ind]+"_zoneTT").val(jQuery("#"+assetArr[ind]+"_zoneName").val());				
				}
			}
			
			if(pageFlow.toLowerCase() === "decommission")
			{
				/*$(".buildingLbl").html(jQuery("#bldng1").val());
				$(".floorLbl").html(jQuery("#flr1").val());
				$(".zoneLbl").html(jQuery("#zone1").val());*/
			}
	}
}

function setAssetMoveToBuildingFloorText()
{
	if(isMultiSelect != undefined && assetArr.length > 0)
	{
		/*for(var ind=0; ind <assetArr.length; ind++)
		{*/
			$(".buildingTT").val(jQuery("#bldngm1").val());
			$(".floorTT").val(jQuery("#flrm1").val());
			$(".zoneTT").val(jQuery("#zonem1").val());
			
			/*$(".assetBuildingName").val(jQuery("#bldngm1").val());
			$(".assetFloorName").val(jQuery("#flrm1").val());
			$(".assetZoneName").val(jQuery("#zonem1").val());*/
			
			$(".assetBuildingId").val("");
			$(".assetFloorId").val("");
			$(".assetZoneId").val("");
			
			if('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true")
			{
				for(var ind=0; ind <assetArr.length; ind++)
				{
					$("#"+assetArr[ind]+"_buildingTT").val(jQuery("#"+assetArr[ind]+"_physicalLocation1").val());
					$("#"+assetArr[ind]+"_floorTT").val(jQuery("#"+assetArr[ind]+"_physicalLocation2").val());
					$("#"+assetArr[ind]+"_zoneTT").val(jQuery("#"+assetArr[ind]+"_zoneName").val());				
				}
			}
			if(pageFlow.toLowerCase() === "decommission")
			{
				/*$(".buildingLbl").html(jQuery("#bldngm1").val());
				$(".floorLbl").html(jQuery("#flrm1").val());
				$(".zoneLbl").html(jQuery("#zonem1").val());*/
			}
		//}
	}
}

function setBuildingFloorDDDefault()
{
	$(".buildingDD").val("");
	$(".floorDD").val("");
	$(".zoneDD").val("");
	
	$(".assetBuildingName").val("");
	$(".assetFloorName").val("");
	$(".assetZoneName").val("");
	
	$(".assetBuildingId").val("");
	$(".assetFloorId").val("");
	$(".assetZoneId").val("");
	
	if(pageFlow.toLowerCase() === "decommission")
	{
		/*$(".buildingLbl").html("");
		$(".floorLbl").html("");
		$(".zoneLbl").html("");*/
	}
}

function loadCoutrySateCitySiteBuildingForLOD(addressId)
{
	//alert("Inside loadCoutrySateCitySiteBuildingForLOD");
	urlParamsObj1.clearHtmlAfter('bldngm');
	urlParamsObj1.setDefaultHTMlAt('bldngm');
		
	urlParamsObj1=new urlParams1();
	var url=appendURLPrams1("${getAllLocationURL}&frCal=true&aid="+addressId,urlParamsObj1);
	urlParamsObj1.disableAll();
	
	urlParamsObj1.clearAssetDD("buildingDD");
	urlParamsObj1.setDefaultDD("buildingDD");
	urlParamsObj1.disableAssetDD();
	
	//setBuildingFloorDDDefault();
	$(".buildingDD").val("");
	$(".floorDD").val("");
	$(".zoneDD").val("");

	$.getJSON(url,function(response){
		urlParamsObj1.enableAll();	
		urlParamsObj1.enableAssetDD();
		
		$('#bldngm').append(response.building);
		$('#zonem').append(response.zone);
		
		/*for(var ind=0; ind <assetArr.length; ind++)
		{*/
			$(".buildingDD").append(response.building);
			$(".zoneDD").append(response.zone);
		//}
		
		selectBuildingMove();
		selectZoneMove();
	});
}

function selectBuildingMove(){
	if($("#bldngm1").val() != null && $.trim($("#bldngm1").val()).length > 0)
	{
		var urlParamsObj1=new urlParams1();
		
		var moveToAddressLevelOfDetails = $("#moveToAddressLevelOfDetails").val();
		var bldDrop=jQuery("#bldngm1").val();
		var insflgm=false;
		
		if(pageFlow.toLowerCase() === "fleetmgmtmove" && (moveToAddressLevelOfDetails.toLowerCase() === "floor level"
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1) && isMultiSelect == undefined)
		{
			var bldDropval=jQuery("#moveTophysicalLocation1h").val();
			$('<option>').val(bldDropval).text(bldDrop).appendTo('#bldngm');
		}
		
		$(function() {
			$("#bldngm option").each(function() {
		    	
		        if( $(this).prop('text') == bldDrop ) { 
		        	$(this).attr('selected','selected');
		        	insflgm=true; 
		        }	 
            });
		                          
        	if(insflgm){
            	//$('#bldngm').attr('disabled', true);
        		if(assetArr.length > 0)
        		{
        			
        			$(".buildingDD").val($("#bldngm").val());
        			//$(".assetBuildingName").val($("#bldngm option:selected").text());
        			//$(".assetBuildingId").val(jQuery("#bldngm").val());
        			//$(".buildingLbl").html($("#bldngm option:selected").text());
        			
        			if('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true")
        			{
	        			for(var ind=0; ind <assetArr.length; ind++)
	            		{
	        				//alert(jQuery("#"+assetArr[ind]+"_physicalLocation1h").val());
	        				//if(jQuery("#"+assetArr[ind]+"_physicalLocation1h").val()!=null && jQuery("#"+assetArr[ind]+"_physicalLocation1h").val().length > 0){
	        					$("#"+assetArr[ind]+"_buildingDD").val($("#"+assetArr[ind]+"_physicalLocation1h").val());
	        					$("#"+assetArr[ind]+"_physicalLocation1").val($("#"+assetArr[ind]+"_buildingDD option:selected").text());
	        				//}
	            		}
        			}
        		}
            }
		    //alert("jQuery('#bldngm option:selected').text()::"+jQuery("#bldngm option:selected").text());
		    //alert("jQuery('#moveTophysicalLocation1h').val::"+jQuery('#moveTophysicalLocation1h').val());
		    
            jQuery('#moveTophysicalLocation1h').val(jQuery("#bldngm option:selected").val());
            
            if(isMultiSelect != undefined || pageFlow.toLowerCase() === "change" || (pageFlow.toLowerCase() === "fleetmgmtmove"
              && moveToAddressLevelOfDetails.toLowerCase() === "mix - see floor"))
            {
	            $('#flrm').attr('disabled',true);
	            urlParamsObj1.disableAssetDDEle("floorDD");
	            $('#btnContinue').attr('disabled',true);
	            getSitem("buildingselect");
	            getFloorm("buildingselect");
            }
       });
       installAddressFlag=true;
	}
}

function selectFloorMove(selectedAction)
{
	var moveToAddressLevelOfDetails = $("#moveToAddressLevelOfDetails").val();
	if($("#flrm1").val() != null && $.trim($("#flrm1").val()).length > 0)
	{
		var flrflgm=false;
		
		if(pageFlow.toLowerCase() === "fleetmgmtmove" && (moveToAddressLevelOfDetails.toLowerCase() === "floor level"
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1) && isMultiSelect == undefined)
		{
			$('<option>').val(jQuery("#moveTophysicalLocation2h").val()).text(jQuery("#flrm1").val()).appendTo('#flrm');
		}
		$(function() {
			if(selectedAction != "buildingchange"){
				$("#flrm option").each(function() {
		             if( $(this).prop('text') == jQuery("#flrm1").val()) 
		             { 
		             	$(this).attr('selected','selected');
		             	flrflgm=true; 
		             }
	           	});
			}
			                 
		    if(flrflgm){
		    	if(assetArr.length > 0)
        		{
        			$(".floorDD").val($("#flrm").val());
        			//$(".assetFloorName").val($("#flrm option:selected").text());
        			//$(".assetFloorId").val(jQuery("#flrm").val());
        			//$(".floorLbl").html($("#flrm option:selected").text());
        			
        			if('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true")
        			{
	        			for(var ind=0; ind <assetArr.length; ind++)
	            		{
	        				//alert(jQuery("#"+assetArr[ind]+"_physicalLocation2h").val());
	        				//if(jQuery("#"+assetArr[ind]+"_physicalLocation2h").val()!=null && jQuery("#"+assetArr[ind]+"_physicalLocation2h").val().length > 0){
	        					$("#"+assetArr[ind]+"_floorDD").val($("#"+assetArr[ind]+"_physicalLocation2h").val());
	        					$("#"+assetArr[ind]+"_physicalLocation2").val($("#"+assetArr[ind]+"_floorDD option:selected").text());
	        				//}
	            		}
        			}
        		}
		    	if(moveToAddressLevelOfDetails.toLowerCase() === "mix - see floor" 
		    		&& $("#flrm option:selected").attr("lod") != undefined
		    		&& $("#flrm option:selected").attr("lod").toLowerCase() === "grid level")
		    	{
		    		//$(".moveToAddressSection").find("#gridLi").show();
		    		showGrid("movetoaddress");
		    	}
			}
		                 
            jQuery('#moveTophysicalLocation2h').val(jQuery("#flrm option:selected").val());
		});
		installAddressFlag=true;
	}
}

function selectSiteMove(selectedAction)
{
	var moveToAddressLevelOfDetails = $("#moveToAddressLevelOfDetails").val();
	//alert("campusm1::"+document.getElementById("campusm1").value);
	if($("#campusm1").val() != null && $.trim($("#campusm1").val()).length > 0)
	{
		if(pageFlow.toLowerCase() === "fleetmgmtmove" && (moveToAddressLevelOfDetails.toLowerCase() === "floor level"
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1) && isMultiSelect == undefined)
		{
			$('<option>').val(jQuery("#campusmh").val()).text(jQuery("#campusm1").val()).appendTo('#campusm');
		}
		
		$(function() {
			if(selectedAction != "buildingchange"){
				$("#campusm option").each(function() {
				    if( $(this).prop('text') == jQuery("#campusm1").val() ) 
			        { 
			        	$(this).attr('selected','selected'); 
			        }
			    });
			}
		                 
       		jQuery('#campusmh').val(jQuery("#campusm option:selected").val());
        });
		installAddressFlag=true;
	}
}

function selectZoneMove()
{
	var moveToAddressLevelOfDetails = $("#moveToAddressLevelOfDetails").val();
	//alert("zonem1::"+document.getElementById("zonem1").value);
	if($("#zonem1").val() != null && $.trim($("#zonem1").val()).length > 0)
	{
		var zoneDrop=jQuery("#zonem1").val();
		var zoneDropval=jQuery("#zonemh").val();
		var zonemflg=false;
		
		if(pageFlow.toLowerCase() === "fleetmgmtmove" && (moveToAddressLevelOfDetails.toLowerCase() === "floor level"
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1) && isMultiSelect == undefined)
		{
			$('<option>').val(zoneDropval).text(zoneDrop).appendTo('#zonem');
		}
		
        $(function() {
	    	$("#zonem option").each(function() {
	        	if( $(this).prop('text') == zoneDrop ) 
	        	{ 
	        		$(this).attr('selected','selected');
	        		zonemflg=true; 
	        	}
	        });
	        if(zonemflg){
	        	//$('#zonem').attr('disabled', true);
		        if(assetArr.length > 0)
	    		{
		        	$(".zoneDD").val($("#zonem").val());
	    			//$(".assetZoneName").val($("#zonem option:selected").text());
        			//$(".assetZoneId").val(jQuery("#zonem").val());
        			//$(".zoneLbl").html($("#zonem option:selected").text());
	    			
		        	if('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true")
        			{
	        			for(var ind=0; ind <assetArr.length; ind++)
	            		{
	        				//if(jQuery("#"+assetArr[ind]+"_zoneh").val()!=null && jQuery("#"+assetArr[ind]+"_zoneh").val().length > 0){
	        					$("#"+assetArr[ind]+"_zoneDD").val($("#"+assetArr[ind]+"_zoneh").val());
	        					$("#"+assetArr[ind]+"_zoneName").val($("#"+assetArr[ind]+"_zoneDD option:selected").text());
	        				//}
	            		}
        			}
	    		}
	        }
	    });
	                     
	    jQuery('#zonemh').val(jQuery("#zonem option:selected").val());
	}
	else
	{
		if($("#flrm1").val()!="" && $("#bldngm1").val()!="")
	    {
			$('#zonem').val("");
			//$('#zonem').text("");
			
			if(isMultiSelect != undefined && assetArr.length > 0)
    		{
    			/*for(var ind=0; ind <assetArr.length; ind++)
    			{*/
    				$(".zoneDD").val("");
    				//$(".zoneDD").text("");
    				
    				$(".assetZoneName").val("");
    				$(".assetZoneId").val("");
    				//$(".zoneLbl").html("");
    			//}
    		}
			
			if(isMultiSelect == undefined)
				$('#zonem').attr('disabled', true);
		}
	}
	installAddressFlag=true;
}

/** Changes for LBS 1.5 - Add Multi Asset address**/

function showBuildingFloorZoneForMultiAdd(index)
{
	
	
var assetAction='install';
var assetIndex=index;
var lbsAddressFlag=$("#installLBSAddressFlag").val();
var addressId=$("#installAddressId").val();
hideAssetBuildingFloorSectionForAdd(assetAction);
$("#"+assetIndex+"_gridLi").hide();

if((lbsAddressFlag != null && lbsAddressFlag.toLowerCase() == "true")
		|| (addressId != null && $.trim(addressId).length > 0))
	{
	
		var addressLevelOfDetails=$("#installLevelOfDetails").val();
		if(addressLevelOfDetails.toLowerCase() ==="street level")
		{
			setBuildingFloorTextAndValue(assetIndex);
			showAssetBlngFlrText(assetAction);
		}
		else if(addressLevelOfDetails.toLowerCase() === "floor level" 
			|| addressLevelOfDetails.toLowerCase() === "grid level")
			{
				showAssetBlngFlrDD(assetAction);
				setBuildingFloorZoneDDValue(assetIndex);
				if(addressLevelOfDetails.toLowerCase() === "grid level")
				{
					bindMapPopup(assetIndex);
					$("#"+assetIndex+"_gridLi").show();
				}
			}
		else if(addressLevelOfDetails.toLowerCase() === "mix - see floor")
		{
			showAssetBlngFlrDD(assetAction);
			setBuildingFloorZoneDDValue(assetIndex);
			var flrDropDownName = "flr";
			if(jQuery("#"+flrDropDownName+" option:selected").attr("lod") != undefined && 
					   jQuery("#"+flrDropDownName+" option:selected").attr("lod").toLowerCase().indexOf("grid") > -1)
					{
						bindMapPopup(assetIndex);
						$("#"+assetIndex+"_gridLi").show();
					}
			
		}
		else
			{
			setBuildingFloorTextAndValue(assetIndex);
			showAssetBlngFlrText(assetAction);
			}
	}
else
	{
	setBuildingFloorTextAndValue(assetIndex);
	showAssetBlngFlrText(assetAction);
	}

}
function setBuildingFloorTextAndValue(index)
{
	
	
$("#"+index+"_buildingTT").val($("#bldng1").val());
$("#"+index+"_floorTT").val($("#flr1").val());
$("#"+index+"_zoneTT").val($("#zone1").val());

$("#"+index+"_physicalLocation1h").val("");
$("#"+index+"_physicalLocation2h").val("");
$("#"+index+"_physicalLocation3h").val("");
}
function setBuildingFloorZoneDDValue(index)
{
	
	$("#"+index+"_buildingDD").html($("#bldng").html());
	$("#"+index+"_floorDD").html($("#flr").html());
	$("#"+index+"_zoneDD").html($("#zone").html());
	
	
}

function hideAssetBuildingFloorSectionForAdd(assetAction)
{
	
	if(((pageFlow.toLowerCase() !== "fleetmgmtmove" && assetAction == "install")
			|| (pageFlow.toLowerCase() === "fleetmgmtmove" && assetAction.toLowerCase() == "movetoaddress"))
			)
	{
		
		//$(".assetGridLi").hide();
		
			$(".buildingDD").hide();
			$(".floorDD").hide();
			$(".zoneDD").hide();
			
			$(".buildingTT").hide();
			$(".floorTT").hide();
			$(".zoneTT").hide();
			
			$(".bldngMandat").hide();
			$(".flrMandat").hide();
			$(".zoneMandat").hide();
		
	}
}

function bindMapPopup(assetInd)
{
	$("#"+assetInd+"_assetGridLink").click(function(){
		//alert("#"+assetArr[ind]+"_buildingDD");
		//alert($("#"+assetArr[ind]+"_buildingDD").val());
		//alert($("#"+assetArr[ind]+"_floorDD").val());

		var astId = $(this).attr("assetIndicator");
		openPopupMap(astId,'${mdmId}','${mdmLevel}','${formPost}','${emailId}',$("#"+astId+"_buildingDD").val(),$("#"+astId+"_floorDD").val());
	});
}