package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;

import java.util.List;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.AssetContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.GenericAddress;

/**
*
* Auto generated Test Codes 
*
**/

public class AssetTestCase {

   private Asset asset = new Asset();
   private Entitlement entitlements = new Entitlement();
   private AccountContact ac = new AccountContact();
   private GenericAddress ga = new GenericAddress();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    asset.setAssetId("assetId");
    asset.setSerialNumber("serialNumber");
    asset.setAssetTag("assetTag");
    asset.setModelNumber("modelNumber");
    asset.setIpAddress("ipAddress");
    asset.setInstallAddress(ga);
    asset.setUserFavoriteFlag(true);
    asset.setEntitlement(entitlements);
    asset.setAssetType("assetType");
    asset.setSupportUrl("supportUrl");
    asset.setControlPanelURL("controlPanelURL");
    asset.setHostName("hostName");
    asset.setDeviceTag("deviceTag");
    asset.setAssetContact(ac);
    asset.setNotMyPrinter(true);
    asset.setProductTLI("productTLI");
    asset.setProductImageURL("productImageURL");
    asset.setDeviceName("deviceName");
    asset.setPhysicalLocation1("physicalLocation1");
    asset.setPhysicalLocation2("physicalLocation2");
    asset.setPhysicalLocation3("physicalLocation3");
    asset.setProductLine("productLine");
    asset.setDownloadsUrl("downloadsUrl");


    assertEquals("assetId",asset.getAssetId());
    assertEquals("serialNumber",asset.getSerialNumber());
    assertEquals("assetTag",asset.getAssetTag());
    assertEquals("modelNumber",asset.getModelNumber());
    assertEquals("ipAddress",asset.getIpAddress());
    assertEquals(ga,asset.getInstallAddress());
    assertEquals(true,asset.getUserFavoriteFlag());
    assertEquals(entitlements,asset.getEntitlement());
    assertEquals("assetType",asset.getAssetType());
    assertEquals("supportUrl",asset.getSupportUrl());
    assertEquals("controlPanelURL",asset.getControlPanelURL());
    assertEquals("hostName",asset.getHostName());
    assertEquals("deviceTag",asset.getDeviceTag());
    assertEquals(ac,asset.getAssetContact());
    assertEquals(true,asset.isNotMyPrinter());
    assertEquals("productTLI",asset.getProductTLI());
    assertEquals("productImageURL",asset.getProductImageURL());
    assertEquals("deviceName",asset.getDeviceName());
    assertEquals("physicalLocation1",asset.getPhysicalLocation1());
    assertEquals("physicalLocation2",asset.getPhysicalLocation2());
    assertEquals("physicalLocation3",asset.getPhysicalLocation3());
    assertEquals("productLine",asset.getProductLine());
   }
}

