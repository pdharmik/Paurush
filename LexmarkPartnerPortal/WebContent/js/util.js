/*
 *Author: Kevin He
 *Version Date: March 21, 2011
 *The following js methods intend to append place holder(:nbsp;) 
 *to the grid list header if the number of field of list header is less than required.
 */
function autoAppendPlaceHolder(input, inputCount){
	var count = 0;
	if(input != null && input.length > 0){
		count = checkNumberOfField(input);
		if(count != inputCount){
			for(var i = 0; i < (inputCount - count); i++)
				input = appandPlaceHolder(input);
		}
	}
	//alert("ActNmbrOfField="+count +" ReqNmbrOfField="+inputCount);
	return input;
}

function checkNumberOfField(input){
	var count = 0;
	var str =(input||"").split(",");
	if(str != null)
		count = str.length;
	return count;
}

function appandPlaceHolder( input){
	input = input +",?";
	return input;
}

function autoApplyRequiredStyle(input){
	if(input != null && input.length > 0){
		input = input.replace(/\*/g,'<span class="required">*</span>');
	}
	return input;
}
/* --- End  ---*/