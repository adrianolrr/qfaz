package br.com.qfaz.interfaces;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.adapters.VisitaAdapter;
import br.com.qfaz.domain.model.Visita;
import br.com.qfaz.domain.model.Visita;

public class VisitaActivity extends AppCompatActivity {

    //a list to store all the visitas
    List<Visita> visitaList;

    //the recyclerview
    RecyclerView recyclerView;
    
    EditText editTextVisita, editTextHorario, editTextData;
    
    String visita, horario, data;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("friends");

      /*  List<Visita> response = new FirestoreRecyclerOptions.Builder<Visita>()
                .setQuery(query, Visita.class)
                .build();*/

       /* visitaList = (List<Visita>) db.collection(user.getUid())
                .whereEqualTo("visitas", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });*/


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


        Button btnSalvarVisita = findViewById(R.id.btnSalvarVisita);
        btnSalvarVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextVisita = findViewById(R.id.editTextLocalVisita);
                editTextHorario = findViewById(R.id.editTextHorarioVisita);
                editTextData = findViewById(R.id.editTextDataVisita);

                visita = editTextVisita.getText().toString();
                horario = editTextHorario.getText().toString();
                data = editTextData.getText().toString();

                FirebaseUser user = mAuth.getCurrentUser();

                Visita visita = new Visita(user.getUid(), null, data, horario);

                HashMap<String, Object> resultVisita = (HashMap<String, Object>) visita.toMap();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Add a new document with a generated ID
                db.collection(user.getUid())
                        .document("visitas")
                        .collection(data)
                        .document(horario)
                        .set(resultVisita)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Sucesso", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Erro", "Error adding document", e);
                            }
                        });
            }
        });
    }
}
