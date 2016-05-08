package com.otto.beekeeperstocksystem.FactoryTests;


import junit.framework.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class ProductFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling",beekeeper );
        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Harvest harvests = HarvestFactory.create("Fossil", 66.00, subLocations);

        Product hives = ProductFactory.create(66.00, harvests);
        Assert.assertEquals(66.00, hives.getTotalStock());
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling", beekeeper);


        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);


        Harvest harvests = HarvestFactory.create("Fossil", 66.00, subLocations);


        Product buckets = ProductFactory.create(66.00, harvests);
        Assert.assertEquals(buckets.getTotalStock(), 66.00);

        Product newProduct = new Product
                .Builder()
                .totalStock(55.55)
                .harvests(harvests)
                .build();
        Assert.assertEquals(newProduct.getTotalStock(), 55.55);


    }
}
