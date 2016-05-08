package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Hive;
import com.otto.beekeeperstocksystem.Domain.SubLocation;

/**
 * Created by Quam on 4/21/2016.
 */
public class HiveFactory {
    public static Hive create(String state, SubLocation subLocations ){
        Hive hives = new Hive.Builder()
                .hiveState(state)
                .subLocations (subLocations )
                .build();
        return hives ;
    }
}
