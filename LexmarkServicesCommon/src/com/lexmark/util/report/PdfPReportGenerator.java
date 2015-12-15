/**
 * Copyright (c)2009 Policy Studies Inc. All rights reserved. This source code
 * constitutes confidential and proprietary information of Policy Studies Inc.
 * Access to it is restricted to PSI employees and agents authorized by PSI, and
 * then solely to the extent of such authorization. By accessing this source
 * code, you agree not to modify, copy, transfer, use, distribute, or delete it
 * except as authorized by PSI. Your failure to comply with these restrictions
 * may result in discipline, including termination of employment, may result in
 * severe civil and criminal penalties, and will be prosecuted to the maximum
 * extent possible under the law. 
 */
package com.lexmark.util.report;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.lexmark.domain.ServiceRequest;
import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
*/
public class PdfPReportGenerator extends PdfPageEventHelper{
	private static final int FOOTER_FONT_SIZE = 10;
	private static final int PAGE_MARGIN_LEFT = 36;
	private static final int PAGE_MARGIN_RIGHT = 36;
	private static final int PAGE_MARGIN_TOP = 36;
	private static final int PAGE_MARGIN_BOTTOM = 36;
	private static final int PAGE_BREAK_NUMBER = 2;
	private static final String TEMP_PDF_FILE = "TempPDF.pdf";
	
	private static final int DATATABLE_HEADER_FONT_SIZE = 10;
	private static final int DATATABLE_DATA_FONT_SIZE = 8;
	
	/** The PdfTemplate that contains the total number of pages. */
	private PdfTemplate totalPageTemplate;
	/** The font that will be used. */
	protected BaseFont helvFont;

	private String[] headerNames;
	private String[] columnPatterns;
	private List dataList;
	private String generateDate;
	private Locale locale;
	private Rectangle rectangle;
	
	public PdfPReportGenerator(String[] headerNames,  String[] columnPatterns, 
			List  dataList) {
		this.headerNames = headerNames;
		this.columnPatterns = columnPatterns;
		this.dataList = dataList;
		this.rectangle = PageSize.A4;
		generateDate = DateLocalizationUtil.formatDateLocale(new Date(), getLocale());
	}

	/**
	 * @see com.lowagie.text.pdf.PdfPageEvent#onOpenDocument(com.lowagie.text.pdf.PdfWriter,
	 *      com.lowagie.text.Document)
	 */
	public void onOpenDocument(PdfWriter writer, Document document) {
		totalPageTemplate = writer.getDirectContent().createTemplate(100, 100);
		totalPageTemplate.setBoundingBox(new Rectangle(-20, -20, 100, 100));	
		try {
			helvFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,
					BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}


	/**
	 * @see com.lowagie.text.pdf.PdfPageEvent#onEndPage(com.lowagie.text.pdf.PdfWriter,
	 *      com.lowagie.text.Document)
	 */
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		cb.setFontAndSize(helvFont, FOOTER_FONT_SIZE);

		float textBase = document.bottom() - 20;
		String printedDate = "Printed On: " + generateDate;

		float printLocation = document.left();
		do {
			cb.beginText();
			cb.setTextMatrix(printLocation, textBase);
			cb.showText(printedDate);
			cb.endText();
			cb.saveState();
			printLocation += PageSize.A4.height();
		} while (printLocation < document.right());

		String text = "Page " + writer.getPageNumber() + " of ";
		float textSize = helvFont.getWidthPoint(text, FOOTER_FONT_SIZE);
		float adjust = helvFont.getWidthPoint("0", FOOTER_FONT_SIZE);

		printLocation = document.right();
		do {
			cb.beginText();
			cb.setTextMatrix(printLocation - textSize - adjust, textBase);
			cb.showText(text);
			cb.endText();
			cb.addTemplate(totalPageTemplate, printLocation - adjust, textBase);
			printLocation -= PageSize.A4.height();
		} while (printLocation > 0);

		
		cb.restoreState();
	}

	/**
	 * @see com.lowagie.text.pdf.PdfPageEvent#onCloseDocument(com.lowagie.text.pdf.PdfWriter,
	 *      com.lowagie.text.Document)
	 */
	public void onCloseDocument(PdfWriter writer, Document document) {
		// the total page number can only be decided when all the pages are done when close document
		// so have to use a template to show it on every page
		totalPageTemplate.beginText();
		totalPageTemplate.setFontAndSize(helvFont, FOOTER_FONT_SIZE);
		totalPageTemplate.setTextMatrix(0, 0);
		String totalPage = String.valueOf(writer.getPageNumber() - 1);
		totalPageTemplate.showText(totalPage);
		totalPageTemplate.endText();
	}
	
	/**
	 * Remove the temp PDF file
	 */
	public void removePDF(String fileName) {
		java.io.File tempFile = new java.io.File(fileName);
		tempFile.delete();
	}
	
	/**
	 * Generates a PDF file with a table.
	 *
	 * @param outputStream   the out stream to output the report
	 */
	public void buildPDF(OutputStream outputStream) {
		// step 1: creation of a document-object
		Document document = new Document(this.getRectangle());
					
		try {

			PdfWriter writer = 
				// step 2:
				// we create a writer
				PdfWriter.getInstance(
						// that listens to the document
						document,
						outputStream);
			// step 3: we open the document
			document.setMargins(PAGE_MARGIN_LEFT, PAGE_MARGIN_RIGHT, PAGE_MARGIN_TOP, PAGE_MARGIN_BOTTOM);
			
			writer.setPageEvent(this);
			document.open();

			// step 4: we add a table to the document
			PdfPTable datatable = new PdfPTable(headerNames.length);
			datatable.setWidthPercentage(100);

			PdfPCell defaultCell = datatable.getDefaultCell();
			defaultCell.setBorderWidth(1);
			defaultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			defaultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

			for(String name: headerNames) {
				PdfPCell cell = new PdfPCell(new Phrase(name, 
						FontFactory
						.getFont(FontFactory.HELVETICA, DATATABLE_HEADER_FONT_SIZE, Font.BOLD)));
				cell.setBorderWidth(1);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				datatable.addCell(cell);
			}

			// the header will show on every page
			datatable.setHeaderRows(1);
			
			// we add the data to the table
			ServiceRequest serviceReq=null;
			
			for(int i=0;i < dataList.size(); i++) {
				
				
				/********Removed the below line because of ClassCastException*********/
				//ServiceRequest serviceReq = (ServiceRequest) dataList.get(i);

				/************Fix For the above by Sourav********/
				if(dataList.get(i) instanceof ServiceRequest)
				{
					serviceReq = (ServiceRequest) dataList.get(i);	
				}
				
				for(int columnIndex=0; columnIndex< columnPatterns.length; columnIndex++) {
					String columnValue = "";
					if( columnPatterns[columnIndex].equals("expediteOrder" )){
						
							if(serviceReq != null && serviceReq.getExpediteOrder()!= null && serviceReq.getExpediteOrder().booleanValue()){
								columnValue = "Y";
							}
							
					}else if(serviceReq != null && columnPatterns[columnIndex].equals("serviceRequestType" )){
						columnValue = serviceReq.getServiceRequestType().getValue();
					}else if(serviceReq != null && columnPatterns[columnIndex].equals("area" )){
						columnValue = serviceReq.getArea().getValue();
					}else if(serviceReq != null && columnPatterns[columnIndex].equals("subArea" )){
						columnValue = serviceReq.getSubArea().getValue();
					}else if(serviceReq != null && columnPatterns[columnIndex].equals("serviceRequestStatus" )){
						columnValue = serviceReq.getStatusType().getValue();
					}else if(columnPatterns[columnIndex].contains(":")){
						String[] splitCols=columnPatterns[columnIndex].split(":");
						if("currencyType".equalsIgnoreCase(splitCols[1])){
						StringBuilder sb=new StringBuilder();
						sb.append(BusinessObjectUtil.formatColumn(dataList.get(i), 
								splitCols[0], locale));
						sb.append("(");
						sb.append(BusinessObjectUtil.formatColumn(dataList.get(i), 
								splitCols[1], locale));
						sb.append(")");
						columnValue=sb.toString();
						}else if("formatDate".equalsIgnoreCase(splitCols[1])){
							columnValue = BusinessObjectUtil.formatColumn(dataList.get(i), 
									columnPatterns[columnIndex], locale);
						}
					}else{
						columnValue = BusinessObjectUtil.formatColumn(dataList.get(i), 
						columnPatterns[columnIndex], locale);	
					}
				
				PdfPCell cell = new PdfPCell(new Phrase(columnValue, 
						FontFactory
						.getFont(FontFactory.HELVETICA, DATATABLE_DATA_FONT_SIZE, Font.NORMAL)));
				cell.setBorderWidth(1);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				datatable.addCell(cell);
				
				}
			}
			datatable.setSplitLate(false);
			document.add(datatable);
		} catch (Exception de) {
			throw new RuntimeException(de.getMessage(), de);
		} finally {
			// step 5: we close the document
			if(document!=null){
				document.close();
			}
		}
	}
	
	/**
	 * Generates a PDF file with a table.
	 * In service portal, PDF is generated in A4 size.
	 * In partner portal, PDF is generated in A4 size and pages are spanned. 
	 *
	 * @param outputStream   the out stream to output the report
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public void generate(OutputStream outputStream) {
		if (this.rectangle.equals(PageSize.A4)) {// generate PDF for service portal.
			buildPDF(outputStream);
		} else {// generate PDF for partner portal.
			generatePDFSpanningPage(outputStream);
		}
	}
	
	/**
	 * Generates a PDF whose pages are spanned.
	 * @param outputStream
	 */
	private void generatePDFSpanningPage(OutputStream outputStream) {
		try {
			buildPDF(new FileOutputStream(TEMP_PDF_FILE));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Document document = null;
		try {
			PdfReader reader = new PdfReader(TEMP_PDF_FILE);
			Rectangle newSize = PageSize.A4.rotate();
			document = new Document(newSize, 0, 0, 0, 0);
			PdfWriter writer = PdfWriter
					.getInstance(document, outputStream);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			PdfImportedPage page;
			int total = reader.getNumberOfPages();
			for (int i = 0; i < total;) {
				i++;
				for (int j = 0; j < PAGE_BREAK_NUMBER; j++) {
					float x = -PageSize.A4.height()
							* (j % PAGE_BREAK_NUMBER);
					float y = PageSize.A4.width() * (j / PAGE_BREAK_NUMBER);
					document.newPage();
					page = writer.getImportedPage(reader, i);
					cb.addTemplate(page, 1, 0, 0, 1, x, y);
				}
			}
		} catch (Exception e) {
		} finally {
			if (document != null)
				document.close();
			removePDF(TEMP_PDF_FILE);
		}
	}
	
	public Locale getLocale() {
		if(locale ==null) {
			locale = Locale.getDefault();
		}
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public void setRectangle(Rectangle rectangle){
		this.rectangle = rectangle;
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
}