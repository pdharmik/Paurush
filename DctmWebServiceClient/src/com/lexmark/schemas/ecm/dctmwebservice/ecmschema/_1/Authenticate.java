
package com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for authenticate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="authenticate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="repository" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceuser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceuserpassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authenticate", propOrder = {
    "repository",
    "serviceuser",
    "user",
    "serviceuserpassword",
    "application"
})
public class Authenticate {

    @XmlElement(required = true)
    protected String repository;
    @XmlElement(required = true)
    protected String serviceuser;
    @XmlElement(required = true)
    protected String user;
    @XmlElement(required = true)
    protected String serviceuserpassword;
    @XmlElement(required = true)
    protected String application;

    /**
     * Gets the value of the repository property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepository() {
        return repository;
    }

    /**
     * Sets the value of the repository property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepository(String value) {
        this.repository = value;
    }

    /**
     * Gets the value of the serviceuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceuser() {
        return serviceuser;
    }

    /**
     * Sets the value of the serviceuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceuser(String value) {
        this.serviceuser = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the serviceuserpassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceuserpassword() {
        return serviceuserpassword;
    }

    /**
     * Sets the value of the serviceuserpassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceuserpassword(String value) {
        this.serviceuserpassword = value;
    }

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(String value) {
        this.application = value;
    }

}
