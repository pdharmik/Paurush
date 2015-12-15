
package com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.2-b05-RC1
 * Generated source version: 2.1
 * 
 */
@WebService(name = "DctmWebService", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DctmWebService {


    /**
     * 
     * @param user
     * @param serviceuser
     * @param serviceuserpassword
     * @param repository
     * @param application
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "authenticate", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.Authenticate")
    @ResponseWrapper(localName = "authenticateResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.AuthenticateResponse")
    public String authenticate(
        @WebParam(name = "repository", targetNamespace = "")
        String repository,
        @WebParam(name = "serviceuser", targetNamespace = "")
        String serviceuser,
        @WebParam(name = "user", targetNamespace = "")
        String user,
        @WebParam(name = "serviceuserpassword", targetNamespace = "")
        String serviceuserpassword,
        @WebParam(name = "application", targetNamespace = "")
        String application)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param dqlquery
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "query", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.Query")
    @ResponseWrapper(localName = "queryResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.QueryResponse")
    public String query(
        @WebParam(name = "dqlquery", targetNamespace = "")
        String dqlquery)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param content
     * @param properties
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createDocument", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.CreateDocument")
    @ResponseWrapper(localName = "createDocumentResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.CreateDocumentResponse")
    public String createDocument(
        @WebParam(name = "properties", targetNamespace = "")
        String properties,
        @WebParam(name = "content", targetNamespace = "")
        byte[] content)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param content
     * @param properties
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateObject", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.UpdateObject")
    @ResponseWrapper(localName = "updateObjectResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.UpdateObjectResponse")
    public String updateObject(
        @WebParam(name = "properties", targetNamespace = "")
        String properties,
        @WebParam(name = "content", targetNamespace = "")
        byte[] content)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param properties
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteDocument", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DeleteDocument")
    @ResponseWrapper(localName = "deleteDocumentResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DeleteDocumentResponse")
    public String deleteDocument(
        @WebParam(name = "properties", targetNamespace = "")
        String properties)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param properties
     * @return
     *     returns byte[]
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getDocument", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.GetDocument")
    @ResponseWrapper(localName = "getDocumentResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.GetDocumentResponse")
    public byte[] getDocument(
        @WebParam(name = "properties", targetNamespace = "")
        String properties)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param properties
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createFolder", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.CreateFolder")
    @ResponseWrapper(localName = "createFolderResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.CreateFolderResponse")
    public String createFolder(
        @WebParam(name = "properties", targetNamespace = "")
        String properties)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param properties
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "moveContent", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.MoveContent")
    @ResponseWrapper(localName = "moveContentResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.MoveContentResponse")
    public String moveContent(
        @WebParam(name = "properties", targetNamespace = "")
        String properties)
        throws ECMServiceException_Exception
    ;

    /**
     * 
     * @param properties
     * @return
     *     returns java.lang.String
     * @throws ECMServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteFolder", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DeleteFolder")
    @ResponseWrapper(localName = "deleteFolderResponse", targetNamespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", className = "com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DeleteFolderResponse")
    public String deleteFolder(
        @WebParam(name = "properties", targetNamespace = "")
        String properties)
        throws ECMServiceException_Exception
    ;

}
