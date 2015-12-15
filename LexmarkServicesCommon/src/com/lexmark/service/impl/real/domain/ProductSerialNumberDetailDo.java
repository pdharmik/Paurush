package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import com.amind.common.domain.BaseEntity;
/**
 * The mapping file: do-productSerialNumber-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain.ProductSerialNumberDetailDo
 * 
 * */
public class ProductSerialNumberDetailDo extends BaseEntity implements Serializable{

	    private String serialNumber;
	    private String productNo;

	 

	    public void setserialNumber(String serialNumber) {
	        this.serialNumber= serialNumber;
	        
	    }
	    public String getserialNumber(){
	        return serialNumber;
	    }
	   
	    public void setmodelNumber(String productNo) {
	        this.productNo= productNo;
	    }
	    public String getproductNo() 
	    {
	        return productNo;
	    }
}
