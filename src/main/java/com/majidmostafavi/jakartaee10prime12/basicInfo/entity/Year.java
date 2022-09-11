package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import jakarta.persistence.*;

@Entity
@Cacheable
@Table(name = "YEAR")
@NamedQueries({
//        @NamedQuery(name = "findYearByDate", query = "select year from Year year where year.startDate<=:intendedDate and year.endDate>=:intendedDate and year.active=true order by year.code asc "),
        @NamedQuery(name = "countYearByDate", query = "select count(year) from Year year where year.startDate<=:intendedDate and year.endDate>=:intendedDate and  year.active=true "),
//        @NamedQuery(name="findFirstYear", query = "select year from Year year where " +
//                "year.startDate = (SELECT MAX(year.startDate) FROM Year year WHERE year.active=:true) order by year.code"),
        @NamedQuery(name = "countYearByNameOrCode", query = "select count(year) from Year year where year.name=:name or year.code=:code "),
        @NamedQuery(name = "findAllSortByYear",query = "select year from Year year order by year.startDate desc "),
//        @NamedQuery(name = "findLastYear",query = "select year from Year year order by year.endDate desc "),
//        @NamedQuery(name = "countTotalYear",query = "select count(year) from Year year"),
//        @NamedQuery(name="findNextYears",query="select year from Year year where year.startDate>=:intendedDate or year.id=:id and year.active=true order by year.code"),
//        @NamedQuery(name = "findFirstDateOfFirstYear",query = "select min(year.startDate) from Year year order by year.startDate asc")
        @NamedQuery(name = "findYearById", query = "select year from Year year where year.id=:id")

})
public class Year extends AbstractYear {
    public Year() {
    }
}