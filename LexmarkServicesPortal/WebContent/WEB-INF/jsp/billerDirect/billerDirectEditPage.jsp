<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<portlet:actionURL var="finalUpdate">
	<portlet:param name="action" value="updateBillerDirectURL"></portlet:param>
</portlet:actionURL>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
	<div class="main-wrap">
      <div class="content"> 
	      <div class="portlet-wrap columnInner">
		       
		      	<div class="expand-min">
		        	<ul>
		            	<li class="first"><img src="" alt="Return to View Mode" width="24" height="24" title="Return to View Mode" /><a href="javascript:showPortletPage();">Return to View Mode</a> </li>
		            </ul>
		        </div>
		      
		  </div>
		  <form:form id="billerDirectForm" commandName="billerDirectForm" method="post">
		  <form:hidden path="sno" id="sno"/>
		  <form:hidden path="funcURLId" id="funcURLId"/> 
		  <form:hidden path="funcName" id="funcName"/>
		  <form:hidden path="funcUrl" id="funcUrl"/>
		  <form:hidden path="languageSupport" id="languageSupport"/>
		  <form:hidden path="deletedURL" id="deletedURL"/>
		  <form:hidden path="deleteSubRowURLId" id="deleteSubRowURLId"/>
		  <div class="portletBlock infoBox rounded shadow">
          	<div class="columnInner">
              <h4>General Information</h4>
            </div>
              <table class="dataGrid columnInner" border="0" cellspacing="0" cellpadding="0">
                <tr>
					<td width="150" class="strong">Header Type:</td>
                  	<td>
				  		<select class="selectWidth" name="header" id="header"> 
					  		<option value="horizontal">HORIZONTAL</option>
					  		<option value="vertical">VERTICAL</option>
				  		</select>
				  	</td>
                </tr>
                <tr>
                  <td width="150" class="strong">Maximum Links Allowed:</td>
                  <td><input class="w100" type="text"/></td>
                </tr>
                <tr>
                  <td width="150" class="strong">Height:</td>
                  <td><input class="w100" type="text"/></td>
                </tr>
                <tr>
                  <td width="150" class="strong">Width:</td>
                  <td><input class="w100" type="text"/></td>
                </tr>
				<tr>
                  <td width="150" class="strong">Scrolling:</td>
                  <td><input class="w100" type="text"/></td>
                </tr>
                <tr>
                  <td width="150" class="strong">Relative to Context Path:</td>
                  <td><input  type="checkbox"/></td>
                </tr>
              </table>
        </div>
        <h3 class="pageTitle">Advance Information</h3>
        <div class="grid-controls">
			<div class="expand-min">
				<ul>
					<li class="first">
						<img class="ui_icon_sprite expand-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="expandAll()" />
						&nbsp;<a href="javascript:void(0)" onClick="expandAll()"; ><spring:message code="requestInfo.link.expandAll"/></a>
					</li>
					<li>
						<img class="ui_icon_sprite minimize-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="minimizeAll()" />
						&nbsp;<a href="javascript:void(0)" onClick="minimizeAll()"; ><spring:message code="requestInfo.link.minimizeAll"/></a>
					</li>
				</ul>
			</div>
			<div class="clear-right"></div>
		</div>
        
        
		
         <div class="portletBlock infoBox rounded shadow">
        	<div class="columnsOne">
            	<div class="columnInner">
              		<h4>Configuration Details</h4>
				</div>
        	
            	
				<div id="billerDirectGridObj" class="width-100per"></div>
					<div id="loadingNotification" class='gridLoading'>
	      				<!-- spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	  				</div>
              
			   	<div class="portlet-wrap columnInner">
			  		<div class="floatR"><button id="addHeader" onclick="addHeaderToGrid();return false;">Add Another Header</button></div>
             	</div>
          </div>
           </div>
        <div class="buttonContainer">
			<a class="button_cancel" href="javascript:showPortletPage();">CANCEL</a>
        	<button class="button" id="btn_save">SAVE</button>
        </div>
        </form:form>
       
      </div>
    </div>
<script type="text/javascript">
//alert('entered');
var deleteURLId = new Array();
var deleteSubRowURLId = new Array();
var supportedLocaleList;
var satrPosition;
var endPosition;
var inputId;
<c:forEach items="${languageMap}" var="languageMapRow">
supportedLocaleList += '<option value="${languageMapRow.key}">${languageMapRow.value}</option>';
</c:forEach>
var retrieveURLListVar="<portlet:resourceURL id='retriveSapList'></portlet:resourceURL>";
var headerString = "&nbsp;,S.No,Functionality Name,URL,Language Support,Delete Header";
billerDirectgrid = new dhtmlXGridObject('billerDirectGridObj');
billerDirectgrid.setHeader(headerString);
billerDirectgrid.setInitWidths("30,40,200,600,130,130");
billerDirectgrid.setColAlign("left,center,left,left,center,center");
billerDirectgrid.enableResizing("false,false,false,false,false,false");
billerDirectgrid.setSkin("light");
billerDirectgrid.setImagePath("<html:imagesPath/>gridImgs/");
billerDirectgrid.setColTypes("sub_row,ro,ro,ro,ro,ro");
billerDirectgrid.enableAutoHeight(true,10000,true);
billerDirectgrid.enableMultiline(true);
billerDirectgrid.enableColumnMove(true);
billerDirectgrid.setAwaitedRowHeight(20000);
billerDirectgrid.init();
billerDirectgrid.setPagingSkin("bricks");
billerDirectgrid.attachEvent("onXLE", function() {
	document.getElementById('loadingNotification').style.display = 'none';
	setTimeout(function(){
		rebrandPagination();
	
	},100);
});

billerDirectgrid.attachEvent("onRowSelect",function(rowId,cellIndex){
	if (window.navigator.userAgent.indexOf("MSIE")>=1&&inputId!=null)
	     moveFocus(inputId);
});		
jQuery(document).ready(function() {	
	//alert('called once');
	billerDirectgrid.loadXML(retrieveURLListVar);
	//alert('calling done');
});

jQuery("#btn_save").click(function(){
	if(billerDirectgrid!=null){
		var row_id=billerDirectgrid.getAllRowIds();
		//alert('row_id '+row_id);
		var billerDirectArray = row_id.split(",");//partsArray
		var sno=new Array();
		var funcURLId = new Array();
		var funcName = new Array();
		var funcUrl = new Array();
		var funcLang = new Array();
		//alert('starting sno');
		for(var i=0;i<billerDirectArray.length;i++){
			var cellObj_sno=billerDirectgrid.cellById(billerDirectArray[i],1);
			sno[i]=cellObj_sno.getValue();
			//alert(sno[i]);
			if(sno[i] == "" || sno[i] == null)
			{
				sno[i] = "empty";
			}
		}
		//alert('ending sno');
		//alert('starting funcUrlId ');
		for(var i=0;i<billerDirectArray.length;i++){
			//var cellObj_fname=billerDirectgrid.cellById(billerDirectArray[i],2);
			//func_name[i]=cellObj_fname.getValue();
			
			var funcURLIds = 'funcURLId'+billerDirectArray[i];
			//alert('funcURLIds '+funcURLIds);
			funcURLId[i] = document.getElementById(funcURLIds).value;
			//alert('I hope here it will not be reached second time');
			//alert(funcName[i].value);
			if(funcURLId[i] == "" || funcURLId[i] == null)
			{
				funcURLId[i] = "empty";
			}
			//alert('end of Name');
		}
		//alert('ending funcUrlId ');
		//alert('urlid '+sno);
		for(var i=0;i<billerDirectArray.length;i++){
			//var cellObj_fname=billerDirectgrid.cellById(billerDirectArray[i],2);
			//func_name[i]=cellObj_fname.getValue();
			var funcNameId = 'funcName'+billerDirectArray[i];
			funcName[i] = document.getElementById(funcNameId).value;
			
			//alert(funcName[i].value);
			if(funcName[i] == "" || funcName[i] == null)
			{
				funcName[i] = "empty";
			}
			//alert('end of Name');
		}
		//alert('Lets start URL');
		for(var i=0;i<billerDirectArray.length;i++){
			//var cellObj_fname=billerDirectgrid.cellById(billerDirectArray[i],2);
			//func_name[i]=cellObj_fname.getValue();
			//alert('Entered URL');
			var funcUrlId = 'funcURL'+billerDirectArray[i];
			//alert('funcUrlId '+funcUrlId);
			funcUrl[i] = document.getElementById(funcUrlId).value;
			//alert('funcUrlId '+funcUrlId);
			//alert(funcUrl[i].value);
			if(funcUrl[i] == "" || funcUrl[i] == null)
			{
				funcUrl[i] = "empty";
			}
		}
		for(var i=0;i<billerDirectArray.length;i++){
			//var cellObj_fname=billerDirectgrid.cellById(billerDirectArray[i],2);
			//func_name[i]=cellObj_fname.getValue();
			var funcCheckboxId = 'langSupportCheckBox'+billerDirectArray[i];
			//alert('funcCheckboxId');
			funcLang[i] = document.getElementById(funcCheckboxId).checked;
			
			//alert("checkbox value should be true or false "+funcLang[i]);
		}
		jQuery("#sno").attr("value",sno);
		jQuery("#funcURLId").attr("value",funcURLId);
		jQuery("#funcName").attr("value",funcName);
		jQuery("#funcUrl").attr("value",funcUrl);
		jQuery("#languageSupport").attr("value",funcLang);
		var forwardURL="${finalUpdate}";
		jQuery("#billerDirectForm").attr("action",forwardURL);
		jQuery("#billerDirectForm").submit();
	}
});
function addHeaderToGrid() {
	//alert('called');
	var row_id=billerDirectgrid.getAllRowIds();
	//alert('row_id is '+row_id);
	var billerDirectArray = row_id.split(",");//partsArray
	var listsize = billerDirectArray.length;
	var rowid = listsize - 1;
	//alert('listsize is '+listsize);
	//alert('rowid is '+rowid);
	var newRowid;
	if(row_id == null||row_id == ''){
		newRowid = rowid;
	}else{
		newRowid = rowid + 1;
	}
	//alert('newRowid is '+newRowid);
	var serialNumber = newRowid + 1;
	var newRowData = "<table id=\"billerDirectSubTab"+newRowid+"\" width='800px' class=\"displayGrid rounded shadow\">"+
	"<thead><tr><th class=\"w100\">Language</th><th class=\"w80\">Header Name</th><th class=\"w200\">URL</th><th class=\"w15 \">Delete</th></tr>"+
	"</thead><tbody><tr><td><button class=\"floatR\" id=\"addLanguage\" onclick=\"addLanguageSubRow('"+newRowid+"');return false;\">Add Another Language</button>"+
	"</td></tr></tbody></table></table>"
	+","+""+serialNumber+"<input type=\"hidden\" id=\"funcURLId"+newRowid+"\" />"
	+","+"<div id=\"funcNameDiv"+newRowid+"\"><input type=\"text\" class=\"w150\" "+
	"onclick=\"focusOnInput('funcName"+newRowid+"');\" id=\"funcName"+newRowid+"\"/></div>"
	+","+"<div id=\"funcURLDiv"+i+"\"><input type=\"text\" class=\"w500\" onclick=\"focusOnInput('funcURL"+newRowid+"');\" id=\"funcURL"+newRowid+"\"/></div>"
	+","+"<input type=\"checkbox\" id=\"langSupportCheckBox"+newRowid+"\"/>"
	+","+"<a href=\"javascript:deleteNewGridRow(\'"+newRowid+"');\"><img style=\"float:none;\" class=\"ui_icon_sprite trash-icon\" src=\"<html:imagesPath/>transparent.png\"  width=\"15\" height=\"15\"/>" ;
	
	//var newRowData = "a,b,c,d,e,f";


	var rowIndex=billerDirectgrid.getRowIndex(rowid);
	//alert('rowIndex is '+rowIndex);
	billerDirectgrid.addRow(newRowid,newRowData,rowIndex+1);
	billerDirectgrid.selectRow(rowIndex,false,false,true);
	billerDirectgrid.cells(newRowid, 0).close();
	//alert('should be done');
	return false;
};
function addLanguageSubRow(mainTabRowNum){
	//alert('entered sub tab');
	var subTable = document.getElementById("billerDirectSubTab"+mainTabRowNum);
	//alert('111111111111');
    var subTableRowNum = subTable.rows.length;
    //alert('subTableRowNum is '+subTableRowNum);
    var newSubRow = subTable.insertRow(subTableRowNum-1);
    //alert('2222222222222222222');
    newSubRow.vAlign = "top";
    var cellLanguage = newSubRow.insertCell(0);
    var cellHeaderName = newSubRow.insertCell(1);
    var cellURL = newSubRow.insertCell(2);
    var cellDeleteSubRow = newSubRow.insertCell(3);
    //alert('3333333333333333333');
    cellLanguage.innerHTML = '<select class="w100" name="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].language" id="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].language"> '+supportedLocaleList+'</select>';
    //alert('44444444444444444444');
    cellHeaderName.innerHTML = '<input type="text" id="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].funcName" name="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].funcName" class="w200"/><input type="hidden" id="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].funcId" name="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].funcId"/>';
    //alert('55555555555555555');
    cellURL.innerHTML = '<input type="text" id="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].funcUrl" name="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum -1) +'].funcUrl" class="w400"/>';
    //alert('6666666666666666666');
    cellDeleteSubRow.innerHTML = '<img id="billerDirectURLList['+mainTabRowNum+'].billerDirectList[' + (subTableRowNum - 1) + ']Img" class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" onClick="deleteSubTabNewRow(this,'+mainTabRowNum+','+(subTableRowNum - 1)+')" style="cursor:pointer;">';
    return false;
 };

 function deleteGridRow(ind){
	 //alert('id is '+ind);
	 var funcURLIds = 'funcURLId'+ind;
	 //alert('funcURLIds value '+funcURLIds);
	 //alert('deleteURLId.length '+deleteURLId.length);
	 deleteURLId[deleteURLId.length] = document.getElementById(funcURLIds).value;
	 //alert('deleteURLId value is '+deleteURLId);
	 jQuery("#deletedURL").attr("value",deleteURLId); 
	 jConfirm("successfully removed from this grid. save the page to delete completely", "", function(confirmResult) {
			if(!confirmResult){
				return;
			}else{
				billerDirectgrid.deleteRow(ind);
			}
		});

	
	}

 function deleteNewGridRow(ind){
	 jConfirm("successfully removed from this grid. save the page to delete completely", "", function(confirmResult) {
			if(!confirmResult){
				return;
			}else{
				billerDirectgrid.deleteRow(ind);
			}
		});
	}

function deleteSubTabRow(object, subTabId, subTabRowId){
	var subTabName = 'billerDirectSubTab'+subTabId;
	//alert('subTabName is '+subTabName);
	//alert('subTabRowId is '+subTabRowId);
	var subTabURLRowId = document.getElementById('billerDirectURLList['+subTabId+'].billerDirectList[' + subTabRowId +'].funcId').value;
	//alert('subTabURLRowId to be deleted '+subTabURLRowId);
	var table = document.getElementById(subTabName);
    var tableRowNum = table.rows.length;
	//alert('tatal number of row of the table is '+tableRowNum);

    // change id and name of parameters in the clicked row
    for (var i = subTabRowId; i < tableRowNum - 3; i ++) {
    	var j = i+1;
		//alert('billerDirectList[' + j +'].');
    	var parameterFieldsId = new Array('language','funcName','funcId','funcUrl');
    	//alert('calling for loop for times '+parameterFieldsId.length);
        for (var fieldIndex = 0; fieldIndex < parameterFieldsId.length; fieldIndex ++) {
            //alert('billerDirectURLList['+subTabId+'].billerDirectList[' + i +'].');
            var firstValue = document.getElementById('billerDirectURLList['+subTabId+'].billerDirectList[' + i +'].' + parameterFieldsId[fieldIndex]); 
			var replaceByValue =  document.getElementById('billerDirectURLList['+subTabId+'].billerDirectList[' + j +'].' + parameterFieldsId[fieldIndex]);
			firstValue.value = replaceByValue.value;
		}
    }
    // delete the clicked row
    var deleteRowId = parseInt(subTabRowId) + 1;
    //alert('deleteRowId '+deleteRowId);
    table.deleteRow(tableRowNum-2); 
    deleteSubRowURLId[deleteSubRowURLId.length] = subTabURLRowId;
	 //alert('deleteURLId value is '+deleteURLId);
	 jQuery("#deleteSubRowURLId").attr("value",deleteSubRowURLId); 
};
function deleteSubTabNewRow(object, subTabId, subTabRowId){
	var subTabName = 'billerDirectSubTab'+subTabId;
	//alert('subTabName is '+subTabName);
	//alert('subTabRowId is '+subTabRowId);
	var table = document.getElementById(subTabName);
    var tableRowNum = table.rows.length;
	//alert('tatal number of row of the table is '+tableRowNum);

    // change id and name of parameters in the clicked row
    for (var i = subTabRowId; i < tableRowNum - 3; i ++) {
    	var j = i+1;
		//alert('billerDirectList[' + j +'].');
    	var parameterFieldsId = new Array('language','funcName','funcId','funcUrl');
    	//alert('calling for loop for times '+parameterFieldsId.length);
        for (var fieldIndex = 0; fieldIndex < parameterFieldsId.length; fieldIndex ++) {
            //alert('billerDirectURLList['+subTabId+'].billerDirectList[' + i +'].');
            var firstValue = document.getElementById('billerDirectURLList['+subTabId+'].billerDirectList[' + i +'].' + parameterFieldsId[fieldIndex]); 
			var replaceByValue =  document.getElementById('billerDirectURLList['+subTabId+'].billerDirectList[' + j +'].' + parameterFieldsId[fieldIndex]);
			firstValue.value = replaceByValue.value;
		}
    }
    // delete the clicked row
    var deleteRowId = parseInt(subTabRowId) + 1;
    //alert('deleteRowId '+deleteRowId);
    table.deleteRow(tableRowNum-2); 
};

 function showPortletPage(){
	 window.location.href="<portlet:renderURL><portlet:param name='action' value=''/></portlet:renderURL>";
	 };

	 function expandAll() {
		 billerDirectgrid.forEachRow(function(id){
			 var cell=billerDirectgrid.cellById(id,0);
		   	cell.open();
		   	}); 
	 };
	 function minimizeAll() {
		 billerDirectgrid.forEachRow(function(id){
		   		var cell=billerDirectgrid.cellById(id,0);
		   		cell.close();
		   	});
		};
		
		function focusOnInput(id){
			if (window.navigator.userAgent.indexOf("MSIE")>=1){
			//document.getElementById(id).focus();
			var inputBox = document.getElementById(id);
			inputId = id;
			//alert('setting timeout');
			setTimeout(function() { inputBox.focus(); }, 0);
			var  range =document.selection.createRange(); 
	        range.setEndPoint("StartToStart",inputBox.createTextRange());
	        satrPosition = range.text.length;
		    }
		};
		function  moveFocus(id){
			inputBox2 = document.getElementById(id);
			inputBox2.focus();
			endPosition = (inputBox2.value.length-satrPosition);
	        var s = inputBox2.createTextRange(); 
	        s.moveStart("character",satrPosition);
			s.moveEnd("character",-endPosition);
			s.collapse(true);
			s.select();
		};
</script>