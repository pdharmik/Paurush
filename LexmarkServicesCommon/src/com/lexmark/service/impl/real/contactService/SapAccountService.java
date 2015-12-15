package com.lexmark.service.impl.real.contactService;

import static com.lexmark.util.LangUtil.isEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AddressListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil;
import com.lexmark.service.impl.real.domain.SapAccountDo;
import com.lexmark.util.LangUtil;

public class SapAccountService {

	private final AddressListContract contract;
	private Session session;
	private QueryObject criteria;
	private StringBuilder searchExpression;

	public SapAccountService(AddressListContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null!");
		}
		this.contract = contract;
	}

	public void checkRequiredFields() {
		if (isEmpty(contract.getSoldToNumbers()) || isEmpty(contract.getSoldToNumbers().get(0))) {
			throw new IllegalArgumentException("SoldToNumber is null or empty!");
		}
	}

	public void buildSearchExpression() {
		
		List<String> soldToNumbers = contract.getSoldToNumbers();
		searchExpression = new StringBuilder();
		
		searchExpression.append("[soldTo] = '");
		searchExpression.append(soldToNumbers.get(0));
		searchExpression.append("'");

		if(soldToNumbers.size()>1) {
			for(int i=1;i<soldToNumbers.size();i++){
				if(soldToNumbers.get(i)!=null) {
				searchExpression.append(" OR [soldTo] = '");
				searchExpression.append(soldToNumbers.get(i));
				searchExpression.append("'");
				}
			}
		}
		
		criteria = new QueryObject(SapAccountDo.class,
				ActionEvent.QUERY);
		criteria.addComponentSearchSpec(SapAccountDo.class, searchExpression.toString());
	}

	public List<GenericAddress> queryAndGetAddressList() {
		
		List<GenericAddress> result = new ArrayList<GenericAddress>();
		
		IDataManager dataManager = getSession().getDataManager();
		List<SapAccountDo> accounts = dataManager.query(criteria);
					List<GenericAddress> addresses = AmindAddressConversionUtil.convertSapBusinessAddressToGenericAddress(accounts);
					result.addAll(distinctAddresses(addresses,result));
		return result;
	}

	private Collection<? extends GenericAddress> distinctAddresses(List<GenericAddress> addresses,List<GenericAddress> existingAddresses) {

		List<GenericAddress> addressesList = new ArrayList<GenericAddress>();

		for (GenericAddress genericAddress : addresses) {
			if(addressesList.size()<1){
				addressesList.add(genericAddress);
			}
			else if (!addressesListContainsAddress(genericAddress, addressesList)) {
				addressesList.add(genericAddress);
			}
		}
		return addressesList;
	}

	private boolean addressesListContainsAddress(GenericAddress address,List<GenericAddress> existingAddresses) {
	
		for (GenericAddress existingGenericAddress : existingAddresses) {
			if (address.getAddressId().equalsIgnoreCase(existingGenericAddress.getAddressId())) {
				return true;
			}
		}
		return false;
	}

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not set!");
		} else {
			return session;
		}
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		} else {
			this.session = session;
		}
	}


}
