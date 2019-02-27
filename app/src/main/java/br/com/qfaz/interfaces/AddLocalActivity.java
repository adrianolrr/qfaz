package br.com.qfaz.interfaces;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Empresa;
import br.com.qfaz.domain.model.Local;

public class AddLocalActivity extends AppCompatActivity {

    Button btnCarregaEnd, btnSalvaLocal;
    private FirebaseAuth mAuth;
    String URLCORREIOS = "https://viacep.com.br/ws/";
    String URLGOOGLE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String TOKENAPI = "&key=AIzaSyD2yX3k_ViMJKCiwYCbaVx6Tmszw2clYnE";

    EditText editTextCep, editTextCnpj, editTextRazao, editTextNumero, editTextNome;

    String cep, latitude, longitude, end, cidade, estado, bairro, numero, razao, cnpj, nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);

        mAuth = FirebaseAuth.getInstance();

        btnSalvaLocal = findViewById(R.id.btnSalvarLocal);
        btnSalvaLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextCnpj = findViewById(R.id.editTextLocalCnpj);
                editTextRazao = findViewById(R.id.editTextLocalRazao);
                editTextNumero = findViewById(R.id.editTextLocalNumero);
                editTextNome = findViewById(R.id.editTextLocalNome);
                numero = editTextNumero.getText().toString();
                cnpj = editTextCnpj.getText().toString();
                razao = editTextRazao.getText().toString();
                nome = editTextNome.getText().toString();

                String URLG = URLGOOGLE + "+" + end + "+" + numero + "+" + cidade + TOKENAPI;
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, URLG, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());

                               /* Gson converter = new Gson();

                                Type type = new TypeToken<String>(){}.getType();
                                String list = converter.fromJson(response.toString(), type);*/

                                try {
                                    latitude = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
                                    longitude = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();


                                 FirebaseUser user = mAuth.getCurrentUser();

                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                Local local = new Local(cnpj, razao, nome, cep, end, numero, latitude, longitude, cidade, estado, bairro);

                                HashMap<String, Object> resultLocal = (HashMap<String, Object>) local.toMap();

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



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

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

                queue.add(getRequest);
            }
        });

        btnCarregaEnd = findViewById(R.id.btnLocalCarregaEndereco);
        btnCarregaEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                // prepare the Request

                editTextCep = findViewById(R.id.editTextLocalCep);

                cep = editTextCep.getText().toString();


                String URL = URLCORREIOS + cep + "/json";

                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());

                                try {
                                    //json = new JSONArray(response);
                                    end = response.get("logradouro").toString();
                                    cidade = response.get("localidade").toString();
                                    estado = response.get("uf").toString();
                                    bairro = response.get("bairro").toString();



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

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

                queue.add(getRequest);
            }
        });
    }
}
