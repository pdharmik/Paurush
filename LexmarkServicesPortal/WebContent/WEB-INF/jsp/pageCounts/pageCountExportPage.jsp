
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>

input:focus, input.ieFocusHack, input.focus, .form input:focus, .form input.ieFocusHack, textarea:focus, .form textarea:focus, .form textarea.ieFocusHack, .form select:focus, .form select.ieFocusHack, .displayGrid input:focus, .displayGrid input.ieFocusHack {
	background-color:transparent!important;
	border:0px #FC3 solid!important;
	padding:0px!important;
}
#endDate11,#endDate12{
width:auto !important;
}
#endDate1,#endDate2{
 list-style-type: none;
 margin-left: 15px;
}


input[type="radio"] {
    outline: none!important;
input[type="text"] {
    border:1px #000 solid !important;
    padding:3px !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="totalContent" class="div-style28">
<div class="">
			<div id="rptAdminScdlMntnTitle"  class="div-style75">
        		<span class="orangeSectionTitles">
            		<spring:message code="meterRead.label.pageCountExport" />
        		</span><br/><br/>
	    	</div>

	    	<div  class="labelBold div-style76">
				<spring:message code="meterRead.label.exportPageCountsFor"/>
			</div>
			<div class="div-style77"></div>
			<div>
				<dl><dd><input id="exportType" name="exportType" type="radio" value="Manual" checked="checked">&nbsp;<spring:message code="meterRead.label.manual"/></input></dd></dl>
				<dl><dd><input id="exportType" name="exportType" type="radio" value="BookMarked">&nbsp;<spring:message code="meterRead.label.bookmarkedDevices"/></input></dd></dl>
				</div>
				<div>
				<dl><dd><input id="exportType" name="exportType" type="radio" value="Missing Manual">&nbsp;<spring:message code="meterRead.label.missingManual"/></input>
				<li id="endDate1"><label for="days"><spring:message code="meterRead.label.noofDays"/></label>&nbsp;&nbsp;&nbsp;<span><input id="endDate11" class="" type="text" value="30"></input></span></li>
				</dd></dl>
				</div>
				<div>
				<dl><dd><input id="exportType" name="exportType" type="radio" value="Missing Automated">&nbsp;<spring:message code="meterRead.label.missingAutomated"/></input>
				<li id="endDate2"><label for="days"><spring:message code="meterRead.label.noofDays"/></label>&nbsp;&nbsp;&nbsp;<span><input id="endDate12" class="" type="text" value="7"></input></span></li>
				</dd></dl>
				</div>
				<div>
				<dl><dd><input id="exportType" name="exportType" type="radio" value="Missing All">&nbsp;<spring:message code="meterRead.label.missingAll"/></input></dd></dl>
				</div>
			</div>
			
			<div id="rptAdminScdlMntnTitleBtns">
				<table width="100%">
				<tr>
					<td align="right">
						<button class="button_cancel" onclick="dialog.dialog('close');">
    						<spring:message code="button.cancel" />
    					</button>&nbsp;
						<button class="button" onclick="doExport();">
    						<spring:message code="button.submit"/>
    					</button>
					</td>
				</tr>
				</table>
			</div>
	</div>


<script type="text/javascript">

jQuery(document).ready(function(){
	
	jQuery('#endDate1').hide();
	jQuery('#endDate2').hide();

	jQuery('input[type=radio][name=exportType]').click(function() {  
        var radioVal = this.value      
           //alert("changed "+radioVal);
        if(radioVal=="Manual"){
        	jQuery('#endDate1').hide();
        	jQuery('#endDate11').val('30');
        	jQuery('#endDate12').val('7');
        	jQuery('#endDate2').hide();
        }
        if(radioVal=="BookMarked"){
        	jQuery('#endDate1').hide();
        	jQuery('#endDate11').val('30');
        	jQuery('#endDate12').val('7');
        	jQuery('#endDate2').hide();
        }
        if(radioVal=="Missing Manual"){
        	jQuery('#endDate1').show();
        	jQuery('#endDate11').show();
        	jQuery('#endDate12').val('7');
        	jQuery('#endDate2').hide();
        }  
        if(radioVal=="Missing Automated"){
        	jQuery('#endDate2').show();
        	jQuery('#endDate12').show();
        	jQuery('#endDate11').val('30');
        	jQuery('#endDate1').hide();
        } 
        if(radioVal=="Missing All"){ 
        	jQuery('#endDate11').val('30');
        	jQuery('#endDate1').hide();
        	jQuery('#endDate12').val('7');
        	jQuery('#endDate2').hide();
        }       
    });
});

  function 	doExport(){
	  var typelist =document.getElementsByName("exportType");
	  var type = "";
	 
	  var needReturnAsFalse = false;
	  for(var i=0;i<typelist.length;i++) 
	  { 
	  	if(typelist[i].checked){
	  		type =  typelist[i].value;
	  		 if(type=="Missing Manual"){
	  			var day1 = document.getElementById("endDate11").value;
	  			//beginDate=convertDateToString(new Date().addDays(null));
	  		  	//endDate=convertDateToString(new Date().addDays(-day1));
	  			if(document.getElementById("endDate11").value == '' || document.getElementById("endDate11").value == null || document.getElementById("endDate11").value == 0){
	  				jQuery("#endDate11").val('30');
	  				day1 = 30;
	  			}
	  		}
	  		if(type=="Missing Automated"){
	  			 var day1 = document.getElementById("endDate12").value;
	  			//beginDate=convertDateToString(new Date().addDays(null));
	  		  	//endDate=convertDateToString(new Date().addDays(-day1));
	  			if(document.getElementById("endDate12").value == '' || document.getElementById("endDate12").value == null || document.getElementById("endDate12").value == 0){
	  				jQuery("#endDate12").val('7');
	  				day1 = 7;
	  			}
	  		}
	  		if(type=="Missing All"){
	  			 //day1 = document.getElementById("endDate13").value;
	  			//beginDate=convertDateToString(new Date().addDays(null));
	  		  	//endDate=convertDateToString(new Date().addDays(-day1));
	  		} 
	  	 }	
	  }
	  //alert("type is "+type);
	  //alert("exportType is "+exportType);
	  //callOmnitureAction('Page Counts', 'Page Count Export type=' + type);
	  var url = document.getElementById("exportPageCountUrl").value + "&exportType=";
	 	window.location=url + type + "&noOfDays=" + day1;
	}
  
  

</script>
