<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript"><%@ include file="../../../js/tree/dhtmlxtree.js"%></script>
<style type="text/css"><%@ include file="../../css/tree/dhtmlxtree.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<script type="text/javascript"><%@ include file="../../../js/portletRedirection.js"%></script>
<%@page import="jsx3.gui.Alerts"%>
<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxgrid_excell_sub_row.js"%></script>

<div id="partnerError" style="display: none;">
	<div class="error">
		<ul><li><strong><spring:message code='validation.selectAccount'/></strong></li></ul>
	</div>
	<div class="portletBodyWrap rounded infoBox">
		<a href="#" onclick="showAccountPopup();"><span><spring:message code='requestInfo.heading.selectAccount'/></span></a>
	</div>
</div>
<div id="totalContent">
	<jsp:include page="/WEB-INF/jsp/common/accountSelection.jsp"/>
</div>

<script type="text/javascript">
var dialog;
jQuery(document).ready(function(){	
	var currentURL = window.location.href;	
	if(currentURL.indexOf('create') != -1)
	{		
		window.location.href="<portlet:renderURL><portlet:param name='action' value='showAssetListPageForPartner'/></portlet:renderURL>";
	}
	else{
		showAccountPopup();
	}
});
function showAccGridinPopup(){
	var mdmLevel="${mdmLevelForAssetDetails}";

	if(mdmLevel != "Account")
	{
			dialog=jQuery('#totalContent').dialog({
				autoOpen: false,
				modal: true,
				height: 460,
				width: 700,
				position: ['center','top'],
				open: function(){
						jQuery('#totalContent').show();
						jQuery('#accountInitialize').show();
	  					jQuery('span.ui-dialog-title').text("<spring:message code='requestInfo.heading.accountSelection'/>");
			  								
						
						},
				close: function(event,ui){
	   				  //jQuery('.ui-dialog').empty().remove();
	   				  dialog.dialog('destroy');

	   				 // jQuery('#accountInitialize').appendTo(jQuery('#totalContent'));
					  dialog=null;
					  /*alert('in change Request close');*/
					  jQuery('#accountInitialize').hide();
					  /*alert(buttName);*/
					  if(ajaxAccountSelection=="success")
					  {
						  //updateRequest();
						  ajaxSuccessFunction();
					  }
					  else
					  {
						  if(window.location.href.indexOf('/partner-portal') != -1)
					  	  {
							  hideOverlay();
							  jQuery("#partnerError").show();
					  	  }
					  }
					}
				});
			
			jQuery(document).scrollTop('0');
			dialog.dialog('open');
			
	}
}
function showAccountPopup(){
	 
		
	showOverlay();
	if(accountGrid==null){
			initializeAccountGrid(showAccGridinPopup);
	}else{

			if(accountGrid.getAllRowIds()=="")
				reloadAccountPopupGrid();
			else
				showAccGridinPopup();
		
				/*if(accountGrid.getAllRowIds()=="" && callAccountSelectionForAssetList == "true")
					reloadAccountPopupGrid();*/
		}
}

ajaxSuccessFunction=function updateRequest(){
	var currentURL = window.location.href;
	if(currentURL.indexOf('/partner-portal') != -1)
	{			
		var partnerURLWithoutParams;
		var partnerIndexOfQuestionMark = currentURL.indexOf("?");
		if (partnerIndexOfQuestionMark > 0) {
			partnerURLWithoutParams = currentURL.substring(0, partnerIndexOfQuestionMark);
		} else {
			partnerURLWithoutParams = currentURL;
		}
		partnerBaseURL = partnerURLWithoutParams.substring(0, partnerURLWithoutParams.lastIndexOf('/partner-portal') + '/partner-portal'.length) + "/service-requests";
		showOverlay();
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+partnerBaseURL;
		return;
	}
}

function cancelRequest(){
	dialog.dialog('close');
}
</script>