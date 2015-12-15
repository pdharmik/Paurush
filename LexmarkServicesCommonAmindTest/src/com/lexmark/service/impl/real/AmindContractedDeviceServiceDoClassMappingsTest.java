package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.compareFields;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

import com.lexmark.service.impl.real.domain.AccountAssetDetailDo;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, Mar 26, 2012
 */
public class AmindContractedDeviceServiceDoClassMappingsTest {

    @Test
    public void test() {
        assertEquals((Object) null, null);
    }

    @Test
    public void testMappings() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-contractedasset-mapping.xml"); 
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-assetFavorites-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-accountassetdetaildo-mapping.xml");
    }

    @Test
    public void accountAssetDetailMappings() throws Exception {
        FieldEntry[] fieldEntries = {
                comment("Input  Data"),
                xmltag("id", "Id"),
                xmltag("assetId", "Asset Id"),
                xmltag("contactId", "Primary Contact Id"),

                comment("Result Data to Populate"),
                // xmltag("assetId", "Asset Id"), // exists in Input Data
                xmltag("serialNumber", "Serial Number"),
                xmltag("assetTag", "Owner Asset Number"),
                xmltag("modelNumber", "LXK C MTM Name"),
                xmltag("ipAddress", "IP Address"),
                xmltag("hostName", "Host Name"),
                xmltag("deviceTagCustomer", "Device Tag Customer"),
                xmltag("productTLI", "Product Name"),
                xmltag("productLine", "LXK C Assignment Product Line"),
                xmltag("devicePhase", "Device Phase"),
                // xmltag("deviceTag", "Device Tag Customer"), // already exists
                xmltag("macAddress", "MAC Address"),
                xmltag("customerCostCode", "Asset Cost Center"), // red
                xmltag("installDate", "Install Date"), // red
                xmltag("hierarchyNode", ""), // red // Newly added
                xmltag("physicalLocation1", "Physical Location 1"),
                xmltag("physicalLocation2", "Physical Location 2"),
                xmltag("physicalLocation3", "Physical Location 3"),
                // xmltag("reading", ""), // todo: to check
                // xmltag("timestamp", ""), // todo: to check

                comment("GenericAddress installAddress"),
                xmltag("addressName", "Install Address Name"),
                xmltag("address1", "LXK UP Install Address Line 1"),
                xmltag("address2", "Install Address Line 2"),
                xmltag("address3", "Install Address Line 3"),
                xmltag("installCity", "Install City"),
                xmltag("installState", "Install State"),
                xmltag("installProvince", "Install Province"),
                xmltag("installPostalCode", "Install Postal Code"),
                xmltag("installCountry", "Install Country"),
                xmltag("addressId", "Install Address ID"),
                xmltag("storeFrontName", "LXK MPS Store Front Name"), // red //
                                                                      // New
                                                                      // Field
                                                                      // in
                                                                      // Siebel

                comment("GenericAddress consumableAddress"),
                xmltag("consumableaddressName", ""),
                xmltag("consumableAddress1", ""),
                xmltag("consumableAddress2", ""),
                xmltag("consumableAddress3", ""),
                xmltag("consumableCity", ""),
                xmltag("consumableState", ""),
                xmltag("consumablePostalCode", ""),
                xmltag("consumableCountry", ""),
                xmltag("consumableAddressId", ""),
                xmltag("consumablePhysicalLocation1", ""),
                xmltag("consumablePhysicalLocation2", ""),
                xmltag("consumablePhysicalLocation3", ""),

                comment("AccountContact  assetContact"),
                xmltag("contactFirstName", "LXK SW Primary Contact First Name"),
                xmltag("contactLastName", "LXK SW Primary Contact Last Name"),
                xmltag("contactEmailAddress", "LXK SW Primary Contact Email"),
                xmltag("contactWorkPhone", "LXK SW Primary Contact Work Phone"),
                // xmltag("contactId", "Primary Contact Id"), // exists in Input
                // Data

                comment("AccountContact   ConsumableContact"),
                xmltag("consumableContactFirstName", ""),
                xmltag("consumableContactMiddleName", ""),
                xmltag("consumableContactLastName", ""),
                xmltag("consumableContactEmailAddress", ""),
                xmltag("consumableContactWorkPhone", ""),
                xmltag("consumableContactAlternatePhone", ""),

                comment("Account account"),
                xmltag("accountId", "Owner Account Id"),
                xmltag("accountName", "Account Name"),

                xmltag("", ""),
                xmltag("", "")

        };
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }

        compareFields(AccountAssetDetailDo.class, fieldEntries);

    }

    @Test
    public void reflectionMethodCall() throws Exception {
        AccountAssetDetailDo obj = new AccountAssetDetailDo();
        Class[] methArgsTypes = {};
        Object[] methArgs = {};
        // String methName = "getIpGateway";
        String methName = "getId";
        Method meth = obj.getClass().getMethod(methName, methArgsTypes);
        System.out.printf("%s = %s \n", methName, meth.invoke(obj, methArgs));

    }
}
