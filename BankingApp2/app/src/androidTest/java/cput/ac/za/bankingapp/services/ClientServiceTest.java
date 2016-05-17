package cput.ac.za.bankingapp.services;

import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Client;
import cput.ac.za.bankingapp.repository.client.ClientRepository;
import cput.ac.za.bankingapp.repository.client.impl.ClientRepositoryImpl;
import cput.ac.za.bankingapp.services.client.ClientService;

/**
 * Created by Scorpian on 2016-05-17.
 */
public class ClientServiceTest extends AndroidTestCase {

    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Context context = getContext();
        ClientRepository clientRepository = new ClientRepositoryImpl(context);

        // CREATE
         Client client = new Client.Builder()
                 .name("thandile")
                 .surName("nomjila")
                 .idNo("199304")
                 .build();


         Client insertedEntity =clientRepository.save(client);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);



    }

}
