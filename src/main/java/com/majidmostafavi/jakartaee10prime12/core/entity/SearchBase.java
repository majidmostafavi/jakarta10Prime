package com.majidmostafavi.jakartaee10prime12.core.entity;

import com.majidmostafavi.jakartaee10prime12.core.enumration.SearchType;

public class SearchBase<T> {
    private String fieldName;
    private SearchType searchType;
    private T value;

    public SearchBase() {
    }

    public String getSearchStatement(){
        switch (getSearchType()) {
            case equal:{
                return getValue().toString();
            }
            case contain:{
                return "%"+getValue()+"%";
            }

            case startWith:{
                return getValue()+"%";
            }
            default:
                return "";
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public SearchType getSearchType() {
        if (searchType!=null) {
            return searchType;
        }else {
            return SearchType.equal;
        }
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
