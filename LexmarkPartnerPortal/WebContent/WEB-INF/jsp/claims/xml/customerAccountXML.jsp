<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<rows>
    <c:forEach var="Account" items="${customerAccountList}" varStatus="rowCounter">
    <row>
        <cell><![CDATA[${Account.accountId}]]></cell>
        <cell><![CDATA[${Account.accountName}]]></cell>
        <cell><![CDATA[${Account.address.addressLine1}]]></cell>
        <cell><![CDATA[${Account.address.city}]]></cell>
        <cell><![CDATA[${Account.address.state}]]></cell>
        <cell><![CDATA[${Account.address.province}]]></cell>
        <cell><![CDATA[${Account.address.postalCode}]]></cell>
        <cell><![CDATA[${Account.address.country}]]></cell>
        <cell><![CDATA[ <input name="btn_select" 
        				class="button"  
        				type="button" 
        				onclick="customerAccountInfo(
						'${Account.accountId}','${Account.accountName}','${Account.address.addressName}',
						'${Account.address.addressLine1}','${Account.address.addressLine2}',
						'${Account.address.addressLine3}','${Account.address.city}',
						'${Account.address.state}','${Account.address.stateProvince}','${Account.address.province}',
						'${Account.address.postalCode}','${Account.address.country}',
						this,
						'${Account.address.addressId}','${Account.address.addressLine4}',
						'${Account.address.newAddressFlag}','${Account.address.userFavorite}');" value="select"/>]]>
		</cell>
    </row>
	</c:forEach>
</rows>