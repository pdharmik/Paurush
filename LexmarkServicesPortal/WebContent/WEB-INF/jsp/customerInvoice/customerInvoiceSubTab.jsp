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
	<%--<ul>
      <li class="${accountsReceivable}">
      <span><a><spring:message code="customerInvoicePayments.link.accountsReceivable"/></a></span>
      </li>
    </ul> --%>
  <div id="totalContent" style="display: none;">
	<jsp:include page="vendorSelection.jsp"/>
	</div>
</div>
<%--
<a  href="<portlet:resourceURL id="showInvoiceListURL"></portlet:resourceURL>">CLICK ME</a>   
 --%>
<script type="text/javascript">
function openPopUp(typeOfGrid){
	//alert('in open popup');
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