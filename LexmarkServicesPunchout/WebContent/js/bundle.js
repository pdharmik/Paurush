/**
 * 
 */

 var bundlesObj={
			
			bundlesData:{},
			globalSearchAccessories:{},
			initTemplate:function(){
				YUI().use('handlebars','node-base', function (Y) {
					
					bundlesObj.learnMoreObj = Y.Handlebars.compile(Y.one('#learn-more-part-template').getHTML());
					bundlesObj.bundleTemplate=Y.Handlebars.compile(Y.one('#bundle-list-template').getHTML());
					bundlesObj.accessoriesTemplateObj =Y.Handlebars.compile(Y.one('#accessories-list-template').getHTML());
					bundlesObj.bundleSearchTemplate=Y.Handlebars.compile(Y.one('#bundleSearch-list-template').getHTML());
			
				
				Y.Handlebars.registerHelper('billingModelCheck', function (billingModel, price) {
					if(billingModel=='Ship and Bill' && price ==""){
						return "disabled=\"disabled\"";
					}	
					
				});

		});
			},
						
			parseData:function(){
				var monoLaser="<h2 class=\"accordion-header\">Mono Laser</h2><div class=\"accordion-content\">";
				var colorLaser="<h2 class=\"accordion-header\">Color Laser</h2><div class=\"accordion-content\">";
				var monoMFP="<h2 class=\"accordion-header\">Mono MFP</h2><div class=\"accordion-content\">";
				var colorMFP="<h2 class=\"accordion-header\">Color MFP</h2><div class=\"accordion-content\">";
				
				for(var index in this.bundlesData){
					
					var bundle=this.bundlesData[index];
					
					switch(bundle.bundleType){
						case "Mono Laser Printer":
							monoLaser+=this.bundleTemplate(bundle);
							break;
						case "Color Laser Printer":
							colorLaser+=this.bundleTemplate(bundle);
							break;
						case "MFP Mono Laser Printer":
							monoMFP+=this.bundleTemplate(bundle);
							break;
						case "MFP Color Laser Printer":
							colorMFP+=this.bundleTemplate(bundle);
							break;
						
						
					}
					
					
				}
				monoLaser+="<div class=\"clearBoth\"></div></div>";
				colorLaser+="<div class=\"clearBoth\"></div></div>";
				monoMFP+="<div class=\"clearBoth\"></div></div>";
				colorMFP+="<div class=\"clearBoth\"></div></div>";
				
				$('#printerBundle_container').html('');
				$('#printerBundle_container').append(monoLaser).append(colorLaser).append(monoMFP).append(colorMFP);
				initAccordian('tab_bundleGrid');

			},
			parseData_PrinterType:function(printerType){
				var printerHtml="";
				for(var index in this.bundlesData){					
					printerHtml+=this.bundleTemplate(this.bundlesData[index]);
				}				
				
				$('#pageTitle').html(this.getPrinterTypeMessage(printerType).title);
				$('#printerBundle_container').append(printerHtml);
			},
			getPrinterTypeMessage:function (type){
				
				var msg={title:"",type:""};
				switch(type){
				case "mono_multi_laser":
					msg.title="Multifunction Mono Laser";
					msg.type="MFP Mono Laser Printer";
					break;
				case "color_single_laser":
					msg.title="Color Laser";
					msg.type="Color Laser Printer";
					break;
				case "color_multi_laser":
					msg.title="Multifunction Color Laser";
					msg.type="MFP Color Laser Printer";
					break;
				case "Dot Matrix":
					msg.title="Dot Matrix";
					msg.type="OPTION (Generic)";
					break;
				case "mono_single_laser":
					msg.title="Mono Laser";
					msg.type="Mono Laser Printer";
					break;
				
				}
				
				return msg;
			},
			parseData_searchResult:function(){
					var keys=Object.keys(bundlesObj.bundlesData);
					for(var i=0;i<keys.length;i++){
						var bId=keys[i];
						$('#globalSearch_container #bundle-global-search').append(bundlesObj.bundleTemplate(bundlesObj.bundlesData[bId]));
					}
					keys=Object.keys(bundlesObj.globalSearchAccessories);
					
					for(var i=0;i<keys.length;i++){
						var accId=keys[i];
						$('#globalSearch_container #accessories-global-search').append(bundlesObj.accessoriesTemplateObj(bundlesObj.globalSearchAccessories[accId]));						
					}			   		
						

			}		
	}; 
 
function gotToOptions(bundleId){
		var bundle=cartItems.getItem(bundleId);
		bundlesObj.bundlesData={};
		bundlesObj.bundlesData[bundleId]=bundle;
		$('#showPrinterDetails').html(bundlesObj.bundleTemplate(bundle));
		$('#printerBundle_container').html('');
		$('#showPrinterDetails #optn_warrnt_img_'+bundleId).click();
		showHideDivs('showPrinterDetails');
		closeShoppingCartPopup();
}
function learnMorePopup(bundleId, type) {
	$('#learn-more-popup-loading-img').show();
	$('#learn-more-content').html('');
	if (type == 'bundle') {
		var partList = bundlesObj.bundlesData[bundleId].parts;
		for ( var i = 0; i < partList.length; i++) {
			<div class="product_title" id="printerId">{{bundleBrand}}  {{bundleModel}}</div>
			var url = "https://www.lexmark.com/en_US/epg/products/"
					+ partList[i].no.trim() + ".json";
			$.getJSON(url, function(response) {
				$('#part_info' + response.pn).append(
						bundlesObj.learnMoreObj(response));
				initAccordian('part_info' + response.pn);
				$('#learn-more-popup-loading-img,.packagedSize_in').hide();
			});
		}
	} else {
		$('#learn-more-content').append(
				'<div class="part_info" id="part_info' + bundleId
						+ '"><p class=\"product_title\">' + bundleId
						+ '</p></div>');
		var url = "https://www.lexmark.com/en_US/epg/products/" + bundleId
				+ ".json";
		$.getJSON(url, function(response) {
			$('#part_info' + response.pn).append(
					bundlesObj.learnMoreObj(response));
			initAccordian('part_info' + response.pn);
			$('#learn-more-popup-loading-img,.not_bundle,.size_in').hide();
		});
	}
	dialogLearnMore.dialog('open');
}
function convertToParams(params){
	var paramString="";
	for (var param in params){
		paramString+=("&"+param+"=");
		paramString+=params[param];
		
	}
	return paramString;
}


