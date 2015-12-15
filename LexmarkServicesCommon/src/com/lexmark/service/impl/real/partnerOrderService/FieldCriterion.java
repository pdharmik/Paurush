package com.lexmark.service.impl.real.partnerOrderService;

/**
* @author vpetruchok
* @version 1.0, 2012-07-25
*/
class FieldCriterion {
    private String portalField;
    private String siebelMapping;
    private boolean multivaluedFiled = false;
    
    public FieldCriterion(String portalField, String siebelMapping, boolean multivaluedFiled) {
        this.portalField = portalField;
        this.siebelMapping = siebelMapping;
        this.multivaluedFiled = multivaluedFiled;
    }

    public String getPortalField() {
        return portalField;
    }

    public String getSiebelMapping() {
        return siebelMapping;
    }

    public boolean isMultivaluedFiled() {
        return multivaluedFiled;
    }
}
