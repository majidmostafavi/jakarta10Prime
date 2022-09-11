package com.majidmostafavi.jakartaee10prime12.basicInfo.service;

import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.PersonDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.DTO.PersonDTO;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Person;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Supplier;
import com.majidmostafavi.jakartaee10prime12.basicInfo.enums.GenderEnum;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.inject.Inject;

import java.util.List;

public class PersonService extends AbstractService {

    @Inject
    PersonDao personDao;


    public void create(Person person) throws DuplicateDataException, RelationDataException, SaveRecordException {
        if (canSave(person)) {
            personDao.create(person);
        }
    }

    public void edit(Person person) throws DuplicateDataException, RelationDataException,SaveRecordException {
        if (canEdit(person))
            personDao.edit(person);
    }

    public void delete (Person person) throws RelationDataException{
        if (canDelete(person))
            personDao.delete(person);
    }


    public boolean canSave (Person person) throws DuplicateDataException{
        return true;
    }

    public boolean canEdit(Person person) throws DuplicateDataException,RelationDataException,SaveRecordException{
        return true;
    }

    public boolean canDelete (Person person) throws RelationDataException{
        return true;
    }


    public Person findPersonById(Long id) {
        return personDao.findPersonById(id);
    }

//    public List<PersonDTO> lazySearch(String firstName,
//                                      String lastName,
//                                      String fatherName,
//                                      String nationalCode,
//                                      String personCode,
//                                      GenderEnum gender,
//                                      String mobile,
//                                      Boolean active,
//                                      int first, int pageSize) {
//        return personDao.search(firstName, lastName, fatherName, nationalCode, personCode, gender, mobile, active, first, pageSize);
//    }
}
