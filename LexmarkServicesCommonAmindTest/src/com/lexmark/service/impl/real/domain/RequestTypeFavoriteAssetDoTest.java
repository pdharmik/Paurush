package com.lexmark.service.impl.real.domain;

import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2013-03-26
 */
public class RequestTypeFavoriteAssetDoTest {

    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(RequestTypeFavoriteAssetDo.class, "", 5);
    }
    
    @Test
    public void query1_noData() throws Exception {
        MiscTest.sampleSiebelQuery(RequestTypeFavoriteAssetDo.class, "[Id] <> [Id]", 5);
    }
    
    @Test
    public void query() throws Exception {
    	String searchespec = "([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS') AND [LXK MPS Legal Entity ID #] = '43800' AND [LXK MPS Account Level] = 'Siebel' AND EXISTS([LXK MPS Asset Contact Id] = '1-6AH086J') AND ([Created] >= '10/22/2013 18:30:00') AND ([Created] <= '11/07/2013 18:56:01') AND ([LXK MPS SR Type] = 'Consumables Management' OR [LXK MPS SR Type] = 'Fleet Management' OR ([LXK MPS SR Type]='Diagnosis' OR [LXK MPS SR Type]='Service' OR [LXK MPS SR Type]='Dispatch' OR [LXK MPS SR Type]='Presales' OR [LXK MPS SR Type]='Complaint - Formal' OR [LXK MPS SR Type]='Complaint - Informal' OR [LXK MPS SR Type]='Customer Does Not Wish to Pay' OR [LXK MPS SR Type]='Dealer Location Queries' OR [LXK MPS SR Type]='Email' OR [LXK MPS SR Type]='Entitlement Check' OR [LXK MPS SR Type]='Install/De-Install' OR [LXK MPS SR Type]='LXK Internet Site Query' OR [LXK MPS SR Type]='Lack of Customer Info' OR [LXK MPS SR Type]='Non-supported Product' OR [LXK MPS SR Type]='OEM Import' OR [LXK MPS SR Type]='Other LXK Dept (Sales, Office)' OR [LXK MPS SR Type]='Other TSC Dept (CPD or PSSD)' OR [LXK MPS SR Type]='Schedule Callback' OR [LXK MPS SR Type]='Web Account' OR [LXK MPS SR Type]='CFL Store' OR [LXK MPS SR Type]='Others') OR [LXK MPS SR Type] = 'Fleet Management')";
    	List<?> aa =  MiscTest.querySiebel(RequestTypeFavoriteAssetDo.class, searchespec);
    	System.out.println("End");
    }
}
