<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>





<div class="portletBlock" id="content_contact">
  <div class="columnsOne">
    <div class="columnInner">
    
      <%-- <div id="msgBanner" style="display:none">	
	      <ul id="message_banner_" class="serviceSuccess">
	        	<li class="portlet-msg-success"><spring:message code="message.servicerequest.setContactFavouriteFlag"/></li>
	      </ul>
	  </div> --%>
	  <%--Added for MPS 2.1 --%>
	  <div><ul class="serviceSuccess" id="message_banner_Popup_contact"></ul>
				<ul class="serviceError" id="error_banner_Popup_contact"></ul>
				  
	 </div>
	  <%--ends Added for MPS 2.1 --%>      
	  <div class="error" id="errorMsgPopup" style="display:none"></div>
    
      <div class="grid-controls">
        <div class="filterLinks">
          <ul>
            <li class="first" id="allContacts"><spring:message code="requestInfo.link.allAccountContacts"/></li>
            <li id="myBookmarkContacts"><img src="<html:imagesPath/>favorite.png" width="15" height="15" />
            	<a href="javascript:showBookmarkedContact();"><spring:message code="requestInfo.link.myBookmarkContacts"/></a>
            </li>
          </ul>
        </div>
      </div>   
	  
	  <!-- Grid section -->		
	  <div class="portlet-wrap rounded">
        <div class="portlet-wrap-inner">
          <div id="contactGridbox" class="gridbox gridbox_light"></div>
        </div>
        <div id="loadingNotification" class='gridLoading'>
	      <br><spring:message code='label.loadingNotification'/>&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
	    </div>
      </div>
      
      <!-- Pagination section -->
	  <div class="pagination" id="paginationId">
		<span id="pagingArea"></span><span id="infoArea"></span>
	  </div>
      
      
	  
	  <!-- Button container -->
	  <div class="buttonContainer" id="createButtonContact">
		<button class="button_cancel" id="cancelNewContactPopup" onclick="javascript:closeDialog();callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPCANCELCONTACTPOPUPBUTTON%>');" type="button"><spring:message code="button.cancel"/></button>
		<button class="button" id="createNewContactPopup"
		onclick="javascript:showNewContact();" type="button"><spring:message code="button.createANewContact"/></button>
	  </div>
	  
	  <!-- Hidden fields for contact -->
	  <div style="display: none;">
		<input id="contactId" name="contactId" type="text" />
		<input id="contactData1" name="contactData" type="text" />
		<input id="contactData2" name="contactData" type="text" />
		<input id="contactData3" name="contactData" type="text" />
		<input id="contactData4" name="contactData" type="text" />
		<input id="contactData5" name="contactData" type="text" />
	  </div>	
			
	  <!-- This block is shown when user will click on Create A New Contact button -->
	  <div class="oneblock" id="update1" style="display: none;">
	  <h3 class="pageTitle"><spring:message code="requestInfo.popup.heading.createNewContact"/>
	  	<span><spring:message code="requestInfo.label.fieldsMarked"/> 
	  	<span class="req">*</span> 
	  	<spring:message code="requestInfo.label.areRequired"/></span>
	  </h3>
	  <p class="info"><spring:message code="requestInfo.cm.manageContact.heading.pleaseEnterTheFollowingInformationToAddaNewContact"/></p>
	  <div class="portletBlock rounded shadow">
          
            <div class="columnInner">
              <ul class="form w70p">
                <li>
                	<label for="lastNamePopup"><spring:message code="requestInfo.contactInfo.label.lastName"/> 
                	<span class="req">*</span></label> 
					<span><input id="lastNamePopup" name="lastNamePopup" size="30" type="text" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
				</li>
				<li>
					<label for="firstNamePopup"><spring:message code="requestInfo.contactInfo.label.firstName"/>
					<span class="req">*</span></label> 
					<span><input id="firstNamePopup" name="firstNamePopup" size="30" type="text" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
				</li>
				<li>
					<label for="workPhonePopup"><spring:message code="requestInfo.contactInfo.label.workPhone"/>
					<span class="req">*</span></label> 
					<span><input id="workPhonePopup" name="workPhonePopup" size="30" type="text" maxlength="35" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
				</li>
				<li>
					<label for="alternatePhonePopup"><spring:message code="requestInfo.contactInfo.label.alternatePhone"/></label> 
				  	<span><input id="alternatePhonePopup" name="alternatePhonePopup" size="30" type="text" maxlength="35" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
				</li>
				<li>
					<label for="emailPopup"><spring:message code="requestInfo.contactInfo.label.emailAddress"/>
					<span class="req">*</span></label> 
					<span><input id="emailPopup" name="emailPopup" size="30" type="text" maxlength="100" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
				</li>
              </ul>
            </div>
          
        </div>
        
        <div class="buttonContainer">
			<button class="button_cancel"
				onclick="javascript:closeDialog();  callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPCANCELCONTACTPOPUPBUTTON%>');" type="button"><spring:message code="button.cancel"/></button>
			<button class="button" onclick="javascript:addAndSaveToMainPage(); callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPSAVENEWCONTACTBUTTON%>');" type="button"><spring:message code="button.saveNewContact"/></button>
		</div>
	  </div>
				
    </div>
  </div>
</div>

	
<script>
var flag;//this flag is used to check whether user has clicked on edit or not.
var editornotClosedflag=false;
//ends
var linkspanId;//Id of the anchor of mainPage
var inputIds;//Id of the input of mainPage
var bookmarkSortFlag = false; //Added for differentiate between bookmark and all contacts

var functionToCallAfterSelect;

	//Hide the new contact section
	function hideNewContact() {
		//alert("Hide");
		document.getElementById('createButtonContact').style.display = "block";
		document.getElementById('update1').style.display = "none";
		jQuery('#content_contact').data('height.dialog','550px');
	}

	//Show the new contact section
	function showNewContact() {
		 callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPCREATENEWCONTACTBUTTON%>');
		//alert('showNewContact');
		document.getElementById('createButtonContact').style.display = "none";
		document.getElementById('update1').style.display = "block";
		//jQuery('#update').show();
		//alert('showNewContact end'+document.getElementById('update1').style.display);
		jQuery('#content_contact').data('height.dialog','720px');
	}
	
	var searchCriteria="";
	var contactGrid;
	function initializeContactGrid(clickedFromId,spanContactId,selectSuccessFunction) {	
		linkspanId=clickedFromId;	
		inputIds=spanContactId;
		functionToCallAfterSelect=selectSuccessFunction;
	contactGrid = new dhtmlXGridObject('contactGridbox');
	//alert("111");
	contactGrid.setImagePath("<html:imagesPath/>gridImgs/");
	//alert("222");
	contactGrid.setHeader("<spring:message code='requestInfo.listHeader.contactListPopup'/>");
	contactGrid.setInitWidths("30,100,100,100,100,120,80,*");
	contactGrid.attachHeader("&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,&nbsp;,&nbsp;");
	contactGrid.setColAlign("left,left,left,left,left,left,center,center");
	contactGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
	contactGrid.setColSorting("na,str,str,str,na,str,na,na");
	contactGrid.a_direction = "asc";
	contactGrid.init();
	contactGrid.prftInit();
	contactGrid.enablePaging(true, 5, 6, "pagingArea", true, "infoArea");
	contactGrid.setPagingSkin("bricks");
	contactGrid.enableAutoWidth(true);
	contactGrid.enableAutoHeight(true);
	contactGrid.enableMultiline(true);
	contactGrid.enableColumnMove(false);
	contactGrid.setSizes();	
	//var url='<portlet:resourceURL id="primarycontactListPopulate"/>';
	//url=url+"&orderBy=2"+"&isContactPopUp=true";
	//alert(url);
	customizedGridURL = populateURLCriterias();
	contactGrid.loadXML(customizedGridURL);
	//contactGrid.setColumnHidden(0,true); 
	contactGrid.setSkin("light");
	contactGrid.sortIndex = null;
	contactGrid.loadOrder(colsOrder);
	contactGrid.loadPagingSorting(colsSorting,1);
	contactGrid.loadSize(colsWidth);
	contactGrid.filterValues = ",,,,,,";
	contactGrid.enableEditEvents(true,false,false);
	//alert("111a");
	contactGrid.attachEvent("onXLS", function() {
		//alert("onXLS");
		document.getElementById('loadingNotification').style.display = 'block';
		document.getElementById('paginationId').style.display = 'none';
		//alert("222a "+ contactGrid.a_direction);
		if(contactGrid.a_direction=='asc'){
			
			contactGrid.setSortImgState(true, contactGrid.getDefaltSortImagIndex(contactGrid.sortIndex,1), 'asc');
        }else{
        	contactGrid.setSortImgState(true, contactGrid.getDefaltSortImagIndex(contactGrid.sortIndex,1), 'des');
        }
		document.getElementById('loadingNotification').style.display = 'block';
	});
	contactGrid.attachEvent("onXLE", function() {
		//contactGrid.setSortImgState(true, 2, "asc");
		document.getElementById('loadingNotification').style.display = 'none';
		document.getElementById('paginationId').style.display = 'block';
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
		document.getElementById('errorMsgPopup').style.display = 'none';
		<%-- Changes for defect 8064 MPS 2.1--%>
		bindBookmarkEvents();
		<%--Ends Changes for defect 8064 MPS 2.1--%>
	});
	<%-- Changes for defect 8064 MPS 2.1--%>
	contactGrid.attachEvent("onPageChanged",function(){
		bindBookmarkEvents();
		});
	<%-- Ends Changes for defect 8064 MPS 2.1--%>
	contactGrid.attachEvent("onMouseOver", function(id,ind){		
		return false;
	});
	//customizedGridURL = populateURLCriterias();;
	contactGrid.attachEvent("onFilterStart", function(indexes,values){
		//alert("onFilterStart");
    	contactGrid.filterValues = ","+values;
    	customizedGridURL = updateGridURL(customizedGridURL, contactGrid.getSortColByOffset(), contactGrid.a_direction, contactGrid.filterValues);
    	//alert("customizedGridURL  "+customizedGridURL);
    	setGridFilerTimer(reloadGrid);
    });

    //When the grid cell will be editable, this method will be called
	contactGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		if(cInd !=0){
						if(stage==0){
										var cellObj = contactGrid.cellById(rId,cInd);
	   								  cellObj.setValue(splitValues(cellObj.getValue()));

							}else if(stage==1){

							}else if(stage==2){
								var cellObj = contactGrid.cellById(rId,cInd);
								var cellvalue=cellObj.getValue();

								cellObj.cell.innerHTML="<input type=text value=\""+cellvalue+" "+"\" onmousedown=\"this.click();\">";

							}
						 return true;

					
					}
			});
		
	contactGrid.attachEvent("onBeforeSorting", customColumnSort);
	}

	//When user clicks edit button in grid, this method will be called
	function changeToEdit(rId,element){
							
				 
							  var buttonLabel=jQuery(element).val();
							  if(buttonLabel=='<spring:message code='button.edit'/>'){
								   callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPEDITCONTACT%>');
								  		//alert('inline edit');
								  		jQuery(element).val('<spring:message code='button.save'/>');
										for(i=3;i<=5;i++){
									  		var cellObj = contactGrid.cellById(rId,i);
											var cellvalue=cellObj.getValue();
											contactGrid.setCellExcellType(rId,i,"ed");
											cellObj.cell.innerHTML="<input type=text value=\""+cellvalue+" "+"\" onmouseover=\"this.click();\">";
										}
										jQuery('#btn_select_'+rId).attr('disabled',true);
										jQuery('#btn_select_'+rId).attr('style','opacity:.5;background:#81BEF7!important;');//Added background to show disable buttons - defect 8067
																				
							}
							  else{
								  contactGrid.editStop();
								  if (inlineValidate(rId)) {
								  flag=true;
								  saveUpdatedContact(rId);
								  callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPSAVENEWCONTACTBUTTON%>');
								  jQuery(element).val('<spring:message code='button.edit'/>');
								  jQuery('#btn_select_'+rId).attr('disabled',false);
								  jQuery('#btn_select_'+rId).attr('style','opacity:1');

								  //Save Updated contact
								  
								  jQuery('#contactId').val(rId);
								  for(i=1;i<=5;i++){
									  var cellObj = contactGrid.cellById(rId,i);
									  var cellvalue=cellObj.getValue();
									  jQuery('#contactData'+i).val(cellvalue);
									  
									
								  }
								  }
							  }
	}
	function saveUpdatedContact(rId) {
		contactGrid.editStop();
		 for(i=3;i<=5;i++){
			var cellObj = contactGrid.cellById(rId,i);
			var cellvalue=cellObj.getValue();
			contactGrid.setCellExcellType(rId,i,"ro");
			cellObj.cell.innerHTML=splitValues(cellvalue);
			
			}
	}	
	function splitValues(cellvalue){
			var dataArr=cellvalue.split("\"");
							if(dataArr.length == 1)
								return cellvalue;
							else{
								var j=0;
								for(j=0;j<dataArr.length;j++){
								if(dataArr[j].indexOf('value=')!= -1){
										return dataArr[j+1];
									}
								}

								}
	}						
	function reloadGrid() {	 
		//alert("reloadGrid for filter");
		clearMessage();			
		refreshGridSettingOnPage(contactGrid);
		//contactGrid.clearAndLoad(customizedGridURL);	
		//alert("bookmarkSortFlag="+bookmarkSortFlag);
		if (!bookmarkSortFlag) {
			contactGrid.clearAndLoad(customizedGridURL);
		}
		else {
			contactGrid.clearAndLoad(customizedGridURL+"&fav='true'");
		}
	}

	//Populate the grid url
	function populateURLCriterias(){		
		var url='<portlet:resourceURL id="primarycontactListPopulate"/>';
		//var colByOffset = contactGrid.getSortColByOffset();
		//url = url + "&orderBy="+colByOffset;		
		url=url+"&orderBy=1";
		url=url+"&isContactPopUp=true";
		contactGrid.setSortImgState(true, 1, "asc");
		//alert(url);    
	    return url;
	};

	//Sort the grid columns
	function customColumnSort(ind) {
		//callOmnitureAction('primary Contact Request', 'primary Contact Request List Sort');
		//alert("ind:::"+ ind);
		var a_state = contactGrid.getSortingState();
		//alert("a_state:::"+ a_state);
		if(a_state[0] == (ind)){
			contactGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
			//alert("contactGrid.a_direction "+contactGrid.a_direction);
		}else {
			contactGrid.a_direction = "asc" ;
			contactGrid.columnChanged = true;
		}
		contactGrid.sortIndex = ind;
		customizedGridURL = updateGridURL(customizedGridURL, contactGrid.getSortColByOffset(), contactGrid.a_direction, contactGrid.filterValues);
		//alert("customizedGridURL::: "+ customizedGridURL);
	 	reloadGrid();
		return true;
	}
	
	//Added on 9 April for inline edit validation
	
	function inlineValidate(rId) {
		
		validationFlagPopup = true;
		document.getElementById('errorMsgPopup').innerHTML = "";
		<%--   jQuery('#msgBanner').hide();--%>
		//Set error message section as popup
		setPopup();
		
		//Validate Work Phone
		var cellObj = contactGrid.cellById(rId,3);
		var cellvalue=splitValues(cellObj.getValue());
		//alert('wp '+cellvalue);
		if(!phoneValidate(cellvalue,"workphone","changeContact")){
			validationFlagPopup = false;
		}
	
		//Validate Alternate Phone
		var cellObj = contactGrid.cellById(rId,4);
		cellvalue=splitValues(cellObj.getValue());
		//alert('ap '+cellvalue);
		if(!phoneValidate(cellvalue,"alternatephone","changeContact")){
			validationFlagPopup = false;
		}
	
		//Validate Email Address
		var cellObj = contactGrid.cellById(rId,5);
		cellvalue=splitValues(cellObj.getValue());
		//alert('ea '+cellvalue);
		if(!emailValidate(cellvalue,"emailAddr","changeContact")){
			validationFlagPopup = false;
		}
		//If validation failed, show errors
		if (!validationFlagPopup) {
			document.getElementById('errorMsgPopup').style.display = "block";
			//alert("block error");
			return false;
		}
		//If validation successful, return true
		else {
			document.getElementById('errorMsgPopup').style.display = "none";
			return true;
		}
		
	}

	//Update the contact. If user edit a cell and select it, this method will be called
	function updateContact(ad1,ad2,city,state,province,country,pocode,
			phyLoc1,phyLoc2,phyLoc3,midName,altPhn){
		
		var ci = document.getElementById("contactId").value;
		var ln = document.getElementById("contactData1").value;
		var fn = document.getElementById("contactData2").value;
		var wp = document.getElementById("contactData3").value;
		var ap = document.getElementById("contactData4").value;
		var ea = document.getElementById("contactData5").value;
		
		
		
		
		
		
	}
	
	
	//If user selects a row from grid, this method will be called
	function selectContact(contactId,element){
		// callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPSELECTCONTACT%>');
		 var contactRowObj={
					clickFromId:"",
					inputIds:"",
					keys:["lastName","firstName","workPhone","alternatePhone","emailAddress","contactId"],
					setValues:function(rowId){
						for(i=0;i<this.keys.length;i++){
							if(this.keys[i]=="contactId")
								this[this.keys[i]]=contactId;
							else
								this[this.keys[i]]=contactGrid.cellById(contactId,i+1).getValue();
							}		
					}
					
							
				};
		 contactRowObj.setValues(contactId);
		 contactRowObj.clickFromId=linkspanId;
		 contactRowObj.inputIds=inputIds;
		 functionToCallAfterSelect(contactRowObj);
			
		

	}
	function addAndSaveToMainPage(){
		var validFlag=validatePopup();
		if (!validFlag)
		{		
			document.getElementById('errorMsgPopup').style.display = "block";
			jQuery(document).scrollTop(0);
         //alert("block error");
		}else{
				var contactRowObj={
						clickFromId:"",
						inputIds:"",
						keys:["lastName","firstName","workPhone","alternatePhone","emailAddress"],
						inputIds:["lastNamePopup","firstNamePopup","workPhonePopup","alternatePhonePopup","emailPopup"],
						setValues:function(rowId){
							for(i=0;i<this.keys.length;i++){
									this[this.keys[i]]=jQuery('#'+this.inputIds[i]).val();
								}		
						}
						
								
					};
			 contactRowObj.setValues(contactId);
			 contactRowObj.clickFromId=linkspanId;
			 contactRowObj.inputIds=inputIds;
			 functionToCallAfterSelect(contactRowObj);
			}
	}
	


	
	//var validationFlagPopup;

	//Validate the fields for add a new contact
	function validatePopup() {
		
		//alert("in script validate");
		var validationFlagPopup = true;
		document.getElementById('errorMsgPopup').innerHTML = "";
		<%--jQuery('#msgBanner').hide();--%>
		//Set error message section as popup
		setPopup();
		//alert('firstname='+document.getElementById("firstname").value);
		//Show error as per above validations
		//alert(jQuery('#firstnamePopup').val());
		/*Changes*/
		if(jQuery('#firstNamePopup').val()==null || jQuery('#firstNamePopup').val()=='' ) {
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.firstname.empty"/></strong></li>');
			jQuery('#firstNamePopup').addClass('errorColor');
			validationFlagPopup = false;
		}else{
				if(!validateFormat(jQuery('#firstNamePopup').val(),'firstNamePopup','errorMsgPopup')){
					jQuery('#firstNamePopup').addClass('errorColor');
					validationFlagPopup = false;		
				}
			}

		
		if(jQuery('#lastNamePopup').val()==null || jQuery('#lastNamePopup').val()=='' ) {
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.lastname.empty"/></strong></li>');
			validationFlagPopup = false;
			jQuery('#lastNamePopup').addClass('errorColor');
		}else{
				if(!validateFormat(jQuery('#lastNamePopup').val(),'lastNamePopup','errorMsgPopup')){
					validationFlagPopup = false;
					jQuery('#lastNamePopup').addClass('errorColor');	
				}
			}

		
		if(jQuery('#workPhonePopup').val()==null || jQuery('#workPhonePopup').val()=='' ) {
			//alert();
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.workphone.empty"/></strong></li>');
			validationFlagPopup = false;
			jQuery('#workPhonePopup').addClass('errorColor');
		}else{
				if(!validateFormat(jQuery('#workPhonePopup').val(),'workPhonePopup','errorMsgPopup')){
					validationFlagPopup = false;
					jQuery('#workPhonePopup').addClass('errorColor');	
				}
			}
		
		
			if(jQuery('#alternatePhonePopup').val()!='' && (!validateFormat(jQuery('#alternatePhonePopup').val(),'alternatePhonePopup','errorMsgPopup'))){
				validationFlagPopup = false;	
				jQuery('#alternatePhonePopup').addClass('errorColor');
			}
		
		
		if(jQuery('#emailPopup').val()==null || jQuery('#emailPopup').val()=='' ) {
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.emailaddress.empty"/></strong></li>');
			validationFlagPopup = false;
			jQuery('#emailPopup').addClass('errorColor');
			
		}else{
				if(!validateFormat(jQuery('#emailPopup').val(),'emailPopup','errorMsgPopup')) {
					validationFlagPopup = false;
					jQuery('#emailPopup').addClass('errorColor');
				}	
			}
		/*Changes ends*/
		return validationFlagPopup;
		//alert('validationFlagPopup='+validationFlagPopup);
		
	}

	
	//Added for Bookmark Contact
	var favStatus = {};
	<%--Changes MPs 2.1  --%>
	function setContactFavourite(favContactId) {
		 callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPBOOKMARKCONTACT%>');
		//alert("contactid "+'${contactid}'+" favContactId "+favContactId);
	    var url = '<portlet:resourceURL id="setContactFavouriteFlag"/>';
	    url += "&contactId=${contactid}";
	    url += "&favoriteContactId=" + favContactId;
	    //url += "&isContactPopUp=true";
	    //alert("url="+url+"doSelectUnfavoriteFlag"+doSelectUnfavoriteFlag);
	    
	    // if user selects an unfavorite contact
	    <%-- Changes MPs 2.1
	    if (doSelectUnfavoriteFlag) {
	    	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPUNBOOKMARKCONTACT%>');
	        url += "&flagStatus=" + "false";
	        doAjaxPopup(url, null, null, "");
	        return;
	    }--%>
	    // if the favorite flag is being updated
	    if (favStatus[favContactId]&&favStatus[favContactId].isLoading) {
	        return;
	    }
	    
	    var starImg = window.document.getElementById("starImg_primary_"+favContactId);
	    var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
	    favStatus[favContactId] = {isFavorite:isFavorite,isLoading:true};
	    starImg.src = "<html:imagesPath/>loading-icon.gif";
	    url += "&flagStatus=" + isFavorite;
	    //alert("before doajaxPopup");
	    <%--Added for MPS 2.1 --%>
	    doAjaxPopup(url, callbackGetPrimaryContactResult, callbackGetPrimaryContactFailure, "Popup_contact");
	    <%--ends Added for MPS 2.1 --%>
	    /*var bookmarkStatus = window.parent.window.bookmarkStatus();
	    //alert(bookmarkStatus);
	    if(bookmarkStatus){
		   // alert("hi");
	    	jQuery("#msgBanner").show();
	    }*/
	}

	// call back, when successfully update favorite status of primary contact
	function callbackGetPrimaryContactResult(result) {
	    var contactId = result.data;
	    <%-- Changes MPs 2.1--%>
	    if (document.getElementById("contactGridbox")!=null && document.getElementById("contactGridbox").style.display!="none") {
	    <%-- Ends Changes MPs 2.1--%>    
	        var starImg = document.getElementById('starImg_primary_' + contactId);
	        //just change the star image
	        //alert("before setting star image");
	        if (favStatus[contactId].isFavorite) {
	            starImg.src = "<html:imagesPath/>unfavorite.png";
	    		<%--  Changes for Defect 8028 --%>
	            starImg.title="<spring:message code="requestInfo.tooltip.contact.Bookmark"/>";
	    		<%-- End Changes for Defect 8028 --%>
	        } else {
	            starImg.src = "<html:imagesPath/>favorite.png";
	    		<%-- Changes for Defect 8028 --%>
	            starImg.title="<spring:message code="requestInfo.tooltip.contact.UnBookmark"/>";
	    		<%-- End Changes for Defect 8028 --%>
	        }
	    }
	    if (favStatus[contactId].isFavorite) {
	        favStatus[contactId].isFavorite = false;
	    } else {
	        favStatus[contactId].isFavorite = true;
	    }
	    favStatus[contactId].isLoading = false;	    
	}

	// call back, when fail to update favorite status of primary contact
	function callbackGetPrimaryContactFailure(result) {
	    var contactId = result.data;
	    <%-- Changes MPs 2.1--%>
	    if (document.getElementById("contactGridbox")!=null && document.getElementById("contactGridbox").style.display!="none") {
	    <%-- Ends Changes MPs 2.1--%>
	        var starImg = document.getElementById('starImg_primary_' + contactId);
	        //just change the star image
	        if (favStatus[contactId].isFavorite) {
	            starImg.src = "<html:imagesPath/>favorite.png";
	          
	        } else {
	            starImg.src = "<html:imagesPath/>unfavorite.png";
	          
	        }
	    }
	    favStatus[contactId].isLoading = false;
	}

	//Show all account contact list
	function showAllAccountContact(){
		 callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPALLACCOUNTCONTACTS%>');
		jQuery('#allContacts').html('<spring:message code="requestInfo.link.allAccountContacts"/>');
		jQuery('#myBookmarkContacts').html('<img src="<html:imagesPath/>favorite.png" width="15" height="15"><a href="javascript:showBookmarkedContact();"><spring:message code="requestInfo.link.myBookmarkContacts"/></a>');
		bookmarkSortFlag = false;
		contactGrid.clearAndLoad(customizedGridURL);
	}

	//Show only bookmarked contact list
	function showBookmarkedContact(){
		 callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkPPOmnitureConstants.CONTACTPOPUPMYBOOKMARKCONTACTS%>');
		//disable my bookmarked contacts
		jQuery('#myBookmarkContacts').html('<img src="<html:imagesPath/>favorite.png" width="15" height="15"><spring:message code="requestInfo.link.myBookmarkContacts"/>');
		//change All Account Contact to Link
		jQuery('#allContacts').html('<a href="javascript:showAllAccountContact();"><spring:message code="requestInfo.link.allAccountContacts"/></a>');
		bookmarkSortFlag = true;
		contactGrid.clearAndLoad(customizedGridURL+"&fav='true'");//+'&searchCriterias='+'${contactid}');
	}
	<%-- Changes for defect 8064 MPS 2.1--%>
	function bindBookmarkEvents(){
		jQuery('.helpIconList').click(function(){
			if(jQuery(this).attr('name')=="bookmark")
				setContactFavourite(jQuery(this).attr('id').split("_")[2]);
			});
	}
	<%-- Ends Changes for defect 8064 MPS 2.1--%>
	<%--
	This is commented out as it is not required 
	for tab validation
	function validateContactPopup() {
		//alert('validateContactPopup');
		jQuery('input[type="text"]').blur(function(){
			var focusFlag = this.id;
			if(jQuery.trim(jQuery(this).val()).length >0) {
				invalidValuePopup=validateFormat(jQuery.trim(jQuery(this).val()),this.id);
				if(invalidValuePopup!=true) {
					jQuery("#errorMsgPopup").html(invalidValuePopup);
					jQuery("#errorMsgPopup").show();

					window.setTimeout(function(){
						document.getElementById(focusFlag).focus();
						}, 2);

					window.setTimeout(function(){
						jQuery(document).scrollTop(0);
						}, 3);
				}
				else {
					jQuery("#errorMsgPopup").html('');
					jQuery("#errorMsgPopup").hide();
				}
			}
		});
	}--%>
</script>	