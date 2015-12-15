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
	d.className = "dhx_header_cmenu rounded shadow";
	d.style.width = d.style.height = "5px";
	d.style.display = "none";
	d.id = "grid_menu";
	var a = [];
	var i = 0;
	if (this._fake)
		i = this._fake._cCount;
	var true_ind = i;
	//a.push("<div class=\"columnInner\">");
	/*a.push("<div align='center' valign='middle' style='border-bottom: 1px outset;'><label>"
			+ global_button_message.selectColumns + "</label></div>");*/
	a.push("<table><tr><td colspan='2'><div><h4><a class='ui-icon ui-icon-closethick' style='cursor: pointer' onclick='document.getElementById(\"grid_menu\").style.display=\"none\"'>"
			/*+ global_button_message.close*/ + "</a><label>"
			+ global_button_message.selectColumns + "</label></h4></div></td></tr>");
	
	//alert('this.hdr.rows[1].cells.length='+this.hdr.rows[1].cells.length);
	var cnt=0;
//	a.push("<table>");
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
		var j= cnt%2;
		if(val!="&nbsp;"&&val!="" && j==0){// 11/25/2010 Kevin Do not create a check box for the header which innerHTML is empty.
//			alert("1="+i+" "+j);
			cnt++;
			a.push("<tr><td><div class='dhx_header_cmenu_item2'><input type='checkbox' column='"
						+ true_ind
						+ "' len='"
						+ (c.colSpan || 1)
						+ "' checked='true' "
						+ disableIt
						+" />&nbsp;" + val + "</div></td>");
			//alert('dhx_header_cmenu_item');
		}
		
		else if (val!="&nbsp;"&&val!="") {
			cnt++;
//			alert("2="+i+" "+j);
			a.push("<td><div class='dhx_header_cmenu_item'><input type='checkbox' column='"
					+ true_ind
					+ "' len='"
					+ (c.colSpan || 1)
					+ "' checked='true' "
					+ disableIt
					+" />&nbsp;" + val + "</div></td></tr>");
			//alert('dhx_header_cmenu_item2');
		}
		if(((i+1)==this.hdr.rows[1].cells.length)&&j==0){a.push("<td></td></tr>");}
		true_ind += (c.colSpan || 1);
	};
	a.push("</table>");
	//a.push("</div>");
	/*a.push("<div align='center' valign='middle' style='border-top: 1px inset;'>"
			+"<a style='cursor: pointer' onclick='document.getElementById(\"grid_menu\").style.display=\"none\"'>"
			+ global_button_message.close + "</a></div>");*/
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
	var inpts = d.getElementsByTagName('input');
	for ( var i = 0; i < inpts.length; i++)
		inpts[i].onclick = f;
	document.body.insertBefore(d, document.body.firstChild);
	this._hContext = d;
	//alert('prft before');
	d.style.position = "absolute";
	//zIndex changed for address list popup CI
	d.style.zIndex = 1006;
	d.style.width = 'auto';
	d.style.height = 'auto';
	d.style.display = 'block';
	//alert('prft');
};

dhtmlXGridObject.prototype._showHContext = function(mode, x, y) {
	if (mode && this.enableColumnMove) {
		this._hContext.parentNode.removeChild(this._hContext);
		this._hContext = null;
	};
	this._createHContext();
	this._hContext.style.display = (mode ? 'block' : 'none');
	if (mode) {
		this._updateHContext(true);
		this._hContext.style.left = parseInt(x - 320) + "px";
		this._hContext.style.top = y + "px";
	}
	reskinInputCheckbox();
};
dhtmlXGridObject.prototype._updateHContext = function() {
	var inpts = this._hContext.getElementsByTagName('input');
	for ( var i = 0; i < inpts.length; i++) {
		var c = inpts[i];
		var col = c.getAttribute("column");
		if (this.isColumnHidden(col) || (this.getColWidth(col) == 0))
			c.checked = false;
	}
};
