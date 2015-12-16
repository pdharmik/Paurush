<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<style type="text/css"><%@ include file="/WEB-INF/css/mps.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css"><%@ include file="/WEB-INF/css/grid/dhtmlxgrid_pgn_bricks.css" %></style>
<style>
.move_type1{padding-left:0px!important;}
@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content-wrapper-inner">
	<div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2> 
      </div>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <div id="accNameAgreeName"></div>
      <!-- END -->
      
       <div class="main-wrap">
	      <div class="content">
	      <div>
					<a href="javascript:void(0)" onClick="doPrint();" id="topPrintLink">
						<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='requestInfo.tooltip.printThisPage'/>" />
						<span class="cursor-pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
					</a>
		
		  </div>

		
	
      <div id="divPrintBodyTop"> </div>
      <div id="divPrintBodyBottom"></div>

		<div>
		<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
				<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='requestInfo.tooltip.printThisPage'/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
			</a>
			</div>
</div>
</div>
</div>
<script type="text/javascript">


window.document.getElementById("divPrintBodyTop").innerHTML = window.opener.window.document.getElementById("emailPrintTop").innerHTML;
window.document.getElementById("divPrintBodyBottom").innerHTML = window.opener.window.document.getElementById("emailPrintBottom").innerHTML;
//document.getElementById("divPrintBodyPageCounts").innerHTML = gridContent;
/* Added for CI7 BRD14-02-12 */
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
/* END */
        function doPrint(){
    		
    		window.document.getElementById("topPrintLink").style.display='none';
    		window.document.getElementById("btmPrint").style.display='none';
    		window.print();
    		window.close();
    	};
</script>