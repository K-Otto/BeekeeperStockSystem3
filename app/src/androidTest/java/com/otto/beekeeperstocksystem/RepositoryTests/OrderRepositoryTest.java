package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.Order;
import com.otto.beekeeperstocksystem.Repositories.Impl.OrderRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.OrderRepository;
import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Quam on 4/23/2016.
 */
public class OrderRepositoryTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        OrderRepository repo = new OrderRepositoryImpl(this.getContext());
        // CREATE
        Order createEntity = new  Order.Builder()
                .orderDate("March")


                .build();
        Order insertedEntity = repo.save(createEntity);
        id=insertedEntity.getOrderID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set< Order> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Order entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Order updateEntity = new  Order.Builder()
                .copy(entity)
                .orderDate("April")
                .build();
        repo.update(updateEntity);
        Order newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","April",newEntity.getSalesDate());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Order deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}