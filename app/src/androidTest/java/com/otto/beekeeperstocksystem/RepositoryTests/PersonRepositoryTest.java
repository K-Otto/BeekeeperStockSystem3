package com.otto.beekeeperstocksystem.RepositoryTests;


import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.Repositories.Impl.PersonRepositoryImpl;
import com.otto.beekeeperstocksystem.Repositories.PersonRepository;



import java.util.Set;
import junit.framework.Assert;
import android.test.AndroidTestCase;


public class PersonRepositoryTest extends AndroidTestCase{

    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PersonRepository repo = new PersonRepositoryImpl(this.getContext());
        // CREATE
        Person createEntity = new Person.Builder()
                .firstName("Karl")
                .lastName("Otto")
                .email("karl@gmail.com")
                .build();
        Person insertedEntity = repo.save(createEntity);
        id=insertedEntity.getPersonId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);
        System.out.print(insertedEntity);
        //READ ALL
        Set<Person> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Person entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Person updateEntity = new Person.Builder()
                .copy(entity)
                .email("fc89b@test.com")
                .build();
        repo.update(updateEntity);
        Person newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","fc89b@test.com",newEntity.getEmail());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Person deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
