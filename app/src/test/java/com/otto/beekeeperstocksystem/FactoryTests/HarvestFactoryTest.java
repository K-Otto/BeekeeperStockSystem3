package com.otto.beekeeperstocksystem.FactoryTests;


import junit.framework.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class HarvestFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling",beekeeper );
        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Harvest hives = HarvestFactory.create("Fossil", 66.00, subLocations);
        Assert.assertEquals(hives.getWeight(), 66.00);
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling", beekeeper);


        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);


        Harvest harvests = HarvestFactory.create("Fossil", 66.00, subLocations);
        Assert.assertEquals(66.00,harvests.getWeight());
        Harvest newHarvest = new Harvest
                .Builder()
                .copy(harvests)
                .totalWeight(55.55)
                .subLocations(subLocations)
                .build();
        Assert.assertEquals(newHarvest.getWeight(),55.55);


    }
}
