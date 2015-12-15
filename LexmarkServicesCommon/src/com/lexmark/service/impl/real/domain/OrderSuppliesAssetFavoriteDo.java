package com.lexmark.service.impl.real.domain;


/**
 * Corresponding mapping file  - do-ordersuppliesassetfavoritedo-mapping.xml .
 * 
 * @see com.lexmark.service.impl.real.domain.OrderSuppliesAssetDo
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-16
 */
@SuppressWarnings("serial")
public class OrderSuppliesAssetFavoriteDo extends OrderSuppliesAssetDo {
    
    public static final String IO = "LXK MPS SW Contact Favorite Entitled Assets";
    public static final String IC = "LXK MPS SW Contact Favorite Entitled Assets";
    public static final String BO = "LXK MPS SW Consumable Asset List";
    public static final String BC = "LXK MPS SW Contact Favorite Entitled Assets";    
    public static final String SIEBEL_MDM_LEVEL_FILTER_FIELD = "LXK MPS Account Level";
    
    private Boolean assetFavFlag;
    private String agreementId;
    private String mdmLevel;

    public Boolean isAssetFavFlag() {
        return assetFavFlag;
    }

    public void setAssetFavFlag(Boolean assetFavFlag) {
        this.assetFavFlag = assetFavFlag;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getMdmLevel() {
        return mdmLevel;
    }

    public void setMdmLevel(String mdmLevel) {
        this.mdmLevel = mdmLevel;
    }

}
