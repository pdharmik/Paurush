package com.lexmark.service.impl.real.partnerPaymentService;

import static com.lexmark.util.LangUtil.first;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.toInt;
import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.domain.AccountPayableDetail;
import com.lexmark.domain.AccountPayableDetailActivity;
import com.lexmark.result.AccountPayableDetailResult;
import com.lexmark.service.impl.real.domain.AccountPayableDetailActivityDo;
import com.lexmark.service.impl.real.domain.AccountPayableDetailDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

/**
 * @author vpetruchok
 * @version 1.0, 2012-10-10
 */
public class AccountPayableDetailService {

    //private static Log logger = LogFactory.getLog(AccountPayableDetailService.class);
	private static Logger logger = LogManager.getLogger(AccountPayableDetailService.class);
    private static final Class<?> DO_CLASS = AccountPayableDetailDo.class;
    private static final Map<String, String> FIELD_MAP = fieldMap();
    private final Session session;
    
    private static Map<String, String> fieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("mdmLevel1AccountId", "LXK SW SP Global Ultimate DUNS");
        m.put("mdmLevel2AccountId", "LXK SW SP Domestic Ultimate DUNS");
        m.put("mdmLevel3AccountId", "LXK SW SP Legal Entity Number");
        m.put("mdmLevel4AccountId", "LXK SW SP MDM Account Number");
        m.put("mdmLevel5AccountId", "LXK R Service Provider Id"); 
//        m.put("mdmLevel", "");
        return Collections.unmodifiableMap(m);
    }
    
    AccountPayableDetailService(Session session) {
        this.session = session;
    }

    AccountPayableDetailResult retrieveAccountPayableDetail(AccountPayableDetailContract contract) {
        checkRequiredFields(contract);
        String searchSpec = buildSearchExpression(contract, FIELD_MAP);
        List<AccountPayableDetailDo> doList = querySiebel(session, searchSpec); 
        return toAccountPayableDetailResult(doList);
    }
    
    private String buildSearchExpression(AccountPayableDetailContract contract, Map<String, String> fieldMap) {
        StringBuilder expr = new StringBuilder();
        expr.append(mdmSearchExpr(contract, fieldMap));
        expr.append(format(" AND [serviceRequestNumber] = '%s'", contract.getServiceRequestNumber()));
        return expr.toString();
    }
    
    private static String mdmSearchExpr(MdmSearchContractBase contract, Map<String, String> fieldMap) {
        return AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), fieldMap, false, FIELD_MAP.get("mdmLevel"));  
    } 
    
    @SuppressWarnings("unchecked")
    static <T> List<T> querySiebel(Session session, String searchSpec) {
        QueryObject qo = new QueryObject(DO_CLASS, ActionEvent.QUERY);
        qo.addComponentSearchSpec(DO_CLASS, searchSpec);
        qo.setQueryString(searchSpec);
        
        return session.getDataManager().query(qo);
    }    
    
    private AccountPayableDetailResult toAccountPayableDetailResult(List<AccountPayableDetailDo> doList) {
        AccountPayableDetailResult result = new AccountPayableDetailResult();
        if (isEmpty(doList)) {
            return result;
        }
        
        AccountPayableDetailDo firstItemDo = first(doList);
        
        AccountPayableDetail item = new AccountPayableDetail();
        item.setRequestNumber(firstItemDo.getServiceRequestNumber());
        item.setCustomerAccountName(firstItemDo.getCustomerAccountName());
        item.setTotalLabor(firstItemDo.getLaborPayment());
        item.setTotalAdditionalPayments(firstItemDo.getAdditionalPayments());
        
        populateAllActivities(item, doList);
        populateAggregatedValues(item, firstItemDo);
        result.setAccountPayableDetail(item);
        
        return result;
    }

    
    private static void populateAggregatedValues(AccountPayableDetail item, AccountPayableDetailDo detailDo) {
        boolean mpsType = "MPS".equalsIgnoreCase(detailDo.getLxkMpsType());
        BigDecimal totalPartFee = mpsType ? detailDo.getPartFee() : detailDo.getExtendedLineTotal();
        BigDecimal totalFulfillmentFee = detailDo.getFulfillmentFee();
        
        for (AccountPayableDetailActivityDo detailActivityDo : LangUtil.notNull(detailDo.getActivities())) {
            totalPartFee = totalPartFee.add(detailActivityDo.getPartFee());
            totalFulfillmentFee = totalFulfillmentFee.add(detailActivityDo.getFulfillmentFee());
         }
     
         item.setTotalPartFee(normalize(totalPartFee));
         item.setTotalFulfillmentFee(normalize(totalFulfillmentFee));        
    }
    
    static final BigDecimal ZERO_MONEY_FORMAT = new BigDecimal("0.00");
    
    static BigDecimal normalize(BigDecimal bd) {
        if (bd == null) 
            return ZERO_MONEY_FORMAT;
        
        return bd.setScale(2, RoundingMode.DOWN);
    }
    
    private static void populateAllActivities(AccountPayableDetail item, List<AccountPayableDetailDo> doList) {
        List<AccountPayableDetailActivity> activities = new ArrayList<AccountPayableDetailActivity>();
         
        for (AccountPayableDetailDo detailDo : doList) {
            boolean mpsType = "MPS".equalsIgnoreCase(detailDo.getLxkMpsType());
            
            for (AccountPayableDetailActivityDo activityDo : LangUtil.notNull(detailDo.getActivities())) {
                AccountPayableDetailActivity activity = new AccountPayableDetailActivity();
                if (mpsType) {
                    activity.setQuantity(activityDo.getQuantity());
                } else {
                    activity.setQuantity(toInt(activityDo.getRecommendedQuantity()));
                }
                activity.setPartFee((activityDo.getPartFee()));
                activity.setPartNumber(activityDo.getPartNumber());
                activity.setPartDescription(activityDo.getPartDescription());
                activity.setFulfillmentFee((activityDo.getFulfillmentFee()));
                activity.setAmount(activity.getPartFee().add(activity.getFulfillmentFee()));
                activity.setPartnerFee((activityDo.getPartnerFee()));
                activity.setEligibilityStatus(activityDo.getEligibilityStatus());

                activities.add(activity);
            }
        }
        
        item.setActivities(activities);
    }
    
    
    
    public void checkRequiredFields(AccountPayableDetailContract contract) {
        if (!PartnerPaymentUtil.isSearchByMdm(contract)) {
            throw new IllegalArgumentException("No mdmId  or mdmLevel specified");
        }
        if (isEmpty(contract.getServiceRequestNumber())) {
            throw new IllegalArgumentException("serviceRequestNumber is null or empty!");
        }
    }
}
