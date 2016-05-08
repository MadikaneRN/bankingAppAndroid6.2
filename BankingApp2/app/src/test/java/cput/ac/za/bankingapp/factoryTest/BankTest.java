package cput.ac.za.bankingapp.factoryTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cput.ac.za.bankingapp.domain.Bank;
import cput.ac.za.bankingapp.factory.BankFactory;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class BankTest {

    private Bank bank;

    @Before
    public void setUp() throws Exception {
        bank = BankFactory.getBank("ABSA","5001");

    }

    @Test
    public void testBank() throws Exception {

        Assert.assertEquals(bank.getCode(),"5001");
        Assert.assertEquals(bank.getName(),"ABSA");
    }

    @Test
    public void testUpdate() throws Exception {

        Bank bankUpdate = new Bank.Builder("5001")
                .copy(bank)
                .name("Capitec")
                .build();

        Assert.assertEquals(bankUpdate.getName(),"Capitec");
        Assert.assertEquals(bankUpdate.getCode(),"5001");

    }




}
