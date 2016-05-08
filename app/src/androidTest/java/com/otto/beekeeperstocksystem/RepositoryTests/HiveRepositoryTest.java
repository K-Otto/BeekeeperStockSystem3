package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;

import com.otto.beekeeperstocksystem.Domain.Hive;
import com.otto.beekeeperstocksystem.Repositories.HiveRepository;
import com.otto.beekeeperstocksystem.Repositories.Impl.HiveRepositoryImpl;
import junit.framework.Assert;

import java.util.Set;
/**
 * Created by Quam on 4/23/2016.
 */
public class HiveRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        HiveRepository repo = new HiveRepositoryImpl(this.getContext());
        // CREATE
        Hive createEntity = new Hive.Builder()

                .hiveState("Active")

                .build();
        Hive insertedEntity = repo.save(createEntity);
        id = insertedEntity.getHiveID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Hive> settings = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", settings.size() > 0);

        //READ ENTITY
        Hive entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);


        //UPDATE ENTITY
        Hive updateEntity = new Hive.Builder()
                .copy(entity)
                .hiveState("Inactive")
                .build();
        repo.update(updateEntity);
        Hive newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "Inactive", newEntity.getHiveState());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Hive deletedEntity = repo.findById(id);
        Assert.assertNull(TAG + " DELETE", deletedEntity);

    }

}

