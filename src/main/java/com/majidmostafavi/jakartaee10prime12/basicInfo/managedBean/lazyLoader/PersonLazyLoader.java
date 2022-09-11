package com.majidmostafavi.jakartaee10prime12.basicInfo.managedBean.lazyLoader;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.DTO.PersonDTO;
import com.majidmostafavi.jakartaee10prime12.basicInfo.enums.GenderEnum;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.PersonService;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;

@Dependent
public class PersonLazyLoader extends LazyDataModel<PersonDTO> {
    @Inject
    PersonService personService;

    private List<PersonDTO> personDTOList;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nationalCode;
    private String personCode;
    private GenderEnum gender;
    private String telephone;
    private String mobile;
    private Boolean active;

    @Override
    public int count(Map<String, FilterMeta> map) {
        return 0;
    }

    @Override
    public List<PersonDTO> load(int first, int pageSize, Map<String, SortMeta> sort, Map<String, FilterMeta> filters) {
//        personDTOList =
       return null ;
    }

    @Override
    public PersonDTO getRowData(String rowKey) {
        for(PersonDTO personnel : personDTOList ) {
            if((personnel.getId().toString()).equals(rowKey))
                return personnel;
        }
        return null;
    }

    @Override
    public String getRowKey(PersonDTO person) {
        if(person.getId()!=null)
            return String.valueOf(person.getId());
        return null;
    }
    //getter && setter


    public PersonService getPersonService() {
        return personService;
    }
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public List<PersonDTO> getPersonDTOList() {
        return personDTOList;
    }
    public void setPersonDTOList(List<PersonDTO> personDTOList) {
        this.personDTOList = personDTOList;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPersonCode() {
        return personCode;
    }
    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public GenderEnum getGender() {
        return gender;
    }
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
}
