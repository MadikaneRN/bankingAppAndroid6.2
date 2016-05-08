package cput.ac.za.bankingapp.factoryTest;
import cput.ac.za.bankingapp.domain.Account;




import cput.ac.za.bankingapp.domain.Account;
import cput.ac.za.bankingapp.factory.AccountFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;

/**
 * Created by Scorpian on 2016-03-28.
 */
public class AccountTest {

    private Account account;
    @Before
    public void setUp() throws Exception {

        account = AccountFactory.getAccount("mad1991", 6000, "Savings");

    }


    @Test
    public void testAccount() throws Exception {

        Assert.assertNotNull(account);
        Assert.assertEquals(account.getAccNo(),"mad1991");
        Assert.assertEquals(account.getBalance(),6000.00, 0.0);
        Assert.assertEquals(account.getAccountType(),"Savings");

    }

    @Test
    public void testUpdate() throws Exception {
        Account accountUpdate = new Account.Builder(account.getAccNo())// uses accNo as primary key
                .copy(account)
                .balance(8000)
                .accountType("CheckAccount")
                .build();

        Assert.assertNotNull(accountUpdate);
        Assert.assertEquals(accountUpdate.getAccNo(),"mad1991");

        Assert.assertEquals(accountUpdate.getBalance(),8000.00, 0.0);
        Assert.assertEquals(accountUpdate.getAccountType(),"CheckAccount");

    }
}
