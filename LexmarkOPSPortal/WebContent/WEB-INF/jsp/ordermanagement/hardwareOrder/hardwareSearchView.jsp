<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<script type="text/javascript">
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>

<!-- SEARCH RESULT BEGIN -->
        
        <!-- Catalog Table BEGIN -->
        
        <div class="colunmInner rounded shadow">
		<div id="hardwareListGridObj" style="width:100%"></div>
		<div id="loadingNotification" class='gridLoading'>
  			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
		</div>
        <div id="showBundle" style="display:none">
        <p class="info inlineTitle">Selected Hardware</p>
        <div class="columnInner">       
          <table class="displayGrid noBorder rounded shadow wFull">
            <tr>
              <td class="pModel w100"><img src="../../../images/dummy_printer.jpg" /></td>
              <td class="w500"><ul class="form">
                  <li>
                    <label>Part No:</label>
                    <span>xxxxxxxxxxxxxx</span></li>
                  <li>
                    <label>Description:</label>
                    <span class="multiLine">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi ac volutpat erat. Suspendisse tincidunt pellentesque accumsan.</span></li>
                </ul></td>
              <td class="w200"><ul class="form">
                  <li>
                    <label>Device Type:</label>
                    <span>Device Type</span></li>
                  <li>
                    <label>Color/Mono:</label>
                    <span>Color/Mono</span></li>
				  <li>
                    <label>Model:</label>
                    <span>Product Model</span></li>
                </ul></td>
             <td>
              	<div class="f200">$1,199.00</div>
              	<div class="lineClear">
					<input class="w50" type="text" name="quantity1">
					<span class="spaceClear"/>
					<button class="button w120 ">Add to Order</button>
				</div>
				
			</td>
            </tr>
          </table>
         
        </div>
       
        <!-- Catalog Table End -->
       
        <!-- TABBED SECTION - START -->
        <div id="tabs" class="nestedTabs">
          <ul>
            <li><a href="#tabs-1">Pre-Configured</a></li>
            <li><a href="#tabs-2">Accessories </a></li>
            <li><a href="#tabs-3">Supplies</a></li>
          </ul>
          <div class="tabContent">
            <div id="tabs-1">
              <div id="mygrid_container2"></div> 
  		      <div class="pagination"> <span id="pagingArea2"></span><span id="infoArea2"></span> </div>
            </div>
            <div id="tabs-2">
             <div id="mygrid_container"></div> 
  		     <div class="pagination"> <span id="pagingArea"></span><span id="infoArea"></span> </div> 
            </div>
            <div id="tabs-3">
             <div id="mygrid_container3"></div> 
  		      <div class="pagination"> <span id="pagingArea3"></span><span id="infoArea3"></span> </div> 
            </div>
          </div>
          </div>
          </div>
          <div id="showOther" style="display:none">
          </div>
        
        <!-- TABBED SECTION - END -->
         
        <!-- SEARCH RESULT END -->
        
        </div>
         <div id="buttonListDiv" class="buttonContainer">
				<button class="button_cancel" type="button" onclick="javascript:redirectToHistory('catalog');"><spring:message code="button.cancel"/></button>
				<button class="button" type="button" onClick="javascript:testMethod();"><spring:message code="button.continue"/></button>
		</div>
		
		<script type="text/javascript">

var mygrid;
var headerString = ",,,";
var ar = [[ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"], 
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"]];
mygrid = new dhtmlXGridObject('mygrid_container');
mygrid.setImagePath("../../../../images/gridImgs/");
mygrid.setHeader(headerString);

mygrid.setInitWidths("175,320,*,295");
mygrid.setColumnMinWidth("110,110,110,110");
mygrid.enableResizing("true,true,true,true");
mygrid.setColAlign("left,left,left,left");
mygrid.setSkin("light");
mygrid.setNoHeader("true");
mygrid.setColTypes("ro,ro,ro,ro");
mygrid.enableAutoHeight(true);
mygrid.enableMultiline(true);
mygrid.enableColumnMove(true);
mygrid.init();
mygrid.enablePaging(true, 2, 3, "pagingArea", true, "infoArea");
mygrid.setPagingSkin("bricks"); 	 
mygrid.parse(ar,"jsarray");

var mygrid2;
var headerString = ", ,";
var ar = [[ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"], 
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<p>Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy</p><p class=\"strong\">Installation and Warranty</p>", "<div><table class=\"displayGrid rounded shadow wFull\"><thead><tr><th class=\"w100\">Part No.</th><th>Description</th><th class=\"w100\">Qty</th></tr></thead><tbody><tr><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr><tr class=\"altRow\"><td class=\"w100\">123456SDFGH</td><td>Description of the item goes here</td><td class=\"w100\">1</td></tr></tbody></table></div>",  "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"]];
mygrid2 = new dhtmlXGridObject('mygrid_container2');
mygrid2.setImagePath("../../../../images/gridImgs/");
mygrid2.setHeader(headerString);

mygrid2.setInitWidths("305,*,295");
mygrid2.setColumnMinWidth("110,110,110");
mygrid2.enableResizing("true,true,true");
mygrid2.setColAlign("left,left,left");
mygrid2.setSkin("light");
mygrid2.setNoHeader("true");
mygrid2.setColTypes("ro,ro,ro");
mygrid2.enableAutoHeight(true);
mygrid2.enableMultiline(true);
mygrid2.enableColumnMove(true);
mygrid2.init();
mygrid2.enablePaging(true, 2, 3, "pagingArea2", true, "infoArea2");
mygrid2.setPagingSkin("bricks"); 	 
mygrid2.parse(ar,"jsarray");

var mygrid3;
var headerString = ",,,";
var ar = [[ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"], 
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"],
          [ "<img src=\"../../../images/dummy_printer.jpg\" alt=\"Change\">", "<ul class=\"form\"><li><label for=\"refId\">Part No.:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Description:</label><span>XXXXXXXXXX</span></li></ul>", "<ul class=\"form\"><li><label for=\"refId\">Device Type:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Color/Mono:</label><span>XXXXXXXXXX</span></li><li><label for=\"refId\">Product Model:</label><span>Model Name</span></li></ul>", "<div class=\"f200\">$1,199.00</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\"><span class=\"spaceClear\"/><button class=\"button w120 \">Add to Order</button></div>"]];
mygrid3 = new dhtmlXGridObject('mygrid_container3');
mygrid3.setImagePath("../../../../images/gridImgs/");
mygrid3.setHeader(headerString);

mygrid3.setInitWidths("175,320,*,295");
mygrid3.setColumnMinWidth("110,110,110,110");
mygrid3.enableResizing("true,true,true,true");
mygrid3.setColAlign("left,left,left,left");
mygrid3.setSkin("light");
mygrid3.setNoHeader("true");
mygrid3.setColTypes("ro,ro,ro,ro");
mygrid3.enableAutoHeight(true);
mygrid3.enableMultiline(true);
mygrid3.enableColumnMove(true);
mygrid3.init();
mygrid3.enablePaging(true, 2, 3, "pagingArea3", true, "infoArea3");
mygrid3.setPagingSkin("bricks"); 	 
mygrid3.parse(ar,"jsarray");


function show() {
		//alert(document.getElementById('prodType').value);
		if(document.getElementById('prodType').value !== "select") { 
			document.getElementById('showBundle').style.display = "block";
			document.getElementById('showOther').style.display = "none";
		}
		else {
			document.getElementById('showOther').style.display = "block";
			document.getElementById('showBundle').style.display = "none";
		}
	}
	
	function showDiv(id){		
		var elm = document.getElementById(id);	
		elm.style.display = "block";		
		}
	
	function hideDiv(id){	
		var elm = document.getElementById(id);
		elm.style.display = "none";	
			
		}