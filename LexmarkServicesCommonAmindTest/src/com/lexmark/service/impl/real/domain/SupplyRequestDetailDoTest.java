package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;

import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;
import com.lexmark.util.LangUtil;


/**
 * 
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTest
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-02
 */
@SuppressWarnings("unused")
public class SupplyRequestDetailDoTest {
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-supplyrequestdetail-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-supplyrequestdetailactivity-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-supplyrequestdetailattachment-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-supplyrequestdetailpendingshipment-mapping.xml");
        
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-supplyrequestdetailshipment-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-supplyrequestdetailshipmentitem-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-supplyrequestdetailshipmentitemorderentry-mapping.xml");
    } 
    
    @Test
    public void testMappings() throws Exception {
        FieldEntry[] fieldEntries = {
                xmltag("id",  "Id"),
                xmltag("serviceRequestNumber",  "SR Number"),
                xmltag("serviceRequestDate",  "LXK SW SR Created Date"),
                xmltag("serialNumber",  "Serial Number"),
                xmltag("assetTag",  "Owner Asset Number"),
                xmltag("ipAddress",  "LXK SW IP Address"),
                xmltag("customerReferenceId",  "LXK R SR Customer Ref Number"),
                xmltag("costCenter",  "LXK MPS Cost Center"),
                xmltag("status",  "Status"), // comment: LXK SW Web SR Status 
                xmltag("description",  "LXK MPS Description"),
                
                xmltag("area",  "LXK MPS SR Area"), 
                xmltag("subArea",  "LXK MPS SR Sub Area"),
                xmltag("requestedEffectiveDate",  "LXK MPS Requested Effective Date"),
                
                comment("GenericAddress serviceAddress"),
                xmltag("serviceAddressLine1",  "LXK UP Service Street Address"),
                xmltag("serviceAddressLine2",  "LXK R Service Street Address 2"),
                xmltag("serviceAddressLine3",  "LXK R Service Street Address 3"),
                xmltag("serviceCity",  "Service City"),
                xmltag("serviceState",  "Service State"),
                xmltag("servicePostalCode",  "Service Postal Code"),
                xmltag("serviceProvince",  "LXK SW Service Province"),
                xmltag("serviceCountry",  "Service Country"),
                xmltag("serviceAddressName",  "LXK UP Service Address Name"),
                
                comment("AccountContact RequestorContact"),
                xmltag("",  "LXK MPS Requestor First Name"),
                xmltag("",  "LXK MPS Requestor Last Name"),
                xmltag("",  "LXK R Contact Email"),
                xmltag("",  "Contact Business Phone"),
                
                comment("AccountContact SecondaryContact"),
                xmltag("secondaryContactFirstName",  "LXK SD SR Alternate Contact First Name"),
                xmltag("secondaryContactLastName",  "LXK SD SR Alternate Contact Last Name"),
                xmltag("secondaryContactEmailAddress",  "LXK SW SR Alternate Contact Email Address"),
                xmltag("secondaryContactWorkPhone",  "LXK SW SR Alternate Contact Work Phone"),
                
                comment("AccountContact PrimaryContact"),
                xmltag("primaryContactFirstName",  "LXK R SR Contact First Name"),
                xmltag("primaryContactLastName",  "LXK R SR Contact Last Name"),
                xmltag("primaryContactEmailAddress",  "LXK SW SR Primary Contact Email Address"),
                xmltag("primaryContactWorkPhone",  "LXK SW SR Primary Contact Work Phone"),
                
                comment("List of ServiceRequestActivity"),
                xmltag("actions",  "com.lexmark.service.impl.real.domain.SupplyRequestDetailActivityDo"),  // TODO(Viktor)
                
                comment("pendingShipments"), 
                xmltag("pendingShipments", "com.lexmark.service.impl.real.domain.SupplyRequestDetailPendingShipmentDo"),

                comment("shippedItems"),
                xmltag("shippedItems", "com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentDo"),
                
                comment("List of Attachmemt"),
                xmltag("attachments",  "com.lexmark.service.impl.real.domain.SupplyRequestDetailAttachmentDo"),
                
               // xmltag("",  ""),
             };
             for (FieldEntry fe : fieldEntries) {
                 System.out.println(fe.toXmlTag());
             }        
             // todo check(fieldEntries)
    }
    
    
    @Test
    public void query1() throws Exception {
        List<SupplyRequestDetailDo> result = MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, 10, 0);
    }
    
    @Test
    public void sampleSiebelQuery() throws Exception {
        List<SupplyRequestDetailDo> result = MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, 10, 0);
        
        System.out.println("printing pendingShipments if have");
        for (SupplyRequestDetailDo detailDo : LangUtil.notNull(result)) {
           for (SupplyRequestDetailOrderItemDo orderItem : LangUtil.notNull(detailDo.getOrderItems())) {
               for (SupplyRequestDetailPendingShipmentDo pendingShipment : LangUtil.notNull(orderItem.getPendingShipments())) {
                   System.out.println(MiscTest.str(pendingShipment));
               }
           }
        }
    }
    
    @Test
    public void testPendingShipmentItems() throws Exception {
        List<SupplyRequestDetailDo> result = MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, 10, 0);
        
        System.out.println("printing pendingShipmentItemss if have");
        for (SupplyRequestDetailDo detailDo : LangUtil.notNull(result)) {
           for (SupplyRequestDetailOrderItemDo orderItem : LangUtil.notNull(detailDo.getOrderItems())) {
               for (SupplyRequestDetailPendingShipmentDo pendingShipment : LangUtil.notNull(orderItem.getPendingShipments())) {
                   MiscTest.print("[pendingShipmentItems] ",  pendingShipment.getPendingShipmentItems());
               }
           }
        }
    }
    
    @Test
    public void testAttachmentFields() throws Exception {
        MiscTest.showStructure(com.lexmark.domain.Attachment.class);
    }
    
    @Test
    public void printICFields() throws Exception {
        MiscTest.printIcFieldDefinitions("LXK MPS Service Request Detail - Service Web", "LXK SW Service Request Detail - Service Web");
    }
    
}
