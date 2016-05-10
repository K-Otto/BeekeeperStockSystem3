package com.otto.beekeeperstocksystem.Service.PersonServices;

import android.content.Context;

import com.otto.beekeeperstocksystem.Domain.Person;

import java.util.Set;

/**
 * Created by Quam on 5/7/2016.
 */
public interface PersonService {


        Person addPerson(Person person);

        Person updatePerson(Person person);

        Person getPerson(Long personID);

        Set<Person> getAll();

        Person deletePerson(Person person);

        int deleteAllPerson();
    }

