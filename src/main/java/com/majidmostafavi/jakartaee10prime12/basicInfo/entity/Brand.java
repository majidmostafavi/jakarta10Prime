package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "Person_Category")
@NamedQueries({
//        @NamedQuery(name = "findBrandByName", query = "select brand from Brand brand where brand.name like :name and brand.active=true"),
//        @NamedQuery(name = "findBrandByCode", query = "select brand from Brand brand where brand.code=:code and brand.active=true"),
//        @NamedQuery(name = "countBrand", query = "select count(brand) from Brand brand where brand.name=:name or brand.code=:code"),
        @NamedQuery(name = "findBrandAllActiveAndSort",query = "select brand from Brand brand where brand.active=:active order by brand.code"),
//        @NamedQuery(name="findAllBrand",query = "select brand from Brand brand")
})
public class Brand extends AbstractBrand {
}
