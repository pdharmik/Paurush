package com.lexmark.service.impl.real;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.amind.session.StatefulSessionFactory;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.AccountAsset;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

public class AccountAssetTest {

    protected static final Log logger =
            LogFactory.getLog(AccountAssetTest.class);

    @Test
    public void testRetrieveAssetsPagination() throws Exception {
        String mdmId = "57408";
        String mdmLevel = "Legal";
        String contactId = "1-1SU00J3";// "1-1LUIPWR";
        String chlNodeId = "";
        String assetType = "ALL";
        boolean favoriteFlag = false;
        int startIndex = 0;
        int numRecords = 20;
        AmindGlobalService globalService = new AmindGlobalService();
        globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
        DeviceService service = new AmindContractedDeviceService();
        CrmSessionHandle handle = globalService.initCrmSessionHandle(null);

        AssetListContract assetConract = new AssetListContract();
        assetConract.setSessionHandle(handle);
        assetConract.setMdmId(mdmId);
        assetConract.setMdmLevel(mdmLevel);
        assetConract.setStartRecordNumber(startIndex);
        assetConract.setIncrement(numRecords);
        assetConract.setNewQueryIndicator(true);
        assetConract.setContactId(contactId);
        assetConract.setFavoriteFlag(favoriteFlag);
        assetConract.setAssetType(assetType);
        assetConract.setChlNodeId(chlNodeId);
        Map<String, Object> sortCriteria = new HashMap<String, Object>();
        sortCriteria.put("serialNumber", "ASCENDING");
        assetConract.setSortCriteria(sortCriteria);

        AssetListResult result = null;
        try {
            long endTime = 0L;
            long startTime = System.currentTimeMillis();
            int iterationCount = 0;
            logger.info("Search criteria: mdm Id is '" + mdmId + "' mdm level is '" + mdmLevel + "'.");
            startTime = System.currentTimeMillis();
            result = service.retrieveDeviceList(assetConract);

            logger.info
                    ("\nRetrieveDeviceList() -- " +
                            "iteration " + iterationCount + " " +
                            "elapsed Time(ms): " +
                            (endTime - startTime));
            logAssetList(result);

            if (result == null ||
                    result.getAssets() == null ||
                    result.getAssets().size() == 0) {
                logger.info("No records found for mdmId: " + mdmId + " and mdmLevel: " + mdmLevel);
            }
        } catch (Exception e) {
            logger.error("Test case failed", e);
            throw new SiebelCrmServiceException(e);
        } finally {
            if (handle != null) {
                try {
                    Session session = ((AmindCrmSessionHandle) handle).acquire();
                    if (session != null) {
                        session.release();
                    }
                    ((AmindCrmSessionHandle) handle).release();
                } catch (InterruptedException e) {
                    //squash
                }
            }
        }
    }

    private void logAssetList(AssetListResult result) {
        logger.info("Loggin results");
        List<com.lexmark.domain.Asset> assetList = result.getAssets();

        if (assetList != null && assetList.size() > 0)
        {

            logger.info("Found Asstes count = " + result.getTotalCount());
            for (com.lexmark.domain.Asset asset : assetList) {
/*
 * // logger.info("product TLI:" + asset.getProductTLI());
 * logger.info("serial number:" + asset.getSerialNumber());
 * logger.info("Product TLI: " + asset.getProductTLI());
 * logger.info("Address Name:" + asset.getInstallAddress().getAddressName());
 */
                logger.info("Asset ID:" + asset.getAssetId());
                logger.info("Mac Address:" + asset.getMacAddress());
                logger.info("Favorite:" + asset.getUserFavoriteFlag());
                logger.info("Machine Model:" + asset.getModelNumber());
                logger.info("Asset tag" + asset.getAssetTag());
            }
        }
        else
            logger.info("Asstes not found");
    }

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountAsset.class, 20);
//        MiscTest.sampleSiebelQuery(AccountAsset.class, "[consumableAssetFlag] = ''",  20); // takes much time
    }
    
    @Test
    public void query2() throws Exception {
//        MiscTest.sampleSiebelQuery(AccountAsset.class, 20);
        MiscTest.sampleSiebelQuery(AccountAsset.class, "[consumableAssetFlag] = ''",  20); // takes much time
    } 

    @Test
    public void testSessionMultithreading() throws Exception {
        final String BO = "LXK SW Contracted Asset - Service Web";
        final String BC = "LXK SW Contracted Asset - Service Web";

        final ExecutorService executorService = Executors.newFixedThreadPool(10);
//        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        
        final StatefulSessionFactory sessionFactory = TestSessionFactories.newStatefulSessionFactory();
        Session session = null;
        Session session2 = null;
        Session session3 = null;

        try {
            long t0 = System.currentTimeMillis();
            try {
                session = sessionFactory.attachSession();
                session2 = sessionFactory.attachSession();
                session3 = sessionFactory.attachSession();
                String searchSpec = "[Asset Number] = '1-9205023'";
                Future<Integer> f1 = executorService.submit(Siebel.count(session, BO, BC, searchSpec));
//                Future<Integer> f2 = executorService.submit(Siebel.count(session2, BO, BC, "[LXK MPS Consumable Flag] = ''"));
                Future<List<AccountAsset>> f3 = executorService.submit(Siebel.select(session2, AccountAsset.class,
                        searchSpec, 40, 0));

                MiscTest.print(f3.get());
                MiscTest.print("", f1.get());
            } finally {
                System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(30L, TimeUnit.MINUTES);

            AmindServiceUtil.detachSession(session);
            AmindServiceUtil.detachSession(session2);
            AmindServiceUtil.detachSession(session3);
        }

    }

    static class Siebel {

        static Callable<Integer> count(final Session session,
                                       final String bo, 
                                       final String bc,
                                       final String searchSpec) {
            return new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return AmindServiceUtil.getTotalCount(bo, bc, searchSpec, session.getSiebelBusServiceProxy());
                }
                
            };
        }
        
       static <T> Callable<List<T>> select(final Session session,
                                           final Class<T> cl,
                                           final String searchSpec,
                                           final int numRows,
                                           final int startRowIndex) {
           
          return new Callable<List<T>>() {

              @SuppressWarnings("unchecked")
              @Override
              public List<T> call() throws Exception {
                  final QueryObject qo = new QueryObject(cl, ActionEvent.QUERY);
                  qo.setExecutionMode(ExecutionMode.BiDirectional);
                  qo.setNumRows(numRows);
                  qo.setStartRowIndex(startRowIndex);
                  qo.addComponentSearchSpec(cl, searchSpec);
                  qo.setQueryString(searchSpec); 
                
                  return session.getDataManager().query(qo);
            }
          };
       }
    }
    
    @Test
    public void  testCallable() throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
//      final ExecutorService executorService = Executors.newSingleThreadExecutor();
        
        try {
            long t0 = System.currentTimeMillis();
            try {
                final long delay = 2L;
                
                long t1 = System.currentTimeMillis();
                Future<Void> f1 = executorService.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        TimeUnit.SECONDS.sleep(delay);
                        return null;
                    }
                }); 
                
                Future<Void> f2 = executorService.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        TimeUnit.SECONDS.sleep(delay);
                        return null;
                    }
                }); 
                
                f1.get(); f2.get();
                
                assertEquals(true, (System.currentTimeMillis() - t1) < TimeUnit.SECONDS.toMillis(delay) + 10 );
            } finally {
                System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(30L, TimeUnit.MINUTES);
        }        
    }
    
    @Test
    public void testFutureTask() throws Exception {
        final SessionFactory sessionFactory = TestSessionFactories.newStatefulSessionFactory();
        Session session = null;

        try {
            long t0 = System.currentTimeMillis();
            try {
                session = sessionFactory.attachSession();
//                String searchSpec = "[Asset Number] <> '1-9205023'";
                String searchSpec = "[Asset Number] = '1-9205023'";
                
                FutureTask<Integer> ft = futureCount(session, 
                                                     "LXK SW Contracted Asset - Service Web",
                                                     "LXK SW Contracted Asset - Service Web", 
                                                     searchSpec);
                
                System.out.println("Counting records...");
                while (!ft.isDone()) {
                    System.out.println("not done yet");
                    Thread.sleep(200L);
                }
                
                MiscTest.print("totalRecordCount = ", ft.get());
                
            } finally {
                System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            AmindServiceUtil.detachSession(session);
        }
    }

    private FutureTask<Integer> futureCount(final Session session, final String bo, final String bc, final String searchSpec) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return AmindServiceUtil.getTotalCount(bo, bc, searchSpec, session.getSiebelBusServiceProxy());
            }
        };
        
        FutureTask<Integer> ft = new FutureTask<Integer>(callable);
        
        new Thread(ft).start();
        
        return ft;
    }

}
