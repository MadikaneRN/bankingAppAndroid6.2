package cput.ac.za.bankingapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Scorpian on 2016-03-28.
 */

public class Account implements Serializable {

    private Long id;
    private String accNo;
    private double balance;
    private String accountType;



    public Account(Builder builder) {
        this.id = builder.id;
        this.accNo = builder.accNo;
        this.balance = builder.balance;
        this.accountType = builder.accountType;

    }


    public Long getId()
    {
        return id;
    }

    public String getAccNo() {
        return accNo;
    }
    public String getAccountType() {
        return accountType;
    }
    public double getBalance() {
        return balance;
    }


    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String accNo;
        private double balance;
        private String accountType;




        public Builder accNo (String accNo)
        {
            this.accNo = accNo; //compalsury
            return this;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }




        public Builder copy(Account account){
            this.id = account.id;
            this.accNo = account.accNo; //changed from account.accNo;
            this.accountType = account.accountType;
            this.balance = account.balance;
            return this;
        }




        public Account build() {
            return new Account(this);
        }
    }


}
