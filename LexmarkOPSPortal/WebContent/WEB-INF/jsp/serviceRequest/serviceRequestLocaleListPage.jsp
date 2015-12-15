<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<style>
#gridbox input {height:10px}
#gridbox select {height:22px}
div.gridbox_light table.hdr .filter input {
    margin-left: 0 !important;
}
</style>

<form:form id="serviceRequestListForm">

<portlet:actionURL var="addURL">
	<portlet:param name="action" value="details"/>
</portlet:actionURL>

<portlet:resourceURL var="deleteURL" id="delete">
</portlet:resourceURL>
<script type="text/javascript">
	var addURL = "${addURL}";
</script>

<div class="main-wrap">
	<div class="content">
		<table width="100%">
			<tr><td><table class="cellTable">
				<tr>
					<td class='orangeSectionTitles'><spring:message code="serviceRequestLocaleListPage.title" /></td>
				</tr>
				<tr>
					<td class='description'><spring:message code="serviceRequestLocaleListPage.description" /></td>
				</tr>
			</table></td></tr>
			<tr class="paddingTR"><td>&nbsp;</td></tr>
			<tr><td><table class="cellTable">
				<tr class="buttonContainer" style="float:left">
					<td style="padding:5px;text-align: left;">
						<a href="javascript:callOmnitureAction('Service Request', 'Service Request Locale List Add Locale');location.href=addURL;" class="button" style="text-decoration:none;color:#FFF;"><spring:message code="serviceRequestLocaleListPage.addLocaleDetails"/></a>
					</td>
				</tr>
				<tr>
					<td>
					<div id="gridbox" style="background-color: white; overflow: auto;"></div></td>
				</tr>
				<tr> 
					<td height="20">
						<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
					</td>
				</tr>
			</table></td></tr>			
		</table>
		<div id="loadingNotification" class='gridLoading' style="width: 100%">
			<!--spring:message code="message.loadingNotification"/>&nbsp;--><img src="<html:imagesPath/>gridloading.gif" />
		</div>
	</div>
		
</div>	
</form:form>
<script type="text/javascript">
if(jQuery.browser.msie){
	var count=1;
}
	srgrid = new dhtmlXGridObject('gridbox');
	srgrid.setImagePath("<html:imagesPath/>/gridImgs/");
	srgrid.setHeader("<spring:message code='serviceRequestLocaleListPage.listHeader'/>");
	if(!jQuery.browser.msie){
	var attachHeader="#combo_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,&nbsp;";
	srgrid.attachHeader(attachHeader);
	}
	srgrid.setColAlign("left,left,left,left,left,left,left");
	srgrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
	srgrid.setColSorting("StrSortCI,domSort,StrSortCI,str,str,str,na");
	srgrid.enableAutoHeight(true);
	srgrid.setInitWidthsP("20,20,14,12,14,10,10");
	srgrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	srgrid.setPagingSkin("bricks");
	var optionTypeMap = [];
	//alert("${finalOptionTypeLovMap}");
	<c:forEach items="${sessionScope.finalOptionTypeLovMap}" var="finalOptionLovMap" varStatus = "status" >
	optionTypeMap[${status.index}] = ["${finalOptionLovMap.key}","${finalOptionLovMap.value}"];
	</c:forEach>
	srgrid.setCustomizeCombo(optionTypeMap,0);
	
	//srgrid.enableAutoWidth(true); //CI-6 Changes
	//srgrid.enableAutoHeight(true);
	//srgrid.enableMultiline(true); //CI-6 Changes
	srgrid.setSizes();
	srgrid.init();
	srgrid.prftInit();				//CI-6 Changes
	srgrid.a_direction = "asc";
	srgrid.setSkin("light");
	srgrid.sortIndex = 1;		//CI-6 Changes
	srgrid.columnChanged = true;	//CI-6 Changes
	srgrid.attachEvent("onXLE", function() {
		if(jQuery.browser.msie){
		if(count==1){
		var attachHeader="#combo_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,&nbsp;";
		srgrid.attachHeader(attachHeader);
		}
		}
		//this.sortRows(0, "StrSortCI", 'asc');
		//this.setSortImgState(true,0,'asc');
		jQuery("#loadingNotification").hide();
		var widthContent=$('#serviceRequestListForm .content').width();
		$('#gridbox').css({"width":widthContent+'px'});
		if(jQuery.browser.msie){
		count++;
		}
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});
	srgrid.loadXML("<portlet:resourceURL></portlet:resourceURL>");
	
</script>
<script type="text/javascript">

function doDelete(id){
    callOmnitureAction('Service Request', 'Service Request Locale List Delete Locale');
	if(confirm("<spring:message code='serviceRequestLocaleListPage.text.confirmDelete'/>")){
		var deleteURL = "${deleteURL}";
		deleteURL += "&id="+id;		
		doAjax(deleteURL, function(a){srgrid.deleteSelectedRows();});
	}
}

/* author:wipro
 * added as a part of the CI 13.10 release
 * BRD #13-10-11 add filter in the locale settings page under administration tab
 *The below code is for the adding filter & facilates case insensitive searching 
 */

srgrid.attachEvent("onFilterStart", function(indexes,values){
  
	var filterValues = values;
	var searchFilterLevel = new Array("optionType","siebelValue","showEntitlementFlag","DirectpartnerType","IndirectpartnerType","statusOrder");
	var queryParam="";
	var manySearchString = 0;
	
	for(i=0;i<filterValues.length;i++)
	{
		if(filterValues[i] != "")
			{
				//alert("Array1-->>"+filterValues[i]);
				//alert("Key-->>"+searchFilterLevel[i]);
				if(manySearchString>=1)
					{
					//alert("inside IF");
					queryParam = queryParam+">>"+searchFilterLevel[i]+":'%25"+filterValues[i]+"%25'";  //%25 is the escape charecters for %
					}
				else
					{
					//alert("inside ELSE");
					queryParam ="filterCriterias="+searchFilterLevel[i]+":'%25"+filterValues[i]+"%25'";
					}
				
				//alert(queryParam);
				manySearchString++;
			}		
	}	
	srgrid.filterValues = values+",";	
 	var customizedGridURL = updateGridURL("<portlet:resourceURL></portlet:resourceURL>", srgrid.sortIndex, srgrid.a_direction, srgrid.filterValues);
 	//alert(customizedGridURL);
 	if(queryParam != "")
 		{
 		//alert("modifying the URL");	
 		//alert("queryParam-->>"+queryParam);
 		//queryParam = "filterCriterias=OPTION_TYPE:yyy>>SIEBEL_VALUE:ppp";
 		customizedGridURL = customizedGridURL.replace(/filterCriterias=.*/,queryParam);
 		//alert("Customized-->>"+customizedGridURL+"----"+queryParam);
 		//customizedGridURL = customizedGridURL.replace(/,/g,"");
 		//alert("After modification-->>"+customizedGridURL);
 		}
 	else
 		{
 		customizedGridURL = customizedGridURL.replace(/filterCriterias=.*/,"");
 		}
	//alert("the grid url is-->>"+customizedGridURL);
	
	srgrid.attachEvent("onXLS", function() {		
		jQuery("#loadingNotification").show();
		//alert("XLS");
		jQuery("#pagingArea").hide();
		jQuery("#infoArea").hide();
	});
	
	srgrid.attachEvent("onXLE", function() {		
		jQuery("#loadingNotification").hide();
		jQuery("#pagingArea").show();
		jQuery("#infoArea").show();
		//alert("XLE");
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});
	var partnerTypeMap = [];
	partnerTypeMap[0]=["YES","YES"];
	partnerTypeMap[1]=["NO","NO"];
	partnerTypeMap[2]=["N/A","N/A"];
	srgrid.setCustomizeCombo(partnerTypeMap,3);
	srgrid.setCustomizeCombo(partnerTypeMap,4);
			var optionTypeMap = [];
			//alert("${finalOptionTypeLovMap}");
			<c:forEach items="${sessionScope.finalOptionTypeLovMap}" var="finalOptionLovMap" varStatus = "status" >
			optionTypeMap[${status.index}] = ["${finalOptionLovMap.key}","${finalOptionLovMap.value}"];
			</c:forEach>
			srgrid.setCustomizeCombo(optionTypeMap,0);	
 	    	srgrid.clearAndLoad(customizedGridURL);
 	}); 


</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request Locale List";
     addPortlet(portletName);
     pageTitle="Locale list";
</script>