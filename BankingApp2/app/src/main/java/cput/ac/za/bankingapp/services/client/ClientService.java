package cput.ac.za.bankingapp.services.client;

import android.content.Context;

import cput.ac.za.bankingapp.domain.Client;

/**
 * Created by Scorpian on 2016-05-16.
 */
public interface ClientService {

    void addClient(Context context, Client client);
    void updateClient(Context context, Client client);

}
