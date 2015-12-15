<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portlet-wrap" id="email_Notifications">
	<div id="emailNotificationsContent">
		<div class="portlet-header">
			<h3><spring:message code="claim.label.emailNotifications" /></h3>
		</div><!-- portlet-header -->
		<div class="portlet-wrap-inner">
			<div class="width-100per">
				<div id="gridCDVEmailNotifications" class="width-100per"></div>
				<div id="pagingArea"></div>
			</div>
		</div><!-- portlet-wrap-inner -->
		<div class="portlet-footer">
			<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
		</div><!-- portlet-footer -->
	</div>
</div>
<script type="text/javascript">
	emailNotificationsGrid = new dhtmlXGridObject('gridCDVEmailNotifications');
	emailNotificationsGrid.setImagePath("<html:imagesPath/>gridImgs/");
	emailNotificationsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.emailNotifications" />',4));
	emailNotificationsGrid.setColAlign("left,left,left,left");
	emailNotificationsGrid.setInitWidths("25,100,200,800");
	emailNotificationsGrid.enableAutoWidth(true);
	emailNotificationsGrid.setColTypes("sub_row,ro,ro,ro");
	emailNotificationsGrid.setColSorting("na,str,str,str");
	emailNotificationsGrid.enableAutoHeight(true);
	emailNotificationsGrid.enableMultiline(true);
	emailNotificationsGrid.setSkin("light");
	emailNotificationsGrid.enablePaging(true, 5, 5, "pagingArea", true);	
	emailNotificationsGrid.setPagingSkin("bricks");
	emailNotificationsGrid.init();
	emailNotificationsGrid.loadXML('<portlet:resourceURL id="showEmailNotificationsURL"></portlet:resourceURL>&serviceRequestId=${serviceRequestId}&emailAddress=${claimDetailForm.claimDetail.emailAddress}');

	emailNotificationsGrid.attachEvent("onXLE", function() {
		if(emailNotificationsGrid.getRowsNum() == 0)
			document.getElementById('email_Notifications').style.display = 'none';
	});
</script>