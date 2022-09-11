package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;


import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

import java.util.Set;

@MappedSuperclass
public abstract class AbstractProvince extends AbstractEntity {

    private Long id;
    private String name;
    private String code;
    private Country country;
    private Set<City> citySet;


    public AbstractProvince() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID", foreignKey = @ForeignKey(name = "FK_COUNTRY_PROVINCE"))
    public Country getCountry() {return country;}
    public void setCountry(Country country) {this.country = country;}

    @OneToMany(mappedBy = "province")
    public Set<City> getCitySet() {
        return citySet;
    }
    public void setCitySet(Set<City> citySet) {
        this.citySet = citySet;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractProvince)) return false;

        AbstractProvince that = (AbstractProvince) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return code != null ? code.equals(that.code) : that.code == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}

