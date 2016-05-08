package cput.ac.za.bankingapp.repository;

import junit.framework.Assert;

import android.test.AndroidTestCase;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Account;
import cput.ac.za.bankingapp.repository.account.AccountRepository;
import cput.ac.za.bankingapp.repository.account.impl.AccountRepositoryImpl;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class AccountRepositoryTest extends AndroidTestCase{

    private static final String TAG = "ACCOUNT TEST";
    private Long id;


    public void testCreateReadUpdate() throws Exception
    {
        AccountRepository repo = new AccountRepositoryImpl( this.getContext());
        // CREATE
        Account createEntity = new Account.Builder()
                .accNo("mad123")
                .balance(5000.0)
                .accountType("savings")
                .build();
        Account insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();

        Assert.assertNotNull(TAG+" CREATE ",insertedEntity);

        //READ ENTITY
        Account entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Account> accounts = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",accounts.size() >0);

        //UPDATE ENTITY
        Account updateEntity = new Account.Builder()
                .copy(entity)
                .accNo("Mgay123")
                .build();

        repo.update(updateEntity);
        Account newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "LOKI", newEntity.getAccNo());


        //DELTE ENTITY
        repo.delete(updateEntity);
        Account deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);


        repo.deleteAll();
    }
}
