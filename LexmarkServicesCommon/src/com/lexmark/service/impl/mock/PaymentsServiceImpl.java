package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Payment;
import com.lexmark.result.AccountPayableDetailResult;
import com.lexmark.result.AccountPayableListResult;
import com.lexmark.result.AccountReceivableListResult;
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.result.PaymentListResult;
import com.lexmark.result.PaymentRequestListResult;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;

public class PaymentsServiceImpl implements PaymentsService {

	@Override
	public PaymentDetailsResult retrievePaymentDetails(PaymentDetailsContract contract) {
		String id = contract.getPaymentId();
		Payment payment = PartnerDomainMockDataGenerator.getPaymentsDetail(Integer.valueOf(id));
		PaymentDetailsResult result = new PaymentDetailsResult();
		result.setPayment(payment);
		return result;
	}

	@Override
	public PaymentLineItemDetailsResult retreivePaymentLineItemList(PaymentLineItemDetailsContract contract) {
		PaymentLineItemDetailsResult result = new PaymentLineItemDetailsResult();
		String paymentId = contract.getPaymentId();
		List<Activity> paymentLineItemList = PartnerDomainMockDataGenerator.getPaymentLineItemList(Integer
				.valueOf(paymentId));
		paymentLineItemList = filterAndSortPaymentLineItemList(paymentLineItemList, contract);

		if (contract.getIncrement() == -1) {
			result.setActivities(paymentLineItemList);
			result.setTotalCount(paymentLineItemList.size());
			return result;
		}

		int toIndex = contract.getStartRecordNumber() + contract.getIncrement();
		toIndex = toIndex < paymentLineItemList.size() ? toIndex : paymentLineItemList.size();
		List<Activity> subActivities = paymentLineItemList.subList(contract.getStartRecordNumber(), toIndex);
		result.setActivities(subActivities);
		result.setTotalCount(paymentLineItemList.size());
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<Activity> filterAndSortPaymentLineItemList(List<Activity> paymentLineItemList,
			PaymentLineItemDetailsContract contract) {

		String sortCriteriaStr = null;
		for (String sort : contract.getSortCriteria().keySet()) {
			sortCriteriaStr = sort + ":" + contract.getSortCriteria().get(sort);
			break;
		}

		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		CollectionSorter sorter = new CollectionSorter();
		List<Activity> filterResult = filter.filter(paymentLineItemList, contract.getFilterCriteria(), contract
				.getSearchCriteria());
		return sorter.sort(filterResult, sortCriteriaStr);
	}

	@Override
	@SuppressWarnings("unchecked")
	public PaymentListResult retreivePaymentList(PaymentListContract contract) throws Exception {
		final PaymentListResult paymentListResult = new PaymentListResult();

		List<Payment> payments = new ArrayList<Payment>();
		Date startDate = (Date) contract.getFilterCriteria().get("Payment.startDate");
		Date endDate = (Date) contract.getFilterCriteria().get("Payment.endDate");
		for (Activity activity : PartnerDomainMockDataGenerator.getActivities()) {
			final Payment payment = activity.getPayment();
			if (payment != null && payment.getDateCreated().before(endDate)
					&& payment.getDateCreated().after(startDate)) {
				payments.add(payment);
			}
		}

		contract.getFilterCriteria().remove("Payment.startDate");
		contract.getFilterCriteria().remove("Payment.endDate");
		final List<Payment> sortedPayments = sortAndFilter(payments, contract);
		contract.getFilterCriteria().put("Payment.startDate", startDate);
		contract.getFilterCriteria().put("Payment.endDate", endDate);

		if (contract.getIncrement() == -1) {
			paymentListResult.setPaymentList(sortedPayments);
			paymentListResult.setTotalcount(sortedPayments.size());
			return paymentListResult;
		}

		int toIndex = contract.getStartRecordNumber() + contract.getIncrement();
		toIndex = toIndex < sortedPayments.size() ? toIndex : sortedPayments.size();
		List<Payment> subPayments = sortedPayments.subList(contract.getStartRecordNumber(), toIndex);

		paymentListResult.setPaymentList(subPayments);
		paymentListResult.setTotalcount(sortedPayments.size());
		return paymentListResult;
	}

	@SuppressWarnings("unchecked")
	private List sortAndFilter(List payments, SearchContractBase contract) {
		String sortCriteriaStr = null;
		List<String> lovAttributeList = Arrays.asList("Activity.payment.paymentStatus");
		for (String sort : contract.getSortCriteria().keySet()) {
			if("Activity.paymentNumber".equals(updateSortingFieldForLOV(sort, lovAttributeList))){
				sortCriteriaStr = "Activity.payment.paymentNumber:"
				+ contract.getSortCriteria().get(sort);
			}else{
				sortCriteriaStr = updateSortingFieldForLOV(sort, lovAttributeList) + ":"
					+ contract.getSortCriteria().get(sort);
			}
			break;
		}

		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		CollectionSorter sorter = new CollectionSorter();
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria = filter.updateFilterCriteriaForLOV(contract.getFilterCriteria(), lovAttributeList);
		List<Activity> filterResult = filter.filter(payments, filterCriteria, contract.getSearchCriteria());

		return sorter.sort(filterResult, sortCriteriaStr);
	}

	private String updateSortingFieldForLOV(String sortingField, List<String> lovAttributeList) {
		if (lovAttributeList.contains(sortingField)) {
			return sortingField + ".value";
		}
		return sortingField;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PaymentRequestListResult retrievePaymentRequestList(PaymentRequestListContract contract) throws Exception {
		PaymentRequestListResult paymentRequestListResult = new PaymentRequestListResult();
		// TODO radio button condition
		final List<Activity> activities = new ArrayList<Activity>();
		for (Activity activity : PartnerDomainMockDataGenerator.getActivities()) {
			if (isMatchCondition(contract, activity)) {
				activities.add(activity);
			}
		}

		Date tempStartDate = (Date) contract.getFilterCriteria().get("Activity.startDate");
		Date tempEndDate = (Date) contract.getFilterCriteria().get("Activity.endDate");
		contract.getFilterCriteria().remove("Activity.startDate");
		contract.getFilterCriteria().remove("Activity.endDate");
		// TODO add payment Agreement to sortAndFilter ??
		final List<Activity> sortedActivities = sortAndFilter(activities, contract);

		contract.getFilterCriteria().put("Activity.startDate", tempStartDate);
		contract.getFilterCriteria().put("Activity.endDate", tempEndDate);

		if (contract.getIncrement() == -1) {
			paymentRequestListResult.setPaymentRequestList(sortedActivities);
			paymentRequestListResult.setTotalcount(sortedActivities.size());
			return paymentRequestListResult;
		}

		int toIndex = contract.getStartRecordNumber() + contract.getIncrement();
		toIndex = toIndex < sortedActivities.size() ? toIndex : sortedActivities.size();
		List<Activity> subActivities = sortedActivities.subList(contract.getStartRecordNumber(), toIndex);

		paymentRequestListResult.setPaymentRequestList(subActivities);
		paymentRequestListResult.setTotalcount(sortedActivities.size());
		return paymentRequestListResult;
	}

	private boolean isMatchCondition(PaymentRequestListContract contract, Activity activity) {
		boolean matchPaymentStatus = true;
		if (StringUtils.isNotEmpty(contract.getPaymentStatus()) && !"All".equalsIgnoreCase(contract.getPaymentStatus()))
			matchPaymentStatus = contract.getPaymentStatus().equalsIgnoreCase(
					activity.getPayment().getPaymentStatus().getValue());

		boolean matchActivityDate = true;
		if (activity.getActivityDate().before(
				(Date) contract.getFilterCriteria().get("Activity.startDate"))
				|| activity.getActivityDate().after(
						(Date) contract.getFilterCriteria().get("Activity.endDate"))) {
			matchActivityDate = false;
		}

		return matchPaymentStatus && matchActivityDate;
	}

    @Override
    public AccountPayableListResult retrieveAccountPayableList(AccountPayableListContract contract) {
        return null;
    }

    @Override
    public AccountReceivableListResult retrieveAccountReceivableList(AccountReceivableListContract contract) {
        return null;
    }

    @Override
    public AccountPayableDetailResult retrieveAccountPayableDetail(AccountPayableDetailContract contract) {
        return null;
    }
    
}
