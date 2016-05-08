package cput.ac.za.bankingapp.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Electricity;
import cput.ac.za.bankingapp.repository.electricity.ElectricityRepository;
import cput.ac.za.bankingapp.repository.electricity.impl.ElectricityRepositoryImpl;

/**
 * Created by Scorpian on 2016-05-03.
 */
public class ElectricityRepositoryTest extends AndroidTestCase {

    private static final String TAG = "ELECTRICITY TEST";
    private Long id;


    public void testCreateReadUpdate() throws Exception
    {
        ElectricityRepository repo = new ElectricityRepositoryImpl( this.getContext());
        // CREATE
        Electricity createEntity = new Electricity.Builder()
                .meterNo("Mad91123")
                .supplierName("Electricity")
                .amount(50.0)
                .build();

        Electricity insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ENTITY
        Electricity entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Electricity> accounts = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",accounts.size() >0);

        //UPDATE ENTITY
        Electricity updateEntity = new Electricity.Builder()
                .copy(entity)
                .meterNo("Mgayi91123")
                .build();

        repo.update(updateEntity);
        Electricity newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", " Mgayi91123", newEntity.getMeterNo());


        //DELTE ENTITY
        repo.delete(updateEntity);
        Electricity deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);


        repo.deleteAll();
    }


}
