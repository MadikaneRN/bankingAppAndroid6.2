package cput.ac.za.bankingapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Statement;
import cput.ac.za.bankingapp.repository.statement.StatementRepository;
import cput.ac.za.bankingapp.repository.statement.impl.StatmentRepositoryImpl;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class StatementRepositoryTest extends AndroidTestCase {

    private static final String TAG = "STATEMENT TEST";
    private Long id;


    public void testCreateUpdateDelete() throws Exception
    {

        StatementRepository repo =  new StatmentRepositoryImpl(this.getContext());

        // CREATE
         Statement createEntity = new Statement.Builder()
                 .weekandDay("Week 14 Monday")
                 .details("Electricity and Airtime")
                .build();

        Statement insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();

         Assert.assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ENTITY
        Statement entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Statement> accounts = repo.findAll();
        Assert.assertTrue(TAG + "READ ENTITY", accounts.size() > 0);


        //UPDATE ENTITY
        Statement updateEntity = new Statement.Builder()
                .copy(entity)
                .details("Airitime and Electricity")
                .build();

        repo.update(updateEntity);
        Statement newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "Airtime and Electricity", newEntity.getDetails());


        //DELTE ENTITY
        repo.delete(updateEntity);
        Statement deleteEntity = repo.findById(id);
        Assert.assertNull(TAG + "DELETE", deleteEntity);

        repo.deleteAll();



    }
}
