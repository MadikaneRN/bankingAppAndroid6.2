package cput.ac.za.bankingapp.factoryTest;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cput.ac.za.bankingapp.domain.Address;
import cput.ac.za.bankingapp.factory.AddressFactory;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class AddressTest {



    private Address address;
    @Before

    public void setUp() throws Exception {

        address = AddressFactory.getAddress("langa", "cape", "8001");

    }


    @Test
    public void testAddress() throws Exception {

        Assert.assertNotNull(address);
        Assert.assertEquals(address.getStreetName(),"langa");
        Assert.assertEquals(address.getPostalCode(),"8001");
        Assert.assertEquals(address.getCity(),"cape");

    }
    //not gna use
    @Test
    public void testUpdate() throws Exception {
        Address addressUpdate = new Address.Builder(address.getStreetName())// uses accNo as primary key
                .copy(address)
                .postalCode("7745")
                .city("JHB")
                .build();

        Assert.assertNotNull(addressUpdate);
        Assert.assertEquals(addressUpdate.getStreetName(),"langa");

        Assert.assertEquals(addressUpdate.getPostalCode(),"7745");
        Assert.assertEquals(addressUpdate.getCity(),"JHB");

    }



}
