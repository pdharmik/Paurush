ResponseProcessor.prototype.processResponse=function (){
			if(this.jsonResp.action=="gridClick" && this.jsonResp.item=='building'){
				mapPopupObj.processResponse(this.jsonResp.info);
			}	
};


function MapPopup(){
	var that=this;
	this.popupObj=null;
	this.postJSON=function(){
		
		encryptionObj.setEncryptLBSFormPostURL(this.params.lbsPostURL+"?accountChange=true&lang="+this.params.locale);
		var accountInfoSiebel=new AccountInformation();
		accountInfoSiebel.setSiebelFieldValues(this.params.mdmId,this.params.mdmLevel);

		var filtersObj=new Filters();

		var siebelJSON=new SiebelJSON();
		//Added for View grid
		siebelJSON.optionalParameters.showGrid=true;
		siebelJSON.optionalParameters.suppressAssets=true;
		siebelJSON.optionalParameters.gridClick=true;
		//Ends added for view Grid
		siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
		siebelJSON.setPostMessageURL(location.href);
		siebelJSON.setFilters(filtersObj);
		siebelJSON.setMview();
		siebelJSON.setQueryId(this.params.emailAddress);

		siebelJSON.defaultArea={};
		siebelJSON.defaultArea.buildingId=this.params.buildingId;//"1-NTD3WVP";
		if(this.params.floorId !=""){
			siebelJSON.defaultArea.floorId=this.params.floorId;	
		}

		encryptionObj.setSiebelJSONString(JSON.stringify(siebelJSON,checkValue));
		 $('#uam-mps-map-form').attr('action',encryptionObj.getEncryptLBSFormPostURL());
			  $.ajax({
					url:this.params.encryptionURL+"&t="+new Date().getTime(),
					type:'POST',
					data:{"jsonString":encryptionObj.getSiebelJSONString()},
					success: function(response){
						$("#uam-mps-map-input").val(response);
						$("#uam-mps-map-form").submit();
					hideOverlay();
						},
				  failure: function(results){}
				});
			  

	};
	this.processResponse=function(info){
		this.params.successFunc({
			"id":that.params.id,
			"info":info			
		});
	};
	
	this.showPopup=function(params){
		this.params=params;
		
		if(this.popupObj!=null){
			this.openPop();
			return;
		}
		
		this.popupObj=$('#popup-Map-html').dialog({
			autoOpen: false,
			modal: true,
			height: 460,
			width: 700,
			position: ['center','top'],
			open: function(){
				$('#popup-Map-html').show();
				},
			close: function(event,ui){}
		
		});
		
		this.openPop();
	};
	this.openPop=function(){
		this.popupObj.dialog('open');
		this.postJSON();
	};
	this.closePopup=function(params){
		this.popupObj.dialog('close');
	};

}

var mapPopupObj=new MapPopup();


var respProcessorObj=new ResponseProcessor();
var lbs=new LBS();
lbs.setResponseProcessor(respProcessorObj);
lbs.attachListner();