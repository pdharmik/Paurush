package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-ordersuppliesassetpagecountreading-mapping.xml 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-05-28
 */
public class OrderSuppliesAssetPageCountReadingDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5185757314085784258L;
    
    private String reading;
    private String timestamp; // this is String type 

    
    public OrderSuppliesAssetPageCountReadingDo() {
    }

 /*   @Override
    public int hashCode() {
        return  31 + LangUtil.hashCode(reading) + LangUtil.hashCode(timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof OrderSuppliesAssetPageCountReadingDo)) {
            return false;
        }
        OrderSuppliesAssetPageCountReadingDo that = (OrderSuppliesAssetPageCountReadingDo) obj;
        return  1 == 1
                  && LangUtil.equal(this.reading, that.reading)
                  && LangUtil.equal(this.timestamp, that.timestamp);
    }
*/
    @Override
    public String toString() {
        return "ReadingDo[reading=" + reading + ", timestamp=" + timestamp + "]";
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String name) {
        this.reading = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
