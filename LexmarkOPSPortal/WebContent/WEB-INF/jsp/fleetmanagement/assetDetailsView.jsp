<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<portlet:resourceURL var="favoriteURL" id="updateUserFavoriteAsset"></portlet:resourceURL>
<portlet:resourceURL var="assetInfoURL" id="getAssetInformation"></portlet:resourceURL>
<portlet:resourceURL var="directMoveUrl" id="getDirectMoveUrl"></portlet:resourceURL>


<div id="leftNavDevices">
			
			<div id="firstBlock">
				<div id="installedAdd">
					<u><b><spring:message code='lbs.label.installedaddress'/>:</b></u><br />	
					<div id="addressDetails"></div>				
				</div>
				<%--Changes LBS 1.5 --%>
				<select id="multi-select-map" class="floatR" onChange="enableMultiSelect(this)">
					<option value="">MultiSelect</option>
					<option value="move">Move</option>
					<option value="change">Change</option>
					<option value="decom">Decommision</option>
					<option value="updateMeter">Update Meter Reads</option>			
				</select>
				<%--End Changes LBS 1.5 --%>
				<div id="totalDiv">
					<b><spring:message code='lbs.label.building'/></b> : <span id="building_assetDetails"></span><br />
					<b><spring:message code='lbs.label.floor'/></b> : <span id="floor_assetDetails"></span><br />
					<b><spring:message code='lbs.label.totaldevicesOnFloor'/></b> : <span id="totalDevicesOnFloor_assetDetails"></span><br />
					<b>Placements</b> : <span id="totalOnFloor_placement"></span><br />
					<b><spring:message code='lbs.label.lod'/></b> : <span id="totalDevicesOnFloor_assetDetails_addressLOD"></span><br />
					<b><spring:message code='lbs.label.buildingType'/></b>:<span id="lbs_buildingType"></span><br />
					<span>_________________</span><br />
					<b><spring:message code='lbs.label.totaldevices'/></b> : <span id="totalDevices_assetDetails"></span><br />
					<b>Total Placements</b> : <span id="total_placement"></span>
				</div>
				<div id="deviceDescriptionDetails" class="clearBoth">
					<div id="alertsSuppliesStatusDescription" class="statusDescriptionBlock">
						<label><b>Supplies Alerts</b></label>
						<div id="alertsSuppliesStatusDescriptionContent"></div>
					</div>
					<div id="alertsDeviceStatusDescription" class="statusDescriptionBlock">
						<label><b>Device Alerts</b></label>
						<div id="alertsDeviceStatusDescriptionContent"></div>
					</div>
					<div id="utilizationStatusDescription"  class="statusDescriptionBlock">
						<label><b>Utilization</b></label>
						<div id="utilizationStatusDescriptionContent"></div>
					</div>
					<div id="reportingStatusDescription"  class="statusDescriptionBlock">
						<label><b>Reporting Status</b></label>
						<div id="reportingStatusDescriptionContent"></div>
					</div>
					<div id="expiredStatusDescription"  class="statusDescriptionBlock">
						<label><b>Terms</b></label>
						<div id="expiredStatusDescriptionContent"></div>
					</div>	
			</div>
			</div>
			<div id="secondBlock">
				<%-- LBS 1.5 Changes start --%>
				<jsp:include page="placementDetailsView.jsp"></jsp:include>
				<%-- LBS 1.5 Changes ENDS --%>
				<%-- <div id="deviceDetailsList">
				<div id="deviceHeader"><b><spring:message code='lbs.label.device'/></b><span class="floatR padding5"><img src="/LexmarkOPSPortal/images/plus.gif" style="cursor:pointer" onclick="javascript:forwardToAddAssetJSP('addone');" /></span></div>
					<div id="deviceContent">
						
					</div>
				</div> --%>
				<%-- LBS 1.5 Changes ENDS --%>
				<div id="deviceDetailsList">
					<div id="deviceHeader">
						<b><spring:message code='lbs.label.device'/></b>
						<span class="floatR padding5">
							<!-- <img id="addDevice_PlusImage" src="/LexmarkOPSPortal/images/plus.gif" style="cursor:pointer" onclick="javascript:forwardToAddAssetJSP('addone');" /> -->
							<img id="addDevice_PlusImage" src="/LexmarkOPSPortal/images/plus.gif" style="cursor:pointer" onclick="openAddDeviceList();" />
						</span>
					</div>
					<div style="display: none;" id="addDevice_List"> 
						<span class="popup_arrow"> <span class="popup_arrow-inner"></span> </span>
						<div onclick="javascript:forwardToAddAssetJSP('addone','');">Add Asset</div> 
						<div onclick="javascript:forwardToAddAssetJSP('addMultiple','');">Add Multiple Assets</div> 
					</div>
					<div id="deviceContent">
						
					</div>
				</div>
				
			
			</div>
		</div>
		<div id="mapWithPendingMove" style="display: none;">
        <div class="blockHeader">
            <h2 style="height:20px ;margin: 0px;"><spring:message code='lbs.label.pendingmoveview'/></h2> 
        </div> 
               <div class="leftNavDevicesStyle">                       
                       <div id="secondBlock1">                           
                           <div id="deviceDetailsList">
                             
                               <div id="deviceContent1">
                                       <div class="deviceDetailsDiv1">
                                               <div class="deviceInfoDiv">                                                      
                                                       <div class="printerImage"><img id="img_pendingMove" src="<html:imagesPath/>loading-icon.gif"  alt="printer img" /></div>
                                                               <div class="deviceDetailedInfo">
                                                                       <div class="deviceID" id="deviceId_pendingMove"></div>
                                                                       <spring:message code='lbs.label.serialno'/>:<span class="deviceSerialNo" id="deviceSerialNo_pendingMove"></span><br/>
                                                                      <spring:message code='lbs.label.ipaddress'/>:<span class="deviceIP" id="deviceIP_pendingMove"></span><br/>
                                                                      <spring:message code='lbs.label.customerdevicetag'/>:<span class="deviceTag" id="deviceTag_pendingMove"></span>
                                                               </div>
                                                       </div>
                                                       <div id="moveAddressDetails">
                                                               <div class="LBS_installedAddress">
                                                                       <span class="deviceID"><b><spring:message code='lbs.label.installedaddress'/>:</b></span><br/><br/>
                                                                     <div id="addressDetailsMoveFrom"></div>
                                                                     <b><spring:message code='lbs.label.building'/></b> : <span id="building_assetDetails_moveFrom"></span><br />
																	 <b><spring:message code='lbs.label.floor'/></b> : <span id="floor_assetDetails_moveFrom"></span><br />
                                                               </div>
                                                               <div id="LBS_moveToAddress">
                                                                       <span class="deviceID"><b><spring:message code='lbs.label.movetoaddress'/>:</b></span><br/><br/>
                                                                       <div id="addressDetailsMoveTo"></div>
                                                                       <b><spring:message code='lbs.label.building'/></b> : <span id="building_assetDetails_moveTo"></span><br />
																		<b><spring:message code='lbs.label.floor'/></b> : <span id="floor_assetDetails_moveTo"></span><br />
                                                               </div>
                                                       </div>
                                       </div>
                                    </div>
                       </div>    
                      </div>
                      <button id="closePendingMove" class="buttonBlue" style="float: right;"><spring:message code='lbs.label.backtoassetlist'/></button>
               </div>
               
               
             
       </div>
       
     
       <div id="mapWithLocationhistory" style="display: none;">
        <div class="blockHeader">
            <h2 style="height:20px ;margin: 0px;"><spring:message code='lbs.label.locationHistory'/></h2> 
        </div> 
               <div class="leftNavDevicesStyle">                       
                       <div id="secondBlock1">                           
                           <div id="deviceDetailsList">
                             
                               <div id="deviceContent1">
                                       <div class="deviceDetailsDiv1">
                                               <div class="deviceInfoDiv">                                                      
                                                       <div class="printerImage"><img id="img_locHistry" src="<html:imagesPath/>loading-icon.gif"  alt="printer img" /></div>
                                                               <div class="deviceDetailedInfo">
                                                                       <div class="deviceID" id="deviceId_locHistry"></div>
                                                                       <spring:message code='lbs.label.serialno'/>:<span class="deviceSerialNo" id="deviceSerialNo_locHistry"></span><br/>
                                                                      <spring:message code='lbs.label.ipaddress'/>:<span class="deviceIP" id="deviceIP_locHistry"></span><br/>
                                                                      <spring:message code='lbs.label.customerdevicetag'/>:<span class="deviceTag" id="deviceTag_locHistry"></span>
                                                               </div>
                                                       </div>
                                                       <div id="moveAddressDetails">
                                                               <div class="LBS_installedAddress_locHistry">
                                                                       <span class="deviceID"><b><spring:message code='lbs.label.installedaddress'/>:</b></span><br/><br/>
                                                                     <div id="addressDetails_locHistry"></div>
                                                                     <b><spring:message code='lbs.label.building'/></b> : <span id="building_assetDetails_locHistry"></span><br />
																	 <b><spring:message code='lbs.label.floor'/></b> : <span id="floor_assetDetails_locHistry"></span><br />
																	 <b><spring:message code='lbs.label.lod'/></b> : <span id="locHistory_addressLOD"></span><br />
                                                               </div>
                                                               
                                                       </div>
                                       </div>
                                    </div>
                       </div>    
                      </div>
                      <button id="closelocation_history" class="buttonBlue" style="float: right;"><spring:message code='lbs.label.backtoassetlist'/></button>
               </div>
               
               
             
       </div>	
		<%-- Changes LBS 1.5 --%>
		<div id="moveMultiAssetLeftNav" style="display:none;height:600px;">
			<div  class="firstBlock" style="padding: 5px 0 5px 5px;height: 130px;border:1px solid #c9c9c1;">
			<u>
				<b id="multiAssetFlowHeaderMove" class="noDisplay">Move From:</b>
				<b id="multiAssetFlowHeaderChange" class="noDisplay"><spring:message code='requestInfo.heading.currentInstallAdd'/></b>
				<b id="multiAssetFlowHeaderDecomm" class="noDisplay"><spring:message code='changemanagement.deleteAsset.heading.pickupAddress'/></b>
			</u>
			<div id="move-from-details"></div>		
			</div>
			<div class="secondBlock" style="height:450px;position:relative;">
				<div class="deviceHeader"><b>Devices Selected (<span id="no-of-asset">0</span>)</b></div>
				<div style="overflow: auto;">
					<table id="multi-select-move-table">
						<thead>
							<tr style="border-bottom:1px solid #999999;">
								<th>SN#</th>
								<th>IP Address</th>
								<th>Building</th>
								<th>Floor</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				
				
			</div>	
		</div>
		<%--Changes Ends LBS 1.5 --%>
		
		
		<div style="display: none;">
			<div id="errorMove">
			<div><spring:message code='lbs.label.youhavealreadypendingmoverequest'/></div>
			<button id="closeErrorMove" class="buttonBlue" onClick="closeWarning()"><spring:message code='lbs.label.close'/></button>
			</div>
		</div>	
		<div style="display: none;">
		<div id="controlPanelDiv" style="text-align: left">
  			  <span><spring:message code="deviceFinder.confirmToControlPanel"/></span>
    			<br/>
  		  <div style="text-align: right">
	  		  <button class="button_cancel" onclick="closeCntlPanelPopup();">
	    		    <spring:message code="button.cancel" />
	  		  </button>
	   		 &nbsp;&nbsp;
	    		<button class="button" id="closeBtnCntrlPanel">
	     		   <spring:message code="button.ok"/>
	  		  </button>
   		 </div>
		</div>
		</div>
	<script id="list-template" type="text/x-handlebars-template">
    
        {{#assets}}
        
		<div class="deviceDetailsDiv" id="deviceDetails{{id}}" onclick="highlightAsset('{{id}}');">
			<div class="deviceInfoDiv">
				<div class="printerImage">
						<img id="printImageId{{id}}" src="<html:imagesPath/>loading-icon.gif" alt="printer img" />
				</div>
				<div class="deviceDetailedInfo"><div class="deviceID" id="deviceId">
					{{modelNumber}}
				</div>
				<div class="bookmark">
					<img src="<html:imagesPath/>transparent.png" class="bookmarkFavouriteImage ui_icon_sprite {{{generateBkmrkClass id }}}" id="starIMG_{{id}}" alt="bookmark-favourite" onclick="bookmarkDevice('{{id}}',this)"/>
					<img src="<html:imagesPath/>loading-icon.gif" id="loadigFavorite{{id}}" style="display: none;"/>
					<span id="bookmarkMsg{{id}}">{{{generateBkmrkMsg}}}</span>
				</div>
				<spring:message code='lbs.label.serialno'/> :<span class="deviceSerialNo" ><a style="cursor:pointer" onClick="lbs.highlightMap('{{id}}');">{{serialNumber}}</a></span><br/>
				<spring:message code='lbs.label.ipaddress'/> :<span class="deviceIP" >{{ipAddress}}</span><br/>
				<spring:message code='lbs.label.customerdevicetag'/>:<span class="deviceTag">{{customerDeviceTag}}</span>
				<div style='margin-top:10px'>
					<c:if test="${showCreateNew=='true'}">
					<a class="createRequests" onClick="showCreateRequestPop('{{id}}')" id="createRequests{{id}}" style="cursor: pointer;" ><spring:message code='lbs.label.createrequest'/></a>
					</c:if> 
					:<a style="cursor: pointer;" onclick="showLocationHistoryById('{{id}}')">Location History</a>
                    <a style="cursor: pointer;display:none" onclick="showRequests('{{id}}',-2)">2 Year SR History</a>
					:<a style="cursor: pointer;" onclick="postDirectMoveMessage('{{id}}');">Move without SR Creation</a>
					
				</div>
			</div>
			<div class="createRequestMenu" id="createRequestMenu{{id}}" style="display:none;">
					<span class="popup_arrow">
						<span class="popup_arrow-inner"></span>
					</span>
									<c:if test="${fleetMgmtForm.showSupplies}">
										<div><a style="cursor: pointer;" onClick="createSuppliesRequest('{{id}}');"><spring:message code='lbs.label.supplies'/></a></div>
									</c:if>
									<c:if test="${fleetMgmtForm.showService}">
										<div><a style="cursor: pointer;" onClick="createServiceRequest('{{id}}');"><spring:message code='lbs.label.service'/></a></div>
									</c:if>
									<c:if test="${fleetMgmtForm.showChangeMgmt}">
										<div><a style="cursor: pointer;" onClick="postMoveMessage('{{id}}');"><spring:message code='lbs.label.move'/></a></div>
										<div><a style="cursor: pointer;" onClick="changeAssetRequest('{{id}}');"><spring:message code='lbs.label.change'/></a></div>
										<div><a style="cursor: pointer;" onClick="decommissionAssetRequest('{{id}}');"><spring:message code='lbs.label.decommission'/></a></div>
									</c:if>
			</div>
	</div>
	{{#if srArray}}
	<div class="deviceRequests">
		<div class="openRequestsDiv">
			<div class="openRequestsHeader"><spring:message code='lbs.label.openrequests'/> :
			</div>
			<div class="openRequestsContent">
					<table>
					{{{showSupplies srArray id}}}
					{{{showService srArray id}}}
					{{{showMove srArray id}}}
					{{{showChange srArray id}}}
					{{{showDecommission srArray id}}}
					</table>
			</div>
		</div>				
	</div>		
	{{/if}}
	<div class="additionalInfo" >
			<div class="additionalInfoHeader" onclick="showOrHideAdditionalInfo(this,'{{id}}')">
				<img src="<html:imagesPath/>arrow-down-grayed.png" alt="" /><spring:message code='lbs.label.viewadditionalinformation'/></div>
					<div class="additionalInfoContent" id="additonalInfoContent{{id}}" style="display:none;">
						<div class="additionalInfoContentLeft">
							<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite user-icon" alt="View Contact Icon"/>
							<a onclick="openContactPopUp('{{id}}');"><spring:message code='lbs.label.viewcontact'/></a>
						</div>
						<div class="additionalInfoContentRight" >
							<ul>
							<li id="reqHistory{{id}}"><a style="cursor: pointer;" onclick="showRequests('{{id}}',-1)"><spring:message code='lbs.label.requesthistory'/></a><br/></li> 
							<li id="cntrlPanel{{id}}"><a targetstyle="cursor: pointer;" onClick="openCntlPanelPopup()" ><spring:message code='lbs.label.controlpanel'/></a><br/></li>
							<li id="sprtDwnlds{{id}}"><a style="cursor: pointer;" onClick="showSupportDownlds('{{id}}')" ><spring:message code='lbs.label.supportanddownloads'/></a><br/></li>
							<li id="pgCounts{{id}}"><a style="cursor: pointer;" onClick="openPopUp('{{id}}', '{{serialNumber}}', '{{ipAddress}}', '{{modelNumber}}', '{{customerDeviceTag}}')" ><spring:message code='lbs.label.updatepagecount'/></a><br/></li>
							<li><a style="cursor: pointer;" onclick="showDeviceStatus('{{id}}');">Device Status</a></li>
							</ul>
						</div>
						
			    	</div>					
			</div>			
	</div>
{{/assets}}					
</script>

<script id="deviceInfoExt" type="text/x-handlebars-template">

							<table>
		                         <tbody>
		                          	<tr class="tableRowShade"><td><spring:message code='lbs.label.assettag'/>:</td><td>{{assettag}}</td></tr>
		                          	<tr>					 <td><spring:message code='lbs.label.accountname'/>:</td><td>{{accountName}}</td></tr>
		                           	<tr class="tableRowShade"><td><spring:message code='lbs.label.devicehostname'/>:</td><td>{{deviceHostname}}</td></tr>
		                           	<tr><td><spring:message code='lbs.label.devicephase'/>:</td><td>{{devicePhase}}</td></tr>
									<tr class="tableRowShade"><td><spring:message code='lbs.label.costcenter'/>:</td><td>{{costCenter}}</td></tr>
		                           	<tr>					 <td><spring:message code='lbs.label.product'/></td><td>{{assetProduct}}</td></tr>
		                           <tr class="tableRowShade"><td><spring:message code='lbs.label.installdate'/>:</td><td>{{assetInstallDate}}</td></tr>
		                           <tr>						 <td><spring:message code='lbs.label.modeltype_family'/>:</td><td>{{modelTypeFamily}}</td></tr>
		                           <%--<tr> <td><spring:message code='lbs.label.producttype'/>:</td><td>{{productType}}</td></tr>--%>
		                           <tr class="tableRowShade"><td><spring:message code='lbs.label.productseries'/>:</td><td>{{productSeries}}</td></tr>
		                           <tr><td><spring:message code='lbs.label.brand'/>:</td><td>{{brand}}</td></tr>
		                           <tr class="tableRowShade"><td><spring:message code='lbs.label.color_mono'/>:</td><td>{{{productTypeHelper colorMono}}}</td></tr>
		                           <tr><td><spring:message code='lbs.label.single_mfp'/>:</td><td>{{{productTypeHelper singleMFP}}}</td></tr>
		                           
		                           <tr class="tableRowShade"><td><spring:message code='lbs.label.department'/>:</td><td>{{department}}</td></tr>
		                           <tr><td><spring:message code='lbs.label.meterreadtype_manual_vs_automated'/>:</td><td>{{meterReadType}}</td></tr>
		                           <tr class="tableRowShade"><td><spring:message code='lbs.label.ownership'/>:</td><td>{{ownership}}</td></tr>
									<tr><td><spring:message code='lbs.label.LXKAssetTag'/>:</td><td>{{lxkAssetTag}}</td></tr>
									<tr class="tableRowShade"><td><spring:message code='lbs.label.assetlifecycle'/>:</td><td>{{assetLifeCycle}}</td></tr>
									<tr><td><spring:message code='lbs.label.hardwarestatus'/>:</td><td>{{hardwareStatus}}</td></tr>
									<tr class="tableRowShade"><td><spring:message code='lbs.label.partType'/>:</td><td>{{partType}}</td></tr>
		                           </tbody>
		                          </table>
							<div class="popupContactInfo" id="popupContactInfo{{deviceId}}" style="display:none;">
							<span class="popup_arrow_low"> <span class="popup_arrow-inner_low"></span></span>
								<div class="popupContactInfoHeader">
									<b><spring:message code='lbs.label.contactinfo'/></b>
								</div><br/>
								<div class="popupContactDetailsDiv">
									<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite user-icon" /><span class="contactName">{{firstName}}</span>
									<span class="contactName">{{lastName}}</span><br/>
									<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite mobile-icon" /><span class="contactMobNo">{{workPhone}}</span><br/>
									<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite phone-icon" /><span class="contactTelephoneNo">{{alternatePhone}}</span><br/>
									<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" /><span class="contactEmail">{{email}}</span><br/>
								</div>
							</div>


</script>
			
	<script>
	$(document).ready(function(){
		
		var addDeviceListFlag = false;
		
		$('body').click(function() {
		  if(!addDeviceListFlag){
			  $('#addDevice_List').hide();
		  }
		});	
		
		$("#addDevice_List,#addDevice_PlusImage").mouseenter(function() {	
			addDeviceListFlag = true;
	    });
		$("#addDevice_List,#addDevice_PlusImage").mouseleave(function() {
			addDeviceListFlag = false;
	    });
	});
	
	function openAddDeviceList(){
		$('#addDevice_List').show().position({
			  my: "right top",
			  at: "right bottom",
			  of: "#addDevice_PlusImage",
			  collision:"none"
			});
	}
	var controlPanelDialog=$('#controlPanelDiv').dialog({
		title:"<spring:message code='lbs.label.controlpanel'/>",
		modal:true,
		autoOpen:false
	});
	var sprtDwnldUrl="${fleetMgmtForm.supportDwnldURL}";
	
	function openCntlPanelPopup(){
		controlPanelDialog.dialog('open');
	}
	function closeCntlPanelPopup(){
		controlPanelDialog.dialog('close');
	}
	
	function showSupportDownlds(assetId){
		//ProductTLI to be appended
		var assetIfo=deviceInformations.getDeviceObjectFromList(assetId);
		//alert(sprtDwnldUrl);
		window.open(sprtDwnldUrl+(("productTli" in assetIfo)==true?assetIfo["productTli"]:""));
	}
	  //alert("${fleetMgmtForm.showSupplies},${fleetMgmtForm.showService},${fleetMgmtForm.showChangeMgmt}");
	var globalMessagesAssetDetails={
		bookmark:"<spring:message code='lbs.label.bookmarkthisdevice'/>",
		unBookmark:"<spring:message code='orderSupplies.label.unbookmarkThisDevice'/>",
		suppliesLink:"<spring:message code='lbs.label.supplies'/>",
		service:"<spring:message code='lbs.label.service'/>",
		move:"<spring:message code='lbs.label.move'/>",
		change:"<spring:message code='lbs.label.change'/>",
		decommission:"<spring:message code='lbs.label.decommission'/>"
		
	};	
	
	function closeWarning(){
		errorDialogMove.dialog('close');
	}
	
	
	
	function showOrHideAdditionalInfo(y,deviceId)
	{	
		if($(y).siblings('.additionalInfoContent').css('display')=="none")
			{
				$(y).siblings('.additionalInfoContent').css('display','block');
				$(y).children('img').attr('src','<html:imagesPath/>arrow-up-grayed.png');
				$(y).css('background','#fff');
				loadAmindAdditionalInformaion(deviceId);
			}else{
				$(y).siblings('.additionalInfoContent').css('display','none');
				$(y).children('img').attr('src','<html:imagesPath/>arrow-down-grayed.png');
				$(y).css('background','#dadada');
			}
		
	}
	
	function loadAmindAdditionalInformaion(deviceId){
		
		var split=deviceId.split(",");
		for(var i=0;i<split.length;i++){
			$('#additonalInfoContent'+split[i]+' table').remove();
			$('#additonalInfoContent'+split[i]+' .popupContactInfo').remove();
			
			$('#additonalInfoContent'+split[i]).append("<div class='gridLoading'>"+
					"<img src='<html:imagesPath/>gridloading.gif'/></div>");
			
		}
		
		$.getJSON("${assetInfoURL}&deviceId="+deviceId,function(response){
			
			$.each(response, function( key, resValue ) {
				$('#additonalInfoContent'+resValue["deviceId"]+' .gridLoading').remove();
				$('#additonalInfoContent'+resValue["deviceId"]).append(templateDeviceExt(resValue));
				var fUrlVal="";
				if(resValue.deviceHostname!=""){
					fUrlVal=resValue.deviceHostname;
				}else{
					var assetIfo=deviceInformations.getDeviceObjectFromList(deviceId);
					fUrlVal=assetIfo.ipAddress;
				}
					$('#closeBtnCntrlPanel').click(function(){
						closeCntlPanelPopup();
						//alert(fUrlVal);
						window.open("http://"+fUrlVal);
					});
				
			
			});
			
			
				
		});
	} 
	
	
	function showCreateRequestPop(deviceId){
		$('#createRequestMenu'+deviceId).show().position({
			  my: "right top",
			  at: "right bottom",
			  of: "#createRequests"+deviceId,
			  collision:"none"
			});
		var dcTotal = $('#deviceContent').offset().top + $("#deviceContent").height();
		var crTotal = $("#createRequestMenu"+deviceId).offset().top+$("#createRequestMenu"+deviceId).height();
		if(crTotal>dcTotal)
			$('#deviceContent').animate({scrollTop: $('#deviceContent').scrollTop()+(crTotal-dcTotal)+15}, 'slow');
		$('#createRequestMenu'+deviceId).mouseleave(function(){
			$(this).hide();
		});
	}
	
	
	
	 
			function setAddressToHTML(addressObj){
					$('#addressDetails').html(generateAddressDisplayForAsset(addressObj));
					$('#building_assetDetails').html(addressObj["buildingName"]);
					$('#floor_assetDetails').html(addressObj["floorName"]);
					
					//Set the address for location history too.. need for future click...
					$('#addressDetails_locHistry').html(generateAddressDisplayForAsset(addressObj));
					$('#building_assetDetails_locHistry').html(addressObj["buildingName"]);
					$('#floor_assetDetails_locHistry').html(addressObj["floorName"]);
					
					var f="";
					if("floorLOD" in addressObj){
						if(addressObj["floorLOD"]==null || addressObj["floorLOD"]==""){
							f="Street Level";
						}else{
							f=addressObj["floorLOD"];
						}
					}else{
						f="Street Level";
					}
					$("#totalDevicesOnFloor_assetDetails_addressLOD,#locHistory_addressLOD").html(f);
					//Set the address to Move from for Multi Asset Select.. LBS 1.5
					$('#move-from-details').html(generateAddressDisplayForAsset(addressObj));
					//added for placement
					$('#lbs_accountId').val(addressObj["accountId"]);
					
					
			}
			
			function setTotalDevice(total){
				$('#totalDevices_assetDetails').html(total);	
			}
			function setTotalDeviceFloor(total){
				$('#totalDevicesOnFloor_assetDetails').html(total);				
			}
			function setTotalPlacements(total){
				$('#total_placement').html(total);	
			}
			function setTotalPlacementFloor(total){
				$('#totalOnFloor_placement').html(total);				
			}
			function setbuildingType(value){
				$('#lbs_buildingType').html(value);				
			}
			function bookmarkDevice(deviceId,object)
			{
				var bookmark=($(object).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
				//alert(bookmark);
				var url="${favoriteURL}&favoriteAssetId="+deviceId+"&favoriteFlag="+(!bookmark);
				$('#loadigFavorite'+deviceId).show();
				$(object).hide();
				doAjax(url, updateFavoriteSuccessCallBack, updateFavoriteFailCallBack);				
			}
			function updateFavoriteSuccessCallBack(loader) {
				
				var bookmark=($('#starIMG_'+loader.data).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
				$('#loadigFavorite'+loader.data).hide();
				$('#starIMG_'+loader.data).show();
				if(bookmark){
					var index=bookMarkedAssets.indexOf(loader.data);
					if(index!=-1){
						bookMarkedAssets.splice(index,1);
					}
					$('#starIMG_'+loader.data).removeClass('bookmark-star-icon');
					$('#starIMG_'+loader.data).addClass('removebookmark-icon');
					$('#bookmarkMsg'+loader.data).html(globalMessagesAssetDetails["bookmark"]);
					
				}else{
					bookMarkedAssets.push(loader.data);<%--this is declared in default view --%>
					$('#starIMG_'+loader.data).removeClass('removebookmark-icon');
					$('#starIMG_'+loader.data).addClass('bookmark-star-icon');
					$('#bookmarkMsg'+loader.data).html(globalMessagesAssetDetails["unBookmark"]);
				}
				return true;
			};

			function updateFavoriteFailCallBack(loader) {
				$('#loadigFavorite'+loader.data).hide();
			    return true;
			}
			
			function openContactPopUp(assetId){
				$('#popupContactInfo'+assetId).show();
				$('#popupContactInfo'+assetId).mouseleave(function(){
					$(this).hide();
				});
			}
			var requestHistoryObj={
				setDeviceId:function(d){
					this.deviceId=d;
				},
				getDeviceId:function(){
					return this.deviceId;
				},
				setGridType:function(d){
					var e="ALL_REQUESTS";
					if(d==1){
						e="SUPPLY_REQUESTS";
					}else if(d==2){
						e="SERVICE_REQUESTS";
					}else if(d==3 || d==4|| d==5){
						e="CHANGE_REQUESTS";
					}
					this.gridType=e;
				},
				getGridType:function(d){
					return this.gridType;
				},
				setWebStatus:function(d){
					var f=new Array();
					if(d==1){
						f=["Submitted","Inprocess","Draft","Shipped"];
					}
					this.webStatus=f;
					},
				getWebStatus:function(f)
				{
				    return this.webStatus;
				}
			};
			
			function viewPendingMove(deviceId){
				navigation="viewPendingMove";
					<%-- Show pending move view code ....  --%>
					pendingObj.info.setId(deviceId);
					closePendingObj.info.setId(deviceId);
					lbs.postMessage(pendingObj);
					$( "#closePendingMove").unbind( "click" );
					var device=deviceInformations.getDeviceObjectFromList(deviceId);
					showPendingMove();<%-- function in lbsService.js --%>
					setDeviceDetails(device,"pendingMove");
					$('#closePendingMove').click(function(){
						window.location.hash='';
						lbs.postMessage(closePendingObj);
						hidePendingMove();
						showNav();<%--Show the left nav with old data --%>
						navigation="";
					});
				
			}
			
			function showRequests(deviceId,type,status){
					requestHistoryObj.setDeviceId(deviceId);
					requestHistoryObj.setGridType(type);
					requestHistoryObj.setWebStatus(status);
					openHistoryPopup(type);
			}
			function setDeviceDetails(assetObj,param){
				$('#deviceId_'+param).html(("name" in assetObj)==true?assetObj.name:"");
				$('#deviceSerialNo_'+param).html((("serialNumber" in assetObj)==true?assetObj.serialNumber:""));
				$('#deviceIP_'+param).html((("ipAddress" in assetObj)==true?assetObj.ipAddress:""));
				$('#deviceTag_'+param).html((("customerDeviceTag" in assetObj)==true?assetObj.customerDeviceTag:""));
				$('#img_'+param).attr('src',assetObj.imgUrl);
				 
			}
			function setAddressHTML_MOVE_From(addressObj){
				$('#addressDetailsMoveFrom').html(generateAddressDisplayForAsset(addressObj));
				$('#building_assetDetails_moveFrom').html(addressObj["name"]);
				$('#floor_assetDetails_moveFrom').html(addressObj["floor"]);
			}
			function setAddressHTML_MOVE_TO(addressObj){
				$('#addressDetailsMoveTo').html(generateAddressDisplayForAsset(addressObj));
				$('#building_assetDetails_moveTo').html(addressObj["name"]);
				$('#floor_assetDetails_moveTo').html(addressObj["floor"]);
			}
			var errorDialogMove;var prvhtml=$('#mapInfo').html();
			function postMoveMessage(deviceId){
				<%-- check for if there is already pending request is ther or not otherwise show warning --%>
				
				var assetIfo=deviceInformations.getDeviceObjectFromList(deviceId);
				var type=null,count=null;
				if("srArray" in assetIfo){
					for(var i=0;i<assetIfo.srArray.length;i++){
						if(requestTypeObj.getRequestType(assetIfo.srArray[i].type)==3){
							type=3;
							count=assetIfo.srArray[i].count;
							break;
						}
					}
				}
				//alert(JSON.stringify(assetIfo));
				var devPhase=("assetLifeCycle" in assetIfo)?assetIfo.assetLifeCycle:null;
				
				if(count!=null && type!=null && count>0 && type==3){
					//show Warning and return false
					errorDialogMove=$('#errorMove').dialog({
						title:"<spring:message code='lbs.title.popup.error'/>",
						modal:true,
						autoOpen:false
					});
					errorDialogMove.dialog('open');
					return;
				}
				
				
				var enableDrag={"action":"enableDrag",
						"item":"asset",
						"info":{"id":deviceId}
				};
				lbs.postMessage(enableDrag);
				hideFiltersAndLeftNav();<%--method declared in LbsService.js --%>
				prvhtml=$('#mapInfo').html();
				$('#mapInfo').html("<spring:message code='lbs.msg.create.move'/> "+assetIfo.name);
				<%--$('#cancelMoveRequest').unbind("click");
				this button is there in default view jsp 
				$('#cancelMoveRequest').click(function(){
				
				});--%>
				
			}
			
			
			var directMoveInfo={id:"",isDirectMove:false};
			
			function postDirectMoveMessage(deviceId){
				isDirectMove=true;
				var assetIfo=deviceInformations.getDeviceObjectFromList(deviceId);
				var enableDrag={"action":"enableDrag",
						"item":"asset",
						"info":{"id":deviceId}
				};
				
				directMoveInfo["id"]=deviceId;
				directMoveInfo["isDirectMove"]=true;
				lbs.postMessage(enableDrag);
				hideFiltersAndLeftNav();<%--method declared in LbsService.js --%>
				var prvhtml=$('#mapInfo').html();
				$('#mapInfo').html("<spring:message code='lbs.msg.create.move'/> "+assetIfo.name);
				$('#cancelMoveRequest').unbind("click");
				<%--this button is there in default view jsp --%>
				$('#cancelMoveRequest').click(function(){
					// Post json to cancel Move REquest!!!!
					var cancelObj={
							"action": "disableDrag",
							"item": "asset",
							"info": {
							"id": deviceId
							}
							
							};
									
					lbs.postMessage(cancelObj);
					$('#mapInfo').html(prvhtml);
					showFiltersAndLeftNav();
					
				});
				
			}
			
			
			function doMove(lbsBuildingDetailsTo){
				var assetId;
				
				var location=lbsBuildingDetailsTo[0];
				
				for(var i=0;i<addressMapping.LBSField.length;i++){
					
					var valObj=location[addressMapping.LBSField[i]];
					
					if(valObj instanceof Array){
						
						if(valObj.length > 0){
							for(var j=0;j<valObj.length;j++){
								var firstField=valObj[j];
								for(field in firstField){
									
									$('#lbs_'+field).val(firstField[field]);
								}
								
							}
							
						}
					
				}
					else{
					$('#'+addressMapping.HtmlId[i]).val(valObj);
					
					}
				}
				
				
				
				assetId=location[addressMapping.LBSField[3]];
			
				$('#backInfo').val(JSON.stringify(location.defaultArea));
				var assetIfo=deviceInformations.getDeviceObjectFromList(assetId);
				var assetLifeCycle=("assetLifeCycle" in assetIfo)?assetIfo.assetLifeCycle:null;
				if(assetLifeCycle!=null && assetLifeCycle=="Shipped"){
					<%-- Redirect it to Install Asset page  --%>
					
					addAssetRequest(assetId);
					return;
				}
				if(directMoveInfo.isDirectMove){
					directMoveInfo.isDirectMove=false;
					<%--assetLifeCycle == "Installed" &&  Commented on 28th Aug--%>
					if($("#fleetMgmtForm #lbs_extAddressId").val().length == 0){
						initOpenAddressPopup();
					}
					else{
						showPopupMessagesOPS("Are you sure you want to move this device?",function(b){
							
							if (b){
								createMoveDirect(location);
							}else{							
								$('#cancelMoveRequest').click();
							}						
						});
					}
					
				}else{
					moveAssetRequest();
				}		
			}
			function createMoveDirect(location){
				showOverlay();
				location["assetId"]=directMoveInfo.id;
				 $.ajax({
						url:"${directMoveUrl}&t="+new Date().getTime(),
						type:'POST',
						dataType:"json",
						data:location,
						success: function(response){
							if(response.message != "success"){
								jAlert(response.message, "");
							}else{
								jAlert("Device Moved Successfully", "");
							}							
							$('#cancelMoveRequest').click();
							hideOverlay();
							},
					  failure: function(results){
						  
					  }
					});
			}
			
			function showLocationHistoryById(deviceId){
					navigation="viewLocationHistory";
				
					<%-- Show pending move view code ....  --%>
					locHistory.setShowAction();
					locHistory.info.setId(deviceId);
					
					lbs.postMessage(locHistory);
					$( "#closelocation_history").unbind( "click" );
					var device=deviceInformations.getDeviceObjectFromList(deviceId);
					if(!device){
						device=deviceInformations.getPlacementfromList(deviceId);
					}
					showLocationHistory();<%-- function in lbsService.js --%>
					setDeviceDetails(device,"locHistry");
					$('#closelocation_history').click(function(){
						window.location.hash='';
						locHistory.setCloseAction();
						lbs.postMessage(locHistory);
						hideLocationHistory();
						showNav();<%--Show the left nav with old data --%>
						navigation="";
					});
			}
			function enableMultiSelect(x){
				var multiSelectMode =$(x).val();
				multiSelectAsset.enableMultiSelect(multiSelectMode);
			}
			function closeMoveMultiSelect(){
				$('.deviceDetailsDiv').removeClass('greenBackground');
				multiSelectAsset.closeMultiSelect();
			}
			function handleMultiAssetMove(){
				multiSelectAsset.handleClickNext();
				
			}
			
			var expirationArray=[];
			var expirationArrayCode=[];
			expirationArray.push({"value":"", "displayValue":"Select Expiring Terms"});
			expirationArray.push({"value":"Select All", "displayValue":"Select All"});
			<c:forEach items="${expiration}" var="loopStatus">
			expirationArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
			expirationArrayCode["${loopStatus.key}".toLowerCase()]="${loopStatus.value}";
			</c:forEach>		
			
			var alertTypeCodesLOV={};
			<c:forEach items="${alertCodes}" var="loopStatus">
			alertTypeCodesLOV["${loopStatus.key}"]="${loopStatus.value}";
			</c:forEach>
	</script>

	<jsp:include page="/WEB-INF/jsp/fleetmanagement/deviceStatus/deviceStatusPopup.jsp"></jsp:include>