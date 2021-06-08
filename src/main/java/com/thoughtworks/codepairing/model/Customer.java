package com.thoughtworks.codepairing.model;

import java.util.Objects;

public class Customer {
    private String name;
    private Order order;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}