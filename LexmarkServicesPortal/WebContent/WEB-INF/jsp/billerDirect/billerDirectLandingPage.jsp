<%@ include file="/WEB-INF/jsp/include.jsp" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
#footer { display:none; }
</style>
<a href="#" onclick="showEditPortletPage();"><span>Edit this portlet</span></a>
	<div id="Tabs">
	<ul>
	 <c:forEach items="${horizontalURLList}" var="horizontalURL" varStatus="counter" begin="0"> 
	 <c:choose>
	 	<c:when test="${counter.count == 1}">
	 		<li id="li_tab${counter.count}" onclick="tab('tab${counter.count}')" ><a>${horizontalURL.funcName}</a></li>
	 	</c:when>
	 	<c:otherwise>
	 		<li id="li_tab${counter.count}" onclick="callURL('tab${counter.count}','${horizontalURL.funcUrl}');tab('tab${counter.count}');"><a>${horizontalURL.funcName}</a></li>
	 	</c:otherwise>
	 </c:choose>
	</c:forEach>
	</ul>
	<div id="Content_Area">
	<c:forEach items="${horizontalURLList}" var="horizontalURLFrame" varStatus="counterFrame" begin="0">
	<div id="tab${counterFrame.count}">
			<iframe id="iframetab${counterFrame.count}" width="100%" height="370" >
			  <p>Your browser does not support iframes.</p>
			</iframe>
	</div>
	</c:forEach>
	
	</div> 
</div> 
<script type="text/javascript">
var urlListSize = '${urlListSize}';
var firstURL = '${firstURL}';
//alert('urlListSize is '+urlListSize);
for(var j=2;j<=urlListSize;j++){
	//alert('2222 j value is '+j);
	var value1 = 'tab'+j;
	var value2 = 'li_tab' + j;
	//alert('val 1'+value1);
	//alert('val 2'+value2);
	document.getElementById(value1).style.display = 'none';
	document.getElementById(value2).setAttribute("class", "");
}
document.getElementById('tab1').style.display = 'block';
document.getElementById('li_tab1').setAttribute("class", "active");
document.getElementById("iframetab1").src=firstURL;

function tab(tab) {
	//alert('tab method is called');
	var urlListSize = '${urlListSize}';
	//alert('urlListSize is '+urlListSize);
	for(var i=1;i<=urlListSize;i++){
		//alert('2222 i value is '+i);
		var value1 = 'tab'+i;
		var value2 = 'li_tab' + i;
		//alert('val 1'+value1);
		//alert('val 2'+value2);
		document.getElementById(value1).style.display = 'none';
		document.getElementById(value2).setAttribute("class", "");
	}

document.getElementById(tab).style.display = 'block';
document.getElementById('li_'+tab).setAttribute("class", "active");
}
function callURL(tab, bdURL){
	//alert('call URL method is called '+bdURL);
	document.getElementById("iframe"+tab).src=bdURL;
}

function showEditPortletPage(){
window.location.href="<portlet:renderURL><portlet:param name='action' value='showEditPortletPage'/></portlet:renderURL>";
}
</script>


