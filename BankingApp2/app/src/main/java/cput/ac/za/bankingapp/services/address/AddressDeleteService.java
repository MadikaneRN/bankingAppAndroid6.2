package cput.ac.za.bankingapp.services.address;

import android.content.Context;

import cput.ac.za.bankingapp.domain.Address;

/**
 * Created by Scorpian on 2016-05-13.
 */
public interface AddressDeleteService {

    void deleteAddress(Context context, Address address);
}
