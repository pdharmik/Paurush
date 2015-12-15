<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%@page import="com.lexmark.services.util.XMLEncodeUtil"%>
<style>
/*
#categoryTree li{
	list-style: none;
	list-style-image: none;
}

ul.outer{
	margin: 0px 0px;
	font-size: 14px;
	font: arial ;
	font-weight: bolder;
	width: 200px;
}
ul.inner{
	margin: 8px 15px;
	font-size: 12px;
	font-weight: normal;
}*/

.left-block-ylw ol, .left-block-ylw ul, .left-block-ylw li {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}



#categoryTree li{	
list-style: none; 
list-style-image: none; 
color:#000;
}
.inner a{
	background-position: 5px 13px;
	display: block;
	padding: 7px 0 7px 15px;
	margin: 0;
	text-decoration: none;
	text-indent: 0em;
	font-size: 12px;
	color:#1e68bc;
	border-bottom: 1px solid #e6e6f0;
	
}
.inner a:hover,.inner a.active{
	text-decoration:underline;
	display: block;
	padding: 7px 0 7px 15px;
	background-color:#eff0f6;
}

div.categoryBox{
	
}
.outer label{
	display: block;
	padding: 0 0 0 5px;
	text-decoration: none;
	text-indent: -1em;
	font-size: 13px;
	font-weight:bolder;
	border-bottom:none;
	color: #1d1d25;
	background:#e6e6f0;
	line-height:25px;
	padding:0 0 0 18px;
	/*width:100%;*/
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


</style>
<script type="text/javascript">
	function openOrClose(id){
		var ul = document.getElementById("ul_"+id);
		var img = document.getElementById("img_"+id);
		var closed = ul.style.display == "none";
		ul.style.display = closed ? "block":"none";
		img.src = closed ? "<html:imagesPath/>minus.gif" : "<html:imagesPath/>plus.gif";
	}
	var defs = [];
</script>
<%-- <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" /> --%>

<div class="left-nav" id="categoryTree">
	<div class="left-nav-inner overflow-hidden2" >
		
		<!-- left-nav-header -->
		<c:forEach items="${hierarchy}" var="category">
		<div class="left-block-ylw">
				<ul class="outer">
					<li class="li-style2"><label onclick="openOrClose('${category.categoryId}')" class="cursor-pointer">
							<img id="img_${category.categoryId}" src="<html:imagesPath/>minus.gif">
							${category.name}
							<c:set var="cateName" value="${category.name}"></c:set>
						</label>
						<ul class="inner" id="ul_${category.categoryId}">
							<c:forEach items="${category.documentList}" var="definition">
								<li>
									<c:set var="defName" value="${definition.name}"></c:set>
									<%
										String defName = (String)pageContext.getAttribute("defName");
										String cateName = (String)pageContext.getAttribute("cateName");
										defName = XMLEncodeUtil.escapeJSON(defName);
										cateName = XMLEncodeUtil.escapeJSON(cateName);
									%>
									<script type="text/javascript">
										defs['${definition.id}'] = {category:'<%=cateName%>', definition:'<%=defName%>'};
									</script>
									<a href="javascript:listDocument(${definition.id});">${definition.name}</a>
								</li>
							</c:forEach>
						</ul>
					</li>
				</ul>
		</div>
		</c:forEach>
		<div class="left-block-ylw">
		<ul class="outer">
			<li class="li-style2"><label onclick="openOrClose('publicDocuments')" class="cursor-pointer">
				<img id="img_publicDocuments" src="<html:imagesPath/>minus.gif" />
 				 <spring:message code='documentUserPage.text.publicFolder'/>
				</label>
						<ul class="inner" id="ul_publicDocuments">
								<li>
									<script type="text/javascript">
										defs['-1'] = {category:'<spring:message code='documentUserPage.text.publicFolder'/>', definition:'<spring:message code='documentUserPage.text.publicDocuments'/>'};
									</script>
									<a href="javascript:listDocument('-1');"><spring:message code='documentUserPage.text.publicDocuments'/></a>
								</li>
						</ul>			</li>
		</ul>
		</div>
	</div><!-- left-nav-inner -->
	<div class="left-nav-footer"></div><!-- left-nav-footer -->
</div>
