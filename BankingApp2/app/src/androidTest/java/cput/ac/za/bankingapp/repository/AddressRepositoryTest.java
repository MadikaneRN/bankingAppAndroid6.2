package cput.ac.za.bankingapp.repository;
import android.test.AndroidTestCase;
import junit.framework.Assert;
import java.util.Set;

import cput.ac.za.bankingapp.domain.Address;
import cput.ac.za.bankingapp.repository.address.AddressRepository;
import cput.ac.za.bankingapp.repository.address.AddressRepositoryImpl;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class AddressRepositoryTest extends AndroidTestCase {

    private static final String TAG = "ADDRESS TEST";
    private Long id;

    public void testCreateReadUpdate() throws Exception {

        AddressRepository repo = new AddressRepositoryImpl(this.getContext());
        // CREATE
        Address createEntity = new Address.Builder()
                .postalCode("7754")
                .streetName("Mpangele")
                .city("cape town")
                .build();
        Address insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();

        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ENTITY
        Address entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Address> accounts = repo.findAll();
        Assert.assertTrue(TAG + "READ ENTITY", accounts.size() > 0);

        //UPDATE ENTITY
        Address updateEntity = new Address.Builder()
                .copy(entity)
                .postalCode("7784")
                .build();

        repo.update(updateEntity);
        Address newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "7784",newEntity.getPostalCode());

        //DELTE ENTITY
        repo.delete(updateEntity);
        Address deleteEntity = repo.findById(id);
        Assert.assertNull(TAG + "DELETE", deleteEntity);

        repo.deleteAll();

    }


}
