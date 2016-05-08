package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Order {

    private Long orderID;
    private String orderDate;




    private Customer customers;


    private Salesman salesman;

    private Order() {
    }

    public Order(Builder builder) {
        orderID = builder.orderID;
        orderDate = builder.orderDate;
        customers = builder.customers;
        salesman = builder.salesman;

    }

    public static class Builder {
        private Long orderID;
        private String orderDate;
        private Double price;
        private Customer customers;
        private Salesman salesman;



        public Builder orderDate(String orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder orderID(Long value) {
            this.orderID = value;
            return this;
        }



        public Builder customers(Customer value) {
            this.customers = value;
            return this;
        }

        public Builder salesman(Salesman value) {
            this.salesman = value;
            return this;
        }



        public Builder copy(Order value) {
            this.orderID = value.orderID;
            this.orderDate = value.orderDate;


            this.customers = value.customers;
            this.salesman = value.salesman;

            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public Long getOrderID() {
        return orderID;
    }

    public String getSalesDate() {
        return orderDate;
    }


    public Customer getCustomers() {
        return customers;
    }

    public Salesman getSalesman() {
        return salesman;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return orderID != null ? orderID.equals(order.orderID) : order.orderID == null;

    }

    @Override
    public int hashCode() {
        return orderID != null ? orderID.hashCode() : 0;
    }
}