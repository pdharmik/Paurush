 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${fn:length(hardwareDebriefForm.activity.activityNoteList)>0}">
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.header"/></h4>
              <div>
					<table class="displayGrid2 rounded shadow wFull">
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.date"/></th>
								<th><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.header"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.author"/></th>
							</tr>
						</thead>
					</table>
					 <div class="scrollTable">
					<table class="displayGrid2 rounded shadow wFull">
						<tbody>
						
						<c:forEach var="activityNote" items="${hardwareDebriefForm.activity.activityNoteList}" varStatus="status">
							
							<c:choose>
									<c:when test="${status.count%2==0}">
													<tr class="altRow">
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
							</c:choose>
								<td class="w100"><util:dateFormat value="${activityNote.noteDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat></td>
								<td>${activityNote.noteDetails }</td>
								<td class="w100">${fn:trim(activityNote.noteAuthor.firstName)}&nbsp;${fn:trim(activityNote.noteAuthor.lastName)}</td>
							
							</tr>
						</c:forEach>
						
						
							
						</tbody>
					</table>
					</div>
				</div>
            </div>
          </div>
        </div>
        </c:if>