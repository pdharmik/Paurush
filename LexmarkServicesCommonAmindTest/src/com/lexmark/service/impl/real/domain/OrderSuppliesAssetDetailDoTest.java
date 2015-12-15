package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.compareFields;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;
import com.lexmark.util.LangUtil;


/**
 *  @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 *  @see com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-28
 */
public class OrderSuppliesAssetDetailDoTest {
    
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetdo-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetfavoritedo-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetdetaildo-mapping.xml");
//        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-consumableassetlocationdo-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetpartdetail-mapping.xml");
        
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetserviceagreement-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetentitlementdetail-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetorderpart-mapping.xml");
    }


    /**
     * The mapping from MPS_OrderManagement_Consumables_Assets_Orders_05_07_12.docx
     */
    @Test
    public void testMappings() throws Exception {
        Class<?> doClass =   OrderSuppliesAssetDetailDo.class;
        FieldEntry[] fieldEntries = {
           xmltag("id",  "Id"),

           comment("Input  Data"),
           xmltag("assetId", "Asset Id"),
           xmltag("languageName", ""),

           comment("Result Data to Populate"),
//           xmltag("assetId", "Asset Id"),  // exists in Input Data
           xmltag("serialNumber", "Serial Number"),
           xmltag("assetTag", "Owner Asset Number"),
           xmltag("modelNumber", "LXK C MTM Name"),
           xmltag("ipAddress", "IP Address"),
           xmltag("hostName", "Host Name"),
           xmltag("deviceTagCustomer", "Device Tag Customer"), 
           xmltag("productTLI", "Product Name"),
           xmltag("productLine", "LXK C Assignment Product Line"),

           xmltag("assetCostCenter", "Asset Cost Center"), 
           xmltag("description", "Asset Description"),
           xmltag("devicePhase", "Device Phase"),
           xmltag("defaultSpecialInstruction", "LXK MPS Specail Instruction"),
           xmltag("poNumber", "LXK MPS PO Number"),
           xmltag("notes", ""),       
           xmltag("addressFlag", "LXK MPS Organization Address Flag"), // Boolean 

           xmltag("macAddress", "MAC Address"),
           xmltag("physicalLocation1", "Physical Location 1"),
           xmltag("physicalLocation2", "Physical Location 2"),
           xmltag("physicalLocation3", "Physical Location 3"),
//           xmltag("reading", ""),      // dynamic processing 
//           xmltag("timestamp", ""),    // dynamic processing 

           comment("GenericAddress installAddress"),
           xmltag("addressName", "Install Address Name"),
           xmltag("address1", "LXK UP Install Address Line 1"),
           xmltag("address2", "Install Address Line 2"),
           xmltag("address3", "Install Address Line 3"),
           xmltag("installCity", "Install City"),
           xmltag("installState", "Install State"),
           xmltag("installProvince", "Install Province"), // in the document no aMind field
           xmltag("installPostalCode", "Install Postal Code"),
           xmltag("installCountry", "Install Country"),
           xmltag("addressId", "Install Address ID"),
           xmltag("storeFrontName", ""), 

           comment("AccountContact  assetContact"),
           xmltag("contactFirstName", "LXK SW Primary Contact First Name"),
           xmltag("contactLastName", "LXK SW Primary Contact Last Name"),
           xmltag("contactMiddleName", "LXK MPS Primary Contact Middle Name"),
           xmltag("contactEmailAddress", "LXK SW Primary Contact Email"),
           xmltag("contactWorkPhone", "LXK SW Primary Contact Work Phone"),
           xmltag("contactId", "Primary Contact Id"), 

           comment("Account account"),
           xmltag("accountId", "Owner Account Id"),  // May not need for MPS
           xmltag("accountName", "Account Name"),    // May not need for MPS 

           xmltag("parts", "com.lexmark.service.impl.real.domain.OrderSuppliesAssetPartDetailDo"),
           xmltag("pageCounts", "com.lexmark.service.impl.real.domain.OrderSuppliesAssetPageCountDo"), 
           xmltag("meterType", ""),
//           xmltag("attachments", ""), 

           xmltag("", ""),
           xmltag("", "")

        };
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }

        compareFields(doClass, fieldEntries);
    }
    
    @Test
    public void query0() throws Exception {
         List<OrderSuppliesAssetDetailDo> result = MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class, "", 20, 0);
    }
    
    @Test
    public void queryOrderSuppliesAssetDetailDo() throws Exception {
         List<OrderSuppliesAssetDetailDo> result = MiscTest.querySiebel(OrderSuppliesAssetDetailDo.class, "", 20, 0);
         for (OrderSuppliesAssetDetailDo detailDo : result) {
             System.out.println("~~~~~~~~~~~~~");
             System.out.println("detailDo = " + MiscTest.str(detailDo));
             System.out.println("pageCounts:");
             MiscTest.print("\t", detailDo.getPageCounts());
         }
    }
    
    /**
     * 
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTest#testRetrieveDeviceDetail_QA_Defect3753()
     */
    @Test
    public void queryOrderSuppliesAssetDetailDo_QA_Defect3753_parts() throws Exception {
         List<OrderSuppliesAssetDetailDo> result = MiscTest.querySiebel(OrderSuppliesAssetDetailDo.class, "[assetId]= '1-69CM-1268'", 200, 0);
         for (OrderSuppliesAssetDetailDo detailDo : result) {
             System.out.println("~~~~~~~~~~~~~");
             MiscTest.print(detailDo.getParts());
         }
    }
    
    @Test
    public void queryOrderSuppliesAssetDetailDoBySerialNumber() throws Exception {
         List<OrderSuppliesAssetDetailDo> result = MiscTest.querySiebel(OrderSuppliesAssetDetailDo.class, "", 20, 0);
         for (OrderSuppliesAssetDetailDo detailDo : result) {
             System.out.println("~~~~~~~~~~~~~");
//             System.out.println("detailDo = " + MiscTest.str(detailDo));
             System.out.printf("id=%s, installDate=%s\n", detailDo.getId(), detailDo.getInstallDate());
//             System.out.println("pageCounts:");
//             MiscTest.print("\t", detailDo.getPageCounts());
         }
    }
    
    @Test
    public void testDate() throws Exception {
        java.util.Date d = new java.util.Date();
        System.out.println(d);
        java.sql.Date sqlD = new java.sql.Date(d.getTime());
        System.out.println("sql Date = " + sqlD);
        System.out.println(new SimpleDateFormat("E MMM dd yyyy").format(d));
        System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM).format(d));
        System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US).format(d));
        System.out.println(DateFormat.getDateInstance(DateFormat.LONG).format(d));
        System.out.println(DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(d));
        System.out.println(DateFormat.getDateInstance(DateFormat.FULL).format(d));
//        System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM).format(null));
    }
    
    @Test
    public void queryDefect4166() throws Exception {
        List<?> r = MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class, "", 5);
//        List<?> r = MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class, 
//                "count([Agreement Entitlement] <> 0", 5);
//                "[LXK MPS Consumable Asset Detail - Service Web.Agreement Entitlement] <> 0", 5);
//        List<?> r = MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class, "[orderParts] <> ''", 5);
    }
    
    /**
     * 
     * FS_spcAgreement_spcEntitlement_spcService_spcDetail
     * Service_spcAgreement
     * 
     * @see do-ordersuppliesassetdetaildo-mapping.xml
     * @see do-ordersuppliesassetentitlementdetail-mapping.xml
     * @see do-ordersuppliesassetserviceagreement-mapping.xml 
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetService#retrieveDeviceDetail(com.lexmark.contract.AssetContract)
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTest#testRetrieveDeviceDetail_QA_Defect1566()
     */
    @Test
    public void queryDefect1566() throws Exception {
        List<OrderSuppliesAssetDetailDo> result = MiscTest.querySiebel(OrderSuppliesAssetDetailDo.class, 
                "[Asset Id] <> ''", 10, 0);
        for (OrderSuppliesAssetDetailDo detailDo : LangUtil.notNull(result)) {
            
            System.out.printf("~~~~~~~~~~~~~ assetId=%s\n", detailDo.getAssetId());
            
            for (OrderSuppliesAssetAgreementEntitlementDo entDo : LangUtil.notNull(detailDo.getAgreementEntitlements())) {
                for (OrderSuppliesAssetEntitlementDetailDo entDetailDo : LangUtil.notNull(entDo.getEntitlementDetails())) {
                    System.out.printf("entitlementDetail: assetId=%s, %s\n", detailDo.getAssetId(),  MiscTest.str(entDetailDo));
                }
            }
            
//            for (OrderSuppliesAssetServiceAgreementDo agrDo : LangUtil.notNull(detailDo.getServiceAgreements())) {
//                System.out.printf("serviceAgreement: assetId=%s, %s\n", detailDo.getAssetId(), MiscTest.str(agrDo));
//            }
        }
    }
}
