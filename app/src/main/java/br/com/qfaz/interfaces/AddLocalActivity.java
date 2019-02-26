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

    private static final String URLAPI = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String TOKENAPI = "&key=AIzaSyArIX2Ns0lrBnogBNadDsDa9onybXR5D0s";

    EditText editTextCep, editTextCnpj, editTextRazao, editTextNumero, editTextNome;

    String cep, latitude, longitude, end, cidade, estado, bairro, numero, razao, cnpj, nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);

        mAuth = FirebaseAuth.getInstance();

        btnCarregaEnd = findViewById(R.id.btnLocalCarregaEndereco);
        btnCarregaEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                // prepare the Request

                editTextCep = findViewById(R.id.editTextLocalCep);
                editTextCnpj = findViewById(R.id.editTextLocalCnpj);
                editTextRazao = findViewById(R.id.editTextLocalRazao);
                editTextNumero = findViewById(R.id.editTextLocalNumero);
                editTextNome = findViewById(R.id.editTextLocalNome);

                cep = editTextCep.getText().toString();
                numero = editTextNumero.getText().toString();
                cnpj = editTextCnpj.getText().toString();
                razao = editTextRazao.getText().toString();
                nome = editTextNome.getText().toString();

               String URL = URLAPI + cep + "+number+" + numero + TOKENAPI;

                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());

                               /* Gson converter = new Gson();

                                Type type = new TypeToken<String>(){}.getType();
                                String list = converter.fromJson(response.toString(), type);*/

                                JSONArray json = null;
                                String rlestados[] = null;
                                try {
                                    //json = new JSONArray(response);
                                    bairro = response.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(1).get("long_name").toString();
                                    cidade = response.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(2).get("long_name").toString();
                                    estado = response.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(3).get("long_name").toString();



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                               /* FirebaseUser user = mAuth.getCurrentUser();

                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                Local local = new Local(cnpj, razao, nome, cep, end, numero, latitude, longitude, cidade, estado, bairro);

                                HashMap<String, Object> resultLocal = (HashMap<String, Object>) local.toMap();

                                db.collection("empresas")
                                        .document("11111")
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

*/

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
