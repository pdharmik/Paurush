package com.lexmark.webservice.impl.real;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.holders.IntHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARD;
import com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARDTYPES;
import com.lexmark.SAPWebServices.AP.invoice.client.BAPIRET1;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ADDSEL;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ALLOCATION;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CONTROL;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CREDITS;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_DM_ALLOCATION;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INIT_ADD_DATA;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INVOICE;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ITEM;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MESSAGES;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MYPAYMENTS;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYALLOCATION;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYEXPLANATION;
import com.lexmark.SAPWebServices.AP.invoice.client.EBPP_TOTALS;
import com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMDISPUTES;
import com.lexmark.SAPWebServices.AP.invoice.client.FIN_BANKID;
import com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATAProxy;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.BAPIRET1Holder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_CREDITSHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_PARTNERHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDTYPESHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ALLOCATIONHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_DM_ALLOCATIONHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INIT_ADD_DATAHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INVOICEHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ITEMHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MESSAGESHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MYPAYMENTSHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYALLOCATIONHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYEXPLANATIONHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_TOTALSHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FDM_EBPP_DMDISPUTESHolder;
import com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FIN_BANKIDHolder;
import com.lexmark.contract.InvoiceListContract;
import com.lexmark.domain.Invoice;
import com.lexmark.result.InvoiceListResult;
import com.lexmark.util.ProxyManager;
import com.lexmark.util.StringUtil;
import com.lexmark.webservice.api.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService{
	
	public static DateFormat DATE_FORMAT_SAP = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat INVOICE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	
	private static Logger logger = LogManager.getLogger(InvoiceServiceImpl.class);

	private String endpointInvoice;
	private String userName; 
	private String password;
	
	
	
	public String getEndpointInvoice() {
		return endpointInvoice;
	}

	public void setEndpointInvoice(String endpointInvoice) {
		this.endpointInvoice = endpointInvoice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public InvoiceListResult retrieveInvoiceList(InvoiceListContract _contract) throws Exception {
		
		logger.debug("Entering Invoice List IMPL");	
		
		ZG_AP_GET_DATAProxy proxy = ProxyManager.getInvoiceListProxy(getEndpointInvoice(),getUserName(),getPassword());
		
		String due_date_low = fomatWSDate(_contract.getFromDate());
		String due_date_high = fomatWSDate(_contract.getToDate());
		
		
		BAPIRET1Holder BAPIHolder = new BAPIRET1Holder();
		TABLE_OF_EBPP_INVOICEHolder invoiceHolder = new TABLE_OF_EBPP_INVOICEHolder();
		TABLE_OF_EBPP_MYPAYMENTSHolder paymentsHolder = new TABLE_OF_EBPP_MYPAYMENTSHolder();
		TABLE_OF_EBPP_ALLOCATIONHolder allocationHolder = new TABLE_OF_EBPP_ALLOCATIONHolder();
		TABLE_OF_FIN_BANKIDHolder bankHolder = new TABLE_OF_FIN_BANKIDHolder();
		TABLE_OF_APAR_EBPP_CARDHolder cardHolder = new TABLE_OF_APAR_EBPP_CARDHolder();
		TABLE_OF_APAR_EBPP_CARDTYPESHolder cardTypesHolder = new TABLE_OF_APAR_EBPP_CARDTYPESHolder();
		TABLE_OF_FDM_EBPP_DMDISPUTESHolder disputesHolder = new TABLE_OF_FDM_EBPP_DMDISPUTESHolder();
		TABLE_OF_EBPP_DM_ALLOCATIONHolder DMallocationHolder = new TABLE_OF_EBPP_DM_ALLOCATIONHolder();
		TABLE_OF_EBPP_INIT_ADD_DATAHolder dataHolder = new TABLE_OF_EBPP_INIT_ADD_DATAHolder();
		TABLE_OF_EBPP_ITEMHolder itemHolder = new TABLE_OF_EBPP_ITEMHolder();
		TABLE_OF_EBPP_MESSAGESHolder messagesHolder = new TABLE_OF_EBPP_MESSAGESHolder();
		TABLE_OF_EBPP_PAYALLOCATIONHolder payAllocationHolder = new TABLE_OF_EBPP_PAYALLOCATIONHolder();
		TABLE_OF_EBPP_PAYEXPLANATIONHolder payExplanationHolder = new TABLE_OF_EBPP_PAYEXPLANATIONHolder();
		TABLE_OF_EBPP_TOTALSHolder totalsHolder = new TABLE_OF_EBPP_TOTALSHolder();
		EBPP_CREDITSHolder creditsHolder = new EBPP_CREDITSHolder();
		EBPP_PARTNERHolder partnerHolder = new EBPP_PARTNERHolder();
		IntHolder returnCode = new IntHolder();
		
		
		
		BAPIHolder.value = new BAPIRET1();
		invoiceHolder.value = new EBPP_INVOICE[0];
		paymentsHolder.value = new EBPP_MYPAYMENTS[0];
		allocationHolder.value = new EBPP_ALLOCATION[0];
		bankHolder.value = new FIN_BANKID[0];
		cardHolder.value = new APAR_EBPP_CARD[0];
		cardTypesHolder.value = new APAR_EBPP_CARDTYPES[0];
		disputesHolder.value = new FDM_EBPP_DMDISPUTES[0];
		DMallocationHolder.value = new EBPP_DM_ALLOCATION[0];
		dataHolder.value = new EBPP_INIT_ADD_DATA[0];
		itemHolder.value = new EBPP_ITEM[0];
		messagesHolder.value = new EBPP_MESSAGES[0];
		payAllocationHolder.value = new EBPP_PAYALLOCATION[0];
		payExplanationHolder.value = new EBPP_PAYEXPLANATION[0];
		totalsHolder.value = new EBPP_TOTALS[0];
		creditsHolder.value = new EBPP_CREDITS();
		partnerHolder.value = new EBPP_PARTNER();
		
		
//		EBPP_ADDSEL i_ADDSEL = new EBPP_ADDSEL("01","5050"," "," ");
		
		EBPP_ADDSEL i_ADDSEL = new EBPP_ADDSEL();
		
		/*TO DO Need to remove comment below 2 lines for real data*/
		i_ADDSEL.setORGTYPE("01");
		i_ADDSEL.setORGKEY(_contract.getCompanyCode());
		i_ADDSEL.setADDSELTYPE(" ");
		i_ADDSEL.setADDSELKEY(" ");
		
		
//		i_ADDSEL.setORGTYPE("01");
//		i_ADDSEL.setORGKEY("5050");
		
//		EBPP_CONTROL i_CONTROLDATA = new EBPP_CONTROL("2010-09-01","2010-10-15","X","X"," ","X"," "," ","X"," "," "," "," "," "," "," ",null,null,0);
		/*TO DO Need to remove comment below 2 lines for real data*/
		
		EBPP_CONTROL i_CONTROLDATA = new EBPP_CONTROL();
		i_CONTROLDATA.setDUE_DATE_LOW(due_date_low);
		i_CONTROLDATA.setDUE_DATE_HIGH(due_date_high);
		
//		i_CONTROLDATA.setDUE_DATE_LOW("2010-09-01");
//		i_CONTROLDATA.setDUE_DATE_HIGH("2010-10-15");
		i_CONTROLDATA.setCLEARED_ITEMS(_contract.getClearedItem());
		i_CONTROLDATA.setOPEN_ITEMS(_contract.getOpenItem());
		i_CONTROLDATA.setSELECT_FOR_PAYMENTS(_contract.getSelectForPayments());
		i_CONTROLDATA.setONLY_INVOICES(_contract.getOnlyInvoice());
		
		i_CONTROLDATA.setDISPLAY_CURRENCY(" ");
		i_CONTROLDATA.setFILTER_FIELD(" ");
		i_CONTROLDATA.setFILTER_MAX_ITEMS(0);
		i_CONTROLDATA.setFILTER_SCREEN(" ");
		i_CONTROLDATA.setFILTERVALUE_AMOUNTHIGH(null);
		i_CONTROLDATA.setFILTERVALUE_AMOUNTLOW(null);
		i_CONTROLDATA.setPAYMENT_ID(" ");
		i_CONTROLDATA.setPAYMENT_TYPE(" ");
		i_CONTROLDATA.setFILTERVALUE_HIGH(" ");
		i_CONTROLDATA.setFILTERVALUE_LOW(" ");
		
//		i_CONTROLDATA.setCLEARED_ITEMS("X");
//		i_CONTROLDATA.setOPEN_ITEMS("X");
//		i_CONTROLDATA.setSELECT_FOR_PAYMENTS("X");
//		i_CONTROLDATA.setONLY_INVOICES("X");
		
//		EBPP_PARTNER i_PARTNER = new EBPP_PARTNER("11","20009450"," "," "," "," ");
		EBPP_PARTNER i_PARTNER = new EBPP_PARTNER();
		/*TO DO Need to remove comment below 2 lines for real data*/
		i_PARTNER.setPARTNERKEY(_contract.getVendorID());
		i_PARTNER.setUNAME(" ");
		i_PARTNER.setAGENT_ACTIVE(" ");
		i_PARTNER.setCC_ACTIVE(" ");
		i_PARTNER.setDESCRIP_LONG(" ");
		
		if(_contract.isInvoiceAP())
		{
			logger.debug("This is for AP");
			i_PARTNER.setPARTNERTYPE("11");
			i_CONTROLDATA.setPARKED_ITEMS(" ");
			i_CONTROLDATA.setCHECK_DISPUTES(" ");
			i_CONTROLDATA.setPREVIOUS_BALANCE(" ");
		}else{
			logger.debug("This is for AR");
			i_PARTNER.setPARTNERTYPE("12");
			i_CONTROLDATA.setPARKED_ITEMS(_contract.getParkedItems());
			i_CONTROLDATA.setCHECK_DISPUTES(_contract.getCheckDisputes());
			i_CONTROLDATA.setPREVIOUS_BALANCE(_contract.getPrevBalance());
		}
		
//		i_PARTNER.setPARTNERKEY("20009450");
//		i_PARTNER.setPARTNERTYPE("11");
		
		logger.debug("Before Service Call");
		
		proxy.APAR_EBPP_GET_DATA(i_ADDSEL, i_CONTROLDATA, null, i_PARTNER, allocationHolder, bankHolder, cardHolder, cardTypesHolder, 
				disputesHolder, DMallocationHolder, dataHolder, invoiceHolder, itemHolder, messagesHolder, paymentsHolder, payAllocationHolder, 
				payExplanationHolder, totalsHolder, creditsHolder, partnerHolder, returnCode, BAPIHolder);
		
		logger.debug("After Service Call");
		
		InvoiceListResult invoiceListResult = mapWSResult(invoiceHolder,paymentsHolder,payAllocationHolder);
		
		return invoiceListResult;
	}
	
	public static String fomatWSDate(Date _date) {
		if (_date == null){
			return null;
		}
		return DATE_FORMAT_SAP.format(_date);
	}
	
	private InvoiceListResult mapWSResult(
			TABLE_OF_EBPP_INVOICEHolder invoiceHolder, TABLE_OF_EBPP_MYPAYMENTSHolder paymentHolder, TABLE_OF_EBPP_PAYALLOCATIONHolder payAllocationHolder) throws ParseException{		
		InvoiceListResult invoiceListResult = new InvoiceListResult();
		logger.debug("invoiceHolder.value.length "+invoiceHolder.value.length);
		logger.debug("paymentHolder.value.length "+paymentHolder.value.length);
		logger.debug("payAllocationHolder.value.length "+payAllocationHolder.value.length);
		
		List<Invoice> invoiceList = new ArrayList<Invoice>(invoiceHolder.value.length);		
		EBPP_MYPAYMENTS EBPPPayments = null;
		for (int i = 0; i < invoiceHolder.value.length; i++) {
			
			EBPP_INVOICE EBPPInvoice = invoiceHolder.value[i];
			if(!StringUtil.isEmpty(EBPPInvoice.getINVID()))
			{
				EBPPPayments = mapPayment(EBPPInvoice.getINVID(),paymentHolder,payAllocationHolder);
			}
//			EBPP_MYPAYMENTS EBPPPayments = paymentHolder.value[i];
			
			Invoice invoice = ws2Invoice(EBPPInvoice,EBPPPayments);
			invoiceList.add(invoice);
		}		
		invoiceListResult.setInvoice(invoiceList);
		return invoiceListResult;
	}
	
	
	private EBPP_MYPAYMENTS mapPayment(String invid, TABLE_OF_EBPP_MYPAYMENTSHolder paymentHolder, TABLE_OF_EBPP_PAYALLOCATIONHolder payAllocationHolder) {
		if(paymentHolder!=null && payAllocationHolder!=null && paymentHolder.value.length > 0 && payAllocationHolder.value.length > 0)
		{			
			for(EBPP_MYPAYMENTS payments:paymentHolder.value)
			{
				for(EBPP_PAYALLOCATION payAllocation:payAllocationHolder.value)
				{
					if(payments.getREFID().equalsIgnoreCase(payAllocation.getREFID()) && payAllocation.getINVID().equalsIgnoreCase(invid))
					{
						return payments;
					}
				}				
			}
		}
		return null;
	}

	public static Invoice ws2Invoice(EBPP_INVOICE EBPPInvoice, EBPP_MYPAYMENTS EBPPPayments) throws ParseException{
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(EBPPInvoice.getXBLNR());
		invoice.setInvoiceDate(DATE_FORMAT_SAP.parse(EBPPInvoice.getBLDAT()));
		invoice.setDueDate(DATE_FORMAT_SAP.parse(EBPPInvoice.getDUE_DATE()));
		
		//Partha Changed
		//invoice.setAmount(new BigDecimal(EBPPInvoice.getAMOUNT().abs().toPlainString(), new MathContext(15, RoundingMode.FLOOR)).setScale(2, BigDecimal.ROUND_HALF_DOWN)); 
		invoice.setAmount(new BigDecimal(EBPPInvoice.getAMOUNT().toPlainString(), new MathContext(15, RoundingMode.FLOOR)).setScale(2, BigDecimal.ROUND_HALF_DOWN));
		invoice.setCurrencyType(EBPPInvoice.getCURRENCY());
		
		if(EBPPPayments != null)
		{
			invoice.setPaymentNumber(EBPPPayments.getDOCT1());
			invoice.setChequeNumber(EBPPPayments.getDOCT1());
			invoice.setPaidDate(DATE_FORMAT_SAP.parse(EBPPPayments.getVALUE_DATE()));
		}else{		
			invoice.setPaymentNumber("");
			invoice.setChequeNumber("");
			invoice.setPaidDate(null);
		}	
		return invoice;
	}

}
