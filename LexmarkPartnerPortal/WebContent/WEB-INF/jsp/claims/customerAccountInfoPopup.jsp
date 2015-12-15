<%@ include file="/WEB-INF/jsp/include.jsp"%>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap" >
	<div class="content">
	 	<div class="portlet-wrap">
	         <div class="portlet-header">
	         	<h3><spring:message code="claim.label.customerAccountInformation" /></h3>
	         </div>
             <div class="portlet-wrap-inner">
                 <div id="customerAccountInfoGridbox" class="customer-account-info-gridbox"></div>
                 <div id="customerLoadingNotification" class="gridLoading" >
                     <!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif"/>
                 </div>
                 <div>
					<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
				 </div>
             </div>
			 <div class="float-right">
				<a href="###" class="button" onclick="cancelSelectCustomerAccount(this);"><spring:message code="button.cancel"/></a>
			 </div>
             <div class="portlet-footer"></div>
	     </div>
	  </div><!-- content -->
</div><!-- main-wrap -->

<script type="text/javascript">
	function customerAccountInfo(accountId,accountName,addressName,addressLine1,addressLine2,addressLine3,
									city,state,stateProvince,province,postalCode,country,thisElement,
									addressId,addressLine4,newAddressFlag,userFavorite){
		callOmnitureAction('Create Claim Device Selection - Customer Account Popup', 'Select A Customer Account');
		var v_accountId = accountId;
		var v_accountName = accountName;

		var v_CustomerAddressLabel =  addressLine1 + '<br>';
		if(addressLine2 == ''){
		}else{
			v_CustomerAddressLabel = v_CustomerAddressLabel + addressLine2 + '<br>';
		} 
		if(addressLine3 == ''){
		}else{
			v_CustomerAddressLabel = v_CustomerAddressLabel + addressLine3 + '<br>';
		} 
		v_CustomerAddressLabel = v_CustomerAddressLabel + city + ',&nbsp;';
		if(state == ''){
			v_CustomerAddressLabel = v_CustomerAddressLabel + province + ',&nbsp;';
		}else{
			v_CustomerAddressLabel = v_CustomerAddressLabel + state + ',&nbsp;';
		}
		v_CustomerAddressLabel = v_CustomerAddressLabel + country + ',&nbsp;' + postalCode ;	

		
		var fromDiv = jQuery('#fromDiv', window.parent.document).val();

		jQuery('#customerAccountIdHidden', window.parent.document).val(v_accountId);
		jQuery('#customerAccountNameHidden', window.parent.document).val(accountName);
		jQuery('#customerAccountAddressNameHidden', window.parent.document).val(addressName);
		jQuery('#customerAccountAddressAddressLine1Hidden', window.parent.document).val(addressLine1);
		jQuery('#customerAccountAddressAddressLine2Hidden', window.parent.document).val(addressLine2);
		jQuery('#customerAccountAddressAddressLine3Hidden', window.parent.document).val(addressLine3);
		jQuery('#customerAccountAddressCityHidden', window.parent.document).val(city);
		jQuery('#customerAccountAddressStateHidden', window.parent.document).val(state);
		jQuery('#customerAccountAddressStateProvinceHidden', window.parent.document).val(stateProvince);
		jQuery('#customerAccountAddressProvinceHidden', window.parent.document).val(province);
		jQuery('#customerAccountAddressPostalCodeHidden', window.parent.document).val(postalCode);
		jQuery('#customerAccountAddressCountryHidden', window.parent.document).val(country);

		jQuery('#customerAccountAddressIdHidden', window.parent.document).val(addressId);
		jQuery('#customerAccountAddressAddressLine4Hidden', window.parent.document).val(addressLine4);
		jQuery('#customerAccountAddressNewAddressFlagHidden', window.parent.document).val(newAddressFlag);
		jQuery('#customerAccountAddressUserFavoriteHidden', window.parent.document).val(userFavorite);

		
		
		if(fromDiv == 'divCustomerInfo1'){
			jQuery("#customerAccount1",window.parent.document).html(v_accountName)
			jQuery("#customerAddress1",window.parent.document).html(v_CustomerAddressLabel);
		}else if(fromDiv == 'divCustomerInfo2'){
			jQuery("#divCustomerInfo2InnerHTMLAccount",window.parent.document).html(v_accountName)
			jQuery("#divCustomerInfo2InnerHTMLAddress",window.parent.document).html(v_CustomerAddressLabel);
		}

		jQuery('#newCustomerAccountFlagHidden', window.parent.document).val('false');
		hideOverlayIE6();

		Liferay.Popup.close(thisElement);
	}

	function cancelSelectCustomerAccount(thisElement){
		hideOverlayIE6();
		Liferay.Popup.close(thisElement);
	}
</script>
<script type="text/javascript">
	/*********************************************************************************
	******************************Customer Account Information grid script***********************
	**********************************************************************************/
	var customerAccountInfoUrl = "<portlet:resourceURL id="getCustomerAccountInfoList"></portlet:resourceURL>";
	customerAccountInfoGrid = new dhtmlXGridObject('customerAccountInfoGridbox');
	customerAccountInfoGrid.setImagePath("<html:imagesPath/>gridImgs/");
	customerAccountInfoGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.customerAccountInformation"/>',9));
	customerAccountInfoGrid.setInitWidths("15,200,250,100,80,100,100,100,100");
	customerAccountInfoGrid.setColAlign("left,left,left,left,left,left,left,left,left");
	customerAccountInfoGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro");
	customerAccountInfoGrid.setColSorting("na,str,str,str,str,str,str,str,na");
	customerAccountInfoGrid.a_direction = "ASCENDING";
	customerAccountInfoGrid.enableAutoHeight(true);
	customerAccountInfoGrid.enableMultiline(true);
	customerAccountInfoGrid.setSizes();
	customerAccountInfoGrid.init();
	customerAccountInfoGrid.prftInit();
	customerAccountInfoGrid.enablePaging(true, 5, 6, "pagingArea", true, "infoArea");
	customerAccountInfoGrid.setPagingSkin("bricks");
	customerAccountInfoGrid.setColumnHidden(0,true);
	customerAccountInfoGrid.setSkin("light");
	customerAccountInfoGrid.loadXML(customerAccountInfoUrl);
	customerAccountInfoGrid.attachEvent("onXLS", function() {
		document.getElementById('customerLoadingNotification').style.display = 'block';
	});
	customerAccountInfoGrid.attachEvent("onXLE", function() {
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
		customerAccountInfoGrid.setSortImgState(true, 2, "asc");
		document.getElementById('customerLoadingNotification').style.display = 'none';
	});
	customerAccountInfoGrid.attachEvent("onMouseOver", function(id,ind) {
		return false;
	});
	
</script>