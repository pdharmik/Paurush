<%@ include file="/WEB-INF/jsp/include2.jsp"%>

<div id="printerList">
<div id="portlet-wrap" style="width:100%!important">
	  <div class="pageTitle"><spring:message code="meterRead.label.Printer"/></div>
      <div class="mid-cntnr">
    	<div id="printer-List-content">
    		<div id="loading-printer-list" class='gridLoading'><img src="/lexmark-punchout-theme/images/custom/loading_big.gif"/><br/>
    	</div>
       
      </div>
    </div>
</div>
  </div>  
<script>

var printerListObj={
		bundleList:{},
		initTemplate:function(){
			YUI().use('handlebars','node-base', function (Y) {
				printerListObj.printerListTemplate = Y.Handlebars.compile(Y.one('#printerList-template').getHTML());
			});
		},
		generateBundleHtml:function(){
			if(printerListObj.printerListTemplate==undefined){
				printerListObj.initTemplate();
			}else{
				return printerListObj.printerListTemplate(printerListObj.bundleList);
			}
		},
		getDataPrinterList:function(){
			$('#loading-printer-list').show();
			var url="${loadPrinterList}";
			$.getJSON(url,function(printerList){
				$('#loading-printer-list').hide();
				printerListObj.bundleList=printerList;
				$('#printerList #printer-List-content').append(printerListObj.generateBundleHtml());
				
			});
		}
};
function loadPrinterListData(){
	printerListObj.getDataPrinterList();
}
</script>
<script id="printerList-template" type="text/x-handlebars-template">
 		{{#if bundleList}}
				{{#bundleList}}
                	<div class="printer-block">
          			<div class="printer-title"><a href="#" id="{{key}}">{{key}}</a></div>
          			<div class="printer-img"><img src="<html:imagesPath/>color-laser.jpg" width="110" height="81" alt="{{key}}"></div>
        			</div>
         		{{/bundleList}}
                
           {{else}}    
            	<div><spring:message code="requestInfo.error.noRecordFound"/></div>        	
           {{/if}}

</script>
   
   
