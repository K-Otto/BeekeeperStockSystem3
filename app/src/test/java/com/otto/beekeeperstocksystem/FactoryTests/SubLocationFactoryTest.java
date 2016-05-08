package com.otto.beekeeperstocksystem.FactoryTests;

import org.junit.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class SubLocationFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling",beekeeper );
        SubLocation location = SubLocationFactory.create("Olives", locations);
        Assert.assertEquals(location.getSubLocationName(), "Olives");
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling", beekeeper);


        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Assert.assertEquals(subLocations.getSubLocationName(), "Fossil");
        SubLocation newSubLocation = new SubLocation
                .Builder()
                .subLocationName("Water")
                .locations(locations)
                .build();
        Assert.assertEquals(newSubLocation.getSubLocationName(),"Water");


    }
}

