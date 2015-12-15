package com.lexmark.service.impl.real.deviceService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.convertMeasurementCharacteristicList;
import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.toAsset;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.lexmark.domain.Asset;
import com.lexmark.service.impl.real.domain.AccountAsset;
import com.lexmark.service.impl.real.domain.AccountAssetDetailDo;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsBCDo;
import com.lexmark.service.impl.real.domain.AssetReadingLatestBcDo;
import com.lexmark.service.impl.real.domain.EntitlementTemplateServiceDetailsDo;
import com.lexmark.service.impl.real.domain.ServiceAgreementProductDo;

/**
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, Mar 6, 2012
 */
public class AmindContractedDeviceDataConversionUtilTest {

    @Test
    public void testConvertCHLIOtoCHLNode() {
        try {
            AmindContractedDeviceDataConversionUtil.convertCHLIOtoCHLNode(null);
            fail();
        } catch (NullPointerException ok) {
        }
    }

    /**
     *  Mapping file:  do-accountassetdetaildo-mapping.xml
     */
    @Test
    public void convertAccountAssetDetailDoToAsset() throws Exception {
        AccountAssetDetailDo a = new AccountAssetDetailDo();
        a.setAssetId("12");
        a.setHostName("sunrise");
        a.setDeviceTagCustomer("devtag");
        a.setProductTLI("prodtli");
        a.setDevicePhase("DevicePhase1");
        a.setCustomerCostCode("customerCostCode-q4");
        a.setConsumableAssetFlag(true);
        Asset b = AmindContractedDeviceDataConversionUtil.convertAccountAssetDetailDoToAsset(a, null);
        assertEquals(a.getAssetId(), b.getAssetId());
        assertEquals(a.getHostName(), b.getHostName());
        assertEquals(a.getDeviceTagCustomer(), b.getDeviceTag());
        assertEquals(a.getProductTLI(), b.getProductTLI());
        assertEquals(a.getDevicePhase(), b.getDevicePhase());
        assertEquals(a.isConsumableAssetFlag(), b.isConsumableAssetFlag());

        a.setCustomerCostCode("customercode123");
        a.setHierarchyNodeId("HierarchyNode-243");
        a.setHierarchyNodeValue("mock-hierarchyNodeValue");
        b = AmindContractedDeviceDataConversionUtil.convertAccountAssetDetailDoToAsset(a, null);
        assertEquals(a.getCustomerCostCode(), b.getAssetCostCenter()); 
        assertEquals(a.getInstallDate(), b.getInstallDate()); 
        assertEquals(a.getHierarchyNodeId(), b.getChlNodeId());
        assertEquals(a.getHierarchyNodeValue(), b.getChlNodeValue()); 

        a.setAddressName("test-addrname");
        a.setInstallProvince("installprovince-a");
        b = AmindContractedDeviceDataConversionUtil.convertAccountAssetDetailDoToAsset(a, null);
        assertEquals(a.getAddressName(), b.getInstallAddress().getAddressName());  
        assertEquals(a.getInstallProvince(), b.getInstallAddress().getProvince()); 

        a.setAccountId("AccountId-mock");
        a.setAccountName("mock-accountName");
        b = AmindContractedDeviceDataConversionUtil.convertAccountAssetDetailDoToAsset(a, null);
        assertEquals(a.getAccountId(), b.getAccount().getAccountId());
        assertEquals(a.getAccountName(), b.getAccount().getAccountName());


        List<ServiceAgreementProductDo> entitlements =  new ArrayList<ServiceAgreementProductDo>();
        entitlements.add(newServiceAgreementProductDo("id1"));
        entitlements.add(newServiceAgreementProductDo("id2"));
        entitlements.add(newServiceAgreementProductDo("id3"));
//        a.setEntitlements(entitlements);
        a.setEntitlements(new ArrayList(entitlements));
        b = AmindContractedDeviceDataConversionUtil.convertAccountAssetDetailDoToAsset(a, null);
        assertEquals("id1", b.getEntitlement().getEntitlementId());  // first
    }

    private ServiceAgreementProductDo newServiceAgreementProductDo(String  entitlementId) {
        ServiceAgreementProductDo a = new  ServiceAgreementProductDo();
        a.setId(entitlementId);
        long oneHour =  1000 * 60 * 60 ;
        long twoHours =  oneHour * 2;
        long t0 =  System.currentTimeMillis();
        a.setStartDate(new Date(t0 - twoHours));
        a.setEndDate(new Date(t0 + twoHours));
        
        // EntitlementTemplateServiceDetailsDo
        ArrayList<EntitlementTemplateServiceDetailsDo> serviceDetails = new ArrayList<EntitlementTemplateServiceDetailsDo>();
        EntitlementTemplateServiceDetailsDo  sd =  new EntitlementTemplateServiceDetailsDo();
        sd.setCustomerShowFlag(true);
        serviceDetails.add(sd);
        a.setServiceDetails(serviceDetails);
        return a;
    }

    @Test
    public void testConvertMeasurementCharacteristicList_Color_name() throws InterruptedException {
        Asset a = new Asset();
        convertMeasurementCharacteristicList(a, null);
        assertEquals(null, a.getColorCapableFlag());
        assertEquals(null, a.getLastColorPageCount());

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(null, a.getColorCapableFlag());
            assertEquals(null, a.getLastColorPageCount());
        }

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            AssetMeasurementCharacteristicsBCDo measurementCharacteristics =
                    newAssetMeasurementCharacteristicsBCDo(meterReads, "Color-non-match");
            ArrayList<AssetReadingLatestBcDo> assetReadings = newAssetReadingLatestBcDos(measurementCharacteristics);
            AssetReadingLatestBcDo newReading = newAssetReadingLatestBcDo(assetReadings, new Date(),  "reading-mock-good-day");
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(null, a.getColorCapableFlag());
            assertEquals(null, a.getLastColorPageCount());
        }

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            Date readingDate = new Date();
            String readingValue = "hello";
            AssetMeasurementCharacteristicsBCDo measurementCharacteristics =
                    newAssetMeasurementCharacteristicsBCDo(meterReads, "Color");
            ArrayList<AssetReadingLatestBcDo> assetReadings = newAssetReadingLatestBcDos(measurementCharacteristics);
            AssetReadingLatestBcDo newReading = newAssetReadingLatestBcDo(assetReadings, readingDate, readingValue);
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(true, a.getColorCapableFlag());
            assertEquals(readingValue, a.getLastColorPageCount());
        }

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            Date readingDate = new Date(-10);
            String readingValue = "world";
            {
                AssetMeasurementCharacteristicsBCDo measurementCharacteristics1 =
                        newAssetMeasurementCharacteristicsBCDo(meterReads, "Color");
                ArrayList<AssetReadingLatestBcDo> assetReadings1 = newAssetReadingLatestBcDos(measurementCharacteristics1);
                AssetReadingLatestBcDo reading1 = newAssetReadingLatestBcDo(assetReadings1, readingDate,  readingValue);
            }
            {
                AssetMeasurementCharacteristicsBCDo measurementCharacteristics2 =
                        newAssetMeasurementCharacteristicsBCDo(meterReads, "Color");
                ArrayList assetReadings2 = newAssetReadingLatestBcDos(measurementCharacteristics2);
                AssetReadingLatestBcDo reading2 = newAssetReadingLatestBcDo(assetReadings2, null,  null);
            }
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(readingValue, a.getLastColorPageCount());
            assertEquals(readingDate, a.getLastColorPageReadDate());
            assertEquals(new Date(-10), readingDate);
            assertEquals(true, a.getColorCapableFlag());
        }
    }

    @Test
    public void testConvertMeasurementCharacteristicList_LTPC_name() {
        Asset a = new Asset();
        convertMeasurementCharacteristicList(a, null);
        /*
                                 asset.setLastPageCount(reading.getReading());
                        asset.setLastPageReadDate(readingDate);
         */
        assertEquals(null, a.getLastPageCount());
        assertEquals(null, a.getLastPageReadDate());

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(null, a.getLastPageCount());
            assertEquals(null, a.getLastPageReadDate());
        }

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            AssetMeasurementCharacteristicsBCDo measurementCharacteristics =
                    newAssetMeasurementCharacteristicsBCDo(meterReads, "LTPC-non-match");
            ArrayList<AssetReadingLatestBcDo> assetReadings = newAssetReadingLatestBcDos(measurementCharacteristics);
            AssetReadingLatestBcDo newReading = newAssetReadingLatestBcDo(assetReadings, new Date(),  "reading-mock-good-day");
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(null, a.getLastPageCount());
            assertEquals(null, a.getLastPageReadDate());
        }

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            Date readingDate = new Date();
            String readingValue = "hello";
            AssetMeasurementCharacteristicsBCDo measurementCharacteristics =
                    newAssetMeasurementCharacteristicsBCDo(meterReads, "LTPC");
            ArrayList<AssetReadingLatestBcDo> assetReadings = newAssetReadingLatestBcDos(measurementCharacteristics);
            AssetReadingLatestBcDo newReading = newAssetReadingLatestBcDo(assetReadings, readingDate, readingValue);
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(readingValue, a.getLastPageCount());
            assertEquals(readingDate, a.getLastPageReadDate());
        }

        {
            ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads = new ArrayList<AssetMeasurementCharacteristicsBCDo>();
            Date readingDate = new Date(-10);
            String readingValue = "world";
            {
                AssetMeasurementCharacteristicsBCDo measurementCharacteristics1 =
                        newAssetMeasurementCharacteristicsBCDo(meterReads, "LTPC");
                ArrayList<AssetReadingLatestBcDo> assetReadings1 = newAssetReadingLatestBcDos(measurementCharacteristics1);
                AssetReadingLatestBcDo reading1 = newAssetReadingLatestBcDo(assetReadings1, readingDate,  readingValue);
            }
            {
                AssetMeasurementCharacteristicsBCDo measurementCharacteristics2 =
                        newAssetMeasurementCharacteristicsBCDo(meterReads, "LTPC");
                ArrayList assetReadings2 = newAssetReadingLatestBcDos(measurementCharacteristics2);
                AssetReadingLatestBcDo reading2 = newAssetReadingLatestBcDo(assetReadings2, null,  null);
            }
            convertMeasurementCharacteristicList(a, meterReads);
            assertEquals(readingValue, a.getLastPageCount());
            assertEquals(readingDate, a.getLastPageReadDate());
            assertEquals(new Date(-10), readingDate);
        }
    }


    private AssetReadingLatestBcDo newAssetReadingLatestBcDo(ArrayList<AssetReadingLatestBcDo> assetReadings, Date  newReadingTimestamp, String newReadingReading) {
        AssetReadingLatestBcDo r = new AssetReadingLatestBcDo();
        r.setTimestamp(newReadingTimestamp);
        r.setReading(newReadingReading);
        assetReadings.add(r);
        return r;
    }

    private ArrayList<AssetReadingLatestBcDo> newAssetReadingLatestBcDos(AssetMeasurementCharacteristicsBCDo measurementCharacteristics) {
        ArrayList<AssetReadingLatestBcDo> readings = new ArrayList<AssetReadingLatestBcDo>();
        measurementCharacteristics.setAssetReading(readings);
        return readings;
    }

    private AssetMeasurementCharacteristicsBCDo newAssetMeasurementCharacteristicsBCDo(List<AssetMeasurementCharacteristicsBCDo> meterReads, String name) {
        AssetMeasurementCharacteristicsBCDo m = new AssetMeasurementCharacteristicsBCDo();
        m.setName(name);
        meterReads.add(m);
        return m;
    }
    
    @Test
    public void testConvertAssetDoToAssetList() throws Exception {
        assertNotNull(AmindContractedDeviceDataConversionUtil.convertAssetDoToAssetList(null, null));
        List<AssetBase> assetBaseList = new ArrayList<AssetBase>();
        assetBaseList.add(new AccountAsset());
        Set<String> favoriteSet = new HashSet<String>();
        assertNotNull(AmindContractedDeviceDataConversionUtil.convertAssetDoToAssetList(assetBaseList, favoriteSet));
    }
    
    @Test
    public void testToAsset() throws Exception {
        AccountAsset doClass = new AccountAsset();
        doClass.setConsumableAssetFlag(true);
        Asset a = toAsset(doClass, null);
        assertEquals(doClass.isConsumableAssetFlag(), a.isConsumableAssetFlag());
    } 
}
