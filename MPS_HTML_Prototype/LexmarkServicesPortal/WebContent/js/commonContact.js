
	var flag;//this flag is used to check whether user has clicked on edit or not.
	//ends
	var isConsContPop = window.parent.window.isConsContPop;//This is referred from the parent changeAsset.jsp
	var siteContactPopUp=window.parent.window.siteContactPopUp;//This is referred from the parent deleteAsset.jsp 
	var bookmarkSortFlag = false; //Added for differentiate between bookmark and all contacts
	
		function hideNewContact() {
			//alert("Hide");
			document.getElementById('createButtonContact').style.display = "block";
			document.getElementById('update').style.display = "none";
		}
			
		function showNewContact() {
			document.getElementById('createButtonContact').style.display = "none";
			document.getElementById('update').style.display = "block";
		}
		
		var searchCriteria="";

		function initializeContactGrid() {		
		contactGrid = new dhtmlXGridObject('contactGridbox');
		alert("111");
		contactGrid.setImagePath("<html:imagesPath/>/gridImgs/");
		//alert("222");
		contactGrid.setHeader("<spring:message code='serviceRequest.listHeader.contactList'/>");
		contactGrid.setInitWidths("30,100,100,100,100,120,90,90");
		contactGrid.attachHeader("&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,&nbsp;,&nbsp;");
		contactGrid.setColAlign("left,left,left,left,left,left,left,left");
		contactGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
		contactGrid.setColSorting("na,str,str,str,str,str,na,na");
		contactGrid.a_direction = "ASCENDING";
		contactGrid.init();
		contactGrid.prftInit();
		contactGrid.enablePaging(true, 5, 6, "pagingArea", true, "infoArea");
		contactGrid.setPagingSkin("bricks");
		contactGrid.enableAutoWidth(true);
		contactGrid.enableAutoHeight(true);
		contactGrid.enableMultiline(true);
		contactGrid.setSizes();	
		var url='<portlet:resourceURL id="primarycontactListPopulate"/>';
		url=url+"&orderBy=2"+"&isContactPopUp=true";
		//alert(url);			
		contactGrid.loadXML(url);
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
			//alert("222a");
			document.getElementById('loadingNotification').style.display = 'block';
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
		});
		
		contactGrid.attachEvent("onMouseOver", function(id,ind){		
			return false;
		});
		customizedGridURL = populateURLCriterias();;
		contactGrid.attachEvent("onFilterStart", function(indexes,values){
			//alert("onFilterStart");
	    	contactGrid.filterValues = ","+values;
	    	customizedGridURL = updateGridURL(customizedGridURL, contactGrid.getSortColByOffset(), contactGrid.a_direction, contactGrid.filterValues);
	    	//alert("customizedGridURL  "+customizedGridURL);
	    	setGridFilerTimer(reloadGrid);
	    });
		contactGrid.attachEvent("onBeforeSorting", customColumnSort);
		contactGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
	            if(cInd !=0){
	            
	                        if(stage==0){
	                              var cellObj = contactGrid.cellById(rId,cInd);
	                              var cellvalue=cellObj.getValue();
	                              //alert(cellvalue);
	                              var dataArr=cellvalue.split("\"");
	                              
	                              if(dataArr.length == 1)
	                                    cellObj.setValue(cellvalue);
	                              else{
	                                    var j=0;
	                                          for(j=0;j<dataArr.length;j++){
	                                          if(dataArr[j].indexOf('value=')!= -1){
	                                                //alert(dataArr[j+1]);
	                                                cellObj.setValue(dataArr[j+1]);
	                                                break;
	                                          }
	                                    }
	                                    
	                              }

	                  
	                        }else if(stage==1){
	                              
	                  }else if(stage==2){
											var cellObj = contactGrid.cellById(rId,cInd);
		                                    var cellvalue=cellObj.getValue();
		                                    //alert("before closing Editable="+cellvalue);
		                                   cellObj.cell.innerHTML="<input type=text value=\""+cellvalue+" "+"\">";
	                        //deviceListGrid.setCellExcellType(rId,cInd,"ro");
	                        
	                  }
	                  return true;

	            
	            }
	            
	            });
	
			}

		function changeToEdit(rId,element){
									//alert("in changeToEdit");
			                        var buttonName=jQuery(element).val();
									alert("buttonName edit/save "+buttonName);
			                        var cInd=2;
									document.getElementById('contactId').value = rId;
									var selectObj = contactGrid.cellById(rId,7);
			                        if (buttonName=='Edit'){
			                              flag=true;
			                              //alert('rowid='+rId);
			                              for(i=1;i<=3;i++){
			                                    var cellObj = contactGrid.cellById(rId,cInd+i);
			                                    var cellvalue=cellObj.getValue();
			                                    //alert(cellvalue);
			                                    contactGrid.setCellExcellType(rId,cInd+i,"ed");
			                                    cellObj.cell.innerHTML="<input type=text value=\""+cellvalue+" "+"\">";
			                                    
			                              }
			                              	//jQuery(element).attr('disabled','disabled');
											//cellObj = contactGrid.cellById(rId,6);
											//cellObj.cell.innerHTML="<input name=\"btn_select\" value=\"Save\" class=\"table_button\" type=\"button\" onclick=\"saveUpdatedContact(rId);\">";
											//cellObj = contactGrid.cellById(rId,7);
											//cellObj.cell.attr('disabled','disabled');
											jQuery(element).val('Save');
											//selectObj.setValue('');
											//selectObj.cell.innerHTML="<input name=\"btn_select\" value=\"\" class=\"table_button\" type=\"button\">";
			                        }
									else if (buttonName=='Save'){
										alert("save button is "+buttonName);
										saveUpdatedContact(rId,element);
										//selectObj.setValue('SELECT');
										//selectObj.cell.innerHTML="<input name=\"btn_select\" value=\"SELECT\" class=\"table_button\" type=\"button\">";
									}
			                       // else{
			                             
			                    //    }	                        
			                  }
		function reloadGrid() {	 
			//alert("reloadGrid for filter");
			clearMessage();			
			contactGrid.clearAndLoad(customizedGridURL);	
			alert("bookmarkSortFlag="+bookmarkSortFlag);
			if (!bookmarkSortFlag) {
				contactGrid.clearAndLoad(customizedGridURL);
			}
			else {
				contactGrid.clearAndLoad(customizedGridURL+"&fav='true'");
			}
		}
		/*
		function populateURLCriterias(){
			
			var url='<portlet:resourceURL id="primarycontactListPopulate"/>';
			//contactGridDisplayUrl=contactGridDisplayUrl+"&orderBy=2";
			if(searchCriteria != null && searchCriteria != "") {
				url = url + "&searchCriterias=" + searchCriteria;
			}
			if(contactGrid.filterValues != null && contactGrid.filterValues != "") {
				url = url + "&filterCriterias=" + contactGrid.filterValues;
			}
	        if(contactGrid.a_direction != null && contactGrid.a_direction != "") {
	            url = url + "&direction=" + contactGrid.a_direction;
	        }
	        url = url + "&orderBy=" + contactGrid.getSortColByOffset();
	        return url;
	    }*/
		function populateURLCriterias(){		

			var url='<portlet:resourceURL id="primarycontactListPopulate"/>';
			url=url+"&orderBy=2";
			url=url+"&isContactPopUp=true";
			//alert(url);    
		    return url;
		};
		
		function loadMyFav() {		
			searchCriteria= "${chlOthersForm.serviceRequest.requestor.contactId}";
			
			url='<portlet:resourceURL id="primarycontactListPopulate"/>';
			if(searchCriteria != null && searchCriteria != "") {
				url = url + "&searchCriterias=" + searchCriteria;
				searchCriteria="";
			}			
			contactGrid.clearAndLoad(url);
		}
		
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
		
		//Added on 9 April for inline edit - save button
		function saveUpdatedContact(rId,element) {
			alert("in saveUpdatedContact rId "+rId);
			var cInd=0;
			var i=1;
			 //var contactCelValue = "";
		    for(i=1;i<=5;i++){
		       var cellObj = contactGrid.cellById(rId,cInd+i);
		       var cellvalue=cellObj.getValue();
		       contactGrid.setCellExcellType(rId,cInd+i,"ro");
		       var dataArr=cellvalue.split("\"");
		       //alert(dataArr);               
		       if(dataArr.length == 1){
					//alert("dataarr len 1");
		           cellObj.cell.innerHTML=cellvalue;
		           document.getElementById("contactData"+i).value = cellvalue;
				   //contactCelValue = cellvalue;
			   }else{
				   alert("dataarr len "+dataArr.length);
				  var j=0;
				  for(j=0;j<dataArr.length;j++){
				    if(dataArr[j].indexOf('value=')!= -1){
				         //alert("dataArr[j+1]"+dataArr[j+1]);
				         cellObj.cell.innerHTML=dataArr[j+1];
					     //contactCelValue = dataArr[j+1];
						 document.getElementById("contactData"+i).value = dataArr[j+1];
				         break;
				    }
				  }
				}
			}
		
		var validationFlag = true;
		
		//Set error message section as popup
		setPopup();
		
		//Validate Work Phone
		if(!phoneValidate(document.getElementById("contactData3").value,"workphone","changeContact")){
			validationFlag = false;
		}

		//Validate Alternate Phone
		if(!phoneValidate(document.getElementById("contactData4").value,"alternatephone","changeContact")){
			validationFlag = false;
		}

		//Validate Email Address
		if(!emailValidate(document.getElementById("contactData5").value,"emailAddr","changeContact")){
			validationFlag = false;
		}
		
		if (!validationFlag) {
			document.getElementById('errorMsgPopup').style.display = "block";
			alert("block error");
		}
		else {
			document.getElementById('errorMsgPopup').style.display = "none";
		}
		
		jQuery(element).val('Edit');
		}
		
		function updateContact(){

			var ci = document.getElementById("contactId").value;
			var ln = document.getElementById("contactData1").value;
			var fn = document.getElementById("contactData2").value;
			var wp = document.getElementById("contactData3").value;
			var ap = document.getElementById("contactData4").value;
			var ea = document.getElementById("contactData5").value;
						
			if(isConsContPop != null){
				if(isConsContPop=="consumablesContact")
				{
						window.parent.window.addConsumablesContactElement(ci, ln, fn, wp, ea);
				}
			}
			else
			window.parent.window.addContactElement(ci, ln, fn, wp, ea);
		}
		
		function selectContact(ci,ln,fn,wp,ea,fav,rId,element){
			var buttonName=jQuery(element).val();
			alert("select button "+buttonName);
//			if (buttonName=='SELECT') {
			alert("select button clicked");
			
			if(flag==true){				 
				//alert('before update contact');
				updateContact();
			}
			else {			
				//alert("in selectContact else");
				
				//alert("Value is " +isConsContPop);
				if(isConsContPop != null){
					if(isConsContPop=="consumablesContact")
					{
							window.parent.window.addConsumablesContactElement('-1', ln, fn, wp, ea);
							isConsContPop=null;
					}
				}
				else if(siteContactPopUp!=null)
				{
					if(siteContactPopUp=="siteContactPrimary")
					{
							window.parent.window.addSiteContactElement('-1', ln, fn, wp, ea);
					}
				}
				else
				window.parent.window.addContactElement('-1', ln, fn, wp, ea);
			}
			hideNewContact();
			closeDialog();
			//}
//			}
		}
		
		var validationFlag;
		
		function validatePopup() {
			
			alert("in script validate");
			validationFlag = true;
			/*
			//Set error message section as popup
			setPopup();
			
			//Validate First Name
			if(!nameValidate(document.getElementById("firstName").value,"firstname","addContact")){
				validationFlag = false;
			}
			//Validate Last Name
			if(!nameValidate(document.getElementById("lastName").value,"lastname","addContact")){
				validationFlag = false;
			}

			//Validate Work Phone
			if(!phoneValidate(document.getElementById("workPhone").value,"workphone","addContact")){
				validationFlag = false;
			}

			//Validate Alternate Phone
			if(!phoneValidate(document.getElementById("alternatePhone").value,"alternatephone","addContact")){
				validationFlag = false;
			}

			//Validate Email Address
			if(!emailValidate(document.getElementById("email").value,"emailAddr","addContact")){
				validationFlag = false;
			}
			//Show error as per above validations
			alert("validationFlag "+validationFlag);
			*/
			if (validationFlag)
			{		
				//addContact();
			}
			else {
				document.getElementById('errorMsgPopup').style.display = "block";
				alert("block error");
			}
		}
		
		//Added for Bookmark Contact
		var favStatus = {};
		function setContactFavourite(favContactId, doSelectUnfavoriteFlag) {
			alert("contactid "+'${contactid}'+" favContactId "+favContactId);
		    var url = '<portlet:resourceURL id="setContactFavouriteFlag"/>';
		    url += "&contactId=${contactid}";
		    url += "&favoriteContactId=" + favContactId;
		    //url += "&isContactPopUp=true";
		    alert("url="+url+"doSelectUnfavoriteFlag"+doSelectUnfavoriteFlag);
		    
		    // if user selects an unfavorite contact
		    if (doSelectUnfavoriteFlag) {
			    alert();
		        url += "&flagStatus=" + "false";
		        doAjax(url, null, null, "");
		        return;
		    }
		    // if the favorite flag is being updated
		    if (favStatus[favContactId]&&favStatus[favContactId].isLoading) {
		        return;
		    }
		    
		    var starImg = window.document.getElementById("starImg_primary_"+favContactId);
		    var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
		    favStatus[favContactId] = {isFavorite:isFavorite,isLoading:true};
		    starImg.src = "<html:imagesPath/>loading-icon.gif";
		    url += "&flagStatus=" + isFavorite;
		    alert("before doajax");
		    doAjax(url, callbackGetPrimaryContactResult, callbackGetPrimaryContactFailure, "");
		}

		// call back, when successfully update favorite status of primary contact
		function callbackGetPrimaryContactResult(result) {
		    var contactId = result.data;
		    alert("in callbackGetPrimaryContactResult result= "+result+" contactId= "+contactId);
		    if (document.getElementById("contactGrid_container").style.display!="none") {
		        var starImg = document.getElementById('starImg_primary_' + contactId);
		        //just change the star image
		        if (favStatus[contactId].isFavorite) {
		            starImg.src = "<html:imagesPath/>unfavorite.png";
		        } else {
		            starImg.src = "<html:imagesPath/>favorite.png";
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
		    if (document.getElementById("contactGrid_container").style.display!="none") {
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

		function showAllAccountContact(){
			jQuery('#allContacts').html('All Account Contacts');
			jQuery('#myBookmarkContacts').html('<a href="javascript:showBookmarkedContact();">My Bookmarked Contacts</a>');
			bookmarkSortFlag = false;
			alert("bookmarkSortFlag="+bookmarkSortFlag);
			contactGrid.clearAndLoad(customizedGridURL);
		}
		function showBookmarkedContact(){
			alert("in showBookmarkedContact");
			//disable my bookmarked addresses
			jQuery('#myBookmarkContacts').html('My Bookmarked Contacts');
			//change All Account Address to Link
			jQuery('#allContacts').html('<a href="javascript:showAllAccountContact();">All Account Contacts</a>');
			bookmarkSortFlag = true;
			alert("bookmarkSortFlag="+bookmarkSortFlag);
			//alert("in popup showBookmarkedContact");
			//alert('contact id is '+'${contactid}');
			//contactGrid.clearAndLoad(customizedGridURL);
			contactGrid.clearAndLoad(customizedGridURL+"&fav='true'");//+'&searchCriterias='+'${contactid}');
		}
		