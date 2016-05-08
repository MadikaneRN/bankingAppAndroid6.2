package cput.ac.za.bankingapp.factory;

import cput.ac.za.bankingapp.domain.Debit;

import cput.ac.za.bankingapp.domain.Account;

/**
 * Created by Scorpian on 2016-04-03.
 */

public class DebitFactory {

    public static Debit getDebit(String accNo, double amount,Account account, Account transferAccount)
    {
        Debit myDebit = new Debit.Builder(accNo) // change name to conivinient name
                .amount(amount)
                .account(account)
                .transferAccount(transferAccount)
                .build();

        return myDebit;
    }





}
