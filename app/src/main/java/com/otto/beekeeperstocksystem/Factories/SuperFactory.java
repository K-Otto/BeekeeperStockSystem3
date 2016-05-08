package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Hive;
import com.otto.beekeeperstocksystem.Domain.Supers;

/**
 * Created by Quam on 4/21/2016.
 */
public class SuperFactory {
    public static Supers create(String state, Hive hives){
        Supers supers= new Supers.Builder()
                .superState(state)
                .hives(hives)
                .build();
        return supers;
    }
}
