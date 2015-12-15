package com.lexmark.service.impl.real.partnerPaymentService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.domain.PartnerAgreement;
import com.lexmark.result.PartnerAgreementListResult;
import com.lexmark.service.impl.real.GlobalServiceStatelessBase;

@RunWith(Parameterized.class)
public class PartnerAgreementListTest extends GlobalServiceStatelessBase {

	private final PartnerAgreementListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "50901", "Legal" });
		return list;
	}

	public PartnerAgreementListTest(String mdmId, String mdmLevel) {
		contract = new PartnerAgreementListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
	}

	@Test
	public void testRetrieveAgreementList() throws Exception {
		PartnerAgreementListResult result = globalService.retrievePartnerAgreementList(contract);
		assertNotNull("result is null!", result);
		List<PartnerAgreement> agreementList = result.getPartnerAgreementList();
		assertNotNull("agreement list is null!", agreementList);
		assertFalse("agreement list is empty!", agreementList.isEmpty());
		logAgreementList(agreementList);
	}

	private void logAgreementList(List<PartnerAgreement> agreementList) {
		for (PartnerAgreement agreement : agreementList) {
			logger.debug("partnerAgreementId: " + agreement.getPartnerAgreementId());
			logger.debug("partnerAgreementName: " + agreement.getPartnerAgreementName());
		}
	}

}
