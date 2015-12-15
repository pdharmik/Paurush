package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;


/**
 * @see com.lexmark.service.impl.real.domain.PartnerRequestOrderDo
 * @see com.lexmark.service.impl.real.domain.PartnerRequestOrderDoTest
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-18
 */
public class OrderLineItemDoTest {
    
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-orderlineitem-mapping.xml");
    }
    
    
    FieldEntry[] fieldEntries;
    
    
    /**
     * These mappings are from Vendor_Management_OrderManagement_070_10_12.docx
     * 
     */
    @Before
    public void setUp() throws Exception {
        fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),

                xmltag("partNumber", "LXK MPS Part Number"),
                xmltag("description", "LXK MPS Product Description"),
                xmltag("partType", "LXK MPS Product Type"),
                xmltag("orderedQuantity", "Quantity"),
                xmltag("status", "Status"),   
                xmltag("vendorId", "LXK MPS Account Id"), 
                xmltag("contactMethod", "LXK SD LP Contact Method"), 
                xmltag("", ""),
        };
    }
    
    @Test
    public void genXmlMappings() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
    
    @Test
    public void genJavaFileds() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toJavaFieldDeclaration());
        }
    }    

    
   @Test 
   public void queryOrderLineItem() {
       MiscTest.sampleSiebelQuery(OrderLineItemDo.class, 5, 0);
   }
   
   @Test 
   public void queryOrderLineItem2() {
       List<?> lst = MiscTest.querySiebel(OrderLineItemDo.class, "", 5, 0);
       MiscTest.print(lst);
   }
   
   @Test 
   public void queryOrderLineItem3() {
       Class<?> class1 = OrderLineItemDo.class;
       QueryObject qo = new QueryObject(class1, ActionEvent.QUERY);
       qo.setExecutionMode(ExecutionMode.BiDirectional);
       qo.setNumRows(0);
       qo.setStartRowIndex(2);
//       qo.addComponentSearchSpec(class1, ""); // TODO(Viktor) discuss
       
       List<?> lst = MiscTest.query(qo);
       MiscTest.print(lst);
   }

}
