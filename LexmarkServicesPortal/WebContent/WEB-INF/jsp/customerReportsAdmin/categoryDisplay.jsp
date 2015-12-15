<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=1.0" />
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.ie7 .portlet-topper{width:99% !important;}
.ie9 #categoryListContainer{width:100% !important;}
.buttonContainer a{color:#FFF !important}
.Role_Restrictions_button{color:#FFF !important}
.button_cancel{color:#606060 !important}
div.gridbox_light .xhdr { 
	background-image:none !important;
	background:#e6e6f0 !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
	    <div id="rptAdminCategoryDisplayTitle"  class="vertical-align-top">
	        <span class="orangeSectionTitles"><spring:message code="customerReportsAdmin.label.reportAdminCategoryDisplay"/></span><br/>
	        <spring:message code="customerReportsAdmin.label.reportAdminCategoryDisplayDescription" /><br/><br/>
	    </div>
	    <div class="height-5px"></div>
	    <div >
	        <div id="categoryListContainer"></div>
	        <div id="loadingNotification" class='gridLoading'>
		    	<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
		   </div>
	        <div>
	            <span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
	        </div>
	    </div>
	    <div>
	    <div class="buttonContainer">
	        <a href="javascript:addCategory()" class="button"><spring:message code="customerReportsAdmin.label.addCategory"/></a>
	        <br/><br/>
	    </div>    
	        <c:if test="${addOrEdit}">
		        <portlet:actionURL var="saveCategoryURL">
		            <portlet:param name="action" value="saveCategory"></portlet:param>
		        </portlet:actionURL>
		        <div id="divCategoryDetail">
		            <form:form id="formCategoryDetail" commandName="categoryDisplayForm" method="post" action="${saveCategoryURL}" onSubmit="return doValidate();">
			            <span class="labelBold">*<spring:message code="customerReportsAdmin.label.categoryNameIdentifier"/>:</span><br/>
			            <form:hidden path="category.categoryId"/>
			            <form:hidden path="category.orderNumber"/>
			            <div class="inputRegWrapper">
			            <form:input path="category.name" class="inputReg" maxlength="100"/>
			            </div>
			            <br/><br/>
			            <span class="labelBold"><spring:message code="customerReportsAdmin.label.localizedSettings"/>:</span>
			            <br/><br/>
			            <table>
			               <tr>
			                   <td width="200">
			                       <span class="labelBold"><spring:message code="customerReportsAdmin.label.supportedLanguage"/></span><br/>
			                   </td>
			                   <td width="300">
			                       <span class="labelBold"><spring:message code="customerReportsAdmin.label.categoryName"/></span><br/>
			                   </td>
		                       <td>
		                           <span class="labelBold"><spring:message code="customerReportsAdmin.label.action"/></span><br/>
		                       </td>
			               </tr>
			            <c:forEach items="${categoryDisplayForm.category.localeList}" var="categoryLocale" varStatus="counter" begin="0">
			               <tr>
			                   <td>
			                       <form:hidden path="category.localeList[${counter.index}].categoryLocaleId"/>
		                           <form:hidden path="category.localeList[${counter.index}].categoryId"/>
			                       <form:hidden path="category.localeList[${counter.index}].supportedLocale.supportedLocaleId"/>
			                       ${categoryLocale.supportedLocale.supportedLocaleName}
			                   </td>
			                   <td>
			                       <div class="inputRegWrapper">
			                       <form:input path="category.localeList[${counter.index}].name" class="inputReg" maxlength="100" onChange="changeProperty(this,'img_delete${counter.index}');" />
			                       </div>
			                   </td>
			                   <td align="center">
			                    	<c:choose>
		                				<c:when test="${categoryLocale.name!=null}">
		                					<img id="img_delete${counter.index}" class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="clearLocalizationInput(${counter.index})"  title="Clear"  />
		                				</c:when>
		                			<c:otherwise>
		                					<img id="img_delete${counter.index}" class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="clearLocalizationInput(${counter.index});"  style="visibility:hidden"/>
		                			</c:otherwise>
		                			</c:choose>
			                   </td>
			               </tr>
			            </c:forEach>
		                </table>
		                <br/>
		                <br/>
		                <label class="labelBold"><spring:message code="customerReportsAdmin.label.roleRestrictions"/>:</label><br><br>
		                <label class="labelBold"><spring:message code="customerReportsAdmin.label.customer"/></label>
		                <div class="columns two">
		                	<div class="first-column">
		                		<label class="labelBold"><spring:message code="customerReportsAdmin.label.customerRoles"/></label><br>
		                	<c:forEach items="${categoryDisplayForm.customerExternalRoleList}" var="role" varStatus="counter" begin="0">
			                    <form:input type="hidden" path="customerExternalRoleList[${counter.index}].id"/>
			                    <form:input type="hidden" path="customerExternalRoleList[${counter.index}].name"/>
			                    <input type="checkbox" id="customerExternalRoleList_${role.id}" name="customerExternalRoleList_${role.id}">&nbsp;${role.name}<br>
		                	</c:forEach>
		                	</div>
		                	<div class="second-column">
		                		<label class="labelBold"><spring:message code="customerReportsAdmin.label.lexmarkRoles"/></label><br>
		                	<c:forEach items="${categoryDisplayForm.customerInternalRoleList}" var="role" varStatus="counter" begin="0">
			                    <form:input type="hidden" path="customerInternalRoleList[${counter.index}].id"/>
			                    <form:input type="hidden" path="customerInternalRoleList[${counter.index}].name"/>
			                    <input type="checkbox" id="customerInternalRoleList_${role.id}" name="customerInternalRoleList_${role.id}">&nbsp;${role.name}<br>
		                	</c:forEach>
		                	</div>
		                </div>
		                <div class="clear"></div>
		  				<label class="labelBold"><spring:message code="customerReportsAdmin.label.partner"/></label>
		                <div class="columns two">
		                	<div class="first-column">
		                		<label class="labelBold"><spring:message code="customerReportsAdmin.label.partnerRoles"/></label><br>
		                	<c:forEach items="${categoryDisplayForm.partnerExternalRoleList}" var="role" varStatus="counter" begin="0">
			                    <form:input type="hidden" path="partnerExternalRoleList[${counter.index}].id"/>
			                    <form:input type="hidden" path="partnerExternalRoleList[${counter.index}].name"/>
			                    <input type="checkbox" id="partnerExternalRoleList_${role.id}" name="partnerExternalRoleList_${role.id}">&nbsp;${role.name}<br>
		                	</c:forEach>
		                	</div>
		                	<div class="second-column">
		                		<label class="labelBold"><spring:message code="customerReportsAdmin.label.lexmarkRoles"/></label><br>
		                	<c:forEach items="${categoryDisplayForm.partnerInternalRoleList}" var="role" varStatus="counter" begin="0">
			                    <form:input type="hidden" path="partnerInternalRoleList[${counter.index}].id"/>
			                    <form:input type="hidden" path="partnerInternalRoleList[${counter.index}].name"/>
			                    <input type="checkbox" id="partnerInternalRoleList_${role.id}" name="partnerInternalRoleList_${role.id}">&nbsp;${role.name}<br>
		                	</c:forEach>
		                	</div>
		                </div>      
			            <div style = "width:720px; text-align: right">
			                <a href="javascript:onCancelClick()" class="button_cancel"><span><spring:message code="button.cancel"/></span></a>&nbsp;
			                <a href="javascript:document.getElementById('formCategoryDetail').submit();" class="button Role_Restrictions_button"><span><spring:message code="button.submit"/></span></a>
			            </div>
		            </form:form>
		        </div>
		    </c:if>
	    </div>
	</div>
</div>

<script type="text/javascript">
    categoryListGrid = new dhtmlXGridObject('categoryListContainer');
    categoryListGrid.setImagePath("<html:imagesPath/>gridImgs/");
    categoryListGrid.setHeader("<spring:message code='customerReportsAdmin.listHeader.categoryFields'/>");
    categoryListGrid.setInitWidths("250,100,120,250");
    categoryListGrid.setColAlign("left,left,left,left");
    categoryListGrid.setColTypes("ro,ro,ro,ro");
    categoryListGrid.setColSorting("na,int,na,na");
    categoryListGrid.enableAutoWidth(true);
    categoryListGrid.enableAutoHeight(true);
    categoryListGrid.enableMultiline(true);
    categoryListGrid.enableLightMouseNavigation(true);
    categoryListGrid.setSkin("light");
    categoryListGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
    categoryListGrid.attachEvent("onXLE", function() {
        categoryListGrid.setSortImgState(true, 1, 'asc');
        document.getElementById('loadingNotification').style.display = 'none';
    });
    categoryListGrid.init();
    
    categoryListGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
    categoryListGrid.setPagingSkin("bricks");

    categoryListGrid.loadXML("${categoryDisplayForm.categoryListURL}?orderBy=1&direction=ASCENDING");

    categoryListGrid.attachEvent("onMouseOver", function(id,index){
        return false;
    });

    initializeRoleRestriction();

    function initializeRoleRestriction() {
    	<c:forEach items="${categoryDisplayForm.category.roles}" var="role">
    		var tempCheckbox;
    		if (document.getElementById("customerExternalRoleList_${role.id}")) {
        		tempCheckbox = document.getElementById("customerExternalRoleList_${role.id}");
    		} else if (document.getElementById("customerInternalRoleList_${role.id}")) {
        		tempCheckbox = document.getElementById("customerInternalRoleList_${role.id}");
    		} else if (document.getElementById("partnerExternalRoleList_${role.id}")) {
        		tempCheckbox = document.getElementById("partnerExternalRoleList_${role.id}");
    		} else if (document.getElementById("partnerInternalRoleList_${role.id}")) {
        		tempCheckbox = document.getElementById("partnerInternalRoleList_${role.id}");
    		}
    		if (tempCheckbox) {
    			tempCheckbox.checked = true;
    		}
        </c:forEach>
    }

    function editCategory(categoryId) {
        var categoryForm = document.createElement("form"); 
        categoryForm.action='<portlet:renderURL><portlet:param name="action" value="showCategoryDisplay"/></portlet:renderURL>&specificAction=addOrEdit'; 
        categoryForm.method="post"; 
        document.body.appendChild(categoryForm);

        var input = document.createElement("input"); 
        input.type="hidden"; 
        input.name="categoryId"; 
        input.value=categoryId; 
        
        categoryForm.appendChild(input); 
        setTimeout(function(){categoryForm.submit();}, 0)
    };
    function changeProperty(inputObj,imgId){
    	inputVal = inputObj.value;
    	var img = document.getElementById(imgId);
    	if(trim(inputVal)==""){
    			img.style.visibility="hidden";
			}else{
				img.style.visibility="visible";
			}
    }
    function doValidate() {
        var isValid = true;
        if (trim(document.getElementById('category.name').value) == "") {
        	showError("<spring:message code='customerReportsAdmin.label.categoryNameRequired'/>",null, true);
        	isValid = false;
        }
        return isValid;
    };
    function trim(str){ 
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    	    
/*
    function convertRoleCheckbox() {
        var i = 0;
        for (i = 0; document.getElementById("roleList[" + i +"].isRequired") != null; i ++) {
            if (document.getElementById("parameterList[" + i +"].isRequired").checked) {
                document.getElementById("parameterList[" + i +"].isRequired").value = "true";
            } else {
                document.getElementById("parameterList[" + i +"].isRequired").value = "false";
            }
        }
        
    };*/
    
    function addCategory() {
        window.location.href = '<portlet:renderURL><portlet:param name="action" value="showCategoryDisplay"/></portlet:renderURL>&specificAction=addOrEdit';
    };

    function doDelete(categoryId, categoryName) {
	   	if(confirm('Are you sure to delete ' + categoryName + '?')){
	       var url = '<portlet:resourceURL id="deleteCategory"/>';
	       url +="&categoryId="+categoryId;
	       doAjax(url, doDeleteGetResult);
	       }
    };

    function swapCategoryOrder(orderNumber, increment) {
        var url = '<portlet:resourceURL id="swapCategory"/>';
        url +="&orderNumber="+orderNumber + "&increment=" + increment;
        doAjax(url, swapCategoryOrderGetResult);
    };

    function doDeleteGetResult(result) {
    	categoryListGrid.clearAndLoad("${categoryDisplayForm.categoryListURL}?orderBy=1&direction=ASCENDING");
    };

    function swapCategoryOrderGetResult() {
    	categoryListGrid.clearAndLoad("${categoryDisplayForm.categoryListURL}?orderBy=1&direction=ASCENDING");
    };

    function onCancelClick() {
    	window.location.href = "<portlet:renderURL></portlet:renderURL>";
    };

    function clearLocalizationInput(localizationIndex) {
        document.getElementById('category.localeList' + localizationIndex + '.name').value = "";
        document.getElementById('img_delete'+localizationIndex).style.visibility="hidden";
    };
</script>