<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

//1)The first value is the id/name of the input field
//2)Siebel Field name for Customer profile
//3) Spring message code transaltion for the field name of customer profile
//4) Spring message code for error message if the field left blank
/*
4:["userEnteredActivity.technician.technicianName",""],
3:["userEnteredActivity.debrief.serviceEndDate",""],
1:["userEnteredActivity.estimatedArrivalTime","",""],
2:["userEnteredActivity.debrief.serviceStartDate","","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.actualStartDate" javaScriptEscape="true"/>"],
5:["userEnteredActivity.serviceRequest.asset.installDate","InstallDate","localised install date","enter install date"],
1:["userEnteredActivity.serviceRequest.asset.serialNumber","SerialNumber" , "localised serial number","enter serial number"],
A3 LTPC
A3 Mono
A4 Color
A4 LTPC
A4 Mono
A5 Color
A5 LTPC
A5 Mono
*/
/*
* 







1:["userEnteredActivity.serviceRequest.asset.assetField1","AssetField1"],


*/
var customerProfileFields=[];
<c:forEach items="${hardwareDebriefForm.userEnteredActivity.cpFields}" var="cpField" varStatus="counter">
	customerProfileFields[${counter.index}]="${cpField.fieldName}";
</c:forEach>



 
 var headingMessage={validationMsg:"<spring:message code="requestInfo.hardwareDebrief.heading" javaScriptEscape="true"/>"};
var inputNamesMapToSiebel={
			 
			 
		1:["userEnteredActivity.serviceRequest.asset.networkConnectedFlag","LXK MPS Network Connected PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.networkConnected" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.networkConnected" javaScriptEscape="true"/>"],

		2:["userEnteredActivity.serviceRequest.asset.ipAddress","LXK MPS IP Address PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.iP" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.iP" javaScriptEscape="true"/>"],
		3:["userEnteredActivity.serviceRequest.asset.ipV6","LXK MPS IP V6 PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipv" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipv" javaScriptEscape="true"/>"],
		4:["userEnteredActivity.serviceRequest.asset.assetField2","LXK MPS Asset Field2 PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.assetField" arguments="2" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebrief.assetField" javaScriptEscape="true"/>"],
		5:["userEnteredActivity.serviceRequest.asset.assetField3","LXK MPS Asset Field3 PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.assetField" arguments="3" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebrief.assetField" javaScriptEscape="true"/>"],
		6:["userEnteredActivity.serviceRequest.asset.specialUsage","LXK MPS Usage UOM PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.splUsage" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.splUsage" javaScriptEscape="true"/>"],
		7:["userEnteredActivity.serviceRequest.asset.operatingSystem","LXK MPS OS PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.os" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.os" javaScriptEscape="true"/>"],
		8:["userEnteredActivity.serviceRequest.asset.operatingSystemVersion","LXK MPS OS Version PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.osVersion" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.osVersion" javaScriptEscape="true"/>"],
		9:["userEnteredActivity.serviceRequest.asset.firmware","LXK MPS Firmware PreDebriefRFV" ,"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.firmware" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.firmware" javaScriptEscape="true"/>"],
		10:["userEnteredActivity.serviceRequest.asset.networkTopology","LXK MPS Network Topology PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.nwTopology" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.nwTopology" javaScriptEscape="true"/>"],
		11:["userEnteredActivity.serviceRequest.asset.department","LXK MPS Dept Name PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.departmentName" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.departmentName" javaScriptEscape="true"/>"],
		12:["userEnteredActivity.serviceRequest.asset.assetCostCenter","LXK MPS Asset Cost Center PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.costCenter" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.costCenter" javaScriptEscape="true"/>"],
		13:["userEnteredActivity.serviceRequest.asset.subnet","LXK MPS IP Submask PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.subnetMask" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.subnetMask" javaScriptEscape="true"/>"],
		14:["userEnteredActivity.serviceRequest.asset.gateway","LXK MPS IP Gateway PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipGateway" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipGateway" javaScriptEscape="true"/>"],
		15:["userEnteredActivity.serviceRequest.asset.macAddress","LXK MPS MAC Address PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.macAddress" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.macAddress" javaScriptEscape="true"/>"],
		16:["userEnteredActivity.serviceRequest.asset.hostName","LXK MPS Host Name PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.hostName" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.hostName" javaScriptEscape="true"/>"],
		17:["userEnteredActivity.serviceRequest.asset.portNumber","LXK MPS Port Num PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.portNo" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.portNo" javaScriptEscape="true"/>"],
		18:["userEnteredActivity.serviceRequest.asset.faxConnectedValue","LXK MPS Fax Connected PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxConnected" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxConnected" javaScriptEscape="true"/>"],
		19:["userEnteredActivity.serviceRequest.asset.faxPortNumber","LXK MPS Fax port num PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxPortNo" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxPortNo" javaScriptEscape="true"/>"],
		20:["userEnteredActivity.serviceRequest.asset.printerWorkingCondition","LXK MPS Device Condition PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.printerCondition" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.printerCondition" javaScriptEscape="true"/>"],
		21:["userEnteredActivity.serviceRequest.asset.wiringClosestNetworkPoint","LXK MPS Wiring Closet_Netwk Pt PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.wiringNwPt" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.wiringNwPt" javaScriptEscape="true"/>"],
		22:["userEnteredActivity.serviceRequest.asset.description","LXK MPS Asset Desc PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.description" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.description" javaScriptEscape="true"/>"],
		23:["userEnteredActivity.serviceRequest.asset.deviceTag","LXK MPS Customer Device Tag PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.customerDeviceTag" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.customerDeviceTag" javaScriptEscape="true"/>"],
		24:["userEnteredActivity.customerAccount.accountName","LXK MPS Account Name PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.accountName" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.accountName" javaScriptEscape="true"/>"],
		25:["userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation1","LXK R Physical Location 1","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.building" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.building" javaScriptEscape="true"/>"],
		26:["userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation2","LXK R Physical Location 2","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.floor" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.floor" javaScriptEscape="true"/>"],
		27:["userEnteredActivity.serviceRequest.asset.installAddress.physicalLocation3","LXK R Physical Location 3","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.office" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.office" javaScriptEscape="true"/>"],
		28:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","A3 Color","<spring:message code="pageCntUploadLegend.a3ColorHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.a3ColorHeader" javaScriptEscape="true"/>"],
		29:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","A3 LTPC","<spring:message code="pageCntUploadLegend.a3LTPCHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.a3LTPCHeader" javaScriptEscape="true"/>"],
		
		31:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","A4 Color","<spring:message code="pageCntUploadLegend.column.a4Color" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.column.a4Color" javaScriptEscape="true"/>"],	 
		32:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","A4 LTPC","<spring:message code="pageCntUploadLegend.column.a4LTPC" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.column.a4LTPC" javaScriptEscape="true"/>"],
        
        34:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","A5 Color","<spring:message code="pageCntUploadLegend.column.a5Color" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.column.a5Color" javaScriptEscape="true"/>"],
		35:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","A5 LTPC","<spring:message code="pageCntUploadLegend.column.a5LTPC" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.column.a5LTPC" javaScriptEscape="true"/>"] ,
		
		37:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Color","<spring:message code="pageCntUpload.color" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUpload.color" javaScriptEscape="true"/>"],
        38:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","LTPC","<spring:message code="pageCntUpload.ltpc" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUpload.ltpc" javaScriptEscape="true"/>"],
        39:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Legal Color","<spring:message code="pageCntUploadLegend.legalColorHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.legalColorHeader" javaScriptEscape="true"/>"],
        60:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Legal LTPC","<spring:message code="pageCntUploadLegend.legalLTPCHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.legalLTPCHeader" javaScriptEscape="true"/>"],
        
        41:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","PGS_SCAN_COPY","<spring:message code="pageCntUploadLegend.pgsScanCopyHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.pgsScanCopyHeader" javaScriptEscape="true"/>"],
		42:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","PGS_SCAN_FAX","<spring:message code="pageCntUploadLegend.pgsScanFaxHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.pgsScanFaxHeader" javaScriptEscape="true"/>"],
		43:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","PGS_SCAN_NETWORK","<spring:message code="pageCntUploadLegend.pgsScanNetworkHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.pgsScanNetworkHeader" javaScriptEscape="true"/>"],
		44:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","PGS_SCAN_USB","<spring:message code="pageCntUploadLegend.pgsScanUsbHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.pgsScanUsbHeader" javaScriptEscape="true"/>"],
		45:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Statement Color","<spring:message code="pageCntUploadLegend.statementColorHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.statementColorHeader" javaScriptEscape="true"/>"],
		46:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Statement LTPC","<spring:message code="pageCntUploadLegend.statementLTPCHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.statementLTPCHeader" javaScriptEscape="true"/>"],
		
		48:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Tabloid Color","<spring:message code="pageCntUploadLegend.tabloidColorHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.tabloidColorHeader" javaScriptEscape="true"/>"],
		49:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Tabloid LTPC","<spring:message code="pageCntUploadLegend.tabloidLTPCHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.tabloidLTPCHeader" javaScriptEscape="true"/>"],
		
		
		52:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Letter LTPC","<spring:message code="pageCntUploadLegend.letterLTPCHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.letterLTPCHeader" javaScriptEscape="true"/>"],
		53:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","Letter Color","<spring:message code="pageCntUploadLegend.letterColorHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.letterColorHeader" javaScriptEscape="true"/>"],
		54:["userEnteredActivity.serviceRequest.contactInfoForDevice[].deviceContactType","Consumable Mgmnt Specialist","<spring:message code="requestInfo.hardwareDebrief.consumbaleContact" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebrief.consumbaleContact" javaScriptEscape="true"/>"],
		
		
		
		
		55:["userEnteredActivity.serviceRequest.asset.deinstSerialNumber","LXK MPS Deinst Serial Num PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.SerialNumber.deinstall" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.SerialNumber.deinstall" javaScriptEscape="true"/>"],
		56:["userEnteredActivity.serviceRequest.asset.deinstAssetTag","LXK R Deinstalled Asset Tag","<spring:message code="hardware.massUploadTemplate.CustomerDeviceTag.deinstall" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.CustomerDeviceTag.deinstall" javaScriptEscape="true"/>"],
		57:["userEnteredActivity.serviceRequest.asset.deinstBrand","LXK MPS Deinst Brand PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.Brand.deinstall"  javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.Brand.deinstall" javaScriptEscape="true"/>"],
		58:["userEnteredActivity.serviceRequest.asset.deinstComments","LXK MPS Deinst Comments PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.Comments"  javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.Comments" javaScriptEscape="true"/>"],
		59:["userEnteredActivity.serviceRequest.asset.deviceCondition","LXK MPS Deinst Device Cond PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.DeviceCondition.deinstall" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.DeviceCondition.deinstall" javaScriptEscape="true"/>"],
		60:["userEnteredActivity.serviceRequest.asset.deinstHostName","LXK MPS Deinst Host Name PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.HostName.deinstall" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.HostName.deinstall" javaScriptEscape="true"/>"],
		61:["userEnteredActivity.serviceRequest.asset.deinstIpAddress","LXK MPS Deinst IP Addr PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.IPAddress.deinstall" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.IPAddress.deinstall" javaScriptEscape="true"/>"],
		62:["userEnteredActivity.serviceRequest.asset.deinstModel","LXK MPS Deinst Model PreDebriefRFV" ,"<spring:message code="hardware.massUploadTemplate.DeviceTypeModelNumber.deinstall" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.DeviceTypeModelNumber.deinstall" javaScriptEscape="true"/>"],
		63:["userEnteredActivity.serviceRequest.asset.deinstPartNumber","LXK MPS Deinst Part Num PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.PartNumber.deinstall" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.PartNumber.deinstall" javaScriptEscape="true"/>"],
		64:["userEnteredActivity.serviceRequest.asset.deinstRemovalDate","LXK MPS Deinst Removal Date PreDebriefRFV","<spring:message code="hardware.massUploadTemplate.RemovalDate" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="hardware.massUploadTemplate.RemovalDate" javaScriptEscape="true"/>"]
		
		
		
		
	 };
var	 inputNamesMapToSiebel_notInPortal={
		4:["","LXK MPS Asset Field1 PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.assetField" arguments="1" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.hardwareDebrief.assetField" javaScriptEscape="true"/>"],
		5:["","LXK MPS Asset Training PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.assetTraining" javaScriptEscape="true"/>",""],
		7:["","LXK MPS CHL Name PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.chlName" javaScriptEscape="true"/>",""],
		8:["","Checklistandsignoff","<spring:message code="requestInfo.hardwareDebrief.customerProfile.checkList" javaScriptEscape="true"/>",""],
		9:["","LXK MPS Dept Id PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.departmentId" javaScriptEscape="true"/>",""],
		10:["","LXK R Device Tag Service Tag","<spring:message code="requestInfo.hardwareDebrief.customerProfile.deviceServiceTag" javaScriptEscape="true"/>",""],
		11:["","LXK R Device Tag PPID","<spring:message code="requestInfo.hardwareDebrief.customerProfile.deviceTagPPId" javaScriptEscape="true"/>",""],
		12:["","LXK R Device Tag OEM","<spring:message code="requestInfo.hardwareDebrief.customerProfile.deviceTagRId" javaScriptEscape="true"/>",""],
		13:["","LXK MPS HW Product Id","<spring:message code="requestInfo.hardwareDebrief.customerProfile.hwProduct" javaScriptEscape="true"/>",""],
		14:["","InstallInstructions","<spring:message code="requestInfo.hardwareDebrief.customerProfile.installInstructions" javaScriptEscape="true"/>",""],
		15:["","LXK MPS Loc Print Server PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.localPrintServer" javaScriptEscape="true"/>",""],
		16:["","LXK R Device Tag OEM","<spring:message code="requestInfo.hardwareDebrief.customerProfile.deviceTagRId" javaScriptEscape="true"/>",""],
		17:["","NetworkPages","<spring:message code="requestInfo.hardwareDebrief.customerProfile.networkPages" javaScriptEscape="true"/>",""],
		18:["","LXK MPS Phone Num2 PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.PhoneNumber2" javaScriptEscape="true"/>",""],
		19:["","LXK MPS Project PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.projectName" javaScriptEscape="true"/>",""],
		20:["","LXK MPS Project Phase PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.projectPhase" javaScriptEscape="true"/>",""],
		21:["","LXK MPS SAP Contract # PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.sapContract" javaScriptEscape="true"/>",""],
		22:["","LXK MPS SAP Line # PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.sapLine" javaScriptEscape="true"/>",""],
		23:["","LXK MPS Scheduled Install Date PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.scheduleInstallDate" javaScriptEscape="true"/>",""],
		24:["","LXK MPS Testing PreDebriefRFV","<spring:message code="requestInfo.hardwareDebrief.customerProfile.testing" javaScriptEscape="true"/>",""],
		25:["","LXK MPS Topbill Num PreDebriefRFV","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.topBill" javaScriptEscape="true"/>",""],
		26:["","TrainingChecklist","<spring:message code="requestInfo.hardwareDebrief.customerProfile.trainingChecklist" javaScriptEscape="true"/>",""],
		50:["","Tabloid Mono","<spring:message code="requestInfo.tooltip.tabloidMono" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.tooltip.tabloidMono" javaScriptEscape="true"/>"],
		47:["","Statement Mono","<spring:message code="requestInfo.tooltip.statementMono" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.tooltip.statementMono" javaScriptEscape="true"/>"],
		62:["","Mono","<spring:message code="pageCntUploadLegend.MonoHeader" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="pageCntUploadLegend.MonoHeader" javaScriptEscape="true"/>"],
		30:["","A3 Mono","<spring:message code="requestInfo.tooltip.a3Mono" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.tooltip.a3Mono" javaScriptEscape="true"/>"],
		33:["","A4 Mono","<spring:message code="requestInfo.tooltip.a4Mono" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.tooltip.a4Mono" javaScriptEscape="true"/>"],
		36:["","A5 Mono","<spring:message code="requestInfo.tooltip.a5Mono" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.tooltip.a5Mono" javaScriptEscape="true"/>"],
		61:["","Legal Mono","<spring:message code="requestInfo.tooltip.legalMono" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.tooltip.legalMono" javaScriptEscape="true"/>"],
		51:["","Letter Mono","<spring:message code="requestInfo.tooltip.letterMono" javaScriptEscape="true"/>","<spring:message code="requestInfo.hardwareDebrief.enterValueFor" javaScriptEscape="true"/> <spring:message code="requestInfo.tooltip.letterMono" javaScriptEscape="true"/>"]


};
	 </script>