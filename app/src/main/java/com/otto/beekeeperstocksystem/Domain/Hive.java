package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Hive implements Serializable {

    private Long hiveID;

    private String hiveState ;


    private SubLocation subLocations;



    private  Hive  () {
    }

    public Hive(Builder builder) {
        hiveID=builder.hiveID;
        hiveState=builder.hiveState ;
        subLocations=builder.subLocations;
    }
    public static class Builder{
        private Long hiveID;
        private String hiveState ;
        private SubLocation subLocations;


        public Builder hiveState(String hiveState ) {
            this.hiveState = hiveState ;
            return this;
        }

        public Builder hiveID(Long value){
            this.hiveID = value;
            return this;
        }

        public Builder subLocations(SubLocation value){
            this.subLocations=value;
            return this;
        }

        public Builder copy(Hive  value){
            this.hiveID = value.hiveID;
            this.hiveState =value.hiveState ;
            this.subLocations=value.subLocations;
            return this;
        }
        public Hive  build(){
            return new Hive  (this);
        }
    }
    public Long getHiveID() {
        return hiveID;
    }

    public String getHiveState() {
        return hiveState;
    }
    public SubLocation getSubLocation() {
        return subLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hive hive = (Hive) o;

        return hiveID != null ? hiveID.equals(hive.hiveID) : hive.hiveID == null;

    }

    @Override
    public int hashCode() {
        return hiveID != null ? hiveID.hashCode() : 0;
    }
}

