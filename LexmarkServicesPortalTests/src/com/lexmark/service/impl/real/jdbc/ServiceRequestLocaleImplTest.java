package com.lexmark.service.impl.real.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.lexmark.contract.LocalizedEntitlementServiceListContract;
import com.lexmark.contract.LocalizedExchangeOptionListContract;
import com.lexmark.contract.LocalizedServiceActivityStatusContract;
import com.lexmark.contract.LocalizedServiceStatusContract;
import com.lexmark.contract.SRAdministrationDetailContract;
import com.lexmark.contract.SRAdministrationSaveContract;
import com.lexmark.contract.SRLocalizationContract;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.EntitlementServiceDetails;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SiebelLocalizationLocale;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.result.LocalizedEntitlementServiceListResult;
import com.lexmark.result.LocalizedExchangeOptionListResult;
import com.lexmark.result.LocalizedServiceActivityStatusResult;
import com.lexmark.result.LocalizedServiceStatusResult;
import com.lexmark.result.SRAdministrationDetailResult;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.result.SRAdministrationSaveResult;
import com.lexmark.result.SupportedLocaleListResult;

public class ServiceRequestLocaleImplTest {

	private final String[] localeCodes = new String[]{"en", "es", "de", "fr", "ja"};
	
	ServiceRequestLocaleImpl target;
	@Before
	public void setUp() throws Exception {
		target = new ServiceRequestLocaleImpl();
		prepareMetaData();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private void prepareMetaData() throws Exception {
		SRAdministrationListResult result = target.retrieveSRAdministrationList(null);
		for(SiebelLocalization sl: result.getSiebelLocalizations()) {
			SRLocalizationContract contract = new SRLocalizationContract();
			contract.setSiebelLocalizationId(sl.getSiebelLocalizationId());
			target.deleteSRLocalization(contract);
		}

		Connection conn = HibernateUtil.getSession().connection();
		PreparedStatement delete = conn.prepareStatement("delete from SUPPORTED_LOCALE");
		PreparedStatement insert = conn.prepareStatement(
		"insert into supported_locale (supported_locale_id, supported_locale_name, supported_locale_code) values (?, ?, ?)");

		delete.executeUpdate();
		delete.close();

		for(int i=0; i< localeCodes.length; i++) {
			insert.setInt(1, i+1);
			insert.setString(2, localeCodes[i]);
			insert.setString(3, localeCodes[i]);
			insert.executeUpdate();
		}
		insert.close();
		conn.commit();
	}
	
	
	private SiebelLocalization createSRAdministration(String optionType, String siebelValue) {
		SRAdministrationSaveContract contract = new SRAdministrationSaveContract();
		
		SiebelLocalization sl = new SiebelLocalization();
		sl.setOptionType(optionType);
		sl.setShowEntitlementFlag(true);
		sl.setSiebelValue(siebelValue);
		for(int i=1; i<6; i++) {
			SiebelLocalizationLocale locale = new SiebelLocalizationLocale();
			locale.setDisplayValue(siebelValue + " " + localeCodes[i-1]);
			SupportedLocale supportedLocale = new SupportedLocale();
			supportedLocale.setSupportedLocaleId(i);
			locale.setSupportedLocale(supportedLocale);
			locale.setSiebelLocalization(sl);
			sl.getLocaleList().add(locale);
		}
		contract.setSiebelLocalization(sl);
		SRAdministrationSaveResult result =  target.saveSRAdministrationDetail(contract);
		return sl;
	}


	@Test
	public void testRetrieveLocalizedEntitlementServiceList() {
		String type = SiebelLocalization.SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue();
		createSRAdministration(type, "test1");
		createSRAdministration(type, "test2");
		createSRAdministration(type, "test3");
		LocalizedEntitlementServiceListContract contract = new LocalizedEntitlementServiceListContract();
		contract.setLocale(new Locale("en", "", ""));
		
		EntitlementServiceDetail detail = new EntitlementServiceDetail();
		detail.setServiceDetailDescription("test2");
		contract.getEntitlementServiceDetails().add(detail);

		detail = new EntitlementServiceDetail();
		detail.setServiceDetailDescription("test3");
		contract.getEntitlementServiceDetails().add(detail);

		LocalizedEntitlementServiceListResult result = target.retrieveLocalizedEntitlementServiceList(contract);
		List<EntitlementServiceDetail> entitlementServiceDetail = result.getEntitlementServiceDetails();
		Assert.assertEquals(Integer.valueOf("2"), entitlementServiceDetail.size());
		Assert.assertEquals("test2" + " en", entitlementServiceDetail.get(0).getServiceDetailDescription() );
	}

	@Test
	public void testRetrieveLocalizedExchangeOptionList() {
		String type = SiebelLocalization.SiebelLocalizationOptionEnum.EXCHANGE_OPTION.getValue();
		createSRAdministration(type, "test1");
		createSRAdministration(type, "test2");
		createSRAdministration(type, "test3");

		LocalizedExchangeOptionListContract contract = new LocalizedExchangeOptionListContract();
		contract.setLocale(new Locale("de", "", ""));
		LocalizedExchangeOptionListResult result = target.retrieveLocalizedExchangeOptionList(contract);
		List<String> list = result.getLocalizedValues();
		Assert.assertEquals(Integer.valueOf("3"), list.size());
		Assert.assertEquals("test1 de", list.get(0));
	}


	@Test
	public void testRetrieveLocalizedServiceActivityStatus() {
		String type = SiebelLocalization.SiebelLocalizationOptionEnum.SERVICE_ACTIVITY_STATUS_DESCRIPTION.getValue();
		String siebelValue = "test";
		createSRAdministration(type, siebelValue);
		LocalizedServiceActivityStatusContract contract = new LocalizedServiceActivityStatusContract();
		contract.setLocale(new Locale("en", "", ""));
		contract.setSiebelValue(siebelValue);
		LocalizedServiceActivityStatusResult result = target.retrieveLocalizedServiceActivityStatus(contract);
		Assert.assertEquals(siebelValue + " en", result.getLocalizedValue());
	}

	@Test
	public void testRetrieveLocalizedServiceStatus() {
		String type = SiebelLocalization.SiebelLocalizationOptionEnum.SERVICE_STATUS.getValue();
		String siebelValue = "test";
		createSRAdministration(type, siebelValue);
		LocalizedServiceStatusContract contract = new LocalizedServiceStatusContract();
		contract.setLocale(new Locale("de", "", ""));
		contract.setSiebelValue(siebelValue);
		LocalizedServiceStatusResult result = target.retrieveLocalizedServiceStatus(contract);
		Assert.assertEquals(siebelValue + " de", result.getLocalizedString());
	}

	@Test
	public void testRetrieveSRAdministrationDetail() {
		SRAdministrationListResult result = target.retrieveSRAdministrationList(null);
		
		if(result.getSiebelLocalizations().size() == 0) {
			return;
		}
		SiebelLocalization sl = result.getSiebelLocalizations().get(0);

		SRAdministrationDetailContract contract = new SRAdministrationDetailContract();
		contract.setSiebelLocalizationId(sl.getSiebelLocalizationId());
		SRAdministrationDetailResult detailResult =  target.retrieveSRAdministrationDetail(contract);
		Assert.assertNotNull(detailResult.getSiebelLocalization());
		Assert.assertEquals(sl.getSiebelLocalizationId(), detailResult.getSiebelLocalization().getSiebelLocalizationId());
//		Assert.assertEquals(Integer.valueOf("5"), detailResult.getSiebelLocalization().getLocaleList().size());
	}

	@Test
	public void testRetrieveSRAdministrationList() {
		SRAdministrationListResult result = target.retrieveSRAdministrationList(null);
		Assert.assertTrue(result.getSiebelLocalizations().size()>=0);
	}

	@Test
	public void testRetrieveSupportedLocaleList() {
		SupportedLocaleListResult result = target.retrieveSupportedLocaleList();
		Assert.assertEquals(5, result.getSupportedLocales().size());	
	}
}
