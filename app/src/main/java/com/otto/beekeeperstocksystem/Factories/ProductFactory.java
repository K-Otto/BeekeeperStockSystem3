package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Harvest;
import com.otto.beekeeperstocksystem.Domain.Product;

/**
 * Created by Quam on 4/21/2016.
 */
public class ProductFactory {
    public static Product create(Double totalStock, Harvest harvests){
        Product buckets= new Product.Builder()
                .totalStock(totalStock)
                .harvests(harvests)
                .build();
        return buckets;
    }
}
