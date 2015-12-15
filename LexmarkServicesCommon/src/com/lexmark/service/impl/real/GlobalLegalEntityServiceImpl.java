package com.lexmark.service.impl.real;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.GlobalAccount;
import com.lexmark.service.api.GlobalLegalEntityService;
import com.lexmark.service.api.GlobalService;

/**
 * Created by IntelliJ IDEA.
 * User: sterrell
 * Date: 10/26/11
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalLegalEntityServiceImpl implements GlobalLegalEntityService {
    private static final Logger logger = LogManager.getLogger(GlobalLegalEntityServiceImpl.class);
    GlobalService globalService = null;
    private List<GlobalAccount> result = null;

    @Override
    public synchronized List<GlobalAccount> getGlobalAccounts() {
        if(result!=null) return result;
        refresh();
        return result;
    }

    public void refresh() {
        logger.info("Starting Global Account refresh.");
        result = Collections.unmodifiableList(globalService.retrieveGlobalLegalEntityList());
        logger.info("Finished Global Account refresh.");
    }

    public GlobalService getGlobalService() {
        return globalService;
    }

    public void setGlobalService(GlobalService globalService) {
        this.globalService = globalService;
    }
}
