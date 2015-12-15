package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.MiscTest.list;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetServiceUtil.processPageCounts;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetServiceUtil.selectMaxGroupPartDetailDo;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetServiceUtil.selectWithImplicitFlag;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetServiceUtil.selectWithoutImplicitFlag;
import static com.lexmark.util.LangUtil.equal;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lexmark.domain.Asset;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetPageCountDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetPageCountReadingDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetPartDetailDo;
import com.lexmark.service.impl.real.domain.ServiceAgreementProductDo;

/**
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-28
 */
public class AmindOrderSuppliesAssetServiceUtilTest {

    @Test
    public void testToAsset() {
        Asset out = new Asset();
        OrderSuppliesAssetDetailDo in = new OrderSuppliesAssetDetailDo();
        in.setAssetId("12");
        in.setSerialNumber("serialnumber-a");
        in.setAssetTag("assettag-a");
        in.setModelNumber("modelnumber-a");
        in.setIpAddress("ipaddress-a");
        in.setHostName("sunrise");
        in.setDeviceTagCustomer("devtag");
        in.setProductTLI("prodtli");
        in.setProductLine("productline-a");
        in.setDevicePhase("DevicePhase1");
        in.setMacAddress("MACAddress-90");
        in.setPhysicalLocation1("PhysicalLocation1-a");
        in.setPhysicalLocation2("PhysicalLocation2-a");
        in.setPhysicalLocation3("PhysicalLocation3-a");
//        in.setPoNumber("poNumber-a");
        in.setDefaultSpecialInstruction("defaultSpecialInstruction-a");
        in.setLanguageName("languageName-a");
        in.setAssetCostCenter("costCenter-a");
        in.setDescription("description-a");
        in.setAddressFlag("Y"); // means false
        out = AmindOrderSuppliesAssetServiceUtil.toAsset(in, null);
        assertEquals(in.getAssetId(), out.getAssetId());
        assertEquals(in.getSerialNumber(), out.getSerialNumber());
        assertEquals(in.getAssetTag(), out.getAssetTag());
        assertEquals(in.getModelNumber(), out.getModelNumber());
        assertEquals(in.getIpAddress(), out.getIpAddress());
        assertEquals(in.getHostName(), out.getHostName());
        assertEquals(in.getDeviceTagCustomer(), out.getDeviceTag());
        assertEquals(in.getProductTLI(), out.getProductTLI());
        assertEquals(in.getProductLine(), out.getProductLine());
        assertEquals(in.getDevicePhase(), out.getDevicePhase());
        assertEquals(in.getMacAddress(), out.getMacAddress());
//        assertEquals(in.getPoNumber(), out.getPoNumber());
        assertEquals(in.getDefaultSpecialInstruction(), out.getDefaultSpecialInstruction());
        assertEquals(in.getAssetCostCenter(), out.getAssetCostCenter());
        assertEquals(in.getDescription(), out.getDescription());
        assertEquals(false, out.isAddressFlag());
//                    assertEquals(in.getPhysicalLocation1(), out.getPhysicalLocationAddress());

        // GenericAddress installAddress
        in.setAddressName("addressName-a");
        in.setAddress1("Address1-a");
        in.setAddress2("Address2-a");
        in.setAddress3("Address3-a");
        in.setInstallCity("installcity-a");
        in.setInstallState("InstallState-a");
        in.setInstallPostalCode("InstallPostalCode-a");
        in.setInstallCountry("InstallCountry-a");
        in.setAddressId("addressId-a");
        in.setStoreFrontName("storeFrontName-a");
        out = AmindOrderSuppliesAssetServiceUtil.toAsset(in, null);
        assertEquals(in.getAddressName(), out.getInstallAddress().getAddressName());
        assertEquals(in.getAddress1(), out.getInstallAddress().getAddressLine1());
        assertEquals(in.getAddress2(), out.getInstallAddress().getAddressLine2());
        assertEquals(in.getAddress3(), out.getInstallAddress().getAddressLine3());
        assertEquals(in.getInstallCity(), out.getInstallAddress().getCity());
        assertEquals(in.getInstallState(), out.getInstallAddress().getState());
        assertEquals(in.getInstallPostalCode(), out.getInstallAddress().getPostalCode());
        assertEquals(in.getInstallCountry(), out.getInstallAddress().getCountry());
        assertEquals(in.getInstallCity(), out.getInstallAddress().getCity());
        assertEquals(in.getAddressId(), out.getInstallAddress().getAddressId());
        assertEquals(in.getStoreFrontName(), out.getInstallAddress().getStoreFrontName());

        //
        // AccountContact assetContact
        in.setContactFirstName("ContactFirstName-a");
        in.setContactLastName("ContactLastName-a");
        // a.setContactMiddleName("ContactMiddleName-a");
        in.setContactEmailAddress("ContactEmailAddress-a");
        in.setContactWorkPhone("ContactWorkPhone-a");
        // a.setContactAlternatePhone("ContactAlternatePhone-a");
        in.setContactWorkPhone("ContactWorkPhone-123");
        in.setContactId("ContactId-438");
        out = AmindOrderSuppliesAssetServiceUtil.toAsset(in, null);
        assertEquals(in.getContactFirstName(), out.getAssetContact().getFirstName());
        assertEquals(in.getContactLastName(), out.getAssetContact().getLastName());
        //  assertEquals(a.getContactMiddleName(),
        //  b.getContact().getMiddleName());
        assertEquals(in.getContactEmailAddress(), out.getAssetContact().getEmailAddress());
        // assertEquals(a.getContactAlternatePhone(),
        // b.getContact().getAlternatePhone());
        assertEquals(in.getContactWorkPhone(), out.getAssetContact().getWorkPhone());
        assertEquals(in.getContactId(), out.getAssetContact().getContactId());

        // Account account
        in.setAccountId("AccountId-mock");
        in.setAccountName("mock-accountName");
        out = AmindOrderSuppliesAssetServiceUtil.toAsset(in, null);
        assertEquals(in.getAccountId(), out.getAccount().getAccountId());
        assertEquals(in.getAccountName(), out.getAccount().getAccountName());

        // userFavoriteFlag
//        assertEquals(false, out.getUserFavoriteFlag());
//        {
//            List<OrderSuppliesAssetFavoriteDo> favList = new ArrayList<OrderSuppliesAssetFavoriteDo>();
//            OrderSuppliesAssetFavoriteDo fav1 = new OrderSuppliesAssetFavoriteDo();
//            favList.add(fav1);
//            out = AmindOrderSuppliesAssetServiceUtil.toAsset(in);
//            assertEquals(false, out.getUserFavoriteFlag());
//
//            OrderSuppliesAssetFavoriteDo fav2 = new OrderSuppliesAssetFavoriteDo();
//            fav2.setAssetId(in.getAssetId());
//            favList.add(fav2);
//            out = AmindOrderSuppliesAssetServiceUtil.toAsset(in);
//            assertEquals(true, out.getUserFavoriteFlag());
//
//        }
        // entitlement
        assertEquals(null, out.getEntitlement());
        ArrayList<ServiceAgreementProductDo> entitlements = new ArrayList<ServiceAgreementProductDo>();
        ServiceAgreementProductDo agreementProductDo1 = new ServiceAgreementProductDo();
        long now = System.currentTimeMillis();
        long oneHour = 1000 * 60 * 60;
        long twoHours = oneHour * 2;
        agreementProductDo1.setStartDate(new Date(now - twoHours));
        agreementProductDo1.setEndDate(new Date(now + twoHours));
        agreementProductDo1.setId("id-1");
        entitlements.add(agreementProductDo1);

        ServiceAgreementProductDo agreementProductDo2 = new ServiceAgreementProductDo();
        agreementProductDo2.setStartDate(new Date(now - oneHour));
        agreementProductDo2.setEndDate(new Date(now + oneHour));
        agreementProductDo2.setId("id-2");
        entitlements.add(agreementProductDo2);

        //         in.setEntitlements(entitlements);
        out = AmindOrderSuppliesAssetServiceUtil.toAsset(in,null);
        //           assertEquals(agreementProductDo1.getId(), out.getEntitlement().getEntitlementId()); // TODO(Viktor) discuss result
        //           assertEquals(agreementProductDo2.getId(), out.getEntitlement().getEntitlementId());
        
        // OrderSuppliesAssetPartDetailDo
        
        OrderSuppliesAssetPartDetailDo partDo1 = new  OrderSuppliesAssetPartDetailDo();
        partDo1.setPartType("partType-1");
        OrderSuppliesAssetPartDetailDo partDo2 = new  OrderSuppliesAssetPartDetailDo();
        partDo1.setPartType("partType-2");
        ArrayList<OrderSuppliesAssetPartDetailDo> parts = MiscTest.newArrayList(partDo1, partDo2);
        in.setParts(parts);
        out = AmindOrderSuppliesAssetServiceUtil.toAsset(in,null);
        List<Part> partList = out.getPartList();
        Part part1 = partList.get(0);
        Part part2 = partList.get(1);
        assertEquals(partDo1.getPartType(), part1.getPartType());
        assertEquals(partDo2.getPartType(), part2.getPartType());
    }

    
    @SuppressWarnings("serial")
    private static class  PartDetailDo extends  OrderSuppliesAssetPartDetailDo {
        
        PartDetailDo(Boolean implicitFlag, String partType, String printerPartNumber, String assetSerialNumber, String assetUsageType, String yield) {
            this.setImplicitFlag(implicitFlag);
            this.setPartType(partType);
            this.setPrinterPartNumber(printerPartNumber);
            this.setAssetSerialNumber(assetSerialNumber);
            this.setAssetUsageType(assetUsageType);
            this.setYield(yield);
        }
        
        PartDetailDo(Boolean implicitFlag)  {
            this.setImplicitFlag(implicitFlag);
        }

        @Override
        public String toString() {
            return String.format("implicitNumber=%s, partType=%s, printerPartNumber=%s, assetSerialNumber=%s, assetUsageType=%s, yield=%s",
                    this.getImplicitFlag(), this.getPartType(), this.getPrinterPartNumber(),  this.getAssetSerialNumber(), this.getAssetUsageType(), this.getYield());
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof OrderSuppliesAssetPartDetailDo)) {
                return false;
            }
            OrderSuppliesAssetPartDetailDo that = (OrderSuppliesAssetPartDetailDo) obj;
            return 1 == 1
                   && equal(this.getImplicitFlag(), that.getImplicitFlag())
                   && equal(this.getPartType(), that.getPartType())
                   && equal(this.getPartNumber(), that.getPartNumber())
                   && equal(this.getAssetSerialNumber() ,that.getAssetSerialNumber())
                   && equal(this.getAssetUsageType() , that.getAssetUsageType())
                   && equal(this.getYield() ,that.getYield())
           ;
        }
    }

    @Test
    public void testSelectWithImplicitFlag() throws Exception {
        assertEquals(list(), selectWithImplicitFlag(null));
        assertEquals(list(), selectWithImplicitFlag(list(new PartDetailDo(false), new PartDetailDo(null))));
        PartDetailDo p1 = new PartDetailDo(true);
        PartDetailDo p2 = new PartDetailDo(false);
        assertEquals(list(p1), selectWithImplicitFlag(list(p2, p1)));
    }
    
    @Test
    public void testSelectWithoutImplicitFlag() throws Exception {
        assertEquals(list(), selectWithoutImplicitFlag(null));
        PartDetailDo p1 = new PartDetailDo(true);
        PartDetailDo p2 = new PartDetailDo(false);
        PartDetailDo p3 = new PartDetailDo(null);
        assertEquals(list(p3), selectWithoutImplicitFlag(list(p3)));
        assertEquals(list(p2, p3), selectWithoutImplicitFlag(list(p2, p1, p3)));
    }
    
    @Test
    public void testWhatAreExplicitParts() throws Exception {
        // TODO(Viktor) define
        // which of them: selectWithImplicitFlag,  selectWithoutImplicitFlag
    }
    
    @Test
    public void testSelectMaxGroupPartDetailDo() throws Exception {
          PartDetailDo p1 = new PartDetailDo(true, null,  "", "", "", "1");
          PartDetailDo p2 = new PartDetailDo(true, null,  "", "", "", "2");
          PartDetailDo p3 = new PartDetailDo(true, null,  "", "", "3", "3");
          PartDetailDo p4 = new PartDetailDo(true, null,  "", "", "4", "4");
          PartDetailDo p5 = new PartDetailDo(true, null,  "", "", "", "5");
          PartDetailDo p6 = new PartDetailDo(true, null,  "", "", "3", "6");
          PartDetailDo p7 = new PartDetailDo(true, null,  "", "2", "4", "7");
          PartDetailDo p8 = new PartDetailDo(true, null,  "", "1", "", "8");
          PartDetailDo p9 = new PartDetailDo(true, "a",  "a", "1", "", "9");
          PartDetailDo p10 = new PartDetailDo(true, null,  "a", "1", "", "10");
          assertEquals(list(p2), selectMaxGroupPartDetailDo(list(p2, p1))); 
          assertEquals(list(p4), selectMaxGroupPartDetailDo(list(p2, p1, p3, p4))); 
          assertEquals(list(p7), selectMaxGroupPartDetailDo(list(p2, p1, p3, p4, p7, p8))); 
          assertEquals(list(p7, p9), selectMaxGroupPartDetailDo(list(p2, p1, p3, p4, p7, p8, p9, p10)));
    }

    @Test
    public void testProcessPageCounts() throws Exception {
        OrderSuppliesAssetPageCountReadingDo readingDo1 = new OrderSuppliesAssetPageCountReadingDo();
        readingDo1.setReading("r1");
        readingDo1.setTimestamp("t1");
        
        OrderSuppliesAssetPageCountReadingDo readingDo2 = new OrderSuppliesAssetPageCountReadingDo();
        readingDo2.setReading("r2");
        readingDo2.setTimestamp("t2");
        
        OrderSuppliesAssetPageCountReadingDo readingDo3 = new OrderSuppliesAssetPageCountReadingDo();
        readingDo3.setReading("r3");
        readingDo3.setTimestamp("t3");
        
        ArrayList<OrderSuppliesAssetPageCountReadingDo> latestReadings1 = new ArrayList<OrderSuppliesAssetPageCountReadingDo>();
        ArrayList<OrderSuppliesAssetPageCountReadingDo> latestReadings2 = new ArrayList<OrderSuppliesAssetPageCountReadingDo>();
        ArrayList<OrderSuppliesAssetPageCountReadingDo> latestReadings3 = new ArrayList<OrderSuppliesAssetPageCountReadingDo>();
        
        latestReadings1.add(readingDo1);
        latestReadings1.add(readingDo2);
        latestReadings1.add(readingDo3);
        
        latestReadings2.add(readingDo2);
        latestReadings2.add(readingDo1);
        latestReadings2.add(readingDo3);
        
        latestReadings3.add(readingDo3);
        latestReadings3.add(readingDo2);
        latestReadings3.add(readingDo1);
        
        
        OrderSuppliesAssetPageCountDo pageCountDo1 = new OrderSuppliesAssetPageCountDo();
        pageCountDo1.setName("Mono");
        OrderSuppliesAssetPageCountDo pageCountDo2 = new OrderSuppliesAssetPageCountDo();
        pageCountDo2.setName("Abc");
        OrderSuppliesAssetPageCountDo pageCountDo3 = new OrderSuppliesAssetPageCountDo();
        pageCountDo3.setName("Aurora");
        
        pageCountDo1.setLatestReadings(latestReadings1);
        pageCountDo2.setLatestReadings(latestReadings2);
        pageCountDo3.setLatestReadings(latestReadings3);
                
        ArrayList<OrderSuppliesAssetPageCountDo> pageCountDos = new ArrayList<OrderSuppliesAssetPageCountDo>();
        pageCountDos.add(pageCountDo1);
        pageCountDos.add(pageCountDo2);
        pageCountDos.add(pageCountDo3);
        
        Asset asset = new Asset();
        List<PageCounts> pageCounts = processPageCounts(asset, pageCountDos); 
        
        assertEquals(2, pageCounts.size());
        assertEquals("Aurora", pageCounts.get(1).getName());
        assertEquals("r3", pageCounts.get(1).getCount());
        assertEquals("t3", pageCounts.get(1).getDate());
    }
    
}
