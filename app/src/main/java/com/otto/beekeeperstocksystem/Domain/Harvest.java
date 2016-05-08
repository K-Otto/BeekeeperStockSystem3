package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Harvest implements Serializable {


    private Long harvestID;

    private String harvestDate ;
    private Double totalWeight;
    private Double totalWeightRemaining;


    private SubLocation  subLocations;




    private  Harvest  () {
    }

    public Harvest(Builder builder) {
        harvestID=builder.harvestID;
        harvestDate=builder.harvestDate  ;
        totalWeight=builder.totalWeight;
        totalWeightRemaining=builder.totalWeightRemaining;
        subLocations=builder.subLocations;

    }
    public static class Builder{
        private Long harvestID;
        private String harvestDate  ;
        private Double totalWeight;
        private Double totalWeightRemaining;
        private SubLocation  subLocations;


        public Builder harvestDate(String harvestDate  ) {
            this.harvestDate  = harvestDate  ;
            return this;
        }

        public Builder harvestID(Long value){
            this.harvestID = value;
            return this;
        }
        public Builder totalWeight(Double value){
            this.totalWeight = value;
            return this;
        }
        public Builder totalWeightRemaining(Double value){
            this.totalWeightRemaining = value;
            return this;
        }


        public Builder subLocations(SubLocation value){
            this.subLocations=value;
            return this;
        }


        public Builder copy(Harvest  value){
            this.harvestID = value.harvestID;
            this.harvestDate  =value.harvestDate  ;
            this.totalWeight  =value.totalWeight  ;
            this.totalWeightRemaining  =value.totalWeightRemaining  ;
            this.subLocations=value.subLocations;

            return this;
        }
        public Harvest  build(){
            return new Harvest  (this);
        }
    }
    public Long getHarvestID() {
        return harvestID;
    }

    public String getHarvestDate () {
        return harvestDate;
    }

    public Double getWeight() {
        return totalWeight;
    }
    public Double gettotalWeightRemaining() {
        return totalWeightRemaining;
    }

    public SubLocation getSubLocation() {
        return subLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Harvest harvest = (Harvest) o;

        return harvestID != null ? harvestID.equals(harvest.harvestID) : harvest.harvestID == null;

    }

    @Override
    public int hashCode() {
        return harvestID != null ? harvestID.hashCode() : 0;
    }
}

