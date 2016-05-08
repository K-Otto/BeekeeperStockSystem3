package com.otto.beekeeperstocksystem.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.otto.beekeeperstocksystem.Conf.Util.App;
import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.Factories.PersonFactory;
import com.otto.beekeeperstocksystem.Repositories.Impl.PersonRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.PersonRepository;
import com.otto.beekeeperstocksystem.Service.PersonServices.Impl.PersonServiceImpl;

import junit.framework.Assert;

import java.util.Set;


/**
 * Created by Quam on 5/8/2016.
 */
public class PersonServiceTest extends AndroidTestCase {
    private PersonServiceImpl personService;
    private boolean isBound;
    Person person;
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), PersonServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        Person role = PersonFactory.create("Karl", "Otto", "Karl@gmail.com");
        person = new Person
                .Builder()
                .copy(role)
                .firstName("karl")
                .lastName("otto")
                .email("karl@gmail.com")
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PersonServiceImpl.ActivateServiceLocalBinder binder
                    = (PersonServiceImpl.ActivateServiceLocalBinder) service;
            personService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAdd()throws Exception{
        Person insertedPerson = personService.addPerson(person);
        id = insertedPerson.getPersonId();
        Assert.assertNotNull(insertedPerson);

        Set<Person> allRam = personService.getAll();
        Assert.assertTrue(allRam.size()>0);

        //READ ENTITY
        Person entity = personService.getPerson(id);
        Assert.assertNotNull(entity);

        //UPDATE ENTITY
        Person updateEntity = new Person.Builder()
                .copy(entity)
                .email("karl@yahoo.com")
                .build();
        personService.updatePerson(updateEntity);
        Person newEntity = personService.getPerson(id);
        Assert.assertEquals("karl@yahoo.com",newEntity.getEmail());

        // DELETE ENTITY
        personService.deletePerson(updateEntity);
        Person deletedEntity = personService.getPerson(id);
        Assert.assertNull(deletedEntity);
    }
}