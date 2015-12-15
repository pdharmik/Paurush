<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%-- <%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%> --%>
<%-- <style type="text/css"><%@ include file="../../css/mps.css" %></style> --%>
<%-- <%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%> --%>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%-- <portlet:renderURL var="updatePageCountURL">
	<portlet:param name="action" value="updatePageCounts"></portlet:param>
</portlet:renderURL> --%>
<script type="text/javascript" src="<html:rootPath/>js/mps.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>

<portlet:resourceURL id="updatePageCounts" var="updatePageCounts"> 
</portlet:resourceURL>

<div id="contentLtpc" style="min-width: 600px;" >
<!-- <div id="overlayPopup" style="display:none">
</div>
<div id="processingHintPopup" tabindex="-1" >
   &nbsp;&nbsp;<img style="margin-top: 10px;" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp; <spring:message code="processing"/><br>
</div>-->
     <div id="validationErrors" class="error" style="display: none;"></div>
     <div id="pageCntUpdtSuccess" class="info banner ok" style="display: none;"></div>
     		<div class="portletBlock bgSolid" id="pageCountPopupCont">	
				<div class="portletBlock infoBox rounded shadow split">
				
					<div class="columnsTwo">
					<div class="columnInner">
						<h4><spring:message code="deviceDetail.header.label.currentPageCounts"/></h4>
						<ul class="form">
							<li><label><spring:message code="deviceDetail.label.LTPC"/></label> 
							<span id="lastPgCnt_1"></span></li>
							<li><label><spring:message code="deviceDetail.label.dateAndTime"/></label> 
							<span id="lastPgRdDt_1"></span></li>
							
						</ul>
						
					</div>
				</div>
				<div class="columnsTwo">
					<div class="columnInner">
						<h4>&nbsp;</h4>
						<ul class="form" id="oldColorDiv">
								<li><label><spring:message code="deviceDetail.label.color"/></label> 
								<span id="lastColorPgCnt_1"></span></li>

								<li><label><spring:message code="deviceDetail.label.dateAndTime"/></label></label> 
								<span id="lastColorPgRdDt_1"></span></li>
							</ul>
						
					</div>
				</div>
				</div>
				
					 <div class="portletBlock infoBox rounded shadow split">
						
						<div class="columnsTwo">
						<div class="columnInner">
						
							<h4><spring:message code="deviceDetail.label.enterNewPageCounts"/></h4>
							
							<input type="hidden" id="assetId"/>
							
							<ul class="form">
								<li><label for="ltpc"><spring:message code="deviceDetail.label.LTPC"/></label>
									<input type="text" id="newLTPCPgCnt" class="w150"/>								 
									
									<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code='requestInfo.tooltip.LTPC'/>">
								</li>
							</p>
								<li><label for="dateTime"><spring:message code="deviceDetail.label.dateAndTime"/></label> 
									
									<input type="text" id="newPgRdDate" class="w150" readonly="readonly"/>
									<img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png"
									onclick="showCal('newPgRdDate', convertDateToString(lastPgRdDtinDateFmt.addDays(null)), convertDateToString(new Date()), true);" />
									
								</li>
								
							</ul>
							
						</div>	
						</div>
						<div class="columnsTwo">
						<div class="columnInner">
							<h4>&nbsp;</h4>
						<ul class="form" id="newColorDiv">
									<li><label for="color"><spring:message code="deviceDetail.label.color"/></label>										
										<input type="text" id="newColorPgCnt" class="w150"/>
										<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code='requestInfo.tooltip.Color'/>"/>
									</li>
									</p>
									<li><label for="dateTime1"><spring:message code="deviceDetail.label.dateAndTime"/></label>										
										<input type="text" id="newColorLTPCDate" class="w150" readonly="readonly"/>
										<img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" 
										onclick="showCal('newColorLTPCDate', convertDateToString(lastColorPgRdDtinDateFmt.addDays(null)), convertDateToString(new Date()), true);">										
									</li>
								</ul>
							
						</div>	
						</div>
					</div>
					<div class="buttonContainer">
						<button class="button_cancel" onclick="dialog.dialog('close');" id="btnCancel" type="button"><spring:message code="deviceDetail.button.cancel"/></button>&nbsp;
						<button class="button" onclick="javascript:validateCountPattern();" id="btnSubmit" type="button"><spring:message code="deviceDetail.button.submit"/></button>
					</div>
				<%-- </form:form> --%>
				
			</div>
</div>

<script type="text/javascript">
	var chkFlginLTPC=false;
	var ltpcDateTime;
	var colourDateTime;	
	var lastPgRdDtinDateFmt;
	var lastColorPgRdDtinDateFmt;
	
	
	function validateCountPattern() {
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTUPDATEPAGECOUNT%>'); 
		jQuery("#validationErrors").html("");
		jQuery("#validationErrors").hide();
		jQuery('.errorColor').removeClass('errorColor');
		
		var newColorPgCntVal=jQuery("#newColorPgCnt").val().trim();		
		var newLTPCPgCntVal=jQuery("#newLTPCPgCnt").val().trim();

		ltpcDateTime=jQuery("#newPgRdDate").val().trim();
		colourDateTime=jQuery("#newColorLTPCDate").val().trim();
		
		/******************** This section is for new color and new LTPC ***************/
		if((newColorPgCntVal.length !=0) || (newLTPCPgCntVal.length !=0))
			{
			var colorFlag="COLOR";
			var ltpcFlag="LTPC";
		
			/******Check whether new color field is numeric ********/
			if(lastColorPgCnt1.length != 0 && lastColorPgCnt1 != 0){
				//alert("last color pg cnt is empty");
				if(newColorPgCntVal.length ==0){
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadRequired'/></li>");
					jQuery("#newColorPgCnt").addClass('errorColor');
					return false;
					
				}else if(!(isDigitForPgCnt(newColorPgCntVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").append("<li><spring:message code='validation.error.notNumber'/></li>");
					jQuery("#newColorPgCnt").addClass('errorColor');
					return false;
				}else {
						if(! validateLTPCColorData(colorFlag))
						{
							return false;						
						}
					}
				}
			/******Check whether new ltpc field is numeric ********/			
				
				if(!isDigitForPgCnt(newLTPCPgCntVal))
				{
						jQuery("#validationErrors").show();
						jQuery("#validationErrors").append("<li><spring:message code='validation.error.notNumber'/></li>");
						jQuery("#newLTPCPgCnt").addClass('errorColor');
						return false;
				}else {
						if(! validateLTPCColorData(ltpcFlag))
						{
							return false;						
						}
					  }	
		}
		 
		/*********LTPC and COLOR CANT BE NULL***************/
		else {
			jQuery("#validationErrors").show();
			jQuery("#validationErrors").html("");			
			if(lastColorPgCnt1.length ==0){
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.ltpcReadRequired'/></li>");
				jQuery("#newLTPCPgCnt").addClass('errorColor');
			}else
			{
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadRequired'/></li>");
				jQuery("#newLTPCPgCnt").addClass('errorColor');
				jQuery("#newColorPgCnt").addClass('errorColor');
			}
			return false;
		}
		/*If everything is fine, go for submission*/
		fireAjax();/*Go for the data submission using ajax*/
	}	
	
	
	/************ This is to validate whether the data entered is greater than the existing values********/
	function validateLTPCColorData(flag)
	{	
		var newColorPgCntVal=jQuery("#newColorPgCnt").val().trim();
		var newLTPCPgCntVal=jQuery("#newLTPCPgCnt").val().trim();
		var ltpcDiff;
		var colorDiff;
		var no_of_day_diff=30;
		
		if(lastColorPgCnt1.length<=0)
		{	
			lastColorPgCnt1=0;
		}
		
		if(lastPgCnt1==""){
			lastPgCnt1=0;
		}
		
		if(flag=="LTPC")
		{
			/*Below if is for checking the whether new ltpc is less than current ltpc*/
			if((parseInt(lastPgCnt1)) > (parseInt(newLTPCPgCntVal)))
			{	
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LTPCValueLess'/></li>");				
				jQuery("#newLTPCPgCnt").addClass('errorColor');
				return false;				
			}
			else {
				ltpcDiff=parseInt(newLTPCPgCntVal) - parseInt(lastPgCnt1);				
				if(ltpcDiff > 50000)
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLTPCHigh'/></li>");					
					jQuery("#newLTPCPgCnt").addClass('errorColor');
					return false;
				}
				/*This section is for ltpc count diff lt than 10, so reject*/
				if(ltpcDiff<10)
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");					
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLTPCLow'/></li>");					
					jQuery("#newLTPCPgCnt").addClass('errorColor');
					return false;
				}				
				/*This section is for ltpc count diff gt than 50000, so reject*/				
				if(ltpcDiff > (no_of_day_diff*2000))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");					
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLTPCHigh'/></li>");					
					jQuery("#newPgRdDate").addClass('errorColor');
					return false;
				}
			}
			return true;
		}
		
		if(flag=="COLOR")
		{	
			if((parseInt(lastColorPgCnt1)) > (parseInt(newColorPgCntVal)))
			{
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");				
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadLess'/></li>");				
				jQuery("#newColorPgCnt").addClass('errorColor');
				return false;
			}
			
			if((parseInt(newColorPgCntVal)) > (parseInt(newLTPCPgCntVal)))
			{	
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");				
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
				jQuery("#newColorPgCnt").addClass('errorColor');
				return false;
			}
			
			colorDiff=parseInt(newColorPgCntVal) - parseInt(lastColorPgCnt1);
			if(colorDiff > 50000)
			{	
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");				
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableColorReadHigh'/></li>");				
				jQuery("#newColorPgCnt").addClass('errorColor');
				return false;
			}
			/*This section is for ltpc count diff lt than 10, so reject*/
			if(colorDiff<10)
			{	
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");				
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableColorReadLow'/></li>");				
				jQuery("#newColorPgCnt").addClass('errorColor');
				return false;
			}
			
			if(colorDiff > (no_of_day_diff*2000))
			{	
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");				
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableColorReadHigh'/></li>");				
				jQuery("#newPgRdDate").addClass('errorColor');
				return false;
			}
			return true;
		}
	}
	
	function fireAjax(){
		
		jQuery.ajax({
				url:'${updatePageCounts}',
				beforeSend: function()
				{	
					jQuery("#validationErrors").html("");
					jQuery("#validationErrors").hide();
					showOverlayPopup();
				},
				type:'POST',
				data: {
						newPageCount: jQuery('#newLTPCPgCnt').val().trim(),
						newPageReadDate: jQuery('#newPgRdDate').val().trim(),
						newColorPageCount: jQuery('#newColorPgCnt').val().trim(),
						newColorPageReadDate: jQuery('#newColorLTPCDate').val().trim(),
						selectedAssetId: assetRowId1
					},
				success: function(results){
					hideOverlayPopup();
					var obj2=null;
					try{
						 //obj2=jQuery.parseJSON(results);
						 obj2=results;
					}catch(e){
						;
						}					
					if(obj2 !=null){
						jQuery.each(obj2, function(name, value) {							
							if(name == "success")
							{
								jQuery("#pageCntUpdtSuccess").show();
								jQuery("#pageCntUpdtSuccess").html(value);
								if (window.PIE) {
					                 document.getElementById("btnCancel").fireEvent("onmove");
					             }
								
								window.parent.window.isSuccessfulUpdate=true;
							}
							else if(name == "error")
							{	
								jQuery("#validationErrors").show();
								jQuery("#validationErrors").html(value);
								window.parent.window.isSuccessfulUpdate=false;
							}
								
					});
							
						if(window.parent.window.isSuccessfulUpdate==true)
						{
							jQuery("#btnSubmit").hide();
							jQuery("#btnCancel").html("Close");	
						}
						
					}
					}
				});
		 }
	
	function writeData()
	{	
		if(lastColorPgCnt1.length>0)
		{
			//alert("inside pgcnt12 " + lastColorPgCnt1);
			window.document.getElementById("lastColorPgCnt_1").innerHTML=lastColorPgCnt1;
		}
		else{
			/*alert("Am hiding color div");*/
			jQuery("#oldColorDiv").hide();
			jQuery("#newColorDiv").hide();
		}
		
		window.document.getElementById("lastColorPgRdDt_1").innerHTML=lastColorPgRdDt1;
		window.document.getElementById("lastPgCnt_1").innerHTML=lastPgCnt1;
		window.document.getElementById("lastPgRdDt_1").innerHTML=lastPgRdDt1;
		adjustLTPCDateTime(lastPgRdDt1);
		adjustCOLORDateTime(lastColorPgRdDt1);
	}
	
	function adjustLTPCDateTime(lastPgRdDt1)
	{
		if(lastPgRdDt1.length !=0)
		{	
			lastPgRdDtinDateFmt=convertStringToDate(lastPgRdDt1);
		}
		else 
		{
			lastPgRdDtinDateFmt=new Date();	
		}
	}

	function adjustCOLORDateTime(lastColorPgRdDt1)
	{
		if(lastColorPgRdDt1.length !=0)
		{	
			lastColorPgRdDtinDateFmt=convertStringToDate(lastColorPgRdDt1);
		}
		else 
		{	
			lastColorPgRdDtinDateFmt=new Date();	
		}
	}
	
	function isDigitForPgCnt(s){ 
		var patrn=/^-{0,1}\d+$/; 
		if (!patrn.exec(s)) 
			return false; 
		return true; 
	}
	
	jQuery(document).ready(function() {
		jQuery('#validationErrors',window.document).empty();
		window.document.getElementById("validationErrors").style.display = 'none';
	});
	
</script>