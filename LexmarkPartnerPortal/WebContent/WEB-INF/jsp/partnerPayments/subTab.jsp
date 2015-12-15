<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="subTabSelected" value="${requestScope.subTabSelected}"></c:set>

<c:choose>
<c:when test="${subTabSelected eq 'accountsPayable'}">
<c:set var="accountsPayable" scope="page" value="selected"></c:set>
</c:when>
<c:when test="${subTabSelected eq 'accountsReceivable'}">
<c:set var="accountsReceivable" scope="page" value="selected"></c:set>
</c:when>
</c:choose>
<portlet:resourceURL var="showInvoiceListURL" id="showInvoiceListURL">
	<%-- <portlet:param name="action" value="showInvoiceList"/> --%>
</portlet:resourceURL>
<portlet:renderURL var="redirectToInvoicePage">
<portlet:param name="action" value="redirectToInvoicePage"></portlet:param>
</portlet:renderURL>
<div id="subnavigation">
	
	<!-- Links for history or create new request -->
	<ul>		
    	<li class="${accountsPayable}">
      	<a href="#tabs-1" onclick="openPopUp('AP');"><spring:message code="partnerPayments.link.accountsPayable"/></a>
		</li>
		<li class="${accountsReceivable}">
      	<a id="tab2anchor" onclick="openPopUp('AR');" href="#tabs-2"><spring:message code="partnerPayments.link.accountsReceivable"/></a>
		</li>
    </ul>
<div id="totalContent" style="display: none;">
	<jsp:include page="vendorSelection.jsp"/>
</div>
</div>
<script>
function openPopUp(typeOfGrid){
	showOverlay();
	showAccountPopup(typeOfGrid);		
				
	}

ajaxSuccessFunction=function updateRequest(){
	//alert('in ajaxSuccessFunction');
	//window.location.href = "${showInvoiceListURL}";
	if(ajaxAccountSelection=="success"){
		showOverlay();
		window.location.href = "${redirectToInvoicePage}";
	}
}

function ajaxSuccessFuncForAR(typeId)
{
	//alert("inside ajax success for ar");
	//var url = "${showInvoiceListURL}&typeId="+typeId;
	//alert('in ajaxSuccessFunction');
	if(ajaxAccountSelection=="success"){
		var url ="${redirectToInvoicePage}&typeId="+typeId;
		showOverlay();
		window.location.href = url;
	}
}
function cancelRequest(){
	dialog.dialog('close');
}
</script>