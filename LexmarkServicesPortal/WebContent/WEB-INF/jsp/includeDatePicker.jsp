<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/localeDateFormat.js"></script>
<script language="javascript" type="text/javascript" src="<html:rootPath/>js/rich-calendar/domready.js"></script>

<c:set var="language" value="${pageContext.request.locale.language}" />
<c:set var="country" value="${pageContext.request.locale.country}" />

<script type="text/javascript">

var dateFormat = "";
var separator = "";
var language = "${language}";
var country = "${country}";

for (var i = 0; i < localeDateFormats.length; i ++) {
    if (localeDateFormats[i].language == language || localeDateFormats[i].language == language + "_" + country) {
        dateFormat = localeDateFormats[i].dateFormat;
        separator = localeDateFormats[i].separator;
        break;
    }
}
var todayStr = convertDateToString(new Date());
if (dateFormat == "") {
    dateFormat = "mm/dd/yyyy";
    separator = "/";
}

var popupCal = null;

var format = '%j %M %Y';

// show calendar
function show_cal(textFieldId, startDate, endDate) {
	
	
	if (popupCal) return;
	var text_field = document.getElementById(textFieldId);
	
	popupCal = new RichCalendar();
	
	popupCal.show_time = false;
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

	popupCal.parse_date(text_field.value, format);
	
    /*var today = new Date();*/
	if (startDate != null && startDate != "") {
    	
        popupCal.start_date = convertStringToDate(startDate);
    }
    if (endDate != null && endDate != "") {
        popupCal.end_date = convertStringToDate(endDate);
    }
	popupCal.show_at_element(text_field, "adjust_left-adjust");
	popupCal.text_field_id = textFieldId;
}

Date.prototype.addDays = Date.prototype.addDays || function(days) {
	
	this.setDate(this.getDate());
	return this;
}
//show Calendar for LTPC
	
function showCal(textFieldId, startDate, endDate, showTime) {
	
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

//user defined onchange handler
function cal_on_change(cal, object_code) {
	if (object_code == 'day') {
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

//user defined onchange handler for LTPC

function calOnChange(cal, object_code) {
	
	if (object_code == 'day') {
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


// user defined onclose handler
function cal_on_close(cal) {
	cal.hide();
	popupCal = null;
}

// user defined onclose handler (used in pop-up mode - when auto_close is true)
function cal_on_autoclose(cal) {
	popupCal = null;
}

/*
 * date: a String in a pattern of 'MM/dd/yyyy'
 * tgtFormat: a String of date format, such as dd/MM/yyyy
 * separator: a String used to separate month, day, and year, such as "/", or "-". 
 */
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
            /*alert("Invalid date format!");*/
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

function moveDate(id, number) {
	if (isNaN(number)) {
		/*alert("Invalid number!");*/
		return;
	}
	if (document.getElementById(id).value == '') {
		/*alert("<spring:message code='richCalendar.chooseDate'/>");*/
		return;
	}
	var date = formatDateToDefault(document.getElementById(id).value);
	var currentDate = convertStringToDate(date);
	var targetDate = new Date(currentDate.getTime() + number * 86400000);
	document.getElementById(id).value = localizeDate(convertDateToString(targetDate));
}

function convertDateToString(date) {
	
	var fullYear = date.getFullYear();
	var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;

    return (month + "/" + day + "/" + fullYear);
}

/***Added for MPS***/
function convertDateToStringWithTime(date)
{
	var fullYear = date.getFullYear();
	var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    
    var hours = date.getHours();
    if(hours<9){
    hours = "0" + hours;
    }
    
    var minutes = date.getMinutes();
    if(minutes<9){
    	minutes = "0" + minutes;
    }
    
    var seconds = date.getSeconds();
    if(seconds<9){
    	seconds = "0" + seconds;
    }
    
    return (month + "/" + day + "/" + fullYear + " " + hours +":" + minutes + ":" + seconds);
}
function convertStringToDate(dateStr) {
	var dateElmts = dateStr.split("/");
	var date = new Date(parseInt(dateElmts[2], 10), (parseInt(dateElmts[0], 10) - 1), parseInt(dateElmts[1], 10));
	return date;
}

function convertStringToDateWithTime(dateTimeStr)
{ 	
	/*alert(dateTimeStr);*/
	var dateElmts = dateTimeStr.split("/");
	/*alert(parseInt(dateElmts[2], 10));*/

	var date = new Date(parseInt(dateElmts[2], 10), (parseInt(dateElmts[0], 10) - 1), parseInt(dateElmts[1], 10));
	/*alert(date);*/
	return date;
	/*var date = new Date(parseInt(dateElmts[2], 10), (parseInt(dateElmts[0], 10) - 1), parseInt(dateElmts[1], 10));*/
	
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
}</script>