package cput.ac.za.bankingapp.services.login.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cput.ac.za.bankingapp.conf.database.DomainState;
import cput.ac.za.bankingapp.domain.Login;
import cput.ac.za.bankingapp.repository.login.LoginRepository;
import cput.ac.za.bankingapp.repository.login.impl.LoginRepositoryImpl;
import cput.ac.za.bankingapp.services.login.LoginService;

/**
 * Created by Scorpian on 2016-05-06.
 */
public class LoginServiceImpl extends Service implements LoginService{


    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public boolean isValiduser(Login login) {
        LoginRepository loginRepository = new LoginRepositoryImpl(getBaseContext());
        Login login1 = null;
        if(login1 == null)
            return false;
        else
            return true;
    }




    public class RetrieveAccountInfoLocalBinder extends Binder {
        public LoginServiceImpl getService()
        {
            return  LoginServiceImpl.this;
        }
    }


    /*
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    //private final IBinder localBinder = new ActivateLoginServiceLocalBinder();
    //private LoginRepository loginRepository;



    public class ActivateLoginServiceLocalBinder extends Binder {

        public LoginServiceImpl getService()
        {
            return LoginServiceImpl.this;
        }
    }

    @Override
    public String activateLoginAccount(String username, String password) {

        if(true)
        {
            Login standardUser = new Login.Builder()
                    .userName(username)
                    .passWord(password)
                    .build();


            createLogin(standardUser);

            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }

    }


    @Override
    public boolean isAccountActivated() {
        return loginRepository.findAll().size()>0;
    }


    @Override
    public boolean deactivateAccount() {
        int row = loginRepository.deleteAll();
        return row>0;
    }


    private Login createLogin(Login login)
    {
        loginRepository = new LoginRepositoryImpl(this.getApplicationContext());

        return loginRepository.save(login);
    }

    */



}
