package cput.ac.za.bankingapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Scorpian on 2016-04-03.
 */


public class Bank implements Serializable{
    private Long id;
    private String code;
    private String name;


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Long getId()
    {
        return  id;
    }

    public Bank(Builder builder) {
        this.id = builder.id;
       this.name = builder.name;
        this.code = builder.code;

    }


    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String code;
        private String name;


        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }


        public Builder code(String code)
        {
            this.code = code; //compalsury
            return this;
        }



        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder copy(Bank bank){
            this.id = bank.id;
            this.name =bank.name;
            this.code = bank.code;
            return this;
        }

        public Bank build() {
            return new Bank(this);
        }
    }



}
