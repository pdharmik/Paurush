<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%--Changes for MPS 2.1 --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ISPOPUP"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%--Ends Changes for MPS 2.1 --%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]--> 
<%-- ----------------------- --%>
<portlet:renderURL var="forwardToAddNewAddress">
	<portlet:param name="action" value="addAddress"/>
</portlet:renderURL>
<portlet:renderURL var="forwardToEditAddress">
	<portlet:param name="action" value="changeAddress"/>
</portlet:renderURL>


<portlet:resourceURL var="setOldAddressSession" id="setOldAddress"></portlet:resourceURL>

<portlet:renderURL var="deleteAddressURL">
	<portlet:param name="action" value="removeAddress"/>
</portlet:renderURL>


<%-- ------------------------ --%>

<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
<div id="error" style="display: none;" class="error">
	<ul>
	<li><strong><spring:message code="exception.generalError.title"></spring:message>
				<spring:message code="exception.notAvailable.title"></spring:message></strong>
	</li></ul>
</div>

 <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.manageAddress.heading.addresses"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong></h3></div>
        	<!-- END -->
        	
    <div class="main-wrap">
      <div class="content">
        
         
          <!-- MAIN CONTENT GOES HERE -->
          <div class="portletBlock rounded shadow">
			<div class="columnsTwo w70p">
				<div class="columnInner">
					
					<p class="info add"><spring:message code="requestInfo.cm.manageAddress.heading.information1"/><br/>
					<spring:message code="requestInfo.cm.manageAddress.heading.information2"/></p>
					
					<p><button class="button" onclick="forwardToAddNewAddress()"><spring:message code="requestInfo.cm.manageAddress.heading.addAddresses"/></button>
					</p>
				</div>
				</div>
				<div class="columnsTwo splitter w30p">				
					<div class="columnInner">
					<h4><spring:message code="requestInfo.cm.manageAddress.heading.manageMultipleAddress"/></h4>
					<ul class="link">
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Add_Addresses','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageAddress.link.addMultipleAddress"/></a></label>
						</li>
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Change_Addresses','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageAddress.link.changeMultipleAddress"/></a></label>
						</li>
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Remove_Addresses','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageAddress.link.removeMultipleAddress"/></a></label>
						</li>
						
					</ul>
					</div>
				</div>
			</div>
	

          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAddress.heading.addressList"/></h3>
		  <div class="grid-controls">
		  	<div class="filterLinks">
		 		<ul>
              		 <li class="first" id="alladdress">
              		 <spring:message code="requestInfo.cm.manageAddress.link.allAccAddress"/>
              		 </li>
             		 <li><img class="ui_icon_sprite bookmark-star-icon" src="<html:imagesPath/>transparent.png" >
             		 	<span id="myBookmarkAddress"><a href="javascript:showBookmarkedAddress();" >
             		 	<spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/>
             		 	</a></span>
             		 </li>
            	</ul>
		  </div>
		   <!-- Expand-min Start -->
            <div class="expand-min">
              <ul>
                <li>
                <%--Changes for MPS 2.1 --%>
                	<a href="#grid" id='headerMenuButton'>
                	<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon"  />
					<spring:message code="requestInfo.link.customizeColumns"/></a>
					
				</li>
				<li> 
					<a id="resetLink" href="javascript:doReset();">
                	<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite reset-icon" /> 
                	<spring:message code="requestInfo.link.reset"/></a>
                	
                	<%--Ends Changes for MPS 2.1 --%> 
                </li>
				
              </ul>
            </div>

            <!-- Expand-min End --> 
	  </div>
          
          <div class="portlet-wrap rounded">
            <div class="portlet-wrap-inner">
              <div id="serviceAddressGridbox_mainPage" class="gridbox gridbox_light"></div>
            </div>
              <div id="loadingNotification" class='gridLoading'>
							<!-- spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif" />
			  </div>
            
           </div>
          
          <div class="pagination" id="paginationId">
          <span id="pagingArea"></span><span id="infoArea"></span>          
          </div>
          <!-- mygrid_container --> 
          <div class="buttonContainer">
            <button class="button_cancel" type="button" onclick="redirectToHistory('ListPage');"><spring:message code="button.cancel"/></button>
           
          </div>
        
      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>

<script type="text/javascript">

var prevSrNo="${serviceRequestNumber}";
//alert(prevSrNo);
var isUpdateFlag="${isUpdateFlag}";
var exceptionOccured="${exceptionOccured}";

//10 columns in address Grid
var favStatus = {};
var serviceAddressGrid;
var addressListGridDisplay;
//var bookmarkFlag=false;
<%-- Changes for defect 8093 MPs 2.1--%>
var favAddressString='&isFavorite=true';
<%--Ends  Changes for defect 8093 MPs 2.1--%>
//load the grid once everything is loaded
jQuery(document).ready(function(){
	var currentURL = window.location.href;
	if(exceptionOccured !=""){
		jQuery('#error').show();
		}
//	Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	initialiseGrid();
});
var lbsAddressFlagFilter=[];
lbsAddressFlagFilter[0]=["Y","Yes"];
lbsAddressFlagFilter[1]=["N","No"];
	//following variables are declared in dynamicGridInitialize
	pagingArea="pagingArea";infoArea="infoArea";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification";backFilterValues="";
	gridCreationId="serviceAddressGridbox_mainPage";

	JSON_Param["<%=gridConfigurationValues[0]%>"]="&nbsp;,&nbsp;,"+"<spring:message code='serviceRequest.listHeader.serviceAddressList'/>";
	JSON_Param["<%=gridConfigurationValues[1]%>"]=",,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter";
	JSON_Param["<%=gridConfigurationValues[2]%>"]="right,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
	JSON_Param["<%=gridConfigurationValues[3]%>"]="55,25,0,150,115,105,105,90,80,65,100,60,60,60,80,80,80";
	JSON_Param["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
	JSON_Param["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,na";
	JSON_Param["<%=gridConfigurationValues[6]%>"]="3";
	JSON_Param["<%=gridConfigurationValues[7]%>"]="3,asc";
	JSON_Param["<%=gridConfigurationValues[8]%>"]="2,7,11,12";
	JSON_Param["<%=JSON_COMBO_FILTER%>1"]=lbsAddressFlagFilter;
	JSON_Param["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="addressListPopulate"/>";
	JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
	JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
	JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
	JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";
	JSON_Param["multiline"]=false;



	reloadGrid=function(){
		//clearMessage();
		//alert('in reload Grid');
		refreshGridSettingOnPage(requestListGrid);
		xmlURLQueryParams.setLoadXMLUrl();
		requestListGrid.clearAndLoad(xmlURL);
		
	};




	initURLParams = function(){
			xmlURLQueryParams={
					isFavorite:false,
					urlParameters:["direction","orderBy","filterCriterias","isFavorite","<%=ISPOPUP%>"],
					setParameters : function(){
											this["direction"]=requestListGrid.a_direction;
											this["orderBy"]=requestListGrid.getSortColByOffset()-3;
											this["<%=ISPOPUP%>"]=false;
											this["isFavorite"]=this.isFavorite==true?true:false;
											this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
											},
					setLoadXMLUrl : function(){
													xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
													this.setParameters();
													for(i=0;i<this.urlParameters.length;i++){
														if(this[this.urlParameters[i]]!=null){
															xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
														}
															
													}	
											}
					};
			
				
				
				
			};
		

			function onMouseOverFunction(id,ind){
				if(ind>2)
					return true;
				else	
					return false;
			}

			function afterXLEfunction(){
				//alert('in contact list');
				showToolTipList('helpIconList');
								
				
			}

	
	
        
    
   
    

//this is called when user clicks on ALL account address
function showAllAccountAddress(){
	clearMessage();
	jQuery('#alladdress').html('<spring:message code="requestInfo.cm.manageAddress.link.allAccAddress"/>');
	jQuery('#myBookmarkAddress').html('<a href="javascript:showBookmarkedAddress();"><spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/></a>');
	xmlURLQueryParams.isFavorite=false;
	reloadGrid();
	
}
//this is called when user clicks on Bookmarked address
function showBookmarkedAddress(){
	clearMessage();
	//disable my bookmarked addresses
	jQuery('#myBookmarkAddress').html('<spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/>');
	//change All Account Address to Link
	jQuery('#alladdress').html('<a href="javascript:showAllAccountAddress();"><spring:message code="requestInfo.cm.manageAddress.link.allAccAddress"/></a>');
	xmlURLQueryParams.isFavorite=true;
	reloadGrid();
	
}
//function is called when user clicks on Add New Address
function forwardToAddNewAddress(){
	showOverlay();
	if(prevSrNo=="" || isUpdateFlag==""  )
		window.location.href="${forwardToAddNewAddress}";
	else
		window.location.href="${forwardToAddNewAddress}" +"&prevSrNo=" + prevSrNo+ "&isUpdateFlag=" + isUpdateFlag;
	
}
//function is called when user clicks on Edit Address
function editAddress(addressId){
	  //alert(serviceAddressGrid.cellById(addressId,11).getValue());
	  fireAjax(addressId,'changeAddress');	
}
//function is called when user clicks on Delete
function deleteAddress(addressId){
	//alert('in delete Address'+addressId);
	fireAjax(addressId,'deleteAddress');
}
//This Ajax is called for setting the values to session
function fireAjax(addressId,state){
	var rId=addressId;
		showOverlay();
		  jQuery.ajax({
				url:'${setOldAddressSession}&state='+state,
				type:'POST',
				data: {
						addressId :   addressId,
						addressName : requestListGrid.cellById(rId,3).getValue(),
						storeFrontName: requestListGrid.cellById(rId,4).getValue(),
						addressLine1: requestListGrid.cellById(rId,5).getValue(),
						addressLine2: requestListGrid.cellById(rId,6).getValue(),
						city: 		  requestListGrid.cellById(rId,8).getValue(),
						houseNumber:  requestListGrid.cellById(rId,7).getValue(),
						country:	  requestListGrid.cellById(rId,15).getValue(),
						stateGrid:	  requestListGrid.cellById(rId,9).getValue(),
						province:	  requestListGrid.cellById(rId,10).getValue(),
						county:	 	  requestListGrid.cellById(rId,11).getValue(),
						district:	  requestListGrid.cellById(rId,12).getValue(),
						zipPostal:    requestListGrid.cellById(rId,14).getValue()
						
						
					},
				success: function(results){
							var setParam=true;
						if(prevSrNo=="" || isUpdateFlag==""  )
							setParam=false;
						
						
						if(results=="success"){
							if(state=='deleteAddress'){
								if(setParam==true)
									window.location.href="${deleteAddressURL}" +"&prevSrNo=" + prevSrNo+ "&isUpdateFlag=" + isUpdateFlag;
								else
									window.location.href="${deleteAddressURL}";
							}	
							else{
								if(setParam==true)
									window.location.href="${forwardToEditAddress}" +"&prevSrNo=" + prevSrNo+ "&isUpdateFlag=" + isUpdateFlag;
								else
									window.location.href="${forwardToEditAddress}";
							}
						}else{
							jQuery('#error').show();
							}
					},
				failure: function(results){
							jQuery('#error').show();
						}
			});
	}
	
	    var favStatus = {};
	    //This is called when user wants to set address favorite
	    function setServiceAddressFavourite(favoriteAddressId){        
	    	clearMessage();
	    	var url = '<portlet:resourceURL id="setServiceAddressFavouriteFlag"/>';
	        url += "&favoriteAddressId="+favoriteAddressId;
	        url += "&contactId=${contactid}";

	        
	        // if the favorite flag is being updated
	        if (favStatus[favoriteAddressId]&&favStatus[favoriteAddressId].isLoading) {
	            return;
	        }
	        
	        var starImg = window.document.getElementById("starImg_address_" + favoriteAddressId);
	        var isFavorite=(jQuery("#starImg_address_"+favoriteAddressId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
	        //var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
	        favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
	        starImg.src = "<html:imagesPath/>loading-icon.gif";
	        url += "&flagStatus=" + isFavorite;
	        
	        doAjax(url, callbackGetServiceAddressResult, callbackGetServiceAddressFailure, "");
	    }

	 // call back, when successfully update favorite status of service address
		function callbackGetServiceAddressResult(result) {
			var addressId = result.data;
			if (document.getElementById("serviceAddressGridbox_mainPage").style.display!="none") {		
				var starImg= document.getElementById('starImg_address_' + addressId);
				if (favStatus[addressId].isFavorite) {
					jQuery('#starImg_address_'+addressId).removeClass('bookmark-star-icon');
					jQuery('#starImg_address_'+addressId).addClass('removebookmark-icon');
					starImg.src = "<html:imagesPath/>transparent.png";
					//starImg.src = "<html:imagesPath/>unfavorite.png";
					starImg.title = '<spring:message code="requestInfo.tooltip.address.Bookmark"/>';
				} else {
					jQuery('#starImg_address_'+addressId).removeClass('removebookmark-icon');
					jQuery('#starImg_address_'+addressId).addClass('bookmark-star-icon');
					starImg.src = "<html:imagesPath/>transparent.png";
					//starImg.src = "<html:imagesPath/>favorite.png";
					starImg.title = '<spring:message code="requestInfo.tooltip.address.UnBookmark"/>';
				}
			}

			showToolTipListbyId('starImg_address_' + addressId);
			
			if (favStatus[addressId].isFavorite) {
				favStatus[addressId].isFavorite = false;
			} else {
				favStatus[addressId].isFavorite = true;
			}
			favStatus[addressId].isLoading = false;
			setTimeout(clearMessage,3000)
		}

	    // call back, when fail to update favorite status of service address
	    function callbackGetServiceAddressFailure(result) {
	        var addressId = result.data;
	        if (document.getElementById("serviceAddressGridbox_mainPage").style.display!="none") {      
	            var starImg = document.getElementById('starImg_address_' + addressId);
	            if (favStatus[addressId].isFavorite) {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            } else {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            }
	        }
	        favStatus[addressId].isLoading = false;
	    }
	
	   
		
		/* Added for CI7 BRD14-02-12 */
	ajaxSuccessFunction=function updateRequest(){
			window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);					
	}
		/* END */
  </script>