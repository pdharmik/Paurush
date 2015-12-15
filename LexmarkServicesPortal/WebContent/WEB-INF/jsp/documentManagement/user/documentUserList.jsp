<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<style type="text/css">
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
.ie7  #fileContent {
margin-left: 400% !important
}
.ie8 #docBrowseBtn {
position:relative
}
.ie8 #fileContent {
position:relative
}
div.gridbox_light .xhdr { 
	background-image:none !important;
	background:#e6e6f0 !important;
}
input.fileContent {
    margin-left: -80px;
    opacity: 0;
    filter: alpha(opacity=0);
    width: 75px !important;
}
</style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<portlet:resourceURL id="documentUserListXML" var="listURL"></portlet:resourceURL>
<portlet:actionURL var="uploadURL">
<portlet:param name="action" value="handleDocumentUpload"/>
</portlet:actionURL>
<%-- <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" /> --%>
<table class="width-100per" border="0" class="sectionWrapper" cellspacing="5px">
	<tr id="hintDiv2">
		<td>
			<div><img src="<html:imagesPath/>documents_banner.jpg" title="document_banner"/></div>
		</td>
	</tr>
	<tr>
<!-- 	Changes for 14.9 CR 14723 -->
		<td class="width-100per" >
			<table class="displayGrid">
              <thead>
                <tr><th class="table-th-style1"><spring:message code="documentViewPage.rhs.title" /></th>
                </tr></thead>
              
            </table>
		</td>
	</tr>
	<tr id="hintDiv">
	    <td>
        	<div><spring:message code="documentViewPage.rhs.hint" /></div>
        </td>
    </tr>
	<c:if test="${isPublishing}">
            <c:if test="${uploadSuccess}">
                <tr><td class="serviceSuccess">
                    <spring:message code='message.documentManagement.uploadDocument'/>
                </td></tr>
            </c:if>
            <c:if test="${uploadFail}">
                <tr><td class="serviceError">
                    <spring:message code='exception.documentManagement.uploadDocument'/>
                </td></tr>
            </c:if>
            <c:if test="${uploadFileNotSupported}">
                <tr><td class="serviceError">
                    <spring:message code='exception.documentManagement.fileFormatNotSupported'/>
                </td></tr>
            </c:if>
		<tr id="fileUploaderTr">
			<td class="table-td-style22">
			<div id="divUpload" style="display:none;">
				<form id="divUploadForm" name="divUploadForm" action="${uploadURL}" enctype="multipart/form-data" method="post">
					<input type="hidden" name="definitionId" id="definitionId"/>
					<table width="auto" class="float-left">
						<tr valign="middle">
							<td class="tdUpload float-left" nowrap="nowrap" >
								<spring:message code="documentUserPage.rhs.label.selectFile" />
								<input type="text" id="txtFilePath" name="txtFilePath" size ="50" readonly="readonly"/>
								<a href="#" class="button anchor-button1" id="docBrowseBtn"><spring:message code="button.browse"/></a>
								<input type="file" id="fileContent" onchange="copyFileName(getFileName(this))" name="fileContent" class="fileContent" hidefocus/>
								<span class="width-30px">&nbsp;&nbsp;</span>
								<a href="javascript:doSubmit();" id="docSubBtn" class="button button-style4"><spring:message code="button.submit" /></a>
							</td>
						</tr>
					</table>	
				</form>
				</div>
			</td>
		</tr>
	</c:if>
	<tr>
		<td>
		    <div id="divDocumentList" style="display:none;">
				<html:statusBanner id="listStatus"/>
				<div class="div-style45">
				<span style="font-size: 16px; padding: 6px 0px;" id="categoryLabel"></span>
					<!-- <span id="categoryLabel" class="span-style3"></span>--> -
					<!-- <span id="definitionLabel" class="span-style4"></span> -->
					 <span id="definitionLabel" style="font-size: 14px; padding: 6px 0px;"></span>
					
				</div>
				<div id="divDocList" class="divDocList"></div>
				<div id="loadingNotification" class='gridLoading' style="display:none;">
		    		<img src="<html:imagesPath/>gridloading.gif"/><br>
		   		</div>
				<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
			</div>
		</td>
	</tr>
</table>
<portlet:resourceURL id="deleteDocument" var="deleteURL"></portlet:resourceURL>
<portlet:resourceURL id="downloadDocument" var="downloadURL"></portlet:resourceURL>
<script type="text/javascript">
	srgrid = new dhtmlXGridObject('divDocList');
    srgrid.setImagePath("<html:imagesPath/>gridImgs/");
    srgrid.setHeader("<spring:message code='documentUserPage.rhs.listHeader'/>");
    srgrid.attachHeader("#text_filter,#text_filter,#text_filter,,");
    srgrid.setColAlign("left,left,left,center,left");
    srgrid.setColTypes("ro,ro,ro,ro,ro");
    srgrid.setColSorting("domSort,dateSort,int,str,na");
    srgrid.setInitWidthsP("30,20,20,15,15");
    srgrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
    srgrid.setPagingSkin("bricks");
    
    srgrid.enableAutoWidth(true);
    srgrid.enableAutoHeight(true);
    srgrid.enableMultiline(true);
    srgrid.init();
    srgrid.setSizes();
    
    srgrid.attachEvent("onXLE", function() {
        document.getElementById('loadingNotification').style.display = 'none';
    });
    srgrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
    srgrid.setSkin("light");
    srgrid.parse([],"jsarray");
	function deleteDocument(id){
		
		clearUploadMessage();
		clearMessage();
		callOmnitureAction('Document User', 'Document User List Delete Document');
		if(!confirm("<spring:message code='documentUserPage.text.confirmDeleteDocument'/>")){
			return;
		}
		var url = "${deleteURL}"
		url = url+"&id="+id;
		showOverlay();
		doAjax(url,function(){
				showMessage("<spring:message code='message.documentManagement.deleteDocument'/>","listStatus");
				hideOverlay();
				if(document.getElementById("definitionId") != null) {
					var definitionId = document.getElementById("definitionId").value;
					srgrid.clearAndLoad("${listURL}"+"&definitionId="+definitionId);
				}
				return true;
			},
			function(){
				showError("<spring:message code='exception.documentManagement.deleteDocument'/>","listStatus");
				return true;
			}
		);
	};
	
	function getFileName(obj) 
	{ 
	    if(obj) 
	    { 
		        var path = obj.value;   
		        var filename = path.replace(/^.*(\\|\/|\:)/, ''); 
	           return filename ; 
	    } 
	};
	
	function move(thisObject, event){
	     if(jQuery.browser.ie){
	        //event=event||window.event;
	        var a=document.getElementById('fileContent');
	        a.style.left=thisObject.offsetLeft;
	        a.style.top=thisObject.offsetTop;
	    } else {
	        event=event||window.event;
	         var a=document.getElementById('fileContent');
	         a.style.left=event.pageX-50+'px';
	         a.style.top=event.pageY-10+'px';
	    }
	};
	
	var today = "<spring:message code='today'/>";
	function dateSort(a,b,order){
		callOmnitureAction('Document User', 'Document User List Sort');
		if(a==today)return (order=="asc"?1:-1);
		if(b==today)return -(order=="asc"?1:-1);
		return (a>b?1:-1)*(order=="asc"?1:-1);
	};
	
	var url = "${listURL}";
	function listDocument(id, keepMessage){
		clearMessage();
		document.getElementById("hintDiv").style.display="none";
		document.getElementById("hintDiv2").style.display="none";
		<c:if test="${isPublishing}">
		    if(id != '-1') {
				document.getElementById("divUpload").style.display="block";
			} else {
				document.getElementById("divUpload").style.display="none";
			}
			document.getElementById("definitionId").value=id;
		</c:if>
		document.getElementById("divDocumentList").style.display="block"; 
		if(keepMessage == null || keepMessage == false) {
			clearUploadMessage();
	    }
		srgrid.clearAndLoad(url+"&definitionId="+id);
		// For defect 969 just for supspecting the jQuery("#categoryLabel").text function not working in IE.
		document.getElementById("categoryLabel").innerHTML = defs[id].category;
		document.getElementById("definitionLabel").innerHTML = defs[id].definition;
//		jQuery("#categoryLabel").text(defs[id].category);
//		jQuery("#definitionLabel").text(defs[id].definition);
	}

	function downloadDocument(id) {
		var categoryName = document.getElementById("categoryLabel").innerHTML;
		//callOmnitureAction('Document User', 'Download Document ' + categoryName + '-' + id);
		var dnldUrl = "${downloadURL}";
		dnldUrl = dnldUrl+"&id="+id;
		window.open(dnldUrl);
		}
	
	function clearUploadMessage() {
		jQuery('td.serviceSuccess').hide();
		jQuery('td.serviceError').hide();
	}

    function doSubmit(){
        if (doValidate()){
    		jQuery("#divUploadForm").submit();
        }
	}

    function doValidate(){
    	var filePath = '';
    	isValid = true;
		if (jQuery.trim(document.getElementById("fileContent").value) == "") {
			showError("<spring:message code='customerReports.manualUpload.fileRequired'/>",null, false);
			isValid = false;
		}
		return isValid;
	}
	
    function copyFileName(fileName){
        document.getElementById('txtFilePath').value=fileName;
	}
	
</script>
<c:if test="${not empty definitionId}">
<script type="text/javascript">
	listDocument('${definitionId}',  true);
</script>
</c:if>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Document User List";
     addPortlet(portletName);
     pageTitle="Document user list";
</script>