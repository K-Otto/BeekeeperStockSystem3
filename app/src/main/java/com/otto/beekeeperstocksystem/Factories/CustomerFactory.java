package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Customer;
import com.otto.beekeeperstocksystem.Domain.Person;

/**
 * Created by Quam on 4/21/2016.
 */
public class CustomerFactory {

    public static Customer create( String address, Person persons){
        Customer customers = new Customer.Builder‭()
                .address(address)
                .persons(persons)
                .build();
        return customers ;
    }
}
