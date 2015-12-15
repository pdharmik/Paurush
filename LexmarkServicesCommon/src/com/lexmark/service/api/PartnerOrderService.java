package com.lexmark.service.api;

import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.MassUploadTemplateResult;
import com.lexmark.result.source.OrderAcceptResult;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.result.source.OrderListResult;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-17
 */
public interface PartnerOrderService {

    //Added By MPS Offshore Team for retrieving Order Detail & Order List
    public OrderDetailResult retrieveOrderDetail(OrderDetailContract contract) throws LGSCheckedException, LGSRuntimeException;

    public OrderListResult retrieveOrderList(OrderListContract contract) throws Exception;

    public OrderAcceptResult acceptServiceOrderRequest(OrderAcceptContract contract) throws LGSCheckedException, LGSRuntimeException;

	public AttachmentListResult retrieveRequestAttachmentList(AttachmentContract contract) throws Exception;
	
	public MassUploadTemplateResult retrieveMassUploadTemplateList(MassUploadTemplateContract contract) throws Exception;

}
