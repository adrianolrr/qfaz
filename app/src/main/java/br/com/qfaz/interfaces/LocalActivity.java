package br.com.qfaz.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Local;
import br.com.qfaz.fragments.LocaisFragments;

public class LocalActivity extends AppCompatActivity  {

    String cnpj, nome, razao, cep, endereco, numero, latitude, longitude, estado, cidade, bairro;

    EditText editTextCnpj, editTextRazao, editTextNome, editTextCep, editTextNumero;

    private int mYear, mMonth, mDay, mHour, mMinute;

    ImageButton btnAddCompany;

    List<Local> listLocais;

    LocaisFragments locaisFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        locaisFragments = new LocaisFragments();




        btnAddCompany = findViewById(R.id.toolbar_button);
        btnAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalActivity.this, AddLocalActivity.class);
                startActivity(intent);
            }
        });
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Gson converter = new Gson();

        final Type type = new TypeToken<String>(){}.getType();


        final List<String> listaLocais = new ArrayList<>();

        CollectionReference docRef = db.collection("locais");//.collection("empresa").document("111111");
        db.collection("locais")
                .document("empresa")
                .collection("111111")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            for(Object item : task.getResult().getData().values())
                                listaLocais.add(item.toString());

                            //List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();


                          /*  Object[] locais = myListOfDocuments.toArray();

                            locaisFragments = new LocaisFragments();

                            Bundle args = new Bundle();
                            //args.putInt("position", position);
                            args.putString("locais", String.valueOf(myListOfDocuments));
                            locaisFragments.setArguments(args);

                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
                            ft.add(R.id.frameLayoutLocal, locaisFragments);                                // add    Fragment
                            ft.commit();


                            Log.d("", myListOfDocuments.toString());*/


                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });



       /* docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("", "No such document");
                    }
                } else {
                    Log.d("", "get failed with ", task.getException());
                }
            }
        });
*/
      /*

        // Add Fragment to FrameLayout (flContainer), using FragmentManager


        Bundle args = new Bundle();
        args.putString("response", "");
        locaisFragments.setArguments(args);



        Button btnSalvarLocal = findViewById(R.id.btnSalvarLocal);
        btnSalvarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextCnpj = findViewById(R.id.editTextLocalCnpj);
                editTextNome = findViewById(R.id.editTextLocalNome);
                editTextRazao = findViewById(R.id.editTextLocalRazao);
                editTextCep = findViewById(R.id.editTextLocalCep);
                editTextNumero = findViewById(R.id.editTextLocalNumero);

                cnpj = editTextCnpj.getText().toString();
                razao = editTextRazao.getText().toString();
                nome = editTextNome.getText().toString();
                cep = editTextCep.getText().toString();
                numero = editTextNumero.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(LocalActivity.this);

                //ALTERAR PARA ENDEREÇO
                //QUEBRAR ENDEREÇO EM ARRAY E ADICIONAR SINAL DE "+" PARA CONCATERNAR PESQUISA
                final String url = "https://maps.googleapis.com/maps/api/geocode/xml?address=" + cep +  "&key==AIzaSyArIX2Ns0lrBnogBNadDsDa9onybXR5D0s";

                // prepare the Request
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error.Response", error.toString());
                            }
                        }
                );

                getRequest.setRetryPolicy(new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                // add it to the RequestQueue
                queue.add(getRequest);


                Local local = new Local(cnpj, razao, nome, cep, endereco, numero, latitude, longitude);

                HashMap<String, Object> resultLocal = (HashMap<String, Object>) local.toMap();

                //myRefEmpresa.setValue(resultLocal);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Add a new document with a generated ID
                db.collection("locais")
                        .document(cnpj)
                        .set(resultLocal)
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


        Button btnCarregaEndereco = findViewById(R.id.btnLocalCarregaEndereco);
        btnCarregaEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextCep = findViewById(R.id.editTextLocalCep);
                cep = editTextCep.getText().toString();

                final String url = "//viaCEP.com.br/ws/" + cep + "/json/?callback=?";

                RequestQueue queue = Volley.newRequestQueue(LocalActivity.this);

                // prepare the Request
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());

                                       // (1) Communicate with Fragment using Bundle


                                PesquisaLocaisFragments pesquisaLocaisFragments = new PesquisaLocaisFragments();

                                Bundle args = new Bundle();
                                args.putString("response", response.toString());
                                pesquisaLocaisFragments.setArguments(args);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.frameLayoutLocal, pesquisaLocaisFragments) // replace flContainer
                                        .addToBackStack(null)
                                        .commit();

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error.Response", error.toString());
                            }
                        }
                );

                getRequest.setRetryPolicy(new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            // add it to the RequestQueue
                queue.add(getRequest);
            }
        });

    }*/

    }
}
