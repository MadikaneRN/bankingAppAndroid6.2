package cput.ac.za.bankingapp.repository;

import android.test.AndroidTestCase;
import junit.framework.Assert;
import java.util.Set;

import cput.ac.za.bankingapp.domain.Airtime;
import cput.ac.za.bankingapp.repository.airtime.AirtimeRepository;
import cput.ac.za.bankingapp.repository.airtime.impl.AirtimeRepositoryImpl;

/**
 * Created by Scorpian on 2016-05-03.
 */
public class AirtimeRepositoryTest  extends AndroidTestCase {

    private static final String TAG = "AIRTIME TEST";
    private Long id;


    public void testCreateUpdateDelete() throws Exception
    {

        AirtimeRepository repo =  new AirtimeRepositoryImpl(this.getContext());

        // CREATE
        Airtime createEntity = new Airtime.Builder()
                .cellphoneNo("0825915433")
                .beneficiary("Ntsikelelo")
                .serviceProvider("Cell C")
                .build();

        Airtime insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();

        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ENTITY
        Airtime entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Airtime> accounts = repo.findAll();
        Assert.assertTrue(TAG + "READ ENTITY", accounts.size() > 0);


        //UPDATE ENTITY
        Airtime updateEntity = new Airtime.Builder()
                .copy(entity)
                .serviceProvider("MTN")
                .build();

        repo.update(updateEntity);
        Airtime newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "MTN", newEntity.getServiceProvider());


        //DELTE ENTITY
        repo.delete(updateEntity);
        Airtime deleteEntity = repo.findById(id);
        Assert.assertNull(TAG + "DELETE", deleteEntity);

        repo.deleteAll();

    }

}
