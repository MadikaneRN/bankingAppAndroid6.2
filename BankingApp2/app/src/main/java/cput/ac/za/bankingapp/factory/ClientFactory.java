package cput.ac.za.bankingapp.factory;


import cput.ac.za.bankingapp.domain.Client;

/**
 * Created by Scorpian on 2016-03-28.
 */
public class ClientFactory {


    public static Client getClient(String idNo, String name, String surName)
    {
        Client myClient = new Client.Builder()
                .idNo(idNo)
                .name(name)
                .surName(surName)
                .build();

        return myClient;

    }



}


