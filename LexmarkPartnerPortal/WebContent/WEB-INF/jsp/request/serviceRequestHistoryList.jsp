<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />			
			<div>
				<H4><img id="showHideServiceHistoryImg" class="ui-icon expand-icon cursor-pointer" src="<html:imagesPath/>transparent.png" onclick="showHideServiceHistory(this)">&nbsp;<spring:message code="claim.label.viewServiceHistory"/></H4>
			</div>
			<div class="portlet-wrap" id="service_history">
				<div id="serviceHistoryContent" style="display:none;">
					<div class="portlet-header">
					
<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.serviceHistory"/></th>
                </tr></thead>
              
            </table>
					</div><!-- portlet-header -->
					<div class="portlet-wrap-inner">
						<div class="scroll width-100per" >
							<div id="service_history_container width-100per" ></div>
							<div id="loadingServiceHistory" class='gridLoading'>
								<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
							</div>
							<div>
								<span id="pagingAreaServiceHistory"></span>&nbsp;<span id="infoAreaServiceHistory"></span>
							</div>
						</div>
					</div><!-- portlet-wrap-inner -->
					<div class="portlet-footer">
						<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
					</div><!-- portlet-footer -->
				</div>
			</div>
			
			<!--Added for column "Part Status" in Grid -- CI17_BRD_15 -- Start -->
				<script type="text/javascript">
				 var serviceHistoryListFirstLoading = true;
				 var serviceHistoryListLoadingEnd = false;
				 var serviceHistoryListURL = '<portlet:resourceURL  id="getServiceHistoryList"></portlet:resourceURL>&assetId=${assetId}&serviceRequestId=${serviceRequestId}&timezoneOffset=' + timezoneOffset;
				 var serviceHistoryContainer = new dhtmlXGridObject('service_history_container');
				 function initGridForServiceHistory(){
					 serviceHistoryContainer.setImagePath("<html:imagesPath/>gridImgs/");
				 	 
					 serviceHistoryContainer.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.serviceHistory"/>',7));
					 serviceHistoryContainer.setColAlign("left,left,left,left,left,left,left");
					 serviceHistoryContainer.setInitWidths("120,100,100,100,100,150,150");
					 //serviceHistoryContainer.enableAutoWidth(false);
					 serviceHistoryContainer.setColTypes("ro,ro,ro,ro,ro,ro,ro");
					 serviceHistoryContainer.setColSorting("str,str,str,str,str,str,str");
					 serviceHistoryContainer.enableAutoHeight(true);
					 serviceHistoryContainer.enableMultiline(true);
					 serviceHistoryContainer.setSkin("light");
					 serviceHistoryContainer.init(); 
					 serviceHistoryContainer.prftInit(); 
		             serviceHistoryContainer.setSortImgState(true, 0, "des");
					 serviceHistoryContainer.sortRows(0);
					 serviceHistoryContainer.enablePaging(true, 5, 10, "pagingAreaServiceHistory", true);
					 serviceHistoryContainer.setPagingSkin("bricks");
					 serviceHistoryContainer.attachEvent("onXLS", function() {
						document.getElementById('loadingServiceHistory').style.display = 'block';
					});
					 serviceHistoryContainer.attachEvent("onXLE", function() {
						 setTimeout(function(){
					    		rebrandPagination();
					    	
					    	},100);
						document.getElementById('loadingServiceHistory').style.display = 'none';
						serviceHistoryListLoadingEnd = true;
					});
				}

				 function showHideServiceHistory(img){
					var collapsedImg = '<html:imagesPath/>collapsed.jpg';
					var expandImg = '<html:imagesPath/>expand.jpg';
					var contentEle = document.getElementById('serviceHistoryContent');
					if(contentEle.style.display != 'none'){
						contentEle.style.display = 'none';
						//img.src = expandImg;
						$(img).removeClass('minimize-icon');
						$(img).addClass('expand-icon');
					}else{
						contentEle.style.display = 'block';
						$(img).addClass('minimize-icon');
						$(img).removeClass('expand-icon');
						if(serviceHistoryListFirstLoading){
							initGridForServiceHistory();
							serviceHistoryContainer.loadXML(serviceHistoryListURL);
							serviceHistoryListFirstLoading = false;
						}else if(serviceHistoryListLoadingEnd){
							serviceHistoryContainer.loadXMLString("<rows></rows>");
						}
					}
				 }
			</script>