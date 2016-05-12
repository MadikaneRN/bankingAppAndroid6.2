package cput.ac.za.bankingapp.services.airtime.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import android.app.IntentService;

import cput.ac.za.bankingapp.conf.database.App;
import cput.ac.za.bankingapp.domain.Airtime;
import cput.ac.za.bankingapp.repository.airtime.AirtimeRepository;
import cput.ac.za.bankingapp.repository.airtime.impl.AirtimeRepositoryImpl;
import cput.ac.za.bankingapp.services.airtime.AirtimeService;

/**
 * Created by Scorpian on 2016-05-12.
 */
public class AirtimeServiceImpl extends IntentService implements AirtimeService {

    private final AirtimeRepository repository;
    private static AirtimeServiceImpl service = null;

    private static final String ACTION_ADD = "package cput.ac.za.bankingapp.services.airtime.impl.ADD";
    private static final String ACTION_UPDATE = "package cput.ac.za.bankingapp.services.airtime.impl.UPDATE";

    private static final String EXTRA_ADD = "package cput.ac.za.bankingapp.services.airtime.impl.ADD";
    private static final String EXTRA_UPDATE = "package cput.ac.za.bankingapp.services.airtime.impl.UPDATE";

    public AirtimeServiceImpl() {
        super("AirtimeServiceImpl");
        repository = new AirtimeRepositoryImpl(App.getAppContext());
    }

    public static AirtimeServiceImpl getInstance() {
        if (service == null)
            service = new AirtimeServiceImpl();
        return service;
    }


    private void postAirtime(Airtime air) {
        Airtime createdAirtime = repository.update(air);
        repository.save(createdAirtime);
    }


    private void updateCustomer(Airtime air) {
        Airtime updatedCustomer = repository.update(air);
        repository.save(updatedCustomer);
    }


    @Override
    public void addAirtime(Context context, Airtime airtime) {
        Intent intent = new Intent(context, AirtimeServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, airtime);
        context.startService(intent);

    }




    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Airtime airtime = (Airtime) intent.getSerializableExtra(EXTRA_ADD);
                postAirtime(airtime);
            } else if (ACTION_UPDATE.equals(action)) {
                final Airtime airtime = (Airtime) intent.getSerializableExtra(EXTRA_UPDATE);
                updateCustomer(airtime);
            }

        }

    }


}