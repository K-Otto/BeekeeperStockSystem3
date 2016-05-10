package com.otto.beekeeperstocksystem.Service.SuperService.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.otto.beekeeperstocksystem.Conf.Util.App;
import com.otto.beekeeperstocksystem.Domain.Supers;
import com.otto.beekeeperstocksystem.Repositories.Impl.SuperRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.SuperRepository;
import com.otto.beekeeperstocksystem.Service.SuperService.SuperService;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public class SuperServiceImpl extends Service implements SuperService {

    final private SuperRepository superRepository;

    private static SuperServiceImpl service = null;

    public static SuperServiceImpl getInstance() {
        if (service == null)
            service = new SuperServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private SuperRepository repo;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public SuperServiceImpl getService() {
            return SuperServiceImpl.this;
        }
    }

    private SuperServiceImpl()
    {
        superRepository = new SuperRepositoryImpl(App.getAppContext());
    }

    @Override
    public Supers addSupers(Supers person) {
        return superRepository.save(person);
    }

    @Override
    public Supers updateSupers(Supers person) {
        return superRepository.update(person);
    }

    @Override
    public Supers getSupers(Long personID) {
        return superRepository.findById(personID);
    }

    @Override
    public Set<Supers> getAll() {
        Set<Supers> ram;
        ram = superRepository.findAll();
        return ram;
    }

    @Override
    public Supers deleteSupers(Supers person) {
        return superRepository.delete(person);
    }

    @Override
    public int deleteAllSupers() {
        return superRepository.deleteAll();
    }


}
