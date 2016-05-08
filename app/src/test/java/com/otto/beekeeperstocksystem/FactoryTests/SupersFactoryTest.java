package com.otto.beekeeperstocksystem.FactoryTests;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by Quam on 4/21/2016.
 */
public class SupersFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling",beekeeper );


        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Hive hives = HiveFactory.create("sss", subLocations);
        Supers supers = SuperFactory.create("sss", hives);
        Assert.assertEquals(supers.getSuperState(), "sss");
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling", beekeeper);


        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Hive hives = HiveFactory.create("sss", subLocations);


        Supers supers = SuperFactory.create("Active", hives);
        Assert.assertEquals(supers.getSuperState(), "Active");
        Supers newSupers = new Supers
                .Builder()
                .superState("Inactive")
                .hives(hives)
                .build();
        Assert.assertEquals(newSupers.getSuperState(),"Inactive");


    }
}
