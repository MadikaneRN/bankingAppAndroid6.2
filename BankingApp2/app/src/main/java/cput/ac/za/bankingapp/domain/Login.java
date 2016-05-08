package cput.ac.za.bankingapp.domain;

import java.io.Serializable;


/**
 * Created by Scorpian on 2016-04-01.
 */

public class Login implements Serializable{
    private Long id;
    private String userName;
    private String passWord;


    public String getUserName() {
        return userName;
    }

    public Long getId()
    {
        return id;
    }

    public String getPassWord() {
        return passWord;
    }


    public Login(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.passWord = builder.passWord;

    }


    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String passWord;
        private String userName;


        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder userName(String userName)
        {
            this.userName = userName; //compalsury
            return this;
        }

        public Builder passWord(String passWord) {
            this.passWord = passWord;
            return this;
        }


        public Builder copy(Login login){
            this.id = login.id;
            this.userName =login.userName;
            this.passWord = login.passWord;

            return this;
        }

        public Login build()
        {
            return new Login(this);
        }
    }





}
