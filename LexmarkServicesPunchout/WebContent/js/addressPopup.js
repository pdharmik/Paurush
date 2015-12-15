
function generateAddressDisplayHTML(addressRowObj){
 	 //fragment="<div>"+addressRowObj.storeFrontName+"</div>";
 	
 	var fragment="<div>"+addressRowObj.addressLine1+"</div>";
	
	if ("officeNumber" in addressRowObj && addressRowObj.officeNumber.trim()!="")
		fragment+="<div>"+addressRowObj.officeNumber+"</div>";
	
	if (addressRowObj.addressLine2!="")
		fragment+="<div>"+addressRowObj.addressLine2+"</div>";

	fragment+="<div>";
	if("city" in addressRowObj && addressRowObj.city){
		fragment+=addressRowObj.city;
	}
	if("county" in addressRowObj && addressRowObj.county.trim()!=""){
		fragment+=",&nbsp;"+addressRowObj.county;
	}
	if (addressRowObj.state!="") {
		fragment+=",&nbsp;"+addressRowObj.state;
		
	}else{
		if("stateCode" in addressRowObj && addressRowObj.stateCode.trim()!=""){
			fragment+=",&nbsp;"+addressRowObj.stateCode;
		}
		else if("stateFullName" in addressRowObj && addressRowObj.stateFullName.trim()!=""){
			fragment+=",&nbsp;"+addressRowObj.stateFullName;
		}
	}
	if( "provice" in addressRowObj && addressRowObj.province.trim()!="" ) {
		fragment+=",&nbsp;"+addressRowObj.province;
		
	}
	if("district" in addressRowObj &&  addressRowObj.district.trim()!="" && addressRowObj.district.trim()!=" ") {
		fragment+=",&nbsp;"+addressRowObj.district;			
	}
	
	fragment+="</div><div>"+addressRowObj.postalCode+"</div>";
	if(addressRowObj.country!=""){
		fragment+="<div>"+addressRowObj.country+"</div>";
	}
	return fragment;
 }