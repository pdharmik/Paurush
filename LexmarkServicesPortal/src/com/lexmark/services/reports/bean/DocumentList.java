package com.lexmark.services.reports.bean;

import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "docList")
@XmlAccessorType (XmlAccessType.FIELD)
public class DocumentList 
{
    @XmlElement(name = "document")
    private List<DocumentBean> documentList = null;
 
    public List<DocumentBean> getDocumentList() {
        return documentList;
    }
 
    public void setDocumentList(List<DocumentBean> documentList) {
        this.documentList = documentList;
    }
}