package com.otto.beekeeperstocksystem.RepositoryTests;

import android.test.AndroidTestCase;
import com.otto.beekeeperstocksystem.Domain.Product;
import com.otto.beekeeperstocksystem.Repositories.Impl.ProductRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.ProductRepository;
import junit.framework.Assert;

import java.util.Set;
/**
 * Created by Quam on 4/23/2016.
 */
public class ProductRepositoryTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ProductRepository repo = new ProductRepositoryImpl(this.getContext());
        // CREATE
        Product createEntity = new Product.Builder()
                .totalStock(5.00)
                .totalStockRemaining(5.00)


                .build();
        Product insertedEntity = repo.save(createEntity);
        id=insertedEntity.getProductID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Product> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Product entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Product updateEntity = new Product.Builder()
                .copy(entity)
                .totalStockRemaining(2.00)
                .build();
        repo.update(updateEntity);
        Product newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",2.00,newEntity.getTotalStockRemaining());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Product deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
