/**
 * Envelope.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContact.client;

public class Envelope  implements java.io.Serializable {
    private java.lang.String activation;

    private java.lang.Integer appLastSeqn;

    private java.lang.String appPassword;

    private java.lang.Integer appSeqn;

    private java.lang.String appUserName;

    private java.lang.String businessContext;

    private short[] controlLabel;

    private java.lang.String errorsTo;

    private java.lang.String errorRequestsTo;

    private java.lang.String locale;

    private java.lang.Integer maxResults;

    private java.lang.String replyTo;

    private java.lang.Integer runLevel;

    private byte[] signature;

    private java.lang.String signatureType;

    private java.lang.Integer startResult;

    private java.lang.Integer tag;

    private java.lang.String trackId;

    private java.lang.String transactionId;

    private java.lang.String transformState;

    private java.lang.Integer age;

    private java.lang.String connectionIntegrity;

    private java.lang.String destId;

    private java.util.Calendar enqueueTime;

    private java.lang.String logBroker;

    private java.lang.String logHost;

    private java.lang.String pubDistinguishedName;

    private java.lang.String pubId;

    private byte[] pubNetAddr;

    private java.lang.Long pubSeqn;

    private short[] pubLabel;

    private java.util.Calendar recvTime;

    private com.lexmark.webServices.processCustomerContact.client.Route[] route;

    private java.lang.String uuid;

    public Envelope() {
    }

    public Envelope(
           java.lang.String activation,
           java.lang.Integer appLastSeqn,
           java.lang.String appPassword,
           java.lang.Integer appSeqn,
           java.lang.String appUserName,
           java.lang.String businessContext,
           short[] controlLabel,
           java.lang.String errorsTo,
           java.lang.String errorRequestsTo,
           java.lang.String locale,
           java.lang.Integer maxResults,
           java.lang.String replyTo,
           java.lang.Integer runLevel,
           byte[] signature,
           java.lang.String signatureType,
           java.lang.Integer startResult,
           java.lang.Integer tag,
           java.lang.String trackId,
           java.lang.String transactionId,
           java.lang.String transformState,
           java.lang.Integer age,
           java.lang.String connectionIntegrity,
           java.lang.String destId,
           java.util.Calendar enqueueTime,
           java.lang.String logBroker,
           java.lang.String logHost,
           java.lang.String pubDistinguishedName,
           java.lang.String pubId,
           byte[] pubNetAddr,
           java.lang.Long pubSeqn,
           short[] pubLabel,
           java.util.Calendar recvTime,
           com.lexmark.webServices.processCustomerContact.client.Route[] route,
           java.lang.String uuid) {
           this.activation = activation;
           this.appLastSeqn = appLastSeqn;
           this.appPassword = appPassword;
           this.appSeqn = appSeqn;
           this.appUserName = appUserName;
           this.businessContext = businessContext;
           this.controlLabel = controlLabel;
           this.errorsTo = errorsTo;
           this.errorRequestsTo = errorRequestsTo;
           this.locale = locale;
           this.maxResults = maxResults;
           this.replyTo = replyTo;
           this.runLevel = runLevel;
           this.signature = signature;
           this.signatureType = signatureType;
           this.startResult = startResult;
           this.tag = tag;
           this.trackId = trackId;
           this.transactionId = transactionId;
           this.transformState = transformState;
           this.age = age;
           this.connectionIntegrity = connectionIntegrity;
           this.destId = destId;
           this.enqueueTime = enqueueTime;
           this.logBroker = logBroker;
           this.logHost = logHost;
           this.pubDistinguishedName = pubDistinguishedName;
           this.pubId = pubId;
           this.pubNetAddr = pubNetAddr;
           this.pubSeqn = pubSeqn;
           this.pubLabel = pubLabel;
           this.recvTime = recvTime;
           this.route = route;
           this.uuid = uuid;
    }


    /**
     * Gets the activation value for this Envelope.
     * 
     * @return activation
     */
    public java.lang.String getActivation() {
        return activation;
    }


    /**
     * Sets the activation value for this Envelope.
     * 
     * @param activation
     */
    public void setActivation(java.lang.String activation) {
        this.activation = activation;
    }


    /**
     * Gets the appLastSeqn value for this Envelope.
     * 
     * @return appLastSeqn
     */
    public java.lang.Integer getAppLastSeqn() {
        return appLastSeqn;
    }


    /**
     * Sets the appLastSeqn value for this Envelope.
     * 
     * @param appLastSeqn
     */
    public void setAppLastSeqn(java.lang.Integer appLastSeqn) {
        this.appLastSeqn = appLastSeqn;
    }


    /**
     * Gets the appPassword value for this Envelope.
     * 
     * @return appPassword
     */
    public java.lang.String getAppPassword() {
        return appPassword;
    }


    /**
     * Sets the appPassword value for this Envelope.
     * 
     * @param appPassword
     */
    public void setAppPassword(java.lang.String appPassword) {
        this.appPassword = appPassword;
    }


    /**
     * Gets the appSeqn value for this Envelope.
     * 
     * @return appSeqn
     */
    public java.lang.Integer getAppSeqn() {
        return appSeqn;
    }


    /**
     * Sets the appSeqn value for this Envelope.
     * 
     * @param appSeqn
     */
    public void setAppSeqn(java.lang.Integer appSeqn) {
        this.appSeqn = appSeqn;
    }


    /**
     * Gets the appUserName value for this Envelope.
     * 
     * @return appUserName
     */
    public java.lang.String getAppUserName() {
        return appUserName;
    }


    /**
     * Sets the appUserName value for this Envelope.
     * 
     * @param appUserName
     */
    public void setAppUserName(java.lang.String appUserName) {
        this.appUserName = appUserName;
    }


    /**
     * Gets the businessContext value for this Envelope.
     * 
     * @return businessContext
     */
    public java.lang.String getBusinessContext() {
        return businessContext;
    }


    /**
     * Sets the businessContext value for this Envelope.
     * 
     * @param businessContext
     */
    public void setBusinessContext(java.lang.String businessContext) {
        this.businessContext = businessContext;
    }


    /**
     * Gets the controlLabel value for this Envelope.
     * 
     * @return controlLabel
     */
    public short[] getControlLabel() {
        return controlLabel;
    }


    /**
     * Sets the controlLabel value for this Envelope.
     * 
     * @param controlLabel
     */
    public void setControlLabel(short[] controlLabel) {
        this.controlLabel = controlLabel;
    }


    /**
     * Gets the errorsTo value for this Envelope.
     * 
     * @return errorsTo
     */
    public java.lang.String getErrorsTo() {
        return errorsTo;
    }


    /**
     * Sets the errorsTo value for this Envelope.
     * 
     * @param errorsTo
     */
    public void setErrorsTo(java.lang.String errorsTo) {
        this.errorsTo = errorsTo;
    }


    /**
     * Gets the errorRequestsTo value for this Envelope.
     * 
     * @return errorRequestsTo
     */
    public java.lang.String getErrorRequestsTo() {
        return errorRequestsTo;
    }


    /**
     * Sets the errorRequestsTo value for this Envelope.
     * 
     * @param errorRequestsTo
     */
    public void setErrorRequestsTo(java.lang.String errorRequestsTo) {
        this.errorRequestsTo = errorRequestsTo;
    }


    /**
     * Gets the locale value for this Envelope.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this Envelope.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the maxResults value for this Envelope.
     * 
     * @return maxResults
     */
    public java.lang.Integer getMaxResults() {
        return maxResults;
    }


    /**
     * Sets the maxResults value for this Envelope.
     * 
     * @param maxResults
     */
    public void setMaxResults(java.lang.Integer maxResults) {
        this.maxResults = maxResults;
    }


    /**
     * Gets the replyTo value for this Envelope.
     * 
     * @return replyTo
     */
    public java.lang.String getReplyTo() {
        return replyTo;
    }


    /**
     * Sets the replyTo value for this Envelope.
     * 
     * @param replyTo
     */
    public void setReplyTo(java.lang.String replyTo) {
        this.replyTo = replyTo;
    }


    /**
     * Gets the runLevel value for this Envelope.
     * 
     * @return runLevel
     */
    public java.lang.Integer getRunLevel() {
        return runLevel;
    }


    /**
     * Sets the runLevel value for this Envelope.
     * 
     * @param runLevel
     */
    public void setRunLevel(java.lang.Integer runLevel) {
        this.runLevel = runLevel;
    }


    /**
     * Gets the signature value for this Envelope.
     * 
     * @return signature
     */
    public byte[] getSignature() {
        return signature;
    }


    /**
     * Sets the signature value for this Envelope.
     * 
     * @param signature
     */
    public void setSignature(byte[] signature) {
        this.signature = signature;
    }


    /**
     * Gets the signatureType value for this Envelope.
     * 
     * @return signatureType
     */
    public java.lang.String getSignatureType() {
        return signatureType;
    }


    /**
     * Sets the signatureType value for this Envelope.
     * 
     * @param signatureType
     */
    public void setSignatureType(java.lang.String signatureType) {
        this.signatureType = signatureType;
    }


    /**
     * Gets the startResult value for this Envelope.
     * 
     * @return startResult
     */
    public java.lang.Integer getStartResult() {
        return startResult;
    }


    /**
     * Sets the startResult value for this Envelope.
     * 
     * @param startResult
     */
    public void setStartResult(java.lang.Integer startResult) {
        this.startResult = startResult;
    }


    /**
     * Gets the tag value for this Envelope.
     * 
     * @return tag
     */
    public java.lang.Integer getTag() {
        return tag;
    }


    /**
     * Sets the tag value for this Envelope.
     * 
     * @param tag
     */
    public void setTag(java.lang.Integer tag) {
        this.tag = tag;
    }


    /**
     * Gets the trackId value for this Envelope.
     * 
     * @return trackId
     */
    public java.lang.String getTrackId() {
        return trackId;
    }


    /**
     * Sets the trackId value for this Envelope.
     * 
     * @param trackId
     */
    public void setTrackId(java.lang.String trackId) {
        this.trackId = trackId;
    }


    /**
     * Gets the transactionId value for this Envelope.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Envelope.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the transformState value for this Envelope.
     * 
     * @return transformState
     */
    public java.lang.String getTransformState() {
        return transformState;
    }


    /**
     * Sets the transformState value for this Envelope.
     * 
     * @param transformState
     */
    public void setTransformState(java.lang.String transformState) {
        this.transformState = transformState;
    }


    /**
     * Gets the age value for this Envelope.
     * 
     * @return age
     */
    public java.lang.Integer getAge() {
        return age;
    }


    /**
     * Sets the age value for this Envelope.
     * 
     * @param age
     */
    public void setAge(java.lang.Integer age) {
        this.age = age;
    }


    /**
     * Gets the connectionIntegrity value for this Envelope.
     * 
     * @return connectionIntegrity
     */
    public java.lang.String getConnectionIntegrity() {
        return connectionIntegrity;
    }


    /**
     * Sets the connectionIntegrity value for this Envelope.
     * 
     * @param connectionIntegrity
     */
    public void setConnectionIntegrity(java.lang.String connectionIntegrity) {
        this.connectionIntegrity = connectionIntegrity;
    }


    /**
     * Gets the destId value for this Envelope.
     * 
     * @return destId
     */
    public java.lang.String getDestId() {
        return destId;
    }


    /**
     * Sets the destId value for this Envelope.
     * 
     * @param destId
     */
    public void setDestId(java.lang.String destId) {
        this.destId = destId;
    }


    /**
     * Gets the enqueueTime value for this Envelope.
     * 
     * @return enqueueTime
     */
    public java.util.Calendar getEnqueueTime() {
        return enqueueTime;
    }


    /**
     * Sets the enqueueTime value for this Envelope.
     * 
     * @param enqueueTime
     */
    public void setEnqueueTime(java.util.Calendar enqueueTime) {
        this.enqueueTime = enqueueTime;
    }


    /**
     * Gets the logBroker value for this Envelope.
     * 
     * @return logBroker
     */
    public java.lang.String getLogBroker() {
        return logBroker;
    }


    /**
     * Sets the logBroker value for this Envelope.
     * 
     * @param logBroker
     */
    public void setLogBroker(java.lang.String logBroker) {
        this.logBroker = logBroker;
    }


    /**
     * Gets the logHost value for this Envelope.
     * 
     * @return logHost
     */
    public java.lang.String getLogHost() {
        return logHost;
    }


    /**
     * Sets the logHost value for this Envelope.
     * 
     * @param logHost
     */
    public void setLogHost(java.lang.String logHost) {
        this.logHost = logHost;
    }


    /**
     * Gets the pubDistinguishedName value for this Envelope.
     * 
     * @return pubDistinguishedName
     */
    public java.lang.String getPubDistinguishedName() {
        return pubDistinguishedName;
    }


    /**
     * Sets the pubDistinguishedName value for this Envelope.
     * 
     * @param pubDistinguishedName
     */
    public void setPubDistinguishedName(java.lang.String pubDistinguishedName) {
        this.pubDistinguishedName = pubDistinguishedName;
    }


    /**
     * Gets the pubId value for this Envelope.
     * 
     * @return pubId
     */
    public java.lang.String getPubId() {
        return pubId;
    }


    /**
     * Sets the pubId value for this Envelope.
     * 
     * @param pubId
     */
    public void setPubId(java.lang.String pubId) {
        this.pubId = pubId;
    }


    /**
     * Gets the pubNetAddr value for this Envelope.
     * 
     * @return pubNetAddr
     */
    public byte[] getPubNetAddr() {
        return pubNetAddr;
    }


    /**
     * Sets the pubNetAddr value for this Envelope.
     * 
     * @param pubNetAddr
     */
    public void setPubNetAddr(byte[] pubNetAddr) {
        this.pubNetAddr = pubNetAddr;
    }


    /**
     * Gets the pubSeqn value for this Envelope.
     * 
     * @return pubSeqn
     */
    public java.lang.Long getPubSeqn() {
        return pubSeqn;
    }


    /**
     * Sets the pubSeqn value for this Envelope.
     * 
     * @param pubSeqn
     */
    public void setPubSeqn(java.lang.Long pubSeqn) {
        this.pubSeqn = pubSeqn;
    }


    /**
     * Gets the pubLabel value for this Envelope.
     * 
     * @return pubLabel
     */
    public short[] getPubLabel() {
        return pubLabel;
    }


    /**
     * Sets the pubLabel value for this Envelope.
     * 
     * @param pubLabel
     */
    public void setPubLabel(short[] pubLabel) {
        this.pubLabel = pubLabel;
    }


    /**
     * Gets the recvTime value for this Envelope.
     * 
     * @return recvTime
     */
    public java.util.Calendar getRecvTime() {
        return recvTime;
    }


    /**
     * Sets the recvTime value for this Envelope.
     * 
     * @param recvTime
     */
    public void setRecvTime(java.util.Calendar recvTime) {
        this.recvTime = recvTime;
    }


    /**
     * Gets the route value for this Envelope.
     * 
     * @return route
     */
    public com.lexmark.webServices.processCustomerContact.client.Route[] getRoute() {
        return route;
    }


    /**
     * Sets the route value for this Envelope.
     * 
     * @param route
     */
    public void setRoute(com.lexmark.webServices.processCustomerContact.client.Route[] route) {
        this.route = route;
    }


    /**
     * Gets the uuid value for this Envelope.
     * 
     * @return uuid
     */
    public java.lang.String getUuid() {
        return uuid;
    }


    /**
     * Sets the uuid value for this Envelope.
     * 
     * @param uuid
     */
    public void setUuid(java.lang.String uuid) {
        this.uuid = uuid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Envelope)) return false;
        Envelope other = (Envelope) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.activation==null && other.getActivation()==null) || 
             (this.activation!=null &&
              this.activation.equals(other.getActivation()))) &&
            ((this.appLastSeqn==null && other.getAppLastSeqn()==null) || 
             (this.appLastSeqn!=null &&
              this.appLastSeqn.equals(other.getAppLastSeqn()))) &&
            ((this.appPassword==null && other.getAppPassword()==null) || 
             (this.appPassword!=null &&
              this.appPassword.equals(other.getAppPassword()))) &&
            ((this.appSeqn==null && other.getAppSeqn()==null) || 
             (this.appSeqn!=null &&
              this.appSeqn.equals(other.getAppSeqn()))) &&
            ((this.appUserName==null && other.getAppUserName()==null) || 
             (this.appUserName!=null &&
              this.appUserName.equals(other.getAppUserName()))) &&
            ((this.businessContext==null && other.getBusinessContext()==null) || 
             (this.businessContext!=null &&
              this.businessContext.equals(other.getBusinessContext()))) &&
            ((this.controlLabel==null && other.getControlLabel()==null) || 
             (this.controlLabel!=null &&
              java.util.Arrays.equals(this.controlLabel, other.getControlLabel()))) &&
            ((this.errorsTo==null && other.getErrorsTo()==null) || 
             (this.errorsTo!=null &&
              this.errorsTo.equals(other.getErrorsTo()))) &&
            ((this.errorRequestsTo==null && other.getErrorRequestsTo()==null) || 
             (this.errorRequestsTo!=null &&
              this.errorRequestsTo.equals(other.getErrorRequestsTo()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.maxResults==null && other.getMaxResults()==null) || 
             (this.maxResults!=null &&
              this.maxResults.equals(other.getMaxResults()))) &&
            ((this.replyTo==null && other.getReplyTo()==null) || 
             (this.replyTo!=null &&
              this.replyTo.equals(other.getReplyTo()))) &&
            ((this.runLevel==null && other.getRunLevel()==null) || 
             (this.runLevel!=null &&
              this.runLevel.equals(other.getRunLevel()))) &&
            ((this.signature==null && other.getSignature()==null) || 
             (this.signature!=null &&
              java.util.Arrays.equals(this.signature, other.getSignature()))) &&
            ((this.signatureType==null && other.getSignatureType()==null) || 
             (this.signatureType!=null &&
              this.signatureType.equals(other.getSignatureType()))) &&
            ((this.startResult==null && other.getStartResult()==null) || 
             (this.startResult!=null &&
              this.startResult.equals(other.getStartResult()))) &&
            ((this.tag==null && other.getTag()==null) || 
             (this.tag!=null &&
              this.tag.equals(other.getTag()))) &&
            ((this.trackId==null && other.getTrackId()==null) || 
             (this.trackId!=null &&
              this.trackId.equals(other.getTrackId()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId()))) &&
            ((this.transformState==null && other.getTransformState()==null) || 
             (this.transformState!=null &&
              this.transformState.equals(other.getTransformState()))) &&
            ((this.age==null && other.getAge()==null) || 
             (this.age!=null &&
              this.age.equals(other.getAge()))) &&
            ((this.connectionIntegrity==null && other.getConnectionIntegrity()==null) || 
             (this.connectionIntegrity!=null &&
              this.connectionIntegrity.equals(other.getConnectionIntegrity()))) &&
            ((this.destId==null && other.getDestId()==null) || 
             (this.destId!=null &&
              this.destId.equals(other.getDestId()))) &&
            ((this.enqueueTime==null && other.getEnqueueTime()==null) || 
             (this.enqueueTime!=null &&
              this.enqueueTime.equals(other.getEnqueueTime()))) &&
            ((this.logBroker==null && other.getLogBroker()==null) || 
             (this.logBroker!=null &&
              this.logBroker.equals(other.getLogBroker()))) &&
            ((this.logHost==null && other.getLogHost()==null) || 
             (this.logHost!=null &&
              this.logHost.equals(other.getLogHost()))) &&
            ((this.pubDistinguishedName==null && other.getPubDistinguishedName()==null) || 
             (this.pubDistinguishedName!=null &&
              this.pubDistinguishedName.equals(other.getPubDistinguishedName()))) &&
            ((this.pubId==null && other.getPubId()==null) || 
             (this.pubId!=null &&
              this.pubId.equals(other.getPubId()))) &&
            ((this.pubNetAddr==null && other.getPubNetAddr()==null) || 
             (this.pubNetAddr!=null &&
              java.util.Arrays.equals(this.pubNetAddr, other.getPubNetAddr()))) &&
            ((this.pubSeqn==null && other.getPubSeqn()==null) || 
             (this.pubSeqn!=null &&
              this.pubSeqn.equals(other.getPubSeqn()))) &&
            ((this.pubLabel==null && other.getPubLabel()==null) || 
             (this.pubLabel!=null &&
              java.util.Arrays.equals(this.pubLabel, other.getPubLabel()))) &&
            ((this.recvTime==null && other.getRecvTime()==null) || 
             (this.recvTime!=null &&
              this.recvTime.equals(other.getRecvTime()))) &&
            ((this.route==null && other.getRoute()==null) || 
             (this.route!=null &&
              java.util.Arrays.equals(this.route, other.getRoute()))) &&
            ((this.uuid==null && other.getUuid()==null) || 
             (this.uuid!=null &&
              this.uuid.equals(other.getUuid())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getActivation() != null) {
            _hashCode += getActivation().hashCode();
        }
        if (getAppLastSeqn() != null) {
            _hashCode += getAppLastSeqn().hashCode();
        }
        if (getAppPassword() != null) {
            _hashCode += getAppPassword().hashCode();
        }
        if (getAppSeqn() != null) {
            _hashCode += getAppSeqn().hashCode();
        }
        if (getAppUserName() != null) {
            _hashCode += getAppUserName().hashCode();
        }
        if (getBusinessContext() != null) {
            _hashCode += getBusinessContext().hashCode();
        }
        if (getControlLabel() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getControlLabel());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getControlLabel(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getErrorsTo() != null) {
            _hashCode += getErrorsTo().hashCode();
        }
        if (getErrorRequestsTo() != null) {
            _hashCode += getErrorRequestsTo().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getMaxResults() != null) {
            _hashCode += getMaxResults().hashCode();
        }
        if (getReplyTo() != null) {
            _hashCode += getReplyTo().hashCode();
        }
        if (getRunLevel() != null) {
            _hashCode += getRunLevel().hashCode();
        }
        if (getSignature() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSignature());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSignature(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSignatureType() != null) {
            _hashCode += getSignatureType().hashCode();
        }
        if (getStartResult() != null) {
            _hashCode += getStartResult().hashCode();
        }
        if (getTag() != null) {
            _hashCode += getTag().hashCode();
        }
        if (getTrackId() != null) {
            _hashCode += getTrackId().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        if (getTransformState() != null) {
            _hashCode += getTransformState().hashCode();
        }
        if (getAge() != null) {
            _hashCode += getAge().hashCode();
        }
        if (getConnectionIntegrity() != null) {
            _hashCode += getConnectionIntegrity().hashCode();
        }
        if (getDestId() != null) {
            _hashCode += getDestId().hashCode();
        }
        if (getEnqueueTime() != null) {
            _hashCode += getEnqueueTime().hashCode();
        }
        if (getLogBroker() != null) {
            _hashCode += getLogBroker().hashCode();
        }
        if (getLogHost() != null) {
            _hashCode += getLogHost().hashCode();
        }
        if (getPubDistinguishedName() != null) {
            _hashCode += getPubDistinguishedName().hashCode();
        }
        if (getPubId() != null) {
            _hashCode += getPubId().hashCode();
        }
        if (getPubNetAddr() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPubNetAddr());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPubNetAddr(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPubSeqn() != null) {
            _hashCode += getPubSeqn().hashCode();
        }
        if (getPubLabel() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPubLabel());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPubLabel(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRecvTime() != null) {
            _hashCode += getRecvTime().hashCode();
        }
        if (getRoute() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRoute());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRoute(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUuid() != null) {
            _hashCode += getUuid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Envelope.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "envelope"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appLastSeqn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appLastSeqn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appSeqn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appSeqn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appUserName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appUserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessContext");
        elemField.setXmlName(new javax.xml.namespace.QName("", "businessContext"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controlLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "controlLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfshortItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorsTo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorsTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorRequestsTo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorRequestsTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxResults");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyTo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "replyTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("runLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "runLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signature");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfbyteItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signatureType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trackId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transformState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transformState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("age");
        elemField.setXmlName(new javax.xml.namespace.QName("", "age"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("connectionIntegrity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "connectionIntegrity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enqueueTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enqueueTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logBroker");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logBroker"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logHost");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logHost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pubDistinguishedName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pubDistinguishedName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pubId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pubId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pubNetAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pubNetAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfbyteItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pubSeqn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pubSeqn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pubLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pubLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfshortItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recvTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recvTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("route");
        elemField.setXmlName(new javax.xml.namespace.QName("", "route"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "route"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfrouteItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uuid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
