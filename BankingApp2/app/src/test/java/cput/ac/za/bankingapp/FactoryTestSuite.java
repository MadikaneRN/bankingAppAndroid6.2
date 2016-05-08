package cput.ac.za.bankingapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import cput.ac.za.bankingapp.factoryTest.AccountTest;
import cput.ac.za.bankingapp.factoryTest.AddressTest;
import cput.ac.za.bankingapp.factoryTest.AirtimeTest;
import cput.ac.za.bankingapp.factoryTest.BankTest;
import cput.ac.za.bankingapp.factoryTest.ClientTest;
import cput.ac.za.bankingapp.factoryTest.DepositTest;
import cput.ac.za.bankingapp.factoryTest.ElectricityTest;
import cput.ac.za.bankingapp.factoryTest.LoanTest;
import cput.ac.za.bankingapp.factoryTest.LoginTest;
import cput.ac.za.bankingapp.factoryTest.StatementTest;
import cput.ac.za.bankingapp.factoryTest.TestDebit;

/**
 * Created by Scorpian on 2016-04-16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountTest.class,
        AddressTest.class,
        AirtimeTest.class,
        BankTest.class,
        ClientTest.class,
        DepositTest.class,
        ElectricityTest.class,
        LoanTest.class,
        LoginTest.class,
        TestDebit.class,
        StatementTest.class,

})


public class FactoryTestSuite {
}
