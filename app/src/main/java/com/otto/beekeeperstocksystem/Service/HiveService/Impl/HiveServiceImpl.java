package com.otto.beekeeperstocksystem.Service.HiveService.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.otto.beekeeperstocksystem.Conf.Util.App;
import com.otto.beekeeperstocksystem.Domain.Hive;
import com.otto.beekeeperstocksystem.Repositories.HiveRepository;
import com.otto.beekeeperstocksystem.Repositories.Impl.HiveRepositoryImpl;
import com.otto.beekeeperstocksystem.Service.HiveService.HiveService;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public class HiveServiceImpl extends Service implements HiveService {

    final private HiveRepository hiveRepository;

    private static HiveServiceImpl service = null;

    public static HiveServiceImpl getInstance() {
        if (service == null)
            service = new HiveServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private HiveRepository repo;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public HiveServiceImpl getService() {
            return HiveServiceImpl.this;
        }
    }

    private HiveServiceImpl()
    {
        hiveRepository = new HiveRepositoryImpl(App.getAppContext());
    }

    @Override
    public Hive addHive(Hive person) {
        return hiveRepository.save(person);
    }

    @Override
    public Hive updateHive(Hive person) {
        return hiveRepository.update(person);
    }

    @Override
    public Hive getHive(Long personID) {
        return hiveRepository.findById(personID);
    }

    @Override
    public Set<Hive> getAll() {
        Set<Hive> ram;
        ram = hiveRepository.findAll();
        return ram;
    }

    @Override
    public Hive deleteHive(Hive person) {
        return hiveRepository.delete(person);
    }

    @Override
    public int deleteAllHive() {
        return hiveRepository.deleteAll();
    }


}
