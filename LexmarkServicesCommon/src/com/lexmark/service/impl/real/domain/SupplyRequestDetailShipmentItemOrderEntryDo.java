package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-supplyrequestdetailshipmentitemorderentry-mapping.xml
 * 
 */
public class SupplyRequestDetailShipmentItemOrderEntryDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
