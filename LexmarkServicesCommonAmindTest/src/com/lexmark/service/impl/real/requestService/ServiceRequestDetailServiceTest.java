package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.ServiceRequestDetailService.retainAttachmentsByVisibilityRole;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.impl.real.AmindSiebelQueryTest;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.ServiceRequestDetail;
import com.lexmark.service.impl.real.domain.ServiceRequestDetailAttachmentDo;
import com.lexmark.service.impl.real.requestService.ServiceRequestDetailService;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-27
 */
public class ServiceRequestDetailServiceTest extends AmindSiebelQueryTest {

    @Test
    public void testRetrieveServiceRequest_by_requestNumber() throws Exception {
        ServiceRequestContract c = new ServiceRequestContract();

        String reqNumber = "1-33783029";
        c.setRequestNumber(reqNumber);
        ServiceRequestDetailService service = new ServiceRequestDetailService(c);
//        service.setSession(MiscTest.statelessSession());
        service.setSession(session);
        service.checkRequiredFields();
        service.buildSearchExpression();
        ServiceRequest serviceRequestDetail = service.queryAndGetServiceRequestDetail();
        System.out.println("serviceRequestDetail=" + str(serviceRequestDetail));
        assertEquals(reqNumber, serviceRequestDetail.getServiceRequestNumber());
    }

    @Test
    public void queryServiceRequestDetail() throws Exception {
        sampleSiebelQuery(ServiceRequestDetail.class, 2, 0);
    }
    
    @Test
    public void queryServiceRequestDetailAttachmentDo() throws Exception {
        sampleSiebelQuery(ServiceRequestDetailAttachmentDo.class, 2, 0);
    }
    
    
    /**
     * Domain objects:
     *    ServiceRequestDetail / do-servicerequestdetail-mapping.xml
     * 
     */
    @Test
    public void testQuery() throws Exception {
//        String searchExpression = "[SR Number] = '1-33783029'";
//        String searchExpression = "[serviceRequestNumber] = '1-33783029' AND [attachments] <> ''";
        String searchExpression = "[serviceRequestNumber] = '1-33783029'";
        Class<?> doClass = ServiceRequestDetail.class;
        QueryObject queryObject = new QueryObject(doClass, ActionEvent.QUERY);
        queryObject.setExecutionMode(ExecutionMode.BiDirectional);
        queryObject.setQueryString(searchExpression);
        queryObject.addComponentSearchSpec(doClass, searchExpression);

        List<?> r = dataManager.query(queryObject);
        MiscTest.print(r);
    }

    @Test
    public void testRetrieveServiceRequest_by_requestNumber_with_visibilityRole() throws Exception {
        String reqNumber = "1-33783029";
        String visibilityRole = "Customer";
        ServiceRequestContract c = new ServiceRequestContract();
        c.setRequestNumber(reqNumber);
        c.setVisibilityRole(visibilityRole);

        ServiceRequestDetailService service = new ServiceRequestDetailService(c);
//        service.setSession(MiscTest.statelessSession());
        service.setSession(session);
        service.checkRequiredFields();
        service.buildSearchExpression();
        ServiceRequest serviceRequestDetail = service.queryAndGetServiceRequestDetail();
        System.out.println("serviceRequestDetail=" + str(serviceRequestDetail));
        assertEquals(reqNumber, serviceRequestDetail.getServiceRequestNumber());
    }

    @Test
    public void testRetainAttachmentsByVisibilityRole() throws Exception {
        retainAttachmentsByVisibilityRole(null, "", "");
        ServiceRequestDetailAttachmentDo attach1 = newAttachment("a");
        ServiceRequestDetailAttachmentDo attach2 = newAttachment("c");
        ServiceRequestDetailAttachmentDo attach3 = newAttachment("b");
        ArrayList<ServiceRequestDetailAttachmentDo> attachments = MiscTest.newArrayList(attach1, attach2, attach3);
        retainAttachmentsByVisibilityRole(attachments, "a", "b");
        assertEquals(2, attachments.size());
        assertEquals("a", attachments.get(0).getVisibilityRole());
        assertEquals("b", attachments.get(1).getVisibilityRole());
    }

    private ServiceRequestDetailAttachmentDo newAttachment(String  visibilityRole) {
        ServiceRequestDetailAttachmentDo a  = new ServiceRequestDetailAttachmentDo();
        a.setVisibilityRole(visibilityRole);
        return a;
    }
    
}
