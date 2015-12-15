<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<style type="text/css">
	@media print{
	.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
	}

</style>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content-wrapper-inner">
	<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
			</div>
	<div class="main-wrap">
		<div class="content">
			<div>
				<a onClick="javascript:doPrint();" id="topPrintLink"> <img
					src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span class="cursor-pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>

			<%--Changes BRD 12 CI 7--%>
               <div id="accNameAgreeName"></div>
               <%--End Changes BRD 12 CI 7--%>


			<div id="divPrintBodyTop"></div>
			<div id="divPrintBodyPageCounts" class="portletBlock infoBox columnInner rounded shadow">
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
          
           
           <c:forEach items="${reviewAssetOrderForm.pageCountsList}" var="assetPageCountsList" varStatus="counter" begin="0">
		  <c:choose>
		  <c:when test="${counter.index == 0}">
		  <tr>
                <th colspan="2" align="left" class="div-style72"><spring:message code="requestInfo.heading.pageCount"/></th>
              </tr>
              	</c:when>
              	<c:otherwise>
              	</c:otherwise>
              	</c:choose>
		 <c:if test="${(assetPageCountsList.count != null && assetPageCountsList.count !='')||(assetPageCountsList.date !=null && assetPageCountsList.date !='')}">	 
		<tr>
          <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
          
              <tr>
                <td valign="top" width="100" class="div-style73"><strong>${assetPageCountsList.name}:</strong></td>
                <td valign="top"  class="table-td-style16">${assetPageCountsList.count}</td>
              </tr>
              <tr>
                <td valign="top"  class="div-style73"><strong><spring:message code="requestInfo.label.dateAndTime"/>:</strong></td>
                <td valign="top"  class="table-td-style16">${assetPageCountsList.date}</td>
              </tr>
              
            </table></td>
          
        </tr>
        </c:if>
       </c:forEach>
      </table>
      </div>
		<div id="divPrintBodyBottom"></div>
			<div>
				<a id="btmPrint" onClick="javascript:doPrint();"> <img
					src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span class="cursor-pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	window.document.getElementById("divPrintBodyTop").innerHTML = window.opener.window.document.getElementById("confirmOrderPrintWrapperTop").innerHTML;
	window.document.getElementById("divPrintBodyBottom").innerHTML = window.opener.window.document.getElementById("confirmOrderPrintWrapperBottom").innerHTML;
	 window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
	function doPrint() {
		window.document.getElementById("topPrintLink").style.display = 'none';
		window.document.getElementById("btmPrint").style.display = 'none';
		window.print();
		window.close();
	};
</script>
