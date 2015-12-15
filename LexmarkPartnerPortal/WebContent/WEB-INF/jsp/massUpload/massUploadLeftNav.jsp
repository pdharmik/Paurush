<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="com.lexmark.constants.MassUploadFlags"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
        <div class="left-nav1">
          <div class="left-nav-inner"> 
            
            <%--Download Template button --%>
            <div><p class="inlineTitle p-style1" >
        		<a class="button max-width-180px" onClick="downloadMassUploadTemplate()" ><spring:message code="requestInfo.massUpload.downloadTemplate"/></a>
        		</p>
        	</div>
        	<%--Ends Download Template button --%>
            			
            <div class="left-nav-header inlineTitle">
              <h3><spring:message code="requestInfo.requestHistory.heading.requestType" /></h3>
            </div>
            <ul class="filters radio" id="leftNavLinks">
            
            <%--Following Id's are used in massuploadDefaultview page don't change --%>
            <c:if test="${sessionScope.accountCurrentDetails['massUploadInstallDebriefFlag'] eq true }">
              <li><a class="cursor-pointer" id="<%=MassUploadFlags.MASSUPLOADHARDWARE.getLinkID()%>"><spring:message code="requestInfo.heading.hardwareOrder.hardwareOrders"/></a></li>
            </c:if>
            <c:if test="${sessionScope.accountCurrentDetails['massUploadConsumablesFlag'] eq true }">
              <li><a class="cursor-pointer" id="<%=MassUploadFlags.MASSUPLOADCONSUMABLES.getLinkID()%>"><spring:message code="requestInfo.heading.serviceOrder.serviceOrders"/></a></li>
            </c:if>           
            </ul>
			<%-- Commented for MPS 2.1 as Completed Status wont be shown in grid
            <div class="left-nav-header inlineTitle">
              <h3><spring:message code="requestInfo.heading.serviceOrder.filterByStatus"/></h3>
            </div>
 			--%>
            <ul id="filterByStatus" class="filters radio" style="display:none;">
              <li> <span>
                <input type="radio" id="showAll" name="byStatus" />
                </span>
                <label for="showAll"><spring:message code="request.filter.all"/></label>
              </li>
			  <li> <span>
                <input type="radio" name="byStatus" id="openReq" checked="checked" />
                </span>
                <label for="openReq"><spring:message code="request.filter.open"/></label>
              </li>
              <li> <span>
                <input type="radio" id="closedReq" name="byStatus" />
                </span>
                <label for="closedReq"><spring:message code="request.filter.Closed"/></label>
              </li>
              
            </ul>
            
              <div class="left-nav-header inlineTitle">
              <h3><spring:message code="requestInfo.heading.serviceOrder.filterByDateRange"/> </h3>
            </div>
            
            <ul class="filters">
               <li>
                 <label for="fromDate"><spring:message code="request.filter.from"/></label>
                 <span>
	              	 <input class="positionShift" type="text" name="localizedBeginDate" id="localizedBeginDate" size="0" readOnly="readonly"/>
				   	 <img id="imgLocalizedBeginDate" width="23" height="23" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon positionFit positionShift cursor-pointer" />
	                       	
	             </span>
               </li>
              <li>
                <label for="toDate"><spring:message code="request.filter.to"/></label>
                <span>
               	    <input class="positionShift" type="text" name="localizedEndDate" id="localizedEndDate" size="0" readOnly="readonly"/>
					<img id="imgLocalizedEndDate"  width="23" height="23" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon positionFit positionShift cursor-pointer" />
           
                </span>
                </li>
            </ul>
            <div class="buttonContainerIn">
              <button id="go_btn" class="button"><spring:message code="request.button.go"/></button>
            </div>
          </div>
          <!--<div class="left-nav-footer"> 
             NO CONTENT HERE PLS  
          </div>
        --></div>
        
      