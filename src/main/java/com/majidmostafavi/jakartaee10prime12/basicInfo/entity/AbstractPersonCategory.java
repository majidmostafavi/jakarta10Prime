package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

import java.util.Objects;


@MappedSuperclass
public abstract class  AbstractPersonCategory extends AbstractEntity {

    private Long id;
    private String name;
    private long number;

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

    @Column(name = "number")
    public long getNumber() {
        return number;
    }
    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPersonCategory that = (AbstractPersonCategory) o;
        return number == that.number && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number);
    }
}
