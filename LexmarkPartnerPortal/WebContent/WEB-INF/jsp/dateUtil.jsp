<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/localeDateFormat.js"></script>

<c:set var="language" value="${pageContext.request.locale.language}"/>
<c:set var="country" value="${pageContext.request.locale.country}"/>

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
	var todayDate = new Date();
	var todayStr = convertDateToString(todayDate);
	if (dateFormat == "") {
	    dateFormat = "mm/dd/yyyy";
	    separator = "/";
	}

	Date.prototype.addDays = Date.prototype.addDays || function(days) {
		this.setDate(this.getDate() + days);
		return this;
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
            	alert("Invalid date format!");
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
            	alert("Invalid date format!");
        	}
        
        	if (j < 2) {
            	localizedDate = localizedDate + tgtSeparator;
        	}
    	}
    	return localizedDate;
	}

	// format date (String) from "mm/dd/yyyy" to client's date format
	function localizeDate(date) {
    	localizedDate = convertDateFormat("mm/dd/yyyy", "/", date, dateFormat, separator);
    	return localizedDate;
	}

	// convert from Date to string formatted client's date format
	function localizeFormatDate(date, includeTime){
		if(date.constructor == Date){
			var localizedStr;
			localizedStr = convertDateFormat("mm/dd/yyyy", "/", convertDateToString(date), dateFormat, separator);

			if (includeTime) {
				var hours = date.getHours();
				var minutes = date.getMinutes();
				var seconds = date.getSeconds();
				if (hours < 10) hours = "0" + hours;
				if (minutes < 10) minutes = "0" + minutes;
				if (seconds < 10) seconds = "0" + seconds;
				localizedStr += " " + hours + ":" + minutes + ":" + seconds; 
			}

			return localizedStr;
		}
	}

	// format date (String) as "mm/dd/yyyy"
	function formatDateToDefault(date) {
		var dateDefaultFormat = convertDateFormat(dateFormat, separator, date, "mm/dd/yyyy", "/");
		return dateDefaultFormat;
	}

	function moveDate(id, number) {
		if (isNaN(number)) {
			alert("Invalid number!");
			return;
		}
		if (document.getElementById(id).value == '') {
			alert("<spring:message code='richCalendar.chooseDate'/>");
			return;
		}
		var date = formatDateToDefault(document.getElementById(id).value);
		var currentDate = convertStringToDate(date);
		var targetDate = new Date(currentDate.getTime() + number * 86400000);
		document.getElementById(id).value = localizeDate(convertDateToString(targetDate));
	}

	// convert Date to String (mm/dd/yyyy)
	function convertDateToString(date) {
		var fullYear = date.getFullYear();
		var month = date.getMonth() + 1;
    	var day = date.getDate();
    	if (month < 10) month = "0" + month;
    	if (day < 10) day = "0" + day;

    	return (month + "/" + day + "/" + fullYear);
	}

	// convert String ("mm/dd/yyyy") to Date
	function convertStringToDate(dateStr) {
		var dateElmts = dateStr.split("/");
		var date = new Date(parseInt(dateElmts[2], 10), (parseInt(dateElmts[0], 10) - 1), parseInt(dateElmts[1], 10));
		return date;
	}

	// localize date & time
	function localizeDateTime(standardDateTime) {
		var dateTime = standardDateTime.split(" ");
		var dateStr = dateTime[0];
		var timeStr = "";
		if (dateTime.length > 1) {
			timeStr = " " + dateTime[1];
		}

		var date = convertStringToDate(dateStr);
		date.setMinutes(date.getMinutes() + offsetMinute);
		dateStr = localizeDate(convertDateToString(date));

		return(dateStr + " " + timeStr);
	}
	//convert date string to standard Date
	function convertStringToStandardDateTime(date){
		var dateTime = date.split(" ");
		var dateStr = dateTime[0];
		var timeStr = "";
		var localizedStr;
		localizedStr = convertDateFormat(dateFormat, separator,dateStr,"mm/dd/yyyy", "/");
		if (dateTime.length > 1) {
			timeStr = " " + dateTime[1];
		}
		return Date.parse(localizedStr+timeStr);
		
	}
	
	//added, This is getting called from the ClaimCreateSubmit.jsp
	function validateServiceRequestedDateTime(dateServicedRequested,dateServicedStart,dateServicedEnd)
	{
	               
					// var dateServicedRequested =document.getElementById("dateServicedRequested").value;	
					var result = true;
					var inputDateTime = formatDateToDefault(dateServicedRequested);
					var inputDate =inputDateTime.split(' ');
					var inputDateWithNoTime = inputDate[0];
					var inputDateTimeWithNoDate = inputDate[1].split(":");
					var inputDateHours = inputDateTimeWithNoDate[0];
					var inputDateMin = inputDateTimeWithNoDate[1];
					var inputDateHoursMin = inputDateHours+inputDateMin;
					//alert("inputDateHoursMin"+inputDateHoursMin);

					var today =new Date();
					var todayDateTime = localizeFormatDate(today)
					//alert("---"+todayDateTime);
					var currentdateTime = formatDateToDefault(todayDateTime);
					var currentDate = currentdateTime.split(' ');
					var currentDateWithNoTime = currentDate[0];
					var currentDateHours = today.getHours();
					//alert(currentDateHours);
					var currentDateMin = today.getMinutes();
					//alert(currentDateMin);
					var currentHoursMin = currentDateHours.toString()+currentDateMin.toString();
					//alert("currentHoursMin"+currentHoursMin);
					
					//var dateServicedStart =document.getElementById("dateServicedStart").value;
					var startDateTime = formatDateToDefault(dateServicedStart);
					var startDate =startDateTime.split(' ');
					var startDateWithNoTime = startDate[0];
					var startDateWithTime = startDate[1].split(":");
					var startDateHours = startDateWithTime[0];
					var startDateMin = startDateWithTime[1];
					var startDateHourMin = startDateHours+startDateMin;
					
					//var dateServicedEnd =document.getElementById("dateServicedEnd").value;
					var endDateTime =  formatDateToDefault(dateServicedEnd);
					var endDate =endDateTime.split(' ');
					var endDateWithNoTime = endDate[0];
					var endDateWithTime = endDate[1].split(":");
					var endDateHours = endDateWithTime[0];
					var endDateMin = endDateWithTime[1];
					var endDateHourMin = endDateHours+endDateMin;
					
					if (convertStringToDate(inputDateWithNoTime)==convertStringToDate(currentDateWithNoTime) && inputDateHoursMin>currentHoursMin)
					{
						result= false;
					    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanToday'/>", null, true);
						jQuery('#dateServicedRequested').addClass('errorColor');
					}
					if (convertStringToDate(inputDateWithNoTime)>convertStringToDate(startDateWithNoTime))
					{
						result= false;
					    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanStartDate'/>", null, true);
						jQuery('#dateServicedStart').addClass('errorColor');
					}
					if (convertStringToDate(inputDateWithNoTime)>convertStringToDate(endDateWithNoTime))
					{
						result= false;
					    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanEndDate'/>", null, true);
						jQuery('#dateServicedEnd').addClass('errorColor');
					}
					if(inputDateWithNoTime==startDateWithNoTime)
					{
						 if(inputDateHoursMin>startDateHourMin || inputDateHoursMin==startDateHourMin)
							 {
							 result= false;
							    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanStartDate'/>", null, true);
								jQuery('#dateServicedStart').addClass('errorColor');
							 }
						
					}
					if(inputDateWithNoTime==endDateWithNoTime)
					{
						if(inputDateHoursMin>endDateHourMin || inputDateHoursMin==endDateHourMin)
						{
							result= false;
						    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanEndDate'/>", null, true);
							jQuery('#dateServicedEnd').addClass('errorColor');	
						}
						
					}
					
					return result;
	}
</script>