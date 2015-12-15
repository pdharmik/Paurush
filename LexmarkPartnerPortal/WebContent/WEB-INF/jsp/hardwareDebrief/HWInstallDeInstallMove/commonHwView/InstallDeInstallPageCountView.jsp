 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${fn:length(hardwareDebriefForm.activity.serviceRequest.asset.deInstAssetPageCounts)>0}">
 <div class="portletBlock infoBox rounded shadow">      
 <div class="columnsOne">
            <div class="columnInner">
            <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.installDeinstallActivityPageCounts"/></h4>
              <div>
					<table class="displayGrid2 rounded shadow wHalf">
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.pageCounType"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.newPageCount"/></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="pageCounts1" items="${hardwareDebriefForm.activity.serviceRequest.asset.deInstAssetPageCounts}" varStatus="statusPageCounts1">
										<c:choose>
												<c:when test="${statusPageCounts1.count%2==0}">
													<tr class="altRow">
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>
											<td>${pageCounts1.type}</td>
											<td>${pageCounts1.count}</td>

									
									
						</c:forEach>	
							
							
							
							
							
							
						</tbody>
					</table>
            </div>
          </div>
        </div>
        </div>
        </c:if>