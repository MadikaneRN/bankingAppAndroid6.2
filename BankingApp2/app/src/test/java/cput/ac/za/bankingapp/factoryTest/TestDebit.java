package cput.ac.za.bankingapp.factoryTest;
import cput.ac.za.bankingapp.factory.DebitFactory;


import cput.ac.za.bankingapp.domain.Account;
import cput.ac.za.bankingapp.domain.Debit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class TestDebit {


    private Debit debit;
    private Account myaccount;
    private Account transferto;

    @Before
    public void setUp() throws Exception {

        debit = DebitFactory.getDebit("mad1991", 6000, myaccount, transferto);

    }

    @Test
    public void testDebit() throws Exception {

        Assert.assertNotNull(debit);
        Assert.assertEquals(debit.getAccno(),"mad1991");
        Assert.assertEquals(debit.getAmount(),6000.00, 0.0);

    }


    @Test
    public void testUpdate() throws Exception {
        Debit debitUpdate = new Debit.Builder(debit.getAccno())// uses accNo as primary key
                .copy(debit)
                .amount(8000)
                .account(myaccount)
                .transferAccount(transferto)
                .build();

        Assert.assertNotNull(debitUpdate);
        Assert.assertEquals(debitUpdate.getAccno(),"mad1991");

        Assert.assertEquals(debitUpdate.getAmount(),8000.00, 0.0);

    }





}
