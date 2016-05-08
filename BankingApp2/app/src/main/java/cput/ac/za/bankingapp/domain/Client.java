package cput.ac.za.bankingapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Scorpian on 2016-03-28.
 */

public class Client implements Serializable{

    private Long id;
    private String idNo;
    private String name;
    private String surName;


    /*
    @Embedded
    private Address address // Valued Object
    */

    public Client(Builder builder) {

        this.id = builder.id;
        this.idNo = builder.idNo;
        this.surName = builder.surName;
        this.name = builder.name;
    }


    public Long getId()
    {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getIdNo() {
        return idNo;
    }


    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String name;
        private String surName;
        private String idNo;


        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder idNo (String idNo) {
            this.idNo = idNo;
            return this;
        }


        /*
        public Builder (String idNo) {
            this.idNo = idNo; //compalsury
        }
         */

        public Builder surName(String Surname) {
            this.surName = Surname;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }



        public Builder copy(Client client){
            this.id = client.id;
            this.idNo = client.idNo;
            this.name = client.name;
            this.surName = client.surName;
            return this;
        }



        public Client build() {
            return new Client(this);
        }
    }



}
