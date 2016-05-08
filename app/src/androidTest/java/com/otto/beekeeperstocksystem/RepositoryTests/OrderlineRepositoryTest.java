package com.otto.beekeeperstocksystem.RepositoryTests;
import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.Orderline;
import com.otto.beekeeperstocksystem.Repositories.Impl.OrderlineRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.OrderlineRepository;
import junit.framework.Assert;

import java.util.Set;
/**
 * Created by Quam on 4/23/2016.
 */
public class OrderlineRepositoryTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        OrderlineRepository repo = new OrderlineRepositoryImpl(this.getContext());
        // CREATE
        Orderline createEntity = new  Orderline.Builder()
                .quantity(2.00)
                .unitPrice(5.00)

                .build();
        Orderline insertedEntity = repo.save(createEntity);
        id=insertedEntity.getOrderlineID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set< Orderline> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Orderline entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Orderline updateEntity = new  Orderline.Builder()
                .copy(entity)
                .quantity(8.00)
                .build();
        repo.update(updateEntity);
        Orderline newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",8.00,newEntity.getQuantity());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Orderline deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
