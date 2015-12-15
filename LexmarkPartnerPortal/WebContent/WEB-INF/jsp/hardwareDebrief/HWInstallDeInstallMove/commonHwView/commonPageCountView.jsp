 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${fn:length(hardwareDebriefForm.activity.serviceRequest.asset.pageCounts)>0}">
 <div class="portletBlock infoBox rounded shadow">      
 <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.header"/></h4>
              <div>
					<table class="displayGrid2 rounded shadow wHalf">
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.pageCounType"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.newPageCount"/></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="pageCounts" items="${hardwareDebriefForm.activity.serviceRequest.asset.pageCounts}" varStatus="statusPageCounts">
										<c:choose>
												<c:when test="${statusPageCounts.count%2==0}">
													<tr class="altRow">
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>
											<td>${pageCounts.type}</td>
											<td>${pageCounts.count}</td>

									
									
						</c:forEach>	
							
							
							
							
							
							
						</tbody>
					</table>
            </div>
          </div>
        </div>
        </div>
        </c:if>