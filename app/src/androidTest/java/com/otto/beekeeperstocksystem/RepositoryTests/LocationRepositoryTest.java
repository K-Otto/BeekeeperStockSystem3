package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.Location;
import com.otto.beekeeperstocksystem.Repositories.Impl.LocationRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.LocationRepository;
import junit.framework.Assert;

import java.util.Set;
/**
 * Created by Quam on 4/23/2016.
 */
public class LocationRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        LocationRepository repo = new LocationRepositoryImpl(this.getContext());
        // CREATE
        Location createEntity = new Location.Builder‭()

                .locationName‭("CapeTown")

                .build();
        Location insertedEntity = repo.save(createEntity);
        id = insertedEntity.getLocID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Location> settings = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", settings.size() > 0);

        //READ ENTITY
        Location entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);


        //UPDATE ENTITY
        Location updateEntity = new Location.Builder‭()
                .copy(entity)
                .locationName‭("Durban")
                .build();
        repo.update(updateEntity);
        Location newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "Durban", newEntity.getLocationName‭‭());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Location deletedEntity = repo.findById(id);
        Assert.assertNull(TAG + " DELETE", deletedEntity);

    }

}


