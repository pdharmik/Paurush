
saw.copyiframe = new function() { };

saw.copyiframe.addMyHeadersToDictionary = function(tagName)
{
   var head = saw.copyiframe.getDocHead(document);
   if (head == null)
      return;
	tags = head.getElementsByTagName(tagName);
	for (i=0; i<tags.length; i++) 	
	{	
		if (tags[i].src != null && tags[i].src.length != 0)
			sawAllHeaders[tags[i].src]="";	
	}
}

saw.copyiframe.getRedirectUrl = function(key)
{
	//use only actual css/js path within OBI EE to avoid duplicates from different sites
	var idx = key.indexOf("RedirectURL="); 
	if (idx > -1)
		key = key.substr(idx);
	return key;
}

saw.copyiframe.addHeaderToDictionary = function(key,header)
{
	//use only actual css/js path within OBI EE to avoid duplicates from different sites
	sawAllHeaders[saw.copyiframe.getRedirectUrl(key)]="";	
}


saw.copyiframe.containsHeader = function (header)
{
	if (header.tagName.toUpperCase() == "SCRIPT")
	{
		if (header.src != null && header.src.length != 0)
			return sawAllHeaders[saw.copyiframe.getRedirectUrl(header.src)] != undefined;
		else
			return sawAllHeaders[header.text] != undefined; 
	}
	else if (header.tagName.toUpperCase() == "LINK")
		return sawAllHeaders[saw.copyiframe.getRedirectUrl(header.href)] != undefined;
	else
	{
		return true;
	}
}

saw.copyiframe.getDocHead = function (doc )
{	
	return doc.getElementsByTagName("HEAD")[0];
}

saw.copyiframe.copyScriptAttrs = function (scrpt, from)
{
	scrpt.language = from.language;
	if (from.text != null && from.text.length != 0)
	{
		scrpt.text = from.text;
		return scrpt.text
	}
	else if (from.src != null && from.src.length != 0)
	{
		scrpt.src = from.src;
		return scrpt.src;
	}
	else
		return "";
}

saw.copyiframe.copyScriptBefore = function (before, par, from)
{
//   alert("saw.copyiframe.copyScriptBefore");
	script = par.ownerDocument.createElement("SCRIPT");
	script.defer = true; // necessary for IE apparently
	var key =  saw.copyiframe.copyScriptAttrs(script,from);
	par.insertBefore(script,before);
	return key;
}

saw.copyiframe.copyScript = function (par, from)
{
	script = par.ownerDocument.createElement("SCRIPT");
	script.defer = true; // necessary for IE apparently
	var key =  saw.copyiframe.copyScriptAttrs(script,from);
	par.appendChild(script);
	return key;
}


saw.copyiframe.copyLink = function (to, from)
{
	lnk = to.ownerDocument.createElement("link");
	lnk.href = from.href;
	lnk.name = from.name;
	lnk.type = from.type;
	lnk.rel = from.rel;
    to.appendChild(lnk);
	return  lnk.href;
}

saw.copyiframe.mergeHeadersCollection = function (tags,copyFunc)
{
	myHead = saw.copyiframe.getDocHead(document);
	
	for (i=0; i<tags.length; i++) 	
	{	
		if( !saw.copyiframe.containsHeader(tags[i]))
		{
			key = copyFunc(myHead,tags[i]);
			saw.copyiframe.addHeaderToDictionary(key, tags[i]);
		}
	}

}

saw.copyiframe.mergeHeaders = function (doc)
{
   var head = saw.copyiframe.getDocHead(doc);
   if (head == undefined || head == null)
      return;
	saw.copyiframe.mergeHeadersCollection( head.getElementsByTagName("SCRIPT"),saw.copyiframe.copyScript);
	saw.copyiframe.mergeHeadersCollection( head.getElementsByTagName("LINK"),saw.copyiframe.copyLink);
}

saw.copyiframe.encodeHTML = function (s)
{
		re1 = /</g;
		re2 = />/g;
		return s.replace(re1, "&lt").replace(re2 , "&gt");
}

saw.copyiframe.setFormParam = function (tForm, name, value)
{
	if (tForm[name] == undefined)
		tForm.innerHTML = tForm.innerHTML + "<input type=hidden name=" + name + " value=" + value + " >";
	else
		tForm[name].value = value;
}


saw.getSAWReportObj = function(reportID)
{
   reportDiv=document.getElementById(reportID);
   strAttributeName="Effective_" + reportID + "_ReportObj_ID";
   reportObjID = reportDiv.getAttribute(strAttributeName);
   return window[reportObjID];
}


saw.copyiframe.kLinkWholePage = 0;
saw.copyiframe.kLinkStayInDiv = 1;
saw.copyiframe.kLinkNew = 2;

saw.copyiframe.EmbeddedReport  = function EmbeddedReport(divID,viewID,viewName,hiddenFrameID,sessionCookie,linksMode)
{
	this.m_LinksType = linksMode;
	this.m_divID = divID;
	this.m_viewID = viewID;
	this.m_hiddenFrameID = hiddenFrameID;
	this.m_sessionCookie = sessionCookie;
	this.m_StateId = "";
	this.m_FullViewState = "";
	this.m_viewName=viewName;
   this.m_hiddenFrameReady=false;
   this.m_AllowRedirectToHiddenIFrame=true;
   this.m_FormForDownloads=null;
   this.m_FormCharset="utf-8";
}

saw.copyiframe.EmbeddedReport.prototype.setFormCharset = function(value)
{
   this.m_FormCharset=value;
}

saw.copyiframe.EmbeddedReport.prototype.executeReportCommand = function(aArguments,linkType,formToUse)
{
   if (formToUse == undefined || formToUse == null)
   {
      reportDiv=document.getElementById(this.m_divID);
      goFormID = reportDiv.getAttribute("Effective_GoForm_ID");
      goForm =document.forms[goFormID];
      formToUse = goForm;
   }
   for (i=0;i< aArguments.length;i+=2)
   {
      saw.copyiframe.setFormParam(formToUse,aArguments[i],aArguments[i+1]);
   }
   this.onBeforeFormSubmit(formToUse,linkType);
   formToUse.submit();
//   NQWSubmitFormWithView(formToUse,this.m_viewID,null);
   onAfterSubmitNQWForm(formToUse,this.m_viewID);
}
   

saw.copyiframe.EmbeddedReport.prototype.downloadOrPrint = function(action,format)
{
   this.executeReportCommand(new Array("Action", action, 
                           "Format", format,
                           "ExpectBinary","1",
                           "ViewID",this.m_viewID),
                           saw.copyiframe.kLinkNew,
                           this.m_FormForDownloads);
}

saw.copyiframe.EmbeddedReport.prototype.printHTML = function()
{
   this.downloadOrPrint("print", "");
}

saw.copyiframe.EmbeddedReport.prototype.printPDF = function()
{
   this.downloadOrPrint("print", "pdf");
}

saw.copyiframe.EmbeddedReport.prototype.downloadToExcel = function()
{
   this.downloadOrPrint("download", "Excel");
}

saw.copyiframe.EmbeddedReport.prototype.downloadMHTML = function()
{
   this.downloadOrPrint("download", "mht");   
}

saw.copyiframe.EmbeddedReport.prototype.downloadCSV = function()
{
   this.downloadOrPrint("download", "CSV");
}

saw.copyiframe.EmbeddedReport.prototype.refresh = function()
{
   this.executeReportCommand(new Array("Action", "Refresh","ViewID",this.m_viewID));
}

saw.copyiframe.EmbeddedReport.prototype.returnToInitialReportView = function()
{
   this.executeReportCommand(new Array("ReturnToInitialReportView", "1","ViewID",this.m_viewID));
}

saw.copyiframe.EmbeddedReport.prototype.allowRedirectToHiddenIframe = function(allow)
{
   this.m_AllowRedirectToHiddenIFrame=allow;
}

saw.copyiframe.EmbeddedReport.prototype.setFormForDownloads = function(formForDownloads)
{
   this.m_FormForDownloads=formForDownloads;
}

saw.copyiframe.EmbeddedReport.prototype.onBeforeFormSubmit = function(formObj,linkType)
{
//	alert("onBeforeFormSubmit " + this.m_divID );
   if (linkType == undefined)
      linkType = this.m_LinksType;
      
	if (formObj.ViewState != undefined)
		formObj.ViewState.value = this.m_StateId;			 


   if (this.m_sessionCookie != null && this.m_sessionCookie.length != 0)
      saw.copyiframe.setFormParam(formObj,"NQID",this.m_sessionCookie);

	saw.copyiframe.setFormParam(formObj,"GenerateForDiv","");
	saw.copyiframe.setFormParam(formObj,"ContainerID",this.m_viewID);
	saw.copyiframe.setFormParam(formObj,"ViewName",this.m_viewName);
	if (this.m_FormCharset != null)
   {
	   saw.copyiframe.setFormParam(formObj,"icharset",this.m_FormCharset );
	   formObj.acceptCharset=this.m_FormCharset ;
	}

   if (formObj.ExpectBinary != undefined && formObj.ExpectBinary == "1")
      formObj.target = "_blank";

	switch (linkType)
	{
		case saw.copyiframe.kLinkWholePage:
			formObj.target = "_self";
			break;
		case saw.copyiframe.kLinkStayInDiv:
		   if (this.m_AllowRedirectToHiddenIFrame)
			   formObj.target = this.m_hiddenFrameID;
			else
			   formObj.target = "_self";
			saw.copyiframe.setFormParam(formObj,"GenerateForDiv",this.m_divID);
			break;
		case saw.copyiframe.kLinkNew:
			formObj.target = "_blank";
			break;
	}
	
	//	alert(formObj.outerHTML);
	
}

saw.copyiframe.getHTMLAsString = function (node)
{
	return "<PRE>" + saw.saw.copyiframe.encodeHTML(node.outerHTML) + "</PRE>";
}


saw.copyiframe.EmbeddedReport.prototype.copyToHiddenIFrame = function(divBodyHolder)
{
 /*  if (!this.m_hiddenFrameReady)
   {
      this.m_PendingIFrameContentHolder=divBodyHolder;
      return;
   }
   else if (this.m_PendingIFrameContentHolder != undefined)
   {
      this.m_PendingIFrameContentHolder=null;
   }*/
   
   var frameobj = window.frames[this.m_hiddenFrameID];
  // alert("copyToHiddenIFrame");
   var docIframe;
   if (frameobj.contentDocument == undefined)
	   docIframe  = frameobj.document;
   else
	   docIframe  = frameobj.contentDocument;

   bodyTexts = divBodyHolder.childNodes;
   var s="";
   for (i=0;i<bodyTexts.length;++i)
   {
      var vFragment = bodyTexts[i]
      if (vFragment.nodeType==3 || vFragment.nodeType==4)//text or CDATA
      {
            var nLen = vFragment.nodeValue.length;
            s = vFragment.nodeValue;
            var j;
            for (j=0;j<nLen-1024;j += 1024)
            {
               docIframe.write(s.substr(j,1024));
            }
            
            docIframe.write(s.substr(j,nLen-j));
      }
//            docIframe.write(bodyTexts[i].nodeValue);
   }
   
   //docIframe.write(s);
   docIframe.close();
  /* var scrpt = new Object();
   scrpt.language = "javascript";
   scrpt.text = "document.domain='"+document.domain+"';";
   var b = docIframe.documentElement.getElementsByTagName("BODY");
   alert(b);
   saw.copyiframe.copyScript(docIframe.documentElement.getElementsByTagName("BODY")[0],scrpt);*/
   /*
   var sProps=""
   for (p in frameobj)
      sProps +=" " + p;
      alert(sProps);*/
}


saw.copyiframe.EmbeddedReport.prototype.onHiddenFrameLoaded = function()
{
   // this.m_hiddenFrameReady=true;
   //   alert("onHiddenFrameLoaded");
   
   // Reset full view state - it should be new every time the iframe is loaded.
   this.m_FullViewState = '';

   var frameobj = window.frames[this.m_hiddenFrameID];
   
   var docIFrame;
   if (frameobj.contentDocument == undefined)
	   docIFrame  = frameobj.document;
   else
	   docIFrame  = frameobj.contentDocument;

   if(docIFrame == null) // this could be null if the iframe isn't part of the main page (e.g. if it was loaded via a partial page update/AJAX)
   {
      var frameobj2 = document.getElementById(this.m_hiddenFrameID);
      if(frameobj2)
      {
         if (frameobj2.contentDocument == undefined)
	   docIFrame  = frameobj2.document;
         else
	   docIFrame  = frameobj2.contentDocument;
      }
   }
   
   saw.copyiframe.mergeHeaders(docIFrame);
   
   this._adfDecodeAndInsertForms();

   divBodyHolder = docIFrame.getElementById("divEncodedBody");
   
/*   divIsEmptyDocument = docIFrame.getElementById("ThisIsEmptyDocument");
   if (divIsEmptyDocument != undefined)
   {
      if (this.m_PendingIFrameContentHolder != undefined && this.m_PendingIFrameContentHolder != null)
      {
         this.copyToHiddenIFrame(this.m_PendingIFrameContentHolder);
      }
      return;
   }
*/
   var s="";
   if (divBodyHolder != undefined)
   {
      bodyTexts = divBodyHolder.childNodes;
      for (i=0;i<bodyTexts.length;++i)
      {
         if (bodyTexts[i].nodeType==3 || bodyTexts[i].nodeType==4)//text or CDATA
            s += bodyTexts[i].nodeValue;
      }
   }
   else
   {
      s = docIFrame.body.innerHTML;
   }
   

	mydiv = document.getElementById(this.m_divID);
	mydiv.innerHTML  = s;
	
	//all links should fall back into hidden frame
	var collA = mydiv.getElementsByTagName("A");
	for (i=0;i<collA.length;++i)
	{
		curA = collA[i];
		if (curA.target == null || curA.target == "" || curA.target == "_self")
			curA.target =this.m_hiddenFrameID ;			 
	}

	
	//mydiv.innerHTML  +=  "<BR>" + getHTMLAsString(docIFrame.getElementsByTagName("HTML")(0));
	
	divStateId = docIFrame.getElementById("divStateIDHolder");
	
	if (divStateId != null)
		this.m_StateId = divStateId.innerHTML;

        var divADFViewStateHolder = docIFrame.getElementById("divViewStateHolder");
        if(divADFViewStateHolder != null)
        {
	   this.m_FullViewState = divADFViewStateHolder.innerHTML;
	}
	

	//myScripts = docIFrame.scripts.length; 
	myScripts = mydiv.getElementsByTagName("SCRIPT");
	
	for(i = 0; i < myScripts.length; ++i)
	{
		curScript = myScripts[i];
		if ((curScript.src == null || curScript.src == ""))
		{
			if ( curScript.htmlFor == null || curScript.htmlFor == "")
			{
				//window.execScript(curScript.innerHTML, "javascript");
				window.eval(curScript.innerHTML);
			}
		}
		else
		{			
		//recreate script			
			nextN = curScript.nextSibling;
			parentN = curScript.parentNode; 
			newSc = new Object();
			newSc.src = curScript.src;
			newSc.language = curScript.language;
			curScript.removeNode(true);
			saw.copyiframe.copyScriptBefore(nextN,parentN,newSc );
		}
		
	}
	
	var reportDiv = document.getElementById(this.m_divID);
	var goFormID = reportDiv.getAttribute("Effective_GoForm_ID");
	var goForm = document.forms[goFormID];
	
	// Ensure we have the right vars
	if(goForm && goForm.syndicate)
	{
	   var syndicate = goForm.syndicate.value;
	
	   if(goForm.pp && goForm.bu && goForm.bd && syndicate == 'adf') // this is ADF
	   {	   
              if(this.m_FullViewState != '')
	      {
	         var pageId = goForm.pp.value;
	         var bindingId = goForm.bd.value;
	         var bridgeUrl = goForm.bu.value;
	      
	         var postBody = 'adfupdp=true&pp=' + escape(pageId) + '&bd=' + escape(bindingId) + '&vx=' + escape(this.m_FullViewState);
	      
	         this.ajaxRequest = this._getXmlHttpReq();
	      
	         this.ajaxRequest.open('POST', bridgeUrl, true);
		 this.ajaxRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		 this.ajaxRequest.setRequestHeader("Content-Length", postBody.length);
		 this.ajaxRequest.setRequestHeader("Connection", "close");
	         this.ajaxRequest.send(postBody);
	         
	         //alert(pageId + '\n' + bindingId + '\n' + bridgeUrl + '\n' + this.m_FullViewState);
	      }
	   }
	}
}
   
/****
 * Private. Used for ADF xml http request to update page definition view state.
 * Added here to avoid using private methods of xhr connection, and because
 * the saw ajax framework is not designed for connections to anything but
 * the BIPS.
 */
saw.copyiframe.EmbeddedReport.prototype._getXmlHttpReq = function()
{
   var xmlHttp = null;
   if (window.XMLHttpRequest)
	  xmlHttp = new XMLHttpRequest();
   else if (window.ActiveXObject)
	  xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
	
   return xmlHttp;
}

saw.copyiframe.EmbeddedReport.prototype._adfDecodeAndInsertForms = function()
{
   var encodedDivID = this.m_divID + 'EncodedFormBody';
   var formDiv = document.getElementById(encodedDivID);
   
   if(formDiv)
   {
      var parent = formDiv.parentNode;
      var bodyElement = document.getElementsByTagName("BODY")[0];
      
      for(var i = 0; i < bodyElement.childNodes.length; i++) // uhg!!
      {
         if(bodyElement.childNodes[i].id == encodedDivID)
         {
            return;
         }
      }
      
      var encodedText = formDiv.innerHTML;
      var decodedText = unescape(encodedText);      
      
      formDiv.parentNode.removeChild(formDiv);
      bodyElement.appendChild(formDiv);
      formDiv.innerHTML = decodedText;
   }
}

function onAfterSubmitNQWForm(tForm,viewID)
{	
	tForm.target = "_self";	
	var paramElems =  tForm.getElementsByTagName("INPUT");
	for (i=0; i<paramElems.length; i++) 	
	{	
	   paramElems[i].value="";
	}
	
}

function onBeforeSubmitNQWForm(tForm,viewID)
{
   //alert("onBeforeNQWFormSubmit");
   if (viewID == undefined || viewID == null)
      viewID =tForm.ViewID.value;
   strDivID = getIdsMapElementId(viewID );
   strEmbeddedReportID = getEffectiveID(strDivID+"_ReportObj",strDivID);
   window[strEmbeddedReportID].onBeforeFormSubmit(tForm);	
}
	
window.sawAllHeaders = new Array()
saw.copyiframe.addMyHeadersToDictionary("SCRIPT");
saw.copyiframe.addMyHeadersToDictionary("LINK");
