<%-- common subtab jsp to select history or create new request --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include>  
<jsp:include page="../../common/validationMPS.jsp"></jsp:include>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<style type="text/css">
       .ie7 .button, .ie7 .button_cancel {
               behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;                
        }
        .buttonContainer{position:relative!important;}
        .button_subrow1{margin-left:81%!important;padding: 0.27em 0.5em 0.34em!important;}
</style>
<![endif]-->
<!--[if IE 8]>
	
<style type="text/css">
       .ie8 .buttonOK {
               behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;                
        }
       
</style>
<![endif]-->

<portlet:resourceURL id="submitUpdatedContact" var="submitUpdatedContactURL"></portlet:resourceURL>
<portlet:resourceURL id="primarycontactListPopulate" var="primaryContactListUrl"></portlet:resourceURL>

<portlet:renderURL var="changeRemoveContactURL">
	<portlet:param name="action" value="changeRemoveContact" />
</portlet:renderURL>

<portlet:renderURL var="setOldContactSession">
	<portlet:param name="action" value="setOldContact"/>
</portlet:renderURL>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div id="content-wrapper-inner">
  	<div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1> 
      <h2 class="step"><spring:message code="requestInfo.cm.manageContact.heading.contacts"/></h2>
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
         
        <div class="error" id="errorMsg" style="display:none"></div>
        
		<div class="portletBlock bgSolid rounded shadow">			
				<!-- Add new contact button -->
				<div class="columnsTwo w70p">			
					<div class="columnInner">
              			<p class="info add"><spring:message code="requestInfo.cm.manageContact.heading.instruction1"/> <br />
                		<spring:message code="requestInfo.cm.manageContact.heading.instruction2"/></p>
              			<p>
              			<button class="button" onclick="javascript:forwardToAddContactJSP('addone');"><spring:message code="requestInfo.cm.manageContact.heading.addNewContact"/></button>
              			</p>
            		</div>
				</div>
				
				<!-- Manage multiple contact links -->
				<div class="columnsTwo splitter w30p">				
					<div class="columnInner">
					<h4><spring:message code="requestInfo.cm.manageContact.heading.manageMultipleContact"/></h4>
					<ul class="link">
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Add_Contacts','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageContact.link.addMultipleContacts"/></a></label>
						</li>
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Change_Contacts','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageContact.link.changeMultipleContact"/></a></label>
						</li>
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Remove_Contacts','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageContact.link.removeMultipleContacts"/></a></label>
						</li>
					</ul>
					</div>
			  	</div>
			</div>
		
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageContact.heading.contactlist"/></h3>		  
		  <div class="grid-controls"> 		  		
		  	<div class="filterLinks">
	            <ul>
	            <%-- Changes mPS 2.1 --%>
	              <li class="first" id="allContacts">
	              	<spring:message code="requestInfo.link.allAccountContacts"/>
	              </li>
	              <li class="first" id="allContactsDisabled" style="display: none;">
	              	<spring:message code="requestInfo.link.allAccountContacts"/>
	              </li>
	              <li id="myBookmarkLi">
	              	<img class="ui_icon_sprite bookmark-star-icon" src="<html:imagesPath/>transparent.png" />
	              	<span id="myBookmarkContacts"><a href="javascript:showBookmarkedContact();"><spring:message code="requestInfo.link.myBookmarkContacts"/></a></span>
	              </li>
	              <li id="myBookmarkLiDisabled" style="display: none;">
	              	<img class="ui_icon_sprite bookmark-star-icon" src="<html:imagesPath/>transparent.png" class="disabledImage"/>
	              	<span><spring:message code="requestInfo.link.myBookmarkContacts"/></span>
	              </li>
	              <%--Ends changes mPS 2.1 --%>
	            </ul>
          	</div>
            <!-- Expand-min Start -->
            <div class="expand-min">
              <ul>
                <li>
                <%--Changes for MPS 2.1 --%>
                	<a href="#grid" id='headerMenuButton'>
                	<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" />
					<spring:message code="requestInfo.link.customizeColumns"/></a>
					<span id='headerMenuButtonDisabled' style="display:none;">
                	<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" class="disabledImage"/>
					<spring:message code="requestInfo.link.customizeColumns"/></span>
				</li>
				<li> 
					<a id="resetLink" href="javascript:doReset();">
                	<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite reset-icon" /> 
                	<spring:message code="requestInfo.link.reset"/></a>
                	<span id='resetDisabled' style="display:none;">
                	<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite reset-icon disabledImage"/> 
                	<spring:message code="requestInfo.link.reset"/></span>
                	<%--Ends Changes for MPS 2.1 --%> 
                </li>
              </ul>
            </div>

            <!-- Expand-min End --> 
          </div>
		  
		  
          <!-- Grid section with pagination -->
          <div class="portlet-wrap rounded">
            <div class="portlet-wrap-inner">
              <div id="contactGrid_containerNew" class="gridbox gridbox_light"></div>
            </div>
            <div id="loadingNotification" class='gridLoading'>
	        	<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    	</div>
          </div>
          <div class="pagination" id="paginationId">
          		<span id="pagingArea"></span><span id="infoArea"></span>          
          </div>
          
          <!-- Button container -->
          <div class="buttonContainer">
            <button class="button_cancel" type="button" onclick="javascript:redirectToHistory('ListPage');"><spring:message code="button.cancel"/></button>
          </div>
      </div>
    </div>
    <%--  ADDED FOR MPS PHASE 2 --%>
    
    <div id="availableAction" style="display: none;">
    	   	<ul class="filters">
	    	<li><a class="cursor-pointer" id="editInlineContact"><spring:message code="requestInfo.contact.editContact"/></a></li>
	    	<li><a class="cursor-pointer" id="editContactAddress"><spring:message code="requestInfo.contact.editContactAddressLink"/></a></li>
	    	</ul>
    	
    </div>
     	<div id="requestConfirmDiv" style="display: none;">
      		
		 <div class="div-style32">
		 	<img src="<html:imagesPath/>green-check.png" alt="green check" width="50" height="50">
		 </div>
		 <div>
		 	<spring:message code="requestInfo.contact.saved"/>
		 </div>
		 <div class="buttonContainer div-style33">
          <button class="button buttonOK" onClick="dialog.dialog('close')"><spring:message code="button.ok"/></button>
        </div>
       </div>
     <%--  ADDED FOR MPS PHASE 2 ends --%>
</div>
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>
<script type="text/javascript">

var prevSrNo="${serviceRequestNumber}";
//alert(prevSrNo);
var isUpdateFlag="${isUpdateFlag}";
var avaialableActionsDialog;
var prevSrNumber='${previousSRNo}'; //This field receives the value of previous SR no for update flow
var bookmarkSortFlag = false;
var contactGrid;
var contactId;


//following variables are declared in dynamicGridInitialize
pagingArea="pagingArea";infoArea="infoArea";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification";backFilterValues="";
gridCreationId="contactGrid_containerNew";

JSON_Param["<%=gridConfigurationValues[0]%>"]=",<spring:message code='requestInfo.listHeader.contactFields'/>";
JSON_Param["<%=gridConfigurationValues[1]%>"]=",,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,";
JSON_Param["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
JSON_Param["<%=gridConfigurationValues[3]%>"]="10,35,35,35,170,150,170,150,200,110,110,110,110,110,110,110,100,100,100,100,100,100,100";
JSON_Param["<%=gridConfigurationValues[4]%>"]="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
JSON_Param["<%=gridConfigurationValues[5]%>"]="na,na,na,na,str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,na,na,na,na";
JSON_Param["<%=gridConfigurationValues[6]%>"]="4";
JSON_Param["<%=gridConfigurationValues[7]%>"]="4,asc";
JSON_Param["<%=gridConfigurationValues[8]%>"]="0,9,10,11,12,13,14,15,16,17,18,19,20,21,22";
JSON_Param["<%=JSON_COMBO_FILTER%>1"]="";
JSON_Param["<%=JSON_RESOURCE_URL%>"]="${contactResultList}";
JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";
/*alert("${gridSettings.colsHidden}");
alert("${gridSettings.colsOrder}");*/
/*
 *Added for MPS phase 2 
 *
 */

reloadGrid=function(){
	//clearMessage();
	//alert('in reload Grid');
	refreshGridSettingOnPage(requestListGrid);
	xmlURLQueryParams.setLoadXMLUrl();
	requestListGrid.clearAndLoad(xmlURL);
	
};
function subRowFunction(id,state){
	
	    if(state==false){
		    if(!inlineGridEdit.isCancelClicked)
	    		requestListGrid.cells(id,0).open();
		    else
		    	inlineGridEdit.isCancelClicked=false;
	    }else{
	    	jQuery('.dhx_sub_row button[name=save]').unbind('click');
	    	jQuery('.dhx_sub_row button[name=cancel]').unbind('click');
	    	jQuery('.dhx_sub_row button[name=save]').click(validateAndSaveToDB);
	    	jQuery('.dhx_sub_row button[name=cancel]').click(cancelEditEvent);
	    		    	
		    }
	
}
function onPageChangedFunction(){
	afterXLEfunction();
}
function onMouseOverFunction(id,ind){
	if(ind>3)
		return true;
	else	
		return false;
}
function editCellfunction(stage,rId,cInd,nValue,oValue){


	
	if(cInd !=0){
		
					if(stage==0){
									var cellObj = requestListGrid.cellById(rId,cInd);
									cellObj.setValue(jQuery(cellObj.getValue()).val());
									

						}else if(stage==1){

						}else if(stage==2){
							var cellObj = requestListGrid.cellById(rId,cInd);
							var cellvalue=cellObj.getValue();
							cellObj.cell.innerHTML="<input type=text value=\""+cellvalue+" "+"\" onmousedown=\"this.click();\" onmouseover=\"this.click();\">";
							
						}
					 return true;

				
				}

}

initURLParams = function(){
		xmlURLQueryParams={
				isFavorite:false,
				urlParameters:["direction","orderBy","filterCriterias","searchCriterias","isContactPopUp"],
				setParameters : function(){
										this["direction"]=requestListGrid.a_direction;
										this["orderBy"]=requestListGrid.getSortColByOffset()-4;
										this["isContactPopUp"]=false;
										this["searchCriterias"]=this.isFavorite==true?"${contactid}":null;
										//this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
										var filterListValues=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
										this["filterCriterias"]=formatFilterValues(filterListValues);
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

//Ends Phase 2






jQuery(document).ready(function(){
	var currentURL = window.location.href;
//	Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	
	bindEditContactInlineEvent();
	jQuery('#errorMsg').empty();
	//Show errros if contact details is not retrieved
	var isException = '${exception}';
	if (isException) {
		jQuery('#errorMsg').append('<li><strong><spring:message code="exception.unableToRetrieveContactDetail"/></strong></li>');
		jQuery('#errorMsg').show();
	}
	initialiseGrid();
	requestListGrid.setColumnMinWidth(35,1);
	requestListGrid.setColumnMinWidth(35,2);
	requestListGrid.setColumnMinWidth(35,3);
	requestListGrid.setColumnMinWidth(200,8);

});


 
	

		
	


	
	


		
	    

/*
 * Navigate to add contact page	
 */	
function forwardToAddContactJSP(value)
{
	//alert(value);
	showOverlay();
	window.location.href="<portlet:renderURL><portlet:param name='action' value='redirectToAddContact' /></portlet:renderURL>&selectedVal="+value
	+"&prevSrNo=" + prevSrNo+ "&isUpdateFlag=" + isUpdateFlag;
}
/*
 * Navigate to change or remove contact page
 */
function changeRemoveContact(requestType, contactId){
	var rId=contactId;
	showOverlay();
	try {
		 jQuery.ajax({
			url:'${setOldContactSession}&requestType='+requestType,			
			type:'POST',
			data: {
				 	//Ajax call with selected row data
					contactId :   contactId,
					firstName : requestListGrid.cellById(rId,5).getValue(),
					lastName: requestListGrid.cellById(rId,4).getValue(),
					workPhone: requestListGrid.cellById(rId,6).getValue(),
					alternatePhone: requestListGrid.cellById(rId,7).getValue(),
					emailId: requestListGrid.cellById(rId,8).getValue(),
					addressLine1: requestListGrid.cellById(rId,9).getValue(),
					addressLine2: requestListGrid.cellById(rId,10).getValue(),
					//addressLine3: requestListGrid.cellById(rId,10).getValue(),
					city: 		  requestListGrid.cellById(rId,12).getValue(),
					state:	  requestListGrid.cellById(rId,13).getValue(),
					province:	  requestListGrid.cellById(rId,14).getValue(),
					country:	  requestListGrid.cellById(rId,17).getValue(),
					zipPostal:	  requestListGrid.cellById(rId,18).getValue(),
					building:    requestListGrid.cellById(rId,19).getValue(),
					floor:    requestListGrid.cellById(rId,20).getValue(),
					office:    requestListGrid.cellById(rId,21).getValue(),
					middleName:    requestListGrid.cellById(rId,22).getValue(),
					officeNumber: requestListGrid.cellById(rId,11).getValue(),
					county:       requestListGrid.cellById(rId,15).getValue(),
					district:       requestListGrid.cellById(rId,16).getValue()
			},
			success: function(results){
					//If ajax call is success, go to change to remove contact page
					//alert("ajax success");
					
					
					var finalUrl='${changeRemoveContactURL}&contactId='+contactId+'&requestType='+requestType+'&prevSrNo=' + prevSrNo
					+ '&isUpdateFlag=' + isUpdateFlag;
					
					window.location.href=finalUrl;
				},
			error: function (jqXHR, textStatus, errorThrown) {
					//alert("contact list textStatus= "+textStatus);
					jQuery('#errorMsg').append('<li><strong><spring:message code="exception.unableToRetrieveContactDetail"/></strong></li>');
					jQuery('#errorMsg').show();
				}
			});
	} catch (ex) {
			//alert("contact list ex= "+ex);
			jQuery('#errorMsg').append('<li><strong><spring:message code="exception.unableToRetrieveContactDetail"/></strong></li>');
			jQuery('#errorMsg').show();
	}
}

/*
 * Added for Bookmark Contact
 */
var favStatus = {};
/*
 * Set the contact to favourite or unfavourite
 */
function setContactFavourite(favContactId, doSelectUnfavoriteFlag) {
	//alert("contactid "+'${contactid}'+" favContactId "+favContactId);
    var url = '<portlet:resourceURL id="setContactFavouriteFlag"/>';
    url += "&contactId=${contactid}";
    url += "&favoriteContactId=" + favContactId;
    
    // if user selects an unfavorite contact
    if (doSelectUnfavoriteFlag) {
        url += "&flagStatus=" + "false";
        doAjax(url, null, null, "");
        return;
    }
    // if the favorite flag is being updated
    if (favStatus[favContactId]&&favStatus[favContactId].isLoading) {
        return;
    }
    
    var starImg = window.document.getElementById("starImg_primary_"+favContactId);
    var isFavorite=(jQuery("#starImg_primary_"+favContactId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
    //var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
    favStatus[favContactId] = {isFavorite:isFavorite,isLoading:true};
    starImg.src = "<html:imagesPath/>loading-icon.gif";
    url += "&flagStatus=" + isFavorite;
    
    doAjax(url, callbackGetPrimaryContactResult, callbackGetPrimaryContactFailure, "");
}

/*
 * call back, when successfully update favorite status of primary contact
 */
function callbackGetPrimaryContactResult(result) {
    var contactId = result.data;
    if (document.getElementById("contactGrid_containerNew").style.display!="none") {
        var starImg = document.getElementById('starImg_primary_' + contactId);
        //just change the star image
        if (favStatus[contactId].isFavorite) {
        	jQuery('#starImg_primary_' + contactId).removeClass('bookmark-star-icon');
			jQuery('#starImg_primary_' + contactId).addClass('removebookmark-icon');
			starImg.src = "<html:imagesPath/>transparent.png";
            //starImg.src = "<html:imagesPath/>unfavorite.png";
            //alert('unbookmarked');
            //document.getElementById('yourElementId').title = 'your new title';
            
            //jQuery('.helpIconList').attr('title', titleValue);
            //jQuery('#starImg_primary_' + contactId).attr('title', 'Bookmark');
            starImg.title = '<spring:message code="requestInfo.tooltip.contact.Bookmark"/>';
        } else {

        	jQuery('#starImg_primary_' + contactId).addClass('bookmark-star-icon');
			jQuery('#starImg_primary_' + contactId).removeClass('removebookmark-icon');
			starImg.src = "<html:imagesPath/>transparent.png";
        	//starImg.src = "<html:imagesPath/>favorite.png";
            //alert('bookmarked');
            //document.getElementById('yourElementId').title = 'your new title';
            
            //jQuery('#starImg_primary_' + contactId).attr('title', 'UnBookmark');
            starImg.title = '<spring:message code="requestInfo.tooltip.contact.UnBookmark"/>';
        }
        //showToolTipList('helpIconList');
        showToolTipListbyId('starImg_primary_' + contactId);
    }
    if (favStatus[contactId].isFavorite) {
        favStatus[contactId].isFavorite = false;
    } else {
        favStatus[contactId].isFavorite = true;
    }
    favStatus[contactId].isLoading = false;
}

/*
 * call back, when fail to update favorite status of primary contact
 */
function callbackGetPrimaryContactFailure(result) {
    var contactId = result.data;
    if (document.getElementById("contactGrid_containerNew").style.display!="none") {
        var starImg = document.getElementById('starImg_primary_' + contactId);
        //just change the star image
        if (favStatus[contactId].isFavorite) {
            starImg.src = "<html:imagesPath/>transparent.png";
        } else {
            starImg.src = "<html:imagesPath/>transparent.png";
        }
    }
    favStatus[contactId].isLoading = false;
}

/*
 * Show all account contacts
 */
function showAllAccountContact(){
	jQuery('#allContacts').html('<spring:message code="requestInfo.link.allAccountContacts"/>');
	jQuery('#myBookmarkContacts').html('<a href="javascript:showBookmarkedContact();"><spring:message code="requestInfo.link.myBookmarkContacts"/></a>');
	xmlURLQueryParams.isFavorite=false;
	
	reloadGrid();
}

/*
 * Show only bookmarked contacts
 */
function showBookmarkedContact(){
	//disable my bookmarked contacts
	jQuery('#myBookmarkContacts').html('<spring:message code="requestInfo.link.myBookmarkContacts"/>');
	//change All Account Contact to Link
	jQuery('#allContacts').html('<a href="javascript:showAllAccountContact();"><spring:message code="requestInfo.link.allAccountContacts"/></a>');
	xmlURLQueryParams.isFavorite=true;
	
	reloadGrid();
	
}
//Added for MPS 2
/*
 * Changes
 */
function afterXLEfunction(){
	//alert('in contact list');
	showToolTipList('helpIconList');
	
	bindDialogAndClickEvents();
	
	
	
}


function bindDialogAndClickEvents(){
	jQuery('.helpIconList').click(function(){
		if(jQuery(this).attr('name')=="remove")
			changeRemoveContact('removeContactRequest',jQuery(this).attr('id').split("_")[1]);
		else if (jQuery(this).attr('name')=="bookmark")
			setContactFavourite(jQuery(this).attr('id').split("_")[2]);
			
	}).removeClass('disabledImage');

jQuery('.changeContactDialog').mouseover(function(){
	
	
	
	 if(avaialableActionsDialog!=null)
		 avaialableActionsDialog.dialog('close');
	 

	 inlineGridEdit.rowId=jQuery(this).attr('id').split("_")[1]; 
	// alert(inlineGridEdit.prevRowId);
	// alert(inlineGridEdit.rowId);
	 jQuery('#availableAction').show();
	 var x = jQuery(this).offset().left ;
	 var y = jQuery(this).offset().top-jQuery(window).scrollTop() ;
	
	 avaialableActionsDialog=jQuery('#availableAction').dialog({
			autoOpen: false,
			title: '<spring:message code="requestInfo.heading.avlAction"/>',
			modal: false,
			position:[x+20,y],
			height: 100,
			width: 200,
			resizable: false,
			draggable:false,
			show:{
		 		effect: "slide",
				duration: 500
			},
			
			close:function(){
					avaialableActionsDialog.dialog('destroy');
					avaialableActionsDialog=null;
					
					}
			});
	
		avaialableActionsDialog.dialog('open');
		 //jQuery('#availableAction').mouseout();
		 var filterTimer=null;
		 
		 jQuery('.ui-dialog').mouseout(function(){
			 filterTimer=setTimeout(function(){
				 	if(avaialableActionsDialog!=null)
			 			avaialableActionsDialog.dialog('close');
				 	},500);
			 });
		 
		 
		 jQuery('.ui-dialog').mouseover(function(){
			 if(filterTimer!=null) {
			 	clearTimeout(filterTimer);
			 }
			});
		

	 }).removeClass('disabledImage');
}

function bindEditContactInlineEvent(){
	jQuery('#editContactAddress').click(function(){
		changeRemoveContact('changeContactRequest',inlineGridEdit.rowId);
	});
	
	jQuery('#editInlineContact').click(function(){

		<%-- This is for disabling customize option of the grid and resizing and column move and sorting--%>
		
			jQuery('#headerMenuButtonDisabled,#resetDisabled').show();
			jQuery('#headerMenuButton,#resetLink').hide();
			if(xmlURLQueryParams.isFavorite==false){
					jQuery('#myBookmarkLiDisabled').show();
					jQuery('#myBookmarkLi').hide();
			}else{
					jQuery('#allContacts').hide();
					jQuery('#allContactsDisabled').show();
				}
		<%--Disable the filters --%>	
		jQuery('.filter input[type=text]').attr('disabled','disabled');
		
		requestListGrid.enableColumnMove(false);
		generateEnableDisableString(false);
		
		
		<%--Ends disbling customize and resizing option  --%>
		jQuery('.changeContactDialog').unbind().addClass('disabledImage');

		
		jQuery('.helpIconList').unbind('click').addClass('disabledImage');
		
		avaialableActionsDialog.dialog('close');
		changetoEditTexts();
			
		});
}

function changetoEditTexts(){

	
	//alert(requestListGrid.isColumnHidden(0));
	//requestListGrid.setColumnHidden(0,false);
	//inlineGridEdit.createSaveButton();
	inlineGridEdit.changeToEdit();
	

	
}
 var inlineGridEdit={
			rowId:0,
			cellValues: ["workPhone","alternatePhone","emailAddress"],
			isCancelClicked: false,
			createSaveButton: function(){
				
				},
			changeToEdit:function(){
					//alert(this.rowId);
					for(i=6;i<=8;i++){
						if(requestListGrid.isColumnHidden(i)){
							//Show the column if it is hidden
							requestListGrid.setColumnHidden(i,false);
						}
						requestListGrid.setCellExcellType(this.rowId,i,"ed");
						var cellObj=requestListGrid.cellById(this.rowId,i);
						this[this.cellValues[i-6]]=cellObj.getValue();
						var htmlCell="<input type=\"text\" value=\""+cellObj.getValue()+"\" style=\"width:100% !important;\" onmouseover=\"this.click();\">";
						cellObj.cell.innerHTML=htmlCell;
						
					}
					requestListGrid.cells(this.rowId,0).open();
					<%--  this section is for positioning select and cancel button--%>
					var totalWidth=0;
					for(i=0;i<8;i++){
						totalWidth+=requestListGrid.getColWidth(i);
						}
					
					jQuery('.button_subrow1').css('margin','0 5px 0 '+totalWidth+'px');
					<%--  ENDS  this section is for positioning select and cancel button--%>
					
				},
			setValuesToCell:function(rowId){
						for(i=6;i<=8;i++){
							requestListGrid.setCellExcellType(rowId,i,"ro");
							var cellObj=requestListGrid.cellById(rowId,i);
							cellObj.cell.innerHTML=this[this.cellValues[i-6]];
						}
						this.isCancelClicked=true;	
						requestListGrid.cells(this.rowId,0).close();	
						
					}
			
						
	};
	function cancelEditEvent(){
		
			enableLinksOfGrid();
		
			var rowId=jQuery(this).attr('id').split("_")[1];
			requestListGrid.editStop();
			inlineGridEdit.setValuesToCell(rowId);
			bindDialogAndClickEvents();
			/*jQuery('.changeContactDialog').click(function(){
				inlineGridEdit.rowId=jQuery(this).attr('id').split("_")[1];
				jQuery('.changeContactDialog').unbind(); 
				changetoEditTexts();
			});*/

		}
	function validateAndSaveToDB() {
		requestListGrid.editStop();
		
		
		jQuery('#errorMsg').hide();
		//alert('in click func');
		var workPhone="",alternatePhone="",emailAddr="",firstName="",lastName="",country="";
		var rowId=jQuery(this).attr('id').split("_")[1];
		var cellObj=requestListGrid.cellById(rowId,6);
		
		workPhone=jQuery(cellObj.getValue()).val();
				
		cellObj=requestListGrid.cellById(rowId,7);
		
		if(cellObj.getValue()!=""){
			alternatePhone=jQuery(cellObj.getValue()).val();	
		}
		
		cellObj=requestListGrid.cellById(rowId,8);
		if(cellObj.getValue()!=""){
			emailAddr=jQuery(cellObj.getValue()).val();	
		}
		
		

		cellObj=requestListGrid.cellById(rowId,5);
		firstName=cellObj.getValue();
		
		
		cellObj=requestListGrid.cellById(rowId,4);
		lastName=cellObj.getValue();
		

		cellObj=requestListGrid.cellById(rowId,14);
		country=cellObj.getValue();
		//alert('contry'+country);
		
		validationFlagPopup = true;
		document.getElementById('errorMsg').innerHTML = "";

		//Validate Work Phone
		
		
		if(!phoneValidate(workPhone,"workphone","changeContact")){
			validationFlagPopup = false;
		}
		
		if(!phoneValidate(alternatePhone,"alternatephone","changeContact")){
			validationFlagPopup = false;
		}
		
		if(!emailValidate(emailAddr,"emailAddr","changeContact")){
			validationFlagPopup = false;
		}
	
		//If validation failed, show errors
		if (!validationFlagPopup) {
			document.getElementById('errorMsg').style.display = "block";
			jQuery(document).scrollTop('0');
			//alert("block error");
			return false;
		}else{
			requestListGrid.setColumnHidden(0,true);
			
			jQuery.ajax({
				url:'${submitUpdatedContactURL}',
				
				type:'POST',
				dataType: 'JSON',
				data: {
						workPhone: workPhone,
						alternatePhone: alternatePhone,
						emailAddr: emailAddr,
						contactId: rowId,
						firstName: firstName,
						lastName: lastName,
						country: country
					},
				beforeSend:function(){
						showOverlayPopup();	
						
						},
				success: function(results){
						//alert('success');
						hideOverlayPopup();
						var obj2;
						//obj2=jQuery.parseJSON(results);
						obj2=results;
						//alert(results);
						
						if(obj2 !=null){						
							var error=obj2.error;
							
							if(error=="none"){
								/*for(i=6;i<=8;i++){
									requestListGrid.setCellExcellType(rowId,i,"ro");
								}*/
								
									dialog=jQuery('#requestConfirmDiv').dialog({
										autoOpen: false,
										title: "<spring:message code='requestInfo.message.title'/>",
										modal: true,
										height: 460,
										width: 400,
										position: 'center',
										open:function(){
											
											jQuery('#requestConfirmDiv').show();
										},
										close: function(event,ui){
											
												enableLinksOfGrid();											
											 dialog.dialog('destroy');
											 requestListGrid.setColumnHidden(0,true);
											 reloadGrid();
											}
										});
									
									dialog.dialog('open');
								
								}else{
									
									jQuery(document).scrollTop(0);
									jQuery('#errorMsg').html('<li><strong><spring:message code='exception.cm.common.webServiceCall'/></strong></li>');
									jQuery('#errorMsg').show();
									}
							
						}
					
							
					},
				failure: function(results){
							jQuery('#errorMsg').html('<li><strong><spring:message code='exception.cm.common.webServiceCall'/></strong></li>');
							jQuery('#errorMsg').show();
						}
			});	
		}
		
	}


	function generateEnableDisableString(flag){
		var defaultresizing=new Array(requestListGrid.getColumnsNum());
		var sortingState=new Array(requestListGrid.getColumnsNum());
		for(i=0;i<defaultresizing.length;i++){
			defaultresizing[i]=flag;
			sortingState[i]="na";
		}
		if(flag)
			requestListGrid.setColSorting(JSON_Param["<%=gridConfigurationValues[5]%>"]);
		else
			requestListGrid.setColSorting(sortingState.toString());
		requestListGrid.enableResizing(defaultresizing.toString());
		}

	function enableLinksOfGrid(){
		<%-- Enable customize menu and enable resizing and column move--%>
		jQuery('#headerMenuButton,#resetLink').show();
		jQuery('#headerMenuButtonDisabled,#resetDisabled').hide();

		if(xmlURLQueryParams.isFavorite==false){
			jQuery('#myBookmarkLiDisabled').hide();
			jQuery('#myBookmarkLi').show();
		}else{
			jQuery('#allContacts').show();
			jQuery('#allContactsDisabled').hide();
		}

		
		<%--Enable the filters --%>	
		jQuery('.filter input[type=text]').removeAttr('disabled');



		
		requestListGrid.enableColumnMove(true);
		generateEnableDisableString(true);
		
		<%-- Ends Enable customize menu and enable resizing--%>
		}
	
	
	/* Added for CI7 BRD14-02-12 */
	ajaxSuccessFunction=function updateRequest(){
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);					
	}
	/* END */
	function formatFilterValues(filterListValues){
		var newFilterListValues=[];
		for (i=0;i<filterListValues.length;i++){
			filterListValues[i]=filterListValues[i].replace("#","%23");
			newFilterListValues[i]=filterListValues[i]==null?"":filterListValues[i];
		}
		return newFilterListValues;
	}
</script>