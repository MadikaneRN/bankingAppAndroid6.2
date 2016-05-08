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


public class Airtime implements Serializable{

    private Long id;
    private String cellphoneNo;
    private String beneficiary;
    private String serviceProvider;


    public Airtime(Builder builder) {
        this.id = builder.id;
        this.cellphoneNo = builder.cellphoneNo;
        this.beneficiary = builder.beneficiary;
        this.serviceProvider = builder.serviceProvider;

    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public String getCellphoneNo() {
        return cellphoneNo;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public Long getId()
    {
        return id;
    }

    public static class Builder {

        private Long id;
        private String cellphoneNo;
        private String beneficiary;
        private String serviceProvider;



        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder cellphoneNo (String cellphoneNo)
        {
            this.cellphoneNo = cellphoneNo;
             return this;//compalsury
        }

        public Builder beneficiary(String beneficiary) {
            this.beneficiary = beneficiary;
            return this;
        }

        public Builder serviceProvider(String serviceProvider) {
            this.serviceProvider = serviceProvider;
            return this;
        }



        public Builder copy(Airtime airtime){
            this.id = airtime.id;
            this.cellphoneNo = airtime.cellphoneNo;
            this.beneficiary = airtime.beneficiary;
            this.serviceProvider = airtime.serviceProvider;
            return this;
        }


        public Airtime build()
        {
            return new Airtime(this);
        }
    }



}
