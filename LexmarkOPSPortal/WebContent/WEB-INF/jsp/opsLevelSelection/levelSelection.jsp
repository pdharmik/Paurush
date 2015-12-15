<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript" src="<html:rootPath/>js/tree/dhtmlxtree.js"></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/tree/dhtmlxtree.css"/>


<portlet:resourceURL id="levelList" var="levelListVar"></portlet:resourceURL>
<portlet:actionURL var="submitVar">
	<portlet:param name="action" value="submitForm"/>
</portlet:actionURL>

<c:if test="${companyName != null && companyName != '' }">
	<div class="success">
		<ul id="message_banner_" class="serviceSuccess">
			<li class="portlet-msg-success"><b>Selected company is ${companyName}</b></li>
		</ul>		
	</div>
</c:if>

<div class="content" id="opsLevelSelection">
<label>Type</label><span class="req">*</span><br/>
<select id="typeSearch">
<option value="1">Company Name</option>
<option value="2">MDM Account Id</option>
</select>
<div id="companyName">
<input type="text" id="nameCom"></input><br/><span>Enter at least 3 characters to populate company tree</span>
</div>
<div id="accFilter" style="display: none;">
<input type="text" id="numCom" style="float:left"></input>
<button class="button" id="submitAccId" style="float:left; margin: 3px 0 5px;">Submit</button><br/>
<span>Please enter exact MDM Account ID</span> 
</div> 

	
					<div id="chlTreeDiv" class="clearBoth">	
					<%-- <a style="cursor: pointer;" onClick="closeTree()" >Collapse All</a> | <a style="cursor: pointer;" onClick="expandTree()">Expand All</a> --%>			<br/>	
							<div id="locationTreeLoadingNotification_chl" class='treeLoading'></div>
			                <div id="chl_node_tree_container" style="display: none"></div>			               
					</div>
<form id="submitForm" method="POST" action="${submitVar}">
<input type="hidden" name="mdmId" id="mdmId" />
<input type="hidden" name="mdmLevel" id="mdmLevel" />
<input type="hidden" name="chlId" id="chlId"/>
<input type="hidden" name="accntName" id="accntName"/>
</form>
</div>
<script>

$(document).ready(function(){
	initTree();
	$('#opsLevelSelection #typeSearch').change(function(){
		var v=$(this).val();
		reportingTree.deleteChildItems(0);
		if(v==1){
			$('#opsLevelSelection #companyName').show();
			$('#opsLevelSelection #accFilter').hide();
		}else{
			$('#opsLevelSelection #companyName').hide();
			$('#opsLevelSelection #accFilter').show();
		}
		
	});
	var timer=null;
	$('#opsLevelSelection #nameCom').keypress(function(){
		if(timer!=null){
			clearTimeout(timer);
		}
		var val=$(this).val();
		timer=setTimeout(function(){
			if(val.length>=2){
				reportingTree.deleteChildItems(0);
				setLoadingCompanyName();
				
			}else{
				reportingTree.deleteChildItems(0);
			}	
		},1500);
		
	});
	$('#opsLevelSelection #submitAccId').click(function(){
		reportingTree.deleteChildItems(0);
		setLoadingCompanyId();
	});
		
});
var reportingTree;
	function initTree(){
		//$('#opsLevelSelection #chl_node_tree_container').html('');
		reportingTree = new dhtmlXTreeObject("chl_node_tree_container","100%","100%",0);
		var loadingId="locationTreeLoadingNotification_chl";
		var divId="chl_node_tree_container";
		reportingTree.setSkin('dhx_skyblue');
		reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
		reportingTree.enableTreeLines(true);

		//reportingTree.enableRadioButtons(true);
			
			
			reportingTree.attachEvent("onXLS", function(tree,id) {
			if(id == null){
				jQuery('#'+loadingId).show();
				jQuery('#'+divId).css('height','0px');
			}
			else
				reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
		});
			
		reportingTree.attachEvent("onXLE", function(tree,id) {

			jQuery('#'+loadingId).hide();
			jQuery('#'+divId).show();
			jQuery('#'+divId).css('height','460px');
			
			setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
		});
		
		
	}
	
	function setLoadingCompanyName(){
		reportingTree.setXMLAutoLoading("${levelListVar}&sString="+$('#nameCom').val());
		reportingTree.loadXML("${levelListVar}&sString="+$('#nameCom').val());
	}
	function setLoadingCompanyId(){
		reportingTree.setXMLAutoLoading("${levelListVar}");
		reportingTree.loadXML("${levelListVar}&sString="+$('#numCom').val()+"^l");
	}

function viewDeviceListByCHL(id,cval){
	
	var mdmId="",mdmLevel="",chlId="";
	
	if(id.indexOf("^g")!=-1){
		mdmId=id.substring(0,id.indexOf("^g"));//Global
		mdmLevel="Global";
	}else if(id.indexOf("^d")!=-1){
		mdmId=id.substring(id.indexOf("^pG")+3,id.indexOf("^g"));//Domestic
		mdmLevel="Domestic";
	}else if(id.indexOf("^l")!=-1){
		mdmId=id.substring(0,id.indexOf("^l"));//Legal
		mdmLevel="Legal";
	}else if(id.indexOf("^5l")!=-1 && id.indexOf("^chl")==-1){
		mdmId=id.substring(0,id.indexOf("^5l"));//Account
		mdmLevel="Account";
	}else{
		mdmId=id.substring(0,id.indexOf("^5l"));//Siebel
		mdmLevel="Siebel";
		chlId=id.substring(id.indexOf("^5l")+3,id.indexOf("^chl"));
	}
	
	$('#opsLevelSelection #mdmId').val(mdmId);//"65077");//"489334806");"623331717"
	$('#opsLevelSelection #mdmLevel').val(mdmLevel);//"Legal");//Global
	$('#opsLevelSelection #chlId').val(chlId);
	//alert(cval.substring(cval.indexOf("-"),cval.length));
	$('#opsLevelSelection #accntName').val(cval.substring(cval.indexOf("-")+1,cval.length));
	
	$('#opsLevelSelection #submitForm').submit();
}

function expandTree(){
	var rootsAr = reportingTree.getSubItems(0).split(",");
	
	for(var i=0;i<rootsAr.length;i++){
		reportingTree.openAllItems(rootsAr[i])
	}
}
function closeTree(){
var rootsAr = reportingTree.getSubItems(0).split(",");
	
	for(var i=0;i<rootsAr.length;i++){
		reportingTree.closeAllItems(rootsAr[i])
	}
}
</script>