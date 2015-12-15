<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Device Contact block start -->
		<div class="columnsOne" id="emailAssetContact">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.contactInfoForDevice"/></h4>
			  <div>
					<table class="displayGrid rounded shadow" id="deviceTable">
						<thead>
						<tr>
							<th class="w20p"><spring:message code="requestInfo.heading.contactType"/></th>
							<th class="w40p"><spring:message code="requestInfo.manageAsset.info.primaryContact"/></th>
							<th class="w40p"><spring:message code="requestInfo.heading.address"/></th>
						</tr>
						</thead>
						
						<tbody>						
						<c:if test="${pageFlow == 'addone'}">
						<c:set var="deviceContactItem" value="${manageAssetForm.assetDetail.deviceContact}" />
						</c:if>
						<c:if test="${pageFlow == 'Change'}">
						<c:set var="deviceContactItem" value="${manageAssetFormForChange.assetDetail.deviceContact}" />
						</c:if>
						
						<c:if test="${pageFlow == 'fleetMgmtChange'}">
						<c:set var="deviceContactItem" value="${manageAssetFormForChange.assetDetail.deviceContact}" />
						</c:if>
						
						<c:if test="${pageFlow == 'fleetMgmtMove'}">
						<c:set var="deviceContactItem" value="${manageAssetFormForChange.assetDetail.deviceContact}" />
						</c:if>
						
						<c:forEach items="${deviceContactItem}" var="deviceContactList" varStatus="counterContact" begin="0">
						<tr class="<c:if test="${counterContact.index % 2 ne 0}">altRow</c:if>">
						
						<c:forEach items="${deviceContactTypeList}" var="deviceContactTypeList">
							<c:if test="${deviceContactTypeList.key == deviceContactList.deviceContactType}">
							<td class="middle"><div id="deviceContactType${counterContact.index+1}">${deviceContactTypeList.value}</div></td>						
							</c:if>
							</c:forEach>
							<td class="middle">
								<div>
								<ul class="form">
									<li style="display:none"> 
										<span id="deviceContactId${counterContact.index+1}">${deviceContactList.contactId}</span>
									</li>
								    <li>
								      <label><spring:message code="requestInfo.label.name"/></label>
								      <span id="deviceFName${counterContact.index+1}">${deviceContactList.firstName}</span>
								      <span id="deviceLName${counterContact.index+1}">${deviceContactList.lastName}</span></li>
								    <li>
								      <label><spring:message code="requestInfo.label.phoneNumber"/></label>
								      <span id="devicePhone${counterContact.index+1}">${deviceContactList.workPhone}</span></li>
								    <li style="display:none"> <span id="deviceAltPhone${counterContact.index+1}">${deviceContactList.alternatePhone}</span></li>
								    <li>
								      <label><spring:message code="requestInfo.label.emailAddress"/></label>
								      <span id="deviceEmail${counterContact.index+1}"></span>${deviceContactList.emailAddress}</li>
							  	</ul>
							  	</div>
							
							</td>
							<td class="middle">								
								<ul class="roDisplay">
								    <li>
									    <div id="deviceAddressId${counterContact.index+1}" style="display:none">${deviceContactList.address.addressId}</div>
									    <div id="deviceStoreFrontName${counterContact.index+1}">${deviceContactList.address.storeFrontName}</div>
										<div id="deviceAddressLine1${counterContact.index+1}">${deviceContactList.address.addressLine1}</div>
										<div id="deviceAddressofficeNumber${counterContact.index+1}">${deviceContactList.address.officeNumber}</div>
										<div id="deviceAddressLine2${counterContact.index+1}">${deviceContactList.address.addressLine2}</div>
										<div id="city_state_zip_popup_span${counterContact.index+1}">
										<span id="deviceAddressCity${counterContact.index+1}">${deviceContactList.address.city}</span>
										<c:if test='${deviceContactList.address.county != null && deviceContactList.address.county!=""}'>
										<span id="deviceAddresscounty${counterContact.index+1}">, ${deviceContactList.address.county}</span>
										</c:if>
										<c:if test='${deviceContactList.address.state != null && deviceContactList.address.state!=""}'>
										<span id="deviceAddressState${counterContact.index+1}">, ${deviceContactList.address.state}</span>
										</c:if>
										<span id="deviceAddressProvince${counterContact.index+1}">${deviceContactList.address.province}</span>
										<span id="deviceAddressDistrict${counterContact.index+1}">${deviceContactList.address.district}</span>
										</div>
										<div id="deviceAddressPostCode${counterContact.index+1}">${deviceContactList.address.postalCode}</div>
										<div id="deviceAddressCountry${counterContact.index+1}">${deviceContactList.address.country}</div>
									</li>
									<li style="display:none">
										<span id="deviceAddressregion${counterContact.index+1}">${deviceContactList.address.region}</span>
										<span id="deviceAddresscountryISOCode${counterContact.index+1}">${deviceContactList.address.countryISOCode}</span>
										<span id="deviceAddressstateCode${counterContact.index+1}">${deviceContactList.address.stateCode}</span>
									</li>
									              	
								    <li>
								      <label for="building"><spring:message code="requestInfo.addressInfo.label.building" /></label>
								      <span id="physicalLocation1${counterContact.index+1}">${deviceContactList.address.physicalLocation1}</span></li>
								    <li>
								      <label for="floor"><spring:message code="requestInfo.addressInfo.label.floor" /></label>
								      <span id="physicalLocation2${counterContact.index+1}">${deviceContactList.address.physicalLocation2}</span></li>
								    <li>
								      <label for="office"><spring:message code="requestInfo.addressInfo.label.office" /></label>
								      <span id="physicalLocation3${counterContact.index+1}">${deviceContactList.address.physicalLocation3}</span></li>
								</ul>								
							</td>
			
						</tr>
						</c:forEach>
						</tbody>
					</table>
            </div>      
        </div>
        </div>
<!-- Device Contact block end -->