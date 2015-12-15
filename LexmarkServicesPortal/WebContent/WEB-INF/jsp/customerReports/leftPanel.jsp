<style>
.left-block-ylw ol, .left-block-ylw ul, .left-block-ylw li {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}
#leftPanel li{	
list-style: none; 
list-style-image: none; 
color:#000;
}
div.inner a{
	display: block;
	padding: 7px 0 7px 15px;
	margin: 0;
	text-decoration: none;
	text-indent: 0em;
	font-size: 12px;
	color:#1e68bc !important;
	border-bottom: 1px solid #e6e6f0;
	
}
div.inner a:hover,div.inner a:focus,div.inner a.active{
	background:#eff0f6;
	background-position: 5px 13px;
	display: block;
	padding: 7px 0 7px 15px;
}

div.outer a{
	display: block;
	padding: 0 0 0 5px;
	text-decoration: none;
	/*text-indent: -1em;*/
	font-size: 13px;
	font-weight:bolder;
	border-bottom:none;
	color: #1d1d25 !important;
	background:#e6e6f0;
	line-height:25px;
	padding:0 0 0 3px;
	width:100%;
}
.left-block{
	background:#e8f3fa; 
	border-radius:3px; 
	border:1px solid #d2e0e9;
	margin-top:1px;
}
.left-block-ylw{
	margin-top:1px;
}
.left-block-red{
	background:#fff3f3; 
	border-radius:3px; 
	border:1px solid #f9d1d1;
	margin-top:1px;
}
.left-block-gry{
	background:#f5f5f5; 
	border-radius:3px; 
	border:1px solid #e7e7e7;
	margin-top:1px;
}

/* Tablets ----------- */
/*@media only screen and (max-width : 768px) {
	
	div.outer a{
		font-size:14px;
		padding:10px 10px 10px 28px;
	}
	div.inner a{
		font-size:14px;
		padding:15px 5px 15px 25px;
		background-position:14px center;
	}
	div.inner a:hover,div.inner a:focus,div.inner a:active{
		padding:15px 5px 15px 25px;
		background-position:14px center;
	}
}*/
	
</style>
<!-- script type="text/javascript" src="/lexmark-services-portal-theme/bootstrap/js/jquery.min.js"></script-->
<script>
$(document).ready(function(){
	/*$(".navbar-toggle").click(function(){
		$("#navbar").slideToggle("slow").toggleClass("in");
		
	})*/
})
</script>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<!-- Changes for 14.9 CR 14720 -->
			<!--div class="navbar-inverse">
				<div class="navbar-header">
						  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						  </button>
					 </div>	
			 </div>
			
			
			<div id="sidebar" class="col-xs-12 col-sm-3 col-md-3">
			 
				<div id="navbar" class="navbar-collapse collapse"-->
				<div id='leftPanel' class="left-nav leftPanel-div">
				<div class="left-nav-inner" style="overflow:hidden;">
					
					
						<c:forEach items="${roleCategories}" var="roleCategory" varStatus="outCounter" begin="0">
							<div class="left-block-ylw">
							<ul>
								<li>
									<div class="categoryBox" id='roleCategory${roleCategory.categoryId}'>
										<div class="outer" id='roleCategory${roleCategory.categoryId}Name'><a href="javascript:showCloseRoleCategory('${roleCategory.categoryId}', '${roleCategory.name}')"><img id="img_${roleCategory.categoryId}" src="<html:imagesPath/>plus.gif">${roleCategory.name}</a></div>
											<div id='roleCategory${roleCategory.categoryId}Defs' style="display:none">
												<c:forEach items="${roleCategory.documentList}" var="docDefinition" varStatus="innerCounter" begin="0">
													<div class="inner" id='roleCategory${roleCategory.categoryId}Doc${docDefinition.id}'>
														<!-- <a href='#' onclick="clickReportDefinition('${roleCategory.categoryId}', '${docDefinition.id}')"> ${docDefinition.name} </a> -->	<!-- commented for employee report -->
														<!-- added for employee report -->
														<c:if test="${not isEmployee}">
															<a href='#' onclick="clickReportDefinition('${roleCategory.categoryId}', '${docDefinition.id}')"> ${docDefinition.name} </a>
														</c:if>
														<c:if test="${isEmployee}">
															<a href='#' onclick="clickReportDefinition('${roleCategory.categoryId}', '${docDefinition.id}')"> ${docDefinition.name} </a>
														</c:if>
														<!-- end of addition for employee report -->
													</div>
												</c:forEach>
											</div>
									</div>		
								</li>
							</ul>
							</div>
							<br/>
						</c:forEach>
							<div class="left-block-ylw">
							<ul>
								<li>
									<div class="categoryBox" id='roleCategoryTop10'>
									<!-- Changed id for 14.9 CR 14722 -->
										<div class="outer" id='roleCategoryTop10Name'><a href="javascript:showCloseRoleCategory('101', '<spring:message code="customerReports.defaultView.shortcuts"/> ')"><img id="img_101" src="<html:imagesPath/>minus.gif" /><spring:message code='customerReports.defaultView.shortcuts'/></a></div>
										<div id='roleCategory101Defs'>
											<div class="inner" id='roleCategory101Doc101'>
												<a href='#' onclick="clickReportDefinition('101', '101')"> <spring:message code='customerReports.defaultView.latest'/> </a>
											</div>
										</div>
									</div>
								</li>
							</ul>
							</div>
				</div>
				<div class="left-nav-footer"></div>
			</div>
		<!-- </div>  -->
		<!--</div>-->
        <portlet:renderURL var="clickReportDefinition">
        	<portlet:param name="action" value="showReports"></portlet:param>
    	</portlet:renderURL>
		<form:form id="docDefinitionForm" target="_self" method="post" action="${clickReportDefinition}">
			<input id='roleCategoryIdInput' type='hidden' name='roleCategoryId'/>
			<input id='docDefinitionIdInput' type='hidden' name='docDefinitionId'/>
<!-- 			Changes for 14.9 Potential CR 14722 -->
			<input id='roleCategoryIdArrayPlus' type='hidden' name='roleCategoryIdArrayPlus'/>
			<input id='roleCategoryIdArrayMinus' type='hidden' name='roleCategoryIdArrayMinus'/>
            <input id='timezone' type='hidden' name='timezone'/>
		</form:form>
		
	<portlet:resourceURL id="downloadReport" var="downloadReport"></portlet:resourceURL>	
	<portlet:resourceURL id="deleteReport" var="deleteReport"></portlet:resourceURL>	
	<portlet:resourceURL id="deleteReportOrDocument" var="deleteReportOrDocument"></portlet:resourceURL>
<script type="text/javascript">
//Changes for 14.9 Potential CR 14722 START
var roleCategoryIdArrayPlus = [];
var roleCategoryIdArrayMinus = [];
var categoryArrayPlus2=${roleCategoryIdArrayPlusList};
var categoryArrayMinus2=${roleCategoryIdArrayMinusList};
var indexPlus=0;
var indexMinus=0;
//Changes for 14.9 Potential CR 14722 ENDS

	var deleteResult = '${deleteResult}';
	if(deleteResult == 'success'){
		clearMessage();
		showMessage("<spring:message code='customerReports.deleteReport.success'/>", null, true);
	}


	var today = new Date();
	var timezone = 0 - today.getTimezoneOffset()/60;
	var preChooseCategory = '${roleCategoryId}';
	var preChooseDef = '${docDefinitionId}'; 
	var docDefEle = document.getElementById('roleCategory' + preChooseCategory + 'Doc' + preChooseDef);
	if(docDefEle){
		docDefEle.style.fontWeight="bold";
	}
		

	

//Changes for 14.9 Potential CR 14722 START
		jQuery(document).ready(function(){
		
			var categoryArrayPlus=${roleCategoryIdArrayPlusList};
			var categoryArrayMinus=${roleCategoryIdArrayMinusList};

			for(var i=0;i<categoryArrayPlus.length;i++)
				{
				var roleCategoryPlusDiv1 = 'roleCategory' + categoryArrayPlus[i] + 'Defs';
				if(categoryArrayPlus[i]!="0"){
					document.getElementById(roleCategoryPlusDiv1).style.display = 'none';
					/*var img = document.getElementById("img_" + categoryArrayPlus[i]);
					img.src = "<html:imagesPath/>plus.gif";*/
				}
				var roleCategoryMinusDiv1 = 'roleCategory' + categoryArrayMinus[i] + 'Defs';
				if(categoryArrayMinus[i]!="0")
					{
					document.getElementById(roleCategoryMinusDiv1).style.display = 'block';
					/*var img = document.getElementById("img_" + categoryArrayMinus[i]);
					img.src = "<html:imagesPath/>minus.gif";*/
					}
			
				}

		});
		
	function showCloseRoleCategory(roleCategoryId, roleCategoryName){
		var roleCategoryDefsDiv = document.getElementById('roleCategory' + roleCategoryId + 'Defs');
		var roleCategoryNameDiv = document.getElementById('roleCategory' + roleCategoryId + 'Name');
		
		if(roleCategoryDefsDiv.style.display == 'none'){
			roleCategoryIdMinus(roleCategoryId);
			roleCategoryDefsDiv.style.display = '';
			var img = document.getElementById("img_" + roleCategoryId);
			img.src = "<html:imagesPath/>minus.gif";
		}
		else{
			roleCategoryIdPlus(roleCategoryId);
			roleCategoryDefsDiv.style.display = 'none';
			var img = document.getElementById("img_" + roleCategoryId);
			img.src = "<html:imagesPath/>plus.gif";
		}
	}
	
	function roleCategoryIdMinus(roleCategoryId)
	{
		var y=categoryArrayMinus2.length;
		var x=categoryArrayPlus2.length;
		if(y<1)
		{
			roleCategoryIdArrayMinus[indexMinus]=roleCategoryId;
			roleCategoryIdArrayPlus[indexPlus]="0";
			indexMinus=indexMinus+1;
			indexPlus=indexPlus+1;
		}
		else{
			roleCategoryIdArrayMinus=categoryArrayMinus2;
			roleCategoryIdArrayMinus[y]=roleCategoryId;
			roleCategoryIdArrayPlus=categoryArrayPlus2;
			roleCategoryIdArrayPlus[x]="0";
			y=y+1;
			x=x+1;
		}
		
	}
	function roleCategoryIdPlus(roleCategoryId)
	{
		
		var x=categoryArrayPlus2.length;
		var y=categoryArrayMinus2.length;
		if(x<1)
			{
			roleCategoryIdArrayPlus[indexPlus]=roleCategoryId;
			roleCategoryIdArrayMinus[indexMinus]="0";
			indexPlus=indexPlus+1;
			indexMinus=indexMinus+1;
			}
		else
			{
			roleCategoryIdArrayPlus=categoryArrayPlus2;
			roleCategoryIdArrayPlus[x]=roleCategoryId;
			roleCategoryIdArrayMinus=categoryArrayMinus2;
			roleCategoryIdArrayMinus[y]="0";
			x=x+1;
			y=y+1;
			}
						
		
	}
//Changes for 14.9 Potential CR 14722 ENDS

	function clickReportDefinition(roleCategoryId, reportDefinitionId){
		
		var docDefEle = document.getElementById('roleCategory' + roleCategoryId + 'Doc' + reportDefinitionId);
		docDefEle.style.fontWeight="bold";
		//Changes for 14.9 Potential CR 14722
		var categoryArrayPlus=${roleCategoryIdArrayPlusList};
		var categoryArrayMinus=${roleCategoryIdArrayMinusList};

		var formEle = document.getElementById('docDefinitionForm');
		var docDefinitionIdInput = document.getElementById('docDefinitionIdInput');
		var roleCategoryIdInput = document.getElementById('roleCategoryIdInput');
		docDefinitionIdInput.value = reportDefinitionId;
		roleCategoryIdInput.value = roleCategoryId;
		document.getElementById('timezone').value = timezone;
//Changes for 14.9 Potential CR 14722 START
		if(roleCategoryIdArrayPlus!=null)
			{
			document.getElementById('roleCategoryIdArrayPlus').value = roleCategoryIdArrayPlus;
			}
		if(roleCategoryIdArrayPlus=="")
			{
			document.getElementById('roleCategoryIdArrayPlus').value = categoryArrayPlus;
			}
		if(roleCategoryIdArrayMinus!=null)
		{
			document.getElementById('roleCategoryIdArrayMinus').value = roleCategoryIdArrayMinus;
		}
		if(roleCategoryIdArrayMinus=="")
		{
		document.getElementById('roleCategoryIdArrayMinus').value = categoryArrayMinus;
		}
//Changes for 14.9 Potential CR 14722 ENDS
		formEle.submit();
	}


  function deleteReport(id,categoryId,defId) {
      
		if (confirm('<spring:message code="customerReports.deleteReport.confirm"/>')) {
			var url = "${deleteReport}"
			url = url + "&id=" + id;
			  showOverlay();	
				doAjax(url,function(){
				clickReportDefinition(categoryId, defId);
				showMessage("<spring:message code='customerReports.deleteReport.success'/>", null, true);
				hideOverlay();
				return true;
			},
			function(){
				return true;
			}
		);

	
		}
	} 
  
  function deleteReportOrDocument(reportJobId,categoryId,defId) {
        if (confirm('<spring:message code="customerReports.deleteReport.confirm"/>')) {
			var url = "${deleteReportOrDocument}"
			url = url + "&id=" + reportJobId;
			showOverlay();	
			doAjax(url,function(){
				if(categoryId == 'null' && defId == 0){
					clickReportDefinition('101', '101');
				}else{
					clickReportDefinition(categoryId, defId);
				}
				hideOverlay();
				showMessage("<spring:message code='customerReports.deleteReport.success'/>", null, true);
				return true;
			},
			function(){
				hideOverlay();
				return true;
			}
		);

	
		}
	} 

	function downloadReport(id) {
		var dnldUrl = "${downloadReport}";
		dnldUrl = dnldUrl + "&id=" + id;
		window.open(dnldUrl);
	}
</script>