<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css">
.displayGrid{
line-height:20px;
}
.modal{
position:absolute !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">	
		<!-- Request Information start -->
		<div class="portlet-wrap" >
			<div class="portlet-header dark-bar">
			<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.RequestInformation"/></th>
                </tr></thead>
              
            </table>
				
			</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div>
						<table>
							<tr>
								<td width="140px"  align="right" ><span ><B><spring:message code="claim.label.requestNumber"/></B></span></td>
								<td width="20px"/></td>
								<td><span>${activity.serviceRequest.serviceRequestNumber}</span></td>
							</tr>
							<tr>
								<td width="140px"  align="right" ><span><B><spring:message code="claim.label.openedDateTime"/></B></span></td>
								<td width="20px"/></td>
								<td><span><util:dateFormat value="${activity.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></span></td>
							</tr>
							<c:if test="${activity.serviceRequest.serviceRequestEndDate != null}">
							<tr>
								<td width="140px"  align="right" ><span><B><spring:message code="claim.label.closedDateTime"/></B></span></td>
								<td width="20px"/></td>
								<td><span><util:dateFormat value="${activity.serviceRequest.serviceRequestEndDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></span></td>
							</tr>
							</c:if>
							<tr>
								<td width="140px"  align="right" ><span><B><spring:message code="claim.label.requestStatus"/></B></span></td>
								<td width="20px"/></td>
								<td><span>${activity.activityStatus.name}</span></td>
							</tr>
							<tr>
								<td width="140px"  align="right" ><span><B><spring:message code="claim.label.statusDetail"/></B></span></td>
								<td width="20px"/></td>
								<td><span>${activity.activitySubStatus.name}</span></td>
							</tr>
						</table>
					</div>
				</div>
			</div>	
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>			
		</div>
		<!-- Request Information end -->
							
		<!-- Device Information start -->
		<div class="portlet-wrap">
			<div class="portlet-header dark-bar dark-bar">
			<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.deviceInformation"/></th>
                </tr></thead>
              
            </table>
			
			</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="first-column div-style27" >
							<dl>
		                       	<dd>
		                        	<H3>${activity.serviceRequest.asset.productLine}</H3>
		                        </dd>
		                        <dd>
		                        	<div class="icon" id="manImage">
		                            	<img src="${activity.serviceRequest.asset.productImageURL}"  />
		                        	</div>
		                        </dd>
							</dl>
						</div><!-- first-column -->
					<div class="second-column div-style27" >
							<table border="0">
								<tr>
								 	<td width="150px" align="right"><b><spring:message code="claim.label.serialNumber"/></b></td>
									<td width="20px" />
								  	<td class="labelTD">${activity.serviceRequest.asset.serialNumber}</td>
								</tr>	 
								<tr>
									<td class="labelTD" align="right"><b><spring:message code="claim.label.machineTypeModel"/></b></td>
									<td/>
									<td class="labelTD">${activity.serviceRequest.asset.modelNumber}</td>
								</tr>
								<tr>
									<td class="labelTD" align="right"><b><spring:message code="claim.label.productPN/TLI"/></b></td>
									<td/>
									<td class="labelTD">${activity.serviceRequest.asset.productTLI}</td>
								</tr>
							</table>
						</div><!-- second-column -->
					</div>
				</div>
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>			
		</div>
		<!-- Device Information end -->

							
		<!-- Problem Information start -->
		<div class="portlet-wrap" id="problem_information">
			<div class="portlet-header dark-bar">
			<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.problemInformation" /></th>
                </tr></thead>
              
            </table>
			
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div class="width-100per">
						<table border="0" width="100%">
							<tr>
								<td><b><spring:message code="claim.label.problemCode" /></b></td>
								<td><b><spring:message code="claim.label.resolutionCode" /></b></td>
							</tr>
							<tr>
								<td class="padding-left-5px">${activity.actualFailureCode.name}</td>
								<td class="padding-left-5px">
									<c:if test="${activity.debrief != null && activity.debrief.resolutionCode != null}">
									${activity.debrief.resolutionCode.name}
									</c:if>
								</td>
							</tr>
							<tr class="height-15px"><td></td><td></td></tr>
							<tr>
								<td><b><spring:message code="claim.label.problemDetails" /></b></td>
								<td><b><spring:message code="claim.label.repairDescription" /></b></td>
							</tr>
							<tr>
								<td class="padding-left-5px">${activity.serviceRequest.problemDescription}</td>
								<td class="padding-left-5px">${activity.debrief.repairDescription}</td>
							</tr>
						</table>
					</div>
			</div><!-- portlet-wrap-inner -->
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div>		
		<!-- Problem Information end -->
		
		<!-- Parts and Tools start -->
		<div class="portlet-wrap">
			<div class="portlet-header dark-bar">
			<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.partsAndTools" /></th>
                </tr></thead>
              
            </table>
				
			</div>
			<div class="portlet-wrap-inner">
				<div class="scroll740">
					<div id="gridVSHPPartsAndTools" class="star-width100per-height100px"></div>
					<div>
						<span id="gridVSHPPartsAndToolsPagingArea"></span>&nbsp;<span id="gridVSHPPartsAndToolsInfoArea"></span>
					</div>
				</div>
			</div>	
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>			
		</div>
		<script type="text/javascript">
		function closePopup(){
			jQuery(window).scrollTop(0);
			showSRPopup.destroy();
			showSRPopupFunction();
		}
			jQuery(document).ready(function() {
				//column added under CI BRD 13-10-15
				var partsAndToolsGrid = new dhtmlXGridObject('gridVSHPPartsAndTools');
				partsAndToolsGrid.setImagePath("<html:imagesPath/>gridImgs/");
				partsAndToolsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.serviceHistory.partsAndTools" />',6));
				partsAndToolsGrid.setColAlign("left,left,left,left,left,left");
				partsAndToolsGrid.setColTypes("ro,ro,ro,ro,ro,ro");
				partsAndToolsGrid.setColSorting("str,str,str,str,str,str");
				partsAndToolsGrid.setInitWidths("80,160,165,165,165,165");
				partsAndToolsGrid.enableAutoWidth(true);
				partsAndToolsGrid.enableAutoHeight(true);
				partsAndToolsGrid.enableMultiline(true);
				partsAndToolsGrid.enablePaging(true, 5, 10, "gridVSHPPartsAndToolsPagingArea", true, "gridVSHPPartsAndToolsInfoArea");
				partsAndToolsGrid.init();
				partsAndToolsGrid.setSkin("light");
				partsAndToolsGrid.setPagingSkin("bricks");		
				partsAndToolsGrid.loadXMLString('${partsAndToolsXML}');
			});
		</script>
		<!-- Parts and Tools end -->
	</div>