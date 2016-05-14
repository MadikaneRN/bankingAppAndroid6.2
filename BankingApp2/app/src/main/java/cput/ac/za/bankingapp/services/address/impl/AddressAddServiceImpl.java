package cput.ac.za.bankingapp.services.address.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import cput.ac.za.bankingapp.conf.database.App;
import cput.ac.za.bankingapp.domain.Address;
import cput.ac.za.bankingapp.repository.address.AddressRepository;
import cput.ac.za.bankingapp.repository.address.AddressRepositoryImpl;
import cput.ac.za.bankingapp.services.address.AddressAddService;

/**
 * Created by Scorpian on 2016-05-06.
 */
public class AddressAddServiceImpl extends IntentService implements AddressAddService {

    private final AddressRepositoryImpl repository;
    private static  AddressAddServiceImpl service = null;

    private static final String ACTION_ADD ="package cput.ac.za.bankingapp.services.address.impl.action.ADD";
    private static final String ACTION_UPDATE="package cput.ac.za.bankingapp.services.address.impl.action.UPDATE";

    private static final String EXTRA_ADD = "package cput.ac.za.bankingapp.services.address.impl.extra.ADD";
    private static final String EXTRA_UPDATE ="package cput.ac.za.bankingapp.services.address.impl.UPDATE";
    public AddressAddServiceImpl()
    {
        super("AddressAddServiceImpl");
        repository = new AddressRepositoryImpl(App.getAppContext());
    }

    private static AddressAddServiceImpl getInstance(){
        if(service == null)
            service = new AddressAddServiceImpl();
        return service;
    }

    public void addAddress(Context context, Address address)
    {
        Intent intent = new Intent(context,AddressAddServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,address);
        context.startService(intent);

    }


    protected void onHandleIntent(Intent intent)
    {
       if(intent != null)
       {
           final String action = intent.getAction();
           if(ACTION_ADD.equals(action))
           {
               final Address address = (Address)intent.getSerializableExtra(EXTRA_ADD);
               repository.save(address );
           }
           else if(ACTION_UPDATE.equals(action))
           {
                final Address address = (Address)intent.getSerializableExtra(EXTRA_UPDATE);
                repository.update(address);
           }
       }

    }



}
