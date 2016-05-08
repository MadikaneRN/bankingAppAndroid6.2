package cput.ac.za.bankingapp.factory;


import cput.ac.za.bankingapp.domain.Airtime;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class AirtimeFactory {


    public static Airtime getAirtime(String cellphoneNo, String beneficiary, String serviceProvider)
    {
        Airtime myAirtime = new Airtime.Builder() // change name to conivinient name
                .cellphoneNo(cellphoneNo)
                .beneficiary(beneficiary)
                .serviceProvider(serviceProvider)
                .build();
        return myAirtime;
    }



}
