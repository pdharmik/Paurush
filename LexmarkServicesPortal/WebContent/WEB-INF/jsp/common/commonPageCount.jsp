<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<portlet:resourceURL var="addPageCount" id="retrievePageCountsForAsset"></portlet:resourceURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div>
              <div class="portletBlock infoBox rounded shadow">
              <c:choose>
              <c:when test="${pageFlow eq 'addone'}">
              <p><spring:message code="requestInfo.info.pageCountsInfoAdd"/></p>
              </c:when>
              <c:otherwise>
              <p><spring:message code="requestInfo.info.pageCountsInfo"/></p>
              </c:otherwise>
              </c:choose>
              <div class="columnInner separatorV">
                  <div id="pageCountsDiv" class="width-100per">
					<div id="pageCounts" class="gridbox_light pageCounts">
						<table id="pageCountsTable">
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
	  							
	  						
	    				</div><!-- mygrid_container -->
	              	 	</div>
	              	 	</div>

	              	 	
				<div class="columnsTwo">
					<br/>
					 <c:if test="${pageFlow eq 'addone'}">
					<button id="addnew" type="button" class="button floatR" onclick="addPageCounts();"><spring:message code='button.addNew'/></button>
					</c:if>
					</div>
				
				</div>
            </div>
          </div>
          <script type="text/javascript">
          var currentURL = window.location.href;
          var pageCountsArray = new Array(); 
          var pageCountsArrayKey = new Array();
          var i=0; 
          var altclass="";
    	  <c:forEach items="${pageCountsList}" var="item">
    	  pageCountsArray[i]= '${item.value}';
    	  pageCountsArrayKey[i]= '${item.key}';
    	  i++;
    	  </c:forEach> 
					<c:if test="${pageFlow eq 'addone'}">
					<c:if test="${manageAssetForm.assetDetail.pageCounts == null || manageAssetForm.assetDetail.pageCounts ==''}">
					<c:if test="${pageCountsList != null}">
					var defaultRow='<select id="select_id_'+0 +'"><option value=\"\"></options>';
					var options="";
						for(var i=0 ; i<pageCountsArray.length;i++){
						options=options+'<option value="'+pageCountsArrayKey[i]+'">' + pageCountsArray[i]+ '</options>';
						}
					var firstRow=defaultRow+options+"</select>";
					var secondRow="<div class=\"w160 floatL\"><input type=\"text\" readonly=\"true\" style=\"float:left;\" id=\"rwid_"+ 0 +"\" class=\"w150\" onchange=\"shwDateCommon('rwid_"+ 0 +"', 'pageCountsDateDelete"+ 0 +"');\" onblur=\"shwDateCommon('rwid_"+ 0 +"', 'pageCountsDateDelete"+ 0 +"');\" /><img id=\"img\" class=\"cal_date ui_icon_sprite calendar-icon\" src=\"" + "/LexmarkServicesPortal/images/transparent.png" + "\" onClick=\"showCal('rwid_"+ 0 +"' , 'Date', 'Date', true);\" onFocus=\"this.className='';\"><img id=\"pageCountsDateDelete"+ 0 +"\" class=\"ui_icon_sprite ui-icon-closethick\" src=\"<html:imagesPath/>transparent.png\" style=\"display:none\" onClick=\"removeDateCommon('rwid_"+ 0 +"', 'pageCountsDateDelete"+ 0 +"');\" /></div>";
					var thirdRow="<input type=\"text\" id='newCount_"+0+"' class=\"w150\"/>";
					var forthRow="<a href=\"javascript:deleteGridRow('0');\"><img class=\"ui_icon_sprite trash-icon\" style=\"float:none;\" src=\'"+"/LexmarkServicesPortal/images/transparent.png"+"'  width=\"15\" height=\"15\"/>";
					jQuery('#pageCountsTable tbody').append("<tr id=\"rowCount_"+0+"\""+"class=\"rowspace\" >"+
							"<td>" +firstRow +"</td>"+"<td>"+secondRow +"</td>"+
							"<td>"+thirdRow +"</td>"+"<td>"+forthRow +"</td>"+"</tr>");
					</c:if>
					</c:if>
					</c:if>
					var id = jQuery('#pageCountsTable tbody').children('tr').length;
					addAlternateClass();
          function addPageCounts(){        	  
        	  id = jQuery('#pageCountsTable tbody').children('tr').length;
        	  var altclass="";
        	  while(document.getElementById("select_id_"+id)){
        		  id = id+1;
        	  }
        	  <c:if test="${pageCountsList != null}">
				var defaultRow='<select id="select_id_'+id +'"><option value=\"\"></options>';
				var options="";
					for(var i=0 ; i<pageCountsArray.length;i++){
					options=options+'<option value="'+pageCountsArrayKey[i]+'">' + pageCountsArray[i]+ '</options>';
					}
				var firstRow=defaultRow+options+"</select>";
				var secondRow="<div class=\"w160 floatL\"><input type=\"text\" readonly=\"true\" style=\"float:left;\" id=\"rwid_"+ id +"\" class=\"w150\" onchange=\"shwDateCommon('rwid_"+ id +"', 'pageCountsDateDelete"+ id +"');\" onblur=\"shwDateCommon('rwid_"+ id +"', 'pageCountsDateDelete"+ id +"');\" /><img id=\"img\" class=\"cal_date ui_icon_sprite calendar-icon\" src=\"" + "/LexmarkServicesPortal/images/transparent.png" + "\" onClick=\"showCal('rwid_"+ id +"' , 'Date', 'Date', true);\" onFocus=\"this.className='';\"><img id=\"pageCountsDateDelete"+ id +"class=\"ui_icon_sprite ui-icon-closethick\""+"\" src=\"<html:imagesPath/>transparent.png\" height=\"17px\" width=\"17px\" style=\"display:none\" onClick=\"removeDateCommon('rwid_"+ id +"', 'pageCountsDateDelete"+ id +"');\" /></div>";
				var thirdRow="<input type=\"text\" id='newCount_"+id+"' class=\"w150\"/>";
				var forthRow="<a href=\"javascript:deleteGridRow("+id+");\"><img class=\"ui_icon_sprite trash-icon\" style=\"float:none;\" src=\'"+"/LexmarkServicesPortal/images/transparent.png"+"'  width=\"15\" height=\"15\"/>";
				jQuery('#pageCountsTable tbody').append("<tr id=\"rowCount_"+id+"\""+" class=\"rowspace\" >"+
							"<td>"+	firstRow +"</td>"+"<td>"+secondRow +"</td>"+
							"<td>"+thirdRow +"</td>"+"<td>"+forthRow +"</td>"+"</tr>");
				</c:if>
				addAlternateClass();
          }
function deleteGridRow(x){
  	  jQuery("#rowCount_"+x).remove();
  	addAlternateClass();
}
function pageCountValidate(){
	for(i=0;i<=id;i++){	
		var idGeneratePT="select_id_"+i;
		var idGenerateD="rwid_"+i;
		var idGenerateV="newCount_"+i;
		jQuery("#"+idGenerateD).removeClass('errorColor');
		jQuery("#"+idGeneratePT).removeClass('errorColor');
		jQuery("#"+idGenerateV).removeClass('errorColor');
		if(document.getElementById(idGeneratePT) != null && document.getElementById(idGenerateD)!=null &&  document.getElementById(idGenerateV)!=null){
			
			if(pageType=="addAsset"&&currentURL.indexOf('/fleet-management') == -1){
			if(jQuery("#"+idGeneratePT).val() != ''){
			if(jQuery("#"+idGenerateD).val() == '' && jQuery("#"+idGenerateV).val()==''){
				jQuery('#errorDiv').append('<li><strong><spring:message code="validation.properPageCount"/> '+(i+1)+' </strong></li>'); //message changed
				jQuery("#"+idGenerateD).addClass('errorColor');
				jQuery("#"+idGenerateV).addClass('errorColor');
				return false;
				}
			}
			}
			if(pageType=="addAsset"&&currentURL.indexOf('/fleet-management') == -1){
		if(jQuery("#"+idGenerateD).val() != '' && jQuery("#"+idGeneratePT).val() == ''){
			jQuery('#errorDiv').append('<li><strong><spring:message code="validation.properPageCount.pageCountsValue"/> '+(i+1)+' </strong></li>');
			jQuery("#"+idGeneratePT).addClass('errorColor');
			return false;
			}
			}
		if(jQuery("#"+idGenerateV).val() != '' && jQuery("#"+idGeneratePT).val() == ''){
			jQuery('#errorDiv').append('<li><strong><spring:message code="validation.properPageCount.pageCountsValue"/> '+(i+1)+' </strong></li>');
			jQuery("#"+idGeneratePT).addClass('errorColor');
			return false;
			}
		//alert("idGenerateV="+(jQuery("#"+idGenerateV).val()));
		if((jQuery("#"+idGenerateV).val() != '') && (!isNumeric(jQuery("#"+idGenerateV).val()))) {
			jQuery('#errorDiv').append('<li><strong><spring:message code="validation.properPageCount.numeric"/> '+(i+1)+' </strong></li>');
			jQuery("#"+idGenerateV).addClass('errorColor');
			return false;
		}
		}
		if(pageType=="addAsset"&&currentURL.indexOf('/fleet-management') == -1){
		if(jQuery("#"+idGeneratePT).val() == ''  && id !=1) {
            jQuery('#errorDiv').append('<li><strong><spring:message code="validation.properPageCount.pageCountsValue"/> '+(i+1)+' </strong></li>');
            jQuery("#"+idGeneratePT).addClass('errorColor');
            return false;
    }
	}
		if(pageType=="addAsset"&&currentURL.indexOf('/fleet-management') == -1){
		if(jQuery("#"+idGeneratePT).val() != ''){
			if(jQuery("#"+idGenerateD).val() == ''){
				jQuery('#errorDiv').append('<li><strong><spring:message code="validation.properPageCount.date"/> '+(i+1)+' </strong></li>'); //message changed
				jQuery("#"+idGenerateD).addClass('errorColor');
				return false;
				}
			else if(jQuery("#"+idGenerateV).val()==''){
				jQuery('#errorDiv').append('<li><strong><spring:message code="validation.properPageCount.newPageCounts"/> '+(i+1)+' </strong></li>'); //message changed
				jQuery("#"+idGenerateV).addClass('errorColor');
				return false;
				}
			}
		}
	}
	
	
 }
function dublicatePCCheck(pageCountType){	
	var dublicatePC=false;
	for (var i=0; i<pageCountType.length; i++) {         
		for (var j=i+1; j<pageCountType.length; j++) {
			if (pageCountType[i]==pageCountType[j]) {
				jQuery("#select_id_"+i).addClass('errorColor');
				jQuery("#select_id_"+j).addClass('errorColor');
				dublicatePC=true;
			}
	}
	}
	
	if(dublicatePC){
		jQuery('#errorDiv').append('<li><strong><spring:message code="requestInfo.label.validation.dublicatePageCount"/></strong></li>');
		return false;
	}else{
		
		return true;
	}
	}
	
function addAlternateClass(){
	var i=1;
	jQuery('#pageCountsTable tbody tr.rowspace').each(function (){
		if (i%2==0){
			jQuery(this).removeClass('ev_light');
			jQuery(this).addClass('odd_light');
			i++;	
		}
		else {
			jQuery(this).removeClass('odd_light');
			jQuery(this).addClass('ev_light');
			i++;
		}
		
	});
}

</script>