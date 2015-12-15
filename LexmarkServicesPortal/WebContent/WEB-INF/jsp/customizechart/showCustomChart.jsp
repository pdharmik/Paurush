<%@ include file="../include.jsp"%>

<c:if test="${isRoleAdmin}">

<portlet:resourceURL var="saveCustomizedChartSettingUrl" id="saveCustomizedChartSetting"/>

<style type="text/css">
	#contentId {
    	display: table;
    	width: 100%;
    }
    #row  {
   		display: table-row;
    }
   #leftChartListId, #rightChartListId, #spacer1{
    	display: table-cell;
    }
    #buttonId {
    	display: table-cell;
    	vertical-align: middle;
    }
</style>

<script type="text/javascript">
	var chartListSize = '<c:out value="${noOfCharts}" />';
	var rightChart;
	var leftChart;
	
	function leftWindowValidateAndSetChartId(clickedObj, exceptObjId){
		var clickedChart = clickedObj.checked;
		if(clickedChart ==  true){
			for(var i=1; i<=chartListSize; i++){
				var currId = "leftChartId_"+i;
				if(currId != exceptObjId){
					var currObj = document.getElementById(currId);
					if(currObj.checked == true){
						clickedObj.checked = false;
					}
				}else{
					var anotherObj = document.getElementById("rightChartId_"+i);
					if(anotherObj.checked == true){
						clickedObj.checked = false;
					}
				}
			}
		}
	}
	
	function rightWindowValidateAndSetChartId(clickedObj, exceptObjId){
		var clickedChart = clickedObj.checked;
		if(clickedChart ==  true){
			for(var i=1; i<=chartListSize; i++){
				var currId = "rightChartId_"+i;
				if(currId != exceptObjId){
					var currObj = document.getElementById(currId);
					if(currObj.checked == true){
						clickedObj.checked = false;
					}
				}
				else{
					var anotherObj = document.getElementById("leftChartId_"+i);
					if(anotherObj.checked == true){
						clickedObj.checked = false;
					}
				}
			}
		}
	}
	
	function saveCustomization(){
		rightChart = ""
		leftChart = "";
		for(var i=1; i<=chartListSize; i++){
			var leftObj = document.getElementById("leftChartId_"+i);
			var rightObj = document.getElementById("rightChartId_"+i);
			if(leftObj.checked){
				leftChart = leftObj.value;
			}
			if(rightObj.checked){
				rightChart = rightObj.value;
			}
		}
		queryUrl ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='saveCustomizedChartSetting' />" + 
		"</portlet:renderURL>";
		
		jQuery.ajax({
		       type: "POST",
		       url: '${saveCustomizedChartSettingUrl}',
		       data: 
		       { 
		    	   leftChartId: leftChart,
		    	   rightChartId: rightChart
		       },
		       dataType: "text/xml",
		       success: function(taskData) {
		    	  jQuery("#output").html(taskData);
		       }
		 });
	}
	
	function toggleDiv(divId) {
		jQuery("#"+divId).toggle();
		document.getElementById("output").innerHTML = "";		
	}
	
	
	
</script>
	<div class="main-wrap">
		<div id="output" >
		
		</div>

		&nbsp;&nbsp;<a href="javascript:toggleDiv('contentId');" >Customize</a>

		<div id="contentId" style="display: none;" >
		<table cellspacing="2">
			<tr>
				<td>
					&nbsp;<b>Left Chart Window</b><br/>
					<div id="leftChartListId" class="content" > 
						<table border="0">
								<c:forEach items="${chartList}" var="leftChart" varStatus="leftCnt">
									<c:if test="${leftCnt.count % 3 == 1}">
										<tr>
									</c:if>
										<td>
											<c:if test="${leftChart.window == 'L'}">
												<input type="checkbox" id='leftChartId_<c:out value="${leftCnt.count}" />' name='leftChart_<c:out value="${leftCnt.count}" />' value='<c:out value="${leftChart.chartId}" />' checked="checked" onclick="leftWindowValidateAndSetChartId(this, 'leftChartId_<c:out value="${leftCnt.count}" />');"/>
											</c:if>
											<c:if test="${leftChart.window != 'L'}">
												<input type="checkbox" id='leftChartId_<c:out value="${leftCnt.count}" />' name='leftChart_<c:out value="${leftCnt.count}" />' value='<c:out value="${leftChart.chartId}" />' onclick="leftWindowValidateAndSetChartId(this, 'leftChartId_<c:out value="${leftCnt.count}" />');"/>
											</c:if>
											&nbsp;
											<c:out value="${leftChart.chartName}" />
											&nbsp; &nbsp;
										</td>						
				       				<c:if test="${(leftCnt.count % 3 == 0)}">
										</tr>
									</c:if>
								</c:forEach>
						</table>
					</div>
				</td>
				<td>
					&nbsp;&nbsp;
				</td>
				<td>
					&nbsp;<b>Right Chart Window</b><br/>
					<div id="rightChartListId" class="content" > 
						<table border="0">
								<c:forEach items="${chartList}" var="rightChart" varStatus="rightCnt">
									<c:if test="${rightCnt.count % 3 == 1}">
										<tr>
									</c:if>
										<td>
											<c:if test="${rightChart.window == 'R'}">
												<input type="checkbox" id='rightChartId_<c:out value="${rightCnt.count}" />' name='rightChart_<c:out value="${rightCnt.count}" />' value='<c:out value="${rightChart.chartId}" />' checked="checked" onclick="rightWindowValidateAndSetChartId(this, 'rightChartId_<c:out value="${rightCnt.count}" />');"/>
											</c:if>
											<c:if test="${rightChart.window != 'R'}">
												<input type="checkbox" id='rightChartId_<c:out value="${rightCnt.count}" />' name='rightChart_<c:out value="${rightCnt.count}" />' value='<c:out value="${rightChart.chartId}" />' onclick="rightWindowValidateAndSetChartId(this, 'rightChartId_<c:out value="${rightCnt.count}" />');"/>
											</c:if>
											&nbsp;
											<c:out value="${rightChart.chartName}" />
											&nbsp; &nbsp;
										</td>						
				       				<c:if test="${(rightCnt.count % 3 == 0)}">
										</tr>
									</c:if>
								</c:forEach>
						</table>
					</div>
				</td>
				<td>
					<div id="buttonId">
						<button name="btnSearch" class="button" id="btnUpdate" onclick='saveCustomization();'>
							<spring:message code="button.submit" />
						</button>
					</div>
				</td>
			</tr>
		</table>
		</div>
	</div>
	
	<script type="text/javascript">
	//---- Ominture script 
	     portletName = "CustomizeChart";
	     addPortlet(portletName);
	     pageTitle="CustomizeChart";
	</script>
</c:if>