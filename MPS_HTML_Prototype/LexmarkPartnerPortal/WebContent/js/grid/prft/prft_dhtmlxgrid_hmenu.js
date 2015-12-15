/*
 * Author: Mike Altieri   mike.altieri@perficient.com
 * 
 * Copyright Perficient Inc. http://www.perficient.com
 * 
 * This is an extension of dhtmlxgrid_hmenu.js and requires dhtmlxgrid_hmenu.js to work
 * 
 */

//based off of DHTMLX LTD. v.2.6 build 100722
/**
 * SVEN: 8/31/2011
 * Close all LOV drop down filter when customize grid column
 * add code:
 * 
 	var filter = that.filters;
		if(that.filters){
			for (var i=0; i<that.filters.length; i++){
				if(that.filters[i][0].tagName.toLowerCase() == "div"){
					that.filters[i][0].combo.closeAll();
				}
			}
		}
 */
dhtmlXGridObject.prototype.location;
dhtmlXGridObject.prototype.enableHeaderMenuForButton = function(buttonId,locationId) {
	var button = document.getElementById(buttonId);
	this.location = document.getElementById(locationId);
	var that = this;
	button.onclick = function customButtonPressed(event) {	
		that._doHContClick(event || window.event);
		dhtmlxEvent(document.body, "click", function() {
			if (that._hContext)
				that._showHContext(false);
		});
	};
};

dhtmlXGridObject.prototype._createHContext = function() {
	if (this._hContext)
		return this._hContext;
	var d = document.createElement("DIV");
	d.oncontextmenu = function(e) {
		(e || event).cancelBubble = true;
		return false;
	};
	d.onclick = function(e) {
		(e || event).cancelBubble = true;
		return true;
	};
	d.className = "dhx_header_cmenu";
	d.style.width = d.style.height = "5px";
	d.style.display = "none";
	d.id = "grid_menu";
	var a = [];
	var i = 0;
	if (this._fake)
		i = this._fake._cCount;
	var true_ind = i;
	a.push("<div align='center' valign='middle' style='border-bottom: 1px outset;'><label>"
			+ global_button_message.selectColumns + "</label></div>");
	for ( var i; i < this.hdr.rows[1].cells.length; i++) {
		var c = this.hdr.rows[1].cells[i];
		if (c.firstChild && c.firstChild.tagName == "DIV")
			var val = c.firstChild.innerHTML;
		else
			var val = c.innerHTML;
		var disableIt = "";
		//alert(this.colsVisibilityLocked[i]);
		if (this.colsVisibilityLocked[i] == "true")
			disableIt = "DISABLED";
		if(val!="&nbsp;"&&val!=""){// 11/25/2010 Kevin Do not create a check box for the header which innerHTML is empty.
			a.push("<div class='dhx_header_cmenu_item'><input type='checkbox' column='"
						+ true_ind
						+ "' len='"
						+ (c.colSpan || 1)
						+ "' checked='true' "
						+ disableIt
						+" />&nbsp;" + val + "</div>");
		}
		true_ind += (c.colSpan || 1);
	};
	a.push("<div align='center' valign='middle' style='border-top: 1px inset;'>"
			+"<a style='cursor: pointer' onclick='document.getElementById(\"grid_menu\").style.display=\"none\"'>"
			+ global_button_message.close + "</a></div>");
	d.innerHTML = a.join("");
	var that = this;
	var f = function() {
		
		var filter = that.filters;
		if(that.filters){
			for (var i=0; i<that.filters.length; i++){
				if(that.filters[i][0].tagName.toLowerCase() == "div"){
					that.filters[i][0].combo.closeAll();
				}
			}
		}
		
		var c = this.getAttribute("column");
		if (!this.checked && !that._checkLast(c))
			return this.checked = true;
		if (that._realfake)
			that = that._fake;
		for ( var i = 0; i < this.getAttribute("len"); i++)
			that.setColumnHidden((c * 1 + i * 1), !this.checked);
		if (this.checked && that.getColWidth(c) == 0)
			that.adjustColumnSize(c);
	};
	for ( var i = 0; i < d.childNodes.length-1; i++)
		d.childNodes[i].firstChild.onclick = f;
	document.body.insertBefore(d, document.body.firstChild);
	this._hContext = d;
	d.style.position = "absolute";
	d.style.zIndex = 999;
	d.style.width = 'auto'
	d.style.height = 'auto'
	d.style.display = 'block';
};

