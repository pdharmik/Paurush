package com.lexmark.service.impl.real;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.SiebelCrmServiceException;
import org.apache.logging.log4j.Logger;


public class AmindCrmSessionHandle implements CrmSessionHandle, Serializable {
	protected static final Logger logger = LogManager.getLogger(AmindCrmSessionHandle.class);
	
	private static final long serialVersionUID = 456546395082094582L;
	private String sessionHandle;
	private transient SessionFactory sessionFactory;
	private transient Session session;
	private Set<String> assetFavoriteSet;
	private Set<String> meterReadFavoriteSet;
	private static final int MAX_AVAILABLE = 3;
	private final Semaphore sessionSemaphore;
	private int serviceRequestCount;
	private int deviceCount;
	private int meterReadCount;
	private int serviceHistoryCount;
	private int contactCount;
	private int partnerActivityCount;
	private int partnerPaymentCount;
	private int partnerPaymentLineItemCount;
	private int partnerPaymentRequestCount;
	private int addressCount;
	private int requestTypeCount;
	private int partnerAddressCount;
	
	private int consumableAssetCount;
	private Set<String> consumableAssetFavoriteSet;
	
	private int partnerRequestOrderCount;
	private Set<String> partnerRequestOrdeFavoriteSet;
	
	private int accountPayableCount;
	private int accountReceivableCount;
	
	private int orderPartCount;
	
	private List<String> currentSessionHandles = Collections.synchronizedList(new ArrayList<String>(MAX_AVAILABLE));
	private Object sessionLocker = new Object();
	private List<Thread> currentThreads = Collections.synchronizedList(new ArrayList<Thread>());
	
	private Set<String> partnerAddressFavoriteSet;
	private int offlineModeActivitiesCount;

	
	public AmindCrmSessionHandle() {
		sessionSemaphore = new Semaphore(MAX_AVAILABLE, true);
	}

	public int getContactCount() {
		return contactCount;
	}

	public void setContactCount(int contactCount) {
		this.contactCount = contactCount;
	}

	public void acquireSimple() throws InterruptedException {
		logger.debug("[IN] acquireSimple");
		sessionSemaphore.acquire();
		logger.debug("[OUT] acquireSimple");
	}

	/* Phase 1 for Stateful Session */
	public Session acquire() throws InterruptedException {
		
		currentThreads.add(Thread.currentThread());
		
		synchronized (sessionLocker) {
			
			while(sessionSemaphore.availablePermits() == 0) {
				sessionLocker.wait();
				if(Thread.currentThread().getId() != currentThreads.get(0).getId()) {
					sessionLocker.wait();
				}
			}
			
			currentThreads.remove(Thread.currentThread());
			
			logger.debug("[IN] acquire");
			sessionSemaphore.acquire();
			session = attachSession();
			logger.debug("[OUT] acquire");
			return session;
		}
	}

	/* Phase 2 for Stateful Session */
	public Session acquireMultiple() throws InterruptedException {
		
		currentThreads.add(Thread.currentThread());

		synchronized (sessionLocker) {
			
			while(sessionSemaphore.availablePermits() == 0) {
				sessionLocker.wait();
				if(Thread.currentThread().getId() != currentThreads.get(0).getId()) {
					sessionLocker.wait();
				}
			}
			
			currentThreads.remove(Thread.currentThread());
			
			logger.debug("[IN] acquireMultiple");
			sessionSemaphore.acquire();
			session = attachMultipleSession();
			logger.debug("Attaching Session " + session.getHandle());
			logger.debug("[OUT] acquireMultiple");
			return session;
		}
	}

	public void release() {
		synchronized (sessionLocker) {

			logger.debug("[IN] release");

			try {
				detachSession();
				sessionSemaphore.release();
			}
			finally {
				sessionLocker.notifyAll();
			}

			logger.debug("[OUT] release");
		}
	}

	public void releaseSimple() {

		logger.debug("[IN] release");
		sessionSemaphore.release();
		logger.debug("[OUT] release");
	}

	public void releaseMultipleSession(Session currentSession) {
		synchronized (sessionLocker) {
			logger.debug("[IN] releaseMultipleSession");

			try {
				if(currentSession!=null) {
					if (currentSession != session) {
						currentSessionHandles.remove(currentSession.getHandle());
						currentSession.release();
					} else {
						detachSession();
					}
				}
				sessionSemaphore.release();
			} finally {
				sessionLocker.notifyAll();
			}

			logger.debug("[OUT] releaseMultipleSession");
		}
	}

	public Set<String> getAssetFavoriteSet() {
		return assetFavoriteSet;
	}

	public void setAssetFavoriteSet(Set<String> assetFavoriteSet) {
		this.assetFavoriteSet = assetFavoriteSet;
	}

	public Set<String> getMeterReadFavoriteSet() {
		return meterReadFavoriteSet;
	}

	public void setMeterReadFavoriteSet(Set<String> meterReadFavoriteSet) {
		this.meterReadFavoriteSet = meterReadFavoriteSet;
	}

	public String getSessionHandle() {
		return sessionHandle;
	}

	public int getServiceRequestCount() {
		return serviceRequestCount;
	}

	public void setServiceRequestCount(int serviceRequestCount) {
		this.serviceRequestCount = serviceRequestCount;
	}

	public int getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public int getMeterReadCount() {
		return meterReadCount;
	}

	public void setMeterReadCount(int meterReadCount) {
		this.meterReadCount = meterReadCount;
	}

	public int getServiceHistoryCount() {
		return serviceHistoryCount;
	}

	public void setServiceHistoryCount(int serviceHistoryCount) {
		this.serviceHistoryCount = serviceHistoryCount;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session attachSession() {
		if (session != null && !currentSessionHandles.contains(sessionHandle)) {
			return session;
		}
		return attachSimpleSession();
	}

	private Session attachSimpleSession() {
		
			try {
				
				if (!currentSessionHandles.contains(sessionHandle)) {
					session = sessionFactory.attachSession(sessionHandle);
					if (logger.isDebugEnabled()) {
						logger.debug("attachSession: Reusing session "
								+ session.getHandle());
					}
				}
				else {
					session = sessionFactory.attachSession();
				}
				sessionHandle = session.getHandle();
			} catch (Exception e) {
				try {
				session = sessionFactory.attachSession();
				if (logger.isDebugEnabled()) {
					logger.debug("attachSession: Recovering to new session: old, new "
							+ sessionHandle + ", " + session.getHandle());
				}
				sessionHandle = session.getHandle();
				} catch (Exception ex) {
					logger.error("Failed to initialize session", ex);
					throw new SiebelCrmServiceException(
							"Failed to initialize session", ex);
				}
			}

			currentSessionHandles.add(sessionHandle);

			return session;
	}

	private Session attachMultipleSession() {
		return attachSimpleSession();
	}

	private void detachSession() {
		if (session != null) {
			currentSessionHandles.remove(session.detach());
//			session = null;
		}
	}

	public Session getSession() {
		if (session != null) {
			return session;
		}
		return null;
	}

	public void setPartnerActivityCount(int partnerActivityCount) {
		this.partnerActivityCount = partnerActivityCount;
	}

	public int getPartnerActivityCount() {
		return partnerActivityCount;
	}

	public void setPartnerPaymentCount(int partnerPaymentCount) {
		this.partnerPaymentCount = partnerPaymentCount;
	}

	public int getPartnerPaymentCount() {
		return partnerPaymentCount;
	}

	public void setPartnerPaymentLineItemCount(int partnerPaymentLineItemCount) {
		this.partnerPaymentLineItemCount = partnerPaymentLineItemCount;
	}

	public int getPartnerPaymentLineItemCount() {
		return partnerPaymentLineItemCount;
	}

	public void setPartnerPaymentRequestCount(int partnerPaymentRequestCount) {
		this.partnerPaymentRequestCount = partnerPaymentRequestCount;
	}

	public int getPartnerPaymentRequestCount() {
		return partnerPaymentRequestCount;
	}

	public int getAddressCount() {
		return addressCount;
	}

	public void setAddressCount(int addressCount) {
		this.addressCount = addressCount;
	}

	public void setRequestTypeCount(int requestTypeCount) {
		this.requestTypeCount = requestTypeCount;
	}

	public int getRequestTypeCount() {
		return requestTypeCount;
	}

    
	public int getConsumableAssetCount() {
        return consumableAssetCount;
    }
	

    public void setConsumableAssetCount(int consumableAssetCount) {
        this.consumableAssetCount = consumableAssetCount;
    }
    

    public Set<String> getConsumableAssetFavoriteSet() {
        return consumableAssetFavoriteSet;
    }
    

    public void setConsumableAssetFavoriteSet(Set<String> consumableAssetFavoriteSet) {
        this.consumableAssetFavoriteSet = consumableAssetFavoriteSet;
    }

    public int getPartnerRequestOrderCount() {
        return partnerRequestOrderCount;
    }

    public void setPartnerRequestOrderCount(int partnerRequestOrderCount) {
        this.partnerRequestOrderCount = partnerRequestOrderCount;
    }

    public Set<String> getPartnerRequestOrdeFavoriteSet() {
        return partnerRequestOrdeFavoriteSet;
    }

    public void setPartnerRequestOrdeFavoriteSet(Set<String> partnerRequestOrdeFavoriteSet) {
        this.partnerRequestOrdeFavoriteSet = partnerRequestOrdeFavoriteSet;
    }

    public int getAccountPayableCount() {
        return accountPayableCount;
    }

    public void setAccountPayableCount(int accountPayableCount) {
        this.accountPayableCount = accountPayableCount;
    }

    public int getAccountReceivableCount() {
        return accountReceivableCount;
    }

    public void setAccountReceivableCount(int accountReceivableCount) {
        this.accountReceivableCount = accountReceivableCount;
    }

    public int getOrderPartCount() {
        return orderPartCount;
    }

    public void setOrderPartCount(int orderPartCount) {
        this.orderPartCount = orderPartCount;
    }

	public int getPartnerAddressCount() {
		return partnerAddressCount;
	}

	public void setPartnerAddressCount(int partnerAddressCount) {
		this.partnerAddressCount = partnerAddressCount;
	}

	public Set<String> getPartnerAddressFavoriteSet() {
		return partnerAddressFavoriteSet;
	}

	public void setPartnerAddressFavoriteSet(Set<String> partnerAddressFavoriteSet) {
		this.partnerAddressFavoriteSet = partnerAddressFavoriteSet;
	}

	public int getOfflineModeActivitiesCount() {
		return offlineModeActivitiesCount;
	}

	public void setOfflineModeActivitiesCount(int offlineModeActivitiesCount) {
		this.offlineModeActivitiesCount = offlineModeActivitiesCount;
	}

}
