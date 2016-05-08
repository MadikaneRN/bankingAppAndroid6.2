package cput.ac.za.bankingapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Client;
import cput.ac.za.bankingapp.repository.client.ClientRepository;
import cput.ac.za.bankingapp.repository.client.impl.ClientRepositoryImpl;

public class MainActivity extends Activity {

    String msg = "Android : ";


    private Long id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");

        Button button_readAll = (Button) findViewById(R.id.button_readAll);

        button_readAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                // Do something in response to button clicko
                ClientRepository repo = new ClientRepositoryImpl(getApplicationContext());
                //READ ALL
                Set<Client> clients = repo.findAll();
                String data = "";
                for(Client myClient : clients){
                    data += myClient.getId() + "   " + myClient.getName() + "   "+ myClient.getSurName() +  "\n";
                }

                for (int i=0; i < 2; i++) {

                    Toast.makeText(getApplicationContext(), "SQL Lite Data\n" + data, Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
