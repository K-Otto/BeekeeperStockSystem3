package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.Domain.Salesman;

/**
 * Created by Quam on 4/21/2016.
 */
public class SalesmanFactory {
    public static Salesman create(double salary, Person persons){
        Salesman salesman = new Salesman.Builder‭()
                .salary(salary)
                .persons(persons)
                .build();
        return salesman ;
    }
}