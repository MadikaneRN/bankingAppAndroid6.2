package cput.ac.za.bankingapp.factoryTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import cput.ac.za.bankingapp.domain.Loan;
import cput.ac.za.bankingapp.factory.LoanFactory;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class LoanTest {

    private Loan loan;

    @Before
    public void setUp() throws Exception {

        loan = LoanFactory.getLoan("Mad1991",10000.0,5000.0,true,null,"qualified","Home");
    }


    @Test
    public void testLoan() throws Exception {

        Assert.assertEquals(loan.getUserId(),"Mad1991");
        Assert.assertEquals(loan.getLoanStatus(),"qualified");
        Assert.assertEquals(loan.getSalary(),5000.0,0.001);
        Assert.assertEquals(loan.getLoanType(),"Home");
    }


    @Test
    public void testUpdate() throws Exception {

        Loan loanUpdate = new Loan.Builder(loan.getUserId())
                .copy(loan)
                .loanStatus("not qualify")
                .salary(1000.0)
                .loanType("Personal")
                .build();

        Assert.assertEquals(loanUpdate.getUserId(),"Mad1991");
        Assert.assertEquals(loanUpdate.getLoanStatus(),"not qualify");
        Assert.assertEquals(loanUpdate.getSalary(),1000.0,0.001);
        Assert.assertEquals(loanUpdate.getLoanType(),"Personal");

    }






}
