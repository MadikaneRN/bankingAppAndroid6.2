package cput.ac.za.bankingapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Scorpian on 2016-04-01.
 */


public class Electricity implements Serializable{

    private Long id;
    private String meterNo;
    private String supplierName;
    private double amount;

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return  amount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getMeterNo() {
        return meterNo;
    }


    public Electricity(Builder builder) {
        this.id = builder.id;
        this.meterNo = builder.meterNo;
        this.supplierName = builder.supplierName;
        this.amount = builder.amount;
    }



    public static class Builder {

        private Long id;
        private String meterNo;
        private String supplierName;
         private double amount;


        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder meterNo(String meterNo)
        {
            this.meterNo = meterNo; //compalsury
            return this;
        }

        public Builder supplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }


        public Builder amount(double amount)
        {
            this.amount = amount;
            return this;
        }


        public Builder copy(Electricity electricity){
            this.id = electricity.id;
            this.supplierName = electricity.supplierName;
            this.meterNo = electricity.meterNo;
            this.amount = electricity.amount;
            return this;
        }


        public Electricity build()
        {
            return new Electricity(this);
        }

    }



}
