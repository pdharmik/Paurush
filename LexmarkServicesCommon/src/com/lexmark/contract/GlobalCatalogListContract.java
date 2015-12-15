package com.lexmark.contract;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.service.api.CrmSessionHandle;

public class GlobalCatalogListContract extends MdmSearchContractBase implements Serializable {
	private static final long serialVersionUID = -3710698966678741897L;
	private CrmSessionHandle sessionHandle;
	
	//Supplies
	private String supMdmID;
	private String supMdmLevel;
	private String supContactID;
	private String supCountry;
	private Locale supLocale;
	private String supSoldToNumber;
	private String supPaymentType;
	private String supContractNumber;
	private String supProductType;
	private String supPartType;
	private String supProductModel;
	private String supPartNumber;
	private String supAccountId;
	private String supAgreementId;
	private String supContactId;
	private boolean supPortalFlag;
	private boolean supCatalogFlag;
	private boolean supHardwareFlag;
	private boolean supHardwareSuppliesFlag;
	private boolean supHardwareAccessoriesFlag;
	private Date supEffectiveDate;
	private String supBundleId;
	private int supStartRecordNumber;
	private int supIncrement;
	private boolean SupNewQueryIndicator;
	private Map<String, Object> supSortCriteria = new HashMap<String, Object>();
	
	//Accessories
	private String accMdmID;
	private String accMdmLevel;
	private String accContactID;
	private String accCountry;
	private Locale accLocale;
	private String accSoldToNumber;
	private String accPaymentType;
	private String accContractNumber;
	private String accProductType;
	private String accPartType;
	private String accProductModel;
	private String accPartNumber;
	private String accAccountId;
	private String accAgreementId;
	private String accContactId;
	private boolean accPortalFlag;
	private boolean accCatalogFlag;
	private boolean accHardwareFlag;
	private boolean accHardwareSuppliesFlag;
	private boolean accHardwareAccessoriesFlag;
	private Date accEffectiveDate;
	private String accBundleId;
	private int accStartRecordNumber;
	private int accIncrement;
	private boolean accNewQueryIndicator;

	//Bundles
	private String bunAgreementId;
	private String bunProductType;
	private String bunProductModel;
	private String bunPartNumber;
	private String bunSoldToNumber;
	private String bunPaymentType;
	private String bunPartType;
	private String bunContractNumber;
	private Date bunEffectiveDate;
	private String bunLocationType;
	private int bunStartRecordNumber;
	private int bunIncrement;
	private boolean bunNewQueryIndicator;
	
	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}
	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}
	public String getSupMdmID() {
		return supMdmID;
	}
	public void setSupMdmID(String supMdmID) {
		this.supMdmID = supMdmID;
	}
	public String getSupMdmLevel() {
		return supMdmLevel;
	}
	public void setSupMdmLevel(String supMdmLevel) {
		this.supMdmLevel = supMdmLevel;
	}
	public String getSupContactID() {
		return supContactID;
	}
	public void setSupContactID(String supContactID) {
		this.supContactID = supContactID;
	}
	public String getSupCountry() {
		return supCountry;
	}
	public void setSupCountry(String supCountry) {
		this.supCountry = supCountry;
	}
	public Locale getSupLocale() {
		return supLocale;
	}
	public void setSupLocale(Locale supLocale) {
		this.supLocale = supLocale;
	}
	public String getSupSoldToNumber() {
		return supSoldToNumber;
	}
	public void setSupSoldToNumber(String supSoldToNumber) {
		this.supSoldToNumber = supSoldToNumber;
	}
	public String getSupPaymentType() {
		return supPaymentType;
	}
	public void setSupPaymentType(String supPaymentType) {
		this.supPaymentType = supPaymentType;
	}
	public String getSupContractNumber() {
		return supContractNumber;
	}
	public void setSupContractNumber(String supContractNumber) {
		this.supContractNumber = supContractNumber;
	}
	public String getSupProductType() {
		return supProductType;
	}
	public void setSupProductType(String supProductType) {
		this.supProductType = supProductType;
	}
	public String getSupPartType() {
		return supPartType;
	}
	public void setSupPartType(String supPartType) {
		this.supPartType = supPartType;
	}
	public String getSupProductModel() {
		return supProductModel;
	}
	public void setSupProductModel(String supProductModel) {
		this.supProductModel = supProductModel;
	}
	public String getSupPartNumber() {
		return supPartNumber;
	}
	public void setSupPartNumber(String supPartNumber) {
		this.supPartNumber = supPartNumber;
	}
	public String getSupAccountId() {
		return supAccountId;
	}
	public void setSupAccountId(String supAccountId) {
		this.supAccountId = supAccountId;
	}
	public String getSupAgreementId() {
		return supAgreementId;
	}
	public void setSupAgreementId(String supAgreementId) {
		this.supAgreementId = supAgreementId;
	}
	public String getSupContactId() {
		return supContactId;
	}
	public void setSupContactId(String supContactId) {
		this.supContactId = supContactId;
	}
	public boolean isSupPortalFlag() {
		return supPortalFlag;
	}
	public void setSupPortalFlag(boolean supPortalFlag) {
		this.supPortalFlag = supPortalFlag;
	}
	public boolean isSupCatalogFlag() {
		return supCatalogFlag;
	}
	public void setSupCatalogFlag(boolean supCatalogFlag) {
		this.supCatalogFlag = supCatalogFlag;
	}
	public boolean isSupHardwareFlag() {
		return supHardwareFlag;
	}
	public void setSupHardwareFlag(boolean supHardwareFlag) {
		this.supHardwareFlag = supHardwareFlag;
	}
	public boolean isSupHardwareSuppliesFlag() {
		return supHardwareSuppliesFlag;
	}
	public void setSupHardwareSuppliesFlag(boolean supHardwareSuppliesFlag) {
		this.supHardwareSuppliesFlag = supHardwareSuppliesFlag;
	}
	public boolean isSupHardwareAccessoriesFlag() {
		return supHardwareAccessoriesFlag;
	}
	public void setSupHardwareAccessoriesFlag(boolean supHardwareAccessoriesFlag) {
		this.supHardwareAccessoriesFlag = supHardwareAccessoriesFlag;
	}
	public Date getSupEffectiveDate() {
		return supEffectiveDate;
	}
	public void setSupEffectiveDate(Date supEffectiveDate) {
		this.supEffectiveDate = supEffectiveDate;
	}
	public String getSupBundleId() {
		return supBundleId;
	}
	public void setSupBundleId(String supBundleId) {
		this.supBundleId = supBundleId;
	}
	public String getAccMdmID() {
		return accMdmID;
	}
	public void setAccMdmID(String accMdmID) {
		this.accMdmID = accMdmID;
	}
	public String getAccMdmLevel() {
		return accMdmLevel;
	}
	public void setAccMdmLevel(String accMdmLevel) {
		this.accMdmLevel = accMdmLevel;
	}
	public String getAccContactID() {
		return accContactID;
	}
	public void setAccContactID(String accContactID) {
		this.accContactID = accContactID;
	}
	public String getAccCountry() {
		return accCountry;
	}
	public void setAccCountry(String accCountry) {
		this.accCountry = accCountry;
	}
	public Locale getAccLocale() {
		return accLocale;
	}
	public void setAccLocale(Locale accLocale) {
		this.accLocale = accLocale;
	}
	public String getAccSoldToNumber() {
		return accSoldToNumber;
	}
	public void setAccSoldToNumber(String accSoldToNumber) {
		this.accSoldToNumber = accSoldToNumber;
	}
	public String getAccPaymentType() {
		return accPaymentType;
	}
	public void setAccPaymentType(String accPaymentType) {
		this.accPaymentType = accPaymentType;
	}
	public String getAccContractNumber() {
		return accContractNumber;
	}
	public void setAccContractNumber(String accContractNumber) {
		this.accContractNumber = accContractNumber;
	}
	public String getAccProductType() {
		return accProductType;
	}
	public void setAccProductType(String accProductType) {
		this.accProductType = accProductType;
	}
	public String getAccPartType() {
		return accPartType;
	}
	public void setAccPartType(String accPartType) {
		this.accPartType = accPartType;
	}
	public String getAccProductModel() {
		return accProductModel;
	}
	public void setAccProductModel(String accProductModel) {
		this.accProductModel = accProductModel;
	}
	public String getAccPartNumber() {
		return accPartNumber;
	}
	public void setAccPartNumber(String accPartNumber) {
		this.accPartNumber = accPartNumber;
	}
	public String getAccAccountId() {
		return accAccountId;
	}
	public void setAccAccountId(String accAccountId) {
		this.accAccountId = accAccountId;
	}
	public String getAccAgreementId() {
		return accAgreementId;
	}
	public void setAccAgreementId(String accAgreementId) {
		this.accAgreementId = accAgreementId;
	}
	public String getAccContactId() {
		return accContactId;
	}
	public void setAccContactId(String accContactId) {
		this.accContactId = accContactId;
	}
	public boolean isAccPortalFlag() {
		return accPortalFlag;
	}
	public void setAccPortalFlag(boolean accPortalFlag) {
		this.accPortalFlag = accPortalFlag;
	}
	public boolean isAccCatalogFlag() {
		return accCatalogFlag;
	}
	public void setAccCatalogFlag(boolean accCatalogFlag) {
		this.accCatalogFlag = accCatalogFlag;
	}
	public boolean isAccHardwareFlag() {
		return accHardwareFlag;
	}
	public void setAccHardwareFlag(boolean accHardwareFlag) {
		this.accHardwareFlag = accHardwareFlag;
	}
	public boolean isAccHardwareSuppliesFlag() {
		return accHardwareSuppliesFlag;
	}
	public void setAccHardwareSuppliesFlag(boolean accHardwareSuppliesFlag) {
		this.accHardwareSuppliesFlag = accHardwareSuppliesFlag;
	}
	public boolean isAccHardwareAccessoriesFlag() {
		return accHardwareAccessoriesFlag;
	}
	public void setAccHardwareAccessoriesFlag(boolean accHardwareAccessoriesFlag) {
		this.accHardwareAccessoriesFlag = accHardwareAccessoriesFlag;
	}
	public Date getAccEffectiveDate() {
		return accEffectiveDate;
	}
	public void setAccEffectiveDate(Date accEffectiveDate) {
		this.accEffectiveDate = accEffectiveDate;
	}
	public String getAccBundleId() {
		return accBundleId;
	}
	public void setAccBundleId(String accBundleId) {
		this.accBundleId = accBundleId;
	}
	public String getBunAgreementId() {
		return bunAgreementId;
	}
	public void setBunAgreementId(String bunAgreementId) {
		this.bunAgreementId = bunAgreementId;
	}
	public String getBunProductType() {
		return bunProductType;
	}
	public void setBunProductType(String bunProductType) {
		this.bunProductType = bunProductType;
	}
	public String getBunProductModel() {
		return bunProductModel;
	}
	public void setBunProductModel(String bunProductModel) {
		this.bunProductModel = bunProductModel;
	}
	public String getBunPartNumber() {
		return bunPartNumber;
	}
	public void setBunPartNumber(String bunPartNumber) {
		this.bunPartNumber = bunPartNumber;
	}
	public String getBunSoldToNumber() {
		return bunSoldToNumber;
	}
	public void setBunSoldToNumber(String bunSoldToNumber) {
		this.bunSoldToNumber = bunSoldToNumber;
	}
	public String getBunPaymentType() {
		return bunPaymentType;
	}
	public void setBunPaymentType(String bunPaymentType) {
		this.bunPaymentType = bunPaymentType;
	}
	public String getBunPartType() {
		return bunPartType;
	}
	public void setBunPartType(String bunPartType) {
		this.bunPartType = bunPartType;
	}
	public String getBunContractNumber() {
		return bunContractNumber;
	}
	public void setBunContractNumber(String bunContractNumber) {
		this.bunContractNumber = bunContractNumber;
	}
	public Date getBunEffectiveDate() {
		return bunEffectiveDate;
	}
	public void setBunEffectiveDate(Date bunEffectiveDate) {
		this.bunEffectiveDate = bunEffectiveDate;
	}
	public String getBunLocationType() {
		return bunLocationType;
	}
	public void setBunLocationType(String bunLocationType) {
		this.bunLocationType = bunLocationType;
	}
	public int getAccStartRecordNumber() {
		return accStartRecordNumber;
	}
	public void setAccStartRecordNumber(int accStartRecordNumber) {
		this.accStartRecordNumber = accStartRecordNumber;
	}
	public int getAccIncrement() {
		return accIncrement;
	}
	public void setAccIncrement(int accIncrement) {
		this.accIncrement = accIncrement;
	}
	public boolean isAccNewQueryIndicator() {
		return accNewQueryIndicator;
	}
	public void setAccNewQueryIndicator(boolean accNewQueryIndicator) {
		this.accNewQueryIndicator = accNewQueryIndicator;
	}
	public int getBunStartRecordNumber() {
		return bunStartRecordNumber;
	}
	public void setBunStartRecordNumber(int bunStartRecordNumber) {
		this.bunStartRecordNumber = bunStartRecordNumber;
	}
	public int getBunIncrement() {
		return bunIncrement;
	}
	public void setBunIncrement(int bunIncrement) {
		this.bunIncrement = bunIncrement;
	}
	public boolean isBunNewQueryIndicator() {
		return bunNewQueryIndicator;
	}
	public void setBunNewQueryIndicator(boolean bunNewQueryIndicator) {
		this.bunNewQueryIndicator = bunNewQueryIndicator;
	}
	public int getSupStartRecordNumber() {
		return supStartRecordNumber;
	}
	public void setSupStartRecordNumber(int supStartRecordNumber) {
		this.supStartRecordNumber = supStartRecordNumber;
	}
	public int getSupIncrement() {
		return supIncrement;
	}
	public void setSupIncrement(int supaIncrement) {
		this.supIncrement = supaIncrement;
	}
	public boolean isSupNewQueryIndicator() {
		return SupNewQueryIndicator;
	}
	public void setSupNewQueryIndicator(boolean supNewQueryIndicator) {
		SupNewQueryIndicator = supNewQueryIndicator;
	}
	public Map<String, Object> getSupSortCriteria() {
		return supSortCriteria;
	}
	public void setSupSortCriteria(Map<String, Object> supSortCriteria) {
		this.supSortCriteria = supSortCriteria;
	}
	
}
