<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
.loadPgImg {
	background-color: #e6e6f0;
	text-align: center;height:auto;
}
.obj{
 text-align: left;
}
#asset-list .obj input {
    width: 120px !important;
}
#asset-list .gridbox {height:auto;max-height:300px;}
.button:focus{
 padding:4px 9px !important;
}
#asset-list .span-apart {
    display: inline-block;
    min-width: 120px !important;
}
</style>
<div id="assetPageCounts-Multiple">
	<div class="error-pg"style="display: none;">
		<ul class="serviceError">			
		</ul>
	</div>
	<div class="success" style="display: none;">
		<ul class="serviceSuccess">	
			<li class="portlet-msg-success"><spring:message code='devicemgmt.meterreads.update.success.mesg'/></li>		
		</ul>
	</div>
	<div class="failure" style="display: none;">
		<ul class="serviceError">
			<li class="portlet-msg-error"><spring:message code='devicemgmt.meterreads.update.failure.mesg'/></li>
		</ul>
	
	</div>
	<div id="asset-list"></div>
</div>


<script  id="list-Asset-template" type="text/x-handlebars-template">
{{#assets}}	
<div id="templatePgCount{{id}}" class="marginTB10">
	<div style="margin-bottom:5px" id="assetInfo-PgCount{{id}}" onClick="onClickAssetInfoPG('{{id}}')">
		<div style="background-color:#eeeeee;padding:2px 10px;cursor:pointer">
			<span class="fontBold"><spring:message code="fleetmanagement.tableheaders.serialnumber"/>: </span><span class="span-apart">{{serialNumber}}</span>
			<span class="fontBold"><spring:message code="fleetmanagement.headers.deviceAlerts"/>: </span><span class="span-apart">{{ipAddress}}</span>
			<span class="fontBold"><spring:message code="lbs.label.model"/>: </span><span class="span-apart">{{name}}</span>
			<span class="fontBold"><spring:message code="lbs.label.assettag"/>: </span class="span-apart"><span>{{customerDeviceTag}}</span>
			<span id="openPopupGridArrow{{id}}" class="arrow_icon arrow_down floatR"></span>
			<span class="clearBoth"></span>
		</div>
	</div>
	<div id="asset-PgCountDetails{{id}}" style="display:none;overflow:auto;" class="gridbox gridbox_light">		
	</div>
	<div class="buttonContainer">
		<%--<input type="button" class="button_cancel" value="Cancel" onClick="onClickAssetInfoPG('{{id}}')"/>--%>
		<input class="button" type="button" value="Update" onClick="submitPageCount('{{id}}')"/>
	</div>
</div>
{{/assets}}	
</script>

<script  id="list-pageCount-template" type="text/x-handlebars-template">
	<table cellspacing="0" cellpadding="0"  class="obj">
<thead>
	<tr>
		<th><spring:message code="fleetmanagement.tableheaders.pagecounttype"/></th>
		<th><spring:message code="fleetmanagement.tableheaders.datetimeupdate"/></th>
		<th><spring:message code="fleetmanagement.tableheaders.currentpagecount"/></th>
		<th><spring:message code="requestInfo.info.heading.datetime"/></th>
		<th><spring:message code="requestInfo.info.heading.newPageCount"/></th>
	</tr>
</thead>
<tbody>
	

	{{#each pageCount}}
		<tr class="{{{alternate index}}}">
		<td valign="middle" align="left">{{name}}</td>
		<td valign="middle" align="left">{{date}}</td>
		<td valign="middle" align="left">{{count}}</td>
		<td valign="middle" align="left">
		<input type="text" id="{{../id}}-d-{{index}}"/>
		<img onclick="calShow('{{../id}}-d-{{index}}','{{date}}')" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon"  style="cursor:pointer;"/>
		<span style="display:none; float:right;">
			<img src="<html:imagesPath/>transparent.png" class="ui-icon trash-icon">
		</span>
		</td>
		<td valign="middle" align="left">
			<input type="text" style="width:70px" id="{{../id}}-c-{{index}}">
		</td>
	</tr>
	{{/each}}
</tbody>
</table>
</script>



<script>
var templateAssetPg,templatePgCounts;
YUI().use('handlebars', 'node-base', function (Y) {
	Y.on('domready', function () {
	 	templateAssetPg = Y.Handlebars.compile(Y.one('#list-Asset-template').getHTML());
	 	templatePgCounts=Y.Handlebars.compile(Y.one('#list-pageCount-template').getHTML());
	 	
	});
	Y.Handlebars.registerHelper('alternate', function (index) {
			if(index%2==0)return "even_row";
			else return "odd_row";
			
		});
});

function onClickAssetInfoPG(id){
	toggleSlide('#asset-PgCountDetails'+id,'up');
	if($('#openPopupGridArrow'+id).hasClass('arrow_down')){
		$('#asset-PgCountDetails'+id).html('');
		$('#asset-PgCountDetails'+id).append('<div class="loadPgImg"><img src="<html:imagesPath/>gridloading.gif"/></div>');
		getPageCountsData(id);	
	}
	toggleClass('#openPopupGridArrow'+id,'arrow_down');
	
	toggleClass('#openPopupGridArrow'+id,'arrow_up');
	
	
}
	
	
	


function submitPageCount(id){
	assetPgCntsObj.updatePageCounts(id);
}
function calShow(id,date){
	show_cal(id,formatDateToDefault(date),formatDateToDefault(todayStr));
}
function AssetPagecounts(){
	this.createAssetHTML=function(assetAr){
		$('#assetPageCounts-Multiple .error-pg ul').html('');
		$('#assetPageCounts-Multiple #asset-list').html('');
		$('#assetPageCounts-Multiple #asset-list').append(templateAssetPg({assets:assetAr}));
	};
	this.updateTablePgCount=function(asset){
		console.log(templatePgCounts(asset));
		$('#assetPageCounts-Multiple #asset-list #asset-PgCountDetails'+asset.id).html(templatePgCounts(asset));
	};
	this.setLoadUrl=function(url){
		this.url=url;
	};
	this.setPostUrl=function(url){
		this.updatePg=url;
	};
	this.updatePageCounts=function(id){
		var assetList=multiSelectAsset.getAssetList();
		var len=assetList.length;
		var updatedAsset;
		for(var i=0;i<len;i++){
			if(assetList[i].id==id){
				updatedAsset=assetList[i];	
				break;
			}
		}
		console.log("before update \n"+ JSON.stringify(updatedAsset));
		if(this.validateAll(updatedAsset)){
			return;
		}
		this.hideError();
		updatedAsset=this.setNwPgcount(updatedAsset);
		console.log("after update \n"+ JSON.stringify(updatedAsset));
		updatedAsset=this.generatePOST(updatedAsset);
		console.log("updated Post \n"+ JSON.stringify(updatedAsset));
		showOverlayPopup();
		$.ajax({
			url:this.updatePg+"&t="+new Date().getTime(),
			type:'POST',
			dataType:"json",
			data:updatedAsset,
			success: function(response){
					hideOverlayPopup();
					console.log(response);
					if(response.success){
						$('#assetPageCounts-Multiple .success').show();
						onClickAssetInfoPG(response.id);
					}else{
						$('#assetPageCounts-Multiple .failure').show();
					}
				},
		  failure: function(results){
			  
		  }
		});
	};
	this.validateAll=function(asset){
		var error=false,i=0,alBlank=true;
		
		for(var key in asset.pageCount){			
			i++;
			var valCount=$('#'+asset.id+'-c-'+asset.pageCount[key].index).val();
			var valDate=$('#'+asset.id+'-d-'+asset.pageCount[key].index).val();
			console.log("Type ="+key+" count="+valCount+" date="+valDate);
			if(valCount!="" && valDate==""){
				error=true;alblank=false;
				this.showError(pageCountsMessages.mandate["DateTime-Count"]+" "+i);
				break;
			}else if(valDate!="" && valCount==""){
				error=true;alblank=false;
				this.showError(pageCountsMessages.mandate["DateTime-Count"]+" "+i);
				break;
			}else if(valCount=="" && valDate==""){
				continue;
			}
			if(isNaN(valCount)){				
				error=true;
				this.showError(pageCountsMessages.mandate["InvalidVal"]);
				break;
			}
			alBlank=false;
			var prevC=parseInt(asset.pageCount[key].count)||0;
			var newC=parseInt(valCount)||0;
			var e1=this.validatePageCount(prevC,newC,key);
			var e2=this.validateDiff(prevC,newC,key);
			if(e1!=""){
				/**console.log("error" + str);*/
				error=true;
				this.showError(e1);
				break;
			}else if(e2!=""){
				/**console.log("error" + str);*/
				error=true;
				this.showError(e2);
				break;
			}			
		}
		if(alBlank){
			console.log('error -- '+pageCountsMessages.noValue);
			this.showError(pageCountsMessages.noValue);
			return true;
		}
	
		if(!error){
			/** This section is for checking difference errors ... */
			for(var key in this.pageCountDiffType){
				console.log(asset.pageCount[key]);
				if(asset.pageCount[key]){
					console.log('insisde if ... '+asset.pageCount[key]);
					var count1=parseInt($('#'+asset.id+'-c-'+asset.pageCount[key].index).val())||0;
					var count2=parseInt($('#'+asset.id+'-c-'+asset.pageCount[this.pageCountDiffType[key]].index).val())||0;
					if(count1==0 || count2==0){continue;}
					var er="";
					if((count1 > count2)){
						er=pageCountsMessages.diffMessage[type1+"-"+type2];
					}				
					if(er!=""){
						error=true;
						this.showError(er);
						break;
					}	
				}
				
			}
		}
				
		return error;
	};
	this.pageCountDiffType={
			"Color":"LTPC",
			"A3 Color":"A3 LTPC",
			"A4 Color":"A4 LTPC",
			"A5 Color":"A5 LTPC",
			"Letter Color":"Letter LTPC",
			"Legal Color":"Legal LTPC",
			"Statement Color":"Statement LTPC",
			"Tabloid Color":"Tabloid LTPC"
	};
	this.setNwPgcount=function(asset){
		for(var key in asset.pageCount){			
			var valC=$('#'+asset.id+'-c-'+asset.pageCount[key].index).val();
			var valD=$('#'+asset.id+'-d-'+asset.pageCount[key].index).val();
			if(valC!="" && valD!=""){
				asset.pageCount[key].count=valC;
				asset.pageCount[key].date=valD;
				asset.pageCount[key].updated=true;
			}
		}
		
		
		
		if((asset.pageCount['LTPC'] && asset.pageCount['Color']) && (asset.pageCount['LTPC'].updated || asset.pageCount['Color'].updated)){
			var cLTPC=parseInt(asset.pageCount['LTPC'].count)||0;var cColor=parseInt(asset.pageCount['Color'].count||0);
			if(cLTPC > cColor){
				var monoPgCount={
						"count":cLTPC - cColor,
						"date":todayStr,
						"updated":true
				};
				asset.pageCount["Mono"]=monoPgCount;		
			}	
		}
		
		return asset;
	};
	this.showError=function(error){
		$('#assetPageCounts-Multiple .error-pg ul').html('');
		$('#assetPageCounts-Multiple .error-pg ul').append("<li class='portlet-msg-error'>"+error+"</li>");
		$('#assetPageCounts-Multiple .error-pg').show();
		$('#assetPageCounts-Multiple .success').hide();
		
	};
	this.hideError=function(){
		$('#assetPageCounts-Multiple .error-pg').hide();
	};
	this.validatePageCount=function(oldCount,newCount,type){
		/** Need to check blank validation & typeOf!!*/
		
		if(oldCount>newCount){
			return pageCountsMessages.moreOrLess[type][0];
		}else{
			return "";
		}
	};
	this.validateDiff=function(oldCount,newCount,type){
		/** Need to check blank validation & typeOf!!*/
		if((newCount-oldCount)>50000 || (newCount-oldCount) > (30*2000)){
			return pageCountsMessages.moreOrLess[type][1];
		}else if((newCount-oldCount) < 10){
			return pageCountsMessages.moreOrLess[type][0];
		}else{
			return "";
		}
	};
	
	this.generatePOST=function(asset){
		var postObj={};
		postObj["assetId"]=asset.id;
		var pageCountRef=asset.pageCount;
		var i=0;
		var d=new Date();
		var utcTime=d.getUTCHours() + ":" + d.getUTCMinutes() + ":" + "00";
		for(var key in pageCountRef){
			if(pageCountRef[key].updated){
				postObj["pageCounts["+i+"].name"]=key.replace(" ","");
				postObj["pageCounts["+i+"].date"]=formatDateToDefault(pageCountRef[key].date)+" "+utcTime;
				postObj["pageCounts["+i+"].count"]=pageCountRef[key].count;
				i++;
			}
		}
		
		return postObj;
	};
}
var assetPgCntsObj=new AssetPagecounts();
assetPgCntsObj.setLoadUrl("<portlet:resourceURL id="getPageCountAssetData"/>");
assetPgCntsObj.setPostUrl("<portlet:resourceURL id="postPgCountData"/>");

function getPageCountsData(id){
	$.getJSON(assetPgCntsObj.url+"&assetRowId="+id,function(response){
		
		console.log('in response function');
		console.log(JSON.stringify(response));
		if(JSON.stringify(response)=="null")
		{
			$('#templatePgCount'+id).children('.buttonContainer').hide();
			$('#asset-PgCountDetails'+id).append('No page count records found.<br/><br/>');
			$('#asset-PgCountDetails'+id+' .loadPgImg').hide();					
		}
		else{
			multiSelectAsset.setPageCounts(response);
			assetPgCntsObj.updateTablePgCount(response);
			if(JSON.stringify(response.pageCount)=="{}"){
				$('#templatePgCount'+id).children('.buttonContainer').hide();
				$('#asset-PgCountDetails'+id).append('No page count records found.<br/><br/>');
			}
			
		}
	});
};


<%-- Below section is for Multi Asset select keep in separate JS--%>
function MultiAssetSelect(){
	
	this._assetList=[];
	this.moveFrom={};
	this.enabled=false;
	this.addAsset=function(asset){
		this._assetList=asset;
		
	};
	this.setPageCounts=function(asset){
		var len=this._assetList.length;
		var assetUpdate;
		for(var i=0;i<len;i++){
			if(this._assetList[i].id==asset.id){
				this._assetList[i].pageCount=asset.pageCount;
				break;
			}
		}
	};
	this.resetObj=function(){
		
		this._assetList=[];
		$('#multi-select-map').val('');
		this.enabled=false;
		$('#multi-select-move-table tbody').html('');
		$('#no-of-asset').html('');
	};
	this.setMoveFromAddress=function(address){
		this.moveFrom=address;
	};
	this.updateHtml=function(){
				
		/*This method will generate the html for the last asset added*/
		var cAsset=this._assetList;
		var html="";
		for(var i=0;i<cAsset.length;i++){
			if(i%2==0){
				html+="<tr><td>"+cAsset[i].serialNumber+"</td>";	
			}else{
				html+="<tr class=\"tableRowShade\"><td>"+cAsset[i].serialNumber+"</td>";
			}			
			html+=cAsset[i].ipAddress!=null?"<td>"+cAsset[i].ipAddress+"</td>":"<td></td>";
			html+="<td>"+cAsset[i].buildingName+"</td>";
			html+="<td>"+cAsset[i].floorName+"</td></tr>";			
		}
		$('#multi-select-move-table tbody').html(html);
		$('#no-of-asset').html(this._assetList.length);
	};
	this.processLBSResponse=function(info){
		/*This method will handle the click response from LBS*/
		this.addAsset(info);
		$('#multiMove #multiMoveTable').append(this.updateHtml());
		$('#multiMove #deviceSelcted').html(this._assetList.length);
	};
	this.handleClickConfirm=function(){
		/*This will handle the click on Next button setting the asset Ids to hidden input*/
		if($('#multi-select-map').val()=="updateMeter"){
			showPgCntMSlct();
		}else{
			var assetIds=[];
			for(var i=0;i<this._assetList.length;i++){
				assetIds.push(this._assetList[i].id);
			}
			
			$('#fleetMgmtForm #multiAssetList').val(assetIds.toString());
			$('#fleetMgmtForm #multiAssetRequestType').val($('#multi-select-map').val());
			
			if(multiSelectAsset._assetList.length>0)
				{
				
				$('#multiSelectContent').append(multiSelectTemplate({assets:multiSelectAsset._assetList}));
				}
			setBackInfo(zoomProcessor.zoomInfo);
			/*Submit the form...*/
			submitMultiAsset();/*Declared in default view .jsp*/
		}
		
	};
	this.showError=function(){
		jAlert("Please select atleast one asset to perform operation!","");
		
		
	};
	this.getAssetList=function(){
		return this._assetList;
	};
	this.enableMultiSelect=function(multiSelectMode){
		this.enabled=true;
		/* Get zoom level info for further use*/
		zoomProcessor.postZoom();
		var v=$('#multi-select-map').val();
		if(v!=""){
			lbs.postMessage({
				"action": "enableMultiSelect",
				 "item": "map"
			});
		}
		if(multiSelectMode=='move'){
			$('#multiAssetFlowHeaderMove').show();
			$('#multiAssetFlowHeaderChange,#multiAssetFlowHeaderDecomm').hide();
		}
		else if(multiSelectMode=='change' || multiSelectMode=='updateMeter'){
			$('#multiAssetFlowHeaderChange').show();
			$('#multiAssetFlowHeaderMove,#multiAssetFlowHeaderDecomm').hide();
		}
		else if(multiSelectMode=='decom'){
			$('#multiAssetFlowHeaderDecomm').show();
			$('#multiAssetFlowHeaderChange,#multiAssetFlowHeaderMove').hide();
		}		
		/* show the div with multi asset details*/
		$('#moveMultiAssetLeftNav').show();
		/* hide existing left nav div*/
		$('#leftNavDevices,#deviceStatusContent').hide();
	};
	this.closeMultiSelect=function(){
		/** Need to post json to disable multi select*/
		
		/** Clear asset list that might already been selected.*/
		this.resetObj();
		/* hide the div with multi asset details*/
		$('#moveMultiAssetLeftNav').hide();
		/* show existing left nav div*/
		$('#leftNavDevices,#deviceStatusContent').show();
		
	};
	this.openAsset=function(){
		if(this._assetList.length==1){
			onClickAssetInfoPG(this._assetList[0].id);
		}
	};
	
};

var multiSelectAsset=new MultiAssetSelect();
var pageCountsMessages={
		/** Type : [<count less error>,<count more error>]*/
		"moreOrLess":{
			"LTPC":["<spring:message code='meterRead.msg.LTPCValueLess'/>","<spring:message code='meterRead.msg.unreasonableLTPCHigh'/>"],
			"Color":["<spring:message code='meterRead.msg.colorReadLess'/>","<spring:message code='meterRead.msg.unreasonableColorReadHigh'/>"],
			"A3 Color":["<spring:message code='meterRead.msg.A3colorReadLess'/>","<spring:message code='meterRead.msg.unreasonableA3ColorHigh'/>"],
			"A3 LTPC":["<spring:message code='meterRead.msg.A3LTPCReadLess'/>","<spring:message code='meterRead.msg.unreasonableA3LTPCHigh'/>"],
			"A4 Color":["<spring:message code='meterRead.msg.A4colorReadLess'/>","<spring:message code='meterRead.msg.unreasonableA4ColorHigh'/>"],
			"A4 LTPC":["<spring:message code='meterRead.msg.A4LTPCReadLess'/>","<spring:message code='meterRead.msg.unreasonableA4LTPCHigh'/>"],
			"Scans":["<spring:message code='meterRead.msg.ScanReadLess'/>","<spring:message code='meterRead.msg.unreasonableScanHigh'/>"],
			"Fax":["<spring:message code='meterRead.msg.FaxReadLess'/>","<spring:message code='meterRead.msg.unreasonableFaxHigh'/>"],
			"Black":["<spring:message code='meterRead.msg.BlackReadLess'/>","<spring:message code='meterRead.msg.unreasonableBlackHigh'/>"],
			"Cyan":["<spring:message code='meterRead.msg.CyanReadLess'/>","<spring:message code='meterRead.msg.unreasonableCyanHigh'/>"],
			"Software":["<spring:message code='meterRead.msg.SoftwareReadLess'/>","<spring:message code='meterRead.msg.unreasonableSoftwareHigh'/>"],
			"PGS_SCAN_COPY":["<spring:message code='meterRead.msg.PgsScanCopyReadLess'/>","<spring:message code='meterRead.msg.unreasonablePgsScanCopyHigh'/>"],
			"PGS_SCAN_FAX":["<spring:message code='meterRead.msg.PgsScanFaxReadLess'/>","<spring:message code='meterRead.msg.unreasonablePgsScanFaxHigh'/>"],
			"PGS_SCAN_NETWORK":["<spring:message code='meterRead.msg.PgsScanNetworkReadLess'/>","<spring:message code='meterRead.msg.unreasonablePgsScanNetworkHigh'/>"],
			"PGS_SCAN_USB":["<spring:message code='meterRead.msg.PgsScanUsbReadLess'/>","<spring:message code='meterRead.msg.unreasonablePgsScanUsbHigh'/>"],
			"Total Scans":["<spring:message code='meterRead.msg.TotalScansReadLess'/>","<spring:message code='meterRead.msg.unreasonableTotalScansHigh'/>"],
			"Letter Color":["<spring:message code='meterRead.msg.LetterColorReadLess'/>","<spring:message code='meterRead.msg.unreasonableLetterColorHigh'/>"],
			"Letter LTPC":["<spring:message code='meterRead.msg.LetterLTPCReadLess'/>","<spring:message code='meterRead.msg.unreasonableLetterLTPCHigh'/>"],
			"A5 Color":["<spring:message code='meterRead.msg.A5ColorReadLess'/>","<spring:message code='meterRead.msg.unreasonableA5ColorHigh'/>"],
			"A5 LTPC":["<spring:message code='meterRead.msg.A5LTPCReadLess'/>","<spring:message code='meterRead.msg.unreasonableA5LTPCHigh'/>"],
			"Legal Color":["<spring:message code='meterRead.msg.LegalColorReadLess'/>","<spring:message code='meterRead.msg.unreasonableLegalColorHigh'/>"],
			"Legal LTPC":["<spring:message code='meterRead.msg.LegalLTPCReadLess'/>","<spring:message code='meterRead.msg.unreasonableLegalLTPCHigh'/>"],
			"Statement Color":["<spring:message code='meterRead.msg.StatementColorReadLess'/>","<spring:message code='meterRead.msg.unreasonableStatementColorHigh'/>"],
			"Statement LTPC":["<spring:message code='meterRead.msg.StatementLTPCReadLess'/>","<spring:message code='meterRead.msg.unreasonableStatementLTPCHigh'/>"],
			"Tabloid Color":["<spring:message code='meterRead.msg.TabloidColorReadLess'/>","<spring:message code='meterRead.msg.unreasonableTabloidColorHigh'/>"],
			"Tabloid LTPC":["<spring:message code='meterRead.msg.TabloidLTPCReadLess'/>","<spring:message code='meterRead.msg.unreasonableTabloidLTPCHigh'/>"]		
		},
		"diffMessage":{
			"Color-LTPC":"<spring:message code='meterRead.msg.colorReadDifference'/>",
			"A3 Color-A3 LTPC":"<spring:message code='meterRead.msg.A3colorReadDifference'/>",
			"A4 Color-A4 LTPC":"<spring:message code='meterRead.msg.A4colorReadDifference'/>",
			"A5 Color-A5 LTPC":"<spring:message code='meterRead.msg.A5colorReadDifference'/>",
			"Letter Color-Letter LTPC":"<spring:message code='meterRead.msg.LettercolorReadDifference'/>",
			"Legal Color-Legal LTPC":"<spring:message code='meterRead.msg.LegalcolorReadDifference'/>",
			"Statement Color-Statement LTPC":"<spring:message code='meterRead.msg.StatementcolorReadDifference'/>",
			"Tabloid Color-Tabloid LTPC":"<spring:message code='meterRead.msg.TabloidcolorReadDifference'/>"			
		},
		"mandate":{
			"DateTime-Count":"<spring:message code='validation.properPageCount'/>",
			"InvalidVal":"<spring:message code='validation.error.notNumber'/>"
		},
		"noValue":"<spring:message code='contact.change.nodata'/>"
		
	
		
};

</script>