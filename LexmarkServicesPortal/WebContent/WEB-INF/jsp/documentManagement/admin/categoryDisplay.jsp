<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<style>
.ie7 .portlet-topper{width:99% !important;}
.ie9 #categoryListContainer{width:100% !important;}
</style>
<!-- bELOW URL added for CI BRD 14-07-04 -->
<portlet:renderURL var="countryListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="countryDropDownPopulate"></portlet:param>
</portlet:renderURL>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
	    <div id="rptAdminCategoryDisplayTitle"  class="div-spl-style1">
	        <span class="orangeSectionTitles"><spring:message code="documentAdminPage.categoryPage.title"/></span><br/>
	        <spring:message code="documentAdminPage.categoryPage.description" /><br/><br/>
	    </div>
	    <div class="height-5px"></div>
	    <div >
	        <div id="categoryListContainer" class="width-800px"></div>
	        <div id="loadingNotification" class='gridLoading'>
		    	<img src="<html:imagesPath/>gridloading.gif"/><br>
		   </div>
	        <div>
	            <span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
	        </div>
	    </div>
	    <div>
	        <a href="javascript:addCategory()" class="button"><span><spring:message code="documentAdminPage.categoryPage.button.addCategory"/></span></a>
	        <br/><br/>
	        <portlet:actionURL var="saveCategoryURL">
	            <portlet:param name="action" value="saveCategory"></portlet:param>
	        </portlet:actionURL>
	        <div id="divCategoryDetail">
	            <form:form id="formCategoryDetail" commandName="categoryDisplayForm" method="post" action="${saveCategoryURL}" onSubmit="return doValidate();">
		            <span class="labelBold"><spring:message code="documentAdminPage.categoryPage.label.categoryName"/></span><br/>
		            <form:hidden path="category.categoryId"/>
		            <form:hidden path="category.orderNumber"/>
		            <div class="inputRegWrapper">
		            <form:input path="category.name" class="inputReg" maxlength="100"/>
		            </div>
		            <br/><br/>
		            <span class="labelBold"><spring:message code="documentAdminPage.categoryPage.label.localizedSettings"/></span>
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
	                					<img id="img_delete${counter.index}" class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="clearLocalizationInput(${counter.index})";   title="Clear"  />
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
	                <label class="labelBold"><spring:message code="documentAdminPage.categoryPage.label.roleRestrictions"/></label><br><br>
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
	                 <!-- Done for CI BRD 14-07-04 STARTS -->     
		                <div class="clear"></div>
		                <div class="columns two">
		                	<div class="first-column">
		                		<label class="labelBold"><spring:message code="customerReportsAdmin.label.partnerType"/></label><br>
<%-- 		                	<c:forEach items="${categoryDisplayForm.partnerExternalRoleList}" var="role" varStatus="counter" begin="0"> --%>
<%-- 			                    <form:input type="hidden" path="partnerExternalRoleList[${counter.index}].id"/> --%>
<%-- 			                    <form:input type="hidden" path="partnerExternalRoleList[${counter.index}].name"/> --%>
<%-- 			                    <input type="checkbox" id="partnerExternalRoleList_${role.id}" name="partnerExternalRoleList_${role.id}">&nbsp;${role.name}<br> --%>
<%-- 		                	</c:forEach> --%>
							<div id="Checkbox">
							<%-- Added for July 2014 Moved to LOV--%>
							<c:set var="found" value=""/>
							<c:forEach items="${partnerTypeLovList}" var="partnerType" varStatus="status">
							<c:set var="found" value=""/>
							<c:forEach items="${partnerTypeListForUser}" var="params">
								<c:if test="${params==partnerType.key}" >
									<c:set var="found" value="checked"/>
								</c:if>
							</c:forEach>
								 <input type="checkbox" id="partnerType${status.count}" name="partnerType${status.count}" value="${partnerType.key}" ${found}>${partnerType.value}<br>
							</c:forEach>
							<%-- Commented for July 2014 Moved to LOV
							<input type="checkbox" id="partnerType1" name="partnerType1" value="Lexmark Technician">&nbsp;<spring:message code="customerReportsAdmin.partnerType.LexmarkTechnician"/><br>
							<input type="checkbox" id="partnerType2" name="partnerType2" value="Alliance Partner">&nbsp;<spring:message code="customerReportsAdmin.partnerType.AlliancePartner"/><br>
							<input type="checkbox" id="partnerType3" name="partnerType3" value="Business Solution Dealer">&nbsp;<spring:message code="customerReportsAdmin.partnerType.BusinessSolutionDealer"/><br>
							<input type="checkbox" id="partnerType4" name="partnerType4" value="Service Provider">&nbsp;<spring:message code="customerReportsAdmin.partnerType.ServiceProvider"/><br>
							<input type="checkbox" id="partnerType5" name="partnerType5" value="Logistics Partner">&nbsp;<spring:message code="customerReportsAdmin.partnerType.LogisticPartner"/><br>
							<input type="checkbox" id="partnerType6" name="partnerType6" value="Authorized Service Partner">&nbsp;<spring:message code="customerReportsAdmin.partnerType.AuthorizedServicePartner"/><br>
							<input type="checkbox" id="partnerType7" name="partnerType7" value="Designated Service Partner">&nbsp;<spring:message code="customerReportsAdmin.partnerType.DesignatedServicePartner"/><br>
							<input type="checkbox" id="partnerType8" name="partnerType8" value="Self Maintainer">&nbsp;<spring:message code="customerReportsAdmin.partnerType.SelfMaintainer"/>
							--%>
		                	</div>
		                	</div>
		                </div> 
		                
		                <div class="clear"></div>   
		                <div id="countriesSelectionDIV">
	           		<table border=0 >
	           			<tr valign="top">
							<td>
								<spring:message code="customerReportsAdmin.label.selectPartnerAccountCountry"/><br/>
	                    		<div id="divCountriesSelection" class="div-style40">
                        <span><select  id="country" class="width-200px"></select></span>
	                    		</div>
							</td>
							 
							<td class="table-td-style21">
							
								<a onClick="checkDuplicateCountry();" class="button"><span><spring:message code="customerReportsAdmin.label.add"/></span></a>
							
							</td>
							
						</tr>
	           		</table>
	           		
					<table id="tblCountriesHeadr" width="50%" border=0>
						<tr height="25px">						
							<td class="labelBold width-90per"><spring:message code="customerReportsAdmin.label.country" /></td>
							<td class="labelBold width-10per"><spring:message code="customerReportsAdmin.label.action" /></td>						
						</tr>
					</table>
					
				</div>
				
				<div id="divCountriesList" class="div-style41">
					<table id="tblCountriesList" width="50%" border=0>
 						<c:forEach items="${countryListForUser}" var="countryListForUser" varStatus="countr" begin="0">
						<tr height="22px">
							<td class="width-93per">
								<input type="hidden" id="countriesList[${countr.index}]" name="countriesList[${countr.index}]" value="${countryListForUser.key}" />
								<label style="display:block" id="countriesList[${countr.index}].country">${countryListForUser.value}</label>
							</td>
							<td class="width-7per">
								<img class="ui_icon_sprite trash-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onClick="deleteCountry(${countr.index})" >
							</td>
						</tr>
 						</c:forEach>
					</table>
					<br/>
				</div>
		                <!-- Done for CI BRD 14-07-04 ENDS --> 
		            <div style = "width:720px; text-align: right">
		                <button onclick="javascript:onCancelClick()" id="Cancel_Library_Administration" class="button_cancel"><span><spring:message code="button.cancel"/></span></button>&nbsp;
		                <button onclick="getPartnerList(); javascript:document.getElementById('formCategoryDetail').submit();" id="select_Library_Administration" class="button"><span><spring:message code="button.submit"/></span></button>
		            </div>
		            <span style="display: none;">
				<input type="text" name="partnerTypeList" id="partnerTypeList" />
				</span>
				<span style="display: none;">
				<input type="text" name="countryList" id="countryList" />
				</span>
	            </form:form>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript">
jQuery(document).ready( function() {
	if (window.PIE) {
	    document.getElementById("Cancel_Library_Administration").fireEvent("onmove");
	    document.getElementById("select_Library_Administration").fireEvent("onmove");
	}
	loadCountryList();
	<%--Commented for July 2014 Moved to LOV
	var n=0;
	<c:forEach items="${partnerTypeListForUser}" var="params">
	if("${params}"==document.getElementById("partnerType1").value){
		document.getElementById("partnerType1").checked = true;
		
	}
	if("${params}"==document.getElementById("partnerType2").value){
		document.getElementById("partnerType2").checked = true;
		
	}
	if("${params}"==document.getElementById("partnerType3").value){
		document.getElementById("partnerType3").checked = true;
		
	}
	if("${params}"==document.getElementById("partnerType4").value){
		document.getElementById("partnerType4").checked = true;
		
	}
	if("${params}"==document.getElementById("partnerType5").value){
		document.getElementById("partnerType5").checked = true;
		
	}
	if("${params}"==document.getElementById("partnerType6").value){
		document.getElementById("partnerType6").checked = true;
		
	}
	if("${params}"==document.getElementById("partnerType7").value){
		document.getElementById("partnerType7").checked = true;
		
	}
	if("${params}"==document.getElementById("partnerType8").value){
		document.getElementById("partnerType8").checked = true;
		
	}
	  n++;
	  </c:forEach>
	--%>
});
	//combo to load the Countries 14-07-04
	//var comboCountriesSelection = new dhtmlXCombo("divCountriesSelection", "legalName", 200);
	
    categoryListGrid = new dhtmlXGridObject('categoryListContainer');
    categoryListGrid.setImagePath("<html:imagesPath/>gridImgs/");
    categoryListGrid.setHeader("<spring:message code='documentAdminPage.categoryPage.listHeader.categoryList'/>");
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
    var selectedCountryName;
    var selectedCountryCode;

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
        categoryForm.action='<portlet:renderURL><portlet:param name="action" value="categoryDisplay"/></portlet:renderURL>'; 
        categoryForm.method="post"; 
        document.body.appendChild(categoryForm);

        var input = document.createElement("input"); 
        input.type="hidden"; 
        input.name="categoryId"; 
        input.value=categoryId; 
        
        categoryForm.appendChild(input); 
        setTimeout(function(){categoryForm.submit();}, 0)
    };
    
    //function to check Duplicate Country -14-07-04
    function checkDuplicateCountry() {
    	//comboCountriesSelection.confirmValue();
    	var x = document.getElementById("country").selectedIndex;
 		selectedCountryName = document.getElementsByTagName("option")[x].text;
 		//alert("selectedCountryName :: "+ selectedCountryName);
 		selectedCountryCode = jQuery("#divCountriesSelection :selected").val();
 		//alert("selectedCountryCode "+selectedCountryCode);
		var tableCountries = document.getElementById('tblCountriesList');	
		var tableCountriesRowNum = tableCountries.rows.length;
		var isNew = true;
		for(i=0;i<tableCountriesRowNum;i++) {
			isNew = false;
			isNew = true;
// 			if(selectedCountryName == document.getElementById('countriesList[' + i +']').value) {
// 				alert('Inside If');
// 				alert('<spring:message code="customerReportsAdmin.alert.duplicateCustomerName"/>');
// 				break;
// 			}
// 			else {
// 				isNew = true;
// 			}
		}
		if(isNew) {
			addCountry();
		}
	};
	//Function to Add a Country to the table- 14-07-04
	var countryList = [];
	var selectedOption;
	function addCountry() {
		if(selectedCountryName == '') {
			// do nothing
		} else {

			var selectedCountries = document.getElementById('tblCountriesList').getElementsByTagName('tr');
			var selectedCountriesCount = selectedCountries.length;
			//alert("selectedCountriesCount "+selectedCountriesCount);
			var countriesTable = document.getElementById('tblCountriesList');			
			selectedOption = jQuery("#divCountriesSelection :selected").val();
			//alert("selectedOption"+selectedOption);
			var colValue;
			var addCountryToTable = "true";
			
			//countryList[0]=selectedOption;
			if(selectedCountriesCount>=1){

				for(var i =0;i<=selectedCountriesCount-1;i++){
					//alert('countriesList['+i+'].country' +i);
					//alert(document.getElementById('countriesList['+i+'].country'));
				    colValue = document.getElementById('countriesList['+i+']').value;
				    //alert("colValue "+colValue);
				    countryList[0]=selectedOption;				
					countryList[i]=colValue;					
					if(selectedOption==countryList[i]){
						//alert("Duplicate Country selected");
						addCountryToTable = "false";
					}

				}	
					
			}
			else if(selectedCountriesCount==0){
				countryList[0]=selectedOption;	
				//alert("countryList in 2nd for :: "+ countryList);
			}
			if(addCountryToTable=="true"){
				addCountriesToTable();
			}
			jQuery('#countryList').val(countryList);
			
			
			
			document.getElementById("country").value='';
		}
	};
	//Ajax call to load the country list - 14-07-04
	function loadCountryList(){

		
		jQuery.ajax({
			url:'${countryListPopulateURL}',
			type:'POST',
			dataType: 'html',
			success: function(countryListResult){
						
						jQuery("#country").append(countryListResult);	
						
						
						//Below code is for Back button to populate country n state list
// 						if(jQuery('#isoCode').val()!="")
// 						{
// 							jQuery('#country').val(jQuery('#isoCode').val());
// 						}			
				},
		  failure: function(results){}
		});
	}
	
	//Function to add the Selected Country to the table -- 14-07-04
	function addCountriesToTable(){
		
		var tableCountries = document.getElementById('tblCountriesList');	
		var tableCountriesRowNum = tableCountries.rows.length;
	    var newCountryRow = tableCountries.insertRow(tableCountriesRowNum);
	    newCountryRow.style.height='22px';
	    var cellCountry = newCountryRow.insertCell(0);
	    cellCountry.style.width='93%';
	    var cellDeleteCountry = newCountryRow.insertCell(1);
	    cellDeleteCountry.style.width='7%';
	    cellCountry.innerHTML = '<input type="hidden" id="countriesList[' + (tableCountriesRowNum) + ']" name="countriesList[' + (tableCountriesRowNum) + ']" value="' + selectedOption + '" /><label id="countriesList[' + (tableCountriesRowNum) + '].country">' + selectedCountryName + '</label>';
	    cellDeleteCountry.innerHTML = '<img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" onClick="deleteCountry(' + (tableCountriesRowNum) + ')" style="cursor:pointer;">';
	    document.getElementById('divCountriesList').style.display = "block";
	};
	
	//To Delete Country-- 14-07-04
	function deleteCountry(countryIndex) {
		var tableCountry = document.getElementById('tblCountriesList');
		var tableCountriesRowNum = tableCountry.rows.length;
		var deletedCountry;
		for (var i = countryIndex; i < tableCountriesRowNum - 1; i ++) {
			document.getElementById('countriesList[' + i +']').value = document.getElementById('countriesList[' + (i + 1) +']').value;
			document.getElementById('countriesList[' + i +'].country').innerHTML = document.getElementById('countriesList[' + (i + 1) +'].country').innerHTML;
		}
		
		tableCountry.deleteRow(tableCountriesRowNum - 1);
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
        window.location.href = '<portlet:renderURL><portlet:param name="action" value="categoryDisplay"/></portlet:renderURL>';
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

    function getPartnerList(){
       // alert("in getPartnerList");
    	var chkArray = [];
    	
    	/* look for all checkboes that have a parent id called 'checkboxlist' attached to it and check if it was checked */
    	jQuery("#Checkbox input:checked").each(function() {
    		chkArray.push(jQuery(this).val());
    	});
    	
    	/* we join the array separated by the comma */
    	var selected;
    	for(var i=0;i<chkArray.length;i++){
    //	alert("size "+chkArray.length);
    	selected = chkArray.join(',') + ",";
    	var selectedList = selected.substring(0, selected.length-1);
    	//alert("checked list is "+selectedList);
    	jQuery('#partnerTypeList').val(selectedList);
    	}
    	var selectedCountries = document.getElementById('tblCountriesList').getElementsByTagName('tr');
		var selectedCountriesCount = selectedCountries.length;
		//alert("selectedCountriesCount "+selectedCountriesCount);
		for(var j =0;j<selectedCountriesCount;j++){
			//alert("in for b4 submitting "+j);
		    colValue = document.getElementById('countriesList['+j+']').value;		
		  //  alert("colValue b4 submitting " +colValue);	
			countryList[j]=colValue;			
		}
		jQuery('#countryList').val(countryList);
    	
    };
</script>