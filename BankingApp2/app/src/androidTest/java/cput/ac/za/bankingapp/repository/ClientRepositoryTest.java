package cput.ac.za.bankingapp.repository;


import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Client;
import cput.ac.za.bankingapp.repository.client.ClientRepository;
import cput.ac.za.bankingapp.repository.client.impl.ClientRepositoryImpl;

/**
 * Created by Scorpian on 2016-04-23.
 */
public class ClientRepositoryTest extends AndroidTestCase {

    private static final String TAG = "CLIENT TEST";
    private Long id;

    public void testCreateReadUpdate() throws Exception
    {
        ClientRepository repo = new ClientRepositoryImpl(this.getContext());
        // CREATE
        Client createEntity = new Client.Builder()
                .idNo("9110123")
                .name("Willem")
                .surName("Rothman")
                .build();
        Client insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ",insertedEntity);

        /*
        //Private system.out.println
        System.out.println(insertedEntity.getId() +" "+insertedEntity.getName());
        System.out.println("The ID is: " + id);
        */

        //READ ENTITY
        Client entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Client> clients = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",clients.size() >0);

        //UPDATE ENTITY
        Client updateEntity = new Client.Builder()
                .copy(entity)
                .name("Loki")
                .build();

        repo.update(updateEntity);
        Client newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "LOKI",newEntity.getName());


        //DELTE ENTITY
        repo.delete(updateEntity);
        Client deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();

    }


}
