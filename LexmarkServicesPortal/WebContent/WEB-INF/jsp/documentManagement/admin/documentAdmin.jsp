<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=1.0" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css"><%@ include file="/WEB-INF/css/mps-ci.css" %></style> <%-- Added for CI BRD 14-06-01 --%>
<portlet:actionURL var="documentNameURL"><portlet:param name="action" value="documentName"/></portlet:actionURL>
<portlet:renderURL var="categoryDisplayURL"><portlet:param name="action" value="categoryDisplay"/></portlet:renderURL>
<portlet:resourceURL id="deleteDocumentName" var="deleteURL"></portlet:resourceURL>
<style>
.content > div {
    overflow: auto;
    padding: 10px 0;
    width: 100%;
}
#pagingArea > div {
    width: 100% !important;
}
.ie7 .portlet-topper{width:99% !important;}
.dhx_combo_edit{width:124px !important;
border-color:#BFBFBF #DEDEDE #DEDEDE #BFBFBF !important;
border-radius:0 !important;
border-style:solid !important;
border-width:1px !important;
position:static !important;
padding:4px!important;
margin-left: 8px;}
.content a { text-decoration:none;}
.displayOrderGridStyle {
/* max-height:180px; */overflow-y:hidden;overflow-x:hidden;
}
#displayOrderGrid .objbox {
/* max-height:174px!important; */
/* overflow-y:auto!important; */
}

#editDisplayPopup {
width:850px;
padding:1px;
min-width: 250px; 
display:none; 
overflow-y:auto;
overflow-x:hidden;
max-height:250px
}

.ie7 #displayOrderGrid .xhdr {
	position: static!important;
}
.ie7 #displayOrderGrid .objbox {
	position: static;
}

.ie7 .buttonContainer2 .button {
 behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc);
 position: static;
}
</style>
<script type="text/javascript">

function str_customSort(a,b,order){ 
    return (a.toLowerCase()>b.toLowerCase()?1:-1)*(order=="asc"?1:-1);
};

$(document).ready(function(){
	var width1=$(".main-wrap").width();
	$('#documentListContainer').css({'width':width1+'px'});
});
var categorySelected="";
function goDocumentName(id,custAcc){  // ******* custAcc added for DOC LIB CR *******
	callOmnitureAction('Document Admin', 'Document Admin Goto Document');
	var url = "${documentNameURL}";
	if(id){
		url +="&id="+id+"&custAcc="+custAcc; // ******* custAcc added for DOC LIB CR *******
	}
	location.href = url;
}

function goCategoryDisplay(){
	callOmnitureAction('Document Admin', 'Document Admin Goto Category');
	var url = "${categoryDisplayURL}";
	location.href = url;
}

function deleteDefinition(id){
	callOmnitureAction('Document Admin', 'Document Admin Delete Definition');
	if(!confirm("<spring:message code='documentUserPage.text.confirmDeleteDocumentDefinition'/>")){
		return;
	}
	var deleteURL = "${deleteURL}";
	deleteURL +="&id="+id; 
	doAjax(deleteURL, function(){documentListGrid.deleteSelectedRows();});
}
function displayCategoryPopup(category)
{

	jQuery("#duplicateOrder").hide();
	jQuery("#numericOrder").hide();
	jQuery("#maxOrder").hide();
	jQuery("#orderSuccess").hide();
	
	categorySelected=category;
	
	/* document.getElementById("editDisplayPopup").style.display = 'block';
	dialog = jQuery("#editDisplayPopup").dialog({
		autoOpen: false,
		open: function() {jQuery(".ui-dialog-titlebar-close").show();},
		modal: true,
		closeOnEscape: false,
		title: "<spring:message code='documentAdminPage.title.popUpHeader'/>",
		resizable: false,
		position: "center",
		width: 850,
		height: "auto"
		
		});
	
	dialog.dialog("open");
	
	if (window.PIE) {
        document.getElementById("popupUpdate").fireEvent("onmove");
        document.getElementById("popupCancel").fireEvent("onmove");
    } */
	initializeDisplayOrderGrid();
    
}
function displayCategoryPopup2()
{
	document.getElementById("editDisplayPopup").style.display = 'block';
	dialog = jQuery("#editDisplayPopup").dialog({
		autoOpen: false,
		open: function() {jQuery(".ui-dialog-titlebar-close").show();},
		modal: true,
		closeOnEscape: false,
		title: "<spring:message code='documentAdminPage.title.popUpHeader'/>",
		resizable: false,
		position: "center",
		width: 830,
		height: "auto",
		close: function(event,ui){
			//dialog.dialog('destroy');
			
			jQuery('#editDisplayPopup').removeAttr('style');
			jQuery('#displayOrderGrid').empty();
			jQuery('#displayOrderGrid').removeAttr('style');
			document.getElementById("editDisplayPopup").style.display = 'none';
			//alert('1');
			//dialog.dialog('destroy');
			
			}
		
		});
	
	dialog.dialog("open");
	
	if (window.PIE) {
        document.getElementById("popupUpdate").fireEvent("onmove");
        document.getElementById("popupCancel").fireEvent("onmove");
    }
}
function doUpdate(){
	jQuery("#maxOrder").empty();
	jQuery("#numericOrder").hide();
	jQuery("#duplicateOrder").hide();
	jQuery("#negativeOrder").hide();
	jQuery("#decimalOrder").hide();
	jQuery("#orderSuccess").empty();
	var x=document.getElementsByName("displayOrder");
	var rowCount= documentOrderGrid.getRowsNum();
	var orderValues = new Array();
	var docIds = new Array();
	var count =0;
	var validateOrder=true;
	var duplicate=true;
	var numeric=true;
	var maxOrder=true;
	var minOrder=true;
	var isNegative=true;
	var isDecimal = true;
	//documentOrderGrid.editStop();
	for(var i=0;i<x.length;i++){
		docIds[count]=documentOrderGrid.cellById(i+1,0).getValue();
		//alert("docIds[count]"+docIds[count]);
		//var cellObj=documentOrderGrid.cellById(i,3).getValue();
		//var cellObj=documentOrderGrid.cellById(i,3);
		var cellvalue=x[i].value;
		//alert('cell val'+cellvalue);
		orderValues[count]=cellvalue;
		count++;
			
	}
	//alert('docIds'+docIds);
	//alert('orderValues'+orderValues);
	for(var j=0;j<x.length;j++){		
		if(isNaN(x[j].value)){
			//alert("Not Numeric");
			numeric=false;
		}
		for(var k=j+1;k<x.length;k++)
		{
		 if(x[j].value == x[k].value)
		 {
		  duplicate = false;
		  break;
		 }
		}
		
		if(x[j].value>x.length){
			
			maxOrder=false;
		}
		if(x[j].value==0){
			
			minOrder=false;
		}
		if(x[j].value.indexOf(".")>0)
			{
			isDecimal = false;
			}
       if(x[j].value<0){			
			isNegative=false;
		   }

	}
	
if(!duplicate)
	{
	jQuery("#duplicateOrder").show();
	validateOrder=false;
	}
	
if(!isDecimal)
{
jQuery("#decimalOrder").show();
validateOrder=false;
}

if(!isNegative)
	{
	jQuery("#negativeOrder").show();
	validateOrder=false;
	}

if (!numeric){
	jQuery("#numericOrder").show();
	validateOrder=false;
}

if(!maxOrder){	
	jQuery('#maxOrder').append('<li><strong><spring:message code="documentAdminPage.validation.maxDiplayOrder"/> '+docIds.length+'</strong></li>');
	jQuery("#maxOrder").show();
	validateOrder=false;
}

if(!minOrder){
	jQuery('#maxOrder').append('<li><strong><spring:message code="documentAdminPage.validation.minDiplayOrder"/> '+1+'</strong></li>');
	jQuery("#maxOrder").show();
	validateOrder=false;
}

	if(validateOrder==true){
		
		dialog.dialog('close');
	
	
	var updateOrderGridURL="<portlet:resourceURL id="updateOrderGrid"/>";
	
	jQuery.ajax({
		url:"<portlet:resourceURL id='updateOrderGrid'></portlet:resourceURL>",
		data:{
			docIds: docIds.toString(),
			orderValues: orderValues.toString()
			 },
		type:'POST',		
		success: function(results){
			try{
				jQuery('#orderSuccess').append('<li><strong><spring:message code="documentAdminPage.displayOrder.successMsg"/></strong></li>');
				jQuery("#orderSuccess").show();
				documentListGrid.clearAndLoad(documentListGridURL);
				}
			catch(e){
				}
			
		}
		
				
	});	
	} 
	
}

/* function editDisplayOrder(){
	var rowCount= documentOrderGrid.getRowsNum();
	for(var i=1;i<=rowCount;i++){
  		var cellObj = documentOrderGrid.cellById(i,3);
  		var cellvalue=cellObj.getValue();
		var strt=cellvalue.indexOf(">")+1;
		var end=cellvalue.indexOf("</");
		var orderTxt = cellvalue.substring(strt,end);
		if(isNaN(orderTxt))
			{
			orderTxt="";
			}
		documentOrderGrid.setCellExcellType(i,3,"ed");
		cellObj.cell.innerHTML="<input type=text value=\""+orderTxt+" "+"\" onmouseover=\"this.click();\">";
	}
	
} */


</script>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap"><div class="content">
<div id="editDisplayPopup">
<div id="duplicateOrder" class="error" style="display: none;"><li><Strong><spring:message code="documentAdminPage.validation.duplicateDiplayOrder"/></Strong></li></div>
<div id="numericOrder" class="error" style="display: none;"><li><Strong><spring:message code="documentAdminPage.validation.numericDiplayOrder"/></Strong></li></div>
<div id="decimalOrder" class="error" style="display: none;"><li><Strong><spring:message code="documentAdminPage.displayOrder.noDecimal"/></Strong></li></div>
<div id="negativeOrder" class="error" style="display: none;"><li><Strong><spring:message code="documentAdminPage.displayOrder.negativeOrder"/></Strong></li></div>
<div id="maxOrder" class="error" style="display: none;"></div>
    		<div class="columnInner" id="loadingDialog">
    		<div class="columnsOne  displayOrderGridStyle">
    					<div class="portlet-wrap rounded">
  						<div class="">	
						 	 <div id="displayOrderGrid" class="gridbox gridbox_light"></div>
							<div id="loadingNotificationPopup_dislayOrder" class="gridLoading" style="display: none;">
										<img src="<html:imagesPath/>gridloading.gif" /><br>
			  				</div>
						</div>
					</div>
					
					<div class="pagination" >
						<span id="pagingAreaPopup"></span><span id="infoArea_pop"></span>
					</div>
      		</div>
      		<div class="buttonContainer popupBtnCont buttonContainer2">
      		<button id="popupUpdate" class="button" onclick="javascript:doUpdate();"><spring:message code="button.update"/></button>
      		<button id="popupCancel" class="button" onclick="dialog.dialog('close');"><spring:message code="button.cancel"/></button>
      		</div>
      		</div>
		</div>
<div>
  	<div id="orderSuccess" class="serviceSuccess" style="display: none;"></div>
  	<div class="orangeSectionTitles padding-bottom-0px"><spring:message code="documentAdminPage.title" /></div> 
  	<span><spring:message code="documentAdminPage.description" /></span>
  </div>
  <div>
  		<a href="javascript:goDocumentName();" class="button color-white"><spring:message code="documentAdminPage.button.addNewDocumentName" /></a>
  		<a href="javascript:goCategoryDisplay();" class="button button-style3"><spring:message code="documentAdminPage.button.categoryDisplay" /></a>
  </div>
  <div>
		<div id="documentListContainer" class="div-style43"></div>
	</div>
	<div>
  		<div id="documentListNoticefier" class='gridLoading' style="display: none;">
  			<img src="<html:imagesPath/>gridloading.gif"/><br>
  		</div>
		<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
  </div>
</div></div>
<script type="text/javascript">

	<portlet:resourceURL id="documentListGrid" var="documentListGridURL"/>
	var documentListGridURL = "${documentListGridURL}";
	documentListGrid = new dhtmlXGridObject('documentListContainer');
	documentListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	documentListGrid.setHeader("<spring:message code='documentAdminPage.listHeader'/>");
	documentListGrid.attachHeader("#text_filter,#text_filter,,#text_filter,#text_filter,");  // ******* limit flag and Customer Account added *******
	documentListGrid.setColAlign("left,left,left,left,left,left");
	documentListGrid.setColTypes("ro,ro,ro,ro,ro,ro");
	documentListGrid.setColSorting("int,domSort,str,str,str_customSort,na");
	documentListGrid.setInitWidthsP("20,30,10,20,20,10");
	documentListGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	documentListGrid.setPagingSkin("bricks");
	
	documentListGrid.enableAutoWidth(true);
	documentListGrid.enableAutoHeight(true);
	documentListGrid.enableMultiline(true);
	documentListGrid.setSizes();
	documentListGrid.attachEvent("onMouseOver", function(id,ind){
		documentListGrid.setRowTextStyle(id, "cursor: pointer;");
	});
	
	documentListGrid.attachEvent("onXLE", function() {
	    document.getElementById('documentListNoticefier').style.display = 'none';
	    setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});
	documentListGrid.attachEvent("onXLS", function() {
	    document.getElementById('documentListNoticefier').style.display = 'block';
	});
	documentListGrid.setSkin("light");	
	documentListGrid.init();
	documentListGrid.loadXML(documentListGridURL);
</script>
<script type="text/javascript">

function initializeDisplayOrderGrid(){
	"<portlet:resourceURL id="displayOrderGrid" var="displayOrderGridURL">" + 
	"</portlet:resourceURL>"
	var displayOrderGridURL = "${displayOrderGridURL}";
	displayOrderGridURL+="&categoryName="+categorySelected;
	documentOrderGrid = new dhtmlXGridObject('displayOrderGrid');
	documentOrderGrid.setImagePath("<html:imagesPath/>gridImgs/");
	documentOrderGrid.setHeader("<spring:message code='documentAdminPage.listHeader.displayOrderpopup'/>");
	documentOrderGrid.setColAlign("left,left,left,left");
	documentOrderGrid.setColTypes("ro,ro,ro,ro");
	documentOrderGrid.setColSorting("int,domSort,str,str");
	documentOrderGrid.setInitWidths("250,350,350,*");
	documentOrderGrid.setLockColVisibility("false,true,true,true");
	//documentOrderGrid.enablePaging(true, 5, 5, "pagingAreaPopup", true, "infoArea_pop");
	documentOrderGrid.setPagingSkin("bricks");
	documentOrderGrid.enableAutoWidth(true);
	documentOrderGrid.enableAutoHeight(true);
	documentOrderGrid.enableMultiline(true);
	documentOrderGrid.setColumnHidden(0, true);
	documentOrderGrid.enableEditEvents(true,false,false);
	documentOrderGrid.setSizes();
	documentOrderGrid.attachEvent("onMouseOver", function(id,ind){
		documentOrderGrid.setRowTextStyle(id, "cursor: pointer;");
	});
	
	documentOrderGrid.attachEvent("onXLE", function() {
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	    document.getElementById('loadingNotificationPopup_dislayOrder').style.display = 'none';
	    displayCategoryPopup2();
	});
	documentOrderGrid.attachEvent("onXLS", function() {
	    document.getElementById('loadingNotificationPopup_dislayOrder').style.display = 'block';
	});
	documentOrderGrid.setSkin("light");	
	documentOrderGrid.init();
	documentOrderGrid.loadXML(displayOrderGridURL);
	
	//When the grid cell will be editable, this method will be called
	documentOrderGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		//alert('in attach');
		
						if(stage==0){
							//alert('stage 0');
										var cellObj = documentOrderGrid.cellById(rId,cInd);
										
	   								  cellObj.setValue(jQuery(cellObj.getValue()).val());
	   								

							}else if(stage==1){
								//alert('stage 1');

							}else if(stage==2){
								//alert('in stage 2');
								var cellObj = documentOrderGrid.cellById(rId,cInd);
								var cellvalue=cellObj.getValue();

								cellObj.cell.innerHTML="<input type=text value=\""+cellvalue+" "+"\" onmousedown=\"this.click();\">";

							}
						 return true;

					
					
			});
}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Document Admin";
     addPortlet(portletName);
     pageTitle="Document admin";
</script>