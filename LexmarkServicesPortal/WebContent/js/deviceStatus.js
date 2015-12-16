/**
 * 
 */

function DeviceStatus(){
	var that=this;
		function Alerts(sMs,hMs){
		
			this.suppliesMultiSelect=sMs;
			this.hardwareMultiSelect=hMs;
			
			
			var thatAlerts=this;
			this.clearSupplies=function(flag){
				$('#alerts_sup_dev').val('');
				$('#alertsSuppliesStatusRadioFlag').attr('checked',false);
				$('#alertsSuppliesStatusDescriptionContent').html('');
				$('#alertsSuppliesStatusDescription').hide();
			    adjustLeftNav();
				this.suppliesMultiSelect.clear();
				that.checkAndSetRadio();
				if(flag===true)
					that.postJSON();
				checkboxReset();
			};
			this.clearDevice=function(flag){
				$('#alerts_sup_dev').val('');
				$('#alertsDeviceStatusRadioFlag').attr('checked',false);
				$('#alertsDeviceStatusDescriptionContent').html('');
				$('#alertsDeviceStatusDescription').hide();
			    adjustLeftNav();
				this.hardwareMultiSelect.clear();
				that.checkAndSetRadio();
				if(flag===true)
					that.postJSON();
				checkboxReset();
			};
			this.getValue=function(){
				if(this.suppliesMultiSelect.getValue()!=null){
				return {
						"category":"Supplies",
						"details":this.suppliesMultiSelect.getValue()				
					   };
				}
				else if(this.hardwareMultiSelect.getValue!=null){
					return{
						"category":"Device",
						"details":this.hardwareMultiSelect.getValue()
					}
				}
				else{
					return null;
				}
				
			};
			this.onSuppliesSelect=function(objData){
				//Remember it is being called from Multiselect use that
				//select the radio & device Status 
				//Post json
				var flag=false;
				//this. refers to Multi Select!!
				if(this.getValue()!=null){
					flag=true;	
				}
				else{					
					deviceStatus.__alerts.clearSupplies(true);
				}
				$('#alertsSuppliesStatusRadioFlag').attr('checked',flag);

				deviceStatus.__alerts.clearDevice(false);
				deviceStatus.__expirationStatus.clear(false);
				deviceStatus.__reportingStatus.clear(false);
				deviceStatus.__utilization.clear(false);
				
				that.checkAndSetRadio();
				that.postJSON();
			};
			this.onDeviceSelect=function(objData){
				//Remember it is being called from Multiselect use that
				//select the radio & device Status 
				//Post json
				var flag=false;
				//this. refers to Multi Select!!
				if(this.getValue()!=null){
					flag=true;	
				}
				else{
					deviceStatus.__alerts.clearDevice(true);
				}
				$('#alertsDeviceStatusRadioFlag').attr('checked',flag);
				deviceStatus.__alerts.clearSupplies(false);
				deviceStatus.__expirationStatus.clear(false);
				deviceStatus.__reportingStatus.clear(false);
				deviceStatus.__utilization.clear(false);				
				
				that.checkAndSetRadio();
				that.postJSON();
			};
		};
		
		function Utilization(uMs){
			this.utilizationMultiSelect=uMs;
			this.clear=function(flag){
				this.utilizationMultiSelect.clear();
				$('#utilizationStatusRadioFlag').attr('checked',false);
				that.checkAndSetRadio();
				$('#utilizationStatusDescriptionContent').html('');
				$('#utilizationStatusDescription').hide();
			    adjustLeftNav();
				if(flag===true)
					that.postJSON();
				checkboxReset();
			};
			this.getValue=function(){
				if(this.utilizationMultiSelect.getValue()==null){
					return null;
				}
				return {
						"category":"Utilization",
						"details":this.utilizationMultiSelect.getValue()				
					   };
			};
			this.onSelect=function(objData){
				//select the radio & device Status 
				//Post json
				var flag=false;
				if(this.getValue()!=null){
					flag=true;
				}
				else{
					deviceStatus.__utilization.clear(true);
				}
				$('#utilizationStatusRadioFlag').attr('checked',flag);
				deviceStatus.__alerts.clearSupplies(false);
				deviceStatus.__alerts.clearDevice(false);
				deviceStatus.__expirationStatus.clear(false);
				deviceStatus.__reportingStatus.clear(false);
				that.checkAndSetRadio();
				that.postJSON();
			};
			this.selectChange=function(value){
				this.onSelect();
			};
		};
		
		function ReportingStatus(rMs){
			this.reportingMultiSelect=rMs;
			this.clear=function(flag){
				this.reportingMultiSelect.clear();
				$('#reportingStatusRadioFlag').attr('checked',false);
				that.checkAndSetRadio();
				$('#reportingStatusDescriptionContent').html('');
				$('#reportingStatusDescription').hide();
			    adjustLeftNav();
				if(flag===true)
					that.postJSON();
				checkboxReset();
			};
			this.getValue=function(){
				if(this.reportingMultiSelect.getValue()==null){
					return null;
				}
				return {
						"category":"Reporting",
						"details":this.reportingMultiSelect.getValue()						
					   }; 
				
			};
			this.onSelect=function(objData){
				var flag=false;
				if(this.getValue()!=null){
					flag=true;
				}
				else{
					deviceStatus.__reportingStatus.clear(true);
				}
				$('#reportingStatusRadioFlag').attr('checked',flag);
				deviceStatus.__alerts.clearSupplies(false);
				deviceStatus.__alerts.clearDevice(false);
				deviceStatus.__expirationStatus.clear(false);
				deviceStatus.__utilization.clear(false);
				that.checkAndSetRadio();
				that.postJSON();
			};
			this.selectChange=function(value){
				this.onSelect();
			};
		};
		
		function ExpirationStatus(eMS){
			this.expirationMultiSelect=eMS;
			this.clear=function(flag){
				this.expirationMultiSelect.clear();
				$('#expiredStatusRadioFlag').attr('checked',false);
				that.checkAndSetRadio();
				$('#expiredStatusDescriptionContent').html('');
				$('#expiredStatusDescription').hide();
			    adjustLeftNav();
				if(flag===true)
					that.postJSON();
				checkboxReset();
				
			};
			this.getValue=function(){
				if(this.expirationMultiSelect.getValue()==null){
					return null;
				}
				return {
						"category":"Expired",
						"details":this.expirationMultiSelect.getValue()						
					   };		
			};
			this.onSelect=function(objData){
				var flag=false;
				if(this.getValue()!=null){
					flag=true;
				}
				else{
					deviceStatus.__expirationStatus.clear(true);
				}
				$('#expiredStatusRadioFlag').attr('checked',flag);
				deviceStatus.__alerts.clearSupplies(false);
				deviceStatus.__alerts.clearDevice(false);
				deviceStatus.__reportingStatus.clear(false);
				deviceStatus.__utilization.clear(false);
				that.checkAndSetRadio();
				that.postJSON();
			};
			this.selectChange=function(value){
				this.onSelect();
			};
		};
		
		
		
		this.clearAll=function(){
			this.__alerts.clearSupplies(false);
			this.__alerts.clearDevice(false);
			this.__utilization.clear(false);
			this.__reportingStatus.clear(false);
			this.__expirationStatus.clear(false);
		
			checkboxReset();
			this.postJSON();
		};
		
		this.clearApplyFilter=function(){
			deviceStatus.__alerts.clearSupplies(false);
			deviceStatus.__alerts.clearDevice(false);
			deviceStatus.__reportingStatus.clear(false);
			deviceStatus.__utilization.clear(false);
			deviceStatus.__expirationStatus.clear(false);
			that.checkAndSetRadio();
		};
		
		this.postJSON=function(){
			deviceStatusJSON=[];//variable declared in defaultView.jsp
			
						
			var values=this.__alerts.getValue();
			if(values!=null && values.details && values.details != null)
				deviceStatusJSON.push(values);
			
			values=this.__utilization.getValue();
			if(values!=null)
				deviceStatusJSON.push(values);
			
			values=this.__reportingStatus.getValue();
			if(values!=null)
				deviceStatusJSON.push(values);
			
			values=this.__expirationStatus.getValue();
			if(values!=null)
				deviceStatusJSON.push(values);
			
			
			
			var obj={
					"action":"highlight",
					"item":"asset",
					"info":deviceStatusJSON
					};
			console.log(JSON.stringify(obj));
			lbs.postMessage(obj);
		};
		
		
		this.inIt=function(){
			this.__alerts=new Alerts(alertSuppliesObj,alertDeviceObj);
			this.__utilization=new Utilization(utilizationObj);
			this.__reportingStatus=new ReportingStatus(reportingObj);
			this.__expirationStatus=new ExpirationStatus(expirationObj);
		};
		
				
		this.checkAndSetRadio=function(){
			var deviceStatusArr=["reportingStatusRadioFlag","alertsSuppliesStatusRadioFlag","alertsDeviceStatusRadioFlag"];
			var deviceUTArr=["expiredStatusRadioFlag","utilizationStatusRadioFlag"];
			for(var i in deviceStatusArr){
				if($('#'+deviceStatusArr[i]).prop('checked')){
					$('#mapDeviceStatusRadioFlag').attr('checked',true);
					$('#mapDeviceUTRadioFlag').attr('checked',false);
					radioReset();
					return;
				}
			}
			for(var i in deviceUTArr){
				if($('#'+deviceUTArr[i]).prop('checked')){
					$('#mapDeviceUTRadioFlag').attr('checked',true);
					$('#mapDeviceStatusRadioFlag').attr('checked',false);
					radioReset();
					return;
				}
			}
			
			$('#mapDeviceStatusRadioFlag,#mapDeviceUTRadioFlag').attr('checked',false);
			radioReset();
		};
		
		this.processDeviceStatus=function(respObj){
					
			if(respObj.Supplies && respObj.Supplies.length!=0){
				$('#alertsSuppliesStatusDescriptionContent').html(templateDeviceAlert({'alerts':respObj.Supplies}));	
				$('#alertsSuppliesStatusDescription').show();
			}else if(respObj.Device && respObj.Device.length!=0){
				$('#alertsDeviceStatusDescriptionContent').html(templateDeviceAlert({'alerts':respObj.Device}));
				$('#alertsDeviceStatusDescription').show();
			}else{
				$('#alertsDeviceStatusDescriptionContent,#alertsSuppliesStatusDescriptionContent').html('');
				$('#alertsDeviceStatusDescription,#alertsSuppliesStatusDescription').hide();
			}
			if(respObj.Utilization){
				$('#utilizationStatusDescriptionContent').html(templateDeviceUtil(respObj));
				$('#utilizationStatusDescription').show();
			}else{
				$('#utilizationStatusDescriptionContent').html('');
				$('#utilizationStatusDescription').hide();
			}
			if(respObj.Reporting){
				$('#reportingStatusDescriptionContent').html(templateDeviceRprtStat(respObj));
				$('#reportingStatusDescription').show();
			}else{
				$('#reportingStatusDescriptionContent').html('');
				$('#reportingStatusDescription').hide();
			}
				
			if(respObj.Expired){
				$('#expiredStatusDescriptionContent').html(expirationStat(respObj));
				$('#expiredStatusDescription').show();
			}else{
				$('#expiredStatusDescriptionContent').html('');
				$('#expiredStatusDescription').hide();
			}
		    adjustLeftNav();
				
			
		};
		
		this.clearAllHtml=function(){
			$('#alertsSuppliesStatusDescriptionContent,#alertsDeviceStatusDescriptionContent,#utilizationStatusDescriptionContent,#reportingStatusDescriptionContent,#expiredStatusDescriptionContent').html('');
			$('#alertsSuppliesStatusDescription,#alertsDeviceStatusDescription,#utilizationStatusDescription,#reportingStatusDescription,#expiredStatusDescription').hide();
		    adjustLeftNav();
		};
	};

	
	

var alertSuppliesObj,alertDeviceObj,utilizationObj,reportingObj,expirationObj;
	function initDeviceStatus(){
		alertSuppliesObj=new MultiSelect({
			"elementId":'alert_supplies',
			"elementWidth":widthAuto,
			"dataList":alertSuppliesArray		
		});

		alertDeviceObj=new MultiSelect({
				"elementId":'alert_device',
				"elementWidth":widthAuto,
				"dataList":alertDeviceArray
				
			});
		utilizationObj=new MultiSelect({
			"elementId":'utilization_pages',
			"elementWidth":widthAuto,
			"dataList":utilizationArray
			
		});
		
		reportingObj=new MultiSelect({
			"elementId":'reporting_status',
			"elementWidth":widthAuto,
			"dataList":reportingStatArray
			
		});
		
		expirationObj=new MultiSelect({
			"elementId":'expiration_status',
			"elementWidth":widthAuto,
			"dataList":expirationArray
			
		});
		
		deviceStatus.inIt();
		
		alertSuppliesObj.setClickFunction(deviceStatus.__alerts.onSuppliesSelect);
		alertDeviceObj.setClickFunction(deviceStatus.__alerts.onDeviceSelect);
		
		utilizationObj.setClickFunction(deviceStatus.__utilization.onSelect);
		reportingObj.setClickFunction(deviceStatus.__reportingStatus.onSelect);
		expirationObj.setClickFunction(deviceStatus.__expirationStatus.onSelect);
	}
	var deviceStatus=new DeviceStatus();
	var templateDeviceAlert,templateDeviceUtil,templateDeviceRprtStat,expirationStat;
	YUI().use('handlebars', 'node-base', function (Y) {
		 Y.on('domready', function () {
			 	templateDeviceAlert = Y.Handlebars.compile(Y.one('#device-Alert-Details').getHTML());
	    	 	templateDeviceUtil=Y.Handlebars.compile(Y.one('#device-utilization').getHTML());
	    	 	templateDeviceRprtStat=Y.Handlebars.compile(Y.one('#device-report-Status').getHTML());
	    	 	expirationStat=Y.Handlebars.compile(Y.one('#expiration-Alert-Details').getHTML());
	    	 	
	    	 	//deviceStatus.processDeviceStatus(mock.info);//Added temporarily 
	    	 	
		 });
		 Y.Handlebars.registerHelper('helpLocalizeAlerts', function (val) {
			 	if(alertTypeCodesLOV[val]){
			 		return alertTypeCodesLOV[val];
			 	}else
			 		return val;
			 
		 });
		 		 
		 Y.Handlebars.registerHelper('localizeDateDevice', function (dateVal) {
			 if(dateVal!=null)
				 return localizeDateTimeFromInputDate(dateVal);
			 else 
				 return "";
			});
		 
		 Y.Handlebars.registerHelper('localizeExpiredDate', function (dateVal) {
			 if(dateVal!=null)
				 return formatDateToDefaultLBS(dateVal);
			 else 
				 return "";
			});
	});
	
	
	