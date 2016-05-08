package cput.ac.za.bankingapp.factory;


import cput.ac.za.bankingapp.domain.Address;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class AddressFactory {


    public static Address getAddress(String streetName, String city, String postalCode)
    {
        Address myAdress = new Address.Builder() // change name to conivinient name
                .streetName(streetName)
                .city(city)
                .postalCode(postalCode)
                .build();

        return myAdress;
    }




}
