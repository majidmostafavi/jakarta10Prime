package com.majidmostafavi.jakartaee10prime12.core.entity;

import jakarta.persistence.Transient;
import jakarta.ws.rs.DefaultValue;

import java.util.List;

public class PageRequest {
    @DefaultValue("0")
    private int pageNum;

    @DefaultValue("10")
    private int pageSize;

    private List<Sort> sortList;
    private List<SearchBase> searchList;

    @Transient
    private int firstResult;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstResult() {
        if (pageNum!=0) {
            return (pageSize * pageNum) - pageSize;
        }else {
            return 0;
        }
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public List<Sort> getSortList() {
        return sortList;
    }

    public void setSortList(List<Sort> sortList) {
        this.sortList = sortList;
    }

    public List<SearchBase> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchBase> searchList) {
        this.searchList = searchList;
    }
}
