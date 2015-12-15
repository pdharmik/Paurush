package com.lexmark.portlet;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletSession;
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
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Payment;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exporter.DataExporter;
import com.lexmark.exporter.DataExporterFactory;
import com.lexmark.result.PartnerAgreementListResult;
import com.lexmark.result.PaymentListResult;
import com.lexmark.result.PaymentRequestListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XmlOutputGenerator;

@Controller
@RequestMapping("VIEW")
public class PaymentRequestController extends BaseController {
	private static Logger logger = LogManager.getLogger(PaymentRequestController.class);

	private static final String KEY_PAYMENTS_DOWN_LOAD_CONTRACT = "paymentsDownLoadContract";
	private static final String KEY_PAYMENT_REQUEST_DOWN_LOAD_CONTRACT = "paymentRequestDownLoadContract";
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private PaymentsService paymentsService;
	@Autowired
	private GridSettingController gridSettingController;
	

	@RequestMapping
	public String showPaymentRequestList(RenderRequest request, RenderResponse response, Model model) throws Exception {
		logger.debug("[Start] showPaymentRequestList");

		Locale locale = request.getLocale();
		Map<String, String> paymentRequestStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.SERVICE_STATUS.getValue(), null, serviceRequestLocaleService, locale);
		Map<String, String> paymentStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_PAYMENT_STATUS.getValue(), null, serviceRequestLocaleService,
				locale);

		model.addAttribute("paymentRequestStatusMap", paymentRequestStatusMap);
		model.addAttribute("paymentStatusMap", paymentStatusMap);

		addAttributeToModel("gridRLVPaymentRequestList", request, model);

		logger.debug("[End] showPaymentRequestList");
		return "payment/paymentRequestListView";
	}

	@ResourceMapping("retrievePaymentRequestList")
	public String getPaymentRequestList(ResourceRequest request, ResourceResponse response, Model model)
			throws Exception {
		logger.debug("[Start] retrievePaymentRequestList");
		PaymentRequestListContract contract = ContractFactory.createPaymentRequestListContract(request);
		PortletSession session = request.getPortletSession();
		session.setAttribute(KEY_PAYMENT_REQUEST_DOWN_LOAD_CONTRACT, contract.clone());

		logger.debug("startPos = " + contract.getStartRecordNumber());

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, logger);
		logger.debug("SESSION HANDLE = " + contract.getSessionHandle());

		PaymentRequestListResult paymentRequestListResult = null;
		try {
			paymentRequestListResult = paymentsService.retrievePaymentRequestList(contract);
		} catch (Exception e) {
			logger.error("Exception while retrieving payment request list, The root cause is " + e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.generalError.title",
					"exception.siebel.retrieveListException", request.getLocale());
			return null;
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		Locale locale = request.getLocale();

		List<Activity> paymentRequestList = paymentRequestListResult.getPaymentRequestList();
		if (paymentRequestList != null)
			ControllerUtil.batchLocalizePaymentRequest(paymentRequestList, serviceRequestLocaleService, locale);
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, Float.valueOf(timezoneOffset).floatValue());
		model.addAttribute("startPos", contract.getStartRecordNumber());
		model.addAttribute("totalcount", paymentRequestListResult.getTotalcount());
		model.addAttribute("activityList", paymentRequestListResult.getPaymentRequestList());

		response.setContentType("text/xml");

		logger.debug("[End] retrievePaymentRequestList");
		return "payment/xml/paymentRequestListXML";
	}

	@RequestMapping(params = "action=showPaymentListPage")
	public String showPaymentList(RenderRequest request, RenderResponse response, Model model) throws Exception {
		logger.debug("[Start] showPaymentPaymentList");
		addAttributeToModel("gridPLVPaymentListGrid", request, model);
		logger.debug("[End] showPaymentPaymentList");
		return "payment/paymentListView";
	}

	private void addAttributeToModel(String gridId, RenderRequest request, Model model) throws Exception {
		gridSettingController.retrieveGridSetting(gridId, request, model);
		PartnerAgreementListContract contract = ContractFactory.createPartnerAgreementListContract(request);
		PartnerAgreementListResult result = globalService.retrievePartnerAgreementList(contract);
		if (result.getPartnerAgreementList() != null && !result.getPartnerAgreementList().isEmpty()) {
			XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(request.getLocale());
			String partnerAgreementsXML = xmlOutputGenerator.convertPartnerAgreementToXML(result
					.getPartnerAgreementList());
			model.addAttribute("partnerAgreementsXML", partnerAgreementsXML);
		}
	}

	@ResourceMapping("retrievePaymentList")
	public String getPaymentList(ResourceRequest request, ResourceResponse response, Model model) throws Exception {
		logger.debug("[Start] getPaymentList");
		PaymentListContract contract = ContractFactory.createPaymentListContract(request);
		request.getPortletSession().setAttribute(KEY_PAYMENTS_DOWN_LOAD_CONTRACT, contract.clone());
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, logger);
		PaymentListResult raymentListResult = null;
		try {
			raymentListResult = paymentsService.retreivePaymentList(contract);
		} catch (Exception e) {
			logger.error("Exception while retrieving payment list, The root cause is " + e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.generalError.title",
					"exception.siebel.retrieveListException", request.getLocale());
			return null;
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, Float.valueOf(timezoneOffset).floatValue());
		model.addAttribute("startPos", contract.getStartRecordNumber());
		model.addAttribute("totalcount", raymentListResult.getTotalcount());
		model.addAttribute("paymentList", raymentListResult.getPaymentList());
		response.setContentType("text/xml");
		logger.debug("[End] getPaymentList");
		return "payment/xml/paymentListXML";
	}

	/**
	 * retrieve Payment Requests and down load it csv and pdf format.
	 * 
	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("downloadPaymentRequests")
	public void exportPaymentRequests(ResourceRequest request, ResourceResponse response, Model model) throws Exception {
		logger.debug("[START] exportPaymentRequests");
		String downloadType = request.getParameter("downloadType");
		float timezoneOffset = 0;
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		if (!StringUtils.isEmpty(timezoneOffsetStr)) {
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
		}
		PortletSession session = request.getPortletSession();
		PaymentRequestListContract contract = (PaymentRequestListContract) session
				.getAttribute(KEY_PAYMENT_REQUEST_DOWN_LOAD_CONTRACT);
		session.setAttribute(KEY_PAYMENT_REQUEST_DOWN_LOAD_CONTRACT, contract.clone());

		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			PaymentRequestListResult result = paymentsService.retrievePaymentRequestList(contract);
			List<Activity> requestList = result.getPaymentRequestList();
			localizeDate(requestList, timezoneOffset);
			DataExporter dataExporter = DataExporterFactory.getDataExporter(downloadType);
			dataExporter.setLocale(request.getLocale());
			dataExporter.export(response, "payment.request", LexmarkPPConstants.PAYMENT_REQUEST_EXPORT, result
					.getPaymentRequestList());
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("[END] exportPaymentRequests");
	}

	private void localizeDate(List<Activity> activityList, float timezoneOffset) {
		for (Activity activity : activityList) {
			TimezoneUtil.adjustDate(activity.getActivityDate(), (0 - timezoneOffset));
		}
	}

	@RequestMapping(params = "action=printPaymentRequests")
	public String showPaymentRequestPrintPage() {
		return "payment/paymentRequestPrint";
	}

	/**
	 * retrieve Services Requests and down load it csv and pdf format.
	 * 
	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("downloadPayments")
	public void exportPayments(ResourceRequest request, ResourceResponse response, Model model) throws Exception {
		logger.debug("[START] exportPayments");
		String downloadType = request.getParameter("downloadType");
		float timezoneOffset = 0;
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		if (!StringUtils.isEmpty(timezoneOffsetStr)) {
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
		}
		PortletSession session = request.getPortletSession();
		PaymentListContract contract = (PaymentListContract) session.getAttribute(KEY_PAYMENTS_DOWN_LOAD_CONTRACT);
		session.setAttribute(KEY_PAYMENTS_DOWN_LOAD_CONTRACT, contract.clone());
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			PaymentListResult result = paymentsService.retreivePaymentList(contract);
			List<Payment> resultList = result.getPaymentList();
			localizeDateForPayment(resultList, timezoneOffset);
			DataExporter dataExporter = DataExporterFactory.getDataExporter(downloadType);
			dataExporter.setLocale(request.getLocale());
			dataExporter.export(response, "payment.payment", LexmarkPPConstants.PATTERNS_PAYMENT_PAYMENT_EXPORT,
					result.getPaymentList());
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("[END] exportPayments");
	}

	private void localizeDateForPayment(List<Payment> paymentList, float timezoneOffset) {
		for (Payment payment : paymentList) {
			TimezoneUtil.adjustDate(payment.getDateCreated(), (0 - timezoneOffset));
		}
	}

	@RequestMapping(params = "action=printPayments")
	public String showPaymentsPrintPage() {
		return "payment/paymentListPrint";
	}
}
