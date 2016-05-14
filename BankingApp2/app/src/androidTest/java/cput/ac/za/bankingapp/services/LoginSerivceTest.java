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

    private LoginServiceImpl activateLoginService;
    private boolean isBound;
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), LoginServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }



    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            LoginServiceImpl.ActivateLoginServiceLocalBinder binder
                    = (LoginServiceImpl.ActivateLoginServiceLocalBinder)service;
            activateLoginService = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void loginReset() throws Exception
    {
        LoginRepository repo =  new LoginRepositoryImpl(this.getContext());
        // CREATE
        Login createEntity = new Login.Builder()
                .passWord("DarthVader")
                .userName("LukeKram")
                .build();
        Login insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();

    }

    public void testActivateAccount() throws Exception {



        String activate = activateLoginService.activateLoginAccount("LukeKram", "DarthVader");
        Assert.assertEquals("ACTIVATED", activate);

    }


    /*
    public void testDeactivatedAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateLoginService.activateLoginAccount("LukeKram", "DarthVader");

        Boolean deactivated = activateLoginService.deactivateAccount();
        Assert.assertTrue("NOTACTIVATED", deactivated);

    }

    */
    /*
    private LoginService loginService;
    private boolean isbound;


    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), LoginServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }


    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LoginServiceImpl.RetrieveAccountInfoLocalBinder binder
                    = (LoginServiceImpl.RetrieveAccountInfoLocalBinder) service;
            loginService = binder.getService();

            isbound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isbound = false;

        }
    };


    public void testLogin() throws Exception {
        Login login = new Login.Builder()
                .passWord("eminem")
                .userName("madikane")
                .id(new Long(2))
                .build();
        boolean isValid = loginService.isValiduser(login);

        Assert.assertTrue(isValid);


    }

    */

}
