/**
 * WebCatalogServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public interface WebCatalogServiceSoap extends java.rmi.Remote {
    public void deleteItem(java.lang.String path, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void removeFolder(java.lang.String path, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void createFolder(java.lang.String path, boolean createIfNotExists, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void createLink(java.lang.String path, java.lang.String pathTarget, boolean overwriteIfExists, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void moveItem(java.lang.String pathSrc, java.lang.String pathDest, int flagACL, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void copyItem(java.lang.String pathSrc, java.lang.String pathDest, int flagACL, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String copyItem2(java.lang.String[] path, boolean recursive, boolean permissions, boolean timestamps, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void pasteItem2(java.lang.String archive, java.lang.String replacePath, int flagACL, int flagOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.ItemInfo[] getSubItems(java.lang.String path, java.lang.String mask, boolean resolveLinks, com.siebel.analytics.web.soap.v5.GetSubItemsParams options, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.ItemInfo getItemInfo(java.lang.String path, boolean resolveLinks, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void setItemProperty(java.lang.String[] path, java.lang.String[] name, java.lang.String[] value, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.CatalogObject readObject(java.lang.String path, boolean resolveLinks, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.CatalogObject[] readObjects(java.lang.String[] paths, boolean resolveLinks, com.siebel.analytics.web.soap.v5.ErrorDetailsLevel errorMode, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void writeObject(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void writeReport(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void writeDashboard(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void writeDashboardPage(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void writeDashboardPrompt(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void writeSavedFilter(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.ErrorInfo[] writeObjects(com.siebel.analytics.web.soap.v5.CatalogObject[] catalogObjects, boolean allowOverwrite, com.siebel.analytics.web.soap.v5.ErrorDetailsLevel errorMode, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void updateCatalogItemACL(java.lang.String path, com.siebel.analytics.web.soap.v5.ACL acl, com.siebel.analytics.web.soap.v5.UpdateCatalogItemACLParams options, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void takeOwnership(java.lang.String[] path, java.lang.String name, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void setItemAttributes(java.lang.String[] path, int value, int valueOff, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException;
}
