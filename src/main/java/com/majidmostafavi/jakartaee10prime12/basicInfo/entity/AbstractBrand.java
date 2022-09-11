package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
public abstract class AbstractBrand extends AbstractEntity {

    private Long id;
    private String name;
    private Long code;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code")
    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBrand that = (AbstractBrand) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
