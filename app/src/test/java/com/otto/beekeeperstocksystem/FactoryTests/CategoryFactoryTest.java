package com.otto.beekeeperstocksystem.FactoryTests;

import junit.framework.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class CategoryFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Category category = CategoryFactory.create(11.00);
        Assert.assertEquals(category.getstockType(), 11.00);
    }

    @Test
    public void testUpdate() throws Exception {
        Category category = CategoryFactory.create(11.00);
        Assert.assertEquals(category.getstockType(),11.00);
        Category newCategory = new Category
                .Builder()
                .stockType(12.99)
                .build();
        Assert.assertEquals(newCategory.getstockType(),12.99);


    }
}
