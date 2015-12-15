
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
	if("county" in addressRowObj && $.trim(addressRowObj.county)!=""){
		fragment+=",&nbsp;"+addressRowObj.county;
	}
	if (addressRowObj.state!="") {
		fragment+=",&nbsp;"+addressRowObj.state;
		
	}else{
		if("stateCode" in addressRowObj && $.trim(addressRowObj.stateCode)!=""){
			fragment+=",&nbsp;"+addressRowObj.stateCode;
		}
		else if("stateFullName" in addressRowObj && $.trim(addressRowObj.stateFullName)!=""){
			fragment+=",&nbsp;"+addressRowObj.stateFullName;
		}
	}
	if( "provice" in addressRowObj && $.trim(addressRowObj.province)!="" ) {
		fragment+=",&nbsp;"+addressRowObj.province;
		
	}
	if("district" in addressRowObj &&  $.trim(addressRowObj.district)!="" && $.trim(addressRowObj.district)!=" ") {
		fragment+=",&nbsp;"+addressRowObj.district;			
	}
	
	fragment+="</div><div>"+addressRowObj.postalCode+"</div>";
	if(addressRowObj.country!=""){
		fragment+="<div>"+addressRowObj.country+"</div>";
	}
	return fragment;
 }


function generateAddressDisplayLBSHTML(addressRowObj){
	 //fragment="<div>"+addressRowObj.storeFrontName+"</div>";
	if(addressRowObj==null){
		return;
	}
	if(("aId" in addressRowObj)==false){
		return;//This checking is done as from DB addressId might not be available.
	}
	
	var fragment="<div>"+(("aLine1" in addressRowObj)==true?addressRowObj.aLine1:"")+"</div>";

	if ("ofceNm" in addressRowObj && $.trim(addressRowObj.ofceNm)!="")
		fragment+="<div>"+addressRowObj.ofceNm+"</div>";
	
	if ("aLine2" in addressRowObj && addressRowObj.aLine2!="")
		fragment+="<div>"+addressRowObj.aLine2+"</div>";
	
	fragment+="<div>";
	if("cty" in addressRowObj && addressRowObj.cty){
		fragment+=addressRowObj.cty;
	}

	if("conty" in addressRowObj && $.trim(addressRowObj.conty)!=""){
		fragment+=",&nbsp;"+addressRowObj.conty;
	}

	if ( "state" in addressRowObj && addressRowObj.state!="") {
		if(addressRowObj.state!=null && addressRowObj.state.indexOf("^")==-1){
			fragment+=",&nbsp;"+addressRowObj.state;
		}else{
			fragment+=",&nbsp;"+(addressRowObj.state.substring(0,addressRowObj.state.indexOf("^")));
		}
		
		
	}else{
		if("stCode" in addressRowObj && $.trim(addressRowObj.stCode)!=""){
			fragment+=",&nbsp;"+addressRowObj.stCode;
		}
		else if("stFulNm" in addressRowObj && $.trim(addressRowObj.stFulNm)!=""){
			fragment+=",&nbsp;"+addressRowObj.stFulNm;
		}
	}
	
	if( "prvnc" in addressRowObj && $.trim(addressRowObj.prvnc)!="" ) {
		fragment+=",&nbsp;"+addressRowObj.prvnc;		
	}
	
	if("dstrct" in addressRowObj &&  $.trim(addressRowObj.dstrct)!="" && $.trim(addressRowObj.dstrct)!=" ") {
		fragment+=",&nbsp;"+addressRowObj.dstrct;			
	}

	fragment+="</div><div>"+(("poCde" in addressRowObj)==true?addressRowObj.poCde:"")+"</div>";
	if("ctry" in addressRowObj && addressRowObj.ctry!=""){
		fragment+="<div>"+addressRowObj.ctry+"</div>";
	}
	
	return fragment;
}

function generateAddressDisplayForAsset(addressObjectLbs){
	var fragment="<div>"+addressObjectLbs["address"]+"</div>";
	fragment+="<div>"+addressObjectLbs["city"]+",&nbsp;"+addressObjectLbs["state"]+"</div>";
	fragment+="<div>"+addressObjectLbs["zipCode"]+"</div>";
	fragment+="<div>"+addressObjectLbs["country"]+"</div>";
	return fragment;
}