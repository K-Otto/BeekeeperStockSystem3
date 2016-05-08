package com.otto.beekeeperstocksystem.RestAPI.Person.API;

import com.otto.beekeeperstocksystem.Domain.Person;

import java.io.IOException;
/**
 * Created by Quam on 5/7/2016.
 */
public interface PersonAPI {

    Person createPerson(Person person) throws IOException;

    Person updatePerson(Person person) throws IOException;
}
