package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import junit.framework.Assert;
import com.otto.beekeeperstocksystem.Domain.Customer;
import com.otto.beekeeperstocksystem.Repositories.CustomerRepository;
import com.otto.beekeeperstocksystem.Repositories.Impl.CustomerRepositoryImpl;

import java.util.Set;

/**
 * Created by Quam on 4/23/2016.
 */
public class CustomerRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        CustomerRepository repo = new CustomerRepositoryImpl(this.getContext());
        // CREATE
        Customer createEntity = new Customer.Builder‭()

                .address("CapeTown")

                .build();
        Customer insertedEntity = repo.save(createEntity);
        id = insertedEntity.getCustomerID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Customer> settings = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", settings.size() > 0);

        //READ ENTITY
        Customer entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);


        //UPDATE ENTITY
        Customer updateEntity = new Customer.Builder‭()
                .copy(entity)
                .address("CapeTown")
                .build();
        repo.update(updateEntity);
        Customer newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "CapeTown", newEntity.getAddress());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Customer deletedEntity = repo.findById(id);
        Assert.assertNull(TAG + " DELETE", deletedEntity);

    }

}

