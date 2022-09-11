package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.annotation.UsecaseSupport;
import com.majidmostafavi.jakartaee10prime12.core.enumration.SubSystem;
import com.majidmostafavi.jakartaee10prime12.core.enumration.UsecaseType;
import jakarta.persistence.*;

@Entity
@Cacheable

@Table(name = "CITY")
@NamedQueries({
        @NamedQuery(name = "findAllCities" , query = "select city from City city where city.active=true "),
        @NamedQuery(name = "countCityByNameOrCode" , query = "select count (city) from City city where city.name=:name or city.code=:code"),
        @NamedQuery(name = "countPersonnelInCity", query = "select count(city) from City city where city.id =:id"),
        @NamedQuery(name = "countProvinceInCity" , query ="select count(city) from City city where city.province=:province"),
        @NamedQuery(name = "countCityByNameOrCodeForEdit" , query = "select count (city) from City city where city.id not in :id and (city.name=:name or city.code=:code)")
})

@UsecaseSupport(subSystem = SubSystem.Common,usecaseType = UsecaseType.Process)
public class City extends AbstractCity {
    public City(){
    }
}

