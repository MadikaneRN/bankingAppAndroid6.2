package cput.ac.za.bankingapp.services;

import android.content.Intent;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.bankingapp.conf.database.App;
import cput.ac.za.bankingapp.domain.Airtime;
import cput.ac.za.bankingapp.repository.airtime.AirtimeRepository;
import cput.ac.za.bankingapp.repository.airtime.impl.AirtimeRepositoryImpl;
import cput.ac.za.bankingapp.services.airtime.AirtimeService;
import cput.ac.za.bankingapp.services.airtime.impl.AirtimeServiceImpl;

/**
 * Created by Scorpian on 2016-05-12.
 */
public class AirtimeServiceTest extends AndroidTestCase{
    private AirtimeService service;
    private AirtimeRepository repo;
    private Airtime airtime;


    public void setUp()throws Exception
    {
        super.setUp();
        AirtimeRepository repo = new AirtimeRepositoryImpl(this.getContext());
    }

  public void testAddCash()throws Exception
  {
    Long id;
    Intent intent = new Intent(App.getAppContext(),AirtimeRepositoryImpl.class);
    AirtimeService myservice = new AirtimeServiceImpl();
    Airtime airtime1 = new Airtime.Builder()
            .cellphoneNo("0825915433")
            .beneficiary("Ntsikelelo")
            .build();

      myservice.addAirtime(App.getAppContext(), airtime1);
      App.getAppContext().startService(intent);
      id = airtime1.getId();

      Set<Airtime> airtimeSet = repo.findAll();
      Assert.assertEquals(airtimeSet.size(),10);

  }



}




