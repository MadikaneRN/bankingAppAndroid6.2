package cput.ac.za.bankingapp.repository;


import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Bank;
import cput.ac.za.bankingapp.repository.bank.BankRepository;
import cput.ac.za.bankingapp.repository.bank.impl.BankRepositoryImpl;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class BankRepositoryTest extends AndroidTestCase{

    private static final String TAG = "BANK TEST";
    private Long id;




    public void testCreateUpdateDelete() throws Exception
    {

        BankRepository repo = new BankRepositoryImpl(this.getContext());
        // CREATE
        Bank createEntity = new Bank.Builder()
                .code("007")
                .name("capitecBellville")
                .build();
        Bank insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);


        //READ ENTITY
       Bank entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Bank> banks = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",banks.size() >0);

        //UPDATE ENTITY
        Bank updateEntity = new Bank.Builder()
                .copy(entity)
                .name("capitecKhayelitsha")
                .build();

        repo.update(updateEntity);
        Bank newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "capitecKhayelitsha",newEntity.getName());


        //DELTE ENTITY
        repo.delete(updateEntity);
        Bank deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();

    }
}
