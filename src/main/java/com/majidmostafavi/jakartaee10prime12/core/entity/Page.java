package com.majidmostafavi.jakartaee10prime12.core.entity;

import java.util.List;

public class Page<T> {

    private long totalElements;

    private List<T> content;

    private int currentPage;
    private int lastPage;
    private int from;
    private int to;
    private int pageSize;

    public Page(long totalElements, List<T> content, int pageSize, int from) {
        this.totalElements = totalElements;
        this.content = content;
        this.pageSize = pageSize;
        this.from = from +1;
        if (content != null) {
            this.to = content.size() + this.from -1;
        } else {
            this.to = 0;
        }
        if (pageSize!=0) {
            this.currentPage = ((this.to - 1) / this.pageSize) + 1;
        }else {
            this.currentPage = 1;
        }
        if (pageSize != 0) {
            this.lastPage = (int)((this.totalElements -1 ) / this.pageSize) +1;
        } else {
            this.lastPage = 1;
        }
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
