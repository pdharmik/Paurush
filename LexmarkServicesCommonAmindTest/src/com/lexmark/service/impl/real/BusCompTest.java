package com.lexmark.service.impl.real;

import org.junit.Test;

import com.siebel.data.SiebelBusComp;
import com.siebel.data.SiebelBusObject;
import com.siebel.data.SiebelDataBean;

/**
 * @author vpetruchok
 * @version 1.0, 2012-06-11
 */
public class BusCompTest {

    @Test
    public void testA() throws Exception {
        //        String connectString = "siebel://dlxkssbliapp01.lex.lexmark.com:2321/SBLDEV/SSEObjMgr_admin_enu";
        String connectString = "siebel.tcpip.none.none://dlxkssbliapp01.lex.lexmark.com:2321/SBLDEV/SSEObjMgr_admin_enu";
        SiebelDataBean dataBean = new SiebelDataBean();
        dataBean.login(connectString, "PORTINTG", "#123@Lex", "enu");
        SiebelBusObject busObject = dataBean.getBusObject("LXK MPS SW Consumable Asset List");
        SiebelBusComp busComp = busObject.getBusComp("LXK MPS SW Consumable Asset List");
        busComp.setViewMode(3);
        busComp.clearToQuery();
        //        busComp.activateField("Id");
        busComp.setUserProperty("PageSize", "5");
        busComp.setUserProperty("StartRowNum", "1");
//        busComp.setSearchSpec("Id", "1-GOEHHI");
//        busComp.executeQuery2(true, true);
        busComp.executeQuery(true);
        if (busComp.firstRecord()) {
            System.out.println("Id: " + busComp.getFieldValue("Id"));
            System.out.println("Created: " + busComp.getFieldValue("Created")); 
            
//            while (busComp.nextRecord()) {
//                System.out.println("Id: " + busComp.getFieldValue("Id"));
//                System.out.println("Created: " + busComp.getFieldValue("Created")); 
//            }
        }
        busComp.release();
        busObject.release();
        dataBean.logoff();
    }
}
