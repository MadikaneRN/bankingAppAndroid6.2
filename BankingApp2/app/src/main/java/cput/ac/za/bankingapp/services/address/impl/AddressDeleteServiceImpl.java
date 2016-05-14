package cput.ac.za.bankingapp.services.address.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import cput.ac.za.bankingapp.conf.database.App;
import cput.ac.za.bankingapp.domain.Address;
import cput.ac.za.bankingapp.repository.address.AddressRepository;
import cput.ac.za.bankingapp.repository.address.AddressRepositoryImpl;
import cput.ac.za.bankingapp.services.address.AddressDeleteService;

/**
 * Created by Scorpian on 2016-05-13.
 */
public class AddressDeleteServiceImpl extends IntentService implements AddressDeleteService {


    private final AddressRepository repository;
    private static  AddressDeleteServiceImpl service = null;


    private static final String ACTION_ADD ="package cput.ac.za.bankingapp.services.address.impl.action.ADD";
    private static final String ACTION_UPDATE="package cput.ac.za.bankingapp.services.address.impl.action.Update";

    private static final String EXTRA_ADD = "package cput.ac.za.bankingapp.services.address.impl.extra.ADD";



    public AddressDeleteServiceImpl() {
        super("AddressDeleteServiceImpl");
        repository = new AddressRepositoryImpl(App.getAppContext());
    }


    private static AddressDeleteServiceImpl getInstance(){
        if(service == null)
            service = new AddressDeleteServiceImpl();
        return service;
    }


    @Override
    public void deleteAddress(Context context, Address address) {
        Intent intent = new Intent(context,AddressDeleteServiceImpl.class);
        intent.putExtra(EXTRA_ADD,address);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Address address = (Address)intent.getSerializableExtra(EXTRA_ADD);
        repository.delete(address);

    }



}
