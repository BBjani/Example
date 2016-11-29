package com.myapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a person.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Person")
@lombok.Getter
@lombok.Setter
@lombok.ToString
@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
public class Person {

    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "address")
    private final Address address;

    public Person() {
        this.address = new Address();
    }
}
