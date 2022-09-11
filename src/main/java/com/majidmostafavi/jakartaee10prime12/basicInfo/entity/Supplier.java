package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUPPLIER")
@NamedQueries({   @NamedQuery(name = "findAllActiveSupplier" , query = "select supplier from Supplier supplier where supplier.active=true "),
                  @NamedQuery(name = "findAllSupplier" , query = "select supplier from Supplier supplier"),
                  @NamedQuery(name = "updateActiveSupplier", query = "update Supplier supplier set supplier.active=:active where supplier.id=:id")


})
public class Supplier extends AbstractSupplier {
    public Supplier() {
    }
}
