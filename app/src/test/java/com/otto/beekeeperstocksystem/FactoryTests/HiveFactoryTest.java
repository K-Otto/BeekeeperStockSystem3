package com.otto.beekeeperstocksystem.FactoryTests;

import org.junit.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class HiveFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling",beekeeper );


        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Hive hives = HiveFactory.create("sss", subLocations);
        Assert.assertEquals(hives.getHiveState(), "sss");
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling", beekeeper);


        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);


        Hive hives = HiveFactory.create("Fossil", subLocations);
        Assert.assertEquals(hives.getHiveState(), "Fossil");
        Hive newHives = new Hive
                .Builder()
                .hiveState("Water")
                .subLocations(subLocations)
                .build();
        Assert.assertEquals(newHives.getHiveState(),"Water");


    }
}
