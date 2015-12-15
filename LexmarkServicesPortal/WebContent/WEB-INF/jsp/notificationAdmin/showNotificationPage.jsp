<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxcommon.js"%></script>
  <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
 <div class="notification-bar" id ="notification-bar" >
 	<div class="notification-inner">
 		<div class="notification-content">
		 	<p><strong><spring:message code="usernotification.label.notification"/>:</strong> <span id="txtNotification" ></span> <span id="linkHere"></span><br/><br/>
		 	<input type="checkbox" id="hideCheck" value="yes" onclick="hideNotification(this)"/>&nbsp;<spring:message code="usernotification.label.hidethisnotification"/>?</p>
 		</div><!-- notification-content -->
	 	<div class="paginate">
	 		<span id="preSpan"><img src="<html:imagesPath/>pre.png" class="OffValue cursor-pointer" onClick="doPre()" onMouseDown="imgMouseDown(this)" onMouseUp ="imgMouseUp(this)"/></span>
			<span id="nextSpan"><img src="<html:imagesPath/>next.png" class="OffValue cursor-pointer" onClick="doNext()" onMouseDown="imgMouseDown(this)" onMouseUp ="imgMouseUp(this)"/></span>
	 	</div><!-- paginate -->
	 	<div class="clear"></div><!-- clear -->
	 </div><!-- notification-inner -->		
	 <div class="notification-footer">
	 	<div class="notification-footer-inner"></div><!-- notification-footer-inner -->
	 </div><!-- notification-footer -->
	 <style>
	 	.notification-bar {
	 		width:100%;
		 	}
	 </style>
 </div><!-- notification-bar --> 


<script type="text/javascript">

	var objectArray = new Array();
	var userId= "${notificationForm.userId}";
	<c:forEach items="${notificationForm.notificationDetailList}" var="notificationDetail" varStatus = "status" >
		objectArray[${status.index}] = { 
			description : "${notificationDetail.notificationLocaleList[0].displayDescription}",
			notificationId : "${notificationDetail.notificationLocaleList[0].notificationId}",
			displayURL : "${notificationDetail.notification.displayURL}"
		}
    </c:forEach>
    
	var item = 0;
	if (objectArray.length > 0) {
		showNotification();
	}
		
	if(objectArray.length == 0){
		//alert("Hi");
		document.getElementById("p_p_id_Notifications_WAR_LexmarkServicesPortal_").style.display='none'; //added for BRD #14-07-13 defect 14468
		//document.getElementById("portlet-wrapper-Notifications_WAR_LexmarkServicesPortal").style.display='none'; //added for BRD #14-07-13
		//document.getElementById("txtNotification").innerHTML = "<spring:message code='usernotification.label.hasnonotification'/>";
		//document.getElementById("hideCheck").disabled = "disabled";
	}
	if(item==0)
		document.getElementById("preSpan").innerHTML= "<img class='OffValue' src = '<html:imagesPath/>disPre.png'/>";
	if(objectArray.length<=1){
		document.getElementById("preSpan").innerHTML= "<img class='OffValue' src = '<html:imagesPath/>disPre.png'/>";
		document.getElementById("nextSpan").innerHTML= "<img class='OffValue' src = '<html:imagesPath/>disNext.png'/>";
	}
	function doNext(){
  		item++;
  		document.getElementById("preSpan").innerHTML= "<img src='<html:imagesPath/>pre.png' class='OffValue cursor-pointer' onClick='doPre()' onMouseDown='imgMouseDown(this)' onMouseUp ='imgMouseUp(this)'/>";
  		clearMessage();
  		
  		showNotification();
  	 	document.getElementById("hideCheck").checked = false;
  	  	if(item == (objectArray.length-1))
  	  		document.getElementById("nextSpan").innerHTML= "<img class='OffValue' src = '<html:imagesPath/>disNext.png'/>";
  	}
  	function doPre()
  	{
  		item--;
  		document.getElementById("hideCheck").disabled = "";
  		document.getElementById("hideCheck").checked = false;
  		clearMessage();
  		
  		if(item != (objectArray.length-1))
  			document.getElementById("nextSpan").innerHTML= "<img src='<html:imagesPath/>next.png' class='OffValue cursor-pointer' onClick='doNext()' onMouseDown='imgMouseDown(this)' onMouseUp ='imgMouseUp(this)'/>";
  		showNotification();

  	  if(item==0){
  			document.getElementById("preSpan").innerHTML= "<img class='OffValue' src = '<html:imagesPath/>disPre.png'/>";
    	}
	
  	}

  	function hideNotification(checkBox){
  	  	
  	  	if(checkBox.checked){
  	  		if(!window.confirm("<spring:message code='usernotification.message.confirm'/>")){
	  	  		checkBox.checked = false;
	  	  		return;
	  	  	}
	  	  	url = '<portlet:resourceURL id="hideUserNotification"/>';
		  	url +="&notificationId="+objectArray[item].notificationId;
		  	url +="&userId="+userId;
			doAjax(url, callbackGetResult);
  	  	}
  	  	  		
  	}
  	function callbackGetResult() {
		var delItem = objectArray.splice(item,1);
		if(item == objectArray.length)
		{
			document.getElementById("txtNotification").innerHTML = "";
			document.getElementById("linkHere").innerHTML = "";	
			document.getElementById("nextSpan").innerHTML= "<img class='OffValue' src = '<html:imagesPath/>disNext.png'/>"; 
			document.getElementById("hideCheck").checked = false;
			document.getElementById("hideCheck").disabled = "disabled";
		}else if(item == (objectArray.length-1)){
			document.getElementById("nextSpan").innerHTML= "<img class='OffValue' src = '<html:imagesPath/>disNext.png'/>";
			showNotification();
			document.getElementById("hideCheck").checked = false;
			}
		else{
			showNotification();
			document.getElementById("hideCheck").checked = false;
			}
		if(0 == objectArray.length) {
			document.getElementById("p_p_id_Notifications_WAR_LexmarkServicesPortal_").style.display='none'; //added for BRD #14-07-13 defect 14468
			//document.getElementById("portlet-wrapper-Notifications_WAR_LexmarkServicesPortal").style.display='none'; //added for BRD #14-07-13
			//document.getElementById("txtNotification").innerHTML = "<spring:message code='usernotification.label.hasnonotification'/>";
		}
	};
	function showNotification(){
		document.getElementById("txtNotification").innerHTML = objectArray[item].description;
		if(objectArray[item].displayURL !=null && objectArray[item].displayURL != "")
  			document.getElementById("linkHere").innerHTML = "<a href="+objectArray[item].displayURL +" target='_blank'><spring:message code='usernotification.label.clickhere'/></a>";
  		else
  			document.getElementById("linkHere").innerHTML = "";	
	};
	function imgMouseDown(element){
		element.className = 'OnValue';   
	}
	function imgMouseUp(element){
		element.className = 'OffValue';  
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Notification Admin Show Notification";
     addPortlet(portletName);
     pageTitle="Show notification";
</script>