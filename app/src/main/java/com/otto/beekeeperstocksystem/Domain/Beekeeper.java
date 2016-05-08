package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Beekeeper implements Serializable {

    private Long beekeeperID;

    private double salary;


    private Person persons;


    private Beekeeper() {
    }




    public Beekeeper (Builder‭ builder)
    {
        salary=builder.salary;
        beekeeperID=builder.beekeeperID;
        persons=builder.persons;
    }

    public static class Builder‭{
        private Long beekeeperID;
        private double salary;
        private Person persons;


        public Builder‭ salary(Double salary) {
            this.salary= salary;
            return this;
        }

        public Builder‭ beekeeperID(Long value‭){
            this.beekeeperID = value‭;
            return this;
        }

        public Builder‭ persons(Person value){
            this.persons=value;
            return this;
        }

        public Builder‭ copy(Beekeeper value){
            this.beekeeperID=value.beekeeperID;
            this.salary=value.salary;
            this.persons=value.persons;
            return this;
        }
        public Beekeeper build(){
            return new Beekeeper (this);
        }
    }
    public Long getBeekeeperID() {
        return beekeeperID;
    }
    public double getSalary‭‭() {
        return salary;
    }

    public Person getPersons(){
        return persons;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beekeeper beekeeper = (Beekeeper) o;

        return beekeeperID != null ? beekeeperID.equals(beekeeper.beekeeperID) : beekeeper.beekeeperID == null;

    }

    @Override
    public int hashCode() {
        return beekeeperID != null ? beekeeperID.hashCode() : 0;
    }
}
