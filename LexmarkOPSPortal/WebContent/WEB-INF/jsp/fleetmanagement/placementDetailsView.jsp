<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<portlet:resourceURL var="placementChangeUrl" id="changePlacement"></portlet:resourceURL>
<portlet:resourceURL var="placementAddUrl" id="addPlacement"></portlet:resourceURL>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="placementDetailsList" style="display: block;">
	<div id="placementHeader" style="display: block;">
		<b><spring:message code='lbs.label.placement' /></b><span class="floatR padding5"><img id="plusImage" src="/LexmarkOPSPortal/images/plus.gif"/><span id="downArrowImage" class="arrow_icon arrow_down"></span></span></span>
	</div>
	<div id="placementContent" style="display: none"></div>
</div>
	
	<div id="popupPlacementDiv" style="display:none">
		<br>
			<div class="plc-error">
				<ul class="serviceError">
				</ul>
			</div>
			<div class="popup_Input_Row"><label><span class="req">*</span>Name :</label><input type="text" name="name" id="plc-name"></div>
			<div class="popup_Input_Row"><label><span class="req">*</span>Model :</label><input type="text" name="model" id="plc-model"></div>
			<div class="popup_Input_Row"><label>IP Address : </label><input type="text" name="address" id="plc-ip"></div><br>
			<div class="buttonContainer">
				<button name="cancel" class="button_cancel" id="plc-cancel">CANCEL</button>
				<button name="submit" class="button" id="plc-ok">OK</button> 
			</div>
	</div>
	
	


<script id="list-placement-template" type="text/x-handlebars-template">
		


        {{#placements}}
        
		<div class="deviceDetailsDiv" id="placement{{id}}" onclick="highlightAsset('{{id}}');">
			<div class="deviceInfoDiv">
				<div class="printerImage">
						<img id="printImageId{{id}}" src="<html:imagesPath/>loading-icon.gif" alt="printer img" />
				</div>
				<div class="deviceDetailedInfo"><div class="deviceID">
					{{modelNumber}}
				</div>
				<div class="bookmark">
					<img src="<html:imagesPath/>transparent.png" class="bookmarkFavouriteImage ui_icon_sprite {{{generateBkmrkClass id }}}" id="starIMG_{{id}}" alt="bookmark-favourite" onclick="bookmarkDevice('{{id}}',this)"/>
					<img src="<html:imagesPath/>loading-icon.gif" id="loadigFavorite{{id}}" style="display: none;"/>
					<span id="bookmarkMsg{{id}}">{{{generateBkmrkMsg}}}</span>
				</div>
				Placement Name :<span class="deviceSerialNo" ><a style="cursor:pointer" onClick="highlightAsset('{{id}}');">{{serialNumber}}</a></span><br/>
				<spring:message code='lbs.label.ipaddress'/> :<span class="deviceIP" >{{ipAddress}}</span><br/>
				<spring:message code='lbs.label.customerdevicetag'/>:<span class="deviceTag">{{customerDeviceTag}}</span>
				<div style='margin-top:10px'>
					<a class="createRequests floatL" onClick="showCreateRequestPlacement('{{id}}')" id="createRequests{{id}}" style="cursor: pointer;" ><spring:message code='lbs.label.createrequest'/></a>
					<a class="editPlacement floatL" onClick="showEditPlacementPop('{{id}}')" id="editPlacement{{id}}" style="cursor: pointer;" ><spring:message code='lbs.label.editplacement'/></a>
					<a style="cursor: pointer;" onClick="showLocationHistoryById('{{id}}')">Location History</a>
				</div>
			</div>
			<div class="createRequestMenu" id="createRequestMenu{{id}}" style="display:none;">
					<span class="popup_arrow">
						<span class="popup_arrow-inner"></span>
					</span>
									<c:if test="${fleetMgmtForm.showHardware}">
										<div><a style="cursor: pointer;" onclick="checkShowAccount('{{id}}')">Hw Order</a></div>
									</c:if>
									<c:if test="${fleetMgmtForm.showChangeMgmt}">
										<div><a style="cursor: pointer;" onclick="checkForwardAddAssetJSP('{{id}}')">Install</a></div>	
									</c:if>					
			</div>

			<div class="editPlacementMenu" id="editPlacementMenu{{id}}" style="display:none;">
					<span class="popup_arrow">
						<span class="popup_arrow-inner"></span>
					</span>
									<div><a style="cursor: pointer;" onclick="placementObj.decomPlacement('{{id}}')">Remove</a></div>
									<div><a style="cursor: pointer;" onclick="placementObj.changePlacement('{{id}}')">Move</a></div>									
			</div>
	</div>
	{{#if srArray}}
	<div class="deviceRequests">
		<div class="openRequestsDiv">
			<div class="openRequestsHeader"><spring:message code='lbs.label.openrequests'/> :
			</div>
			<div class="openRequestsContent">
					<table>
					{{{showHWOrder srArray id}}}
					</table>
			</div>
		</div>				
	</div>		
	{{/if}}
	<div class="clearBoth"></div>
	</div>
{{/placements}}					
</script>

<script>

var placement_Msgs={
		placementError1:"Please enter Placement Name",
		placementError2:"Please enter Placement Model",
		placementError3:"Please enter Valid IP Address",
		hwOrder:"HW Order",
		install:"Install"
}


var popupVariable = $( "#popupPlacementDiv" ).dialog({ 
					height: 550,
					width: 800,
					title:'Placement Details',
					modal: true,
					position: ['center','top'],
					autoOpen:false,
					open:function(){
						$('.serviceError').empty();
					},
				});

	$("#deviceHeader").click(function() {
		$("#deviceContent").toggle();
	});
	
	$("#plusImage").click(function(){
		$("#popupPlacementDiv :input").each(function(){
			$(this).val('');
			});
		popupVariable.dialog('open');
	});
	function placementPopupClose(){
		popupVariable.dialog('close');
	}
	
	$("#downArrowImage").click(function(){
		$("#placementContent").toggle();
		if($("#placementContent").attr("style")=="display: none;"){
			$("#placementDetailsList").css("height","10%");
		}
		else{
			if($("#deviceDetailsList").attr("style")=="display: none;")
				$("#placementDetailsList").css("height","100%");
			else
				$("#placementDetailsList").css("height","50%");
		}
		adjustLeftNav();
	});
	
	$('#popupPlacementDiv #plc-cancel').click(function(){
		popupVariable.dialog('close');
		
	});
	$('#popupPlacementDiv #plc-ok').click(function(){
		placementObj.addPlacement();
	});
	function adjustLeftNav(){
			if($("#left-nav-Div").css('display')!='none')
				$("#deviceStatusContent").width($('#firstBlock').width());
			if($("#placementContent").css('display')=='none'){
				$("#downArrowImage").addClass('arrow_down');
				$("#downArrowImage").removeClass('arrow_up');
			}
			else{
				$("#downArrowImage").removeClass('arrow_down');
				$("#downArrowImage").addClass('arrow_up');
			}
			var leftNavHeight = $("#uam-mps-view-map-iframe").height();
			var deviceStatusContentHeight = $("#deviceStatusContent").height();
			$("#leftNavDevices #firstBlock").css("margin-top",deviceStatusContentHeight+"px");
			var firstBlockHeight = $("#leftNavDevices #firstBlock").height();
			var placementDetailsListNone = ($('#placementDetailsList').css('display')=="none");
			var deviceDetailsListNone = ($("#deviceDetailsList").css('display')=="none");
			var placementContentNone = ($('#placementContent').css('display')=="none");
			var remainingLeftNav = leftNavHeight - deviceStatusContentHeight - firstBlockHeight - 50;
			$('#leftNavDevices #secondBlock').css('height',remainingLeftNav+'px');
			if(!placementDetailsListNone){
				remainingLeftNav = remainingLeftNav - 35;
				if(!placementContentNone){
					if(deviceDetailsListNone){
						$('#placementContent').css({'height':(remainingLeftNav + 35)+'px','max-height':(remainingLeftNav + 35)+'px'});
						return;
					}
					$('#placementContent').css('max-height',(remainingLeftNav/2)+'px');
					remainingLeftNav = remainingLeftNav - $('#placementContent').height();
				}
			}
			$('#deviceContent').css('height',remainingLeftNav+'px');
		}
	function showCreateRequestPlacement(deviceId){
		$('#createRequestMenu'+deviceId).show().position({
			  my: "right top",
			  at: "right bottom",
			  of: "#createRequests"+deviceId,
			  collision:"none"
			});
		var pcTotal = $('#placementContent').offset().top+$("#placementContent").height();
		var crTotal = $("#createRequestMenu"+deviceId).offset().top+$("#createRequestMenu"+deviceId).height();
		if(crTotal>pcTotal)
			$('#placementContent').animate({scrollTop: $('#placementContent').scrollTop()+(crTotal-pcTotal)+15}, 'slow');
		$('#createRequestMenu'+deviceId+',#placement'+deviceId).mouseleave(function(){
			$('#createRequestMenu'+deviceId).hide();
		});
	}
	
	function showEditPlacementPop(deviceId){
		$('#editPlacementMenu'+deviceId).show().position({
			  my: "right top",
			  at: "right bottom",
			  of: "#editPlacement"+deviceId,
			  collision:"none"
			});
			var pcTotal = $('#placementContent').offset().top+$("#placementContent").height();
			var epTotal = $("#editPlacementMenu"+deviceId).offset().top+$("#editPlacementMenu"+deviceId).height();
			if(epTotal>pcTotal)
				$('#placementContent').animate({scrollTop: $('#placementContent').scrollTop()+(epTotal-pcTotal)+15}, 'slow');
		$('#editPlacementMenu'+deviceId+',#placement'+deviceId).mouseleave(function(){
			$('#editPlacementMenu'+deviceId).hide();
		});
	}
	
	function doPLacementChange(lbsBuildingDetailsTo){
		showPopupMessagesOPS("Are you sure you want to move this device?",function(b){
			var location=lbsBuildingDetailsTo[0];
			if (b){
				showOverlay();
				$.ajax({
					url:"${placementChangeUrl}&t="+new Date().getTime()+"&placementId="+$('#lbs_placementId').val(),
					type:'POST',
					dataType:"json",
					data:location,
					success: function(response){
						if(response.message != "success"){
							jAlert("Falure in Placement Move", "");
						}else{
							jAlert("Placement Moved Successfully", "");
						}							
						$('#cancelMoveRequest').click();
						hideOverlay();
						},
				  failure: function(results){
					  
				  }
				});
			}
				});
	}
	
	function doPLacementAdd(lbsBuildingDetailsTo){
		showPopupMessagesOPS("Are you sure you want to Add this placement?",function(b){
			var location=lbsBuildingDetailsTo[0];
			var details={
					"plcName":$('#plc-name').val(),
					"plcModel":$('#plc-model').val(),
					"plcIp":$('#plc-ip').val(),
					"accountId":$('#lbs_accountId').val()
					
			};
			for(var params in location){
				details[params]=location[params];
			}
			for(var attr in location.attributes){
				var val =location.attributes[attr]["storeFrontName"];
				if(val != null){
					details["storeFrontName"]=val;
				}
			}
			
			if (b){
				showOverlay();
				$.ajax({
					url:"${placementAddUrl}&t="+new Date().getTime(),
					type:'POST',
					dataType:"json",
					data:details,
					success: function(response){
						if(response.message != "success"){
							jAlert("Failure in Add Placement", "");
						}else{
							jAlert("Placement Added Successfully", "");
							$( "#popup_ok" ).click(function() {
								 zoomProcessor.refreshMap=true;
								 zoomProcessor.postZoom();
								});
						}							
						$('#cancelButton').click();
						hideOverlay();
						},
				  failure: function(results){
					  
				  }
				});
			}
				});
	}
	function showHistoryPlacement(id){
		var obj={	   "action": "showHistory",
			   "item": "asset",
			   "info": {
				   "id": id 
			   }
			      
		}
		lbs.postMessage(obj);
	}
</script>