package com.otto.beekeeperstocksystem.Domain;

import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Supers implements Serializable {

    private Long superID;

    private String superState;


    private Hive hives;


    private Supers() {
    }

    public Supers(Builder builder) {
        superID = builder.superID;
        superState = builder.superState;
        hives=builder.hives;
    }

    public static class Builder {
        private Long superID;
        private String superState;
        private Hive hives;


        public Builder superState(String superState) {
            this.superState = superState;
            return this;
        }

        public Builder superID(Long value) {
            this.superID = value;
            return this;
        }

        public Builder hives(Hive value) {
            this.hives = value;
            return this;
        }

        public Builder copy(Supers value) {
            this.superID = value.superID;
            this.superState = value.superState;
            this.hives = value.hives;
            return this;
        }

        public Supers build() {
            return new Supers(this);
        }
    }

    public Long getSuperID() {
        return superID;
    }

    public String getSuperState() {
        return superState;
    }

    public Hive getHives() {
        return hives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supers supers = (Supers) o;

        return superID != null ? superID.equals(supers.superID) : supers.superID == null;

    }

    @Override
    public int hashCode() {
        return superID != null ? superID.hashCode() : 0;
    }
}

