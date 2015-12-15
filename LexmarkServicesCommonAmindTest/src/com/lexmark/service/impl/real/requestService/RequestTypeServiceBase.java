package com.lexmark.service.impl.real.requestService;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindGlobalService;
import com.lexmark.service.impl.real.AmindRequestTypeService;

public abstract class RequestTypeServiceBase {
	protected static final Log logger = LogFactory.getLog(RequestTypeServiceBase.class);
	protected static CrmSessionHandle handle;
	protected static AmindRequestTypeService service;
	protected static StatelessSessionFactory statelessSessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.debug("Connecting to session...");
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		handle = globalService.initCrmSessionHandle(null);
		statelessSessionFactory = TestSessionFactories.newStatelessSessionFactory();
		logger.debug("Creating RequestTypeService...");
		service = new AmindRequestTypeService();
		service.setStatelessSessionFactory(statelessSessionFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("Releasing session...");
		

		if (handle != null) {
			try {
				Session session = ((AmindCrmSessionHandle) handle).acquire();
				if (session != null) {
					session.release();
				}
				((AmindCrmSessionHandle) handle).release();
			} catch (InterruptedException e) {
				// squash
			}
		}
		if(statelessSessionFactory != null){
			statelessSessionFactory.releaseAllStatelessSessions();
		}
	}
	
	protected void debugRequests(List<ServiceRequest> list) throws InterruptedException {
		int count = 10;
		logger.debug("first " + count + " requests");
		for (ServiceRequest request : list) {
			logger.debug("===start===");
			logger.debug("serviceRequestNumber: " + request.getServiceRequestNumber());
			logger.debug("serviceRequestType: " + request.getServiceRequestType());
			logger.debug("serviceRequestDate: " + request.getServiceRequestDate());
			logger.debug("serviceRequestStatus: " + request.getServiceRequestStatus());
			Asset asset = request.getAsset();
			logger.debug("serialNumber: " + asset.getSerialNumber());
			logger.debug("modelNumber: " + asset.getModelNumber());
			logger.debug("problemDescription: " + asset.getProblemDescription());
			logger.debug("expediteOrder: " + request.getExpediteOrder());
			logger.debug("area: " + request.getArea());
			logger.debug("subArea: " + request.getSubArea());
			logger.debug("helpDeskReferenceNumber: " + request.getHelpdeskReferenceNumber());
			logger.debug("eta: " + request.getEta());
			logger.debug("PONumber: " + request.getPoNumber());
			logger.debug("contractType: " + request.getContractType());
			AccountContact primary = request.getPrimaryContact();
			logger.debug("primaryFirstName: " + primary.getFirstName());
			logger.debug("primaryLastName: " + primary.getLastName());
			logger.debug("primaryEmailAddress: " + primary.getEmailAddress());
			logger.debug("primaryWorkPhone: " + primary.getWorkPhone());
			AccountContact requestor = request.getRequestor();
			logger.debug("requestorFirstName: " + requestor.getFirstName());
			logger.debug("requestorLastName: " + requestor.getLastName());
			logger.debug("requestorEmailAddress: " + requestor.getEmailAddress());
			logger.debug("requestorWorkPhone: " + requestor.getWorkPhone());
			GenericAddress address = request.getServiceAddress();
			logger.debug("addressLine1: " + address.getAddressLine1());
			logger.debug("addressLine2: " + address.getAddressLine2());
			logger.debug("addressLine3: " + address.getAddressLine3());
			logger.debug("city: " + address.getCity());
			logger.debug("state: " + address.getState());
			logger.debug("postalCode: " + address.getPostalCode());
			logger.debug("province: " + address.getProvince());
			logger.debug("country: " + address.getCountry());
			logger.debug("addressName: " + address.getAddressName());
			logger.debug("list of parts: ");
			for (Part part : request.getParts()) {
				logger.debug("partNumber: " + part.getPartNumber());
				logger.debug("description: " + part.getDescription());
				logger.debug("partType: " + part.getPartType());
				logger.debug("orderQuantity: " + part.getOrderQuantity());
			}
			logger.debug("end list of parts.");

			logger.debug("====end====");

			count--;
			if (count <= 0) {
				break;
			}
		}
	}
}
