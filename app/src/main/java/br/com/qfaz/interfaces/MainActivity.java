package br.com.qfaz.interfaces;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.qfaz.R;
import br.com.qfaz.adapters.VisitaAdapter;
import br.com.qfaz.domain.model.Local;
import br.com.qfaz.domain.model.Visita;
import br.com.qfaz.fragments.LocaisFragments;
import br.com.qfaz.fragments.VisitasFragments;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    List<Visita> visitaList;

    //the recyclerview
    RecyclerView recyclerView;

    VisitasFragments visitasFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        visitasFragments = new VisitasFragments();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutVisita, visitasFragments) // replace flContainer
                .addToBackStack(null)
                .commit();

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
                        "22:15",
                        ""));

        visitaList.add(
                new Visita(
                        "2",
                        null,
                        "05/02/2019",
                        "22:15",
                        ""));


        visitaList.add(
                new Visita(
                        "3",
                        null,
                        "05/02/2019",
                        "22:15",
                        ""));


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("visitas")
                .document("empresa")
                .collection("111111")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Map<String, Object>> listaLocais = new ArrayList<>();
                            List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                            JSONArray jsonArr = null;
                            List<Visita> lista = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                listaLocais.add(document.getData());

                            }

                            ObjectMapper mapper = new ObjectMapper();
                            String locaisJson = null;
                            try {
                                locaisJson = mapper.writeValueAsString(lista);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }

                            LocaisFragments locaisFragments = new LocaisFragments();
                            Bundle args = new Bundle();
                            args.putString("locais", lista.toString());
                            args.putString("locaisJson", locaisJson);
                            locaisFragments.setArguments(args);

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frameLayoutLocal, locaisFragments) // replace flContainer
                                    .addToBackStack(null)
                                    .commit();

                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });



        //creating recyclerview adapter
        VisitaAdapter adapter = new VisitaAdapter(this, visitaList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        /*if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);*/
        switch (item.getItemId()) {
            case R.id.visitas:
                Intent intentVisita = new Intent(this, VisitaActivity.class);
                startActivity(intentVisita);
                return true;
            case R.id.locais:
                Intent intentLocais = new Intent(this, LocalActivity.class);
                startActivity(intentLocais);
                return true;
            case R.id.cadastro:
                Intent intentCadastro = new Intent(this, CadastroActivity.class);
                startActivity(intentCadastro);
                return true;
            case R.id.tickets:
                Intent intentConfiguracao = new Intent(this, TicketActivity.class);
                startActivity(intentConfiguracao);
                return true;
            case R.id.empresa:
                Intent intentEmpresa = new Intent(this, EmpresaActivity.class);
                startActivity(intentEmpresa);
                return true;
            case R.id.relatorios:
                Intent intentReltorio = new Intent(this, RelatorioActivity.class);
                startActivity(intentReltorio);
                return true;
            case R.id.sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.visitas:
                Intent intentVisita = new Intent(this, VisitaActivity.class);
                startActivity(intentVisita);
                return true;
            case R.id.locais:
                Intent intentLocais = new Intent(this, LocalActivity.class);
                startActivity(intentLocais);
                return true;
            case R.id.cadastro:
                Intent intentCadastro = new Intent(this, CadastroActivity.class);
                startActivity(intentCadastro);
                return true;
            case R.id.tickets:
                Intent intentConfiguracao = new Intent(this, TicketActivity.class);
                startActivity(intentConfiguracao);
                return true;
            case R.id.empresa:
                Intent intentEmpresa = new Intent(this, EmpresaActivity.class);
                startActivity(intentEmpresa);
                return true;
            case R.id.relatorios:
                Intent intentReltorio = new Intent(this, RelatorioActivity.class);
                startActivity(intentReltorio);
                return true;
            case R.id.sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
