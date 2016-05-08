package cput.ac.za.bankingapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

/**
 * Created by Scorpian on 2016-04-01.
 */


 //user has a statement, statement by itself // keep statement as entity has relationship with client
//Theres a Date

public class Statement implements Serializable{

    private Long id;
    private String details;
	private String weekandDay;
	//might have toString of the relevant classes ie loan balance and account;

    public Statement(Builder builder) {
        this.id = builder.id;
        this.details = builder.details;
        this.weekandDay = builder.weekandDay;

    }

    public Long getId()
    {
        return id;
    }
    public String getWeekandDay()
	{	
		return weekandDay;
	}
    public String getDetails() {
        return details;
    }
    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String weekandDay;
        private String details;



        public Builder details(String details)
        {
            this.details = details; //compalsury
            return this;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }


        public Builder weekandDay(String weekandDay) {
            this.weekandDay = weekandDay;
            return this;
        }



        public Builder copy(Statement statement){
            this.id = statement.id;
            this.details = statement.details;
            this.weekandDay = statement.weekandDay;
            return this;
        }




        public Statement build()
        {
            return new Statement(this);
        }
    }

}
