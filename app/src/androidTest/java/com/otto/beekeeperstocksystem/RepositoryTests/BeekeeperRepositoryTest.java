package com.otto.beekeeperstocksystem.RepositoryTests;

import com.otto.beekeeperstocksystem.Domain.Beekeeper;
import com.otto.beekeeperstocksystem.Repositories.BeekeeperRepository;
import com.otto.beekeeperstocksystem.Repositories.Impl.BeekeeperRepositoryImpl;
import android.test.AndroidTestCase;
import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Quam on 4/23/2016.
 */
public class BeekeeperRepositoryTest extends AndroidTestCase{
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        BeekeeperRepository repo = new BeekeeperRepositoryImpl(this.getContext());
        // CREATE
        Beekeeper createEntity = new Beekeeper.Builder‭()

                .salary(20.00)

                .build();
        Beekeeper insertedEntity = repo.save(createEntity);
        id=insertedEntity.getBeekeeperID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Beekeeper> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Beekeeper entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Beekeeper updateEntity = new Beekeeper.Builder‭()
                .copy(entity)
                .salary(22.00)
                .build();
        repo.update(updateEntity);
        Beekeeper newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",22.00,newEntity.getSalary‭‭());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Beekeeper deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}


