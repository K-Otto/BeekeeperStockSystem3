package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class SubLocation implements Serializable {

    private Long subLocID;

    private String subLocationName;


    private Location locations;


    private SubLocation() {
    }

    public SubLocation(Builder builder) {
        subLocID = builder.subLocID;
        subLocationName = builder.subLocationName;
        locations=builder.locations;
    }

    public static class Builder {
        private Long subLocID;
        private String subLocationName;
        private Location locations;


        public Builder subLocationName(String subLocationName) {
            this.subLocationName = subLocationName;
            return this;
        }

        public Builder subLocID(Long value) {
            this.subLocID = value;
            return this;
        }

        public Builder locations(Location value) {
            this.locations = value;
            return this;
        }

        public Builder copy(SubLocation value) {
            this.subLocID = value.subLocID;
            this.subLocationName = value.subLocationName;
            this.locations = value.locations;
            return this;
        }

        public SubLocation build() {
            return new SubLocation(this);
        }
    }

    public Long getSubLocID() {
        return subLocID;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public Location getLocation() {
        return locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubLocation subLocation = (SubLocation) o;

        return subLocID != null ? subLocID.equals(subLocation.subLocID) : subLocation.subLocID == null;

    }

    @Override
    public int hashCode() {
        return subLocID != null ? subLocID.hashCode() : 0;
    }
}

