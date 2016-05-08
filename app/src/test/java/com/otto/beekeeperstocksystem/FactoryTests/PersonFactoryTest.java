package com.otto.beekeeperstocksystem.FactoryTests;

import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.Factories.PersonFactory;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by Quam on 4/21/2016.
 */
public class PersonFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person role = PersonFactory.create("Karl","Piet", "Karl@gmail.com");
        Assert.assertEquals(role.getFirstName(), "Karl");
    }

    @Test
    public void testUpdate() throws Exception {
        Person role = PersonFactory.create("Karl", "Otto", "Karl@gmail.com");
        Assert.assertEquals("Karl@gmail.com", role.getEmail());
        Person newBeekeeper = new Person
                .Builder()
                .copy(role)
                .email("piet@gmail.com")
                .build();
        Assert.assertEquals("piet@gmail.com", newBeekeeper.getEmail());


    }
}
