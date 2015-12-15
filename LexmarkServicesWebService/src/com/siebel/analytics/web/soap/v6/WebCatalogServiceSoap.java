/**
 * WebCatalogServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v6;

public interface WebCatalogServiceSoap extends java.rmi.Remote {
    public void deleteItem(java.lang.String path, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void removeFolder(java.lang.String path, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void createFolder(java.lang.String path, boolean createIfNotExists, boolean createIntermediateDirs, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void createLink(java.lang.String path, java.lang.String pathTarget, boolean overwriteIfExists, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void moveItem(java.lang.String pathSrc, java.lang.String pathDest, int flagACL, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void copyItem(java.lang.String pathSrc, java.lang.String pathDest, int flagACL, java.lang.String sessionID) throws java.rmi.RemoteException;
    public byte[] copyItem2(java.lang.String[] path, boolean recursive, boolean permissions, boolean timestamps, boolean useMtom, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void pasteItem2(byte[] archive, java.lang.String replacePath, int flagACL, int flagOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.ItemInfo[] getSubItems(java.lang.String path, java.lang.String mask, boolean resolveLinks, com.siebel.analytics.web.soap.v6.GetSubItemsParams options, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.ItemInfo getItemInfo(java.lang.String path, boolean resolveLinks, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void setItemProperty(java.lang.String[] path, java.lang.String[] name, java.lang.String[] value, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void maintenanceMode(boolean flag, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.CatalogObject[] readObjects(java.lang.String[] paths, boolean resolveLinks, com.siebel.analytics.web.soap.v6.ErrorDetailsLevel errorMode, com.siebel.analytics.web.soap.v6.ReadObjectsReturnOptions returnOptions, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.ErrorInfo[] writeObjects(com.siebel.analytics.web.soap.v6.CatalogObject[] catalogObjects, boolean allowOverwrite, boolean createIntermediateDirs, com.siebel.analytics.web.soap.v6.ErrorDetailsLevel errorMode, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void updateCatalogItemACL(java.lang.String[] path, com.siebel.analytics.web.soap.v6.ACL acl, com.siebel.analytics.web.soap.v6.UpdateCatalogItemACLParams options, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void setOwnership(java.lang.String[] path, com.siebel.analytics.web.soap.v6.Account owner, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void setItemAttributes(java.lang.String[] path, int value, int valueOff, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
}
