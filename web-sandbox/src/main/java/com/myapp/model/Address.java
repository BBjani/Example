package com.myapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an address.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Address")
@lombok.Getter
@lombok.Setter
@lombok.ToString
@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
public class Address {

    @XmlElement(name = "country")
    private String country;

    @XmlElement(name = "postalcode")
    private Integer postalcode;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "street")
    private String street;
}
