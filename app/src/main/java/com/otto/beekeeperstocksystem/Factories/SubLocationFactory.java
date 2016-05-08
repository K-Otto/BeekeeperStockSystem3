package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Location;
import com.otto.beekeeperstocksystem.Domain.SubLocation;

/**
 * Created by Quam on 4/21/2016.
 */
public class SubLocationFactory {
    public static SubLocation create(String subLocationName, Location locations ){
        SubLocation subLocations = new SubLocation.Builder()
                .subLocationName(subLocationName)
                .locations(locations)
                .build();
        return subLocations;
    }
}
