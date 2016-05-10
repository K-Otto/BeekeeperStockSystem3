package com.otto.beekeeperstocksystem.Service.LocationService;

import com.otto.beekeeperstocksystem.Domain.Location;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public interface LocationService {

    Location addLocation(Location location);

    Location updateLocation(Location location);

    Location getLocation(Long locationID);

    Set<Location> getAll();

    Location deleteLocation(Location location);

    int deleteAllLocation();
}
