<%--This page is common for MOVE and Deinstall PAGE of hardware debried
June RElease  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="display:none;">
	<c:forEach items="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.partList}" var="partItem" varStatus="counter">
		<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].partNumber" value="${partItem.partNumber}"/>
		<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].description" value="${partItem.description}"/>
		<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].orderQuantity" value="${partItem.orderQuantity}"/>
		<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].status" value="used"/>
		<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].orderNumber" value="${partItem.orderNumber}"/>
		
		<input type="hidden" name="activity.serviceRequest.asset.partList[${counter.index}].partNumber" value="${partItem.partNumber}"/>
		<input type="hidden" name="activity.serviceRequest.asset.partList[${counter.index}].description" value="${partItem.description}"/>
		<input type="hidden" name="activity.serviceRequest.asset.partList[${counter.index}].orderQuantity" value="${partItem.orderQuantity}"/>
		<input type="hidden" name="activity.serviceRequest.asset.partList[${counter.index}].orderNumber" value="${partItem.orderNumber}"/>
	</c:forEach>
</div>