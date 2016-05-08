package com.otto.beekeeperstocksystem.FactoryTests;

import org.junit.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class LocationFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location location = LocationFactory.create("Darling", beekeeper);
        Assert.assertEquals(location.getLocationName‭‭(), "Darling");
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);

        Location location = LocationFactory.create("Cape", beekeeper);
        Assert.assertEquals(location.getLocationName‭‭(), "Cape");
        Location newLocation = new Location
                .Builder‭()
                .locationName‭("Darling")
                .beekeepers(beekeeper)
                .build();
        Assert.assertEquals(newLocation.getLocationName‭‭(),"Darling");


    }
}
