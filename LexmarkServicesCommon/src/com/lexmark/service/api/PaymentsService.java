package com.lexmark.service.api;

import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.result.AccountPayableDetailResult;
import com.lexmark.result.AccountPayableListResult;
import com.lexmark.result.AccountReceivableListResult;
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.result.PaymentListResult;
import com.lexmark.result.PaymentRequestListResult;

public interface PaymentsService {
	public PaymentDetailsResult retrievePaymentDetails (PaymentDetailsContract contract) throws Exception;
	public PaymentLineItemDetailsResult retreivePaymentLineItemList (PaymentLineItemDetailsContract contract) throws Exception;
	public PaymentListResult retreivePaymentList(PaymentListContract contract) throws Exception ;
	public PaymentRequestListResult retrievePaymentRequestList(PaymentRequestListContract contract ) throws Exception;
	
    public AccountPayableListResult retrieveAccountPayableList(AccountPayableListContract contract);
    public AccountReceivableListResult retrieveAccountReceivableList(AccountReceivableListContract contract);
    
    public AccountPayableDetailResult retrieveAccountPayableDetail(AccountPayableDetailContract contract);
}
