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

function _getiTextContext(parentSelector,selctor){
	if($('#'+parentSelector+selctor).val()!=''){
		return $('#'+parentSelector+selctor+' option:selected').text();
	}else{
		return null;
	}
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
	                       "CustomerAssetTag","AgreementName","HostName",
	                       "AssetLifeCycle","DevicePhase","AssetMeterSource"];
	//These are JSON field names for storing in DB in portal
	 this.dbFieldNames=    ["srNm","ipAddr","prmdl",
	                   	                       "prNm","instDtF","instDtT","mdTyp","prTyp",
	                   	                       "prSr","br","coctr","dpt",
	                   	                       "astg","agrnm","hstNm","asLf","dvPh","mRdType"];
	 //These fields are HTML Input ids for Device filter
	 this.deviceIdInputIds=["serialNumber","ipAddress","productModel",
	                        "assetProduct" ,"installDateFrom","installDateTo","modelType",
	                        "productType-options","productSeries-options","brand",
	                        "costCenterAsset","departmentAsset",
	                        "assetTag","agreement","deviceHostName","assetLifeCycle","devicePhase","meterReadTypeAsset"];
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
	this.siebelFieldNames=["SRNumber","SRType","WebStatus","SRCreatedStartDate","SRCreatedEndDate","HelpDeskRefNum","SRArea","SRSubArea"];
	this.dbFieldNames=["reqNo","reqType","reqStat","stDt","edDt","hpDskRf","srAr","srSuAr"];	
	this.srInputIds=["requestNo","requestType-options","requestStatus-options","startDateFilter","endDateFilter","customerRef","areaDrop-options","subAreaDrop-options"];
	
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
				var ind=addressFields.dbFieldNames.indexOf(field);
				if(ind!=-1)
				this[addressFields.siebelFieldNames[ind]]=addressPopupValues[field];
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
				
				this["sIT"]=_getiVal("site")==""?null:_getiText("site");//Site display vlaue
				this["sNm"]=_getiVal("site");//==""?null:_getiText("site");
				
				this["bIT"]=_getiVal("bldng")==""?null:_getiText("bldng");//building display value
				this["bNm"]=_getiVal("bldng");//==""?null:_getiText("bldng");
				
				this["fIT"]=_getiVal("flr")==""?null:_getiText("flr");//Floor Display value
				this["fNm"]=_getiVal("flr");//==""?null:_getiText("flr");
				
				this["zIT"]=_getiVal("zone")==""?null:_getiText("zone");//zone text value
				this["zn"]=_getiVal("zone");//==""?null:_getiText("zone");
				
				this["btIT"]=_getiVal("bldngType")==""?null:_getiText("bldngType");//zone text value
				this["bTyp"]=_getiVal("bldngType");//==""?null:_getiText("zone");
			}
		},
		/*pC=parent Context either  class or id*/
		setEditPrefPopVals:function(addressPopupValues,pC,allhtmlTextIdsPref){
			for(field in addressPopupValues){
				var ind=addressFields.dbFieldNames.indexOf(field);
				if(ind!=-1)
				this[addressFields.dbFieldNames[ind]]=addressPopupValues[field];				
			}
			
			this["ctry"]=_getiValContext(pC,"selectCountry",allhtmlTextIdsPref);
			this["state"]=_getiValContext(pC,"selectState",allhtmlTextIdsPref);
			this["cty"]=_getiValContext(pC,"selectCity",allhtmlTextIdsPref);
			this["sIT"]=_getiTextContext(pC,"site");//Site display vlaue
			this["sNm"]=_getiValContext(pC,"site",allhtmlTextIdsPref);
			this["bIT"]=_getiTextContext(pC,"bldng");//building display value
			this["bNm"]=_getiValContext(pC,"bldng",allhtmlTextIdsPref);	
			this["fIT"]=_getiTextContext(pC,"flr");//Floor Display value
			this["fNm"]=_getiValContext(pC,"flr",allhtmlTextIdsPref);
			this["zIT"]=_getiTextContext(pC,"zone");//zone text value
			this["zn"]=_getiValContext(pC,"zone",allhtmlTextIdsPref);
			this["btIT"]=_getiTextContext(pC,"bldngType");//building type text value
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
	this.displayAssetLifeCycle=false;
}
function SiebelJSON(){
	this.optionalParameters=new optSiebelParam();
	this.applicationId= "lex_lbs_portal";
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
					setbuildingType(this.jsonResp.info.buildingInfo.buildingType);
					deviceInformations.setDeviceInformation(this.jsonResp.info);//save the reference for future use!!!
					this.processResponseShowAsset();
					 
				}
				if(this.jsonResp.action=='move' && this.jsonResp.item=='asset'){
					this.processResponseMove();
				}else if (this.jsonResp.action=='click' && this.jsonResp.item=='asset'){
					this.processResponseClick();
				}else if(this.jsonResp.action=='zoomOutToAggregation' && this.jsonResp.item=='map'){
					hideNav();
					if(window.deviceStatus)
						deviceStatus.clearAllHtml();
				}/*else if (this.jsonResp.action=='enableDrag' && this.jsonResp.item=='asset'){
					setAddressHTML_MOVE_TO(addressObj);
				}*/
				else if(this.jsonResp.action=="pendingLocations" && this.jsonResp.item=='asset'){
					setAddressHTML_MOVE_From(this.jsonResp.info.actual);//This method is declared in assetDetailsView.jsp
					setAddressHTML_MOVE_TO(this.jsonResp.info.pending);//This method is declared in assetDetailsView.jsp
					
				}else if(this.jsonResp.action=="info" && this.jsonResp.item=='map'){
					zoomProcessor.zoomInfo=this.jsonResp.info;
					zoomProcessor.processResponse();
				}else if(this.jsonResp.action=="moveCancel" && this.jsonResp.item=='asset'){
					$('#mapInfo').html(prvhtml);
					showFiltersAndLeftNav();
				}else if(this.jsonResp.action=="multiSelect" && this.jsonResp.item=='asset'){
					/** Changes for LBS 1.5 add the list of assets for multiple Page Counts*/
					if(multiSelectAsset.enabled){
						multiSelectAsset.addAsset(this.jsonResp.info.assets);
						multiSelectAsset.updateHtml();
					}					
					/**Ends multiselect asset changes*/
				}else if(this.jsonResp.action=="deviceStatusListNotification" && this.jsonResp.item=='asset'){
					deviceStatus.processDeviceStatus(this.jsonResp.info);
				}else if(this.jsonResp.action=="multiSelectCancel" && this.jsonResp.item=='map'){
					handleMultiSelectCancel();
				}else if(this.jsonResp.action=="multiSelectConfirm" && this.jsonResp.item=='map'){
					if(!this.jsonResp.info){
						multiSelectAsset.showError();
						lbs.postMessage({
							"action": "enableMultiSelect",
							 "item": "map"
						});
						return;
					}
					if(multiSelectAsset.enabled){
						
						console.log(JSON.stringify(this.jsonResp.info));
						setMoveToAndInstallAddress(this.jsonResp.info);
						multiSelectAsset.handleClickConfirm();
					}
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
				
				
				var ht=0;
				var infoVal=this.jsonResp.info[0].id;
				
				this.showAssetInLeftNav(infoVal);
				
				
				//update bread crump with the data
				breadCrumpObject.updateDevice(this.jsonResp.info[0].name);//Defined in defaultview.jsp
				breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
				
				
				
				
			},
			processResponseShowAsset:function(){
				
				this.htmlEleObj.updateAssetInformationInLeft(this.jsonResp.info);
			},
			setJSONResponse:function(jsonRespObj){
				this.jsonResp=jsonRespObj;
			},
			showAssetInLeftNav:function(infoVal){
				$("#deviceContent").scrollTop(0);
				$('.deviceDetailsDiv').removeClass('greenBackground');
				$("#deviceContent").animate({ 
					scrollTop: ($('#deviceDetails'+infoVal).offset().top)-($("#deviceContent").offset().top) 
					}, 1000);
				
				jQuery('#deviceDetails'+infoVal).addClass('greenBackground');
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
				console.log(event.data);
				var eventData = JSON.parse(event.data);
				console.log("eventData.action.toLowerCase()::"+eventData.action.toLowerCase());
				
				if(eventData.action.toLowerCase() != "deselect")
				{
					self.postMsgLoc=event.origin;
					
					respProcessorObj.setJSONResponse(JSON.parse(event.data));		
					respProcessorObj.processResponse();
				}
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
			},
			highlightMap:function(id){
				
				$('.deviceDetailsDiv').removeClass('greenBackground');
				$('#deviceDetails'+id).addClass('greenBackground');
				
				this.postMessage({
					   "action": "select",
					   "item": "asset",
					   "info": {
						   "id": id
					   }});	
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
			if(typeof templateDevice !== 'function'){
				initHandleBar();
			}
			var info1={}
			if($('#showDeviceStatusInPopup').val()==="true" || $('#showDeviceStatusUtilInPopup').val()==="true"){
				info1={"showDeviceStat":true,
						"assetInfo":info};
			}else{
				info1={"showDeviceStat":false,
						"assetInfo":info};
			}
			$('#deviceContent').append(templateDevice(info1));
			
			var arrDeviceId=[];
			for(var i=0;i<info.assets.length;i++){
				arrDeviceId.push(info.assets[i].productName);
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
									$('#printImageId'+devices[i].id).attr('src','/LexmarkServicesPortal/images/dummy_noimg_2.jpg');
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
	var inFloorLevel = false;
	function showNav(){
			inFloorLevel = true;
			jQuery('#left-nav-Div,#leftNavDevices,#deviceStatusContent,#viewGridActions').show();
			jQuery('#uam-mps-view-map-iframe').css('width','69%');
			changeSize();
			adjustLeftNav();
			
	}
	function hideNav(){
		inFloorLevel = false;
		//jQuery('#deviceStatusContent').show();
		jQuery('#left-nav-Div,#leftNavDevices,#moveMultiAssetLeftNav,#viewGridActions').hide();
		jQuery('#uam-mps-view-map-iframe').css('width','100%');
		changeSize();	
	}	
	function adjustLeftNav(){
		if($("#left-nav-Div").css('display')!='none')
			$("#deviceStatusContent").width($('#firstBlock').width());
		var leftNavHeight = $("#uam-mps-view-map-iframe").height();
		jQuery('#leftNavDevices').css('min-height',leftNavHeight+'px');
		var firstBlockHeight = $("#leftNavDevices #firstBlock").height();
		var aggregateConditionsHeight = $("#deviceStatusContent").height();
		$('#leftNavDevices #firstBlock').css('margin-top',aggregateConditionsHeight+"px");
		var remainingLeftNav = leftNavHeight - firstBlockHeight - aggregateConditionsHeight;
		$('#leftNavDevices #secondBlock').css('height',remainingLeftNav+'px');

		var deviceHeaderHeight = $('#deviceHeader').height();
		remainingLeftNav = remainingLeftNav - deviceHeaderHeight - 10;
		$("#deviceContent").css('height',remainingLeftNav+"px");
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
		this.productType=["9",false];
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
		this.mdmId=["22",false];
		
		
		
		
		
		this.requestNo=["23",false];
		this.requestType=["24",false];
		this.requestStatus=["25",false];
		this.customerRef=["26",true];
		this.dateRangeFilterContainer=["27",true];
		this.areaDrop=["28",true];
		this.subAreaDrop=["29",true];
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
			data:{src:"SP",id:-1,indexes:stringArr},
			success: function(response){
						//alert('success fully saved to DB settings');
				},
		  failure: function(results){}
		});
	}
	//This will be used for MOVE request for setting address fields from LBS object to Portal Object
	function  LBSAddressInformation(){
		this.LBSField=["lat","lng","floorNumber","id","buildingId","buildingName","regionId","regionName","address","city","state","country","zipCode","extAddressId","campusId","campusName","floorId","floorName","addressLOD","floorLOD","attributes"];
		this.HtmlId=["lbs_lat","lbs_lng","lbs_floorNumber","assetId","lbs_buildingId","lbs_buildingName","lbs_regionId","lbs_regionName","lbs_address","lbs_city","lbs_state","lbs_country","lbs_zipCode","lbs_extAddressId","lbs_campusId","lbs_campusName","lbs_floorId","lbs_floorName","lbs_addressLOD","lbs_floorLOD"];
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
			},
			setPageCounts:function(assetPg){
				var asset=this.getDeviceObjectFromList(assetPg.id);
					asset.pageCount=assetPg.pageCount;				
			},
			getBuildingInfo:function(){
				return{
					bId:this.deviceInformations.buildingInfo.buildingId,
					fId:this.deviceInformations.buildingInfo.floorId
				};
				
				
				
			},
			getAssetAddress:function(){
				return this.deviceInformations;
				
			},
			getAddress:function(){
				return this.deviceInformations.buildingInfo;
				
			}
	};
	var deviceInformations=new LBSDeviceInformations();
	
	
	
	function showPendingMove(){
		//hide left nav and show the pending move view
		$('#parentFilter').slideUp();
		$('#leftNavDevices,#deviceStatusContent').hide();
		$('#mapWithPendingMove').show();
		changeSize();
		
	}
	function hidePendingMove(){
		//show left nav and hide the pending move view
		$('#parentFilter').slideDown();
		$('#leftNavDevices,#deviceStatusContent').show();
		$('#mapWithPendingMove').hide();
		changeSize();
	}
	
	function showLocationHistory(){
		//hide left nav and show the pending move view
		$('#parentFilter').slideUp();
		$('#leftNavDevices,#deviceStatusContent').hide();
		$('#mapWithLocationhistory').show();
		changeSize();
		
	}
	function hideLocationHistory(){
		//show left nav and hide the pending move view
		$('#parentFilter').slideDown();
		$('#leftNavDevices,#deviceStatusContent').show();
		$('#mapWithLocationhistory').hide();
		changeSize();
	}
	
	function hideFiltersAndLeftNav (){
		
		//show left nav and hide the pending move view
		$('#parentFilter').slideUp();
		$('#leftNavDevices,#deviceStatusContent').hide();
		//alert($('#cfrmMoveHidden').width());
		//alert($('#uam-mps-view-map-iframe').contents().find(".confirmButton").css('width'));
		//$('#cancelMoveRequest').css('right',($('#cfrmMoveHidden').width()+70)+'px');
		//$('#cancelMoveRequest').show();
		$('#breadCrumpMap').hide();
		$('#uam-mps-view-map-iframe').css('width','100%');
		changeSize();
		
	}
	function showFiltersAndLeftNav(){
		//hide left nav and show the pending move view
		$('#parentFilter').slideDown();
		$('#leftNavDevices,#deviceStatusContent').show();
		//$('#cancelMoveRequest').hide();
		$('#breadCrumpMap').show();
		if(inFloorLevel)
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
	YUI().use('node-base', function (Y) {
		Y.on('domready', function () {
			initHandleBar();
			if(window.createMultiSelectTemplate){
				createMultiSelectTemplate();
			}
		});
	});
	
function initHandleBar(){
	YUI().use('handlebars','node-base', function (Y) {
		if(Y.one('#list-template')!=null && Y.one('#deviceInfoExt')!=null){
			templateDevice = Y.Handlebars.compile(Y.one('#list-template').getHTML());
		 	templateDeviceExt=Y.Handlebars.compile(Y.one('#deviceInfoExt').getHTML());			
		}
		
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
		
		Y.Handlebars.registerHelper('localizeAddressLOD', function (val) {
			if(addressLOD[val]){
				return addressLOD[val];
			}else{
				return val;
			}
		});
		Y.Handlebars.registerHelper('localizeFloorLOD', function (val) {
			if(floorLOD[val]){
				return floorLOD[val];
			}else{
				return val;
			}
		});

	});
}	

	
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
var captureMapObj={
		   "action": "captureMap",
		   "item": "map",
		   "info": {}
		};	
function postCapture(){	
	lbs.postMessage(captureMapObj);
}

function handleMultiSelectCancel(){
	multiSelectAsset.resetObj();
	$('#moveMultiAssetLeftNav').hide();
	hideNav();
	siebelJSON.defaultArea=	zoomProcessor.zoomInfo;
	showMapBtnClicked();hideOverlay();
	deviceStatus.clearApplyFilter(false);
}