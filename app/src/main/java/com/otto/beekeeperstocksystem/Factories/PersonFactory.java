package com.otto.beekeeperstocksystem.Factories;

import com.otto.beekeeperstocksystem.Domain.Person;

/**
 * Created by Quam on 4/21/2016.
 */
public class PersonFactory {
    public static Person create(String firstName, String lastName, String email){
        Person persons = new Person.Builder()
                .lastName(lastName)
                .firstName(firstName)
                .email(email)
                .build();
        return persons ;
    }
}
