package cput.ac.za.bankingapp.services.login;

import cput.ac.za.bankingapp.domain.Login;

/**
 * Created by Scorpian on 2016-05-06.
 */
public interface LoginService {

    String activateLoginAccount(String username, String password);

    boolean isAccountActivated();
    boolean deactivateAccount();



    /*
    boolean isValiduser(Login login);
    */
}
