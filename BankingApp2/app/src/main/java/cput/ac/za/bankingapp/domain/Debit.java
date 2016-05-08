package cput.ac.za.bankingapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Scorpian on 2016-04-01.
 */


public class Debit implements Serializable{

    private Long id;
    private String accNo;

    /** RELATIONSHIPS **/
    private Account account;
    private Account transferAccount;
    /** RELATIONSHIPS **/


    private double amount;

    public Debit(Builder builder) {

        this.accNo = builder.accNo;
        this.account = builder.account;
        this.transferAccount = builder.transferAccount;
        this.amount = builder.amount;

    }

    public Account getAccount() {
        return account;
    }

    public String getAccno() {
        return accNo;
    }

    public Account getTransferAccount() {
        return transferAccount;
    }

    public double getAmount() {
        return amount;
    }

    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String accNo;
        private Account account;
        private Account transferAccount;
        private double amount;

        public Builder (String accNo)
        {
            this.accNo =accNo; //compalsury
        }

    public Builder account(Account account) {
        this.account = account;
        return this;
    }

    public Builder transferAccount(Account transferAccount) {
        this.transferAccount = transferAccount;
        return this;
    }

    public Builder amount(double amount)
    {
        this.amount = amount;
        return this;
    }

    public Builder copy(Debit debit){
        this.id = debit.id;
        this.accNo = debit.accNo;
        this.amount = debit.amount;
        this.transferAccount = debit.transferAccount;
        this.account = debit.account;
        return this;
    }


    public Debit build()
    {
        return new Debit(this);
    }


}



}


