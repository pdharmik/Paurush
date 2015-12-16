/** Changes for LBS 1.5 - Level of Details checking in Change Asset and Move Asset pages **/
function showBuildingFloorZone(assetAction)
{
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
		if(lbsAddressFlag != null && lbsAddressFlag.toLowerCase() == "true"
			&& addressId != null && $.trim(addressId).length > 0)
		{
			showBuildingFloorDropDOwns(addressId, assetAction, addressSection);
		}
		else
		{
			showBuildingFloorText(assetAction, addressSection);
			jQuery("#"+assetAction+"LBSAddressFlag").val("false");
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
			showBuildingFloorText(assetAction, addressSection);
			jQuery("#"+assetAction+"LBSAddressFlag").val("false");
		}
		else if(addressId != null && $.trim(addressId).length > 0)
		{
			showBuildingFloorDropDOwns(addressId, assetAction, addressSection);
		}
		else
		{
			showBuildingFloorText(assetAction, addressSection);
			jQuery("#"+assetAction+"LBSAddressFlag").val("false");
		}
	}
}

function showBuildingFloorDropDOwns(addressId, assetAction, addressSection)
{
	var addressLevelOfDetails = $("#"+assetAction+"LevelOfDetails").val();
	
	jQuery("#"+addressSection+"1").hide();
	jQuery("#"+addressSection).hide();
	$("."+assetAction+"Section").find("#gridLi").hide();
	
	if(addressLevelOfDetails.toLowerCase().indexOf("street") > -1)
	{
		showBuildingFloorText(assetAction, addressSection);
	}
	else
	{
		jQuery("#"+addressSection+"1").hide();
		jQuery("#"+addressSection).show();
		
		if(addressLevelOfDetails.toLowerCase() === "floor level" 
			|| addressLevelOfDetails.toLowerCase() === "grid level")
		{
			if(assetAction.toLowerCase() != "install" && 
				pageFlow === "fleetMgmtMove" && $.trim(jQuery("#bldngm1").val()).length > 0
			   && $.trim(jQuery("#flrm1").val()).length > 0)
			{
				selectBuildingMove();
				selectFloorMove();
				selectZoneMove();
				selectSiteMove();
			}
			else if(assetAction.toLowerCase() === "install")
			{
				loadCoutrySateCitySiteBuilding(addressId);
			}
			else
			{
				loadCoutrySateCitySiteBuildingForLOD(addressId);
			}
			
			if(addressLevelOfDetails.toLowerCase() === "grid level")
			{
				if(curURL.indexOf('/fleet-management') != -1 && assetAction == "install")
				{
					//$("."+assetAction+"Section").find("#gridLi").hide();
					
					if(pageFlow.toLowerCase() === "addone")
					{
						$("."+assetAction+"Section").find("#gridLi").show();
					}
					else
					{
						$("."+assetAction+"Section").find("#gridLi").show();
						$("."+assetAction+"Section").find("#installedXYLblDiv").hide();
					}
				}
				else
					$("."+assetAction+"Section").find("#gridLi").show();
			}
		}
		else if(addressLevelOfDetails.toLowerCase() === "mix - see floor")
		{
			var flrDropDownName = "flr";
			if(assetAction.toLowerCase() === "install")
			{
				loadCoutrySateCitySiteBuilding(addressId);
			}
			else
			{
				loadCoutrySateCitySiteBuildingForLOD(addressId);
				flrDropDownName = flrDropDownName + "m";
			}
			if(jQuery("#"+flrDropDownName+" option:selected").attr("lod") != undefined && 
			   jQuery("#"+flrDropDownName+" option:selected").attr("lod").toLowerCase().indexOf("grid") > -1)
			{
				if(curURL.indexOf('/fleet-management') != -1 && assetAction == "install")
				{
					//$("."+assetAction+"Section").find("#gridLi").hide();
					
					if(pageFlow.toLowerCase() === "addone")
					{
						$("."+assetAction+"Section").find("#gridLi").show();
					}
					else
					{
						$("."+assetAction+"Section").find("#gridLi").show();
						$("."+assetAction+"Section").find("#installedXYLblDiv").hide();
					}
				}
				else
					$("."+assetAction+"Section").find("#gridLi").show();
			}
		}
		else
		{
			showBuildingFloorText(assetAction, addressSection);
		}
	}
}

function showBuildingFloorText(assetAction, addressSection)
{
	jQuery("#"+addressSection+"1").show();
	jQuery("#"+addressSection).hide();
	
	if(assetAction === "install")
	{
		jQuery("#physicalLocation1").val(jQuery("#bldng1").val());
		jQuery("#physicalLocation2").val(jQuery("#flr1").val());
	}
	else
	{
		jQuery("#moveTophysicalLocation1").val(jQuery("#bldngm1").val());
		jQuery("#moveTophysicalLocation2").val(jQuery("#flrm1").val());
	}
}

function loadCoutrySateCitySiteBuildingForLOD(addressId)
{
	urlParamsObj1.clearHtmlAfter('bldngm');
	urlParamsObj1.setDefaultHTMlAt('bldngm');
		
	urlParamsObj1=new urlParams1();
	var url=appendURLPrams1("${getAllLocationURL}&frCal=true&aid="+addressId,urlParamsObj1);
	urlParamsObj1.disableAll();

	$.getJSON(url,function(response){
		urlParamsObj1.enableAll();	
			
		$('#bldngm').append(response.building);
		$('#zonem').append(response.zone);
		selectBuildingMove();
		selectZoneMove();
	});
}

function selectBuildingMove(){
	if($("#bldngm1").val() != null && $.trim($("#bldngm1").val()).length > 0)
	{
		var moveToAddressLevelOfDetails = $("#moveToAddressLevelOfDetails").val();
		var bldDrop=jQuery("#bldngm1").val();
		var insflgm=false;
		
		if(pageFlow.toLowerCase() === "fleetmgmtmove" && (moveToAddressLevelOfDetails.toLowerCase() === "floor level"
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1))
		{
			var bldDropval=jQuery("#moveTophysicalLocation1h").val();
			$('<option>').val(bldDropval).text(bldDrop).appendTo('#bldngm');
		}
		
		$(function() {
			$("#bldngm option").each(function() {
		    	//alert("$(this).prop('text'):::"+$(this).prop('text'));
		        if( $(this).prop('text') == bldDrop ) { 
		        	$(this).attr('selected','selected');
		        	insflgm=true; 
		        }	 
            });
		                          
        	if(insflgm){
            	$('#bldngm').attr('disabled', true);
            }
		    //alert("jQuery('#bldngm option:selected').text()::"+jQuery("#bldngm option:selected").text());
		    //alert("jQuery('#moveTophysicalLocation1h').val::"+jQuery('#moveTophysicalLocation1h').val());
		    
            jQuery('#moveTophysicalLocation1h').val(jQuery("#bldngm option:selected").val());
            
            if(pageFlow.toLowerCase() === "change" || (pageFlow.toLowerCase() === "fleetmgmtmove"
              && moveToAddressLevelOfDetails.toLowerCase() === "mix - see floor"))
            {
	            $('#flrm').attr('disabled',true);
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
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1))
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
			 	$('#flrm').attr('disabled', true);
			 	
			 	if(moveToAddressLevelOfDetails.toLowerCase() === "mix - see floor" 
		    		&& $("#flrm option:selected").attr("lod") != undefined
		    		&& $("#flrm option:selected").attr("lod").toLowerCase() === "grid level")
		    	{
		    		$(".moveToAddressSection").find("#gridLi").show();		    		
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
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1))
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
		  || moveToAddressLevelOfDetails.toLowerCase().indexOf("grid") > -1))
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
	        	$('#zonem').attr('disabled', true);
	        }
	    });
	                     
	    jQuery('#zonemh').val(jQuery("#zonem option:selected").val());
	}
	else
	{
		if($("#flrm1").val()!="" && $("#bldngm1").val()!="")
	    {
			$('#zonem').val("");
			$('#zonem').text("");
			$('#zonem').attr('disabled', true);
		}
	}
	installAddressFlag=true;
}