package br.com.qfaz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.qfaz.R;
import br.com.qfaz.adapters.ListLocalAdapter;
import br.com.qfaz.adapters.LocalAdapter;
import br.com.qfaz.domain.model.Local;



public class LocaisFragments extends Fragment implements SearchView.OnQueryTextListener {

    int position = 0;
    TextView txtValor, txtData, txtApelido, txtRamo;
    String local;
    private List<Local> localList;

    ArrayAdapter<Local> listaLocalAdapter;

    String[] arrayFecha;

    ListView listViewLocals;
    ListFragment listFragmentLocals;

    ListLocalAdapter adapterlocals;

    String locaisJson;
    LocalAdapter localAdapter;

    private RecyclerView mRecyclerView;
    private List<Local> mModels;
    List<Local> listLocal;
    ListView listviewlocals;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                local = getArguments().getString("locais");
                locaisJson = getArguments().getString("locaisJson");
            }
        }


        ObjectMapper mapper = new ObjectMapper();
        //List<Local> listLocal = null;
        try {
            listLocal = mapper.readValue(locaisJson, new TypeReference<List<Local>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        //adapterlocals = new ArrayAdapter<String>(getContext(),  android.R.layout.simple_list_item_1, listLocal);
        adapterlocals = new ListLocalAdapter(getContext(), listLocal);
        updateView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_locais, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listviewlocals =  view.findViewById(R.id.lvItemsLocal);
        listviewlocals.setAdapter(adapterlocals);

    }

    // Activity is calling this to update view on Fragment
    public void updateView(){
        adapterlocals.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        localAdapter.filter(text);
        return false;
    }


    private ArrayList<Local> populateList(){

        ArrayList<Local> list = new ArrayList<>();

        for(int i = 0; i < listLocal.size(); i++){
            Local imageModel = new Local();
            imageModel.setNome(listLocal.get(i).getNome());
            list.add(imageModel);
        }

        return list;
    }


}
