package br.com.qfaz.interfaces;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Local;
import br.com.qfaz.fragments.LocaisFragments;
import br.com.qfaz.fragments.PesquisaLocaisFragments;

public class LocalActivity extends AppCompatActivity  {

    String cnpj, nome, razao, cep, endereco, numero, latitude, longitude;

    EditText editTextCnpj, editTextRazao, editTextNome, editTextCep, editTextNumero;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        LocaisFragments locaisFragments = new LocaisFragments();

        // Add Fragment to FrameLayout (flContainer), using FragmentManager


        Bundle args = new Bundle();
        args.putString("response", "");
        locaisFragments.setArguments(args);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
        ft.add(R.id.frameLayoutLocal, locaisFragments);                                // add    Fragment
        ft.commit();

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

    }


}
