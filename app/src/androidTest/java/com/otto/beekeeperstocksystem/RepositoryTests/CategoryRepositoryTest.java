package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import junit.framework.Assert;
import java.util.Set;
import com.otto.beekeeperstocksystem.Domain.Category;
import com.otto.beekeeperstocksystem.Repositories.CategoryRepository;
import com.otto.beekeeperstocksystem.Repositories.Impl.CategoryRepositoryImpl;

/**
 * Created by Quam on 4/23/2016.
 */
public class CategoryRepositoryTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        CategoryRepository repo = new CategoryRepositoryImpl(this.getContext());
        // CREATE
        Category createEntity = new Category.Builder()

                .stockType(5.00)

                .build();
        Category insertedEntity = repo.save(createEntity);
        id=insertedEntity.getCategoryId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Category> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Category entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Category updateEntity = new Category.Builder()
                .copy(entity)
                .stockType(10.00)
                .build();
        repo.update(updateEntity);
        Category newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",10.00,newEntity.getstockType());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Category deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}



