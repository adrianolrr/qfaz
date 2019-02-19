package br.com.qfaz.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import br.com.qfaz.R;
import br.com.qfaz.interfaces.LocalActivity;

public class NovoLocalFragments extends Fragment {
    private static final String URLAPI = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String TOKENAPI = "&key=AIzaSyArIX2Ns0lrBnogBNadDsDa9onybXR5D0s";


    Button btnCarregaEnd, btnSalvaLocal;

    EditText editTextCep, editTextCnpj, editTextRazao, editTextNumero;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnCarregaEnd = getActivity().findViewById(R.id.btnLocalCarregaEndereco);
        btnCarregaEnd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                // prepare the Request
                String cep ="";
                String URL = URLAPI + cep + TOKENAPI;

                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
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

                queue.add(getRequest);
            }
        });

        btnSalvaLocal = getActivity().findViewById(R.id.btnSalvarLocal);
        btnSalvaLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_novo_local, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    // Activity is calling this to update view on Fragment
    public void updateView(){
    }
}
