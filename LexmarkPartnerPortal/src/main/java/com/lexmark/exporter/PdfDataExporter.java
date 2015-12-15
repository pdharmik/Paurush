package com.lexmark.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceResponse;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lexmark.domain.Order;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Payment;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.report.PdfPReportGenerator;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;


public class PdfDataExporter implements DataExporter {

	private static final String SUFFIX_PDF = ".pdf";
	private Locale locale = null;
	private float timezoneOffset = 0;
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void export(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException {
		if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(dataType)) {
			throw new IllegalArgumentException("The data type[" + dataType + "] is not supported of PdfDataExporter");
		}

		String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(dataType);
		String fileName = DownloadFileLocalizationUtil.getPropertyLocaleValue(resourceLabelName, locale) + SUFFIX_PDF;
		String headerLabelName = HEADER_RESOURCE_LABELS_MAP.get(dataType);
		String[] headers = DownloadFileLocalizationUtil.getPropertyNormal(DownloadFileLocalizationUtil.BUNDLE_NAME,headerLabelName, locale).split(",");

		response.setProperty("Content-disposition", "attachment; filename=" + fileName);
		response.setContentType("application/pdf");
		PartnerPdfPReportGenerator generator = new PartnerPdfPReportGenerator(headers, columnPatterns, dataList);
		generator.setRectangle(new Rectangle(1684.0F,595.0F));
		generator.setLocale(locale);
		OutputStream out = response.getPortletOutputStream();
		try {
			generator.generate(out);
			 
		} catch (Exception e) {
			
		}
		finally {
			try {
				out.close();
			} catch (IOException ignored) {
				throw new IOException(ignored.getLocalizedMessage());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void exportOrder(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException {
		if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(dataType)) {
			throw new IllegalArgumentException("The data type[" + dataType + "] is not supported of PdfDataExporter");
		}

		String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(dataType);
		String fileName = DownloadFileLocalizationUtil.getPropertyLocaleValue(resourceLabelName, locale) + SUFFIX_PDF;
		String headerLabelName = HEADER_RESOURCE_LABELS_MAP.get(dataType);
		String[] headers = DownloadFileLocalizationUtil.getPropertyNormal(DownloadFileLocalizationUtil.BUNDLE_NAME,headerLabelName, locale).split(",");
		//Character encoding attached for Defect #7854
		response.setProperty("Content-disposition", "attachment; filename=\""
				+ fileName+"\"");
		response.setContentType("application/pdf;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PartnerPdfPReportGenerator generator = new PartnerPdfPReportGenerator(headers, columnPatterns, dataList);
		generator.setRectangle(new Rectangle(1684.0F,595.0F));
		generator.setLocale(locale);
		OutputStream out = response.getPortletOutputStream();
		try {
			generator.generate(out);
			 
		} catch (Exception e) {
			
		}
		finally {
			try {
				out.close();
			} catch (IOException ignored) {
				throw new IOException(ignored.getLocalizedMessage());
			}
		}
	}
	
	public void generateClaimDetailPdf(Activity activity,List<ServiceRequestActivity> serviceRequestActivityList,List<Activity> serviceHistoryList,ResourceResponse response) throws Exception{
		PartnerPDFTemplate template = new PartnerPDFTemplate();
		template.setTimezoneOffset(timezoneOffset);
		template.setLocale(locale);
		String context = template.generateClaimDetailString(activity,serviceRequestActivityList,serviceHistoryList);
		String fileName = "claimDetail_" + activity.getActivityId() + PdfDataExporter.SUFFIX_PDF;
		outputPDF(context,fileName,response);
	}
	
	//Added By MPS Offshore Team for Service Order Detail Print in PDF
	public void generateOrderDetailPdf(Order order,ResourceResponse response) throws Exception{
		PartnerPDFTemplate template = new PartnerPDFTemplate();
		template.setTimezoneOffset(timezoneOffset);
		template.setLocale(locale);
		String context = template.generateOrderDetailString(order);
		String fileName = "orderDetail_" + order.getOrderNumber() + PdfDataExporter.SUFFIX_PDF;
		outputPDF(context,fileName,response);
	}
	
	public void generateServiceRequestDetailPdf(Activity activity,List<ServiceRequestActivity> serviceRequestActivityList,List<Activity> serviceHistoryList,ResourceResponse response) throws Exception{
		PartnerPDFTemplate template = new PartnerPDFTemplate();
		template.setTimezoneOffset(timezoneOffset);
		template.setLocale(locale);
		String context = template.generateServiceRequestDetailString(activity,serviceRequestActivityList,serviceHistoryList);
		String fileName = "serviceRequestDetail_" + activity.getActivityId() + PdfDataExporter.SUFFIX_PDF;
		outputPDF(context,fileName,response);
	}
	
	public void generatePaymentDetailPdf(Payment payment ,List<Activity> activity ,ResourceResponse response) throws Exception{
		PartnerPDFTemplate template = new PartnerPDFTemplate();
		template.setTimezoneOffset(timezoneOffset);
		template.setLocale(locale);
		String context = template.generatePaymentDetailString(payment, activity);
		context = context.replaceAll("&", "&amp;");
		String fileName = "paymentDetail_" + payment.getPaymentId() + PdfDataExporter.SUFFIX_PDF;
		outputPDF(context, fileName, response);
	}
	
	private void outputPDF(String PDFString, String fileName, ResourceResponse response)throws Exception{
		response.setProperty("Content-disposition", "attachment; filename=" + fileName);
		response.setContentType("application/pdf");
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont(LexmarkPPConstants.FONT_PATH,BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        if(PDFString.contains(" & "))
		{
			PDFString = PDFString.replaceAll(" & ", " &amp;");
		}
		renderer.setDocumentFromString(PDFString);
		OutputStream os = response.getPortletOutputStream();
		try {
			renderer.layout();
			renderer.createPDF(os);
		}finally{
			os.flush();
			os.close();
		}
	}

	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportRequestClaims(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException {}
}
