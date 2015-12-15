package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;
import com.lexmark.util.LangUtil;

/**
 * @see com.lexmark.service.impl.real.AmindPartnerOrderServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-22
 */
public class PartnerRequestOrderDoTest {

    FieldEntry[] fieldEntries;

    /**
     * These mappings are from Vendor_Management_OrderManagement_070_10_12.docx
     * 
     */
    @Before
    public void setUp() throws Exception {
        fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),

                xmltag("mdmLevel1AccountId", "LXK MPS Global Account #"),
                xmltag("mdmLevel2AccountId", "LXK MPS Domestic Account #"),
                xmltag("mdmLevel3AccountId", "LXK MPS Legal Account #"),
                xmltag("mdmLevel4AccountId", "LXK MPS MDM Account #"),
                xmltag("mdmLevel5AccountId", "LXK MPS Account Id"),

                xmltag("mdmLevel", "LXK MPS Account Level"),
                xmltag("vendorId", "LXK MPS Account Id"),
                xmltag("status", "Status"),  
                xmltag("mpsStatus", "LXK MPS Status"),  
                xmltag("contactMethod", "LXK SD LP Contact Method"),

                xmltag("orderNumber", "Order Number"),
                xmltag("createdDate", "Created"),  // TODO(Viktor)  new , discuss
                xmltag("requestedDeliveryDate", "LXK MPS Requested Delivery date"),
                xmltag("requestNumber", "SR Number"),
                xmltag("customerAccount", "Account Name"),

                comment("AccountContact CustomerContact"),
                xmltag("firstName", "LXK MPS Requestor First Name"),
                xmltag("lastName", "LXK MPS Requestor Last Name"),
                xmltag("phoneNumber", "LXK MPS Requestor Work Phone"),
                xmltag("emailAddress", "LXK MPS Requestor Email Address"),

                xmltag("responseMetric", "LXK MPS Response Metric"),
                xmltag("customerRequestedResponseDate", "LXK MPS Requested Delivery date"),
                xmltag("serviceProviderReferenceNumber", "LXK MPS Customer Ref Number"),
                xmltag("serviceRequestNumber", "SR Number"),

                comment("Asset asset"),
                xmltag("serialNumber", "LXK MPS Serial Number"),
                xmltag("machineTypeModel", "LXK MPS Machine Type Model"),
                xmltag("productLine", "LXK MPS Product Line"),

                comment("GenericAddress CustomerAddress"),
                xmltag("addressId", "LXK MPS Service Address Id"),
                xmltag("addressName", "LXK MPS Service Address1"),
                xmltag("city", "LXK MPS Service City"),
                xmltag("state", "LXK MPS Service State"),
                xmltag("postalCode", "LXK MPS Service Postal Code"),
                xmltag("province", "LXK MPS Service Province"),

                comment("PartLineItem.PartList"),
                xmltag("partNumber", "LXK MPS Part Number"),
                xmltag("description", "LXK MPS Product Description"),
                xmltag("partType", "LXK MPS Product Type"),
//                xmltag("shippedQuantity", "NA"),
//                xmltag("eta", "NA"),
                xmltag("orderedQuantity", "Quantity"),
//                xmltag("status", "Status"),   // already defined above
//                xmltag("vendorId", "LXK MPS Account Id"), // already defined above
//                xmltag("contactMethod", "LXK SD LP Contact Method"), // defined above
                xmltag("orderLineItems", "com.lexmark.service.impl.real.domain.OrderLineItemDo"),
                xmltag("", ""),

        };
    }

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-partnerrequestorder-mapping.xml");
    }

    @Test
    public void genXmlMappings() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
    
    @Test
    public void genJavaFileds() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toJavaFieldDeclaration());
        }
    }

    @Test
    public void queryPartnerRequestOrderDo() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, 5, 0);
    }
    
    
    @Test
    public void queryPartnerRequestOrderDo_OrderLineItemDo() throws Exception {
        List<PartnerRequestOrderDo> orderDos = MiscTest.querySiebel(PartnerRequestOrderDo.class, "" ,5, 0);
        for (PartnerRequestOrderDo orderDo : LangUtil.notNull(orderDos)) {
            MiscTest.print(orderDo.getOrderLineItems());
        }
    }
    
    @Test
    public void testSearchSpec() throws Exception {
        String searchSpec = "EXISTS([LXK MPS Global Account #] = '4537264' AND [LXK MPS Account Level] = 'Siebel' and [LXK MPS Status] = 'Assigned to LP' and LXK SD LP Contact Method = 'Portal')";
        List<?> lst = MiscTest.querySiebel(PartnerRequestOrderDo.class, searchSpec, 5, 1);
        MiscTest.print(lst);
    }
    
    @Test
    public void queryPartnerRequestOrderDoWithVendorId() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "EXISTS([vendorId] <> '')", 5);
    }
    
    @Test
    public void queryForAddressLines() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "[addressLine1] <> '' AND [addressLine2] <> '' AND [addressLine3] <> ''", 5);
    } 
}
