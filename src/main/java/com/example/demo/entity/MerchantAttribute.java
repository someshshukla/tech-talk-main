package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MerchantAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "meta_attribute_id")
    private Long metaAttributeId;

    @Column(name = "meta_attribute_name")
    private String metaAttributeName;

    @Column(name = "schema_json")
    private String jsonData;

}
