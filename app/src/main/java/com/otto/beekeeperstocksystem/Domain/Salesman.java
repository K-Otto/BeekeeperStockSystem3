package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Salesman {

    private Long salesmanID;

    private double salary;


    private Person persons;


    private Salesman() {
    }


    public Salesman(Builder‭ builder) {
        salary = builder.salary;
        salesmanID = builder.salesmanID;
        persons = builder.persons;
    }

    public static class Builder‭ {
        private Long salesmanID;
        private double salary;
        private Person persons;


        public Builder‭ salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Builder‭ salesmanID(Long value‭) {
            this.salesmanID = value‭;
            return this;
        }

        public Builder‭ persons(Person value) {
            this.persons = value;
            return this;
        }

        public Builder‭ copy(Salesman value) {
            this.salesmanID = value.salesmanID;
            this.salary = value.salary;
            this.persons = value.persons;
            return this;
        }

        public Salesman build() {
            return new Salesman(this);
        }
    }

    public Long getSalesmanID() {
        return salesmanID;
    }

    public double getSalary‭‭() {
        return salary;
    }

    public Person getPersons() {
        return persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salesman salesman = (Salesman) o;

        return salesmanID != null ? salesmanID.equals(salesman.salesmanID) : salesman.salesmanID == null;

    }

    @Override
    public int hashCode() {
        return salesmanID != null ? salesmanID.hashCode() : 0;
    }
}
