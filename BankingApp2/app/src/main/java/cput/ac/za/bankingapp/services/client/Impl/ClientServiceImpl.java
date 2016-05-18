package cput.ac.za.bankingapp.services.client.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import cput.ac.za.bankingapp.conf.database.App;
import cput.ac.za.bankingapp.domain.Client;
import cput.ac.za.bankingapp.repository.client.ClientRepository;
import cput.ac.za.bankingapp.repository.client.impl.ClientRepositoryImpl;
import cput.ac.za.bankingapp.services.client.ClientService;

/**
 * Created by Scorpian on 2016-05-16.
 */
public class ClientServiceImpl extends IntentService implements ClientService {

    ClientRepository clientRepository = new ClientRepositoryImpl(App.getAppContext());

    private static final String ACTION_ADD = "cput.ac.za.bankingapp.services.client.Impl.action.ADD";
    private static final String ACTION_UPDATE ="cput.ac.za.bankingapp.services.client.Impl.action.UPDATE";

    private static final String EXTRA_ADD ="cput.ac.za.bankingapp.services.client.Impl.extra.ADD";
    private static final String EXTRA_UPDATE ="cput.ac.za.bankingapp.services.client.Impl.extra.UPDATE";


    private static ClientServiceImpl service = null;

    public static ClientServiceImpl getInstance()
    {
        if(service == null)
        {
            service = new ClientServiceImpl();
        }
        return service;
    }


    public ClientServiceImpl()
    {
        super("ClientServiceImpl");
    }


    protected void onHandleIntent(Intent intent)
    {
        try
        {
            if(intent !=null)
            {
                final String action = intent.getAction();
                if(ACTION_ADD.equals(action))
                {
                    final Client param1 = (Client)intent.getSerializableExtra(EXTRA_ADD);
                    saveClient(param1);
                }
                else if(ACTION_UPDATE.equals(action))
                {
                    final Client param1 = (Client)intent.getSerializableExtra(EXTRA_ADD);
                    clientUpdate(param1);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void addClient(Context context, Client client)
     {
        Intent intent = new Intent(context, ClientServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, client);
        context.startService(intent);
     }


    @Override
    public void updateClient(Context context,Client client)
    {
        Intent intent = new Intent(context,ClientServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE,client);
        context.startService(intent);

    }




    private void clientUpdate(Client client)
    {
        ClientRepository personRepository = new ClientRepositoryImpl(getBaseContext());
        personRepository.update(client);
    }

    private void saveClient(Client client)
    {
        ClientRepository clientRepository = new ClientRepositoryImpl(getBaseContext());
        clientRepository.save(client);
    }


}
