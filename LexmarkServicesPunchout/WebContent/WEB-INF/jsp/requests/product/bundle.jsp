<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<portlet:resourceURL var="retrieveBundleGrid" id="retrieveBundleGrid"></portlet:resourceURL>
	
<style>
.gridSubrowHeader {
	background-color: #3a81ab;
    border: 1px solid #d8d8d8;
    color: #fff;
    float: left;
    font-size: 14px;
    height: 27px;
    line-height: 26px;
    overflow: auto;
    width: 99%;
    padding-left: 5px;
}
div.gridbox_light table.obj td img {
    cursor: pointer;
    display: inline-block;
    float: right;
    padding: 7px 3px;
}
div.gridbox_light table.obj tr.rowselected td{
	background-color: inherit;
}
tr.altRow td { background-color:#f0f0f0 !important; }
.objbox table .rowselected td a{
	color:#1e68bc !important;
}
</style>    

<div class="mid-cntnr"> 
<div class="columnInner separatorV">
                         <div id="div_bundleGrid">
							<div id="tab_bundleGrid"  style="display:block;">
								<div id="printerBundle_container" style="width:100%;"></div>
								<div id="loadingNotification_printer" class='gridLoading'><img src="<html:imagesPath/>gridloading.gif"/><br/>
	    	  </div>
	  							<div class="pagination"><span id="bundlesPagingArea"></span><span id="bundlesInfoArea"></span></div>
	  						</div>
	    				</div><!-- mygrid_container -->
	              	 	</div>
  </div>
  <div id="requestDetailsPopup" style="display:none" ></div>
<script>

	var bundleGrid;
	if("${forGlobalSearch}" != "true"){
	var url= "${retrieveBundleGrid}";
	if(global_click_msgs.clickedFrom=="printerList"){//defined in rightNavHome.jsp
		url+="&pTyp="+printerObject.printerType+"&cType="+cartCheckObj.cartType+"&fromPrinterList=true";//Printerype object resides in printerlist jsp
	}
	else if(global_click_msgs.clickedFrom=="homePageProducts" && objLinkProducts.isPrinter=="true"){//for dot matrix printers
		url+="&pTyp="+objLinkProducts.partType+"&cType="+cartCheckObj.cartType;
	}else if(global_click_msgs.clickedFrom=="homePageProducts"){
		url+="&partType="+objLinkProducts.partType+"&cType="+cartCheckObj.cartType;//objLinkProducts.contractNumber resides in certifiedproducts.jsp
	}else if(global_click_msgs.clickedFrom=="certProducts"){
		url+="&certType="+objLinkProducts.certType+"&cType="+cartCheckObj.cartType;//objLinkProducts.contractNumber resides in certifiedproducts.jsp
	}else if(global_click_msgs.clickedFrom=="srDetails"){
		url+="&srNumber="+srDetailsObj.srNumber+"&rTyp="+srDetailsObj.srType+"&cType="+cartCheckObj.cartType;//srDetailsObj resides in gridrequesthistory.jsp
	}
	else if(global_click_msgs.clickedFrom=="shoppingCartPage"){
		url+="&fromCart="+shoppingCartObj.fromCart+"&cType="+cartCheckObj.cartType;
		if(objLinkProducts.isPrinter != null && objLinkProducts.isPrinter != undefined 
				&& objLinkProducts.isPrinter=="true")
			url+="&pTyp="+objLinkProducts.partType;
		else if(objLinkProducts.partType != null && objLinkProducts.partType != undefined 
				&& objLinkProducts.partType.length > 0)
			url+="&partType="+objLinkProducts.partType;
		else if(objLinkProducts.certType != null && objLinkProducts.certType != undefined
				&& objLinkProducts.certType.length > 0)
			url+="&certType="+objLinkProducts.certType;
	}
	}
	//var headerString = "Request No.,Date/Time Created,Request Type,Area,Status,&nbsp;";
	var plus_minus_path="<html:imagesPath/>";
	bundleGrid = new dhtmlXGridObject('printerBundle_container');
	bundleGrid.setImagePath("<html:imagesPath/>gridImgs/");
	bundleGrid.setHeader(",");
	bundleGrid.setNoHeader(true);
	bundleGrid.setInitWidths("0,*");
	
	bundleGrid.setColAlign("left","left");
	bundleGrid.setSkin("light");
	bundleGrid.setColSorting("na,na");
	bundleGrid.setColTypes("sub_row_ajax,ro");
	bundleGrid.enableAutoHeight(true);
	bundleGrid.enableMultiline(true);
	bundleGrid.enableColumnMove(false);
	bundleGrid.enableAlterCss("even_row","odd_row");
	bundleGrid.init();
	if("${forGlobalSearch}"=="true"){
		bundleGrid.enablePaging(true,2, 2, "bundlesPagingArea", true, "bundlesInfoArea");
	}
	else{
		bundleGrid.enablePaging(true,15, 2, "bundlesPagingArea", true, "bundlesInfoArea");
	}
	bundleGrid.setPagingSkin("bricks");
	bundleGrid.attachEvent("onXLE", function() {
    	jQuery('#loadingNotification_printer').hide();
    	if("${fromAriba}"=="true"){
    		if("${forGlobalSearch}" != "true"){
    			loadCartSize(cartObj);
    		}
    	}    	
    	
	});
	bundleGrid.attachEvent("onXLS", function() {
		jQuery('#loadingNotification_printer').show();
	});
	
	
	
	bundleGrid.attachEvent("onMouseOver", function(id,ind){
		if(ind==1){				
			return false;
		}else{
			return true;
			}
		});
	if("${forGlobalSearch}"=="true"){
		bundleGrid.parse("${bundleListXml}");
	}
	else{
		bundleGrid.loadXML(url);
	}
	
	
	function openSubRow(rowId,imgObj){
		if(imgObj.src.indexOf("minus.png")==-1){
			//open the cell
			imgObj.src=plus_minus_path+"minus.png";
			bundleGrid.cellById(rowId, 0).open();
		}else{
			//close the cell
			imgObj.src=plus_minus_path+"plus.png";
			bundleGrid.cellById(rowId, 0).close();
		}
		
	}
	
	var dialog;
	function learnMorePopup(bundleId){
		var url = "<portlet:resourceURL id='loadLearnMorePopup'></portlet:resourceURL>&bundleId="+bundleId;
    	url= encodeURI(url);
    	showOverlay();	
		jQuery('#requestDetailsPopup').load(url);
	}

	
           
    </script>  
      
       
        
    