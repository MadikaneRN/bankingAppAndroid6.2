package cput.ac.za.bankingapp.factory;

import cput.ac.za.bankingapp.domain.Deposit;
import cput.ac.za.bankingapp.domain.Account;


/**
 * Created by Scorpian on 2016-04-03.
 */
public class DepositFactory {


    private String depositNo;
    private Account fromAccount;
    private Account toAccount;
    private double amount;


    public static Deposit getDeposit(String depositNo, Account fromAccount,  Account toAccount,double amount)
    {
        Deposit myDeposit = new Deposit.Builder() // change name to conivinient name
                .depositNo(depositNo)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .build();
        return myDeposit;
    }





}
