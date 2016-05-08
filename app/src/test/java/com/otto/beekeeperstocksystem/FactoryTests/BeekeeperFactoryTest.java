package com.otto.beekeeperstocksystem.FactoryTests;

import junit.framework.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class BeekeeperFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Assert.assertEquals(beekeeper.getSalary‭‭(), 10.00);
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");


        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Assert.assertEquals(beekeeper.getSalary‭‭(), 10.00);
        Beekeeper newBeekeeper = new Beekeeper
                .Builder‭()
                .salary(11.00)
                .persons(persons)
                .build();
        Assert.assertEquals(newBeekeeper.getSalary‭‭(),11.00);


    }
}

