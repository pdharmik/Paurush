package com.lexmark.service.impl.real.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

import com.lexmark.contract.RequestListContract;
import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-12-13
 */
public class RecursiveToStringStyleTest {

    @Test
    public void test1() throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        m.put("1", "one");
        m.put("2", "two");
        System.out.println(ToStringBuilder.reflectionToString(m));
        System.out.println(m);
        assertEquals("java.util.HashMap[threshold=12,loadFactor=0.75]", to_s(m));
    }

    @Test
    public void test2() throws Exception {
        String s = to_s(new C1());
        assertEquals(
                "com.lexmark.service.impl.real.util.RecursiveToStringStyleTest$C1[c1i=1,c2=com.lexmark.service.impl.real.util.RecursiveToStringStyleTest$C2[c2i=2,c3=com.lexmark.service.impl.real.util.RecursiveToStringStyleTest$C3[c3i=3,ids={id1,id2,id3}],map=java.util.HashMap{2=com.lexmark.service.impl.real.util.RecursiveToStringStyleTest$C3[c3i=3,ids={id1,id2,id3}],1=com.lexmark.service.impl.real.util.RecursiveToStringStyleTest$C3[c3i=3,ids={id1,id2,id3}]}],c1s=hello]"
                , s);
    }

    @Test
    public void testArrayList() throws Exception {
        ArrayList<String> al = new ArrayList<String>();
        al.add("id1");
        al.add("id2");
        System.out.println(al);
        assertEquals(
                "java.util.ArrayList[size=2]"
                , to_s(al));
        
        
        assertEquals(
                "[Ljava.lang.Object;[{id1,id2}]"
                , to_s(al.toArray()));

    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testMap() throws Exception {
        Map<String,Object> m = new HashMap<String, Object>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", new Date(0));
        System.out.println(m);
        assertEquals(
                "java.util.HashMap[threshold=12,loadFactor=0.75]"
                , to_s(m));
        
        assertEquals(
                "[Ljava.lang.Object;[{k3=java.util.Date[1970-01-01T02:00:00.000+0200],k1=v1,k2=v2}]"
                , to_s(m.entrySet().toArray(), false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testObjectWithMapAndListAsMapValue() throws Exception {
        A1 a4 = new A1();
        a4.list1 = Arrays.asList("reg", "green", "blue");
        a4.list2.add("red");
        a4.list2.add("green");
        a4.list2.add("blue");

        a4.map.put("requestType",  Arrays.asList("vendor", "customer"));
        ArrayList<String> al = new ArrayList<String>();
        al.add("id1");
        al.add("id2");
        a4.map.put("ids",  al);

        Map capitals = new HashMap();
        capitals.put("Ukraine", "Kiyev");
        capitals.put("USA", "Washington");

        a4.map.put("capitals", capitals);
        a4.map.put("key1", "value1");
        a4.map.put("key2", new C3());


        HashMap<String, String> activities = new HashMap<String, String>();
        activities.put("Ivanov", "tenis");
        activities.put("Petrov", "chess");

        a4.map.put("stat", Arrays.asList(activities));

        assertEquals(
                "com.lexmark.service.impl.real.util.RecursiveToStringStyleTest$A1[map=java.util.HashMap{capitals=java.util.HashMap{USA=Washington,Ukraine=Kiyev},key2=com.lexmark.service.impl.real.util.RecursiveToStringStyleTest$C3[c3i=3,ids={id1,id2,id3}],ids={id1,id2},key1=value1,requestType={vendor,customer},stat={java.util.HashMap{Petrov=chess,Ivanov=tenis}}},list1={reg,green,blue},list2={red,green,blue}]"
                , to_s(a4));
    }

    private static String to_s(Object obj) {
        return to_s(obj, false);
    }
    
    private static String to_s(Object obj, boolean multiline) {
        RecursiveToStringStyle style = new RecursiveToStringStyle(5);
        if (multiline) {
            style.setMultilineMode();
        }
        String s = ToStringBuilder.reflectionToString(obj, style);
        System.out.println(s);
        System.out.println("depth="+ style.getRecursionLevel());
        return s;
    }

    private static String p2(Object obj) {
        String s = ToStringBuilder.reflectionToString(obj,
                ToStringStyle.DEFAULT_STYLE, true);
        System.out.println(s);
        return s;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testRequestListContractLog() throws Exception {
        RequestListContract  reqListContract = new RequestListContract();
        reqListContract.setStartRecordNumber(0);
        reqListContract.setIncrement(40);
//        reqListContract.setMdmId("1-A7JZ-183");
        reqListContract.setMdmLevel("Siebel");
        reqListContract.setVendorAccountId("1-A7JZ-183");
        reqListContract.setVendorFlag(true);
        reqListContract.setAssetFavoriteFlag(false);
        reqListContract.setShowAllFlag(true);
        
        reqListContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
                "serviceRequest.startDate", "09/02/2012 05:30:00",
                "serviceRequest.endDate", "10/02/2012 10:59:47"
                ,
                "requestType", Arrays.asList("Consumables Management")
                , "datetype1", new java.util.Date(0)
//                ,
//                "requestType", Arrays.asList("Archived")
                ));
        reqListContract.setSearchCriteria((Map<String, Object>) MiscTest.newHashMap("serviceRequestNumber", "DESCENDING"));
        reqListContract.setSearchCriteria((Map<String, Object>) MiscTest.newHashMap("d1", new java.util.Date(-1)));
        assertEquals(
                "com.lexmark.contract.RequestListContract[  chlNodeId=<null>  status=<null>  contactId=<null>  showAllFlag=true  assetFavoriteFlag=false  assetType=<null>  serviceRequestNumber=<null>  vendorFlag=true  vendorAccountId=1-A7JZ-183  mdmId=<null>  mdmLevel=Siebel  filterCriteria=java.util.HashMap{datetype1=java.util.Date[1970-01-01T02:00:00.000+0200],serviceRequest.endDate=10/02/2012 10:59:47,serviceRequest.startDate=09/02/2012 05:30:00,requestType={Consumables Management}}  searchCriteria=java.util.HashMap{d1=java.util.Date[1970-01-01T01:59:59.999+0200]}  startRecordNumber=0  increment=40  newQueryIndicator=false  sortCriteria=java.util.HashMap{}  sessionHandle=<null>]"
                 , to_s(reqListContract, true).replaceAll("[\n\r]", ""));
    }
    
    class C1 {
        int c1i = 1;
        C2 c2 = new C2();
        String c1s = "hello";
    }

    class C2 {
        int c2i = 2;
        C3 c3 = new C3();
        Map<String, C3> map = new HashMap<String, C3>();
        {
            map.put("1", new C3());
            map.put("2", new C3());
        }
    }
 
    class C3 {
        int c3i = 3;
        List<String> ids = Arrays.asList("id1", "id2", "id3");
    }
 
    class A1 {
        Map map = new HashMap();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
    }
    
}
