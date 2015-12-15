<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<portlet:renderURL var="createClaimRequestPageURL">
	<portlet:param name="action" value="showCreateClaimRequestPage"/>
</portlet:renderURL>

<portlet:renderURL var="claimUpdateRequestPageURL">
	<portlet:param name="action" value="showClaimUpdatePage"/>
	<portlet:param name="fromPage" value="createClaimMultipleClaimView" />
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr>
		<td>
			<div class="main-wrap">
			    <div class="content">
			        <div class="grid-controls">
			            <div class="utilities">
			            </div><!-- utilities -->
			            
			            <div class="expand-min">
			                        <a href="javascript:void(0);" onClick="returnToRequests();return false;"><<&nbsp;<spring:message code="link.return.to.requests"/></a>
			            </div><!-- expand-min -->  
			            <div class="clear-right"></div><!-- clear -->
						<br>
			        </div>
			        
			        <!-- Device Information Start-->
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			                <h3><spring:message code="claim.label.deviceInformation"/></h3>
			            </div>
			            <div class="portlet-wrap-inner">
			            	<div class="width-100per">
				                <div class="columns two">
				                    <div class="first-column">
				                        <dl>
				                            <dd>
				                                <H3>${deviceSelectionForm.asset.productLine}</H3>
				                            </dd>
				                            <dd>
				                                <div class="icon" id="manImage">
				                                    <img src="${deviceSelectionForm.asset.productImageURL}"  />
				                                </div>
				                            </dd>
				                        </dl>
				                    </div>
				                    <div class="second-column">
				                        <dl>
				                            <dd>
				                                <table>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.serialNumber"/></label></td>
				                                        <td>
				                                           ${deviceSelectionForm.asset.serialNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.machineTypeModel"/></label></td>
				                                        <td>
				                                           ${deviceSelectionForm.asset.modelNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.productPN/TLI"/></label></td>
				                                        <td>
				                                           ${deviceSelectionForm.asset.productTLI}
				                                        </td>
				                                    </tr>
				                                </table>
				                            </dd>
				                            
				                            <dd>
				                                <a href="###" onClick="showDeviceSelectionPage();"><spring:message code="claim.label.selectAnotherPrinter"/></a>
				                            </dd>
				                        </dl>
				                    </div>
				                </div>
			                </div>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			        <!-- Device Information End-->
			        
			        <!-- Open Claim Exists Start -->
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			                <h3><spring:message code="claim.label.openClaimExist"/></h3>
			            </div>
			            <div class="portlet-wrap-inner">
 			            <!--Style changes done for below div under CI 7 -->
				            <div class="div-style10" style="width: expression(this.width > 1200 ? 1200: true;">
				                <div class="columns two">
				                    <div class="icon float-left" id="manImage" >
				                        <img src="<html:imagesPath/>exclamation.png"  />
				                    </div>
				                    <dl>
				                        <dd><spring:message code="claim.label.openClaimExist.description"/></dd>
				                        <dd>
				                            <label><spring:message code="claim.label.openClaimExist.note"/>:</label><spring:message code="claim.label.openClaimExist.noteDetail"/>
				                        </dd>
				                    </dl>
				                </div>
				                <br><br>
				                <div class="clear-right"></div><!-- clear -->
 				                <!--Below width changed under CI 7 -->
				                <div id="gridDSVClaim"  class="width-900px"></div>
				                <div id="claimLoadingNotification" class="gridLoading" style="display: none;">
				                    <br><!--spring:message code="label.loadingNotification"/-->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
				                </div>
								<div>
									<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
								</div>
				                <!-- Hidden form for selecting a device -->
								<form:form id="deviceSelectionForm" commandName="deviceSelectionForm" method="post">
									<form:hidden path="asset.assetId" />
									<form:hidden path="notMyPrinterFlag"/>
									<form:hidden path="asset.productLine"/>
									<form:hidden path="asset.serialNumber" />
									<form:hidden path="asset.modelNumber"/>
									<form:hidden path="asset.productTLI"/>
								</form:form>
				                <a href="javascript:createNewClaim();" class="button"><spring:message code="claim.button.createNewClaim"/></a>
			            	</div>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			        <!-- Open Claim Exists end -->
			        
			    </div><!-- content -->
			</div><!-- main-wrap -->
		</td>
	</tr>
</table>

    
<script type="text/javascript">
    var openClaimListGrid = new dhtmlXGridObject("gridDSVClaim");
    openClaimListGrid.setImagePath("<html:imagesPath/>gridImgs/");
    openClaimListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='claim.headerList.openClaimList'/>",6));
    openClaimListGrid.setColAlign("left,left,left,left,left,left");
    openClaimListGrid.setColTypes("ro,ro,ro,ro,ro,ro");
    openClaimListGrid.setInitWidths("140,140,230,230,250,150");
	openClaimListGrid.setColSorting("str,str,str,str,str,na");
    openClaimListGrid.enableAutoHeight(true);
    openClaimListGrid.enableAutoWidth(true);
    openClaimListGrid.setSkin("light");
    openClaimListGrid.init();
	openClaimListGrid.enablePaging(true, 5, 10, "pagingArea", true);
	openClaimListGrid.setPagingSkin("bricks");
    openClaimListGrid.setSortImgState(true, 0, "des");
    openClaimListGrid.loadXMLString("${openClaimListXML}");
    
    function showClaimUpdateView(activityId) {
    	callOmnitureAction("Create Claim Device Selection - Multiple Claims View", "Select an open claim to update");
    	//alert("Go to Claim Update View");
    	window.location.href = "${claimUpdateRequestPageURL}" + "&activityId=" + activityId + "&timezoneOffset="+timezoneOffset;
    }

    function createNewClaim() {    	
    	var mki = "${mki}";    	
    	var serviceProvider = "${serviceProvider}";
    	callOmnitureAction("Create Claim Device Selection - Multiple Claims View", "Create new claim");
    	assetId = document.getElementById('asset.assetId').value;
    	window.location.href = "${createClaimRequestPageURL}" + "&assetId=" + assetId + "&notMyPrinterFlag=false" + "&timezoneOffset="+timezoneOffset + "&mki=" + mki + "&serviceProvider=" + serviceProvider;
    }

    function showDeviceSelectionPage() {
    	callOmnitureAction('Create Claim Device Selection - Multiple Claims View', 'Select Another Printer');
    	jConfirm("<spring:message code="claim.alert.selectAnotherPrinter" />", "", function(answer) {
	  		if (!answer) {
	  			return;
	  		} else {
	  			window.location.href = "<portlet:renderURL><portlet:param name='action' value='showGlobalPartnerAssetSectionView' /></portlet:renderURL>";
	  		}
  		});
    }

    function returnToRequests() {
        window.location.href = "<portlet:renderURL><portlet:param name='action' value='retrieveRequestList' /></portlet:renderURL>";
    }

</script>
<script type="text/javascirpt">
	addPortlet("Create Claim Device Selection - Multiple Claims View");
</script>