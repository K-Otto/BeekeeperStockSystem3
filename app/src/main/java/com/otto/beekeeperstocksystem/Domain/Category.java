package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Category {

    private Long categoryID;

    private double stockType;



    private Category() {
    }

    public Long getCategoryId() {
        return categoryID;
    }
    public double getstockType() {
        return stockType;
    }


    public Category(Builder builder){
        categoryID=builder.categoryID;
        stockType=builder.stockType;


    }

    public static class Builder{
        private double stockType;

        private Long categoryID;
        public Builder categoryID(Long categoryID) {
            this.categoryID = categoryID;
            return this;
        }

        public Builder stockType(double stockType) {
            this.stockType = stockType;
            return this;
        }


        public Builder copy(Category value){
            this.categoryID = value.categoryID;
            this.stockType =value.stockType ;

            return this;
        }

        public Category build(){
            return new Category(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return categoryID != null ? categoryID.equals(category.categoryID) : category.categoryID == null;

    }

    @Override
    public int hashCode() {
        return categoryID != null ? categoryID.hashCode() : 0;
    }
}

