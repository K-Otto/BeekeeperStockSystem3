package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Category;

/**
 * Created by Quam on 4/21/2016.
 */
public class CategoryFactory {
    public static Category create(double stockType){
        Category categories = new Category.Builder()
                .stockType(stockType)
                .build();
        return categories ;
    }
}