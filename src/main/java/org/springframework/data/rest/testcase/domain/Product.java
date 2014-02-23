
package org.springframework.data.rest.testcase.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Product implements Identifiable<Long>, Serializable {

    private static final long serialVersionUID = 5378362263427920938L;

    /**
     * Primary Key - Unique Identifier of the Object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    @JsonIgnore
    private final Long id;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("productAttributes")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductAttribute> productAttributes = new ArrayList<>();

    public Product() {
        this.id = null;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public void addProductAttribute(ProductAttribute productAttribute) {
        productAttribute.setProduct(this);
        getProductAttributes().add(productAttribute);
    }

}
