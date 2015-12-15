<%@ page contentType="text/html" isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- <c:if test='${sessionScope.accountCurrentDetails["rowCount"]!="1"}'> --%>
<!-- If condition commented for Defect #12162 -->
	  
	  <c:set var = "accountName" value = "${sessionScope.accountCurrentDetails['accountName'] }"/>
      <c:set var = "agreementName" value = "${sessionScope.accountCurrentDetails['agreementName'] }"/>
<!--Changed for CI Defect 11260 -->
    	<c:if test="${not empty accountName &&  not empty agreementName }">
    	&nbsp;&nbsp;&nbsp;${accountName } / ${agreementName }
    	</c:if>
    	<c:if test="${not empty accountName && empty agreementName }">
    	&nbsp;&nbsp;&nbsp;${accountName }
    	</c:if>
    	<c:if test="${empty accountName && not empty agreementName }">
    	&nbsp;&nbsp;&nbsp;${agreementName }
    	</c:if>
    	
		

<%-- </c:if> --%>
<script type="text/javascript">
function showChangeAccountPopup(){
	jConfirm('<spring:message code="common.country.alert"/>', "", function(confirmResult) {
		if(confirmResult){
			showAccountPopup();
		}else{
			return false;
		}
});
}

function showAccountPopup(){


showOverlay();
if(accountGrid==null){
		initializeAccountGrid(showAccGridinPopup);
}else{

		if(accountGrid.getAllRowIds()=="")
			reloadAccountPopupGrid();
		else{
			accountGrid.filterBy(0,"true",true);
    		if(accountGrid.getAllRowIds() != "" ){
				if(accountGrid.getAllRowIds().split(',').length == 1){
    	        	jQuery('#button'+accountGrid.getAllRowIds()).click();
    		 	}else{
    		 		showAccGridinPopup();
	    		 	}
			}else{
				showAccGridinPopup();
				}
		}
	}

	
	
}

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
  							jQuery('span.ui-dialog-title').text("Select An Account");
					},
			close: function(event,ui){
   				  dialog.dialog('destroy');
					hideOverlay();
				  dialog=null;
				  jQuery('#accountInitialize').hide();
				  if(ajaxAccountSelection=="success")
				  {
					  ajaxSuccessFunction();
				  }
				}
			});
		
		jQuery(document).scrollTop('0');
		dialog.dialog('open');
}else{
ajaxSuccessFunction();
}
}

</script>
		