package br.com.qfaz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.adapters.LocalAdapter;
import br.com.qfaz.domain.model.Local;



public class LocaisFragments extends Fragment {

    int position = 0;
    TextView txtValor, txtData, txtApelido, txtRamo;
    String local;
    private List<Local> LocalList;

    ArrayAdapter<Local> listaLocalAdapter;

    String[] arrayFecha;

    ListView listViewLocals;
    ListFragment listFragmentLocals;

    LocalAdapter adapterlocals;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                position = getArguments().getInt("position", 0);
                local = getArguments().getString("local");
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        /*
        Local[] arrayLocal = new Local[1];

        try {
            arrayLocal = mapper.readValue(local, Local[].class);
        } catch (IOException e) {
          e.printStackTrace();
        }*/


        List<Local> list = new ArrayList<>();
        try {
            list = mapper.readValue(local, new TypeReference<List<Local>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.LocalList = list;

        //listaLocalAdapter = new ArrayAdapter<Local>(getContext(),  R.layout.layout_local, R.id.textViewApelido,  list);

        //ListView listviewlocals = findViewById(R.id.lista_local_fechamentos);

        adapterlocals = new LocalAdapter(getContext(), list);
        updateView();
        //listviewlocals.setAdapter(adapterlocals);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_linha_local, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listViewLocals =  view.findViewById(R.id.lvItemsLocal);
        listViewLocals.setAdapter(adapterlocals);

        //listFragmentLocals.setListAdapter(adapterlocals);

    }

    // Activity is calling this to update view on Fragment
    public void updateView(){
        adapterlocals.notifyDataSetChanged();
    }
}
