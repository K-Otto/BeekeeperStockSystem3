package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.SubLocation;
import com.otto.beekeeperstocksystem.Repositories.Impl.SubLocationRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.SubLocationRepository;
import junit.framework.Assert;
import java.util.Set;

/**
 * Created by Quam on 4/23/2016.
 */
public class SubLocationRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        SubLocationRepository repo = new SubLocationRepositoryImpl(this.getContext());
        // CREATE
        SubLocation createEntity = new SubLocation.Builder()

                .subLocationName("Watergat")

                .build();
        SubLocation insertedEntity = repo.save(createEntity);
        id = insertedEntity.getSubLocID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<SubLocation> settings = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", settings.size() > 0);

        //READ ENTITY
        SubLocation entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);


        //UPDATE ENTITY
        SubLocation updateEntity = new SubLocation.Builder()
                .copy(entity)
                .subLocationName("river1")
                .build();
        repo.update(updateEntity);
        SubLocation newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "river1", newEntity.getSubLocationName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        SubLocation deletedEntity = repo.findById(id);
        Assert.assertNull(TAG + " DELETE", deletedEntity);

    }

}

