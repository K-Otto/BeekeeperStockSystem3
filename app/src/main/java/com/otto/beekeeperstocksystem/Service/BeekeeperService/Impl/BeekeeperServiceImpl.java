package com.otto.beekeeperstocksystem.Service.BeekeeperService.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.otto.beekeeperstocksystem.Conf.Util.App;
import com.otto.beekeeperstocksystem.Domain.Beekeeper;
import com.otto.beekeeperstocksystem.Repositories.BeekeeperRepository;
import com.otto.beekeeperstocksystem.Repositories.Impl.BeekeeperRepositoryImpl;
import com.otto.beekeeperstocksystem.Service.BeekeeperService.BeekeeperService;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public class BeekeeperServiceImpl extends Service implements BeekeeperService{

    final private BeekeeperRepository beekeeperRepository;

    private static BeekeeperServiceImpl service = null;

    public static BeekeeperServiceImpl getInstance() {
        if (service == null)
            service = new BeekeeperServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private BeekeeperRepository repo;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public BeekeeperServiceImpl getService() {
            return BeekeeperServiceImpl.this;
        }
    }

    private BeekeeperServiceImpl()
    {
        beekeeperRepository = new BeekeeperRepositoryImpl(App.getAppContext());
    }

    @Override
    public Beekeeper addBeekeeper(Beekeeper person) {
        return beekeeperRepository.save(person);
    }

    @Override
    public Beekeeper updateBeekeeper(Beekeeper person) {
        return beekeeperRepository.update(person);
    }

    @Override
    public Beekeeper getBeekeeper(Long personID) {
        return beekeeperRepository.findById(personID);
    }

    @Override
    public Set<Beekeeper> getAll() {
        Set<Beekeeper> ram;
        ram = beekeeperRepository.findAll();
        return ram;
    }

    @Override
    public Beekeeper deleteBeekeeper(Beekeeper person) {
        return beekeeperRepository.delete(person);
    }

    @Override
    public int deleteAllBeekeeper() {
        return beekeeperRepository.deleteAll();
    }
}

