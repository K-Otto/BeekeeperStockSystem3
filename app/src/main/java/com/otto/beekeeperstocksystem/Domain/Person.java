package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;

/**
 * Created by Quam on 4/21/2016.
 */

public class Person implements Serializable {

    private Long personID;

    private String firstName;
    private String lastName;
    private String email;


    private Person() {
    }

    public Long getPersonId() {
        return personID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    public Person(Builder builder){
        personID=builder.personID;
        firstName=builder.firstName;
        lastName=builder.lastName;
        email=builder.email;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private String email;
        private Long personID;

        public Builder personID(Long personID) {
            this.personID = personID;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String value){
            this.firstName=value;
            return this;
        }

        public Builder email(String value){
            this.email=value;
            return this;

        }
        public Builder copy(Person value){
            this.personID = value.personID;
            this.lastName =value.lastName ;
            this.firstName=value.firstName;
            this.email=value.email;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return personID != null ? personID.equals(person.personID) : person.personID == null;

    }

    @Override
    public int hashCode() {
        return personID != null ? personID.hashCode() : 0;
    }
}



