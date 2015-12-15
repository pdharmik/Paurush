package com.lexmark.services.mock;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.lexmark.contract.TaxContract;
import com.lexmark.domain.Price;
import com.lexmark.result.TaxResult;
import com.lexmark.services.api.om.RetrieveTaxService;

public class RetrieveTaxServiceImplMock implements RetrieveTaxService{
private static final Logger LOGGER = LogManager.getLogger(RetrieveTaxServiceImplMock.class);        
       @Override
       public TaxResult retrieveTaxList(TaxContract contract)
                       throws Exception {
               
               TaxResult taxResult = new TaxResult();
               List<Price> taxInfoList = new ArrayList<Price>();
               for(Price lineInfo: contract.getLineInformationList()){        
                       Price lineInfoElement = new Price();
                       lineInfoElement.setPartNumber(lineInfo.getPartNumber());
                       lineInfoElement.setPrice(lineInfo.getPrice());        
                       lineInfoElement.setSourceReferenceLineId(lineInfo.getSourceReferenceLineId());
                       lineInfoElement.setTax("11.80");
                       taxInfoList.add(lineInfoElement);
               }                
               taxResult.setLineInformationList(taxInfoList);                
               return taxResult;
       }        
}