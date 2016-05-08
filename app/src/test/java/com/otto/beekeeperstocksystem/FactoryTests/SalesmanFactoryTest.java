package com.otto.beekeeperstocksystem.FactoryTests;

import junit.framework.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class SalesmanFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Salesman beekeeper = SalesmanFactory.create(10.00, persons);
        Assert.assertEquals(beekeeper.getSalary‭‭(), 10.00);
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");


        Salesman salesman = SalesmanFactory.create(10.00, persons);
        Assert.assertEquals(salesman.getSalary‭‭(), 10.00);
        Salesman newSalesman = new Salesman
                .Builder‭()
                .salary(12.00)
                .persons(persons)
                .build();
        Assert.assertEquals(newSalesman.getSalary‭‭(),12.00);


    }
}
