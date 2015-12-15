/**
 * Used for LBS 
 * classes and services
 */
function LBSAddress(){};
if(!(typeof [].indexOf === 'function')){
	Array.prototype.indexOf=function(searchVal){
		if(this == null){
			throw new TypeError(" this is null");
		}
		for(var i=0;i<this.length;i++){
			if(this[i]==searchVal){
				return i;
			}
		}
		return -1;
	};
}

function checkValue(key,value){
	
	if(value==null){
		return undefined;		
	}else if(typeof value == "string" && value == ""){
		return undefined;
	}else{
		return value;
	}
}

function _getiVal(id){
	//allhtmlTextIdsObj this is declared in mapFilters.jsp
	for(fields in allhtmlTextIdsObj){
		if(jQuery('#'+id).val()==allhtmlTextIdsObj[fields])
			return null;
	}
	return jQuery('#'+id).val();
}

function _getiValContext(parentSelector,selctor,allhtmlTextIdsPref){
	var val=$('#'+parentSelector+selctor).val();
	
	if(val==allhtmlTextIdsPref[selctor]){
		return null;
	}
	return val;
	
	
}

function _getiText(id){
	
	return jQuery('#'+id+' option:selected').text();
}
function replaceAll(source,replaceTarget,replaceWith){
	
		while(source.indexOf(replaceTarget)>0)
			source=source.replace(replaceTarget,replaceWith);

		return source;
	}

function AccountFields(){
	this.siebelFieldNames=["GlobalDUNSAccountNumber","DomesticDUNSAccountNumber","LegalAccountNumber","MDMAccountNumber","AccountId"];
	this.dbFieldNames=["gbDnNm","doDnNm","lgacNm","mdAcNm","acId"];	
	this.siebelLevels=["Global","Domestic","Legal","Account","Siebel"];
	
};

var lbsBuildingDetails=new LBSAddress();// This contains the address sent by LBS
var lbsBuildingDetailsTo=new LBSAddress();// This contains the address sent by LBS when the asset is dragged

var accfields=new AccountFields();
function AccountInformation(){};
AccountInformation.prototype={
		setSiebelFieldValues:function(mdmId,mdmLevel){
			this[accfields.siebelFieldNames[accfields.siebelLevels.indexOf(mdmLevel)]]=mdmId;			
		},
		setSiebelAccountName:function(accNm){this["AccountName"]=accNm;},
		setSiebelAccountId:function(accId){this["AccountId"]=accId;},
		
		setDBAccountName:function(accNm){this["accNm"]=accNm;},
		setDBAccountId:function(accId){this["acId"]=accId;},
		
		setDBFieldValues:function(mdmId,mdmLevel){
			this[accfields.dbFieldNames[accfields.siebelLevels.indexOf(mdmLevel)]]=mdmId;			
		}
};

function DeviceFields(){
	//These fields are for SIEBL JSON param names
	this.siebelFieldNames=["AssetSerialNumber","IPAddress","ProductModel",
	                       "ProductName","InstallStartDate","InstallEndDate","ProductModelType",
	                       "ProductType","ProductSeries" ,"ProductBrand",
	                       "CostCenter","DepartmentName",
	                       "CustomerAssetTag","HostName",
	                       "AssetLifeCycle","DevicePhase","AssetMeterSource","LXKAssetTag","HardwareStatus","mdmID"];
	//These are JSON field names for storing in DB in portal
	 this.dbFieldNames=    ["srNm","ipAddr","prmdl",
	                   	                       "prNm","instDtF","instDtT","mdTyp","prTyp",
	                   	                       "prSr","br","coctr","dpt",
	                   	                       "astg","hstNm","asLf","dvPh","mRdType","lAstg","hwSt","mId"];
	 //These fields are HTML Input ids for Device filter
	 this.deviceIdInputIds=["serialNumber","ipAddress","productModel",
	                        "assetProduct" ,"installDateFrom","installDateTo","modelType",
	                        "productType-options","productSeries-options","brand",
	                        "costCenterAsset","departmentAsset",
	                        "assetTag","deviceHostName","assetLifeCycle-options","devicePhase-options","meterReadTypeAsset",
	                        "LXKAssetTag","hardwareStatus-options","mdmID"];
	//These fields we will get from AMIND to show on left nav (View Additional Information)
	 //The fields name should match at backend as these fields will be provided as JSON params!!!!!!
	 /*this.deviceFieldsLeftNavAMIND=["assetTag","accountName","deviceHostname","devicePhase","costCenter","assetProduct",
	                           "assetInstallDate","modelTypeFamily","trdPartyDevices","productType",
	                           "productSeries","brand","colorMono","singleMFP","ownership",
	                           "department","meterReadType","firstName","lastName","workPhone","alternatePhone","email"];*/
	 
};

var deviceFields=new DeviceFields();
function DeviceInformation(){};
DeviceInformation.prototype={
		setSiebelFieldValues:function(){//This method is for setting Siebel DB params
			for(var i=0;i<deviceFields.deviceIdInputIds.length;i++){
				this[deviceFields.siebelFieldNames[i]]=_getiVal(deviceFields.deviceIdInputIds[i]);
			}
			
		},
		setDBFieldValues:function(){//This method is for setting Local DB params
			for(var i=0;i<deviceFields.deviceIdInputIds.length;i++){
				this[deviceFields.dbFieldNames[i]]=_getiVal(deviceFields.deviceIdInputIds[i]);
			}
		},
		/*pC=parent Context either  class or id*/
		setEditPrefPopVals:function(pC,allhtmlTextIdsPref){
			for(var i=0;i<deviceFields.deviceIdInputIds.length;i++){
				
				this[deviceFields.dbFieldNames[i]]=_getiValContext(pC,deviceFields.deviceIdInputIds[i],allhtmlTextIdsPref);
			}
		},
		setSiebelDataFromDBObj:function(obj){
			for(field in obj){
				this[deviceFields.siebelFieldNames[deviceFields.dbFieldNames.indexOf(field)]]=obj[field];
			}
		}
		
};



function SrRequestFields(){
	/* order of the siebelFieldNames and dbFiledNames and deviceInInputIds are important */
	this.siebelFieldNames=["SRNumber","SRType","WebStatus","SRCreatedStartDate","SRCreatedEndDate","HelpDeskRefNum","SRArea","SRSubArea","SRStatus","SRSubStatus","SRSource"];
	this.dbFieldNames=["reqNo","reqType","reqStat","stDt","edDt","hpDskRf","srAr","srSuAr","srSt","srSuSt","srSrc"];	
	this.srInputIds=["requestNo","requestType-options","requestStatus-options","startDateFilter","endDateFilter","customerRef","areaDrop-options","subAreaDrop-options","srOPSStatus-options","srSubStatus-options","srSource-options"];
	
};

var srFields=new SrRequestFields();
function SrRequestInformation(){};
SrRequestInformation.prototype={
		setSiebelFieldValues:function(){//This method is for setting Siebel DB params
			for(var i=0;i<srFields.srInputIds.length;i++){
				this[srFields.siebelFieldNames[i]]=_getiVal(srFields.srInputIds[i]);
			}
			
		},
		setDBFieldValues:function(){//This method is for setting Local DB params
			for(var i=0;i<srFields.srInputIds.length;i++){
				this[srFields.dbFieldNames[i]]=_getiVal(srFields.srInputIds[i]);
			}
		},
		getSrType:function(){
			return this["SRType"];
		},
		getSrTypeString:function(){
			return "SRType";
		},
		/*pC=parent Context either  class or id*/
		setEditPrefPopVals:function(pC,allhtmlTextIdsPref){
			for(var i=0;i<srFields.srInputIds.length;i++){
				this[srFields.dbFieldNames[i]]=_getiValContext(pC,srFields.srInputIds[i],allhtmlTextIdsPref);
			}
		},
		setSiebelDataFromDBObj:function(obj){
			for(field in obj){
				this[srFields.siebelFieldNames[srFields.dbFieldNames.indexOf(field)]]=obj[field];
			}
		}
		
};

function AddressFields(){
	/* order of the siebelFieldNames and dbFiledNames and deviceInInputIds are important */
	this.siebelFieldNames=["InstalledAddressId","City","Country","State","Province","Zipcode","Site","Building","Floor","AddressLine1","AddressLine2","Zone","BuildingType"];
	this.dbFieldNames=    ["aId","cty","ctry","state","prvnc","poCde","sNm","bNm","fNm","aLine1","aLine2","zn","bTyp"];	
	this.htmlInputIds=    ["","selectCity","selectCountry","selectState","","","site","bldng","flr","","","zone","bldngType"];
};

var addressFields=new AddressFields();
function AddressInformation(){};
AddressInformation.prototype={
		setSiebelFieldValues:function(addressPopupValues,isCalledFrmDB){//This method is for setting Siebel DB params
			for(field in addressPopupValues){
				
				this[addressFields.siebelFieldNames[addressFields.dbFieldNames.indexOf(field)]]=addressPopupValues[field];
			}
			/*for(i=0;i<addressFields.htmlInputIds.length;i++){
				this[addressFields.siebelFieldNames[addressFields.dbFieldNames.indexOf(addressFields.htmlInputIds[i].replace("_",""))]]=_getiVal(addressFields.htmlInputIds[i]);
			}*/
			/* isCalledFrmDB flag is used to check whether the settings are from DB or from the Front end
			 * isCalledFrmDB = False means apply filter button clicked and read from dropdown
			 * isCalledFrmDB = True means read from addressPopupValues i.e DB  
			 * */
			if(!isCalledFrmDB){
				this["Country"]=_getiVal("selectCountry");
				this["State"]=_getiVal("selectState");
				this["City"]=_getiVal("selectCity");
				
			//	this["SiteId"]=_getiVal("site");
				this["SiteName"]=_getiVal("site")==""?null:_getiText("site");
				
				//this["BuildingId"]=_getiVal("bldng");
				this["BuildingName"]=_getiVal("bldng")==""?null:_getiText("bldng");
				
			//	this["FloorId"]=_getiVal("flr");
				this["FloorName"]=_getiVal("flr")==""?null:_getiText("flr");
				
			//	this["ZoneId"]=_getiVal("zone");
				this["ZoneName"]=_getiVal("zone")==""?null:_getiText("zone");
				this["BuildingType"]=_getiVal("bldngType")==""?null:_getiText("bldngType");
			}
			
			
		},
		setDBFieldValues:function(addressPopupValues,isCalledFrmDB){//This method is for setting Local DB params
			for(field in addressPopupValues){
				
				this[addressFields.dbFieldNames[addressFields.dbFieldNames.indexOf(field)]]=addressPopupValues[field];
			}
			/*for(i=0;i<addressFields.htmlInputIds.length;i++){
				this[addressFields.htmlInputIds[i].replace("_","")]=_getiVal(addressFields.htmlInputIds[i]);
			}*/
					
			/* isCalledFrmDB flag is used to check whether the settings are from DB or from the Front end
			 * isCalledFrmDB = False means apply filter button clicked and read from dropdown
			 * isCalledFrmDB = True means read from addressPopupValues i.e DB  
			 * */
			if(!isCalledFrmDB){
				this["ctry"]=_getiVal("selectCountry");
				this["state"]=_getiVal("selectState");
				this["cty"]=_getiVal("selectCity");
				
				//this["sId"]=_getiVal("site");
				this["sNm"]=_getiVal("site");//==""?null:_getiText("site");
				
				//this["bId"]=_getiVal("bldng");
				this["bNm"]=_getiVal("bldng");//==""?null:_getiText("bldng");
				
				//this["fId"]=_getiVal("flr");
				this["fNm"]=_getiVal("flr");//==""?null:_getiText("flr");
				
				//this["znId"]=_getiVal("zone");
				this["zn"]=_getiVal("zone");//==""?null:_getiText("zone");
				this["bTyp"]=_getiVal("bldngType");//==""?null:_getiText("zone");
			}
		},
		/*pC=parent Context either  class or id*/
		setEditPrefPopVals:function(addressPopupValues,pC,allhtmlTextIdsPref){
			for(field in addressPopupValues){
				this[addressFields.dbFieldNames[addressFields.dbFieldNames.indexOf(field)]]=addressPopupValues[field];				
			}
			
			this["ctry"]=_getiValContext(pC,"selectCountry",allhtmlTextIdsPref);
			this["state"]=_getiValContext(pC,"selectState",allhtmlTextIdsPref);
			this["cty"]=_getiValContext(pC,"selectCity",allhtmlTextIdsPref);
			this["sNm"]=_getiValContext(pC,"site",allhtmlTextIdsPref);
			this["bNm"]=_getiValContext(pC,"bldng",allhtmlTextIdsPref);			
			this["fNm"]=_getiValContext(pC,"flr",allhtmlTextIdsPref);			
			this["zn"]=_getiValContext(pC,"zone",allhtmlTextIdsPref);
			this["bTyp"]=_getiValContext(pC,"bldngType",allhtmlTextIdsPref);//==""?null:_getiText("zone");
			
			
		},
		setSiebelDataFromDBObj:function(obj){
			for(field in obj){
				this[addressFields.siebelFieldNames[addressFields.dbFieldNames.indexOf(field)]]=obj[field];
			}
		}
		
};

function  SiebelMviewInformation(){
	this.mviewNames=["LBS_Asset","LBS_Asset_SR","LBS_Asset_SR","LBS_Asset_SR_Order","LBS_Asset_SR_Activity"];
	this.fieldAvailableFlags=["Asset","SR","AssetSR","AssetSROrder","AssetSRActivity"];
};
var mviewInfo=new SiebelMviewInformation();

function optSiebelParam(){
	this.displayAlertIcon=false;
	this.displayAssetLifeCycle=true;
}
function SiebelJSON(){
	this.optionalParameters=new optSiebelParam();
	this.applicationId= "lex_lbs_internalportal";
};
SiebelJSON.prototype={
		setMdmIDMdmLevel:function(accountInformation){
			this["iframeHeight"]=getIframeHeight();
			for(field in accountInformation){
				if(accountInformation[field]!=null && accountInformation[field]!=""){
					this[field]=accountInformation[field];
				}
					
			}
		},
		
		setPostMessageURL:function(url){
			this["postMessageUrl"]=url; 
		},
		setMview:function(){
			var data="";
			if(isPresentinObj(this["filters"],deviceFields.siebelFieldNames)){
				data+="Asset";
			}if(isPresentinObj(this["filters"],srFields.siebelFieldNames)){
				data+="SR";
			}
			if(data==""){
				data="Asset";
			}
			this["mview"]=mviewInfo.mviewNames[mviewInfo.fieldAvailableFlags.indexOf(data)];
			
		}, 
		setFilters:function(filtersObj){
			this["filters"]=filtersObj;
			
		},
		setDisplayAlertIcon:function(v){
			this["displayAlertIcon"]=v;
		},
		setQueryId:function(v){
			
			var d = new Date(),dformat = [d.getMonth()+1,d.getDate(),d.getFullYear()].join('/')+' '+[d.getHours(),d.getMinutes(),d.getSeconds()<10?("0"+d.getSeconds()):d.getSeconds()].join(':');
			this["QueryID"]=v+dformat;
			this.username=v;
		},
		setDefaultArea:function(s){
			this["defaultArea"]=JSON.parse(s);
		},
		setLanguage:function(l){
            this["lang"]=l;
        }
		
};



function chlFields(){
	/* order of the siebelFieldNames and dbFiledNames and deviceInInputIds are important */
	this.siebelFieldNames=["CHLId","CHLName"];
	this.dbFieldNames=["clvl","cNm"];	
	this.htmlInputIds=["chlNodeId","chlNodeValue"];
	
};
var chlInformation=new chlFields();
function chl(){};
chl.prototype={
		setSiebelFieldValues:function(){
			for(var i=0;i<chlInformation.htmlInputIds.length;i++){
				this[chlInformation.siebelFieldNames[i]]=_getiVal(chlInformation.htmlInputIds[i]);
			}
		},
		setDBFieldValues:function(){
			for(var i=0;i<chlInformation.htmlInputIds.length;i++){
				this[chlInformation.dbFieldNames[i]]=_getiVal(chlInformation.htmlInputIds[i]);
			}
		},
		setL5UserInformation:function(a){
			this["CHLDescription"]=a;
		},
		setDBL5UserInformation:function(a){
			this["clvl"]=a;
		},
		setEditPrefPopVals:function(pC,allhtmlTextIdsPref){
			for(var i=0;i<chlInformation.htmlInputIds.length;i++){
				this[chlInformation.dbFieldNames[i]]=_getiValContext(pC,chlInformation.htmlInputIds[i],allhtmlTextIdsPref);
			}
		}
};

var srFields=new SrRequestFields();

function isPresentinObj(object,arrayTobeSearched){
	for(field in object){
		if(arrayTobeSearched.indexOf(field)!=-1 && object[field]!=null && object[field]!=""){
			return true;
		}
		
	}
	return false;
}

/*
 * `this method is uset to set the request types for siebel json
 *  the param names are coming from Lov if there is any change in the 
 *  LOV the param names needs to be changed.
 * */
function RequestType(){
	this.change=["Fleet Management"];
	this.supplies=["Consumables Management"];
	this.service=["Diagnosis","Web Account","Schedule Callback","OEM Import","Claims","Service","Install/De-Install"];
};
RequestType.prototype={
		 getRequestType:function(type){
			  //1-> Supplies/Consumables
				//2-> service
				//3-> move
				//4-> change
				//5-> decommission
			 	if(type==null){
			 		return -1;
			 	}
				if(type.search("Consumables Management")!=-1){
					return 1;
				}else if(type.search("Decommission Asset")!=-1 ){//|| type.search("Deregister Asset")!=-1
					return 5;
				}else if (type.search("Install Asset")!=-1 || type.search("Change Asset")!=-1 || type.search("HW BAU Install")!=-1){//type.search("Change Asset Data")!=-1
					return 4;
				}else if (type.search("Move Asset")!=-1 || type.search("HW BAU Install Move")!=-1 ){//|| type.search("Install Asset")!=-1 || type.search("Register Asset")!=-1
					return 3;
				}else{
					return 2;
				}
			},
		getChangeString:function(){
			return "change";
		},
		getSuppliesString:function(){
			return "supplies";
		},
		getBreakfixString:function(){
			return "service";
		},
		getChangeVal:function(){
			return this.change;
		},
		getSuppliesVal:function(){
			return this.supplies;
		},
		getBreakfixVal:function(){
			return this.service;
		}
};

var requestTypeObj=new RequestType();

function Filters(){};
	Filters.prototype={
		/*setSR:function(sr){
			this["sr"]=sr;
		},*/
		setRestFields:function(deviceObj,addressObj,chlObj,srObj,requestType){
			for(field in deviceObj){
				if( deviceObj[field]!=null && deviceObj[field]!=""){
					if(field=="ProductType"){
						//alert(this.getProductQuery(deviceObj[field]));
						var v=this.getProductQuery(deviceObj[field]);
						if(v!=null)
							this[field]=v;
					}else{
						this[field]=deviceObj[field];
					}
					
					
				}
			}
			
			for(field in addressObj){
				if( addressObj[field]!=null && addressObj[field]!=""){
					
					if(field=="State"){
						setRegionFields(this,addressObj[field]);
						//this[field]=checkAndSetState(this[field]);//state will contain ^ need to remove
					}else{
						this[field]=addressObj[field];
					}
				}
			}
			for(field in srObj){
				if( srObj[field]!=null && srObj[field]!=""){
					
					if(field=="SRCreatedStartDate" || field=="SRCreatedEndDate"){
						this[field]=formatDateToDefault(srObj[field])+" 00:00:00";
					}else{
						this[field]=srObj[field];
					}					
					
				}
			}
			/*Below code is for sending the types of SR
			 * to Siebel 
			 * */
			
			if(srObj.getSrType()!=null){
				var finalArr=[];
				for(var i=0;i<srObj.getSrType().length;i++){
					var type=srObj.getSrType()[i];
					
					if(type==requestType.getChangeString()){
						finalArr=finalArr.concat(requestType.getChangeVal());
					}
					if(type==requestType.getSuppliesString()){
						finalArr=finalArr.concat(requestType.getSuppliesVal());
					}
					if(type==requestType.getBreakfixString()){
						finalArr=finalArr.concat(requestType.getBreakfixVal());
					}
				}
				this[srObj.getSrTypeString()]=finalArr;
			}
			
				
		
			for(field in chlObj){
				if( chlObj[field]!=null && chlObj[field]!=""){
					this[field]=chlObj[field];
				}
			}
			
		},
		setBreadCrumpCountry:function(country){
			this["Country"]=country;
						
		},
		setBreadCrumpState:function(state){
			setRegionFields(this,state);
			//this["State"]=checkAndSetState(state);
			
		},
		setBreadCrumpCity:function(city){
			this["City"]=city;
			
		},
		setContactId:function(c){
			this["ContactId"]=c;
		},
		setCannedValues:function(c){
			for(field in c){
				this[field]=c[field];
			}
		},
		getProductQuery:function(v){
			return prQueries.generateFinalString(v);
		}
	};
	function ProductTypeQueryString(){
		this.Mono="NOT LIKE '%Color%'";
		this.Color="LIKE '%Color%'";
		this.SingleFunctionPrinter="NOT LIKE '%MFP%'";
		this.MultiFunctionPrinter="LIKE '%MFP%'";		
	};
	ProductTypeQueryString.prototype={
			onlyColorMono:function(arr){
				if(arr.indexOf("Mono")!=-1 && arr.indexOf("Color")!=-1){
					
					return true;
				}else{
					return false;
				}
			},
			onlySF_MFP:function(arr){
				if(arr.indexOf("SingleFunctionPrinter")!=-1 && arr.indexOf("MultiFunctionPrinter")!=-1){
					
					return true;
				}else{
					return false;
				}
			},
			generateFinalString:function(arr){
				var dArr=[];
				for(var i=0;i<arr.length;i++){
					dArr[i]=arr[i];
				}
				
				if((this.onlySF_MFP(dArr)==true || this.onlyColorMono(dArr)==true) && arr.length==2){
					return null;
				}else if((this.onlySF_MFP(dArr)==true && this.onlyColorMono(dArr)==true) && arr.length==4){
					return null;
				}else {
					
					var farr=[];
					if(this.onlyColorMono(dArr)){
						dArr.splice(dArr.indexOf("Mono"),1);
						dArr.splice(dArr.indexOf("Color"),1);
						
					}
					if(this.onlySF_MFP(dArr)){
					dArr.splice(dArr.indexOf("SingleFunctionPrinter"),1);
					dArr.splice(dArr.indexOf("MultiFunctionPrinter"),1);
											
					}
					
					for(var i=0;i<dArr.length;i++){
						farr.push(this[dArr[i]]);
					}
					return farr;
				}
				
			}
			
	};
	var prQueries=new ProductTypeQueryString();
	function Visualization(){
		this.action="";
		this.item="";
		this.info=null;
	};
	Visualization.prototype={
			setAction:function(action){
				this.action=action;		
			},
			setItem:function(item){
				this.item=item;		
			},
			setInfo:function(info){
				this.info=info;		
			}
	};
	
	function PostMsg(){
		this.postMessageUrl="";
		this.accountId="";
		this.filters=null;
		//this.mapOptions=new MapOptions();
	};
	
	PostMsg.prototype={
			setPostMessageUrl:function(poMessgURL){
				this.postMessageUrl=poMessgURL;
			},
			setAccountID:function(accId){
				this.accountId=accId;
			},
			setFilters:function(filters){
				this.filters=filters;
			}
	};
	
	
	
	function Info(){
		this.id="";
		this.name="";
		this.type="";
	};
	Info.prototype={
		setId:function(id){
			this.id=id;
		},
		setName:function(name){
			this.name=name;
		},
		setType:function(type){
			this.type=type;
		}
	};
	
	
	
	
	
	
	function ResponseProcessor(){
		this.jsonResp=null;
		this.htmlEleObj=null;
		this.visualizationObj=null;
	};
	ResponseProcessor.prototype={
			processResponse:function (){
				if(this.jsonResp.action=='move' && this.jsonResp.item=='map'){
					//This is for getting Asset data from map
					//map data will be as array
					showNav();//show the left Nav
					//setAddress values and total number of asset in assetDetailsView.jsp
					//lbsBuildingDetails=this.jsonResp.info.buildingInfo;
					setAddressToHTML(this.jsonResp.info.buildingInfo);//this method is there in assetDetailsView.jsp addressDB object is in defaultView.jsp
					setTotalDevice(this.jsonResp.info.buildingInfo.totalAssets);//this method is there in assetDetailsView.jsp
					setTotalDeviceFloor(this.jsonResp.info.buildingInfo.assetCount);
					deviceInformations.setDeviceInformation(this.jsonResp.info);//save the reference for future use!!!
					this.processResponseShowAsset();
					 
				}
				if(this.jsonResp.action=='move' && this.jsonResp.item=='asset'){
					this.processResponseMove();
				}else if (this.jsonResp.action=='click' && this.jsonResp.item=='asset'){
					this.processResponseClick();
				}else if(this.jsonResp.action=='zoomOutToAggregation' && this.jsonResp.item=='map'){
					hideNav();
				}/*else if (this.jsonResp.action=='enableDrag' && this.jsonResp.item=='asset'){
					setAddressHTML_MOVE_TO(addressObj);
				}*/
				else if(this.jsonResp.action=="pendingLocations" && this.jsonResp.item=='asset'){
					setAddressHTML_MOVE_From(this.jsonResp.info.actual);//This method is declared in assetDetailsView.jsp
					setAddressHTML_MOVE_TO(this.jsonResp.info.pending);//This method is declared in assetDetailsView.jsp
					
				}else if(this.jsonResp.action=="info" && this.jsonResp.item=='map'){
					var zoomInfo = JSON.stringify(this.jsonResp.info);
					saveZoomToSession(zoomInfo);
				}
				
			},
			setHTMLElement:function(htmlEleObj){
				this.htmlEleObj=htmlEleObj;
			},
			setVisualization:function(visObj){
				this.visualizationObj=visObj;
			},
			processResponseMove:function(){
				lbsBuildingDetailsTo=this.jsonResp.info;
				doMove(lbsBuildingDetailsTo);//method is there in assetDetailsView.jsp
				
			},
			processResponseClick: function (){
				//here we need to send a post response to enable drag of an asset
				//{"action":"click","item":"asset","info":{"id":"108","name":"94002CW"}
				/*var deviceNm=this.jsonResp.info.name;
				jQuery('#deviceContent').children().each(function(){
					var divId="deviceDetails"+deviceNm;
					
					if(jQuery(this).attr('id')==divId){
						alert(jQuery(this).position().top);
						jQuery('#deviceContent').scrollTop(jQuery(this).position().top);
					}
				});*/
				
				//var hpx=jQuery('#deviceDetails'+this.jsonResp.info.name).position().top-jQuery('#deviceContent').position().top-jQuery('#deviceDetails'+this.jsonResp.info.name).height();
				//alert(hpx);
				
				var ht=0;
				var infoVal=this.jsonResp.info.id;
				
				
				$("#deviceContent").scrollTop(0);
				$('.deviceDetailsDiv').removeClass('greenBackground');
				$("#deviceContent").animate({ 
					scrollTop: ($('#deviceDetails'+infoVal).offset().top)-($("#deviceContent").offset().top) 
					}, 1000);
				
				jQuery('#deviceDetails'+this.jsonResp.info.id).addClass('greenBackground');
				
				//update bread crump with the data
				breadCrumpObject.updateDevice(this.jsonResp.info.name);//Defined in defaultview.jsp
				breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
				
				//below commented as of now will uncomment later for MOVE			
				/*this.visualizationObj.setAction("enableDrag");
				this.visualizationObj.setItem(this.jsonResp.item);
				this.visualizationObj.setInfo(this.jsonResp.info);	
				this.htmlEleObj.showAssetInfo(this.jsonResp.info,"Selected Asset");
				this.htmlEleObj.enableButton();*/
				
				
			},
			processResponseShowAsset:function(){
				
				this.htmlEleObj.updateAssetInformationInLeft(this.jsonResp.info);
			},
			setJSONResponse:function(jsonRespObj){
				this.jsonResp=jsonRespObj;
			}
			
	};
	
	
	function LBS(){
		this.self=this;
		this.postMsgLoc="";
		this.respProcessorObj=null;
	};
	LBS.prototype={
			attachListner:function(){
				
				if (window.addEventListener){
					//This is for !IE
					  addEventListener("message", this.listener, false);
				} else {
					//This is for IE
					 attachEvent("onmessage", this.listener);
				}	
			},
			setResponseProcessor:function(respProc){
				this.respProcessorObj=respProc;
			},
			listener:function(event){	
				//alert(event.data);
				self.postMsgLoc=event.origin;
				
				respProcessorObj.setJSONResponse(JSON.parse(event.data));		
				respProcessorObj.processResponse();
			},
			postMessage:function(visualizationObj){
				
				var iframe = document.getElementsByTagName('iframe')[0];
				 if(self.postMsgLoc==null){
                     self.postMsgLoc=encryptionObj.getLBSFormPostURL();
                 }
				iframe.contentWindow.postMessage(JSON.stringify(visualizationObj), self.postMsgLoc);
				
		        /*var ref = window.open("", "uam-mps-view-map-iframe");
		        
		        ref.postMessage(JSON.stringify(visualizationObj), lbs.postMsgLoc);*/
			},
			postEncryptedMessage:function(stringMsg){
				var iframe = document.getElementsByTagName('iframe')[0];
				iframe.contentWindow.postMessage(stringMsg, self.postMsgLoc);
			}
			
	};
	
	
       
     
	
	function HtmlElement(){
		this.id="";
	};
	HtmlElement.prototype={
		setImageURL:function(url){
			this.imageUrl=url;
		},
		setEnableDragButtonId:function(id){
				this.id=id;
			},
		enableButton:function(){
				jQuery('#'+this.id).attr('disabled','');
			},
		disbleButton:function(){
				jQuery('#'+this.id).attr('disabled','disabled');
			},
		showAssetInfo:function(info,message){
				var stringHtml="<b>"+message+"</b>";
				if(info instanceof Array){
					for(var i=0;i<info.length;i++){
						for(param in info[i]){
							stringHtml+="<li><label>"+param+"</label>";
							stringHtml+="<span>"+info[i][param]+"</span></li>";
						}	
					}
				}else{
					for(param in info){
						stringHtml+="<li><label>"+param+"</label>";
						stringHtml+="<span>"+info[param]+"</span></li>";
					}	
				}
				
				
				jQuery('#assetInformation').html(stringHtml);
			},
		clearAssetHtml:function(){
				jQuery('#assetInformation').html('');
			},
		updateAssetInformationInLeft:function (info){
			
			$('#deviceContent').html('');//Clear Previous html content
			
			$('#deviceContent').append(templateDevice(info));
			
			var arrDeviceId=[];
			for(var i=0;i<info.assets.length;i++){
				arrDeviceId.push(info.assets[i].productName);
				//var html=assetDetailsHTML(deviceId);
				//if(findDeviceWithinObject(deviceId)){
					//html=html.replace('removebookmark-icon','bookmark-star-icon');
				//}	
				
				
					//htmlDeviceExt=templateDeviceExt(object2);
					
					
				    // Append the rendered template to the page.
				 
				/*$('#deviceName'+deviceId).html((("name" in info[i])==true?info[i].name:""));
				//Set value to deviceTag,serial , ip address 
				$('#devTag'+deviceId).html((("customerDeviceTag" in info[i])==true?info[i].customerDeviceTag:""));
				$('#ipAddr'+deviceId).html((("ipAddress" in info[i])==true?info[i].ipAddress:""));
				$('#serailNum'+deviceId).html((("serialNumber" in info[i])==true?info[i].serialNumber:""));
				*/
				//Show supplies,service,move,change,decommission links based on the values from LBS
				/*for(var l=0;l<info[i].srArray.length;l++){
					var type=info[i].srArray[l].type;
					var count=info[i].srArray[l].count;
					if(count>0){
						if(type=="Consumables" || type=="Supplies"){
							$('#suppliesLink'+deviceId).show();
						}	
						if(type=="Service"){
							$('#serviceLink'+deviceId).show();
						}
						if(type=="Move"){
							$('#moveLink'+deviceId).show();
						}
						if(type=="Change"){
							$('#changeLink'+deviceId).show();
						}
						if(type=="Decommission"){
							$('#decomisionLink'+deviceId).show();
						}
					}
					
					
					
				}*/
				
			
				
			}
			//Need to call for the retrieving the image from Server
			this.getImageFromServer(arrDeviceId);
			
		},
		getImageFromServer:function(deviceId){
			$.ajax({
				url:this.imageUrl+"&t="+new Date().getTime(),
				type:'POST',
				dataType:"JSON",
				data:{deviceId:deviceId.toString()},
				success: function(array){
							var devices=deviceInformations.getDeviceInformations();
							for(var i=0;i<devices.length;i++){
								devices[i]["imgUrl"]=array[i];
								
								if(array[i]!="Not found"){
									$('#printImageId'+devices[i].id).attr('src',array[i]);
								}else{
									$('#printImageId'+devices[i].id).attr('src','/LexmarkOPSPortal/images/dummy_noimg_2.jpg');
								}
								
							}
					},
			  failure: function(results){}
			});
		}
			
	};
	
	
	
	
	
	var dialogMaps;
	function openMapInPopup(){
		
		 dialogMaps=jQuery('#maps-Div-Popup').dialog({
				autoOpen: false,
				title: "Maps",
				modal: true,
				draggable: false,
				resizable: false,
				height: 500,
				width: 650,
				open:function(){
					jQuery('#uam-mps-view-map-iframe').attr('style','height:600px;width:600px;');
					var poMsgObjPop=new PostMsg();
					var filtersObjPop=new Filters();					
					poMsgObjPop.setPostMessageUrl(location.href);
					poMsgObjPop.setAccountID("1-85BYD");
					poMsgObjPop.setFilters(filtersObjPop);
					
					//alert(JSON.stringify(poMsgObj));
					
					//jQuery("#uam-mps-view-assets-text").val(assetText);
					//var jsonObj = JSON.parse(jQuery("#uam-mps-view-assets-text").val());
				    jQuery("#uam-mps-map-input").val(JSON.stringify(poMsgObjPop));//jQuery("#uam-mps-view-assets-text").val());
				    jQuery("#uam-mps-map-form").submit();
				},
				close: function(event,ui){	
					hideOverlay();
					dialogMaps=null;					
					}
				});			
		 dialogMaps.dialog('open');	
		
	}
	
	function changeSize(){
		var obj={
				 "action": "changeSize",
				    "item": "map",
				    "info": {height: getIframeHeight(),//method declared in maps-Iframe.jsp
				            width: getIframeWidth()
				    }
				    
		};
		lbs.postMessage(obj); 
	}

	function showNav(){
		
			jQuery('#left-nav-Div,#leftNavDevices').show();
			jQuery('#uam-mps-view-map-iframe').css('width','69%');
			changeSize();	
	}
	function hideNav(){
		
		jQuery('#left-nav-Div,#leftNavDevices').hide();
		jQuery('#uam-mps-view-map-iframe').css('width','100%');
		changeSize();	
	}	
	
	
	//This section is for customize Popup settings
	//Key is the index to be saved and value is the id's of the input
	function customizeIndex(){
		this.filterLocation=["1",true];
		this.customerHierarchy=["2",false];
		
		this.serialNumber=["3",true];
		this.ipAddress=["4",true];
		this.productModel=["5",true];
		this.assetProduct=["6",true];
		this.assetInstallDateDiv=["7",true];
		this.modelType=["8",false];
		this.productType=["9",true];
		this.productSeries=["10",false];
		this.brand=["11",false];
	
		this.costCenterAsset=["12",false];
		this.departmentAsset=["13",false];
		this.meterReadTypeAsset=["14",false];
		this.assetTag=["15",false];
		this.agreement=["16",false];
		this.deviceHostName=["17",false];
				
		
		this.LXKAssetTag=["18",false];
		this.assetLifeCycle=["19",false];
		this.devicePhase=["20",false];
		this.hardwareStatus=["21",false];
		this.mdmID=["22",false];
		
		
		
		
		
		this.requestNo=["23",false];
		this.requestType=["24",false];
		this.requestStatus=["25",false];
		this.customerRef=["26",true];
		this.dateRangeFilterContainer=["27",true];
		this.areaDrop=["28",true];
		this.subAreaDrop=["29",true];
		
		this.srOPSStatus=["30",false];
		this.srSubStatus=["31",false];
		this.srSource=["32",false];
	};


			
	var customizeIndexSetting=new customizeIndex();	

	/**
	 * indexes is the comma separated values
	 * 1,2,3,4
	 * */
	function loadCustomizeSetting(indexes){
		
		if(indexes==""){
			for(fields in customizeIndexSetting){
				if(customizeIndexSetting[fields][1]==true){
					$('#'+fields).show();
					
				}else{
					$('#'+fields).hide();
					
				}
				
			}
			return;
		}
		var array=indexes.split(",");
		for(field in customizeIndexSetting){
			
			if(array.indexOf(customizeIndexSetting[field][0])==-1){
				customizeIndexSetting[field][1]=false;	
				$('#'+field).hide();
			}else{
				customizeIndexSetting[field][1]=true;
				$('#'+field).show();
			}
		}
		
		
		
	}
	function findDeviceWithinObject(assetId){
		//bookMarkedAssets = we will get from ajax call from document ready
		if(bookMarkedAssets==null){
			return false;
		}
		for(var j=0;j<bookMarkedAssets.length;j++){			
			if(bookMarkedAssets[j]==assetId){
				return true;
			}
		}
		return false;
	}
	function findWithingObj(index){
		for(field in customizeIndexSetting){
			if(customizeIndexSetting[field][0]==index){
				return field;
			}
		}
	}
	
	/**
	 * id=HTML input check val
	 * isChecked= true/false 
	 * */
	function saveCustomizeSetting(id,isChecked,url){
		customizeIndexSetting[id][1]=(isChecked==true?true:false);
		var stringArr="";
		for(field in customizeIndexSetting){
			if(customizeIndexSetting[field][1]==true){
				stringArr+=customizeIndexSetting[field][0];
				stringArr+=",";
			}				 
		}
		//alert('final array =' + stringArr);
		$.ajax({
			url:url+"&t="+new Date().getTime(),
			type:'POST',
			data:{id:-1,indexes:stringArr},
			success: function(response){
						//alert('success fully saved to DB settings');
				},
		  failure: function(results){}
		});
	}
	//This will be used for MOVE request for setting address fields from LBS object to Portal Object
	function  LBSAddressInformation(){
		this.LBSField=["lat","lng","floorNumber","id","buildingId","buildingName","regionId","regionName","address","city","state","country","zipCode","extAddressId","campusId","campusName","floorId","floorName","attributes"];
		this.HtmlId=["lbs_lat","lbs_lng","lbs_floorNumber","assetId","lbs_buildingId","lbs_buildingName","lbs_regionId","lbs_regionName","lbs_address","lbs_city","lbs_state","lbs_country","lbs_zipCode","lbs_extAddressId","lbs_campusId","lbs_campusName","lbs_floorId","lbs_floorName"];
	};
	var addressMapping=new LBSAddressInformation();
	
	//this will contain the json response of list of assets
	function LBSDeviceInformations(){}
	LBSDeviceInformations.prototype={
			setDeviceInformation:function(info){
				this["deviceInformations"]=info;
			},
			getDeviceObjectFromList:function(deviceId){
				for(var i=0;i<this["deviceInformations"].assets.length;i++){
					if(deviceId==this["deviceInformations"].assets[i].id){
						break;
					}
				}
				return this["deviceInformations"].assets[i];
			},
			getDeviceInformations:function(){
				return this["deviceInformations"].assets;
			}
	};
	var deviceInformations=new LBSDeviceInformations();
	
	function showPendingMove(){
		//hide left nav and show the pending move view
		$('#parentFilter').slideUp();
		$('#leftNavDevices').hide();
		$('#mapWithPendingMove').show();
		changeSize();
		
	}
	function hidePendingMove(){
		//show left nav and hide the pending move view
		$('#parentFilter').slideDown();
		$('#leftNavDevices').show();
		$('#mapWithPendingMove').hide();
		changeSize();
	}
	
	function showLocationHistory(){
		//hide left nav and show the pending move view
		$('#parentFilter').slideUp();
		$('#leftNavDevices').hide();
		$('#mapWithLocationhistory').show();
		changeSize();
		
	}
	function hideLocationHistory(){
		//show left nav and hide the pending move view
		$('#parentFilter').slideDown();
		$('#leftNavDevices').show();
		$('#mapWithLocationhistory').hide();
		changeSize();
	}
	
	function hideFiltersAndLeftNav (){
		
		//show left nav and hide the pending move view
		$('#parentFilter').slideUp();
		$('#leftNavDevices').hide();
		//alert($('#cfrmMoveHidden').width());
		//alert($('#uam-mps-view-map-iframe').contents().find(".confirmButton").css('width'));
		$('#cancelMoveRequest').css('right',($('#cfrmMoveHidden').width()+70)+'px');
		$('#cancelMoveRequest').show();
		$('#breadCrumpMap').hide();
		$('#uam-mps-view-map-iframe').css('width','100%');
		changeSize();
		
	}
	function showFiltersAndLeftNav(){
		//hide left nav and show the pending move view
		$('#parentFilter').slideDown();
		$('#leftNavDevices').show();
		$('#cancelMoveRequest').hide();
		$('#breadCrumpMap').show();
		$('#uam-mps-view-map-iframe').css('width','69%');
		changeSize();
	}
	
	//Below section is for maps-request to set input values from address object
	function addressMappingMapsRequest(){
		this.addressObjFields=["aId","aName","aLine1","aLine2","cty","state","prvnc","poCde","ctry"];
		//These input fields are declared in subTab. jsp
		this.htmlFieldsMap=["maps_req_addressId","maps_req_addressName","maps_req_addressLine1","maps_req_addressLine2",
		                    "maps_req_addressCity","maps_req_addressState","maps_req_addressProvince",
		                    "maps_req_addressPostCode","maps_req_addressCountry"];
		this.extraHtmlFields=["maps_req_addressSite","maps_req_addressBuilding","maps_req_addressFloor","maps_req_addressZone"];
		this.dropDwnFields=["site","bldng","flr","zone"];
	}
	var addressMapRequest=new addressMappingMapsRequest();
	function setAddressFieldToMapRequest(addressObj){
		for(var i=0;i<addressMapRequest.addressObjFields.length;i++){
			$('#'+addressMapRequest.htmlFieldsMap[i]).val(addressObj[addressMapRequest.addressObjFields[i]]);
		}
		for(var i=10;i<addressMapRequest.extraHtmlFields.length;i++){
			$('#'+addressMapRequest.extraHtmlFields[i]).val(addressMapRequest.dropDwnFields[i].val());
		}
	}
	
	function EncryptionAES(){this.encrypt=false;this.cookie=null;};
	EncryptionAES.prototype={
		setSiebelJSONString:function(s){
			this["jsonString"]=s;
		},	
		getSiebelJSONString:function(s){
			return this["jsonString"];
		},
		setEncryptionFlag:function(f){
			this["encrypt"]=f==true?true:false;
		},
		getEncryptionFlag:function(){
			return this["encrypt"];
		},
		setCookieValueLBS:function(c){
			this["cookie"]=c;
		},
		getCookieValueLBS:function(){
			return this["cookie"];
		},
		setEncryptLBSFormPostURL:function(f){
			this["frmEUrl"]=f;
		},
		getEncryptLBSFormPostURL:function(){
			return this["frmEUrl"];
		},
		setLBSFormPostURL:function(f){
			this["frmUrl"]=f;
		},
		getLBSFormPostURL:function(){
			return this["frmUrl"];
		}
	};
	var encryptionObj=new EncryptionAES();
	
	
	
	var templateDevice,templateDeviceExt;
YUI().use('handlebars', 'node-base', function (Y) {
	
	 Y.on('domready', function () {
		 	templateDevice = Y.Handlebars.compile(Y.one('#list-template').getHTML());
    	 	templateDeviceExt=Y.Handlebars.compile(Y.one('#deviceInfoExt').getHTML());
    	 	
    	 	
    	 	/*showNav();
    		var obj={"assets":[{"id":"1-MPAOXM9","devicePhase":"Installed","productName":"47B1000","name":"1-49418825265","ipAddress":null,"serialNumber":"LBSVALUS04118","modelNumber":"7562-436","customerDeviceTag":"","srArray":[],"regionId":"","regionName":""}]};
    		htmlEleObj.updateAssetInformationInLeft(obj);*/
	 });
	
	var bClass="";
	
Y.Handlebars.registerHelper('generateBkmrkClass', function (deviceId) {
		if(findDeviceWithinObject(deviceId)){bClass= "bookmark-star-icon";}else{bClass= "removebookmark-icon";}	
		return bClass;
	});

Y.Handlebars.registerHelper('generateBkmrkMsg', function () {
	if(bClass=="bookmark-star-icon"){return globalMessagesAssetDetails["unBookmark"];}else{return globalMessagesAssetDetails["bookmark"];}
	
});

Y.Handlebars.registerHelper('showSupplies', function (srArray,id) {
		for(var i=0;i<srArray.length;i++){
			var type=requestTypeObj.getRequestType(srArray[i].type);
			var count=srArray[i].count;
			
			if(type==1 && count>0){
						return "<tr><td style=\"width:30px\"><img class=\"ui_icon_sprite supplies-icon\" alt=\"\" /></td>"+
										"<td><a style=\"cursor: pointer;\" onClick=\"showRequests('"+id+"',1,1)\">"+globalMessagesAssetDetails["suppliesLink"]+"</a>"+
									"</tr>";
			}
			
		}
		
	    
	});
	Y.Handlebars.registerHelper('showService', function (srArray,id) {
		for(var i=0;i<srArray.length;i++){
			var type=requestTypeObj.getRequestType(srArray[i].type);
			var count=srArray[i].count;
			if(type==2 && count>0){
							return "<tr><td style=\"width:30px\"><img class=\"ui_icon_sprite services-icon\" alt=\"\" /></td>"+
										"<td><a style=\"cursor: pointer;\" onClick=\"showRequests('"+id+"',2,1)\">"+globalMessagesAssetDetails["service"]+"</a>"+
									"</tr>";
						}
			
		}
		
	    
	});
	Y.Handlebars.registerHelper('showMove', function (srArray,id) {
		for(var i=0;i<srArray.length;i++){
			var type=requestTypeObj.getRequestType(srArray[i].type);
			var count=srArray[i].count;
			if(type==3 && count>0){
							return "<tr><td style=\"width:30px\"><img class=\"ui_icon_sprite move-icon\" alt=\"\" /></td>"+
										"<td><a style=\"cursor: pointer;\" onClick=\"showRequests('"+id+"',3,1)\">"+globalMessagesAssetDetails["move"]+"</a>"+
									"<td style=\"width:30px\"><a class=\"ui_icon_sprite pending_move-icon\" href=\"#pM\" onClick=\"viewPendingMove('"+id+"')\"></a></td></tr>";
						}
			
		}
		
	    
	});
	Y.Handlebars.registerHelper('showChange', function (srArray,id) {
		for(var i=0;i<srArray.length;i++){
			var type=requestTypeObj.getRequestType(srArray[i].type);
			var count=srArray[i].count;
			if(type==4 && count>0){
							return "<tr><td style=\"width:30px\"><img class=\"ui_icon_sprite change-icon\" alt=\"\" /></td>"+
										"<td><a style=\"cursor: pointer;\" onClick=\"showRequests('"+id+"',4,1)\">"+globalMessagesAssetDetails["change"]+"</a>"+
									"</tr>";
						}
			
		}
		
	    
	});
	Y.Handlebars.registerHelper('showDecommission', function (srArray,id) {
		for(var i=0;i<srArray.length;i++){
			var type=requestTypeObj.getRequestType(srArray[i].type);
			var count=srArray[i].count;
			if(type==5 && count>0){
							return "<tr><td style=\"width:30px\"><img class=\"ui_icon_sprite decomission-icon\" alt=\"\" /></td>"+
										"<td><a style=\"cursor: pointer;\" onClick=\"showRequests('"+id+"',5,1)\">"+globalMessagesAssetDetails["decommission"]+"</a>"+
									"</tr>";
						}
			
		}
		
	    
	});
    
	Y.Handlebars.registerHelper('productTypeHelper', function (type) {
		
		var optionVal="";
		if(type=="Mono"){
			optionVal="Mono";
		}else if(type=="Color"){
			optionVal="Color";
		}else if(type=="Single"){
			optionVal="SingleFunctionPrinter";
		}else if(type=="MFP"){
			optionVal="MultiFunctionPrinter";
		}
		
		if(optionVal==""){
			return "";
		}
		
		/* the producttypearray is declared in mapfilter.jsp this is a LOV list*/
		var i=0;
		for(i=0;i<productTypeArray.length;i++){
			if(productTypeArray[i].value==optionVal){
				
				return productTypeArray[i].displayValue;
			}
		}
		
	});
});
	
function checkAndSetState(s){
	if (s==null || s=="" || s.indexOf("^")==-1){
		return s;
	}else{
		return (s.substring(0,s.indexOf("^")));
	}
}
/* This method will set state or province or county or district 
 * State param will contain ^s,^p,^c,^d
 * */
function setRegionFields(obj,state){
	if(state==null || state ==""){
		obj["Province"]=null;
		obj["County"]=null;
		obj["District"]=null;
		obj["State"]=null;
		return;
	}
	if(state.indexOf("^p")!=-1){
		obj["Province"]=checkAndSetState(state);
	}else if(state.indexOf("^c")!=-1){
		obj["County"]=checkAndSetState(state);
	}else if(state.indexOf("^d")!=-1){
		obj["District"]=checkAndSetState(state);
	}else{
		obj["State"]=checkAndSetState(state);
	}
}
	
function postCapture(){
	var captureMapObj={
			   "action": "captureMap",
			   "item": "map",
			   "info": {}
			};
	lbs.postMessage(captureMapObj);
}