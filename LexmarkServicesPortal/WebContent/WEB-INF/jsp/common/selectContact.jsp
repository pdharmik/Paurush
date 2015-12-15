<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>



<div class="portletBlock" id="contentContact">
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
            <li id="myBookmarkContacts"><img class="ui_icon_sprite bookmark-star-icon" src="<html:imagesPath/>transparent.png" >
            	<a href="javascript:showBookmarkedContact();">
            	<spring:message code="requestInfo.link.myBookmarkContacts"/>
            	</a>
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
	      <!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="/LexmarkServicesPortal/images/gridloading.gif"/>
	    </div>
      </div>
      
      <!-- Pagination section -->
	  <div class="pagination" id="paginationId">
		<span id="pagingArea"></span><span id="infoArea"></span>
	  </div>
      
      
	  
	  <!-- Button container -->
	  <div class="buttonContainer" id="createButtonContact">
		<button class="button_cancel" id="cancelNewContactPopup" onclick="javascript:closeDialog();callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPCANCELCONTACTPOPUPBUTTON%>');" type="button"><spring:message code="button.cancel"/></button>
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
					<span><input id="workPhonePopup" name="workPhonePopup" size="30" type="text" maxlength="40" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
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
				onclick="javascript:closeDialog();  callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPCANCELCONTACTPOPUPBUTTON%>');" type="button"><spring:message code="button.cancel"/></button>
			<button class="button" onclick="javascript:validatePopup();addContact();" type="button"><spring:message code="button.saveNewContact"/></button>
		</div>
	  </div>
				
    </div>
  </div>
</div>

	
<script type="text/javascript">
var newContactAddFlag=false;
var flag;//this flag is used to check whether user has clicked on edit or not.
var editornotClosedflag=false;
//ends
var isConsContPop = window.parent.window.isConsContPop;//This is referred from the parent changeAsset.jsp
var siteContactPopUp=window.parent.window.siteContactPopUp;//This is referred from the parent deleteAsset.jsp 
//Added for MPS 2.1 Wave4
var deviceContactPopUp=window.parent.window.deviceContactPopUp;//This is referred from the parent deleteAsset.jsp 

var bookmarkSortFlag = false; //Added for differentiate between bookmark and all contacts
var validationFlagPopup;//For validation

	//Hide the new contact section
	function hideNewContact() {
		//alert("Hide");
		document.getElementById('createButtonContact').style.display = "block";
		document.getElementById('update1').style.display = "none";
		jQuery('#content').data('height.dialog','550px');
	}

	//Show the new contact section
	function showNewContact() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPCREATENEWCONTACTBUTTON%>');
		//alert('showNewContact');
		document.getElementById('createButtonContact').style.display = "none";
		document.getElementById('update1').style.display = "block";
		//jQuery('#update').show();
		//alert('showNewContact end'+document.getElementById('update1').style.display);
		jQuery('#content').data('height.dialog','720px');
	}
	
	var searchCriteria="";
	var contactGrid;
	function initializeContactGrid() {	
	contactGrid = new dhtmlXGridObject('contactGridbox');
	//alert("111");
	contactGrid.setImagePath("<html:imagesPath/>gridImgs/");
	//alert("222");
	contactGrid.setHeader("<spring:message code='requestInfo.listHeader.contactListPopup'/>");
	<%-- Changed for defect 7975 MPS 2.1 --%>
	contactGrid.setInitWidths("80,*,30,100,100,100,100,120");
	contactGrid.attachHeader(",,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	contactGrid.setColAlign("center,center,left,left,left,left,left,left");
	contactGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
	contactGrid.setColSorting("na,na,na,str,str,str,na,str");
	<%--Ends Changed for defect 7975 MPS 2.1 --%>
	contactGrid.a_direction = "ASCENDING";
	contactGrid.init();
	contactGrid.prftInit();
	contactGrid.enablePaging(true, 5, 6, "pagingArea", true, "infoArea");
	contactGrid.setPagingSkin("bricks");
	contactGrid.enableAutoWidth(true);
	contactGrid.enableAutoHeight(true);
	contactGrid.enableMultiline(true);
	contactGrid.setSizes();	
	customizedGridURL = populateURLCriterias();
	contactGrid.loadXML(customizedGridURL);
	//contactGrid.setColumnHidden(0,true); 
	contactGrid.setSkin("light");
	contactGrid.sortIndex = null;
	contactGrid.loadOrder(colsOrder);
	<%-- Changed for defect 7975 MPS 2.1 --%>
	contactGrid.loadPagingSorting(colsSorting,3);
	<%--Ends  Changed for defect 7975 MPS 2.1 --%>
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
			
			contactGrid.setSortImgState(true, contactGrid.getDefaltSortImagIndex(contactGrid.sortIndex,3), 'asc');
        }else{
        	contactGrid.setSortImgState(true, contactGrid.getDefaltSortImagIndex(contactGrid.sortIndex,3), 'des');
        }
		document.getElementById('loadingNotification').style.display = 'block';
	});
	contactGrid.attachEvent("onXLE", function() { 
		//alert("onXLE");
		//contactGrid.setSortImgState(true, 2, "asc");
		document.getElementById('loadingNotification').style.display = 'none';
		document.getElementById('paginationId').style.display = 'block';
		document.getElementById('errorMsgPopup').style.display = 'none';
		<%-- Changes for defect 8064 MPS 2.1--%>
		bindBookmarkEvents();
		<%--Ends Changes for defect 8064 MPS 2.1--%>
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
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
    	customizedGridURL = updateGridURL(customizedGridURL, contactGrid.getSortColByOffset()-2, contactGrid.a_direction, contactGrid.filterValues);
    	//alert("customizedGridURL  "+customizedGridURL);
    	setGridFilerTimer(reloadGrid);
    });

    //When the grid cell will be editable, this method will be called
	contactGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		if(cInd >2){
						if(stage==0){
										var cellObj = contactGrid.cellById(rId,cInd);
										<%-- Changed for defect 7975 MPS 2.1 --%>
	   								  cellObj.setValue(jQuery(cellObj.getValue()).val());
	   								<%-- Ends Changed for defect 7975 MPS 2.1 --%>

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
								   callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPEDITCONTACT%>');
								  		//alert('inline edit');
								  		jQuery(element).val('<spring:message code='button.save'/>');
								  		<%-- Changed for defect 7975 MPS 2.1 --%>
										for(i=5;i<=7;i++){
									  		var cellObj = contactGrid.cellById(rId,i);
											var cellvalue=cellObj.getValue();
											contactGrid.setCellExcellType(rId,i,"ed");
											cellObj.cell.innerHTML="<input type=text value=\""+cellvalue+" "+"\" onmouseover=\"this.click();\">";
										}
										<%--Ends Changed for defect 7975 MPS 2.1 --%>
										jQuery('#btn_select_'+rId).attr('disabled',true);
										jQuery('#btn_select_'+rId).attr('style','opacity:.5;background:#81BEF7!important;');//Added background to show disable buttons - defect 8067
																				
							}
							  else{
								  contactGrid.editStop();
								  if (inlineValidate(rId)) {
								  flag=true;
								  saveUpdatedContact(rId);
								  callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPSAVENEWCONTACTBUTTON%>');
								  jQuery(element).val('<spring:message code='button.edit'/>');
								  jQuery('#btn_select_'+rId).attr('disabled',false);
								  jQuery('#btn_select_'+rId).attr('style','opacity:1');

								  //Save Updated contact
								  
								  jQuery('#contactId').val(rId);
								  <%-- Changed for defect 7975 MPS 2.1 --%>
								  for(i=3;i<=7;i++){
									  var cellObj = contactGrid.cellById(rId,i);
									  var cellvalue=cellObj.getValue();
									  jQuery('#contactData'+(i-2)).val(cellvalue);
									  
									
								  }
								  <%--Ends Changed for defect 7975 MPS 2.1 --%>
								  }
							  }
	}
	function saveUpdatedContact(rId) {
		contactGrid.editStop();
		<%-- Changed for defect 7975 MPS 2.1 --%>
		 for(i=5;i<=7;i++){
			var cellObj = contactGrid.cellById(rId,i);
			var cellvalue=cellObj.getValue();
			contactGrid.setCellExcellType(rId,i,"ro");
			cellObj.cell.innerHTML=jQuery(cellvalue).val();
			
			}
		 <%-- Ends Changed for defect 7975 MPS 2.1 --%>
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
		<%-- Changed for defect 7975 MPS 2.1 --%>
		url=url+"&orderBy=1";
		url=url+"&isContactPopUp=true";
		contactGrid.setSortImgState(true, 3, "asc");
		<%--Ends Changed for defect 7975 MPS 2.1 --%>
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
		customizedGridURL = updateGridURL(customizedGridURL, contactGrid.getSortColByOffset()-2, contactGrid.a_direction, contactGrid.filterValues);
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
		
		<%-- Changed for defect 7975 MPS 2.1 --%>
		//Validate Work Phone
		var cellObj = contactGrid.cellById(rId,5);
		var cellvalue=jQuery(cellObj.getValue()).val();
		<%--Ends Changed for defect 7975 MPS 2.1 --%>
		//alert('wp '+cellvalue);
		if(!phoneValidate(cellvalue,"workphone","changeContact")){
			validationFlagPopup = false;
		}
		<%-- Changed for defect 7975 MPS 2.1 --%>
		//Validate Alternate Phone
		var cellObj = contactGrid.cellById(rId,6);
		cellvalue=jQuery(cellObj.getValue()).val();
		<%--Ends Changed for defect 7975 MPS 2.1 --%>
		//alert('ap '+cellvalue);
		if(!phoneValidate(cellvalue,"alternatephone","changeContact")){
			validationFlagPopup = false;
		}
		<%-- Changed for defect 7975 MPS 2.1 --%>
		//Validate Email Address
		var cellObj = contactGrid.cellById(rId,7);
		cellvalue=jQuery(cellObj.getValue()).val();
		<%--Ends Changed for defect 7975 MPS 2.1 --%>
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
	function updateContact(addressId,addressName,storefrontName,ad1,ad2,city,state,province,country,pocode,
			officeNumber,district,county,countryISO,region,stateCode,phyLoc1,phyLoc2,phyLoc3,midName,altPhn){
		
		var ci = document.getElementById("contactId").value;
		var ln = document.getElementById("contactData1").value;
		var fn = document.getElementById("contactData2").value;
		var wp = document.getElementById("contactData3").value;
		var ap = document.getElementById("contactData4").value;
		var ea = document.getElementById("contactData5").value;
		
		
		
		if(isConsContPop != null){
			if(isConsContPop=="consumablesContact")
			{
					window.parent.window.addConsumablesContactElement('-1', ln, fn, wp, ea, ad1,ad2,city,state,
							province,country,pocode,phyLoc1,phyLoc2,phyLoc3,midName,ap);
			}
		}
		
		else if(siteContactPopUp!=null)
		{
			
			if(siteContactPopUp=="siteContactPrimary")
			{
				
				window.parent.window.addSiteContactElement('-1', ln, fn, wp, ea);
			}
		}

		else if(deviceContactPopUp!=null && deviceContactPopUp!="false")
		{
				window.parent.window.addDeviceContactElement('-1', ln, fn, wp, ea, altPhn, deviceContactPopUp,
						addressId,addressName,storefrontName,ad1,ad2,city,state,province,country,pocode,
						officeNumber,district,county,countryISO,region,stateCode,phyLoc1,phyLoc2,phyLoc3);
				deviceContactPopUp="false";
		
		}		
		else{
		window.parent.window.addContactElement(ci, ln, fn, wp, ap, ea, true, false);
		}
	}

	//If user selects a row from grid, this method will be called
	function selectContact(ci,ln,fn,wp,ea,fav,addressId,addressName,storefrontName,ad1,ad2,city,state,province,country,pocode,
			officeNumber,district,county,countryISO,region,stateCode,phyLoc1,phyLoc2,phyLoc3,midName,altPhn,rId,element){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPSELECTCONTACT%>');
		var duplicateContact = false;
		if(flag==true && ci == jQuery('#contactId').val()){		
			
			//alert('before update contact');
			updateContact(addressId,addressName,storefrontName,ad1,ad2,city,state,province,country,pocode,
					officeNumber,district,county,countryISO,region,stateCode,phyLoc1,phyLoc2,phyLoc3,midName,altPhn);
			flag=false;
		}
		else {			
			//alert("in selectContact else");
			//alert("isConsContPop="+isConsContPop+" siteContactPopUp="+siteContactPopUp+"deviceContactPopUp="+deviceContactPopUp);
			
			
			if(isConsContPop != null){
				//alert('inside isConsContPop');
				if(isConsContPop=="consumablesContact")
				{
						//window.parent.window.addConsumablesContactElement('-1', ln, fn, wp, ea);
					window.parent.window.addConsumablesContactElement('-1', ln, fn, wp, ea,ad1,ad2,
							city,state,province,country,pocode,phyLoc1,phyLoc2,phyLoc3,midName,altPhn);
						isConsContPop=null;
				}
			}
			else if(siteContactPopUp!=null)
			{
				//alert('inside siteContactPopUp');
				if(siteContactPopUp=="siteContactPrimary")
				{
						
						window.parent.window.addSiteContactElement('-1', ln, fn, wp, ea, altPhn);
						siteContactPopUp=null;
				}
			}
			else if(deviceContactPopUp!=null && deviceContactPopUp!="false")
			{			
			 	newContactAddFlag=false;
					//alert('deviceContactPopUp='+deviceContactPopUp);
					window.parent.window.addDeviceContactElement(ci, ln, fn, wp, ea, altPhn, deviceContactPopUp,
							addressId,addressName,storefrontName,ad1,ad2,city,state,province,country,pocode,
							officeNumber,district,county,countryISO,region,stateCode,phyLoc1,phyLoc2,phyLoc3);
					deviceContactPopUp="false";
			}
			else {
				//alert('common popup');
				if ((fn == document.getElementById("primaryFirstName").value && ln == document.getElementById("primaryLastName").value)
					|| (fn == document.getElementById("secondaryFirstName").value && ln == document.getElementById("secondaryLastName").value)) {
					jQuery('#errorMsgPopup').html('<li><strong><spring:message code="validation.contact.popup.duplicateData"/></strong></li>');
					document.getElementById('errorMsgPopup').style.display = "block";
					jQuery(document).scrollTop(0);
					duplicateContact = true;
				}
				else {
					//alert('before addContactElement in common');
					window.parent.window.addContactElement(ci, ln, fn, wp, altPhn, ea, false, false);
				}
			}
		}
		if (!duplicateContact) {
			hideNewContact();
			closeDialog();
		}
		//}
//		}
	}
	
	//var validationFlagPopup;

	//Validate the fields for add a new contact
	function validatePopup() {
		
		//alert("in script validate");
		validationFlagPopup = true;
		document.getElementById('errorMsgPopup').innerHTML = "";
		<%--jQuery('#msgBanner').hide();--%>
		//Set error message section as popup
		setPopup();
		
		
		if(jQuery('#firstNamePopup').val()==null || jQuery.trim(jQuery('#firstNamePopup').val())=='' ) {
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.firstname.empty"/></strong></li>');
			jQuery('#firstNamePopup').addClass('errorColor');
			validationFlagPopup = false;
		}else{
				if(!validateFormat(jQuery('#firstNamePopup').val(),'firstNamePopup','errorMsgPopup')){
					jQuery('#firstNamePopup').addClass('errorColor');
					validationFlagPopup = false;		
				}
			}
		if(jQuery('#lastNamePopup').val()==null || jQuery.trim(jQuery('#lastNamePopup').val())=='' ) {
		
		
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.lastname.empty"/></strong></li>');
			validationFlagPopup = false;
			jQuery('#lastNamePopup').addClass('errorColor');
		}else{
				if(!validateFormat(jQuery('#lastNamePopup').val(),'lastNamePopup','errorMsgPopup')){
					validationFlagPopup = false;
					jQuery('#lastNamePopup').addClass('errorColor');	
				}
			}
		/*var firstNameFlag=false;
		var lastNameFlag=false;
		
		if(jQuery('#firstNamePopup').val()!=null && jQuery('#firstNamePopup').val()!='' ) {
			for(var i=0;i<=9;i++){
				if(!(jQuery('#firstNamePopup').val().indexOf(i)==-1)){
					firstNameFlag=true;
				}
			}
		}
			if(jQuery('#lastNamePopup').val()!=null && jQuery('#lastNamePopup').val()!='' ) {
				for(var i=0;i<=9;i++){
					if(!(jQuery('#lastNamePopup').val().indexOf(i)==-1)){
						lastNameFlag=true;
					}
				}
			}
		if(firstNameFlag){
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.firstname.format.errorMsg"/></strong></li>');
			validationFlagPopup = false;
			jQuery('#firstNamePopup').addClass('errorColor');
			}
		if(lastNameFlag){
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.contact.lastname.format.errorMsg"/></strong></li>');
			validationFlagPopup = false;
			jQuery('#lastNamePopup').addClass('errorColor');
			}*/
		
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
		//alert('validationFlagPopup='+validationFlagPopup);
		if (!validationFlagPopup)
		{		
			document.getElementById('errorMsgPopup').style.display = "block";
			jQuery(document).scrollTop(0);
			//alert("block error");
		}
	}
	
	//Added for Bookmark Contact
	var favStatus = {};
	<%--Changes MPs 2.1  --%>
	function setContactFavourite(favContactId) {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPBOOKMARKCONTACT%>');
		//alert("contactid "+'${contactid}'+" favContactId "+favContactId);
	    var url = '<portlet:resourceURL id="setContactFavouriteFlag"/>';
	    url += "&contactId=${contactid}";
	    url += "&favoriteContactId=" + favContactId;
	    //url += "&isContactPopUp=true";
	    //alert("url="+url+"doSelectUnfavoriteFlag"+doSelectUnfavoriteFlag);
	    
	    // if user selects an unfavorite contact
	   
	    // if the favorite flag is being updated
	    if (favStatus[favContactId]&&favStatus[favContactId].isLoading) {
	        return;
	    }
	    
	    var starImg = window.document.getElementById("starImg_primary_"+favContactId);
	   // var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
	    var isFavorite = (jQuery("#starImg_primary_"+favContactId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
	   
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
	        	jQuery('#starImg_primary_'+contactId).removeClass('bookmark-star-icon');
				jQuery('#starImg_primary_'+contactId).addClass('removebookmark-icon');
	    		<%--  Changes for Defect 8028 --%>
	            starImg.title="<spring:message code="requestInfo.tooltip.contact.Bookmark"/>";
	            starImg.src = "<html:imagesPath/>transparent.png";
	    		<%-- End Changes for Defect 8028 --%>
	        } else {
	        	jQuery('#starImg_primary_'+contactId).addClass('bookmark-star-icon');
				jQuery('#starImg_primary_'+contactId).removeClass('removebookmark-icon');
	    		<%-- Changes for Defect 8028 --%>
	            starImg.title="<spring:message code="requestInfo.tooltip.contact.UnBookmark"/>";
	    		<%-- End Changes for Defect 8028 --%>
	    		starImg.src = "<html:imagesPath/>transparent.png";
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
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPALLACCOUNTCONTACTS%>');
		jQuery('#allContacts').html('<spring:message code="requestInfo.link.allAccountContacts"/>');
		jQuery('#myBookmarkContacts').html('<img class="ui_icon_sprite bookmark-star-icon" src="<html:imagesPath/>transparent.png" ><a href="javascript:showBookmarkedContact();"><spring:message code="requestInfo.link.myBookmarkContacts"/></a>');
		bookmarkSortFlag = false;
		contactGrid.clearAndLoad(customizedGridURL);
	}

	//Show only bookmarked contact list
	function showBookmarkedContact(){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTACONTACTPOPUP%>','<%=LexmarkSPOmnitureConstants.CONTACTPOPUPMYBOOKMARKCONTACTS%>');
		//disable my bookmarked contacts
		jQuery('#myBookmarkContacts').html('<img class="ui_icon_sprite bookmark-star-icon" src="<html:imagesPath/>transparent.png" ><spring:message code="requestInfo.link.myBookmarkContacts"/>');
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
	
</script>	