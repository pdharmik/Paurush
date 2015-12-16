<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<portlet:resourceURL var="encryptJSONVar" id="encryptJSON"></portlet:resourceURL>
<script type="text/javascript" src="<html:rootPath/>js/LbsService.js?version=3.74"></script>



	<div id="popupMapDiv" title="Basic dialog" style="display:none">
   		<jsp:include page="/WEB-INF/jsp/fleetmanagement/maps-iframe.jsp"/>
   		
	</div>
	
	<script>
	var popupVariable = null;
	var flag = null;
	function openPopupMap(action,mdmId,mdmLevel,formPost,emailId,buildingId,floorId){
		flag=action;
		popupVariable = jQuery('#popupMapDiv').dialog({
			autoOpen: false,
				modal: true,
				height: 600,
				width: 700,
				position: ['center','top'],
				open: function(){
						 jQuery('#popupMapDiv').show();
						 
						 
						    var accountInfoSiebel=new AccountInformation();
							accountInfoSiebel.setSiebelFieldValues(mdmId,mdmLevel);
							var siebelJSON=new SiebelJSON();
							var filtersObj=new Filters();
							siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
							siebelJSON.setPostMessageURL(location.href);
							siebelJSON.setMview();
							siebelJSON.optionalParameters.showGrid=true;
							siebelJSON.optionalParameters.suppressAssets=true;
							siebelJSON.optionalParameters.gridClick=true;
							siebelJSON.setFilters(filtersObj);
							siebelJSON.defaultArea={};
							siebelJSON.defaultArea.buildingId=buildingId;
							siebelJSON.defaultArea.floorId=floorId;
							siebelJSON.setQueryId(emailId);
							encryptionObj.setEncryptLBSFormPostURL(formPost+"?accountChange=true&lang=<%=request.getLocale()%>");
							console.log(JSON.stringify(siebelJSON,checkValue));
							encryptionObj.setSiebelJSONString(JSON.stringify(siebelJSON,checkValue));
							encryptionObj.setEncryptionFlag(true);
						  	if(encryptionObj.getEncryptionFlag()==true){
							  $('#uam-mps-map-form').attr('action',encryptionObj.getEncryptLBSFormPostURL());
							  $.ajax({
									url:"${encryptJSONVar}&t="+new Date().getTime(),
									type:'POST',
									data:{"jsonString":encryptionObj.getSiebelJSONString()},
									success: function(response){
										$("#uam-mps-map-input").val(response);
										$("#uam-mps-map-form").submit();
										hideOverlay();
										},
								  failure: function(results){}
								});
							  encryptionObj.setEncryptionFlag(false);
						  }else{
							  $('#uam-mps-map-form').attr('action',encryptionObj.getLBSFormPostURL());
							  $("#uam-mps-map-input").val(JSON.stringify(siebelJSON,checkValue));
							  $("#uam-mps-map-form").submit();
						     //alert("Submitted");
						
						  }
						jQuery('span.ui-dialog-title').text("Map");
		  			},
				close: function(event,ui){
				  	
					hideOverlay();
					popupVariable=null;
					
				  	}
		});
					jQuery(this).scrollTop(0);
		  			popupVariable.dialog('open');
		  			
		  		
	
		
	}
	
	function closePopupMap(){
		popupVariable.dialog('close');
	}
	ResponseProcessor.prototype.processResponse=function (){

		var curURL = window.location.href;
		if(curURL.indexOf('/fleet-management') != -1 && flag === "installed" && pageFlow.toLowerCase() != "addone")
			return;
		
		if(this.jsonResp.action=="gridClick" && this.jsonResp.item=='building'){
			coordinates(this.jsonResp.info.gridX,this.jsonResp.info.gridY,flag);
			closePopupMap();
		}	
	};
	
	var respProcessorObj=new ResponseProcessor();
		var lbs=new LBS();
		lbs.setResponseProcessor(respProcessorObj);
		lbs.attachListner();
	

</script>
