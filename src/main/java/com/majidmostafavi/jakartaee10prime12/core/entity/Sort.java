package com.majidmostafavi.jakartaee10prime12.core.entity;


import com.majidmostafavi.jakartaee10prime12.core.enumration.OrderType;

public class Sort implements Comparable<Sort>{
    private String name;
    private OrderType orderType;
    private int priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Sort sort) {
        return Integer.compare(this.priority, sort.priority);
    }
}
