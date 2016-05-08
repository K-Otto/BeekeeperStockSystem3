package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.Salesman;
import com.otto.beekeeperstocksystem.Repositories.Impl.SalesmanRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.SalesmanRepository;
import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Quam on 4/23/2016.
 */
public class SalesmanRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        SalesmanRepository repo = new SalesmanRepositoryImpl(this.getContext());
        // CREATE
        Salesman createEntity = new Salesman.Builder‭()

                .salary(20.00)

                .build();
        Salesman insertedEntity = repo.save(createEntity);
        id = insertedEntity.getSalesmanID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Salesman> settings = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", settings.size() > 0);

        //READ ENTITY
        Salesman entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);


        //UPDATE ENTITY
        Salesman updateEntity = new Salesman.Builder‭()
                .copy(entity)
                .salary(22.00)
                .build();
        repo.update(updateEntity);
        Salesman newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", 22.00, newEntity.getSalary‭‭());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Salesman deletedEntity = repo.findById(id);
        Assert.assertNull(TAG + " DELETE", deletedEntity);

    }

}