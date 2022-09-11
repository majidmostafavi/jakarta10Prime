package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.annotation.UsecaseSupport;
import com.majidmostafavi.jakartaee10prime12.core.enumration.SubSystem;
import com.majidmostafavi.jakartaee10prime12.core.enumration.UsecaseType;
import jakarta.persistence.*;

@Entity

@Table(name = "PROVINCE")
@NamedQueries({
        @NamedQuery(name = "findAllProvinces" , query = "select province from Province province where province.active=true "),
        @NamedQuery(name = "countProvinceByNameOrCode" , query ="select count(province) from Province province where province.name=:name or province.code=:code"),
        @NamedQuery(name = "countCountriesInProvinces" , query ="select count(province) from Province province where province.country=:country"),
        @NamedQuery(name = "countProvinceByNameOrCodeForEdit" , query ="select count(province) from Province province where province.id not in :id and (province.name=:name or province.code=:code)")
})

@UsecaseSupport(subSystem = SubSystem.Common,usecaseType = UsecaseType.Process)
public class Province extends AbstractProvince {
    public Province() {
    }
}