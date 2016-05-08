package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Location implements Serializable {

    private Long LocID;

    private String locationName‭;


    private Beekeeper beekeepers;


    private Location() {
    }




    public Location (Builder‭ builder)
    {
        locationName‭=builder.locationName‭;
        LocID=builder.LocID;
        beekeepers=builder.beekeepers;
    }

    public static class Builder‭{
        private Long LocID;
        private String locationName‭;
        private Beekeeper beekeepers;


        public Builder‭ locationName‭(String locationName‭) {
            this.locationName‭= locationName‭;
            return this;
        }

        public Builder‭ LocID(Long value‭){
            this.LocID = value‭;
            return this;
        }

        public Builder‭ beekeepers(Beekeeper value){
            this.beekeepers=value;
            return this;
        }

        public Builder‭ copy(Location value){
            this.LocID=value.LocID;
            this.locationName‭=value.locationName‭;
            this.beekeepers=value.beekeepers;
            return this;
        }
        public Location build(){
            return new Location (this);
        }
    }
    public Long getLocID() {
        return LocID;
    }
    public String getLocationName‭‭() {
        return locationName‭;
    }

    public Beekeeper getBeekeepers(){
        return beekeepers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return LocID != null ? LocID.equals(location.LocID) : location.LocID == null;

    }

    @Override
    public int hashCode() {
        return LocID != null ? LocID.hashCode() : 0;
    }
}

