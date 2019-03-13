package br.com.qfaz.interfaces;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import br.com.qfaz.domain.model.Visita;
import br.com.qfaz.fragments.LocaisFragments;
import br.com.qfaz.fragments.VisitasFragments;

public class PrincipalActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;

    List<Visita> visitaList;

    //the recyclerview
    RecyclerView recyclerView;

    VisitasFragments visitasFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);


        visitasFragments = new VisitasFragments();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutVisita, visitasFragments) // replace flContainer
                .addToBackStack(null)
                .commit();



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
                                    .replace(R.id.frameLayoutVisita, locaisFragments) // replace flContainer
                                    .addToBackStack(null)
                                    .commit();

                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.visitas) {
            Intent intentVisita = new Intent(this, VisitaActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.locais) {
            Intent intentVisita = new Intent(this, LocalActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.tickets) {
            Intent intentVisita = new Intent(this, TicketActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.cadastro) {
            Intent intentVisita = new Intent(this, CadastroActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
