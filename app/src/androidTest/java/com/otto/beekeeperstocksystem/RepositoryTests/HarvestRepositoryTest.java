package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.Harvest;
import com.otto.beekeeperstocksystem.Repositories.HarvestRepository;
import com.otto.beekeeperstocksystem.Repositories.Impl.HarvestRepositoryImpl;
import junit.framework.Assert;

import java.util.Set;
/**
 * Created by Quam on 4/23/2016.
 */
public class HarvestRepositoryTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        HarvestRepository repo = new HarvestRepositoryImpl(this.getContext());
        // CREATE
        Harvest createEntity = new Harvest.Builder()
                .harvestDate("March")
                .totalWeight(5.00)
                .totalWeightRemaining(5.00)


                .build();
        Harvest insertedEntity = repo.save(createEntity);
        id=insertedEntity.getHarvestID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Harvest> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Harvest entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Harvest updateEntity = new Harvest.Builder()
                .copy(entity)
                .totalWeightRemaining(7.00)
                .build();
        repo.update(updateEntity);
        Harvest newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",7.00,newEntity.gettotalWeightRemaining());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Harvest deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
