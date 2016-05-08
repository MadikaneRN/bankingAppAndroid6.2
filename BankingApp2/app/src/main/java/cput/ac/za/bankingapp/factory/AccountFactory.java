package cput.ac.za.bankingapp.factory;


import cput.ac.za.bankingapp.domain.Account;

/**
 * Created by Scorpian on 2016-03-28.
 */

//google package naming conventions
public class AccountFactory {



    public static Account getAccount(String accNo, double balance, String accountType)
    {
        Account myAccount = new Account.Builder() // change name to conivinient name
                .accNo(accNo)
                .balance(balance)
                .accountType(accountType)
                .build();

        return myAccount;
    }



}
