package com.thoughtworks.repospring.modal;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Product {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    private String name;

    private String weight;

    private String amount;

    private String description;
}
