package com.lexmark.service.impl.real.partnerOrderService;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* @author vpetruchok
* @version 1.0, 2012-07-25
*/
public class FieldCriteria {
     Set<FieldCriterion> criterias = new HashSet<FieldCriterion>();
    
    FieldCriteria add(String portalField, String siebelMapping, boolean multivaluedFiled)  {
        criterias.add(new FieldCriterion(portalField, siebelMapping, multivaluedFiled));
        return this;
    }
    
    FieldCriteria add(String portalField, String siebelMapping)  {
        return add(portalField, siebelMapping, false);
    }
    

    public Map<String, String> fieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        for (FieldCriterion fc : criterias) {
            if (!fc.isMultivaluedFiled()) {
                m.put(fc.getPortalField(), fc.getSiebelMapping());
            }
        } 
        return Collections.unmodifiableMap(m);
    }
    
    public Map<String, String> multiValuedFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        for (FieldCriterion fc : criterias) {
            if (fc.isMultivaluedFiled()) {
                m.put(fc.getPortalField(), fc.getSiebelMapping());
            }
        } 
        return Collections.unmodifiableMap(m);
    }

    public Map<String, String> allFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        for (FieldCriterion fc : criterias) {
            m.put(fc.getPortalField(), fc.getSiebelMapping());
        } 
        return Collections.unmodifiableMap(m); 
    }
}