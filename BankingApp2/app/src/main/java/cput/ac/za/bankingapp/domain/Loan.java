package cput.ac.za.bankingapp.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by Scorpian on 2016-04-01.
 */


public class Loan implements Serializable{

    private Long id;
    private double salary;
    private boolean qualify;
    private double loanAmount;
    private Date currentDate;

    //private String loanStatus;
    //private String loanType;
    //private String userId;

    public Loan(Builder builder) {

        this.id = builder.id;
        this.salary = builder.salary;
        this.loanAmount = builder.loanAmount;
        this.currentDate = builder.currentDate;
        this.qualify =  builder.qualify;

        //  this.userId = builder.userId;
       //   this.loanStatus = builder.loanStatus;
       //   this.loanType = builder.loanType;

    }

    /*
        public String getLoanStatus() {
        return loanStatus;
        }

    */

    /*
        public String getLoanType() {
        return loanType;
         }
    */

        /*
    public String getUserId() {
        return userId;
    }

    */

    public double getSalary() {
        return salary;
    }

    public boolean getQualify() {
        return qualify;
    }
    public double getLoanAmount() {
        return loanAmount;
    }


    public Date getCurrentDate() {
        return currentDate;
    }


    public Long id()
    {
        return id;
    }

    public static class Builder {

        private Long id;
        private double salary;
        private boolean qualify;
        private double loanAmount;
        private Date currentDate;      //maybeAmmend
       // private String userId;        //ammend
       // private String loanStatus;   //ammend
        //private String loanType;    //ammend




        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

      /*
        public Builder userId(String userId)
        {
            this.userId = userId; //compalsury
            return this;
        }

        public Builder loanStatus(String loanStatus)
        {
            this.loanStatus = loanStatus;
            return this;
        }

        public Builder loanType(String loanType)
        {
            this.loanType = loanType;
            return this;
        }
       */

        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Builder qualify(boolean qualify) {
            this.qualify =qualify;
            return this;
        }

        public Builder loanAmount(double loanAmount) {
            this.loanAmount = loanAmount;
            return this;
        }


        public Builder currentDate(Date currentDate) {
            this.currentDate = currentDate;
            return this;
        }


        public Builder copy(Loan loan){

            this.id = loan.id;
            this.currentDate = loan.currentDate;
            this.loanAmount = loan.loanAmount;
            this.salary = loan.salary;
            this.qualify = loan.qualify;
           // this.loanType = loan.loanType;
           // this.loanStatus = loan.loanStatus;
            //  this.userId = loan.userId;
            return this;
        }


        public Loan build()
        {
            return new Loan(this);
        }
    }




}
