package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Product implements Serializable {

    private Long productID;

    private double totalStock;
    private double totalStockRemaining;


    private Harvest harvests;

    private Category  categories;


    private Product() {
    }

    public Product(Builder builder) {
        productID = builder.productID;
        totalStock = builder.totalStock;
        totalStockRemaining = builder.totalStockRemaining;
        categories=builder.categories;

    }

    public static class Builder {
        private Long productID;
        private double totalStock;
        private double totalStockRemaining;
        private Harvest harvests;
        private Category  categories;


        public Builder totalStock(double totalStock) {
            this.totalStock = totalStock;
            return this;
        }


        public Builder totalStockRemaining(double value) {
            this.totalStockRemaining = value;
            return this;
        }
        public Builder productID(Long value) {
            this.productID = value;
            return this;
        }

        public Builder harvests(Harvest value) {
            this.harvests = value;
            return this;
        }
        public Builder categories(Category value){
            this.categories=value;
            return this;
        }

        public Builder copy(Product value) {
            this.productID = value.productID;
            this.totalStock = value.totalStock;
            this.totalStockRemaining = value.totalStockRemaining;
            this.harvests = value.harvests;
            this.categories=value.categories;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public Long getProductID() {
        return productID;
    }

    public Double getTotalStock() {
        return totalStock;
    }

    public Double getTotalStockRemaining() {
        return totalStockRemaining;
    }

    public Harvest getHarvests() {
        return harvests;
    }
    public Category getCategory() {
        return categories;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productID != null ? productID.equals(product.productID) : product.productID == null;

    }

    @Override
    public int hashCode() {
        return productID != null ? productID.hashCode() : 0;
    }
}
