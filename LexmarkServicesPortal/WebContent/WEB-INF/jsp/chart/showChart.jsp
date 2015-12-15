<%@ include file="../include.jsp"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<style type="text/css">
.ErrorMessage {
	width: 430px;
	word-wrap: break-word;
}
</style>
<div class="main-wrap">
	<div class="content">
		<style type="text/css"> 
		<% 
		String portletid = (String)request.getAttribute(WebKeys.PORTLET_ID);
		portletid = portletid.substring(portletid.lastIndexOf('_'));
		%>
 			#m\3Aportlet\7Er\3A<%= portletid %>Links { display:none; }
		</style>
			<a href="<portlet:renderURL/>"><spring:message code="analyticsReport.startOver"/></a><br/>
			<!-- CI-6 (as config function not done and chart path stored in DB) -->
			<c:if test="${isPublishing}">
			<a href="<portlet:renderURL><portlet:param name='action' value='showConfigurationPage' /></portlet:renderURL>"><spring:message code="chartportlet.configure"/></a>
			</c:if>
			
			<br/>
			<div>
				<div id="analyticsReport<portlet:namespace/>" style="display:none;"></div>
				<div id="chartloadingNotification<portlet:namespace />" class='gridLoading'>
					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
				</div>	
			</div>
	</div>
</div>
<script type="text/javascript">
	jQuery(document).ready(function(){
			//alert('entered method');
		if(${analyticsSessionID == false}){
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
									var url = "<portlet:resourceURL id='retrieveChart'></portlet:resourceURL>&portletId=<portlet:namespace/>&sessionID="+sessionID;
									jQuery("#analyticsReport<portlet:namespace />").load(url,function(response){
											//alert('Resourcemapping run properly');
										jQuery("#chartloadingNotification<portlet:namespace />").hide();
							            jQuery("#analyticsReport<portlet:namespace />").show();
										});
									
									
								
								},
							failure: function(){
									//alert('it is failure');
								}
							});
			
		           }

		if(${analyticsSessionID == true}){
			var url = "<portlet:resourceURL id='retrieveChart'></portlet:resourceURL>&portletId=<portlet:namespace/>";
			jQuery("#analyticsReport<portlet:namespace />").load(url);
					//alert('Resourcemapping run properly');
				jQuery("#chartloadingNotification<portlet:namespace />").hide();
	            jQuery("#analyticsReport<portlet:namespace />").show();
		}
			
				
	});
</script>


<script type="text/javascript">
//---- Ominture script 
     portletName = "Chart";
     addPortlet(portletName);
     pageTitle="Chart";
</script>