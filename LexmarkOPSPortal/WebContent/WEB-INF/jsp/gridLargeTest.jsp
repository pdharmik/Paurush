<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp" %> 

<style type="text/css"><%@ include file="../css/grid/dhtmlxgrid_dhx_skyblue.css" %></style> 
<style type="text/css"><%@ include file="../css/grid/dhtmlxgrid_pgn_bricks.css" %></style> 
<script type="text/javascript"><%@ include file="../../js/grid/ext/dhtmlxgrid_pgn.js"%></script>
<script type="text/javascript"><%@ include file="../../js/grid/ext/dhtmlxgrid_splt.js"%></script>

<div class="portlet-section-header"></div>

<div class="portlet-section-body">
	<table>
		<tr>
			
			<td width="35%" height="100%"></td>
			<td width="65%" height="100%">
				<table width="100%">
					<tr>
						<td width="99%">
							<fieldset style="width:99%"><br>
								<font class="orangeSectionTitles"><spring:message code="serviceRequest.description.pleaseEnterYourPrinterInfo"/></font><br><br>
	    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="blackCopyTitle">	<spring:message code="serviceRequest.description.search"/> </font>&nbsp;&nbsp;&nbsp;&nbsp;
	    						<select id='a10'>
	        						<option value='0'><spring:message code="serviceRequest.listHeader.serialNumber"/></option>
	        						<option value='1'><spring:message code="serviceRequest.listHeader.model"/></option>
	        						<option value='2'><spring:message code="serviceRequest.listHeader.assetTagDeviceTag"/></option>
	        						<option value='3'><spring:message code="serviceRequest.listHeader.ipAddress"/></option>
	        						<option value='4'><spring:message code="serviceRequest.listHeader.location"/></option>
	    						</select>
	    						<input type="text" name="a12" value="" id="a12">
	    						<button name="btn_search"  class="button" id="a11" onclick='mygrid.filterBy(document.getElementById("a10").value,document.getElementById("a12").value);'><spring:message code="serviceRequest.button.search"/></button>
	    						<br/><br/>
	    						<a href="#"><spring:message code="serviceRequest.label.advancedSearch"/></a>
	    						<br><br>
          					</fieldset>
						</td>
					</tr>
				</table>
				<br>
				<table>
				<tr>
					<td align = "left" class="orangeSection"><spring:message code="serviceRequest.label.myAsset"/></td>
				</tr>
				<tr>
					<td>
					<div id="gridbox" style="width:700px; height:300px; background-color:white;"></div>
					<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
					<br>
					    
				<script type="text/javascript">
						function gotoSubmitPage(rowId){
							window.location.href="<portlet:renderURL></portlet:renderURL>";
						}		
						mygrid = new dhtmlXGridObject('gridbox');
						mygrid.setImagePath("<html:imagesPath/>/gridImgs/");
						mygrid.setHeader("<spring:message code='serviceRequest.listHeader.allItems'/>");
						mygrid.attachHeader("#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
						mygrid.setInitWidths("140,140,140,140,140");
						mygrid.setColAlign("left,left,left,left,left");
						mygrid.setColTypes("ro,ro,ro,ro,ro");
						mygrid.setColSorting("str,str,str,str,str");
						mygrid.enableMultiline(true);
						mygrid.setSkin("dhx_skyblue");
						//mygrid.enableAutoWidth(true);
    					
    					

						//************************ Grid filer**********************//
    					mygrid.attachEvent("onFilterStart", function(indexes,values){
						// The value of filtering Criterias can be get from the values by the position
						// e.g.if values=" ,,,,," than 7 is the filtering value for second columns
    			            mygrid.filterValues = values;
    			            mygrid.filterValues = "Open";
    			            mygrid.searchvalues=document.getElementById(input.id);

    			        	setGridFilerTimer(reloadGrid);
    			        });
    			        //********************************************************//
    			        
						mygrid.init();
						
						//********************* Smart rendering **************//
    			        // KH: enableSmartRendering can not work together with pagination
						//mygrid.enableSmartRendering(true);
						//******************************************************//
						
						//***********************Paging logic for large data set *********//
						//mygrid.attachEvent("onBeforePageChanged", function() {
        				//if (!this.getRowsNum());
        				//	return false;
        				//	return true;
    					//});
    					//**************************************************************//
    					
						mygrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
						mygrid.setPagingSkin("bricks");  
				
						//mygrid.load("<portlet:resourceURL></portlet:resourceURL>","json");                           
						mygrid.loadXML("<portlet:resourceURL></portlet:resourceURL>");
						//mygrid.loadXML("http://www.dhtmlx.com/docs/products/dhtmlxGrid/samples/14_loading_big_datasets/php/50000_load_grid.php");
						
						//*********************** Overwrite Large data soring *********// 
						
						// KH:if the grid is dealing with a large set of data, than, sorting should be rewrited.
    					mygrid.attachEvent("onBeforeSorting", customColumnSort);
						mygrid.sortRows = function(col, type, order) {}
						
						function customColumnSort(ind) {
    						var a_state = mygrid.getSortingState();
    						mygrid.a_direction = ((a_state[1] == "des") ? "asc": "des");
    						mygrid.s_col = ind + 1;
   						 	reloadGrid();
    						return true;
						}
						//**************************************************************//
						
						function reloadGrid() {
							var url = "<portlet:resourceURL></portlet:resourceURL>";
							if(mygrid.filterValues != null && mygrid.filterValues != "") {
								url = url + "&searchCriterias=" + mygrid.filterValues;
							}
							if(mygrid.s_col != undefined && mygrid.s_col != "") {
                                url = url + "&orderBy=" + (mygrid.s_col-1);
                            }
                            if(mygrid.a_direction != null && mygrid.a_direction != "") {
                                url = url + "&direction=" + mygrid.a_direction;
                            }
                            
						    mygrid.clearAndLoad(url);
						  
						}
					</script>
					</td>
				</tr>
				
			</table>
		</td>
	</tr>
</table>
</div>
	
