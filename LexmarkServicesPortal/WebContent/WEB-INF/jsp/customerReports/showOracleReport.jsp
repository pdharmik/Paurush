<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<style>
	div.buttonsPanel {
	
		padding: 25px 0;
		overflow: hidden;	
	}
	
	
.report-btn {
	color: #fff;
	background: #3181c5;
	border: 0px;
}
.report-btn2 {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 12px/100% Arial, Helvetica, sans-serif;
	min-width:50px;
	padding: 4px 9px;
	text-transform: uppercase;
	margin-right:5px;
	line-height:normal !important;
}
.ie .report-btn:hover, .ie .gridbox .report-btn:hover .report-btn:link {
	background: #489CE3;
}
.report-btn:hover, .gridbox .report-btn:hover {
	background: #489CE3;
}
.ie .report-btn:active, .ie .gridbox .report-btn:active, .bactive {
	background: #096BBE;
}
.report-btn:active, .gridbox .report-btn:active .report-btn:link {
	background: #096BBE;
}

	/*div.right-column{overflow:hidden;margin-left:0;padding-left: 5px;}
	#reports-banner,div.portlet-wrap{width:auto;}*/

	
</style>

<script type="text/javascript">
function callAjax(){
	//alert('entered method');
	jQuery.ajax({
		
		url:'/LexmarkServicesPortal/ChartObieeServlet',			
		type:'POST',
		data: {
		
			 	//Ajax call with selected row data
				mdmParamRequire :   '${mdmParamRequire}',
				userLanguage:  '${userLanguage}',
				userCountry:  '${userCountry}',
				userSegment:  '${userSegment}',
				userId:  '${userId}'
				//accountOrganization : '',
														
		},
		success: function(results){
				//If ajax call is success, go to change to remove contact page
				//alert('ajax call successs from here call for report bridge');
				var sessionID = results;
				//alert('session id is '+sessionID);
				var docDefinitionId = '${docDefinitionId}';
				//alert('docDefinitionId is '+docDefinitionId);
				var url = "<portlet:resourceURL id='retrieveChart'></portlet:resourceURL>&sessionID="+sessionID+"&docDefinitionId="+docDefinitionId;
				jQuery("#analyticsReport").load(url,function(response){
						//alert('Resourcemapping run properly');
					});
					
					
				jQuery("#analyticsReport").load(url,function(response){
                                                //alert('Resourcemapping run properly');
                                        });
					
					
					
					
				jQuery("#chartloadingNotification").hide();
	            jQuery("#analyticsReport").show();
			},
		failure: function(){
				//alert('it is failure');
			}
		});
}
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div class="main-wrap">
<div class="journal-content-article">
      <h1>Reports</h1>
      
</div>


	<div class="content">
		<%@ include file="/WEB-INF/jsp/customerReports/leftPanel.jsp"%>
		<style type="text/css"> 
		<% 
		String portletid = (String)request.getAttribute(WebKeys.PORTLET_ID);
		portletid = portletid.substring(portletid.lastIndexOf('_'));
		%>
		
 			#m\3Aportlet\7Er\3A<%= portletid %>Links { display:none; }
 			#m\3Aportlet\7Er\3A_nullLinks { display:none; }
		</style>
		<div class="right-column"><!-- col-xs-12 col-sm-9 col-md-9 --> 
			<div class="portlet-wrap">
				<div class="portlet-header">
				<!-- added for employee report -->
				<c:if test="${not isEmployee}">
				<table class="displayGrid line-height-20px">
              	<thead>
                	<tr><th>${definitionDisplayForm.definitionName}</th></tr>
               	</thead>
             	</table>
            	</c:if>
            	<c:if test="${isEmployee}">
				<table class="displayGrid width-100per">
             	 <thead>
                <tr><th>${definitionDisplayForm.definitionName} - ${accountName}</th></tr>
                </thead>
              	</table>
				</c:if>
				<!-- end of addition for employee report -->
					
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${definitionDisplayForm.definitionDescription}</span>
				</div>
				<div class="portlet-wrap-inner">
					<div class="buttonsPanel">
			 			<portlet:actionURL var="editOracleReport">
	        				<portlet:param name="action" value="editOracleReport"></portlet:param>
	    	 			</portlet:actionURL>				
						 <form id="editOracleReportForm" method="post" action="${editOracleReport}">
			 				<input type="hidden" id="docDefinitionId" name="docDefinitionId" value="${docDefinitionId}"/>
			 				<input type="hidden" id="roleCategoryId" name="roleCategoryId" value="${roleCategoryId}"/>
			 				<!-- 			Changes for 14.9 Potential CR 14722 -->
							<input id='roleCategoryIdArrayPlus1' type='hidden' name='roleCategoryIdArrayPlus'/>
							<input id='roleCategoryIdArrayMinus1' type='hidden' name='roleCategoryIdArrayMinus'/>
			 				<button class="report-btn report-btn2" type="submit" onclick="setArray()"><spring:message code="customerReports.oracleReport.editReportParameter"/></button>
						 </form>
					</div>
					<div>
					<div id="analyticsReport" style="display:none;"></div>
					<div id="chartloadingNotification" class='gridLoading'>	
						<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
					</div>	
			</div>
				</div>
			</div>
		</div>
		<div class="portlet-footer">
			<div class="portlet-footer-inner">	</div>
		</div>
	</div>
</div>	
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery("#analyticsReport").load(callAjax(),function(response){
        jQuery("#chartloadingNotification").hide();
        jQuery("#analyticsReport").show();
       
	});
});
//Added for 14.9 Potential CR 14722
function setArray(){
	var roleCategoryIdArrayPlusList = ${roleCategoryIdArrayPlusList};
	var roleCategoryIdArrayMinusList = ${roleCategoryIdArrayMinusList};
	document.getElementById('roleCategoryIdArrayPlus1').value = roleCategoryIdArrayPlusList;
	document.getElementById('roleCategoryIdArrayMinus1').value = roleCategoryIdArrayMinusList;
}
</script>

<script type="text/javascript">
//---- Ominture script 
     portletName = "Oracle Anaytics Report";
     addPortlet(portletName);
     pageTitle="Oracle Anaytics Report";
</script>