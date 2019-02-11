package br.com.qfaz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.qfaz.adapters.VisitaAdapter;
import br.com.qfaz.domain.model.Visita;

public class HomeActivity extends AppCompatActivity {

    //a list to store all the visitas
    List<Visita> visitaList;

    //the recyclerview
    RecyclerView recyclerView;

    Button btnMapa, btnSalvar, btnTicket, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the visitalist
        visitaList = new ArrayList<>();

        visitaList.add(
                new Visita(
                        "1",
                        null,
                        "05/02/2019",
                        "22:15"));


        //creating recyclerview adapter
        VisitaAdapter adapter = new VisitaAdapter(this, visitaList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

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
