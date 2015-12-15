
//************** Generic validation **************//

// start validateEmailAddress 
var keyCode;
function getKeyCode(event){
	keyCode = event.keyCode||event.which;
}
function initEmailFormat(element){
	if(keyCode ==32 || keyCode ==44||keyCode==59||keyCode==13){
		keyCode =0;
		var str = element.value.Rtrim();
		var strBuff="";
		var address = str.split(/[, ;\r\n]/);
		for(var x=0;x<address.length;x++){
			address[x] = address[x]+";";
			if(address[x] !=";"){
			strBuff += address[x];
			}
		}
		element.value = strBuff;
	}
};

function  validateLength(mimlen,maxlen, evt){
    var str=jQuery.trim(evt.value);
	evt.value=str;
    var myLen = 0;
    for(i=0;(i<str.length)&&(myLen<=maxlen);i++){
        if(str.charCodeAt(i)>0&&str.charCodeAt(i)<128)
            myLen++;
        else
            myLen+=2;
    }
    if(myLen>maxlen){
    	jAlert('<spring:message code="warning.maximumLengthExceed" arguments="'+maxlen+'"/>', "");
        evt.value=str.substring(0,i-1);	 
    }
}
function lost(element){	
	var strTemp = element.value.Rtrim();
	if(!strTemp){
		return;
	}
	if(strTemp.charAt(strTemp.length-1)!=";"){
		element.value = strTemp+";";
	}
}
String.prototype.Rtrim = function(){
    return this.replace(/(\s*$)/g, "");
};
function ignoreEnter(str){ 
    var reg=new RegExp("\r\n","g"); 
    str = str.replace(reg,""); 
    return str; 
};

function validateEmailAddress(txtEmailAddress){
	if(null== txtEmailAddress || ""==txtEmailAddress){
		return false;
	}
	var addresses = txtEmailAddress.split(/[;]/);
	var patrn = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	for(var index = 0 ; index < addresses.length ; index++){
		if(addresses[index] ==null ||addresses[index] ==""){continue;}
		if(!patrn.exec(addresses[index]))
			return false;
	}
	return true;
}
// end
function isDigit(s){ 
	var patrn=/^[0-9]{1,20}$/; 
	if (!patrn.exec(s)) 
		return false; 
	return true; 
} 

// return true is the name is consists of the letters that are only in a-z,A-Z.
function isValidName(s)  {  
	var patrn=/^[a-zA-Z]{1,30}$/;  
	if (!patrn.exec(s)) 
		return false;  
	return true;  
}  

// return TRUE if the Tel/Fax number is leading with "+". 
// "-" is allowed(e.g. +1303-290-1980)
function isTel(s)  {  
	var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;  
	if (!patrn.exec(s)) 
		return false;  
	return true;  
}  


function isIPAddress(s){  
	var patrn=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;  
	//var patrn=/^[0-9.]{1,20}$/;  
	if (!patrn.exec(s)) 
		return false;  
	return true;  
} 

function isMACAddress(s){
	var regex=/^([0-9a-f]{2}([:-]|$)){6}$|([0-9a-f]{4}([.]|$)){3}$/i;
	if (regex.test(s))
		return true;
	else
		return false;
}

function isPostalCode(s){  
	var patrn=/^[a-zA-Z0-9 ]{3,12}$/;  
	if (!patrn.exec(s)) 
		return false;  
	return true;  
}  

function isURL(str_url){
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)" 
	     + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP Address URL- 199.194.52.184 
	     + "|" // allow IP and DOMAIN
	     + "([0-9a-z_!~*'()-]+\.)*" // www. 
	     + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // sub hostname
	     + "[a-z]{2,6})" // first level domain- .com or .museum 
	     + "(:[0-9]{1,4})?" // port- :80 
	     + "((/?)|" // a slash isn't required if there is no file name 
	     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
    var re=new RegExp(strRegex); 
        if (re.test(str_url)){
            return (true); 
        }else{ 
            return (false); 
        }
}

function onlyTelNumbers(evt)
{
    var e = event || evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;	
    if ((charCode > 31 && (charCode < 48 || charCode > 57)) && (charCode!=45 && charCode!=43))
        return false;
    return true;
}
