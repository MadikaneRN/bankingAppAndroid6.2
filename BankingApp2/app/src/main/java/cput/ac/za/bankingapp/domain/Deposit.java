package cput.ac.za.bankingapp.domain;

import android.support.annotation.IdRes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Scorpian on 2016-04-01.
 */


public class Deposit implements Serializable{
    //just values processes elsewhere==use same interface as debit

    private Long id;
    private String depositNo;
    /***RELATIONSHIPS***/
    private Account fromAccount;
    private Account toAccount;
    /***RELATIONSHIps***/
    private double amount;


    public Deposit(Builder builder) {
        this.id = builder.id;
        this.depositNo = builder.depositNo;
        this.amount = builder.amount;
        this.toAccount = builder.toAccount;
        this.fromAccount = builder.fromAccount;

    }

    public String getDepositNo()
    {
        return depositNo;
    }
    public double getAmount() {
        return amount;
    }
    public Account getToAccount() {
        return toAccount;
    }
    public Account getFromAccount() {
        return fromAccount;
    }



    public static class Builder {

        private Long id;
        private String depositNo;
        private Account fromAccount;
        private Account toAccount;
        private double amount;


        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }
        public Builder depositNo(String depositNo)
        {
            this.depositNo = depositNo;
             return this;
        }

        public Builder fromAccount(Account fromAccount) {
            this.fromAccount = fromAccount;
            return this;
        }
        public Builder toAccount(Account toAccount) {
            this.toAccount = toAccount;
            return this;
        }

        public Builder postalCode(Account fromAccount) {
            this.fromAccount = fromAccount;
            return this;
        }

        public Builder amount(double amount)
        {
            this.amount = amount;
            return this;
        }


        public Builder copy(Deposit deposit){
            this.id = deposit.id;
            this.depositNo =deposit.depositNo;
            this.amount = deposit.amount;
            this.fromAccount = deposit.fromAccount;
            this.toAccount = deposit.toAccount;
            return this;
        }



        public Deposit build() {
            return new Deposit(this);
        }
    }




}
