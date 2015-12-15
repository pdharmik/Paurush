<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portletBlock infoBox rounded shadow">
	<div class="columnsOne">
        <div class="columnInner">
          <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.header"/></h4>
          <div class="wHalf">
		<table class="displayGrid2 rounded shadown wFull"  >
			<thead>
				<tr>
					<th><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.header"/></th>
					<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.newPageCount"/></th>
					<th></th>
				</tr>
			</thead></table></div>
						<div class="scrollTable wHalf position-relative" >
						<table class="displayGrid2 rounded shadow wFull position-relative" id="pageCountsTable" >
			<tbody>
			<c:choose>
			<c:when test="${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.pageCounts)>0}">
			<c:forEach var="pageCount" items="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.pageCounts}" varStatus="status">
				
					        <c:choose>
									<c:when test="${status.count%2==0}">
													<tr class="altRow" id="pageCountTableRow[${status.index}]">
												</c:when>
												<c:otherwise>
													<tr id="pageCountTableRow[${status.index}]">
												</c:otherwise>
							</c:choose>
				
					<!-- <tr id="pageCountTableRow[${status.index}]"> -->
					
						<td class="width-200px-important"><select class="width-190px-important" name="userEnteredActivity.serviceRequest.asset.pageCounts[${status.index}].type" onchange="validatePageCountsType(this)">
						
						   <option value="">----<spring:message code="generateAssetList.label.select"/>----</option>
							<c:forEach items="${hardwareDebriefForm.pageCountsMap}" var="entry">							    
			                    <c:choose>
		                  			<c:when test="${pageCount.type==entry.key}">
		                  				<option value="${entry.key}" selected="selected">${entry.value}</option>
		                  			</c:when>
		                  			<c:otherwise>
		                  				<option value="${entry.key}" >${entry.value}</option>
		                  			</c:otherwise>
		                  		</c:choose>
							</c:forEach>
							
							</select>
						</td>
						<td><input class="w100" type="text" name="userEnteredActivity.serviceRequest.asset.pageCounts[${status.index }].count" value="${fn:replace(pageCount.count,',', '')}"/></td>
						<td><a class="cursor-pointer" onclick="removePageCountTableRow('pageCountTableRow[${status.index}]')"><img class="ui-icon ui-icon-closethick" src="<html:imagesPath/>transparent.png" alt="Remove" title="Remove"></a></td>
					</tr>
				</c:forEach>
			     </c:when>
				<c:otherwise>
					<tr><td colspan="4"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		</div>
		<div class="width-375px">
			<br/>
			<button id="addnew" type="button" class="button button21 floatR margin-right-0px" onClick="addnewPageCountsRow()"><spring:message code="claim.button.addNew"/></button>
		</div>
		
        </div>

	</div>
</div>
         
       
<div style="display:none;" class="position-relative" id="pageCountTemplateDiv">
	<!-- THIS DIV IS USED TO CREATE A NEW TABLE ROW FOR ADD PAGE COUNT -->
	<table id="defaultTableTemplatePageCount" class="position-relative">
		<tbody>
		<tr class="altRow" id="pageCountTableRow[-1]">
				<td><select class="width-190px-important" name="userEnteredActivity.serviceRequest.asset.pageCounts[-1].type" onchange="validatePageCountsType(this)">
				    <option value="">-----<spring:message code="generateAssetList.label.select"/>----</option>
					<c:forEach items="${hardwareDebriefForm.pageCountsMap}" var="entry">
	                    <option value="${entry.key}">${entry.value}</option>
					</c:forEach>
					</select>
				</td>

				<td><div class="position-relative"><input class="w100" type="text" name="userEnteredActivity.serviceRequest.asset.pageCounts[-1].count"/></div></td>
				<td><a class="cursor-pointer" onclick="removePageCountTableRow('pageCountTableRow[-1]')"><img class="ui-icon ui-icon-closethick" src="<html:imagesPath/>transparent.png" width="16" height="16" alt="Remove" title="Remove"></a></td>
			</tr>
		
			
		</tbody>
	</table>
</div>
 <%--
 		<c:forEach var="pageCount" items="${userEnteredActivity.serviceRequest.asset.pageCounts}" varStatus="status">
					<tr>
						<td><select class="w100" name="userEnteredActivity.serviceRequest.asset.pageCounts[${status.index}].type">
							<c:forEach items="${hardwareDebriefForm.pageCountsMap}" var="entry">
			                    <option value="${entry.key}">${entry.value}</option>
							</c:forEach>
							
							</select>
						</td>
<!--						<td width="180"><input class="w100" type="text" id="userEnteredActivity.serviceRequest.asset.pageCounts[${status.index }].date" name="userEnteredActivity.serviceRequest.asset.pageCounts[${status.index }].date" value="<util:dateFormat value="${pageCount.date}" timezoneOffset="${tzOffset}" showTime="true" showSecond="true"></util:dateFormat>" readonly="readonly"/>-->
<!--						<img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Date" title="Select a Date" onclick="show_cal('userEnteredActivity.serviceRequest.asset.pageCounts[${status.index }].date',null,null)"></td>-->
						<td><input class="w100" type="text" name="userEnteredActivity.serviceRequest.asset.pageCounts[${status.index }].count" value="${pageCount.count}"/></td>
						<td><a href="#"><img class="ui-icon ui-icon-closethick" src="<html:imagesPath/>transparent.png" width="16" height="16" alt="Remove" title="Remove"></a></td>
					</tr>
				</c:forEach>
				<!--				<td width="180"><input class="w100" type="text" id="userEnteredActivity.serviceRequest.asset.pageCounts[0].date" name="userEnteredActivity.serviceRequest.asset.pageCounts[0].date" id="userEnteredActivity.serviceRequest.asset.pageCounts[0].date" readonly="readonly"/>-->
<!--					<img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Date" title="Select a Date"  onclick="show_cal('userEnteredActivity.serviceRequest.asset.pageCounts[0].date',null,null)">-->
<!--				</td>-->
  --%>        
          
<script>


	

        
  var pageCountslength=-1;
 // alert("${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.pageCounts)}");
  if(" ${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.pageCounts)}"!=0){
	  pageCountslength=parseInt(" ${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.pageCounts)}");
	  pageCountslength--;
   }else{
	  // addnewPageCountsRow();
	   }
  	
  function addnewPageCountsRow(){
	   if(pageCountslength==-1){
       		<%-- This is for removing the first row which table is empty --%>
       		jQuery('#pageCountsTable tbody tr:first').remove();       		
       	}
 		var trHtml=jQuery('#defaultTableTemplatePageCount tbody').html();
 		pageCountslength++;
 		formatValidation[9][3]=pageCountslength;
 		formatValidation[12][3]=pageCountslength;
 		
      	trHtml=replaceAllPageCounts(trHtml,'[-1]','['+pageCountslength+']');
      	if(pageCountslength%2==0){
      		trHtml=replaceAllPageCounts(trHtml,'altRow','');
      	}
      	jQuery(trHtml).appendTo(jQuery('#pageCountsTable tbody'));
      	bindClickEvent();
      }
  function removePageCountTableRow(tableRowId){
 	 tableRowId=tableRowId.replace('[','\\[');
 	 tableRowId=tableRowId.replace(']','\\]');
      	jQuery('#'+tableRowId).remove();
      	pageCountslength--;
      	formatValidation[9][3]=pageCountslength;
      	formatValidation[12][3]=pageCountslength;
      	if(pageCountslength==-1){
      		var trHTML="<tr><td colspan=\"4\"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec" javaScriptEscape="true"/></td></tr>";
      		jQuery(trHTML).appendTo(jQuery('#pageCountsTable tbody'));
      	}
      }	        
  function replaceAllPageCounts(source,replaceTarget,replaceWith){
  	
		while(source.indexOf(replaceTarget)>0)
				source=source.replace(replaceTarget,replaceWith);

			return source;
	}    
  
  function validatePageCountsType(selectObj){
	  var count=0;
	  jQuery(selectObj).removeClass('errorColor');
	  jQuery('#pageCountsTable tbody tr').each(function(){
		  jQuery(this).find('td:first').each(function(){
			  var selObj=jQuery(this).find("select");
			  
			  if(jQuery(selectObj).val()==jQuery(selObj).val()){
				  count++;
			  }
		  });
	  }); 
	  if(count>1){
		  jAlert("<spring:message code="requestInfo.hardwareDebrief.pageCountTypeExists"/>");
		  jQuery(selectObj).val('');
	  }
  }
</script>