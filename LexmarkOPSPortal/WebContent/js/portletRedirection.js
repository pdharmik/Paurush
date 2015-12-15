function getBaseURL() {
	var currentURL = window.location.href;
	var URLWithoutParams;
	var indexOfQuestionMark = currentURL.indexOf("?");
	if (indexOfQuestionMark > 0) {
		URLWithoutParams = currentURL.substring(0, indexOfQuestionMark);
	} else {
		URLWithoutParams = currentURL;
	}
	
	// TODO right now make sure the global-services always be part of the URL
	//if(URLWithoutParams.contains("/partner-portal")){
	if(URLWithoutParams.indexOf("/partner-portal") != -1){
		baseURL = URLWithoutParams.substring(0, URLWithoutParams.lastIndexOf('/partner-portal') + '/partner-portal'.length) + "/";
		baseURL=baseURL.replace("partner-portal","internal")
	}else{
		baseURL = URLWithoutParams.substring(0, URLWithoutParams.lastIndexOf('/internal') + '/internal'.length) + "/";
	}
	return baseURL;
};
function populateURL(pageDisplayName, paramNames, paramValues) {
	var targetURL;
	targetURL = getBaseURL() + pageDisplayName;
	if (paramNames == null) {
		return targetURL;
	};
	var paramNum = paramNames.length;
	if (paramNum != paramValues.length) {
		alert('Please provide correct parameters.');
		return;
	}
	for (i=0; i<paramNum; i++) {
		if (i==0) {
			targetURL = targetURL + "?" + paramNames[i] + "=" + paramValues[i];
		} else {
			targetURL = targetURL + "&" + paramNames[i] + "=" + paramValues[i];
		}
	}
	return targetURL;
};