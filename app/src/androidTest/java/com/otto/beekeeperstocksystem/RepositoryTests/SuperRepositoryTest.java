package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.Supers;
import com.otto.beekeeperstocksystem.Repositories.Impl.SuperRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.SuperRepository;
import junit.framework.Assert;
import java.util.Set;
/**
 * Created by Quam on 4/23/2016.
 */
public class SuperRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        SuperRepository repo = new SuperRepositoryImpl(this.getContext());
        // CREATE
        Supers createEntity = new Supers.Builder()

                .superState("Active")

                .build();
        Supers insertedEntity = repo.save(createEntity);
        id = insertedEntity.getSuperID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Supers> settings = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", settings.size() > 0);

        //READ ENTITY
        Supers entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);


        //UPDATE ENTITY
        Supers updateEntity = new Supers.Builder()
                .copy(entity)
                .superState("InActive")
                .build();
        repo.update(updateEntity);
        Supers newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "InActive", newEntity.getSuperState());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Supers deletedEntity = repo.findById(id);
        Assert.assertNull(TAG + " DELETE", deletedEntity);

    }

}