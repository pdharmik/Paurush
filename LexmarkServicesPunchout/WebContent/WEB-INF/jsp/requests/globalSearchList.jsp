<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<portlet:resourceURL var="loadGlobalSearchList" id="loadGlobalSearchList"></portlet:resourceURL> <!-- this might not be required -->
<style>
	#bundlesPagingArea > div {
	    width: 100% !important;
	}
	#pagingArea > div {
	    width: 100% !important;
	}
</style>
	<h3>Search Result</h3>
	<div class="heading">
		<br/><br/>
		<div class="showCount">
		        <form name="option_Form" id="option_Form">
		            <span class="countdescription">Display</span>
		            <select onchange="" name="ItemSize" id="optionsSize">
		              	<option selected="" value="10">10</option>
						<option value="25">25</option>
						<option value="50">50</option>
		            </select>
		          <span class="countdescription">Item per page</span>
		        </form>
		</div>
	</div>
	<div id="globalSearch_container">
		<table id="accessories-global-search">
			<thead>
				<tr>
					<td colspan="3"></td>
					<td align="right" class="qtyHeader">Qty</td>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<div id="bundle-global-search"></div>
	</div>

<script>
function getDataForGlobalSearch(params){
	$('#accessories-global-search tbody,#bundle-global-search').html('');
	$('#globalSearch_container #accessories-global-search').hide();
	showOverlay();				
		$.getJSON("${loadGlobalSearchList}"+convertToParams(params),function(jsonBundles){
			hideOverlay();
			$('#loadingNotification_printer').hide();
			bundlesObj.bundlesData=jsonBundles.bundle;
			bundlesObj.globalSearchAccessories=jsonBundles.optionsWarranties;
			bundlesObj.parseData_searchResult();
								
							
		});
	
}



</script>

<script id="bundleSearch-list-template" type="text/x-handlebars-template">

	<div class="globalSearch_List">
	<div class="floatL description_Area">
		<img class="floatL" src="<html:imagesPath/>product-printer.jpg" width="89" height="72" alt="Mono Laser Printers">
		<div class="floatL description_Detail">
			<a href="/kaiser/printer/35S0228">{{mpsdesc}}</a>
			{{#parts}}
				<div>Part#: {{no}}</div>
			{{/parts}}
		</div>
	</div>
	<div class="floatR qty_AddToCart">
		<div class="price">{{price}}({{curr}})</div><br/>
		<label>Quantity</label>
		<input class="quantityInputBox floatR" type="text" name="Quantity" id="quantity{{bundleId}}"/>
		<div class="clearBoth"></div><br/>
		<input type="button" id="globalCart_{{bundleId}}" class="button floatR" value="Add To Cart" onclick="moveToCart('{{bundleId}}',this.id)"/>
	</div>
	<div class="clearBoth"></div>
	</div>
</script>

