<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<portlet:resourceURL var="buildingListPopulateURL" id="getBuilding"/>
<portlet:resourceURL var="floorURL" id="getFloor"/>
<portlet:resourceURL var="siteURL" id="getSite"/>
<portlet:resourceURL var="zoneURL" id="getZone"/>
<portlet:resourceURL var="getAllLocationURL" id="getAllLocation"/>
<portlet:resourceURL var="siteBuildingListPopulateURL" id="getSiteBuildingZone"/>
<portlet:resourceURL var="encryptJSONVar" id="encryptJSON"></portlet:resourceURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portletBlock infoBox rounded shadow position-static" >
          <div class="columnsOne position-static" >
            <div class="columnInner position-static" >
              <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.header"/></h4>
              <div class="portletBlock position-static" >
          <div class="columnsTwo position-static" >
            <div class="infoBox columnInner rounded shadow position-static" >
              <h4 class="gray">
              
              <%-- <a style="cursor:pointer" class="fontBlack"  title="<spring:message code="address.selectAddress"/>" id="serviceInformationDeviceInstalledAddressLink" onClick="popUpAddress(id,'userEnteredActivity.serviceRequest.asset.installAddress',setAddressValuesFromPopup);">
              
               <c:choose>
              <c:when test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.addressLine1 }">
              <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.addressLink"/>
              </c:when>
              <c:otherwise>
              <spring:message code="address.selectAddress"/>
              </c:otherwise>
              </c:choose>
              
              </a> --%>
              <c:choose>
              <c:when test='${isHwMove}'>
              	<spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.moveToAddr"/>
              	<c:set var="address" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress}"/>
					<c:set var="preName" value="userEnteredActivity.serviceRequest.asset.installAddress"/>
              </c:when>
              <c:otherwise>
              	<spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.deviceInstalledAddress"/>
              <c:set var="address" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress}"/>
					<c:set var="preName" value="userEnteredActivity.serviceRequest.asset.installAddress"/>
              
              </c:otherwise>
             </c:choose>
              </h4>
              
              <ul class="roDisplay">
                <li id="showAddress_userEnteredActivity.serviceRequest.asset.installAddress">
                <%-- <span>
                  Store-front Name<br/>
                  Address Line 1<br />
                  Address Line 2<br />
                  City, State / Province &lt;Zip/Postal Code&gt;<br />
                  Country</span> 
                  --%>
 
                  <c:choose>
                  <c:when test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.addressLine1 }">
                   <util:addressOutput value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress}" displayInDivs="true"></util:addressOutput>
                    
                  </c:when>
                  <c:otherwise>
                  <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.addrNotAvl"/>
                  </c:otherwise>
                  </c:choose>
                  </li>
              </ul>
           <%--   <ul class="form division" style="position:static;">
              
             
                <li>
                  <label for="building" class="labelie"><spring:message code="claimCreate.addressInfo.label.building"/></label>
                  <span style="position:static;">

                  <input style="position:static;" type="text" id="building" class="w100" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1}"/>
                  </span>
                  </li>
             
                <li>
                  <label for="floor" class="labelie"><spring:message code="claimCreate.addressInfo.label.floor"/></label>
                  <span style="position:static;">

                  <input style="position:static;" type="text" id="floor" class="w100" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2}"/>
                  </span>
                  </li>
             
                <li>
                  <label for="office" class="labelie"><spring:message code="claimCreate.addressInfo.label.office"/></label>
                  <span style="position:static;">

                  <input style="position:static;" type="text" id="office" class="w100" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3}"/>
                  </span></li>
             
              </ul>--%>
             	
             <%--For Non LBS Adresses --%>
             <%-- Commented for LBS 1.5 LOD changes 
             <c:choose>
                  <c:when test="${address.lbsAddressFlag ==null || address.lbsAddressFlag == false}">
                  	<div id="selectLocalAddress1">
					<ul class="form division">
						<li>
							<label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label> 
							<span><input type="text" id="physicalLocation1" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1}" class="w100" maxlength="100" /></span>
						</li>
						<li>
							<label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/> </label> 
							<span><input type="text" id="physicalLocation2" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2}" class="w100" maxlength="100" /></span>
						</li>
						<li>
						 	<label for="office"><spring:message code="claimCreate.addressInfo.label.office"/> </label> 
						 	<span><input type="text" id="physicalLocation3" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3}" class="w100" maxlength="100" /></span>
						</li>						 
					</ul>
				
					</div>
                 </c:when>
                 <c:otherwise>
                  <%--For LBS Adresses --%>
				<%-- 		<div id="selectLocalAddress">
						<ul class="form division1">
							<li><label for="building"><span class="req">*</span><spring:message code="claimCreate.addressInfo.label.building"/></label> 
							<span><select name="userEnteredActivity.serviceRequest.asset.installAddress.buildingId"	id="bldng"><option value=""><spring:message code="hardware.massUploadTemplate.Building"/></option></select>
							<input type="hidden" id="physicalLocation1" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1"  value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1}" />
							</span>
							</li>
							<li><label for="floor"><span class="req">*</span><spring:message code="claimCreate.addressInfo.label.floor"/></label> 
							<span><select name="userEnteredActivity.serviceRequest.asset.installAddress.floorId" id="flr"  ><option value=""><spring:message code="hardware.massUploadTemplate.Floor"/></option></select>
							<input type="hidden" id="physicalLocation2" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2}" />
							</span>
							</li>
							
							<li><label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label> 
							<span><input type="text" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3" 
							value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3}"
							id="office"  value="" class="width-144px"/></span>
							</li>
						 	<li><label for="coordinateX"><spring:message code='hardwareDebrief.closeOut.coordinateX'/></label> 
						 	<span><input type="text" name="${preName}.coordinatesXPreDebriefRFV" class="w100" maxlength="100" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV}" /></span>
						 	</li>
							<li><label for="coordinateY"><spring:message code='hardwareDebrief.closeOut.coordinateY'/></label>
							<span><input type="text" name="${preName}.coordinatesYPreDebriefRFV"  class="w100" maxlength="100" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV}" /></span>
							</li>
							
               			
						 	
						 
						<%--For LBS Adresses display names--%>
						<%--  </ul>				
						
					</div>		
                  </c:otherwise>
            </c:choose>--%>
            
             
             
             <c:set var="isLbsAddress" value="false" scope="request"/>
             <c:set var="islod" value="false" scope="request"/>
             <%--
             address.lbsAddressFlag=${address.lbsAddressFlag}
             address.lbsAddressLod=${address.lbsAddressLod}
			--%>
             <c:if test="${address.lbsAddressFlag != null && address.lbsAddressFlag != false}">
             <c:set var="isLbsAddress" value="true"/>
             </c:if>
             
             <c:if test="${address.lbsAddressLod != null && address.lbsAddressLod != ''}">
             <c:set var="islod" value="true"/>
             </c:if>
             
             <%-- LOD Changes LBS 1.5 --%>
             <c:choose>
                  <c:when test="${(isLbsAddress == false &&  islod == false) ||
                   (isLbsAddress == true &&  islod == false) ||
                  (isLbsAddress == true && islod == true && address.lbsAddressLod == 'Street Level')}">
                  	<div id="selectLocalAddress1">
					<ul class="form division">
						<li>
							<label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label> 
							<span><input type="text" id="physicalLocation1" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1}" class="w100" maxlength="100" /></span>
						</li>
						<li>
							<label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/> </label> 
							<span><input type="text" id="physicalLocation2" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2}" class="w100" maxlength="100" /></span>
						</li>
						<li>
						 	<label for="office"><spring:message code="claimCreate.addressInfo.label.office"/> </label> 
						 	<span><input type="text" id="physicalLocation3" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3}" class="w100" maxlength="100" /></span>
						</li>						 
					</ul>
				
					</div>
                 </c:when>
                 
                  <c:when test="${isLbsAddress==true && islod== true}">
                  
                  <div id="selectLocalAddress">
						<ul class="form division1">
							<li><label for="building"><span class="req">*</span><spring:message code="claimCreate.addressInfo.label.building"/></label> 
							<span><select name="userEnteredActivity.serviceRequest.asset.installAddress.buildingId"	id="bldng"><option value=""><spring:message code="hardware.massUploadTemplate.Building"/></option></select>
							<input type="hidden" id="physicalLocation1" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1"  value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1}" />
							</span>
							</li>
							<li><label for="floor"><span class="req">*</span><spring:message code="claimCreate.addressInfo.label.floor"/></label> 
							<span><select name="userEnteredActivity.serviceRequest.asset.installAddress.floorId" id="flr"  ><option value=""><spring:message code="hardware.massUploadTemplate.Floor"/></option></select>
							<input type="hidden" id="physicalLocation2" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2}" />
							</span>
							</li>
							
							<li><label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label> 
							<span><input type="text" name="userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3" 
							value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3}"
							id="office"  value="" style="width:144px"/></span>
							</li>
							<c:set var="showGrid" value="display:block;"/>
							<%-- <c:if test="${address.lbsAddressLod == 'Floor Level' || address.lbsAddressLod == 'Grid Level'}">
                  			</c:if>--%>
    	              		<c:if test="${address.lbsAddressLod == 'Mix - See Floor'}">
                  				<c:set var="showGrid" value="display:none;"/>
        	          		</c:if>
							
							</ul>
							<c:if test="${address.lbsAddressLod != 'Floor Level'}">
								<ul id="lod-floor-grid" style="${showGrid}" class="form division1">
								 	<li><label for="coordinateX"><span class="req">*</span>Coordinate X</label>
								 	<span> <input type="text" id="co-X" class="disable" disabled style="width:144px;background-color:#F0F0F5!important;"/></span> 
								 	<span><input type="hidden" name="${preName}.coordinatesXPreDebriefRFV" class="w100" maxlength="100" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV}" /></span>
								 	</li>
									<li><label for="coordinateY"><span class="req">*</span>Coordinate Y</label>
									<span><input type="text" id="co-Y" class="disable" disabled style="width:144px;background-color:#F0F0F5!important;"/></span>
									<span><input type="hidden" name="${preName}.coordinatesYPreDebriefRFV"  class="w100" maxlength="100" value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV}" /></span>
									</li>
									<li> <a onclick="showMapPopup()" style="cursor: pointer;"><spring:message code="hardwareDebrief.closeOut.viewGrid"/></a></li>
									<%--For LBS Adresses display names--%>
							 	</ul>	
							</c:if>
									
						
					</div>
                  
                  
                  		
                  
			 				
                 </c:when>
                 
            </c:choose>
             
           
				
											
				
				<div style="display:none">
				<%-- This tag used for hidden input fields that are set when address selected from popup. 
				<div style="display:none;">
             <util:generateAddressInputFields value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress}" index="" preName="userEnteredActivity.serviceRequest.asset.installAddress." postName=""></util:generateAddressInputFields>
            									 
            </div>
				<util:generateAddressInputFields value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress}" index="" preName="userEnteredActivity.serviceRequest.asset.installAddress." postName=""></util:generateAddressInputFields>--%>
					
					</div>
						<div style="display:none;">
						<util:generateAddressInputFields value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress}" index="" preName="userEnteredActivity.serviceRequest.asset.installAddress." postName=""></util:generateAddressInputFields>
             			</div>	
             
             
            </div>
            
          </div>
        
          <c:if test="${isHwMove}">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4 class="gray">
              <%-- <a style="cursor:pointer" class="fontBlack" title="<spring:message code="address.selectAddress"/>" id="serviceInformationMoveToAddressLink" onClick="popUpAddress(id,'userEnteredActivity.serviceRequest.asset.moveToAddress',setAddressValuesFromPopup);">
              <c:choose>
              <c:when test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveToAddress.addressLine1 }">
              <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.addressLink"/>
              </c:when>
              <c:otherwise>
              <spring:message code="address.selectAddress"/>
              </c:otherwise>
              </c:choose>
              </a> --%>
<%--               <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.moveToAddr"/> --%>
              <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.deviceInstalledAddress"/>
              </h4>
              
              <ul class="roDisplay">
                <li id="showAddress_userEnteredActivity.serviceRequest.asset.moveToAddress">
                <%--<span>
                  Store-front Name<br/>
                  Address Line 1<br />
                  Address Line 2<br />
                  City, State / Province &lt;Zip/Postal Code&gt;<br />
                  Country</span>
                   --%> 
                   <c:choose>
                  <c:when test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.addressLine1}">
                  	<%--<util:convertToGenericAddress attrName="moveToAddressDisplayServiceInfo" addressString="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress}"></util:convertToGenericAddress>
                   <util:addressOutput value="${moveToAddressDisplayServiceInfo}" displayInDivs="true"></util:addressOutput>
                    --%> 
                    <div>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.storeFrontName}</div>
                    <util:addressOutput value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress}" displayInDivs="true"></util:addressOutput>
                  </c:when>
                   <c:otherwise>
                   <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.addrNotAvl"/>
                   </c:otherwise>
                   </c:choose> 
                  </li>
              </ul>
               <ul class="form division">
                <li>
                  <label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label>
                  <span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation1}</span>
                  </li>
                <li>
                  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/></label>
                  <span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation2}</span>
                  </li>
                <li>
                  <label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label>
                  <span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation3}</span></li>
              </ul> 
            </div>
               <div style="display:none;">
                <%-- <util:generateAddressInputFields value="${moveToAddressDisplayServiceInfo}" index="" preName="userEnteredActivity.serviceRequest.asset.moveToAddress." postName=""></util:generateAddressInputFields> --%> 
                <util:generateAddressInputFields value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress}" index="" preName="userEnteredActivity.serviceRequest.asset.moveToAddress." postName=""></util:generateAddressInputFields>
            									 
            </div>
			
            </div>
            </c:if>
          </div>
        </div>
	</div>
</div>


 <script type="text/javascript">
 
 
        function urlParams(){
        	
    		this.htmlInputIds=["bldng","flr"];
    		this.defaultMessages=["<spring:message code='hardware.massUploadTemplate.Building'/>","<spring:message code='hardware.massUploadTemplate.Floor'/>"];
    		this.paramNames1=["bldng","","flr"];
    		this.paramNames=["ctry","state","cty","site","bldng","","flr","extra"];
    		
    	
    	this.setDefaultHTMlAt=function(id){
    		
    		var index=$.inArray(id,this.htmlInputIds);
    		$('#'+id).html('<option value="">'+this.defaultMessages[index]+'</option>');
    	};
    	this.setDropParamsToObj=function(){
    		for(var i=0;i<this.paramNames1.length;i++){
    			if(this.paramNames1[i]!=""){
    				this[this.paramNames1[i]]=$('#'+this.htmlInputIds[i]).val();				
    			}
    			
    		}
    	};
    	this.disableAll=function(){
    		for(var i=0;i<this.htmlInputIds.length;i++){
    			$('#'+this.htmlInputIds[i]).attr('disabled','disabled');
    		}
    	};
    	this.enableAll=function(){
    		for(var i=0;i<this.htmlInputIds.length;i++){
    			$('#'+this.htmlInputIds[i]).removeAttr('disabled');
    		}
    	};
    };
    var urlParamsObj=new urlParams();
    
    $('#bldng').change(function(){
    	urlParamsObj.setDefaultHTMlAt('flr');
    	if($(this).val()==""){
    		return;
    	}
    	
    	$('#physicalLocation1').val($('#bldng :selected').text());
    	urlParamsObj.setDropParamsToObj();	
    	$('#flr').attr('disabled',true);
    	getFloor(null);
    	
    });
    
    $('#flr').change(function(){
    	$('#physicalLocation2').val($('#flr :selected').text());
    	<%-- Changes for LBS 1.5--%>
    	<c:if test="${address.lbsAddressLod == 'Mix - See Floor'}">
    	if($('#flr :selected').attr('lod')=='Grid Level'){
    		$('#lod-floor-grid').show();	
    	}else{
    		$('#lod-floor-grid').hide();
    	}
    	</c:if>
    	
    	<%--Ends Changes for LBS 1.5--%>
    	
    });
    function getFloor(p){
    	$('#flr').attr('disabled',true);
		var ctry="${address.country}";
		urlParamsObj["ctry"]=ctry;
		var state="${address.state}";
		var county="${address.county}";
		var province="${address.province}";
		var district="${address.region}";
		if(state!=""){
		urlParamsObj["state"]=state+"^s";
		}
		else if(county!=""){
			urlParamsObj["state"]=county+"^c";	
		}
		else if(province!=""){
			urlParamsObj["state"]=province+"^p";	
		}
		else if(district!=""){
			urlParamsObj["state"]=district+"^d";
		}
		
		var addressId="${address.addressId}";
		
		var cty="${address.city}";
		urlParamsObj["cty"]=cty;
		
		var bId=jQuery("#bldng option:selected").val();
		urlParamsObj["bldng"]=bId;
		urlParamsObj["extra"]="blank";
		var url=appendURLPrams("${floorURL}&aid="+addressId,urlParamsObj);
	
		$.get(url,function(response){
			$('#flr').attr('disabled',false);
			//$('#btnContinue').attr('disabled',false);
			$('#flr').append(response);
			if(p!=null){
				$('#flr option').each(function(){
					if($(this).text()==p){
						$('#flr').val($(this).val());
						return false;
					}
				});
				
				<%-- Changes for LBS 1.5--%>
		    	<c:if test="${address.lbsAddressLod == 'Mix - See Floor'}">
		    	if($('#flr :selected').attr('lod')=='Grid Level'){
		    		$('#lod-floor-grid').show();	
		    	}
		    	</c:if>		    	
		    	<%--Ends Changes for LBS 1.5--%>
				//$('#flr').val("${address.floorId}");	
			}
			
		});	
	
};
 
function appendURLPrams(url,paramsObj){
	var urlT=url;
	for(var i=0;i<paramsObj.paramNames.length;i++){
		if(paramsObj[paramsObj.paramNames[i]]!=null && paramsObj[paramsObj.paramNames[i]]!="")
			urlT+="&"+paramsObj.paramNames[i]+"="+encodeURI(paramsObj[paramsObj.paramNames[i]]);
	}
	
	return urlT;
};

function loadCoutrySateCitySiteBuilding(addressId){
	urlParamsObj=new urlParams();
	var url=appendURLPrams("${getAllLocationURL}&frCal=true&aid="+addressId,urlParamsObj);
	urlParamsObj.disableAll();

	$.getJSON(url,function(response){
		urlParamsObj.enableAll();	
		$('#bldng').append(response.building);
		$('#bldng option').each(function(){
			if($(this).text()=="${address.physicalLocation1}"){
				$('#bldng').val($(this).val());
				return false;
			}
			
		});
		<c:if test="${address.physicalLocation2 !=null && address.physicalLocation2 !=''}">	
		getFloor("${address.physicalLocation2}");
		</c:if>
		//$('#bldng').val("${address.buildingId}");
	});

};

<c:if test="${isLbsAddress && islod}">
	loadCoutrySateCitySiteBuilding("${address.addressId}");	
</c:if>	
	
	
function successMap(obj){
	$('#co-X').val(obj.info.gridX);
	$('#co-Y').val(obj.info.gridY);
	$("input[name='${preName}.coordinatesXPreDebriefRFV']").val(obj.info.gridX);
	$("input[name='${preName}.coordinatesYPreDebriefRFV']").val(obj.info.gridY);
	mapPopupObj.closePopup();
	
}

function showMapPopup(){
	mapPopupObj.showPopup({
		"id":'',
		"buildingId":$('#bldng').val(),//"${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1}",
		"floorId":$('#flr').val(),//"${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2}",
		"successFunc":successMap,
		"encryptionURL":"${encryptJSONVar}",
		"lbsPostURL":"${hardwareDebriefForm.mapForm.formPostUrl}",
		"locale":"<%=request.getLocale()%>",
		"mdmId":"${hardwareDebriefForm.userEnteredActivity.customerAccount.accountId}",
		"mdmLevel":"Siebel",
		"emailAddress":"${hardwareDebriefForm.mapForm.emailaddress}"
	});
	return false;
}
</script>