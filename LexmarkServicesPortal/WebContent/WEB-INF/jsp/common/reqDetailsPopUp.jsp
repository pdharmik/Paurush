<%@ page contentType="text/html" isELIgnored="false" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>

<script type="text/javascript">
function goToDetailPage(serviceRequestNumber,serviceRqstType)
	{
	
	var requestType=serviceRqstType;
	//timezoneOffset added for CI Defect #8217
	var url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showRequestDetailsPopup'/></portlet:renderURL>&requestType="+
	requestType+"&serviceRequestNumber="+serviceRequestNumber+"&timeZoneOffset="+timezoneOffset+"&uniqueTime="+new Date().getTime();
	 if(requestType=='Consumables_Management') {
			showOverlay();	
			
				
						jQuery('#dialogSupplyDetails').load(url,function(){
							
						dialog=jQuery('#contentDetailsSupply').dialog({
							autoOpen: false,
							title: '<spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/>',
							modal: true,
							draggable: false,
							resizable: false,
							position: 'center',
							height: 'auto',
							width: 940,
							open: function(){	
														
								supplyRqstDetails(serviceRequestNumber,requestType);
							},
							close: function(event,ui){
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsSupply').remove();
								},
							error: function (jqXHR, textStatus, errorThrown) {
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsSupply').remove();
								}
							});
						dialog.dialog('open');
						});						
			
		} else if(requestType=='Fleet_Management') {   

		
			showOverlay();
					jQuery('#dialogChangeDetails').load(url,function(){
					dialog=jQuery('#contentDetailsChange').dialog({
						autoOpen: false,
						title: '<spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/>',
						modal: true,
						draggable: false,
						resizable: false,
						position: 'center',
						height: 'auto',
						width: 940,
						open: function(){							
							changeRqstDetails(serviceRequestNumber,requestType);
						},
						close: function(event,ui){
							hideOverlay();
							jQuery('#contentDetailsChange').remove();
							//jQuery('#dialogChangeDetails').remove();
							dialog.dialog('destroy');					 
							dialog=null;
							
							},
						error: function (jqXHR, textStatus, errorThrown) {
							hideOverlay();
							dialog.dialog('destroy');					 
							dialog=null;
							jQuery('#contentDetailsChange').remove();
							}
						});
					dialog.dialog('open');
					});
		}

		else {
			showOverlay();
			if($('#serviceRequestDetailDiv').length==0){
				$('body').append("<div id=\"serviceRequestDetailDiv\"></div>");
			}
			
			var url="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='serviceRequestDrillDownLightBox' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox";
			
			$('#serviceRequestDetailDiv').load(url,function(){
	        	dialog=$('#serviceRequestDetailDiv').dialog({
	        		autoOpen: false,
					modal: true,
					title: "<spring:message code='serviceRequest.title.serviceRequest'/>",
					height: 900,
					width: 900,
					draggable: false,
					resizable: false,
					position: ['center','top'],
					open: function(){
							
			  			},
					close: function(event,ui){
	   				  	  hideOverlay();
	   				  	  dialog.dialog('destroy');
	   				  	showSelect();
						}
	        	});
	        	dialog.dialog('open');
	        });
			
			
		}
	}


 
function closeCustomizedWindow(){
	if(document.getElementById('grid_menu')!=null)
	{
   	  document.getElementById('grid_menu').style.display = 'none';
	}
   }
   
   </script>