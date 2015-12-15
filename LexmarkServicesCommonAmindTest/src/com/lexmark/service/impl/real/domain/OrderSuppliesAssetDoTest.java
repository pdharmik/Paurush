package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;

/**
 * The mapping file: do-ordersuppliesassetdo-mapping.xml.
 * 
 * 
 * @see com.lexmark.service.impl.real.domain.OrderSuppliesAssetFavoriteDoTest
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-05-07
 */
public class OrderSuppliesAssetDoTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-ordersuppliesassetdo-mapping.xml");
    }

    /**
     * These mappings are from MPS_OrderManagement_Consumables_Assets_Orders_05_07_12.docx
     *
     */ 
    @Test
    public void testMappings() throws Exception {
        FieldEntry[] fieldEntries = {
                xmltag("id", "Id"),

                xmltag("mdmLevel1AccountId", "LXK Account Global DUNS Number"),
                xmltag("mdmLevel2AccountId", "LXK Account Domestic DUNS Number"),
                xmltag("mdmLevel3AccountId", "LXM MDM Legal Entity ID #"),
                xmltag("mdmLevel4AccountId", "LXM MDM Account #"),
                xmltag("mdmLevel5AccountId", "Owner Account Id"),

                xmltag("contactId", "Contact Id"),
                // xmltag("assetFavFlag", "LXK SW Asset Favorite Flag"),

                xmltag("ownerAccountId", "Owner Account Id"),
                xmltag("parentChain", "CHL Parent Chain"), 
                xmltag("", ""),

                // xmltag("", ""),

                xmltag("assetId", "Asset Id"),
                xmltag("serialNumber", "Serial Number"),
                xmltag("assetTag", "Owner Asset Number"),
                xmltag("machineTypeModel", "LXK C MTM Name"),
                xmltag("ipAddress", "IP Address"),
                xmltag("agreementType", "LXK SW Agreement Type"),
                xmltag("hostName", "Host Name"),
                xmltag("deviceTagCustomer", "Device Tag Customer"),
                xmltag("productTLI", "Product Name"),
                xmltag("productLine", "LXK C Assignment Product Line"),
                xmltag("devicePhase", "Device Phase"),
                xmltag("macAddress", "MAC Address"),
                // xmltag("assetFavFlag", "LXK SW Asset Favorite Flag"),
                // xmltag("", ""),

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
                // xmltag("", ""),
                
                // in MPS_OrderManagement_Consumables_Assets_Orders_05_07_12.docx Siebel values are empty
                comment("AccountContact assetContact"),
                xmltag("contactFirstName", "LXK SW Primary Contact First Name"),
                xmltag("contactLastName", "LXK SW Primary Contact Last Name"),
                xmltag("contactEmailAddress", "LXK SW Primary Contact Email"),
                xmltag("contactWorkPhone", "LXK SW Primary Contact Work Phone"),
//                xmltag("contactId", "Primary Contact Id"),
                
                comment("misc"),
                xmltag("operationalStatus", "Operating Status"),
                xmltag("agreementId", ""),  // TODO(Viktor) what value ?
                xmltag("mdmLevel", "LXK SW MDM Account Level"),                 
                xmltag("consumableAssetFlag", "LXK MPS Consumable Flag"),                 
                xmltag("", "")
        };
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
    
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(OrderSuppliesAssetDo.class, "", 5);
    }
}
