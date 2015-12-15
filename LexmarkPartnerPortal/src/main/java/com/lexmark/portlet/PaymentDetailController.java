package com.lexmark.portlet;

import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Payment;
import com.lexmark.exporter.PdfDataExporter;
import com.lexmark.form.PaymentDetailForm;
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;

@Controller
@RequestMapping("VIEW")
public class PaymentDetailController extends BaseController {
	private static Logger logger = LogManager.getLogger(PaymentDetailController.class);
	@Autowired
	private PaymentsService paymentService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private GridSettingController gridSettingController;

	@RequestMapping(params = "action=showPaymentDetail")
	public String showPaymentDetail(Model model, RenderRequest request, RenderResponse response,
			@RequestParam("paymentId") String paymentId) throws Exception {
		PaymentDetailsContract contract = ContractFactory.createPaymentDetailsContract(paymentId, request);
		PaymentDetailsResult result = paymentService.retrievePaymentDetails(contract);
		PaymentDetailForm form = new PaymentDetailForm();
		if (result != null && result.getPayment() != null) {
			Payment payment = result.getPayment();
			form.setPayment(payment);
		} else {
			throw new IllegalArgumentException("Failed to open Payment Detail page: <br/>payment is null");
		}
		boolean allowAddtionalPaymentRequest = PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(request
				.getPortletSession());
		form.setAllowAdditionalPaymentRequest(allowAddtionalPaymentRequest);

		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, Float.valueOf(timezoneOffset).floatValue());
		model.addAttribute("paymentDetail", form);
		model.addAttribute("from", request.getParameter("from"));
		gridSettingController.retrieveGridSetting("gridPDVPaymentDetails", request, model);

		return "payment/paymentDetailView";
	}

	@ResourceMapping("getPaymentLineItemList")
	public String getPaymentLineItemList(ResourceRequest request, ResourceResponse response, Model model)
			throws Exception {
		PaymentLineItemDetailsContract contract = ContractFactory.createPaymentLineItemDetailsContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		PaymentLineItemDetailsResult result = null;
		try {
			result = paymentService.retreivePaymentLineItemList(contract);
		} catch (Exception e) {
			logger.error("Exception while retrieving payment line item list, The root cause is " + e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.generalError.title",
					"exception.siebel.retrieveListException", request.getLocale());
			return null;
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, Float.valueOf(timezoneOffset).floatValue());
		model.addAttribute("paymentLineItemList", result.getActivities());
		model.addAttribute("startPos", contract.getStartRecordNumber());
		model.addAttribute("totalCount", result.getTotalCount());
		model.addAttribute("paymentId", contract.getPaymentId());
		response.setContentType("text/xml");
		return "payment/xml/paymentLineItemListXML";
	}

	@RequestMapping(params = "action=printPaymentDetails")
	public String printPaymentDetails() {
		return "payment/paymentDetailPrint";
	}

	@ResourceMapping("downPaymentDetailPdfURL")
	public void downPaymentDetailPdfURL(Model model, ResourceRequest request, ResourceResponse response)
			throws Exception {
		String paymentId = request.getParameter("paymentId");
		PaymentDetailsContract contract = ContractFactory.createPaymentDetailsContract(paymentId, request);
		PaymentDetailsResult result = paymentService.retrievePaymentDetails(contract);
		Payment payment = result.getPayment();

		PaymentLineItemDetailsContract paymentLineItemDetailsContract = ContractFactory
				.createPaymentLineItemDetailsContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		paymentLineItemDetailsContract.setSessionHandle(crmSessionHandle);
		PaymentLineItemDetailsResult paymentLineItemDetailsResult = paymentService
				.retreivePaymentLineItemList(paymentLineItemDetailsContract);
		List<Activity> activityList = paymentLineItemDetailsResult.getActivities();

		float timezoneOffset = 0;
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		if (!StringUtils.isEmpty(timezoneOffsetStr)) {
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
		}
		Locale locale = request.getLocale();
		PdfDataExporter pdfDataExporter = new PdfDataExporter();
		pdfDataExporter.setLocale(locale);
		pdfDataExporter.setTimezoneOffset(timezoneOffset);
		pdfDataExporter.generatePaymentDetailPdf(payment, activityList, response);

	}

}
