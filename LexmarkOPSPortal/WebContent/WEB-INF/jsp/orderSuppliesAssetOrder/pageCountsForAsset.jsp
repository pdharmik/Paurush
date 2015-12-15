<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div>
              <div class="infoBox rounded shadow">
              <div id="pageCountInfo"><p><spring:message code="requestInfo.info.pageCountsInfo"/></p></div>
              <div class="columnInner separatorV">
              	<div id="pageCountsDiv">
					<div id="pageCounts" class="gridbox_light" style="width: 100%;">
					<c:choose>
					<c:when test="${not empty pageCountsString}">	
					<div id="pageCountsConfirmPage" style="width:100%;"></div>	
								<div class="pagination"><span id="div_pageCountsPagingArea"></span>
	    				<span id="div_pageCountsinfoArea"></span></div>		
	    				</c:when>
	    				<c:otherwise>
	    				<div style=" overflow-x: hidden; overflow-y: auto; max-height: 268px; width:560px;">
						<table id="pageCountsTable" style="table-layout: fixed;">
							<thead>
								<tr>
									<th><spring:message code="requestInfo.info.heading.pageCount"/></th>
									<th><spring:message code="requestInfo.info.heading.datetime"/></th>
									<th><spring:message code="requestInfo.info.heading.newPageCount"/></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						</div>
						</c:otherwise>
						</c:choose>	
					</div>	
	  						
	    		</div><!-- mygrid_container -->
	            </div>
	            </div>
				
				</div>
            </div>
          </div>
          <script type="text/javascript">
           	       	 
          			<c:if test="${not empty pageCountsString}">
          			pageCountsGrid = new dhtmlXGridObject('pageCountsConfirmPage');
					pageCountsGrid.setImagePath("<html:imagesPath/>gridImgs/");
					pageCountsGrid.setHeader("<spring:message code='requestInfo.heading.commonPageCounts'/>");
					pageCountsGrid.setColAlign("left,left,left,left");
					pageCountsGrid.setColTypes("ro,ro,ro,ro");
					//Changes for CI7 BRD 14-06-07
					pageCountsGrid.setInitWidths("160,220,180,30");
					pageCountsGrid.enableAutoWidth(true);
					pageCountsGrid.enableMultiline(true);
					pageCountsGrid.enableAutoHeight(true);
					</c:if>
					//<c:if test="${empty pageCountsString}">
					//pageCountsGrid.enableAutoHeight(true,300,true);
					//</c:if>
					<c:if test="${not empty pageCountsString}">
					pageCountsGrid.init(); 
					pageCountsGrid.prftInit();
					pageCountsGrid.enablePaging(true, 5, 10, "div_pageCountsPagingArea", true);
					pageCountsGrid.setPagingSkin("bricks");
					pageCountsGrid.setSkin("light");					
					pageCountsGrid.setColumnHidden(3,true);			
					pageCountsGrid.loadXMLString('${pageCountsString}');
					</c:if>
					
					function pageCountValidate(id){						
						for(i=0;i<=id;i++){	
							var idGeneratePT="select_id_"+i;
							var idGenerateD="rwid_"+i;
							var idGenerateV="newCount_"+i;
							jQuery("#"+idGenerateD).removeClass('errorColor');
							jQuery("#"+idGeneratePT).removeClass('errorColor');
							jQuery("#"+idGenerateV).removeClass('errorColor');
							if(document.getElementById(idGeneratePT) != null && document.getElementById(idGenerateD)!=null &&  document.getElementById(idGenerateV)!=null){
							
							if((jQuery("#"+idGenerateV).val() != '') && (!isNumeric(jQuery("#"+idGenerateV).val()))) {
								jQuery('#errorMsg').append('<li><strong><spring:message code="validation.properPageCount.numeric"/> '+(i+1)+' </strong></li>');
								jQuery("#"+idGenerateV).addClass('errorColor');
								validationFlag = false;
							}
							if(document.getElementById(idGenerateV).value=='' && document.getElementById(idGenerateD).value!='' || 
									document.getElementById(idGenerateV).value!='' && document.getElementById(idGenerateD).value=='' ) {
										validationFlag = false;
										if(document.getElementById(idGenerateV).value==''){
											jQuery('#'+idGenerateV).addClass('errorColor');
											jQuery("#errorMsg").append("<li><strong>"+"<spring:message code='validation.properPageCount.newPageCounts'/> " 
													+(i+1)+ "</strong></li>");
										}else{
											jQuery('#'+idGenerateD).addClass('errorColor');
											jQuery("#errorMsg").append("<li><strong>"+"<spring:message code='validation.properPageCount.date'/> " 
													+(i+1)+ "</strong></li>");
										}
										
									}
							}
						}
						
					 }
					
					function isNumeric(s)  {  
						var patrn=/^[0-9]+$/;  
						if (!patrn.exec(s))
						{	
							return false;  
						}
						
						return true;  
					}

</script>