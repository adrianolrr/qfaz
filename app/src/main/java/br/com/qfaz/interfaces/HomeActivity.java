package br.com.qfaz.interfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.qfaz.R;

public class HomeActivity extends AppCompatActivity {



    Button btnMapa, btnSalvar, btnTicket, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        btnMapa = findViewById(R.id.btnMapaHome);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMapa = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(intentMapa);
            }
        });

        btnSalvar = findViewById(R.id.btnSalvarVisita);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnTicket = findViewById(R.id.btnTicketsHome);
       btnTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTicket = new Intent(HomeActivity.this, TicketActivity.class);
                startActivity(intentTicket);
            }
        });

       btnBuscar = findViewById(R.id.btnBuscarEmpresa);
       btnBuscar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentBuscar = new Intent(HomeActivity.this, LocalActivity.class);
               startActivity(intentBuscar);
           }
       });


    }
}
