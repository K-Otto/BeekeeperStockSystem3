package com.otto.beekeeperstocksystem.FactoryTests;

import junit.framework.Assert;
import org.junit.Test;

import com.otto.beekeeperstocksystem.Domain.*;
import com.otto.beekeeperstocksystem.Factories.*;
/**
 * Created by Quam on 4/21/2016.
 */
public class OrderlineFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling",beekeeper );
        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Harvest harvests = HarvestFactory.create("Fossil", 66.00, subLocations);
        Customer customers = CustomerFactory.create("", persons);
        Product product = ProductFactory.create(66.00, harvests);
        Salesman salesman = SalesmanFactory.create(10.00, persons);
        Order order = OrderFactory.create("March", customers, salesman);
        Orderline orderline = OrderlineFactory.create(10.00, 22.22, product, order);
        Assert.assertEquals(orderline.getQuantity(), 22.22);
    }

    @Test
    public void testUpdate() throws Exception {
        Person persons = PersonFactory.create("karl", "otto", "karl1256@yahoo.com");
        Beekeeper beekeeper = BeekeeperFactory.create(10.00, persons);
        Location locations = LocationFactory.create("Darling",beekeeper );
        SubLocation subLocations = SubLocationFactory.create("Fossil", locations);
        Harvest harvests = HarvestFactory.create("Fossil", 66.00, subLocations);
        Product product = ProductFactory.create(66.00, harvests);
        Customer customers = CustomerFactory.create("", persons);
        Salesman salesman = SalesmanFactory.create(10.00, persons);
        Order order = OrderFactory.create("March", customers, salesman);

        Orderline orderline = OrderlineFactory.create(10.00, 22.00, product, order);
        Assert.assertEquals(orderline.getUnitPrice(), 10.00);
        Orderline newOrderline = new Orderline
                .Builder()
                .copy(orderline)
                .unitPrice(26.00)
                .product(product)
                .order(order)
                .build();
        Assert.assertEquals(newOrderline.getUnitPrice(),26.00);


    }
}
