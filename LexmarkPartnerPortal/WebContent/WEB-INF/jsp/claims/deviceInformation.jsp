<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<portlet:renderURL var="customerAccountInfoUrl"
	windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
		<portlet:param name="action" value="customerAccountInfo"/>
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portlet-wrap" id="device_information">
  <div class="portlet-header">
    <h3><spring:message code="claim.label.deviceInformation" /></h3>
  </div>
  <div class="portlet-wrap-inner">
  	<div class="width-100per">
	    <div class="columns three">
	      <div class="columnsThree width-20per" ><%--width changed for CI 13.10 BRD13-10-07 --%>
		    <dl>
			  <dd>
	            <div class="device">
				  <label>${claimRequestConfirmationForm.asset.productLine}</label><br />
				  <img width="75" src="${claimRequestConfirmationForm.asset.productImageURL}"/><br />
			    </div><!-- device -->
			  </dd>
	        </dl>		  
	      </div><!-- first-column -->
	      <div class="second-column width-40per-important" ><%--width changed for CI 13.10 BRD13-10-07 --%>
	      	<table>
			  	<tr>
				  	<td align="right" ><span ><B><spring:message code="claim.label.deviceData" /></B></span></td>
				  	<td width="20px">&nbsp;</td>
		  				<td>&nbsp;</td>
	  				</tr>
			  	<tr>
				  	<td align="right" ><span ><B><spring:message code="claim.label.serialNumber" /></B></span></td>
				  	<td width="20px">&nbsp;</td>
		  				<td><span>${claimRequestConfirmationForm.asset.serialNumber}</span></td>
	  				</tr>
	  				<tr>
				  	<td align="right" ><span><B><spring:message code="claim.label.machineTypeModel" /></B></span></td>
				  	<td width="20px">&nbsp;</td>
		  				<td><span>${claimRequestConfirmationForm.asset.modelNumber}</span></td>
	  				</tr>
	  				<tr>
				  	<td align="right" ><span ><B><spring:message code="claim.label.productPN/TLI" /></B></span></td>
				  	<td width="20px">&nbsp;</td>
		  				<td><span>${claimRequestConfirmationForm.asset.productTLI}</span></td>
	  			</tr>
	  			<tr>
				  	<td align="right" style="vertical-align:top"><span ><B><spring:message code="claim.label.productAndAssetWarningMsg" /></B></span></td>
				  	<td width="20px" >&nbsp;</td>
		  				<td class="max-width-170px"><span>${claimRequestConfirmationForm.asset.displayWarning}</span></td>
	  			</tr>
	  			<tr>
				  	<td align="right" >&nbsp;</td>
				  	<td width="20px">&nbsp;</td>
		  				<td>&nbsp;</td>
	  			</tr>
	  			<tr>
				  	<td align="right" ><a href="javascript:void(0);" onClick="selectAnotherPrinter();return false;"><spring:message code="claim.label.selectAnotherPrinter" /></a></td>
				  	<td width="20px">&nbsp;</td>
		  				<td >&nbsp;</td>
	  			</tr>
			</table>
	      </div><!-- second-column -->
	      <div class="third-column width-40per-important">
	      	<input type="hidden" id = "newCustomerAccountFlagHidden" name="activity.newCustomerAccountFlag" value="false"></input>
	     	<input type="hidden" id = "customerAccountIdHidden" name="activity.customerAccount.accountId" value="${claimRequestConfirmationForm.asset.account.accountId}"></input>
	     	
	     	<input type="hidden" id = "customerAccountNameHidden" name="activity.customerAccount.accountName" value="${claimRequestConfirmationForm.asset.account.accountName}"></input>
	     	
	     	
	     	<input type="hidden" id = "customerAccountAddressNameHidden" name="asset.installAddress.addressName" value="${claimRequestConfirmationForm.asset.installAddress.addressName}"></input>
	     	<input type="hidden" id = "customerAccountAddressAddressLine1Hidden" name="asset.installAddress.addressLine1" value="${claimRequestConfirmationForm.asset.installAddress.addressLine1}"></input>
	  		<input type="hidden" id = "customerAccountAddressAddressLine2Hidden" name="asset.installAddress.addressLine2" value="${claimRequestConfirmationForm.asset.installAddress.addressLine2}"></input>
	     	<input type="hidden" id = "customerAccountAddressAddressLine3Hidden" name="asset.installAddress.addressLine3" value="${claimRequestConfirmationForm.asset.installAddress.addressLine3}"></input>
	     	<input type="hidden" id = "customerAccountAddressCityHidden" name="asset.installAddress.city" value="${claimRequestConfirmationForm.asset.installAddress.city}"></input>
	     	<input type="hidden" id = "customerAccountAddressStateHidden" name="asset.installAddress.state" value="${claimRequestConfirmationForm.asset.installAddress.state}"></input>
	     	<input type="hidden" id = "customerAccountAddressStateProvinceHidden" name="asset.installAddress.stateProvince" value="${claimRequestConfirmationForm.asset.installAddress.stateProvince}"></input>
	     	<input type="hidden" id = "customerAccountAddressProvinceHidden" name="asset.installAddress.province" value="${claimRequestConfirmationForm.asset.installAddress.province}"></input>
	     	<input type="hidden" id = "customerAccountAddressPostalCodeHidden" name="asset.installAddress.postalCode" value="${claimRequestConfirmationForm.asset.installAddress.postalCode}"></input>
	     	<input type="hidden" id = "customerAccountAddressCountryHidden" name="asset.installAddress.country" value="${claimRequestConfirmationForm.asset.installAddress.country}"></input>
	     	
	     	<input type="hidden" id = "customerAccountAddressIdHidden" name="asset.installAddress.addressId" value="${claimRequestConfirmationForm.asset.installAddress.addressId}"></input>
	     	<input type="hidden" id = "customerAccountAddressAddressLine4Hidden" name="asset.installAddress.addressLine4" value="${claimRequestConfirmationForm.asset.installAddress.addressLine4}"></input>
	     	<input type="hidden" id = "customerAccountAddressNewAddressFlagHidden" name="installAddress.newAddressFlag" value="${claimRequestConfirmationForm.asset.installAddress.newAddressFlag}"></input>
	     	<input type="hidden" id = "customerAccountAddressUserFavoriteHidden" name="installAddress.userFavorite" value="${claimRequestConfirmationForm.asset.installAddress.userFavorite}"></input>

	     	
	     	<input type="hidden" id = "partnerAccountIdHidden" name="activity.partnerAccount.accountId" value="${claimRequestConfirmationForm.asset.partnerAccount.accountId}"></input>
	     	<input type="hidden" id = "partnerAccountNameHidden" name="activity.partnerAccount.accountName" value="${claimRequestConfirmationForm.asset.partnerAccount.accountName}"></input>
	     	<input type="hidden" id = "partnerAccountOrganizationIdHidden" name="activity.partnerAccount.organizationID" value="${claimRequestConfirmationForm.asset.partnerAccount.organizationID}"></input>
	      	<input type="hidden" id = "partnerAccountCreateNewAddressFlagHidden" name="activity.partnerAccount.createShipToAddressFlag" value="${claimRequestConfirmationForm.asset.partnerAccount.createShipToAddressFlag}"></input>
	      	
	      	<input type="hidden" id = "fromDiv"/>
	      	<input type="hidden" id = "PartnerAccountOptionValue" value=""/>
	      	<input type="hidden" id = "OnChangeOfPartnerAccount"/>
	      	
	      	<input type="hidden" id = "CustomerPartnerStatus" /> 
	      	<input type="hidden" id = "partnerAgreementSelectFlag" value="false"/> 
	      	
	      	<input type="hidden" id = "defaultCurrencyHidden" value="${claimRequestConfirmationForm.asset.partnerAccount.defaultCurrency}"/> 
	      	<div id="divCustomerInfo1" style="display:none">
		        <table width="100%">
		        	<c:if test="${condition == '2' || condition == '1'}">
			        	<tr id="claimTypeLASP">
			  				<td align="right"><label class="color-red">*&nbsp;</label><spring:message code="partner.claimType" /></td>
			  				<td width="20px">&nbsp;</td>
			  				<td align="left">
			  					<select id="claimTypeListLASP" name="claimTypeLst" onchange="claimTypeOnChange();" class="width-150px" >
			  					<option value=""></option>
			  					<c:forEach items="${claimType}" var="claimType" varStatus="loop">
			  					     <option value="${claimType.key}">${claimType.value}</option>
			  					</c:forEach>
			  					</select>
			  				</td>
			  			</tr>
		  			</c:if>
				  	<tr>
					  	<td align="right" ><span ><B><spring:message code="claim.label.customerInformation" />:</B></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">&nbsp;</td>
		  				</tr>
				  	<tr>
					  	<td align="right" ><span ><B><spring:message code="claim.label.customerAccount" /></B></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">
	 	  					<span id = "customerAccount1">${claimRequestConfirmationForm.asset.account.accountName}</span>
	 	  				</td>
		  			</tr>
		  			<tr>
					  	<td align="right" valign="top" rowspan="3"><span ><B><spring:message code="claim.label.customerAddress" /></B></span></td>
					  	<td width="20px" rowspan="3">&nbsp;</td>
	 	  				<td align="left" valign="top" rowspan="3">
	 	  					<span id = "customerAddress1">
								<util:addressOutput value="${claimRequestConfirmationForm.asset.installAddress}"/>
	 	  					</span>
	 	  				</td>
		  			</tr>
		  			<!-- Commented for CI CR's 15.1 -->
		  			<%-- <tr></tr>
		  			<tr></tr>
		  			<tr>
					  	<td align="right" >&nbsp;</td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td>&nbsp;</td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><a href="javascript:void(0);"  onclick="showCustomerAccountInfoPop('divCustomerInfo1');"><spring:message code="claim.label.chooseDifferentCustomerAccount"/></a></td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><a href="javascript:void(0);"  onclick="createNewCustomerAccountHiddenShow();return false;"><spring:message code="claim.label.createNewCustomerAccount" /></a></td>
		  			</tr> --%>
		  			<!-- END -->
	 			</table>
	      	</div><!-- divCustomerInfo1 -->
	      	<div id="divCustomerInfo2" style="display:none">
		        <table width="100%">
				  	<tr>
					  	<td align="right" ><span ><B><spring:message code="claim.label.customerInformation" />:</B></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">&nbsp;</td>
		  			</tr>
		  			<c:if test="${condition == '2' || condition == '1'}">
		  			<tr id="claimType">
		  				<td align="right"><label class="color-red">*&nbsp;</label><spring:message code="partner.claimType" /></td>
		  				<td width="20px">&nbsp;</td>
		  				<td align="left">
		  					<select id="claimTypeList" name="claimTypeLst" onchange="claimTypeOnChange();" class="width-150px" >
		  					<option value=""></option>
		  					<c:forEach items="${claimType}" var="claimType" varStatus="loop">
		  					     <option value="${claimType.key}">${claimType.value}</option>
		  					</c:forEach>
		  					</select>
		  				</td>
		  			</tr>
		  			</c:if>
		  			<tr id="selectPartnerAgreementTR">
					  	<td align="right"><label class="color-red">*&nbsp;</label><span ><spring:message code="claim.label.partnerAgreement" /></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">
	 	  				<%--Below width changed for CI 13.10 BRD13-10-07 --%>
	 	  					<select id="selectPartnerAgreement" name="selectPartnerAgreement" onchange="partnerAgreementOnChange();" class="width-209px" onmouseover="this.title=this.options[this.selectedIndex].title">
								<option value=""></option>	
								<c:if test="${condition == '3' ||  condition == '1'}">													
			 	  				<c:forEach items="${claimRequestConfirmationForm.partnerIndirectAccountList}" var="accounts">
			 	  					<c:choose >  
			 	  						<c:when test="${accounts.accountId == claimRequestConfirmationForm.asset.partnerAccount.accountId}">
											<option value="${accounts.accountId}" title="${accounts.accountName}" selected = "selected">${accounts.accountName}</option>
												<script type="text/javascript">
													document.getElementById("partnerAgreementSelectFlag").value = 'true';
												</script>
									 	</c:when>  
										<c:otherwise>  
											<option value="${accounts.accountId}" title="${accounts.accountName}">${accounts.accountName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>	
								</c:if>						
							</select>
						</td>
		  			</tr>
		  			
				  	<tr>
					  	<td align="right"><label class="color-red">*&nbsp;</label><spring:message code="claim.label.customerAccount" /></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">
	 	  					<div id ="divCustomerInfo2InnerHTMLAccount">
	 	  						<div class="inputMedWrapper"><input class="inputMed" type="text" name="newAccountName" id="customerAccount2" /></div>
	 	  					</div>
	 	  				</td>
		  			</tr>
		  			<tr>
					  	<td align="right" valign="top" rowspan="3"><label class="color-red">*&nbsp;</label><span ><spring:message code="claim.label.customerAddress" /></span></td>
					  	<td width="20px" rowspan="3">&nbsp;</td>
	 	  				<td align="left" valign="top" rowspan="3">
	 	  					<div id ="divCustomerInfo2InnerHTMLAddress">
	 	  						<textarea  id="customerAddress2" name="activity.newCustomerAddressCombined" rows="4" cols="30" class="customer-address2"></textarea>
	 	  					</div>
	 	  				</td>
		  			</tr>
		  		<!-- Commented for CI CR's 15.1 -->
		  			<%-- <tr></tr>
		  			<tr></tr>
		  			<tr>
					  	<td align="right" >&nbsp;</td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td>&nbsp;</td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><span id="chooseDifferentCustomerAccountSpan"><a href="javascript:void(0);" onclick="showCustomerAccountInfoPop('divCustomerInfo2');"><spring:message code="claim.label.chooseDifferentCustomerAccount"/></a></span></td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><span id="createNewCustomerAccountSpan"><a href="javascript:void(0);"  onclick="createNewCustomerAccountHiddenShow();return false;"><spring:message code="claim.label.createNewCustomerAccount" /></a></span></td>
		  			</tr> --%>
		  			<!-- END -->
		  		</table>
		  	
	      	</div><!-- divCustomerInfo2 -->
	      	<script type="text/javascript">
	      	var disableAccount2 = '<input class="text" type="text" name="newAccountName" id="customerAccount2" disable="true" />';
			var disableAddress2 = '<textarea  id="customerAddress2" name="activity.newCustomerAddressCombined" rows="4" cols="30" disable="true"></textarea>';
			var inableAccount2 = '<input class="text" type="text" name="newAccountName" id="customerAccount2"/>';
			var inableAddress2 = '<textarea  id="customerAddress2" name="activity.newCustomerAddressCombined" rows="4" cols="30"></textarea>';
 	 
			/* Commented for CI CR's 15.1 */
			/* function createNewCustomerAccountHiddenShow(){
				callOmnitureAction('Create Claim Device Selection - Multiple Claims View', 'Create New Customer Account');
				flag = document.getElementById("CustomerPartnerStatus").value;
				
				document.getElementById("newCustomerAccountFlagHidden").value = "true";
				jQuery('#customerAccountIdHidden').val('');//added
				
				jQuery("#divCustomerInfo1").hide();
				jQuery("#divCustomerInfo2").show();
				
				if(document.getElementById("partnerAgreementSelectFlag").value == 'true'){
					jQuery("#divCustomerInfo2InnerHTMLAccount").html(inableAccount2);
					jQuery("#divCustomerInfo2InnerHTMLAddress").html(inableAddress2);
				}else{
					callDisableAllInputAndButtons();

					jQuery("#divCustomerInfo2InnerHTMLAccount").html(disableAccount2);
					jQuery("#divCustomerInfo2InnerHTMLAddress").html(disableAddress2);
				}

			} */
			/* END */

			function selectAnotherPrinter(){
				callOmnitureAction('Create Claim Device Selection - Multiple Claims View', 'Select Another Printer');
				jConfirm("<spring:message code='claimCreate.alert.selectAnotherPrinter'/>", "", function(answer) {
						if(!answer){
							return;
						}else{
							window.location.href="<portlet:renderURL><portlet:param name='action' value='showGlobalPartnerAssetSectionView' /></portlet:renderURL>";
						}
					});
			}
			
			/* Commented for CI CR's 15.1 */
			 /* function showCustomerAccountInfoPop(fromDiv){
				callOmnitureAction('Create Claim Device Selection - Multiple Claims View', 'Choose A Different Customer Account');
				document.getElementById("fromDiv").value = fromDiv;
				var customerAccountInfoUrl = "${customerAccountInfoUrl}"
				var widthStr = (document.documentElement.clientWidth-1145)/2;
				var heightStr = (document.documentElement.clientHeight-300)/2;
				showOverlayIE6();
				new Liferay.Popup({
					title: "<spring:message code='claim.label.customerAccountInformation'/>",
					position: [widthStr,heightStr], 
					modal: true,
					width: 1145,
					resizable:false, 
					height: 'auto',
					xy: [100, 100],
					url:customerAccountInfoUrl
					});
				bindingClosePopupIE6();
			} */
			/* END */
	
			//PartnerAgreement change alert & change message to shiptoAddress 
			function partnerAgreementOnChange(){
				// onclick
				var firstPartnerAccountValue = document.getElementById("PartnerAccountOptionValue").value;
				// onchange
				var nowPartnerAccountValue = document.getElementById("selectPartnerAgreement").value;

				if(firstPartnerAccountValue == ""){ //from blank to exist, use enable no confirm
					
					callCleanAllInputs();
					callEnableAllInputAndButtons();
					partnerAgreementChange(nowPartnerAccountValue);
					document.getElementById("partnerAgreementSelectFlag").value = 'true';
					jQuery("#divCustomerInfo2InnerHTMLAccount").html(inableAccount2);
					jQuery("#divCustomerInfo2InnerHTMLAddress").html(inableAddress2);
					
				}else if(nowPartnerAccountValue != firstPartnerAccountValue){//exist to exist or blank,use confirm
					
					jConfirm('<spring:message code="clainCreate.alert.partnerAgreementOnChange" />','',function(answer) {
						if(!answer){
							document.getElementById("selectPartnerAgreement").value = firstPartnerAccountValue;
							document.getElementById("PartnerAccountOptionValue").value = firstPartnerAccountValue;
							return;
						}else{
							if(nowPartnerAccountValue == ""){//exist to blank, disable all input box
								callDisableAllInputAndButtons();
							}else{							// exist to exist, clean all and inable
								callCleanAllInputs();
								callEnableAllInputAndButtons();
								partnerAgreementChange(nowPartnerAccountValue);
	
								document.getElementById("partnerAgreementSelectFlag").value = 'true';
								jQuery("#divCustomerInfo2InnerHTMLAccount").html(inableAccount2);
								jQuery("#divCustomerInfo2InnerHTMLAddress").html(inableAddress2);
							}
						}
					});
				}
				document.getElementById("PartnerAccountOptionValue").value = document.getElementById("selectPartnerAgreement").value;
			}
			
			function partnerAgreementOnClick(){
				document.getElementById("PartnerAccountOptionValue").value = document.getElementById("selectPartnerAgreement").value;
			}
	
			function partnerAgreementChange(accountId){
				//update this page's hidden value of partner Account Id
				var el = document.getElementById("selectPartnerAgreement");
				var val = el[el.selectedIndex].text;
				
				document.getElementById("partnerAccountNameHidden").value =  val;
				document.getElementById("partnerAccountIdHidden").value =  document.getElementById("selectPartnerAgreement").value;
				document.getElementById("OnChangeOfPartnerAccount").value =  document.getElementById("selectPartnerAgreement").value;
				
				ajaxRetrieveOrgId(accountId);
				ajaxShiptoAddressbyAccountId(accountId);
				ajaxTechnicianInformation(accountId);
				setDefaultCurrencyAndAddPart(accountId);
			}
			function setDefaultCurrencyAndAddPart(accountId){
				if(accountId != ""){
					<c:forEach items="${claimRequestConfirmationForm.partnerIndirectAccountList}" var="account">
						if("${account.accountId}" == accountId){
							document.getElementById("defaultCurrencyHidden").value = "${account.defaultCurrency}";
							if("${account.orderPartsFlag}" == "true"){
								if(null != document.getElementById("claimTypeList")){
									claimTypeValue = document.getElementById("claimTypeList").value;
									if(claimTypeValue == "Maintenance Kit Install"){
										document.getElementById("partAndToolDiv").style.display="none";
									}
									else{										
										document.getElementById("partAndToolDiv").style.display="block";
									}
									
								}
								else{									
									document.getElementById("partAndToolDiv").style.display="block";
								}
								
								if(exchangeflag==false || exchangeflag=='false'){     // added by ragesree -2098
									document.getElementById("partNumberWrapper").innerHTML='<input class="inputMed text" type="text" id="partNumber"/>';
								}
								partsAndToolsDebriefGrid.setColumnHidden(5,true);
								partsAndToolsDebriefGrid.setColumnHidden(6,true);
								partsAndToolsDebriefGrid.loadXMLString("<rows></rows>");
							}else{
								document.getElementById("partAndToolDiv").style.display="none";
								clearAddress();
							}
							return;
						}
					</c:forEach>
				}
				
			}
	
			function ajaxRetrieveOrgId(accountId){
				var url = '<portlet:resourceURL id="retrieveOrganizationId"/>';
			    url += "&accountId="+accountId;
	
			    doAjax(url, callbackGetOrganizationIdResult, null, "");
			}
	
			function callbackGetOrganizationIdResult(result){
				document.getElementById("partnerAccountOrganizationIdHidden").value = result.data;
				return true;
			}
			function callDisableAllInputAndButtons(){
				disableAllInputAndButtons();
			}
			
			function callCleanAllInputs(){
				cleanAllInputs();
			}
			
			function callEnableAllInputAndButtons(){
				enableAllInputAndButtons();
			}
	
			
			function ajaxTechnicianInformation(accountId){
				//var result = // result from Ajax;
				var url = '<portlet:resourceURL id="setTechnicianInformation"/>';
				url +="&accountId=" + accountId;
				document.getElementById("technicianLoading").style.display = "block";
				doAjax(url, callbackGetTechInfoAccountList, null, null);
			}
	
			function callbackGetTechInfoAccountList(result){
				var mapList = result.data;
				//Alex0, zhou/1-P00910}Alex1, zhou/1-P3YKE1}Alex2, zhou/1-P00912}Alex3, zhou/1-P00913}
				//put the result value into parent page technician choose.
				var valueKey = mapList.split("}");
				var selectObject = document.getElementById('technicianChoose');
				selectObject.options.length=0;
				//clearn all then added
				for (i=0;i<valueKey.length;i++){
					var textValue = valueKey[i].split("/");
					selectObject.options.add(new Option(textValue[0],valueKey[i]));
				}
				selectObject.remove(selectObject.length-1);
				selectObject.options.add(new Option('<spring:message code="claim.label.dropdownlist.optionvalue.technician.other"/>','other'));
				
				document.getElementById("technicianLoading").style.display = "none";
				return true;
			}
	
			function ajaxShiptoAddressbyAccountId(accountId){
				//put the result into parent page
				var url = '<portlet:resourceURL id="setShiptoAddress"/>';
				url +="&accountId=" + accountId;
				doAjax(url, callbackGetShiptoAddress, null, null);
			}
	
			function callbackGetShiptoAddress(result){
				var mapList = result.data;
				if(mapList == ""){
					//when this situation, disable and clean function have been called. so here do nothing
				}else{
					
					var valueKey = mapList.split("/");
						document.getElementById("partnerAccountCreateNewAddressFlagHidden").value = valueKey[0];
					if(valueKey[1]!=""&&valueKey[1]!="null"){
						//addPartnerAddressElement(ai, an, al1, al2, al3, city, state, province, stateProvince, country, zip, currentFavStatus)
					var currentfav="";	
						addPartnerAddressElement(valueKey[1],valueKey[2],currentfav,valueKey[3],valueKey[4],valueKey[5],valueKey[6],valueKey[7],valueKey[8],valueKey[9],valueKey[10],valueKey[11],valueKey[12],valueKey[13]);
						if(typeof setCreateNewAddressButton != "undefined"){
							setCreateNewAddressButton(document.getElementById("partnerAccountCreateNewAddressFlagHidden").value);	
						}
						
					}else{
						if(exchangeflag==false || exchangeflag=='false'){  //added by ragesree -2098
							chooseADifferentAddress();
						}
					}
				}
				return true;
			} 

			</script>
			
			<script type="text/javascript">
     		// step 1 inorder to get asset account and partner account status
			jQuery(document).ready(function(){

				var claimTypeValue="";
		     	var partnerAccount = '${claimRequestConfirmationForm.asset.partnerAccount}';
		      	var customerAccount = '${claimRequestConfirmationForm.asset.account}';
				if(customerAccount != ''){ // customer Account is not null
					if(partnerAccount !=''){ //	 partner Account is not null
						// customer info
						jQuery("#CustomerPartnerStatus").val("CustomerPartner");
					}else{
						// show drop down and customer info
						jQuery("#CustomerPartnerStatus").val("Customer");
					}
				}else{
					if(partnerAccount !=''){ // partner Account is not null
						// show customer input
						jQuery("#CustomerPartnerStatus").val("Partner");
					}else{
						// show drop down and customer input
						jQuery("#CustomerPartnerStatus").val("Nothing");
					}
				}
				
				if(${condition}=="1"){
					if(customerAccount == '' || partnerAccount ==''){
						jQuery("#selectPartnerAgreement").val("${accountId}");
						document.getElementById("selectPartnerAgreement").disabled=true;										
						var accountId = "${accountId}";
						ajaxRetrieveOrgId(accountId);
						ajaxShiptoAddressbyAccountId(accountId);
						ajaxTechnicianInformation(accountId);
						setDefaultCurrencyAndAddPart(accountId);
						//callCleanAllInputs();
						//callEnableAllInputAndButtons();
					}
				}
				
		    	// step 2 display condition 
				var flag = document.getElementById("CustomerPartnerStatus").value;
				if(flag == 'CustomerPartner'){
					jQuery("#divCustomerInfo1").show();
					jQuery("#divCustomerInfo2").hide();
				}
				if(flag == 'Nothing' || flag == 'Customer' || flag == 'Partner'){
					jQuery("#divCustomerInfo1").hide();
					jQuery("#divCustomerInfo2").show();

					jQuery("#newCustomerAccountFlagHidden").val("true");

					if(flag == 'Nothing'||flag == 'Customer'){
						callDisableAllInputAndButtons();
						jQuery("#divCustomerInfo2InnerHTMLAccount").html(disableAccount2);
						jQuery("#divCustomerInfo2InnerHTMLAddress").html(disableAddress2);
					}else{
						//the partner agreement have been selected	
						if(document.getElementById("partnerAgreementSelectFlag").value == 'true'){
							jQuery("#divCustomerInfo2InnerHTMLAccount").html(inableAccount2);
							jQuery("#divCustomerInfo2InnerHTMLAddress").html(inableAddress2);
						}else{
							jQuery("#divCustomerInfo2InnerHTMLAccount").html(disableAccount2);
							jQuery("#divCustomerInfo2InnerHTMLAddress").html(disableAddress2);
							
							callDisableAllInputAndButtons();
						}
					}
				}
			});
     		
     		function claimTypeOnChange(){
     			var customerPartnerStatus = document.getElementById("CustomerPartnerStatus").value;
     			if(customerPartnerStatus == 'CustomerPartner'){
     				var claimTypeValue = document.getElementById("claimTypeListLASP").value;
     				  if(claimTypeValue == "Maintenance Kit Install"){
     					 document.getElementById("partAndToolDiv").style.display="none";
     				   }
     				  else{
     					 document.getElementById("partAndToolDiv").style.display="block";
     				  }
     				
     			}
     			else{
     			 var agreementList = [];
     			<c:forEach items="${claimRequestConfirmationForm.partnerIndirectAccountList}" var="accounts">
     			agreementList.push({"displayValue":"${accounts.accountName}",
     								"value":"${accounts.accountId}",
     								"flag":"${accounts.maintenanceKit}"});
     			</c:forEach>
     		    var claimTypeValue = document.getElementById("claimTypeList").value;  
     		   document.getElementById("partAndToolDiv").style.display="block";
     		   if(claimTypeValue == "Maintenance Kit Install"){
     			  document.getElementById("partAndToolDiv").style.display="none";
     		   }
     		   if(${condition != '1'}){
     			var optionValue = "<option value=\"\"></option>";
       			if(claimTypeValue == "Maintenance Kit Install"){     				
       				for(var i =0; i<agreementList.length;i++){         				
       					if(agreementList[i].flag == "true"){
       						optionValue = optionValue+"<option value=\""+agreementList[i].value+"\">"+agreementList[i].displayValue+"</option>";
       					}
       					jQuery('#selectPartnerAgreement').html(optionValue);
           			}
       			}
       			else{
       				for(var i =0; i<agreementList.length;i++){     						
       					optionValue = optionValue+"<option value=\""+agreementList[i].value+"\">"+agreementList[i].displayValue+"</option>";     					
       					jQuery('#selectPartnerAgreement').html(optionValue);
           			}
       			}
     		   }
     			
     			//var selectPartnerAgreement = document.getElementById('selectPartnerAgreement');
     			//selectPartnerAgreement.options[selectPartnerAgreement.options.length] = new Option('Text 1', 'Value1');
     			}
     		}
			</script>
			
	      </div><!-- third-column -->
	    </div><!-- columns -->
    </div>
  </div>
  <div class="portlet-footer">
	<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
  </div><!-- portlet-footer -->
</div>
