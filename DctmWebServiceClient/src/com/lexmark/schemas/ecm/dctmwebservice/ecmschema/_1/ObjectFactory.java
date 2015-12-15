
package com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Query_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "query");
    private final static QName _UpdateObjectResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "updateObjectResponse");
    private final static QName _DeleteDocument_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "deleteDocument");
    private final static QName _CreateDocument_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "createDocument");
    private final static QName _DeleteFolder_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "deleteFolder");
    private final static QName _DeleteDocumentResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "deleteDocumentResponse");
    private final static QName _ECMServiceException_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "ECMServiceException");
    private final static QName _CreateFolder_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "createFolder");
    private final static QName _MoveContentResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "moveContentResponse");
    private final static QName _UpdateObject_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "updateObject");
    private final static QName _CreateDocumentResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "createDocumentResponse");
    private final static QName _DocumentumSecurityToken_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "DocumentumSecurityToken");
    private final static QName _DeleteFolderResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "deleteFolderResponse");
    private final static QName _MoveContent_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "moveContent");
    private final static QName _QueryResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "queryResponse");
    private final static QName _GetDocumentResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "getDocumentResponse");
    private final static QName _GetDocument_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "getDocument");
    private final static QName _AuthenticateResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "authenticateResponse");
    private final static QName _Authenticate_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "authenticate");
    private final static QName _CreateFolderResponse_QNAME = new QName("http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", "createFolderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Query }
     * 
     */
    public Query createQuery() {
        return new Query();
    }

    /**
     * Create an instance of {@link UpdateObjectResponse }
     * 
     */
    public UpdateObjectResponse createUpdateObjectResponse() {
        return new UpdateObjectResponse();
    }

    /**
     * Create an instance of {@link DeleteDocumentResponse }
     * 
     */
    public DeleteDocumentResponse createDeleteDocumentResponse() {
        return new DeleteDocumentResponse();
    }

    /**
     * Create an instance of {@link QueryResponse }
     * 
     */
    public QueryResponse createQueryResponse() {
        return new QueryResponse();
    }

    /**
     * Create an instance of {@link DocumentumSecurityToken }
     * 
     */
    public DocumentumSecurityToken createDocumentumSecurityToken() {
        return new DocumentumSecurityToken();
    }

    /**
     * Create an instance of {@link UpdateObject }
     * 
     */
    public UpdateObject createUpdateObject() {
        return new UpdateObject();
    }

    /**
     * Create an instance of {@link MoveContent }
     * 
     */
    public MoveContent createMoveContent() {
        return new MoveContent();
    }

    /**
     * Create an instance of {@link DeleteFolderResponse }
     * 
     */
    public DeleteFolderResponse createDeleteFolderResponse() {
        return new DeleteFolderResponse();
    }

    /**
     * Create an instance of {@link ECMServiceException }
     * 
     */
    public ECMServiceException createECMServiceException() {
        return new ECMServiceException();
    }

    /**
     * Create an instance of {@link GetDocument }
     * 
     */
    public GetDocument createGetDocument() {
        return new GetDocument();
    }

    /**
     * Create an instance of {@link Authenticate }
     * 
     */
    public Authenticate createAuthenticate() {
        return new Authenticate();
    }

    /**
     * Create an instance of {@link CreateFolder }
     * 
     */
    public CreateFolder createCreateFolder() {
        return new CreateFolder();
    }

    /**
     * Create an instance of {@link DeleteDocument }
     * 
     */
    public DeleteDocument createDeleteDocument() {
        return new DeleteDocument();
    }

    /**
     * Create an instance of {@link DeleteFolder }
     * 
     */
    public DeleteFolder createDeleteFolder() {
        return new DeleteFolder();
    }

    /**
     * Create an instance of {@link CreateDocument }
     * 
     */
    public CreateDocument createCreateDocument() {
        return new CreateDocument();
    }

    /**
     * Create an instance of {@link AuthenticateResponse }
     * 
     */
    public AuthenticateResponse createAuthenticateResponse() {
        return new AuthenticateResponse();
    }

    /**
     * Create an instance of {@link MoveContentResponse }
     * 
     */
    public MoveContentResponse createMoveContentResponse() {
        return new MoveContentResponse();
    }

    /**
     * Create an instance of {@link GetDocumentResponse }
     * 
     */
    public GetDocumentResponse createGetDocumentResponse() {
        return new GetDocumentResponse();
    }

    /**
     * Create an instance of {@link CreateDocumentResponse }
     * 
     */
    public CreateDocumentResponse createCreateDocumentResponse() {
        return new CreateDocumentResponse();
    }

    /**
     * Create an instance of {@link CreateFolderResponse }
     * 
     */
    public CreateFolderResponse createCreateFolderResponse() {
        return new CreateFolderResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Query }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "query")
    public JAXBElement<Query> createQuery(Query value) {
        return new JAXBElement<Query>(_Query_QNAME, Query.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "updateObjectResponse")
    public JAXBElement<UpdateObjectResponse> createUpdateObjectResponse(UpdateObjectResponse value) {
        return new JAXBElement<UpdateObjectResponse>(_UpdateObjectResponse_QNAME, UpdateObjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "deleteDocument")
    public JAXBElement<DeleteDocument> createDeleteDocument(DeleteDocument value) {
        return new JAXBElement<DeleteDocument>(_DeleteDocument_QNAME, DeleteDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "createDocument")
    public JAXBElement<CreateDocument> createCreateDocument(CreateDocument value) {
        return new JAXBElement<CreateDocument>(_CreateDocument_QNAME, CreateDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFolder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "deleteFolder")
    public JAXBElement<DeleteFolder> createDeleteFolder(DeleteFolder value) {
        return new JAXBElement<DeleteFolder>(_DeleteFolder_QNAME, DeleteFolder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "deleteDocumentResponse")
    public JAXBElement<DeleteDocumentResponse> createDeleteDocumentResponse(DeleteDocumentResponse value) {
        return new JAXBElement<DeleteDocumentResponse>(_DeleteDocumentResponse_QNAME, DeleteDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ECMServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "ECMServiceException")
    public JAXBElement<ECMServiceException> createECMServiceException(ECMServiceException value) {
        return new JAXBElement<ECMServiceException>(_ECMServiceException_QNAME, ECMServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateFolder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "createFolder")
    public JAXBElement<CreateFolder> createCreateFolder(CreateFolder value) {
        return new JAXBElement<CreateFolder>(_CreateFolder_QNAME, CreateFolder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoveContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "moveContentResponse")
    public JAXBElement<MoveContentResponse> createMoveContentResponse(MoveContentResponse value) {
        return new JAXBElement<MoveContentResponse>(_MoveContentResponse_QNAME, MoveContentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "updateObject")
    public JAXBElement<UpdateObject> createUpdateObject(UpdateObject value) {
        return new JAXBElement<UpdateObject>(_UpdateObject_QNAME, UpdateObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "createDocumentResponse")
    public JAXBElement<CreateDocumentResponse> createCreateDocumentResponse(CreateDocumentResponse value) {
        return new JAXBElement<CreateDocumentResponse>(_CreateDocumentResponse_QNAME, CreateDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentumSecurityToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "DocumentumSecurityToken")
    public JAXBElement<DocumentumSecurityToken> createDocumentumSecurityToken(DocumentumSecurityToken value) {
        return new JAXBElement<DocumentumSecurityToken>(_DocumentumSecurityToken_QNAME, DocumentumSecurityToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFolderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "deleteFolderResponse")
    public JAXBElement<DeleteFolderResponse> createDeleteFolderResponse(DeleteFolderResponse value) {
        return new JAXBElement<DeleteFolderResponse>(_DeleteFolderResponse_QNAME, DeleteFolderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoveContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "moveContent")
    public JAXBElement<MoveContent> createMoveContent(MoveContent value) {
        return new JAXBElement<MoveContent>(_MoveContent_QNAME, MoveContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "queryResponse")
    public JAXBElement<QueryResponse> createQueryResponse(QueryResponse value) {
        return new JAXBElement<QueryResponse>(_QueryResponse_QNAME, QueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "getDocumentResponse")
    public JAXBElement<GetDocumentResponse> createGetDocumentResponse(GetDocumentResponse value) {
        return new JAXBElement<GetDocumentResponse>(_GetDocumentResponse_QNAME, GetDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "getDocument")
    public JAXBElement<GetDocument> createGetDocument(GetDocument value) {
        return new JAXBElement<GetDocument>(_GetDocument_QNAME, GetDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "authenticateResponse")
    public JAXBElement<AuthenticateResponse> createAuthenticateResponse(AuthenticateResponse value) {
        return new JAXBElement<AuthenticateResponse>(_AuthenticateResponse_QNAME, AuthenticateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authenticate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "authenticate")
    public JAXBElement<Authenticate> createAuthenticate(Authenticate value) {
        return new JAXBElement<Authenticate>(_Authenticate_QNAME, Authenticate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateFolderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0", name = "createFolderResponse")
    public JAXBElement<CreateFolderResponse> createCreateFolderResponse(CreateFolderResponse value) {
        return new JAXBElement<CreateFolderResponse>(_CreateFolderResponse_QNAME, CreateFolderResponse.class, null, value);
    }

}
