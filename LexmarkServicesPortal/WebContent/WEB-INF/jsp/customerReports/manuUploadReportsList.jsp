<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<style type="text/css"><jsp:include page="/WEB-INF/css/mps.css"></jsp:include></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
	div.buttonsPanel {
		padding: 25px 0;
		overflow: hidden;	
		}
	#uploadFileInput .manualReport {

		}
		.displayGrid{width:100%}

	/*div.right-column{overflow:hidden;margin-left:0;padding-left: 5px;}
	#reports-banner,div.portlet-wrap{width:auto;}*/

</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
<div class="journal-content-article">
      <h1>Reports</h1>
      
</div>


<div id="emailReport" style="display:none"></div>
	<div class="content">
		<%@ include file="/WEB-INF/jsp/customerReports/leftPanel.jsp"%>
		<div class="right-column"><!--  col-xs-12 col-sm-9 col-md-9"-->
			<div class="portlet-wrap">
				<div class="rounded shadow">
					<!-- <h3>${definitionDisplayForm.definitionName}</h3> -->	<!-- commented for employee report -->
					<!-- added for employee report -->
					<c:if test="${not isEmployee}">
					<table class="displayGrid width-100per">
              <thead>
                <tr><th>${definitionDisplayForm.definitionName}</th>
                </tr></thead>
              
            </table>
					
					</c:if>
					<c:if test="${isEmployee}">
						<table class="displayGrid width-100per">
              <thead>
                <tr><th>${definitionDisplayForm.definitionName} - ${accountName}</th>
                </tr></thead>
              
            </table>
				
					</c:if>
					<!-- end of addition for employee report -->
				</div>
				<div class="portlet-wrap-inner">
					<table >
						  <tr><td width="800px">
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${definitionDisplayForm.definitionDescription}</span>
					  	  </td></tr>
				    </table>
				    <c:if test="${isPublishing}">
				    
					<div class="buttonsPanel">
						<portlet:actionURL var="uploadFileAction">
							<portlet:param name="action" value="uploadFile"></portlet:param>
						</portlet:actionURL>
						<form:form id="uploadForm" method="post" action="${uploadFileAction}" enctype="multipart/form-data">
							<table>
								<tr valign="top">
									<td class="padding-bottom-10px">
										<LABEL class="labelBold" for="fileInput"><spring:message code="customerReports.manualUpload.label.fileUpload"/>&nbsp;</LABEL>
										<input type="text" id="txtFilePath" name="txtFilePath" size ="50" readonly="readonly"/>
									</td>
									<!-- 	Padding changed for CI Defect # 10808 -->
									<td width="2" class="padding-bottom-10px"></td>
									<td valign="top" class="padding-bottom-10px">
										<a href="#" onmousemove="move(this, event)" class="button anchor-button1" ><spring:message code="button.browse"/></a>
										<input type="file" id="uploadFileInput" class="manualReport uploadFileInput" onchange="copyFileName(getFileName(this))" name="uploadFileInput" hidefocus/>
									</td>
								</tr>
								
								<tr>
									<td>
										<LABEL class="labelBold"><spring:message code="customerReports.manualUpload.label.fileContentDate"/>:&nbsp;</LABEL>
										<a id="prevLocalizedFileContentDate" onClick="clickDateAction('localizedFileContentDate', 'PREV')" class="validDateAction" title="Previous Day"><<</a>
										<input type="hidden" id="fileContentDate" name="fileContentDate" readonly="readonly"/>
										<input type="text" name="localizedFileContentDate" id="localizedFileContentDate" size="7" class="inputSm" onMouseUp="callOmnitureAction('Manual upload report list', 'Manual upload report list show file content date calendar');show_cal('localizedFileContentDate', null, todayStr);" onFocus="reloadDateAction('localizedFileContentDate', todayStr, 'NEXT')" onBlur="this.className='';" readonly=""/>
										<img id="imgLocalizedFileContentDate" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="callOmnitureAction('Manual upload report list', 'Manual upload report list show file content date calendar');show_cal('localizedFileContentDate', null, todayStr)"/>
										<a id="nextLocalizedFileContentDate" onClick="clickDateAction('localizedFileContentDate', 'NEXT')" class="validDateAction" title="Next Day">>></a>
									</td>
									
									<td width="2"></td>
									<td>
										<a href="javascript:doSubmit();" class="button anchor-button1" type="submit" id="btn_submit"><spring:message code="button.uploadFile"/></a>							</td>
								</tr>
							</table>
							
							<input type="hidden" id="hiddenTimezone" name="timezone"/>
							<input type="hidden" id="definitionIdHidden" name="definitionIdHidden" value="${docDefinitionId}"/>
							<input type="hidden" id="roleCategoryIdHidden" name="roleCategoryIdHidden" value="${roleCategoryId}"/>
							<input type="hidden" id="fileNameHidden" name="fileNameHidden"/>
						<style type="text/css">
							input[type="file"] {
								cursor:pointer;
							}
						</style>
						
						</form:form>
					</div>
					</c:if>
					<div id="reportListContainer" class="width-100per"></div>
					<div id="loadingNotification" class='gridLoading'>
						<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
					</div>
					<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
				</div>
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div>
				</div>
			</div>
		</div>
	</div>
</div>	

<script type="text/javascript">
<c:if test="${isPublishing}">
	jQuery("#fileContentDate").val = todayStr; //Changed for CI Defect #10808
	document.getElementById('localizedFileContentDate').value = localizeDate(todayStr);
	reloadDateAction('localizedFileContentDate', todayStr, 'NEXT');
	document.getElementById("hiddenTimezone").value = timezone;
</c:if>
	
	var headerTxt = "<spring:message code='customerReports.listHeader.reportFields'/>";
	reportListGrid = new dhtmlXGridObject('reportListContainer');
	reportListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	reportListGrid.setHeader(headerTxt);
	reportListGrid.setInitWidths("*,100,*");
	reportListGrid.setColAlign("left,left,left");
	reportListGrid.setColTypes("ro,ro,ro");
	reportListGrid.setColSorting("str,date,na");
    reportListGrid.enableAutoWidth(true);
    reportListGrid.enableAutoHeight(true);
    reportListGrid.enableMultiline(true);
    reportListGrid.enableLightMouseNavigation(true);
    reportListGrid.setSkin("light");
    reportListGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
    var isFistLoadFlag = true;
    reportListGrid.attachEvent("onXLE", function() {
        if(isFistLoadFlag){
            reportListGrid.setSortImgState(true, 1, 'asc');
            isFistLoadFlag = false;
        }
        document.getElementById('loadingNotification').style.display = 'none';
        
      /*//Function for Responsive table
  	  var hdTxt = headerTxt.split(',');
  	  
  		$("table.obj tr td:first-child").attr("data-title",hdTxt[0])
  		.next().attr("data-title",hdTxt[1])
  		.next().attr("data-title",hdTxt[2])
  		.next().attr("data-title",hdTxt[3]);
  		
  		//Remove data-title attribute for inner td's of table
  		$("table.obj td td").removeAttr("data-title");*/
    });
    reportListGrid.init();
    reportListGrid.setPagingSkin("bricks");
    reportListGrid.loadXML("${definitionDisplayForm.definitionURL}&definitionId=${definitionDisplayForm.definitionId}&timezone=" + timezone);

    reportListGrid.attachEvent("onMouseOver", function(id,index){
        return false;
    }); 

	var uploadResult = '${uploadResult}';
	if(uploadResult == 'success'){
		clearMessage();
		showMessage("<spring:message code='customerReports.manualUpload.uploadSuccess'/>", null, true);
	} else if(uploadResult == 'fail'){
		clearMessage();
		showError("<spring:message code='exception.documentManagement.uploadDocument'/>",null, false);
	} else if(uploadResult == 'uploadFileNotSupported'){
		clearMessage();
		showError("<spring:message code='exception.documentManagement.fileFormatNotSupported'/>",null, false);
	}
	
		
    function doSubmit(){
        if (doValidate()){
        	jQuery("#uploadForm").submit();
        }
	}
    
    function doValidate(){
    	document.getElementById("fileContentDate").value = formatDateToDefault(document.getElementById("localizedFileContentDate").value);
    	var filePath = '';
    	isValid = true;
		if (jQuery.trim(document.getElementById("uploadFileInput").value) == "") {
			showError("<spring:message code='customerReports.manualUpload.fileRequired'/>",null, false);
			isValid = false;
		}
		else{
			filePath = jQuery.trim(document.getElementById("uploadFileInput").value);
			var dotPosition = filePath.lastIndexOf(".");
			if(dotPosition == -1){
				showError("<spring:message code='customerReports.manualUpload.mustBePDForXLS'/>",null, false);
				isValid = false;
			}
			else{
				var ext = filePath.substring(dotPosition + 1).toLowerCase();
		/*	right now , we do not need validate the document type
			if(!isFileSupported(ext)){
					showError("<spring:message code='customerReports.manualUpload.mustBePDForXLS'/>",null, false);
					isValid = false;
				}  */
			}
		}
		if(isValid){
			document.getElementById('btn_submit').disabled =true;
			clearMessage();
			document.getElementById("fileNameHidden").value = filePath;
		}
		return isValid;
	}

   	var allSupportFileExtension = new Array(${allSupportFileExtension});
    function isFileSupported(extension) {
    	for(var i=0; i< allSupportFileExtension.length; i++) {
	    	if(allSupportFileExtension[i] == extension) {
		    	return true;
	    	}
    	}
    	return false;
    }

    function openSendEmailPage(reportDefinitionName ,reportId){
    	callOmnitureAction('Customer Reports', 'Report User Send Email' + reportDefinitionName);
       var  url ="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'>" + 
        "<portlet:param name='action' value='showEmailPage' />" +
        "</portlet:renderURL>&reportDefinitionName=" + encodeURIComponent(reportDefinitionName)
    	    +"&reportId="+reportId;

    showOverlay();	
    jQuery('#emailReport').load(url,function(){
    	dialog=jQuery('#emailReport').dialog({
    	autoOpen: false,
    	title: "<spring:message code='customerReport.title.reportEmailNotification'/>",
    	modal: true,
    	draggable: false,
    	resizable: false,
    	position: 'center',
    	height: 'auto',
    	width: 600,
    	open: function(){	
    		jQuery('#emailReport').show();
    		hideOverlay();
    	},
    	close: function(event,ui){
    		hideOverlay();
    		dialog.dialog('destroy');					 
    		dialog=null;
    		showSelect();
    	}
    	});
    dialog.dialog('open');
    });
                           
    }
	
    function hideSelect(){
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "hidden";
		}
	}  

	function showSelect(){
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "visible";
		}
	}
    function getFullPath(obj)
    {
        if(obj)
        {
            //ie
            if (window.navigator.userAgent.indexOf("MSIE")>=1)
            {
               obj.select();
               var path = document.selection.createRange().text;
			   return path ;
            }
            else
            {
                if(obj.files)
                {
					return obj.files.item(0).fileName;
                }
                return obj.value;
            }
        }
    }
	function getFileName(obj) 
	{ 
	    if(obj) 
	    { 
		        var path = obj.value;   
		        var filename = path.replace(/^.*(\\|\/|\:)/, ''); 
	           return filename ; 
	    } 
	};
	
    function copyFileName(fileName){
        document.getElementById('txtFilePath').value=fileName;
	}
    
    function move(thisObject, event){
    	 if(jQuery.browser.ie){ //Changes done for CI Defect #10808
			//event=event||window.event;
			var a=$('#uploadFileInput');
			a.css('left',thisObject.offsetLeft);
			a.css('top',thisObject.offsetTop);
		} else {
			event=event||window.event;
			 var a=document.getElementById('uploadFileInput');
			 a.style.left=event.pageX-50+'px';
			 a.style.top=event.pageY-10+'px';
		}
	};
</script>


<script type="text/javascript">
//---- Ominture script 
     portletName = "Manual upload report list";
     addPortlet(portletName);
     pageTitle="Manual upload report list";
</script>