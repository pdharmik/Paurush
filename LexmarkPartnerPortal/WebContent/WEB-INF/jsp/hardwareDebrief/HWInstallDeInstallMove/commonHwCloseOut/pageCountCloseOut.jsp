<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.header"/></h4>
              <div>
              
              	<div class="portlet-wrap rounded width-35per" >
  						<div class="portlet-wrap-inner">	
						 	<div id="pageCountsGridbox" class="gridbox gridbox_light" ></div>
							<%-- <div id="loadingNotification_pageCounts" class='gridLoading'>
							<br><!--spring:message code="requestInfo.popup.loading"/-->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
							</div>--%>
						</div>
					</div>
						
				<div class="pagination" id="pagination_pageCounts"><span id="pagingArea6"></span><span id="infoArea6"></span></div>
				
				
              <%--
					<table class="displayGrid rounded shadow wHalf" id="pageCountsTable">
						<thead>
							<tr>
								<th class="w100">Page Count</th>
								<th>Date/Time</th>
								<th class="w100">New Page Count</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pageCount" items="${activity.serviceRequest.asset.pageCounts}" varStatus="status">
								<tr>
									<td><select class="w100" name="activity.serviceRequest.asset.pageCounts[${status.index}].type">
										<option>MonoLPC</option>
										</select>
									</td>
									<td width="180"><input class="w100" type="text" id="activity.serviceRequest.asset.pageCounts[${status.index }].date" name="activity.serviceRequest.asset.pageCounts[${status.index }].date" value="<util:dateFormat value="${pageCount.date}" timezoneOffset="${tzOffset}" showTime="true" showSecond="true"></util:dateFormat>" readonly="readonly"/>
									<img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Date" title="Select a Date" onclick="show_cal('activity.serviceRequest.asset.pageCounts[${status.index }].date',null,null)"></td>
									<td><input class="w100" type="text" name="activity.serviceRequest.asset.pageCounts[${status.index }].count" value="${pageCount.count}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					 --%>
					 <%-- 
			
					<div class="columnsTwo">
						<br/>
						<button id="addnew" type="button" class="button floatR" onClick="addnewPageCountsRow()">Add New</button>
					</div>
			
					 --%>
				</div>
            </div>
            <div style="display:none;" id="pageCountHiddenInputs"></div>
          </div>
        </div>
         
        <%--
         <div style="display:none;" id="templateContactDiv">
         <!-- THIS DIV IS USED TO CREATE A NEW TABLE ROW FOR ADD PAGE COUNT -->
         	<table id="defaultTableTemplatePageCount">
         	<tbody>
         		<tr>
								<td><select class="w100" name="activity.serviceRequest.asset.pageCounts[-1].type"><option>MonoLPC</option></select></td>
								<td width="180"><input class="w100" type="text" id="activity.serviceRequest.asset.pageCounts[-1].date" name="activity.serviceRequest.asset.pageCounts[-1].date" id="activity.serviceRequest.asset.pageCounts[-1].date" readonly="readonly"/>
								<img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Date" title="Select a Date"  onclick="show_cal('activity.serviceRequest.asset.pageCounts[-1].date',null,null)">
								</td>
								<td><input class="w100" type="text" name="activity.serviceRequest.asset.pageCounts[-1].count"/></td>
				</tr>
         	</tbody>
         	</table>
         </div>
          --%>
          
         <script>
         //show_cal(textId.attr('id'), beginDate, endDate);
         
         <%--
         var pageCountslength=-1;
         	if("${activity.serviceRequest.asset.pageCounts.size}"!=""){
         		pageCountslength=parseInt("${activity.serviceRequest.asset.pageCounts.size}");
             }
 
         function addnewPageCountsRow(){

        		var trHtml=jQuery('#defaultTableTemplatePageCount tbody').html();
        		pageCountslength++;
             	
                 	
             	trHtml=replaceAll(trHtml,'[-1]','['+pageCountslength+']');
             	
             	jQuery(trHtml).appendTo(jQuery('#pageCountsTable tbody'));
             	
             }
             --%>
			var pageCountXML="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><rows>";
			<c:forEach var="pageCount"  items="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.pageCounts}" varStatus="status">
				pageCountXML+="<row id=\""+${status.index} +"\">";
				pageCountXML+="<cell>"+"${pageCount.type}"+"</cell>";
				pageCountXML+="<cell>"+"&lt;input type='text' name='pageCountGrid[${status.index}].count' value='${fn:replace(pageCount.count,',', '')}'/&gt;"+"</cell>";
				<%--pageCountXML+="<cell>"+"&lt;img src='<html:imagesPath/>transparent.png'  alt=\"Remove\" title=\"Remove\" class=\"edit ui-icon ui-icon-closethick\" onclick=\"removeRow('${status.index}')\"/&gt;"+"</cell>";--%>
				pageCountXML+="</row>";
				
			</c:forEach>
				pageCountXML+="</rows>";
			
             var pageCountGrid;
            pageCountGrid = new dhtmlXGridObject('pageCountsGridbox');
         	pageCountGrid.setHeader("<spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.pageCounType"/>,<spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.newPageCount"/>");
         	pageCountGrid.setInitWidths("*,*");
         	pageCountGrid.setColAlign("left,left");
         	pageCountGrid.setColTypes("ro,ro");
         	
         	pageCountGrid.init();
         	pageCountGrid.prftInit();
         	
         	pageCountGrid.enablePaging(true, 2, 6, "pagingArea6", true, "infoArea6");
         	pageCountGrid.setPagingSkin("bricks");
         	//pageCountGrid.enableAutoWidth(true);
         	pageCountGrid.enableAutoHeight(true);
         	pageCountGrid.enableMultiline(true);
         	pageCountGrid.enableColumnMove(false);
         	<%--alert(pageCountXML);--%>
         	pageCountGrid.parse(pageCountXML);
         	pageCountGrid.setSkin("light");
         	
         	
         	function creatHiddenInputsPageCount(){
         		var page=1;
         		pageCountGrid.changePage(page);
         		for(i=0;i<pageCountGrid.getRowsNum();i++){
                 	//name=''
             		    var value=pageCountGrid.cellById(i,0).getValue();
             		    var html="<input type='text' value='"+value+"' name='userEnteredActivity.serviceRequest.asset.pageCounts["+i+"].type' />";
             		 //   alert(html);
             		    jQuery('#pageCountHiddenInputs').append(html);
             		   
             		   html="<input type='text' value='"+jQuery("[name='pageCountGrid["+i+"].count']").val()+"' name='userEnteredActivity.serviceRequest.asset.pageCounts["+i+"].count' />";
             		// alert(html);
             		jQuery('#pageCountHiddenInputs').append(html);
             		if((i+1)%2==0){             			
             			page++;
             			pageCountGrid.changePage(page);
             			
             		}
             		
             		
             	}	
         	}
         	

         	
         	

             
         </script>