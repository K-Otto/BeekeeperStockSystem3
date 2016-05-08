package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Order;
import com.otto.beekeeperstocksystem.Domain.Orderline;
import com.otto.beekeeperstocksystem.Domain.Product;

/**
 * Created by Quam on 4/21/2016.
 */
public class OrderlineFactory {
    public static Orderline create(double unitPrice, double quantity, Product product, Order order){
        Orderline sales= new Orderline.Builder()
                .quantity(quantity)
                .unitPrice(unitPrice)
                .order(order)
                .product(product)
                .build();
        return sales;
    }
}
