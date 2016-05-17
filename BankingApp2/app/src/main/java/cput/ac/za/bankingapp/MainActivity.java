package cput.ac.za.bankingapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

import cput.ac.za.bankingapp.domain.Client;
import cput.ac.za.bankingapp.factory.ClientFactory;
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
        Button button_delete = (Button) findViewById(R.id.button_send);
        Button button_update = (Button) findViewById(R.id.button_send);
        Button button_save = (Button) findViewById(R.id.button_send);




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




        button_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button clicko
                ClientRepository repo = new ClientRepositoryImpl(getApplicationContext());

                Client adminSave = ClientFactory.getClient("9110123", "Loki", "Rothman");
                //READ ALL
                Client savedEntity = repo.save(adminSave);

                String data = savedEntity.getId() + "  " + savedEntity.getIdNo() + "  " + savedEntity.getName()
                        + "   " + savedEntity.getSurName();

                for (int i=0; i < 2; i++) {

                    Toast.makeText(getApplicationContext(), "SQL Lite Data\n" + data, Toast.LENGTH_LONG).show();

                }

                id = savedEntity.getId();
            }
        });



        button_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button clicko
                ClientRepository repo = new ClientRepositoryImpl(getApplicationContext());
                //READ ALL
                Set<Client> administrators = repo.findAll();
                String data = "";
                for(Client admin : administrators){
                    data += admin.getId() + "   " + admin.getName() + "\n";
                }

                for (int i=0; i < 2; i++) {

                    Toast.makeText(getApplicationContext(), "SQL Lite Data\n" + data, Toast.LENGTH_LONG).show();

                }
            }
        });






    }
}
