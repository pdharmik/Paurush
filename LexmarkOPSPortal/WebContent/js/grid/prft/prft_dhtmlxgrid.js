/*
 * Author: Mike Altieri   mike.altieri@perficient.com
 * 
 * Copyright Perficient Inc. http://www.perficient.com
 * 
 * This is an extension of dhtmlxgrid.js and requires dhtmlxgrid.js to work
 * 
 */

//based off of DHTMLX LTD. v.2.6 build 100722

dhtmlXGridObject.prototype.setLockColVisibility = function(visibilityLockedStr) {
	this.colsVisibilityLocked=dhtmlxArray(visibilityLockedStr.split(this.delim));
	
	//For debug...
	//for (var i = 0;	i < this.colsVisibilityLocked.length;	i++){
	//	alert("curCol#="+i+" curColHeader="+this.hdrLabels[i]+" curColAlwaysVisible="+this.colsVisibilityLocked[i]);		
	//};
	
};

dhtmlXGridObject.prototype.prftInit = function(){
	if (this.colsVisibilityLocked == null){
		this.colsVisibilityLocked = dhtmlxArray(this.hdrLabels);
		for (var i = 0;	i < this.colsVisibilityLocked.length;	i++){
			this.colsVisibilityLocked[i] = "false";		
		};
	}
};

dhtmlXGridObject.prototype.setColumnHidden = function(ind, state){

	
	if ((this.colsVisibilityLocked[ind] != null) && (this.colsVisibilityLocked[ind] == "true")){	
		return;
	}
	if (!this.hdr.rows.length){
		if (!this._ivizcol)this._ivizcol=[];
		return this._ivizcol[ind]=state;
	};
	if ((this.fldSorted)&&(this.fldSorted.cellIndex == ind)&&(state))
		this.sortImg.style.display="none";
	var f = convertStringToBoolean(state);
	if (f){
		if (!this._hrrar)this._hrrar=new Array();
		else if (this._hrrar[ind])return;
		this._hrrar[ind]="display:none;";
		this._hideShowColumn(ind, "none");
	}else {
		if ((!this._hrrar)||(!this._hrrar[ind]))
			return;
		this._hrrar[ind]="";
		this._hideShowColumn(ind, "");
	};
	if ((this.fldSorted)&&(this.fldSorted.cellIndex == ind)&&(!state))
		this.sortImg.style.display="inline";
	this.setSortImgPos();
	this.callEvent("onColumnHidden",[ind,state]);
};

/**
* Set Filters Value by filterValues which are separate by comma
* Author: Lyn Chen
* Version date: 9/1/2011
*/
dhtmlXGridObject.prototype.setFiltersValue = function(filterValues){
	var filterValueArr = filterValues.split(",");
	for (var i=0;i < this.filters.length;i++){
		if(!filterValueArr[i])
				continue;
		
		if(this.filters[i][0].combo){
			this.filters[i][0].combo.setComboValue(filterValueArr[i]);
		}else{
			this.filters[i][0].value = filterValueArr[i];
		}
	};
	this.filterValues = filterValues;
};

/**
 * Clear all filters value of the Grid.
 * Version date: 10/8/2011 
 **/
dhtmlXGridObject.prototype.clearFiltersValue = function(){
	for (var i=0;i < this.filters.length;i++){
		if(this.filters[i][0].combo){
			this.filters[i][0].combo.setComboValue("");
		}else{
			this.filters[i][0].value = "";
		}
	};	
	this.filterValues = "";
}

