package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Customer  implements Serializable {

    private Long customerID;

    private String address;


    private Person persons;


    private Customer() {
    }


    public Customer(Builder‭ builder) {
        address = builder.address;
        customerID = builder.customerID;
        persons = builder.persons;
    }

    public static class Builder‭ {
        private Long customerID;
        private String address;
        private Person persons;


        public Builder‭ address(String address) {
            this.address = address;
            return this;
        }

        public Builder‭ customerID(Long value‭) {
            this.customerID = value‭;
            return this;
        }

        public Builder‭ persons(Person value) {
            this.persons = value;
            return this;
        }

        public Builder‭ copy(Customer value) {
            this.customerID = value.customerID;
            this.address = value.address;
            this.persons = value.persons;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getAddress() {
        return address;
    }

    public Person getPersons() {
        return persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return customerID != null ? customerID.equals(customer.customerID) : customer.customerID == null;

    }

    @Override
    public int hashCode() {
        return customerID != null ? customerID.hashCode() : 0;
    }
}

