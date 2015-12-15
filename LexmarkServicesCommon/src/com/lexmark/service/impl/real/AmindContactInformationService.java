package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.util.AmindContactInformationConversionUtil.convertGlobalContactDoListToContactInformationList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.ACCOUNT_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.SIEBEL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.ContactInformationContract;
import com.lexmark.domain.ContactInformation;
import com.lexmark.result.ContactInformationResult;
import com.lexmark.service.api.ContactInformationService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.GlobalContactDO;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

/**
 * do-classes:
 * GlobalLegalEntityDO: "do-globalLegalEntity-mapping.xml"
 * GlobalContactDO: "do-globalcontactdo-mapping.xml"
 */
public class AmindContactInformationService extends AmindSiebelCrmService implements
		ContactInformationService {

	// TODO move to parent class
	private SessionFactory statelessSessionFactory;

	/**
	 * TESTS: ContactInformationTest.class
	 */
	@Override
	public ContactInformationResult retrieveContactInformation(ContactInformationContract contract) {
		logger.debug("[IN] retrieveContactInformation");
		ContactInformationResult result = new ContactInformationResult();
		Session session = null;

		try {
			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();

			String accountDoSearchSpec = buildAccountDoSearchExpression(contract);
			String mdmId = contract.getMdmId();
			String mdmLevel = contract.getMdmLevel();

			if (isNotEmpty(accountDoSearchSpec)) {
				List<GlobalLegalEntityDO> accountDoList = queryAccountDoList(contract, dataManager,
						accountDoSearchSpec);
				if (isNotEmpty(accountDoList)) {
					GlobalLegalEntityDO account = accountDoList.get(0);
					mdmId = account.getMdmLevel3AccountId();
					mdmLevel = "Legal";
				}
			}

			String globalContactSearchSpec = buildGlobalContactSearchSpec(contract, mdmId, mdmLevel);

			List<GlobalContactDO> globalContactList = queryGlobalContactList(contract, dataManager,
					globalContactSearchSpec, mdmId);
			List<ContactInformation> contactInfoList = convertGlobalContactDoListToContactInformationList(globalContactList);

			result.setContactInfoList(contactInfoList);
			result.setTotalCount(contactInfoList.size());

		} catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);			
			throw new SiebelCrmServiceException("retrieveContactInformation() failed ", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}

		logger.debug("[OUT] retrieveContactInformation");
		return result;
	}

	private String buildAccountDoSearchExpression(ContactInformationContract contract) {
		String mdmId = contract.getMdmId();
		if (mdmId == null) {
			throw new IllegalArgumentException("mdmId is null!");
		} else if (mdmId.isEmpty()) {
			throw new IllegalArgumentException("mdmId is empty!");
		}
		String mdmLevel = contract.getMdmLevel();
		if (mdmLevel == null) {
			throw new IllegalArgumentException("mdmLevel is null!");
		} else if (mdmLevel.isEmpty()) {
			throw new IllegalArgumentException("mdmLevel is empty!");
		}

		if ((ACCOUNT_MDMLEVEL.equals(mdmLevel)) || SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			return buildmdmSearchExpression(mdmId, mdmLevel, null, false, false);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<GlobalLegalEntityDO> queryAccountDoList(ContactInformationContract contract,
			IDataManager dataManager, String searchSpec) {
		List<GlobalLegalEntityDO> accountDoList = dataManager.queryBySearchExpression(
				GlobalLegalEntityDO.class, searchSpec);
		return notNull(accountDoList);
	}

	private String buildGlobalContactSearchSpec(ContactInformationContract contract, String mdmId,
			String mdmLevel) {

		StringBuilder result = new StringBuilder();
		result.append(" [mdmLevel3AccountId] = '");
		result.append(mdmId);
		result.append("' AND [accountLevel] = '");
		result.append(mdmLevel);
		result.append("'");
		result.append(" AND [noteType] = '");
		result.append(contract.getRoleName());
		result.append("'");

		return result.toString();
	}

	@SuppressWarnings("unchecked")
	private List<GlobalContactDO> queryGlobalContactList(ContactInformationContract contract,
			IDataManager dataManager, String searchSpec, String mdmId) {
		List<GlobalContactDO> globalContactList = dataManager.queryBySearchExpression(GlobalContactDO.class,
				searchSpec);
		return notNull(globalContactList);
	}

	// TODO move to parent class
	public SessionFactory getStatelessSessionFactory() {
		if (statelessSessionFactory == null) {
			throw new SiebelCrmServiceException("SessionFactory not initialized");
		}
		return statelessSessionFactory;
	}

	public void setStatelessSessionFactory(SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

}
