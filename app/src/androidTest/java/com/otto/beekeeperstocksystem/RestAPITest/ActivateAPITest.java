package com.otto.beekeeperstocksystem.RestAPITest;

import android.test.AndroidTestCase;

import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.Factories.PersonFactory;
import com.otto.beekeeperstocksystem.RestAPI.Person.API.Impl.PersonAPIImpl;
import com.otto.beekeeperstocksystem.RestAPI.Person.API.PersonAPI;

import junit.framework.Assert;

import org.mindrot.jbcrypt.BCrypt;
/**
 * Created by Quam on 5/7/2016.
 */
public class ActivateAPITest extends AndroidTestCase {

    private PersonAPI api;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        api = new PersonAPIImpl();

    }

    public void testActivateAccount() throws Exception {
        Person person1 = PersonFactory.create("","","karl@gmail.com");
        Person person = api.createPerson(person1);
        Assert.assertTrue(BCrypt.checkpw("karl@gmail.com", person1.getEmail()));
    }
}
