package com.lexmark.security;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.lexmark.contract.api.SecureContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.api.AccountSecuredResult;

/**
 * Created by IntelliJ IDEA.
 * User: sterrell
 * Date: 11/1/11
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Aspect
public class SecuredServiceValidator {
    Logger LOGGER = LogManager.getLogger(SecuredServiceValidator.class);
    @Around(value="execution(public * com.lexmark.service.impl.real.*.*(com.lexmark.contract.api.SecureContract+))")
    public Object preCheckedService(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("------\n------\n------\nSTART OF AOP SECURITY\n------\n------\n------");
        ServicesUser user=null;
        boolean haveSecureContract = false;
        for(Object arg: joinPoint.getArgs()) {
            if(arg instanceof SecureContract) {
                haveSecureContract = true;
                user = ((SecureContract) arg).getServicesUser();
                break;
            }
        }
        if(user==null && haveSecureContract) {
            Signature signature = joinPoint.getSignature();
            throw new RuntimeException("Account is missing from Secured service call: " + signature.getClass().getName()+"."+signature.getName());
        }
        Object ret = joinPoint.proceed();
        if(!haveSecureContract) return ret;
        LOGGER.info("AFTER FUNCTION CALL");
        if(ret instanceof AccountSecuredResult) {
            return checkSecurity(user,(AccountSecuredResult) ret);
        }
        throw new RuntimeException("Security method missing from: " + ret.getClass());
    }

    public Object checkSecurity(ServicesUser user, AccountSecuredResult result) throws AccountUnauthorized {
        for(Account account: result.getAuthorizedAccounts()) {
            if(user.hasAccount(account)) return result;
        }
        throw new AccountUnauthorized(user,result);
    }
}
