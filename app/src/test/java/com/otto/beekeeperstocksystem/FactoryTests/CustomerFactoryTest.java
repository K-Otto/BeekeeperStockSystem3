package com.otto.beekeeperstocksystem.FactoryTests;

import org.junit.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class CustomerFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl@gmail.com");
        Customer role = CustomerFactory.create("Milnerton",persons);
        Assert.assertEquals(role.getAddress(), "Milnerton");
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Customer role = CustomerFactory.create("Milnerton", persons);
        Assert.assertEquals(role.getAddress(),"Milnerton");
        Customer newBeekeeper = new Customer
                .Builder‭()
                .address("Melkbos")
                .build();
        Assert.assertEquals(newBeekeeper.getAddress(),"Melkbos");


    }
}
