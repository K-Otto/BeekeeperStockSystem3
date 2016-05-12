package com.otto.beekeeperstocksystem.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import junit.framework.Assert;
import java.util.Set;

import com.otto.beekeeperstocksystem.Domain.Beekeeper;
import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.Factories.BeekeeperFactory;
import com.otto.beekeeperstocksystem.Factories.PersonFactory;
import com.otto.beekeeperstocksystem.Service.BeekeeperService.Impl.BeekeeperServiceImpl;

/**
 * Created by 212026992 on 5/12/2016.
 */
public class BeekeeperServiceTest extends AndroidTestCase {
    private BeekeeperServiceImpl beekeeperService;
    private boolean isBound;
    Beekeeper beekeeper;
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), BeekeeperServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        Person person = PersonFactory.create("","","");
        Beekeeper role = BeekeeperFactory.create( 11.00, person);
        beekeeper = new Beekeeper
                .Builder‭()
                .copy(role)
                .salary(12.00)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BeekeeperServiceImpl.ActivateServiceLocalBinder binder
                    = (BeekeeperServiceImpl.ActivateServiceLocalBinder) service;
            beekeeperService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        Beekeeper insertedPerson = beekeeperService.addBeekeeper(beekeeper);
        id = insertedPerson.getBeekeeperID();
        Assert.assertNotNull(insertedPerson);

        Set<Beekeeper> allRam = beekeeperService.getAll();
        Assert.assertTrue(allRam.size()>0);

        //READ ENTITY
        Beekeeper entity = beekeeperService.getBeekeeper(id);
        Assert.assertNotNull(entity);

        //UPDATE ENTITY
        Beekeeper updateEntity = new Beekeeper
                .Builder‭()
                .copy(entity)
                .salary(33.00)
                .build();
        beekeeperService.updateBeekeeper(updateEntity);
        Beekeeper newEntity = beekeeperService.getBeekeeper(id);
        Assert.assertEquals(33.00,newEntity.getSalary‭‭());

        // DELETE ENTITY
        beekeeperService.deleteBeekeeper(updateEntity);
        Beekeeper deletedEntity = beekeeperService.getBeekeeper(id);
        Assert.assertNull(deletedEntity);
    }
}
