package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;

import junit.framework.TestCase;

import org.junit.Test;


/**
 * 
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-23
 */
public class ConsumableAssetLocationDoTest extends TestCase {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-consumableassetlocationdo-mapping.xml");
    }

    @Test
    public void testMappings() throws Exception {
        FieldEntry[] fieldEntries = {
           xmltag("id",  "Id"),
           
           comment("Input  Data"),
           xmltag("mdmLevel1AccountId", "LXK Account Global DUNS Number"),
           xmltag("mdmLevel2AccountId", "LXK Account Domestic DUNS Number"),
           xmltag("mdmLevel3AccountId", "LXM MDM Legal Entity ID #"),
           xmltag("mdmLevel4AccountId", "LXM MDM Account #"),
           xmltag("mdmLevel5AccountId", "LXK MPS Owner Account Id"),
           xmltag("chlNodeId", "LXK MPS CHL ID"),
           xmltag("chlParentChain", "LXK MPS CHL Parent Chain"),

           comment("Result Data to Populate"),
           comment("GenericAddress"), //aMind mapping from Consumable Assets - Asset List
           xmltag("addressName", "Address Name"),
           xmltag("addressLine1", "Street Address"),
           xmltag("addressLine2", "Street Address 2"),
           xmltag("addressLine3", ""),
           xmltag("city", "City"),
           xmltag("state", "State"),
           xmltag("province", "Province"),
           xmltag("postalCode", "Postal Code"),
           xmltag("country", "Country"),
           xmltag("", "")

        };
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
}
