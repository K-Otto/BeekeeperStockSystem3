package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Beekeeper;
import com.otto.beekeeperstocksystem.Domain.Person;

/**
 * Created by Quam on 4/21/2016.
 */
public class BeekeeperFactory {
    public static Beekeeper create(double salary, Person persons){
        Beekeeper beekeepers = new Beekeeper.Builder‭()
                .salary(salary)
                .persons(persons)
                .build();
        return beekeepers ;
    }
}
