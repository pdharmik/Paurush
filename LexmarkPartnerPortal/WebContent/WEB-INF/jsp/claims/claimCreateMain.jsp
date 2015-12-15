<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap" >
    <div class="content">	
		<div class="grid-controls">
			<div class="utilities">
				<ul>
					<li class="first">
						<img width="23px" height="23px" title="<spring:message code='image.title.exportPDF'/>" src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon cursor-pointer">
					</li>
					<li>
						<img width="23px" height="23px" title="<spring:message code='image.title.exportExcel'/>" src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon cursor-pointer">
					</li>
					<li>
						<img width="23px" height="23px" title="<spring:message code='image.title.print'/>" src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer">
					</li>
				</ul>
			</div><!-- utilities -->
			<div class="expand-min"><A href="#"><< Return to Requests</A></div>
			<div class="clear-right"></div>
			<br/>
		</div>
		
		
		<h1>Submit a Warranty Claim</h1>
		<div class="portlet-wrap" id="claim_information">
			<!-- include jsp here -->
		</div>	
		
		
		<div>
			<H4><img class="cursor-pointer" src="<html:imagesPath/>collapsed.jpg">&nbsp;View Service History</H4>
		</div>
		<div class="portlet-wrap" id="service_history">
			<div class="portlet-header"><h3>Service History</h3></div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div id="service_history_container"></div>
			</div><!-- portlet-wrap-inner -->
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div>	
		
		
	</div>
</div>