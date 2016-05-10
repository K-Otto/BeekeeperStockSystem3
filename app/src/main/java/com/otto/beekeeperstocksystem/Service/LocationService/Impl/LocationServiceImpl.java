package com.otto.beekeeperstocksystem.Service.LocationService.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.otto.beekeeperstocksystem.Conf.Util.App;
import com.otto.beekeeperstocksystem.Domain.Location;
import com.otto.beekeeperstocksystem.Repositories.Impl.LocationRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.LocationRepository;
import com.otto.beekeeperstocksystem.Service.LocationService.LocationService;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public class LocationServiceImpl extends Service implements LocationService {

    final private LocationRepository locationRepository;

    private static LocationServiceImpl service = null;

    public static LocationServiceImpl getInstance() {
        if (service == null)
            service = new LocationServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private LocationRepository repo;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public LocationServiceImpl getService() {
            return LocationServiceImpl.this;
        }
    }

    private LocationServiceImpl()
    {
        locationRepository = new LocationRepositoryImpl(App.getAppContext());
    }

    @Override
    public Location addLocation(Location person) {
        return locationRepository.save(person);
    }

    @Override
    public Location updateLocation(Location person) {
        return locationRepository.update(person);
    }

    @Override
    public Location getLocation(Long personID) {
        return locationRepository.findById(personID);
    }

    @Override
    public Set<Location> getAll() {
        Set<Location> ram;
        ram = locationRepository.findAll();
        return ram;
    }

    @Override
    public Location deleteLocation(Location person) {
        return locationRepository.delete(person);
    }

    @Override
    public int deleteAllLocation() {
        return locationRepository.deleteAll();
    }


}
