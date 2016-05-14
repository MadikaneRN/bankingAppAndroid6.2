package cput.ac.za.bankingapp.services;

import android.content.Intent;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import cput.ac.za.bankingapp.conf.database.App;
import cput.ac.za.bankingapp.domain.Address;
import cput.ac.za.bankingapp.services.address.AddressAddService;
import cput.ac.za.bankingapp.services.address.impl.AddressAddServiceImpl;

/**
 * Created by Scorpian on 2016-05-13.
 */
public class AddressAddServiceTest extends AndroidTestCase{

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }


    public void testAddTenantService() throws Exception {


        Long id;
        Intent intent = new Intent(App.getAppContext(),AddressAddServiceImpl.class);

        AddressAddService addressService = new AddressAddServiceImpl();


        Address address = new Address.Builder()
                .city("jhb")
                .postalCode("7789")
                .streetName("victoria")
                .build();

        addressService.addAddress(App.getAppContext(),address);
        App.getAppContext().startService(intent);
        id = address.getId();

        Assert.assertNotNull("CREATE", address);
    }


}
