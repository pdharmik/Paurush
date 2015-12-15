
package com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for query complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="query">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dqlquery" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "query", propOrder = {
    "dqlquery"
})
public class Query {

    @XmlElement(required = true)
    protected String dqlquery;

    /**
     * Gets the value of the dqlquery property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDqlquery() {
        return dqlquery;
    }

    /**
     * Sets the value of the dqlquery property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDqlquery(String value) {
        this.dqlquery = value;
    }

}
