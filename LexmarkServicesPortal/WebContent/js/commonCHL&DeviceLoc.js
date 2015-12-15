var cId,cValue;
var queue;
var isChange;
var isSearch;
var found;
var searchItem;//='1-GOSNU1';//'1-7YYU6D';//'1-7YYUH5';//'1-GOSNU1';//'1-EEX-144';
function initialiseCHLTree()
{
	//alert("Under initialise chl tree");
	//Below code is to set the flags for Change in CHL location
	//Below queue is used for searching the node
	queue=new Array();
	queue.push('0_0');//adding the first node
	if(jQuery("#chlNodeId").val()!=""){
		isChange=true;
		isSearch=true;
		found=false;
		searchItem=jQuery("#chlNodeId").val();
	}
	//Ends
	var chlTreeXMLUrl='<portlet:resourceURL id="chlTreeXMLURL"/>';
	
	//alert(chlTreeXMLUrl);
	
	var reportingTree = new dhtmlXTreeObject("chl_node_tree_container","100%","100%",0);
	reportingTree.setSkin('dhx_skyblue');
	reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
	reportingTree.enableTreeLines(true);
	
	
		if(jQuery.browser.ie) {
		reportingTree.enableIEImageFix(true);
	}
		
		reportingTree.attachEvent("onXLS", function(tree,id) {
		if(id == null)
			jQuery("#reportingTreeLoadingNotification").show();
		else
			reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
	});
		/*reportingTree.attachEvent("onOpenEnd",function(){
							var h = this.allTree.scrollHeight;	
						//	jQuery('#content_chlTree').data("height.dialog", h+150);
							return true;
		});*/
	reportingTree.attachEvent("onXLE", function(tree,id) {
		jQuery("#reportingTreeLoadingNotification").hide();
		
		if(isChange == true){
			var popV=queue.shift();
			var childItems=reportingTree.hasChildren(popV);
			while(childItems==0){
				popV=queue.shift();
				childItems=reportingTree.hasChildren(popV);
				if(childItems==true)
					break;
			
			}
			if(popV==searchItem){
				found=true;
				isChange=false;
			}else{
				reportingTree.openItem(popV);
			}
				
			
			
		}
		if(found==true && isSearch==true){
		
			reportingTree.selectItem(searchItem,false);
			reportingTree.focusItem(searchItem);
			isSearch=false;
	
		}
	
		
	});
	
	reportingTree.attachEvent("onOpenEnd",function(id){
		if(isChange == true){
			var subItems = reportingTree.getAllSubItems(id);
			
			if(subItems.search(searchItem)== -1){
				var tempArr= new Array();
				var dataArr=subItems.split(",");
				for(i=0;i<dataArr.length;i++){
					tempArr.push(dataArr[i]);
				}
				//attach rest from the queue
				for(i=0;i<queue.length;i++){
					tempArr.push(queue[i]);
				}
				queue=tempArr;
			}else{
				
				found=true;
				isChange=false;
			}
		}
		});
	//reportingTree.enableSmartXMLParsing(true);
	reportingTree.setXMLAutoLoading(chlTreeXMLUrl);
	reportingTree.loadXML(chlTreeXMLUrl);


}

function initialiseDeviceLocatnTree()
{

	var deviceLocationXMLURL='<portlet:resourceURL id="deviceLocationXMLURL"/>';

	var reportingTree = new dhtmlXTreeObject("location_tree_container","100%","100%",0);
	reportingTree.setSkin('dhx_skyblue');
	reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
	reportingTree.enableTreeLines(true);


		if(jQuery.browser.ie) {
		reportingTree.enableIEImageFix(true);
	}
		reportingTree.attachEvent("onXLS", function(tree,id) {
		if(id == null)
			jQuery("#locationTreeLoadingNotification").show();
			//document.getElementById('reportingTreeLoadingNotification').style.display = 'block';
		else
			reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
	});
		
	reportingTree.attachEvent("onXLE", function() {
		
		jQuery("#locationTreeLoadingNotification").hide();
		jQuery("#location_tree_container").show();
		//reportingTree.openAllItems(0);
	    document.getElementById('locationTreeLoadingNotification').style.display = 'none';
	    document.getElementById('location_tree_container').style.display = 'block';
	});

	reportingTree.setXMLAutoLoading(deviceLocationXMLURL);
	reportingTree.loadXML(deviceLocationXMLURL);

	return reportingTree;
}


function viewDeviceListByCHL(chlNodeId, chlValue) {
	
	//alert("Node Id "+chlNodeId + "Node Value " + chlValue);
	//set the data to variables for future use when user clicks on select
	
	cId=chlNodeId;
	cValue=chlValue;
	
};

function setValToPage(){
	//Please keep a span with id chlNodeValueLabel for display on screen
	
	jQuery("#chlNodeValueLabel").html(cValue);
	
	//Bind the id & value with the form element	
	jQuery("#chlNodeId").val(cId);
	jQuery("#chlNodeValue").val(cValue);
	closeDialog();
}