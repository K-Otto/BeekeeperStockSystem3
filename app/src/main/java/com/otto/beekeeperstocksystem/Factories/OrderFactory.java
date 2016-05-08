package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Customer;
import com.otto.beekeeperstocksystem.Domain.Order;
import com.otto.beekeeperstocksystem.Domain.Salesman;

/**
 * Created by Quam on 4/21/2016.
 */
public class OrderFactory {
    public static Order create(String salesDate, Customer customers, Salesman salesman){
        Order order= new Order.Builder()
                .orderDate(salesDate)
                .customers(customers)
                .salesman(salesman)
                .build();
        return order;
    }
}