<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>

<link rel="STYLESHEET" type="text/css" href="<html:rootPath/>js/rich-calendar/rich_calendar.css">
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rich_calendar.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_en.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_fr.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_de.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_es.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_it.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_ru.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_ja.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_ko.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_pt_BR.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_pt_PT.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_zh_CN.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_zh_TW.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_pl.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_tr.js"></script>
<!-- 
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_no.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_da.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_sw.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_fi.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_nl.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_tr.js"></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/rc_lang_pl.js"></script>
-->
<script type="text/javascript" src="<html:rootPath/>js/rich-calendar/domready.js"></script>
<script type="text/javascript">
var popupCal = null;

var format = '%j %M %Y';

// show calendar
function show_cal(textFieldId, startDate, endDate, showTime) {
	if (popupCal) return;
	var text_field = document.getElementById(textFieldId);
	popupCal = new RichCalendar();
	if(!showTime){
		showTime = false;
	}
	popupCal.show_time = showTime;
	if (language == 'zh' || language == 'pt') {
		popupCal.language = language + '_' + country;
	} else {
		popupCal.language = language;
	}
	popupCal.start_week_day = parseInt(popupCal.text('startDay'));
	format = popupCal.text('dateFormat'); 
	popupCal.user_onchange_handler = cal_on_change;
	popupCal.user_onclose_handler = cal_on_close;
	popupCal.user_onautoclose_handler = cal_on_autoclose;
	
	popupCal.parse_date(text_field.value, popupCal.get_date_format(format));
	
    var today = new Date();
    if (startDate != null && startDate != "") {
        popupCal.start_date = convertStringToDate(startDate);
    }
    if (endDate != null && endDate != "") {
        popupCal.end_date = convertStringToDate(endDate);
    }
    
	popupCal.show_at_element(text_field, "adjust_left-adjust_bottom");
	popupCal.text_field_id = textFieldId;
	if(showTime && (text_field.value == null || text_field.value == "")){
		popupCal.hours_obj.value = today.getHours();
		popupCal.mins_obj.value = today.getMinutes();
	}
}
	

function showcal(textFieldId, startDate, endDate, showTime) {
	
	if (popupCal) return;
	var text_field = document.getElementById(textFieldId);

	popupCal = new RichCalendar();
	
	if(!showTime)
	{
		popupCal.show_time = false;	
	}
	else popupCal.show_time = true;
	
	if (language == 'zh' || language == 'pt') {
		popupCal.language = language + '_' + country;
	} else {
		popupCal.language = language;
	}
	
	popupCal.start_week_day = parseInt(popupCal.text('startDay'));
	
	if(!showTime)
	{
		custFormat = popupCal.text('dateFormat');
	}
	else custFormat = popupCal.text('dateFormat') + " %H:%i:%s";
	
	var today = new Date();
	
	
    if (startDate != null && startDate != "") {
    	//alert("Start date "+startDate);
        popupCal.start_date = convertStringToDate(startDate);
    }
    if (endDate != null && endDate != "") {
        popupCal.end_date = convertStringToDate(endDate);
    }
    
    popupCal.user_onchange_handler = calOnChange;	
	popupCal.user_onclose_handler = cal_on_close;
	popupCal.user_onautoclose_handler = cal_on_autoclose;	
	
	popupCal.parse_date(text_field.value, custFormat);
	
	popupCal.show_at_element(text_field, "adjust_left-adjust");
	popupCal.text_field_id = textFieldId;
		
	if(showTime && (text_field.value == null || text_field.value == "")){
		popupCal.hours_obj.value = today.getHours();
		popupCal.mins_obj.value = today.getMinutes();
	}	
}

//user defined onchange handler for source

function calOnChange(cal, object_code) {
	
	if(cal.show_time) format = cal.get_date_format(custFormat);
	if (object_code == 'select' || object_code == 'clear') {
		var fieldId = cal.get_text_field_id();
		var targetObject = document.getElementById(fieldId);
		if (cal.date) {
			targetObject.value = cal.get_formatted_date(custFormat);
		} else {
			targetObject.value = '';
		}
		cal.hide();
		popupCal = null;
        // trigger onFocus event
        targetObject.focus();
        targetObject.blur();
	}
}

//user defined onchange handler (when "Select" is clicked)
function cal_on_change(cal, object_code) {
	if(cal.show_time) format = cal.get_date_format(format);
	if (object_code == 'select' || object_code == 'clear') {
		var fieldId = cal.get_text_field_id();
		var targetObject = document.getElementById(fieldId);
		if (cal.date) {
			targetObject.value = cal.get_formatted_date(format);
		} else {
			targetObject.value = '';
		}
		cal.hide();
		popupCal = null;
        // trigger onFocus event
        targetObject.focus();
        targetObject.blur();
	}
}

// user defined onclose handler
function cal_on_close(cal) {
	cal.hide();
	popupCal = null;
}

// user defined onclose handler (used in pop-up mode - when auto_close is true)
function cal_on_autoclose(cal) {
	popupCal = null;
}

function reloadDateAction(dateInputId, dateStr, action) {
    var selectedDateStr = formatDateToDefault(document.getElementById(dateInputId).value);
    if (document.getElementById(dateInputId).value == "") {
    	invalidateDateActionLink(dateInputId, 'PREV');
        invalidateDateActionLink(dateInputId, 'NEXT');
        return;
    }

    if (dateStr != null) {
	    var selectedDate = convertStringToDate(selectedDateStr);
	    var date = convertStringToDate(dateStr);
    }
    
    if (action == "PREV") {
        if (dateStr != null && selectedDate <= date) {
        	invalidateDateActionLink(dateInputId, 'PREV');
        } else {
        	validateDateActionLink(dateInputId, 'PREV');
        }
    } else if (action == "NEXT") {
        if (dateStr != null && selectedDate >= date) {
        	invalidateDateActionLink(dateInputId, 'NEXT');
        } else {
        	validateDateActionLink(dateInputId, 'NEXT');
        }
    }
}

function clickDateAction(dateFieldId, type) {
	if (type == 'PREV') {
		moveDate(dateFieldId, -1);
	} else if (type == 'NEXT') {
        moveDate(dateFieldId, 1);
	} else return;
	document.getElementById(dateFieldId).focus();
	document.getElementById(dateFieldId).blur();
}

function validateDateActionLink(dateInputId, actionType) {
	var linkId;
	if (actionType == 'PREV') {
		linkId = 'prev' + dateInputId.substring(0,1).toUpperCase() + dateInputId.substring(1);
	} else if (actionType == 'NEXT') {
		linkId = 'next' + dateInputId.substring(0,1).toUpperCase() + dateInputId.substring(1);
	} else return;
	linkObj = document.getElementById(linkId);
	linkObj.onclick = function() {
		clickDateAction(dateInputId, actionType);
	};
	linkObj.className = "validDateAction";
}

function invalidateDateActionLink(dateInputId, actionType) {
    var linkId;
    if (actionType == 'PREV') {
        linkId = 'prev' + dateInputId.substring(0,1).toUpperCase() + dateInputId.substring(1);
    } else if (actionType == 'NEXT') {
        linkId = 'next' + dateInputId.substring(0,1).toUpperCase() + dateInputId.substring(1);
    } else return;
    linkObj = document.getElementById(linkId);
    linkObj.onclick = null;
    linkObj.className = "invalidDateAction";
}

//Added for CI13.10 BRD13-10-27 --STARTS
function convertDateFormat(srcFormat, srcSeparator, date, tgtFormat, tgtSeparator) {
    var dateElmts = date.split(srcSeparator);
    var srcFormatElmts = srcFormat.split(srcSeparator);
    for (var i = 0; i < 3; i ++) {
        switch (srcFormatElmts[i].substring(0, 1).toUpperCase()) {
        case 'M':
        	var month = dateElmts[i];
            break;
        case 'D':
        	 var day = dateElmts[i];
            break;
        case 'Y':
        	var year = dateElmts[i];
            break;
        default:
            //alert("Invalid date format!");
        }
    }
    var localizedDate = '';
    var formatElmts = tgtFormat.split(tgtSeparator);

    for (var j = 0; j < 3; j ++) {
        switch (formatElmts[j].substring(0, 1).toUpperCase()) {
        case 'M':
            localizedDate = localizedDate + month;
            break;
        case 'D':
            localizedDate = localizedDate + day;
            break;
        case 'Y':
            localizedDate = localizedDate + year;
            break;
        default:
            //alert("Invalid date format!");
        }
        
        if (j < 2) {
            localizedDate = localizedDate + tgtSeparator;
        }
    }
    return localizedDate;
}

function localizeDate(date) {
    localizedDate = convertDateFormat("mm/dd/yyyy", "/", date, dateFormat, separator);
    return localizedDate;
}

function formatDateToDefault(date) {
	var dateDefaultFormat = convertDateFormat(dateFormat, separator, date, "mm/dd/yyyy", "/");
	return dateDefaultFormat;
}
//Added for CI13.10 BRD13-10-27 --ENDS


			//validatePartnerAgreement() function added by sankha for Defect # 2258
			function validatePartnerAgreement(){
				var result = true;
				var selectagrmt = document.getElementById('selectPartnerAgreement');
				var prtagrmt = selectagrmt.options[selectagrmt.selectedIndex].text;
				if(!trim(prtagrmt)){
					result = false;
					callOmnitureFieldErrorAction('Partner Agreement');
					showError("<spring:message code='claim.errorMsg.partnerAgreementNotNull'/>",null, true);
					jQuery('#selectPartnerAgreement').addClass('errorColor');
				}
				return result;
			}
			//Changed for CI Defect # 10981
			function validateCustomerAccount(){
				var result = true;
				var customerAccount;
				var customerAddress;
				var newCustomerAccountFlag = document.getElementById('newCustomerAccountFlagHidden').value;
				if(document.getElementById('customerAccount1').innerHTML=="" || document.getElementById('customerAddress1').innerHTML==""){
				if(document.getElementById('customerAccount2')==null ||document.getElementById('customerAddress2')==null)
					{					
					 customerAccount = document.getElementById('divCustomerInfo2InnerHTMLAccount').innerHTML;
					 customerAddress = document.getElementById('divCustomerInfo2InnerHTMLAddress').innerHTML;
					}
				else
					{					
					if(document.getElementById('customerAccount2').value!="" && document.getElementById('customerAddress2').value!="")
						{
					 	customerAccount = document.getElementById('customerAccount2').value;
					 	customerAddress = document.getElementById('customerAddress2').value;
						}
					else
						{
						customerAccount="";
						customerAddress="";
						}
					}
				}
				else
					{
					customerAccount=document.getElementById('customerAccount1').innerHTML;
					customerAddress=document.getElementById('customerAddress1').innerHTML;
					}
				if(newCustomerAccountFlag == true||customerAccount==""||customerAddress==""){
					if(!trim(customerAccount)){
						result = false;
						callOmnitureFieldErrorAction('Customer Account');
						showError("<spring:message code='claim.errorMsg.customerAccountNotNull'/>",null, true);
						jQuery('#customerAccount2').addClass('errorColor');
					}
					if(!trim(customerAddress)){
						result = false;
						callOmnitureFieldErrorAction('Customer Address');
						showError("<spring:message code='claim.errorMsg.customerAddressNotNull'/>",null, true);
						jQuery('#customerAddress2').addClass('errorColor');
					}
				}
				
				return result;
			}
</script>