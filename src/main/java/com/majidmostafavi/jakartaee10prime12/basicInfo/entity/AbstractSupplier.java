package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class AbstractSupplier extends AbstractEntity {
    private Long id;
    private String name;
    private String economicCode;
    private String address;
    private String mobile;
    private Person marketer;
    private Brand brand;

    public AbstractSupplier() {

    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ECONOMIC_CODE")
    public String getEconomicCode() {
        return economicCode;
    }
    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ManyToOne
    @JoinColumn(name = "PERSON_Id", foreignKey = @ForeignKey(name = "FK_SUPPLIER_PRSN"))
    public Person getMarketer() {
        return marketer;
    }
    public void setMarketer(Person marketer) {
        this.marketer = marketer;
    }

    @ManyToOne
    @JoinColumn(name = "BRAND_Id", foreignKey = @ForeignKey(name = "FK_SUPPLIER_BRAND"))
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
