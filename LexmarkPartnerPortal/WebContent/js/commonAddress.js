//var addressArray=[];
	function setAddressValuesFromPopup(addressRowObj){
		
		var selectAddressLink=addressRowObj.clickFromId;
		
		selectAddressLink=selectAddressLink.replace('[','\\[');
		selectAddressLink=selectAddressLink.replace(']','\\]');
		jQuery('#'+selectAddressLink).html(contact_AddressMessages.diffAddressMsg);
		jQuery('#'+selectAddressLink).attr('title',contact_AddressMessages.diffAddressMsg);
	//	addressArray.push(addressRowObj);
		
 		for(var i=0;i<addressRowObj.keys.length;i++){
     		
 			//serviceContact[${status.index}]_firstName
 			//"activity.serviceRequest.contactInfoForDevice[-1].address."
 			
 			var inputHTMLId=addressRowObj.inputIds+"."+addressRowObj.keys[i];

			
		    //alert('contactRowObj.inputIds'+contactRowObj.inputIds);     			
 			//alert('inputHTMLId='+inputHTMLId);
 			//alert('value='+contactRowObj[contactRowObj.keys[i]]);
 			
			
 			jQuery("[name='"+inputHTMLId+"']").val(addressRowObj[addressRowObj.keys[i]]);
 			if(inputHTMLId.search("physicalLocation")!=-1){
 					jQuery("[name='"+inputHTMLId+"']").removeAttr('disabled');
 			}
     		//alert(inputHTMLId+'='+jQuery("[name='"+inputHTMLId+"']").val());
     	}
 		var splitIds=addressRowObj.inputIds.toString().split('.');
        var liHTMLId="showAddress_";
   		for(i=0;i<splitIds.length;i++){
       		if(i!=splitIds.length-1)
   	  			liHTMLId+=splitIds[i]+'\\.';
       		else
       			liHTMLId+=splitIds[i];
       		}
       		
   		liHTMLId=liHTMLId.replace('[','\\[');
   		liHTMLId=liHTMLId.replace(']','\\]');
   		//alert(liHTMLId);
			//alert(generateAddressDisplayHTML(addressRowObj));
			//alert(jQuery('#'+liHTMLId).html());
			
 		jQuery('#'+liHTMLId).html(generateAddressDisplayHTML(addressRowObj));
 		closeDialog();
     }
   
    function generateAddressDisplayHTML(addressRowObj){
     	var fragment="<div>"+addressRowObj.storeFrontName+"</div>";
     	
		fragment+="<div>"+addressRowObj.addressLine1+"</div>";
		
		if ("officeNumber" in addressRowObj && addressRowObj.officeNumber!="")
			fragment+="<div>"+addressRowObj.officeNumber+"</div>";
		
		if (addressRowObj.addressLine2!="")
			fragment+="<div>"+addressRowObj.addressLine2+"</div>";

		fragment+="<div>";
		if("city" in addressRowObj && addressRowObj.city){
    		fragment+=addressRowObj.city;
		}
		if("county" in addressRowObj && addressRowObj.county!=""){
			fragment+=",&nbsp;"+addressRowObj.county;
		}
		if (addressRowObj.state!="") {
			fragment+=",&nbsp;"+addressRowObj.state;
			
		}
		if( "provice" in addressRowObj && addressRowObj.province!="" ) {
			fragment+=",&nbsp;"+addressRowObj.province;
			
		}
		if("district" in addressRowObj &&  addressRowObj.district!="" && addressRowObj.district!=" ") {
			fragment+=",&nbsp;"+addressRowObj.district;			
		}
		
		fragment+="</div><div>"+addressRowObj.postalCode+"</div>";
		if(addressRowObj.country!=""){
			fragment+="<div>"+addressRowObj.country+"</div>";
		}
		return fragment;
     }