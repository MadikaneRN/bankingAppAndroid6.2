
package cput.ac.za.bankingapp.factoryTest;

import cput.ac.za.bankingapp.factory.ClientFactory;
import cput.ac.za.bankingapp.domain.Client;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Scorpian on 2016-03-28.
 */


//Finish the rest of the Test cases to match the class AccountTest

public class ClientTest {

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = ClientFactory.getClient("91101258","Ntsikelelo","Madikane");

    }


    @Test
    public void testClient() throws Exception {

        Assert.assertEquals("91101258",client.getIdNo());

    }

    public void testUpdate() throws Exception {
        Client clienttUpdate = new Client.Builder(client.getIdNo())// uses accNo as primary key
                .copy(client)
                .name("Richard")
                .surName("Madikane")
                .build();

        org.junit.Assert.assertNotNull(clienttUpdate);
        org.junit.Assert.assertEquals(client.getIdNo(),"911012");

        org.junit.Assert.assertEquals(client.getName(),"Richard");
        org.junit.Assert.assertEquals(client.getSurName(),"Madikane");

    }




}
