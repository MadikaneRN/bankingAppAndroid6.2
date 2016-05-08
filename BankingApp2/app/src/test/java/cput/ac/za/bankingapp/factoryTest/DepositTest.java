package cput.ac.za.bankingapp.factoryTest;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cput.ac.za.bankingapp.domain.Deposit;
import cput.ac.za.bankingapp.factory.DebitFactory;
import cput.ac.za.bankingapp.factory.DepositFactory;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class DepositTest {

    private Deposit deposit;

    @Before
    public void setUp() throws Exception {
        deposit = DepositFactory.getDeposit("M100",null,null,1000.0);

    }

    @Test
    public void testDeposit() throws Exception {

        Assert.assertEquals(deposit.getDepositNo(),"M100");
        Assert.assertEquals(deposit.getAmount(),1000.0);

    }

    public void testUpdate() throws Exception
    {
        Deposit depositUpdate = new Deposit.Builder("M100")
                .copy(deposit)
                .fromAccount(null)
                .toAccount(null)
                .amount(5000.0)
                .build();

        Assert.assertEquals(depositUpdate.getDepositNo(),"M100");
        Assert.assertEquals(depositUpdate.getAmount(),5000.0);

    }


}

