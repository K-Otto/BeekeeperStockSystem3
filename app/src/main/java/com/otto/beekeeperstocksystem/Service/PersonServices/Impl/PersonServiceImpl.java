package com.otto.beekeeperstocksystem.Service.PersonServices.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import java.util.Set;
import android.app.Service;

import com.otto.beekeeperstocksystem.Conf.Util.App;
import com.otto.beekeeperstocksystem.Domain.Person;

import com.otto.beekeeperstocksystem.Repositories.Impl.PersonRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.PersonRepository;

import com.otto.beekeeperstocksystem.Service.PersonServices.PersonService;


import java.io.IOException;
/**
 * Created by Quam on 5/7/2016.
 */
public class PersonServiceImpl extends Service implements PersonService {

    final private PersonRepository personRepository;

    private static PersonServiceImpl service = null;

    public static PersonServiceImpl getInstance() {
        if (service == null)
            service = new PersonServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private PersonRepository repo;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public PersonServiceImpl getService() {
            return PersonServiceImpl.this;
        }
    }

    private PersonServiceImpl()
    {
        personRepository = new PersonRepositoryImpl(App.getAppContext());
    }

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.update(person);
    }

    @Override
    public Person getPerson(Long personID) {
        return personRepository.findById(personID);
    }

    @Override
    public Set<Person> getAll() {
        Set<Person> ram;
        ram = personRepository.findAll();
        return ram;
    }

    @Override
    public Person deletePerson(Person person) {
        return personRepository.delete(person);
    }

    @Override
    public int deleteAllPerson() {
        return personRepository.deleteAll();
    }
}