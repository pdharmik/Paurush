
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.VENDORACCID_AVAIL" %>






<div id="partnerError" style="display: none;">
	<div class="error">
		<ul><li><strong><spring:message code="requestInfo.accountSelection.errorMessage"/></strong></li></ul>
	</div>
	<div class="portletBodyWrap rounded infoBox">
		<a href="#" onclick="showAccountPopup();"><span>Select Account</span></a>
	</div>
</div>
<div id="totalContent">
	<jsp:include page="/WEB-INF/jsp/accountSelection/accountSelection.jsp"/>
</div>

<script>
var dialog;

function showAccGridinPopup(){
	var mdmLevel="${mdmLevel}";

	if(mdmLevel != "Account")
	{
			dialog=jQuery('#totalContent').dialog({
				autoOpen: false,
				modal: true,
				height: 460,
				width: 600,
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
						  var url=window.location.href;
						  hideOverlay();
						 if(url.indexOf("<%=VENDORACCID_AVAIL%>")==-1)
							  jQuery("#partnerError").show();
					  	 
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



function cancelRequest(){
	dialog.dialog('close');
}
</script>