package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.annotation.UsecaseSupport;
import com.majidmostafavi.jakartaee10prime12.core.enumration.SubSystem;
import com.majidmostafavi.jakartaee10prime12.core.enumration.UsecaseType;
import jakarta.persistence.*;

@Entity

@Table(name = "COUNTRY")
@NamedQueries({
        @NamedQuery(name = "findAllCountries" , query = "select country from Country country where country.active=true "),
        @NamedQuery(name = "countCountryByNameOrCode" , query ="select count (country) from Country country where country.name=:name or country.code =:code"),
        @NamedQuery(name = "countCountryByNameOrCodeForEdit" , query ="select count (country) from Country country where country.id not in :id and (country.name=:name or country.code =:code)")
})
@UsecaseSupport(subSystem = SubSystem.Common,usecaseType = UsecaseType.Process)
public class Country extends AbstractCountry {
    public Country() {
    }
}

