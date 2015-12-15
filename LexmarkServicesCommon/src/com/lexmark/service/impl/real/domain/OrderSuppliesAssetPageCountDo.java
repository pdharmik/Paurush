package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * 
 * The logic is similar to com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsBCDo. 
 * 
 * The mapping file: do-ordersuppliesassetpagecount-mapping.xml . 
 * <p />
 *  
 * @see com.lexmark.service.impl.real.domain#OrderSuppliesAssetDetailDo 
 * @see com.lexmark.service.impl.real.domain.AssetReadingLatestBase 
 * @see com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsBCDo 
 * @see com.lexmark.service.impl.real.domain.AccountAssetDetailDo
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest#queryOrderSuppliesAssetPageCountDo() 
 */
public class OrderSuppliesAssetPageCountDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -4600971563716264696L;

	private String name;
	private ArrayList<OrderSuppliesAssetPageCountReadingDo> latestReadings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OrderSuppliesAssetPageCountReadingDo> getLatestReadings() {
        return latestReadings;
    }

    public void setLatestReadings(ArrayList<OrderSuppliesAssetPageCountReadingDo> latestReadings) {
        this.latestReadings = latestReadings;
    }
}
