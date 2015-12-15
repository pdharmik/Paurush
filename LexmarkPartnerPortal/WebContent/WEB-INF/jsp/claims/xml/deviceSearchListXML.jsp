<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<rows>
	<c:if test="${globalPartnerAssetListResult.assetList != null && globalPartnerAssetListResult.totalCount>0}">
		<c:forEach var="asset" items="${globalPartnerAssetListResult.assetList}" varStatus="state"> 
			<row>
				<cell><![CDATA[
					<table width="100%">
						<tr>
							<td width="30%">
								<div class="icon" id="manImage">
									<img width="75" src="${asset.productImageURL}"  />
								</div>
							</td>
							<td width="50%">
								<table>
										<tr>
											<td align="right"><label><b><spring:message code='generateAssetList.label.productLine' />:</b></label></td>
											<td width="20px">&nbsp;</td>
											<td>
											   ${asset.productLine}
											</td>
										</tr>
										<tr>
											<td align="right"><label><b><spring:message code='generateAssetList.label.MachineType' />:</b></label></td>
											<td width="20px">&nbsp;</td>
											<td>
											   ${asset.modelNumber}
											</td>
										</tr>
										<tr>
											<td align="right"><label><b><spring:message code='generateAssetList.label.productTLI' />:</b></label></td>
											<td width="20px">&nbsp;</td>
											<td>
											   ${asset.productTLI}
											</td>
										</tr>
								</table>
							</td>
							<td width="20%">
								<input type="hidden" id="assetId${state.index}" value="${asset.assetId}">
								<input type="hidden" id="assetProductLine${state.index}" value="${asset.productLine}">
								<input type="hidden" id="assetModelNumber${state.index}" value="${asset.modelNumber}">
								<input type="hidden" id="assetProductTLI${state.index}" value="${asset.productTLI}">
								<input type="hidden" id="customerReportingName${state.index}" value="${asset.customerReportingName}">
								<input type="hidden" id="duplicateDevice${state.index}" value="${asset.duplicateDevice}">
								<input type="hidden" id="maintainanceKitInstall${state.index}" value="${asset.mki}">
								<input type="hidden" id="serviceProvider${state.index}" value="${asset.serviceProvider}">
								<table width="100%">
									<tr>
										<td>
											<a href="javascript:selectDevice(${state.index});" class="button"><span><spring:message code='generateAssetList.label.select' /></span></a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				]]></cell>
			</row>
		</c:forEach>
	</c:if>
</rows>
