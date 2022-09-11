package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class AbstractYear extends AbstractEntity implements Comparable<AbstractYear> {
    private Long id;
    private String code;
    private String name;
    private Date startDate;
    private Date endDate;

    public AbstractYear() {
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

    @Column(name = "CODE",unique = true,nullable = false)
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "NAME",unique = true,nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "STARTDATE",unique = true,nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Column(name = "ENDDATE",unique = true,nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Transient
    public String getPathId(){
        return "#" + getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractYear)) return false;

        AbstractYear that = (AbstractYear) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (getCode() != null && getName() != null)
            return "(" + getCode() + ") "+ getName();
        else if (getCode() != null)
            return "(" + getCode() + ")";
        else if (getName() != null)
            return getName();
        else return "";
    }

    @Override
    public int compareTo(AbstractYear fiscalyear) {
        if (this.getId()!=null) {
            return this.getCode().compareTo(fiscalyear.getCode());
        }else {
            return 0 ;
        }
    }
}
