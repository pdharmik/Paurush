package com.lexmark.service.api;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.CheckedEntitlementServiceDetailContract;
import com.lexmark.contract.LocalizedEntitlementServiceDetailContract;
import com.lexmark.contract.LocalizedEntitlementServiceListContract;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.LocalizedServiceActivityStatusContract;
import com.lexmark.contract.LocalizedServiceStatusContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.SRAdministrationDetailContract;
import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.contract.SRAdministrationSaveContract;
import com.lexmark.contract.SRLocalizationContract;
import com.lexmark.result.CheckedEntitlementServiceDetailResult;
import com.lexmark.result.LocalizedEntitlementServiceDetailResult;
import com.lexmark.result.LocalizedEntitlementServiceListResult;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.LocalizedServiceActivityStatusResult;
import com.lexmark.result.LocalizedServiceStatusResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.SRAdministrationDetailResult;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.result.SRAdministrationSaveResult;
import com.lexmark.result.SRLocalizationResult;
import com.lexmark.result.SupportedLocaleListResult;

public interface ServiceRequestLocale {
	public SRAdministrationListResult retrieveSRAdministrationList (SRAdministrationListContract contract);
	public SRAdministrationListResult retrieveSRAdministrationListFilter (SRAdministrationListContract contract,String filterCriterias); //added by sbag
	public SRLocalizationResult deleteSRLocalization (SRLocalizationContract contract);
	public SRAdministrationDetailResult retrieveSRAdministrationDetail (SRAdministrationDetailContract contract);
	public SupportedLocaleListResult retrieveSupportedLocaleList ();
	public CheckedEntitlementServiceDetailResult retrieveCheckedEntitlementServiceDetail(CheckedEntitlementServiceDetailContract contract);
	public SRAdministrationSaveResult saveSRAdministrationDetail (SRAdministrationSaveContract contract);
	@Cacheable(cacheName="retrieveLocalizedServiceStatus", keyGeneratorName="myKeyGenerator")
	public LocalizedServiceStatusResult retrieveLocalizedServiceStatus (LocalizedServiceStatusContract contract);
	public LocalizedEntitlementServiceListResult retrieveLocalizedEntitlementServiceList (LocalizedEntitlementServiceListContract contract);
	@Cacheable(cacheName="retrieveLocalizedServiceActivityStatus", keyGeneratorName="myKeyGenerator")
	public LocalizedServiceActivityStatusResult retrieveLocalizedServiceActivityStatus (LocalizedServiceActivityStatusContract contract);
	@Cacheable(cacheName="retrieveLocalizedEntitlementServiceDetail", keyGeneratorName="myKeyGenerator")
	public LocalizedEntitlementServiceDetailResult retrieveLocalizedEntitlementServiceDetail (LocalizedEntitlementServiceDetailContract contract);
	@Cacheable(cacheName="retrieveLocalizedSiebelValue", keyGeneratorName="myKeyGenerator")
	public LocalizedSiebelValueResult retrieveLocalizedSiebelValue(LocalizedSiebelValueContract contract);
	@Cacheable(cacheName="retrieveLocalizedSiebelLOVList", keyGeneratorName="myKeyGenerator")
	public LocalizedSiebelLOVListResult retrieveLocalizedSiebelLOVList(LocalizedSiebelLOVListContract contract);
	public LocalizedPageCountNameResult retrieveLocalizedPageCountName(	LocalizedPageCountNameContract contract);
}
