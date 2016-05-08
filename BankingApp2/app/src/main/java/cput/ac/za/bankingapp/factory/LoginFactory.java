package cput.ac.za.bankingapp.factory;


import cput.ac.za.bankingapp.domain.Login;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class LoginFactory {

    public static Login getLogin(String userName, String passWord)
    {
        Login myLogin= new Login.Builder() // change name to conivinient name
                .userName(userName)
                .passWord(passWord)
                .build();

        return myLogin;
    }

}
