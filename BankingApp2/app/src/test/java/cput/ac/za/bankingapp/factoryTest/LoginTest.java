
package cput.ac.za.bankingapp.factoryTest;
import cput.ac.za.bankingapp.domain.Login;
import cput.ac.za.bankingapp.factory.LoginFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class LoginTest {

    private Login login;
    @Before

    public void setUp() throws Exception {

        login = LoginFactory.getLogin("richard", "123");

    }


    @Test
    public void testLogin() throws Exception {

        Assert.assertNotNull(login);
        Assert.assertEquals(login.getUserName(),"richard");
        Assert.assertEquals(login.getPassWord(),"123");


    }
    //not gna use
    @Test
    public void testUpdate() throws Exception {
        Login loginUpdate = new Login.Builder(login.getUserName())// uses accNo as primary key
                .copy(login)
                .passWord("321")
                .build();

        Assert.assertNotNull(loginUpdate);
        Assert.assertEquals(loginUpdate.getPassWord(),"321");


    }

}
