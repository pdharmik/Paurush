		

		var mandatoryStatusComments=["ETA Provided","Deferred Appt","Not Accepted by Partner","Could Not Reach Cust","Tech Unavailable","Customer Damage","Cancelled - Lexmark","Cancelled - Customer","Cancelled - Partner","Dead on Arrival","Force Majeure","Delayed Due to Shipment","Delayed Due to Weather","Delayed Due to Customer"];
		var titleValue=jQuery('#statusValues').attr('title');
		jQuery('#statusValues').attr('title',titleValue+mandatoryStatusComments.toString());
		jQuery(':input').click(function(){
			jQuery(this).removeClass('errorColor');
		});
		
		jQuery('#topnavigation li a').each(function(){
			
			if(backURL.backURLocation==""){
				return false;
			}
			var hrefL=jQuery(this).attr('href');
			if(hrefL.indexOf(backURL.backURLocation.substring(0,backURL.backURLocation.indexOf('?')))!=-1){
				jQuery(this).parent().addClass('selected');
			
			}
		});
		var htmlContact=jQuery('#templateContactDiv').html();
		
		jQuery('#templateContactDiv').remove();
		jQuery('#tempContactDiv').html(htmlContact);
		
		var htmlPageCount=jQuery('#pageCountTemplateDiv').html();
		
		jQuery('#pageCountTemplateDiv').remove();
		jQuery('#tempPageCountDiv').html(htmlPageCount);
		
      var htmlPageCount2=jQuery('#pageCountTemplateDiv2').html();
		
		jQuery('#pageCountTemplateDiv2').remove();
		jQuery('#tempPageCountDiv2').html(htmlPageCount2);
		
		var htmlNotesPopup=jQuery('#templateNotesPopupDiv').html();
		
		jQuery('#templateNotesPopupDiv').remove();
		jQuery('#tempNotesPopupDiv').html(htmlNotesPopup);
	
		var htmlAdditonalParts=jQuery('#templateAdditionalPartsDiv').html();
		
		jQuery('#templateAdditionalPartsDiv').remove();
		jQuery('#tempAdditionalPartsDiv').html(htmlAdditonalParts);
		
		jQuery('#returnRequests,#returnRequests_but').click(function(){
			if(jQuery(this).attr('name')=='view'){
				showOverlay();
				window.location.href=backURL.backURLocation.substring(backURL.backURLocation.lastIndexOf('/')+1,backURL.backURLocation.length);//hw=hardware
			}else{
			// changed for defect no 10912
			jConfirm(confirmationMessages.ConfirmMsg1, confirmationMessages.ConfirmTitle, function(r)
					{
						if(r==true){
							showOverlay();
							window.location.href=backURL.backURLocation.substring(backURL.backURLocation.lastIndexOf('/')+1,backURL.backURLocation.length);//hw=hardware
						}
						
					});
			}		
		});
		function showToolTipList(clName){
			jQuery('.'+clName).qtip({
				style: {
					name: 'green',
					tip: true,
					width: 200,
					padding: 3,
					//background: '#A2D959',
					color: '#000000',
		      		textAlign: 'center',
					border: {
						//color: '#6699CC',
						width: 0,
						radius: 3
					}
				},
				position: {
					corner: {
						target: 'rightMiddle',
						tooltip: 'topRight'
					},
					adjust: {
						screen: true,
						mouse: true
					}
				}
			});
		}
  
  /*******************Method for opening the popup for all kinds of address***************/
	function popUpAddress(hyperlinkId,spanContactId,selectSuccessFunction,showCreateNew){
		showOverlay();
	if(showCreateNew==null){
		showCreateNew=true;
	}
	var temphyperlinkId=hyperlinkId.replace('[','\\[');
	temphyperlinkId=temphyperlinkId.replace(']','\\]');
	
	var url=popupURLS.addressPopupURL;
	url+='&shwCrN='+showCreateNew;
	jQuery('#addressListPopUp').load(url,function(){
			
	 	 dialog=jQuery('#content_addr').dialog({
				autoOpen: false,
				title: jQuery('#'+temphyperlinkId).attr('title'),
				modal: true,
				draggable: false,
				resizable: false,
				height: 500,
				width: 950,
				open:function(){
	 		 		initializeAddressGrid(hyperlinkId,spanContactId,selectSuccessFunction);
				},
				close: function(event,ui){
					hideOverlay();
					dialog=null;
					jQuery('#content_addr').remove();
					}
				});
			//jQuery(document).scrollTop(0);
			dialog.dialog('open');
		
			if (window.PIE) {
	            document.getElementById("createNewAddressButton").fireEvent("onmove");
	            document.getElementById("cancelAddressButton").fireEvent("onmove");
	        }								
	});
	
    }

	function popUpContact(hyperlinkId,spanContactId,selectSuccessFunction)
	{
		
		showOverlay();
		var temphyperlinkId=hyperlinkId.replace('[','\\[');
		temphyperlinkId=temphyperlinkId.replace(']','\\]');
		var url=popupURLS.contactPopupURL;
		//jQuery('#contactPopUp').load(jQuery('#'+hyperlinkId).attr('href'),function(){
			jQuery('#contactPopUp').load(url,function(){
			dialog=jQuery('#content_contact').dialog({
				autoOpen: false,
				title: jQuery('#'+temphyperlinkId).attr('title'),
				modal: true,
				draggable: false,
				resizable: false,
				open:function(){
				initializeContactGrid(hyperlinkId,spanContactId,selectSuccessFunction);
				},
				height: 550,
				width: 790,
				close: function(event,ui){
					
					hideOverlay();
					dialog=null;
					jQuery('#content_contact').remove();
					}
				});
			//jQuery(document).scrollTop(0);
			dialog.dialog('open');
			
			});
		return false;
	}


	function popUpChlTree()
	{
		var url=popupURLS.chlPopupURL;
		jQuery('#chlPopUp').load(url,function(){
			var heightDoc=jQuery(window).height()<630?630:jQuery(window).height();
			var divht=heightDoc-150;
			jQuery('#chl_node_tree_container').css('width','100%');
			jQuery('#chl_node_tree_container').css('height',divht+'px');	
			dialog=jQuery('#content_chlTree').dialog({
			autoOpen: false,
			open:function(){
				initialiseCHLTree();		
				},
			title: jQuery('#selectChlLink').attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
			width: 400,
			height: heightDoc-80,
			position: 'top',
			close: function(event,ui){
					dialog=null;
					jQuery('#content_chlTree').remove();
									
					
				}
		});
		//jQuery(document).scrollTop(0);
		dialog.dialog('open');

		if (window.PIE) {
		 	document.getElementById("button_cancel_chl").fireEvent("onmove");
		   	document.getElementById("button_chl").fireEvent("onmove");
		}
		
		
	});
	return false;
	};	

	function closeDialog(){
		dialog.dialog('close');
	}
	function replaceAll(source,replaceTarget,replaceWith){
    	
 		while(source.indexOf(replaceTarget)>0)
				source=source.replace(replaceTarget,replaceWith);

			return source;
 	}

	 function submitForm(mandatoryFieldObj){
		 //remove the date fields if blank.
		
		 if(mandatoryFieldObj["save"]){
			 for(key in mandatoryFieldObj){
				 
				  if(typeof mandatoryFieldObj[key] != 'boolean' && typeof mandatoryFieldObj[key]!='string'){
					 if( mandatoryFieldObj[key][0].indexOf("Date")>0){ 
						 if(jQuery('#'+mandatoryFieldObj[key][0]).val()==""){
							 jQuery('#'+mandatoryFieldObj[key][0]).attr('name','');
						 }
					 }
					 
				 }
			 }
			 
		 }
		
		 //this is for enabling the fields as it will not get submitted
		 for(key in mandatoryFieldObj){
			 
			  if(typeof mandatoryFieldObj[key] != 'boolean' && typeof mandatoryFieldObj[key]!='string'){
				  if(mandatoryFieldObj[key][0].indexOf("Date")>0 ){
					 jQuery('#'+mandatoryFieldObj[key][0]).removeAttr('disabled');
				  }
				
			 }
			 
			  
		 }
		 /* Commented for Sept 2014 release if(mandatoryFieldObj["requestFrom"]=='move' || mandatoryFieldObj["requestFrom"]=='deinstall'){
		   	creatHiddenInputsPageCount();
		 }*/
		 if(mandatoryFieldObj["save"]){
			 jQuery('#'+mandatoryFieldObj["formId"]).submit();
		 }else if(mandatoryFieldObj["closeOut"]){
			 jQuery('#saveCloseoutFlag').val(mandatoryFieldObj["closeOut"]);
			 jQuery('#'+mandatoryFieldObj["formId"]).submit();
			 //closeOutRequest();
		 }
		 

	 }
	 
	 var acptRejectData={};
	 function commentConfirm(){
		 
				if(jQuery('#reject_textArea').val()==""){
					jQuery('#commentErrorDiv').show();
					return;
				}
				acptRejectData["comments"]=jQuery('#reject_textArea').val();
				
				acceptRejectRequest(acptRejectData.status,acptRejectData.srNum,acptRejectData.activityNum,acptRejectData.id);
				
		
	 }
	 function acceptRejectRequest(status,srNum,activityNum,id){	
		 
		 //Added for 10913 June Release MPS 2.1
		 var comments="";
		 if( "comments" in acptRejectData){
			 if(acptRejectData.comments!=""){
				 comments=acptRejectData.comments;
				 closeDialog();
			 }
			 
		 }
		 if(status.indexOf('Not')!=-1 && comments==""){
			 //the status is Not Accepted or Reject request
			
			 dialog=jQuery('#commentsDiv').dialog({
					autoOpen: false,
					title: confirmationMessages.commentTitle,
					modal: true,
					draggable: false,
					resizable: false,
					height: 150,
					width: 350,
					open:function(){
						jQuery('#commentErrorDiv').hide();
						jQuery('#reject_textArea').val("");
						acptRejectData["status"]=status;
						acptRejectData["srNum"]=srNum;
						acptRejectData["activityNum"]=activityNum;
						acptRejectData["id"]=id;
						
						
					},
					close:function(){
						acptRejectData["comments"]="";
						dialog.dialog('destroy');
					}
					});
				
				dialog.dialog('open');
			
			
			
			 return;
		 }
		
			//alert(popupURLS.acceptURL);
		 //alert('b4 ajax ='+comments);
		 jQuery.ajax({
				url : popupURLS.acceptURL,
				beforeSend: function()
				{
					showOverlay();
				},
				type:'POST',
				dataType: 'JSON',
				data: {
					srNumber:srNum,
					activityId:activityNum,
					status:status,
					comments:comments	
					},
				success: function(results){
						//alert('success');
						
						var obj2=results;
						//obj2=jQuery.parseJSON(results);
						
						
						if(obj2 !=null){						
							var error=obj2.error;
							if(obj2.error == "success"){
								jQuery('#accept_'+id.split("_")[1]).remove();
								jQuery('#reject_'+id.split("_")[1]).remove();
								
								if(id.indexOf('accept')!=-1){
									jQuery('#update_'+id.split("_")[1]).show();
								}
								
								hideOverlay();
								//jAlert(confirmationMessages.alertSuccess,"",hideOverlay);								
							}else{
								jAlert(confirmationMessages.alertFailure,"",hideOverlay);
							}
						}
					}
			});
			
		
		}
	 
	 function checkMandatoryFields(mandatoryFieldObj){
	
		 var errorExist=false;
		 var html="<ul>";
		 if(mandatoryFieldObj["closeOut"] || mandatoryFieldObj["save"]){
			 
		 for(key in mandatoryFieldObj){
			 
			 var val="";
			
			 if(typeof mandatoryFieldObj[key] != 'boolean' && typeof mandatoryFieldObj[key] != 'string'){
				 
				 if(mandatoryFieldObj[key][0].indexOf(".")>0){
					 val=jQuery("[name='"+mandatoryFieldObj[key][0]+"']").val();
				 }else{
					 val=jQuery('#'+mandatoryFieldObj[key][0]).val(); 
				 }
				
				 if(mandatoryFieldObj[key][0]=='serviceRequestDate' || mandatoryFieldObj[key][0]=='expectedEndDate'){
					
				 }else if( typeof val != 'undefined' && val.trim()=="" ){
					 if(mandatoryFieldObj[key][0]=="desc"){
					 		var statusValue=jQuery('#'+mandatoryFieldObj[5][0]).val();
					 		if(mandatoryStatusComments.indexOf(statusValue)==-1){
					 			continue;
					 		}
					 	}
					 errorExist=true;
					 jQuery('#'+mandatoryFieldObj[key][0]).addClass('errorColor');
					 html+="<li>"+mandatoryFieldObj[key][1]+"</li>";
						 
				 }
			 }
		 }
		 
		 }
		 html+="</ul>";
		 jQuery('#errorDiv').html(html);
		 		 
		 return errorExist;
		 
	 }
	 
	 function checkFormats(formatValidation){
		 var errorexist=false;
		 var html="<ul>";
		 for(key in formatValidation){
			 if(typeof formatValidation[key] != 'boolean'){
				 if(formatValidation[key][0].indexOf("[")>0 || formatValidation[13][1].indexOf("Consumables")){
					 //this is for list of quantity validation
					 var flagValid=false;
					 for(i=0;i<=formatValidation[key][3];i++){
						 var nameId=formatValidation[key][0].split('[');
						 var finalName=nameId[0]+"["+i+nameId[1];
						 if(formatValidation.save==true && finalName.indexOf('.count')>0 && jQuery("[name='"+finalName+"']").val()==""){
							 continue;
						 }
						if(formatValidation.closeOut==true && finalName.indexOf("deviceContactType")>0){
							
							
							if(typeof jQuery("[name='"+finalName+"']").val() == 'undefined'){
								continue;
							}
							if(jQuery("[name='"+finalName+"']").val()=="Consumable Mgmnt Specialist")
							{
								
							            var addressID=jQuery("[name='userEnteredActivity.serviceRequest.contactInfoForDevice["+i+"].address.addressId']").val();
										if(checkBlankValue(addressID) || addressID=="No Match Row Id")
										{
											
											 if(!flagValid){
												 flagValid=true;
												 html+="<li>"+formatValidation[13][1]+"</li>";
											 }
											 errorexist=true;
										}
							}
							
							
						}
						 if(typeof jQuery("[name='"+finalName+"']").val() != 'undefined' && formatValidation[key][2](jQuery("[name='"+finalName+"']").val())){
							 jQuery("[name='"+finalName+"']").addClass('errorColor');
							 if(!flagValid){
								 flagValid=true;
								 html+="<li>"+formatValidation[key][1]+"</li>";
							 }
							 errorexist=true;
						 }
						 
					 }
				 }else{
					 
					 val=jQuery("[name='"+formatValidation[key][0]+"']").val();
					if(val!=""){
						if(!formatValidation[key][2](val)){
							jQuery("[name='"+formatValidation[key][0]+"']").addClass('errorColor');
							html+="<li>"+formatValidation[key][1]+"</li>";
							errorexist=true;
						}
					}
					 
				 }
			 }
		 }
		 html+="</ul>";
		 jQuery('#errorDiv').html(html);
		
		 return errorexist;
	 }
	 
	 function doCustomerProfileValidation(customerProfileArray,inputNamesMapToSiebel,reqType){
		 
		 var errorexist=false;
		 var html="<ul><li>"+headingMessage.validationMsg+"</li>";
		
		 
		for(key in inputNamesMapToSiebel){	
			 
			 var siebelObjDetails=null;
			 
			 for(i=0;i<customerProfileArray.length;i++){
				 if(inputNamesMapToSiebel[key][1]==customerProfileArray[i]){
					 siebelObjDetails=inputNamesMapToSiebel[key];
					break;
				 }
			 }
			 
			 if(siebelObjDetails==null){
				 continue;
			 }
			 
			 var val="";
			 if(siebelObjDetails[0].indexOf('[]')!=-1){
				 if(reqType!='install' && siebelObjDetails[0].indexOf('pageCounts')!=-1){
					 continue;
				 }
				 //alert('inside if of list values');
				 var stIndex=0;
				 var nameId=siebelObjDetails[0].split('[');
				 var finalName;
				 var valFound=false;
				 var typeofval=null;
				 
				 do{
					 finalName=nameId[0]+"["+stIndex+nameId[1];
					 typeofval=typeof (jQuery("[name='"+finalName+"']").val());
					
					 if(typeofval!='undefined' && jQuery("[name='"+finalName+"']").val()==siebelObjDetails[1]){
						 valFound=true;
						 val=jQuery("[name='"+finalName+"']").val();
						 break;
					 }
					 stIndex++;
				 }while(typeofval!='undefined');
				 if(valFound==false){
					 val="";
				 }
			 }else{
				 val=jQuery("[name='"+siebelObjDetails[0]+"']").val();
			 }
			 
			
			 if(val==""){
				
				 html+="<li>"+siebelObjDetails[3].replace(":","")+"</li>";
				  jQuery("[name='"+siebelObjDetails[0]+"']").addClass('errorColor');
				 errorexist=true;
			 }
			
			 
			 
		 }
		 html+="</ul>"; 
		
		 jQuery('#errorDiv').html(html);
		 return errorexist;
	 }
	 
	 function bindClickEvent(){
		 jQuery(':input').click(function(){
				jQuery(this).removeClass('errorColor');
			});
		 
	 }
	 
	 function checkBlankValue(val){
		 if(val==""){
			 return true;
		 }else{
			 return false;
		 }
		 
	 }
	 
	 function popCustomerProfileInformation()
		{
			
			
				dialog=jQuery('#customerProfilePopup').dialog({
				autoOpen: false,
				open:function(){
					jQuery('#customerProfilePopup div').html('');
						var html="<ul>";
						for(i=0;i<customerProfileFields.length;i++){
							
							for(key in inputNamesMapToSiebel){
								if(inputNamesMapToSiebel[key][1]==customerProfileFields[i]){
									html+="<li class=\"first\"><b>";
									html+=inputNamesMapToSiebel[key][2].replace(":","");
									html+="</b></li>";
								}
							}
							
							
							
						}
						for(i=0;i<customerProfileFields.length;i++){
							
							for(key in inputNamesMapToSiebel_notInPortal){
								if(inputNamesMapToSiebel_notInPortal[key][1]==customerProfileFields[i]){
									html+="<li class=\"first\">";
									html+=inputNamesMapToSiebel_notInPortal[key][2].replace(":","");
									html+="</li>";
								}
							}
							
							
							
						}
						html+="</ul>";
						jQuery('#customerProfilePopup div').append(html);
					},
				title: jQuery('#customerProfilePopup').attr('title'),
				modal: true,
				draggable: true,
				resizable: false,
				width: 400,
				height: 450,
				position: 'top',
				close: function(event,ui){
						dialog=null;								
						
					}
			});
			//jQuery(document).scrollTop(0);
			dialog.dialog('open');

			
		return false;
		};	
	 
	function addRequiredFieldToInput(){
		for(i=0;i<customerProfileFields.length;i++){
			
			for(key in inputNamesMapToSiebel){
				if(inputNamesMapToSiebel[key][1]==customerProfileFields[i] && inputNamesMapToSiebel[key][0].indexOf('[]')==-1){
					
					var html=jQuery("[name='"+inputNamesMapToSiebel[key][0]+"']").parent().prev('td').html();
					if(html != null && html.indexOf("req")==-1)
						jQuery("[name='"+inputNamesMapToSiebel[key][0]+"']").parent().prev('td').html("<span class=\"req\">*</span>"+html);
					html=jQuery("[name='"+inputNamesMapToSiebel[key][0]+"']").parent().prev('label').html();
					if(html != null && html.indexOf("req")==-1)
						jQuery("[name='"+inputNamesMapToSiebel[key][0]+"']").parent().prev('label').html("<span class=\"req\">*</span>"+html);
				}
			}
			
			
			
		}
	}
	 