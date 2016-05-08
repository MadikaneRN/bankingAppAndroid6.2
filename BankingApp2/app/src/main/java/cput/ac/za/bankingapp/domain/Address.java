package cput.ac.za.bankingapp.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Created by Scorpian on 2016-04-01.
 */


public class Address implements Serializable{

    private Long id;
    private String streetName;
    private String city;
    private String postalCode;


    public Address(Builder builder) {

        this.id = builder.id;
        this.streetName = builder.streetName;
        this.city = builder.city;
        this.postalCode = builder.postalCode;

    }


    public Long getId()
    {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }


    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String streetName;
        private String city;
        private String postalCode;


        public Builder id(Long value){
            this.id =value;
            return this;
        }


        public Builder streetName(String streetName)
        {
            this.streetName = streetName; //compalsury
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }




        public Builder copy(Address address){
            this.id = address.id;
            this.streetName =address.streetName;
            this.city = address.city;
            this.postalCode =address.postalCode;
            return this;
        }




        public Address build() {
            return new Address(this);
        }
    }



}
