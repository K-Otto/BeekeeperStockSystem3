package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Beekeeper;
import com.otto.beekeeperstocksystem.Domain.Location;

/**
 * Created by Quam on 4/21/2016.
 */
public class LocationFactory {
    public static Location create(String locationName‭, Beekeeper beekeepers1){
        Location locations = new Location.Builder‭()
                .locationName‭(locationName‭)
                .beekeepers(beekeepers1)
                .build();
        return locations ;
    }
}