<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.xhdr {
       position: static!important;
}
.objbox {
       position: static!important;
}
.move_type1{padding-left:0px!important;}
</style>
  <div id="content-wrapper">
    <div class="journal-content-article">
		<h1>
			<spring:message
				code="requestInfo.cm.heading.changeRequest" />
		</h1>
		<h2 class="step"><spring:message
				code="requestInfo.cm.manageAsset.heading.assets" /></h2>
	</div>

<!-- Added for CI7 BRD14-02-12 -->
	<div id="accNameAgreeName"><h3><strong>
	<%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
	</strong></h3></div>
	<!-- END -->
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
<h3 class="pageTitle">
				<spring:message
					code="requestInfo.heading.requestConfirmation" />
			</h3>
  <div class="utilities printPage floatR">
				<ul>
					<li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png"
							 class="ui_icon_sprite email-icon" alt="Email this page"
							></a></li>
					<li><a href="javascript:print();"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon"
							alt="Print this page" ></a></li>
				</ul>
			</div>
			<div id="emailPrintTop">
 	<c:if test="${error ne null && error != ''}">
		  <p class="info banner err"><span>${error}</span></p>
		  </c:if>
		  <c:if test="${srNumber != null && srNumber != ''}">
		  <p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/>
		  <span id="reqNumber">${srNumber}</span>	
		  <spring:message code="requestInfo.message.confirmMessage2"/></p>
		  </c:if>
<div class="portletBlock">
  <div class="columnsTwo">
 
    <div class="infoBox columnInner rounded shadow">
      <h4>
							<spring:message
								code="requestInfo.heading.primaryContactForThisRequest" />
						</h4>
    				 <c:choose>

							<%--<c:when test='${manageAssetForm.typeOfRequest eq "Add_Asset"}'>--%>
							<c:when test='${typeOfRequest eq "Add_Asset"}'>
								<ul class="form">
									<li><label><spring:message
												code="requestInfo.label.name" /></label> 
												<span id="firstLastName1_add">${manageAssetForm.serviceRequest.primaryContact.firstName}
											&nbsp;
											${manageAssetForm.serviceRequest.primaryContact.lastName}</span></li>
									<li><label><spring:message
												code="requestInfo.label.phoneNumber" /></label> <span id="phoneNo1_add">${manageAssetForm.serviceRequest.primaryContact.workPhone}</span></li>
									<li><label><spring:message
												code="requestInfo.label.emailAddress"/></label>
										<span id="email1_add">${manageAssetForm.serviceRequest.primaryContact.emailAddress}</span>
									</li>
								</ul>
							</c:when>
							<c:when
								test='${typeOfRequest eq "Change_Asset"}'>
								<ul class="form">
									<li><label><spring:message
												code="requestInfo.label.name" /></label> <span id="firstLastName1_change">${manageAssetFormForChange.serviceRequest.primaryContact.firstName}
											&nbsp;
											${manageAssetFormForChange.serviceRequest.primaryContact.lastName}</span></li>
									<li><label><spring:message
												code="requestInfo.label.phoneNumber" /></label> <span id="phoneNo1_change">${manageAssetFormForChange.serviceRequest.primaryContact.workPhone}</span></li>
									<li><label><spring:message
												code="requestInfo.label.emailAddress" /></label>
										<span id="email1_change">${manageAssetFormForChange.serviceRequest.primaryContact.emailAddress}</span></li>
								</ul>
							</c:when>
							<c:when
								test='${typeOfRequest eq "Decomm_Asset"}'>
								<ul class="form">
									<li><label><spring:message
												code="requestInfo.label.name" /></label> <span id="firstLastName1_del">${manageAssetFormForDecommission.serviceRequest.primaryContact.firstName}
											&nbsp;
											${manageAssetFormForDecommission.serviceRequest.primaryContact.lastName}</span></li>
									<li><label><spring:message
												code="requestInfo.label.phoneNumber" /></label> <span id="phoneNo1_del">${manageAssetFormForDecommission.serviceRequest.primaryContact.workPhone}</span></li>
									<li><label><spring:message
												code="requestInfo.label.emailAddress" /></label>
										<span id="email1_del">${manageAssetFormForDecommission.serviceRequest.primaryContact.emailAddress}</span></li>
								</ul>
							</c:when>
							</c:choose>
    </div>
    <div class="infoBox columnInner rounded shadow" id="addiContact">
      <h4>
							<spring:message
								code="requestInfo.heading.secondaryContactForThisRequest" />
						</h4>

     				 <c:choose>
							<%--<c:when test='${manageAssetForm.typeOfRequest eq "Add_Asset"}'>--%>
							<c:when test='${typeOfRequest eq "Add_Asset"}'>
								<ul class="form">
									<li><label><spring:message
												code="requestInfo.label.name" /></label > <span id="firstLastName2_add">${manageAssetForm.serviceRequest.secondaryContact.firstName}
											&nbsp;
											${manageAssetForm.serviceRequest.secondaryContact.lastName}</span></li>
									<li><label><spring:message
												code="requestInfo.label.phoneNumber" /></label> <span id="phoneNo2_add">${manageAssetForm.serviceRequest.secondaryContact.workPhone}</span></li>
									<li><label><spring:message
												code="requestInfo.label.emailAddress" /></label>
										<span id="email2_add">${manageAssetForm.serviceRequest.secondaryContact.emailAddress}</span></li>
								</ul>

							</c:when>
							<c:when
								test='${typeOfRequest eq "Change_Asset"}'>
								<ul class="form">
									<li><label><spring:message
												code="requestInfo.label.name" /></label> <span id="firstLastName2_change">${manageAssetFormForChange.serviceRequest.secondaryContact.firstName}
											&nbsp;
											${manageAssetFormForChange.serviceRequest.secondaryContact.lastName}</span></li>
									<li><label><spring:message
												code="requestInfo.label.phoneNumber" /></label> <span id="phoneNo2_change">${manageAssetFormForChange.serviceRequest.secondaryContact.workPhone}</span></li>
									<li><label><spring:message
												code="requestInfo.label.emailAddress" /></label>
										<span id="email2_change">${manageAssetFormForChange.serviceRequest.secondaryContact.emailAddress}</span></li>
								</ul>

							</c:when>
							<c:when
								test='${typeOfRequest eq "Decomm_Asset"}'>
								<ul class="form">
									<li><label><spring:message
												code="requestInfo.label.name" /></label> <span id="firstLastName2_del">${manageAssetFormForDecommission.serviceRequest.secondaryContact.firstName}
											&nbsp;
											${manageAssetFormForDecommission.serviceRequest.secondaryContact.lastName}</span></li>
									<li><label><spring:message
												code="requestInfo.label.phoneNumber" /></label> <span id="phoneNo2_del">${manageAssetFormForDecommission.serviceRequest.secondaryContact.workPhone}</span></li>
									<li><label><spring:message
												code="requestInfo.label.emailAddress" /></label>
										<span id="email2_del">${manageAssetFormForDecommission.serviceRequest.secondaryContact.emailAddress}</span></li>
								</ul>

							</c:when>
							</c:choose>
    </div>
  </div>
  <div class="columnsTwo">
    <div class="infoBox columnInner rounded shadow">
      <h4>
							<spring:message
								code="requestInfo.heading.additionalInformationForThisRequest" />
						</h4>
      <ul class="form">
        <li>
          <label><spring:message
										code="requestInfo.label.customerRefereceId" /></label>
          <span><c:choose>
									
										<c:when test='${typeOfRequest eq "Add_Asset"}'>
                   <span id="customerReferenceId_add"> ${manageAssetForm.serviceRequest.customerReferenceId}</span>
                    </c:when>
                    <c:when	test='${typeOfRequest eq "Change_Asset"}'>
                   <span id="customerReferenceId_change"> ${manageAssetFormForChange.serviceRequest.customerReferenceId}</span>
                    </c:when>
                    <c:when test='${typeOfRequest eq "Decomm_Asset"}'>
                    <span id="customerReferenceId_del">${manageAssetFormForDecommission.serviceRequest.customerReferenceId}</span>
                    </c:when>
                    </c:choose></span></li>
        <li>
          <label><spring:message code="requestInfo.label.costCentre" /></label>
          <span><c:choose>
          	<c:when test='${typeOfRequest eq "Add_Asset"}'>
                    <span id="costCenter_add">${manageAssetForm.serviceRequest.costCenter}</span>
                    </c:when>
                    <c:when test='${typeOfRequest eq "Change_Asset"}'>
                    <span id="costCenter_change">${manageAssetFormForChange.serviceRequest.costCenter}</span>
                    </c:when>
                    <c:when	test='${typeOfRequest eq "Decomm_Asset"}'>
                   <span id="costCenter_del"> ${manageAssetFormForDecommission.serviceRequest.costCenter}</span>
                    </c:when>
                    </c:choose>
                    </span></li>
        <li>
          <label><spring:message code="requestInfo.label.description" /></label>
          <span class="multiLine"><c:choose>
						<c:when test='${typeOfRequest eq "Add_Asset"}'>
                   <span id="addtnlDescription_add" style="word-wrap: break-word;word-break: normal;width:250px"> ${manageAssetForm.serviceRequest.addtnlDescription}</span>
                    </c:when>
                    <c:when test='${typeOfRequest eq "Change_Asset"}'>
                    <span id="addtnlDescription_change" style="word-wrap: break-word;word-break: normal;width:250px">${manageAssetFormForChange.serviceRequest.addtnlDescription}</span>
                    </c:when>
                    <c:when test='${typeOfRequest eq "Decomm_Asset"}'>
                    <span id="addtnlDescription_del" style="word-wrap: break-word;word-break: normal;width:250px">${manageAssetFormForDecommission.serviceRequest.addtnlDescription}</span>
                    </c:when>
                    </c:choose></span></li>
        <li>
          <label><spring:message code="requestInfo.label.dateOfChange" /></label>
          <span><c:choose>
										
										<c:when test='${typeOfRequest eq "Add_Asset"}'>
											<span id="eddate_add"><util:dateFormat value="${manageAssetForm.serviceRequest.requestedEffectiveDate}">
											</util:dateFormat></span>
										</c:when>
										<c:when test='${typeOfRequest eq "Change_Asset"}'>
											<span id="eddate_change"><util:dateFormat
												value="${manageAssetFormForChange.serviceRequest.requestedEffectiveDate}"></util:dateFormat></span>
										</c:when>
										<c:when test='${typeOfRequest eq "Decomm_Asset"}'>
											<span id="eddate_del"> <util:dateFormat
												value="${manageAssetFormForDecommission.serviceRequest.requestedEffectiveDate}">
											</util:dateFormat></span>
										</c:when>
										</c:choose></span></li>
      </ul>
    </div>
  </div>
</div>
<hr class="separator" />

 <!-- ASSET INFO BLOCK START -->
        <div class="portletBlock">
<div class="columnsTwo">
  <div class="infoBox rounded shadow"> 
    <!-- Outter table: 1 row & 2 colums -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
      <tr> 
        <!-- Outter Table > column 1: Product image & Name -->
        <c:choose>
        
        <c:when test='${typeOfRequest eq "Change_Asset"}'>
								<td class="pModel">
									<ul>
												<li class="center"><img
													src="${manageAssetFormForChange.assetDetail.productImageURL}" width="100"
													height="100" onError="image_error(this.id);" id="changeImage"></img></li>
												<li class="pModelName">
												<span id="productTLIold_change"><%-- ${manageAssetFormForChange.oldAssetInfo.productTLI} --%>
												${manageAssetFormForChange.assetDetail.descriptionLocalLang}
												</span></li>
									</ul>
								</td>
          </c:when>
          <c:when test='${typeOfRequest eq "Decomm_Asset"}'>
								<td class="pModel">
									<ul>
												<li class="center"><img
													src="${manageAssetFormForDecommission.assetDetail.productImageURL}" width="100"
													height="100" id="delImage" onError="image_error(this.id);"></img></li>
												<li class="pModelName">
												<span id="productTLIold_del"><%-- ${manageAssetFormForDecommission.assetDetail.productTLI} --%>
												${manageAssetFormForDecommission.assetDetail.descriptionLocalLang}
												</span></li>
									</ul>
								</td>
					</c:when>
          </c:choose>
        <!-- Outter Table > Column 2: Inner table for details -->
        <td class="pDetail"><!-- Inner table: 4 row2 & 2 colums -->
          
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="h4"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails" /></td>
            </tr>
            <tr>
              <td class="infoDsiplay"><ul class="form">
                  <li>
                    <label><spring:message	code="requestInfo.assetInfo.label.serialNumber" /></label>
                    <span><c:choose>
                    <c:when test='${typeOfRequest eq "Add_Asset"}'>
													  <span id="serialNumber_add"> ${manageAssetForm.assetDetail.serialNumber}</span>
													   </c:when>
													    <c:when test='${typeOfRequest eq "Change_Asset"}'>
					 <span id="serialNumber_change"> ${manageAssetFormForChange.assetDetail.serialNumber}</span>
							   </c:when>
				 <c:when test='${typeOfRequest eq "Decomm_Asset"}'>
					 <span id="serialNumber_del"> ${manageAssetFormForDecommission.assetDetail.serialNumber}</span>
						 </c:when>
														</c:choose></span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.productName" /></label>
                    <span><c:choose><c:when test='${typeOfRequest eq "Add_Asset"}'>
													   <span id="productLine_add">${manageAssetForm.assetDetail.productLine}</span>
													   </c:when>
									<c:when test='${typeOfRequest eq "Change_Asset"}'>
									   <span id="productLine_change">${manageAssetFormForChange.assetDetail.productLine}</span>
											   </c:when>
									  <c:when test='${typeOfRequest eq "Decomm_Asset"}'>
										   <span id="productLine_del">${manageAssetFormForDecommission.assetDetail.productLine}</span>
									  </c:when>
														</c:choose></span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.installDate" /></label>
                    <span><c:choose><c:when test='${typeOfRequest eq "Add_Asset"}'>
									<span id="installDate_add"><util:dateFormat value="${manageAssetForm.assetDetail.installDate}"></util:dateFormat></span>
													   </c:when>
						<c:when test='${typeOfRequest eq "Change_Asset"}'>
							<span id="installDate_change"><util:dateFormat value="${manageAssetFormForChange.assetDetail.installDate}"></util:dateFormat></span>
													   </c:when>
						<c:when test='${typeOfRequest eq "Decomm_Asset"}'>
							<span id="installDate_del"><util:dateFormat value="${manageAssetFormForDecommission.assetDetail.installDate}"></util:dateFormat></span>
													   </c:when>
														</c:choose></span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.ipAddress" /></label>
                    <span><c:choose><c:when test='${typeOfRequest eq "Add_Asset"}'>
														<%--<span id="ipAddress_add">${manageAssetForm.assetDetail.ipAddress}</span> --%>
															<%--Start: Added for Defect 3924-Jan Release--%>
											                    <span id="ipAddress_add"><a href="javascript:gotoControlPanel('${manageAssetForm.assetDetail.controlPanelURL}')">${manageAssetForm.assetDetail.ipAddress}</a></span>
											                <%--End: Added for Defect 3924-Jan Release--%>
													   </c:when>
						<c:when test='${typeOfRequest eq "Change_Asset"}'>
									<%--<span id="ipAddress_add">${manageAssetForm.assetDetail.ipAddress}</span> --%>
								<%--Start: Added for Defect 3924-Jan Release--%>
						               <span id="ipAddress_change"><a href="javascript:gotoControlPanel('${manageAssetFormForChange.assetDetail.controlPanelURL}')">${manageAssetFormForChange.newAssetInfo.ipAddress}</a></span>
							     <%--End: Added for Defect 3924-Jan Release--%>
													   </c:when>
							<c:when test='${typeOfRequest eq "Decomm_Asset"}'>
									<%--<span id="ipAddress_add">${manageAssetForm.assetDetail.ipAddress}</span> --%>
								<%--Start: Added for Defect 3924-Jan Release--%>
						               <span id="ipAddress_del"><a href="javascript:gotoControlPanel('${manageAssetFormForDecommission.assetDetail.controlPanelURL}')">${manageAssetFormForDecommission.assetDetail.ipAddress}</a></span>
							     <%--End: Added for Defect 3924-Jan Release--%>
													   </c:when>						   
														</c:choose></span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.hostName" /></label>
                    <span><c:choose><c:when test='${typeOfRequest eq "Add_Asset"}'>
														<span id="hostName_add">${manageAssetForm.assetDetail.hostName}</span>
													   </c:when>
								<c:when test='${typeOfRequest eq "Change_Asset"}'>
								<span id="hostName_change">${manageAssetFormForChange.newAssetInfo.hostName}</span>
													   </c:when>
								<c:when test='${typeOfRequest eq "Decomm_Asset"}'>
								<span id="hostName_del">${manageAssetFormForDecommission.assetDetail.hostName}</span>
													   </c:when>
														</c:choose></span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag" /></label>
                    <span><c:choose><c:when test='${typeOfRequest eq "Add_Asset"}'>
													   <span id="deviceTag_add">${manageAssetForm.assetDetail.deviceTag}</span>
													   </c:when>
							<c:when test='${typeOfRequest eq "Change_Asset"}'>
													   <span id="deviceTag_change">${manageAssetFormForChange.newAssetInfo.deviceTag}</span>
													   </c:when>
							<c:when test='${typeOfRequest eq "Decomm_Asset"}'>
													   <span id="deviceTag_del">${manageAssetFormForDecommission.assetDetail.deviceTag}</span>
													   </c:when>
														</c:choose></span></li>
                </ul></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>

<c:if test='${typeOfRequest ne "Decomm_Asset"}'>
  <div class="columnsTwo">
    <div class="infoBox columnInner rounded shadow">
      <h4><spring:message code="requestInfo.cm.manageAsset.heading.billingDetails" /></h4>
      <ul class="form">
        <li>
          <label for="assetCost"><spring:message code="requestInfo.assetInfo.label.assetCostCenter" /></label>
          <span id="assetCostCenter_add"><c:choose>
          <c:when test='${typeOfRequest eq "Add_Asset"}'>
						<span id="assetCostCenter_add">${manageAssetForm.assetDetail.assetCostCenter}</span>
						</c:when>
		<c:when test='${typeOfRequest eq "Change_Asset"}'>
						<span id="assetCostCenter_change">${manageAssetFormForChange.newAssetInfo.assetCostCenter}</span>
						</c:when>
						</c:choose></span></li>
      </ul>
    </div>
  </div>
  <div class="columnsTwo">
    <div class="infoBox columnInner rounded shadow">
      <h4><spring:message code="requestInfo.heading.customerHierarchy" /></h4>
      <ul class="form">
        <li>
          <label> <spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel" /></label>
          <span id="chlNodeValue_add">	<c:choose>
         		 <c:when test='${typeOfRequest eq "Add_Asset"}'>
						<span id="chlNodeValue_add">${manageAssetForm.assetDetail.chlNodeValue}</span>
						</c:when>
						<c:when test='${typeOfRequest eq "Change_Asset"}'>
						<span id="chlNodeValue_change">${manageAssetFormForChange.newAssetInfo.chlNodeValue}</span>
						</c:when>
						</c:choose></span></li>
      </ul>
    </div>
  </div>
  </c:if>
</div>
        <!-- ASSET INFO BLOCK END -->
</div>
		

	<div class="columnsOne" id="emailPageCounts">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount" /></h4>
              <div  class="collapse" id="statusExpand">
                <div id="div_pageCounts">
					<div id="tab_pageCounts" style="display:block;">
						<div id="pageCounts_container" style="background-color: white;width:100%;"></div>
	    				<div class="pagination"><span id="div_pageCountsPagingArea"></span>
	    				<span id="div_pageCountsinfoArea"></span></div>
					</div>
				</div>
              </div>
            </div>
          </div>
          
          
          <c:choose>
  <c:when test='${typeOfRequest eq "Add_Asset"}'>
   <!-- Project fields -->
            
            
            
           <c:if test="${manageAssetForm.serviceRequest.project eq 'true'}">
            <div class="infoBox columnInner rounded shadow">
          
             <h4><spring:message code="ops.heading.project"/></h4>
            
            <ul class="roDisplay">
                  <c:if test="${manageAssetForm.serviceRequest.projectName ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectName"/></label>
                    <span id="projectName">${manageAssetForm.serviceRequest.projectName} </span></li>
                  </c:if>
                  <c:if test="${manageAssetForm.serviceRequest.projectPhase ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectPhase"/></label>
                    <span id="projectPhase">${manageAssetForm.serviceRequest.projectPhase}</span></li>
                   </c:if>
                   </ul>
            
            </div>
            
            
          </c:if>  
           
            </c:when>
            <c:when test='${typeOfRequest eq "Change_Asset"}'>
             <!-- Project fields -->
            
         
            <c:if test="${manageAssetFormForChange.serviceRequest.project eq 'true'}">
            <div class="infoBox columnInner rounded shadow">
            
            <h4><spring:message code="ops.heading.project"/></h4>
            <ul class="roDisplay">
                  <c:if test="${manageAssetFormForChange.serviceRequest.projectName ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectName"/></label>
                    <span id="projectName">${manageAssetFormForChange.serviceRequest.projectName} </span></li>
                  </c:if>
                  <c:if test="${manageAssetFormForChange.serviceRequest.projectPhase ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectPhase"/></label>
                    <span id="projectPhase">${manageAssetFormForChange.serviceRequest.projectPhase}</span></li>
                   </c:if>
                   </ul>
            
            </div>
			</c:if>
            
            
            </c:when>
            <c:when	test='${typeOfRequest eq "Decomm_Asset"}'>
            
         
           <c:if test="${manageAssetFormForDecommission.serviceRequest.project eq 'true'}">
            <div class="infoBox columnInner rounded shadow">
            
            <h4><spring:message code="ops.heading.project"/></h4>
            <ul class="roDisplay">
                  <c:if test="${manageAssetFormForDecommission.serviceRequest.projectName ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectName"/></label>
                    <span id="projectName">${manageAssetFormForDecommission.serviceRequest.projectName} </span></li>
                  </c:if>
                  <c:if test="${manageAssetFormForDecommission.serviceRequest.projectPhase ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectPhase"/></label>
                    <span id="projectPhase">${manageAssetFormForDecommission.serviceRequest.projectPhase}</span></li>
                   </c:if>
                   </ul>
            
            </div>
            </c:if>
            
            </c:when>
            </c:choose>
          
          	
<div id="emailPrintBottom">
<div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner">
              <ul class="form installCheck">
					  <c:choose>
         		 <c:when test='${typeOfRequest eq "Add_Asset"}'>
					  <li>
                        <label for="installAsset"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDevice"/></label>
						<span class="radio_confirm" id="installAssetFlag_add">${manageAssetForm.installAssetFlag}</span>
                      </li>
                      </c:when>                      
                      <c:when test='${typeOfRequest eq "Decomm_Asset"}'>
					  <li><label><spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAsset" />
								</label><span class="radio_confirm" id="decommAssetFlag_del"> ${manageAssetFormForDecommission.decommAssetFlag}</span></li>
                      </c:when>
                      </c:choose>
					</ul>
            </div>
          </div>
        </div>

<c:choose>
	<c:when test="${typeOfRequest eq 'Add_Asset'}">
	<div class="infoBox columnInner rounded shadow">
	<h4>Install Information</h4>
	</c:when>
	<c:when test="${typeOfRequest eq 'Change_Asset'}">
	<div class="infoBox columnInner rounded shadow">
	<h4>Move Information</h4>
	<div class="portletBlock">
	<ul class=" installCheck">
	<li style="list-style-type:none;" style="width: 90%">
						<span style="font-weight:bold;"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToPhysicallyMoveThisDevice" /></span>
                    	<span id="installAssetFlag_change"  class="radio_confirm">${manageAssetFormForChange.installAssetFlag}</span>
                    </li>
                   <%-- Commented for MPS 2.1 --%>
                   <%-- <c:if test="${not empty manageAssetFormForChange.moveType}">
                     
                      <li class="move_type1" style="list-style-type:none;">
						<div id="move_type" class="move_type_confirmation" style="width:auto;float:left;font-weight:bold;"><spring:message code="requestInfo.cm.manageAsset.label.moveTypeConfirm"/></div>
						<div id="installAssetFlag_change_value" class="" style="width:auto;float:left;margin-left:40px;">${manageAssetFormForChange.moveType}</div> Changed for CI7 Defect 7853 
                      	<div style="clear:both"></div>
                      </li> 
                      </c:if>--%>
                </ul>
      </div>
	</c:when>
	<c:when test="${typeOfRequest eq 'Decomm_Asset' && manageAssetFormForDecommission.decommAssetFlag ne 'no'}">
	<div class="infoBox columnInner rounded shadow">
	<h4><spring:message code="requestInfo.cm.manageAsset.heading.pickupInformation"/></h4>
	</c:when>
	</c:choose>
	<div class="portletBlock">
	<c:if test="${typeOfRequest eq 'Add_Asset' || typeOfRequest eq 'Change_Asset' || typeOfRequest eq 'Decomm_Asset' && manageAssetFormForDecommission.decommAssetFlag ne 'no' }">
	<div class="columnsTwo">
    <div class="infoBox columnInner rounded shadow">
    <c:choose>
	<c:when test="${typeOfRequest eq 'Add_Asset'}">
	<h4></><spring:message code="requestInfo.heading.installAddress"/></h4>
	</c:when>
	<c:when test="${typeOfRequest eq 'Change_Asset'}">
	<h4><spring:message code="requestInfo.heading.currentInstallAdd"/></h4>
	</c:when>
	<c:when test="${typeOfRequest eq 'Decomm_Asset' && manageAssetFormForDecommission.decommAssetFlag ne 'no'}">
	<h4><spring:message code="requestInfo.cm.manageAsset.heading.pickupAddress"/></h4>
	</c:when>
	</c:choose>
      <c:if test="${typeOfRequest eq 'Add_Asset' || typeOfRequest eq 'Change_Asset' || typeOfRequest eq 'Decomm_Asset' && manageAssetFormForDecommission.decommAssetFlag ne 'no' }">
     <ul class="roDisplay">
                      <%--Changed for LBS --%>
                  
                      <c:choose>
                       <c:when test="${manageAssetForm.assetDetail.installAddress.lbsAddressFlag eq 'null' }">
                      <li id="installAddressAdd">
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span id="building_add">${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span id="floor_add">${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span id="office_add">${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      
                      </c:when>
                      <c:when test="${manageAssetForm.assetDetail.installAddress.lbsAddressFlag eq 'true' }">
                      
                      <li id="installAddressAdd">
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span id="building_add">${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span id="floor_add">${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					 <li><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span>${manageAssetForm.assetDetail.installAddress.zoneName}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span id="office_add">${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      
                      <c:if test="${manageAssetForm.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'grid level' 
                                                  ||  (manageAssetForm.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'mix - see floor' 
                                                     &&  manageAssetForm.assetDetail.installAddress.floorLevelOfDetails.toLowerCase() eq 'grid level')}">
                      	
                      	<li id="gridLiAdd">
							<div id="installedXYLblDiv">
							<label id="installedXYLbl">Grid X/Y : </label><label id="installedCoords"></label>
							</div>
						</li>
						
						</c:if>
						</c:when>
                       <c:when test="${manageAssetForm.assetDetail.installAddress.lbsAddressFlag ne 'true' }">
                      <li id="installAddressAdd">
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span id="building_add">${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span id="floor_add">${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span id="office_add">${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      </c:choose>
                      
					 </ul>
					 </c:if>		 
    </div>
  </div>
 </c:if>
 
  <c:choose>
        <c:when test='${typeOfRequest eq "Change_Asset"}'>
  <div class="columnsTwo" id="hideMoveToAddress">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.moveToAddr"/></h4>
              <c:choose><%--Changed for LBS --%>
                     <c:when test="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag eq 'null' }"> 
              <ul class="roDisplay">
                      <li id="moveToAddress"><div>${manageAssetFormForChange.assetDetail.moveToAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForChange.assetDetail.moveToAddress}"></util:addressOutput>
                      
                      
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span id="building_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span id="floor_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation2}</span>
                      </li>
					
					<%-- <c:if test="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag eq 'true'}">--%>
					 <li><span id="zone_move"><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span >${manageAssetFormForChange.assetDetail.moveToAddress.zoneName}</span></span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span id="office_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation3}</span>
                      </li>
					 </ul>
					 </c:when>
                      <c:when test="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag eq 'true' }">
                  <ul class="roDisplay">
                      <li id="moveToAddress"><div>${manageAssetFormForChange.assetDetail.moveToAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForChange.assetDetail.moveToAddress}"></util:addressOutput>
                      
                      
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span id="building_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span id="floor_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation2}</span>
                      </li>
										
					 <li><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span id="zone_move">${manageAssetFormForChange.assetDetail.moveToAddress.zoneName}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span id="office_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation3}</span>
                      </li>
                      
                      
					 </ul>
                  </c:when>
                   <c:when test="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag ne 'true' }"> 
              <ul class="roDisplay">
                      <li id="moveToAddress"><div>${manageAssetFormForChange.assetDetail.moveToAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForChange.assetDetail.moveToAddress}"></util:addressOutput>
                                            
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span id="building_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span id="floor_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation2}</span>
                      </li>
									
					 <li><span id="zone_move"><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span >${manageAssetFormForChange.assetDetail.moveToAddress.zoneName}</span></span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span id="office_move">${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation3}</span>
                      </li>
					 </ul>
					 </c:when>
					 </c:choose>
					 <ul class="roDisplay">
					 <c:if test="${manageAssetFormForChange.assetDetail.moveToAddress.levelOfDetails.toLowerCase() eq 'grid level' 
                                                  ||  (manageAssetFormForChange.assetDetail.moveToAddress.levelOfDetails.toLowerCase() eq 'mix - see floor' 
                                                     &&  manageAssetFormForChange.assetDetail.moveToAddress.floorLevelOfDetails.toLowerCase() eq 'grid level')}">
                      	<li id="gridLiMove">
							<div id="installedXYLblDiv">
							<label id="installedXYLbl">Grid X/Y : </label><label id="moveToCoords"></label>
							</div>
						</li>
						</c:if>
					 
					 </ul>
            </div>
          </div>
          </c:when>
          </c:choose>
</div>
</div>
		<c:choose>
        <c:when test='${typeOfRequest ne "Decomm_Asset"}'>
		 <jsp:include page="/WEB-INF/jsp/changemanagement/manageAsset/commonAssetContactReview.jsp" />
		 </c:when>
		 </c:choose>
	<p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine" style="word-wrap: break-word;word-break: normal;width:500px"><span id="attachmentDescription">${manageAssetForm.assetDetail.notes}</span></p>
            			</div>
            		</div>
            	</div>
	<div id="div_attachmentListPrint">
	 <c:if test="${not empty displayAttachmentList}">
				<div class="portletBlock infoBox">
					<div class="wHalf displayGrid columnInnerTable rounded shadow" id="attachmentDetailsTable">
						<table id="attachmentDiv">
						<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${displayAttachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
										<td class="w70p">${attachmentListDetail.fileName}</td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.fileSize div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
				</div>
				</c:if>
				<div id="attachmentFailed" class="error" style="display: none; width: 50%">
				</div>
				</div>
</div>

	<div class="filterLinks floatR" style="width: 100%;">
		 
        <%--  <c:if test='${fleetManagementFlag ne "true"}'> --%>
					<div class="cloumnsTwo" id="backtomap" style="float: left; display: none; width: 12%;">
						<button class="button_cancel" onclick="javascript:backToMapView();"	type="button">Back to Map</button>
					</div>
		<%-- </c:if>--%>
			
					<div style="float: right;">
						<ul>
							<li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon"
									src="<html:rootPath/>images/transparent.png" title="email"
									title="email" height="23" width="27" /> <spring:message
										code="requestInfo.link.emailThisPage" /></a></li>
							<li><a href="javascript:print();"><img class="ui_icon_sprite print-icon"
									src="<html:rootPath/>images/transparent.png" title="print"
									title="print" height="27" width="27" /> <spring:message
										code="requestInfo.tooltip.printThisPage" /></a></li>
						</ul>
					</div>
	</div>

        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>
  
  
 <form action="fleet-management" id="backFormMap" method="post">
 <c:if test="${typeOfRequest eq 'Add_Asset' }">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetForm.backToMap}"/>
	</c:if>
	<c:if test="${typeOfRequest eq 'Change_Asset' }">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForChange.backToMap}"/>
	</c:if>
	<c:if test="${typeOfRequest eq 'Decomm_Asset'  }">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForDecommission.backToMap}"/>
	</c:if>
</form>
  
  <script type="text/javascript">
  pageCountsGrid = new dhtmlXGridObject('pageCounts_container');
	pageCountsGrid.setImagePath("<html:imagesPath/>gridImgs/");
	pageCountsGrid.setHeader("<spring:message code='requestInfo.heading.commonPageCounts'/>");
	pageCountsGrid.setColAlign("left,left,left");
	pageCountsGrid.setColTypes("ro,ro,ro,ro");
	pageCountsGrid.setInitWidths("160,200,180,0");
	pageCountsGrid.enableAutoWidth(true);
	pageCountsGrid.enableMultiline(true);
	pageCountsGrid.enableAutoHeight(true);
	pageCountsGrid.init(); 
	pageCountsGrid.prftInit();
	pageCountsGrid.enablePaging(true, 5, 10, "div_pageCountsPagingArea", true);
	pageCountsGrid.setPagingSkin("bricks");
	pageCountsGrid.setSkin("light");
	//alert('${requestForm.srStausXML}');
	pageCountsGrid.loadXMLString('${pageCountsString}');
  jQuery(document).ready(function() {
  
  var xCordInstall='${manageAssetForm.assetDetail.installAddress.coordinatesXPreDebriefRFV}';
  var yCordInstall='${manageAssetForm.assetDetail.installAddress.coordinatesYPreDebriefRFV}';
  
  var xCordMove='${manageAssetFormForChange.assetDetail.moveToAddress.coordinatesXPreDebriefRFV}';
  var yCordMove='${manageAssetFormForChange.assetDetail.moveToAddress.coordinatesYPreDebriefRFV}';

  coordinates(xCordInstall,yCordInstall,"installed");
  coordinates(xCordMove,yCordMove,"moveTo");
  
  
	  var installFlag ="${manageAssetFormForChange.installAssetFlag}";
	  var currentURL = window.location.href;

		if(currentURL.indexOf('/fleet-management') > -1){
			var typeOfReq="${typeOfRequest}";
			if(typeOfReq =='Add_Asset')
				{
				jQuery('#gridLiAdd').show();
				}
			else
				{
				jQuery('#gridLiAdd').hide();
				}
			
			}
		else
			{
			jQuery('#gridLiAdd').show();
			}
		if(installFlag=='No'||installFlag==''||installFlag==null)
		{
			//jQuery('#hideMoveToAddress').hide();
			var flowPage="${pageFlow}";
			//jQuery("#move_type").hide();
			//alert("flowPage"+flowPage);
			if(flowPage=="fleetMgmtMove"){
				jQuery('#hideMoveToAddress').show();
				jQuery('#gridLiAdd').hide();
				
			}
			else{
			jQuery('#hideMoveToAddress').hide();
			}
		}
		else if(installFlag=='Yes')
		{
			jQuery('#hideMoveToAddress').show();
		}
	  var typeOfReq="${typeOfRequest}";
		
		if(typeOfReq=="Add_Asset")
		{
			if ("${manageAssetForm.serviceRequest.secondaryContact.firstName}"=="" || "${manageAssetForm.serviceRequest.secondaryContact.firstName}"==null) 
			{
				  jQuery("#addiContact").hide();	
			}
			textScroll(document.getElementById('addtnlDescription_add'));
			textScroll(document.getElementById('costCenter_add'));
			if("${attachmentException}"=="attachfailed"){
				jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
				 jQuery("#attachmentFailed").show();					
				}
		}
		if(typeOfReq=="Change_Asset")
		{
			if ("${manageAssetFormForChange.serviceRequest.secondaryContact.firstName}"=="" || "${manageAssetFormForChange.serviceRequest.secondaryContact.firstName}"==null) 
			{	
				  jQuery("#addiContact").hide();	
			}

			textScroll(document.getElementById('addtnlDescription_change'));
			textScroll(document.getElementById('costCenter_change'));

			if("${attachmentException}"=="attachfailed"){
				jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
				 jQuery("#attachmentFailed").show();					
				}
			if("${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag}" =="true"){
				jQuery('#zone_move').show();
			}else{
				
				if(currentURL.indexOf('/fleet-management') == -1){
					//alert("zone hide");
					jQuery('#zone_move').hide();
					}
				else{
					//alert("zone show");
					jQuery('#zone_move').show();
				}
			}
		}
		
		if(typeOfReq=="Decomm_Asset")
		{
			if ("${manageAssetFormForDecommission.serviceRequest.secondaryContact.firstName}"=="" || "${manageAssetFormForDecommission.serviceRequest.secondaryContact.firstName}"==null) 
			{
				
				  jQuery("#addiContact").hide();
			}

			textScroll(document.getElementById('addtnlDescription_del'));
			textScroll(document.getElementById('costCenter_del'));

			if("${attachmentException}"=="attachfailed"){
				jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
				 jQuery("#attachmentFailed").show();					
				}
		}
		
		if(!(currentURL.indexOf('/fleet-management') == -1)){
			
			jQuery('#backtomap').show();
			}
		
  });
  function print() {
		
		
		//callOmnitureAction('Asset', 'Asset Summary');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
			"<portlet:param name='action' value='printAsset' />"+
			"</portlet:renderURL>";

	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};
	function email() {
		
			
			url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
				"<portlet:param name='action' value='AssetEmail' />"+
				"</portlet:renderURL>" + "&typeOfRequest=" + "${typeOfRequest}";
			
	 	//alert(typeOfReq);
	 	//alert(url);
	 		
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};	
	image_error= function () {
		if(document.getElementById("delImage") != null){
		  document.getElementById("delImage").src = '<html:imagesPath/>dummy_noimg.jpg'; 
		}
		if(document.getElementById("changeImage") != null){
			document.getElementById("changeImage").src = '<html:imagesPath/>dummy_noimg.jpg'; 
			}
		  
		};
		function backToMapView(){
			showOverlay();
			var typeOfReq="${typeOfRequest}";
			if(typeOfReq=="Add_Asset")
			{
				//var lat=49.475075016409235;
				//var lon=1.112987995147705;
				var lat="${manageAssetForm.assetDetail.installAddress.lbsLatitude}";
				
				var lon="${manageAssetForm.assetDetail.installAddress.lbsLongitude}";
				if("${manageAssetForm.backToMap}"== ""){
					var defaultArea={											
				                lat: lat,
				                lng: lon
							};

					$('#backJson').val(JSON.stringify(defaultArea));
					}
			}
			if(typeOfReq=="Change_Asset"&&"${manageAssetFormForChange.backToMap}" == ""){
				
				var lat="${manageAssetFormForChange.assetDetail.installAddress.lbsLatitude}";
				//var lat=49.475075016409235;
				//var lon=1.112987995147705;
				var lon="${manageAssetFormForChange.assetDetail.installAddress.lbsLongitude}";
				var defaultArea={											
		                lat: lat,
		                lng: lon,							                		           
		               

					
					};
				$('#backJson').val(JSON.stringify(defaultArea));
				
			}
			if(typeOfReq=="Decomm_Asset"){
				
				var lat="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLatitude}";
				
				var lon="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLongitude}";
				//var lat=49.475075016409235;
				//var lon=1.112987995147705;
				var defaultArea={											
		                lat: lat,
		                lng: lon,							                		           
		               

					
					};
				$('#backJson').val(JSON.stringify(defaultArea));
			}
			
			
			
		

$('#backFormMap').submit();
			
			//window.location.href='fleet-management';
		}
		
		function coordinates(xCo,yCo,flag){
	var xCoordinate="";
	var yCoordinate="";
	var seperator="/";
	if(!(xCo && yCo))
	{
			seperator="";
	}
	if(xCo){xCoordinate=xCo;}
	if(yCo){yCoordinate=yCo;}
	if(flag=="moveTo"){
		$('#moveToCoords').html(xCoordinate+seperator+yCoordinate);
		$('#moveToAddresscoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#moveToAddresscoordinatesYPreDebriefRFV').val(yCoordinate);
	}
	else if(flag=="installed"){
		$('#installedCoords').html(xCoordinate+seperator+yCoordinate);
		$('#installcoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#installcoordinatesYPreDebriefRFV').val(yCoordinate);
	}	
}
  </script>