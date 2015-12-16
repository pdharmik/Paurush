<portlet:resourceURL var="saveFilterPrefPopVar" id="saveFilterPrefPopup"></portlet:resourceURL>
<portlet:resourceURL var="removePrefOPSVar" id="removePrefOPS"></portlet:resourceURL>


<div style="display: none;" >
<div id="editPreferencesPopup">

<div class="error" style="display: none;">
	<ul>
		<li>Please provide preference name</li>
	</ul>
</div>

<label>Preference Name</label>: <input type="text" id="name"/>
<input type="hidden" id="prefId">
<div id="FilterDetails">
			<div id="filterLocation" class="filterLocationPref">
					<div class="filterHeader">
						<h3><spring:message code='lbs.label.location'/></h3>
						<div id="${prefixId}customizeLocation" onclick="showCustomize(this,'${prefixId}')" >
							<a><spring:message code='lbs.label.customize'/></a>
							<img class="ui_icon_sprite customize-icon" src="<html:imagesPath/>transparent.png" alt="Customize Grid" title="Customize Grid" />
							
						</div>
					</div>
					
			
					
					
					<div id="filterCountryStateCity">
						<ul>
							<li>
								<select id="${prefixId}selectCountry">
									<option value=""><spring:message code='lbs.label.country'/></option>
									${fleetMgmtForm.countryString}
								</select>
							</li>
							<li>
								<select id="${prefixId}selectState" >
								<option value=""><spring:message code='lbs.label.state'/></option>
								</select>
							</li>
							<li>
								<select id="${prefixId}selectCity" >
								<option value=""><spring:message code='lbs.label.city'/></option>
								</select>
							</li>
						</ul>
						<div id="${prefixId}selectAddress">
						<ul class="roDisplay"><li id="${prefixId}address_display_li"></li></ul>
						<a id="${prefixId}addRessLink" onClick="openAddressPopup()" style="cursor: pointer;"><spring:message code='lbs.label.selectaddress'/></a>
						</div>
					</div>
					
					<div id="selectLocalAddress" class="selectLocalAddress">
						<ul>
							<li>
								<select id="${prefixId}site">
								<option value=""><spring:message code='lbs.label.site'/></option>
								</select>
							</li>
							<li>
								<select id="${prefixId}bldng">
								<option value=""><spring:message code='lbs.label.building'/></option>
								</select>
							</li>
							<li>
								<select id="${prefixId}flr">
								<option value=""><spring:message code='lbs.label.floor'/></option>
								</select>
							</li>
							<li>
								<select id="${prefixId}zone">
								<option value=""><spring:message code='lbs.label.zone'/></option>
								</select>
							</li>
							<li>
								<select id="${prefixId}bldngType">
								<option value=""><spring:message code='lbs.label.buildingType'/></option>
								${fleetMgmtForm.buildingTypes}
								</select>
							</li>
						</ul>
						
					</div>
				</div>
				<div id="${prefixId}customerHierarchy" class="customerHierarchyPref">
					<div class="filterHeader">
						<h3><spring:message code='lbs.label.customerhierarchy'/></h3>
						<div id="${prefixId}customizeCustomerHierarchy" onclick="showCustomize(this,'${prefixId}')" >
							<a><spring:message code='lbs.label.customize'/></a>
							<img class="ui_icon_sprite customize-icon" src="<html:imagesPath/>transparent.png" alt="Customize Grid" title="Customize Grid" />
							
						</div>
					</div>						
						<a id="${prefixId}customerHierarchyDir" onclick="popUpChlTree()" style="cursor: pointer;"><spring:message code='lbs.label.customerhierarchy'/></a><br/>
						<span id="${prefixId}chlNodeValueLabel"></span>	
						<input type="hidden" id="${prefixId}chlNodeId"/>
						<input type="hidden" id="${prefixId}chlNodeValue"/>					
					</div>
				
				<div id="filterDevice">
				<div class="filterHeader">
					<h3><spring:message code='lbs.label.device'/></h3>
					<div id="${prefixId}customizeDevice" onclick="showCustomize(this,'${prefixId}')" >
						<a><spring:message code='lbs.label.customize'/></a>
						<img class="ui_icon_sprite customize-icon" src="<html:imagesPath/>transparent.png" alt="Customize Grid" title="Customize Grid" />
					</div>
				</div>
				<div id="DeviceProperties">
					<ul>
						<li>
							<input id="${prefixId}serialNumber" type="text" name="SerialNumber" value="<spring:message code='lbs.label.serialno'/>" />
						</li>	
						<li>
							<input id="${prefixId}ipAddress" type="text" name="IPAddress" value="<spring:message code='lbs.label.ipaddress'/>" />
						</li>
					
						<li>
							<input id="${prefixId}productModel" type="text" name="productModel" value="<spring:message code='lbs.fleetmgmt.ops.model'/>" />							
						</li>
				
						<li >
						<input id="${prefixId}assetProduct" type="text" name="assetProduct" value="<spring:message code='lbs.label.product'/>" />
							
						</li>
						
						<li>
						
						<input id="${prefixId}modelType" type="text" name="modelType" value="<spring:message code='lbs.label.modeltype_family'/>" />
							
						</li>
						<li>
							<div id="${prefixId}productType"></div>
							
						</li>
						<li>
							<div id="${prefixId}productSeries"></div>
						</li>
						<li>
						<input id="${prefixId}brand" type="text" name="brand" value="<spring:message code='lbs.label.brand'/>" />
							
						</li>
						
						<li>
							
							<input id="${prefixId}costCenterAsset" type="text" name="costCenterAsset" value="<spring:message code='lbs.label.costcenter'/>" />
						</li>
						<li>
							<input id="${prefixId}departmentAsset" type="text" name="departmentAsset" value="<spring:message code='lbs.label.department'/>" />
							
						</li>
						<li>
							
							<select id="${prefixId}meterReadTypeAsset">
								<option value=""><spring:message code='lbs.label.meterreadtype'/></option>
								<c:forEach items="${meterReads}" var="requestStatus" varStatus = "status" >
									<option value="${requestStatus.key}">${requestStatus.value}</option>
								</c:forEach>																
							</select>			

						</li>
						<li>
							<input id="${prefixId}assetTag" type="text" name="assetTag" value="<spring:message code='lbs.label.assettag'/>" />
							
						</li>
						<%--17366 Changes  
						<li>
							<input type="text" id="${prefixId}agreement" name="Agreement" value="<spring:message code='lbs.label.agreement'/>" />
						</li> --%>
						<li>
							<input type="text" id="${prefixId}deviceHostName" name="DeviceHostName" value="<spring:message code='lbs.label.devicehostname'/>" />
						</li>
						<li style="clear:both;width:100%">
						
						<ul id="${prefixId}assetInstallDateDiv" style="display: inline;"><%-- Don't remove this DIV  --%>					
								<li><input id="${prefixId}installDateFrom" type="text"  name="startDateFrom" class="popupDate" value="<spring:message code='lbs.fleetmgmt.installDate.From'/>" readonly="readonly" />
								<img id="${prefixId}imgLocalizedBeginDateAsset" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon"  style="cursor:pointer;" />
								</li>
								<li><input id="${prefixId}installDateTo" type="text"  name="endDateTo" class="popupDate" value="<spring:message code='lbs.fleetmgmt.installDate.To'/>"  readonly="readonly"/>
								<img id="${prefixId}imgLocalizedEndDateAsset" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon"  style="cursor:pointer;" />
								</li>
							</ul>
						<li>
						
						
						
						
						<li>
						<input type="text" id="${prefixId}LXKAssetTag" value="<spring:message code='lbs.label.LXKAssetTag'/>"/> 
							
						</li>
						<li>
						<div id="${prefixId}assetLifeCycle"></div>
						 
							
						</li>
						<li>
						<div id="${prefixId}devicePhase"></div>
						
							
						</li>
						<li>
						<div id="${prefixId}hardwareStatus"></div>
						
							
						</li>
						<li>
							<input type="text" id="${prefixId}mdmID" name="mdmId" value="<spring:message code='lbs.label.MDMID'/>" />
						</li>
						
						</ul>
				</div>
				
			</div>
				<div id="filterRequestsDiv">
				<div class="filterHeader">
					<h3><spring:message code='lbs.label.requests'/></h3>
					<div id="${prefixId}customizeRequests" onclick="showCustomize(this,'${prefixId}')" >
						<a><spring:message code='lbs.label.customize'/></a>
						<img class="ui_icon_sprite customize-icon" src="<html:imagesPath/>transparent.png" alt="Customize Grid" title="Customize Grid" />
					</div>
				</div>
				<div id="RequestDetails">
					<ul>
						<li>
							<input id="${prefixId}requestNo" type="text" name="RequestNo" value="<spring:message code='lbs.label.requestnumber'/>"/>
						</li>
						<li>
							
							<div id="${prefixId}requestType"></div>
						</li>
						<li>
						
							<div id="${prefixId}requestStatus"></div>	
						</li>
						<li>
							<input id="${prefixId}customerRef" type="text" name="customerRef" value="<spring:message code='lbs.label.customerreference'/>" />
						</li>
						
						<li>
							
							<div id="${prefixId}areaDrop"></div>
						</li>
						<li>
							
							<div id="${prefixId}subAreaDrop"></div>
						</li>
						
						<li>
							<div id="${prefixId}srOPSStatus"></div>
						</li>
						<li>
							<div id="${prefixId}srSubStatus"></div>
						</li>
						<li>
							<div id="${prefixId}srSource"></div>
						</li>
						<li style="clear:both;width:100%">
							<ul id="${prefixId}dateRangeFilterContainer"><%-- Don't remove this DIV  --%>					
								<li><input id="${prefixId}startDateFilter" class="popupDate" type="text"  name="startDate" value="<spring:message code='requestInfo.requestHistory.label.from'/>" readonly="readonly"/>
								<img id="${prefixId}imgLocalizedBeginDate" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon"  style="cursor:pointer;" />
								</li>
								<li><input id="${prefixId}endDateFilter" class="popupDate" type="text"  name="endDate" value="<spring:message code='requestInfo.requestHistory.label.to'/>" readonly="readonly"/>
								<img id="${prefixId}imgLocalizedEndDate" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon"  style="cursor:pointer;" />
								</li>
							</ul>
						</li>
					</ul>		
				</div>
			
			</div>
			</div>
	<div class="clearDiv">							
	<input type="checkbox" id="default"/><label>Default Preference</label>
	<button class="button_cancel btnpopupPref" onclick="closeDialogPrefPop()">Cancel</button>
	<button class="button btnpopupPref" onClick="savePrefPopup()">Save</button>
	</div>
	
	<div style="display: none;" id="${prefixId}customizepop" class="customizePopup">
				<span class="popup_arrow">
					<span class="popup_arrow-inner"></span>
				</span>
				<div class="popupHeader">
					<span><b><spring:message code='lbs.label.customize'/></b></span>
				</div>
				<div id="${prefixId}loadCustomizeSelectors" class="clearBoth"></div>
	</div>
</div>
</div>


			

			<script type="text/javascript" src="<html:rootPath/>js/LbsCommonFilters.js?version=0.1"></script>
			<script>
			
			
			
			function showCustomize(x,prefix){
				var p=$(x).position();
				var w=$("#editPreferencesPopup #FilterDetails").width();
				if($(x).attr('id')==(prefix+"customizeLocation") || $(x).attr('id')==(prefix+"customizeCustomerHierarchy")){
					generateHTMLforLocCustomize(prefix);
					
				}
					
				if($(x).attr('id')==(prefix+"customizeDevice")){
					generateHTMLforDeviceCustomize(prefix);
				}
					
				if($(x).attr('id')==(prefix+"customizeRequests")){
					generateHTMLforSRCustomize(prefix);
				}
				var l=p.left;
				var r=w-l-80;
				$('#'+prefix+'customizepop').css({right:r});		
				$('#'+prefix+'customizepop').show(function(){
					popTimer=setTimeout(function(){
						$('#${prefixId}customizepop').hide();
					},1000);
				});
			}
			function generateHTMLforDeviceCustomize(prefix){
				
				var defaultHTml="<td><input class=\"customize\" type=\"checkbox\" value=\"-1\" -2 onclick=\"customizePopupSave(this)\">-3</td>";
				var finalHtml="<div class=\"popupHeader popupSubHeader\"><span><b>Customer</b></span></div><br/><table><tr>";
				var countt=0;
				for(field in deviceIdsHTMLObj){
					if(field=="LXKAssetTag"){
						finalHtml+="</tr></table><div class=\"popupHeader popupSubHeader\"><span><b>Employee</b></span></div><table><tr>";
					}
					var t=defaultHTml.replace('-1',$('#'+prefix+field).attr('id'));
					t=t.replace('-3',deviceIdsHTMLObj[field]);
					if($('#'+prefix+field).css('display')!="none"){
						t=t.replace('-2','checked');	
					}
					if(countt%2==0){
						finalHtml+="</tr><tr>";
					}
					
					finalHtml+=t;
					countt=countt+1;
				}
				finalHtml+="</tr></table>";
				
				
				$("#"+prefix+"loadCustomizeSelectors").html(finalHtml);
				
			}
			
			function generateHTMLforSRCustomize(prefix){
				var strRequestNoChecked="";
				var strRequestTypeChecked="";
				var strRequestStatusChecked="";
				var strDateRangeChecked="";
				var strCustomerReference="";
				var strArea="";
				var strSubArea="";
				var strSrOPSStatus="";
				var strSrSubStatus="";
				var strSrSource="";
				
				
				if($('#'+prefix+'requestNo').css('display')!="none"){	strRequestNoChecked=checked;}
				if($('#'+prefix+'requestType').css('display')!="none"){strRequestTypeChecked=checked;}
				if($('#'+prefix+'requestStatus').css('display')!="none"){strRequestStatusChecked=checked;}
				if($('#'+prefix+'dateRangeFilterContainer').css('display')!="none"){strDateRangeChecked=checked;}
				if($('#'+prefix+'customerRef').css('display')!="none"){strCustomerReference=checked;}
				if($('#'+prefix+'areaDrop').css('display')!="none"){strArea=checked;}
				if($('#'+prefix+'subAreaDrop').css('display')!="none"){strSubArea=checked;}
				if($('#'+prefix+'srOPSStatus').css('display')!="none"){strSrOPSStatus=checked;}
				if($('#'+prefix+'srSubStatus').css('display')!="none"){strSrSubStatus=checked;}
				if($('#'+prefix+'srSource').css('display')!="none"){strSrSource=checked;}
				//alert('inside customizeRequests');
				$("#"+prefix+"loadCustomizeSelectors").html("<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"areaDrop\" "+strArea+"onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.area'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"customerRef\" "+strCustomerReference+"onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.customerreference'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" disabled=\"disabled\" value=\""+prefix+"dateRangeFilterContainer\" "+strDateRangeChecked+"onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.daterange'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"requestNo\" "+strRequestNoChecked+" onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.requestnumber'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"requestStatus\" "+strRequestStatusChecked+"onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.requeststatus'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"requestType\" "+strRequestTypeChecked+"onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.requesttype'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"subAreaDrop\" "+strSubArea+" onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.subarea'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"srOPSStatus\" "+strSrOPSStatus+" onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.srOPSStatus'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"srSubStatus\" "+strSrSubStatus+" onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.srSubStatus'/><br>"+
													"<input class=\"customize\" type=\"checkbox\" value=\""+prefix+"srSource\" "+strSrSource+" onclick=\"customizePopupSave(this)\"><spring:message code='lbs.label.srSource'/><br>");
			}
			
			function generateHTMLforLocCustomize(prefix){
				var strLocationChecked="";
				var strChlChecked="";
				if($('#editPreferencesPopup #filterLocation').css('display')!="none")
				{
					strLocationChecked=checked;
				}if($('#'+prefix+'customerHierarchy').css('display')!="none")
				{
					strChlChecked=checked;
				}
				$("#"+prefix+"loadCustomizeSelectors").html("<input id=\"chl\" type=\"radio\" name=\"chl\" value=\""+prefix+"chl\" "+strChlChecked+" onclick=\"customizePopshowCHLFilter()\"><spring:message code='lbs.label.chl'/><br>"+
				"<input id=\"location\" type=\"radio\" name=\"chl\" value=\""+prefix+"location\" "+strLocationChecked+" onclick=\"customizePopshowLocationFilter()\"><spring:message code='lbs.label.location'/>");
				
			}
			
			function generateTemplatePref(result){
				var templatePref="<div class=\"clearBoth\" id=\"pref_"+result.id+"\">"+
				"<a onClick=\"loadToMainPage("+result.id+")\">"+result.prefName+"</a>"+
				"<img class=\"floatR ui_icon_sprite trash-icon\" onClick=\"removePref("+result.id+")\" src=\"<html:imagesPath/>transparent.png\"/>"+
				"<img class=\"floatR ui_icon_sprite edit-icon\" src=\"<html:imagesPath/>transparent.png\" onClick=\"openPrefPopup("+result.id+")\"/>"+
				"</div>";
				return templatePref;
			}	
			
			function removePref(prefId){
				showPopupMessages("Are you sure you want to Delete this Filter?",function(){
					$.getJSON("${removePrefOPSVar}&index="+prefId,function(result){
						preferenceObject.removePreferenceById(result.id);
						$('#pref_'+result.id).remove();
					});			
				});					
			}
			function savePrefPopup(){
				
				if($('#editPreferencesPopup #name').val()==""){
					$('#editPreferencesPopup .error').show();
					setTimeout(function(){
						$('#editPreferencesPopup .error').hide('slow');	
					},4000);
					return;
				}
				
				if($('#editPreferencesPopup #default').attr('checked')){
					var jsonObj=preferenceObject.getPreferenceObject();
					//alert('jsonObj=' +jsonObj);
					if(jsonObj==null){
						//There is no default preference set
						c.__savePreference("${saveFilterPrefPopVar}",c.__createObjectsForDB());	
					}else{
							if(jsonObj.isDefault=='Y'){
								showPopupMessages("There is already a Default Filter. Do you want to Continue?",function(){
									$('#editPreferencesPopup #prefId').val(jsonObj.id);
									c.__savePreference("${saveFilterPrefPopVar}",c.__createObjectsForDB());		
								});	
		
							}
						}
				}else{
					c.__savePreference("${saveFilterPrefPopVar}",c.__createObjectsForDB());	
				}
					
				
				
			}		
					
			function loadToMainPage(id){
				var jsonObj=preferenceObject.getPreferenceObjectById(id);
				//alert(JSON.stringify(jsonObj));
				resetFilters(false);
				parseSettings(jsonObj);
				setValuesToObjects(true);checkAndSetCancelForDate();showMapBtnClicked();hideOverlay();
			}		
						
			var	dialogEditPref;
						function openPrefPopup(prefId){
							
							
							/*if(c.__checkIfPrefAvail(prefId)==false){
								c.__getPreference(prefId,createDialogForPref);	
							}else{*/
								c.setParentContext("${prefixId}");
								c.__resetFilters();
								var obj=preferenceObject.getPreferenceObjectById(prefId);
								//alert(JSON.stringify(obj));
								c.__displayInfoInPopup(c.__parseSettings(obj));
								c.__loadCustomizeSetting("fieldDisp" in obj==true?obj.fieldDisp:"");
								$('#editPreferencesPopup #FilterDetails').show();
								$('#editPreferencesPopup .clearDiv').addClass('clearDivHeight');
								createDialogForPref();								
								openDialogForPref();
							//}
							
						}
						function createDialogForPref(){
							dialogEditPref=jQuery('#editPreferencesPopup').dialog({
								autoOpen: false,
								title: "Edit Preferences",
								modal: true,
								draggable: false,
								resizable: false,
								height: 1000,
								width: 1000,
								close: function(event,ui){
									dialogEditPref.dialog('destroy');
									$('#editPreferencesPopup #prefId').val('');
									$('#editPreferencesPopup #name').val('');
									$('#editPreferencesPopup #default').attr('checked',false).trigger('click');
									$('#editPreferencesPopup .clearDiv').removeClass('clearDivHeight');
									c.setParentContext("");
								}
								
								});
							
						}	
						function openDialogForPref(){
							dialogEditPref.dialog('open');
						}
						function showSetFilterAlert(){
							createDialogForPref();
							$('#editPreferencesPopup #FilterDetails').hide();
							c.setParentContext("");
							dialogEditPref.dialog('open');
						}
						function closeDialogPrefPop(){
							
							dialogEditPref.dialog('close');
						}		
						var c=new CommonFilters();
						var popTimer;
						
						$(document).ready(function(){
							c.setParentContext("${prefixId}");
							c._initBindChange();
							c.__createMultiSelects();
							c._initUrls("${stateListPopulateURL}","${cityListPopulateURL}","${siteBuildingListPopulateURL}","${buildingListPopulateURL}","${floorURL}","","${getAllLocationURL}","${opsSaveCustomzieSettingsPopupURL}");
							c.setParentContext("");<%--settiing it to blank otherwise c.getParentContext will return ${prefixID} it will be issue while selecting from address popup --%>
							$('#${prefixId}customizepop').mouseout(function(){
								
								popTimer=setTimeout(function(){
									$('#${prefixId}customizepop').hide();
								},1000);		
							});
							$('#${prefixId}customizepop').mouseover(function(){
							
							if(popTimer!=null) {
									 	clearTimeout(popTimer);
									 }
								$('#${prefixId}customizepop').show(function(){
									
								});
							});
						
							
						});
						
						function customizePopupSave(x){
							var n=$(x).val();
							if($(x).is(':checked')){$("#"+n).css("display","inline");}
							else{$("#"+n).css("display","none");}	
							c.__saveCustomizeSetting(n,$(x).is(':checked'));
							
							
						}
						
						function customizePopshowCHLFilter()
						{

							$("#editPreferencesPopup #filterLocation").css('display','none');
								$("#editPreferencesPopup #${prefixId}customerHierarchy").css('display','block');
								//closePopupCustomize();
								//Below code is for saving the customer hierarchy display as customize popup
								//customizeIndexSetting['filterLocation'][1]=false;//This is locatted in LBSService.js
								c.__saveCustomizeSetting('${prefixId}customerHierarchy',true);
								
						}
						function customizePopshowLocationFilter()
						{
							$("#editPreferencesPopup #${prefixId}customerHierarchy").css('display','none');
								$("#editPreferencesPopup #filterLocation").css('display','block');
								//closePopupCustomize();
								//Below code is for saving the filter location display as customize popup
								//customizeIndexSetting['customerHierarchy'][1]=false;//This is locatted in LBSService.js
								c.__saveCustomizeSetting('filterLocation',true);
								
								
						}
						
						
						function clearValPop(){
							c.clearValuesInput(deviceFields.deviceIdInputIds);
							c.clearValuesInput(srFields.srInputIds);
							$('#editPreferencesPopup .placeholderContent').each(function(){
								var txt="";
								$(this).parent().parent().siblings('.options').each(function(){
									$(this).val([]);
								});
								$(this).parent().parent().siblings('.options').find('option').each(function(i){
									if(i==0){
										txt=$(this).text();
										return false;
									}
									
								});
								$(this).html(txt);
								

							});
							$('#editPreferencesPopup .listValue').each(function(){
								$(this).prop('checked', false);//this is for unchecking the the checkbox of multiselect.
							});
							
							
							
							c.clearValuesInput(addressFields.htmlInputIds);
							c.clearValuesInput(chlInformation.htmlInputIds);
							
							//Clear AddressLink reset to Select Address
							$('#${prefixId}addRessLink').text("<spring:message code='lbs.label.selectaddress'/>");
							
							//Clear address popup values
							addressPrefPops=null;
							
							
						}
						
						
						
						
			</script>