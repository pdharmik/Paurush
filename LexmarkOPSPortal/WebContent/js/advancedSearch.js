
function populateSearchCriterias(){
    	var tmpSearchCriteria = "";
    	var div = document.getElementById("divAdvancedSearch");
    	var inputElements = div.getElementsByTagName('input');
    	var selectElements = div.getElementsByTagName('select');
    	for(var i=0; i<inputElements.length; i++){
    	   if(inputElements[i].type=='text'){
    		   var txtValue = inputElements[i].value;
    		   var txtName = inputElements[i].name;
    		   if(txtValue.trim() != ""){
    			   if(tmpSearchCriteria == "")
    				   tmpSearchCriteria = txtName +"^" + txtValue;
    			   else
    				   tmpSearchCriteria = tmpSearchCriteria + "__" + txtName +"^" + txtValue;
    		   }
    	   }else if(inputElements[i].type=='checkbox'){
    		   var cheName = inputElements[i].name;
    		   if(inputElements[i].checked){
    			   if(tmpSearchCriteria == "")
    				   tmpSearchCriteria = cheName +"^" + "true";
    			   else
    				   tmpSearchCriteria = tmpSearchCriteria + "__" + cheName +"^" + "true";
    		   }
    	   }
    	 }
    	
    	for(var j=0; j<selectElements.length; j++){
    		   var selValue = selectElements[j].value;
    		   var selName = selectElements[j].name;
    		   if(selValue!= ""){
    			   if(tmpSearchCriteria == "")
    				   tmpSearchCriteria = selName +"^" + selValue;
    			   else
    				   tmpSearchCriteria = tmpSearchCriteria + "__" + selName +"^" + selValue;
    		   }
    	 }
    	 return tmpSearchCriteria; 
    }