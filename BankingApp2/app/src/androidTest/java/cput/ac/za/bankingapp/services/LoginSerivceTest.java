package cput.ac.za.bankingapp.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import cput.ac.za.bankingapp.domain.Login;
import cput.ac.za.bankingapp.repository.login.LoginRepository;
import cput.ac.za.bankingapp.repository.login.impl.LoginRepositoryImpl;
import cput.ac.za.bankingapp.services.login.LoginService;
import cput.ac.za.bankingapp.services.login.impl.LoginServiceImpl;

/**
 * Created by Scorpian on 2016-05-13.
 */
public class LoginSerivceTest extends AndroidTestCase {


    private LoginService loginService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        Intent intent = new Intent(this.getContext(),LoginServiceImpl.class);
        this.getContext().bindService(intent,connection,Context.BIND_AUTO_CREATE);


    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service)
        {
            LoginServiceImpl.RetrieveAccountInfoLocalBinder binder
                    = (LoginServiceImpl.RetrieveAccountInfoLocalBinder)service;
            loginService = binder.getService();
            isBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            isBound = false;

        }
    };



    public void testLogin()throws Exception
    {
        Login login = new Login.Builder()
                .passWord("123")
                .userName("Madikane")
                .id(new Long(2))
                .build();

        boolean isValid = loginService.isValiduser(login);
        Assert.assertFalse(isValid);
    }



}
