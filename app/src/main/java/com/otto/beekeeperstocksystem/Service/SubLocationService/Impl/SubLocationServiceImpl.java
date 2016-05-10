package com.otto.beekeeperstocksystem.Service.SubLocationService.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.otto.beekeeperstocksystem.Conf.Util.App;
import com.otto.beekeeperstocksystem.Domain.SubLocation;
import com.otto.beekeeperstocksystem.Repositories.Impl.SubLocationRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.SubLocationRepository;
import com.otto.beekeeperstocksystem.Service.SubLocationService.SubLocationService;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public class SubLocationServiceImpl extends Service implements SubLocationService {

    final private SubLocationRepository subLocationRepository;

    private static SubLocationServiceImpl service = null;

    public static SubLocationServiceImpl getInstance() {
        if (service == null)
            service = new SubLocationServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private SubLocationRepository repo;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public SubLocationServiceImpl getService() {
            return SubLocationServiceImpl.this;
        }
    }

    private SubLocationServiceImpl()
    {
        subLocationRepository = new SubLocationRepositoryImpl(App.getAppContext());
    }

    @Override
    public SubLocation addSubLocation(SubLocation person) {
        return subLocationRepository.save(person);
    }

    @Override
    public SubLocation updateSubLocation(SubLocation person) {
        return subLocationRepository.update(person);
    }

    @Override
    public SubLocation getSubLocation(Long personID) {
        return subLocationRepository.findById(personID);
    }

    @Override
    public Set<SubLocation> getAll() {
        Set<SubLocation> ram;
        ram = subLocationRepository.findAll();
        return ram;
    }

    @Override
    public SubLocation deleteSubLocation(SubLocation person) {
        return subLocationRepository.delete(person);
    }

    @Override
    public int deleteAllSubLocation() {
        return subLocationRepository.deleteAll();
    }


}
