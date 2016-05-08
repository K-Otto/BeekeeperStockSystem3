package com.otto.beekeeperstocksystem.Domain;


import java.io.Serializable;
/**
 * Created by Quam on 4/21/2016.
 */

public class Orderline {

    private Long orderlineID;
    private double quantity;
    private double unitPrice;


    private Product product;


    private Order order;


    private Orderline() {
    }

    public Orderline(Builder builder) {
        orderlineID = builder.orderlineID;
        unitPrice=builder.unitPrice;
        quantity = builder.quantity;
        order = builder.order;
        product=builder.product;
    }

    public static class Builder {
        private Long orderlineID;
        private double quantity;
        private double unitPrice;
        private Product product;
        private Order order;


        public Builder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder orderlineID(Long value) {
            this.orderlineID = value;
            return this;
        }

        public Builder unitPrice(double value) {
            this.unitPrice = value;
            return this;
        }

        public Builder order(Order value) {
            this.order = value;
            return this;
        }

        public Builder product(Product value) {
            this.product = value;
            return this;
        }

        public Builder copy(Orderline value) {
            this.orderlineID = value.orderlineID;
            this.quantity = value.quantity;
            this.unitPrice = value.unitPrice;

            this.order = value.order;
            this.product = value.product;
            return this;
        }

        public Orderline build() {
            return new Orderline(this);
        }
    }

    public Long getOrderlineID() {
        return orderlineID;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderline orderline = (Orderline) o;

        return orderlineID != null ? orderlineID.equals(orderline.orderlineID) : orderline.orderlineID == null;

    }

    @Override
    public int hashCode() {
        return orderlineID != null ? orderlineID.hashCode() : 0;
    }
}

